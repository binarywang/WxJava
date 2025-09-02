package com.github.binarywang.wxpay.bean.notify;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;
import org.w3c.dom.Document;

/**
 * <pre>
 *  退款结果通知对象.
 *  Created by BinaryWang on 2017/8/27.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WxPayRefundNotifyResult extends BaseWxPayResult implements Serializable {
  private static final long serialVersionUID = 4651725860079259186L;

  /**
   * 从xml字符串创建bean对象.
   *
   * @param xmlString xml字符串
   * @param mchKey    商户密钥
   * @return the wx pay refund notify result
   * @throws WxPayException the wx pay exception
   */
  public static WxPayRefundNotifyResult fromXML(String xmlString, String mchKey) throws WxPayException {
    WxPayRefundNotifyResult result = BaseWxPayResult.fromXML(xmlString, WxPayRefundNotifyResult.class);
    if (WxPayConstants.ResultCode.FAIL.equals(result.getReturnCode())) {
      return result;
    }

    String reqInfoString = result.getReqInfoString();
    try {
      final String keyMd5String = DigestUtils.md5Hex(mchKey).toLowerCase();
      SecretKeySpec key = new SecretKeySpec(keyMd5String.getBytes(StandardCharsets.UTF_8), "AES");

      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(Cipher.DECRYPT_MODE, key);
      result.setReqInfo(ReqInfo.fromXML(new String(cipher.doFinal(Base64.decodeBase64(reqInfoString)),
        StandardCharsets.UTF_8)));
    } catch (Exception e) {
      throw new WxPayException("解密退款通知加密信息时出错", e);
    }

    return result;
  }

  /**
   * <pre>
   * 字段名：加密信息.
   * 变量名：req_info
   * 是否必填：是
   * 类型：String(1024)
   * 描述：加密信息请用商户证书与商户秘钥进行解密
   * </pre>
   */
  @XStreamAlias("req_info")
  private String reqInfoString;

  private ReqInfo reqInfo;

  // 解密后的reqInfo 字符串
  private transient String decryptedReqInfo;

  @Override
  protected void loadXml(Document d) {
    reqInfoString = readXmlString(d, "req_info");
  }

  /**
   * 解密并解析reqInfo
   *
   * @param mchKey
   * @throws WxPayException
   */
  public void decryptReqInfo(String mchKey) throws WxPayException {
    //如果是失败，直接返回，不用解析
    if (WxPayConstants.ResultCode.FAIL.equals(getReturnCode())) {
      return;
    }
    try {
      final String keyMd5String = DigestUtils.md5Hex(mchKey).toLowerCase();
      SecretKeySpec key = new SecretKeySpec(keyMd5String.getBytes(StandardCharsets.UTF_8), "AES");

      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(Cipher.DECRYPT_MODE, key);
      decryptedReqInfo = new String(cipher.doFinal(Base64.decodeBase64(reqInfoString)), StandardCharsets.UTF_8);
      loadReqInfo(decryptedReqInfo);
    } catch (Exception e) {
      throw new WxPayException("解密退款通知加密信息时出错", e);
    }
  }

  // 本方法独立出来方便测试
  protected void loadReqInfo(String decryptedReqInfo) {
    Document document = openXML(decryptedReqInfo);
    reqInfo = new ReqInfo();
    reqInfo.loadXML(document);
  }

  /**
   * 加密信息字段解密后的内容.
   */
  @Data
  @NoArgsConstructor
  @XStreamAlias("root")
  public static class ReqInfo {
    @Override
    public String toString() {
      return WxGsonBuilder.create().toJson(this);
    }

    /**
     * <pre>
     * 字段名：微信订单号.
     * 变量名：transaction_id
     * 是否必填：是
     * 类型：String(32)
     * 示例值：1217752501201407033233368018
     * 描述：微信订单号
     * </pre>
     */
    @XStreamAlias("transaction_id")
    private String transactionId;

    /**
     * <pre>
     * 字段名：商户订单号.
     * 变量名：out_trade_no
     * 是否必填：是
     * 类型：String(32)
     * 示例值：1217752501201407033233368018
     * 描述：商户系统内部的订单号
     * </pre>
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * <pre>
     * 字段名：微信退款单号.
     * 变量名：refund_id
     * 是否必填：是
     * 类型：String(28)
     * 示例值：1217752501201407033233368018
     * 描述：微信退款单号
     * </pre>
     */
    @XStreamAlias("refund_id")
    private String refundId;

    /**
     * <pre>
     * 字段名：商户退款单号.
     * 变量名：out_refund_no
     * 是否必填：是
     * 类型：String(64)
     * 示例值：1217752501201407033233368018
     * 描述：商户退款单号
     * </pre>
     */
    @XStreamAlias("out_refund_no")
    private String outRefundNo;

    /**
     * <pre>
     * 字段名：订单金额.
     * 变量名：total_fee
     * 是否必填：是
     * 类型：Int
     * 示例值：100
     * 描述：订单总金额，单位为分，只能为整数，详见支付金额
     * </pre>
     */
    @XStreamAlias("total_fee")
    private Integer totalFee;

    /**
     * <pre>
     * 字段名：结订单金额.
     * 变量名：settlement_total_fee
     * 是否必填：否
     * 类型：Int
     * 示例值：100
     * 描述：当该订单有使用非充值券时，返回此字段。应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     * </pre>
     */
    @XStreamAlias("settlement_total_fee")
    private Integer settlementTotalFee;

    /**
     * <pre>
     * 字段名：申请退款金额.
     * 变量名：refund_fee
     * 是否必填：是
     * 类型：Int
     * 示例值：100
     * 描述：退款总金额,单位为分
     * </pre>
     */
    @XStreamAlias("refund_fee")
    private Integer refundFee;

    /**
     * <pre>
     * 字段名：退款金额.
     * 变量名：settlement_refund_fee
     * 是否必填：是
     * 类型：Int
     * 示例值：100
     * 描述：退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
     * </pre>
     */
    @XStreamAlias("settlement_refund_fee")
    private Integer settlementRefundFee;

    /**
     * <pre>
     * 字段名：退款状态.
     * 变量名：refund_status
     * 是否必填：是
     * 类型：String(16)
     * 示例值：SUCCESS
     * 描述：SUCCESS-退款成功，CHANGE-退款异常，REFUNDCLOSE—退款关闭
     * </pre>
     */
    @XStreamAlias("refund_status")
    private String refundStatus;

    /**
     * <pre>
     * 字段名：退款成功时间.
     * 变量名：success_time
     * 是否必填：否
     * 类型： String(20)
     * 示例值：2017-12-15 09:46:01
     * 资金退款至用户帐号的时间，格式2017-12-15 09:46:01
     * </pre>
     */
    @XStreamAlias("success_time")
    private String successTime;

    /**
     * <pre>
     * 字段名：退款入账账户.
     * 变量名：refund_recv_accout
     * 是否必填：是
     * 类型：String(64)
     * 示例值：招商银行信用卡0403
     * 描述：取当前退款单的退款入账方，1）退回银行卡：{银行名称}{卡类型}{卡尾号}，2）退回支付用户零钱:支付用户零钱 ，3）退还商户: 商户基本账户，商户结算银行账户，4）退回支付用户零钱通: 支付用户零钱通
     * </pre>
     */
    @XStreamAlias("refund_recv_accout")
    private String refundRecvAccount;

    /**
     * <pre>
     * 字段名：退款资金来源.
     * 变量名：refund_account
     * 是否必填：是
     * 类型：String(30)
     * 示例值：REFUND_SOURCE_RECHARGE_FUNDS
     * 描述：REFUND_SOURCE_RECHARGE_FUNDS 可用余额退款/基本账户，REFUND_SOURCE_UNSETTLED_FUNDS 未结算资金退款
     * </pre>
     */
    @XStreamAlias("refund_account")
    private String refundAccount;

    /**
     * <pre>
     * 字段名：退款发起来源.
     * 变量名：refund_request_source
     * 是否必填：是
     * 类型：String(30)
     * 示例值：API
     * 描述：API接口，VENDOR_PLATFORM商户平台
     * </pre>
     */
    @XStreamAlias("refund_request_source")
    private String refundRequestSource;

    /**
     * 从xml字符串构造ReqInfo对象.
     *
     * @param xmlString xml字符串
     * @return ReqInfo对象
     */
    public static ReqInfo fromXML(String xmlString) {
      XStream xstream = XStreamInitializer.getInstance();
      xstream.processAnnotations(ReqInfo.class);
      return (ReqInfo) xstream.fromXML(xmlString);
    }

    public void loadXML(Document d) {
      transactionId = readXmlString(d, "transaction_id");
      outTradeNo = readXmlString(d, "out_trade_no");
      refundId = readXmlString(d, "refund_id");
      outRefundNo = readXmlString(d, "out_refund_no");
      totalFee = readXmlInteger(d, "total_fee");
      settlementTotalFee = readXmlInteger(d, "settlement_total_fee");
      refundFee = readXmlInteger(d, "refund_fee");
      settlementRefundFee = readXmlInteger(d, "settlement_refund_fee");
      refundStatus = readXmlString(d, "refund_status");
      successTime = readXmlString(d, "success_time");
      refundRecvAccount = readXmlString(d, "refund_recv_accout");
      refundAccount = readXmlString(d, "refund_account");
      refundRequestSource = readXmlString(d, "refund_request_source");
    }
  }


}
