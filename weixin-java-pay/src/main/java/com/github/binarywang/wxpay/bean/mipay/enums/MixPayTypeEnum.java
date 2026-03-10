package com.github.binarywang.wxpay.bean.mipay.enums;

import com.google.gson.annotations.SerializedName;

/**
 * 混合支付类型枚举
 * <p>
 * 描述医保自费混合支付的类型
 * 文档地址：https://pay.weixin.qq.com/doc/v3/partner/4012503131
 *
 * @author xgl
 * @date 2025/12/20 09:21
 */
public enum MixPayTypeEnum {

  /**
   * 未知的混合支付类型，会被拦截。
   */
  @SerializedName("UNKNOWN_MIX_PAY_TYPE")
  UNKNOWN_MIX_PAY_TYPE,

  /**
   * 只向微信支付下单，没有向医保局下单。包括没有向医保局上传费用明细、预结算。
   */
  @SerializedName("CASH_ONLY")
  CASH_ONLY,

  /**
   * 只向医保局下单，没有向微信支付下单。如果医保局分账结果中有自费部份，但由于有减免抵扣，没有向微信支付下单，也是纯医保。
   */
  @SerializedName("INSURANCE_ONLY")
  INSURANCE_ONLY,

  /**
   * 向医保局下单，也向微信支付下单。如果医保预结算全部需自费，也属于混合类型。
   */
  @SerializedName("CASH_AND_INSURANCE")
  CASH_AND_INSURANCE


}
