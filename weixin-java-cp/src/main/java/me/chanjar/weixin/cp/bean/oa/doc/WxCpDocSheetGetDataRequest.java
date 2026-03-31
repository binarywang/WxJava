package me.chanjar.weixin.cp.bean.oa.doc;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 获取表格数据请求
 * 
 * @author zhongying
 * @since 2026-01-07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpDocSheetGetDataRequest implements Serializable {
    private static final long serialVersionUID = 827718590347822812L;

    /**
     * 在线表格唯一标识.必填
     */
    @SerializedName("docid")
    private String docId;

    /**
     * 工作表ID，工作表的唯一标识.必填
     */
    @SerializedName("sheet_id")
    private String sheetId;

    /**
     * 查询的范围，格式为"A1:B2"，表示获取A1到B2单元格的数据.必填
     */
    @SerializedName("range")
    private String range;

    /**
     * From json to {@link WxCpDocSheetGetDataRequest}.
     *
     * @param json the json string representing {@link WxCpDocSheetGetDataRequest}
     * @return the {@link WxCpDocSheetGetDataRequest} object
     */
    public static WxCpDocSheetGetDataRequest fromJson(String json) {
        return WxCpGsonBuilder.create().fromJson(json, WxCpDocSheetGetDataRequest.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
        return WxCpGsonBuilder.create().toJson(this);
    }
}
