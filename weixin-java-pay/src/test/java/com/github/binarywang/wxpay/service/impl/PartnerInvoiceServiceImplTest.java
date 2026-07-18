package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.bean.invoice.GeneralInvoiceRequest;
import com.github.binarywang.wxpay.bean.invoice.InvoiceResult;
import com.github.binarywang.wxpay.bean.invoice.ReverseInvoiceRequest;
import com.github.binarywang.wxpay.bean.invoice.InvoiceFileResult;
import com.github.binarywang.wxpay.bean.invoice.SubMerchantInvoiceStatus;
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

  @Test
  public void shouldQueryInvoiceWithRequiredSubMchId() throws Exception {
    AtomicReference<String> requestedUrl = new AtomicReference<>();
    WxPayService payService = (WxPayService) Proxy.newProxyInstance(
      getClass().getClassLoader(), new Class[]{WxPayService.class}, (proxy, method, args) -> {
        if ("getPayBaseUrl".equals(method.getName())) {
          return "https://api.mch.weixin.qq.com";
        }
        if ("getV3".equals(method.getName())) {
          requestedUrl.set((String) args[0]);
          return "{\"total_count\":1,\"fapiao_information\":[{\"fapiao_id\":\"fapiao-001\",\"status\":\"ISSUED\"}]}";
        }
        throw new UnsupportedOperationException(method.getName());
      });

    InvoiceResult result = new PartnerInvoiceServiceImpl(payService)
      .getInvoice("apply-001", "1900000109", "fapiao-001");

    Assert.assertEquals(requestedUrl.get(), "https://api.mch.weixin.qq.com/v3/new-tax-control-fapiao/fapiao-applications/apply-001?sub_mchid=1900000109&fapiao_id=fapiao-001");
    Assert.assertEquals(result.getTotalCount(), Integer.valueOf(1));
    Assert.assertEquals(result.getFapiaoInformation().get(0).getStatus(), "ISSUED");
  }

  @Test
  public void shouldReverseInvoiceUsingPathApplyId() throws Exception {
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
    ReverseInvoiceRequest request = new ReverseInvoiceRequest();
    request.setFapiaoApplyId("apply-001");
    request.setSubMchid("1900000109");
    request.setReverseReason("SALES_RETURN");

    new PartnerInvoiceServiceImpl(payService).reverseInvoice(request);

    Assert.assertEquals(requestedUrl.get(),
      "https://api.mch.weixin.qq.com/v3/new-tax-control-fapiao/fapiao-applications/apply-001/reverse");
    Assert.assertTrue(requestedBody.get().contains("\"sub_mchid\":\"1900000109\""));
    Assert.assertFalse(requestedBody.get().contains("fapiao_apply_id"));
  }

  @Test
  public void shouldGetInvoiceFileDownloadInfo() throws Exception {
    AtomicReference<String> requestedUrl = new AtomicReference<>();
    WxPayService payService = (WxPayService) Proxy.newProxyInstance(
      getClass().getClassLoader(), new Class[]{WxPayService.class}, (proxy, method, args) -> {
        if ("getPayBaseUrl".equals(method.getName())) {
          return "https://api.mch.weixin.qq.com";
        }
        if ("getV3".equals(method.getName())) {
          requestedUrl.set((String) args[0]);
          return "{\"fapiao_download_info_list\":[{\"fapiao_id\":\"fapiao-001\",\"download_url\":\"https://download.example.com/file\",\"status\":\"ISSUED\"}]}";
        }
        throw new UnsupportedOperationException(method.getName());
      });

    InvoiceFileResult result = new PartnerInvoiceServiceImpl(payService)
      .getInvoiceFileDownloadInfo("apply-001", "1900000109", "fapiao-001");

    Assert.assertEquals(requestedUrl.get(), "https://api.mch.weixin.qq.com/v3/new-tax-control-fapiao/fapiao-applications/apply-001/fapiao-files?sub_mchid=1900000109&fapiao_id=fapiao-001");
    Assert.assertEquals(result.getFapiaoDownloadInfoList().get(0).getDownloadUrl(), "https://download.example.com/file");
  }

  @Test
  public void shouldCheckSubMerchantInvoiceStatus() throws Exception {
    AtomicReference<String> requestedUrl = new AtomicReference<>();
    WxPayService payService = (WxPayService) Proxy.newProxyInstance(
      getClass().getClassLoader(), new Class[]{WxPayService.class}, (proxy, method, args) -> {
        if ("getPayBaseUrl".equals(method.getName())) {
          return "https://api.mch.weixin.qq.com";
        }
        if ("getV3".equals(method.getName())) {
          requestedUrl.set((String) args[0]);
          return "{\"sub_mchid\":\"1900000109\",\"third_mode\":{\"status\":\"ENABLED\"}}";
        }
        throw new UnsupportedOperationException(method.getName());
      });

    SubMerchantInvoiceStatus result = new PartnerInvoiceServiceImpl(payService).getSubMerchantInvoiceStatus("1900000109");

    Assert.assertEquals(requestedUrl.get(), "https://api.mch.weixin.qq.com/v3/new-tax-control-fapiao/merchant/1900000109/check-status");
    Assert.assertEquals(result.getThirdMode().getStatus(), "ENABLED");
  }
}
