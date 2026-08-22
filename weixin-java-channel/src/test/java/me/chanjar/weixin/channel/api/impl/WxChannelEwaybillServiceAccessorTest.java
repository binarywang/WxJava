package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

/**
 * @author GitHub Copilot
 */
public class WxChannelEwaybillServiceAccessorTest {

  @Test
  public void testGetEwaybillService() {
    WxChannelServiceImpl service = new WxChannelServiceImpl();
    assertNotNull(service.getEwaybillService());
  }
}
