package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.invoice.InviteUrlResult;
import com.github.binarywang.wxpay.bean.invoice.GeneralInvoiceRequest;
import com.github.binarywang.wxpay.bean.invoice.InvoiceResult;
import com.github.binarywang.wxpay.bean.invoice.InvoiceFileResult;
import com.github.binarywang.wxpay.bean.invoice.ReverseInvoiceRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.PartnerInvoiceService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * 微信支付服务商电子发票 API 实现。
 *
 * @author binarywang
 */
@RequiredArgsConstructor
public class PartnerInvoiceServiceImpl implements PartnerInvoiceService {
  private static final Gson GSON = new Gson();
  private static final String INVITE_URL_PATH = "/v3/new-tax-control-fapiao/fapiaomerchant/getspinviteurl";
  private static final String ISSUE_GENERAL_PATH = "/v3/new-tax-control-fapiao/fapiao-applications/issue-general";
  private static final String FAPIAO_APPLICATIONS_PATH = "/v3/new-tax-control-fapiao/fapiao-applications/";

  private final WxPayService payService;

  @Override
  public InviteUrlResult getInviteUrl(String subMchId) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + INVITE_URL_PATH;
    if (StringUtils.isNotBlank(subMchId)) {
      url += "?sub_mchid=" + subMchId;
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
    String url = this.payService.getPayBaseUrl() + FAPIAO_APPLICATIONS_PATH + fapiaoApplyId + "?sub_mchid=" + subMchId;
    if (StringUtils.isNotBlank(fapiaoId)) {
      url += "&fapiao_id=" + fapiaoId;
    }
    return GSON.fromJson(this.payService.getV3(url), InvoiceResult.class);
  }

  @Override
  public void reverseInvoice(ReverseInvoiceRequest request) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + FAPIAO_APPLICATIONS_PATH + request.getFapiaoApplyId() + "/reverse";
    JsonObject body = GSON.toJsonTree(request).getAsJsonObject();
    body.remove("fapiao_apply_id");
    this.payService.postV3(url, GSON.toJson(body));
  }

  @Override
  public InvoiceFileResult getInvoiceFileDownloadInfo(String fapiaoApplyId, String subMchId, String fapiaoId) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + FAPIAO_APPLICATIONS_PATH + fapiaoApplyId + "/fapiao-files?sub_mchid=" + subMchId;
    if (StringUtils.isNotBlank(fapiaoId)) {
      url += "&fapiao_id=" + fapiaoId;
    }
    return GSON.fromJson(this.payService.getV3(url), InvoiceFileResult.class);
  }
}
