package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.invoice.InviteUrlResult;
import com.github.binarywang.wxpay.bean.invoice.GeneralInvoiceRequest;
import com.github.binarywang.wxpay.bean.invoice.InvoiceResult;
import com.github.binarywang.wxpay.bean.invoice.InvoiceFileResult;
import com.github.binarywang.wxpay.bean.invoice.ReverseInvoiceRequest;
import com.github.binarywang.wxpay.bean.invoice.SubMerchantInvoiceStatus;
import com.github.binarywang.wxpay.bean.invoice.CardTemplateRequest;
import com.github.binarywang.wxpay.bean.invoice.CardTemplateResult;
import com.github.binarywang.wxpay.bean.invoice.DevelopmentConfigRequest;
import com.github.binarywang.wxpay.bean.invoice.DevelopmentConfigResult;
import com.github.binarywang.wxpay.bean.invoice.TitleUrlRequest;
import com.github.binarywang.wxpay.bean.invoice.TitleUrlResult;
import com.github.binarywang.wxpay.bean.invoice.BuyerInformation;
import com.github.binarywang.wxpay.bean.invoice.IndustryInvoiceRequest;
import com.github.binarywang.wxpay.bean.invoice.InsertCardRequest;
import com.github.binarywang.wxpay.bean.invoice.InviteMerchantQuery;
import com.github.binarywang.wxpay.bean.invoice.InviteMerchantResult;
import com.github.binarywang.wxpay.bean.invoice.InvoiceFileUploadRequest;
import com.github.binarywang.wxpay.bean.invoice.InvoiceFileUploadResult;
import com.github.binarywang.wxpay.v3.WechatPayUploadHttpPost;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.PartnerInvoiceService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.io.UnsupportedEncodingException;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

/**
 * 微信支付服务商电子发票 API 实现。
 *
 * @author binarywang
 */
@RequiredArgsConstructor
public class PartnerInvoiceServiceImpl implements PartnerInvoiceService {
  private static final com.google.gson.Gson GSON = WxGsonBuilder.create();
  private static final String INVITE_URL_PATH = "/v3/new-tax-control-fapiao/fapiaomerchant/getspinviteurl";
  private static final String ISSUE_GENERAL_PATH = "/v3/new-tax-control-fapiao/fapiao-applications/issue-general";
  private static final String FAPIAO_APPLICATIONS_PATH = "/v3/new-tax-control-fapiao/fapiao-applications/";

  private final WxPayService payService;

  @Override
  public InviteUrlResult getInviteUrl(String subMchId) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + INVITE_URL_PATH;
    if (StringUtils.isNotBlank(subMchId)) {
      url += "?sub_mchid=" + encode(subMchId);
    }
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, InviteUrlResult.class);
  }

  @Override
  public void issueGeneralInvoice(GeneralInvoiceRequest request) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + ISSUE_GENERAL_PATH;
    this.payService.postV3(url, GSON.toJson(request));
  }

  @Override
  public InvoiceResult getInvoice(String fapiaoApplyId, String subMchId, String fapiaoId) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + FAPIAO_APPLICATIONS_PATH + encode(fapiaoApplyId) + "?sub_mchid=" + encode(subMchId);
    if (StringUtils.isNotBlank(fapiaoId)) {
      url += "&fapiao_id=" + encode(fapiaoId);
    }
    return GSON.fromJson(this.payService.getV3(url), InvoiceResult.class);
  }

  @Override
  public void reverseInvoice(ReverseInvoiceRequest request) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + FAPIAO_APPLICATIONS_PATH + encode(request.getFapiaoApplyId()) + "/reverse";
    JsonObject body = GSON.toJsonTree(request).getAsJsonObject();
    body.remove("fapiao_apply_id");
    this.payService.postV3(url, GSON.toJson(body));
  }

  @Override
  public InvoiceFileResult getInvoiceFileDownloadInfo(String fapiaoApplyId, String subMchId, String fapiaoId) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + FAPIAO_APPLICATIONS_PATH + encode(fapiaoApplyId) + "/fapiao-files?sub_mchid=" + encode(subMchId);
    if (StringUtils.isNotBlank(fapiaoId)) {
      url += "&fapiao_id=" + encode(fapiaoId);
    }
    return GSON.fromJson(this.payService.getV3(url), InvoiceFileResult.class);
  }

  @Override
  public SubMerchantInvoiceStatus getSubMerchantInvoiceStatus(String subMchId) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + "/v3/new-tax-control-fapiao/merchant/" + encode(subMchId) + "/check-status";
    return GSON.fromJson(this.payService.getV3(url), SubMerchantInvoiceStatus.class);
  }

  @Override
  public CardTemplateResult createCardTemplate(CardTemplateRequest request) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + "/v3/new-tax-control-fapiao/card-template";
    return GSON.fromJson(this.payService.postV3(url, GSON.toJson(request)), CardTemplateResult.class);
  }

  @Override
  public DevelopmentConfigResult updateDevelopmentConfig(DevelopmentConfigRequest request) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + "/v3/new-tax-control-fapiao/merchant/development-config";
    return GSON.fromJson(this.payService.putV3(url, GSON.toJson(request)), DevelopmentConfigResult.class);
  }

  @Override
  public TitleUrlResult getUserTitleUrl(TitleUrlRequest request) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + "/v3/new-tax-control-fapiao/user-title/title-url?"
      + query("sub_mchid", request.getSubMchid()) + "&" + query("fapiao_apply_id", request.getFapiaoApplyId())
      + "&" + query("source", request.getSource()) + "&" + query("appid", request.getAppid())
      + "&" + query("openid", request.getOpenid()) + "&" + query("total_amount", request.getTotalAmount());
    url = appendQuery(url, "seller_name", request.getSellerName());
    url = appendQuery(url, "show_phone_cell", request.getShowPhoneCell());
    url = appendQuery(url, "must_input_phone", request.getMustInputPhone());
    url = appendQuery(url, "show_email_cell", request.getShowEmailCell());
    url = appendQuery(url, "must_input_email", request.getMustInputEmail());
    return GSON.fromJson(this.payService.getV3(url), TitleUrlResult.class);
  }

  @Override
  public BuyerInformation getUserTitle(String subMchId, String scene, String fapiaoApplyId) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + "/v3/new-tax-control-fapiao/user-title?" + query("sub_mchid", subMchId)
      + "&" + query("scene", scene) + "&" + query("fapiao_apply_id", fapiaoApplyId);
    return GSON.fromJson(this.payService.getV3(url), BuyerInformation.class);
  }

  @Override
  public void issueRealEstateLeasingInvoice(IndustryInvoiceRequest request) throws WxPayException {
    this.payService.postV3(this.payService.getPayBaseUrl() + "/v3/new-tax-control-fapiao/fapiao-applications/real-estate-leasing", GSON.toJson(request));
  }

  @Override
  public void issueRefinedOilInvoice(IndustryInvoiceRequest request) throws WxPayException {
    this.payService.postV3(this.payService.getPayBaseUrl() + "/v3/new-tax-control-fapiao/fapiao-applications/issue-refined-oil", GSON.toJson(request));
  }

  @Override
  public void insertCards(InsertCardRequest request) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + FAPIAO_APPLICATIONS_PATH + encode(request.getFapiaoApplyId()) + "/insert-cards";
    JsonObject body = GSON.toJsonTree(request).getAsJsonObject();
    body.remove("fapiao_apply_id");
    this.payService.postV3(url, GSON.toJson(body));
  }

  @Override
  public InviteMerchantResult listInviteMerchants(InviteMerchantQuery query) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + "/v3/new-tax-control-fapiao/fapiaomerchant/listspinvitemchinfo"
      + "?" + query("query_time_start", query.getQueryTimeStart()) + "&" + query("query_time_end", query.getQueryTimeEnd())
      + "&" + query("offset", query.getOffset()) + "&" + query("limit", query.getLimit()) + "&" + query("mch_invite_status", query.getMchInviteStatus());
    if (StringUtils.isNotBlank(query.getInviteCode())) {
      url += "&invite_code=" + encode(query.getInviteCode());
    }
    return GSON.fromJson(this.payService.getV3(url), InviteMerchantResult.class);
  }

  @Override
  public InvoiceFileUploadResult uploadInvoiceFile(InvoiceFileUploadRequest request) throws WxPayException, IOException {
    String url = this.payService.getPayBaseUrl() + FAPIAO_APPLICATIONS_PATH + "upload-fapiao-file";
    JsonObject meta = new JsonObject();
    meta.addProperty("sub_mchid", request.getSubMchid());
    meta.addProperty("file_type", request.getFileType());
    // 微信支付官方接口字段即为 digest_alogrithm（文档中的既定拼写）。
    meta.addProperty("digest_alogrithm", request.getDigestAlogrithm());
    meta.addProperty("digest", request.getDigest());
    try (FileInputStream inputStream = new FileInputStream(request.getFile())) {
      WechatPayUploadHttpPost post = new WechatPayUploadHttpPost.Builder(URI.create(url))
        .withFapiaoFile(request.getFile().getName(), GSON.toJson(meta), inputStream).buildFapiaoFile();
      return GSON.fromJson(this.payService.postV3(url, post), InvoiceFileUploadResult.class);
    }
  }

  private static String query(String key, Object value) {
    return key + "=" + encode(value);
  }

  private static String appendQuery(String url, String key, Object value) {
    return value == null ? url : url + "&" + query(key, value);
  }

  private static String encode(Object value) {
    if (value == null) {
      throw new IllegalArgumentException("微信支付接口必填参数不能为空");
    }
    try {
      return URLEncoder.encode(String.valueOf(value), StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException(e);
    }
  }
}
