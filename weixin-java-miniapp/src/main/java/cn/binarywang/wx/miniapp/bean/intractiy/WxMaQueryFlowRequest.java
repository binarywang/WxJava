package cn.binarywang.wx.miniapp.bean.intractiy;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WxMaQueryFlowRequest {
  private static final Logger logger = LoggerFactory.getLogger(WxMaQueryFlowRequest.class);

  private String wxStoreId;

  /** 流水类型: 1:充值流水， 2:消费流水，3:退款流水 */
  private int flowType = 1;

  /** 运力ID */
  private String serviceTransId;

  private transient Date beginDate;
  private transient Date endDate;
  private long beginTime;
  private long endTime;

  public String getWxStoreId() {
    return wxStoreId;
  }

  public void setWxStoreId(String wxStoreId) {
    this.wxStoreId = wxStoreId;
  }

  public int getFlowType() {
    return flowType;
  }

  public void setFlowType(int flowType) {
    this.flowType = flowType;
  }

  public String getServiceTransId() {
    return serviceTransId;
  }

  public void setServiceTransId(String serviceTransId) {
    this.serviceTransId = serviceTransId;
  }

  public Date getBeginDate() {
    return beginDate;
  }

  public void setBeginDate(Date beginDate) {
    this.beginDate = beginDate;
    this.beginTime = beginDate.getTime() / 1000;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
    this.endTime = endDate.getTime() / 1000;
  }

  public long getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(long beginTime) {
    this.beginTime = beginTime;
    this.beginDate = new Date(beginTime * 1000);
  }

  public long getEndTime() {
    return endTime;
  }

  public void setEndTime(long endTime) {
    this.endTime = endTime;
    this.endDate = new Date(endTime * 1000);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
