package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaCustomserviceWorkService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.customservice.WxMaCustomserviceResult;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;



/**
 * <pre>
 *  小程序 - 微信客服 相关接口
 *  负责处理 https://api.weixin.qq.com/customservice/work/**
 *  文档：https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/kf-work/getKfWorkBound.html
 *  绑定的企业ID，需和小程序主体一致。
 *  目前仅支持绑定非个人小程序。
 *  Created by tryking123 on 2025/8/18.
 * </pre>
 *
 * @author <a href="https://github.com/tryking123">tryking123</a>
 */
@RequiredArgsConstructor
public class WxMaCustomserviceWorkServiceImpl implements WxMaCustomserviceWorkService {
  private static final String CORPID = "corpid";

  private final WxMaService service;

  @Override
  public WxMaCustomserviceResult getCustomservice() throws WxErrorException {
    String responseContent = this.service.get(GET_CUSTOMSERVICE_URL, null);
    return WxMaCustomserviceResult.fromJson(responseContent);
  }

  @Override
  public WxMaCustomserviceResult bindCustomservice(String corpid) throws WxErrorException {
    JsonObject paramJson = new JsonObject();
    paramJson.addProperty(CORPID, corpid);
    String response = this.service.post(BIND_CUSTOMSERVICE_URL, paramJson);
    return WxMaCustomserviceResult.fromJson(response);
  }

  @Override
  public WxMaCustomserviceResult unbindCustomservice(String corpid) throws WxErrorException {
    JsonObject paramJson = new JsonObject();
    paramJson.addProperty(CORPID, corpid);
    String response = this.service.post(UNBIND_CUSTOMSERVICE_URL, paramJson);
    return WxMaCustomserviceResult.fromJson(response);
  }
}
