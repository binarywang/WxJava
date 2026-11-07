package me.chanjar.weixin.channel.bean.ewaybill;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 面单标准模板编码请求参数。
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.ewaybill.TemplateCodeParam}。
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class TemplateCodeParam implements Serializable {
  private static final long serialVersionUID = 4473438799300843172L;

  @JsonProperty("template_code")
  private String templateCode;
}
