package cn.binarywang.wx.miniapp.message;

import me.chanjar.weixin.common.api.WxConsts;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WxMaJsonOutMessageTest {

  @Test
  public void testToJson() {
    WxMaJsonOutMessage message = WxMaJsonOutMessage.builder()
      .fromUserName("test_from_user")
      .toUserName("test_to_user")
      .msgType(WxConsts.XmlMsgType.TRANSFER_CUSTOMER_SERVICE)
      .createTime(System.currentTimeMillis() / 1000)
      .build();

    String jsonResult = message.toJson();
    assertThat(jsonResult).isNotEmpty();
    assertThat(jsonResult).contains("test_from_user");
    assertThat(jsonResult).contains("test_to_user");
    assertThat(jsonResult).contains(WxConsts.XmlMsgType.TRANSFER_CUSTOMER_SERVICE);
    
    System.out.println("JSON Output:");
    System.out.println(jsonResult);
  }

  @Test
  public void testEmptyMessage() {
    WxMaJsonOutMessage message = new WxMaJsonOutMessage();
    String jsonResult = message.toJson();
    assertThat(jsonResult).isNotEmpty();
    System.out.println("Empty message JSON:");
    System.out.println(jsonResult);
  }
}