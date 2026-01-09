package com.github.binarywang.wxpay.bean.result;

import com.github.binarywang.wxpay.util.XmlConfig;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * WxSignQueryResult 单元测试
 *
 * @author Binary Wang
 */
public class WxSignQueryResultTest {

  /**
   * 测试 XML 解析，特别是 contract_expired_time 字段
   */
  @Test
  public void testFromXML() {
    /*
     * xml样例字符串来自于官方文档
     * https://pay.weixin.qq.com/doc/v2/merchant/4011987640
     */
    String xmlString = "<xml>\n" +
      "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
      "  <result_code><![CDATA[SUCCESS]]></result_code>\n" +
      "  <mch_id><![CDATA[80000000]]></mch_id>\n" +
      "  <appid><![CDATA[wx426b3015555b46be]]></appid>\n" +
      "  <contract_id>203</contract_id>\n" +
      "  <plan_id>66</plan_id>\n" +
      "  <openid><![CDATA[oHZx6uMbIG46UXQ3SKxVYEgw1LZs]]></openid>\n" +
      "  <request_serial>123</request_serial>\n" +
      "  <contract_code><![CDATA[1005]]></contract_code>\n" +
      "  <contract_display_account><![CDATA[test]]></contract_display_account>\n" +
      "  <contract_state>1</contract_state>\n" +
      "  <contract_signed_time>2015-07-01 10:00:00</contract_signed_time>\n" +
      "  <contract_expired_time>2015-07-01 10:00:00</contract_expired_time>\n" +
      "  <contract_terminated_time>2015-07-01 10:00:00</contract_terminated_time>\n" +
      "  <contract_termination_mode>3</contract_termination_mode>\n" +
      "  <contract_termination_remark><![CDATA[delete ....]]></contract_termination_remark>\n" +
      "  <err_code>0</err_code>\n" +
      "  <err_code_des><![CDATA[SUCCESS]]></err_code_des>\n" +
      "  <sign><![CDATA[8FC9DACB7DDF9B48333DCCC2224E0CAC]]></sign>\n" +
      "</xml>";

    // 启用 fastMode 以覆盖 WxSignQueryResult#loadXml 分支
    XmlConfig.fastMode = true;
    try {
      WxSignQueryResult result = WxSignQueryResult.fromXML(xmlString, WxSignQueryResult.class);

      // 验证基本字段
      Assert.assertEquals(result.getReturnCode(), "SUCCESS");
      Assert.assertEquals(result.getResultCode(), "SUCCESS");
      Assert.assertEquals(result.getMchId(), "80000000");
      Assert.assertEquals(result.getAppid(), "wx426b3015555b46be");

      // 验证签约相关字段
      Assert.assertEquals(result.getContractId(), "203");
      Assert.assertEquals(result.getPlanId(), "66");
      Assert.assertEquals(result.getOpenId(), "oHZx6uMbIG46UXQ3SKxVYEgw1LZs");
      Assert.assertEquals(result.getRequestSerial().longValue(), 123L);
      Assert.assertEquals(result.getContractCode(), "1005");
      Assert.assertEquals(result.getContractDisplayAccount(), "test");
      Assert.assertEquals(result.getContractState().intValue(), 1);

      // 重点测试时间字段，特别是 contract_expired_time
      Assert.assertEquals(result.getContractSignedTime(), "2015-07-01 10:00:00");
      Assert.assertEquals(result.getContractExpiredTime(), "2015-07-01 10:00:00");
      Assert.assertEquals(result.getContractTerminatedTime(), "2015-07-01 10:00:00");

      // 验证其他字段
      Assert.assertEquals(result.getContractTerminatedMode().intValue(), 3);
      Assert.assertEquals(result.getContractTerminationRemark(), "delete ....");
    } finally {
      // 恢复默认值
      XmlConfig.fastMode = false;
    }
  }

  /**
   * 测试 XML 解析 - 只包含必填字段
   */
  @Test
  public void testFromXML_RequiredFieldsOnly() {
    String xmlString = "<xml>\n" +
      "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
      "  <result_code><![CDATA[SUCCESS]]></result_code>\n" +
      "  <mch_id><![CDATA[10000098]]></mch_id>\n" +
      "  <appid><![CDATA[wxcbda96de0b165486]]></appid>\n" +
      "  <contract_id>Wx15463511252015071056489715</contract_id>\n" +
      "  <plan_id>123</plan_id>\n" +
      "  <request_serial>1695</request_serial>\n" +
      "  <contract_code><![CDATA[100001256]]></contract_code>\n" +
      "  <contract_display_account><![CDATA[张三]]></contract_display_account>\n" +
      "  <contract_state>0</contract_state>\n" +
      "  <contract_signed_time>2015-07-01 10:00:00</contract_signed_time>\n" +
      "  <contract_expired_time>2016-07-01 10:00:00</contract_expired_time>\n" +
      "  <openid><![CDATA[ozoKAt9TIPHfwVMkcniiNKZ1vbyw]]></openid>\n" +
      "  <sign><![CDATA[C380BEC2BFD727A4B6845133519F3AD6]]></sign>\n" +
      "</xml>";

    // 启用 fastMode 以覆盖 WxSignQueryResult#loadXml 分支
    XmlConfig.fastMode = true;
    try {
      WxSignQueryResult result = WxSignQueryResult.fromXML(xmlString, WxSignQueryResult.class);

      // 验证必填字段
      Assert.assertEquals(result.getReturnCode(), "SUCCESS");
      Assert.assertEquals(result.getResultCode(), "SUCCESS");
      Assert.assertEquals(result.getContractId(), "Wx15463511252015071056489715");
      Assert.assertEquals(result.getPlanId(), "123");
      Assert.assertEquals(result.getContractState().intValue(), 0);

      // 验证 contract_expired_time 字段能正确解析
      Assert.assertEquals(result.getContractExpiredTime(), "2016-07-01 10:00:00");

      // 验证非必填字段为 null
      Assert.assertNull(result.getContractTerminatedTime());
      Assert.assertNull(result.getContractTerminatedMode());
      Assert.assertNull(result.getContractTerminationRemark());
    } finally {
      // 恢复默认值
      XmlConfig.fastMode = false;
    }
  }
}
