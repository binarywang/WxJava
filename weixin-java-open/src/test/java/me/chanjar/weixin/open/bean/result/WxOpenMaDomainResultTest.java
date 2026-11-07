package me.chanjar.weixin.open.bean.result;

import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class WxOpenMaDomainResultTest {

  @Test
  public void testFromJson() {
    String json = "{"
      + "\"udpdomain\":[\"udp://valid.example.com\"],"
      + "\"tcpdomain\":[\"tcp://valid.example.com\"],"
      + "\"invalid_udpdomain\":[\"udp://invalid.example.com\"],"
      + "\"invalid_tcpdomain\":[\"tcp://invalid.example.com\"]"
      + "}";

    WxOpenMaDomainResult result = WxOpenMaDomainResult.fromJson(json);

    assertEquals(result.getUdpDomain(), Arrays.asList("udp://valid.example.com"));
    assertEquals(result.getTcpDomain(), Arrays.asList("tcp://valid.example.com"));
    assertEquals(result.getInvalidUdpDomain(), Arrays.asList("udp://invalid.example.com"));
    assertEquals(result.getInvalidTcpDomain(), Arrays.asList("tcp://invalid.example.com"));
  }
}
