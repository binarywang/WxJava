package me.chanjar.weixin.cp.util.crypto;

import me.chanjar.weixin.common.util.crypto.WxCryptUtil;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import org.apache.commons.lang3.StringUtils;

import java.util.Base64;

/**
 * The type Wx cp tp crypt util.
 *
 * @author someone
 */
public class WxCpTpCryptUtil extends WxCryptUtil {
  /**
   * 构造函数.
   *
   * @param wxCpTpConfigStorage the wx cp tp config storage
   */
  public WxCpTpCryptUtil(WxCpTpConfigStorage wxCpTpConfigStorage) {
    /*
     * @param token          公众平台上，开发者设置的token
     * @param encodingAesKey 公众平台上，开发者设置的EncodingAESKey
     * @param appidOrCorpid          公众平台corpId
     */
    String encodingAesKey = wxCpTpConfigStorage.getEncodingAESKey();
    String token = wxCpTpConfigStorage.getToken();
    String corpId = wxCpTpConfigStorage.getCorpId();

    this.token = token;
    this.appidOrCorpid = corpId;
    this.aesKey = Base64.getDecoder().decode(StringUtils.remove(encodingAesKey, " "));
  }


}
