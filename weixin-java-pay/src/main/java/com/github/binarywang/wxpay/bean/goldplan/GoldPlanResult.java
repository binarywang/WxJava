package com.github.binarywang.wxpay.bean.goldplan;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 点金计划操作结果
 *
 * @author zhangyl
 */
@Data
public class GoldPlanResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 特约商户号
   */
  @SerializedName("sub_mchid")
  private String subMchId;
}
