package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.transfer.ReservationTransferBatchRequest;
import com.github.binarywang.wxpay.bean.transfer.ReservationTransferBatchGetResult;
import com.github.binarywang.wxpay.bean.transfer.ReservationTransferBatchResult;
import com.github.binarywang.wxpay.bean.transfer.UserAuthorizationStatusResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.gson.Gson;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;

/**
 * 用户授权免确认相关接口 API 路径兼容性测试
 * <p>
 * 通过动态代理拦截 WxPayService 调用，验证各接口的请求路径和参数是否符合官方文档要求，
 * 无需真实微信 API 凭据即可运行。
 * </p>
 *
 * @author GitHub Copilot
 */
@Test
public class TransferAuthorizationApiCompatibilityTest {

  private static final String BASE_URL = "https://api.mch.weixin.qq.com";

  /**
   * 验证查询用户授权状态接口使用正确的 API 路径和查询参数。
   */
  public void shouldGetUserAuthorizationStatusWithCorrectPath() throws WxPayException {
    RequestCaptureHandler handler = new RequestCaptureHandler();
    WxPayService wxPayService = handler.createWxPayService();
    TransferServiceImpl transferService = new TransferServiceImpl(wxPayService);

    transferService.getUserAuthorizationStatus("oX_7Jzr9gSZz4X_Xc9-_7HGf8XzI", "1005");

    Assert.assertEquals(handler.lastGetUrl,
      BASE_URL + "/v3/fund-app/mch-transfer/authorization/openid/oX_7Jzr9gSZz4X_Xc9-_7HGf8XzI?transfer_scene_id=1005");
  }

  /**
   * 验证查询用户授权状态接口返回结果可正确反序列化。
   */
  public void shouldDeserializeUserAuthorizationStatusResult() {
    Gson gson = new Gson();
    String json = "{\"appid\":\"wxf636efh5xxxxx\",\"mch_id\":\"1900000109\","
      + "\"openid\":\"oX_7Jzr9gSZz4X_Xc9-_7HGf8XzI\","
      + "\"authorization_state\":\"AUTHORIZED\","
      + "\"authorize_time\":\"2024-01-01T10:00:00+08:00\","
      + "\"deauthorize_time\":null}";
    UserAuthorizationStatusResult result = gson.fromJson(json, UserAuthorizationStatusResult.class);

    Assert.assertEquals(result.getAppid(), "wxf636efh5xxxxx");
    Assert.assertEquals(result.getMchId(), "1900000109");
    Assert.assertEquals(result.getOpenid(), "oX_7Jzr9gSZz4X_Xc9-_7HGf8XzI");
    Assert.assertEquals(result.getAuthorizationState(), "AUTHORIZED");
    Assert.assertEquals(result.getAuthorizeTime(), "2024-01-01T10:00:00+08:00");
  }

  /**
   * 验证批量预约商家转账接口使用正确的 API 路径。
   */
  public void shouldReservationTransferBatchUseCorrectPath() throws WxPayException {
    RequestCaptureHandler handler = new RequestCaptureHandler();
    WxPayService wxPayService = handler.createWxPayService();
    TransferServiceImpl transferService = new TransferServiceImpl(wxPayService);

    ReservationTransferBatchRequest request = ReservationTransferBatchRequest.newBuilder()
      .appid("wxf636efh5xxxxx")
      .outBatchNo("BATCH20240101001")
      .transferSceneId("1005")
      .batchRemark("测试批量预约转账")
      .totalAmount(1000)
      .totalNum(1)
      .transferDetailList(Collections.singletonList(
        ReservationTransferBatchRequest.TransferDetail.newBuilder()
          .outDetailNo("detail001")
          .transferAmount(1000)
          .transferRemark("测试转账")
          .openid("oX_7Jzr9gSZz4X_Xc9-_7HGf8XzI")
          // 不设置 userName，避免触发证书加密流程
          .build()
      ))
      .notifyUrl("https://example.com/notify")
      .build();

    transferService.reservationTransferBatch(request);

    Assert.assertEquals(handler.lastPostUrl,
      BASE_URL + "/v3/fund-app/mch-transfer/reservation/transfer-batches");
    Assert.assertTrue(handler.lastPostBody.contains("\"out_batch_no\""));
    Assert.assertTrue(handler.lastPostBody.contains("BATCH20240101001"));
    Assert.assertTrue(handler.lastPostBody.contains("\"transfer_detail_list\""));
  }

  /**
   * 验证批量预约商家转账响应结果可正确反序列化。
   */
  public void shouldDeserializeReservationTransferBatchResult() {
    Gson gson = new Gson();
    String json = "{\"out_batch_no\":\"BATCH20240101001\","
      + "\"reservation_batch_no\":\"1030000071100999991182020050700019480001\","
      + "\"create_time\":\"2024-01-01T10:00:00+08:00\","
      + "\"batch_state\":\"ACCEPTED\"}";
    ReservationTransferBatchResult result = gson.fromJson(json, ReservationTransferBatchResult.class);

    Assert.assertEquals(result.getOutBatchNo(), "BATCH20240101001");
    Assert.assertEquals(result.getReservationBatchNo(), "1030000071100999991182020050700019480001");
    Assert.assertEquals(result.getBatchState(), "ACCEPTED");
    Assert.assertEquals(result.getCreateTime(), "2024-01-01T10:00:00+08:00");
  }

  /**
   * 验证商户预约批次单号查询接口使用正确的 API 路径和查询参数。
   */
  public void shouldGetReservationBatchByOutBatchNoWithCorrectPath() throws WxPayException {
    RequestCaptureHandler handler = new RequestCaptureHandler();
    WxPayService wxPayService = handler.createWxPayService();
    TransferServiceImpl transferService = new TransferServiceImpl(wxPayService);

    transferService.getReservationTransferBatchByOutBatchNo(
      "BATCH20240101001", true, 0, 20, "SUCCESS");

    Assert.assertTrue(handler.lastGetUrl.contains(
      "/v3/fund-app/mch-transfer/reservation/transfer-batches/out-batch-no/BATCH20240101001"));
    Assert.assertTrue(handler.lastGetUrl.contains("need_query_detail=true"));
    Assert.assertTrue(handler.lastGetUrl.contains("offset=0"));
    Assert.assertTrue(handler.lastGetUrl.contains("limit=20"));
    Assert.assertTrue(handler.lastGetUrl.contains("detail_state=SUCCESS"));
  }

  /**
   * 验证不携带可选参数时商户预约批次单号查询接口仍能正确构造 URL。
   */
  public void shouldGetReservationBatchByOutBatchNoWithoutOptionalParams() throws WxPayException {
    RequestCaptureHandler handler = new RequestCaptureHandler();
    WxPayService wxPayService = handler.createWxPayService();
    TransferServiceImpl transferService = new TransferServiceImpl(wxPayService);

    transferService.getReservationTransferBatchByOutBatchNo(
      "BATCH20240101001", false, null, null, null);

    Assert.assertTrue(handler.lastGetUrl.contains(
      "/v3/fund-app/mch-transfer/reservation/transfer-batches/out-batch-no/BATCH20240101001"));
    Assert.assertTrue(handler.lastGetUrl.contains("need_query_detail=false"));
    Assert.assertFalse(handler.lastGetUrl.contains("offset="));
    Assert.assertFalse(handler.lastGetUrl.contains("limit="));
    Assert.assertFalse(handler.lastGetUrl.contains("detail_state="));
  }

  /**
   * 验证微信预约批次单号查询接口使用正确的 API 路径和查询参数。
   */
  public void shouldGetReservationBatchByReservationBatchNoWithCorrectPath() throws WxPayException {
    RequestCaptureHandler handler = new RequestCaptureHandler();
    WxPayService wxPayService = handler.createWxPayService();
    TransferServiceImpl transferService = new TransferServiceImpl(wxPayService);

    transferService.getReservationTransferBatchByReservationBatchNo(
      "1030000071100999991182020050700019480001", true, 0, 20, "PROCESSING");

    Assert.assertTrue(handler.lastGetUrl.contains(
      "/v3/fund-app/mch-transfer/reservation/transfer-batches/reservation-batch-no/"
        + "1030000071100999991182020050700019480001"));
    Assert.assertTrue(handler.lastGetUrl.contains("need_query_detail=true"));
    Assert.assertTrue(handler.lastGetUrl.contains("offset=0"));
    Assert.assertTrue(handler.lastGetUrl.contains("limit=20"));
    Assert.assertTrue(handler.lastGetUrl.contains("detail_state=PROCESSING"));
  }

  /**
   * 验证批次查询结果可正确反序列化（包括明细列表）。
   */
  public void shouldDeserializeReservationBatchGetResult() {
    Gson gson = new Gson();
    String json = "{\"mch_id\":\"1900000109\","
      + "\"out_batch_no\":\"BATCH20240101001\","
      + "\"reservation_batch_no\":\"1030000071100999991182020050700019480001\","
      + "\"appid\":\"wxf636efh5xxxxx\","
      + "\"batch_state\":\"FINISHED\","
      + "\"total_amount\":1000,"
      + "\"total_num\":1,"
      + "\"success_amount\":1000,"
      + "\"success_num\":1,"
      + "\"fail_amount\":0,"
      + "\"fail_num\":0,"
      + "\"transfer_detail_list\":["
      + "{\"out_detail_no\":\"detail001\","
      + "\"transfer_bill_no\":\"bill001\","
      + "\"detail_state\":\"SUCCESS\"}"
      + "]}";
    ReservationTransferBatchGetResult result = gson.fromJson(json, ReservationTransferBatchGetResult.class);

    Assert.assertEquals(result.getMchId(), "1900000109");
    Assert.assertEquals(result.getBatchState(), "FINISHED");
    Assert.assertEquals(result.getTotalAmount(), Integer.valueOf(1000));
    Assert.assertEquals(result.getSuccessNum(), Integer.valueOf(1));
    Assert.assertNotNull(result.getTransferDetailList());
    Assert.assertEquals(result.getTransferDetailList().size(), 1);
    Assert.assertEquals(result.getTransferDetailList().get(0).getDetailState(), "SUCCESS");
  }

  /**
   * 验证关闭预约商家转账批次接口使用正确的 API 路径。
   */
  public void shouldCloseReservationTransferBatchWithCorrectPath() throws WxPayException {
    RequestCaptureHandler handler = new RequestCaptureHandler();
    WxPayService wxPayService = handler.createWxPayService();
    TransferServiceImpl transferService = new TransferServiceImpl(wxPayService);

    transferService.closeReservationTransferBatch("BATCH20240101001");

    Assert.assertEquals(handler.lastPostUrl,
      BASE_URL + "/v3/fund-app/mch-transfer/reservation/transfer-batches/out-batch-no/BATCH20240101001/close");
    Assert.assertEquals(handler.lastPostBody, "");
  }

  /**
   * 通过动态代理拦截 WxPayService 请求并记录 URL/请求体，便于断言接口路径和参数。
   */
  private static class RequestCaptureHandler implements InvocationHandler {
    private String lastPostUrl;
    private String lastPostBody;
    private String lastGetUrl;

    private WxPayService createWxPayService() {
      return (WxPayService) Proxy.newProxyInstance(
        WxPayService.class.getClassLoader(),
        new Class<?>[]{WxPayService.class},
        this
      );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
      if ("getPayBaseUrl".equals(method.getName())) {
        return BASE_URL;
      }
      if ("postV3".equals(method.getName())) {
        this.lastPostUrl = (String) args[0];
        this.lastPostBody = (String) args[1];
        return "{}";
      }
      if ("postV3WithWechatpaySerial".equals(method.getName())) {
        this.lastPostUrl = (String) args[0];
        this.lastPostBody = (String) args[1];
        return "{}";
      }
      if ("getV3".equals(method.getName())) {
        this.lastGetUrl = (String) args[0];
        return "{}";
      }
      if ("toString".equals(method.getName())) {
        return "MockWxPayService";
      }
      Class<?> returnType = method.getReturnType();
      if (boolean.class.equals(returnType)) {
        return false;
      }
      if (int.class.equals(returnType)) {
        return 0;
      }
      if (long.class.equals(returnType)) {
        return 0L;
      }
      if (double.class.equals(returnType)) {
        return 0D;
      }
      if (float.class.equals(returnType)) {
        return 0F;
      }
      if (short.class.equals(returnType)) {
        return (short) 0;
      }
      if (byte.class.equals(returnType)) {
        return (byte) 0;
      }
      if (char.class.equals(returnType)) {
        return (char) 0;
      }
      return null;
    }
  }
}
