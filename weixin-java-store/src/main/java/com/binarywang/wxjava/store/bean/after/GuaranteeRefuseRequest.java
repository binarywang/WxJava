package com.binarywang.wxjava.store.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 商家拒绝保障单请求参数。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class GuaranteeRefuseRequest extends GuaranteeOrderIdParam {

  private static final long serialVersionUID = -6905594717805091393L;

  /** 拒绝原因。 */
  @JsonProperty("reason")
  private String reason;

  /** 拒绝凭证图片 media_id 列表。 */
  @JsonProperty("pic_list")
  private List<String> picList;

  public GuaranteeRefuseRequest(String guaranteeOrderId, String reason, List<String> picList) {
    super(guaranteeOrderId);
    this.reason = reason;
    this.picList = picList;
  }
}
