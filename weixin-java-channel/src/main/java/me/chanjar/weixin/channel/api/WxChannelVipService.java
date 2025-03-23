package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.vip.VipInfoResponse;
import me.chanjar.weixin.channel.bean.vip.VipListResponse;
import me.chanjar.weixin.channel.bean.vip.VipScoreResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 会员功能接口
 *
 * @author <a href="https://github.com/asushiye">aushiye</a>
 * @link <a href="https://developers.weixin.qq.com/doc/channels/API/vip/access_guide.html">会员功能接口文档</a>
 */
public interface WxChannelVipService {
  /** 拉取用户详情 */
  // String VIP_USER_INFO_URL = "https://api.weixin.qq.com/channels/ec/vip/user/info/get";
  // /** 拉取用户列表 */
  // String VIP_USER_LIST_URL = "https://api.weixin.qq.com/channels/ec/vip/user/list/get";
  //
  // /** 获取用户积分 */
  // String VIP_SCORE_URL = "https://api.weixin.qq.com/channels/ec/vip/user/score/get";
  // /** 增加用户积分 */
  // String SCORE_INCREASE_URL = "https://api.weixin.qq.com/channels/ec/vip/user/score/increase";
  // /** 减少用户积分 */
  // String SCORE_DECREASE_URL = "https://api.weixin.qq.com/channels/ec/vip/user/score/decrease";
  //
  // /** 更新用户等级 */
  // String GRADE_UPDATE_URL = "https://api.weixin.qq.com/channels/ec/vip/user/grade/update";


  /**
   * 获取用户详情
   *
   * @param openId          the open id
   * @param needPhoneNumber the need phone number
   * @return the vip info
   * @throws WxErrorException the wx error exception
   */
  VipInfoResponse getVipInfo(String openId, Boolean needPhoneNumber) throws WxErrorException;


  /**
   * 获取用户积分
   *
   * @param needPhoneNumber the need phone number
   * @param pageNum         the page num
   * @param pageSize        the page size
   * @return the vip list
   * @throws WxErrorException the wx error exception
   */
  VipListResponse getVipList(Boolean needPhoneNumber, Integer pageNum, Integer pageSize) throws WxErrorException;

  /**
   * 获取用户积分
   *
   * @param openId the open id
   * @return the vip score
   * @throws WxErrorException the wx error exception
   */
  VipScoreResponse getVipScore(String openId) throws WxErrorException;

  /**
   * 增加用户积分
   *
   * @param openId    the open id
   * @param score     the score
   * @param remark    the remark
   * @param requestId the request id
   * @return the wx channel base response
   * @throws WxErrorException the wx error exception
   */
  WxChannelBaseResponse increaseVipScore(String openId, String score, String remark, String requestId) throws WxErrorException;

  /**
   * 减少用户积分
   *
   * @param openId    the open id
   * @param score     the score
   * @param remark    the remark
   * @param requestId the request id
   * @return the wx channel base response
   * @throws WxErrorException the wx error exception
   */
  WxChannelBaseResponse decreaseVipScore(String openId, String score, String remark, String requestId) throws WxErrorException;

  /**
   * 更新用户等级
   *
   * @param openId the open id
   * @param score  the score
   * @return the wx channel base response
   * @throws WxErrorException the wx error exception
   */
  WxChannelBaseResponse updateVipGrade(String openId, Integer score) throws WxErrorException;

}
