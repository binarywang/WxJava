package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GuaranteeMerchantProofParam extends GuaranteeIdParam {
  private static final long serialVersionUID = -2365495841866160967L;

  @JsonProperty("proof_info")
  private GuaranteeProofInfo proofInfo;
}
