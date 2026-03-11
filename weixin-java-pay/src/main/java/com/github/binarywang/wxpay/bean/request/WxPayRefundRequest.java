package com.github.binarywang.wxpay.bean.request;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants.RefundAccountSource;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.*;
import lombok.experimental.Accessors;
import me.chanjar.weixin.common.annotation.Required;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Map;

/**
 * <pre>
 * 微信支付-申请退款请求参数
 * Created by Binary Wang on 2016-10-08.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
@Accessors(chain = true)
public class WxPayRefundRequest extends BaseWxPayRequest {
  private static final long serialVersionUID = 522565152886671848L;

  private static final String[] REFUND_ACCOUNT = new String[]{
    RefundAccountSource.RECHARGE_FUNDS, RefundAccountSource.UNSETTLED_FUNDS};

  /**
   * <pre>
   * 字段名：设备号.
   * 变量名：device_info
   * 是否必填：否
   * 类型：String(32)
   * 示例值：13467007045764
   * 描述：终端设备号
   * </pre>
   */
  @XStreamAlias("device_info")
  private String deviceInfo;
  /**
   * <pre>
   * 字段名：微信订单号.
   * 变量名：transaction_id
   * 是否必填：跟out_trade_no二选一
   * 类型：String(28)
   * 示例值：1217752501201400000000000000
   * 描述：微信生成的订单号，在支付通知中有返回
   * </pre>
   */
  @XStreamAlias("transaction_id")
  private String transactionId;
  /**
   * <pre>
   * 字段名：商户订单号.
   * 变量名：out_trade_no
   * 是否必填：跟transaction_id二选一
   * 类型：String(32)
   * 示例值：1217752501201400000000000000
   * 描述：商户侧传给微信的订单号
   * </pre>
   */
  @XStreamAlias("out_trade_no")
  private String outTradeNo;
  /**
   * <pre>
   * 字段名：商户退款单号.
   * 变量名：out_refund_no
   * 是否必填：是
   * 类型：String(32)
   * 示例值：1217752501201400000000000000
   * 描述：商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
   * </pre>
   */
  @Required
  @XStreamAlias("out_refund_no")
  private String outRefundNo;
  /**
   * <pre>
   * 字段名：订单金额.
   * 变量名：total_fee
   * 是否必填：是
   * 类型：Int
   * 示例值：100
   * 描述：订单总金额，单位为分，只能为整数，详见支付金额
   * </pre>
   */
  @Required
  @XStreamAlias("total_fee")
  private Integer totalFee;
  /**
   * <pre>
   * 字段名：退款金额.
   * 变量名：refund_fee
   * 是否必填：是
   * 类型：Int
   * 示例值：100
   * 描述：退款总金额，订单总金额，单位为分，只能为整数，详见支付金额
   * </pre>
   */
  @Required
  @XStreamAlias("refund_fee")
  private Integer refundFee;
  /**
   * <pre>
   * 字段名：货币种类.
   * 变量名：refund_fee_type
   * 是否必填：否
   * 类型：String(8)
   * 示例值：CNY
   * 描述：货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
   * </pre>
   */
  @XStreamAlias("refund_fee_type")
  private String refundFeeType;
  /**
   * <pre>
   * 字段名：操作员.
   * 变量名：op_user_id
   * 是否必填：是
   * 类型：String(32)
   * 示例值：1900000109
   * 描述：操作员帐号, 默认为商户号
   * </pre>
   */
  @XStreamAlias("op_user_id")
  private String opUserId;
  /**
   * <pre>
   * 字段名：退款资金来源.
   * 变量名：refund_account
   * 是否必填：否
   * 类型：String(30)
   * 示例值：REFUND_SOURCE_RECHARGE_FUNDS
   * 描述：仅针对老资金流商户使用，
   * <li>REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款），
   * <li>REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
   * </pre>
   */
  @XStreamAlias("refund_account")
  private String refundAccount;

  /**
   * <pre>
   * 字段名：退款原因.
   * 变量名：refund_account
   * 是否必填：否
   * 类型：String(80)
   * 示例值：商品已售完
   * 描述：若商户传入，会在下发给用户的退款消息中体现退款原因
   * </pre>
   */
  @XStreamAlias("refund_desc")
  private String refundDesc;

  /**
   * <pre>
   * 字段名：退款结果通知url.
   * 变量名：notify_url
   * 是否必填：否
   * 类型：String(256)
   * 示例值：https://weixin.qq.com/notify/
   * 描述：	异步接收微信支付退款结果通知的回调地址，通知URL必须为外网可访问的url，不允许带参数
   * 如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效。
   * </pre>
   */
  @XStreamAlias("notify_url")
  private String notifyUrl;

  /**
   * <pre>
   * 字段名：商品详情
   * 变量名：detail
   * 类型：否
   * 示例值：String(6000)
   * 退款包含的商品列表信息detail字段列表说明：
   *
   * 字段名	变量名	必填	类型	示例值	描述
   * 商品列表	goods_detail	是	String	示例见下文	商品信息，使用Json数组格式提交
   * 商品列表goods_detail字段列表说明：
   *
   * 字段名	变量名	必填	类型	示例值	描述
   * 商品编码	goods_id	是	String(32)	商品编码	由半角的大小写字母、数字、中划线、下划线中的一种或几种组成
   * 微信侧商品编码	wxpay_goods_id	否	String(32)	1001	微信支付定义的统一商品编号（没有可不传）
   * 商品名称	goods_name	否	String(256)	iPhone6s 16G	商品的实际名称
   * 商品退款金额	refund_amount	是	int	528800	商品退款金额
   * 商品退货数量	refund_quantity	是	int	1	单品的退款数量
   * 商品单价	price	是	int	528800	单位为：分。如果商户有优惠，需传输商户优惠后的单价(例如：用户对一笔100元的订单使用了商场发的优惠券100-50，则活动商品的单价应为原单价-50)
   * detail字段值举例如下：
   *
   * {
   * "goods_detail": [
   * {
   * "goods_id": "商品编码",
   * "wxpay_goods_id": "1001",
   * "goods_name": "iPhone6s 16G",
   * "refund_amount": 528800,
   * "refund_quantity": 1,
   * "price": 528800
   * },
   * {
   * "goods_id": "商品编码",
   * "wxpay_goods_id": "1001",
   * "goods_name": "iPhone6s 16G",
   * "refund_amount": 528800,
   * "refund_quantity": 1,
   * "price": 608800
   * }
   * ]
   * }
   * 描述：退款包含的商品列表信息，全额退款可不传，必须按照规范上传，JSON格式
   * </pre>
   */
  @XStreamAlias("detail")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String detail;

  @Override
  public void checkAndSign(WxPayConfig config) throws WxPayException {
    if (StringUtils.isBlank(this.getOpUserId())) {
      this.setOpUserId(config.getMchId());
    }
    if (StringUtils.isBlank(this.getNotifyUrl())) {
      this.setNotifyUrl(config.getRefundNotifyUrl());
    }
    super.checkAndSign(config);
  }

  @Override
  protected void checkConstraints() throws WxPayException {
    if (StringUtils.isNotBlank(this.getRefundAccount())) {
      if (!ArrayUtils.contains(REFUND_ACCOUNT, this.getRefundAccount())) {
        throw new WxPayException(
          String.format("refund_account目前必须为%s其中之一,实际值：%s", Arrays.toString(REFUND_ACCOUNT), this.getRefundAccount()));
      }
    }

    if (StringUtils.isBlank(this.getOutTradeNo()) && StringUtils.isBlank(this.getTransactionId())) {
      throw new WxPayException("transaction_id 和 out_trade_no 不能同时为空，必须提供一个");
    }
  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("device_info", deviceInfo);
    map.put("transaction_id", transactionId);
    map.put("out_trade_no", outTradeNo);
    map.put("out_refund_no", outRefundNo);
    map.put("total_fee", totalFee.toString());
    map.put("refund_fee", refundFee.toString());
    map.put("refund_fee_type", refundFeeType);
    map.put("op_user_id", opUserId);
    map.put("refund_account", refundAccount);
    map.put("refund_desc", refundDesc);
    map.put("notify_url", notifyUrl);
  }

}
