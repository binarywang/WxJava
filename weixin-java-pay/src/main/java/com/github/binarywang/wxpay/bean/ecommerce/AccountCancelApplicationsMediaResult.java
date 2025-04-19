package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 图片上传API
 * <pre>
 *   https://pay.weixin.qq.com/docs/partner/apis/ecommerce-cancel/media/upload-media.html
 * </pre>
 */
@Data
@NoArgsConstructor
public class AccountCancelApplicationsMediaResult implements Serializable {

  /**
   * 微信返回的媒体文件标识ID。
   */
  @SerializedName(value = "media_id")
  private String mediaId;
}
