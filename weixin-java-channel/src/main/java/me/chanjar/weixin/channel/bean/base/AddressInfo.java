package me.chanjar.weixin.channel.bean.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 地址信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Deprecated
public class AddressInfo implements Serializable {

  private static final long serialVersionUID = 6928300709804576100L;

  /** 收件人姓名 */
  @JsonProperty("user_name")
  private String userName;

  /** 收件人手机号码 */
  @JsonProperty("tel_number")
  private String telNumber;

  /** 邮编 */
  @JsonProperty("postal_code")
  private String postalCode;

  /** 省份 */
  @JsonProperty("province_name")
  private String provinceName;

  /** 城市 */
  @JsonProperty("city_name")
  private String cityName;

  /** 区 */
  @JsonProperty("county_name")
  private String countyName;

  /** 详细地址 */
  @JsonProperty("detail_info")
  private String detailInfo;

  /** 国家码 */
  @JsonProperty("national_code")
  private String nationalCode;

  /** 门牌号码 */
  @JsonProperty("house_number")
  private String houseNumber;

  /** 纬度 */
  @JsonProperty("lat")
  private Double lat;

  /** 经度 */
  @JsonProperty("lng")
  private Double lng;

  public AddressInfo(String provinceName, String cityName, String countyName) {
    this.provinceName = provinceName;
    this.cityName = cityName;
    this.countyName = countyName;
  }
}
