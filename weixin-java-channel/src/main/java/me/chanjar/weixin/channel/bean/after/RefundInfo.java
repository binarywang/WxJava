package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 退款信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.after.RefundInfo}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class RefundInfo implements Serializable {

  private static final long serialVersionUID = -6994243947898889309L;
  /** 退款金额（分） */
  @JsonProperty("amount")
  private Integer amount;

  /** 标明售后单退款直接原因, 枚举值详情请参考 {@link me.chanjar.weixin.channel.enums.RefundReason} */
  @JsonProperty("refund_reason")
  private Integer refundReason;
}
