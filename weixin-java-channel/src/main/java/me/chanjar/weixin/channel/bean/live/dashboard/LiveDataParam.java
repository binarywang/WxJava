package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 获取直播大屏数据请求参数
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LiveDataParam implements Serializable {

  private static final long serialVersionUID = 6346941931704153857L;

  /**
   * 直播唯一ID
   */
  @JsonProperty("export_id")
  private String exportId;

}
