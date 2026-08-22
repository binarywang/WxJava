package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaSchemeService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.scheme.WxMaGenerateNfcSchemeRequest;
import cn.binarywang.wx.miniapp.bean.scheme.WxMaGenerateSchemeRequest;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Scheme.GENERATE_NFC_SCHEME_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Scheme.GENERATE_SCHEME_URL;

/**
 * @author : cofedream
 * created on  : 2021-01-28
 */
@AllArgsConstructor
public class WxMaSchemeServiceImpl implements WxMaSchemeService {
  private final WxMaService wxMaService;

  /**
   * 获取小程序scheme码
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/url-scheme/urlscheme.generate.html
   *
   * @param request 请求参数
   * @throws WxErrorException 生成失败时抛出，具体错误码请看文档
   */
  @Override
  public String generate(WxMaGenerateSchemeRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(GENERATE_SCHEME_URL, request.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return jsonObject.get("openlink").getAsString();
  }

  /**
   * 获取NFC 的小程序 scheme
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/server/API/qrcode-link/url-scheme/api_generatenfcscheme.html
   *
   * @param request 请求参数
   * @throws WxErrorException 生成失败时抛出，具体错误码请看文档
   */
  @Override
  public String generateNFC(WxMaGenerateNfcSchemeRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(GENERATE_NFC_SCHEME_URL, request.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return jsonObject.get("openlink").getAsString();
  }
}
