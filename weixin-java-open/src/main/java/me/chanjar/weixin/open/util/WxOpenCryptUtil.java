package me.chanjar.weixin.open.util;

import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import org.apache.commons.lang3.StringUtils;

import java.util.Base64;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxOpenCryptUtil extends me.chanjar.weixin.common.util.crypto.WxCryptUtil {
  /**
   * 构造函数
   *
   * @param wxOpenConfigStorage
   */
  public WxOpenCryptUtil(WxOpenConfigStorage wxOpenConfigStorage) {
    /*
     * @param token          公众平台上，开发者设置的token
     * @param encodingAesKey 公众平台上，开发者设置的EncodingAESKey
     * @param appId          公众平台appid
     */
    String encodingAesKey = wxOpenConfigStorage.getComponentAesKey();
    String token = wxOpenConfigStorage.getComponentToken();
    String appId = wxOpenConfigStorage.getComponentAppId();

    this.token = token;
    this.appidOrCorpid = appId;
    this.aesKey = Base64.getDecoder().decode(StringUtils.remove(encodingAesKey, " "));
  }
}
