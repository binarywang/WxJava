package me.chanjar.weixin.cp.api.impl;

import com.google.gson.Gson;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.school.user.WxCpDepartmentList;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.School.DEPARTMENT_LIST;
import static org.testng.Assert.assertEquals;

public class WxCpSchoolUserServiceImplTest {


  String allDeptListJson = "{\n" +
    "\t\"errcode\": 0,\n" +
    "\t\"errmsg\": \"ok\",\n" +
    "\t\"departments\": [\n" +
    "\t\t{\n" +
    "\t\t\t\"name\": \"一年级\",\n" +
    "\t\t\t\"parentid\": 1,\n" +
    "\t\t\t\"id\": 2,\n" +
    "\t\t\t\"type\":2,\n" +
    "\t\t\t\"register_year\":2018,\n" +
    "\t\t\t\"standard_grade\":1,\n" +
    "\t\t\t\"order\":1,\n" +
    "\t\t\t\"department_admins\": [\n" +
    "\t\t\t\t{\n" +
    "\t\t\t\t\t\"userid\": \"zhangsan\",\n" +
    "\t\t\t\t\t\"type\": 1\n" +
    "\t\t\t\t},\n" +
    "\t\t\t\t{\n" +
    "\t\t\t\t\t\"userid\": \"lisi\",\n" +
    "\t\t\t\t\t\"type\": 2\n" +
    "\t\t\t\t}\n" +
    "\t\t\t],\n" +
    "            \"is_graduated\": 0\n" +
    "\t\t},\n" +
    "\t\t{\n" +
    "\t\t\t\"name\": \"一年级一班\",\n" +
    "\t\t\t\"parentid\": 1,\n" +
    "\t\t\t\"id\": 3,\n" +
    "\t\t\t\"type\": 1,\n" +
    "\t\t\t\"department_admins\": [\n" +
    "\t\t\t\t{\n" +
    "\t\t\t\t\t\"userid\": \"zhangsan\",\n" +
    "\t\t\t\t\t\"type\": 3,\n" +
    "\t\t\t\t\t\"subject\":\"语文\"\n" +
    "\t\t\t\t},\n" +
    "\t\t\t\t{\n" +
    "\t\t\t\t\t\"userid\": \"lisi\",\n" +
    "\t\t\t\t\t\"type\": 4,\n" +
    "\t\t\t\t\t\"subject\":\"数学\"\n" +
    "\t\t\t\t}\n" +
    "\t\t\t],\n" +
    "\t\t\t\"open_group_chat\": 1,\n" +
    "            \"group_chat_id\": \"group_chat_id\"\n" +
    "\t\t}\n" +
    "\t]\n" +
    "}\n";

  String deptId3Json = "{\n" +
    "  \"errcode\": 0,\n" +
    "  \"errmsg\": \"ok\",\n" +
    "  \"departments\": [\n" +
    "    {\n" +
    "      \"name\": \"一年级一班\",\n" +
    "      \"parentid\": 1,\n" +
    "      \"id\": 3,\n" +
    "      \"type\": 1,\n" +
    "      \"department_admins\": [\n" +
    "        {\n" +
    "          \"userid\": \"zhangsan\",\n" +
    "          \"type\": 3,\n" +
    "          \"subject\":\"语文\"\n" +
    "        },\n" +
    "        {\n" +
    "          \"userid\": \"lisi\",\n" +
    "          \"type\": 4,\n" +
    "          \"subject\":\"数学\"\n" +
    "        }\n" +
    "      ],\n" +
    "      \"open_group_chat\": 1,\n" +
    "      \"group_chat_id\": \"group_chat_id\"\n" +
    "    }\n" +
    "  ]\n" +
    "}";

  String deptId2Json = "{\n" +
    "  \"errcode\": 0,\n" +
    "  \"errmsg\": \"ok\",\n" +
    "  \"departments\": []\n" +
    "}\n";


  @Test
  public void testListDepartmentWhenIdIsNull() throws WxErrorException {

    WxCpService mockCpService = Mockito.mock(WxCpService.class);
    WxCpSchoolUserServiceImpl wxCpSchoolUserService = new WxCpSchoolUserServiceImpl(mockCpService);
    Mockito.when(mockCpService.getWxCpConfigStorage()).thenReturn(new WxCpDefaultConfigImpl());
    Mockito.when(mockCpService.get(mockCpService.getWxCpConfigStorage().getApiUrl(DEPARTMENT_LIST), null)).thenReturn(allDeptListJson);
    WxCpDepartmentList wxCpDepartmentList = wxCpSchoolUserService.listDepartment(null);
    //WxCpDepartmentList没有重写Equals和Hashcode,不能直接比较
    Gson gson = new Gson();
    assertEquals(gson.toJson(wxCpDepartmentList), gson.toJson(gson.fromJson(allDeptListJson, WxCpDepartmentList.class)), "should be equal");

  }

  @Test
  public void testListDepartmentWhenIdIs2() throws WxErrorException {

    WxCpService mockCpService = Mockito.mock(WxCpService.class);
    WxCpSchoolUserServiceImpl wxCpSchoolUserService = new WxCpSchoolUserServiceImpl(mockCpService);
    Mockito.when(mockCpService.getWxCpConfigStorage()).thenReturn(new WxCpDefaultConfigImpl());
    Gson gson = new Gson();
    int deptId = 2;
    Mockito.when(mockCpService.get(String.format("%s?id=%s", mockCpService.getWxCpConfigStorage().getApiUrl(DEPARTMENT_LIST), deptId), null)).thenReturn(deptId2Json);
    //WxCpDepartmentList没有重写Equals和Hashcode,不能直接比较
    assertEquals(gson.toJson(wxCpSchoolUserService.listDepartment(deptId)), gson.toJson(gson.fromJson(deptId2Json, WxCpDepartmentList.class)), "should be equal");

  }

  @Test
  public void testListDepartmentWhenIdIs3() throws WxErrorException {

    WxCpService mockCpService = Mockito.mock(WxCpService.class);
    WxCpSchoolUserServiceImpl wxCpSchoolUserService = new WxCpSchoolUserServiceImpl(mockCpService);
    Mockito.when(mockCpService.getWxCpConfigStorage()).thenReturn(new WxCpDefaultConfigImpl());
    Gson gson = new Gson();
    int deptId = 3;
    Mockito.when(mockCpService.get(String.format("%s?id=%s", mockCpService.getWxCpConfigStorage().getApiUrl(DEPARTMENT_LIST), deptId), null)).thenReturn(deptId3Json);
    //WxCpDepartmentList没有重写Equals和Hashcode,不能直接比较
    assertEquals(gson.toJson(wxCpSchoolUserService.listDepartment(deptId)), gson.toJson(gson.fromJson(deptId3Json, WxCpDepartmentList.class)), "should be equal");

  }
}
