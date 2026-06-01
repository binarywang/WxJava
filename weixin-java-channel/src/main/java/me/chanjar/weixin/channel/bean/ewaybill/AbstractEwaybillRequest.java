package me.chanjar.weixin.channel.bean.ewaybill;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 电子面单通用请求参数容器。
 *
 * <p>字段按官方文档动态透传，避免非官方字段定义。</p>
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
public abstract class AbstractEwaybillRequest implements Serializable {

  private static final long serialVersionUID = 4213577159985597237L;

  private Map<String, Object> params = new LinkedHashMap<>();

  @JsonAnySetter
  public void addParam(String key, Object value) {
    params.put(key, value);
  }

  @JsonAnyGetter
  public Map<String, Object> anyParams() {
    return params;
  }
}
