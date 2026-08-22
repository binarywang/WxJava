package com.github.binarywang.wxpay.bean.invoice;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.io.Serializable;
/** 服务商电子发票开发配置请求。 */
@Data public class DevelopmentConfigRequest implements Serializable {
  private static final long serialVersionUID = 1L;
  @SerializedName("callback_url") private String callbackUrl;
  @SerializedName("sub_mch_code") private String subMchCode;
  @SerializedName("show_fapiao_cell") private Boolean showFapiaoCell;
  @SerializedName("support_vat_fapiao") private Boolean supportVatFapiao;
}
