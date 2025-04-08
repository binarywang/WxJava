package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.kf.*;

import java.util.List;

/**
 * 微信客服接口
 * <p>
 * 微信客服由腾讯微信团队为企业打造，用于满足企业的客服需求，帮助企业做好客户服务。企业可以在微信内、外各个场景中接入微信客服，
 * 用户可以发起咨询，企业可以进行回复。
 * 企业可在微信客服官网使用企业微信扫码开通微信客服，开通后即可使用。
 *
 * @author Fu  created on  2022/1/19 19:25
 */
public interface WxCpKfService {

  /**
   * 添加客服帐号，并可设置客服名称和头像。目前一家企业最多可添加10个客服帐号
   *
   * @param add 客服帐号信息
   * @return result -新创建的客服帐号ID
   * @throws WxErrorException 异常
   */
  WxCpKfAccountAddResp addAccount(WxCpKfAccountAdd add) throws WxErrorException;

  /**
   * 修改已有的客服帐号，可修改客服名称和头像。
   *
   * @param upd 新的客服账号信息
   * @return result wx cp base resp
   * @throws WxErrorException 异常
   */
  WxCpBaseResp updAccount(WxCpKfAccountUpd upd) throws WxErrorException;

  /**
   * 删除已有的客服帐号
   *
   * @param del 要删除的客服帐号
   * @return result wx cp base resp
   * @throws WxErrorException 异常
   */
  WxCpBaseResp delAccount(WxCpKfAccountDel del) throws WxErrorException;

  /**
   * 获取客服帐号列表，包括所有的客服帐号的客服ID、名称和头像。
   *
   * @param offset 分页，偏移量, 默认为0
   * @param limit  分页，预期请求的数据量，默认为100，取值范围 1 ~ 100
   * @return 客服帐号列表 wx cp kf account list resp
   * @throws WxErrorException 异常
   */
  WxCpKfAccountListResp listAccount(Integer offset, Integer limit) throws WxErrorException;

  /**
   * 企业可通过此接口获取带有不同参数的客服链接，不同客服帐号对应不同的客服链接。获取后，企业可将链接嵌入到网页等场景中，
   * 微信用户点击链接即可向对应的客服帐号发起咨询。企业可依据参数来识别用户的咨询来源等
   *
   * @param link 参数
   * @return 链接 account link
   * @throws WxErrorException 异常
   */
  WxCpKfAccountLinkResp getAccountLink(WxCpKfAccountLink link) throws WxErrorException;

  /**
   * 接待人员管理
   * 添加指定客服帐号的接待人员，每个客服帐号目前最多可添加500个接待人员。
   *
   * @param openKfid   客服帐号ID
   * @param userIdList 接待人员userid列表。第三方应用填密文userid，即open_userid 可填充个数：1 ~ 100。超过100个需分批调用。
   * @return 添加客服账号结果 wx cp kf servicer op resp
   * @throws WxErrorException 异常
   */
  WxCpKfServicerOpResp addServicer(String openKfid, List<String> userIdList) throws WxErrorException;

  /**
   * 接待人员管理
   * 添加指定客服账号的接待人员，每个客服账号目前最多可添加2000个接待人员，20个部门。
   * userid_list和department_id_list至少需要填其中一个
   *
   * @param openKfid   客服帐号ID
   * @param userIdList 接待人员userid列表。第三方应用填密文userid，即open_userid 可填充个数：1 ~ 100。超过100个需分批调用。
   * @param departmentIdList 接待人员部门id列表 可填充个数：0 ~ 20。
   * @return 添加客服账号结果 wx cp kf servicer op resp
   * @throws WxErrorException 异常
   */
  WxCpKfServicerOpResp addServicer(String openKfid, List<String> userIdList,List<String> departmentIdList) throws WxErrorException;

  /**
   * 接待人员管理
   * 从客服帐号删除接待人员
   *
   * @param openKfid   客服帐号ID
   * @param userIdList 接待人员userid列表。第三方应用填密文userid，即open_userid 可填充个数：1 ~ 100。超过100个需分批调用。
   * @return 删除客服账号结果 wx cp kf servicer op resp
   * @throws WxErrorException 异常
   */
  WxCpKfServicerOpResp delServicer(String openKfid, List<String> userIdList) throws WxErrorException;

  /**
   * 接待人员管理
   * 从客服帐号删除接待人员
   * userid_list和department_id_list至少需要填其中一个
   *
   * @param openKfid   客服帐号ID
   * @param userIdList 接待人员userid列表。第三方应用填密文userid，即open_userid 可填充个数：1 ~ 100。超过100个需分批调用。
   * @param departmentIdList 接待人员部门id列表 可填充个数：0 ~ 100。超过100个需分批调用。
   * @return 删除客服账号结果 wx cp kf servicer op resp
   * @throws WxErrorException 异常
   */
  WxCpKfServicerOpResp delServicer(String openKfid, List<String> userIdList, List<String> departmentIdList) throws WxErrorException;

  /**
   * 接待人员管理
   * 获取某个客服帐号的接待人员列表
   *
   * @param openKfid 客服帐号ID
   * @return 接待人员列表 wx cp kf servicer list resp
   * @throws WxErrorException 异常
   */
  WxCpKfServicerListResp listServicer(String openKfid) throws WxErrorException;

  /**
   * 分配客服会话
   * 获取会话状态
   *
   * @param openKfid       客服帐号ID
   * @param externalUserId 微信客户的external_userid
   * @return service state
   * @throws WxErrorException the wx error exception
   */
  WxCpKfServiceStateResp getServiceState(String openKfid, String externalUserId)
    throws WxErrorException;

  /**
   * 分配客服会话
   * 变更会话状态
   *
   * @param openKfid       客服帐号ID
   * @param externalUserId 微信客户的external_userid
   * @param serviceState   变更的目标状态，状态定义和所允许的变更可参考概述中的流程图和表格
   * @param servicerUserId 接待人员的userid。第三方应用填密文userid，即open_userid。当state=3时要求必填，接待人员须处于“正在接待”中。
   * @return 部分状态返回回复语code wx cp kf service state trans resp
   * @throws WxErrorException the wx error exception
   */
  WxCpKfServiceStateTransResp transServiceState(String openKfid, String externalUserId,
                                                Integer serviceState, String servicerUserId) throws WxErrorException;

  /**
   * 读取消息
   * 微信客户发送的消息、接待人员在企业微信回复的消息、发送消息接口发送失败事件（如被用户拒收）、客户点击菜单消息的回复消息，
   * 可以通过该接口获取具体的消息内容和事件。不支持读取通过发送消息接口发送的消息。
   * 支持的消息类型：文本、图片、语音、视频、文件、位置、链接、名片、小程序、菜单、事件。
   *
   * @param cursor      上一次调用时返回的next_cursor，第一次拉取可以不填。不多于64字节
   * @param token       回调事件返回的token字段，10分钟内有效；可不填，如果不填接口有严格的频率限制。不多于128字节
   * @param limit       期望请求的数据量，默认值和最大值都为1000。              注意：可能会出现返回条数少于limit的情况，需结合返回的has_more字段判断是否继续请求。
   * @param voiceFormat 语音消息类型，0-Amr 1-Silk，默认0。可通过该参数控制返回的语音格式
   * @return 微信消息 wx cp kf msg list resp
   * @throws WxErrorException 异常
   */
  @Deprecated
  WxCpKfMsgListResp syncMsg(String cursor, String token, Integer limit, Integer voiceFormat)
    throws WxErrorException;

  WxCpKfMsgListResp syncMsg(String cursor, String token, Integer limit, Integer voiceFormat,String open_kfid)
    throws WxErrorException;

  /**
   * 发送消息
   * 当微信客户处于“新接入待处理”或“由智能助手接待”状态下，可调用该接口给用户发送消息。
   * 注意仅当微信客户在主动发送消息给客服后的48小时内，企业可发送消息给客户，最多可发送5条消息；若用户继续发送消息，企业可再次下发消息。
   * 支持发送消息类型：文本、图片、语音、视频、文件、图文、小程序、菜单消息、地理位置。
   *
   * @param request 发送信息
   * @return 发送结果 wx cp kf msg send resp
   * @throws WxErrorException 异常
   */
  WxCpKfMsgSendResp sendMsg(WxCpKfMsgSendRequest request) throws WxErrorException;

  /**
   * 发送欢迎语等事件响应消息
   * 当特定的事件回调消息包含code字段，或通过接口变更到特定的会话状态，会返回code字段。
   * 开发者可以此code为凭证，调用该接口给用户发送相应事件场景下的消息，如客服欢迎语、客服提示语和会话结束语等。
   * 除"用户进入会话事件"以外，响应消息仅支持会话处于获取该code的会话状态时发送，如将会话转入待接入池时获得的code仅能在会话状态为”待接入池排队中“时发送。
   * <p>
   * 目前支持的事件场景和相关约束如下：
   * <p>
   * 事件场景	                  允许下发条数	code有效期	  支持的消息类型	  获取code途径
   * 用户进入会话，用于发送客服欢迎语	1条	        20秒	      文本、菜单	    事件回调
   * 进入接待池，用于发送排队提示语等	1条	        48小时	      文本	          转接会话接口
   * 从接待池接入会话，用于发送非工作
   * 时间的提示语或超时未回复的提示语
   * 等	                        1条	        48小时	      文本	          事件回调、转接会话接口
   * 结束会话，用于发送结束会话提示语
   * 或满意度评价等	              1条	        20秒	      文本、菜单	    事件回调、转接会话接口
   *
   * @param request the request
   * @return wx cp kf msg send resp
   * @throws WxErrorException the wx error exception
   */
  WxCpKfMsgSendResp sendMsgOnEvent(WxCpKfMsgSendRequest request) throws WxErrorException;

  /**
   * 获取客户基础信息
   *
   * @param externalUserIdList the external user id list
   * @return wx cp kf customer batch get resp
   * @throws WxErrorException the wx error exception
   */
  WxCpKfCustomerBatchGetResp customerBatchGet(List<String> externalUserIdList)
    throws WxErrorException;

  /**
   * <pre>
   * 获取「客户数据统计」企业汇总数据
   * 通过此接口，可以获取咨询会话数、咨询客户数等企业汇总统计数据
   * 请求方式：POST(HTTPS)
   * 请求地址：
   * <a href="https://qyapi.weixin.qq.com/cgi-bin/kf/get_corp_statistic?access_token=ACCESS_TOKEN">https://qyapi.weixin.qq.com/cgi-bin/kf/get_corp_statistic?access_token=ACCESS_TOKEN</a>
   * 文档地址：
   * <a href="https://developer.work.weixin.qq.com/document/path/95489">https://developer.work.weixin.qq.com/document/path/95489</a>
   * <pre>
   * @param request 查询参数
   * @return 客户数据统计 -企业汇总数据
   * @throws WxErrorException the wx error exception
   */
  WxCpKfGetCorpStatisticResp getCorpStatistic(WxCpKfGetCorpStatisticRequest request) throws WxErrorException;

  /**
   * <pre>
   * 获取「客户数据统计」接待人员明细数据
   * 通过此接口，可获取接入人工会话数、咨询会话数等与接待人员相关的统计信息
   * 请求方式：POST(HTTPS)
   * 请求地址：
   * <a href="https://qyapi.weixin.qq.com/cgi-bin/kf/get_servicer_statistic?access_token=ACCESS_TOKEN">https://qyapi.weixin.qq.com/cgi-bin/kf/get_servicer_statistic?access_token=ACCESS_TOKEN</a>
   * 文档地址：
   * <a href="https://developer.work.weixin.qq.com/document/path/95490">https://developer.work.weixin.qq.com/document/path/95490</a>
   * <pre>
   * @param request 查询参数
   * @return 客户数据统计 -企业汇总数据
   * @throws WxErrorException the wx error exception
   */
  WxCpKfGetServicerStatisticResp getServicerStatistic(WxCpKfGetServicerStatisticRequest request) throws WxErrorException;

  // 「升级服务」配置

  /**
   * 获取配置的专员与客户群
   *
   * @return upgrade service config
   * @throws WxErrorException the wx error exception
   */
  WxCpKfServiceUpgradeConfigResp getUpgradeServiceConfig() throws WxErrorException;

  /**
   * 升级专员服务
   *
   * @param openKfid       客服帐号ID
   * @param externalUserId 微信客户的external_userid
   * @param userid         服务专员的userid
   * @param wording        推荐语
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp upgradeMemberService(String openKfid, String externalUserId,
                                    String userid, String wording) throws WxErrorException;

  /**
   * 升级客户群服务
   *
   * @param openKfid       客服帐号ID
   * @param externalUserId 微信客户的external_userid
   * @param chatId         客户群id
   * @param wording        推荐语
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp upgradeGroupchatService(String openKfid, String externalUserId,
                                       String chatId, String wording) throws WxErrorException;

  /**
   * 为客户取消推荐
   *
   * @param openKfid       客服帐号ID
   * @param externalUserId 微信客户的external_userid
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp cancelUpgradeService(String openKfid, String externalUserId)
    throws WxErrorException;
}
