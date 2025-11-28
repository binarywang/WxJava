package com.github.binarywang.wxpay.bean.marketing.transfer;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 发起批量转账API
 * <pre>
 * 文档地址:<a href="https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/transfer_partner/chapter3_1.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/transfer_partner/chapter3_1.shtml</a>
 * </pre>
 *
 * @author xiaoqiang
 * created on  2021-12-06
 */
@Data
@NoArgsConstructor
public class PartnerTransferRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：特约商户号
   * 变量名：sub_mchid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  特约商户号
   *  示例值：1900000109
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;

  /**
   * <pre>
   * 字段名：特约商户appid
   * 变量名：sub_appid
   * 是否必填：否
   * 类型：string（32）
   * 描述：
   *  示例值：wxf636efh567hg4356
   * </pre>
   */
  @SerializedName(value = "sub_appid")
  private String subAppid;

  /**
   * <pre>
   * 字段名：特约商户授权类型
   * 变量名：authorization_type
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  特约商户授权类型：
   *    INFORMATION_AUTHORIZATION_TYPE：特约商户信息授权类型
   *    FUND_AUTHORIZATION_TYPE：特约商户资金授权类型
   *    INFORMATION_AND_FUND_AUTHORIZATION_TYPE：特约商户信息和资金授权类型
   * 示例值：INFORMATION_AUTHORIZATION_TYPE
   * </pre>
   */
  @SerializedName(value = "authorization_type")
  private String authorizationType;
  /**
   * <pre>
   * 字段名：商家批次单号
   * 变量名：out_batch_no
   * 是否必填：是
   * 类型：string（5-32）
   * 描述：
   *    商户系统内部的商家批次单号，要求此参数只能由数字、大小写字母组成，在商户系统内部唯一
   *    示例值：plfk2020042013
   * </pre>
   */
  @SerializedName(value = "out_batch_no")
  private String outBatchNo;

  /**
   * <pre>
   * 字段名：批次名称
   * 变量名：batch_name
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  body该笔批量转账的名称
   *  示例值：2019年1月深圳分部报销单
   * </pre>
   */
  @SerializedName(value = "batch_name")
  private String batchName;

  /**
   * <pre>
   * 字段名：批次备注
   * 变量名：out_trade_no
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  body转账说明，UTF8编码，最多允许32个字符
   *   示例值：2019年1月深圳分部报销单
   * </pre>
   */
  @SerializedName(value = "batch_remark")
  private String batchRemark;

  /**
   * <pre>
   * 字段名：转账总金额
   * 变量名：time_expire
   * 是否必填：是
   * 类型：int
   * 描述：
   *  body转账金额单位为“分”。转账总金额必须与批次内所有明细转账金额之和保持一致，否则无法发起转账操作
   *  示例值：4000000
   * </pre>
   */
  @SerializedName(value = "total_amount")
  private Integer totalAmount;

  /**
   * <pre>
   * 字段名：转账总笔数
   * 变量名：total_num
   * 是否必填：是
   * 类型：int
   * 描述：
   *  body一个转账批次单最多发起三千笔转账。转账总笔数必须与批次内所有明细之和保持一致，否则无法发起转账操作
   *  示例值：200
   * </pre>
   */
  @SerializedName(value = "total_num")
  private Integer totalNum;

  /**
   * <pre>
   * 字段名：转账明细列表
   * 变量名：transfer_detail_list
   * 是否必填：是
   * 类型：array
   * 描述：
   *  body发起批量转账的明细列表，最多三千笔
   * </pre>
   */
  @SerializedName(value = "transfer_detail_list")
  private List<TransferDetail> transferDetailList;

  @Data
  @NoArgsConstructor
  public static class TransferDetail implements Serializable {
    private static final long serialVersionUID = 109053454401492L;

    /**
     * <pre>
     * 字段名：商家明细单号
     * 变量名：out_detail_no
     * 是否必填：是
     * 类型：string[5, 32]
     * 描述：
     *  商户系统内部区分转账批次单下不同转账明细单的唯一标识，要求此参数只能由数字、大小写字母组成
     *  示例值：x23zy545Bd5436
     * </pre>
     */
    @SerializedName(value = "out_detail_no")
    private String outDetailNo;


    /**
     * <pre>
     * 字段名：转账金额
     * 变量名：transfer_amount
     * 是否必填：是
     * 类型：int
     * 描述：
     *  转账金额单位为“分”
     *  示例值：200000
     * </pre>
     */
    @SerializedName(value = "transfer_amount")
    private Integer transferAmount;

    /**
     * <pre>
     * 字段名：转账备注
     * 变量名：transfer_amount
     * 是否必填：是
     * 类型：string[1, 32]
     * 描述：
     *  单条转账备注（微信用户会收到该备注），UTF8编码，最多允许32个字符
     *  示例值：2020年4月报销
     * </pre>
     */
    @SerializedName(value = "transfer_remark")
    private String transferRemark;

    /**
     * <pre>
     * 字段名：收款用户openid
     * 变量名：openid
     * 是否必填：是
     * 类型：string[1, 64]
     * 描述：
     *  收款用户openid。如果转账特约商户授权类型是INFORMATION_AUTHORIZATION_TYPE，对应的是特约商户公众号下的openid。
     *  示例值：o-MYE42l80oelYMDE34nYD456Xoy
     * </pre>
     */
    @SerializedName(value = "openid")
    private String openid;

    /**
     * <pre>
     * 字段名：收款用户姓名
     * 变量名：user_name
     * 是否必填：是
     * 类型：string[1, 1024]
     * 描述：
     *  1、收款用户姓名。采用标准RSA算法，公钥由微信侧提供
     *  2、该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
     *  示例值：757b340b45ebef5467rter35gf464344v3542sdf4t6re4tb4f54ty45t4yyry45
     * </pre>
     */
    @SerializedName(value = "user_name")
    private String userName;
    /**
     * <pre>
     * 字段名：收款用户身份证
     * 变量名：user_id_card
     * 是否必填：否
     * 类型：string[1, 1024]
     * 描述：
     *  1、收款方身份证号，可不用填（采用标准RSA算法，公钥由微信侧提供）
     *  2、该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
     *  示例值：8609cb22e1774a50a930e414cc71eca06121bcd266335cda230d24a7886a8d9f
     * </pre>
     */
    @SerializedName(value = "user_id_card")
    private String userIdCard;
  }

  /**
   * <pre>
   * 字段名：服务商的appid
   * 变量名：sp_appid
   * 是否必填：否
   * 类型：string[1, 32]
   * 描述：
   *  特约商户授权类型为FUND_AUTHORIZATION_TYPE时需要填写
   *  示例值：wxf636efh567hg4388
   * </pre>
   */
  @SerializedName(value = "sp_appid")
  private String spAppid;


  /**
   * <pre>
   * 字段名：批量转账用途
   * 变量名：transfer_purpose
   * 是否必填：否
   * 类型：string[1, 32]
   * 描述：
   *  body批量转账用途，枚举值：
   *        GOODSPAYMENT：货款、COMMISSION：佣金、REFUND：退款、REIMBURSEMENT：报销
   *        FREIGHT：运费、OTHERS：其他
   *  示例值：COMMISSION
   * </pre>
   */
  @SerializedName(value = "transfer_purpose")
  private String transferPurpose;

  /**
   * <pre>
   * 字段名：转账场景【微工卡】
   * 变量名：transfer_scene
   * 是否必填：否
   * 类型：string[1, 32]
   * 描述：
   *  body商户的转账场景
   *     ORDINARY_TRANSFER：普通转账，可转入用户的零钱账户
   *     PAYROLL_CARD_TRANSFER：微工卡转账，可转入用户在微工卡选择的收款账户（零钱/银行卡）
   *  示例值：ORDINARY_TRANSFER
   * </pre>
   */
  @SerializedName(value = "transfer_scene")
  private String transferScene;

}
