package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GuaranteeInfo implements Serializable {
  private static final long serialVersionUID = 1L;
  @JsonProperty("guarantee_id")
  private String guaranteeId;
}
