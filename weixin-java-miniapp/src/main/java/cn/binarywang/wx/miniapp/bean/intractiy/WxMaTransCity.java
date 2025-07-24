package cn.binarywang.wx.miniapp.bean.intractiy;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class WxMaTransCity {
  private String serviceTransId;
  private List<City> cityList;

  public String getServiceTransId() {
    return serviceTransId;
  }

  public void setServiceTransId(String serviceTransId) {
    this.serviceTransId = serviceTransId;
  }

  public List<City> getCityList() {
    return cityList;
  }

  public void setCityList(List<City> cityList) {
    this.cityList = cityList;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  public static class City {
    private String cityName;
    private String cityCode;

    public String getCityName() {
      return cityName;
    }

    public void setCityName(String cityName) {
      this.cityName = cityName;
    }

    public String getCityCode() {
      return cityCode;
    }

    public void setCityCode(String cityCode) {
      this.cityCode = cityCode;
    }

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this);
    }
  }
}
