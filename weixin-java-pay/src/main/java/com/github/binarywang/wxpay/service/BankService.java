package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.bank.*;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * <pre>
 * 微信支付-银行组件
 * </pre>
 *
 * @author zhongjun
 **/
public interface BankService {
  /**
   * <pre>
   *
   * 获取对私银行卡号开户银行
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/capital/capitallhh/banks/search-banks-by-bank-account">https://api.mch.weixin.qq.com/v3/capital/capitallhh/banks/search-banks-by-bank-account</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter11_2_1.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter11_2_1.shtml</a>
   * </pre>
   *
   * @param accountNumber 银行卡号
   * @return BankAccountResult 对私银行卡号开户银行信息
   * @throws WxPayException .
   */
  BankAccountResult searchBanksByBankAccount(String accountNumber) throws WxPayException;

  /**
   * <pre>
   *
   * 查询支持个人业务的银行列表
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/capital/capitallhh/banks/personal-banking">https://api.mch.weixin.qq.com/v3/capital/capitallhh/banks/personal-banking</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter11_2_2.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter11_2_2.shtml</a>
   * </pre>
   *
   * @param offset 本次查询偏移量
   * @param limit  本次请求最大查询条数,最大值为200
   * @return PersonalBankingResult 支持个人业务的银行列表信息
   * @throws WxPayException .
   */
  BankingResult personalBanking(Integer offset, Integer limit) throws WxPayException;

  /**
   * <pre>
   *
   * 支持对公业务的银行列表
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/capital/capitallhh/banks/corporate-banking">https://api.mch.weixin.qq.com/v3/capital/capitallhh/banks/corporate-banking</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter11_2_3.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter11_2_3.shtml</a>
   * </pre>
   *
   * @param offset 本次查询偏移量
   * @param limit  本次请求最大查询条数,最大值为200
   * @return BankingResult 支持对公业务的银行列表信息
   * @throws WxPayException .
   */
  BankingResult corporateBanking(Integer offset, Integer limit) throws WxPayException;

  /**
   * <pre>
   *
   * 查询省份列表API
   * 通过本接口获取省份列表数据（不包含中国港澳台地区），可用于省份下的城市数据查询
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/capital/capitallhh/areas/provinces">https://api.mch.weixin.qq.com/v3/capital/capitallhh/areas/provinces</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter11_2_4.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter11_2_4.shtml</a>
   * </pre>
   *
   * @return ProvincesResult 省份列表信息
   * @throws WxPayException .
   */
  ProvincesResult areasProvinces() throws WxPayException;

  /**
   * <pre>
   *
   * 查询城市列表API
   * 通过本接口根据省份编码获取省份下的城市列表信息，不包含中国港澳台地区城市信息，可用于支行数据过滤查询
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/capital/capitallhh/areas/provinces/{province_code}/cities">https://api.mch.weixin.qq.com/v3/capital/capitallhh/areas/provinces/{province_code}/cities</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter11_2_5.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter11_2_5.shtml</a>
   * </pre>
   *
   * @return CitiesResult 城市列表信息
   * @throws WxPayException .
   */
  CitiesResult areasCities(Integer provinceCode) throws WxPayException;

  /**
   * <pre>
   *
   * 查询支行列表API
   * 本接口可以用于根据银行别名编码（仅支持需要填写支行的银行别名编码）和城市编码过滤查询支行列表数据
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/capital/capitallhh/banks/{bank_alias_code}/branches">https://api.mch.weixin.qq.com/v3/capital/capitallhh/banks/{bank_alias_code}/branches</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter11_2_5.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter11_2_5.shtml</a>
   * </pre>
   *
   * @param bankAliasCode 银行别名的编码，查询支行接口仅支持需要填写支行的银行别名编码。示例值：1000006247
   * @param cityCode 城市编码，唯一标识一座城市，用于结合银行别名编码查询支行列表。示例值：536
   * @param offset 非负整数，表示该次请求资源的起始位置，从0开始计数。调用方选填，默认为0。offset为20，limit为100时，查询第21-120条数据
   * @param limit  非0非负的整数，该次请求可返回的最大资源条数。示例值：200
   * @return BankBranchesResult 城市列表信息
   * @throws WxPayException .
   */
  BankBranchesResult bankBranches(String bankAliasCode, Integer cityCode, Integer offset, Integer limit) throws WxPayException;
}
