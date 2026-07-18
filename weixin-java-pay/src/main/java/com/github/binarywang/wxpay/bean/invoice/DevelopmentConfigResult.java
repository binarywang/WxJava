package com.github.binarywang.wxpay.bean.invoice;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.io.Serializable;
/** 服务商电子发票开发配置结果。 */
@Data public class DevelopmentConfigResult implements Serializable {
  private static final long serialVersionUID = 1L;
  @SerializedName("callback_url") private String callbackUrl;
  @SerializedName("show_fapiao_cell") private Boolean showFapiaoCell;
  @SerializedName("support_vat_fapiao") private Boolean supportVatFapiao;
}
