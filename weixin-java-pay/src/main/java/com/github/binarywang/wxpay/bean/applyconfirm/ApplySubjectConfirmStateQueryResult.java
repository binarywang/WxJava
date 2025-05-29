package com.github.binarywang.wxpay.bean.applyconfirm;

import com.github.binarywang.wxpay.bean.applyconfirm.enums.ApplySubjectStateEnum;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 查询申请单状态返回对象信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ApplySubjectConfirmStateQueryResult implements Serializable {
  private static final long serialVersionUID = 3842134912775708112L;

  /**
   * 申请单状态
   */
  @SerializedName("applyment_state")
  private ApplySubjectStateEnum applymentState;
  /**
   * 二维码图片
   */
  @SerializedName("qrcode_data")
  private String qrcodeData;
  /**
   * 驳回参数
   */
  @SerializedName("reject_param")
  private String rejectParam;
  /**
   * 驳回原因
   */
  @SerializedName("reject_reason")
  private String rejectReason;
}
