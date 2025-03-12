package me.chanjar.weixin.cp.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.external.*;
import me.chanjar.weixin.cp.bean.external.acquisition.*;
import me.chanjar.weixin.cp.bean.external.contact.*;
import me.chanjar.weixin.cp.bean.external.interceptrule.WxCpInterceptRule;
import me.chanjar.weixin.cp.bean.external.interceptrule.WxCpInterceptRuleAddRequest;
import me.chanjar.weixin.cp.bean.external.interceptrule.WxCpInterceptRuleInfo;
import me.chanjar.weixin.cp.bean.external.interceptrule.WxCpInterceptRuleList;

/**
 * <pre>
 * 外部联系人管理接口，企业微信的外部联系人的接口和通讯录接口已经拆离
 *  Created by Joe Cao on 2019/6/14
 * </pre>
 *
 * @author <a href="https://github.com/JoeCao">JoeCao</a>
 */
public interface WxCpExternalContactService {

  /**
   * 配置客户联系「联系我」方式
   * <pre>
   * 企业可以在管理后台-客户联系中配置成员的「联系我」的二维码或者小程序按钮，客户通过扫描二维码或点击小程序上的按钮，即可获取成员联系方式，主动联系到成员。
   * 企业可通过此接口为具有客户联系功能的成员生成专属的「联系我」二维码或者「联系我」按钮。
   * 如果配置的是「联系我」按钮，需要开发者的小程序接入小程序插件。
   *
   * 注意:
   * 通过API添加的「联系我」不会在管理端进行展示，每个企业可通过API最多配置50万个「联系我」。
   * 用户需要妥善存储返回的config_id，config_id丢失可能导致用户无法编辑或删除「联系我」。
   * 临时会话模式不占用「联系我」数量，但每日最多添加10万个，并且仅支持单人。
   * 临时会话模式的二维码，添加好友完成后该二维码即刻失效。
   * <a href="https://developer.work.weixin.qq.com/document/path/92228">文档地址</a>
   * </pre>
   *
   * @param info 客户联系「联系我」方式
   * @return wx cp contact way result
   * @throws WxErrorException the wx error exception
   */
  WxCpContactWayResult addContactWay(WxCpContactWayInfo info) throws WxErrorException;

  /**
   * 获取企业已配置的「联系我」方式
   *
   * <pre>
   * <b>批量</b>获取企业配置的「联系我」二维码和「联系我」小程序按钮。
   * </pre>
   *
   * @param configId 联系方式的配置id,必填
   * @return contact way
   * @throws WxErrorException the wx error exception
   */
  WxCpContactWayInfo getContactWay(String configId) throws WxErrorException;

  /**
   * 获取企业已配置的「联系我」列表
   *
   * <pre>
   * 获取企业配置的「联系我」二维码和「联系我」小程序插件列表。不包含临时会话。
   * 注意，<b>该接口仅可获取2021年7月10日以后创建的「联系我」</b>
   * </pre>
   *
   * 文档地址: <a href="https://developer.work.weixin.qq.com/document/path/92228#%E8%8E%B7%E5%8F%96%E4%BC%81%E4%B8%9A%E5%B7%B2%E9%85%8D%E7%BD%AE%E7%9A%84%E3%80%8C%E8%81%94%E7%B3%BB%E6%88%91%E3%80%8D%E5%88%97%E8%A1%A8">获取企业已配置的「联系我」列表</a>
   *
   * @param startTime 「联系我」创建起始时间戳, 默认为90天前
   * @param endTime 「联系我」创建结束时间戳, 默认为当前时间
   * @param cursor 分页查询使用的游标，为上次请求返回的 next_cursor
   * @param limit 每次查询的分页大小，默认为100条，最多支持1000条
   * @return contact way configId
   * @throws WxErrorException the wx error exception
   */
  WxCpContactWayList listContactWay(Long startTime, Long endTime, String cursor, Long limit) throws WxErrorException;

  /**
   * 更新企业已配置的「联系我」方式
   *
   * <pre>
   * 更新企业配置的「联系我」二维码和「联系我」小程序按钮中的信息，如使用人员和备注等。
   * </pre>
   *
   * @param info 客户联系「联系我」方式
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp updateContactWay(WxCpContactWayInfo info) throws WxErrorException;

  /**
   * 删除企业已配置的「联系我」方式
   *
   * <pre>
   * 删除一个已配置的「联系我」二维码或者「联系我」小程序按钮。
   * </pre>
   *
   * @param configId 企业联系方式的配置id,必填
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp deleteContactWay(String configId) throws WxErrorException;

  /**
   * 结束临时会话
   *
   * <pre>
   * 将指定的企业成员和客户之前的临时会话断开，断开前会自动下发已配置的结束语。
   *
   * 注意：请保证传入的企业成员和客户之间有仍然有效的临时会话, 通过<b>其他方式的添加外部联系人无法通过此接口关闭会话</b>。
   * </pre>
   *
   * @param userId         the user id
   * @param externalUserId the external user id
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp closeTempChat(String userId, String externalUserId) throws WxErrorException;


  /**
   * 获取外部联系人详情.
   * <pre>
   *   企业可通过此接口，根据外部联系人的userid，拉取外部联系人详情。权限说明：
   * 企业需要使用外部联系人管理secret所获取的accesstoken来调用
   * 第三方应用需拥有“企业客户”权限。
   * 第三方应用调用时，返回的跟进人follow_user仅包含应用可见范围之内的成员。
   * </pre>
   *
   * @param externalUserId 外部联系人的userid
   * @return . external contact
   * @throws WxErrorException the wx error exception
   * @deprecated 建议使用 {@link #getContactDetail(String, String)}
   */
  @Deprecated
  WxCpExternalContactInfo getExternalContact(String externalUserId) throws WxErrorException;

  /**
   * 获取客户详情.
   * <pre>
   *
   * 企业可通过此接口，根据外部联系人的userid（如何获取?），拉取客户详情。
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get?access_token=ACCESS_TOKEN&external_userid=EXTERNAL_USERID
   *
   * 权限说明：
   *
   * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）；
   * 第三方/自建应用调用时，返回的跟进人follow_user仅包含应用可见范围之内的成员。
   * </pre>
   *
   * @param externalUserId 外部联系人的userid，注意不是企业成员的帐号
   * @param cursor 用于分页查询的游标，字符串类型，由上一次调用返回，首次调用可不填
   * @return . contact detail
   * @throws WxErrorException .
   */
  WxCpExternalContactInfo getContactDetail(String externalUserId, String cursor) throws WxErrorException;

  /**
   * 企业和服务商可通过此接口，将微信外部联系人的userid转为微信openid，用于调用支付相关接口。暂不支持企业微信外部联系人（ExternalUserid为wo开头）的userid转openid。
   *
   * @param externalUserid 微信外部联系人的userid
   * @return 该企业的外部联系人openid string
   * @throws WxErrorException .
   */
  String convertToOpenid(String externalUserid) throws WxErrorException;

  /**
   * 服务商为企业代开发微信小程序的场景，服务商可通过此接口，将微信客户的unionid转为external_userid。
   * <pre>
   *
   * 文档地址：https://work.weixin.qq.com/api/doc/90001/90143/93274
   *
   * 服务商代开发小程序指企业使用的小程序为企业主体的，非服务商主体的小程序。
   * 场景：企业客户在微信端从企业主体的小程序（非服务商应用）登录，同时企业在企业微信安装了服务商的第三方应用，服务商可以调用该接口将登录用户的unionid转换为服务商全局唯一的外部联系人id
   *
   * 权限说明：
   *
   * 仅认证企业可调用
   * unionid必须是企业主体下的unionid。即unionid的主体（为绑定了该小程序的微信开放平台账号主体）需与当前企业的主体一致。
   * unionid的主体（即微信开放平台账号主体）需认证
   * 该客户的跟进人必须在应用的可见范围之内
   * </pre>
   *
   * @param unionid 微信客户的unionid
   * @param openid  the openid
   * @return 该企业的外部联系人ID string
   * @throws WxErrorException .
   */
  String unionidToExternalUserid(String unionid, String openid) throws WxErrorException;

  /**
   * 配置客户群进群方式
   * 企业可以在管理后台-客户联系中配置「加入群聊」的二维码或者小程序按钮，客户通过扫描二维码或点击小程序上的按钮，即可加入特定的客户群。
   * 企业可通过此接口为具有客户联系功能的成员生成专属的二维码或者小程序按钮。
   * 如果配置的是小程序按钮，需要开发者的小程序接入小程序插件。
   * 注意:
   * 通过API添加的配置不会在管理端进行展示，每个企业可通过API最多配置50万个「加入群聊」(与「联系我」共用50万的额度)。
   * 文档地址：https://developer.work.weixin.qq.com/document/path/92229
   *
   * @param wxCpGroupJoinWayInfo the wx cp group join way info
   * @return {@link WxCpGroupJoinWayResult}
   * @throws WxErrorException the wx error exception
   */
  WxCpGroupJoinWayResult addJoinWay(WxCpGroupJoinWayInfo wxCpGroupJoinWayInfo) throws WxErrorException;

  /**
   * 更新客户群进群方式配置
   * 更新进群方式配置信息。注意：使用覆盖的方式更新。
   * 文档地址：https://developer.work.weixin.qq.com/document/path/92229
   *
   * @param wxCpGroupJoinWayInfo the wx cp group join way info
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp updateJoinWay(WxCpGroupJoinWayInfo wxCpGroupJoinWayInfo) throws WxErrorException;

  /**
   * 获取客户群进群方式配置
   * 获取企业配置的群二维码或小程序按钮。
   * 文档地址：https://developer.work.weixin.qq.com/document/path/92229
   *
   * @param configId the config id
   * @return join way
   * @throws WxErrorException the wx error exception
   */
  WxCpGroupJoinWayInfo getJoinWay(String configId) throws WxErrorException;

  /**
   * 删除客户群进群方式配置
   * 文档地址：https://developer.work.weixin.qq.com/document/path/92229
   *
   * @param configId the config id
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp delJoinWay(String configId) throws WxErrorException;

  /**
   * 代开发应用external_userid转换
   * <pre>
   *
   * 文档地址：https://work.weixin.qq.com/api/doc/90001/90143/95195
   *
   * 企业同时安装服务商第三方应用以及授权代开发自建应用的时，服务商可使用该接口将代开发应用获取到的外部联系人id跟第三方应用的id进行关联，
   * 该接口可将代开发自建应用获取到的external_userid转换为服务商第三方应用的external_userid。
   *
   * 权限说明：
   *
   * 该企业授权了该服务商第三方应用,且授权的第三方应用具备“企业客户权限->客户基础信息”权限
   * 该客户的跟进人必须在应用的可见范围之内
   * 应用需具备“企业客户权限->客户基础信息”权限
   * </pre>
   *
   * @param externalUserid 代开发自建应用获取到的外部联系人ID
   * @return 该服务商第三方应用下的企业的外部联系人ID string
   * @throws WxErrorException .
   */
  String toServiceExternalUserid(String externalUserid) throws WxErrorException;

  /**
   * 将代开发应用或第三方应用获取的externaluserid转换成自建应用的externaluserid
   * <pre>
   * 文档地址：https://developer.work.weixin.qq.com/document/path/95884#external-userid%E8%BD%AC%E6%8D%A2
   *
   * 权限说明：
   *
   * 需要使用自建应用或基础应用的access_token
   * 客户的跟进人，或者用户所在客户群的群主，需要同时在access_token和source_agentid所对应应用的可见范围内
   * </pre>
   *
   * @param externalUserid 服务商主体的external_userid，必须是source_agentid对应的应用所获取
   * @param sourceAgentId  企业授权的代开发自建应用或第三方应用的agentid
   * @return
   * @throws WxErrorException
   */
  String fromServiceExternalUserid(String externalUserid, String sourceAgentId) throws WxErrorException;

  /**
   * 企业客户微信unionid的升级 - unionid查询external_userid
   * <pre>
   *
   * 文档地址：https://open.work.weixin.qq.com/api/doc/35863#4.2%20unionid%E6%9F%A5%E8%AF%A2external_userid
   *
   * 当微信用户在微信中使用第三方应用的小程序或公众号时，第三方可将获取到的unionid与openid，调用此接口转换为企业客户external_userid。
   * 该接口调用频次有限，每个服务商每小时仅可调用1万次，仅用于微信用户主动使用第三方应用的场景来调用，服务商切不可滥用。
   * 同时建议服务商的小程序路径或公众号页面链接带上corpid参数，如此可明确地转换出该企业对应的external_userid，以获得更好的性能。
   *
   * 权限说明：
   *
   * 该企业授权了该服务商第三方应用
   * 调用频率最大为10000次/小时
   * unionid和openid的主体需与服务商的主体一致
   * openid与unionid必须是在同一个小程序或同一个公众号获取到的
   * </pre>
   *
   * @param unionid 微信客户的unionid
   * @param openid  微信客户的openid
   * @param corpid  需要换取的企业corpid，不填则拉取所有企业
   * @return 该服务商第三方应用下的企业的外部联系人ID wx cp external user id list
   * @throws WxErrorException .
   */
  WxCpExternalUserIdList unionidToExternalUserid3rd(String unionid, String openid, String corpid) throws WxErrorException;

  /**
   * 转换external_userid
   * <pre>
   *
   * 文档地址：https://open.work.weixin.qq.com/api/doc/35863#转换external_userid
   *
   * 对于历史已授权的企业，在2022年3月1号之前，所有接口与回调返回的external_userid仍然为旧的external_userid，
   * 从2022年3月1号0点开始，所有输入与返回的external_userid字段，将启用升级后的external_userid。
   * 所以服务商需要在此之前完成历史数据的迁移整改
   *
   * 权限说明：
   *
   * 该企业授权了该服务商第三方应用
   * external_userid对应的跟进人需要在应用可见范围内
   * </pre>
   *
   * @param externalUserIdList 微信客户的unionid
   * @return List<String>  新外部联系人id
   * @throws WxErrorException .
   */
  WxCpNewExternalUserIdList getNewExternalUserId(String[] externalUserIdList) throws WxErrorException;

  /**
   * 设置迁移完成
   * <pre>
   *
   * 文档地址：https://open.work.weixin.qq.com/api/doc/35863#转换external_userid
   *
   * 企业授权确认之后，且服务商完成了新旧external_userid的迁移，即可主动将该企业设置为“迁移完成”，
   * 设置之后，从该企业获取到的将是新的external_userid。注意，该接口需要使用provider_access_token来调用，
   * 对于有多个应用的服务商，可逐个应用进行external_userid的转换，最后再使用provider_access_token调用该接口完成设置。
   *
   * 权限说明：
   *
   * 该企业授权了该服务商第三方应用
   * </pre>
   *
   * @param corpid 企业corpid
   * @return wx cp base resp
   * @throws WxErrorException .
   */
  WxCpBaseResp finishExternalUserIdMigration(String corpid) throws WxErrorException;

  /**
   * 客户群opengid转换
   * <pre>
   *
   * 文档地址：https://open.work.weixin.qq.com/api/doc/90000/90135/94822
   *
   * 用户在微信里的客户群里打开小程序时，某些场景下可以获取到群的opengid，如果该群是企业微信的客户群，
   * 则企业或第三方可以调用此接口将一个opengid转换为客户群chat_id
   *
   * 权限说明：
   *
   * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）
   * 第三方应用需具有“企业客户权限->客户基础信息”权限
   * 对于第三方/自建应用，群主必须在应用的可见范围
   * 仅支持企业服务人员创建的客户群
   * 仅可转换出自己企业下的客户群chat_id
   * </pre>
   *
   * @param opengid 小程序在微信获取到的群ID，参见wx.getGroupEnterInfo(https://developers.weixin.qq
   *                .com/miniprogram/dev/api/open-api/group/wx.getGroupEnterInfo.html)
   * @return 客户群ID ，可以用来调用获取客户群详情
   * @throws WxErrorException .
   */
  String opengidToChatid(String opengid) throws WxErrorException;

  /**
   * 批量获取客户详情.
   * <pre>
   *
   * 企业/第三方可通过此接口获取指定成员添加的客户信息列表。
   *
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/externalcontact/batch/get_by_user?access_token=ACCESS_TOKEN
   *
   * 权限说明：
   *
   * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）；
   * 第三方/自建应用调用时，返回的跟进人follow_user仅包含应用可见范围之内的成员。
   * </pre>
   *
   * @param userIdList 企业成员的userid列表，注意不是外部联系人的帐号
   * @param cursor     the cursor
   * @param limit      the  limit
   * @return wx cp user external contact batch info
   * @throws WxErrorException .
   */
  WxCpExternalContactBatchInfo getContactDetailBatch(String[] userIdList, String cursor,
                                                     Integer limit)
    throws WxErrorException;

  /**
   * 获取已服务的外部联系人
   * <pre>
   *  企业可通过此接口获取所有已服务的外部联系人，及其添加人和加入的群聊。
   * 外部联系人分为客户和其他外部联系人，如果是客户，接口将返回外部联系人临时ID和externaluserid；如果是其他外部联系人，接口将只返回外部联系人临时ID。
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/externalcontact/contact_list?access_token=ACCESS_TOKEN
   * 文档地址: https://developer.work.weixin.qq.com/document/path/99434
   * </pre>
   *
   * @param cursor     the cursor
   * @param limit      the  limit
   * @return 已服务的外部联系人列表
   * @throws WxErrorException .
   * @apiNote 企业可通过外部联系人临时ID排除重复数据，外部联系人临时ID有效期为4小时。
   */
  WxCpExternalContactListInfo getContactList(String cursor, Integer limit) throws WxErrorException;

  /**
   * 修改客户备注信息.
   * <pre>
   * 企业可通过此接口修改指定用户添加的客户的备注信息。
   * 请求方式: POST(HTTP)
   * 请求地址:https://qyapi.weixin.qq.com/cgi-bin/externalcontact/remark?access_token=ACCESS_TOKEN
   * 文档地址：https://work.weixin.qq.com/api/doc/90000/90135/92115
   * </pre>
   *
   * @param request 备注信息请求
   * @throws WxErrorException .
   */
  void updateRemark(WxCpUpdateRemarkRequest request) throws WxErrorException;

  /**
   * 获取客户列表.
   * <pre>
   *   企业可通过此接口获取指定成员添加的客户列表。客户是指配置了客户联系功能的成员所添加的外部联系人。没有配置客户联系功能的成员，所添加的外部联系人将不会作为客户返回。
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/externalcontact/list?access_token=ACCESS_TOKEN&userid=USERID
   *
   * 权限说明：
   *
   * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）；
   * 第三方应用需拥有“企业客户”权限。
   * 第三方/自建应用只能获取到可见范围内的配置了客户联系功能的成员。
   * </pre>
   *
   * @param userId 企业成员的userid
   * @return List of External wx id
   * @throws WxErrorException .
   */
  List<String> listExternalContacts(String userId) throws WxErrorException;

  /**
   * 企业和第三方服务商可通过此接口获取配置了客户联系功能的成员(Customer Contact)列表。
   * <pre>
   *   企业需要使用外部联系人管理secret所获取的accesstoken来调用（accesstoken如何获取？）；
   *   第三方应用需拥有“企业客户”权限。
   *   第三方应用只能获取到可见范围内的配置了客户联系功能的成员
   * </pre>
   *
   * @return List of CpUser id
   * @throws WxErrorException .
   */
  List<String> listFollowers() throws WxErrorException;

  /**
   * 获取待分配的离职成员列表
   * 企业和第三方可通过此接口，获取所有离职成员的客户列表，并可进一步调用分配离职成员的客户接口将这些客户重新分配给其他企业成员。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get_unassigned_list?access_token=ACCESS_TOKEN
   *
   * @param pageId   分页查询，要查询页号，从0开始
   * @param cursor   分页查询游标，字符串类型，适用于数据量较大的情况，如果使用该参数则无需填写page_id，该参数由上一次调用返回
   * @param pageSize 每次返回的最大记录数，默认为1000，最大值为1000
   * @return wx cp user external unassign list
   * @throws WxErrorException the wx error exception
   */
  WxCpUserExternalUnassignList listUnassignedList(Integer pageId, String cursor, Integer pageSize) throws WxErrorException;

  /**
   * 企业可通过此接口，将已离职成员的外部联系人分配给另一个成员接替联系。
   *
   * @param externalUserid the external userid
   * @param handOverUserid the hand over userid
   * @param takeOverUserid the take over userid
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   * @deprecated 此后续将不再更新维护, 建议使用 {@link #transferCustomer(WxCpUserTransferCustomerReq)}
   */
  @Deprecated
  WxCpBaseResp transferExternalContact(String externalUserid, String handOverUserid, String takeOverUserid) throws WxErrorException;

  /**
   * 企业可通过此接口，转接在职成员的客户给其他成员。
   * <per>
   * external_userid必须是handover_userid的客户（即配置了客户联系功能的成员所添加的联系人）。
   * 在职成员的每位客户最多被分配2次。客户被转接成功后，将有90个自然日的服务关系保护期，保护期内的客户无法再次被分配。
   * <p>
   * 权限说明：
   * * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）。
   * 第三方应用需拥有“企业客户权限->客户联系->在职继承”权限
   * 接替成员必须在此第三方应用或自建应用的可见范围内。
   * 接替成员需要配置了客户联系功能。
   * 接替成员需要在企业微信激活且已经过实名认证。
   * </per>
   *
   * @param req 转接在职成员的客户给其他成员请求实体
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpUserTransferCustomerResp transferCustomer(WxCpUserTransferCustomerReq req) throws WxErrorException;

  /**
   * 企业和第三方可通过此接口查询在职成员的客户转接情况。
   * <per>
   * 权限说明：
   * <p>
   * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）。
   * 第三方应用需拥有“企业客户权限->客户联系->在职继承”权限
   * 接替成员必须在此第三方应用或自建应用的可见范围内。
   * </per>
   *
   * @param handOverUserid 原添加成员的userid
   * @param takeOverUserid 接替成员的userid
   * @param cursor         分页查询的cursor，每个分页返回的数据不会超过1000条；不填或为空表示获取第一个分页；
   * @return 客户转接接口实体 wx cp user transfer result resp
   * @throws WxErrorException the wx error exception
   */
  WxCpUserTransferResultResp transferResult(String handOverUserid, String takeOverUserid,
                                            String cursor) throws WxErrorException;

  /**
   * 企业可通过此接口，分配离职成员的客户给其他成员。
   * <per>
   * handover_userid必须是已离职用户。
   * external_userid必须是handover_userid的客户（即配置了客户联系功能的成员所添加的联系人）。
   * 在职成员的每位客户最多被分配2次。客户被转接成功后，将有90个自然日的服务关系保护期，保护期内的客户无法再次被分配。
   * <p>
   * 权限说明：
   * <p>
   * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）。
   * 第三方应用需拥有“企业客户权限->客户联系->离职分配”权限
   * 接替成员必须在此第三方应用或自建应用的可见范围内。
   * 接替成员需要配置了客户联系功能。
   * 接替成员需要在企业微信激活且已经过实名认证。
   * </per>
   *
   * @param req 转接在职成员的客户给其他成员请求实体
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpUserTransferCustomerResp resignedTransferCustomer(WxCpUserTransferCustomerReq req) throws WxErrorException;

  /**
   * 企业和第三方可通过此接口查询离职成员的客户分配情况。
   * <per>
   * 权限说明：
   * <p>
   * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）。
   * 第三方应用需拥有“企业客户权限->客户联系->在职继承”权限
   * 接替成员必须在此第三方应用或自建应用的可见范围内。
   * </per>
   *
   * @param handOverUserid 原添加成员的userid
   * @param takeOverUserid 接替成员的userid
   * @param cursor         分页查询的cursor，每个分页返回的数据不会超过1000条；不填或为空表示获取第一个分页；
   * @return 客户转接接口实体 wx cp user transfer result resp
   * @throws WxErrorException the wx error exception
   */
  WxCpUserTransferResultResp resignedTransferResult(String handOverUserid, String takeOverUserid,
                                                    String cursor) throws WxErrorException;

  /**
   * <pre>
   * 该接口用于获取配置过客户群管理的客户群列表。
   * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）。
   * 暂不支持第三方调用。
   * 微信文档：https://work.weixin.qq.com/api/doc/90000/90135/92119
   * </pre>
   *
   * @param pageIndex the page index
   * @param pageSize  the page size
   * @param status    the status
   * @param userIds   the user ids
   * @param partyIds  the party ids
   * @return the wx cp user external group chat list
   * @throws WxErrorException the wx error exception
   * @deprecated 请使用 {@link WxCpExternalContactService#listGroupChat(Integer, String, int, String[])}
   */
  @Deprecated
  WxCpUserExternalGroupChatList listGroupChat(Integer pageIndex, Integer pageSize, int status, String[] userIds,
                                              String[] partyIds) throws WxErrorException;

  /**
   * <pre>
   * 该接口用于获取配置过客户群管理的客户群列表。
   * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）。
   * 暂不支持第三方调用。
   * 微信文档：https://work.weixin.qq.com/api/doc/90000/90135/92119
   * </pre>
   *
   * @param limit   分页，预期请求的数据量，取值范围 1 ~ 1000
   * @param cursor  用于分页查询的游标，字符串类型，由上一次调用返回，首次调用不填
   * @param status  客户群跟进状态过滤。0 - 所有列表(即不过滤)  1 - 离职待继承  2 - 离职继承中  3 - 离职继承完成 默认为0
   * @param userIds 群主过滤。如果不填，表示获取应用可见范围内全部群主的数据（但是不建议这么用，如果可见范围人数超过1000人，为了防止数据包过大，会报错 81017）;用户ID列表。最多100个
   * @return the wx cp user external group chat list
   * @throws WxErrorException the wx error exception
   */
  WxCpUserExternalGroupChatList listGroupChat(Integer limit, String cursor, int status, String[] userIds) throws WxErrorException;

  /**
   * <pre>
   * 通过客户群ID，获取详情。包括群名、群成员列表、群成员入群时间、入群方式。（客户群是由具有客户群使用权限的成员创建的外部群）
   * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）。
   * 暂不支持第三方调用。
   * 微信文档：https://work.weixin.qq.com/api/doc/90000/90135/92122
   * </pre>
   *
   * @param chatId   the chat id
   * @param needName the need name
   * @return group chat
   * @throws WxErrorException the wx error exception
   */
  WxCpUserExternalGroupChatInfo getGroupChat(String chatId, Integer needName) throws WxErrorException;

  /**
   * 企业可通过此接口，将已离职成员为群主的群，分配给另一个客服成员。
   *
   * <per>
   * 注意：：
   * <p>
   * 群主离职了的客户群，才可继承
   * 继承给的新群主，必须是配置了客户联系功能的成员
   * 继承给的新群主，必须有设置实名
   * 继承给的新群主，必须有激活企业微信
   * 同一个人的群，限制每天最多分配300个给新群主
   * <p>
   * 权限说明:
   * <p>
   * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）。
   * 第三方应用需拥有“企业客户权限->客户联系->分配离职成员的客户群”权限
   * 对于第三方/自建应用，群主必须在应用的可见范围。
   * </per>
   *
   * @param chatIds  需要转群主的客户群ID列表。取值范围： 1 ~ 100
   * @param newOwner 新群主ID
   * @return 分配结果 ，主要是分配失败的群列表
   * @throws WxErrorException the wx error exception
   */
  WxCpUserExternalGroupChatTransferResp transferGroupChat(String[] chatIds, String newOwner) throws WxErrorException;


  /**
   * 企业可通过此接口，将在职成员为群主的群，分配给另一个客服成员。
   * <per>
   * 注意：
   * 继承给的新群主，必须是配置了客户联系功能的成员
   * 继承给的新群主，必须有设置实名
   * 继承给的新群主，必须有激活企业微信
   * 同一个人的群，限制每天最多分配300个给新群主
   * 为保障客户服务体验，90个自然日内，在职成员的每个客户群仅可被转接2次。
   * </pre>
   *
   * @param chatIds  需要转群主的客户群ID列表。取值范围： 1 ~ 100
   * @param newOwner 新群主ID
   * @return 分配结果 ，主要是分配失败的群列表
   * @throws WxErrorException the wx error exception
   */
  WxCpUserExternalGroupChatTransferResp onjobTransferGroupChat(String[] chatIds, String newOwner) throws WxErrorException;

  /**
   * <pre>
   * 企业可通过此接口获取成员联系客户的数据，包括发起申请数、新增客户数、聊天数、发送消息数和删除/拉黑成员的客户数等指标。
   * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）。
   * 第三方应用需拥有“企业客户”权限。
   * 第三方/自建应用调用时传入的userid和partyid要在应用的可见范围内;
   * </pre>
   *
   * @param startTime the start time
   * @param endTime   the end time
   * @param userIds   the user ids
   * @param partyIds  the party ids
   * @return user behavior statistic
   * @throws WxErrorException the wx error exception
   */
  WxCpUserExternalUserBehaviorStatistic getUserBehaviorStatistic(Date startTime, Date endTime, String[] userIds,
                                                                 String[] partyIds) throws WxErrorException;

  /**
   * <pre>
   * 获取指定日期全天的统计数据。注意，企业微信仅存储60天的数据。
   * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）。
   * 暂不支持第三方调用。
   * </pre>
   *
   * @param startTime the start time
   * @param orderBy   the order by
   * @param orderAsc  the order asc
   * @param pageIndex the page index
   * @param pageSize  the page size
   * @param userIds   the user ids
   * @param partyIds  the party ids
   * @return group chat statistic
   * @throws WxErrorException the wx error exception
   */
  WxCpUserExternalGroupChatStatistic getGroupChatStatistic(Date startTime, Integer orderBy, Integer orderAsc,
                                                           Integer pageIndex, Integer pageSize, String[] userIds,
                                                           String[] partyIds) throws WxErrorException;

  /**
   * 添加企业群发消息任务
   * 企业可通过此接口添加企业群发消息的任务并通知客服人员发送给相关客户或客户群。（注：企业微信终端需升级到2.7.5版本及以上）
   * 注意：调用该接口并不会直接发送消息给客户/客户群，需要相关的客服人员操作以后才会实际发送（客服人员的企业微信需要升级到2.7.5及以上版本）
   * 同一个企业每个自然月内仅可针对一个客户/客户群发送4条消息，超过限制的用户将会被忽略。
   * <p>
   * 请求方式: POST(HTTP)
   * <p>
   * 请求地址:https://qyapi.weixin.qq.com/cgi-bin/externalcontact/add_msg_template?access_token=ACCESS_TOKEN
   * <p>
   * <a href="https://work.weixin.qq.com/api/doc/90000/90135/92135">文档地址</a>
   *
   * @param wxCpMsgTemplate the wx cp msg template
   * @return the wx cp msg template add result
   * @throws WxErrorException the wx error exception
   */
  WxCpMsgTemplateAddResult addMsgTemplate(WxCpMsgTemplate wxCpMsgTemplate) throws WxErrorException;


  /**
   * 提醒成员群发
   * 企业和第三方应用可调用此接口，重新触发群发通知，提醒成员完成群发任务，24小时内每个群发最多触发三次提醒。
   * <p>
   * 请求方式: POST(HTTPS)
   * <p>
   * 请求地址:https://qyapi.weixin.qq.com/cgi-bin/externalcontact/remind_groupmsg_send?access_token=ACCESS_TOKEN
   * <p>
   * <a href="https://developer.work.weixin.qq.com/document/path/97610">文档地址</a>
   *
   * @param msgId 群发消息的id，通过获取群发记录列表接口返回
   * @return the wx cp msg template add result
   */
  WxCpBaseResp remindGroupMsgSend(String msgId) throws WxErrorException;


  /**
   * 停止企业群发
   * 企业和第三方应用可调用此接口，停止无需成员继续发送的企业群发
   * <p>
   * 请求方式: POST(HTTPS)
   * <p>
   * 请求地址:https://qyapi.weixin.qq.com/cgi-bin/externalcontact/cancel_groupmsg_send?access_token=ACCESS_TOKEN
   * <p>
   * <a href="https://developer.work.weixin.qq.com/document/path/97611">文档地址</a>
   *
   * @param msgId 群发消息的id，通过获取群发记录列表接口返回
   * @return the wx cp msg template add result
   */
  WxCpBaseResp cancelGroupMsgSend(String msgId) throws WxErrorException;


  /**
   * 发送新客户欢迎语
   * <pre>
   * 企业微信在向企业推送添加外部联系人事件时，会额外返回一个welcome_code，企业以此为凭据调用接口，即可通过成员向新添加的客户发送个性化的欢迎语。
   * 为了保证用户体验以及避免滥用，企业仅可在收到相关事件后20秒内调用，且只可调用一次。
   * 如果企业已经在管理端为相关成员配置了可用的欢迎语，则推送添加外部联系人事件时不会返回welcome_code。
   * 每次添加新客户时可能有多个企业自建应用/第三方应用收到带有welcome_code的回调事件，但仅有最先调用的可以发送成功。后续调用将返回41051（externaluser has started chatting）错误，请用户根据实际使用需求，合理设置应用可见范围，避免冲突。
   * 请求方式: POST(HTTP)
   *
   * 请求地址:https://qyapi.weixin.qq.com/cgi-bin/externalcontact/send_welcome_msg?access_token=ACCESS_TOKEN
   *
   * <a href="https://work.weixin.qq.com/api/doc/90000/90135/92137">文档地址</a>
   * </pre>
   *
   * @param msg .
   * @throws WxErrorException .
   */
  void sendWelcomeMsg(WxCpWelcomeMsg msg) throws WxErrorException;

  /**
   * <pre>
   * 企业可通过此接口获取企业客户标签详情。
   * </pre>
   *
   * @param tagId the tag id
   * @return corp tag list
   * @throws WxErrorException the wx error exception
   */
  WxCpUserExternalTagGroupList getCorpTagList(String[] tagId) throws WxErrorException;

  /**
   * <pre>
   * 企业可通过此接口获取企业客户标签详情。
   * 若tag_id和group_id均为空，则返回所有标签。
   * 同时传递tag_id和group_id时，忽略tag_id，仅以group_id作为过滤条件。
   * </pre>
   *
   * @param tagId   the tag id
   * @param groupId the tagGroup id
   * @return corp tag list
   * @throws WxErrorException the wx error exception
   */
  WxCpUserExternalTagGroupList getCorpTagList(String[] tagId, String[] groupId) throws WxErrorException;

  /**
   * <pre>
   * 企业可通过此接口向客户标签库中添加新的标签组和标签，每个企业最多可配置3000个企业标签。
   * 暂不支持第三方调用。
   * </pre>
   *
   * @param tagGroup the tag group
   * @return wx cp user external tag group info
   * @throws WxErrorException the wx error exception
   */
  WxCpUserExternalTagGroupInfo addCorpTag(WxCpUserExternalTagGroupInfo tagGroup) throws WxErrorException;

  /**
   * <pre>
   * 企业可通过此接口编辑客户标签/标签组的名称或次序值。
   * 暂不支持第三方调用。
   * </pre>
   *
   * @param id    the id
   * @param name  the name
   * @param order the order
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp editCorpTag(String id, String name, Integer order) throws WxErrorException;

  /**
   * <pre>
   * 企业可通过此接口删除客户标签库中的标签，或删除整个标签组。
   * 暂不支持第三方调用。
   * </pre>
   *
   * @param tagId   the tag id
   * @param groupId the group id
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp delCorpTag(String[] tagId, String[] groupId) throws WxErrorException;

  /**
   * <pre>
   * 企业可通过此接口为指定成员的客户添加上由企业统一配置的标签。
   * <a href="https://work.weixin.qq.com/api/doc/90000/90135/92117">文档地址</a>
   * </pre>
   *
   * @param userid         the userid
   * @param externalUserid the external userid
   * @param addTag         the add tag
   * @param removeTag      the remove tag
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp markTag(String userid, String externalUserid, String[] addTag, String[] removeTag) throws WxErrorException;

  /**
   * <pre>
   *   企业和第三方应用可通过该接口创建客户朋友圈的发表任务。
   *   <a href="https://open.work.weixin.qq.com/api/doc/90000/90135/95094">文档地址</a>
   * </pre>
   *
   * @param task the task
   * @return wx cp add moment result
   * @throws WxErrorException the wx error exception
   */
  WxCpAddMomentResult addMomentTask(WxCpAddMomentTask task) throws WxErrorException;

  /**
   * <pre>
   * 由于发表任务的创建是异步执行的，应用需要再调用该接口以获取创建的结果。
   * <a href="https://open.work.weixin.qq.com/api/doc/90000/90135/95094">文档地址</a>
   * </pre>
   *
   * @param jobId 异步任务id，最大长度为64字节，由创建发表内容到客户朋友圈任务接口获取
   * @return moment task result
   * @throws WxErrorException the wx error exception
   */
  WxCpGetMomentTaskResult getMomentTaskResult(String jobId) throws WxErrorException;


  /**
   * <pre>
   *   停止发表企业朋友圈。
   *   <a href="https://developer.work.weixin.qq.com/document/path/97612">文档地址</a>
   * </pre>
   *
   * @param momentId 朋友圈id，可通过<a href="https://developer.work.weixin.qq.com/document/path/97612#25254/%E8%8E%B7%E5%8F%96%E5%AE%A2%E6%88%B7%E6%9C%8B%E5%8F%8B%E5%9C%88%E4%BC%81%E4%B8%9A%E5%8F%91%E8%A1%A8%E7%9A%84%E5%88%97%E8%A1%A8">获取客户朋友圈企业发表的列表</a>接口获取朋友圈企业发表的列表
   * @return wx cp add moment result
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp cancelMomentTask(String momentId) throws WxErrorException;


  /**
   * <pre>
   * 获取客户朋友圈全部的发表记录 获取企业全部的发表列表
   * <a href="https://open.work.weixin.qq.com/api/doc/90000/90135/93333">文档地址</a>
   * </pre>
   *
   * @param startTime  朋友圈记录开始时间。Unix时间戳
   * @param endTime    朋友圈记录结束时间。Unix时间戳
   * @param creator    朋友圈创建人的userid
   * @param filterType 朋友圈类型。0：企业发表 1：个人发表 2：所有，包括个人创建以及企业创建，默认情况下为所有类型
   * @param cursor     用于分页查询的游标，字符串类型，由上一次调用返回，首次调用可不填
   * @param limit      返回的最大记录数，整型，最大值100，默认值100，超过最大值时取默认值
   * @return moment list
   * @throws WxErrorException the wx error exception
   */
  WxCpGetMomentList getMomentList(Long startTime, Long endTime, String creator, Integer filterType,
                                  String cursor, Integer limit) throws WxErrorException;

  /**
   * <pre>
   * 获取客户朋友圈全部的发表记录 获取客户朋友圈企业发表的列表
   * <a href="https://open.work.weixin.qq.com/api/doc/90000/90135/93333">文档地址</a>
   * </pre>
   *
   * @param momentId 朋友圈id,仅支持企业发表的朋友圈id
   * @param cursor   用于分页查询的游标，字符串类型，由上一次调用返回，首次调用可不填
   * @param limit    返回的最大记录数，整型，最大值1000，默认值500，超过最大值时取默认值
   * @return moment task
   * @throws WxErrorException the wx error exception
   */
  WxCpGetMomentTask getMomentTask(String momentId, String cursor, Integer limit)
    throws WxErrorException;

  /**
   * <pre>
   * 获取客户朋友圈全部的发表记录 获取客户朋友圈发表时选择的可见范围
   * <a href="https://open.work.weixin.qq.com/api/doc/90000/90135/93333">文档地址</a>
   * </pre>
   *
   * @param momentId 朋友圈id
   * @param userId   企业发表成员userid，如果是企业创建的朋友圈，可以通过获取客户朋友圈企业发表的
   *                 列表获取已发表成员userid，如果是个人创建的朋友圈，创建人userid就是企业发表成员userid
   * @param cursor   用于分页查询的游标，字符串类型，由上一次调用返回，首次调用可不填
   * @param limit    返回的最大记录数，整型，最大值1000，默认值500，超过最大值时取默认值
   * @return moment customer list
   * @throws WxErrorException the wx error exception
   */
  WxCpGetMomentCustomerList getMomentCustomerList(String momentId, String userId,
                                                  String cursor, Integer limit) throws WxErrorException;

  /**
   * <pre>
   * 获取客户朋友圈全部的发表记录 获取客户朋友圈发表后的可见客户列表
   * <a href="https://open.work.weixin.qq.com/api/doc/90000/90135/93333">文档地址</a>
   * </pre>
   *
   * @param momentId 朋友圈id
   * @param userId   企业发表成员userid，如果是企业创建的朋友圈，可以通过获取客户朋友圈企业发表的列表获取已发表成员userid，
   *                 如果是个人创建的朋友圈，创建人userid就是企业发表成员userid
   * @param cursor   用于分页查询的游标，字符串类型，由上一次调用返回，首次调用可不填
   * @param limit    返回的最大记录数，整型，最大值5000，默认值3000，超过最大值时取默认值
   * @return moment send result
   * @throws WxErrorException the wx error exception
   */
  WxCpGetMomentSendResult getMomentSendResult(String momentId, String userId,
                                              String cursor, Integer limit) throws WxErrorException;

  /**
   * <pre>
   * 获取客户朋友圈全部的发表记录 获取客户朋友圈的互动数据
   * <a href="https://open.work.weixin.qq.com/api/doc/90000/90135/93333">文档地址</a>
   * </pre>
   *
   * @param momentId 朋友圈id
   * @param userId   企业发表成员userid，如果是企业创建的朋友圈，可以通过获取客户朋友圈企业发表的列表获取已发表成员userid，
   *                 如果是个人创建的朋友圈，创建人userid就是企业发表成员userid
   * @return moment comments
   * @throws WxErrorException the wx error exception
   */
  WxCpGetMomentComments getMomentComments(String momentId, String userId)
    throws WxErrorException;

  /**
   * <pre>
   * 企业和第三方应用可通过此接口获取企业与成员的群发记录。
   * <a href="https://work.weixin.qq.com/api/doc/90000/90135/93338">文档地址</a>
   * </pre>
   *
   * @param chatType   群发任务的类型，默认为single，表示发送给客户，group表示发送给客户群
   * @param startTime  群发任务记录开始时间
   * @param endTime    群发任务记录结束时间
   * @param creator    群发任务创建人企业账号id
   * @param filterType 创建人类型。0：企业发表 1：个人发表 2：所有，包括个人创建以及企业创建，默认情况下为所有类型
   * @param limit      返回的最大记录数，整型，最大值100，默认值50，超过最大值时取默认值
   * @param cursor     用于分页查询的游标，字符串类型，由上一次调用返回，首次调用可不填
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpGroupMsgListResult getGroupMsgListV2(String chatType, Date startTime, Date endTime,
                                           String creator, Integer filterType, Integer limit, String cursor) throws WxErrorException;

  /**
   * <pre>
   * 企业和第三方应用可通过此接口获取企业与成员的群发记录。
   * <a href="https://work.weixin.qq.com/api/doc/90000/90135/93338#获取企业群发成员执行结果">获取企业群发成员执行结果</a>
   * </pre>
   *
   * @param msgid  群发消息的id，通过获取群发记录列表接口返回
   * @param userid 发送成员userid，通过获取群发成员发送任务列表接口返回
   * @param limit  返回的最大记录数，整型，最大值1000，默认值500，超过最大值时取默认值
   * @param cursor 用于分页查询的游标，字符串类型，由上一次调用返回，首次调用可不填
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpGroupMsgSendResult getGroupMsgSendResult(String msgid, String userid, Integer limit, String cursor) throws WxErrorException;

  /**
   * <pre>
   * 企业跟第三方应用可通过该接口获取到创建企业群发的群发发送结果。
   * <a href="https://work.weixin.qq.com/api/doc/16251">文档</a>
   * </pre>
   *
   * @param msgid  群发消息的id，通过创建企业群发接口返回
   * @param limit  返回的最大记录数，整型，最大值10000，默认值10000
   * @param cursor 用于分页查询的游标，字符串类型，由上一次调用返回，首次调用可不填
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpGroupMsgResult getGroupMsgResult(String msgid, Integer limit, String cursor) throws WxErrorException;

  /**
   * <pre>
   * 获取群发成员发送任务列表。
   * <a href="https://work.weixin.qq.com/api/doc/90000/90135/93338#获取群发成员发送任务列表">获取群发成员发送任务列表</a>
   * </pre>
   *
   * @param msgid  群发消息的id，通过获取群发记录列表接口返回
   * @param limit  返回的最大记录数，整型，最大值1000，默认值500，超过最大值时取默认值
   * @param cursor 用于分页查询的游标，字符串类型，由上一次调用返回，首次调用可不填
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpGroupMsgTaskResult getGroupMsgTask(String msgid, Integer limit, String cursor) throws WxErrorException;

  /**
   * <pre>
   * 添加入群欢迎语素材。
   * <a href="https://open.work.weixin.qq.com/api/doc/90000/90135/92366#添加入群欢迎语素材">添加入群欢迎语素材</a>
   * </pre>
   *
   * @param template 素材内容
   * @return template_id 欢迎语素材id
   * @throws WxErrorException the wx error exception
   */
  String addGroupWelcomeTemplate(WxCpGroupWelcomeTemplateResult template) throws WxErrorException;

  /**
   * <pre>
   * 编辑入群欢迎语素材。
   * <a href="https://open.work.weixin.qq.com/api/doc/90000/90135/92366#编辑入群欢迎语素材">编辑入群欢迎语素材</a>
   * </pre>
   *
   * @param template the template
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp editGroupWelcomeTemplate(WxCpGroupWelcomeTemplateResult template) throws WxErrorException;

  /**
   * <pre>
   * 获取入群欢迎语素材。
   * <a href="https://open.work.weixin.qq.com/api/doc/90000/90135/92366#获取入群欢迎语素材">获取入群欢迎语素材</a>
   * </pre>
   *
   * @param templateId 群欢迎语的素材id
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpGroupWelcomeTemplateResult getGroupWelcomeTemplate(String templateId) throws WxErrorException;

  /**
   * <pre>
   * 删除入群欢迎语素材。
   * 企业可通过此API删除入群欢迎语素材，且仅能删除调用方自己创建的入群欢迎语素材。
   * <a href="https://open.work.weixin.qq.com/api/doc/90000/90135/92366#删除入群欢迎语素材">删除入群欢迎语素材</a>
   * </pre>
   *
   * @param templateId 群欢迎语的素材id
   * @param agentId    the agent id
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp delGroupWelcomeTemplate(String templateId, String agentId) throws WxErrorException;

  /**
   * <pre>
   * 获取商品图册
   * <a href="https://work.weixin.qq.com/api/doc/90000/90135/95096#获取商品图册列表">获取商品图册列表</a>
   * </pre>
   *
   * @param limit  返回的最大记录数，整型，最大值100，默认值50，超过最大值时取默认值
   * @param cursor 用于分页查询的游标，字符串类型，由上一次调用返回，首次调用可不填
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpProductAlbumListResult getProductAlbumList(Integer limit, String cursor) throws WxErrorException;

  /**
   * <pre>
   * 获取商品图册
   * <a href="https://work.weixin.qq.com/api/doc/90000/90135/95096#获取商品图册">获取商品图册</a>
   * </pre>
   *
   * @param productId 商品id
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpProductAlbumResult getProductAlbum(String productId) throws WxErrorException;

  /**
   * <pre>
   * 上传附件资源
   * <a href="https://open.work.weixin.qq.com/api/doc/90001/90143/95178">...</a>
   * </pre>
   *
   * @param mediaType      the media type
   * @param fileType       the file type
   * @param attachmentType the attachment type
   * @param inputStream    the input stream
   * @return wx media upload result
   * @throws WxErrorException the wx error exception
   * @throws IOException      the io exception
   */
  WxMediaUploadResult uploadAttachment(String mediaType, String fileType, Integer attachmentType,
                                       InputStream inputStream) throws WxErrorException, IOException;

  /**
   * <pre>
   * 上传附件资源
   * <a href="https://open.work.weixin.qq.com/api/doc/90001/90143/95178">...</a>
   * </pre>
   *
   * @param mediaType      the media type
   * @param attachmentType the attachment type
   * @param file           the file
   * @return wx media upload result
   * @throws WxErrorException the wx error exception
   */
  WxMediaUploadResult uploadAttachment(String mediaType, Integer attachmentType, File file)
    throws WxErrorException;

  /**
   * <pre>
   * 新建敏感词规则
   * 企业和第三方应用可以通过此接口新建敏感词规则
   * 请求方式：POST(HTTPS)
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/externalcontact/add_intercept_rule?access_token=ACCESS_TOKEN
   * <pre>
   * @param ruleAddRequest the rule add request
   * @return 规则id
   * @throws WxErrorException the wx error exception
   */
  String addInterceptRule(WxCpInterceptRuleAddRequest ruleAddRequest) throws WxErrorException;

  /**
   * <pre>
   * 修改敏感词规则
   * <a href="https://developer.work.weixin.qq.com/document/path/95097#%E4%BF%AE%E6%94%B9%E6%95%8F%E6%84%9F%E8%AF%8D%E8%A7%84%E5%88%99">文档地址</a>
   * 企业和第三方应用可以通过此接口修改敏感词规则
   * 请求方式：POST(HTTPS)
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/externalcontact/update_intercept_rule?access_token=ACCESS_TOKEN
   * <pre>
   * @param interceptRule the rule
   * @throws WxErrorException the wx error exception
   */
  void updateInterceptRule(WxCpInterceptRule interceptRule) throws WxErrorException;

  /**
   * <pre>
   * 删除敏感词规则
   * 企业和第三方应用可以通过此接口修改敏感词规则
   * 请求方式：POST(HTTPS)
   * <a href="https://qyapi.weixin.qq.com/cgi-bin/externalcontact/del_intercept_rule?access_token=ACCESS_TOKEN">请求地址</a>
   * <pre>
   * @param ruleId 规则id
   * @throws WxErrorException the wx error exception
   */
  void delInterceptRule(String ruleId) throws WxErrorException;

  /**
   * 获取敏感词规则列表
   *
   * 企业和第三方应用可以通过此接口获取所有设置的敏感词规则列表。
   * 请求方式：GET(HTTPS)
   * 文档地址：<a href="https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get_intercept_rule_list">获取敏感词规则列表</a>
   *
   * @return WxCpInterceptRuleList 敏感词规则列表
   * @throws WxErrorException 微信API异常
   */
  WxCpInterceptRuleList getInterceptRuleList() throws WxErrorException;

  /**
   * 获取敏感词详情
   *
   * 企业和第三方应用可以通过此接口获取单个敏感词规则的详细信息。
   * 请求方式：GET(HTTPS)
   * 文档地址：<a href="https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get_intercept_rule">获取敏感词详情</a>
   *
   * @param ruleId 敏感词规则ID
   * @return WxCpInterceptRuleInfo 敏感词规则详情
   * @throws WxErrorException 微信API异常
   */
  WxCpInterceptRuleInfo getInterceptRuleDetail(String ruleId) throws WxErrorException;

  /**
   * <pre>
   * 创建商品图册
   * 企业和第三方应用可以通过此接口增加商品
   * 请求方式：POST(HTTPS)
   * 请求地址：
   * <a href="https://qyapi.weixin.qq.com/cgi-bin/externalcontact/add_product_album?access_token=ACCESS_TOKEN">https://qyapi.weixin.qq.com/cgi-bin/externalcontact/add_product_album?access_token=ACCESS_TOKEN</a>
   * <a href="https://developer.work.weixin.qq.com/document/path/95096#%E5%88%9B%E5%BB%BA%E5%95%86%E5%93%81%E5%9B%BE%E5%86%8C">文档地址</a>
   * <pre>
   * @param wxCpProductAlbumInfo 商品图册信息
   * @return 商品id string
   * @throws WxErrorException the wx error exception
   */
  String addProductAlbum(WxCpProductAlbumInfo wxCpProductAlbumInfo) throws WxErrorException;

  /**
   * <pre>
   * 编辑商品图册
   * 企业和第三方应用可以通过此接口修改商品信息
   * 请求方式：POST(HTTPS)
   * 请求地址：
   * <a href="https://qyapi.weixin.qq.com/cgi-bin/externalcontact/update_product_album?access_token=ACCESS_TOKEN">https://qyapi.weixin.qq.com/cgi-bin/externalcontact/update_product_album?access_token=ACCESS_TOKEN</a>
   * <a href="https://developer.work.weixin.qq.com/document/path/95096#%E7%BC%96%E8%BE%91%E5%95%86%E5%93%81%E5%9B%BE%E5%86%8C">文档地址</a>
   * <pre>
   * @param wxCpProductAlbumInfo 商品图册信息
   * @throws WxErrorException the wx error exception
   */
  void updateProductAlbum(WxCpProductAlbumInfo wxCpProductAlbumInfo) throws WxErrorException;

  /**
   * <pre>
   * 删除商品图册
   * 企业和第三方应用可以通过此接口删除商品信息
   * 请求方式：POST(HTTPS)
   * 请求地址：
   * <a href="https://qyapi.weixin.qq.com/cgi-bin/externalcontact/delete_product_album?access_token=ACCESS_TOKEN">https://qyapi.weixin.qq.com/cgi-bin/externalcontact/delete_product_album?access_token=ACCESS_TOKEN</a>
   *
   * <a href="https://developer.work.weixin.qq.com/document/path/95096#%E5%88%A0%E9%99%A4%E5%95%86%E5%93%81%E5%9B%BE%E5%86%8C">文档地址</a>
   * <pre>
   * @param productId 商品id
   * @throws WxErrorException the wx error exception
   */
  void deleteProductAlbum(String productId) throws WxErrorException;

  /**
   * <pre>
   * 获取获客链接列表
   * 企业可通过此接口获取当前仍然有效的获客链接。
   * 请求方式：POST(HTTPS)
   * 请求地址：
   * <a href="https://qyapi.weixin.qq.com/cgi-bin/externalcontact/customer_acquisition/list_link?access_token=ACCESS_TOKEN">接口地址</a>
   *
   * <a href="https://developer.work.weixin.qq.com/document/path/97297#%E8%8E%B7%E5%8F%96%E8%8E%B7%E5%AE%A2%E9%93%BE%E6%8E%A5%E5%88%97%E8%A1%A8">文档地址</a>
   * </pre>
   * @param limit 商品id
   * @param cursor 商品id
   * @return 获客链接列表
   * @throws WxErrorException the wx error exception
   */
  WxCpCustomerAcquisitionList customerAcquisitionLinkList(Integer limit, String cursor) throws WxErrorException;

  /**
   * <pre>
   * 获取获客链接详情
   * 企业可通过此接口根据获客链接id获取链接配置详情。。
   * 请求方式：POST(HTTPS)
   * 请求地址：
   * <a href="https://qyapi.weixin.qq.com/cgi-bin/externalcontact/customer_acquisition/get?access_token=ACCESS_TOKEN">接口地址</a>
   *
   * <a href="https://developer.work.weixin.qq.com/document/path/97297#%E8%8E%B7%E5%8F%96%E8%8E%B7%E5%AE%A2%E9%93%BE%E6%8E%A5%E8%AF%A6%E6%83%85">文档地址</a>
   * </pre>
   * @param linkId 获客链接ID
   * @return 获客链接详情
   * @throws WxErrorException the wx error exception
   */
  WxCpCustomerAcquisitionInfo customerAcquisitionLinkGet(String linkId) throws WxErrorException;

  /**
   * <pre>
   * 创建获客链接
   * 企业可通过此接口创建新的获客链接。
   * 请求方式：POST(HTTPS)
   * 请求地址：
   * <a href="https://qyapi.weixin.qq.com/cgi-bin/externalcontact/customer_acquisition/create_link?access_token=ACCESS_TOKEN">接口地址</a>
   * <a href="https://developer.work.weixin.qq.com/document/path/97297#%E5%88%9B%E5%BB%BA%E8%8E%B7%E5%AE%A2%E9%93%BE%E6%8E%A5">文档地址</a>
   * </pre>
   *
   * @param wxCpCustomerAcquisitionRequest 创建链接请求
   * @return 创建链接详情
   * @throws WxErrorException the wx error exception
   */
  WxCpCustomerAcquisitionCreateResult customerAcquisitionLinkCreate(WxCpCustomerAcquisitionRequest wxCpCustomerAcquisitionRequest) throws WxErrorException;

  /**
   * <pre>
   * 编辑获客链接
   * 企业可通过此接口编辑获客链接，修改获客链接的关联范围或修改获客链接的名称。
   * 请求方式：POST(HTTPS)
   * 请求地址：
   * <a href="https://qyapi.weixin.qq.com/cgi-bin/externalcontact/customer_acquisition/update_link?access_token=ACCESS_TOKEN">接口地址</a>
   * <a href="https://developer.work.weixin.qq.com/document/path/97297#%E7%BC%96%E8%BE%91%E8%8E%B7%E5%AE%A2%E9%93%BE%E6%8E%A5">文档地址</a>
   * </pre>
   *
   * @param wxCpCustomerAcquisitionRequest 编辑链接请求
   * @return 编辑链接详情
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp customerAcquisitionUpdate(WxCpCustomerAcquisitionRequest wxCpCustomerAcquisitionRequest) throws WxErrorException;

  /**
   * <pre>
   * 删除获客链接
   * 企业可通过此接口删除获客链接，删除后的获客链接将无法继续使用。
   * 请求方式：POST(HTTPS)
   * 请求地址：
   * <a href="https://qyapi.weixin.qq.com/cgi-bin/externalcontact/customer_acquisition/delete_link?access_token=ACCESS_TOKEN">接口地址</a>
   * <a href="https://developer.work.weixin.qq.com/document/path/97297#%E5%88%A0%E9%99%A4%E8%8E%B7%E5%AE%A2%E9%93%BE%E6%8E%A5">文档地址</a>
   * </pre>
   *
   * @param linkId 获客链接的id
   * @return 删除结果
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp customerAcquisitionLinkDelete(String linkId) throws WxErrorException;

  /**
   * <pre>
   * 获取获客客户列表
   * 企业可通过此接口获取到由指定的获客链接添加的客户列表。
   * 请求方式：POST(HTTPS)
   * 请求地址：
   * <a href="https://qyapi.weixin.qq.com/cgi-bin/externalcontact/customer_acquisition/customer?access_token=ACCESS_TOKEN">接口地址</a>
   * <a href="https://developer.work.weixin.qq.com/document/path/97298">文档地址</a>
   * </pre>
   *
   * @param linkId 获客链接id
   * @param limit  返回的最大记录数，整型，最大值1000
   * @param cursor 用于分页查询的游标，字符串类型，由上一次调用返回，首次调用可不填
   * @return 由获客链接添加的客户信息列表
   * @throws WxErrorException the wx error exception
   */
  WxCpCustomerAcquisitionCustomerList customerAcquisitionCustomer(String linkId, Integer limit, String cursor) throws WxErrorException;

  /**
   * <pre>
   * 查询剩余使用量
   * 企业可通过此接口查询当前剩余的使用量。
   * 请求方式：GET(HTTPS)
   * 请求地址：
   * <a href="https://qyapi.weixin.qq.com/cgi-bin/externalcontact/customer_acquisition_quota?access_token=ACCESS_TOKEN">接口地址</a>
   * <a href="https://developer.work.weixin.qq.com/document/path/97375">文档地址</a>
   * </pre>
   *
   * @return 剩余使用量
   * @throws WxErrorException the wx error exception
   */
  WxCpCustomerAcquisitionQuota customerAcquisitionQuota() throws WxErrorException;


  /**
   * 查询链接使用详情
   * 服务商可通过此接口查询指定组件授权的获客链接在指定时间范围内的访问情况。
   *
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/externalcontact/customer_acquisition/statistic?access_token=ACCESS_TOKEN
   *
   * @author Hugo
   * @date 2023/12/5 14:34
   * @param linkId 获客链接的id
   * @param startTime 统计起始时间
   * @param endTime 统计结束时间
   * @return 点击链接客户数和新增客户数
   * @throws WxErrorException the wx error exception
   */
  WxCpCustomerAcquisitionStatistic customerAcquisitionStatistic(String linkId, Date startTime, Date endTime) throws WxErrorException;
}
