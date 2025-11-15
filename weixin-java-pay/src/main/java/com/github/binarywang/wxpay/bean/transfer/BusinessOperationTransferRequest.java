package com.github.binarywang.wxpay.bean.transfer;

import com.github.binarywang.wxpay.v3.SpecEncrypt;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 运营工具-商家转账请求参数
 * 
 * @author WxJava Team
 * @see <a href="https://pay.weixin.qq.com/doc/v3/merchant/4012711988">运营工具-商家转账API</a>
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class BusinessOperationTransferRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 直连商户的appid
   * 必须
   */
  @SerializedName("appid")
  private String appid;

  /**
   * 商户系统内部的商家单号
   * 必须，要求此参数只能由数字、大小写字母组成，在商户系统内部唯一
   */
  @SerializedName("out_bill_no")
  private String outBillNo;

  /**
   * 运营工具转账场景ID
   * 必须，用于标识运营工具转账的具体业务场景
   */
  @SerializedName("operation_scene_id")
  private String operationSceneId;

  /**
   * 用户在直连商户应用下的用户标示
   * 必须
   */
  @SerializedName("openid")
  private String openid;

  /**
   * 收款用户姓名
   * 可选，传入则校验收款用户姓名
   * 使用RSA加密，使用OAEP填充方式
   */
  @SpecEncrypt
  @SerializedName("user_name")
  private String userName;

  /**
   * 转账金额
   * 必须，单位为"分"
   */
  @SerializedName("transfer_amount")
  private Integer transferAmount;

  /**
   * 转账备注
   * 必须，会在转账成功消息和转账详情页向用户展示
   */
  @SerializedName("transfer_remark")
  private String transferRemark;

  /**
   * 用户收款感知
   * 可选，用于在转账成功消息中向用户展示特定内容
   */
  @SerializedName("user_recv_perception")
  private String userRecvPerception;

  /**
   * 异步接收微信支付转账结果通知的回调地址
   * 可选，通知URL必须为外网可以正常访问的地址，不能携带查询参数
   */
  @SerializedName("notify_url")
  private String notifyUrl;
}