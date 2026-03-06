package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpHrService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.hr.WxCpHrEmployeeFieldData;
import me.chanjar.weixin.cp.bean.hr.WxCpHrEmployeeFieldDataResp;
import me.chanjar.weixin.cp.bean.hr.WxCpHrEmployeeFieldInfoResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Hr.*;

/**
 * 人事助手相关接口实现类.
 * 官方文档：https://developer.work.weixin.qq.com/document/path/99132
 *
 * @author <a href="https://github.com/leejoker">leejoker</a> created on 2024-01-01
 */
@RequiredArgsConstructor
public class WxCpHrServiceImpl implements WxCpHrService {

  private final WxCpService cpService;

  @Override
  public WxCpHrEmployeeFieldInfoResp getFieldInfo(List<String> fields) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    if (fields != null && !fields.isEmpty()) {
      jsonObject.add("fields", WxCpGsonBuilder.create().toJsonTree(fields));
    }
    String response = this.cpService.post(
      this.cpService.getWxCpConfigStorage().getApiUrl(GET_FIELD_INFO),
      jsonObject.toString()
    );
    return WxCpHrEmployeeFieldInfoResp.fromJson(response);
  }

  @Override
  public WxCpHrEmployeeFieldDataResp getEmployeeFieldInfo(List<String> userids, List<String> fields) throws WxErrorException {
    if (userids == null || userids.isEmpty()) {
      throw new IllegalArgumentException("userid 不能为空");
    }
    if (userids.size() > 20) {
      throw new IllegalArgumentException("userid 每次最多传入20个");
    }
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("userid", WxCpGsonBuilder.create().toJsonTree(userids));
    if (fields != null && !fields.isEmpty()) {
      jsonObject.add("fields", WxCpGsonBuilder.create().toJsonTree(fields));
    }
    String response = this.cpService.post(
      this.cpService.getWxCpConfigStorage().getApiUrl(GET_EMPLOYEE_FIELD_INFO),
      jsonObject.toString()
    );
    return WxCpHrEmployeeFieldDataResp.fromJson(response);
  }

  @Override
  public void updateEmployeeFieldInfo(String userid, List<WxCpHrEmployeeFieldData.FieldItem> fieldList) throws WxErrorException {
    if (userid == null || userid.trim().isEmpty()) {
      throw new IllegalArgumentException("userid 不能为空");
    }
    if (fieldList == null || fieldList.isEmpty()) {
      throw new IllegalArgumentException("fieldList 不能为空");
    }
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("userid", userid);
    jsonObject.add("field_list", WxCpGsonBuilder.create().toJsonTree(fieldList));
    this.cpService.post(
      this.cpService.getWxCpConfigStorage().getApiUrl(UPDATE_EMPLOYEE_FIELD_INFO),
      jsonObject.toString()
    );
  }
}
