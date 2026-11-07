package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.after.ExchangeSkuInfo}。
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class ExchangeSkuInfo implements Serializable {
  private static final long serialVersionUID = 1L;
  @JsonProperty("new_sku_id")
  private String newSkuId;
}
