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
public class WxMaXPayGetComplaintListRequest implements Serializable {
    private static final long serialVersionUID = 7495157056049312108L;
    @SerializedName("env")
    private Integer env;
    @SerializedName("begin_date")
    private String beginDate;
    @SerializedName("end_date")
    private String endDate;
    @SerializedName("offset")
    private Integer offset;
    @SerializedName("limit")
    private Integer limit;


    public String toJson() {
        return WxMaGsonBuilder.create().toJson(this);
    }
}
