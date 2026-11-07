package me.chanjar.weixin.channel.bean.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 地址id 请求参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.address.AddressIdParam}。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Deprecated
public class AddressIdParam implements Serializable {

  private static final long serialVersionUID = -7001183932180608746L;

  /** 地址id */
  @JsonProperty("address_id")
  private String addressId;
}
