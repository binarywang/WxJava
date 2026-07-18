package com.github.binarywang.wxpay.bean.invoice;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.io.Serializable;
/** 电子发票卡券模板结果。 */
@Data public class CardTemplateResult implements Serializable {
  private static final long serialVersionUID = 1L;
  @SerializedName("card_appid") private String cardAppid;
  @SerializedName("card_id") private String cardId;
}
