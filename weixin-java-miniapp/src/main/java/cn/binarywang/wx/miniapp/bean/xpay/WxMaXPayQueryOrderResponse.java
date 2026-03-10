package cn.binarywang.wx.miniapp.bean.xpay;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaXPayQueryOrderResponse extends WxMaBaseResponse implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;
  @SerializedName("order")
  private OrderInfo order;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

  @Data
  public static class OrderInfo {
    @SerializedName("order_id")
    private String orderId;
    @SerializedName("create_time")
    private Long createTime;
    @SerializedName("update_time")
    private Long updateTime;
    @SerializedName("status")
    private Integer status;
    @SerializedName("biz_type")
    private Integer bizType;
    @SerializedName("order_fee")
    private Long orderFee;
    @SerializedName("coupon_fee")
    private Long couponFee;
    @SerializedName("paid_fee")
    private Long paidFee;
    @SerializedName("order_type")
    private Integer orderType;
    @SerializedName("refund_fee")
    private Long refundFee;
    @SerializedName("paid_time")
    private Long paidTime;//unix秒级时间戳
    @SerializedName("provide_time")
    private Long provideTime;
    @SerializedName("env_type")
    private Long envType;
    @SerializedName("biz_meta")
    private String bizMeta;
    @SerializedName("token")
    private String token;

    @SerializedName("leftFee")
    private Long leftFee; //支付单类型时表示此单经过退款还剩余的金额，单位分
    @SerializedName("wxOrderId")
    private String wxOrderId;

    /** 渠道单号，为用户微信支付详情页面上的商户单号 */
    @SerializedName("channel_order_id")
    private String channelOrderId;
    /** 微信支付交易单号，为用户微信支付详情页面上的交易单号 */
    @SerializedName("wxpay_order_id")
    private String wxpayOrderId;
    /** 结算时间的秒级时间戳，大于0表示结算成功 */
    @SerializedName("sett_time")
    private Long settTime;
    /** 结算状态：0-未开始结算 1-结算中 2-结算成功 3-待结算（与0相同） */
    @SerializedName("sett_state")
    private Integer settState;
    /** 虚拟支付技术服务费，单位为分；sett_state = 2时返回 */
    @SerializedName("platform_fee_fen")
    private Long platformFeeFen;
    /** 公众号、视频号平台的cps服务费，单位为分；sett_state = 2时返回 */
    @SerializedName("cps_fee_fen")
    private Long cpsFeeFen;

  }
}
