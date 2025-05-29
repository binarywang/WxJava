package cn.binarywang.wx.miniapp.bean.intractiy;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class WxMaStoreChargeRequest extends BasicWxMaStoreChargeRefundRequest {

  /** 充值金额 单位：分, 50元起充 */
  private int amount;

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
