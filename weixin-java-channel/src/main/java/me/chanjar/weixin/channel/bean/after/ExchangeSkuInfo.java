package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class ExchangeSkuInfo implements Serializable {
  private static final long serialVersionUID = 1L;
  @JsonProperty("new_sku_id")
  private String newSkuId;
}
