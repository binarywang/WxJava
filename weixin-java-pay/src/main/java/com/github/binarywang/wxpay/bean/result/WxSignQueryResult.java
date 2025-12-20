package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * @author chenliang
 * created on  2021-08-02 5:40 下午
 *
 * <pre>
 *   微信签约查询返回结果
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("xml")
public class WxSignQueryResult extends BaseWxPayResult implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 委托代扣协议ID
   */
  @XStreamAlias("contract_id")
  private String contractId;

  /**
   * 模板ID
   */
  @XStreamAlias("plan_id")
  private String planId;

  /**
   * 请求序列号
   */
  @XStreamAlias("request_serial")
  private Long requestSerial;

  /**
   * 签约协议号
   */
  @XStreamAlias("contract_code")
  private String contractCode;

  /**
   * 用户账户展示名称
   */
  @XStreamAlias("contract_display_account")
  private String contractDisplayAccount;

  /**
   * 协议状态
   */
  @XStreamAlias("contract_state")
  private Integer contractState;

  /**
   * 协议签署时间
   */
  @XStreamAlias("contract_signed_time")
  private String contractSignedTime;

  /**
   * 协议到期时间
   */
  @XStreamAlias("contract_expired_time")
  private String contractExpiredTime;

  /**
   * 协议解约时间
   * 非必传
   */
  @XStreamAlias("contract_terminated_time")
  private String contractTerminatedTime;

  /**
   * 协议解约方式
   * 非必传
   */
  @XStreamAlias("contract_termination_mode")
  private Integer contractTerminatedMode;

  /**
   * 解约备注
   * 非必传
   */
  @XStreamAlias("contract_termination_remark")
  private String contractTerminationRemark;

  /**
   * 用户表示
   */
  @XStreamAlias("openid")
  private String openId;


  @Override
  protected void loadXml(Document d) {
    contractId = readXmlString(d, "contract_id");
    planId = readXmlString(d, "plan_id");
    requestSerial = readXmlLong(d, "request_serial");
    contractCode = readXmlString(d, "contract_code");
    contractDisplayAccount = readXmlString(d, "contract_display_account");
    contractState = readXmlInteger(d, "contract_state");
    contractSignedTime = readXmlString(d, "contract_signed_time");
    contractExpiredTime = readXmlString(d, "contrace_Expired_time");
    contractTerminatedTime = readXmlString(d, "contract_terminated_time");
    contractTerminatedMode = readXmlInteger(d, "contract_termination_mode");
    contractTerminationRemark = readXmlString(d, "contract_termination_remark");
    openId = readXmlString(d, "openid");
  }

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }
}
