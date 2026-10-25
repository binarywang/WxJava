package com.github.binarywang.wxpay.bean.brandstore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/** 品牌门店经营信息。 */
@Data
public class BrandStoreBusiness implements Serializable {
  private static final long serialVersionUID = 1L;

  /** 服务电话，最多两个，以英文竖线分隔。 */
  @SerializedName("service_phone")
  private String servicePhone;

  /** 经营时间，最多七个时间段，以英文竖线分隔。 */
  @SerializedName("business_hours")
  private String businessHours;
}
