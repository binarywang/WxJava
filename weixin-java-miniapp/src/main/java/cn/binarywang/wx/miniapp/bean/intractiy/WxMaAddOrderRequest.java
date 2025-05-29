package cn.binarywang.wx.miniapp.bean.intractiy;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WxMaAddOrderRequest extends BasicWxMaOrder {
  private static final Logger logger = LoggerFactory.getLogger(WxMaAddOrderRequest.class);
  private String storeOrderId;
  private String userOpenid;
  private String orderSeq;

  /** 验证码类型 0:不生成 1:生成取货码 2:生成收货码 3:两者都生成 */
  private int verifyCodeType;

  private String orderDetailPath;
  private String callbackUrl;
  private Cargo cargo;

  public String getStoreOrderId() {
    return storeOrderId;
  }

  public void setStoreOrderId(String storeOrderId) {
    this.storeOrderId = storeOrderId;
  }

  public String getUserOpenid() {
    return userOpenid;
  }

  public void setUserOpenid(String userOpenid) {
    this.userOpenid = userOpenid;
  }

  public String getOrderSeq() {
    return orderSeq;
  }

  public void setOrderSeq(String orderSeq) {
    this.orderSeq = orderSeq;
  }

  public int getVerifyCodeType() {
    return verifyCodeType;
  }

  public void setVerifyCodeType(int verifyCodeType) {
    this.verifyCodeType = verifyCodeType;
  }

  public String getOrderDetailPath() {
    return orderDetailPath;
  }

  public void setOrderDetailPath(String orderDetailPath) {
    this.orderDetailPath = orderDetailPath;
  }

  public String getCallbackUrl() {
    return callbackUrl;
  }

  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }

  public Cargo getCargo() {
    return cargo;
  }

  public void setCargo(Cargo cargo) {
    this.cargo = cargo;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  public static class Cargo extends BasicWxMaOrder.Cargo {
    private List<ItemDetail> itemList;

    public List<ItemDetail> getItemList() {
      return itemList;
    }

    public void setItemList(List<ItemDetail> itemList) {
      this.itemList = itemList;
    }

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this);
    }
  }

  public static class ItemDetail {
    private String itemName;
    private String itemPicUrl;
    private int count;

    public String getItemName() {
      return itemName;
    }

    public void setItemName(String itemName) {
      this.itemName = itemName;
    }

    public String getItemPicUrl() {
      return itemPicUrl;
    }

    public void setItemPicUrl(String itemPicUrl) {
      this.itemPicUrl = itemPicUrl;
    }

    public int getCount() {
      return count;
    }

    public void setCount(int count) {
      this.count = count;
    }

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this);
    }
  }
}
