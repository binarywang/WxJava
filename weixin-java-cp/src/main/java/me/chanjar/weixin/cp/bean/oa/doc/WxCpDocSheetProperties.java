package me.chanjar.weixin.cp.bean.oa.doc;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 获取表格行列信息
 * 
 * @author zhongying
 * @since 2026-01-07
 */
@Data
public class WxCpDocSheetProperties extends WxCpBaseResp implements Serializable {
    private static final long serialVersionUID = 666707252457243065L;

    @SerializedName("properties")
    private List<Properties> properties;

    /**
     * From json sheet properties.
     *
     * @param json the json
     * @return the sheet properties
     */
    public static WxCpDocSheetProperties fromJson(String json) {
        return WxCpGsonBuilder.create().fromJson(json, WxCpDocSheetProperties.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
        return WxCpGsonBuilder.create().toJson(this);
    }

    /**
     * 工作表属性
     */
    @Getter
    @Setter
    public static class Properties implements Serializable {
        private static final long serialVersionUID = 640301090538839892L;

        /**
         * 工作表ID，工作表的唯一标识
         */
        @SerializedName("sheet_id")
        private String sheetId;

        /**
         * 工作表名称
         */
        @SerializedName("title")
        private String title;

        /**
         * 表格的总行数
         */
        @SerializedName("row_count")
        private int rowCount;

        /**
         * 表格的总列数
         */
        @SerializedName("column_count")
        private int columnCount;

    }
}
