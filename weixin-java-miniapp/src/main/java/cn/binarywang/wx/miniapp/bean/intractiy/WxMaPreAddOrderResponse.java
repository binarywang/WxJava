package cn.binarywang.wx.miniapp.bean.intractiy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WxMaPreAddOrderResponse {
  private static final Logger logger = LoggerFactory.getLogger(WxMaPreAddOrderResponse.class);

  /** 运力公司ID */
  private String serviceTransId;

  /** 配送距离 */
  private int distance;

  /** 预估配送费 */
  private int estFee;

  /** 商品预计送达时间 */
  private long expectedFinishedTime;

  /** 配送时长（单位：分钟） */
  private int promiseDeliveryTime;

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

  public int getEstFee() {
    return estFee;
  }

  public void setEstFee(int estFee) {
    this.estFee = estFee;
  }

  public long getExpectedFinishedTime() {
    return expectedFinishedTime;
  }

  public void setExpectedFinishedTime(long expectedFinishedTime) {
    this.expectedFinishedTime = expectedFinishedTime;
  }

  public int getPromiseDeliveryTime() {
    return promiseDeliveryTime;
  }

  public void setPromiseDeliveryTime(int promiseDeliveryTime) {
    this.promiseDeliveryTime = promiseDeliveryTime;
  }
}
