package com.github.binarywang.wxpay.bean.payscore;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 授权/解除授权服务回调通知结果
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter4_4.shtml
 * </pre>
 */
@Data
@NoArgsConstructor
public class UserAuthorizationStatusNotifyResult implements Serializable {

  /**
   * 源数据
   */
  private PayScoreNotifyData rawData;

  /**
   * <pre>
   * 字段名：公众账号ID
   * 变量名：appid
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  调用授权服务接口提交的公众账号ID。
   * 示例值：wxd678efh567hg6787
   * </pre>
   */
  @SerializedName(value = "appid")
  private String appid;
  
  /**
   * <pre>
   * 字段名：商户号
   * 变量名：mchid
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  调用授权服务接口提交的商户号。
   * 示例值：1230000109
   * </pre>
   */
  @SerializedName(value = "mchid")
  private String mchid;
  
  /**
   * <pre>
   * 字段名：商户签约单号
   * 变量名：out_request_no
   * 是否必填：否
   * 类型：	string[1,64]
   * 描述：
   *  调用授权服务接口提交的商户请求唯一标识（新签约的用户，且在授权签约中上传了该字段，则在解约授权回调通知中有返回）。
   * 示例值：1234323JKHDFE1243252
   * </pre>
   */
  @SerializedName(value = "out_request_no")
  private String outRequestNo;
  
  /**
   * <pre>
   * 字段名：服务ID
   * 变量名：service_id
   * 是否必填：是
   * 类型：	string[1,32]
   * 描述：
   *  调用授权服务接口提交的服务ID。
   * 示例值：1234323JKHDFE1243252
   * </pre>
   */
  @SerializedName(value = "service_id")
  private String serviceId;
  
  /**
   * <pre>
   * 字段名：用户标识
   * 变量名：openid
   * 是否必填：是
   * 类型：	string[1,128]
   * 描述：
   *  微信用户在商户对应appid下的唯一标识。
   * 示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
   * </pre>
   */
  @SerializedName(value = "openid")
  private String openid;
  
  /**
   * <pre>
   * 字段名：回调状态
   * 变量名：user_service_status
   * 是否必填：否
   * 类型：	string[1,32]
   * 描述：
   *  1、USER_OPEN_SERVICE：授权成功 
   *  2、USER_CLOSE_SERVICE：解除授权成功
   * 示例值：USER_OPEN_SERVICE
   * </pre>
   */
  @SerializedName(value = "user_service_status")
  private String userServiceStatus;
  
  /**
   * <pre>
   * 字段名：服务授权/解除授权时间
   * 变量名：openorclose_time
   * 是否必填：否
   * 类型：	string[1,32]
   * 描述：
   *  服务授权/解除授权成功时间。
   * 示例值：20180225112233
   * </pre>
   */
  @SerializedName(value = "openorclose_time")
  private String openOrCloseTime;
  
  /**
   * <pre>
   * 字段名：授权协议号
   * 变量名：authorization_code
   * 是否必填：否
   * 类型：	string[1,32]
   * 描述：
   *  授权协议号，预授权时返回，非预授权不返回
   * 示例值：1275342195190894594
   * </pre>
   */
  @SerializedName(value = "authorization_code")
  private String authorizationCode;

}
