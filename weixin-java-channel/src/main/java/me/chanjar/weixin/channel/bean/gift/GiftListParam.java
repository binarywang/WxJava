package me.chanjar.weixin.channel.bean.gift;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.StreamPageParam;

/**
 * 获取赠品列表 请求参数
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 * @see <a href="https://developers.weixin.qq.com/doc/store/shop/API/channels-shop-product/gift/api_getgiftproductlist.html">获取赠品列表</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GiftListParam extends StreamPageParam {

  private static final long serialVersionUID = -1L;

  /**
   * 商品状态，不填默认拉全部商品（不包含回收站）。
   * 5: 上架；6: 回收站；11: 所有下架
   */
  @JsonProperty("status")
  private Integer status;

  public GiftListParam(Integer pageSize, String nextKey, Integer status) {
    this.pageSize = pageSize;
    this.nextKey = nextKey;
    this.status = status;
  }
}
