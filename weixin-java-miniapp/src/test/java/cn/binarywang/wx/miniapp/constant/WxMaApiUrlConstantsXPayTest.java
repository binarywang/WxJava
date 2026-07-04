package cn.binarywang.wx.miniapp.constant;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class WxMaApiUrlConstantsXPayTest {

  @Test
  public void testBindTransferAccountUrl() {
    // 官方文档接口路径本身拼写为 accout，保持与官方文档一致
    String url = WxMaApiUrlConstants.XPay.BIND_TRANSFER_ACCOUNT_URL;
    assertEquals(url, "https://api.weixin.qq.com/xpay/bind_transfer_accout?pay_sig=%s");
  }
}
