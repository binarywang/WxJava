package com.github.binarywang.wxpay.bean.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import lombok.experimental.Accessors;
import me.chanjar.weixin.common.annotation.Required;

import java.util.Map;

/**
 * <pre>
 *  获取微信刷脸调用凭证请求对象类
 *  详见文档：https://pay.weixin.qq.com/wiki/doc/wxfacepay/develop/sdk-android.html#获取数据-getwxpayfacerawdata
 * Created by Jmdhappy on 2019-09-04.
 * </pre>
 *
 * @author <a href="https://github.com/jmdhappy/xxpay-master">XxPay</a>
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WxPayFaceAuthInfoRequest extends BaseWxPayRequest {
  private static final long serialVersionUID = -2909189635374300870L;

  /**
   * <pre>
   * 字段名：门店编号
   * 变量名：store_id
   * 是否必填：是
   * 类型：string(32)
   * 示例值：1001
   * 描述：门店编号， 由商户定义， 各门店唯一
   * </pre>
   */
  @Required
  @XStreamAlias("store_id")
  private String storeId;

  /**
   * <pre>
   * 字段名：门店名称
   * 变量名：store_name
   * 是否必填：是
   * 类型：string(128)
   * 示例值：骏易科技
   * 描述：门店名称，由商户定义。（可用于展示）
   * </pre>
   */
  @Required
  @XStreamAlias("store_name")
  private String storeName;

  /**
   * <pre>
   * 字段名：终端设备编号
   * 变量名：device_id
   * 是否必填：是
   * 类型：string(32)
   * 示例值：
   * 描述：终端设备编号，由商户定义。
   * </pre>
   */
  @Required
  @XStreamAlias("device_id")
  private String deviceId;

  /**
   * <pre>
   * 字段名：附加字段
   * 变量名：attach
   * 是否必填：是
   * 类型：string
   * 示例值：
   * 描述：附加字段。字段格式使用Json
   * </pre>
   */
  @XStreamAlias("attach")
  private String attach;

  /**
   * <pre>
   * 字段名：初始化数据
   * 变量名：attach
   * 是否必填：是
   * 类型：string(2048)
   * 示例值：
   * 描述：初始化数据。由微信人脸SDK的接口返回。
   * </pre>
   */
  @Required
  @XStreamAlias("rawdata")
  private String rawdata;

  /**
   * <pre>
   * 字段名：当前时间
   * 变量名：now
   * 是否必填：否
   * 类型：String(10)
   * 示例值：1239878956
   * 描述：取当前时间，10位unix时间戳。 例如：1239878956
   * </pre>
   */
  @Required
  @XStreamAlias("now")
  private String now;

  /**
   * <pre>
   * 字段名：接口版本号.
   * 变量名：version
   * 是否必填：是
   * 类型：String
   * 示例值：1.0
   * 描述：版本号。固定为1
   * </pre>
   */
  @Required
  @XStreamAlias("version")
  private String version;

  @Override
  protected void checkConstraints() {
    //do nothing
  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("now", now);
    map.put("version", version);
    map.put("rawdata", rawdata);
    map.put("store_id", storeId);
    map.put("store_name", storeName);
    map.put("device_id", deviceId);
    map.put("attach", attach);
  }

}
