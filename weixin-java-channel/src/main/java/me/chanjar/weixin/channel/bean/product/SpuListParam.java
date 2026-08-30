package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.chanjar.weixin.channel.bean.base.StreamPageParam;

/**
 * 商品列表查询参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@JsonInclude(Include.NON_NULL)
@Deprecated
public class SpuListParam extends StreamPageParam {

  private static final long serialVersionUID = -242932365961748404L;

  /** 商品状态 */
  @JsonProperty("status")
  private Integer status;

  public SpuListParam() {
  }

  public SpuListParam(Integer pageSize, String nextKey, Integer status) {
    this.pageSize = pageSize;
    this.nextKey = nextKey;
    this.status = status;
  }
}
