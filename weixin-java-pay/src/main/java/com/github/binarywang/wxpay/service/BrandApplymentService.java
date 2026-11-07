package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.brandapplyment.BrandApplymentCancelRequest;
import com.github.binarywang.wxpay.bean.brandapplyment.BrandApplymentQueryResult;
import com.github.binarywang.wxpay.bean.brandapplyment.BrandApplymentRequest;
import com.github.binarywang.wxpay.bean.brandapplyment.BrandApplymentResult;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * 连锁品牌入驻申请 API。
 * <a href="https://pay.weixin.qq.com/doc/v3/partner/4016985537">官方接入文档</a>
 */
public interface BrandApplymentService {
  /**
   * 提交入驻申请；SDK 自动加密管理员姓名和证件号，不修改传入资料。
   *
   * @param request 品牌入驻资料，敏感字段填写明文，图片填写预先上传的 MediaID
   * @return 微信支付申请单号及业务申请编号
   * @throws WxPayException 接口调用或敏感信息加密失败
   */
  BrandApplymentResult createApplyment(BrandApplymentRequest request) throws WxPayException;

  /**
   * 撤销尚未完成开号的入驻申请。
   *
   * @param request 业务申请编号与微信支付申请单号至少填写一个
   * @return 被撤销的申请单号及业务申请编号
   * @throws WxPayException 接口调用失败
   */
  BrandApplymentResult cancelApplyment(BrandApplymentCancelRequest request) throws WxPayException;

  /**
   * 根据业务申请编号查询申请状态。
   *
   * @param businessCode 服务商自定义的业务申请编号，仅包含数字、字母或下划线
   * @return 申请状态及管理员授权链接
   * @throws WxPayException 接口调用失败
   */
  BrandApplymentQueryResult queryApplymentByBusinessCode(String businessCode) throws WxPayException;

  /**
   * 根据微信支付申请单号查询申请状态。
   *
   * @param applymentId 微信支付分配的申请单号
   * @return 申请状态及管理员授权链接
   * @throws WxPayException 接口调用失败
   */
  BrandApplymentQueryResult queryApplymentByApplymentId(String applymentId) throws WxPayException;
}
