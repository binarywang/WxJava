package me.chanjar.weixin.cp.tp.service.impl;

import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.bean.WxCpInviteResult;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.bean.WxCpUserExternalContactInfo;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import me.chanjar.weixin.cp.tp.service.WxCpTpUserService;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;
import java.util.Map;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.User.*;

/**
 * <pre>
 *  Created by jamie on 2020/7/22.
 * </pre>
 */
@RequiredArgsConstructor
public class WxCpTpUserServiceImpl implements WxCpTpUserService {
  private final WxCpTpService mainService;

  @Override
  public void authenticate(String userId) throws WxErrorException {
    this.mainService.get(mainService.getWxCpTpConfigStorage().getApiUrl(USER_AUTHENTICATE + userId), null);
  }

  @Override
  public void create(WxCpUser user) throws WxErrorException {
    String url = mainService.getWxCpTpConfigStorage().getApiUrl(USER_CREATE);
    this.mainService.post(url, user.toJson());
  }

  @Override
  public void update(WxCpUser user) throws WxErrorException {
    String url = mainService.getWxCpTpConfigStorage().getApiUrl(USER_UPDATE);
    this.mainService.post(url, user.toJson());
  }

  @Override
  public void delete(String... userIds) throws WxErrorException {
    if (userIds.length == 1) {
      String url = mainService.getWxCpTpConfigStorage().getApiUrl(USER_DELETE + userIds[0]);
      this.mainService.get(url, null);
      return;
    }

    JsonObject jsonObject = new JsonObject();
    JsonArray jsonArray = new JsonArray();
    for (String userId : userIds) {
      jsonArray.add(new JsonPrimitive(userId));
    }

    jsonObject.add("useridlist", jsonArray);
    this.mainService.post(mainService.getWxCpTpConfigStorage().getApiUrl(USER_BATCH_DELETE),
      jsonObject.toString());
  }

  @Override
  public WxCpUser getById(String userid, String corpId) throws WxErrorException {
    String url = mainService.getWxCpTpConfigStorage().getApiUrl(USER_GET + userid);
    url += "&access_token=" + mainService.getWxCpTpConfigStorage().getAccessToken(corpId);
    String responseContent = this.mainService.get(url, null);
    return WxCpUser.fromJson(responseContent);
  }

  @Override
  public List<WxCpUser> listByDepartment(Long departId, Boolean fetchChild, Integer status, String corpId) throws WxErrorException {
    String params = "";
    if (fetchChild != null) {
      params += "&fetch_child=" + (fetchChild ? "1" : "0");
    }
    if (status != null) {
      params += "&status=" + status;
    } else {
      params += "&status=0";
    }
    params += "&access_token=" + mainService.getWxCpTpConfigStorage().getAccessToken(corpId);

    String url = mainService.getWxCpTpConfigStorage().getApiUrl(USER_LIST + departId);
    String responseContent = this.mainService.get(url, params);
    JsonObject tmpJsonElement = GsonParser.parse(responseContent);
    return WxCpGsonBuilder.create()
      .fromJson(tmpJsonElement.getAsJsonObject().get("userlist"),
        new TypeToken<List<WxCpUser>>() {
        }.getType()
      );
  }

  @Override
  public List<WxCpUser> listSimpleByDepartment(Long departId, Boolean fetchChild, Integer status, String corpId)
    throws WxErrorException {
    StringBuilder params = new StringBuilder();
    if (fetchChild != null) {
      params.append("fetch_child=").append(fetchChild ? "1" : "0");
    }
    if (status != null) {
      if (params.length() > 0) {
        params.append('&');
      }
      params.append("status=").append(status);
    } else {
      if (params.length() > 0) {
        params.append('&');
      }
      params.append("status=0");
    }
    if (params.length() > 0) {
      params.append('&');
    }
    params.append("access_token=")
      .append(mainService.getWxCpTpConfigStorage().getAccessToken(corpId));

    String url = mainService.getWxCpTpConfigStorage().getApiUrl(USER_SIMPLE_LIST + departId);
    String responseContent = this.mainService.get(url, params.toString(), true);
    JsonObject tmpJsonElement = GsonParser.parse(responseContent);
    return WxCpGsonBuilder.create()
      .fromJson(
        tmpJsonElement.getAsJsonObject().get("userlist"),
        new TypeToken<List<WxCpUser>>() {
        }.getType()
      );
  }

  @Override
  @Deprecated
  public List<WxCpUser> listSimpleByDepartment(Long departId, Boolean fetchChild, Integer status)
    throws WxErrorException {
    String params = "";
    if (fetchChild != null) {
      params += "&fetch_child=" + (fetchChild ? "1" : "0");
    }
    if (status != null) {
      params += "&status=" + status;
    } else {
      params += "&status=0";
    }

    String url = mainService.getWxCpTpConfigStorage().getApiUrl(USER_SIMPLE_LIST + departId);
    String responseContent = this.mainService.get(url, params);
    JsonObject tmpJsonElement = GsonParser.parse(responseContent);
    return WxCpGsonBuilder.create()
      .fromJson(
        tmpJsonElement.getAsJsonObject().get("userlist"),
        new TypeToken<List<WxCpUser>>() {
        }.getType()
      );
  }

  @Override
  public WxCpInviteResult invite(List<String> userIds, List<String> partyIds, List<String> tagIds)
    throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    if (userIds != null) {
      JsonArray jsonArray = new JsonArray();
      for (String userId : userIds) {
        jsonArray.add(new JsonPrimitive(userId));
      }
      jsonObject.add("user", jsonArray);
    }

    if (partyIds != null) {
      JsonArray jsonArray = new JsonArray();
      for (String userId : partyIds) {
        jsonArray.add(new JsonPrimitive(userId));
      }
      jsonObject.add("party", jsonArray);
    }

    if (tagIds != null) {
      JsonArray jsonArray = new JsonArray();
      for (String tagId : tagIds) {
        jsonArray.add(new JsonPrimitive(tagId));
      }
      jsonObject.add("tag", jsonArray);
    }

    String url = mainService.getWxCpTpConfigStorage().getApiUrl(BATCH_INVITE);
    return WxCpInviteResult.fromJson(this.mainService.post(url, jsonObject.toString()));
  }

  @Override
  public Map<String, String> userId2Openid(String userId, Integer agentId) throws WxErrorException {
    String url = mainService.getWxCpTpConfigStorage().getApiUrl(USER_CONVERT_TO_OPENID);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("userid", userId);
    if (agentId != null) {
      jsonObject.addProperty("agentid", agentId);
    }

    String responseContent = this.mainService.post(url, jsonObject.toString());
    JsonObject tmpJsonElement = GsonParser.parse(responseContent);
    Map<String, String> result = Maps.newHashMap();
    if (tmpJsonElement.getAsJsonObject().get("openid") != null) {
      result.put("openid", tmpJsonElement.getAsJsonObject().get("openid").getAsString());
    }

    if (tmpJsonElement.getAsJsonObject().get("appid") != null) {
      result.put("appid", tmpJsonElement.getAsJsonObject().get("appid").getAsString());
    }

    return result;
  }

  @Override
  public String openid2UserId(String openid) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("openid", openid);
    String url = mainService.getWxCpTpConfigStorage().getApiUrl(USER_CONVERT_TO_USERID);
    String responseContent = this.mainService.post(url, jsonObject.toString());
    JsonObject tmpJsonElement = GsonParser.parse(responseContent);
    return tmpJsonElement.getAsJsonObject().get("userid").getAsString();
  }

  @Override
  public String getUserId(String mobile, String corpId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("mobile", mobile);
    String url = mainService.getWxCpTpConfigStorage().getApiUrl(GET_USER_ID)
    + "?access_token=" + mainService.getWxCpTpConfigStorage().getAccessToken(corpId);
    String responseContent = this.mainService.post(url, jsonObject.toString());
    JsonObject tmpJsonElement = GsonParser.parse(responseContent);
    return tmpJsonElement.getAsJsonObject().get("userid").getAsString();
  }

  @Override
  public WxCpUserExternalContactInfo getExternalContact(String userId) throws WxErrorException {
    String url = mainService.getWxCpTpConfigStorage().getApiUrl(GET_EXTERNAL_CONTACT + userId);
    String responseContent = this.mainService.get(url, null);
    return WxCpUserExternalContactInfo.fromJson(responseContent);
  }
}
