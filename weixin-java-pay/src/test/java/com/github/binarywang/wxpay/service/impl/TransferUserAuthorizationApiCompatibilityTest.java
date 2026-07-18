package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.transfer.PreTransferWithAuthorizationRequest;
import com.github.binarywang.wxpay.bean.transfer.PreTransferWithAuthorizationResult;
import com.github.binarywang.wxpay.bean.transfer.TransferBillsAfterAuthorizationRequest;
import com.github.binarywang.wxpay.bean.transfer.TransferBillsAfterAuthorizationResult;
import com.github.binarywang.wxpay.bean.transfer.TransferBillsRequest;
import com.github.binarywang.wxpay.bean.transfer.TransferBillsResult;
import com.github.binarywang.wxpay.bean.transfer.UserAuthorizationNotifyResult;
import com.github.binarywang.wxpay.bean.transfer.UserConfirmAuthorizationRequest;
import com.github.binarywang.wxpay.bean.transfer.UserConfirmAuthorizationResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Test
public class TransferUserAuthorizationApiCompatibilityTest {

  private static final String BASE_URL = "https://api.mch.weixin.qq.com";

  /**
   * 验证新增接口不会改变原有 transferBills 发起转账接口路径.
   */
  public void shouldKeepTransferBillsApiPathUnchanged() throws Exception {
    RequestCaptureHandler handler = new RequestCaptureHandler();
    TransferServiceImpl transferService = new TransferServiceImpl(handler.createWxPayService());

    TransferBillsResult result = transferService.transferBills(TransferBillsRequest.newBuilder()
      .appid("wxf636efh567hg4356")
      .outBillNo("plfk2020042013")
      .transferSceneId("1000")
      .openid("o-MYE42l80oelYMDE34nYD456Xoy")
      .transferAmount(400000)
      .transferRemark("新会员开通有礼")
      .build());

    Assert.assertEquals(handler.lastPostWithSerialUrl, BASE_URL + "/v3/fund-app/mch-transfer/transfer-bills");
    Assert.assertFalse(handler.lastPostWithSerialBody.contains("authorization_info"));
    Assert.assertEquals(result.getPackageInfo(), "transfer-package");
  }

  /**
   * 验证“发起转账并完成免确认收款授权”使用独立接口路径和授权参数.
   */
  public void shouldUseTransferBillsWithAuthorizationPathAndPayload() throws Exception {
    RequestCaptureHandler handler = new RequestCaptureHandler();
    TransferServiceImpl transferService = new TransferServiceImpl(handler.createWxPayService());

    PreTransferWithAuthorizationResult result = transferService.transferBillsWithAuthorization(
      PreTransferWithAuthorizationRequest.newBuilder()
        .appid("wxf636efh567hg4356")
        .outBillNo("plfk2020042013")
        .transferSceneId("1000")
        .openid("o-MYE42l80oelYMDE34nYD456Xoy")
        .transferAmount(400000)
        .transferRemark("新会员开通有礼")
        .authorizationInfo(PreTransferWithAuthorizationRequest.AuthorizationInfo.newBuilder()
          .userDisplayName("wx_123456")
          .outAuthorizationNo("auth2020042013")
          .authorizationNotifyUrl("https://www.weixin.qq.com/wxpay/auth.php")
          .build())
        .build());

    Assert.assertEquals(handler.lastPostWithSerialUrl,
      BASE_URL + "/v3/fund-app/mch-transfer/transfer-bills/pre-transfer-with-authorization");
    Assert.assertTrue(handler.lastPostWithSerialBody.contains("\"authorization_info\""));
    Assert.assertTrue(handler.lastPostWithSerialBody.contains("\"out_authorization_no\":\"auth2020042013\""));
    Assert.assertEquals(result.getUserDisplayName(), "wx_123456");
    Assert.assertEquals(result.getOutAuthorizationNo(), "auth2020042013");
    Assert.assertEquals(result.getPackageInfo(), "pre-transfer-package");
  }

  /**
   * 验证转账并授权接口会在本地拦截缺失的免确认收款授权信息.
   */
  public void shouldRejectMissingAuthorizationInfo() throws Exception {
    RequestCaptureHandler handler = new RequestCaptureHandler();
    TransferServiceImpl transferService = new TransferServiceImpl(handler.createWxPayService());

    try {
      transferService.transferBillsWithAuthorization(PreTransferWithAuthorizationRequest.newBuilder()
        .appid("wxf636efh567hg4356")
        .outBillNo("plfk2020042013")
        .build());
      Assert.fail("缺少authorizationInfo时应抛出WxPayException");
    } catch (WxPayException e) {
      Assert.assertTrue(e.getMessage().contains("authorizationInfo"));
    }
    Assert.assertNull(handler.lastPostWithSerialUrl);
  }

  /**
   * 验证免确认收款授权信息内部字段不能为空.
   */
  public void shouldRejectBlankAuthorizationInfoFields() throws Exception {
    RequestCaptureHandler handler = new RequestCaptureHandler();
    TransferServiceImpl transferService = new TransferServiceImpl(handler.createWxPayService());

    try {
      transferService.transferBillsWithAuthorization(PreTransferWithAuthorizationRequest.newBuilder()
        .appid("wxf636efh567hg4356")
        .outBillNo("plfk2020042013")
        .authorizationInfo(PreTransferWithAuthorizationRequest.AuthorizationInfo.newBuilder()
          .userDisplayName("wx_123456")
          .outAuthorizationNo("")
          .authorizationNotifyUrl("https://www.weixin.qq.com/wxpay/auth.php")
          .build())
        .build());
      Assert.fail("authorizationInfo内部字段为空时应抛出WxPayException");
    } catch (WxPayException e) {
      Assert.assertTrue(e.getMessage().contains("outAuthorizationNo"));
    }
    Assert.assertNull(handler.lastPostWithSerialUrl);
  }

  /**
   * 验证“用户授权后转账”使用独立接口路径和授权单号参数.
   */
  public void shouldUseTransferBillsAfterAuthorizationPathAndPayload() throws Exception {
    RequestCaptureHandler handler = new RequestCaptureHandler();
    TransferServiceImpl transferService = new TransferServiceImpl(handler.createWxPayService());

    TransferBillsAfterAuthorizationResult result = transferService.transferBillsAfterAuthorization(
      TransferBillsAfterAuthorizationRequest.newBuilder()
        .appid("wxf636efh567hg4356")
        .outBillNo("plfk2020042014")
        .transferSceneId("1000")
        .transferAmount(400000)
        .transferRemark("新会员开通有礼")
        .authorizationId("201202504101000123456789012")
        .outAuthorizationNo("auth2020042013")
        .build());

    Assert.assertEquals(handler.lastPostWithSerialUrl,
      BASE_URL + "/v3/fund-app/mch-transfer/transfer-bills/transfer");
    Assert.assertTrue(handler.lastPostWithSerialBody.contains("\"authorization_id\":\"201202504101000123456789012\""));
    Assert.assertTrue(handler.lastPostWithSerialBody.contains("\"out_authorization_no\":\"auth2020042013\""));
    Assert.assertFalse(handler.lastPostWithSerialBody.contains("\"openid\""));
    Assert.assertEquals(result.getState(), "SUCCESS");
    Assert.assertEquals(result.getTransferAmount(), Integer.valueOf(400000));
    Assert.assertEquals(result.getOpenid(), "o-MYE42l80oelYMDE34nYD456Xoy");
  }

  /**
   * 验证用户授权后转账至少需要一个授权单号标识.
   */
  public void shouldRejectMissingAuthorizationIdentifiers() throws Exception {
    RequestCaptureHandler handler = new RequestCaptureHandler();
    TransferServiceImpl transferService = new TransferServiceImpl(handler.createWxPayService());

    try {
      transferService.transferBillsAfterAuthorization(TransferBillsAfterAuthorizationRequest.newBuilder()
        .appid("wxf636efh567hg4356")
        .outBillNo("plfk2020042014")
        .build());
      Assert.fail("缺少授权单号标识时应抛出WxPayException");
    } catch (WxPayException e) {
      Assert.assertTrue(e.getMessage().contains("authorizationId"));
    }
    Assert.assertNull(handler.lastPostWithSerialUrl);
  }

  /**
   * 验证“发起免确认收款授权”只创建授权申请，并返回用于调起授权页的 package_info.
   */
  public void shouldUseUserConfirmAuthorizationPathAndPayload() throws Exception {
    RequestCaptureHandler handler = new RequestCaptureHandler();
    TransferServiceImpl transferService = new TransferServiceImpl(handler.createWxPayService());

    UserConfirmAuthorizationResult result = transferService.userConfirmAuthorization(
      UserConfirmAuthorizationRequest.newBuilder()
        .outAuthorizationNo("auth2020042013")
        .appid("wxf636efh567hg4356")
        .openid("o-MYE42l80oelYMDE34nYD456Xoy")
        .transferSceneId("1000")
        .userDisplayName("wx_123456")
        .userRecvPerception("现金奖励")
        .authorizationNotifyUrl("https://www.weixin.qq.com/wxpay/auth.php")
        .sceneInfo(UserConfirmAuthorizationRequest.SceneInfo.newBuilder()
          .clientIp("113.84.136.9")
          .deviceId("8d67f169fe104008cd20b72573a0c8c9")
          .deviceType("IOS")
          .build())
        .build());

    Assert.assertEquals(handler.lastPostUrl, BASE_URL + "/v3/fund-app/mch-transfer/user-confirm-authorization");
    Assert.assertTrue(handler.lastPostBody.contains("\"scene_info\""));
    Assert.assertTrue(handler.lastPostBody.contains("\"device_type\":\"IOS\""));
    Assert.assertEquals(result.getState(), "WAIT_USER_CONFIRM");
    Assert.assertEquals(result.getPackageInfo(), "authorization-package");
  }

  /**
   * 验证商户侧授权单号查询免确认收款授权结果接口路径和响应字段.
   */
  public void shouldGetUserConfirmAuthorizationByOutAuthorizationNo() throws Exception {
    RequestCaptureHandler handler = new RequestCaptureHandler();
    TransferServiceImpl transferService = new TransferServiceImpl(handler.createWxPayService());

    UserConfirmAuthorizationResult result = transferService.getUserConfirmAuthorizationByOutAuthorizationNo(
      "auth2020042013", true);

    Assert.assertEquals(handler.lastGetUrl,
      BASE_URL + "/v3/fund-app/mch-transfer/user-confirm-authorization/out-authorization-no/auth2020042013"
        + "?is_display_authorization=true");
    Assert.assertEquals(result.getState(), "TAKING_EFFECT");
    Assert.assertEquals(result.getAuthorizationId(), "201202504101000123456789012");
    Assert.assertEquals(result.getTransferSceneId(), "1000");
    Assert.assertEquals(result.getPackageInfo(), "authorization-package");
  }

  /**
   * 验证解除免确认收款授权接口路径和关闭信息解析.
   */
  public void shouldCloseUserConfirmAuthorization() throws Exception {
    RequestCaptureHandler handler = new RequestCaptureHandler();
    TransferServiceImpl transferService = new TransferServiceImpl(handler.createWxPayService());

    UserConfirmAuthorizationResult result = transferService.closeUserConfirmAuthorization("auth2020042013");

    Assert.assertEquals(handler.lastPostUrl,
      BASE_URL + "/v3/fund-app/mch-transfer/user-confirm-authorization/out-authorization-no/auth2020042013/close");
    Assert.assertEquals(handler.lastPostBody, "");
    Assert.assertEquals(result.getState(), "CLOSED");
    Assert.assertNotNull(result.getCloseInfo());
    Assert.assertEquals(result.getCloseInfo().getCloseReason(), "CLOSE_VIA_MCH_API");
  }

  /**
   * 验证查询和解除授权接口会拦截空的商户侧授权单号.
   */
  public void shouldRejectBlankOutAuthorizationNoForAuthorizationQueryAndClose() throws Exception {
    RequestCaptureHandler handler = new RequestCaptureHandler();
    TransferServiceImpl transferService = new TransferServiceImpl(handler.createWxPayService());

    try {
      transferService.getUserConfirmAuthorizationByOutAuthorizationNo(" ", true);
      Assert.fail("查询授权结果缺少outAuthorizationNo时应抛出WxPayException");
    } catch (WxPayException e) {
      Assert.assertTrue(e.getMessage().contains("outAuthorizationNo"));
    }
    try {
      transferService.closeUserConfirmAuthorization("");
      Assert.fail("解除授权缺少outAuthorizationNo时应抛出WxPayException");
    } catch (WxPayException e) {
      Assert.assertTrue(e.getMessage().contains("outAuthorizationNo"));
    }
    Assert.assertNull(handler.lastGetUrl);
    Assert.assertNull(handler.lastPostUrl);
  }

  /**
   * 验证免确认收款授权结果通知会复用微信支付V3通知解析能力，并绑定正确的结果类型.
   */
  public void shouldParseUserAuthorizationNotifyResult() throws Exception {
    RequestCaptureHandler handler = new RequestCaptureHandler();
    TransferServiceImpl transferService = new TransferServiceImpl(handler.createWxPayService());

    UserAuthorizationNotifyResult result = transferService.parseUserAuthorizationNotifyResult("{\"id\":\"notify-id\"}", null);

    Assert.assertEquals(handler.lastNotifyResultType, UserAuthorizationNotifyResult.class);
    Assert.assertEquals(handler.lastNotifyDataType, UserAuthorizationNotifyResult.DecryptNotifyResult.class);
    Assert.assertEquals(result.getResult().getOutAuthorizationNo(), "auth2020042013");
    Assert.assertEquals(result.getResult().getAuthorizationId(), "201202504101000123456789012");
    Assert.assertEquals(result.getResult().getState(), "TAKING_EFFECT");
    Assert.assertEquals(result.getResult().getAuthorizeTime(), "2015-05-20T13:29:35.120+08:00");
  }

  /**
   * 通过动态代理拦截 WxPayService 请求，便于断言接口路径、请求体和响应解析.
   */
  private static class RequestCaptureHandler implements InvocationHandler {
    private String lastPostUrl;
    private String lastPostBody;
    private String lastPostWithSerialUrl;
    private String lastPostWithSerialBody;
    private String lastGetUrl;
    private Class<?> lastNotifyResultType;
    private Class<?> lastNotifyDataType;

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
      if ("getV3".equals(method.getName())) {
        this.lastGetUrl = (String) args[0];
        return "{\"out_authorization_no\":\"auth2020042013\",\"appid\":\"wxf636efh567hg4356\","
          + "\"openid\":\"o-MYE42l80oelYMDE34nYD456Xoy\",\"user_display_name\":\"wx_123456\","
          + "\"authorization_id\":\"201202504101000123456789012\",\"state\":\"TAKING_EFFECT\","
          + "\"authorize_time\":\"2015-05-20T13:29:35.120+08:00\",\"transfer_scene_id\":\"1000\","
          + "\"user_recv_perception\":\"现金奖励\",\"create_time\":\"2015-05-20T13:29:35.120+08:00\","
          + "\"package_info\":\"authorization-package\"}";
      }
      if ("postV3".equals(method.getName())) {
        this.lastPostUrl = (String) args[0];
        this.lastPostBody = (String) args[1];
        if (this.lastPostUrl.endsWith("/close")) {
          return "{\"out_authorization_no\":\"auth2020042013\",\"appid\":\"wxf636efh567hg4356\","
            + "\"openid\":\"o-MYE42l80oelYMDE34nYD456Xoy\",\"user_display_name\":\"wx_123456\","
            + "\"authorization_id\":\"201202504101000123456789012\",\"state\":\"CLOSED\","
            + "\"authorize_time\":\"2015-05-20T13:29:35.120+08:00\","
            + "\"close_info\":{\"close_time\":\"2015-05-20T13:29:35.120+08:00\","
            + "\"close_reason\":\"CLOSE_VIA_MCH_API\"}}";
        }
        return "{\"out_authorization_no\":\"auth2020042013\",\"state\":\"WAIT_USER_CONFIRM\","
          + "\"create_time\":\"2015-05-20T13:29:35.120+08:00\",\"package_info\":\"authorization-package\"}";
      }
      if ("postV3WithWechatpaySerial".equals(method.getName())) {
        this.lastPostWithSerialUrl = (String) args[0];
        this.lastPostWithSerialBody = (String) args[1];
        if (this.lastPostWithSerialUrl.endsWith("/pre-transfer-with-authorization")) {
          return "{\"out_bill_no\":\"plfk2020042013\",\"transfer_bill_no\":\"1330000071100999991182020050700019480001\","
            + "\"create_time\":\"2015-05-20T13:29:35.120+08:00\",\"state\":\"WAIT_USER_CONFIRM\","
            + "\"package_info\":\"pre-transfer-package\",\"user_display_name\":\"wx_123456\","
            + "\"out_authorization_no\":\"auth2020042013\"}";
        }
        if (this.lastPostWithSerialUrl.endsWith("/transfer-bills/transfer")) {
          return "{\"mch_id\":\"1900001109\",\"out_bill_no\":\"plfk2020042014\","
            + "\"transfer_bill_no\":\"1330000071100999991182020050700019480002\","
            + "\"appid\":\"wxf636efh567hg4356\",\"state\":\"SUCCESS\",\"transfer_amount\":400000,"
            + "\"transfer_remark\":\"新会员开通有礼\",\"openid\":\"o-MYE42l80oelYMDE34nYD456Xoy\","
            + "\"user_name\":\"张三\",\"create_time\":\"2015-05-20T13:29:35.120+08:00\","
            + "\"update_time\":\"2015-05-20T13:29:35.120+08:00\"}";
        }
        return "{\"out_bill_no\":\"plfk2020042013\",\"transfer_bill_no\":\"1330000071100999991182020050700019480001\","
          + "\"create_time\":\"2015-05-20T13:29:35.120+08:00\",\"state\":\"WAIT_USER_CONFIRM\","
          + "\"package_info\":\"transfer-package\"}";
      }
      if ("baseParseOrderNotifyV3Result".equals(method.getName())) {
        this.lastNotifyResultType = (Class<?>) args[2];
        this.lastNotifyDataType = (Class<?>) args[3];
        UserAuthorizationNotifyResult notifyResult = new UserAuthorizationNotifyResult();
        UserAuthorizationNotifyResult.DecryptNotifyResult decryptResult =
          new UserAuthorizationNotifyResult.DecryptNotifyResult();
        decryptResult.setOutAuthorizationNo("auth2020042013");
        decryptResult.setAppid("wxf636efh567hg4356");
        decryptResult.setOpenid("o-MYE42l80oelYMDE34nYD456Xoy");
        decryptResult.setUserDisplayName("wx_123456");
        decryptResult.setAuthorizationId("201202504101000123456789012");
        decryptResult.setState("TAKING_EFFECT");
        decryptResult.setAuthorizeTime("2015-05-20T13:29:35.120+08:00");
        notifyResult.setResult(decryptResult);
        return notifyResult;
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
