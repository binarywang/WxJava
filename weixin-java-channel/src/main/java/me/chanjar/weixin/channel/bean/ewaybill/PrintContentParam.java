package me.chanjar.weixin.channel.bean.ewaybill;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 获取电子面单打印报文请求参数。 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrintContentParam implements Serializable {
  private static final long serialVersionUID = 6898522842175667816L;

  @JsonProperty("waybill_ids")
  private List<String> waybillIds;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("template_id")
  private String templateId;
}
