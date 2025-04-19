package me.chanjar.weixin.channel.bean.home.banner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 精选展示位明细中的视频号数据
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BannerItemFinder implements Serializable {

  private static final long serialVersionUID = -7397790079913284012L;

  /** 视频号ID */
  @JsonProperty("finder_user_name")
  private String finderUserName;

  /** 视频号视频的唯一标识 */
  @JsonProperty("feed_id")
  private String feedId;
}
