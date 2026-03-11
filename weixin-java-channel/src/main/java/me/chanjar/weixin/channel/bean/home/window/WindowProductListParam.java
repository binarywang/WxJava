package me.chanjar.weixin.channel.bean.home.window;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取主页展示商品列表 参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WindowProductListParam implements Serializable {

  /** 每页数量（默认10，不超过30） */
  @JsonProperty("page_size")
  private Integer pageSize;

  /** 由上次请求返回，记录翻页的上下文。传入时会从上次返回的结果往后翻一页，不传默认获取第一页数据。 */
  @JsonProperty("next_key")
  private String nextKey;
}
