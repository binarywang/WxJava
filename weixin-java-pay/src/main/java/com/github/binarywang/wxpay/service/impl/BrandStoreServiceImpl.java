package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.brandstore.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.BrandStoreService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/** 品牌门店接口实现。 */
@RequiredArgsConstructor
public class BrandStoreServiceImpl implements BrandStoreService {
  private static final Gson GSON = new Gson();
  private static final String PATH = "/v3/brand/partner/store/brandstores";
  private final WxPayService wxPayService;

  @Override
  public BrandStoreResult create(BrandStoreRequest request) throws WxPayException {
    return GSON.fromJson(wxPayService.postV3(wxPayService.getPayBaseUrl() + PATH, GSON.toJson(request)), BrandStoreResult.class);
  }

  @Override
  public BrandStoreResult query(String brandId, String storeId) throws WxPayException {
    return GSON.fromJson(wxPayService.getV3(storeUrl(storeId) + "?brand_id=" + encode(brandId)), BrandStoreResult.class);
  }

  @Override
  public BrandStoreResult update(String storeId, BrandStoreRequest request) throws WxPayException {
    return GSON.fromJson(wxPayService.patchV3(storeUrl(storeId), GSON.toJson(request)), BrandStoreResult.class);
  }

  @Override
  public void delete(String brandId, String storeId) throws WxPayException {
    String url = storeUrl(storeId);
    // 删除接口的 brand_id 位于 JSON 请求体，普通 HttpDelete 不支持实体。
    HttpEntityEnclosingRequestBase request = new HttpEntityEnclosingRequestBase() {
      @Override
      public String getMethod() {
        return "DELETE";
      }
    };
    request.setURI(URI.create(url));
    request.setHeader("Accept", "application/json");
    JsonObject body = new JsonObject();
    body.addProperty("brand_id", brandId);
    request.setEntity(new StringEntity(GSON.toJson(body), ContentType.APPLICATION_JSON));
    wxPayService.requestV3(url, request);
  }

  @Override
  public BrandStoreBindRecipientResult bindRecipient(String storeId, BrandStoreBindRecipientRequest request) throws WxPayException {
    return GSON.fromJson(wxPayService.postV3(storeUrl(storeId) + "/bindrecipient", GSON.toJson(request)), BrandStoreBindRecipientResult.class);
  }

  @Override
  public BrandStoreUnbindRecipientResult unbindRecipient(String storeId, BrandStoreUnbindRecipientRequest request) throws WxPayException {
    return GSON.fromJson(wxPayService.postV3(storeUrl(storeId) + "/unbindrecipient", GSON.toJson(request)), BrandStoreUnbindRecipientResult.class);
  }

  private String storeUrl(String storeId) {
    return wxPayService.getPayBaseUrl() + PATH + "/" + encode(storeId);
  }

  private static String encode(String value) {
    if (value == null) {
      throw new IllegalArgumentException("微信支付接口必填参数不能为空");
    }
    try {
      return URLEncoder.encode(value, StandardCharsets.UTF_8.name()).replace("+", "%20");
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException(e);
    }
  }
}
