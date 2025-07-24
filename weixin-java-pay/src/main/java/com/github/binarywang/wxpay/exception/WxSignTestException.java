package com.github.binarywang.wxpay.exception;

/**
 * <pre>
 *   微信支付签名探测异常类
 * </pre>
 * @author je45
 * @date 2024/11/27 9:35
 */
public class WxSignTestException extends WxPayException {
  private static final long serialVersionUID = -303371909244098058L;

  /**
   * Instantiates a new Wx pay exception.
   *
   * @param customErrorMsg the custom error msg
   */
  public WxSignTestException(String customErrorMsg) {
    super(customErrorMsg);
  }

  /**
   * Instantiates a new Wx pay exception.
   *
   * @param customErrorMsg the custom error msg
   * @param tr             the tr
   */
  public WxSignTestException(String customErrorMsg, Throwable tr) {
    super(customErrorMsg, tr);
  }
}
