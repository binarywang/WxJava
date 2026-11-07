package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 全量售后原因
 *
 * @author lizhengwu
 * @date 2024/7/24
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.after.AfterSaleReason}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class AfterSaleReason implements Serializable {

  private static final long serialVersionUID = -3674527884494606230L;

  /**
   * 售后原因枚举
   */
  @JsonProperty("reason")
  private Integer reason;

  /**
   * 售后原因说明
   */
  @JsonProperty("reason_text")
  private String reasonText;

}
