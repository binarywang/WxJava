package com.github.binarywang.wxpay.bean.invoice;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.io.Serializable;
import java.util.List;
/** 将电子发票插入微信用户卡包请求。 */
@Data public class InsertCardRequest implements Serializable {
  private static final long serialVersionUID = 1L;
  @SerializedName("sub_mchid") private String subMchid;
  private String scene;
  @SerializedName("fapiao_apply_id") private String fapiaoApplyId;
  @SerializedName("buyer_information") private BuyerInformation buyerInformation;
  @SerializedName("fapiao_card_information") private List<Object> fapiaoCardInformation;
}
