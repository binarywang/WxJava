package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品定制信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class OrderCustomInfo implements Serializable {
  private static final long serialVersionUID = 6681266835402157651L;

  /** 定制图片，custom_type=2时返回 */
  @JsonProperty("custom_img_url")
  private String customImgUrl;

  /** 定制文字，custom_type=1时返回 */
  @JsonProperty("custom_word")
  private String customWord;

  /** 定制类型，枚举值请参考CustomType枚举 */
  @JsonProperty("custom_type")
  private Integer customType;

  /** 定制预览图片，开启了定制预览时返回 */
  @JsonProperty("custom_preview_img_url")
  private String customPreviewImgUrl;
}
