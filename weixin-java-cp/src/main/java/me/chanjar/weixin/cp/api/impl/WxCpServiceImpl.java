package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonObject;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.http.apache.ApacheBasicResponseHandler;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.constant.WxCpApiPathConsts;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.util.concurrent.locks.Lock;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.GET_AGENT_CONFIG_TICKET;
import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.GET_JSAPI_TICKET;

/**
 * <pre>
 *  默认接口实现类，使用apache httpclient实现
 * Created by Binary Wang on 2017-5-27.
 * </pre>
 * <pre>
 * 增加分布式锁（基于WxCpConfigStorage实现）的支持
 * Updated by yuanqixun on 2020-05-13
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxCpServiceImpl extends WxCpServiceApacheHttpClientImpl {
  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    final WxCpConfigStorage configStorage = getWxCpConfigStorage();
    if (!configStorage.isAccessTokenExpired() && !forceRefresh) {
      return configStorage.getAccessToken();
    }
    Lock lock = configStorage.getAccessTokenLock();
    lock.lock();
    try {
      // 拿到锁之后，再次判断一下最新的token是否过期，避免重刷
      if (!configStorage.isAccessTokenExpired() && !forceRefresh) {
        return configStorage.getAccessToken();
      }
      String url = String.format(configStorage.getApiUrl(WxCpApiPathConsts.GET_TOKEN),
        this.configStorage.getCorpId(), this.configStorage.getCorpSecret());
      try {
        HttpGet httpGet = new HttpGet(url);
        if (getRequestHttpProxy() != null) {
          RequestConfig config = RequestConfig.custom().setProxy(getRequestHttpProxy()).build();
          httpGet.setConfig(config);
        }
        String resultContent = getRequestHttpClient().execute(httpGet, ApacheBasicResponseHandler.INSTANCE);
        WxError error = WxError.fromJson(resultContent, WxType.CP);
        if (error.getErrorCode() != 0) {
          throw new WxErrorException(error);
        }

        WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
        configStorage.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
      } catch (IOException e) {
        throw new WxRuntimeException(e);
      }
    } finally {
      lock.unlock();
    }
    return configStorage.getAccessToken();
  }

  @Override
  public String getAgentJsapiTicket(boolean forceRefresh) throws WxErrorException {
    final WxCpConfigStorage configStorage = getWxCpConfigStorage();
    if (forceRefresh) {
      configStorage.expireAgentJsapiTicket();
    }
    if (configStorage.isAgentJsapiTicketExpired()) {
      Lock lock = configStorage.getAgentJsapiTicketLock();
      lock.lock();
      try {
        // 拿到锁之后，再次判断一下最新的token是否过期，避免重刷
        if (configStorage.isAgentJsapiTicketExpired()) {
          String responseContent = this.get(configStorage.getApiUrl(GET_AGENT_CONFIG_TICKET), null);
          JsonObject jsonObject = GsonParser.parse(responseContent);
          configStorage.updateAgentJsapiTicket(jsonObject.get("ticket").getAsString(),
            jsonObject.get("expires_in").getAsInt());
        }
      } finally {
        lock.unlock();
      }
    }
    return configStorage.getAgentJsapiTicket();
  }

  @Override
  public String getJsapiTicket(boolean forceRefresh) throws WxErrorException {
    final WxCpConfigStorage configStorage = getWxCpConfigStorage();
    if (forceRefresh) {
      configStorage.expireJsapiTicket();
    }

    if (configStorage.isJsapiTicketExpired()) {
      Lock lock = configStorage.getJsapiTicketLock();
      lock.lock();
      try {
        // 拿到锁之后，再次判断一下最新的token是否过期，避免重刷
        if (configStorage.isJsapiTicketExpired()) {
          String responseContent = this.get(configStorage.getApiUrl(GET_JSAPI_TICKET), null);
          JsonObject tmpJsonObject = GsonParser.parse(responseContent);
          configStorage.updateJsapiTicket(tmpJsonObject.get("ticket").getAsString(),
            tmpJsonObject.get("expires_in").getAsInt());
        }
      } finally {
        lock.unlock();
      }
    }
    return configStorage.getJsapiTicket();
  }
}
