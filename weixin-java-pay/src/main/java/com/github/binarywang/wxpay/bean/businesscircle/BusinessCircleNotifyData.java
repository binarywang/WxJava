package com.github.binarywang.wxpay.bean.businesscircle;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 智慧商圈回调通知对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/businesscircle/chapter3_1.shtml
 *   https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/businesscircle/chapter3_3.shtml
 * </pre>
 *
 * @author thinsstar
 */
@NoArgsConstructor
@Data
public class BusinessCircleNotifyData implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 通知ID
   */
  @SerializedName("id")
  private String id;

  /**
   * 通知创建时间
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * 通知类型
   */
  @SerializedName("event_type")
  private String eventType;

  /**
   * 通知数据类型
   */
  @SerializedName("resource_type")
  private String resourceType;

  /**
   * 回调摘要
   * summary
   */
  @SerializedName("summary")
  private String summary;

  /**
   * 通知数据
   */
  @SerializedName("resource")
  private Resource resource;

  @Data
  public static class Resource implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 加密算法类型
     */
    @SerializedName("algorithm")
    private String algorithm;

    /**
     * 数据密文
     */
    @SerializedName("ciphertext")
    private String cipherText;

    /**
     * 附加数据
     */
    @SerializedName("associated_data")
    private String associatedData;

    /**
     * 随机串
     */
    @SerializedName("nonce")
    private String nonce;

    /**
     * 原始回调类型
     */
    @SerializedName("original_type")
    private String originalType;
  }
}
