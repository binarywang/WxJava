package cn.binarywang.wx.miniapp.bean.intractiy;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class WxMaStoreRefundRequest extends BasicWxMaStoreChargeRefundRequest {

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
