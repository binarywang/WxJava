package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaUserService;
import cn.binarywang.wx.miniapp.bean.WxMaCode2VerifyInfoResult;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import cn.binarywang.wx.miniapp.util.crypt.WxMaCryptUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.SignUtils;
import me.chanjar.weixin.common.util.json.GsonParser;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.User.CODE_2_VERIFY_INFO_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.User.GET_PHONE_NUMBER_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.User.SET_USER_STORAGE;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RequiredArgsConstructor
public class WxMaUserServiceImpl implements WxMaUserService {
  private static final String PHONE_INFO = "phone_info";
  private final WxMaService service;

  @Override
  public WxMaJscode2SessionResult getSessionInfo(String jsCode) throws WxErrorException {
    return service.jsCode2SessionInfo(jsCode);
  }

  @Override
  public WxMaUserInfo getUserInfo(String sessionKey, String encryptedData, String ivStr) {
    return WxMaUserInfo.fromJson(WxMaCryptUtils.decrypt(sessionKey, encryptedData, ivStr));
  }

  @Override
  public void setUserStorage(Map<String, String> kvMap, String sessionKey, String openid) throws WxErrorException {
    final WxMaConfig config = this.service.getWxMaConfig();
    JsonObject param = new JsonObject();
    JsonArray array = new JsonArray();
    for (Map.Entry<String, String> e : kvMap.entrySet()) {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("key", e.getKey());
      jsonObject.addProperty("value", e.getValue());
      array.add(jsonObject);
    }
    param.add("kv_list", array);
    String params = param.toString();
    String signature = SignUtils.createHmacSha256Sign(params, sessionKey);
    String url = String.format(SET_USER_STORAGE, config.getAppid(), signature, openid, "hmac_sha256");
    this.service.post(url, params);
  }

  @Override
  public WxMaPhoneNumberInfo getPhoneNoInfo(String sessionKey, String encryptedData, String ivStr) {
    return WxMaPhoneNumberInfo.fromJson(WxMaCryptUtils.decrypt(sessionKey, encryptedData, ivStr));
  }

  @Override
  public WxMaPhoneNumberInfo getPhoneNumber(String code) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("code", code);
    String responseContent = this.service.post(GET_PHONE_NUMBER_URL, param.toString());
    JsonObject response = GsonParser.parse(responseContent);
    if (response.has(PHONE_INFO)) {
      return WxMaGsonBuilder.create().fromJson(response.getAsJsonObject(PHONE_INFO),
        WxMaPhoneNumberInfo.class);
    }
    return null;
  }

  @Override
  public WxMaPhoneNumberInfo getPhoneNoInfo(String code) throws WxErrorException {
    return this.getPhoneNumber(code);
  }

  @Override
  public boolean checkUserInfo(String sessionKey, String rawData, String signature) {
    final String generatedSignature = DigestUtils.sha1Hex(rawData + sessionKey);
    return generatedSignature.equals(signature);
  }

  @Override
  public WxMaCode2VerifyInfoResult getCode2VerifyInfo(String code, String checkcode) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("code", code);
    param.addProperty("checkcode", checkcode);
    String responseContent = this.service.post(CODE_2_VERIFY_INFO_URL, param.toString());
    return WxMaCode2VerifyInfoResult.fromJson(responseContent);
  }

}
