package me.chanjar.weixin.open.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.api.WxOpenMaAuthAndIcpService;
import me.chanjar.weixin.open.bean.authandicp.WxOpenQueryAuthAndIcpResult;
import me.chanjar.weixin.open.bean.authandicp.WxOpenSubmitAuthAndIcpParam;
import me.chanjar.weixin.open.bean.authandicp.WxOpenSubmitAuthAndIcpResult;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

/**
 * 微信第三方平台 小程序认证及备案
 *
 * @author 痴货
 * @createTime 2025/06/18 23:00
 */
public class WxOpenMaAuthAndIcpServiceImpl implements WxOpenMaAuthAndIcpService {

  private final WxMaService wxMaService;

  public WxOpenMaAuthAndIcpServiceImpl(WxMaService wxMaService) {
    this.wxMaService = wxMaService;
  }

  @Override
  public WxOpenQueryAuthAndIcpResult queryAuthAndIcp(String procedureId) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("procedure_id", procedureId);
    String response = wxMaService.post(QUERY_AUTH_AND_ICP, params);
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenQueryAuthAndIcpResult.class);
  }

  @Override
  public WxOpenSubmitAuthAndIcpResult submitAuthAndIcp(WxOpenSubmitAuthAndIcpParam param) throws WxErrorException {
    String response = wxMaService.post(SUBMIT_AUTH_AND_ICP, param);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenSubmitAuthAndIcpResult.class);
  }
}
