package me.chanjar.weixin.cp.api;

import lombok.NonNull;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.doc.*;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * 企业微信文档相关接口.
 * <a href="https://developer.work.weixin.qq.com/document/path/97392">文档</a>
 *
 * @author Hugo
 */
public interface WxCpOaWeDocService {

  /**
   * 新建文档
   * 该接口用于新建文档和表格，新建收集表可前往 收集表管理 查看。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/create_doc?access_token=ACCESS_TOKEN
   *
   * @param request 新建文档对应请求参数
   * @return url：新建文档的访问链接，docid：新建文档的docid
   * @throws WxErrorException the wx error exception
   */
  WxCpDocCreateData docCreate(@NonNull WxCpDocCreateRequest request) throws WxErrorException;

  /**
   * 重命名文档/收集表
   * 该接口用于对指定文档/收集表进行重命名。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/rename_doc?access_token=ACCESS_TOKEN
   *
   * @param request 重命名文档/收集表
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp docRename(@NonNull WxCpDocRenameRequest request) throws WxErrorException;

  /**
   * 删除文档/收集表
   * 该接口用于删除指定文档/收集表。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/del_doc?access_token=ACCESS_TOKEN
   *
   * @param docId  文档docid（docid、formid只能填其中一个）
   * @param formId 收集表id（docid、formid只能填其中一个）
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp docDelete(String docId, String formId) throws WxErrorException;

  /**
   * 获取文档基础信息
   * 该接口用于获取指定文档的基础信息。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/get_doc_base_info?access_token=ACCESS_TOKEN
   *
   * @param docId 文档docid
   * @return wx cp doc info
   * @throws WxErrorException the wx error exception
   */
  WxCpDocInfo docInfo(@NonNull String docId) throws WxErrorException;

  /**
   * 分享文档
   * 该接口用于获取文档的分享链接。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/doc_share?access_token=ACCESS_TOKEN
   *
   * @param docId 文档docid
   * @return url 文档分享链接
   * @throws WxErrorException the wx error exception
   */
  WxCpDocShare docShare(@NonNull String docId) throws WxErrorException;

  /**
   * 分享文档/收集表
   * 该接口用于获取文档或收集表的分享链接。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/doc_share?access_token=ACCESS_TOKEN
   *
   * @param request 分享请求，docid/formid 二选一
   * @return url 文档分享链接
   * @throws WxErrorException the wx error exception
   */
  WxCpDocShare docShare(@NonNull WxCpDocShareRequest request) throws WxErrorException;

  /**
   * 获取文档权限信息
   * 该接口用于获取文档、表格、智能表格的查看规则、文档通知范围及权限、安全设置信息。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/doc_get_auth?access_token=ACCESS_TOKEN
   *
   * @param docId 文档docid
   * @return 文档权限信息
   * @throws WxErrorException the wx error exception
   */
  WxCpDocAuthInfo docGetAuth(@NonNull String docId) throws WxErrorException;

  /**
   * 修改文档查看规则
   * 该接口用于修改文档、表格、智能表格查看规则。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/mod_doc_join_rule?access_token=ACCESS_TOKEN
   *
   * @param request 修改文档查看规则请求
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp docModifyJoinRule(@NonNull WxCpDocModifyJoinRuleRequest request) throws WxErrorException;

  /**
   * 修改文档通知范围及权限
   * 该接口用于修改文档、表格、智能表格通知范围列表。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/mod_doc_member?access_token=ACCESS_TOKEN
   *
   * @param request 修改文档通知范围及权限请求
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp docModifyMember(@NonNull WxCpDocModifyMemberRequest request) throws WxErrorException;

  /**
   * 修改文档安全设置
   * 该接口用于修改文档、表格、智能表格的安全设置。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/mod_doc_safty_setting?access_token=ACCESS_TOKEN
   *
   * @param request 修改文档安全设置请求
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp docModifySafetySetting(
    @NonNull WxCpDocModifySafetySettingRequest request
  ) throws WxErrorException;

  /**
   * @deprecated Use {@link #docModifySafetySetting(WxCpDocModifySafetySettingRequest)} instead.
   */
  @Deprecated
  default WxCpBaseResp docModifySaftySetting(
    @NonNull WxCpDocModifySaftySettingRequest request
  ) throws WxErrorException {
    WxCpDocModifySafetySettingRequest newReq =
      WxCpDocModifySafetySettingRequest.builder()
        .docId(request.getDocId())
        .enableReadonlyCopy(request.getEnableReadonlyCopy())
        .watermark(request.getWatermark())
        .build();
    return docModifySafetySetting(newReq);
  }

  /**
   * 编辑表格内容
   * 该接口可以对一个在线表格批量执行多个更新操作
   * <p>
   * 注意：
   * 1.批量更新请求中的各个操作会逐个按顺序执行，直到全部执行完成则请求返回，或者其中一个操作报错则不再继续执行后续的操作
   * 2.每一个更新操作在执行之前都会做请求校验（包括权限校验、参数校验等等），如果校验未通过则该更新操作会报错并返回，不再执行后续操作
   * 3.单次批量更新请求的操作数量 <= 5
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/spreadsheet/batch_update?access_token=ACCESS_TOKEN
   *
   * @param request 编辑表格内容请求参数
   * @return 编辑表格内容批量更新的响应结果
   * @throws WxErrorException the wx error exception
   */
  WxCpDocSheetBatchUpdateResponse docBatchUpdate(@NonNull WxCpDocSheetBatchUpdateRequest request) throws WxErrorException;

  /**
   * 获取表格行列信息
   * 该接口用于获取在线表格的工作表、行数、列数等。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/spreadsheet/get_sheet_properties?access_token=ACCESS_TOKEN
   *
   * @param docId 	在线表格的docid
   * @return 返回表格行列信息
   * @throws WxErrorException
   */
  WxCpDocSheetProperties getSheetProperties(@NonNull String docId) throws WxErrorException;


  /**
   * 本接口用于获取指定范围内的在线表格信息，单次查询的范围大小需满足以下限制：
   * <p>
   * 查询范围行数 <=1000
   * 查询范围列数 <=200
   * 范围内的总单元格数量 <=10000
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/spreadsheet/get_sheet_range_data?access_token=ACCESS_TOKEN
   *
   * @param request 获取指定范围内的在线表格信息请求参数
   * @return 返回指定范围内的在线表格信息
   * @throws WxErrorException
   */
  WxCpDocSheetData getSheetRangeData(@NonNull WxCpDocSheetGetDataRequest request) throws WxErrorException;

  /**
   * 获取文档数据
   * 该接口用于获取在线文档内容数据。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/get_doc_data?access_token=ACCESS_TOKEN
   *
   * @param request 获取文档数据请求参数
   * @return 文档内容数据
   * @throws WxErrorException the wx error exception
   */
  WxCpDocData docGetData(@NonNull WxCpDocGetDataRequest request) throws WxErrorException;

  /**
   * 编辑文档内容
   * 该接口用于编辑在线文档内容。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/mod_doc?access_token=ACCESS_TOKEN
   *
   * @param request 编辑文档内容请求参数
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp docModify(@NonNull WxCpDocModifyRequest request) throws WxErrorException;

  /**
   * 上传文档图片
   * 该接口用于上传在线文档编辑时使用的图片资源。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/upload_doc_image?access_token=ACCESS_TOKEN
   *
   * @param file 图片文件
   * @return 上传结果
   * @throws WxErrorException the wx error exception
   */
  WxCpDocImageUploadResult docUploadImage(@NonNull File file) throws WxErrorException;

  /**
   * 添加文档高级功能账号
   * 该接口用于为在线文档添加高级功能账号。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/add_admin?access_token=ACCESS_TOKEN
   *
   * @param request 文档高级功能账号请求
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp docAddAdmin(@NonNull WxCpDocAdminRequest request) throws WxErrorException;

  /**
   * 删除文档高级功能账号
   * 该接口用于删除在线文档的高级功能账号。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/del_admin?access_token=ACCESS_TOKEN
   *
   * @param request 文档高级功能账号请求
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp docDeleteAdmin(@NonNull WxCpDocAdminRequest request) throws WxErrorException;

  /**
   * 获取文档高级功能账号列表
   * 该接口用于获取在线文档的高级功能账号列表。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/get_admin_list?access_token=ACCESS_TOKEN
   *
   * @param docId 文档 docid
   * @return 文档高级功能账号列表
   * @throws WxErrorException the wx error exception
   */
  WxCpDocAdminListResult docGetAdminList(@NonNull String docId) throws WxErrorException;

  /**
   * 获取智能表格内容权限
   * 该接口用于获取智能表格字段/记录等内容权限信息。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/smartsheet/get_sheet_auth?access_token=ACCESS_TOKEN
   *
   * @param request 智能表格内容权限请求
   * @return 智能表格内容权限
   * @throws WxErrorException the wx error exception
   */
  WxCpDocSmartSheetAuth smartSheetGetAuth(@NonNull WxCpDocSmartSheetAuthRequest request) throws WxErrorException;

  /**
   * 修改智能表格内容权限
   * 该接口用于修改智能表格字段/记录等内容权限信息。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/smartsheet/mod_sheet_auth?access_token=ACCESS_TOKEN
   *
   * @param request 修改智能表格内容权限请求
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp smartSheetModifyAuth(@NonNull WxCpDocSmartSheetModifyAuthRequest request) throws WxErrorException;

  /**
   * 获取智能表格工作表信息.
   *
   * @param request 智能表格请求
   * @return 智能表格工作表信息
   * @throws WxErrorException the wx error exception
   */
  WxCpDocSmartSheetResult smartSheetGetSheet(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException;

  /**
   * 新增智能表格工作表.
   *
   * @param request 智能表格请求
   * @return 智能表格工作表信息
   * @throws WxErrorException the wx error exception
   */
  WxCpDocSmartSheetResult smartSheetAddSheet(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException;

  /**
   * 删除智能表格工作表.
   *
   * @param request 智能表格请求
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp smartSheetDeleteSheet(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException;

  /**
   * 更新智能表格工作表.
   *
   * @param request 智能表格请求
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp smartSheetUpdateSheet(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException;

  /**
   * 获取智能表格视图.
   *
   * @param request 智能表格请求
   * @return 智能表格视图
   * @throws WxErrorException the wx error exception
   */
  WxCpDocSmartSheetResult smartSheetGetViews(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException;

  /**
   * 新增智能表格视图.
   *
   * @param request 智能表格请求
   * @return 智能表格视图
   * @throws WxErrorException the wx error exception
   */
  WxCpDocSmartSheetResult smartSheetAddView(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException;

  /**
   * 删除智能表格视图.
   *
   * @param request 智能表格请求
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp smartSheetDeleteViews(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException;

  /**
   * 更新智能表格视图.
   *
   * @param request 智能表格请求
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp smartSheetUpdateView(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException;

  /**
   * 获取智能表格字段.
   *
   * @param request 智能表格请求
   * @return 智能表格字段
   * @throws WxErrorException the wx error exception
   */
  WxCpDocSmartSheetResult smartSheetGetFields(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException;

  /**
   * 新增智能表格字段.
   *
   * @param request 智能表格请求
   * @return 智能表格字段
   * @throws WxErrorException the wx error exception
   */
  WxCpDocSmartSheetResult smartSheetAddFields(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException;

  /**
   * 删除智能表格字段.
   *
   * @param request 智能表格请求
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp smartSheetDeleteFields(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException;

  /**
   * 更新智能表格字段.
   *
   * @param request 智能表格请求
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp smartSheetUpdateFields(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException;

  /**
   * 获取智能表格记录.
   *
   * @param request 智能表格请求
   * @return 智能表格记录
   * @throws WxErrorException the wx error exception
   */
  WxCpDocSmartSheetResult smartSheetGetRecords(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException;

  /**
   * 新增智能表格记录.
   *
   * @param request 智能表格请求
   * @return 智能表格记录
   * @throws WxErrorException the wx error exception
   */
  WxCpDocSmartSheetResult smartSheetAddRecords(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException;

  /**
   * 删除智能表格记录.
   *
   * @param request 智能表格请求
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp smartSheetDeleteRecords(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException;

  /**
   * 更新智能表格记录.
   *
   * @param request 智能表格请求
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp smartSheetUpdateRecords(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException;

  /**
   * 创建收集表
   * 该接口用于创建收集表。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/create_collect?access_token=ACCESS_TOKEN
   *
   * @param request 创建收集表请求
   * @return 创建收集表结果
   * @throws WxErrorException the wx error exception
   */
  WxCpFormCreateResult formCreate(@NonNull WxCpFormCreateRequest request) throws WxErrorException;

  /**
   * 编辑收集表
   * 该接口用于编辑收集表。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/modify_collect?access_token=ACCESS_TOKEN
   *
   * @param request 编辑收集表请求
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp formModify(@NonNull WxCpFormModifyRequest request) throws WxErrorException;

  /**
   * 获取收集表信息
   * 该接口用于读取收集表的信息。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/get_form_info?access_token=ACCESS_TOKEN
   *
   * @param formId 收集表id
   * @return 收集表信息
   * @throws WxErrorException the wx error exception
   */
  WxCpFormInfoResult formInfo(@NonNull String formId) throws WxErrorException;

  /**
   * 获取收集表统计信息
   * 该接口用于获取收集表的统计信息、已回答成员列表和未回答成员列表。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/get_form_statistic?access_token=ACCESS_TOKEN
   *
   * @param requests 收集表统计请求数组
   * @return 收集表统计结果（包含 statistic_list）
   * @throws WxErrorException the wx error exception
   */
  WxCpFormStatisticResult formStatistic(@NonNull List<WxCpFormStatisticRequest> requests) throws WxErrorException;

  /**
   * 单个收集表统计查询的兼容封装，底层仍按官方数组请求发送。
   *
   * @param request 收集表统计请求
   * @return 收集表统计信息
   * @throws WxErrorException the wx error exception
   */
  default WxCpFormStatistic formStatistic(@NonNull WxCpFormStatisticRequest request) throws WxErrorException {
    WxCpFormStatisticResult result = formStatistic(Collections.singletonList(request));
    List<WxCpFormStatistic> list = result == null ? null : result.getStatisticList();
    return list == null || list.isEmpty() ? null : list.get(0);
  }

  /**
   * 获取收集表答案
   * 该接口用于读取收集表的答案。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedoc/get_form_answer?access_token=ACCESS_TOKEN
   *
   * @param request 收集表答案请求
   * @return 收集表答案
   * @throws WxErrorException the wx error exception
   */
  WxCpFormAnswer formAnswer(@NonNull WxCpFormAnswerRequest request) throws WxErrorException;

}
