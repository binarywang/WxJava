package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.applyment.*;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * 特约商户进件
 * 产品介绍：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/tool/applyment4sub/chapter1_1.shtml
 *
 * @author zhouyongshen
 */
public interface Applyment4SubService {
  /**
   * 提交申请单API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/tool/applyment4sub/chapter3_1.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/applyment4sub/applyment/
   *
   * @param request 请求对象
   * @return WxPayApplymentCreateResult 响应结果
   * @throws WxPayException the wx pay exception
   */
  WxPayApplymentCreateResult createApply(WxPayApplyment4SubCreateRequest request) throws WxPayException;

  /**
   * 通过业务申请编号查询申请状态
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/tool/applyment4sub/chapter3_2.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/applyment4sub/applyment/business_code/{business_code}
   *
   * @param businessCode 业务申请编号
   *  1、只能由数字、字母或下划线组成，建议前缀为服务商商户号。
   *  2、服务商自定义的唯一编号。
   *  3、每个编号对应一个申请单，每个申请单审核通过后生成一个微信支付商户号。
   *  4、若申请单被驳回，可填写相同的“业务申请编号”，即可覆盖修改原申请单信息。
   *  示例值：1900013511_10000
   * @return the applyment state query result
   * @throws WxPayException the wx pay exception
   */
  ApplymentStateQueryResult queryApplyStatusByBusinessCode(String businessCode) throws WxPayException;

  /**
   * 通过申请单号查询申请状态
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/tool/applyment4sub/chapter3_2.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/applyment4sub/applyment/applyment_id/{applyment_id}
   *
   * @param applymentId 微信支付分的申请单号。示例值：2000001234567890
   * @return the applyment state query result
   * @throws WxPayException the wx pay exception
   */
  ApplymentStateQueryResult queryApplyStatusByApplymentId(String applymentId) throws WxPayException;

  /**
   * 根据特约子商户ID查询结算账户
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter11_1_4.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/apply4sub/sub_merchants/{sub_mchid}/settlement
   *
   * @param subMchid 本服务商进件、已签约的特约商户号。
   * @return the settlement info result
   * @throws WxPayException the wx pay exception
   */
  SettlementInfoResult querySettlementBySubMchid(String subMchid) throws WxPayException;

  /**
   * 修改结算帐号
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter11_1_3.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/apply4sub/sub_merchants/{sub_mchid}/modify-settlement
   *
   * @param subMchid 特约商户号
   * @param request  修改结算账户请求对象信息
   * @throws WxPayException the wx pay exception
   * @return
   */
  String modifySettlement(String subMchid, ModifySettlementRequest request) throws WxPayException;

  /**
   * 查询结算账户修改申请状态
   *
   * @param subMchid      特约商户号
   * @param applicationNo 修改结算账户申请单号
   *
   * @return {@link SettlementModifyStateQueryResult}
   * @throws WxPayException
   * @apiNote <a href="https://pay.weixin.qq.com/docs/partner/apis/modify-settlement/sub-merchants/get-application.html">查询结算账户修改申请状态</a>
   *
   * <p>接口链接：https://api.mch.weixin.qq.com/v3/apply4sub/sub_merchants/{sub_mchid}/application/{applicationNo} </p>
   */
  SettlementModifyStateQueryResult querySettlementModifyStatusByApplicationNo(String subMchid, String applicationNo) throws WxPayException;

}
