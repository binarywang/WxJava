package com.github.binarywang.wxpay.bean.transfer;

import com.github.binarywang.wxpay.v3.SpecEncrypt;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

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
   * 转账场景ID
   * 必须，该笔转账使用的转账场景，可前往“商户平台-产品中心-商家转账”中申请。如：1000（现金营销），1006（企业报销）等
   */
  @SerializedName("transfer_scene_id")
  private String transferSceneId;

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

  /**
   * 转账场景报备信息
   * 必须，需按转账场景准确填写报备信息，参考 <a href="https://pay.weixin.qq.com/doc/v3/merchant/4013774588">转账场景报备信息字段说明</a>
   */
  @SerializedName("transfer_scene_report_infos")
  private List<TransferSceneReportInfo> transferSceneReportInfos;

  /**
   * 转账场景报备信息
   */
  @Data
  @Accessors(chain = true)
  public static class TransferSceneReportInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 信息类型
     * 必须，不能超过15个字符，商户所属转账场景下的信息类型，此字段内容为固定值，需严格按照 <a href="https://pay.weixin.qq.com/doc/v3/merchant/4013774588">转账场景报备信息字段说明</a> 传参。
     */
    @SerializedName("info_type")
    private String infoType;

    /**
     * 信息内容
     * 必须，不能超过32个字符，商户所属转账场景下的信息内容，商户可按实际业务场景自定义传参，需严格按照 <a href="https://pay.weixin.qq.com/doc/v3/merchant/4013774588">转账场景报备信息字段说明</a> 传参。
     */
    @SerializedName("info_content")
    private String infoContent;

  }

}
