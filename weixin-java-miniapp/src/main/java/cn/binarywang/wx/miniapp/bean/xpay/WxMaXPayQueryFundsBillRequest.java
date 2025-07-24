package cn.binarywang.wx.miniapp.bean.xpay;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @date 2025-07-23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaXPayQueryFundsBillRequest implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;
  @SerializedName("page")
  private Integer page;
  @SerializedName("page_size")
  private Integer pageSize;
  @SerializedName("filter")
  private Filter filter;
  @SerializedName("env")
  private Integer env;

  @Data
  public static class Filter {
    @SerializedName("oper_time_begin")
    private Long operTimeBegin;
    @SerializedName("oper_time_end")
    private Long operTimeEnd;
    @SerializedName("bill_id")
    private String billId;
    @SerializedName("request_id")
    private String requestId;
  }

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
