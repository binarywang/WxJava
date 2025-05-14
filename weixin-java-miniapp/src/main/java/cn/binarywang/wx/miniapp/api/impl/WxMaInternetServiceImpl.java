package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaInternetService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.internet.WxMaInternetResponse;
import cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * 服务端网络相关接口
 *
 * @author <a href="https://github.com/chutian0124">chutian0124</a>
 * created on  2021-09-06
 */
@RequiredArgsConstructor
public class WxMaInternetServiceImpl implements WxMaInternetService {
  private final WxMaService wxMaService;

  private String sha256(String data, String sessionKey) throws Exception {
    Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
    SecretKeySpec secret_key = new SecretKeySpec(sessionKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    sha256_HMAC.init(secret_key);
    byte[] array = sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8));
    StringBuilder sb = new StringBuilder();
    for (byte item : array) {
      sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
    }
    return sb.toString().toUpperCase();
  }

  @Override
  public WxMaInternetResponse getUserEncryptKey(String openid, String signature, String sigMethod) throws WxErrorException {
    String url = WxMaApiUrlConstants.Internet.GET_USER_ENCRYPT_KEY + "?openid=" + openid + "&signature=" + signature + "&sig_method=" + sigMethod;
    return getWxMaInternetResponse(url);
  }

  @Override
  public WxMaInternetResponse getUserEncryptKey(String openid, String sessionKey) throws WxErrorException {
    String signature = null;
    try {
      signature = sha256("", sessionKey);
    } catch (Exception e) {
      throw new WxErrorException("签名错误");
    }
    String url = WxMaApiUrlConstants.Internet.GET_USER_ENCRYPT_KEY + "?sig_method=hmac_sha256&openid=" + openid + "&signature=" + signature;
    return getWxMaInternetResponse(url);
  }

  private WxMaInternetResponse getWxMaInternetResponse(String url) throws WxErrorException {
    String responseContent = this.wxMaService.post(url, "");
    WxMaInternetResponse response = WxMaGsonBuilder.create().fromJson(responseContent, WxMaInternetResponse.class);
    if (response.getErrcode() == -1) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return response;
  }
}
