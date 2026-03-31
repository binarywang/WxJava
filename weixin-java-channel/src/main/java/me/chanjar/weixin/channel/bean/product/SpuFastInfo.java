package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品免审更新参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpuFastInfo implements Serializable {

  /** 商品ID */
  @JsonProperty("product_id")
  protected String productId;

  /** SKU列表 */
  @JsonProperty("skus")
  protected List<SkuFastInfo> skus;

  /** 商品编码 */
  @JsonProperty("spu_code")
  protected String spuCode;

  /** 限购信息 */
  @JsonProperty("limit_info")
  protected LimitInfo limitInfo;

  /** 运费信息 */
  @JsonProperty("express_info")
  protected ExpressInfo expressInfo;

  /** 额外服务 */
  @JsonProperty("extra_service")
  protected ExtraServiceInfo extraService;

  /** 发货方式：0-快递发货；1-无需快递，手机号发货；3-无需快递，可选发货账号类型，默认为0，若为无需快递，则无需填写运费模版id */
  @JsonProperty("deliver_method")
  private Integer deliverMethod;

  /** 商品待开售信息 */
  @JsonProperty("timing_onsale_info")
  private TimingOnSaleInfo timingOnSaleInfo;

}
