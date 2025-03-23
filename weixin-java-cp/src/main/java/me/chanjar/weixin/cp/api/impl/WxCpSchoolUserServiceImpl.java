package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.WxCpSchoolUserService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.WxCpOauth2UserInfo;
import me.chanjar.weixin.cp.bean.school.user.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.ExternalContact.*;
import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.School.*;

/**
 * 企业微信家校沟通相关接口.
 * https://developer.work.weixin.qq.com/document/path/91638
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a> created on : 2022/6/18 9:10
 */
@Slf4j
@RequiredArgsConstructor
public class WxCpSchoolUserServiceImpl implements WxCpSchoolUserService {

  private final WxCpService cpService;

  @Override
  public WxCpOauth2UserInfo getUserInfo(@NonNull String code) throws WxErrorException {
    return cpService.getOauth2Service().getUserInfo(code);
  }

  @Override
  public WxCpOauth2UserInfo getSchoolUserInfo(@NonNull String code) throws WxErrorException {
    return cpService.getOauth2Service().getSchoolUserInfo(code);
  }

  @Override
  public WxCpBaseResp createStudent(@NonNull String studentUserId, @NonNull String name,
                                    @NonNull List<Integer> departments) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(CREATE_STUDENT);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("student_userid", studentUserId);
    jsonObject.addProperty("name", name);
    JsonArray jsonArray = new JsonArray();
    for (Integer depart : departments) {
      jsonArray.add(new JsonPrimitive(depart));
    }
    jsonObject.add("department", jsonArray);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBatchResultList batchCreateStudent(@NonNull WxCpBatchCreateStudentRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(BATCH_CREATE_STUDENT);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBatchResultList.fromJson(responseContent);
  }

  @Override
  public WxCpBatchResultList batchDeleteStudent(@NonNull WxCpBatchDeleteStudentRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(BATCH_DELETE_STUDENT);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBatchResultList.fromJson(responseContent);
  }

  @Override
  public WxCpBatchResultList batchUpdateStudent(@NonNull WxCpBatchUpdateStudentRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(BATCH_UPDATE_STUDENT);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBatchResultList.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp deleteStudent(@NonNull String studentUserId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(DELETE_STUDENT) + studentUserId;
    String responseContent = this.cpService.get(apiUrl, null);
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp updateStudent(@NonNull String studentUserId, String newStudentUserId, String name,
                                    List<Integer> departments) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(UPDATE_STUDENT);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("student_userid", studentUserId);
    if (StringUtils.isNotEmpty(newStudentUserId)) {
      jsonObject.addProperty("new_student_userid", newStudentUserId);
    }
    if (StringUtils.isNotEmpty(name)) {
      jsonObject.addProperty("name", name);
    }
    if (departments != null && departments.size() > 0) {
      JsonArray jsonArray = new JsonArray();
      for (Integer depart : departments) {
        jsonArray.add(new JsonPrimitive(depart));
      }
      jsonObject.add("department", jsonArray);
    }
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp createParent(@NonNull WxCpCreateParentRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(CREATE_PARENT);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBatchResultList batchCreateParent(@NonNull WxCpBatchCreateParentRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(BATCH_CREATE_PARENT);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBatchResultList.fromJson(responseContent);
  }

  @Override
  public WxCpBatchResultList batchDeleteParent(@NonNull String... userIdList) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(BATCH_DELETE_PARENT);
    JsonObject jsonObject = new JsonObject();
    JsonArray jsonArray = new JsonArray();
    for (String userId : userIdList) {
      jsonArray.add(new JsonPrimitive(userId));
    }
    jsonObject.add("useridlist", jsonArray);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpBatchResultList.fromJson(responseContent);
  }

  @Override
  public WxCpBatchResultList batchUpdateParent(@NonNull WxCpBatchUpdateParentRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(BATCH_UPDATE_PARENT);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBatchResultList.fromJson(responseContent);
  }

  @Override
  public WxCpUserResult getUser(@NonNull String userId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(GET_USER) + userId;
    String responseContent = this.cpService.get(apiUrl, null);
    return WxCpUserResult.fromJson(responseContent);
  }

  @Override
  public WxCpUserListResult getUserList(@NonNull Integer departmentId, Integer fetchChild) throws WxErrorException {
    String apiUrl = String.format(this.cpService.getWxCpConfigStorage().getApiUrl(GET_USER_LIST), departmentId,
      fetchChild);
    String responseContent = this.cpService.get(apiUrl, null);
    return WxCpUserListResult.fromJson(responseContent);
  }

  @Override
  public WxCpListParentResult getUserListParent(@NonNull Integer departmentId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(GET_USER_LIST_PARENT) + departmentId;
    String responseContent = this.cpService.get(apiUrl, null);
    return WxCpListParentResult.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp updateParent(@NonNull WxCpUpdateParentRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(UPDATE_PARENT);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp deleteParent(@NonNull String userId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(DELETE_PARENT) + userId;
    String responseContent = this.cpService.get(apiUrl, null);
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp setArchSyncMode(@NonNull Integer archSyncMode) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(SET_ARCH_SYNC_MODE);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("arch_sync_mode", archSyncMode);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpCreateDepartment createDepartment(@NonNull WxCpCreateDepartmentRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(DEPARTMENT_CREATE);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpCreateDepartment.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp updateDepartment(@NonNull WxCpUpdateDepartmentRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(DEPARTMENT_UPDATE);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp deleteDepartment(Integer id) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(DEPARTMENT_DELETE) + id;
    String responseContent = this.cpService.get(apiUrl, null);
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp setSubscribeMode(@NonNull Integer subscribeMode) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(SET_SUBSCRIBE_MODE);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("subscribe_mode", subscribeMode);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public Integer getSubscribeMode() throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(GET_SUBSCRIBE_MODE);
    String responseContent = this.cpService.get(apiUrl, null);
    return GsonParser.parse(responseContent).get("subscribe_mode").getAsInt();
  }

  @Override
  public WxCpExternalContact getExternalContact(@NonNull String externalUserId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(EXTERNAL_CONTACT_GET) + externalUserId;
    String responseContent = this.cpService.get(apiUrl, null);
    return WxCpExternalContact.fromJson(responseContent);
  }

  @Override
  public WxCpAllowScope getAllowScope(@NonNull Integer agentId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(GET_ALLOW_SCOPE) + agentId;
    String responseContent = this.cpService.get(apiUrl, null);
    return WxCpAllowScope.fromJson(responseContent);
  }

  @Override
  public String convertToOpenId(@NonNull String externalUserId) throws WxErrorException {
    return cpService.getExternalContactService().convertToOpenid(externalUserId);
  }

  @Override
  public WxCpDepartmentList listDepartment(Integer id) throws WxErrorException {
    String apiUrl = Objects.isNull(id) ? this.cpService.getWxCpConfigStorage().getApiUrl(DEPARTMENT_LIST) : String.format("%s?id=%s", this.cpService.getWxCpConfigStorage().getApiUrl(DEPARTMENT_LIST), id);
    String responseContent = this.cpService.get(apiUrl, null);
    return WxCpDepartmentList.fromJson(responseContent);
  }

  @Override
  public WxCpSubscribeQrCode getSubscribeQrCode() throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(GET_SUBSCRIBE_QR_CODE);
    String responseContent = this.cpService.get(apiUrl, null);
    return WxCpSubscribeQrCode.fromJson(responseContent);
  }

  @Override
  public WxCpSetUpgradeInfo setUpgradeInfo(Long upgradeTime, Integer upgradeSwitch) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(SET_UPGRADE_INFO);
    JsonObject jsonObject = new JsonObject();
    if (upgradeTime != null) {
      jsonObject.addProperty("upgrade_time", upgradeTime);
    }
    if (upgradeSwitch != null) {
      jsonObject.addProperty("upgrade_switch", upgradeSwitch);
    }
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpSetUpgradeInfo.fromJson(responseContent);
  }

}
