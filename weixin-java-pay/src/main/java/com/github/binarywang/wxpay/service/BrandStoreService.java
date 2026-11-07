package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.brandstore.*;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * 品牌门店（服务商模式）。
 * <p>服务商须获得品牌授权，门店信息创建和更新可能进入审核流程。</p>
 * @see <a href="https://pay.weixin.qq.com/doc/v3/partner/4016628135">开发指引</a>
 */
public interface BrandStoreService {
  /**
   * 创建门店并获取微信支付门店ID。
   * @param request 品牌ID和门店信息，地址必填
   * @return 门店信息和审核状态
   * @throws WxPayException 微信支付接口调用失败
   */
  BrandStoreResult create(BrandStoreRequest request) throws WxPayException;

  /**
   * 查询品牌门店及审核状态。
   * @param brandId 品牌ID
   * @param storeId 微信支付门店ID
   * @return 门店信息、审核状态和收款商户
   * @throws WxPayException 微信支付接口调用失败
   */
  BrandStoreResult query(String brandId, String storeId) throws WxPayException;

  /**
   * 更新门店信息，生效门店的营业状态不受审核影响。
   * @param storeId 微信支付门店ID
   * @param request 品牌ID和待更新的门店信息
   * @return 更新后的门店信息和审核状态
   * @throws WxPayException 微信支付接口调用失败
   */
  BrandStoreResult update(String storeId, BrandStoreRequest request) throws WxPayException;

  /**
   * 删除已关闭的品牌门店。
   * @param brandId 品牌ID
   * @param storeId 微信支付门店ID
   * @throws WxPayException 微信支付接口调用失败
   */
  void delete(String brandId, String storeId) throws WxPayException;

  /**
   * 绑定品牌下已有的收款商户号。
   * @param storeId 微信支付门店ID
   * @param request 品牌ID、商户号和主体名称
   * @return 收款商户绑定状态
   * @throws WxPayException 微信支付接口调用失败
   */
  BrandStoreBindRecipientResult bindRecipient(String storeId, BrandStoreBindRecipientRequest request) throws WxPayException;

  /**
   * 解绑收款商户号。
   * @param storeId 微信支付门店ID
   * @param request 品牌ID和商户号
   * @return 解绑结果，失败时包含失败原因
   * @throws WxPayException 微信支付接口调用失败
   */
  BrandStoreUnbindRecipientResult unbindRecipient(String storeId, BrandStoreUnbindRecipientRequest request) throws WxPayException;
}
