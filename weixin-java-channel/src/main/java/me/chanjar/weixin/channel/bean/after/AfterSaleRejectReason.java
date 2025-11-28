package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 拒绝售后原因
 *
 * @author lizhengwu
 * @date 2024/7/24
 */
@Data
@NoArgsConstructor
public class AfterSaleRejectReason implements Serializable {

  private static final long serialVersionUID = -3672834150982780L;

  /**
   * 售后拒绝原因枚举
   */
  @JsonProperty("reject_reason_type")
  private Integer rejectReasonType;

  /**
   * 售后拒绝原因说明
   */
  @JsonProperty("reject_reason_type_text")
  private String rejectReasonTypeText;

  /**
   * 售后拒绝原因默认描述
   */
  @JsonProperty("reject_reason")
  private String rejectReason;

  /**
   * 售后拒绝原因适用场景
   */
  @JsonProperty("reject_scene")
  private Integer rejectScene;
}
