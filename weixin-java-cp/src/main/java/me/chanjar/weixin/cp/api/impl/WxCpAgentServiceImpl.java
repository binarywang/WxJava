package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.WxCpAgentService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpAgent;
import me.chanjar.weixin.cp.bean.WxCpTpAdmin;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Agent.*;


/**
 * <pre>
 *  管理企业号应用
 *  Created by huansinho on 2018/4/13.
 * </pre>
 *
 * @author <a href="https://github.com/huansinho">huansinho</a>
 */
@RequiredArgsConstructor
public class WxCpAgentServiceImpl implements WxCpAgentService {


  private final WxCpService mainService;

  @Override
  public WxCpAgent get(Integer agentId) throws WxErrorException {
    if (agentId == null) {
      throw new IllegalArgumentException("缺少agentid参数");
    }

    final String url = String.format(this.mainService.getWxCpConfigStorage().getApiUrl(AGENT_GET), agentId);
    return WxCpAgent.fromJson(this.mainService.get(url, null));
  }

  @Override
  public void set(WxCpAgent agentInfo) throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getApiUrl(AGENT_SET);
    String responseContent = this.mainService.post(url, agentInfo.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.CP));
    }
  }

  @Override
  public List<WxCpAgent> list() throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getApiUrl(AGENT_LIST);
    String responseContent = this.mainService.get(url, null);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.CP));
    }

    return WxCpGsonBuilder.create().fromJson(jsonObject.get("agentlist").toString(), new TypeToken<List<WxCpAgent>>() {
    }.getType());
  }

  @Override
  public WxCpTpAdmin getAdminList(Integer agentId) throws WxErrorException {
    if (agentId == null) {
      throw new IllegalArgumentException("缺少agentid参数");
    }

    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("agentid", agentId);
    String url = this.mainService.getWxCpConfigStorage().getApiUrl(AGENT_GET_ADMIN_LIST);
    String responseContent = this.mainService.post(url, jsonObject.toString());
    JsonObject respObj = GsonParser.parse(responseContent);
    if (respObj.get(WxConsts.ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.CP));
    }
    return WxCpTpAdmin.fromJson(responseContent);
  }

}
