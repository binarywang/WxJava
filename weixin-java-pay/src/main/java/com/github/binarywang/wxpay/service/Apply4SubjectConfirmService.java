package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.applyconfirm.ApplySubjectConfirmCreateRequest;
import com.github.binarywang.wxpay.bean.applyconfirm.ApplySubjectConfirmCreateResult;
import com.github.binarywang.wxpay.bean.applyconfirm.ApplySubjectConfirmMerchantStateQueryResult;
import com.github.binarywang.wxpay.bean.applyconfirm.ApplySubjectConfirmStateQueryResult;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * <pre>
 * 商户开户意愿确认
 * 产品文档：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/open/pay/chapter6_1_1.shtml">商户开户意愿确认流程</a>
 * </pre>
 *
 * @author <a href="https://github.com/wslongchen">Mr.Pan</a>
 */
public interface Apply4SubjectConfirmService {

  /**
   * <pre>
   * 提交申请单
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter10_1_1.shtml">间连商户开户意愿确认（提交申请单）</a>
   * </pre>
   *
   * @param request 申请请求参数
   * @return 审核结果
   * @throws WxPayException 异常
   */
  ApplySubjectConfirmCreateResult applyment(ApplySubjectConfirmCreateRequest request) throws WxPayException;

  /**
   *
   * <pre>
   * 查询申请单审核结果
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter10_1_3.shtml">查询申请单审核结果</a>
   * </pre>
   *
   * @param businessCode 业务申请编号
   * @return 审核结果
   * @throws WxPayException 异常
   */
  ApplySubjectConfirmStateQueryResult queryApplyStatusByBusinessCode(String businessCode) throws WxPayException;

  /**
   *
   * <pre>
   * 查询申请单审核结果
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter10_1_3.shtml">查询申请单审核结果</a>
   * </pre>
   *
   * @param applymentId 申请编号
   * @return 审核结果
   * @throws WxPayException 异常
   */
  ApplySubjectConfirmStateQueryResult queryApplyStatusByApplymentId(String applymentId) throws WxPayException;


  /**
   *
   * <pre>
   * 获取商户开户意愿确认状态
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter10_1_4.shtml">获取商户开户意愿确认状态API</a>
   * </pre>
   *
   * @param subMchId 微信支付分配的特约商户的唯一标识。
   * @return 确认状态结果
   * @throws WxPayException 异常
   */
  ApplySubjectConfirmMerchantStateQueryResult queryMerchantApplyStatusByMchId(String subMchId) throws WxPayException;


  /**
   *
   * <pre>
   * 撤销申请单
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter10_1_2.shtml">撤销申请单API</a>
   * </pre>
   *
   * @param businessCode 业务申请编号
   * @throws WxPayException 异常
   */
  void cancelApplyByBusinessCode(String businessCode) throws WxPayException;

  /**
   *
   * <pre>
   * 撤销申请单
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter10_1_2.shtml">撤销申请单API</a>
   * </pre>
   *
   * @param applymentId 申请编号
   * @throws WxPayException 异常
   */
  void cancelApplyByApplymentId(String applymentId) throws WxPayException;


}
