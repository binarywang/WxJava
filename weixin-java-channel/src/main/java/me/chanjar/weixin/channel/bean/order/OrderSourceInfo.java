package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单带货来源信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class OrderSourceInfo implements Serializable {

  private static final long serialVersionUID = 3131907659419977296L;

  /**
   * sku_id
   */
  @JsonProperty("sku_id")
  private String skuId;

  /**
   * 带货账号类型，1：视频号，2：公众号，3：小程序，4：企业微信，5：带货达人，6：服务号，1000：带货机构
   */
  @JsonProperty("account_type")
  private Integer accountType;

  /**
   * 带货账号id，取决于带货账号类型（分别为视频号id、公众号appid、小程序appid、企业微信id、带货达人appid、服务号appid、带货机构id）
   */
  @JsonProperty("account_id")
  private String accountId;

  /**
   * 账号关联类型，0：关联账号，1：合作账号，2：授权号，100：达人带货，101：带货机构推广
   */
  @JsonProperty("sale_channel")
  private Integer saleChannel;

  /**
   * 带货账号昵称
   */
  @JsonProperty("account_nickname")
  private String accountNickname;

  /**
   * 带货内容类型，1：企微成员转发
   */
  @JsonProperty("content_type")
  private String contentType;

  /**
   * 带货内容id，取决于带货内容类型（企微成员user_id）
   */
  @JsonProperty("content_id")
  private String contentId;

  /**
   * 自营推客推广的带货机构id
   */
  @JsonProperty("promoter_head_supplier_id")
  private String promoterHeadSupplierId;
}
