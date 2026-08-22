package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GuaranteeProofInfo implements Serializable {
  private static final long serialVersionUID = 1L;
  @JsonProperty("text")
  private String text;
  @JsonProperty("image_ids")
  private List<String> imageIds;
}
