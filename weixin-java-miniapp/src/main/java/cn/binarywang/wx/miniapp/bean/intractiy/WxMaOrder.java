package cn.binarywang.wx.miniapp.bean.intractiy;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class WxMaOrder extends WxMaAddOrderRequest {
  private String wxOrderId;
  private int orderStatus;
  private String appid;
  private String serviceTransId;
  private String deliveryNo;
  private int actualfee;
  private int deductfee;
  private int distance;
  private long createTime;
  private long acceptTime;
  private long fetchTime;
  private long finishTime;
  private long cancelTime;
  private long expectedFinishTime;
  private String fetchCode;
  private String recvCode;
  private TransporterInfo transporterInfo;
  private StoreInfo storeInfo;
  private ReceiverInfo receiverInfo;
  private Cargo cargoInfo;

  public String getWxOrderId() {
    return wxOrderId;
  }

  public void setWxOrderId(String wxOrderId) {
    this.wxOrderId = wxOrderId;
  }

  public int getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(int orderStatus) {
    this.orderStatus = orderStatus;
  }

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public String getServiceTransId() {
    return serviceTransId;
  }

  public void setServiceTransId(String serviceTransId) {
    this.serviceTransId = serviceTransId;
  }

  public String getDeliveryNo() {
    return deliveryNo;
  }

  public void setDeliveryNo(String deliveryNo) {
    this.deliveryNo = deliveryNo;
  }

  public int getActualfee() {
    return actualfee;
  }

  public void setActualfee(int actualfee) {
    this.actualfee = actualfee;
  }

  public int getDeductfee() {
    return deductfee;
  }

  public void setDeductfee(int deductfee) {
    this.deductfee = deductfee;
  }

  public long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(long createTime) {
    this.createTime = createTime;
  }

  public long getAcceptTime() {
    return acceptTime;
  }

  public void setAcceptTime(long acceptTime) {
    this.acceptTime = acceptTime;
  }

  public long getFetchTime() {
    return fetchTime;
  }

  public void setFetchTime(long fetchTime) {
    this.fetchTime = fetchTime;
  }

  public long getFinishTime() {
    return finishTime;
  }

  public void setFinishTime(long finishTime) {
    this.finishTime = finishTime;
  }

  public long getCancelTime() {
    return cancelTime;
  }

  public void setCancelTime(long cancelTime) {
    this.cancelTime = cancelTime;
  }

  public long getExpectedFinishTime() {
    return expectedFinishTime;
  }

  public void setExpectedFinishTime(long expectedFinishTime) {
    this.expectedFinishTime = expectedFinishTime;
  }

  public String getFetchCode() {
    return fetchCode;
  }

  public void setFetchCode(String fetchCode) {
    this.fetchCode = fetchCode;
  }

  public String getRecvCode() {
    return recvCode;
  }

  public void setRecvCode(String recvCode) {
    this.recvCode = recvCode;
  }

  public TransporterInfo getTransporterInfo() {
    return transporterInfo;
  }

  public void setTransporterInfo(TransporterInfo transporterInfo) {
    this.transporterInfo = transporterInfo;
  }

  public StoreInfo getStoreInfo() {
    return storeInfo;
  }

  public void setStoreInfo(StoreInfo storeInfo) {
    this.storeInfo = storeInfo;
  }

  public ReceiverInfo getReceiverInfo() {
    return receiverInfo;
  }

  public void setReceiverInfo(ReceiverInfo receiverInfo) {
    this.receiverInfo = receiverInfo;
  }

  public Cargo getCargoInfo() {
    return cargoInfo;
  }

  public void setCargoInfo(Cargo cargoInfo) {
    this.cargoInfo = cargoInfo;
  }

  public Date getCreateDate() {
    return createTime == 0 ? null : new Date(createTime * 1000);
  }

  public Date getAcceptDate() {
    return acceptTime == 0 ? null : new Date(acceptTime * 1000);
  }

  public Date getFetchDate() {
    return fetchTime == 0 ? null : new Date(fetchTime * 1000);
  }

  public Date getFinishDate() {
    return finishTime == 0 ? null : new Date(finishTime * 1000);
  }

  public Date getCancelDate() {
    return cancelTime == 0 ? null : new Date(cancelTime * 1000);
  }

  public int getDistance() {
    return distance;
  }

  public void setDistance(int distance) {
    this.distance = distance;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  public static class TransporterInfo {
    private String transporterName;
    private String transporterPhone;

    public String getTransporterName() {
      return transporterName;
    }

    public void setTransporterName(String transporterName) {
      this.transporterName = transporterName;
    }

    public String getTransporterPhone() {
      return transporterPhone;
    }

    public void setTransporterPhone(String transporterPhone) {
      this.transporterPhone = transporterPhone;
    }

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this);
    }
  }

  public static class StoreInfo {
    private String storeName;
    private String wxStoreId;
    private String address;
    private double lng;
    private double lat;
    private String phoneNum;

    public String getStoreName() {
      return storeName;
    }

    public void setStoreName(String storeName) {
      this.storeName = storeName;
    }

    public String getWxStoreId() {
      return wxStoreId;
    }

    public void setWxStoreId(String wxStoreId) {
      this.wxStoreId = wxStoreId;
    }

    public String getAddress() {
      return address;
    }

    public void setAddress(String address) {
      this.address = address;
    }

    public double getLng() {
      return lng;
    }

    public void setLng(double lng) {
      this.lng = lng;
    }

    public double getLat() {
      return lat;
    }

    public void setLat(double lat) {
      this.lat = lat;
    }

    public String getPhoneNum() {
      return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
      this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this);
    }
  }

  public static class ReceiverInfo {
    private String receiverName;
    private String address;
    private String phoneNum;
    private double lng;
    private double lat;

    public String getReceiverName() {
      return receiverName;
    }

    public void setReceiverName(String receiverName) {
      this.receiverName = receiverName;
    }

    public String getAddress() {
      return address;
    }

    public void setAddress(String address) {
      this.address = address;
    }

    public String getPhoneNum() {
      return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
      this.phoneNum = phoneNum;
    }

    public double getLng() {
      return lng;
    }

    public void setLng(double lng) {
      this.lng = lng;
    }

    public double getLat() {
      return lat;
    }

    public void setLat(double lat) {
      this.lat = lat;
    }

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this);
    }
  }
}
