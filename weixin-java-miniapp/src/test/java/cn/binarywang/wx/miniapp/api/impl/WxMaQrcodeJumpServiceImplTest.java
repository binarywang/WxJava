package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaQrcodeJumpService;
import cn.binarywang.wx.miniapp.bean.qrcode.WxMaQrcodeJumpRule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.QrcodeJump.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * {@link WxMaQrcodeJumpServiceImpl} 单元测试。
 */
public class WxMaQrcodeJumpServiceImplTest {

  private cn.binarywang.wx.miniapp.api.WxMaService wxMaService;
  private WxMaQrcodeJumpService qrcodeJumpService;

  @BeforeMethod
  public void setUp() {
    this.wxMaService = mock(cn.binarywang.wx.miniapp.api.WxMaService.class);
    this.qrcodeJumpService = new WxMaQrcodeJumpServiceImpl(this.wxMaService);
  }

  @Test
  public void testAddRule() throws WxErrorException {
    when(this.wxMaService.post(anyString(), anyString())).thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");

    WxMaQrcodeJumpRule rule = WxMaQrcodeJumpRule.builder()
      .prefix("/pages/index")
      .permitSubRule(true)
      .openVersion(1)
      .path("pages/index")
      .build();

    String result = this.qrcodeJumpService.addRule(rule);
    assertTrue(result.contains("\"errcode\":0"));
    verify(this.wxMaService).post(eq(QRCODE_JUMP_ADD), anyString());
  }

  @Test
  public void testGetRules() throws WxErrorException {
    when(this.wxMaService.post(anyString(), anyString()))
      .thenReturn("{\"rule_list\":[{\"prefix\":\"/pages/index\",\"path\":\"pages/index\"}]}");

    List<WxMaQrcodeJumpRule> rules = this.qrcodeJumpService.getRules(false, "/pages");

    assertNotNull(rules);
    assertEquals(rules.size(), 1);
    assertEquals(rules.get(0).getPrefix(), "/pages/index");
    assertEquals(rules.get(0).getPath(), "pages/index");
    verify(this.wxMaService).post(eq(QRCODE_JUMP_GET), eq("{\"is_default\":false,\"prefix\":\"/pages\"}"));
  }

  @Test
  public void testGetRuleList() throws WxErrorException {
    when(this.wxMaService.post(anyString(), anyString()))
      .thenReturn("{\"rule_list\":[{\"prefix\":\"/pages/index\",\"path\":\"pages/index\"}]}");

    List<WxMaQrcodeJumpRule> rules = this.qrcodeJumpService.getRuleList(1, 1, 20);

    assertNotNull(rules);
    assertEquals(rules.size(), 1);
    assertEquals(rules.get(0).getPrefix(), "/pages/index");
    verify(this.wxMaService).post(eq(QRCODE_JUMP_GET_LIST), eq("{\"get_type\":1,\"page_num\":1,\"page_size\":20}"));
  }

  @Test
  public void testGetRuleListWhenNoRules() throws WxErrorException {
    when(this.wxMaService.post(anyString(), anyString())).thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");

    List<WxMaQrcodeJumpRule> rules = this.qrcodeJumpService.getRuleList(null, null, null);

    assertNotNull(rules);
    assertTrue(rules.isEmpty());
    verify(this.wxMaService).post(eq(QRCODE_JUMP_GET_LIST), eq("{}"));
  }

  @Test
  public void testDeleteRule() throws WxErrorException {
    when(this.wxMaService.post(anyString(), anyString())).thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");

    String result = this.qrcodeJumpService.deleteRule("/pages/index");

    assertTrue(result.contains("\"errcode\":0"));
    verify(this.wxMaService).post(eq(QRCODE_JUMP_DELETE), eq("{\"prefix\":\"/pages/index\"}"));
  }
}

