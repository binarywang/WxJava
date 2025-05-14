package me.chanjar.weixin.cp.api;

import lombok.NonNull;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.*;

import java.util.Date;
import java.util.List;

/**
 * 企业微信OA相关接口.
 *
 * @author Element & Wang_Wong created on  2019-04-06 10:52
 */
public interface WxCpOaService {

  /**
   * <pre>提交审批申请
   * 调试工具
   * 企业可通过审批应用或自建应用Secret调用本接口，代应用可见范围内员工在企业微信“审批应用”内提交指定类型的审批申请。
   *
   * 请求方式：POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/applyevent?access_token=ACCESS_TOKEN
   * <a href="https://work.weixin.qq.com/api/doc/90000/90135/91853">文档地址</a>
   * </pre>
   *
   * @param request 请求
   * @return 表单提交成功后 ，返回的表单编号
   * @throws WxErrorException .
   */
  String apply(WxCpOaApplyEventRequest request) throws WxErrorException;

  /**
   * <pre>
   *  获取打卡数据
   *  <a href="https://work.weixin.qq.com/api/doc#90000/90135/90262">文档地址</a>
   * </pre>
   *
   * @param openCheckinDataType 打卡类型。1：上下班打卡；2：外出打卡；3：全部打卡
   * @param startTime           获取打卡记录的开始时间
   * @param endTime             获取打卡记录的结束时间
   * @param userIdList          需要获取打卡记录的用户列表
   * @return 打卡数据列表 checkin data
   * @throws WxErrorException 异常
   */
  List<WxCpCheckinData> getCheckinData(Integer openCheckinDataType, Date startTime, Date endTime,
                                       List<String> userIdList) throws WxErrorException;

  /**
   * <pre>
   *   获取打卡规则
   *  <a href="https://work.weixin.qq.com/api/doc#90000/90135/90263">文档地址</a>
   * </pre>
   *
   * @param datetime   需要获取规则的当天日期
   * @param userIdList 需要获取打卡规则的用户列表
   * @return 打卡规则列表 checkin option
   * @throws WxErrorException .
   */
  List<WxCpCheckinOption> getCheckinOption(Date datetime, List<String> userIdList) throws WxErrorException;


  /**
   * <pre>
   *   获取企业所有打卡规则
   * <a href="https://work.weixin.qq.com/api/doc/90000/90135/93384">文档地址</a>
   * </pre>
   *
   * @return 打卡规则列表 crop checkin option
   * @throws WxErrorException the wx error exception
   */
  List<WxCpCropCheckinOption> getCropCheckinOption() throws WxErrorException;

  /**
   * <pre>
   *
   * 批量获取审批单号
   *
   * 审批应用及有权限的自建应用，可通过Secret调用本接口，以获取企业一段时间内企业微信“审批应用”单据的审批编号，支持按模板类型、申请人、部门、申请单审批状态等条件筛选。
   * 自建应用调用此接口，需在“管理后台-应用管理-审批-API-审批数据权限”中，授权应用允许提交审批单据。
   *
   * 一次拉取调用最多拉取100个审批记录，可以通过多次拉取的方式来满足需求，但调用频率不可超过600次/分。
   *
   * <a href="https://work.weixin.qq.com/api/doc/90000/90135/91816">文档地址</a>
   * </pre>
   *
   * @param startTime 开始时间
   * @param endTime   结束时间
   * @param cursor    分页查询游标，默认为0，后续使用返回的next_cursor进行分页拉取
   * @param size      一次请求拉取审批单数量，默认值为100，上限值为100
   * @param filters   筛选条件，可对批量拉取的审批申请设置约束条件，支持设置多个条件,nullable
   * @return WxCpApprovalInfo approval info
   * @throws WxErrorException .
   */
  @Deprecated
  WxCpApprovalInfo getApprovalInfo(@NonNull Date startTime, @NonNull Date endTime, Integer cursor, Integer size,
                                   List<WxCpApprovalInfoQueryFilter> filters) throws WxErrorException;

  /**
   * short method
   *
   * @param startTime 开始时间
   * @param endTime   结束时间
   * @return WxCpApprovalInfo approval info
   * @throws WxErrorException .
   * @see me.chanjar.weixin.cp.api.WxCpOaService#getApprovalInfo me.chanjar.weixin.cp.api
   * .WxCpOaService#getApprovalInfome.chanjar.weixin.cp.api.WxCpOaService#getApprovalInfo
   */
  @Deprecated
  WxCpApprovalInfo getApprovalInfo(@NonNull Date startTime, @NonNull Date endTime) throws WxErrorException;


  /**
   * <pre>
   *
   * 批量获取审批单号
   *
   * 审批应用及有权限的自建应用，可通过Secret调用本接口，以获取企业一段时间内企业微信“审批应用”单据的审批编号，支持按模板类型、申请人、部门、申请单审批状态等条件筛选。
   * 自建应用调用此接口，需在“管理后台-应用管理-审批-API-审批数据权限”中，授权应用允许提交审批单据。
   *
   * 一次拉取调用最多拉取100个审批记录，可以通过多次拉取的方式来满足需求，但调用频率不可超过600次/分。
   *
   * <a href="https://work.weixin.qq.com/api/doc/90000/90135/91816">文档地址</a>
   *
   * 1 接口频率限制 600次/分钟
   * 2 请求的参数endtime需要大于startime， 起始时间跨度不能超过31天；
   * 3 老的分页游标字段cursor和next_cursor待废弃，请开发者使用新字段new_cursor和new_next_cursor。
   * </pre>
   *
   * @param startTime 开始时间
   * @param endTime   结束时间
   * @param newCursor    分页查询游标，默认为0，后续使用返回的next_cursor进行分页拉取
   * @param size      一次请求拉取审批单数量，默认值为100，上限值为100
   * @param filters   筛选条件，可对批量拉取的审批申请设置约束条件，支持设置多个条件,nullable
   * @return WxCpApprovalInfo approval info
   * @throws WxErrorException .
   */
  WxCpApprovalInfo getApprovalInfo(@NonNull Date startTime, @NonNull Date endTime, String newCursor, Integer size,
                                   List<WxCpApprovalInfoQueryFilter> filters) throws WxErrorException;


  /**
   * <pre>
   *   获取审批申请详情
   *
   *   企业可通过审批应用或自建应用Secret调用本接口，根据审批单号查询企业微信“审批应用”的审批申请详情。
   *
   *  <a href="https://work.weixin.qq.com/api/doc/90000/90135/91983">文档地址</a>
   * </pre>
   *
   * @param spNo 审批单编号。
   * @return WxCpApprovaldetail approval detail
   * @throws WxErrorException .
   */
  WxCpApprovalDetailResult getApprovalDetail(@NonNull String spNo) throws WxErrorException;


  /**
   * 获取企业假期管理配置
   * 企业可通过审批应用或自建应用Secret调用本接口，获取可见范围内员工的“假期管理”配置，包括：各个假期的id、名称、请假单位、时长计算方式、发放规则等。
   * 第三方应用可获取应用可见范围内员工的“假期管理”配置，包括：各个假期的id、名称、请假单位、时长计算方式、发放规则等。
   * <p>
   * 请求方式：GET(HTTPS)
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/oa/vacation/getcorpconf?access_token=ACCESS_TOKEN
   *
   * @return corp conf
   * @throws WxErrorException the wx error exception
   */
  WxCpCorpConfInfo getCorpConf() throws WxErrorException;


  /**
   * 获取成员假期余额
   * 企业可通过审批应用或自建应用Secret调用本接口，获取可见范围内各个员工的假期余额数据。
   * 第三方应用可获取应用可见范围内各个员工的假期余额数据。
   * <p>
   * 请求方式：POST(HTTPS)
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/oa/vacation/getuservacationquota?access_token=ACCESS_TOKEN
   *
   * @param userId 需要获取假期余额的成员的userid
   * @return user vacation quota
   * @throws WxErrorException the wx error exception
   */
  WxCpUserVacationQuota getUserVacationQuota(@NonNull String userId) throws WxErrorException;


  /**
   * 获取审批数据（旧）
   * 提示：推荐使用新接口“批量获取审批单号”及“获取审批申请详情”，此接口后续将不再维护、逐步下线。
   * 通过本接口来获取公司一段时间内的审批记录。一次拉取调用最多拉取100个审批记录，可以通过多次拉取的方式来满足需求，但调用频率不可超过600次/分。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/corp/getapprovaldata?access_token=ACCESS_TOKEN
   *
   * @param startTime 获取审批记录的开始时间。Unix时间戳
   * @param endTime   获取审批记录的结束时间。Unix时间戳
   * @param nextSpNum 第一个拉取的审批单号，不填从该时间段的第一个审批单拉取
   * @return approval data
   * @throws WxErrorException the wx error exception
   */
  WxCpGetApprovalData getApprovalData(@NonNull Long startTime, @NonNull Long endTime, Long nextSpNum) throws WxErrorException;


  /**
   * 修改成员假期余额
   * 企业可通过审批应用或自建应用Secret调用本接口，修改可见范围内员工的“假期余额”。
   * 第三方应用可通过应本接口修改应用可见范围内指定员工的“假期余额”。
   * <p>
   * 请求方式：POST(HTTPS)
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/oa/vacation/setoneuserquota?access_token=ACCESS_TOKEN
   *
   * @param userId       需要修改假期余额的成员的userid
   * @param vacationId   假期id
   * @param leftDuration 设置的假期余额，单位为秒，不能大于1000天或24000小时，当假期时间刻度为按小时请假时，必须为360整倍数，即0.1小时整倍数，按天请假时，必须为8640整倍数，即0.1天整倍数
   * @param timeAttr     假期时间刻度：0-按天请假；1-按小时请假
   * @param remarks      修改备注，用于显示在假期余额的修改记录当中，可对修改行为作说明，不超过200字符
   * @return one user quota
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp setOneUserQuota(@NonNull String userId, @NonNull Integer vacationId, @NonNull Integer leftDuration,
                               @NonNull Integer timeAttr, String remarks) throws WxErrorException;


  /**
   * 获取公费电话拨打记录
   *
   * @param startTime 查询的起始时间戳
   * @param endTime   查询的结束时间戳
   * @param offset    分页查询的偏移量
   * @param limit     分页查询的每页大小,默认为100条，如该参数大于100则按100处理
   * @return . dial record
   * @throws WxErrorException .
   */
  List<WxCpDialRecord> getDialRecord(Date startTime, Date endTime, Integer offset,
                                     Integer limit) throws WxErrorException;

  /**
   * 获取审批模板详情
   *
   * @param templateId 模板ID
   * @return . template detail
   * @throws WxErrorException .
   */
  WxCpOaApprovalTemplateResult getTemplateDetail(@NonNull String templateId) throws WxErrorException;

  /**
   * 创建审批模板
   * <br>
   * 可以调用此接口创建审批模板。创建新模板后，管理后台及审批应用内将生成对应模板，并生效默认流程和规则配置。
   * <pre>
   *  文档地址: <a href="https://developer.work.weixin.qq.com/document/path/97437">https://developer.work.weixin.qq.com/document/path/97437</a>
   *  权限说明
   * • 仅『审批』系统应用、自建应用和代开发自建应用可调用。
   * </pre>
   *
   * @param cpTemplate cpTemplate
   * @return templateId
   * @throws WxErrorException .
   */
  String createOaApprovalTemplate(WxCpOaApprovalTemplate cpTemplate) throws WxErrorException;

  /**
   * 更新审批模板
   * <br>
   * 可调用本接口更新审批模板。更新模板后，管理后台及审批应用内将更新原模板的内容，已配置的审批流程和规则不变。
   * <pre>
   *  文档地址: <a href="https://developer.work.weixin.qq.com/document/path/97438">https://developer.work.weixin.qq.com/document/path/97438</a>
   *  权限说明
   * • 仅『审批』系统应用，自建应用和代开发自建应用可调用
   * • 所有应用都可以通过本接口更新自己的模板
   * • 『审批』系统应用可以修改管理员手动创建的模板
   * • 自建应用和代开发自建应用不可通过本接口更新其他应用创建的模板
   * </pre>
   *
   * @param wxCpTemplate wxCpTemplate
   * @throws WxErrorException .
   */
  void updateOaApprovalTemplate(WxCpOaApprovalTemplate wxCpTemplate) throws WxErrorException;

  /**
   * 获取打卡日报数据
   *
   * @param startTime  获取日报的开始时间
   * @param endTime    获取日报的结束时间
   * @param userIdList 获取日报的userid列表
   * @return 日报数据列表 checkin day data
   * @throws WxErrorException the wx error exception
   */
  List<WxCpCheckinDayData> getCheckinDayData(Date startTime, Date endTime, List<String> userIdList) throws WxErrorException;


  /**
   * 获取打卡月报数据
   *
   * @param startTime  获取月报的开始时间
   * @param endTime    获取月报的结束时间
   * @param userIdList 获取月报的userid列表
   * @return 月报数据列表 checkin month data
   * @throws WxErrorException the wx error exception
   */
  List<WxCpCheckinMonthData> getCheckinMonthData(Date startTime, Date endTime, List<String> userIdList) throws WxErrorException;

  /**
   * 获取打卡人员排班信息
   *
   * @param startTime  获取排班信息的开始时间。Unix时间戳
   * @param endTime    获取排班信息的结束时间。Unix时间戳（与starttime跨度不超过一个月）
   * @param userIdList 需要获取排班信息的用户列表（不超过100个）
   * @return 排班表信息 checkin schedule list
   * @throws WxErrorException the wx error exception
   */
  List<WxCpCheckinSchedule> getCheckinScheduleList(Date startTime, Date endTime, List<String> userIdList) throws WxErrorException;


  /**
   * 为打卡人员排班
   *
   * @param wxCpSetCheckinSchedule the wx cp set checkin schedule
   * @throws WxErrorException the wx error exception
   */
  void setCheckinScheduleList(WxCpSetCheckinSchedule wxCpSetCheckinSchedule) throws WxErrorException;

  /**
   * <pre>
   * 录入打卡人员人脸信息
   * 企业可通过打卡应用Secret调用本接口，为企业打卡人员录入人脸信息，人脸信息仅用于人脸打卡。
   * 上传图片大小限制:图片数据不超过1M
   * 请求方式：POST(HTTPS)
   * 请求地址：
   * <a href="https://qyapi.weixin.qq.com/cgi-bin/checkin/addcheckinuserface?access_token=ACCESS_TOKEN">https://qyapi.weixin.qq.com/cgi-bin/checkin/addcheckinuserface?access_token=ACCESS_TOKEN</a>
   * 文档地址：
   * <a href="https://developer.work.weixin.qq.com/document/path/93378">https://developer.work.weixin.qq.com/document/path/93378</a>
   * <pre>
   * @param userId 需要录入的用户id
   * @param userFace 需要录入的人脸图片数据，需要将图片数据base64处理后填入，对已录入的人脸会进行更新处理
   * @throws WxErrorException the wx error exception
   */
  void addCheckInUserFace(String userId, String userFace) throws WxErrorException;
}
