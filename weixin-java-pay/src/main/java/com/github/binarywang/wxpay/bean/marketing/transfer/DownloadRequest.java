package com.github.binarywang.wxpay.bean.marketing.transfer;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 下载电子回单API
 * 接口说明
 * 适用对象：直连商户 服务商
 * 请求URL：通过申请账单接口获取到“download_url”，URL有效期10min
 * 请求方式：GET
 * 前置条件：调用申请账单接口并获取到“download_url”
 * 接口规则：https://pay.weixin.qq.com/wiki/doc/apiv3/wechatpay/wechatpay-1.shtml
 *
 * @author xiaoqiang
 * created on  2021-12-06
 */
@Data
@NoArgsConstructor
public class DownloadRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：电子回单文件的hash方法
   * 变量名：hash_type
   * 是否必填：否
   * 类型：string[1, 20]
   * 描述：
   *  电子回单文件的hash方法，回单状态为：FINISHED时返回
   *  例值：SHA256
   * </pre>
   */
  @SerializedName(value = "hash_type")
  private String hashType;
  /**
   * <pre>
   * 字段名：电子回单文件的hash值
   * 变量名：hash_value
   * 是否必填：否
   * 类型：string[3, 1000]
   * 描述：
   *  电子回单文件的hash值，用于下载之后验证文件的完整、正确性，回单状态为：FINISHED时返回
   *  示例值：DE731F35146A0BEFADE5DB9D1E468D96C01CA8898119C674FEE9F11F4DBE5529
   * </pre>
   */
  @SerializedName(value = "hash_value")
  private String hashValue;
  /**
   * <pre>
   * 字段名：电子回单文件的下载地址
   * 变量名：download_url
   * 是否必填：否
   * 类型：string[10, 3000]
   * 描述：
   *  电子回单文件的下载地址，回单状态为：FINISHED时返回。URL有效时长为10分钟，10分钟后需要重新去获取下载地址（但不需要走受理）
   * 示例值：https://api.mch.weixin.qq.com/v3/billdownload/file?token=xxx
   * </pre>
   */
  @SerializedName(value = "download_url")
  private String downloadUrl;
}
