package cn.binarywang.wx.miniapp.bean.intractiy;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WxMaStore {
  private static final Logger logger = LoggerFactory.getLogger(WxMaStore.class);

  /** 微信分配的ID，创建时不用填写，查询时返回，根据此ID下单等 */
  private String wxStoreId;

  /** 自己设置的门店ID，创建时填写，查询时返回，不可修改 */
  private String outStoreId;

  /** 门店名称，创建时需要，可修改；查询结果微信不返回此字段 */
  private String storeName;

  /** 创建时不用设置，查询时返回，微信自动设置 */
  private String cityId;

  /** 1：价格优先，2：运力优先 */
  private int orderPattern = 1;

  /**
   * 运力优先时优先使用的运力。运力ID请参考：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/industry/express/business/intracity_service.html#_6-%E8%BF%90%E5%8A%9B%E5%88%97%E8%A1%A8
   */
  private String ServiceTransPrefer;

  private AddressInfo addressInfo;

  public String getWxStoreId() {
    return wxStoreId;
  }

  public void setWxStoreId(String wxStoreId) {
    this.wxStoreId = wxStoreId;
  }

  public String getOutStoreId() {
    return outStoreId;
  }

  public void setOutStoreId(String outStoreId) {
    this.outStoreId = outStoreId;
  }

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public String getCityId() {
    return cityId;
  }

  public void setCityId(String cityId) {
    this.cityId = cityId;
  }

  public int getOrderPattern() {
    return orderPattern;
  }

  public void setOrderPattern(int orderPattern) {
    this.orderPattern = orderPattern;
  }

  public String getServiceTransPrefer() {
    return ServiceTransPrefer;
  }

  public void setServiceTransPrefer(String serviceTransPrefer) {
    ServiceTransPrefer = serviceTransPrefer;
  }

  public AddressInfo getAddressInfo() {
    return addressInfo;
  }

  public void setAddressInfo(AddressInfo addressInfo) {
    this.addressInfo = addressInfo;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  public static class AddressInfo {
    /** 省、自治区、直辖市 必填 */
    private String province;

    /** 地级市 必填 */
    private String city;

    /** 区/县/县级市 必填 */
    private String area;

    /** 街道/镇 选填 */
    private String street;

    /** 路名和门牌号 必填 */
    private String house;

    /** 门店电话号码 必填 */
    private String phone;

    /** 纬度 必填 */
    private double lat;

    /** 经度 必填 */
    private double lng;

    public String getProvince() {
      return province;
    }

    public void setProvince(String province) {
      this.province = province;
    }

    public String getCity() {
      return city;
    }

    public void setCity(String city) {
      this.city = city;
    }

    public String getArea() {
      return area;
    }

    public void setArea(String area) {
      this.area = area;
    }

    public String getStreet() {
      return street;
    }

    public void setStreet(String street) {
      this.street = street;
    }

    public String getHouse() {
      return house;
    }

    public void setHouse(String house) {
      this.house = house;
    }

    public String getPhone() {
      return phone;
    }

    public void setPhone(String phone) {
      this.phone = phone;
    }

    public double getLat() {
      return lat;
    }

    public void setLat(double lat) {
      this.lat = lat;
    }

    public double getLng() {
      return lng;
    }

    public void setLng(double lng) {
      this.lng = lng;
    }

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this);
    }
  }
}
