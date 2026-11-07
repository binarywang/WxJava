package me.chanjar.weixin.channel.bean.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 品牌注册信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.brand.BrandRegisterDetail}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class BrandRegisterDetail implements Serializable {

  private static final long serialVersionUID = 1169957179510362405L;

  /** 商标注册人, R标时必填 */
  @JsonProperty("registrant")
  private String registrant;

  /** 商标注册号, R标时必填 */
  @JsonProperty("register_no")
  private String registerNo;

  /** 商标注册有效期（时间戳秒）, 开始时间, 长期有效可不填 */
  @JsonProperty("start_time")
  private Long startTime;

  /** 商标注册有效期（时间戳秒）, 结束时间, 长期有效可不填 */
  @JsonProperty("end_time")
  private Long endTime;

  /** 是否长期有效 */
  @JsonProperty("is_permanent")
  private boolean permanent;

  /** 商标注册证的file_id, R标时必填, 限制最多传1张, 需要先调用“资质上传”接口上传资质图片 */
  @JsonProperty("register_certifications")
  private List<String> registerCertifications;

  /** 变更/续展证明的file_id, 限制最多传5张, 需要先调用“资质上传”接口上传资质图片 */
  @JsonProperty("renew_certifications")
  private List<String> renewCertifications;

}
