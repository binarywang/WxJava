package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.invoice.InviteUrlResult;
import com.github.binarywang.wxpay.bean.invoice.GeneralInvoiceRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.PartnerInvoiceService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.gson.Gson;
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
}
