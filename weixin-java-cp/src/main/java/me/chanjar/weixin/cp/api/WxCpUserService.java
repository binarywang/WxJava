package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpInviteResult;
import me.chanjar.weixin.cp.bean.WxCpOpenUseridToUseridResult;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.bean.WxCpUseridToOpenUseridResult;
import me.chanjar.weixin.cp.bean.external.contact.WxCpExternalContactInfo;
import me.chanjar.weixin.cp.bean.user.WxCpDeptUserResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 用户管理接口
 *  Created by BinaryWang on 2017/6/24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxCpUserService {

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
   * 获取部门成员详情
   * 请求方式：GET（HTTPS）
   * {@code 请求地址：https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD}
   *
   * 文档地址：https://work.weixin.qq.com/api/doc/90000/90135/90201
   * </pre>
   *
   * @param departId   必填。部门id
   * @param fetchChild 非必填。1/0：是否递归获取子部门下面的成员
   * @param status     非必填。0获取全部员工，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。status可叠加
   * @return the list
   * @throws WxErrorException the wx error exception
   */
  List<WxCpUser> listByDepartment(Long departId, Boolean fetchChild, Integer status) throws WxErrorException;

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
   */
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
   * @return the by id
   * @throws WxErrorException the wx error exception
   */
  WxCpUser getById(String userid) throws WxErrorException;

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
   * @return userid mobile对应的成员userid
   * @throws WxErrorException .
   */
  String getUserId(String mobile) throws WxErrorException;

  /**
   * <pre>
   *
   * 通过邮箱获取其所对应的userid。
   *
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/user/get_userid_by_email?access_token=ACCESS_TOKEN
   *
   * 文档地址：https://developer.work.weixin.qq.com/document/path/95895
   * </pre>
   *
   * @param email 邮箱
   * @param emailType 邮箱类型：1-企业邮箱；2-个人邮箱
   * @return userid email对应的成员userid
   * @throws WxErrorException .
   */
  String getUserIdByEmail(String email,int emailType) throws WxErrorException;

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
  WxCpExternalContactInfo getExternalContact(String userId) throws WxErrorException;

  /**
   * <pre>
   *
   * 获取加入企业二维码。
   *
   * 请求方式：GET（HTTPS）
   * {@code 请求地址：https://qyapi.weixin.qq.com/cgi-bin/corp/get_join_qrcode?access_token=ACCESS_TOKEN&size_type=SIZE_TYPE}
   *
   * 文档地址：https://work.weixin.qq.com/api/doc/90000/90135/91714
   * </pre>
   *
   * @param sizeType qrcode尺寸类型，1: 171 x 171; 2: 399 x 399; 3: 741 x 741; 4: 2052 x 2052
   * @return join_qrcode 二维码链接，有效期7天
   * @throws WxErrorException .
   */
  String getJoinQrCode(int sizeType) throws WxErrorException;

  /**
   * <pre>
   *
   * 获取企业活跃成员数。
   *
   * 请求方式：POST（HTTPS）
   * 请求地址：<a href="https://qyapi.weixin.qq.com/cgi-bin/user/get_active_stat?access_token=ACCESS_TOKEN">https://qyapi.weixin.qq.com/cgi-bin/user/get_active_stat?access_token=ACCESS_TOKEN</a>
   *
   * 文档地址：<a href="https://developer.work.weixin.qq.com/document/path/92714">https://developer.work.weixin.qq.com/document/path/92714</a>
   * </pre>
   *
   * @param date 具体某天的活跃人数，最长支持获取30天前数据
   * @return join_qrcode 活跃成员数
   * @throws WxErrorException .
   */
  Integer getActiveStat(Date date) throws WxErrorException;

  /**
   * userid转换为open_userid
   * 将自建应用或代开发应用获取的userid转换为第三方应用的userid
   * https://developer.work.weixin.qq.com/document/path/95603
   *
   * @param useridList the userid list
   * @return the WxCpUseridToOpenUseridResult
   * @throws WxErrorException the wx error exception
   */
  WxCpUseridToOpenUseridResult useridToOpenUserid(ArrayList<String> useridList) throws WxErrorException;

  /**
   * open_userid转换为userid
   * 将代开发应用或第三方应用获取的密文open_userid转换为明文userid
   * <pre>
   * 文档地址：<a href="https://developer.work.weixin.qq.com/document/path/95884#userid%E8%BD%AC%E6%8D%A2">https://developer.work.weixin.qq.com/document/path/95884#userid%E8%BD%AC%E6%8D%A2</a>
   *
   * 权限说明：
   *
   * 需要使用自建应用或基础应用的access_token
   * 成员需要同时在access_token和source_agentid所对应应用的可见范围内
   * </pre>
   * @param openUseridList open_userid列表，最多不超过1000个。必须是source_agentid对应的应用所获取
   * @param sourceAgentId 企业授权的代开发自建应用或第三方应用的agentid
   * @return the WxCpOpenUseridToUseridResult
   * @throws WxErrorException the wx error exception
   */
  WxCpOpenUseridToUseridResult openUseridToUserid(List<String> openUseridList, String sourceAgentId) throws WxErrorException;

  /**
   * 获取成员ID列表
   * 获取企业成员的userid与对应的部门ID列表，预计于2022年8月8号发布。若需要获取其他字段，参见「适配建议」。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/user/list_id?access_token=ACCESS_TOKEN
   *
   * @param cursor the cursor
   * @param limit  the limit
   * @return user list id
   * @throws WxErrorException the wx error exception
   */
  WxCpDeptUserResult getUserListId(String cursor, Integer limit) throws WxErrorException;

}
