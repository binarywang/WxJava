package me.chanjar.weixin.cp.config.impl;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class WxCpDefaultConfigImplTest {

  @Test
  public void shouldStoreAgentIdBeyondIntegerRange() {
    WxCpDefaultConfigImpl config = new WxCpDefaultConfigImpl();

    config.setAgentId(1013699173317L);

    assertEquals(config.getAgentId(), Long.valueOf(1013699173317L));
  }
}
