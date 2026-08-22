package com.github.binarywang.wxpay.bean.invoice;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.io.Serializable;
/** 获取用户抬头填写链接请求。 */
@Data public class TitleUrlRequest implements Serializable {
  private static final long serialVersionUID = 1L;
  @SerializedName("sub_mchid") private String subMchid;
  @SerializedName("fapiao_apply_id") private String fapiaoApplyId;
  private String source;
  private String appid;
  private String openid;
  @SerializedName("total_amount") private Integer totalAmount;
  @SerializedName("seller_name") private String sellerName;
  @SerializedName("show_phone_cell") private Boolean showPhoneCell;
  @SerializedName("must_input_phone") private Boolean mustInputPhone;
  @SerializedName("show_email_cell") private Boolean showEmailCell;
  @SerializedName("must_input_email") private Boolean mustInputEmail;
}
