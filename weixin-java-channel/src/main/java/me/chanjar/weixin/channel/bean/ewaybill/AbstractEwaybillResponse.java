package me.chanjar.weixin.channel.bean.ewaybill;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 电子面单通用响应参数容器。
 *
 * <p>未显式声明字段将保存到 extra 字段，便于兼容官方接口变更。</p>
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractEwaybillResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -2460196179063989718L;

  @JsonIgnore private Map<String, Object> extra = new LinkedHashMap<>();

  @JsonAnySetter
  public void addExtra(String key, Object value) {
    extra.put(key, value);
  }
}
