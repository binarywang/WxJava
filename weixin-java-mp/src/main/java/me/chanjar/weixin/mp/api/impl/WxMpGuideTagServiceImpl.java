package me.chanjar.weixin.mp.api.impl;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.api.WxMpGuideTagService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideBuyerResp;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideTagInfo;
import me.chanjar.weixin.mp.enums.WxMpApiUrl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * created on  2021/5/13/013
 */

@AllArgsConstructor
public class WxMpGuideTagServiceImpl implements WxMpGuideTagService {
  private static final String ACCOUNT = "guide_account";
  private static final String OPENID = "guide_openid";
  private final WxMpService mpService;

  @Override
  public void newGuideTagOption(String tagName, List<String> values) throws WxErrorException {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("tag_name", tagName);
    body.put("tag_values", values);
    this.mpService.post(WxMpApiUrl.Guide.NEW_GUIDE_TAG_OPTION, body);
  }

  @Override
  public void delGuideTagOption(String tagName) throws WxErrorException {
    this.mpService.post(WxMpApiUrl.Guide.DEL_GUIDE_TAG_OPTION, GsonHelper.buildJsonObject("tag_name", tagName));
  }

  @Override
  public void addGuideTagOption(String tagName, List<String> values) throws WxErrorException {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("tag_name", tagName);
    body.put("tag_values", values);
    this.mpService.post(WxMpApiUrl.Guide.ADD_GUIDE_TAG_OPTION, body);
  }

  @Override
  public List<WxMpGuideTagInfo> getGuideTagOption() throws WxErrorException {
    String returnString = this.mpService.post(WxMpApiUrl.Guide.GET_GUIDE_TAG_OPTION, new JsonObject());
    List<WxMpGuideTagInfo> infoList = WxGsonBuilder.create().fromJson(GsonParser.parse(returnString).getAsJsonArray("options"),
      new TypeToken<List<WxMpGuideTagInfo>>() {
      }.getType());
    return infoList.stream().filter(it -> !it.getTagName().equals("")).collect(Collectors.toList());
  }

  @Override
  public List<WxMpGuideBuyerResp> addGuideBuyerTag(String account, String openid, String value, List<String> userOpenIds) throws WxErrorException {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put(ACCOUNT, account);
    body.put(OPENID, openid);
    body.put("tag_value", value);
    body.put("openid_list", userOpenIds);
    String returnString = this.mpService.post(WxMpApiUrl.Guide.ADD_GUIDE_BUYER_TAG, body);
    return WxGsonBuilder.create().fromJson(GsonParser.parse(returnString).getAsJsonArray("buyer_resp"),
      new TypeToken<List<WxMpGuideBuyerResp>>() {
      }.getType());
  }

  @Override
  public void addGuideBuyerTag(String account, String openid, String value, String userOpenid) throws WxErrorException {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put(ACCOUNT, account);
    body.put(OPENID, openid);
    body.put("tag_value", value);
    body.put("openid", userOpenid);
    this.mpService.post(WxMpApiUrl.Guide.ADD_GUIDE_BUYER_TAG, body);
  }

  @Override
  public List<String> getGuideBuyerTag(String account, String openid, String userOpenid, Boolean isExclude) throws WxErrorException {
    String returnString = this.mpService.post(WxMpApiUrl.Guide.GET_GUIDE_BUYER_TAG,
      GsonHelper.buildJsonObject(ACCOUNT, account, OPENID, openid,
        "openid", userOpenid));
    List<String> list = WxGsonBuilder.create().fromJson(GsonParser.parse(returnString).getAsJsonArray("tag_values"),
      new TypeToken<List<String>>() {
      }.getType());
    if (isExclude) {
      if (!list.isEmpty()) {
        if (list.get(list.size() - 1).contains("\n")) {
          list.remove(list.size() - 1);
        }
      }
    }
    return list;
  }

  @Override
  public List<String> queryGuideBuyerByTag(String account, String openid, Integer pushCount, List<String> value) throws WxErrorException {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put(ACCOUNT, account);
    body.put(OPENID, openid);
    body.put("push_count", pushCount);
    body.put("tag_values", value);
    String returnString = this.mpService.post(WxMpApiUrl.Guide.QUERY_GUIDE_BUYER_BY_TAG, body);
    return WxGsonBuilder.create().fromJson(GsonParser.parse(returnString).getAsJsonArray("openid_list"),
      new TypeToken<List<String>>() {
      }.getType());
  }

  @Override
  public List<WxMpGuideBuyerResp> delGuideBuyerTag(String account, String openid, String value, List<String> userOpenIds) throws WxErrorException {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put(ACCOUNT, account);
    body.put(OPENID, openid);
    body.put("tag_value", value);
    body.put("openid_list", userOpenIds);
    String returnString = this.mpService.post(WxMpApiUrl.Guide.DEL_GUIDE_BUYER_TAG, body);
    return WxGsonBuilder.create().fromJson(GsonParser.parse(returnString).getAsJsonArray("buyer_resp"),
      new TypeToken<List<WxMpGuideBuyerResp>>() {
      }.getType());
  }

  @Override
  public void delGuideBuyerTag(String account, String openid, String value, String userOpenid) throws WxErrorException {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put(ACCOUNT, account);
    body.put(OPENID, openid);
    body.put("tag_value", value);
    body.put("openid", userOpenid);
    this.mpService.post(WxMpApiUrl.Guide.DEL_GUIDE_BUYER_TAG, body);
  }

  @Override
  public void addGuideBuyerDisplayTag(String account, String openid, String userOpenid, List<String> msgList) throws WxErrorException {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put(ACCOUNT, account);
    body.put(OPENID, openid);
    body.put("openid", userOpenid);
    body.put("display_tag_list", msgList);
    this.mpService.post(WxMpApiUrl.Guide.ADD_GUIDE_BUYER_DISPLAY_TAG, body);
  }

  @Override
  public List<String> getGuideBuyerDisplayTag(String account, String openid, String userOpenid) throws WxErrorException {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put(ACCOUNT, account);
    body.put(OPENID, openid);
    body.put("openid", userOpenid);
    String returnString = this.mpService.post(WxMpApiUrl.Guide.GET_GUIDE_BUYER_DISPLAY_TAG, body);
    return WxGsonBuilder.create().fromJson(GsonParser.parse(returnString).getAsJsonArray("display_tag_list"),
      new TypeToken<List<String>>() {
      }.getType());
  }
}
