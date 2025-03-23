package me.chanjar.weixin.open.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.api.WxOpenMaEmbeddedService;
import me.chanjar.weixin.open.bean.result.WxOpenMaEmbeddedListResult;
import org.apache.commons.lang3.StringUtils;

/**
 * 半屏小程序管理服务
 * <pre>
 *   <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/embedded-management/addEmbedded.html">半屏小程序管理</a>
 * </pre>
 *
 * @author Yuan
 * @version 1.0.0
 * @date 2024-12-04 16:55:19
 */
@AllArgsConstructor
public class WxOpenMaEmbeddedServiceImpl implements WxOpenMaEmbeddedService {

  private final WxMaService wxMaService;

  /**
   * 添加半屏小程序
   *
   * @param embeddedAppId 半屏小程序appId
   * @param applyReason   申请理由
   * @author Yuan
   * @date 2024-12-04 17:33:33
   */
  @Override
  public void addEmbedded(String embeddedAppId, String applyReason) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("appid", embeddedAppId);
    if (StringUtils.isNotBlank(applyReason)) {
      params.addProperty("apply_reason", applyReason);
    }
    String response = wxMaService.post(API_ADD_EMBEDDED, params);
    WxError wxError = WxError.fromJson(response, WxType.MiniApp);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
  }

  /**
   * 删除半屏小程序
   *
   * @param embeddedAppId 半屏小程序appId
   * @author Yuan
   * @date 2024-12-04 17:33:33
   */
  @Override
  public void deleteEmbedded(String embeddedAppId) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("appid", embeddedAppId);
    String response = wxMaService.post(API_DELETE_EMBEDDED, params);
    WxError wxError = WxError.fromJson(response, WxType.MiniApp);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
  }

  /**
   * 获取半屏小程序调用列表
   *
   * @author Yuan
   * @date 2024-12-04 17:33:33
   */
  @Override
  public WxOpenMaEmbeddedListResult getEmbeddedList() throws WxErrorException {
    String response = wxMaService.get(API_GET_EMBEDDED_LIST, null);
    WxError wxError = WxError.fromJson(response, WxType.MiniApp);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaEmbeddedListResult.class);
  }

  /**
   * 取消授权小程序
   *
   * @param embeddedAppId 半屏小程序appId
   * @author Yuan
   * @date 2024-12-04 17:33:33
   */
  @Override
  public void deleteAuthorizedEmbedded(String embeddedAppId) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("appid", embeddedAppId);
    String response = wxMaService.post(API_DELETE_AUTHORIZED_EMBEDDED, params);
    WxError wxError = WxError.fromJson(response, WxType.MiniApp);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
  }

  /**
   * 获取半屏小程序授权列表
   *
   * @return {@link WxOpenMaEmbeddedListResult }
   * @author Yuan
   * @date 2024-12-04 17:33:33
   */
  @Override
  public WxOpenMaEmbeddedListResult getOwnList() throws WxErrorException {
    String response = wxMaService.get(API_GET_OWN_LIST + "?num=1000", null);
    WxError wxError = WxError.fromJson(response, WxType.MiniApp);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaEmbeddedListResult.class);
  }

  /**
   * 获取半屏小程序授权列表
   *
   * @param start 分页起始值 ，默认值为0
   * @param num   一次拉取最大值，最大 1000，默认值为10
   * @return {@link WxOpenMaEmbeddedListResult }
   * @author Yuan
   * @date 2024-12-04 17:33:33
   */
  @Override
  public WxOpenMaEmbeddedListResult getOwnList(Integer start, Integer num) throws WxErrorException {
    if (null == start) {
      start = 0;
    }
    if (null == num) {
      num = 10;
    }
    if (num > 1000) {
      num = 1000;
    }
    String response = wxMaService.get(API_GET_OWN_LIST + "?start=" + start + "&num=" + num, null);
    WxError wxError = WxError.fromJson(response, WxType.MiniApp);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaEmbeddedListResult.class);
  }

  /**
   * 设置授权方式
   *
   * @param flag 半屏小程序授权方式。0表示需要管理员验证；1表示自动通过；2表示自动拒绝。
   * @author Yuan
   * @date 2024-12-04 17:33:33
   */
  @Override
  public void setAuthorizedEmbedded(Integer flag) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("flag", flag);
    String response = wxMaService.post(API_SET_AUTHORIZED_EMBEDDED, params);
    WxError wxError = WxError.fromJson(response, WxType.MiniApp);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
  }
}
