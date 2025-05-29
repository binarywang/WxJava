package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 直播列表数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class LiveItem implements Serializable {

  private static final long serialVersionUID = 6693176992531666035L;

  /**
   * 直播唯一ID
   */
  @JsonProperty("export_id")
  private String exportId;

  /**
   * 直播创建时间
   */
  @JsonProperty("create_time")
  private Long createTime;

}
