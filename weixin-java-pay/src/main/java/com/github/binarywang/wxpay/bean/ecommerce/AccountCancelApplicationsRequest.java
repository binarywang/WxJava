package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 提交注销申请单
 * <pre>
 *   https://pay.weixin.qq.com/docs/partner/apis/ecommerce-cancel/cancel-applications/create-cancel-application.html
 * </pre>
 */
@Data
@NoArgsConstructor
public class AccountCancelApplicationsRequest implements Serializable {

  /**
   * 【申请注销的二级商户号】 电商平台二级商户号，由微信支付生成并下发
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;

  /**
   * 【商户注销申请单号】 商户注销申请单号，由商户自定义生成，要求在服务商维度下是唯一的，必须仅包含大小写字母与数字
   */
  @SerializedName(value = "out_apply_no")
  private String outApplyNo;

  /**
   * 【注销申请材料】 注销申请材料，详见文档：注销申请材料
   */
  @SerializedName(value = "application_info")
  private List<CancelApplicationInfo> applicationInfo;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class CancelApplicationInfo implements Serializable {

    /**
     *【注销申请材料类型】 注销申请材料类型，详见文档：注销申请材料
     * 可选取值：
     * SP_MERCHANT_APPLICATION: 此枚举值已废弃，请使用新字段 SP_CANCEL_ACCOUNT_APPLICATION 以及新版本材料
     * SUB_MERCHANT_APPLICATION: 此枚举值已废弃，请使用新字段 SUB_CANCEL_ACCOUNT_APPLICATION 以及新版本材料
     * MISSING_OFFICIAL_SEAL_LETTER: 此材料已废弃，无需上传
     * SP_CANCEL_ACCOUNT_APPLICATION: 电商服务商注销电商子申请书，请下载模板打印纸质版、填写盖章后拍照。模板文档详见：微信支付商户号注销申请书-服务商（纸质版）
     * SUB_CANCEL_ACCOUNT_APPLICATION: 电商服务商子商户注销申请书，详见文档：微信支付商户号注销申请书-电商平台子商户适用（纸质版）
     */
    @SerializedName("application_type")
    private String applicationType;

    /**
     * 【注销申请材料照片ID】 注销申请材料照片ID，请填写通过上传图片接口预先上传图片生成好的media_id
     */
    @SerializedName("application_media_id")
    private String applicationMediaId;
  }
}
