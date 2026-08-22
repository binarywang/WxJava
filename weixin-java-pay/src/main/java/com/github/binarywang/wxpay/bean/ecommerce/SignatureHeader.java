package com.github.binarywang.wxpay.bean.ecommerce;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 微信通知接口头部信息，需要做签名验证
 * 文档地址: https://wechatpay-api.gitbook.io/wechatpay-api-v3/qian-ming-zhi-nan-1/qian-ming-yan-zheng
 *
 * @author cloudX
 */
@Deprecated
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
public class SignatureHeader extends com.github.binarywang.wxpay.bean.notify.SignatureHeader implements Serializable {
  private static final long serialVersionUID = -6958015499416059949L;

  /**
   * 已签名字符串
   */
  private String signed;

  /**
   * 证书序列号
   */
  private String serialNo;

  /**
   * 保留在旧类中的序列化字段，避免升级后反序列化旧数据时丢失。
   */
  private String timeStamp;

  /**
   * 保留在旧类中的序列化字段，避免升级后反序列化旧数据时丢失。
   */
  private String nonce;

  public SignatureHeader() {
    super();
  }

  /**
   * 保留 4.8.4 及以前版本的构造器签名。
   */
  public SignatureHeader(String timeStamp, String nonce, String signed, String serialNo) {
    setTimeStamp(timeStamp);
    setNonce(nonce);
    this.signed = signed;
    this.serialNo = serialNo;
  }

  private SignatureHeader(SignatureHeaderBuilder builder) {
    super(builder);
    this.timeStamp = builder.timeStamp;
    this.nonce = builder.nonce;
    this.signed = builder.signed;
    this.serialNo = builder.serialNo;
  }

  @Override
  public String getTimeStamp() {
    return this.timeStamp;
  }

  @Override
  public void setTimeStamp(String timeStamp) {
    super.setTimeStamp(timeStamp);
    this.timeStamp = timeStamp;
  }

  @Override
  public String getNonce() {
    return this.nonce;
  }

  @Override
  public void setNonce(String nonce) {
    super.setNonce(nonce);
    this.nonce = nonce;
  }

  /**
   * 保留旧版 builder 的类型和方法返回值描述符。
   */
  public static SignatureHeaderBuilder builder() {
    return new SignatureHeaderBuilder();
  }

  public static class SignatureHeaderBuilder extends com.github.binarywang.wxpay.bean.notify.SignatureHeader
    .SignatureHeaderBuilder<SignatureHeader, SignatureHeaderBuilder> {
    private String timeStamp;
    private String nonce;
    private String signed;
    private String serialNo;

    @Override
    public SignatureHeaderBuilder timeStamp(String timeStamp) {
      super.timeStamp(timeStamp);
      this.timeStamp = timeStamp;
      return this;
    }

    @Override
    public SignatureHeaderBuilder nonce(String nonce) {
      super.nonce(nonce);
      this.nonce = nonce;
      return this;
    }

    public SignatureHeaderBuilder signed(String signed) {
      this.signed = signed;
      return this;
    }

    public SignatureHeaderBuilder serialNo(String serialNo) {
      this.serialNo = serialNo;
      return this;
    }

    @Override
    protected SignatureHeaderBuilder self() {
      return this;
    }

    @Override
    public SignatureHeader build() {
      return new SignatureHeader(this);
    }
  }
}
