package com.github.binarywang.wxpay.bean.brandstore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/** 品牌门店创建、查询或更新响应。 */
@Data
public class BrandStoreResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /** 品牌ID。 */
  @SerializedName("brand_id")
  private String brandId;

  /** 微信支付门店ID。 */
  @SerializedName("store_id")
  private String storeId;

  /** 门店状态：OPEN、CREATING、CLOSED。 */
  @SerializedName("store_state")
  private String storeState;

  /** 审核状态：SUCCESS、PROCESSING、REJECTED。 */
  @SerializedName("audit_state")
  private String auditState;

  /** 审核驳回原因。 */
  @SerializedName("review_reject_reason")
  private String reviewRejectReason;

  /** 门店基础信息。 */
  @SerializedName("store_basics")
  private BrandStoreBasics storeBasics;

  /** 门店地址信息。 */
  @SerializedName("store_address")
  private BrandStoreAddress storeAddress;

  /** 门店经营信息。 */
  @SerializedName("store_business")
  private BrandStoreBusiness storeBusiness;

  /** 绑定收款商户列表。 */
  @SerializedName("store_recipient")
  private List<BrandStoreRecipient> storeRecipient;
}
