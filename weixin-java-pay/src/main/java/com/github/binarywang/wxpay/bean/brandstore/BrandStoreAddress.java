package com.github.binarywang.wxpay.bean.brandstore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/** 品牌门店地址信息。 */
@Data
public class BrandStoreAddress implements Serializable {
  private static final long serialVersionUID = 1L;

  /** 必填，微信支付省市区编码。 */
  @SerializedName("address_code")
  private String addressCode;

  /** 必填，精确到门牌号的地址，不重复省市区。 */
  @SerializedName("address_detail")
  private String addressDetail;

  /** 地址辅助描述。 */
  @SerializedName("address_complements")
  private String addressComplements;

  /** 腾讯地图经度，范围[-180,180]。 */
  @SerializedName("longitude")
  private String longitude;

  /** 腾讯地图纬度，范围[-90,90]。 */
  @SerializedName("latitude")
  private String latitude;
}
