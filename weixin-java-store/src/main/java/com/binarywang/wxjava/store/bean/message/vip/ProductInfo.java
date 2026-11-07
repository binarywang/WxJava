package com.binarywang.wxjava.store.bean.message.vip;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商品信息
 *
 * @author <a href="https://github.com/asushiye">asushiye</a>
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ProductInfo implements Serializable {

  private static final long serialVersionUID = -3037180342360944232L;
  /** 兑换的商品ID**/
  @JsonProperty("related_product_id")
  @JacksonXmlProperty(localName = "related_product_id")
  private Long relatedProductId;
}
