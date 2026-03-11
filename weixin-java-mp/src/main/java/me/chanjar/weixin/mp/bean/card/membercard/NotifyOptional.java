package me.chanjar.weixin.mp.bean.card.membercard;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 控制原生消息结构体，包含各字段的消息控制字段。
 * <p>
 * 用于 `7 更新会员信息` 的接口参数调用
 * </p>
 * <p>
 * {@code 参考：<a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1451025283">会员卡接口</a>}
 * </p>
 *
 * @author YuJian(mgcnrx11@gmail.com)
 * @version 2017/7/15
 */
@Data
public class NotifyOptional implements Serializable {
  private static final long serialVersionUID = 4488842021504939176L;

  /**
   * 积分变动时是否触发系统模板消息，默认为true
   */
  @SerializedName("is_notify_bonus")
  private Boolean isNotifyBonus;

  /**
   * 余额变动时是否触发系统模板消息，默认为true
   */
  @SerializedName("is_notify_balance")
  private Boolean isNotifyBalance;

  /**
   * 自定义group1变动时是否触发系统模板消息，默认为false。（2、3同理）
   */
  @SerializedName("is_notify_custom_field1")
  private Boolean isNotifyCustomField1;

  @SerializedName("is_notify_custom_field2")
  private Boolean isNotifyCustomField2;

  @SerializedName("is_notify_custom_field3")
  private Boolean isNotifyCustomField3;
}
