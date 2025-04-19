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
   * 带货账户类型，1：视频号，2：公众号，3：小程序
   */
  @JsonProperty("account_type")
  private Integer accountType;

  /**
   * 带货账户id，如果带货账户类型是视频号，此id为视频号id; 如果带货类型为 公众号/小程序， 此id 为对应 公众号/小程序 的appid
   */
  @JsonProperty("account_id")
  private String accountId;

  /**
   * 销售渠道， 0：关联账号，1：合作账号，100：联盟达人带货
   */
  @JsonProperty("sale_channel")
  private Integer saleChannel;

  /**
   * 带货账户昵称
   */
  @JsonProperty("account_nickname")
  private String accountNickname;
}
