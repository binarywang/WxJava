package me.chanjar.weixin.open.api.impl;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.open.api.WxOpenComponentService;
import me.chanjar.weixin.open.api.WxOpenMpService;
import me.chanjar.weixin.open.bean.mp.FastRegisterResult;
import me.chanjar.weixin.open.bean.result.WxAmpLinkResult;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

import java.net.URLEncoder;
import java.util.Objects;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxOpenMpServiceImpl extends WxMpServiceImpl implements WxOpenMpService {
  private WxOpenComponentService wxOpenComponentService;
  private WxMpConfigStorage wxMpConfigStorage;
  private String appId;

  /**
   *
   * @param wxOpenComponentService
   * @param appId
   * @param wxMpConfigStorage
   */
  public WxOpenMpServiceImpl(WxOpenComponentService wxOpenComponentService, String appId, WxMpConfigStorage wxMpConfigStorage) {
//    wxOpenComponentService.oauth2getAccessToken(appId)
    this.wxOpenComponentService = wxOpenComponentService;
    this.appId = appId;
    this.wxMpConfigStorage = wxMpConfigStorage;
    setOAuth2Service(new WxOpenMpOAuth2ServiceImpl(wxOpenComponentService, getOAuth2Service(), wxMpConfigStorage));
    //添加addConfigStorage是为了解决处理来自微信开放平台的异步消息时调用WxMpServiceImpl.switchoverTo(String, Function)，因为configStorageMap没有任何公众号配置信息，最终会主动抛出无法找到对应公众号配置异常的问题。
    //Issue:https://gitee.com/binary/weixin-java-tools/issues/I81AAF
    addConfigStorage(appId,wxMpConfigStorage);
    initHttp();

  }

  @Override
  public WxMpConfigStorage getWxMpConfigStorage() {
    return wxMpConfigStorage;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    return wxOpenComponentService.getAuthorizerAccessToken(appId, forceRefresh);
  }

  @SneakyThrows
  @Override
  public String getFastRegisterAuthUrl(String redirectUri, Boolean copyWxVerify) {
    String copyInfo = Objects.equals(copyWxVerify, false) ? "0" : "1";
    String componentAppId = wxOpenComponentService.getWxOpenConfigStorage().getComponentAppId();
    String encoded = URLEncoder.encode(redirectUri, "UTF-8");
    return String.format(URL_FAST_REGISTER_AUTH, appId, componentAppId, copyInfo, encoded);
  }

  @Override
  public FastRegisterResult fastRegister(String ticket) throws WxErrorException {
    String json = post(API_FAST_REGISTER, ImmutableMap.of("ticket", ticket));
    return FastRegisterResult.fromJson(json);
  }


  @Override
  public WxAmpLinkResult getWxAmpLink() throws WxErrorException {
    String response = post(API_WX_AMP_LINK_GET, "{}");
    return WxMaGsonBuilder.create().fromJson(response, WxAmpLinkResult.class);
  }

  @Override
  public WxOpenResult wxAmpLink(String appid, String notifyUsers, String showProfile) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("appid", appid);
    params.addProperty("notify_users", notifyUsers);
    params.addProperty("show_profile", showProfile);
    String response = post(API_WX_AMP_LINK_CREATE, params.toString());
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenResult wxAmpUnLink(String appid) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("appid", appid);
    String response = post(API_WX_AMP_LINK_UN, params.toString());
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

}
