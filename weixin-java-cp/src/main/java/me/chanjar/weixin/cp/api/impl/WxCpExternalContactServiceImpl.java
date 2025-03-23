package me.chanjar.weixin.cp.api.impl;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.ExternalContact.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxCpErrorMsgEnum;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.BeanUtils;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.WxCpExternalContactService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.external.*;
import me.chanjar.weixin.cp.bean.external.acquisition.*;
import me.chanjar.weixin.cp.bean.external.contact.*;
import me.chanjar.weixin.cp.bean.external.interceptrule.WxCpInterceptRule;
import me.chanjar.weixin.cp.bean.external.interceptrule.WxCpInterceptRuleAddRequest;
import me.chanjar.weixin.cp.bean.external.interceptrule.WxCpInterceptRuleInfo;
import me.chanjar.weixin.cp.bean.external.interceptrule.WxCpInterceptRuleList;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * The type Wx cp external contact service.
 *
 * @author 曹祖鹏 & yuanqixun & Mr.Pan & Wang_Wong
 */
@RequiredArgsConstructor
public class WxCpExternalContactServiceImpl implements WxCpExternalContactService {
  private final WxCpService mainService;

  @Override
  public WxCpContactWayResult addContactWay(WxCpContactWayInfo info) throws WxErrorException {

    if (info.getContactWay().getUsers() != null && info.getContactWay().getUsers().size() > 100) {
      throw new WxRuntimeException("「联系我」使用人数默认限制不超过100人(包括部门展开后的人数)");
    }

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(ADD_CONTACT_WAY);

    return WxCpContactWayResult.fromJson(this.mainService.post(url, info.getContactWay().toJson()));
  }

  @Override
  public WxCpContactWayInfo getContactWay(String configId) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("config_id", configId);

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_CONTACT_WAY);
    return WxCpContactWayInfo.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpContactWayList listContactWay(Long startTime, Long endTime, String cursor, Long limit) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("start_time", startTime);
    json.addProperty("end_time", endTime);
    json.addProperty("cursor", cursor);
    json.addProperty("limit", limit);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(LIST_CONTACT_WAY);
    return WxCpContactWayList.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpBaseResp updateContactWay(WxCpContactWayInfo info) throws WxErrorException {
    if (StringUtils.isBlank(info.getContactWay().getConfigId())) {
      throw new WxRuntimeException("更新「联系我」方式需要指定configId");
    }
    if (info.getContactWay().getUsers() != null && info.getContactWay().getUsers().size() > 100) {
      throw new WxRuntimeException("「联系我」使用人数默认限制不超过100人(包括部门展开后的人数)");
    }

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(UPDATE_CONTACT_WAY);

    return WxCpBaseResp.fromJson(this.mainService.post(url, info.getContactWay().toJson()));
  }

  @Override
  public WxCpBaseResp deleteContactWay(String configId) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("config_id", configId);

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(DEL_CONTACT_WAY);

    return WxCpBaseResp.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpBaseResp closeTempChat(String userId, String externalUserId) throws WxErrorException {

    JsonObject json = new JsonObject();
    json.addProperty("userid", userId);
    json.addProperty("external_userid", externalUserId);


    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(CLOSE_TEMP_CHAT);

    return WxCpBaseResp.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpExternalContactInfo getExternalContact(String externalUserId) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_EXTERNAL_CONTACT + externalUserId);
    return WxCpExternalContactInfo.fromJson(this.mainService.get(url, null));
  }

  @Override
  public WxCpExternalContactInfo getContactDetail(String externalUserId, String cursor) throws WxErrorException {
    String params = externalUserId;
    if (StringUtils.isNotEmpty(cursor)) {
      params = params + "&cursor=" + cursor;
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_CONTACT_DETAIL + params);
    return WxCpExternalContactInfo.fromJson(this.mainService.get(url, null));
  }

  @Override
  public String convertToOpenid(String externalUserId) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("external_userid", externalUserId);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(CONVERT_TO_OPENID);
    String responseContent = this.mainService.post(url, json.toString());
    return GsonParser.parse(responseContent).get("openid").getAsString();
  }

  @Override
  public String unionidToExternalUserid(String unionid, String openid) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("unionid", unionid);
    if (StringUtils.isNotEmpty(openid)) {
      json.addProperty("openid", openid);
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(UNIONID_TO_EXTERNAL_USERID);
    String responseContent = this.mainService.post(url, json.toString());
    return GsonParser.parse(responseContent).get("external_userid").getAsString();
  }

  @Override
  public String toServiceExternalUserid(String externalUserid) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("external_userid", externalUserid);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(TO_SERVICE_EXTERNAL_USERID);
    String responseContent = this.mainService.post(url, json.toString());
    return GsonParser.parse(responseContent).get("external_userid").getAsString();
  }

  @Override
  public String fromServiceExternalUserid(String externalUserid, String sourceAgentId) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("external_userid", externalUserid);
    json.addProperty("source_agentid", sourceAgentId);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(FROM_SERVICE_EXTERNAL_USERID);
    String responseContent = this.mainService.post(url, json.toString());
    return GsonParser.parse(responseContent).get("external_userid").getAsString();
  }

  @Override
  public WxCpExternalUserIdList unionidToExternalUserid3rd(String unionid, String openid,
                                                           String corpid) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("unionid", unionid);
    json.addProperty("openid", openid);
    if (StringUtils.isNotEmpty(corpid)) {
      json.addProperty("corpid", corpid);
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(UNIONID_TO_EXTERNAL_USERID_3RD);
    return WxCpExternalUserIdList.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpNewExternalUserIdList getNewExternalUserId(String[] externalUserIdList) throws WxErrorException {
    JsonObject json = new JsonObject();
    if (ArrayUtils.isNotEmpty(externalUserIdList)) {
      json.add("external_userid_list", new Gson().toJsonTree(externalUserIdList).getAsJsonArray());
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_NEW_EXTERNAL_USERID);
    return WxCpNewExternalUserIdList.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpBaseResp finishExternalUserIdMigration(String corpid) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("corpid", corpid);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(FINISH_EXTERNAL_USERID_MIGRATION);
    return WxCpBaseResp.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public String opengidToChatid(String opengid) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("opengid", opengid);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(OPENID_TO_CHATID);
    String responseContent = this.mainService.post(url, json.toString());
    return GsonParser.parse(responseContent).get("chat_id").getAsString();
  }

  @Override
  public WxCpExternalContactBatchInfo getContactDetailBatch(String[] userIdList, String cursor, Integer limit)
    throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_CONTACT_DETAIL_BATCH);
    JsonObject json = new JsonObject();
    json.add("userid_list", new Gson().toJsonTree(userIdList).getAsJsonArray());
    if (StringUtils.isNotBlank(cursor)) {
      json.addProperty("cursor", cursor);
    }
    if (limit != null) {
      json.addProperty("limit", limit);
    }
    String responseContent = this.mainService.post(url, json.toString());
    return WxCpExternalContactBatchInfo.fromJson(responseContent);
  }

  @Override
  public WxCpExternalContactListInfo getContactList(String cursor, Integer limit) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_CONTACT_LIST);
    JsonObject json = new JsonObject();
    if (StringUtils.isNotBlank(cursor)) {
      json.addProperty("cursor", cursor);
    }
    if (limit != null) {
      json.addProperty("limit", limit);
    }
    String responseContent = this.mainService.post(url, json.toString());
    return WxCpExternalContactListInfo.fromJson(responseContent);
  }

  @Override
  public void updateRemark(WxCpUpdateRemarkRequest request) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(UPDATE_REMARK);
    this.mainService.post(url, request.toJson());
  }

  @Override
  public List<String> listExternalContacts(String userId) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(LIST_EXTERNAL_CONTACT + userId);
    try {
      String responseContent = this.mainService.get(url, null);
      return WxCpUserExternalContactList.fromJson(responseContent).getExternalUserId();
    } catch (WxErrorException e) {
      // not external contact,无客户则返回空列表
      if (e.getError().getErrorCode() == WxCpErrorMsgEnum.CODE_84061.getCode()) {
        return Collections.emptyList();
      }
      throw e;
    }
  }

  @Override
  public List<String> listFollowers() throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_FOLLOW_USER_LIST);
    String responseContent = this.mainService.get(url, null);
    return WxCpUserWithExternalPermission.fromJson(responseContent).getFollowers();
  }

  @Override
  public WxCpUserExternalUnassignList listUnassignedList(Integer pageIndex, String cursor, Integer pageSize) throws WxErrorException {
    JsonObject json = new JsonObject();
    if (pageIndex != null) {
      json.addProperty("page_id", pageIndex);
    }
    json.addProperty("cursor", StringUtils.isEmpty(cursor) ? "" : cursor);
    json.addProperty("page_size", pageSize == null ? 1000 : pageSize);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(LIST_UNASSIGNED_CONTACT);
    return WxCpUserExternalUnassignList.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpBaseResp transferExternalContact(String externalUserid, String handOverUserid, String takeOverUserid) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("external_userid", externalUserid);
    json.addProperty("handover_userid", handOverUserid);
    json.addProperty("takeover_userid", takeOverUserid);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(TRANSFER_UNASSIGNED_CONTACT);
    return WxCpBaseResp.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpUserTransferCustomerResp transferCustomer(WxCpUserTransferCustomerReq req) throws WxErrorException {
    BeanUtils.checkRequiredFields(req);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(TRANSFER_CUSTOMER);
    final String result = this.mainService.post(url, req.toJson());
    return WxCpUserTransferCustomerResp.fromJson(result);
  }

  @Override
  public WxCpUserTransferResultResp transferResult(String handOverUserid, String takeOverUserid,
                                                   String cursor) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("cursor", cursor);
    json.addProperty("handover_userid", handOverUserid);
    json.addProperty("takeover_userid", takeOverUserid);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(TRANSFER_RESULT);
    return WxCpUserTransferResultResp.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpUserTransferCustomerResp resignedTransferCustomer(WxCpUserTransferCustomerReq req)
    throws WxErrorException {
    BeanUtils.checkRequiredFields(req);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(RESIGNED_TRANSFER_CUSTOMER);
    return WxCpUserTransferCustomerResp.fromJson(this.mainService.post(url, req.toJson()));
  }

  @Override
  public WxCpUserTransferResultResp resignedTransferResult(String handOverUserid,
                                                           String takeOverUserid, String cursor)
    throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("cursor", cursor);
    json.addProperty("handover_userid", handOverUserid);
    json.addProperty("takeover_userid", takeOverUserid);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(RESIGNED_TRANSFER_RESULT);
    return WxCpUserTransferResultResp.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpUserExternalGroupChatList listGroupChat(Integer pageIndex, Integer pageSize, int status,
                                                     String[] userIds, String[] partyIds) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("offset", pageIndex == null ? 0 : pageIndex);
    json.addProperty("limit", pageSize == null ? 100 : pageSize);
    json.addProperty("status_filter", status);
    if (ArrayUtils.isNotEmpty(userIds) || ArrayUtils.isNotEmpty(partyIds)) {
      JsonObject ownerFilter = new JsonObject();
      if (ArrayUtils.isNotEmpty(userIds)) {
        ownerFilter.add("userid_list", new Gson().toJsonTree(userIds).getAsJsonArray());
      }
      if (ArrayUtils.isNotEmpty(partyIds)) {
        ownerFilter.add("partyid_list", new Gson().toJsonTree(partyIds).getAsJsonArray());
      }
      json.add("owner_filter", ownerFilter);
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GROUP_CHAT_LIST);
    return WxCpUserExternalGroupChatList.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpUserExternalGroupChatList listGroupChat(Integer limit, String cursor, int status, String[] userIds) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("cursor", cursor == null ? "" : cursor);
    json.addProperty("limit", limit == null ? 100 : limit);
    json.addProperty("status_filter", status);
    if (ArrayUtils.isNotEmpty(userIds)) {
      JsonObject ownerFilter = new JsonObject();
      if (ArrayUtils.isNotEmpty(userIds)) {
        ownerFilter.add("userid_list", new Gson().toJsonTree(userIds).getAsJsonArray());
      }
      json.add("owner_filter", ownerFilter);
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GROUP_CHAT_LIST);
    return WxCpUserExternalGroupChatList.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpUserExternalGroupChatInfo getGroupChat(String chatId, Integer needName) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("chat_id", chatId);
    json.addProperty("need_name", needName);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GROUP_CHAT_INFO);
    return WxCpUserExternalGroupChatInfo.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpUserExternalGroupChatTransferResp transferGroupChat(String[] chatIds, String newOwner) throws WxErrorException {
    JsonObject json = new JsonObject();
    if (ArrayUtils.isNotEmpty(chatIds)) {
      json.add("chat_id_list", new Gson().toJsonTree(chatIds).getAsJsonArray());
    }
    json.addProperty("new_owner", newOwner);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GROUP_CHAT_TRANSFER);
    return WxCpUserExternalGroupChatTransferResp.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpUserExternalGroupChatTransferResp onjobTransferGroupChat(String[] chatIds, String newOwner) throws WxErrorException {
    JsonObject json = new JsonObject();
    if (ArrayUtils.isNotEmpty(chatIds)) {
      json.add("chat_id_list", new Gson().toJsonTree(chatIds).getAsJsonArray());
    }
    json.addProperty("new_owner", newOwner);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GROUP_CHAT_ONJOB_TRANSFER);
    return WxCpUserExternalGroupChatTransferResp.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpUserExternalUserBehaviorStatistic getUserBehaviorStatistic(Date startTime, Date endTime,
                                                                        String[] userIds, String[] partyIds) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("start_time", startTime.getTime() / 1000);
    json.addProperty("end_time", endTime.getTime() / 1000);
    if (ArrayUtils.isNotEmpty(userIds) || ArrayUtils.isNotEmpty(partyIds)) {
      if (ArrayUtils.isNotEmpty(userIds)) {
        json.add("userid", new Gson().toJsonTree(userIds).getAsJsonArray());
      }
      if (ArrayUtils.isNotEmpty(partyIds)) {
        json.add("partyid", new Gson().toJsonTree(partyIds).getAsJsonArray());
      }
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(LIST_USER_BEHAVIOR_DATA);
    return WxCpUserExternalUserBehaviorStatistic.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpUserExternalGroupChatStatistic getGroupChatStatistic(Date startTime, Integer orderBy, Integer orderAsc,
                                                                  Integer pageIndex, Integer pageSize,
                                                                  String[] userIds, String[] partyIds) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("day_begin_time", startTime.getTime() / 1000);
    json.addProperty("order_by", orderBy == null ? 1 : orderBy);
    json.addProperty("order_asc", orderAsc == null ? 0 : orderAsc);
    json.addProperty("offset", pageIndex == null ? 0 : pageIndex);
    json.addProperty("limit", pageSize == null ? 500 : pageSize);
    if (ArrayUtils.isNotEmpty(userIds) || ArrayUtils.isNotEmpty(partyIds)) {
      JsonObject ownerFilter = new JsonObject();
      if (ArrayUtils.isNotEmpty(userIds)) {
        ownerFilter.add("userid_list", new Gson().toJsonTree(userIds).getAsJsonArray());
      }
      if (ArrayUtils.isNotEmpty(partyIds)) {
        ownerFilter.add("partyid_list", new Gson().toJsonTree(partyIds).getAsJsonArray());
      }
      json.add("owner_filter", ownerFilter);
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(LIST_GROUP_CHAT_DATA);
    return WxCpUserExternalGroupChatStatistic.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpMsgTemplateAddResult addMsgTemplate(WxCpMsgTemplate wxCpMsgTemplate) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(ADD_MSG_TEMPLATE);
    return WxCpMsgTemplateAddResult.fromJson(this.mainService.post(url, wxCpMsgTemplate.toJson()));
  }

  @Override
  public WxCpBaseResp remindGroupMsgSend(String msgId) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("msgid", msgId);
    final String url = this.mainService.getWxCpConfigStorage()
                                       .getApiUrl(REMIND_GROUP_MSG_SEND);
    return WxCpBaseResp.fromJson(this.mainService.post(url, params.toString()));
  }

  @Override
  public WxCpBaseResp cancelGroupMsgSend(String msgId) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("msgid", msgId);
    final String url = this.mainService.getWxCpConfigStorage()
                                       .getApiUrl(CANCEL_GROUP_MSG_SEND);
    return WxCpBaseResp.fromJson(this.mainService.post(url, params.toString()));
  }

  @Override
  public void sendWelcomeMsg(WxCpWelcomeMsg msg) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(SEND_WELCOME_MSG);
    this.mainService.post(url, msg.toJson());
  }

  @Override
  public WxCpUserExternalTagGroupList getCorpTagList(String[] tagId) throws WxErrorException {
    JsonObject json = new JsonObject();
    if (ArrayUtils.isNotEmpty(tagId)) {
      json.add("tag_id", new Gson().toJsonTree(tagId).getAsJsonArray());
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_CORP_TAG_LIST);
    return WxCpUserExternalTagGroupList.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpUserExternalTagGroupList getCorpTagList(String[] tagId, String[] groupId) throws WxErrorException {
    JsonObject json = new JsonObject();
    if (ArrayUtils.isNotEmpty(tagId)) {
      json.add("tag_id", new Gson().toJsonTree(tagId).getAsJsonArray());
    }
    if (ArrayUtils.isNotEmpty(groupId)) {
      json.add("group_id", new Gson().toJsonTree(groupId).getAsJsonArray());
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_CORP_TAG_LIST);
    return WxCpUserExternalTagGroupList.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpUserExternalTagGroupInfo addCorpTag(WxCpUserExternalTagGroupInfo tagGroup) throws WxErrorException {

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(ADD_CORP_TAG);
    return WxCpUserExternalTagGroupInfo.fromJson(this.mainService.post(url, tagGroup.getTagGroup().toJson()));
  }

  @Override
  public WxCpBaseResp editCorpTag(String id, String name, Integer order) throws WxErrorException {

    JsonObject json = new JsonObject();
    json.addProperty("id", id);
    json.addProperty("name", name);
    json.addProperty("order", order);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(EDIT_CORP_TAG);
    return WxCpBaseResp.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpBaseResp delCorpTag(String[] tagId, String[] groupId) throws WxErrorException {
    JsonObject json = new JsonObject();
    if (ArrayUtils.isNotEmpty(tagId)) {
      json.add("tag_id", new Gson().toJsonTree(tagId).getAsJsonArray());
    }
    if (ArrayUtils.isNotEmpty(groupId)) {
      json.add("group_id", new Gson().toJsonTree(groupId).getAsJsonArray());
    }

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(DEL_CORP_TAG);
    return WxCpBaseResp.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpBaseResp markTag(String userid, String externalUserid, String[] addTag, String[] removeTag) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("userid", userid);
    json.addProperty("external_userid", externalUserid);

    if (ArrayUtils.isNotEmpty(addTag)) {
      json.add("add_tag", new Gson().toJsonTree(addTag).getAsJsonArray());
    }
    if (ArrayUtils.isNotEmpty(removeTag)) {
      json.add("remove_tag", new Gson().toJsonTree(removeTag).getAsJsonArray());
    }

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(MARK_TAG);
    return WxCpBaseResp.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpAddMomentResult addMomentTask(WxCpAddMomentTask task) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(ADD_MOMENT_TASK);
    return WxCpAddMomentResult.fromJson(this.mainService.post(url, task.toJson()));
  }

  @Override
  public WxCpGetMomentTaskResult getMomentTaskResult(String jobId) throws WxErrorException {
    String params = "&jobid=" + jobId;
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_MOMENT_TASK_RESULT);
    return WxCpGetMomentTaskResult.fromJson(this.mainService.get(url, params));
  }

  @Override
  public WxCpBaseResp cancelMomentTask(String momentId) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(CANCEL_MOMENT_TASK);
    JsonObject json = new JsonObject();
    json.addProperty("moment_id", momentId);
    return WxCpBaseResp.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpGetMomentList getMomentList(Long startTime, Long endTime, String creator, Integer filterType,
                                         String cursor, Integer limit) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("start_time", startTime);
    json.addProperty("end_time", endTime);
    if (!StringUtils.isEmpty(creator)) {
      json.addProperty("creator", creator);
    }
    if (filterType != null) {
      json.addProperty("filter_type", filterType);
    }
    if (!StringUtils.isEmpty(cursor)) {
      json.addProperty("cursor", cursor);
    }
    if (limit != null) {
      json.addProperty("limit", limit);
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_MOMENT_LIST);
    return WxCpGetMomentList.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpGetMomentTask getMomentTask(String momentId, String cursor, Integer limit)
    throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("moment_id", momentId);
    if (!StringUtils.isEmpty(cursor)) {
      json.addProperty("cursor", cursor);
    }
    if (limit != null) {
      json.addProperty("limit", limit);
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_MOMENT_TASK);
    return WxCpGetMomentTask.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpGetMomentCustomerList getMomentCustomerList(String momentId, String userId,
                                                         String cursor, Integer limit) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("moment_id", momentId);
    json.addProperty("userid", userId);
    if (!StringUtils.isEmpty(cursor)) {
      json.addProperty("cursor", cursor);
    }
    if (limit != null) {
      json.addProperty("limit", limit);
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_MOMENT_CUSTOMER_LIST);
    return WxCpGetMomentCustomerList.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpGetMomentSendResult getMomentSendResult(String momentId, String userId,
                                                     String cursor, Integer limit) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("moment_id", momentId);
    json.addProperty("userid", userId);
    if (!StringUtils.isEmpty(cursor)) {
      json.addProperty("cursor", cursor);
    }
    if (limit != null) {
      json.addProperty("limit", limit);
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_MOMENT_SEND_RESULT);
    return WxCpGetMomentSendResult.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpGetMomentComments getMomentComments(String momentId, String userId)
    throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("moment_id", momentId);
    json.addProperty("userid", userId);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_MOMENT_COMMENTS);
    return WxCpGetMomentComments.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpGroupMsgListResult getGroupMsgListV2(String chatType, Date startTime, Date endTime,
                                                  String creator, Integer filterType, Integer limit, String cursor) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("chat_type", chatType);
    json.addProperty("start_time", startTime.getTime() / 1000);
    json.addProperty("end_time", endTime.getTime() / 1000);
    json.addProperty("creator", creator);
    json.addProperty("filter_type", filterType);
    json.addProperty("limit", limit);
    json.addProperty("cursor", cursor);

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_GROUP_MSG_LIST_V2);
    return WxCpGroupMsgListResult.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpGroupMsgSendResult getGroupMsgSendResult(String msgid, String userid, Integer limit, String cursor) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("msgid", msgid);
    json.addProperty("userid", userid);
    json.addProperty("limit", limit);
    json.addProperty("cursor", cursor);

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_GROUP_MSG_SEND_RESULT);
    return WxCpGroupMsgSendResult.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpGroupMsgResult getGroupMsgResult(String msgid, Integer limit, String cursor) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("msgid", msgid);
    json.addProperty("limit", limit);
    json.addProperty("cursor", cursor);

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_GROUP_MSG_RESULT);
    return WxCpGroupMsgResult.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpGroupMsgTaskResult getGroupMsgTask(String msgid, Integer limit, String cursor) throws WxErrorException {

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_GROUP_MSG_TASK);
    return WxCpGroupMsgTaskResult.fromJson(this.mainService.post(url,
      GsonHelper.buildJsonObject("msgid", msgid,
        "limit", limit,
        "cursor", cursor)));
  }

  @Override
  public String addGroupWelcomeTemplate(WxCpGroupWelcomeTemplateResult template) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GROUP_WELCOME_TEMPLATE_ADD);
    final String responseContent = this.mainService.post(url, template.toJson());
    return GsonParser.parse(responseContent).get("template_id").getAsString();
  }

  @Override
  public WxCpBaseResp editGroupWelcomeTemplate(WxCpGroupWelcomeTemplateResult template) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GROUP_WELCOME_TEMPLATE_EDIT);
    return WxCpGroupWelcomeTemplateResult.fromJson(this.mainService.post(url, template.toJson()));
  }

  @Override
  public WxCpGroupWelcomeTemplateResult getGroupWelcomeTemplate(String templateId) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("template_id", templateId);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GROUP_WELCOME_TEMPLATE_GET);
    return WxCpGroupWelcomeTemplateResult.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpBaseResp delGroupWelcomeTemplate(String templateId, String agentId) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("template_id", templateId);
    if (!StringUtils.isEmpty(agentId)) {
      json.addProperty("agentid", agentId);
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GROUP_WELCOME_TEMPLATE_DEL);
    return WxCpBaseResp.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpProductAlbumListResult getProductAlbumList(Integer limit, String cursor) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("limit", limit);
    json.addProperty("cursor", cursor);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_PRODUCT_ALBUM_LIST);
    return WxCpProductAlbumListResult.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxCpProductAlbumResult getProductAlbum(String productId) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("product_id", productId);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_PRODUCT_ALBUM);
    return WxCpProductAlbumResult.fromJson(this.mainService.post(url, json.toString()));
  }

  @Override
  public WxMediaUploadResult uploadAttachment(String mediaType, String fileType, Integer attachmentType,
                                              InputStream inputStream) throws WxErrorException, IOException {
    return uploadAttachment(mediaType, attachmentType, FileUtils.createTmpFile(inputStream,
      UUID.randomUUID().toString(), fileType));
  }

  @Override
  public WxMediaUploadResult uploadAttachment(String mediaType, Integer attachmentType, File file)
    throws WxErrorException {
    String params = "?media_type=" + mediaType + "&attachment_type=" + attachmentType;
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(UPLOAD_ATTACHMENT + params);
    return this.mainService.execute(MediaUploadRequestExecutor.create(
      this.mainService.getRequestHttp()), url, file);
  }

  @Override
  public String addInterceptRule(WxCpInterceptRuleAddRequest ruleAddRequest) throws WxErrorException {
    String responseContent = this.mainService.post(this.mainService.getWxCpConfigStorage()
      .getApiUrl(ADD_INTERCEPT_RULE), ruleAddRequest);
    return GsonParser.parse(responseContent).get("rule_id").getAsString();
  }

  @Override
  public void updateInterceptRule(WxCpInterceptRule interceptRule) throws WxErrorException {
    this.mainService.post(this.mainService.getWxCpConfigStorage().getApiUrl(UPDATE_INTERCEPT_RULE),
      interceptRule);
  }

  @Override
  public void delInterceptRule(String ruleId) throws WxErrorException {
    this.mainService.post(this.mainService.getWxCpConfigStorage().getApiUrl(DEL_INTERCEPT_RULE),
      GsonHelper.buildJsonObject("rule_id", ruleId));
  }

  @Override
  public WxCpInterceptRuleList getInterceptRuleList() throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_INTERCEPT_RULE_LIST);
    return WxCpInterceptRuleList.fromJson(this.mainService.get(url,null));
  }

  @Override
  public WxCpInterceptRuleInfo getInterceptRuleDetail(String ruleId) throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_INTERCEPT_RULE);
    String json = this.mainService.post(url, GsonHelper.buildJsonObject("rule_id", ruleId));
    return WxCpInterceptRuleInfo.fromJson(json);
  }

  @Override
  public String addProductAlbum(WxCpProductAlbumInfo wxCpProductAlbumInfo) throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getApiUrl(ADD_PRODUCT_ALBUM);
    String responseContent = this.mainService.post(url, wxCpProductAlbumInfo.toJson());
    return GsonParser.parse(responseContent).get("product_id").getAsString();
  }

  @Override
  public void updateProductAlbum(WxCpProductAlbumInfo wxCpProductAlbumInfo) throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getApiUrl(UPDATE_PRODUCT_ALBUM);
    this.mainService.post(url, wxCpProductAlbumInfo.toJson());
  }

  @Override
  public void deleteProductAlbum(String productId) throws WxErrorException {
    JsonObject o = new JsonObject();
    o.addProperty("product_id", productId);
    String url = this.mainService.getWxCpConfigStorage().getApiUrl(DELETE_PRODUCT_ALBUM);
    this.mainService.post(url, o.toString());
  }

  @Override
  public WxCpCustomerAcquisitionList customerAcquisitionLinkList(Integer limit, String cursor) throws WxErrorException {
    JsonObject o = new JsonObject();
    o.addProperty("limit", limit);
    o.addProperty("cursor", cursor);

    String url = this.mainService.getWxCpConfigStorage().getApiUrl(CUSTOMER_ACQUISITION_LINK_LIST);
    return WxCpCustomerAcquisitionList.fromJson(this.mainService.post(url, o));
  }

  @Override
  public WxCpCustomerAcquisitionInfo customerAcquisitionLinkGet(String linkId) throws WxErrorException {
    JsonObject o = new JsonObject();
    o.addProperty("link_id", linkId);

    String url = this.mainService.getWxCpConfigStorage().getApiUrl(CUSTOMER_ACQUISITION_LINK_GET);
    return WxCpCustomerAcquisitionInfo.fromJson(this.mainService.post(url, o));
  }

  @Override
  public WxCpCustomerAcquisitionCreateResult customerAcquisitionLinkCreate(WxCpCustomerAcquisitionRequest wxCpCustomerAcquisitionRequest) throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getApiUrl(CUSTOMER_ACQUISITION_LINK_CREATE);
    return WxCpCustomerAcquisitionCreateResult.fromJson(this.mainService.post(url, wxCpCustomerAcquisitionRequest.toJson()));
  }

  @Override
  public WxCpBaseResp customerAcquisitionUpdate(WxCpCustomerAcquisitionRequest wxCpCustomerAcquisitionRequest) throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getApiUrl(CUSTOMER_ACQUISITION_LINK_UPDATE);
    return WxCpBaseResp.fromJson(this.mainService.post(url, wxCpCustomerAcquisitionRequest.toJson()));
  }

  @Override
  public WxCpBaseResp customerAcquisitionLinkDelete(String linkId) throws WxErrorException {
    JsonObject o = new JsonObject();
    o.addProperty("link_id", linkId);

    String url = this.mainService.getWxCpConfigStorage().getApiUrl(CUSTOMER_ACQUISITION_LINK_DELETE);
    return WxCpBaseResp.fromJson(this.mainService.post(url, o));
  }

  @Override
  public WxCpCustomerAcquisitionCustomerList customerAcquisitionCustomer(String linkId, Integer limit, String cursor) throws WxErrorException {
    JsonObject o = new JsonObject();
    o.addProperty("link_id", linkId);
    o.addProperty("limit", limit);
    o.addProperty("cursor", cursor);

    String url = this.mainService.getWxCpConfigStorage().getApiUrl(CUSTOMER_ACQUISITION_CUSTOMER);
    return WxCpCustomerAcquisitionCustomerList.fromJson(this.mainService.post(url, o));
  }

  @Override
  public WxCpCustomerAcquisitionQuota customerAcquisitionQuota() throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getApiUrl(CUSTOMER_ACQUISITION_QUOTA);
    return WxCpCustomerAcquisitionQuota.fromJson(this.mainService.get(url, null));
  }

  @Override
  public WxCpCustomerAcquisitionStatistic customerAcquisitionStatistic(String linkId, @NonNull Date startTime,
                                                                       @NonNull Date endTime) throws WxErrorException {
    long endTimestamp = endTime.getTime() / 1000L;
    long startTimestamp = startTime.getTime() / 1000L;

    JsonObject o = new JsonObject();
    o.addProperty("link_id", linkId);
    o.addProperty("start_time", startTimestamp);
    o.addProperty("end_time", endTimestamp);

    String url = this.mainService.getWxCpConfigStorage().getApiUrl(CUSTOMER_ACQUISITION_STATISTIC);
    return WxCpCustomerAcquisitionStatistic.fromJson(this.mainService.post(url, o));
  }


  @Override
  public WxCpGroupJoinWayResult addJoinWay(WxCpGroupJoinWayInfo wxCpGroupJoinWayInfo) throws WxErrorException {
    if (wxCpGroupJoinWayInfo.getJoinWay().getChatIdList() != null && wxCpGroupJoinWayInfo.getJoinWay().getChatIdList().size() > 5) {
      throw new WxRuntimeException("使用该配置的客户群ID列表，支持5个");
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(ADD_JOIN_WAY);

    return WxCpGroupJoinWayResult.fromJson(this.mainService.post(url, wxCpGroupJoinWayInfo.getJoinWay().toJson()));
  }

  @Override
  public WxCpBaseResp updateJoinWay(WxCpGroupJoinWayInfo wxCpGroupJoinWayInfo) throws WxErrorException {
    if (wxCpGroupJoinWayInfo.getJoinWay().getChatIdList() != null && wxCpGroupJoinWayInfo.getJoinWay().getChatIdList().size() > 5) {
      throw new WxRuntimeException("使用该配置的客户群ID列表，支持5个");
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(UPDATE_JOIN_WAY);
    return WxCpBaseResp.fromJson(this.mainService.post(url, wxCpGroupJoinWayInfo.getJoinWay().toJson()));
  }

  @Override
  public WxCpGroupJoinWayInfo getJoinWay(String configId) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("config_id", configId);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_JOIN_WAY);

    return WxCpGroupJoinWayInfo.fromJson(this.mainService.post(url, json));
  }

  @Override
  public WxCpBaseResp delJoinWay(String configId) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("config_id", configId);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(DEL_JOIN_WAY);
    return WxCpBaseResp.fromJson(this.mainService.post(url, json));
  }
}
