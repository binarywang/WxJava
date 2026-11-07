package me.chanjar.weixin.channel.bean.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商标申请信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.brand.BrandApplicationDetail}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class BrandApplicationDetail implements Serializable {

  private static final long serialVersionUID = 2145344855482129473L;

  /** 商标申请受理时间, TM标时必填 */
  @JsonProperty("acceptance_time")
  private Long acceptanceTime;

  /** 商标注册申请受理书file_id, TM标时必填, 限制最多传1张, 需要先调用“资质上传”接口上传资质图片 */
  @JsonProperty("acceptance_certification")
  private List<String> acceptanceCertification;

  /** 商标申请号, TM标时必填 */
  @JsonProperty("acceptance_no")
  private String acceptanceNo;
}
