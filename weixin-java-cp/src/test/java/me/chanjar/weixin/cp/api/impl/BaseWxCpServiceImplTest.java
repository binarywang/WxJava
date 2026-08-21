package me.chanjar.weixin.cp.api.impl;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BaseWxCpServiceImplTest {

  @Test
  public void redactQueryStringShouldHideTemporaryResponseUrlCredentials() {
    assertEquals(BaseWxCpServiceImpl.redactQueryString("https://example.com/reply?token=temporary-secret&nonce=123"),
      "https://example.com/reply?******");
  }

  @Test
  public void redactQueryStringShouldKeepUrlWithoutQueryString() {
    assertEquals(BaseWxCpServiceImpl.redactQueryString("https://example.com/reply"), "https://example.com/reply");
  }
}
