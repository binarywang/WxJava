package me.chanjar.weixin.channel.bean.home.banner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 精选展示位明细中的明细
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BannerItemDetail implements Serializable {

  private static final long serialVersionUID = 5975434996207526173L;

  /** 图片url */
  @JsonProperty("img_url")
  private String imgUrl;

  /** 标题 */
  @JsonProperty("title")
  private String title;

  /** 描述 */
  @JsonProperty("description")
  private String description;
}
