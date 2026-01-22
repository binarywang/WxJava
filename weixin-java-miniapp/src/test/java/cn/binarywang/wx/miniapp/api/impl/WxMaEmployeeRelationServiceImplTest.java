package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaEmployeeRelationService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.employee.WxMaSendEmployeeMsgRequest;
import cn.binarywang.wx.miniapp.bean.employee.WxMaUnbindEmployeeRequest;
import cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * 小程序用工关系服务测试
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on 2026-01-22
 */
public class WxMaEmployeeRelationServiceImplTest {

  private WxMaService wxMaService;
  private WxMaEmployeeRelationService employeeRelationService;

  @BeforeMethod
  public void setUp() {
    wxMaService = mock(WxMaService.class);
    employeeRelationService = new WxMaEmployeeRelationServiceImpl(wxMaService);
  }

  @Test
  public void testUnbindEmployee() throws WxErrorException {
    // 准备测试数据
    WxMaUnbindEmployeeRequest request = WxMaUnbindEmployeeRequest.newBuilder()
      .openid("test_openid")
      .corpId("test_corp_id")
      .build();

    // Mock响应
    when(wxMaService.post(anyString(), anyString())).thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");

    // 执行测试
    employeeRelationService.unbindEmployee(request);

    // 验证调用
    verify(wxMaService, times(1)).post(
      eq(WxMaApiUrlConstants.Employee.UNBIND_EMPLOYEE_URL),
      anyString()
    );
  }

  @Test
  public void testUnbindEmployeeWithCorrectUrl() {
    // 验证URL是否包含正确的路径
    String expectedUrl = "https://api.weixin.qq.com/wxa/laboruse/unbinduserb2cauthinfo";
    Assert.assertEquals(WxMaApiUrlConstants.Employee.UNBIND_EMPLOYEE_URL, expectedUrl,
      "解绑用工关系API地址应包含 /laboruse/ 路径前缀");
  }

  @Test
  public void testSendEmployeeMsg() throws WxErrorException {
    // 准备测试数据
    WxMaSendEmployeeMsgRequest request = WxMaSendEmployeeMsgRequest.newBuilder()
      .openid("test_openid")
      .corpId("test_corp_id")
      .msg("测试用工消息")
      .build();

    // Mock响应
    when(wxMaService.post(anyString(), anyString())).thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");

    // 执行测试
    employeeRelationService.sendEmployeeMsg(request);

    // 验证调用
    verify(wxMaService, times(1)).post(
      eq(WxMaApiUrlConstants.Employee.SEND_EMPLOYEE_MSG_URL),
      anyString()
    );
  }

  @Test
  public void testSendEmployeeMsgWithCorrectUrl() {
    // 验证URL是否包含正确的路径
    String expectedUrl = "https://api.weixin.qq.com/wxa/laboruse/sendemployeerelationmsg";
    Assert.assertEquals(WxMaApiUrlConstants.Employee.SEND_EMPLOYEE_MSG_URL, expectedUrl,
      "推送用工消息API地址应包含 /laboruse/ 路径前缀");
  }

  @Test
  public void testUnbindEmployeeJsonSerialization() {
    // 测试JSON序列化
    WxMaUnbindEmployeeRequest request = WxMaUnbindEmployeeRequest.newBuilder()
      .openid("test_openid_123")
      .corpId("test_corp_id_456")
      .build();

    String json = request.toJson();

    Assert.assertNotNull(json, "JSON序列化结果不应为null");
    Assert.assertTrue(json.contains("test_openid_123"), "JSON应包含openid");
    Assert.assertTrue(json.contains("test_corp_id_456"), "JSON应包含corp_id");
  }

  @Test
  public void testSendEmployeeMsgJsonSerialization() {
    // 测试JSON序列化
    WxMaSendEmployeeMsgRequest request = WxMaSendEmployeeMsgRequest.newBuilder()
      .openid("test_openid_789")
      .corpId("test_corp_id_abc")
      .msg("这是一条测试消息")
      .build();

    String json = request.toJson();

    Assert.assertNotNull(json, "JSON序列化结果不应为null");
    Assert.assertTrue(json.contains("test_openid_789"), "JSON应包含openid");
    Assert.assertTrue(json.contains("test_corp_id_abc"), "JSON应包含corp_id");
    Assert.assertTrue(json.contains("这是一条测试消息"), "JSON应包含msg");
  }
}
