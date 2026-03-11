package com.github.binarywang.wxpay.bean.transfer;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 运营工具-商家转账结果
 *
 * @author WxJava Team
 * @see <a href="https://pay.weixin.qq.com/doc/v3/merchant/4012711988">运营工具-商家转账API</a>
 */
@Data
@NoArgsConstructor
public class BusinessOperationTransferResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 商户系统内部的商家单号
   */
  @SerializedName("out_bill_no")
  private String outBillNo;

  /**
   * 微信转账单号
   * 微信商家转账系统返回的唯一标识
   */
  @SerializedName("transfer_bill_no")
  private String transferBillNo;

  /**
   * 单据状态
   * 商家转账订单状态
   * ACCEPTED：转账已受理，可原单重试（非终态）。
   * PROCESSING: 转账锁定资金中。如果一直停留在该状态，建议检查账户余额是否足够，如余额不足，可充值后再原单重试（非终态）。
   * WAIT_USER_CONFIRM:  待收款用户确认，当前转账单据资金已锁定，可拉起微信收款确认页面进行收款确认（非终态）。
   * TRANSFERING:  转账中，可拉起微信收款确认页面再次重试确认收款（非终态）。
   * SUCCESS:  转账成功，表示转账单据已成功（终态）。
   * FAIL:  转账失败，表示该笔转账单据已失败。若需重新向用户转账，请重新生成单据并再次发起（终态）。
   * CANCELING:  转账撤销中，商户撤销请求受理成功，该笔转账正在撤销中，需查单确认撤销的转账单据状态（非终态）。
   * CANCELLED:  转账撤销完成，代表转账单据已撤销成功（终态）。
   */
  @SerializedName("state")
  private String state;

  /**
   * 跳转领取页面的package信息
   * 跳转微信支付收款页的package信息， <a href="https://pay.weixin.qq.com/doc/v3/merchant/4012719576">APP调起用户确认收款</a> 或者 <a href="https://pay.weixin.qq.com/doc/v3/merchant/4012716430">JSAPI调起用户确认收款</a> 时需要使用的参数。仅当转账单据状态为WAIT_USER_CONFIRM时返回。<br>
   * 单据创建后，用户24小时内不领取将过期关闭，建议拉起用户确认收款页面前，先查单据状态：如单据状态为WAIT_USER_CONFIRM，可用之前的package信息拉起；单据到终态时需更换单号重新发起转账。
   */
  @SerializedName("package_info")
  private String packageInfo;

  /**
   * 发起转账的时间
   * 遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * 转账更新时间
   * 遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE
   */
  @SerializedName("update_time")
  private String updateTime;

  /**
   * 失败原因
   * 当transfer_state为FAIL时返回
   */
  @SerializedName("fail_reason")
  private String failReason;
}