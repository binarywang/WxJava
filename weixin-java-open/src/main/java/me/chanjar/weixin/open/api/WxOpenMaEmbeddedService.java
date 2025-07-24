package me.chanjar.weixin.open.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.bean.result.WxOpenMaEmbeddedListResult;

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
public interface WxOpenMaEmbeddedService {

  /**
   * 添加半屏小程序
   * <pre>
   *     本接口用于添加半屏小程序
   * </pre>
   */
  String API_ADD_EMBEDDED = "https://api.weixin.qq.com/wxaapi/wxaembedded/add_embedded";

  /**
   * 删除半屏小程序
   * <pre>
   *     用本接口可以删除已经获得授权调用的半屏小程序
   *     说明：通过add_embedded接口添加半屏小程序后，可通过当前接口删除已经添加到半屏小程序列表的小程序
   * </pre>
   */
  String API_DELETE_EMBEDDED = "https://api.weixin.qq.com/wxaapi/wxaembedded/del_embedded";

  /**
   * 获取半屏小程序调用列表
   * <pre>
   *     调用本接口可以获取半屏小程序调用列表
   *     说明：通过addEmbedded接口添加半屏小程序后，可通过当前接口获取半屏小程序调用列表
   * </pre>
   */
  String API_GET_EMBEDDED_LIST = "https://api.weixin.qq.com/wxaapi/wxaembedded/get_list";

  /**
   * 取消授权小程序
   * <pre>
   *     调用本接口可以取消已经授权的小程序
   *     说明：可通过get_own_list接口获取当前半屏小程序已经授权的小程序列表，可通过当前接口取消对某个小程序的调用权限
   * </pre>
   */
  String API_DELETE_AUTHORIZED_EMBEDDED = "https://api.weixin.qq.com/wxaapi/wxaembedded/del_authorize";

  /**
   * 获取半屏小程序授权列表
   * <pre>
   *     调用本接口可以获取半屏小程序授权列表
   *     说明：一个半屏小程序可授权给1000个小程序调用，通过该接口可获取已经授权的小程序列表
   * </pre>
   */
  String API_GET_OWN_LIST = "https://api.weixin.qq.com/wxaapi/wxaembedded/get_own_list";

  /**
   * 设置授权方式
   */
  String API_SET_AUTHORIZED_EMBEDDED = "https://api.weixin.qq.com/wxaapi/wxaembedded/set_authorize";

  /**
   * 添加半屏小程序
   *
   * @param embeddedAppId 半屏小程序appId
   * @param applyReason   申请理由
   * @author Yuan
   * @date 2024-12-04 17:33:33
   */
  void addEmbedded(String embeddedAppId, String applyReason) throws WxErrorException;

  /**
   * 删除半屏小程序
   *
   * @param embeddedAppId 半屏小程序appId
   * @author Yuan
   * @date 2024-12-04 17:33:33
   */
  void deleteEmbedded(String embeddedAppId) throws WxErrorException;

  /**
   * 获取半屏小程序调用列表
   *
   * @return {@link WxOpenMaEmbeddedListResult }
   * @author Yuan
   * @date 2024-12-04 17:33:33
   */
  WxOpenMaEmbeddedListResult getEmbeddedList() throws WxErrorException;

  /**
   * 取消授权小程序
   *
   * @param embeddedAppId 半屏小程序appId
   * @author Yuan
   * @date 2024-12-04 17:33:33
   */
  void deleteAuthorizedEmbedded(String embeddedAppId) throws WxErrorException;

  /**
   * 获取半屏小程序授权列表,默认分页起始值为0，一次拉取最大值为1000
   *
   * @return {@link WxOpenMaEmbeddedListResult }
   * @author Yuan
   * @date 2024-12-04 17:33:33
   */
  WxOpenMaEmbeddedListResult getOwnList() throws WxErrorException;

  /**
   * 获取半屏小程序授权列表
   *
   * @param start 分页起始值 ，默认值为0
   * @param num   一次拉取最大值，最大 1000，默认值为10
   * @return {@link WxOpenMaEmbeddedListResult }
   * @author Yuan
   * @date 2024-12-04 17:33:33
   */
  WxOpenMaEmbeddedListResult getOwnList(Integer start, Integer num) throws WxErrorException;

  /**
   * 设置授权方式
   *
   * @param flag 半屏小程序授权方式。0表示需要管理员验证；1表示自动通过；2表示自动拒绝。
   * @author Yuan
   * @date 2024-12-04 17:33:33
   */
  void setAuthorizedEmbedded(Integer flag) throws WxErrorException;

}
