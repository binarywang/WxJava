package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.bean.invoice.GeneralInvoiceRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicReference;

/**
 * {@link PartnerInvoiceServiceImpl} 测试。
 */
public class PartnerInvoiceServiceImplTest {

  @Test
  public void shouldRequestInviteUrlWithOptionalSubMchId() throws Exception {
    AtomicReference<String> requestedUrl = new AtomicReference<>();
    WxPayService payService = (WxPayService) Proxy.newProxyInstance(
      getClass().getClassLoader(), new Class[]{WxPayService.class}, (proxy, method, args) -> {
        if ("getPayBaseUrl".equals(method.getName())) {
          return "https://api.mch.weixin.qq.com";
        }
        if ("getV3".equals(method.getName())) {
          requestedUrl.set((String) args[0]);
          return "{\"invite_url\":\"https://wxacurl.cn?xxx=\"}";
        }
        throw new UnsupportedOperationException(method.getName());
      });

    PartnerInvoiceServiceImpl service = new PartnerInvoiceServiceImpl(payService);

    Assert.assertEquals(service.getInviteUrl("19998278783").getInviteUrl(), "https://wxacurl.cn?xxx=");
    Assert.assertEquals(requestedUrl.get(),
      "https://api.mch.weixin.qq.com/v3/new-tax-control-fapiao/fapiaomerchant/getspinviteurl?sub_mchid=19998278783");
  }

  @Test
  public void shouldPostGeneralInvoiceToV3Endpoint() throws Exception {
    AtomicReference<String> requestedUrl = new AtomicReference<>();
    AtomicReference<String> requestedBody = new AtomicReference<>();
    WxPayService payService = (WxPayService) Proxy.newProxyInstance(
      getClass().getClassLoader(), new Class[]{WxPayService.class}, (proxy, method, args) -> {
        if ("getPayBaseUrl".equals(method.getName())) {
          return "https://api.mch.weixin.qq.com";
        }
        if ("postV3".equals(method.getName())) {
          requestedUrl.set((String) args[0]);
          requestedBody.set((String) args[1]);
          return "";
        }
        throw new UnsupportedOperationException(method.getName());
      });
    GeneralInvoiceRequest request = new GeneralInvoiceRequest();
    request.setSubMchid("1900000109");
    request.setFapiaoApplyId("invoice-001");

    new PartnerInvoiceServiceImpl(payService).issueGeneralInvoice(request);

    Assert.assertEquals(requestedUrl.get(),
      "https://api.mch.weixin.qq.com/v3/new-tax-control-fapiao/fapiao-applications/issue-general");
    Assert.assertTrue(requestedBody.get().contains("\"fapiao_apply_id\":\"invoice-001\""));
  }
}
