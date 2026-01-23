package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.employee.WxMaSendEmployeeMsgRequest;
import cn.binarywang.wx.miniapp.bean.employee.WxMaUnbindEmployeeRequest;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.*;

@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaEmployeeRelationServiceImplTest {

  @Inject
  protected WxMaService wxService;

  @Test
  public void testSendEmployeeMsg() throws WxErrorException {
    WxMaSendEmployeeMsgRequest wxMaSendEmployeeMsgRequest = new WxMaSendEmployeeMsgRequest();
    wxMaSendEmployeeMsgRequest.setPage("/pages/index/index");
    wxMaSendEmployeeMsgRequest.setTouser("o0uBr12b1zdgCk1qDoBivmSYb9GA");
    wxMaSendEmployeeMsgRequest.setTemplateId("nmO-O4V33TOREVLAlumwPCsHssqkt7mea_cyWNE-IFmZqT9jh_LsERhzDOsOqa-3");

    // 使用 HashMap 构建数据结构
    Map<String, Object> data1 = new HashMap<>();
    // 内层字段
    Map<String, Object> dataContent = getStringObjectMap();

    data1.put("data", dataContent);
    wxMaSendEmployeeMsgRequest.setData(WxMaGsonBuilder.create().toJson(data1));
    this.wxService.getEmployeeRelationService().sendEmployeeMsg(wxMaSendEmployeeMsgRequest);
  }

  @NotNull
  private static Map<String, Object> getStringObjectMap() {
    Map<String, String> thing1 = new HashMap<>();
    Map<String, String> thing2 = new HashMap<>();
    Map<String, String> time1 = new HashMap<>();
    Map<String, String> character_string1 = new HashMap<>();
    Map<String, String> time2 = new HashMap<>();
    thing1.put("value", "高和蓝枫箱体测试");
    thing2.put("value", "门口全英测试");
    time1.put("value", "2026年11月23日 19:19");
    character_string1.put("value", "50kg");
    time2.put("value", "2026年11月23日 19:19");

    // 模板消息变量，有顺序要求
    Map<String, Object> dataContent = new LinkedHashMap<>();
    dataContent.put("thing1", thing1);
    dataContent.put("thing2", thing2);
    dataContent.put("time1", time1);
    dataContent.put("character_string1", character_string1);
    dataContent.put("time2", time2);
    return dataContent;
  }


  @Test
  public void testUnbinduserb2cauthinfo() throws WxErrorException {
    WxMaUnbindEmployeeRequest wxMaUnbindEmployeeRequest = new WxMaUnbindEmployeeRequest();
    wxMaUnbindEmployeeRequest.setOpenidList(List.of("o0uBr12b1zdgCk1qDoBivmSYb9GA"));
    this.wxService.getEmployeeRelationService().unbindEmployee(wxMaUnbindEmployeeRequest);
  }

}
