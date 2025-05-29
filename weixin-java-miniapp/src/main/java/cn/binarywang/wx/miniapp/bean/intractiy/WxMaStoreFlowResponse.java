package cn.binarywang.wx.miniapp.bean.intractiy;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WxMaStoreFlowResponse<T extends WxMaStoreFlowResponse.BasicFlowRecord> {
  private static final Logger logger = LoggerFactory.getLogger(WxMaStoreFlowResponse.class);

  /** 总支付金额 */
  private Long totalPayAmt;

  /** 总退款金额 */
  private Long totalRefundAmt;

  /** 总违约金 查询消费流水才返回 */
  private Long totalDeductAmt;

  /** 流水 */
  private List<T> flowList;

  public List<T> getFlowList() {
    return flowList;
  }

  public void setFlowList(List<T> flowList) {
    this.flowList = flowList;
  }

  public Long getTotalPayAmt() {
    return totalPayAmt;
  }

  public void setTotalPayAmt(Long totalPayAmt) {
    this.totalPayAmt = totalPayAmt;
  }

  public Long getTotalRefundAmt() {
    return totalRefundAmt;
  }

  public void setTotalRefundAmt(Long totalRefundAmt) {
    this.totalRefundAmt = totalRefundAmt;
  }

  public Long getTotalDeductAmt() {
    return totalDeductAmt;
  }

  public void setTotalDeductAmt(Long totalDeductAmt) {
    this.totalDeductAmt = totalDeductAmt;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  public static class BasicFlowRecord {
    private int flowType;
    private String appid;
    private String wxStoreId;
    private String payOrderId;
    private String serviceTransId;
    private int payAmount;
    private long payTime;
    private long createTime;

    public int getFlowType() {
      return flowType;
    }

    public void setFlowType(int flowType) {
      this.flowType = flowType;
    }

    public String getAppid() {
      return appid;
    }

    public void setAppid(String appid) {
      this.appid = appid;
    }

    public String getWxStoreId() {
      return wxStoreId;
    }

    public void setWxStoreId(String wxStoreId) {
      this.wxStoreId = wxStoreId;
    }

    public String getPayOrderId() {
      return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
      this.payOrderId = payOrderId;
    }

    public String getServiceTransId() {
      return serviceTransId;
    }

    public void setServiceTransId(String serviceTransId) {
      this.serviceTransId = serviceTransId;
    }

    public int getPayAmount() {
      return payAmount;
    }

    public void setPayAmount(int payAmount) {
      this.payAmount = payAmount;
    }

    public Date getPayDate() {
      return this.payTime == 0 ? null : new Date(this.payTime * 1000);
    }

    public long getPayTime() {
      return payTime;
    }

    public void setPayTime(long payTime) {
      this.payTime = payTime;
    }

    public Date getCreateDate() {
      return this.createTime == 0 ? null : new Date(this.createTime * 1000);
    }

    public long getCreateTime() {
      return createTime;
    }

    public void setCreateTime(long createTime) {
      this.createTime = createTime;
    }

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this);
    }
  }

  /** 充值流水 */
  public static class ChargeFlowRecord extends BasicFlowRecord {
    private String payStatus;

    private long consumeDeadline;

    public String getPayStatus() {
      return payStatus;
    }

    public void setPayStatus(String payStatus) {
      this.payStatus = payStatus;
    }

    public Date getConsumeDeadlineDate() {
      return this.consumeDeadline == 0 ? null : new Date(this.consumeDeadline * 1000);
    }

    public long getConsumeDeadline() {
      return consumeDeadline;
    }

    public void setConsumeDeadline(long consumeDeadline) {
      this.consumeDeadline = consumeDeadline;
    }

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this);
    }
  }

  /** 退款流水 */
  public static class RefundFlowRecord extends BasicFlowRecord {
    private int refundAmount;
    private long refundTime;
    private long consumeDeadline;

    public int getRefundAmount() {
      return refundAmount;
    }

    public void setRefundAmount(int refundAmount) {
      this.refundAmount = refundAmount;
    }

    public Date getRefundDate() {
      return this.refundTime == 0 ? null : new Date(this.refundTime * 1000);
    }

    public long getRefundTime() {
      return refundTime;
    }

    public void setRefundTime(long refundTime) {
      this.refundTime = refundTime;
    }

    public Date getConsumeDeadlineDate() {
      return this.consumeDeadline == 0 ? null : new Date(this.consumeDeadline * 1000);
    }

    public long getConsumeDeadline() {
      return consumeDeadline;
    }

    public void setConsumeDeadline(long consumeDeadline) {
      this.consumeDeadline = consumeDeadline;
    }

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this);
    }
  }

  /** 消费流水 */
  public static class ConsumeFlowRecord extends BasicFlowRecord {
    private String wxOrderId;
    private String openid;
    private String deliveryStatus;
    private String payStatus;
    private String refundStatus;
    private int refundAmount;
    private int deductAmount;
    private String billId;
    private long deliveryFinishedTime;

    public String getWxOrderId() {
      return wxOrderId;
    }

    public void setWxOrderId(String wxOrderId) {
      this.wxOrderId = wxOrderId;
    }

    public String getOpenid() {
      return openid;
    }

    public void setOpenid(String openid) {
      this.openid = openid;
    }

    public String getDeliveryStatus() {
      return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
      this.deliveryStatus = deliveryStatus;
    }

    public String getPayStatus() {
      return payStatus;
    }

    public void setPayStatus(String payStatus) {
      this.payStatus = payStatus;
    }

    public String getRefundStatus() {
      return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
      this.refundStatus = refundStatus;
    }

    public int getRefundAmount() {
      return refundAmount;
    }

    public void setRefundAmount(int refundAmount) {
      this.refundAmount = refundAmount;
    }

    public int getDeductAmount() {
      return deductAmount;
    }

    public void setDeductAmount(int deductAmount) {
      this.deductAmount = deductAmount;
    }

    public String getBillId() {
      return billId;
    }

    public void setBillId(String billId) {
      this.billId = billId;
    }

    public Date getDeliveryFinishedDate() {
      return this.deliveryFinishedTime == 0 ? null : new Date(this.deliveryFinishedTime * 1000);
    }

    public long getDeliveryFinishedTime() {
      return deliveryFinishedTime;
    }

    public void setDeliveryFinishedTime(long deliveryFinishedTime) {
      this.deliveryFinishedTime = deliveryFinishedTime;
    }

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this);
    }
  }
}
