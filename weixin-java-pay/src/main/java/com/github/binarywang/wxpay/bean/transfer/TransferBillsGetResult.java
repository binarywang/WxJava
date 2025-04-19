package com.github.binarywang.wxpay.bean.transfer;

import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * 商家转账到零钱查询转账单接口
 * 文档地址：https://pay.weixin.qq.com/doc/v3/merchant/4012716457 https://pay.weixin.qq.com/doc/v3/merchant/4012716437
 * </pre>
 *
 * @author Nor
 * @date 2025/1/17
 */
@Data
@NoArgsConstructor
public class TransferBillsGetResult implements Serializable {
  private static final long serialVersionUID = -6376955113492371763L;

  /**
   * 【商户号】 微信支付分配的商户号
   */
  @SerializedName("mch_id")
  private String mchId;

  /**
   * 【商户单号】 商户系统内部的商家单号，要求此参数只能由数字、大小写字母组成，在商户系统内部唯一
   */
  @SerializedName("out_bill_no")
  private String outBillNo;

  /**
   * 【商家转账订单号】 商家转账订单的主键，唯一定义此资源的标识
   */
  @SerializedName("transfer_bill_no")
  private String transferBillNo;

  /**
   * 【商户AppID】 申请商户号的AppID或商户号绑定的AppID（企业号corpid即为此AppID）
   */
  private String appid;

  /**
   * 【单据状态】
   * 可选取值
   * ACCEPTED: 转账已受理
   * PROCESSING: 转账处理中，转账结果尚未明确，如一直处于此状态，建议检查账户余额是否足够
   * WAIT_USER_CONFIRM: 待收款用户确认，可拉起微信收款确认页面进行收款确认
   * TRANSFERING: 转账结果尚未明确，可拉起微信收款确认页面再次重试确认收款
   * SUCCESS: 转账成功
   * FAIL: 转账失败
   * CANCELING: 商户撤销请求受理成功，该笔转账正在撤销中
   * CANCELLED: 转账撤销完成
   *
   * @see WxPayConstants.TransformBillState
   */
  private String state;

  /**
   * 【转账金额】 转账金额单位为“分”。
   */
  @SerializedName("transfer_amount")
  private String transferAmount;

  /**
   * 【转账备注】 单条转账备注（微信用户会收到该备注），UTF8编码，最多允许32个字符
   */
  @SerializedName("transfer_remark")
  private String transferRemark;

  /**
   * 【失败原因】 订单已失败或者已退资金时，返回失败原因
   */
  @SerializedName("fail_reason")
  private String failReason;

  /**
   * 【收款用户OpenID】 商户AppID下，某用户的OpenID
   */
  private String openid;

  /**
   * 【收款用户姓名】 收款方真实姓名。支持标准RSA算法和国密算法，公钥由微信侧提供转账金额 >= 2,000元时，该笔明细必须填写若商户传入收款用户姓名，微信支付会校验用户OpenID与姓名是否一致，并提供电子回单
   */
  @SerializedName("user_name")
  private String userName;

  /**
   * 【单据创建时间】 单据受理成功时返回，按照使用rfc3339所定义的格式，格式为yyyy-MM-DDThh:mm:ss+TIMEZONE
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * 【最后一次状态变更时间】 单据最后更新时间，按照使用rfc3339所定义的格式，格式为yyyy-MM-DDThh:mm:ss+TIMEZONE
   */
  @SerializedName("update_time")
  private String updateTime;
}
