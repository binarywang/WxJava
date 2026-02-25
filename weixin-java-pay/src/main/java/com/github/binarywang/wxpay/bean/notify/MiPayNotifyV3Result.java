package com.github.binarywang.wxpay.bean.notify;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * 医保混合收款成功通知结果
 * 文档地址：https://pay.weixin.qq.com/doc/v3/partner/4012165722
 * </pre>
 *
 * @author xgl
 * @since 2025/12/20
 */
@Data
@NoArgsConstructor
public class MiPayNotifyV3Result implements Serializable, WxPayBaseNotifyV3Result<MiPayNotifyV3Result.DecryptNotifyResult> {

    /**
     * 源数据
     */
    private OriginNotifyResponse rawData;

    /**
     * 解密后的数据
     */
    private DecryptNotifyResult result;

    @Data
    @NoArgsConstructor
    public static class DecryptNotifyResult implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * <pre>
         * 字段名：应用ID
         * 变量名：appid
         * 是否必填：是
         * 类型：string(32)
         * 描述：
         *   从业机构/服务商的公众号ID
         * </pre>
         */
        @SerializedName(value = "appid")
        private String appid;

        /**
         * <pre>
         * 字段名：医疗机构的公众号ID
         * 变量名：sub_appid
         * 是否必填：是
         * 类型：string(32)
         * 描述：
         *   医疗机构的公众号ID
         * </pre>
         */
        @SerializedName(value = "sub_appid")
        private String subAppid;

        /**
         * <pre>
         * 字段名：医疗机构的商户号
         * 变量名：sub_mchid
         * 是否必填：是
         * 类型：string(32)
         * 描述：
         *   医疗机构的商户号
         * </pre>
         */
        @SerializedName(value = "sub_mchid")
        private String subMchid;

        /**
         * <pre>
         * 字段名：从业机构订单号
         * 变量名：out_trade_no
         * 是否必填：是
         * 类型：string(64)
         * 描述：
         *   从业机构/服务商订单号
         * </pre>
         */
        @SerializedName(value = "out_trade_no")
        private String outTradeNo;

        /**
         * <pre>
         * 字段名：医保自费混合订单号
         * 变量名：mix_trade_no
         * 是否必填：是
         * 类型：string(32)
         * 描述：
         *   微信支付系统生成的医保自费混合订单号
         * </pre>
         */
        @SerializedName(value = "mix_trade_no")
        private String mixTradeNo;

        /**
         * <pre>
         * 字段名：医保自费混合订单支付状态
         * 变量名：mix_pay_status
         * 是否必填：是
         * 类型：string
         * 描述：
         *   医保自费混合订单支付状态，枚举值：
         *   UNKNOWN_MIX_PAY_STATUS：未知类型，需报错
         *   MIX_PAY_CREATED：等待支付
         *   MIX_PAY_SUCCESS：支付成功
         *   MIX_PAY_REFUND：自费和医保均已退款
         *   MIX_PAY_FAIL：支付失败
         * </pre>
         */
        @SerializedName(value = "mix_pay_status")
        private String mixPayStatus;

        /**
         * <pre>
         * 字段名：自费部分的支付状态
         * 变量名：self_pay_status
         * 是否必填：否
         * 类型：string
         * 描述：
         *   混合订单中自费部分的支付状态，枚举值：
         *   UNKNOWN_SELF_PAY_STATUS：未知类型，需报错
         *   SELF_PAY_CREATED：等待支付
         *   SELF_PAY_SUCCESS：支付成功
         *   SELF_PAY_REFUND：已退款
         *   SELF_PAY_FAIL：支付失败
         *   NO_SELF_PAY：没有自费
         * </pre>
         */
        @SerializedName(value = "self_pay_status")
        private String selfPayStatus;

        /**
         * <pre>
         * 字段名：医保部分的支付状态
         * 变量名：med_ins_pay_status
         * 是否必填：否
         * 类型：string
         * 描述：
         *   混合订单中医保部分的支付状态，枚举值：
         *   UNKNOWN_MED_INS_PAY_STATUS：未知类型，需报错
         *   MED_INS_PAY_CREATED：等待支付
         *   MED_INS_PAY_SUCCESS：支付成功
         *   MED_INS_PAY_REFUND：已退款
         *   MED_INS_PAY_FAIL：支付失败
         *   NO_MED_INS_PAY：没有医保
         * </pre>
         */
        @SerializedName(value = "med_ins_pay_status")
        private String medInsPayStatus;

        /**
         * <pre>
         * 字段名：订单支付时间
         * 变量名：paid_time
         * 是否必填：否
         * 类型：string(64)
         * 描述：
         *   订单支付时间，遵循rfc3339标准格式
         * </pre>
         */
        @SerializedName(value = "paid_time")
        private String paidTime;

        /**
         * <pre>
         * 字段名：医保局返回内容
         * 变量名：passthrough_response_content
         * 是否必填：否
         * 类型：string(2048)
         * 描述：
         *   支付完成后医保局返回内容（透传给医疗机构）
         * </pre>
         */
        @SerializedName(value = "passthrough_response_content")
        private String passthroughResponseContent;

        /**
         * <pre>
         * 字段名：混合支付类型
         * 变量名：mix_pay_type
         * 是否必填：是
         * 类型：string
         * 描述：
         *   混合支付类型，枚举值：
         *   UNKNOWN_MIX_PAY_TYPE：未知类型，需报错
         *   CASH_ONLY：纯自费
         *   INSURANCE_ONLY：纯医保
         *   CASH_AND_INSURANCE：医保自费混合
         * </pre>
         */
        @SerializedName(value = "mix_pay_type")
        private String mixPayType;

        /**
         * <pre>
         * 字段名：订单类型
         * 变量名：order_type
         * 是否必填：否
         * 类型：string
         * 描述：
         *   订单类型，枚举值：
         *   UNKNOWN_ORDER_TYPE：未知类型，需报错
         *   REG_PAY：挂号支付
         *   DIAG_PAY：诊间支付
         *   COVID_EXAM_PAY：新冠检测费用（核酸）
         *   IN_HOSP_PAY：住院费支付
         *   PHARMACY_PAY：药店支付
         *   INSURANCE_PAY：保险费支付
         *   INT_REG_PAY：互联网医院挂号支付
         *   INT_RE_DIAG_PAY：互联网医院复诊支付
         *   INT_RX_PAY：互联网医院处方支付
         *   COVID_ANTIGEN_PAY：新冠抗原检测
         *   MED_PAY：药费支付
         * </pre>
         */
        @SerializedName(value = "order_type")
        private String orderType;

        /**
         * <pre>
         * 字段名：用户标识
         * 变量名：openid
         * 是否必填：否
         * 类型：string(128)
         * 描述：
         *   用户在appid下的唯一标识
         * </pre>
         */
        @SerializedName(value = "openid")
        private String openid;

        /**
         * <pre>
         * 字段名：用户子标识
         * 变量名：sub_openid
         * 是否必填：否
         * 类型：string(128)
         * 描述：
         *   用户在sub_appid下的唯一标识
         * </pre>
         */
        @SerializedName(value = "sub_openid")
        private String subOpenid;

        /**
         * <pre>
         * 字段名：是否代亲属支付
         * 变量名：pay_for_relatives
         * 是否必填：否
         * 类型：bool
         * 描述：
         *   是否代亲属支付，不传默认替本人支付
         * </pre>
         */
        @SerializedName(value = "pay_for_relatives")
        private Boolean payForRelatives;

        /**
         * <pre>
         * 字段名：医疗机构订单号
         * 变量名：serial_no
         * 是否必填：否
         * 类型：string(20)
         * 描述：
         *   医疗机构订单号
         * </pre>
         */
        @SerializedName(value = "serial_no")
        private String serialNo;

        /**
         * <pre>
         * 字段名：医保局支付单ID
         * 变量名：pay_order_id
         * 是否必填：否
         * 类型：string(64)
         * 描述：
         *   医保局返回的支付单ID
         * </pre>
         */
        @SerializedName(value = "pay_order_id")
        private String payOrderId;

        /**
         * <pre>
         * 字段名：医保局支付授权码
         * 变量名：pay_auth_no
         * 是否必填：否
         * 类型：string(40)
         * 描述：
         *   医保局返回的支付授权码
         * </pre>
         */
        @SerializedName(value = "pay_auth_no")
        private String payAuthNo;

        /**
         * <pre>
         * 字段名：用户定位信息
         * 变量名：geo_location
         * 是否必填：否
         * 类型：string(40)
         * 描述：
         *   用户定位信息，经纬度。格式：经度,纬度
         * </pre>
         */
        @SerializedName(value = "geo_location")
        private String geoLocation;

        /**
         * <pre>
         * 字段名：城市ID
         * 变量名：city_id
         * 是否必填：是
         * 类型：string(8)
         * 描述：
         *   城市ID
         * </pre>
         */
        @SerializedName(value = "city_id")
        private String cityId;

        /**
         * <pre>
         * 字段名：医疗机构名称
         * 变量名：med_inst_name
         * 是否必填：是
         * 类型：string(128)
         * 描述：
         *   医疗机构名称
         * </pre>
         */
        @SerializedName(value = "med_inst_name")
        private String medInstName;

        /**
         * <pre>
         * 字段名：医疗机构编码
         * 变量名：med_inst_no
         * 是否必填：是
         * 类型：string(32)
         * 描述：
         *   医疗机构编码
         * </pre>
         */
        @SerializedName(value = "med_inst_no")
        private String medInstNo;

        /**
         * <pre>
         * 字段名：微信支付订单号
         * 变量名：transaction_id
         * 是否必填：是
         * 类型：string(32)
         * 描述：
         *   微信支付系统生成的订单号
         * </pre>
         */
        @SerializedName(value = "transaction_id")
        private String transactionId;

        /**
         * <pre>
         * 字段名：医保订单创建时间
         * 变量名：med_ins_order_create_time
         * 是否必填：是
         * 类型：string(64)
         * 描述：
         *   医保订单创建时间，遵循rfc3339标准格式
         * </pre>
         */
        @SerializedName(value = "med_ins_order_create_time")
        private String medInsOrderCreateTime;

        /**
         * <pre>
         * 字段名：医保订单完成时间
         * 变量名：med_ins_order_finish_time
         * 是否必填：是
         * 类型：string(64)
         * 描述：
         *   医保订单完成时间，遵循rfc3339标准格式
         * </pre>
         */
        @SerializedName(value = "med_ins_order_finish_time")
        private String medInsOrderFinishTime;

        /**
         * <pre>
         * 字段名：总金额
         * 变量名：total_fee
         * 是否必填：否
         * 类型：long
         * 描述：
         *   总金额，单位为分
         * </pre>
         */
        @SerializedName(value = "total_fee")
        private Long totalFee;

        /**
         * <pre>
         * 字段名：医保统筹基金支付金额
         * 变量名：med_ins_gov_fee
         * 是否必填：否
         * 类型：long
         * 描述：
         *   医保统筹基金支付金额，单位为分
         * </pre>
         */
        @SerializedName(value = "med_ins_gov_fee")
        private Long medInsGovFee;

        /**
         * <pre>
         * 字段名：医保个人账户支付金额
         * 变量名：med_ins_self_fee
         * 是否必填：否
         * 类型：long
         * 描述：
         *   医保个人账户支付金额，单位为分
         * </pre>
         */
        @SerializedName(value = "med_ins_self_fee")
        private Long medInsSelfFee;

        /**
         * <pre>
         * 字段名：医保其他基金支付金额
         * 变量名：med_ins_other_fee
         * 是否必填：否
         * 类型：long
         * 描述：
         *   医保其他基金支付金额，单位为分
         * </pre>
         */
        @SerializedName(value = "med_ins_other_fee")
        private Long medInsOtherFee;

        /**
         * <pre>
         * 字段名：医保现金支付金额
         * 变量名：med_ins_cash_fee
         * 是否必填：否
         * 类型：long
         * 描述：
         *   医保现金支付金额，单位为分
         * </pre>
         */
        @SerializedName(value = "med_ins_cash_fee")
        private Long medInsCashFee;

        /**
         * <pre>
         * 字段名：微信支付现金支付金额
         * 变量名：wechat_pay_cash_fee
         * 是否必填：否
         * 类型：long
         * 描述：
         *   微信支付现金支付金额，单位为分
         * </pre>
         */
        @SerializedName(value = "wechat_pay_cash_fee")
        private Long wechatPayCashFee;

        /**
         * <pre>
         * 字段名：附加数据
         * 变量名：attach
         * 是否必填：否
         * 类型：string(128)
         * 描述：
         *   附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用
         * </pre>
         */
        @SerializedName(value = "attach")
        private String attach;

        /**
         * <pre>
         * 字段名：支付状态
         * 变量名：trade_state
         * 是否必填：是
         * 类型：string(32)
         * 描述：
         *   交易状态，枚举值：
         *   SUCCESS：支付成功
         *   REFUND：转入退款
         *   NOTPAY：未支付
         *   CLOSED：已关闭
         *   REVOKED：已撤销
         *   USERPAYING：用户支付中
         *   PAYERROR：支付失败
         * </pre>
         */
        @SerializedName(value = "trade_state")
        private String tradeState;

        /**
         * <pre>
         * 字段名：支付状态描述
         * 变量名：trade_state_desc
         * 是否必填：是
         * 类型：string(256)
         * 描述：
         *   交易状态描述
         * </pre>
         */
        @SerializedName(value = "trade_state_desc")
        private String tradeStateDesc;
    }
}
