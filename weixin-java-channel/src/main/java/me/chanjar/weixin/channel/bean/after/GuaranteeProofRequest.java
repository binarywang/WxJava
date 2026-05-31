package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 商家举证保障单请求参数.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class GuaranteeProofRequest extends GuaranteeOrderIdParam {

  private static final long serialVersionUID = -4521077589624343197L;

  /** 举证图片media_id列表. */
  @JsonProperty("media_ids")
  private List<String> mediaIds;

  /** 举证描述. */
  @JsonProperty("desc")
  private String desc;

  public GuaranteeProofRequest(String guaranteeOrderId, List<String> mediaIds, String desc) {
    super(guaranteeOrderId);
    this.mediaIds = mediaIds;
    this.desc = desc;
  }
}
