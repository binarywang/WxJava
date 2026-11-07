package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 运费信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.product.ExpressInfo}。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class ExpressInfo implements Serializable {

  private static final long serialVersionUID = 3274035362148612426L;

  /** 运费模板ID（先通过获取运费模板接口merchant/getfreighttemplatelist拿到），若deliver_method=1，则不用填写 */
  @JsonProperty("template_id")
  private String templateId;

  /** 商品重量，单位克，若当前运费模版计价方式为[按重量]，则必填 */
  @JsonProperty("weight")
  private Integer weight;
}
