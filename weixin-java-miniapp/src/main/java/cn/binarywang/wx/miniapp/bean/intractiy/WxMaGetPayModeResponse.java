package cn.binarywang.wx.miniapp.bean.intractiy;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WxMaGetPayModeResponse {
  private static final Logger logger = LoggerFactory.getLogger(WxMaGetPayModeResponse.class);

  private PayMode payMode;
  private String payAppid;
  private String componentAppid;

  public PayMode getPayMode() {
    return payMode;
  }

  public void setPayMode(PayMode payMode) {
    this.payMode = payMode;
  }

  public String getPayAppid() {
    return payAppid;
  }

  public void setPayAppid(String payAppid) {
    this.payAppid = payAppid;
  }

  public String getComponentAppid() {
    return componentAppid;
  }

  public void setComponentAppid(String componentAppid) {
    this.componentAppid = componentAppid;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
