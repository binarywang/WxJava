package me.chanjar.weixin.open.api.impl;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;

/**
 * @author yangyidian
 * created on  2020/01/09
 **/
public abstract class AbstractWxOpenInRedisConfigStorage extends WxOpenInMemoryConfigStorage {
  protected static final String COMPONENT_VERIFY_TICKET_KEY = "wechat_component_verify_ticket:";
  protected static final String COMPONENT_ACCESS_TOKEN_KEY = "wechat_component_access_token:";

  protected static final String AUTHORIZER_REFRESH_TOKEN_KEY = "wechat_authorizer_refresh_token:";
  protected static final String AUTHORIZER_ACCESS_TOKEN_KEY = "wechat_authorizer_access_token:";

  protected static final String LOCK_KEY = "wechat_lock:";

  protected static final String JSAPI_TICKET_KEY = "wechat_jsapi_ticket:";
  protected static final String CARD_API_TICKET_KEY = "wechat_card_api_ticket:";

  /**
   * redis 存储的 key 的前缀，可为空
   */
  protected String keyPrefix;
  protected String componentVerifyTicketKey;
  protected String componentAccessTokenKey;
  protected String authorizerRefreshTokenKey;
  protected String authorizerAccessTokenKey;
  protected String jsapiTicketKey;
  protected String cardApiTicket;
  protected String lockKey;

  @Override
  public void setComponentAppId(String componentAppId) {
    super.setComponentAppId(componentAppId);
    String prefix = StringUtils.isBlank(keyPrefix) ? "" :
      (Strings.CS.endsWith(keyPrefix, ":") ? keyPrefix : (keyPrefix + ":"));
    componentVerifyTicketKey = prefix + COMPONENT_VERIFY_TICKET_KEY.concat(componentAppId);
    componentAccessTokenKey = prefix + COMPONENT_ACCESS_TOKEN_KEY.concat(componentAppId);
    authorizerRefreshTokenKey = prefix + AUTHORIZER_REFRESH_TOKEN_KEY.concat(componentAppId);
    authorizerAccessTokenKey = prefix + AUTHORIZER_ACCESS_TOKEN_KEY.concat(componentAppId);
    lockKey = prefix + LOCK_KEY.concat(componentAppId);
    jsapiTicketKey = prefix + JSAPI_TICKET_KEY.concat(componentAppId);
    cardApiTicket = prefix + CARD_API_TICKET_KEY.concat(componentAppId);
  }

  protected String getKey(String prefix, String appId) {
    return prefix.endsWith(":") ? prefix.concat(appId) : prefix.concat(":").concat(appId);
  }

}
