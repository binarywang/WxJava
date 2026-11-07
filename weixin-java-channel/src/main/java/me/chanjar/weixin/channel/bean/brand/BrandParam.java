package me.chanjar.weixin.channel.bean.brand;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 品牌参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.brand.BrandParam}。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class BrandParam implements Serializable {

  private static final long serialVersionUID = -4894709391464428613L;

  /** 品牌信息 */
  @JsonProperty("brand")
  private Brand brand;
}
