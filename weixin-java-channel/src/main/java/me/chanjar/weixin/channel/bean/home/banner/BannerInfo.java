package me.chanjar.weixin.channel.bean.home.banner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 精选展示位
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BannerInfo implements Serializable {

  private static final long serialVersionUID = -2003175482038217418L;

  /** 展示位的展示样式 1-小图模式；2-大图模式 */
  @JsonProperty("scale")
  private Integer scale;

  /** 精选展示位明细 */
  @JsonProperty("banner")
  private List<BannerItem> banner;

}
