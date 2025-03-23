package me.chanjar.weixin.channel.bean.compass;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 获取达人罗盘数据通用请求参数
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompassFinderBaseParam implements Serializable {

  private static final long serialVersionUID = - 4900361041041434435L;

  /**
   * 日期，格式 yyyyMMdd
   */
  @JsonProperty("ds")
  private String ds;

}
