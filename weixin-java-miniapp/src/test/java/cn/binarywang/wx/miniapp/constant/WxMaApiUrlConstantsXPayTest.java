package cn.binarywang.wx.miniapp.constant;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WxMaApiUrlConstantsXPayTest {

  @Test
  public void testBindTransferAccountUrl() {
    String url = WxMaApiUrlConstants.XPay.BIND_TRANSFER_ACCOUNT_URL;
    assertEquals(url, "https://api.weixin.qq.com/xpay/bind_transfer_account?pay_sig=%s");
    assertTrue(!url.contains("bind_transfer_accout"));
  }
}
