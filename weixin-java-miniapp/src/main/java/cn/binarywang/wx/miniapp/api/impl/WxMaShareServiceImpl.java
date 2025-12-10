package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaShareService;
import cn.binarywang.wx.miniapp.bean.WxMaGroupEnterInfo;
import cn.binarywang.wx.miniapp.bean.WxMaShareInfo;
import cn.binarywang.wx.miniapp.util.crypt.WxMaCryptUtils;
import lombok.RequiredArgsConstructor;

/**
 * @author zhfish
 */
@RequiredArgsConstructor
public class WxMaShareServiceImpl implements WxMaShareService {
  private final WxMaService service;

  @Override
  public WxMaShareInfo getShareInfo(String sessionKey, String encryptedData, String ivStr) {
    return WxMaShareInfo.fromJson(WxMaCryptUtils.decrypt(sessionKey, encryptedData, ivStr));

  }

  @Override
  public WxMaGroupEnterInfo getGroupEnterInfo(String sessionKey, String encryptedData, String ivStr) {
    return WxMaGroupEnterInfo.fromJson(WxMaCryptUtils.decrypt(sessionKey, encryptedData, ivStr));
  }
}
