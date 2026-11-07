package me.chanjar.weixin.channel.bean.message.fund;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 提现通知信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.message.fund.WithdrawNotifyInfo}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class WithdrawNotifyInfo implements Serializable {

  private static final long serialVersionUID = 2987401114254821956L;
  /** 1.发起提现，生成二维码 2.扫码验证成功，申请提现 3.提现成功 4.提现失败 */
  @JsonProperty("event")
  @JacksonXmlProperty(localName = "event")
  private Integer event;

  /** 提现单号 */
  @JsonProperty("withdraw_id")
  @JacksonXmlProperty(localName = "withdraw_id")
  private String withdrawId;

}
