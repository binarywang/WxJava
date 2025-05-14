package me.chanjar.weixin.mp.api.impl;

import com.google.common.collect.ImmutableMap;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpCardService;
import me.chanjar.weixin.mp.api.WxMpMerchantInvoiceService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.invoice.merchant.*;
import me.chanjar.weixin.mp.enums.WxMpApiUrl;

import java.util.Map;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Invoice.*;


/**
 * @author Mario Luo
 */
@AllArgsConstructor
public class WxMpMerchantInvoiceServiceImpl implements WxMpMerchantInvoiceService {
  private final WxMpService wxMpService;
  private final WxMpCardService wxMpCardService;

  @Override
  public InvoiceAuthPageResult getAuthPageUrl(InvoiceAuthPageRequest params) throws WxErrorException {
    String ticket = wxMpCardService.getCardApiTicket();
    params.setTicket(ticket);
    return this.doCommonInvoiceHttpPost(GET_AUTH_URL, params, InvoiceAuthPageResult.class);
  }

  @Override
  public InvoiceAuthDataResult getAuthData(InvoiceAuthDataRequest params) throws WxErrorException {
    return this.doCommonInvoiceHttpPost(GET_AUTH_DATA, params, InvoiceAuthDataResult.class);
  }

  @Override
  public void rejectInvoice(InvoiceRejectRequest params) throws WxErrorException {
    this.doCommonInvoiceHttpPost(REJECT_INSERT, params, null);
  }

  @Override
  public void makeOutInvoice(MakeOutInvoiceRequest params) throws WxErrorException {
    this.doCommonInvoiceHttpPost(MAKE_OUT_INVOICE, params, null);
  }

  @Override
  public void clearOutInvoice(ClearOutInvoiceRequest params) throws WxErrorException {
    this.doCommonInvoiceHttpPost(CLEAR_OUT_INVOICE, params, null);
  }

  @Override
  public InvoiceResult queryInvoiceInfo(String fpqqlsh, String nsrsbh) throws WxErrorException {
    Map<String, String> data = ImmutableMap.of("fpqqlsh", fpqqlsh, "nsrsbh", nsrsbh);
    return this.doCommonInvoiceHttpPost(QUERY_INVOICE_INFO, data, InvoiceResult.class);
  }

  @Override
  public void setMerchantContactInfo(MerchantContactInfo contact) throws WxErrorException {
    this.doCommonInvoiceHttpPost(SET_CONTACT_SET_BIZ_ATTR, new MerchantContactInfoWrapper(contact), null);
  }

  @Override
  public MerchantContactInfo getMerchantContactInfo() throws WxErrorException {
    MerchantContactInfoWrapper merchantContactInfoWrapper = this.doCommonInvoiceHttpPost(GET_CONTACT_SET_BIZ_ATTR, null, MerchantContactInfoWrapper.class);
    return merchantContactInfoWrapper == null ? null : merchantContactInfoWrapper.getContact();
  }

  @Override
  public void setAuthPageSetting(InvoiceAuthPageSetting authPageSetting) throws WxErrorException {
    this.doCommonInvoiceHttpPost(SET_AUTH_FIELD_SET_BIZ_ATTR, authPageSetting, null);
  }

  @Override
  public InvoiceAuthPageSetting getAuthPageSetting() throws WxErrorException {
    return this.doCommonInvoiceHttpPost(GET_AUTH_FIELD_SET_BIZ_ATTR, new JsonObject(), InvoiceAuthPageSetting.class);
  }

  @Override
  public void setMerchantInvoicePlatform(MerchantInvoicePlatformInfo paymchInfo) throws WxErrorException {
    MerchantInvoicePlatformInfoWrapper data = new MerchantInvoicePlatformInfoWrapper();
    data.setPaymchInfo(paymchInfo);
    this.doCommonInvoiceHttpPost(SET_PAY_MCH_SET_BIZ_ATTR, data, null);
  }

  @Override
  public MerchantInvoicePlatformInfo getMerchantInvoicePlatform(MerchantInvoicePlatformInfo merchantInvoicePlatformInfo) throws WxErrorException {
    MerchantInvoicePlatformInfoWrapper result = this.doCommonInvoiceHttpPost(GET_PAY_MCH_SET_BIZ_ATTR, new JsonObject(), MerchantInvoicePlatformInfoWrapper.class);
    return result == null ? null : result.getPaymchInfo();
  }

  /**
   * 电子发票公用post请求方法
   */
  private <T> T doCommonInvoiceHttpPost(WxMpApiUrl url, Object data, Class<T> resultClass) throws WxErrorException {
    String json = "";
    final Gson gson = this.createGson();
    if (data != null) {
      json = gson.toJson(data);
    }
    String responseText = wxMpService.post(url, json);
    if (resultClass == null) {
      return null;
    }

    return gson.fromJson(responseText, resultClass);
  }

  private Gson createGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
    return gsonBuilder.create();
  }
}
