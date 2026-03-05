package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaFaceService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.face.WxMaFaceGetVerifyIdRequest;
import cn.binarywang.wx.miniapp.bean.face.WxMaFaceGetVerifyIdResponse;
import cn.binarywang.wx.miniapp.bean.face.WxMaFaceQueryVerifyInfoRequest;
import cn.binarywang.wx.miniapp.bean.face.WxMaFaceQueryVerifyInfoResponse;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Face.GET_VERIFY_ID_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Face.QUERY_VERIFY_INFO_URL;

/**
 * 微信小程序人脸核身相关接口实现
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@RequiredArgsConstructor
public class WxMaFaceServiceImpl implements WxMaFaceService {
  private final WxMaService service;

  @Override
  public WxMaFaceGetVerifyIdResponse getVerifyId(WxMaFaceGetVerifyIdRequest request)
    throws WxErrorException {
    String responseContent = this.service.post(GET_VERIFY_ID_URL, request.toJson());
    return WxMaFaceGetVerifyIdResponse.fromJson(responseContent);
  }

  @Override
  public WxMaFaceQueryVerifyInfoResponse queryVerifyInfo(WxMaFaceQueryVerifyInfoRequest request)
    throws WxErrorException {
    String responseContent = this.service.post(QUERY_VERIFY_INFO_URL, request.toJson());
    return WxMaFaceQueryVerifyInfoResponse.fromJson(responseContent);
  }
}
