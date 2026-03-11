package me.chanjar.weixin.cp.tp.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpTpConvertTmpExternalUserIdResult;
import me.chanjar.weixin.cp.bean.WxCpTpOpenKfIdConvertResult;
import me.chanjar.weixin.cp.bean.WxCpTpTagIdListConvertResult;
import me.chanjar.weixin.cp.bean.WxCpTpUnionidToExternalUseridResult;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.constant.WxCpApiPathConsts;
import me.chanjar.weixin.cp.tp.service.WxCpTpIdConvertService;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;


/**
 * @author cocoa
 */
@RequiredArgsConstructor
public class WxCpTpIdConvertServiceImpl implements WxCpTpIdConvertService {
  private final WxCpTpService mainService;

  @Override
  public WxCpTpUnionidToExternalUseridResult unionidToExternalUserid(String cropId, String unionid, String openid, Integer subjectType) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("unionid", unionid);
    json.addProperty("openid", openid);
    if (subjectType != null) {
      json.addProperty("subject_type", subjectType);
    }
    WxCpTpConfigStorage wxCpTpConfigStorage = mainService.getWxCpTpConfigStorage();
    String accessToken = wxCpTpConfigStorage.getAccessToken(cropId);
    String url = wxCpTpConfigStorage.getApiUrl(WxCpApiPathConsts.IdConvert.UNION_ID_TO_EXTERNAL_USER_ID);
    url += "?access_token=" + accessToken;
    String responseContent = this.mainService.post(url, json.toString());
    return WxCpTpUnionidToExternalUseridResult.fromJson(responseContent);
  }

  @Override
  public WxCpTpTagIdListConvertResult externalTagId(String corpId, String... externalTagIdList) throws WxErrorException {
    WxCpTpConfigStorage wxCpTpConfigStorage = mainService.getWxCpTpConfigStorage();
    String url = wxCpTpConfigStorage.getApiUrl(WxCpApiPathConsts.IdConvert.EXTERNAL_TAG_ID ) + "?access_token=" + mainService.getWxCpTpConfigStorage().getAccessToken(corpId);

    JsonObject jsonObject = new JsonObject();
    JsonArray jsonArray = new JsonArray();
    for (String tagId : externalTagIdList) {
      jsonArray.add(new JsonPrimitive(tagId));
    }
    jsonObject.add("external_tagid_list", jsonArray);
    String responseContent = this.mainService.post(url, jsonObject.toString());

    return WxCpTpTagIdListConvertResult.fromJson(responseContent);
  }

  @Override
  public WxCpTpOpenKfIdConvertResult ConvertOpenKfId(String corpId, String... openKfIdList) throws WxErrorException {
    WxCpTpConfigStorage wxCpTpConfigStorage = mainService.getWxCpTpConfigStorage();
    String url = wxCpTpConfigStorage.getApiUrl(WxCpApiPathConsts.IdConvert.OPEN_KF_ID + "?access_token=" + mainService.getWxCpTpConfigStorage().getAccessToken(corpId));
    JsonObject jsonObject = new JsonObject();
    JsonArray jsonArray = new JsonArray();
    for (String kfId : openKfIdList) {
      jsonArray.add(new JsonPrimitive(kfId));
    }
    jsonObject.add("open_kfid_list", jsonArray);
    String responseContent = this.mainService.post(url, jsonObject.toString());
    return WxCpTpOpenKfIdConvertResult.fromJson(responseContent);
  }

  @Override
  public WxCpTpConvertTmpExternalUserIdResult convertTmpExternalUserId(String corpId, int businessType, int userType, String... tmpExternalUserIdList) throws WxErrorException {
    WxCpTpConfigStorage wxCpTpConfigStorage = mainService.getWxCpTpConfigStorage();
    String url = wxCpTpConfigStorage.getApiUrl(WxCpApiPathConsts.IdConvert.CONVERT_TMP_EXTERNAL_USER_ID + "?access_token=" + mainService.getWxCpTpConfigStorage().getAccessToken(corpId));
    JsonObject jsonObject = new JsonObject();
    JsonArray jsonArray = new JsonArray();
    jsonObject.addProperty("business_type", businessType);
    jsonObject.addProperty("user_type", userType);
    for (String userId : tmpExternalUserIdList) {
      jsonArray.add(new JsonPrimitive(userId));
    }
    jsonObject.add("tmp_external_userid_list", jsonArray);
    String responseContent = mainService.post(url, jsonObject.toString());
    return WxCpTpConvertTmpExternalUserIdResult.fromJson(responseContent);
  }


}
