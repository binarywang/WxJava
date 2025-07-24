package cn.binarywang.wx.miniapp.bean.intractiy;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class WxMaCancelOrderResponse {
  private String wxOrderId;
  private String storeOrderId;
  private String wxStoreId;
  private String orderStatus;
  private String appid;

  /** 违约金 */
  private int deductfee;

  public String getWxOrderId() {
    return wxOrderId;
  }

  public void setWxOrderId(String wxOrderId) {
    this.wxOrderId = wxOrderId;
  }

  public String getStoreOrderId() {
    return storeOrderId;
  }

  public void setStoreOrderId(String storeOrderId) {
    this.storeOrderId = storeOrderId;
  }

  public String getWxStoreId() {
    return wxStoreId;
  }

  public void setWxStoreId(String wxStoreId) {
    this.wxStoreId = wxStoreId;
  }

  public String getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(String orderStatus) {
    this.orderStatus = orderStatus;
  }

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public int getDeductfee() {
    return deductfee;
  }

  public void setDeductfee(int deductfee) {
    this.deductfee = deductfee;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
