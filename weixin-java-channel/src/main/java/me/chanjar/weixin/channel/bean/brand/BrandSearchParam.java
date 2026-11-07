package me.chanjar.weixin.channel.bean.brand;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.channel.bean.base.StreamPageParam;

/**
 * 品牌搜索参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.brand.BrandSearchParam}。
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class BrandSearchParam extends StreamPageParam {

  private static final long serialVersionUID = 5961201403338269712L;
  /** 审核单状态, 不填默认拉全部商品 */
  @JsonProperty("status")
  private Integer status;

  public BrandSearchParam() {
  }

  public BrandSearchParam(Integer pageSize, String nextKey, Integer status) {
    super(pageSize, nextKey);
    this.status = status;
  }
}
