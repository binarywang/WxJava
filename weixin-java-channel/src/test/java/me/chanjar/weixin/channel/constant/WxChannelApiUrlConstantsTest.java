package me.chanjar.weixin.channel.constant;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class WxChannelApiUrlConstantsTest {

  @Test
  public void testFundBankAndQrCodeUrlPrefix() {
    assertEquals(WxChannelApiUrlConstants.Fund.GET_BANK_BY_NUM_URL,
      "https://api.weixin.qq.com/shop/funds/getbankbynum");
    assertEquals(WxChannelApiUrlConstants.Fund.GET_BANK_LIST_URL,
      "https://api.weixin.qq.com/shop/funds/getbanklist");
    assertEquals(WxChannelApiUrlConstants.Fund.GET_CITY_URL,
      "https://api.weixin.qq.com/shop/funds/getcity");
    assertEquals(WxChannelApiUrlConstants.Fund.GET_PROVINCE_URL,
      "https://api.weixin.qq.com/shop/funds/getprovince");
    assertEquals(WxChannelApiUrlConstants.Fund.GET_SUB_BANK_URL,
      "https://api.weixin.qq.com/shop/funds/getsubbranch");
    assertEquals(WxChannelApiUrlConstants.Fund.GET_QRCODE_URL,
      "https://api.weixin.qq.com/shop/funds/qrcode/get");
    assertEquals(WxChannelApiUrlConstants.Fund.CHECK_QRCODE_URL,
      "https://api.weixin.qq.com/shop/funds/qrcode/check");
  }
}
