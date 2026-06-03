package com.github.binarywang.wxpay.bean.notify;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商户平台处置记录回调通知 <a href="https://pay.weixin.qq.com/doc/v3/partner/4012064844">产品介绍</a>
 *
 * @author zhangyl
 */
@Data
@NoArgsConstructor
public class MerchantViolationNotifyResult implements Serializable,
  WxPayBaseNotifyV3Result<MerchantViolationNotifyResult.DecryptNotifyResult> {
  private static final long serialVersionUID = 1L;
  /**
   * 源数据
   */
  private OriginNotifyResponse rawData;
  /**
   * 解密后的数据
   */
  private DecryptNotifyResult result;

  @Data
  @NoArgsConstructor
  public static class DecryptNotifyResult implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 处置记录对应的商户号
     */
    @SerializedName("sub_mchid")
    private String subMchId;
    /**
     * 子商户公司名称
     */
    @SerializedName("company_name")
    private String companyName;
    /**
     * 微信支付对违约商户处理通知的唯一标识，可用于去重
     */
    @SerializedName("record_id")
    private String recordId;
    /**
     * 微信支付对违约商户的具体处罚方案，可根据具体的处罚方案指引商户登录商户平台/商家助手小程序进行申诉/相关操作，使用时请留意该值为处罚方法的文本内容，并非枚举值。
     */
    @SerializedName("punish_plan")
    private String punishPlan;
    /**
     * 微信支付对违约商户的处置时间
     */
    @SerializedName("punish_time")
    private String punishTime;
    /**
     * 微信支付对违约商户处罚方案的详细描述信息，补充处罚方案的相关影响。
     */
    @SerializedName("punish_description")
    private String punishDescription;
    /**
     * 微信支付对违约商户定义的风险类型
     */
    @SerializedName("risk_type")
    private String riskType;
    /**
     * 微信支付对违约商户定义的风险类型枚举值对应的中文描述
     */
    @SerializedName("risk_description")
    private String riskDescription;
  }
}
