package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.cooperation.CooperationListResponse;
import me.chanjar.weixin.channel.bean.cooperation.CooperationQrCodeResponse;
import me.chanjar.weixin.channel.bean.cooperation.CooperationStatusResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店 合作账号相关接口
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @see <a href="https://developers.weixin.qq.com/doc/store/API/cooperation/">合作账号状态机</a>
 */
public interface WxStoreCooperationService {

  /**
   * 获取合作账号列表
   *
   * @param sharerType 合作账号类型 2公众号 3小程序
   * @return 合作账号列表
   *
   * @throws WxErrorException 异常
   */
  CooperationListResponse listCooperation(Integer sharerType) throws WxErrorException;

  /**
   * 获取合作账号状态
   *
   * @param sharerId   合作账号id 公众号: gh_开头id 小程序: appid
   * @param sharerType 合作账号类型 2公众号 3小程序
   * @return 合作账号状态
   *
   * @throws WxErrorException 异常
   */
  CooperationStatusResponse getCooperationStatus(String sharerId, Integer sharerType) throws WxErrorException;

  /**
   * 生成合作账号邀请二维码
   *
   * @param sharerId   合作账号id 公众号: gh_开头id 小程序: appid
   * @param sharerType 合作账号类型 2公众号 3小程序
   * @return 二维码
   *
   * @throws WxErrorException 异常
   */
  CooperationQrCodeResponse generateQrCode(String sharerId, Integer sharerType) throws WxErrorException;

  /**
   * 取消合作账号邀请
   *
   * @param sharerId   合作账号id 公众号: gh_开头id 小程序: appid
   * @param sharerType 合作账号类型 2公众号 3小程序
   * @return WxChannelBaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse cancelInvitation(String sharerId, Integer sharerType) throws WxErrorException;

  /**
   * 解绑合作账号
   *
   * @param sharerId   合作账号id 公众号: gh_开头id 小程序: appid
   * @param sharerType 合作账号类型 2公众号 3小程序
   * @return WxChannelBaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse unbind(String sharerId, Integer sharerType) throws WxErrorException;
}
