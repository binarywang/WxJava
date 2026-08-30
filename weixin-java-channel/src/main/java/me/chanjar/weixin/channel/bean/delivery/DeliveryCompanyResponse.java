package me.chanjar.weixin.channel.bean.delivery;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 快递公司列表响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class DeliveryCompanyResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -7695903997951385166L;
  /** 快递公司 */
  @JsonProperty("company_list")
  private List<DeliveryCompanyInfo> companyList;
}
