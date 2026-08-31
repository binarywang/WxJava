package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 商家举证保障单请求参数。
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.after.GuaranteeProofRequest}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
@Deprecated
public class GuaranteeProofRequest extends GuaranteeOrderIdParam {

  private static final long serialVersionUID = 6599721896742974275L;

  /** 举证内容。 */
  @JsonProperty("content")
  private String content;

  /** 举证图片 media_id 列表。 */
  @JsonProperty("pic_list")
  private List<String> picList;

  public GuaranteeProofRequest(String guaranteeOrderId, String content, List<String> picList) {
    super(guaranteeOrderId);
    this.content = content;
    this.picList = picList;
  }
}
