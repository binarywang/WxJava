package com.github.binarywang.wxpay.bean.invoice;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.io.File;
import java.io.Serializable;
/** 上传电子发票 PDF 文件请求。digest 为官方要求的 SM3 十六进制摘要。 */
@Data public class InvoiceFileUploadRequest implements Serializable {
  private static final long serialVersionUID = 1L;
  private File file;
  @SerializedName("sub_mchid") private String subMchid;
  @SerializedName("file_type") private String fileType = "PDF";
  @SerializedName("digest_alogrithm") private String digestAlgorithm = "SM3";
  private String digest;
}
