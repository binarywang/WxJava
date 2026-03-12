package me.chanjar.weixin.cp.tp.service;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpInviteResult;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.bean.WxCpUserExternalContactInfo;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 用户管理接口
 *  Created by jamie on 2020/7/22.
 * </pre>
 */
public interface WxCpTpUserService {

  /**
   * <pre>
   *   用在二次验证的时候.
   *   企业在员工验证成功后，调用本方法告诉企业号平台该员工关注成功。
   * </pre>
   *
   * @param userId 用户id
   * @throws WxErrorException the wx error exception
   */
  void authenticate(String userId) throws WxErrorException;

  /**
   * <pre>
   * 获取部门成员(详情).
   *
   * http://qydev.weixin.qq.com/wiki/index.php?title=管理成员#.E8.8E.B7.E5.8F.96.E9.83.A8.E9.97.A8.E6.88.90.E5.91.98.28.E8.AF.A6.E6.83.85.29
   * </pre>
   *
   * @param departId   必填。部门id
   * @param fetchChild 非必填。1/0：是否递归获取子部门下面的成员
   * @param status     非必填。0获取全部员工，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。status可叠加
   * @param corpId     the corp id
   * @return the list
   * @throws WxErrorException the wx error exception
   */
  List<WxCpUser> listByDepartment(Long departId, Boolean fetchChild, Integer status, String corpId) throws WxErrorException;

  /**
   * <pre>
   * 获取部门成员.
   *
   * http://qydev.weixin.qq.com/wiki/index.php?title=管理成员#.E8.8E.B7.E5.8F.96.E9.83.A8.E9.97.A8.E6.88.90.E5.91.98
   * </pre>
   *
   * @param departId   必填。部门id
   * @param fetchChild 非必填。1/0：是否递归获取子部门下面的成员
   * @param status     非必填。0获取全部员工，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。status可叠加
   * @param corpId     企业id
   * @return the list
   * @throws WxErrorException the wx error exception
   */
  List<WxCpUser> listSimpleByDepartment(Long departId, Boolean fetchChild, Integer status, String corpId)
    throws WxErrorException;

  /**
   * <pre>
   * 获取部门成员.
   *
   * http://qydev.weixin.qq.com/wiki/index.php?title=管理成员#.E8.8E.B7.E5.8F.96.E9.83.A8.E9.97.A8.E6.88.90.E5.91.98
   * </pre>
   *
   * @param departId   必填。部门id
   * @param fetchChild 非必填。1/0：是否递归获取子部门下面的成员
   * @param status     非必填。0获取全部员工，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。status可叠加
   * @return the list
   * @throws WxErrorException the wx error exception
   * @deprecated 第三方应用调用此接口需要使用 corpId 对应的 access_token，请使用
   *     {@link #listSimpleByDepartment(Long, Boolean, Integer, String)}
   */
  @Deprecated
  List<WxCpUser> listSimpleByDepartment(Long departId, Boolean fetchChild, Integer status) throws WxErrorException;

  /**
   * 新建用户.
   *
   * @param user 用户对象
   * @throws WxErrorException the wx error exception
   */
  void create(WxCpUser user) throws WxErrorException;

  /**
   * 更新用户.
   *
   * @param user 用户对象
   * @throws WxErrorException the wx error exception
   */
  void update(WxCpUser user) throws WxErrorException;

  /**
   * <pre>
   * 删除用户/批量删除成员.
   * http://qydev.weixin.qq.com/wiki/index.php?title=管理成员#.E6.89.B9.E9.87.8F.E5.88.A0.E9.99.A4.E6.88.90.E5.91.98
   * </pre>
   *
   * @param userIds 员工UserID列表。对应管理端的帐号
   * @throws WxErrorException the wx error exception
   */
  void delete(String... userIds) throws WxErrorException;

  /**
   * 获取用户.
   *
   * @param userid 用户id
   * @param corpId the corp id
   * @return the by id
   * @throws WxErrorException the wx error exception
   */
  WxCpUser getById(String userid, String corpId) throws WxErrorException;

  /**
   * <pre>
   * 邀请成员.
   * 企业可通过接口批量邀请成员使用企业微信，邀请后将通过短信或邮件下发通知。
   * 请求方式：POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/batch/invite?access_token=ACCESS_TOKEN
   * 文档地址：https://work.weixin.qq.com/api/doc#12543
   * </pre>
   *
   * @param userIds  成员ID列表, 最多支持1000个。
   * @param partyIds 部门ID列表，最多支持100个。
   * @param tagIds   标签ID列表，最多支持100个。
   * @return the wx cp invite result
   * @throws WxErrorException the wx error exception
   */
  WxCpInviteResult invite(List<String> userIds, List<String> partyIds, List<String> tagIds) throws WxErrorException;

  /**
   * <pre>
   *  userid转openid.
   *  该接口使用场景为微信支付、微信红包和企业转账。
   *
   * 在使用微信支付的功能时，需要自行将企业微信的userid转成openid。
   * 在使用微信红包功能时，需要将应用id和userid转成appid和openid才能使用。
   * 注：需要成员使用微信登录企业微信或者关注微信插件才能转成openid
   *
   * 文档地址：https://work.weixin.qq.com/api/doc#11279
   * </pre>
   *
   * @param userId  企业内的成员id
   * @param agentId 非必填，整型，仅用于发红包。其它场景该参数不要填，如微信支付、企业转账、电子发票
   * @return map对象 ，可能包含以下值： - openid 企业微信成员userid对应的openid，若有传参agentid，则是针对该agentid的openid。否则是针对企业微信corpid的openid -
   * appid 应用的appid，若请求包中不包含agentid则不返回appid。该appid在使用微信红包时会用到
   * @throws WxErrorException the wx error exception
   */
  Map<String, String> userId2Openid(String userId, Integer agentId) throws WxErrorException;

  /**
   * <pre>
   * openid转userid.
   *
   * 该接口主要应用于使用微信支付、微信红包和企业转账之后的结果查询。
   * 开发者需要知道某个结果事件的openid对应企业微信内成员的信息时，可以通过调用该接口进行转换查询。
   * 权限说明：
   * 管理组需对openid对应的企业微信成员有查看权限。
   *
   * 文档地址：https://work.weixin.qq.com/api/doc#11279
   * </pre>
   *
   * @param openid 在使用微信支付、微信红包和企业转账之后，返回结果的openid
   * @return userid 该openid在企业微信对应的成员userid
   * @throws WxErrorException the wx error exception
   */
  String openid2UserId(String openid) throws WxErrorException;

  /**
   * <pre>
   *
   * 通过手机号获取其所对应的userid。
   *
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/user/getuserid?access_token=ACCESS_TOKEN
   *
   * 文档地址：https://work.weixin.qq.com/api/doc#90001/90143/91693
   * </pre>
   *
   * @param mobile 手机号码。长度为5~32个字节
   * @param corpId – the corp id
   * @return userid mobile对应的成员userid
   * @throws WxErrorException .
   */
  String getUserId(String mobile, String corpId) throws WxErrorException;

  /**
   * 获取外部联系人详情.
   * <pre>
   *   企业可通过此接口，根据外部联系人的userid，拉取外部联系人详情。权限说明：
   * 企业需要使用外部联系人管理secret所获取的accesstoken来调用
   * 第三方应用需拥有“企业客户”权限。
   * 第三方应用调用时，返回的跟进人follow_user仅包含应用可见范围之内的成员。
   * </pre>
   *
   * @param userId 外部联系人的userid
   * @return 联系人详情 external contact
   * @throws WxErrorException .
   */
  WxCpUserExternalContactInfo getExternalContact(String userId) throws WxErrorException;


}
