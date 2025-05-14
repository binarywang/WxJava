package me.chanjar.weixin.mp.api.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.api.WxMpGuideService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.guide.*;
import me.chanjar.weixin.mp.enums.WxMpApiUrl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * .
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2020-10-06
 */
@AllArgsConstructor
public class WxMpGuideServiceImpl implements WxMpGuideService {
  private static final String ACCOUNT = "guide_account";
  private static final String OPENID = "guide_openid";
  private final WxMpService mpService;

  @Override
  public void addGuide(String account, String openid, String headImgUrl, String nickName) throws WxErrorException {
    this.mpService.post(WxMpApiUrl.Guide.ADD_GUIDE, GsonHelper.buildJsonObject(ACCOUNT, account,
      "guide_headimgurl", headImgUrl, "guide_nickname", nickName, OPENID, openid));
  }

  @Override
  public void addGuide(WxMpGuideInfo guideInfo) throws WxErrorException {
    this.mpService.post(WxMpApiUrl.Guide.ADD_GUIDE,
      GsonHelper.buildJsonObject(ACCOUNT, guideInfo.getAccount(),
        "guide_headimgurl", guideInfo.getHeadImgUrl(),
        "guide_nickname", guideInfo.getNickName(),
        OPENID, guideInfo.getOpenid()));
  }

  @Override
  public void updateGuide(WxMpGuideInfo guideInfo) throws WxErrorException {
    this.mpService.post(WxMpApiUrl.Guide.UPDATE_GUIDE,
      GsonHelper.buildJsonObject(ACCOUNT, guideInfo.getAccount(),
        "guide_headimgurl", guideInfo.getHeadImgUrl(),
        "guide_nickname", guideInfo.getNickName(),
        OPENID, guideInfo.getOpenid()));

  }

  @Override
  public WxMpGuideInfo getGuide(String account, String openid) throws WxErrorException {
    return WxMpGuideInfo.fromJson(this.mpService.post(WxMpApiUrl.Guide.GET_GUIDE,
      GsonHelper.buildJsonObject(ACCOUNT, account, OPENID, openid)));
  }

  @Override
  public void delGuide(String account, String openid) throws WxErrorException {
    this.mpService.post(WxMpApiUrl.Guide.DEL_GUIDE,
      GsonHelper.buildJsonObject(ACCOUNT, account, OPENID, openid));
  }

  @Override
  public WxMpGuideList listGuide(int page, int num) throws WxErrorException {
    return WxMpGuideList.fromJson(this.mpService.post(WxMpApiUrl.Guide.LIST_GUIDE,
      GsonHelper.buildJsonObject("page", page, "num", num)));
  }

  @Override
  public String createGuideQrCode(String account, String openid, String qrcodeInfo) throws WxErrorException {
    String json = this.mpService.post(WxMpApiUrl.Guide.CREATE_QR_CODE,
      GsonHelper.buildJsonObject(ACCOUNT, account, OPENID, openid, "qrcode_info", qrcodeInfo));
    return GsonParser.parse(json).get("qrcode_url").toString().replaceAll("\"","");
  }

  @Override
  public WxMpGuideMsgList getGuideChatRecord(String account, String openid, String clientOpenid, Long beginTime, Long endTime, int page, int num) throws WxErrorException {
    return WxMpGuideMsgList.fromJson(this.mpService.post(WxMpApiUrl.Guide.GET_GUIDE_CHAT_RECORD,
      GsonHelper.buildJsonObject(ACCOUNT, account, OPENID, openid,
        "begin_time", beginTime,
        "end_time", endTime,
        "page", page,
        "num", num)
    ));
  }

  @Override
  public void setGuideConfig(String account, String openid, boolean isDelete, List<String> guideFastReplyList, WxMpAddGuideAutoReply guideAutoReply,WxMpAddGuideAutoReply guideAutoReplyPlus) throws WxErrorException {
    JsonArray jsonArray = null;
    if (guideFastReplyList != null) {
      jsonArray = new JsonArray();
      for (String it : guideFastReplyList) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("content", it);
        jsonArray.add(jsonObject);
      }
    }
    Map<String, Object> body = new LinkedHashMap<>();
    body.put(ACCOUNT, account);
    body.put(OPENID, openid);
    body.put("is_delete", isDelete);
    body.put("guide_fast_reply_list", jsonArray);
    body.put("guide_auto_reply", guideAutoReply);
    body.put("guide_auto_reply_plus", guideAutoReplyPlus);

    this.mpService.post(WxMpApiUrl.Guide.SET_GUIDE_CONFIG,body);
  }

  @Override
  public WxMpGuideConfig getGuideConfig(String account, String openid) throws WxErrorException {
    return WxMpGuideConfig.fromJson(this.mpService.post(WxMpApiUrl.Guide.GET_GUIDE_CONFIG,
      GsonHelper.buildJsonObject(ACCOUNT, account, OPENID, openid)));
  }

  @Override
  public void setGuideAcctConfig(boolean isDelete, List<String> blackKeyWord, String guideAutoReply) throws WxErrorException {
    JsonObject jsonObject1 = null;
    if (blackKeyWord != null && !blackKeyWord.isEmpty()) {
      jsonObject1 = new JsonObject();
      JsonArray jsonArray = new JsonArray();
      blackKeyWord.forEach(jsonArray::add);
      jsonObject1.add("values", jsonArray);
    }

    JsonObject jsonObject2 = null;
    if (guideAutoReply != null) {
      jsonObject2 = new JsonObject();
      jsonObject2.addProperty("content", guideAutoReply);
    }

    this.mpService.post(WxMpApiUrl.Guide.SET_GUIDE_ACCT_CONFIG,
      GsonHelper.buildJsonObject(
        "is_delete", isDelete,
        "black_keyword", jsonObject1,
        "guide_auto_reply", jsonObject2));
  }

  @Override
  public WxMpGuideAcctConfig getGuideAcctConfig() throws WxErrorException {
    return WxMpGuideAcctConfig.fromJson(this.mpService.post(WxMpApiUrl.Guide.GET_GUIDE_ACCT_CONFIG, new JsonObject()));
  }

  @Override
  public void pushShowWxaPathMenu(String appId, String userName) throws WxErrorException {
    this.mpService.post(WxMpApiUrl.Guide.PUSH_SHOW_WX_PATH_MENU,
      GsonHelper.buildJsonObject("wxa_appid", appId, "wx_username", userName));
  }

  @Override
  public Long newGuideGroup(String groupName) throws WxErrorException {
    String json = this.mpService.post(WxMpApiUrl.Guide.NEW_GUIDE_GROUP,
      GsonHelper.buildJsonObject("group_name", groupName));
    return Long.valueOf(GsonParser.parse(json).get("group_id").toString());
  }

  @Override
  public List<WxMpGuideGroup> getGuideGroupList() throws WxErrorException {
    String json = this.mpService.post(WxMpApiUrl.Guide.GET_GUIDE_GROUP_LIST, new JsonObject());
    return WxGsonBuilder.create().fromJson(GsonParser.parse(json).getAsJsonArray("group_list"),
      new TypeToken<List<WxMpGuideGroup>>() {
      }.getType());
  }

  @Override
  public WxMpGuideGroupInfoList getGroupInfo(long groupId, int page, int num) throws WxErrorException {
    return WxMpGuideGroupInfoList.fromJson(this.mpService.post(WxMpApiUrl.Guide.GET_GROUP_GUIDE_INFO,
      GsonHelper.buildJsonObject("group_id", groupId, "page", page, "num", num)
    ));
  }

  @Override
  public void addGuide2GuideGroup(long groupId, String account) throws WxErrorException {
    this.mpService.post(WxMpApiUrl.Guide.ADD_GROUP_GUIDE,
      GsonHelper.buildJsonObject("group_id", groupId, ACCOUNT, account));
  }

  @Override
  public void delGuide2GuideGroup(long groupId, String account) throws WxErrorException {
    this.mpService.post(WxMpApiUrl.Guide.DEL_GROUP_GUIDE,
      GsonHelper.buildJsonObject("group_id", groupId, ACCOUNT, account));
  }

  @Override
  public List<Long> getGroupByGuide(String account) throws WxErrorException {
    String json = this.mpService.post(WxMpApiUrl.Guide.GET_GROUP_ON_GUIDE,
      GsonHelper.buildJsonObject(ACCOUNT, account));
    return WxGsonBuilder.create().fromJson(GsonParser.parse(json).getAsJsonArray("group_id_list"),
      new TypeToken<List<Long>>() {
      }.getType());
  }

  @Override
  public void delGuideGroup(long groupId) throws WxErrorException {
    this.mpService.post(WxMpApiUrl.Guide.DEL_GROUP,
      GsonHelper.buildJsonObject("group_id", groupId));
  }
}
