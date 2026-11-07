package me.chanjar.weixin.channel.bean.freight;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 运费模板 请求参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.freight.TemplateAddParam}。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Deprecated
public class TemplateAddParam implements Serializable {

  private static final long serialVersionUID = 2602919369418149309L;
  /** 起始位置 */
  @JsonProperty("freight_template")
  private FreightTemplate template;
}
