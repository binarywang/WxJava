package me.chanjar.weixin.channel.bean.fund.bank;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 银行城市信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class BankCityInfo implements Serializable {

  private static final long serialVersionUID = 374087891799491196L;
  /** 城市名称 */
  @JsonProperty("city_name")
  private String cityName;

  /** 城市编号 */
  @JsonProperty("city_code")
  private Integer cityCode;

  /** 开户银行省市编码 */
  @JsonProperty("bank_address_code")
  private String bankAddressCode;
}
