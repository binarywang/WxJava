package com.github.binarywang.wxpay.bean.applyconfirm;

import com.github.binarywang.wxpay.bean.applyconfirm.enums.AuthorizeStateEnum;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *获取商户开户意愿确认状态返回对象信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ApplySubjectConfirmMerchantStateQueryResult implements Serializable {
  private static final long serialVersionUID = 3842134912775708112L;

  /**
   * 授权状态
   */
  @SerializedName("authorize_state")
  private AuthorizeStateEnum applymentState;
}
