package com.github.binarywang.wxpay.bean.brandstore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/** 创建或更新品牌门店的请求。 */
@Data
public class BrandStoreRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /** 必填，品牌ID。 */
  @SerializedName("brand_id")
  private String brandId;

  /** 门店基础信息。 */
  @SerializedName("store_basics")
  private BrandStoreBasics storeBasics;

  /** 门店地址信息；创建时必填。 */
  @SerializedName("store_address")
  private BrandStoreAddress storeAddress;

  /** 门店经营信息。 */
  @SerializedName("store_business")
  private BrandStoreBusiness storeBusiness;
}
