package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.after.GuaranteeMerchantProofParam}。
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class GuaranteeMerchantProofParam extends GuaranteeOrderIdParam {
  private static final long serialVersionUID = -2365495841866160967L;

  @JsonProperty("content")
  private String content;

  @JsonProperty("pic_list")
  private java.util.List<String> picList;
}
