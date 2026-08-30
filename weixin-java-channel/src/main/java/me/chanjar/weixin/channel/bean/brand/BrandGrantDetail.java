package me.chanjar.weixin.channel.bean.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商标授权信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class BrandGrantDetail implements Serializable {

  private static final long serialVersionUID = 3537812707384823606L;

  /** 品牌销售授权书的file_id, 授权品牌必填, 限制最多传9张, 需要先调用“资质上传”接口上传资质图片 */
  @JsonProperty("grant_certifications")
  private List<String> grantCertifications;

  /** 授权级数, 授权品牌必填, 取值1-3 */
  @JsonProperty("grant_level")
  private Integer grantLevel;

  /** 授权有效期, 开始时间, 长期有效可不填 */
  @JsonProperty("start_time")
  private Long startTime;

  /** 授权有效期, 结束时间, 长期有效可不填 */
  @JsonProperty("end_time")
  private Long endTime;

  /** 是否长期有效 */
  @JsonProperty("is_permanent")
  private boolean permanent;

  /** 品牌权利人证件照的file_id, 限制最多传2张, 需要先调用“资质上传”接口上传资质图片 */
  @JsonProperty("brand_owner_id_photos")
  private List<String> brandOwnerIdPhotos;

}
