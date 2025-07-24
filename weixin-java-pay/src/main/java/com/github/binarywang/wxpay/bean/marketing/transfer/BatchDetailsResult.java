package com.github.binarywang.wxpay.bean.marketing.transfer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * <pre>
 * 微信支付明细单号查询明细单API
 * 文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/transfer_partner/chapter3_3.shtml
 *
 * 适用对象：服务商
 * 请求URL：https://api.mch.weixin.qq.com/v3/partner-transfer/batches/batch-id/{batch_id}/details/detail-id/{detail_id}
 * 请求方式：GET
 * 接口限频：单个服务商 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
 * </pre>
 *
 * @author xiaoqiang
 * created on  2021-12-06
 */
@Data
@NoArgsConstructor
public class BatchDetailsResult implements Serializable {
  private static final long serialVersionUID = 1L;

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }

  /**
   * <pre>
   * 字段名：服务商商户号
   * 变量名：sp_mchid
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  微信支付分配的商户号，此处为服务商商户号
   *  示例值：1900001109
   * </pre>
   */
  @SerializedName(value = "sp_mchid")
  private String spMchid;
  /**
   * <pre>
   * 字段名：商家批次单号
   * 变量名：out_batch_no
   * 是否必填：是
   * 类型：string[5, 32]
   * 描述：
   *  商户系统内部的商家批次单号，在商户系统内部唯一
   *     示例值：plfk2020042013
   * </pre>
   */
  @SerializedName(value = "out_batch_no")
  private String outBatchNo;
  /**
   * <pre>
   * 字段名：微信支付批次单号
   * 变量名：batch_id
   * 是否必填：是
   * 类型：string[5, 32]
   * 描述：
   *  微信支付批次单号，微信商家转账系统返回的唯一标识
   *     示例值：1030000071100999991182020050700019480001
   * </pre>
   */
  @SerializedName(value = "batch_id")
  private String batchId;
  /**
   * <pre>
   * 字段名：商户的appid
   * 变量名：
   * 是否必填：否
   * 类型：string[1, 32]
   * 描述：
   * 微信分配的特约商户公众账号ID。特约商户授权类型为 INFORMATION_AUTHORIZATION_TYPE和
   *     INFORMATION_AND_FUND_AUTHORIZATION_TYPE时对应的是特约商户的appid，
   *     特约商户授权类型为FUND_AUTHORIZATION_TYPE时为服务商的appid
   * 例值：wxf636efh567hg4356
   * </pre>
   */
  @Expose
  @SerializedName(value = "appid")
  private String appId;
  /**
   * <pre>
   * 字段名：商家明细单号
   * 变量名：out_detail_no
   * 是否必填：是
   * 类型：string[5, 32]
   * 描述：
   * 商户系统内部区分转账批次单下不同转账明细单的唯一标识
   *     示例值：x23zy545Bd5436
   * </pre>
   */
  @SerializedName(value = "out_detail_no")
  private String outDetailNo;
  /**
   * <pre>
   * 字段名：微信支付明细单号
   * 变量名：detail_id
   * 是否必填：是
   * 类型：string[32, 64]
   * 描述：
   * 微信支付系统内部区分转账批次单下不同转账明细单的唯一标识
   *     示例值：1040000071100999991182020050700019500100
   * </pre>
   */
  @SerializedName(value = "detail_id")
  private String detailId;
  /**
   * <pre>
   * 字段名：明细状态
   * 变量名：detail_status
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   * 枚举值：
   *     PROCESSING：转账中。正在处理中，转账结果尚未明确
   *     SUCCESS：转账成功
   *     FAIL：转账失败。需要确认失败原因后，再决定是否重新发起对该笔明细单的转账（并非整个转账批次单）
   * 示例值：SUCCESS
   * </pre>
   */
  @SerializedName(value = "detail_status")
  private String detailStatus;
  /**
   * <pre>
   * 字段名：转账金额
   * 变量名：transfer_amount
   * 是否必填：是
   * 类型：int
   * 描述：
   * 转账金额单位为“分”
   *     示例值：200000
   * </pre>
   */
  @SerializedName(value = "transfer_amount")
  private Integer transferAmount;
  /**
   * <pre>
   * 字段名：转账备注
   * 变量名：transfer_remark
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   * 单条转账备注（微信用户会收到该备注），UTF8编码，最多允许32个字符
   *     示例值：2020年4月报销
   * </pre>
   */
  @SerializedName(value = "transfer_remark")
  private String transferRemark;
  /**
   * <pre>
   * 字段名：明细失败原因
   * 变量名：fail_reason
   * 是否必填：否
   * 类型：string[1, 64]
   * 描述：
   * 如果转账失败则有失败原因：
   *     ACCOUNT_FROZEN：账户冻结
   *     REAL_NAME_CHECK_FAIL：用户未实名
   *     NAME_NOT_CORRECT：用户姓名校验失败
   *     OPENID_INVALID：Openid校验失败
   *     TRANSFER_QUOTA_EXCEED：超过用户单笔收款额度
   *     DAY_RECEIVED_QUOTA_EXCEED：超过用户单日收款额度
   *     MONTH_RECEIVED_QUOTA_EXCEED：超过用户单月收款额度
   *     DAY_RECEIVED_COUNT_EXCEED：超过用户单日收款次数
   *     PRODUCT_AUTH_CHECK_FAIL：产品权限校验失败
   *     OVERDUE_CLOSE：转账关闭
   *     ID_CARD_NOT_CORRECT：用户身份证校验失败
   *     ACCOUNT_NOT_EXIST：用户账户不存在
   *     TRANSFER_RISK：转账存在风险
   * 示例值：ACCOUNT_FROZEN
   * </pre>
   */
  @SerializedName(value = "fail_reason")
  private String failReason;
  /**
   * <pre>
   * 字段名：收款用户openid
   * 变量名：openid
   * 是否必填：是
   * 类型：string[1, 64]
   * 描述：
   * 收款用户openid。如果转账特约商户授权类型是INFORMATION_AUTHORIZATION_TYPE，对应的是特约商户公众号下的openid;
   *     如果转账特约商户授权类型是FUND_AUTHORIZATION_TYPE，对应的是服务商商户公众号下的openid。
   * 示例值：o-MYE42l80oelYMDE34nYD456Xoy
   * </pre>
   */
  @SerializedName(value = "openid")
  private String openid;
  /**
   * <pre>
   * 字段名：收款用户姓名
   * 变量名：username
   * 是否必填：是
   * 类型：string[1, 1024]
   * 描述：
   * 1、收款方姓名。采用标准RSA算法，公钥由微信侧提供
   * 2、该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
   *     示例值：757b340b45ebef5467rter35gf464344v3542sdf4t6re4tb4f54ty45t4yyry45
   * </pre>
   */
  @SerializedName(value = "username")
  private String userName;
  /**
   * <pre>
   * 字段名：转账发起时间
   * 变量名：initiate_time
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   * 转账发起的时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示北京时间2015年05月20日13点29分35秒
   *     示例值：2015-05-20T13:29:35.120+08:00
   * </pre>
   */
  @SerializedName(value = "initiate_time")
  private String initiateTime;
  /**
   * <pre>
   * 字段名：明细更新时间
   * 变量名：initiate_time
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   * 明细最后一次状态变更的时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示北京时间2015年05月20日13点29分35秒
   *     示例值：2015-05-20T13:29:35.120+08:00
   * </pre>
   */
  @SerializedName(value = "update_time")
  private String updateTime;
}
