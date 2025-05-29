package cn.binarywang.wx.miniapp.bean.intractiy;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class WxMaAddOrderResponse {
  private String wxOrderId;
  private String storeOrderId;
  private String wxStoreId;

  /** 配送运力 */
  private String serviceTransId;

  /** 配送距离 单位：米 */
  private int distance;

  /** 运力订单号 */
  private String transOrderId;

  /** 运力配送单号 */
  private String waybillId;

  /** 配送费 */
  private int fee;

  /** 取货码 */
  private String fetchCode;

  /** 取货序号 */
  private String orderSeq;

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

  public String getServiceTransId() {
    return serviceTransId;
  }

  public void setServiceTransId(String serviceTransId) {
    this.serviceTransId = serviceTransId;
  }

  public int getDistance() {
    return distance;
  }

  public void setDistance(int distance) {
    this.distance = distance;
  }

  public String getTransOrderId() {
    return transOrderId;
  }

  public void setTransOrderId(String transOrderId) {
    this.transOrderId = transOrderId;
  }

  public String getWaybillId() {
    return waybillId;
  }

  public void setWaybillId(String waybillId) {
    this.waybillId = waybillId;
  }

  public int getFee() {
    return fee;
  }

  public void setFee(int fee) {
    this.fee = fee;
  }

  public String getFetchCode() {
    return fetchCode;
  }

  public void setFetchCode(String fetchCode) {
    this.fetchCode = fetchCode;
  }

  public String getOrderSeq() {
    return orderSeq;
  }

  public void setOrderSeq(String orderSeq) {
    this.orderSeq = orderSeq;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
