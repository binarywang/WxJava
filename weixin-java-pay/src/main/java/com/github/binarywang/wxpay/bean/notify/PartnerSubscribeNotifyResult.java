package com.github.binarywang.wxpay.bean.notify;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 合作伙伴订阅通知 <a href="https://pay.weixin.qq.com/doc/v3/partner/4016022264">产品介绍</a>
 * <p>
 * 该类是订阅通知的通用结构，每个字段代表的含义和订阅类型有关。请依据文档自行判断使用。
 * </p>
 *
 * @author zhangyl
 */
@Data
@NoArgsConstructor
public class PartnerSubscribeNotifyResult implements Serializable,
  WxPayBaseNotifyV3Result<PartnerSubscribeNotifyResult.DecryptNotifyResult> {
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
     * 商户号
     */
    @SerializedName("merchant_code")
    private String merchantCode;
    /**
     * 商户全称
     */
    @SerializedName("merchant_company_name")
    private String merchantCompanyName;
    /**
     * 业务发生时间
     */
    @SerializedName("business_time")
    private String businessTime;
    /**
     * 业务单据
     */
    @SerializedName("business_code")
    private String businessCode;
    /**
     * 业务状态
     */
    @SerializedName("business_state")
    private String businessState;
    /**
     * 备注
     */
    @SerializedName("remark")
    private String remark;
  }
}
