package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.after.GuaranteeMerchantModifyParam}。
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class GuaranteeMerchantModifyParam extends GuaranteeOrderIdParam {
  private static final long serialVersionUID = 9193536167701367687L;

  @JsonProperty("bad_level")
  private Integer badLevel;

  @JsonProperty("merchant_remark")
  private String merchantRemark;
}
