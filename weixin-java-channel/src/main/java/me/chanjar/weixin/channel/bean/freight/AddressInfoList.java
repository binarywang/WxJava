package me.chanjar.weixin.channel.bean.freight;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.AddressInfo;

/**
 * 地址列表
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class AddressInfoList implements Serializable {

  private static final long serialVersionUID = 5923805297331862706L;
  /** 地址列表 */
  @JsonProperty("address_infos")
  private List<AddressInfo> addressInfos;
}
