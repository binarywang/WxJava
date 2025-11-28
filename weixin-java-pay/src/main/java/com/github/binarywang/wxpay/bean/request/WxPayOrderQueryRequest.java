package com.github.binarywang.wxpay.bean.request;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * <pre>
 * 订单查询请求对象
 * Created by Binary Wang on 2016-10-24.
 * 注释中各行每个字段描述对应如下：
 * <li>字段名
 * <li>变量名
 * <li>是否必填
 * <li>类型
 * <li>示例值
 * <li>描述
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WxPayOrderQueryRequest extends BaseWxPayRequest {

  /**
   * <pre>
   * 字段名：接口版本号.
   * 变量名：version
   * 是否必填：单品优惠必填
   * 类型：String(32)
   * 示例值：1.0
   * 描述：单品优惠新增字段，区分原接口，固定填写1.0，
   * 查单接口上传version后查询结果才返回单品信息，不上传不返回单品信息。
   * 更多信息，详见文档：https://pay.weixin.qq.com/wiki/doc/api/danpin.php?chapter=9_102&index=2
   * </pre>
   */
  @XStreamAlias("version")
  private String version;

  /**
   * <pre>
   * 微信订单号
   * transaction_id
   * 二选一
   * String(32)
   * 1009660380201506130728806387
   * 微信的订单号，优先使用
   * </pre>
   */
  @XStreamAlias("transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 商户订单号
   * out_trade_no
   * 二选一
   * String(32)
   * 20150806125346
   * 商户系统内部的订单号，当没提供transaction_id时需要传这个。
   * </pre>
   */
  @XStreamAlias("out_trade_no")
  private String outTradeNo;

  @Override
  protected void checkConstraints() throws WxPayException {
    if ((StringUtils.isBlank(transactionId) && StringUtils.isBlank(outTradeNo)) ||
      (StringUtils.isNotBlank(transactionId) && StringUtils.isNotBlank(outTradeNo))) {
      throw new WxPayException("transaction_id 和 out_trade_no 不能同时存在或同时为空，必须二选一");
    }
  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("version", version);
    map.put("transaction_id", transactionId);
    map.put("out_trade_no", outTradeNo);
  }

}
