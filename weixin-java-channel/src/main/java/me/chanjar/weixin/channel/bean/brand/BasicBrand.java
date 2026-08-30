package me.chanjar.weixin.channel.bean.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基础品牌信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class BasicBrand implements Serializable {

  private static final long serialVersionUID = -1991771439710177859L;

  /** 品牌库中的品牌编号(Long) */
  @JsonProperty("brand_id")
  private String brandId;

  /** 品牌商标中文名 */
  @JsonProperty("ch_name")
  private String chName;

  /** 品牌商标英文名 */
  @JsonProperty("en_name")
  private String enName;
}
