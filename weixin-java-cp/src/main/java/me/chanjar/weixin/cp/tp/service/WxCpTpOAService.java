package me.chanjar.weixin.cp.tp.service;

import lombok.NonNull;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.oa.WxCpApprovalDetailResult;
import me.chanjar.weixin.cp.bean.oa.WxCpOaApplyEventRequest;
import me.chanjar.weixin.cp.bean.oa.WxCpOaApprovalTemplateResult;

/**
 * 企业微信OA相关接口.
 *
 * @author Element  created on  2019-04-06 10:52
 */
public interface WxCpTpOAService {

  /**
   * <pre>提交审批申请
   * 调试工具
   * 企业可通过审批应用或自建应用Secret调用本接口，代应用可见范围内员工在企业微信“审批应用”内提交指定类型的审批申请。
   *
   * 请求方式：POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/applyevent?access_token=ACCESS_TOKEN
   * 文档地址：https://work.weixin.qq.com/api/doc/90000/90135/91853
   * </pre>
   *
   * @param request 请求
   * @param corpId  the corp id
   * @return 表单提交成功后 ，返回的表单编号
   * @throws WxErrorException .
   */
  String apply(WxCpOaApplyEventRequest request, String corpId) throws WxErrorException;

  /**
   * 获取审批模板详情
   *
   * @param templateId 模板ID
   * @param corpId     the corp id
   * @return . template detail
   * @throws WxErrorException .
   */
  WxCpOaApprovalTemplateResult getTemplateDetail(@NonNull String templateId, String corpId) throws WxErrorException;

  /**
   * 复制/更新模板到企业
   *
   * @param openTemplateId 模板ID
   * @param corpId         the corp id
   * @return . string
   * @throws WxErrorException .
   */
  String copyTemplate(@NonNull String openTemplateId, String corpId) throws WxErrorException;

  /**
   * 获取审批申请详情
   *
   * @param spNo 审批单编号。
   * @param corpId the corp id
   * @return WxCpApprovaldetail approval detail
   * @throws WxErrorException .
   */
  WxCpApprovalDetailResult getApprovalDetail(@NonNull String spNo, String corpId) throws WxErrorException;
}
