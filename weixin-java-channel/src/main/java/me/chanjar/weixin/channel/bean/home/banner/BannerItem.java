package me.chanjar.weixin.channel.bean.home.banner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 精选展示位明细
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BannerItem implements Serializable {

  private static final long serialVersionUID = 6982412458700854481L;

  /** 展示位类型 1-商品 3-视频号 4-公众号 {@link me.chanjar.weixin.channel.enums.BannerType} */
  @JsonProperty("type")
  private Integer type;

  /** 展示位信息 */
  @JsonProperty("banner")
  private BannerItemDetail banner;

  /** 商品 */
  @JsonProperty("product")
  private BannerItemProduct product;

  /** 视频号 */
  @JsonProperty("finder")
  private BannerItemFinder finder;

  /** 公众号 */
  @JsonProperty("official_account")
  private BannerItemOfficialAccount officialAccount;
}
