package com.github.binarywang.wxpay.bean.marketing.transfer;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 转账电子回单申请受理API
 * 接口说明
 * 适用对象：直连商户 服务商
 * 请求URL：https://api.mch.weixin.qq.com/v3/transfer/bill-receipt
 * 请求方式：POST
 *
 * @author xiaoqiang
 * created on  2021-12-06
 */
@Data
@NoArgsConstructor
public class BillReceiptResult implements Serializable {
  private static final long serialVersionUID = 1L;

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }

  /**
   * <pre>
   * 字段名：商家批次单号
   * 变量名：out_batch_no
   * 是否必填：是
   * 类型：string[5,32]
   * 描述：
   *  商户系统内部的商家批次单号，在商户系统内部唯一。需要电子回单的批次单号
   *  示例值：plfk2020042013
   * </pre>
   */
  @SerializedName(value = "out_batch_no")
  private String outBatchNo;

  /**
   * <pre>
   * 字段名：电子回单申请单号
   * 变量名：signature_no
   * 是否必填：是
   * 类型：string[3.45]
   * 描述：
   *  电子回单申请单号，申请单据的唯一标识
   *     示例值：1050000010509999485212020110200058820001
   * </pre>
   */
  @SerializedName(value = "signature_no")
  private String signatureNo;

  /**
   * <pre>
   * 字段名：电子回单状态
   * 变量名：signature_status
   * 是否必填：否
   * 类型：string[1,10]
   * 描述：
   *  枚举值：
   *     ACCEPTED:已受理，电子签章已受理成功
   *     FINISHED:已完成。电子签章已处理完成
   *     示例值：ACCEPTED
   * </pre>
   */
  @SerializedName(value = "signature_status")
  private String signatureStatus;

  /**
   * <pre>
   * 字段名：电子回单文件的hash方法
   * 变量名：hash_type
   * 是否必填：否
   * 类型：string[1,20]
   * 描述：
   *  电子回单文件的hash方法，回单状态为：FINISHED时返回。
   *     示例值：SHA256
   * </pre>
   */
  @SerializedName(value = "hash_type")
  private String hashType;

  /**
   * <pre>
   * 字段名：电子回单文件的hash值
   * 变量名：hash_value
   * 是否必填：否
   * 类型：string[3,1000]
   * 描述：
   *  电子回单文件的hash值，用于下载之后验证文件的完整、正确性，回单状态为：FINISHED时返回。
   *     示例值：DE731F35146A0BEFADE5DB9D1E468D96C01CA8898119C674FEE9F11F4DBE5529
   * </pre>
   */
  @SerializedName(value = "hash_value")
  private String hashValue;


  /**
   * <pre>
   * 字段名：电子回单文件的下载地址
   * 变量名：hash_value
   * 是否必填：否
   * 类型：string[10,3000]
   * 描述：
   *  电子回单文件的下载地址，回单状态为：FINISHED时返回。URL有效时长为10分钟，10分钟后需要重新去获取下载地址
   *     示例值：https://api.mch.weixin.qq.com/v3/billdownload/file?token=xxx
   * </pre>
   */
  @SerializedName(value = "download_url")
  private String downloadUrl;

  /**
   * <pre>
   * 字段名：创建时间
   * 变量名：create_time
   * 是否必填：否
   * 类型：string[1, 32]
   * 描述：
   * 	电子签章单创建时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示北京时间2015年05月20日13点29分35秒
   *     示例值：2020-05-20T13:29:35.120+08:00
   * </pre>
   */
  @SerializedName(value = "create_time")
  private String createTime;
  /**
   * <pre>
   * 字段名：更新时间
   * 变量名：update_time
   * 是否必填：否
   * 类型：string[1, 32]
   * 描述：
   * 	电子签章单最近一次状态变更的时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示北京时间2015年05月20日13点29分35秒
   *     示例值：2020-05-21T13:29:35.120+08:00
   * </pre>
   */
  @SerializedName(value = "update_time")
  private String updateTime;
}
