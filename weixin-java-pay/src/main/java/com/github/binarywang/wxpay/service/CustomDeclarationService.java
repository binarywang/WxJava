package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.customs.*;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * <pre>
 * 微信支付 支付报关 API.
 * Created by xifengzhu on 2022/05/05.
 * </pre>
 *
 * @author <a href="https://github.com/xifengzhu">xifengzhu</a>
 */
public interface CustomDeclarationService {

  /**
   * The constant DECLARATION_BASE_URL.
   */
  String DECLARATION_BASE_URL = "https://apihk.mch.weixin.qq.com/global/v3/customs";

  /**
   * <pre>
   * 报关API
   * 文档地址: <a href="https://pay.weixin.qq.com/wiki/doc/api/wxpay/ch/declarecustom_ch/chapter3_1.shtml">...</a>
   * </pre>
   *
   * @param request the request
   * @return 返回数据 declaration result
   * @throws WxPayException the wx pay exception
   */
  DeclarationResult declare(DeclarationRequest request) throws WxPayException;

  /**
   * <pre>
   * 报关查询API
   * 文档地址: <a href="https://pay.weixin.qq.com/wiki/doc/api/wxpay/ch/declarecustom_ch/chapter3_3.shtml">...</a>
   * </pre>
   *
   * @param request the request
   * @return 返回数据 declaration query result
   * @throws WxPayException the wx pay exception
   */
  DeclarationQueryResult query(DeclarationQueryRequest request) throws WxPayException;

  /**
   * <pre>
   * 身份信息校验API
   * 文档地址: <a href="https://pay.weixin.qq.com/wiki/doc/api/wxpay/ch/declarecustom_ch/chapter3_2.shtml">...</a>
   * </pre>
   *
   * @param request the request
   * @return 返回数据 verify certification result
   * @throws WxPayException the wx pay exception
   */
  VerifyCertificateResult verifyCertificate(VerifyCertificateRequest request) throws WxPayException;

  /**
   * <pre>
   * 报关信息修改API
   * 文档地址: <a href="https://pay.weixin.qq.com/wiki/doc/api/wxpay/ch/declarecustom_ch/chapter3_5.shtml">...</a>
   * </pre>
   *
   * @param request the request
   * @return 返回数据 declaration result
   * @throws WxPayException the wx pay exception
   */
  DeclarationResult modify(DeclarationRequest request) throws WxPayException;

  /**
   * <pre>
   * 报关重推API
   * 文档地址: <a href="https://pay.weixin.qq.com/wiki/doc/api/wxpay/ch/declarecustom_ch/chapter3_4.shtml">...</a>
   * </pre>
   *
   * @param request the request
   * @return 返回数据 redeclaration result
   * @throws WxPayException the wx pay exception
   */
  RedeclareResult redeclare(RedeclareRequest request) throws WxPayException;
}
