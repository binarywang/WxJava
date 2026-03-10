package cn.binarywang.wx.miniapp.bean.xpay;

import com.google.gson.annotations.SerializedName;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 虚拟支付拼团信息.
 * 用于 xpay_goods_deliver_notify、xpay_refund_notify 等推送事件
 */
@Data
@NoArgsConstructor
@XStreamAlias("TeamInfo")
public class WxMaXPayTeamInfo implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 活动id.
   */
  @SerializedName("ActivityId")
  @XStreamAlias("ActivityId")
  private String activityId;

  /**
   * 团id.
   */
  @SerializedName("TeamId")
  @XStreamAlias("TeamId")
  private String teamId;

  /**
   * 团类型.
   * 1-支付全部，拼成退款
   */
  @SerializedName("TeamType")
  @XStreamAlias("TeamType")
  private Integer teamType;

  /**
   * 0-创团 1-参团.
   */
  @SerializedName("TeamAction")
  @XStreamAlias("TeamAction")
  private Integer teamAction;
}
