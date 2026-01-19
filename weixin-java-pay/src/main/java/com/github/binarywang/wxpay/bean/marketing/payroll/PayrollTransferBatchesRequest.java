package com.github.binarywang.wxpay.bean.marketing.payroll;

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
 * 微工卡批量转账API请求参数
 * 文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter4_1_8.shtml
 *
 * 适用对象：服务商
 * 请求URL：https://api.mch.weixin.qq.com/v3/payroll-card/transfer-batches
 * 请求方式：POST
 * </pre>
 *
 * @author binarywang
 * created on  2025/01/19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayrollTransferBatchesRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：应用ID
   * 变量名：appid
   * 是否必填：二选一
   * 类型：string[1, 32]
   * 描述：
   *  服务商在微信申请公众号/小程序或移动应用成功后分配的账号ID
   *  示例值：wxa1111111
   * </pre>
   */
  @SerializedName(value = "appid")
  private String appid;

  /**
   * <pre>
   * 字段名：子商户应用ID
   * 变量名：sub_appid
   * 是否必填：二选一
   * 类型：string[1, 32]
   * 描述：
   *  特约商户在微信申请公众号/小程序或移动应用成功后分配的账号ID
   *  示例值：wxa1111111
   * </pre>
   */
  @SerializedName(value = "sub_appid")
  private String subAppid;

  /**
   * <pre>
   * 字段名：子商户号
   * 变量名：sub_mchid
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  微信服务商下特约商户的商户号，由微信支付生成并下发
   *  示例值：1111111
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;

  /**
   * <pre>
   * 字段名：商家批次单号
   * 变量名：out_batch_no
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  商户系统内部的商家批次单号，要求此参数只能由数字、大小写字母组成，在商户系统内部唯一
   *  示例值：plfk2020042013
   * </pre>
   */
  @SerializedName(value = "out_batch_no")
  private String outBatchNo;

  /**
   * <pre>
   * 字段名：批次名称
   * 变量名：batch_name
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  该笔批量转账的名称
   *  示例值：2019年1月深圳分部报销单
   * </pre>
   */
  @SerializedName(value = "batch_name")
  private String batchName;

  /**
   * <pre>
   * 字段名：批次备注
   * 变量名：batch_remark
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  转账说明，UTF8编码，最多允许32个字符
   *  示例值：2019年1月深圳分部报销单
   * </pre>
   */
  @SerializedName(value = "batch_remark")
  private String batchRemark;

  /**
   * <pre>
   * 字段名：转账总金额
   * 变量名：total_amount
   * 是否必填：是
   * 类型：int64
   * 描述：
   *  转账金额单位为"分"。转账总金额必须与批次内所有明细转账金额之和保持一致，否则无法发起转账操作
   *  示例值：4000000
   * </pre>
   */
  @SerializedName(value = "total_amount")
  private Long totalAmount;

  /**
   * <pre>
   * 字段名：转账总笔数
   * 变量名：total_num
   * 是否必填：是
   * 类型：int
   * 描述：
   *  一个转账批次单最多发起一千笔转账。转账总笔数必须与批次内所有明细之和保持一致，否则无法发起转账操作
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
   *  发起批量转账的明细列表，最多一千笔
   * </pre>
   */
  @SerializedName(value = "transfer_detail_list")
  private List<TransferDetail> transferDetailList;

  /**
   * 转账明细
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class TransferDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <pre>
     * 字段名：商家明细单号
     * 变量名：out_detail_no
     * 是否必填：是
     * 类型：string[1, 32]
     * 描述：
     *  商户系统内部区分转账批次单下不同转账明细单的唯一标识
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
     * 类型：int64
     * 描述：
     *  转账金额单位为"分"
     *  示例值：200000
     * </pre>
     */
    @SerializedName(value = "transfer_amount")
    private Long transferAmount;

    /**
     * <pre>
     * 字段名：转账备注
     * 变量名：transfer_remark
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
     * 字段名：用户标识
     * 变量名：openid
     * 是否必填：是
     * 类型：string[1, 64]
     * 描述：
     *  用户在商户对应appid下的唯一标识
     *  示例值：o-MYE42l80oelYMDE34nYD456Xoy
     * </pre>
     */
    @SerializedName(value = "openid")
    private String openid;

    /**
     * <pre>
     * 字段名：收款用户姓名
     * 变量名：user_name
     * 是否必填：否
     * 类型：string[1, 1024]
     * 描述：
     *  收款用户真实姓名。该字段需进行加密处理，加密方法详见敏感信息加密说明
     *  示例值：757b340b45ebef5467rter35gf464344v3542sdf4t6re4tb4f54ty45t4yyry45
     * </pre>
     */
    @SpecEncrypt
    @SerializedName(value = "user_name")
    private String userName;
  }
}
