package com.github.binarywang.wxpay.bean.brandstore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/** 品牌门店基础信息。 */
@Data
public class BrandStoreBasics implements Serializable {
  private static final long serialVersionUID = 1L;

  /** 商家内部唯一门店编号，最长32字符。 */
  @SerializedName("store_reference_id")
  private String storeReferenceId;

  /** 纯分店名称，最长50字符，不包含品牌名或辅助符号。 */
  @SerializedName("branch_name")
  private String branchName;
}
