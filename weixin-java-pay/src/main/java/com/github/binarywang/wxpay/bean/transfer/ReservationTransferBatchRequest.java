package com.github.binarywang.wxpay.bean.transfer;

import com.github.binarywang.wxpay.v3.SpecEncrypt;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 批量预约商家转账请求参数
 * 商户可以通过批量预约接口一次发起批量转账请求，最多可以同时向50个用户发起转账。
 * 文档地址：https://pay.weixin.qq.com/doc/v3/merchant/4015901167
 * </pre>
 *
 * @author wanggang
 * created on 2025/11/28
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class ReservationTransferBatchRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 【商户AppID】 商户在微信申请公众号或移动应用成功后分配的账号ID
   */
  @SerializedName("appid")
  private String appid;

  /**
   * 【商户预约批次单号】 商户系统内部的商家预约批次单号，要求此参数只能由数字、大小写字母组成，在商户系统内部唯一
   */
  @SerializedName("out_batch_no")
  private String outBatchNo;

  /**
   * 【转账场景ID】 商户在商户平台-产品中心-商家转账中申请的转账场景ID
   */
  @SerializedName("transfer_scene_id")
  private String transferSceneId;

  /**
   * 【批次备注】 批次备注
   */
  @SerializedName("batch_remark")
  private String batchRemark;

  /**
   * 【转账总金额】 转账金额单位为"分"，转账总金额必须与批次内所有转账明细金额之和保持一致，否则无法发起转账操作
   */
  @SerializedName("total_amount")
  private Integer totalAmount;

  /**
   * 【转账总笔数】 转账总笔数，需要与批次内所有转账明细笔数保持一致，否则无法发起转账操作
   */
  @SerializedName("total_num")
  private Integer totalNum;

  /**
   * 【转账明细列表】 转账明细列表，最多50条
   */
  @SerializedName("transfer_detail_list")
  private List<TransferDetail> transferDetailList;

  /**
   * 【异步回调地址】 异步接收微信支付结果通知的回调地址，通知url必须为公网可访问的url，必须为https，不能携带参数
   */
  @SerializedName("notify_url")
  private String notifyUrl;

  /**
   * 转账明细
   */
  @Data
  @Builder(builderMethodName = "newBuilder")
  @NoArgsConstructor
  @AllArgsConstructor
  public static class TransferDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 【商户明细单号】 商户系统内部区分转账批次单下不同转账明细单的唯一标识，要求此参数只能由数字、大小写字母组成
     */
    @SerializedName("out_detail_no")
    private String outDetailNo;

    /**
     * 【转账金额】 转账金额单位为"分"
     */
    @SerializedName("transfer_amount")
    private Integer transferAmount;

    /**
     * 【转账备注】 单条转账备注（微信用户会收到该备注），UTF8编码，最多允许32个字符
     */
    @SerializedName("transfer_remark")
    private String transferRemark;

    /**
     * 【收款用户OpenID】 商户AppID下，某用户的OpenID
     */
    @SerializedName("openid")
    private String openid;

    /**
     * 【收款用户姓名】 收款方真实姓名。支持标准RSA算法和国密算法，公钥由微信侧提供
     */
    @SpecEncrypt
    @SerializedName("user_name")
    private String userName;

    /**
     * 【转账场景报备信息】
     */
    @SerializedName("transfer_scene_report_infos")
    private List<TransferSceneReportInfo> transferSceneReportInfos;
  }

  /**
   * 转账场景报备信息
   */
  @Data
  @Builder(builderMethodName = "newBuilder")
  @NoArgsConstructor
  @AllArgsConstructor
  public static class TransferSceneReportInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 【信息类型】 信息类型编码
     */
    @SerializedName("info_type")
    private String infoType;

    /**
     * 【信息内容】 信息内容
     */
    @SerializedName("info_content")
    private String infoContent;
  }
}
