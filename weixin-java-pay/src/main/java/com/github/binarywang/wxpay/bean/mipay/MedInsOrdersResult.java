package com.github.binarywang.wxpay.bean.mipay;

import com.github.binarywang.wxpay.bean.mipay.enums.MedInsPayStatusEnum;
import com.github.binarywang.wxpay.bean.mipay.enums.MixPayStatusEnum;
import com.github.binarywang.wxpay.bean.mipay.enums.MixPayTypeEnum;
import com.github.binarywang.wxpay.bean.mipay.enums.OrderTypeEnum;
import com.github.binarywang.wxpay.bean.mipay.enums.SelfPayStatusEnum;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

/**
 * 医保自费混合收款下单响应
 * <p>
 * 从业机构调用医保自费混合收款下单接口后返回的结果
 * 文档地址：https://pay.weixin.qq.com/doc/v3/partner/4012503131
 * @author xgl
 * @date 2025/12/19 14:37
 */
@Data
public class MedInsOrdersResult {
  /**
   * <pre>
   * 字段名：混合交易订单号
   * 变量名：mix_trade_no
   * 必填：是
   * 类型：string
   * 描述：微信支付生成的混合交易订单号
   * </pre>
   */
  @SerializedName("mix_trade_no")
  public String mixTradeNo;

  /**
   * <pre>
   * 字段名：混合支付状态
   * 变量名：mix_pay_status
   * 必填：是
   * 类型：string
   * 描述：混合支付整体状态
   * </pre>
   */
  @SerializedName("mix_pay_status")
  public MixPayStatusEnum mixPayStatus;

  /**
   * <pre>
   * 字段名：自费支付状态
   * 变量名：self_pay_status
   * 必填：是
   * 类型：string
   * 描述：自费部分支付状态
   * </pre>
   */
  @SerializedName("self_pay_status")
  public SelfPayStatusEnum selfPayStatus;

  /**
   * <pre>
   * 字段名：医保支付状态
   * 变量名：med_ins_pay_status
   * 必填：是
   * 类型：string
   * 描述：医保部分支付状态
   * </pre>
   */
  @SerializedName("med_ins_pay_status")
  public MedInsPayStatusEnum medInsPayStatus;

  /**
   * <pre>
   * 字段名：支付完成时间
   * 变量名：paid_time
   * 必填：否
   * 类型：string
   * 描述：支付完成时间，格式为yyyyMMddHHmmss
   * </pre>
   */
  @SerializedName("paid_time")
  public String paidTime;

  /**
   * <pre>
   * 字段名：透传响应内容
   * 变量名：passthrough_response_content
   * 必填：否
   * 类型：string
   * 描述：透传响应内容
   * </pre>
   */
  @SerializedName("passthrough_response_content")
  public String passthroughResponseContent;

  /**
   * <pre>
   * 字段名：混合支付类型
   * 变量名：mix_pay_type
   * 必填：是
   * 类型：string
   * 描述：
   *   混合支付类型可选取值：
   *   - UNKNOWN_MIX_PAY_TYPE: 未知的混合支付类型，会被拦截
   *   - CASH_ONLY: 只向微信支付下单，没有向医保局下单
   *   - INSURANCE_ONLY: 只向医保局下单，没有向微信支付下单
   *   - CASH_AND_INSURANCE: 向医保局下单，也向微信支付下单
   * </pre>
   */
  @SerializedName("mix_pay_type")
  public MixPayTypeEnum mixPayType;

  /**
   * <pre>
   * 字段名：订单类型
   * 变量名：order_type
   * 必填：是
   * 类型：string
   * 描述：
   *   订单类型可选取值：
   *   - UNKNOWN_ORDER_TYPE: 未知类型，会被拦截
   *   - REG_PAY: 挂号支付
   *   - DIAG_PAY: 诊间支付
   *   - COVID_EXAM_PAY: 新冠检测费用（核酸）
   *   - IN_HOSP_PAY: 住院费支付
   *   - PHARMACY_PAY: 药店支付
   *   - INSURANCE_PAY: 保险费支付
   *   - INT_REG_PAY: 互联网医院挂号支付
   *   - INT_RE_DIAG_PAY: 互联网医院复诊支付
   *   - INT_RX_PAY: 互联网医院处方支付
   *   - COVID_ANTIGEN_PAY: 新冠抗原检测
   *   - MED_PAY: 药费支付
   * </pre>
   */
  @SerializedName("order_type")
  public OrderTypeEnum orderType;

  /**
   * <pre>
   * 字段名：从业机构/服务商的公众号ID
   * 变量名：appid
   * 必填：是
   * 类型：string(32)
   * 描述：从业机构/服务商的公众号ID
   * </pre>
   */
  @SerializedName("appid")
  public String appid;

  /**
   * <pre>
   * 字段名：医疗机构的公众号ID
   * 变量名：sub_appid
   * 必填：是
   * 类型：string(32)
   * 描述：医疗机构的公众号ID
   * </pre>
   */
  @SerializedName("sub_appid")
  public String subAppid;

  /**
   * <pre>
   * 字段名：医疗机构的商户号
   * 变量名：sub_mchid
   * 必填：是
   * 类型：string(32)
   * 描述：医疗机构的商户号
   * </pre>
   */
  @SerializedName("sub_mchid")
  public String subMchid;

  /**
   * <pre>
   * 字段名：用户在appid下的唯一标识
   * 变量名：openid
   * 必填：否
   * 类型：string(128)
   * 描述：openid与sub_openid二选一，传入openid时需要使用appid调起医保自费混合支付
   * </pre>
   */
  @SerializedName("openid")
  public String openid;

  /**
   * <pre>
   * 字段名：用户在sub_appid下的唯一标识
   * 变量名：sub_openid
   * 必填：否
   * 类型：string(128)
   * 描述：openid与sub_openid二选一，传入sub_openid时需要使用sub_appid调起医保自费混合支付
   * </pre>
   */
  @SerializedName("sub_openid")
  public String subOpenid;

  /**
   * <pre>
   * 字段名：是否代亲属支付
   * 变量名：pay_for_relatives
   * 必填：否
   * 类型：boolean
   * 描述：不传默认替本人支付
   *   - true: 代亲属支付
   *   - false: 本人支付
   * </pre>
   */
  @SerializedName("pay_for_relatives")
  public Boolean payForRelatives;

  /**
   * <pre>
   * 字段名：从业机构订单号
   * 变量名：out_trade_no
   * 必填：是
   * 类型：string(64)
   * 描述：从业机构/服务商需要调两次接口：从业机构/服务商向微信支付下单获取微信支付凭证，请求中会带上out_trade_no。下单成功后，从业机构/服务商调用混合下单的接口（即该接口），请求中也会带上out_trade_no。
   * </pre>
   */
  @SerializedName("out_trade_no")
  public String outTradeNo;

  /**
   * <pre>
   * 字段名：医疗机构订单号
   * 变量名：serial_no
   * 必填：是
   * 类型：string(40)
   * 描述：例如医院HIS系统订单号。传与费用明细上传中medOrgOrd字段一样的值，局端会校验，不一致将会返回错误
   * </pre>
   */
  @SerializedName("serial_no")
  public String serialNo;

  /**
   * <pre>
   * 字段名：支付订单号
   * 变量名：pay_order_id
   * 必填：否
   * 类型：string
   * 描述：支付订单号
   * </pre>
   */
  @SerializedName("pay_order_id")
  public String payOrderId;

  /**
   * <pre>
   * 字段名：支付授权号
   * 变量名：pay_auth_no
   * 必填：否
   * 类型：string
   * 描述：支付授权号
   * </pre>
   */
  @SerializedName("pay_auth_no")
  public String payAuthNo;

  /**
   * <pre>
   * 字段名：地理位置
   * 变量名：geo_location
   * 必填：否
   * 类型：string
   * 描述：地理位置
   * </pre>
   */
  @SerializedName("geo_location")
  public String geoLocation;

  /**
   * <pre>
   * 字段名：城市ID
   * 变量名：city_id
   * 必填：否
   * 类型：string
   * 描述：城市ID
   * </pre>
   */
  @SerializedName("city_id")
  public String cityId;

  /**
   * <pre>
   * 字段名：医疗机构名称
   * 变量名：med_inst_name
   * 必填：否
   * 类型：string
   * 描述：医疗机构名称
   * </pre>
   */
  @SerializedName("med_inst_name")
  public String medInstName;

  /**
   * <pre>
   * 字段名：医疗机构编号
   * 变量名：med_inst_no
   * 必填：否
   * 类型：string
   * 描述：医疗机构编号
   * </pre>
   */
  @SerializedName("med_inst_no")
  public String medInstNo;

  /**
   * <pre>
   * 字段名：医保订单创建时间
   * 变量名：med_ins_order_create_time
   * 必填：否
   * 类型：string
   * 描述：医保订单创建时间
   * </pre>
   */
  @SerializedName("med_ins_order_create_time")
  public String medInsOrderCreateTime;

  /**
   * <pre>
   * 字段名：总金额
   * 变量名：total_fee
   * 必填：否
   * 类型：Integer
   * 描述：总金额
   * </pre>
   */
  @SerializedName("total_fee")
  public Integer totalFee;

  /**
   * <pre>
   * 字段名：医保统筹基金支付金额
   * 变量名：med_ins_gov_fee
   * 必填：否
   * 类型：Integer
   * 描述：医保统筹基金支付金额
   * </pre>
   */
  @SerializedName("med_ins_gov_fee")
  public Integer medInsGovFee;

  /**
   * <pre>
   * 字段名：医保个人账户支付金额
   * 变量名：med_ins_self_fee
   * 必填：否
   * 类型：Integer
   * 描述：医保个人账户支付金额
   * </pre>
   */
  @SerializedName("med_ins_self_fee")
  public Integer medInsSelfFee;

  /**
   * <pre>
   * 字段名：医保其他基金支付金额
   * 变量名：med_ins_other_fee
   * 必填：否
   * 类型：Integer
   * 描述：医保其他基金支付金额
   * </pre>
   */
  @SerializedName("med_ins_other_fee")
  public Integer medInsOtherFee;

  /**
   * <pre>
   * 字段名：医保现金支付金额
   * 变量名：med_ins_cash_fee
   * 必填：否
   * 类型：Integer
   * 描述：医保现金支付金额
   * </pre>
   */
  @SerializedName("med_ins_cash_fee")
  public Integer medInsCashFee;

  /**
   * <pre>
   * 字段名：微信支付现金支付金额
   * 变量名：wechat_pay_cash_fee
   * 必填：否
   * 类型：Integer
   * 描述：微信支付现金支付金额
   * </pre>
   */
  @SerializedName("wechat_pay_cash_fee")
  public Integer wechatPayCashFee;

  /**
   * <pre>
   * 字段名：现金增加明细
   * 变量名：cash_add_detail
   * 必填：否
   * 类型：list
   * 描述：现金增加明细
   * </pre>
   */
  @SerializedName("cash_add_detail")
  public List<MedInsOrdersRequest.CashAddEntity> cashAddDetail;

  /**
   * <pre>
   * 字段名：现金减少明细
   * 变量名：cash_reduce_detail
   * 必填：否
   * 类型：list
   * 描述：现金减少明细
   * </pre>
   */
  @SerializedName("cash_reduce_detail")
  public List<MedInsOrdersRequest.CashReduceEntity> cashReduceDetail;

  /**
   * <pre>
   * 字段名：回调URL
   * 变量名：callback_url
   * 必填：否
   * 类型：string
   * 描述：回调URL
   * </pre>
   */
  @SerializedName("callback_url")
  public String callbackUrl;

  /**
   * <pre>
   * 字段名：预支付交易会话标识
   * 变量名：prepay_id
   * 必填：否
   * 类型：string
   * 描述：预支付交易会话标识
   * </pre>
   */
  @SerializedName("prepay_id")
  public String prepayId;

  /**
   * <pre>
   * 字段名：透传请求内容
   * 变量名：passthrough_request_content
   * 必填：否
   * 类型：string
   * 描述：透传请求内容
   * </pre>
   */
  @SerializedName("passthrough_request_content")
  public String passthroughRequestContent;

  /**
   * <pre>
   * 字段名：扩展字段
   * 变量名：extends
   * 必填：否
   * 类型：string
   * 描述：扩展字段
   * </pre>
   */
  @SerializedName("extends")
  public String _extends;

  /**
   * <pre>
   * 字段名：附加数据
   * 变量名：attach
   * 必填：否
   * 类型：string
   * 描述：附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用
   * </pre>
   */
  @SerializedName("attach")
  public String attach;

  /**
   * <pre>
   * 字段名：渠道编号
   * 变量名：channel_no
   * 必填：否
   * 类型：string
   * 描述：渠道编号
   * </pre>
   */
  @SerializedName("channel_no")
  public String channelNo;

  /**
   * <pre>
   * 字段名：医保测试环境标识
   * 变量名：med_ins_test_env
   * 必填：否
   * 类型：boolean
   * 描述：医保测试环境标识
   * </pre>
   */
  @SerializedName("med_ins_test_env")
  public Boolean medInsTestEnv;
}
