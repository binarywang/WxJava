package com.github.binarywang.wxpay.bean.result;

import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.v3.util.SignUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;

/**
 * <pre>
 * WxPayUnifiedOrderV3Result 测试类
 * 主要测试prepayId字段和静态工厂方法的解耦功能
 * </pre>
 *
 * @author copilot
 */
public class WxPayUnifiedOrderV3ResultTest {

  /**
   * 生成测试用的RSA密钥对
   */
  private KeyPair generateKeyPair() throws Exception {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(2048);
    return keyPairGenerator.generateKeyPair();
  }

  /**
   * 测试JsapiResult中的prepayId字段是否正确设置
   */
  @Test
  public void testJsapiResultWithPrepayId() throws Exception {
    // 准备测试数据
    String testPrepayId = "wx201410272009395522657a690389285100";
    String testAppId = "wx8888888888888888";
    KeyPair keyPair = generateKeyPair();
    PrivateKey privateKey = keyPair.getPrivate();

    // 创建WxPayUnifiedOrderV3Result对象
    WxPayUnifiedOrderV3Result result = new WxPayUnifiedOrderV3Result();
    result.setPrepayId(testPrepayId);

    // 调用getPayInfo生成JsapiResult
    WxPayUnifiedOrderV3Result.JsapiResult jsapiResult =
      result.getPayInfo(TradeTypeEnum.JSAPI, testAppId, null, privateKey);

    // 验证prepayId字段是否正确设置
    Assert.assertNotNull(jsapiResult.getPrepayId(), "prepayId不应为null");
    Assert.assertEquals(jsapiResult.getPrepayId(), testPrepayId, "prepayId应该与设置的值相同");

    // 验证其他字段
    Assert.assertEquals(jsapiResult.getAppId(), testAppId);
    Assert.assertNotNull(jsapiResult.getTimeStamp());
    Assert.assertNotNull(jsapiResult.getNonceStr());
    Assert.assertEquals(jsapiResult.getPackageValue(), "prepay_id=" + testPrepayId);
    Assert.assertEquals(jsapiResult.getSignType(), "RSA");
    Assert.assertNotNull(jsapiResult.getPaySign());
  }

  /**
   * 测试使用静态工厂方法生成JsapiResult（解耦场景）
   */
  @Test
  public void testGetJsapiPayInfoStaticMethod() throws Exception {
    // 准备测试数据
    String testPrepayId = "wx201410272009395522657a690389285100";
    String testAppId = "wx8888888888888888";
    KeyPair keyPair = generateKeyPair();
    PrivateKey privateKey = keyPair.getPrivate();

    // 使用静态工厂方法生成JsapiResult
    WxPayUnifiedOrderV3Result.JsapiResult jsapiResult =
      WxPayUnifiedOrderV3Result.getJsapiPayInfo(testPrepayId, testAppId, privateKey);

    // 验证prepayId字段
    Assert.assertNotNull(jsapiResult.getPrepayId(), "prepayId不应为null");
    Assert.assertEquals(jsapiResult.getPrepayId(), testPrepayId, "prepayId应该与输入的值相同");

    // 验证其他字段
    Assert.assertEquals(jsapiResult.getAppId(), testAppId);
    Assert.assertNotNull(jsapiResult.getTimeStamp());
    Assert.assertNotNull(jsapiResult.getNonceStr());
    Assert.assertEquals(jsapiResult.getPackageValue(), "prepay_id=" + testPrepayId);
    Assert.assertEquals(jsapiResult.getSignType(), "RSA");
    Assert.assertNotNull(jsapiResult.getPaySign());
  }

  /**
   * 测试使用静态工厂方法生成AppResult（解耦场景）
   */
  @Test
  public void testGetAppPayInfoStaticMethod() throws Exception {
    // 准备测试数据
    String testPrepayId = "wx201410272009395522657a690389285100";
    String testAppId = "wx8888888888888888";
    String testMchId = "1900000109";
    KeyPair keyPair = generateKeyPair();
    PrivateKey privateKey = keyPair.getPrivate();

    // 使用静态工厂方法生成AppResult
    WxPayUnifiedOrderV3Result.AppResult appResult =
      WxPayUnifiedOrderV3Result.getAppPayInfo(testPrepayId, testAppId, testMchId, privateKey);

    // 验证prepayId字段
    Assert.assertNotNull(appResult.getPrepayId(), "prepayId不应为null");
    Assert.assertEquals(appResult.getPrepayId(), testPrepayId, "prepayId应该与输入的值相同");

    // 验证其他字段
    Assert.assertEquals(appResult.getAppid(), testAppId);
    Assert.assertEquals(appResult.getPartnerId(), testMchId);
    Assert.assertNotNull(appResult.getTimestamp());
    Assert.assertNotNull(appResult.getNoncestr());
    Assert.assertEquals(appResult.getPackageValue(), "Sign=WXPay");
    Assert.assertNotNull(appResult.getSign());
  }

  /**
   * 测试解耦场景：先获取prepayId，后续再生成支付信息
   */
  @Test
  public void testDecoupledScenario() throws Exception {
    // 模拟场景：先创建订单获取prepayId
    String testPrepayId = "wx201410272009395522657a690389285100";
    String testAppId = "wx8888888888888888";
    KeyPair keyPair = generateKeyPair();
    PrivateKey privateKey = keyPair.getPrivate();

    // 步骤1：模拟从创建订单接口获取prepayId
    WxPayUnifiedOrderV3Result orderResult = new WxPayUnifiedOrderV3Result();
    orderResult.setPrepayId(testPrepayId);

    // 获取prepayId用于存储
    String storedPrepayId = orderResult.getPrepayId();
    Assert.assertEquals(storedPrepayId, testPrepayId);

    // 步骤2：后续支付失败时，使用存储的prepayId重新生成支付信息
    WxPayUnifiedOrderV3Result.JsapiResult newPayInfo =
      WxPayUnifiedOrderV3Result.getJsapiPayInfo(storedPrepayId, testAppId, privateKey);

    // 验证重新生成的支付信息
    Assert.assertEquals(newPayInfo.getPrepayId(), storedPrepayId);
    Assert.assertEquals(newPayInfo.getPackageValue(), "prepay_id=" + storedPrepayId);
    Assert.assertNotNull(newPayInfo.getPaySign());
  }

  /**
   * 测试多次生成支付信息，签名应该不同（因为timestamp和nonceStr每次都不同）
   */
  @Test
  public void testMultipleGenerationsHaveDifferentSignatures() throws Exception {
    String testPrepayId = "wx201410272009395522657a690389285100";
    String testAppId = "wx8888888888888888";
    KeyPair keyPair = generateKeyPair();
    PrivateKey privateKey = keyPair.getPrivate();

    // 生成第一次支付信息
    WxPayUnifiedOrderV3Result.JsapiResult result1 =
      WxPayUnifiedOrderV3Result.getJsapiPayInfo(testPrepayId, testAppId, privateKey);

    // 等待一秒确保timestamp不同
    Thread.sleep(1000);

    // 生成第二次支付信息
    WxPayUnifiedOrderV3Result.JsapiResult result2 =
      WxPayUnifiedOrderV3Result.getJsapiPayInfo(testPrepayId, testAppId, privateKey);

    // prepayId应该相同
    Assert.assertEquals(result1.getPrepayId(), result2.getPrepayId());

    // 但是timestamp、nonceStr和签名应该不同
    Assert.assertNotEquals(result1.getTimeStamp(), result2.getTimeStamp(), "timestamp应该不同");
    Assert.assertNotEquals(result1.getNonceStr(), result2.getNonceStr(), "nonceStr应该不同");
    Assert.assertNotEquals(result1.getPaySign(), result2.getPaySign(), "签名应该不同");
  }

  /**
   * 测试AppResult中的prepayId字段
   */
  @Test
  public void testAppResultWithPrepayId() throws Exception {
    String testPrepayId = "wx201410272009395522657a690389285100";
    String testAppId = "wx8888888888888888";
    String testMchId = "1900000109";
    KeyPair keyPair = generateKeyPair();
    PrivateKey privateKey = keyPair.getPrivate();

    WxPayUnifiedOrderV3Result result = new WxPayUnifiedOrderV3Result();
    result.setPrepayId(testPrepayId);

    // 调用getPayInfo生成AppResult
    WxPayUnifiedOrderV3Result.AppResult appResult =
      result.getPayInfo(TradeTypeEnum.APP, testAppId, testMchId, privateKey);

    // 验证prepayId字段
    Assert.assertNotNull(appResult.getPrepayId(), "prepayId不应为null");
    Assert.assertEquals(appResult.getPrepayId(), testPrepayId, "prepayId应该与设置的值相同");
  }

  /**
   * 测试getJsapiPayInfo方法的空值验证
   */
  @Test(expectedExceptions = IllegalArgumentException.class, 
        expectedExceptionsMessageRegExp = "prepayId, appId 和 privateKey 不能为空")
  public void testGetJsapiPayInfoWithNullPrepayId() {
    WxPayUnifiedOrderV3Result.getJsapiPayInfo(null, "appId", null);
  }

  /**
   * 测试getJsapiPayInfo方法的空值验证 - appId为null
   */
  @Test(expectedExceptions = IllegalArgumentException.class,
        expectedExceptionsMessageRegExp = "prepayId, appId 和 privateKey 不能为空")
  public void testGetJsapiPayInfoWithNullAppId() throws Exception {
    KeyPair keyPair = generateKeyPair();
    WxPayUnifiedOrderV3Result.getJsapiPayInfo("prepayId", null, keyPair.getPrivate());
  }

  /**
   * 测试getJsapiPayInfo方法的空值验证 - privateKey为null
   */
  @Test(expectedExceptions = IllegalArgumentException.class,
        expectedExceptionsMessageRegExp = "prepayId, appId 和 privateKey 不能为空")
  public void testGetJsapiPayInfoWithNullPrivateKey() {
    WxPayUnifiedOrderV3Result.getJsapiPayInfo("prepayId", "appId", null);
  }

  /**
   * 测试getAppPayInfo方法的空值验证 - prepayId为null
   */
  @Test(expectedExceptions = IllegalArgumentException.class,
        expectedExceptionsMessageRegExp = "prepayId, appId, mchId 和 privateKey 不能为空")
  public void testGetAppPayInfoWithNullPrepayId() {
    WxPayUnifiedOrderV3Result.getAppPayInfo(null, "appId", "mchId", null);
  }

  /**
   * 测试getAppPayInfo方法的空值验证 - appId为null
   */
  @Test(expectedExceptions = IllegalArgumentException.class,
        expectedExceptionsMessageRegExp = "prepayId, appId, mchId 和 privateKey 不能为空")
  public void testGetAppPayInfoWithNullAppId() throws Exception {
    KeyPair keyPair = generateKeyPair();
    WxPayUnifiedOrderV3Result.getAppPayInfo("prepayId", null, "mchId", keyPair.getPrivate());
  }

  /**
   * 测试getAppPayInfo方法的空值验证 - mchId为null
   */
  @Test(expectedExceptions = IllegalArgumentException.class,
        expectedExceptionsMessageRegExp = "prepayId, appId, mchId 和 privateKey 不能为空")
  public void testGetAppPayInfoWithNullMchId() throws Exception {
    KeyPair keyPair = generateKeyPair();
    WxPayUnifiedOrderV3Result.getAppPayInfo("prepayId", "appId", null, keyPair.getPrivate());
  }

  /**
   * 测试getAppPayInfo方法的空值验证 - privateKey为null
   */
  @Test(expectedExceptions = IllegalArgumentException.class,
        expectedExceptionsMessageRegExp = "prepayId, appId, mchId 和 privateKey 不能为空")
  public void testGetAppPayInfoWithNullPrivateKey() {
    WxPayUnifiedOrderV3Result.getAppPayInfo("prepayId", "appId", "mchId", null);
  }
}
