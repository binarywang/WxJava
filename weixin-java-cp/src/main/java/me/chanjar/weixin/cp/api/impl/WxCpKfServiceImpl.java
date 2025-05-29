package me.chanjar.weixin.cp.api.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpKfService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.kf.*;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Kf.*;

/**
 * 微信客服接口-服务实现
 *
 * @author Fu  created on  2022/1/19 19:41
 */
@RequiredArgsConstructor
public class WxCpKfServiceImpl implements WxCpKfService {
  private final WxCpService cpService;
  private static final Gson GSON = new GsonBuilder().create();

  @Override
  public WxCpKfAccountAddResp addAccount(WxCpKfAccountAdd add) throws WxErrorException {
    String url = cpService.getWxCpConfigStorage().getApiUrl(ACCOUNT_ADD);
    String responseContent = cpService.post(url, WxCpGsonBuilder.create().toJson(add));
    return WxCpKfAccountAddResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp updAccount(WxCpKfAccountUpd upd) throws WxErrorException {
    String url = cpService.getWxCpConfigStorage().getApiUrl(ACCOUNT_UPD);
    String responseContent = cpService.post(url, WxCpGsonBuilder.create().toJson(upd));
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp delAccount(WxCpKfAccountDel del) throws WxErrorException {
    String url = cpService.getWxCpConfigStorage().getApiUrl(ACCOUNT_DEL);
    String responseContent = cpService.post(url, WxCpGsonBuilder.create().toJson(del));
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpKfAccountListResp listAccount(Integer offset, Integer limit) throws WxErrorException {
    String url = cpService.getWxCpConfigStorage().getApiUrl(ACCOUNT_LIST);
    JsonObject json = new JsonObject();
    if (offset != null) {
      json.addProperty("offset", offset);
    }
    if (limit != null) {
      json.addProperty("limit", limit);
    }
    String responseContent = cpService.post(url, json.toString());
    return WxCpKfAccountListResp.fromJson(responseContent);
  }

  @Override
  public WxCpKfAccountLinkResp getAccountLink(WxCpKfAccountLink link) throws WxErrorException {
    String url = cpService.getWxCpConfigStorage().getApiUrl(ADD_CONTACT_WAY);
    String responseContent = cpService.post(url, WxCpGsonBuilder.create().toJson(link));
    return WxCpKfAccountLinkResp.fromJson(responseContent);
  }

  @Override
  public WxCpKfServicerOpResp addServicer(String openKfid, List<String> userIdList) throws WxErrorException {
    return servicerOp(openKfid, userIdList, null, SERVICER_ADD);
  }

  @Override
  public WxCpKfServicerOpResp addServicer(String openKfId, List<String> userIdList, List<String> departmentIdList) throws WxErrorException {
    validateParameters(SERVICER_ADD, userIdList, departmentIdList);
    return servicerOp(openKfId, userIdList, departmentIdList, SERVICER_ADD);
  }

  @Override
  public WxCpKfServicerOpResp delServicer(String openKfid, List<String> userIdList) throws WxErrorException {
    return servicerOp(openKfid, userIdList, null, SERVICER_DEL);
  }

  @Override
  public WxCpKfServicerOpResp delServicer(String openKfid, List<String> userIdList, List<String> departmentIdList) throws WxErrorException {
    validateParameters(SERVICER_DEL, userIdList, departmentIdList);
    return servicerOp(openKfid, userIdList, departmentIdList, SERVICER_DEL);
  }

  private void validateParameters(String uri, List<String> userIdList, List<String> departmentIdList) {
    if ((userIdList == null || userIdList.isEmpty()) && (departmentIdList == null || departmentIdList.isEmpty())) {
      throw new IllegalArgumentException("userid_list和department_id_list至少需要填其中一个");
    }
    if (SERVICER_DEL.equals(uri)) {
      if (userIdList != null && userIdList.size() > 100) {
        throw new IllegalArgumentException("可填充个数：0 ~ 100。超过100个需分批调用。");
      }
      if (departmentIdList != null && departmentIdList.size() > 100) {
        throw new IllegalArgumentException("可填充个数：0 ~ 100。超过100个需分批调用。");
      }
    } else {
      if (userIdList != null && userIdList.size() > 100) {
        throw new IllegalArgumentException("可填充个数：0 ~ 100。超过100个需分批调用。");
      }
      if (departmentIdList != null && departmentIdList.size() > 20) {
        throw new IllegalArgumentException("可填充个数：0 ~ 20。");
      }
    }
  }

  private WxCpKfServicerOpResp servicerOp(String openKfid, List<String> userIdList, List<String> departmentIdList, String uri) throws WxErrorException {
    String url = cpService.getWxCpConfigStorage().getApiUrl(uri);

    JsonObject json = new JsonObject();
    json.addProperty("open_kfid", openKfid);
    if (userIdList != null && !userIdList.isEmpty()) {
      JsonArray userIdArray = new JsonArray();
      userIdList.forEach(userIdArray::add);
      json.add("userid_list", userIdArray);
    }
    if (departmentIdList != null && !departmentIdList.isEmpty()) {
      JsonArray departmentIdArray = new JsonArray();
      departmentIdList.forEach(departmentIdArray::add);
      json.add("department_id_list", departmentIdArray);
    }
    String responseContent = cpService.post(url, json.toString());
    return WxCpKfServicerOpResp.fromJson(responseContent);
  }

  @Override
  public WxCpKfServicerListResp listServicer(String openKfid) throws WxErrorException {
    String url = cpService.getWxCpConfigStorage().getApiUrl(SERVICER_LIST + openKfid);
    String responseContent = cpService.get(url, null);
    return WxCpKfServicerListResp.fromJson(responseContent);
  }

  @Override
  public WxCpKfServiceStateResp getServiceState(String openKfid, String externalUserId)
    throws WxErrorException {
    String url = cpService.getWxCpConfigStorage().getApiUrl(SERVICE_STATE_GET);

    JsonObject json = new JsonObject();
    json.addProperty("open_kfid", openKfid);
    json.addProperty("external_userid", externalUserId);

    String responseContent = cpService.post(url, json.toString());
    return WxCpKfServiceStateResp.fromJson(responseContent);
  }

  @Override
  public WxCpKfServiceStateTransResp transServiceState(String openKfid, String externalUserId,
                                                       Integer serviceState, String servicerUserId) throws WxErrorException {
    String url = cpService.getWxCpConfigStorage().getApiUrl(SERVICE_STATE_TRANS);

    JsonObject json = new JsonObject();
    json.addProperty("open_kfid", openKfid);
    json.addProperty("external_userid", externalUserId);
    json.addProperty("service_state", serviceState);
    json.addProperty("servicer_userid", servicerUserId);

    String responseContent = cpService.post(url, json.toString());
    return WxCpKfServiceStateTransResp.fromJson(responseContent);
  }

  @Override
  public WxCpKfMsgListResp syncMsg(String cursor, String token, Integer limit, Integer voiceFormat)
    throws WxErrorException {
    String url = cpService.getWxCpConfigStorage().getApiUrl(SYNC_MSG);

    JsonObject json = new JsonObject();
    if (cursor != null) {
      json.addProperty("cursor", cursor);
    }
    if (token != null) {
      json.addProperty("token", token);
    }
    if (limit != null) {
      json.addProperty("limit", limit);
    }
    if (voiceFormat != null) {
      json.addProperty("voice_format", voiceFormat);
    }

    String responseContent = cpService.post(url, json);
    return WxCpKfMsgListResp.fromJson(responseContent);
  }

  @Override
  public WxCpKfMsgListResp syncMsg(String cursor, String token, Integer limit, Integer voiceFormat, String openKfId) throws WxErrorException {
    String url = cpService.getWxCpConfigStorage().getApiUrl(SYNC_MSG);

    JsonObject json = new JsonObject();
    if (cursor!=null) {
      json.addProperty("cursor", cursor);
    }
    if (token!=null) {
      json.addProperty("token", token);
    }
    if (limit!=null) {
      json.addProperty("limit", limit);
    }
    if (voiceFormat!=null) {
      json.addProperty("voice_format", voiceFormat);
    }
    if (openKfId != null) {
      json.addProperty("open_kfid", openKfId);
    }

    String responseContent = cpService.post(url, json);
    return WxCpKfMsgListResp.fromJson(responseContent);
  }

  @Override
  public WxCpKfMsgSendResp sendMsg(WxCpKfMsgSendRequest request) throws WxErrorException {
    String url = cpService.getWxCpConfigStorage().getApiUrl(SEND_MSG);

    String responseContent = cpService.post(url, GSON.toJson(request));

    return WxCpKfMsgSendResp.fromJson(responseContent);
  }

  @Override
  public WxCpKfMsgSendResp sendMsgOnEvent(WxCpKfMsgSendRequest request) throws WxErrorException {
    String url = cpService.getWxCpConfigStorage().getApiUrl(SEND_MSG_ON_EVENT);

    String responseContent = cpService.post(url, GSON.toJson(request));

    return WxCpKfMsgSendResp.fromJson(responseContent);
  }

  @Override
  public WxCpKfCustomerBatchGetResp customerBatchGet(List<String> externalUserIdList)
    throws WxErrorException {
    String url = cpService.getWxCpConfigStorage().getApiUrl(CUSTOMER_BATCH_GET);

    JsonArray array = new JsonArray();

    externalUserIdList.forEach(array::add);
    JsonObject json = new JsonObject();
    json.add("external_userid_list", array);
    String responseContent = cpService.post(url, json.toString());
    return WxCpKfCustomerBatchGetResp.fromJson(responseContent);
  }

  @Override
  public WxCpKfServiceUpgradeConfigResp getUpgradeServiceConfig() throws WxErrorException {
    String url = cpService.getWxCpConfigStorage().getApiUrl(CUSTOMER_GET_UPGRADE_SERVICE_CONFIG);

    String response = cpService.get(url, null);
    return WxCpKfServiceUpgradeConfigResp.fromJson(response);
  }

  @Override
  public WxCpBaseResp upgradeMemberService(String openKfid, String externalUserId,
                                           String userid, String wording) throws WxErrorException {
    String url = cpService.getWxCpConfigStorage().getApiUrl(CUSTOMER_UPGRADE_SERVICE);

    JsonObject json = new JsonObject();
    json.addProperty("open_kfid", openKfid);
    json.addProperty("external_userid", externalUserId);
    json.addProperty("type", 1);

    JsonObject memberJson = new JsonObject();
    memberJson.addProperty("userid", userid);
    memberJson.addProperty("wording", wording);
    json.add("member", memberJson);

    String response = cpService.post(url, json);
    return WxCpBaseResp.fromJson(response);
  }

  @Override
  public WxCpBaseResp upgradeGroupchatService(String openKfid, String externalUserId,
                                              String chatId, String wording) throws WxErrorException {
    String url = cpService.getWxCpConfigStorage().getApiUrl(CUSTOMER_UPGRADE_SERVICE);

    JsonObject json = new JsonObject();
    json.addProperty("open_kfid", openKfid);
    json.addProperty("external_userid", externalUserId);
    json.addProperty("type", 2);

    JsonObject groupchatJson = new JsonObject();
    groupchatJson.addProperty("chat_id", chatId);
    groupchatJson.addProperty("wording", wording);
    json.add("groupchat", groupchatJson);

    String response = cpService.post(url, json);
    return WxCpBaseResp.fromJson(response);
  }

  @Override
  public WxCpBaseResp cancelUpgradeService(String openKfid, String externalUserId)
    throws WxErrorException {
    String url = cpService.getWxCpConfigStorage().getApiUrl(CUSTOMER_CANCEL_UPGRADE_SERVICE);

    JsonObject json = new JsonObject();
    json.addProperty("open_kfid", openKfid);
    json.addProperty("external_userid", externalUserId);
    String response = cpService.post(url, json);
    return WxCpBaseResp.fromJson(response);
  }

  @Override
  public WxCpKfGetCorpStatisticResp getCorpStatistic(WxCpKfGetCorpStatisticRequest request) throws WxErrorException {
    String url = cpService.getWxCpConfigStorage().getApiUrl(GET_CORP_STATISTIC);
    String responseContent = cpService.post(url, GSON.toJson(request));
    return WxCpKfGetCorpStatisticResp.fromJson(responseContent);
  }

  @Override
  public WxCpKfGetServicerStatisticResp getServicerStatistic(WxCpKfGetServicerStatisticRequest request) throws WxErrorException {
    String url = cpService.getWxCpConfigStorage().getApiUrl(GET_SERVICER_STATISTIC);
    String responseContent = cpService.post(url, GSON.toJson(request));
    return WxCpKfGetServicerStatisticResp.fromJson(responseContent);
  }

}
