package me.chanjar.weixin.mp.bean.template;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * <pre>
 * Created by Binary Wang on 2017-3-30.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMpTemplateMessageTest {
  @Test
  public void testToJson() {
    WxMpTemplateMessage tm = WxMpTemplateMessage.builder()
      .toUser("OPENID")
      .templateId("ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY")
      .miniProgram(new WxMpTemplateMessage.MiniProgram("xiaochengxuappid12345", "index?foo=bar", true))
      .url("http://weixin.qq.com/download")
      .clientMsgId("MSG_000001")
      .build();

    tm.addData(
      new WxMpTemplateData("first", "haahah", "#FF00FF"));
    tm.addData(
      new WxMpTemplateData("remark", "heihei", "#FF00FF"));

    assertEquals("{\"touser\":\"OPENID\",\"template_id\":\"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY\",\"client_msg_id\":\"MSG_000001\",\"url\":\"http://weixin.qq.com/download\",\"miniprogram\":{\"appid\":\"xiaochengxuappid12345\",\"path\":\"index?foo=bar\"},\"data\":{\"first\":{\"value\":\"haahah\",\"color\":\"#FF00FF\"},\"remark\":{\"value\":\"heihei\",\"color\":\"#FF00FF\"}}}", tm.toJson());
  }

  @Test
  public void testAddData() {
    WxMpTemplateMessage tm = WxMpTemplateMessage.builder().build()
      .addData(new WxMpTemplateData("thing01", "张三李四王麻子张三李四王麻子张三李四王麻子张三李四王麻子"))
      .addData(new WxMpTemplateData("time01", "2019年10月1日 15:01"))
      .addData(new WxMpTemplateData("character_string01", "1234567890123456789012345678901234567890"))
      .addData(new WxMpTemplateData("amount01", "￥100.21"))
      .addData(new WxMpTemplateData("phone_number01", "+86-0766-668888661111"))
      .addData(new WxMpTemplateData("car_number01", "粤A8Z888挂9"))
      .addData(new WxMpTemplateData("const01", "支付状态、排队状态、天气状态、物流状态、用药提醒、还款提醒"));

    assertEquals(7, tm.getData().size());

    assertEquals("张三李四王麻子张三李四王麻子张三李...", tm.getData().get(0).getValue());
    assertEquals("2019年10月1日 15:01", tm.getData().get(1).getValue());
    assertEquals("12345678901234567890123456789...", tm.getData().get(2).getValue());
    assertEquals("￥100.21", tm.getData().get(3).getValue());
    assertEquals("+86-0766-66888...", tm.getData().get(4).getValue());
    assertEquals("粤A8Z8...", tm.getData().get(5).getValue());
    assertEquals("支付状态、排队状态、天气状态、物流...", tm.getData().get(6).getValue());
  }
}
