package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AfterSaleHandleFastExchangeReceiptParam extends AfterSaleIdParam {
  private static final long serialVersionUID = 5430106715116197677L;

  @JsonProperty("act")
  private Integer act;

  @JsonProperty("reject_reason")
  private String rejectReason;

  @JsonProperty("reject_reason_type")
  private Integer rejectReasonType;

  @JsonProperty("merchant_text")
  private String merchantText;

  @JsonProperty("reject_confirm_exchange")
  private List<String> rejectConfirmExchange;
}
