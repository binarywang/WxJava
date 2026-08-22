package com.github.binarywang.wxpay.bean.invoice;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 电子发票文件下载信息。
 *
 * @see <a href="https://pay.weixin.qq.com/doc/v3/partner/4015792576">获取发票下载信息</a>
 */
@Data
public class InvoiceFileResult implements Serializable {
  private static final long serialVersionUID = 1L;

  @SerializedName("fapiao_download_info_list")
  private List<DownloadInfo> fapiaoDownloadInfoList;

  @Data
  public static class DownloadInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("fapiao_id")
    private String fapiaoId;
    @SerializedName("download_url")
    private String downloadUrl;
    private String status;
  }
}
