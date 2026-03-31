package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.device.*;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;

/**
 * 小程序设备订阅消息相关 API
 * 文档：
 *
 * @author <a href="https://github.com/leejuncheng">JCLee</a>
 * @since 2021-12-16 17:13:35
 */
public interface WxMaDeviceSubscribeService {

  /**
   * <pre>
   * 获取设备票据
   * 应用场景：
   * 小程序前端界面拉起设备消息授权订阅弹框界面
   * 注意：
   * 设备ticket有效时间为5分钟
   * </pre>
   *
   * @param deviceTicketRequest
   * @return
   * @throws WxErrorException
   */
  String getSnTicket(WxMaDeviceTicketRequest deviceTicketRequest) throws WxErrorException;

  /**
   * <pre>
   * 发送设备订阅消息
   * </pre>
   *
   * @param deviceSubscribeMessageRequest 订阅消息
   * @throws WxErrorException .
   */
  void sendDeviceSubscribeMsg(WxMaDeviceSubscribeMessageRequest deviceSubscribeMessageRequest) throws WxErrorException;

  /**
   * <pre>
   * 创建设备组
   * 详情请见：https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/hardware-device/createIotGroupId.html
   * </pre>
   *
   * @param createIotGroupIdRequest 请求参数
   * @return 设备组的唯一标识
   * @throws WxErrorException
   */
  String createIotGroupId(WxMaCreateIotGroupIdRequest createIotGroupIdRequest) throws WxErrorException;

  /**
   * <pre>
   * 查询设备组信息
   * 详情请见：https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/hardware-device/getIotGroupInfo.html
   * </pre>
   *
   * @param getIotGroupInfoRequest 请求参数
   * @return 设备组信息
   * @throws WxErrorException
   */
  WxMaIotGroupDeviceInfoResponse getIotGroupInfo(WxMaGetIotGroupInfoRequest getIotGroupInfoRequest) throws WxErrorException;

  /**
   * <pre>
   * 设备组添加设备
   * 一个设备组最多添加 50 个设备。 一个设备同一时间只能被添加到一个设备组中。
   * 详情请见：https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/hardware-device/addIotGroupDevice.html
   * </pre>
   *
   * @param request 请求参数
   * @return 成功添加的设备信息
   * @throws WxErrorException
   */
  List<WxMaDeviceTicketRequest> addIotGroupDevice(WxMaIotGroupDeviceRequest request) throws WxErrorException;

  /**
   * <pre>
   * 设备组删除设备
   * 详情请见：https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/hardware-device/removeIotGroupDevice.html
   * </pre>
   *
   * @param request 请求参数
   * @return 成功删除的设备信息
   * @throws WxErrorException
   */
  List<WxMaDeviceTicketRequest> removeIotGroupDevice(WxMaIotGroupDeviceRequest request) throws WxErrorException;

}
