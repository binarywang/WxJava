package com.github.binarywang.wxpay.bean.result;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * 向微信用户个人发现金红包返回结果
 * https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=13_4&index=3
 *
 * @author kane
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxPaySendRedpackResult extends BaseWxPayResult implements Serializable {
  private static final long serialVersionUID = -4837415036337132073L;

  private static final String PROCESSING = "PROCESSING";

  @XStreamAlias("mch_billno")
  private String mchBillNo;

  @XStreamAlias("wxappid")
  private String wxAppId;

  @XStreamAlias("re_openid")
  private String reOpenid;

  @XStreamAlias("total_amount")
  private Integer totalAmount;

  @XStreamAlias("send_time")
  private String sendTime;

  @XStreamAlias("send_listid")
  private String sendListId;

  /**
   * 从XML结构中加载额外的熟悉
   *
   * @param d Document
   */
  @Override
  protected void loadXml(Document d) {
    mchBillNo = readXmlString(d, "mch_billno");
    wxAppId = readXmlString(d, "wxappid");
    reOpenid = readXmlString(d, "re_openid");
    totalAmount = readXmlInteger(d, "total_amount");
    sendTime = readXmlString(d, "send_time");
    sendListId = readXmlString(d, "send_listid");
  }

  @Override
  public void checkResult(WxPayService wxPayService, String signType, boolean checkSuccess) throws WxPayException {
    try {
      super.checkResult(wxPayService, signType, checkSuccess);
    } catch (WxPayException e) {
      if (!PROCESSING.equals(e.getErrCode())) {
        throw e;
      }
    }
  }
}
