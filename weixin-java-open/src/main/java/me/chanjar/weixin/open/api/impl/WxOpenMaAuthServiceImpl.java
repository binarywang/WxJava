package me.chanjar.weixin.open.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import me.chanjar.weixin.common.bean.CommonUploadData;
import me.chanjar.weixin.common.bean.CommonUploadParam;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.api.WxOpenMaAuthService;
import me.chanjar.weixin.open.bean.auth.*;

/**
 * 微信第三方平台 小程序认证接口 (年审)
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * Created on 2024/01/11
 */
public class WxOpenMaAuthServiceImpl implements WxOpenMaAuthService {

  private final WxMaService wxMaService;

  public WxOpenMaAuthServiceImpl(WxMaService wxMaService) {
    this.wxMaService = wxMaService;
  }

  @Override
  public MaAuthSubmitResult submit(MaAuthSubmitParam param) throws WxErrorException {
    String response = wxMaService.post(OPEN_MA_AUTH_SUBMIT, param);
    return WxMaGsonBuilder.create().fromJson(response, MaAuthSubmitResult.class);
  }

  @Override
  public MaAuthQueryResult query(String taskId) throws WxErrorException {
    String response = wxMaService.post(OPEN_MA_AUTH_QUERY, MapsKt.mapOf(new Pair<>("taskid", taskId)));
    return WxMaGsonBuilder.create().fromJson(response, MaAuthQueryResult.class);
  }

  @Override
  public MaAuthUploadResult upload(CommonUploadData data) throws WxErrorException {
    String response = wxMaService.upload(OPEN_MA_AUTH_UPLOAD, new CommonUploadParam("media", data));
    return WxMaGsonBuilder.create().fromJson(response, MaAuthUploadResult.class);
  }

  @Override
  public MaAuthSubmitResult resubmit(MaAuthResubmitParam param) throws WxErrorException {
    String response = wxMaService.post(OPEN_MA_AUTH_RESUBMIT, param);
    return WxMaGsonBuilder.create().fromJson(response, MaAuthSubmitResult.class);
  }

  @Override
  public MaAuthQueryIdentityTreeResult queryIdentityTree() throws WxErrorException {
    String response = wxMaService.get(OPEN_MA_AUTH_IDENTITY, null);
    return WxMaGsonBuilder.create().fromJson(response, MaAuthQueryIdentityTreeResult.class);
  }
}
