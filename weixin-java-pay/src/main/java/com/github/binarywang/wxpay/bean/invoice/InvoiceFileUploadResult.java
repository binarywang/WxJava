package com.github.binarywang.wxpay.bean.invoice;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.io.Serializable;
/** 上传电子发票文件结果。 */
@Data public class InvoiceFileUploadResult implements Serializable {
  private static final long serialVersionUID = 1L;
  @SerializedName("fapiao_media_id") private String fapiaoMediaId;
}
