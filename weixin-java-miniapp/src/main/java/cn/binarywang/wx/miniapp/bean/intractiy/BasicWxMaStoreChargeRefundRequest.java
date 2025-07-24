package cn.binarywang.wx.miniapp.bean.intractiy;

import org.apache.commons.lang3.builder.ToStringBuilder;

class BasicWxMaStoreChargeRefundRequest {

  /** 微信门店编号 pay_mode = PAY_MODE_STORE时必传，不传pay_mode时必传wx_store_id */
  private String wxStoreId;

  /**
   * 充值/扣费主体 <br>
   * 门店：PAY_MODE_STORE；小程序:PAY_MODE_APP；服务商：PAY_MODE_COMPONENT，不传pay_mode默认pay_mode=PAY_MODE_STORE
   */
  private PayMode payMode;

  /**
   * 运力Id，必填。运力ID请参考：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/industry/express/business/intracity_service.html#_6-%E8%BF%90%E5%8A%9B%E5%88%97%E8%A1%A8
   */
  private String serviceTransId;

  public String getWxStoreId() {
    return wxStoreId;
  }

  public void setWxStoreId(String wxStoreId) {
    this.wxStoreId = wxStoreId;
  }

  public PayMode getPayMode() {
    return payMode;
  }

  public void setPayMode(PayMode payMode) {
    this.payMode = payMode;
  }

  public String getServiceTransId() {
    return serviceTransId;
  }

  public void setServiceTransId(String serviceTransId) {
    this.serviceTransId = serviceTransId;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
