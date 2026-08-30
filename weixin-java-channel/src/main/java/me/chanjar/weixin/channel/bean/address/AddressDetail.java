package me.chanjar.weixin.channel.bean.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.AddressInfo;

/**
 * 用户地址
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class AddressDetail implements Serializable {

  private static final long serialVersionUID = -7839578838482198641L;

  /** 地址id */
  @JsonProperty("address_id")
  private String addressId;

  /** 联系人姓名 */
  @JsonProperty("name")
  private String name;

  /** 地区信息 */
  @JsonProperty("address_info")
  private AddressInfo addressInfo;

  /** 座机 */
  @JsonProperty("landline")
  private String landline;

  /** 是否为发货地址 */
  @JsonProperty("send_addr")
  private Boolean sendAddr;

  /** 是否为收货地址 */
  @JsonProperty("recv_addr")
  private Boolean recvAddr;

  /** 是否为默认发货地址 */
  @JsonProperty("default_send")
  private Boolean defaultSend;

  /** 是否为默认收货地址 */
  @JsonProperty("default_recv")
  private Boolean defaultRecv;

  /** 创建时间戳（秒) */
  @JsonProperty("create_time")
  private Long createTime;

  /** 更新时间戳（秒） */
  @JsonProperty("update_time")
  private Long updateTime;

  /** 线下配送地址类型 */
  @JsonProperty("address_type")
  private OfflineAddressType addressType;

}
