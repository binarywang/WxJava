package com.binarywang.wxjava.store.bean.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 礼物订单新增备注信息 请求参数
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PresentNoteAddParam implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 礼物订单ID */
  @JsonProperty("order_id")
  private String orderId;

  /** 备注内容 */
  @JsonProperty("note")
  private String note;

}
