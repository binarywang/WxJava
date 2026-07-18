package com.github.binarywang.wxpay.bean.invoice;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.io.Serializable;

/** 创建电子发票卡券模板请求。 */
@Data
public class CardTemplateRequest implements Serializable {
  private static final long serialVersionUID = 1L;
  @SerializedName("sub_mchid") private String subMchid;
  @SerializedName("card_appid") private String cardAppid;
  @SerializedName("card_template_information") private TemplateInformation cardTemplateInformation;
  @Data public static class TemplateInformation implements Serializable {
    private static final long serialVersionUID = 1L;
    @SerializedName("payee_name") private String payeeName;
    @SerializedName("logo_url") private String logoUrl;
    @SerializedName("custom_cell") private CustomCell customCell;
  }
  @Data public static class CustomCell implements Serializable {
    private static final long serialVersionUID = 1L;
    private String words;
    private String description;
    @SerializedName("jump_url") private String jumpUrl;
    @SerializedName("miniprogram_user_name") private String miniprogramUserName;
    @SerializedName("miniprogram_path") private String miniprogramPath;
  }
}
