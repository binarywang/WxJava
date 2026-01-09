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
