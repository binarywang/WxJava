package me.chanjar.weixin.cp.bean.oa.doc;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.oa.doc.WxCpDocSheetProperties.Properties;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 更新操作(UpdateRequest])对应的响应结构体类型
 * 
 * @author zhongying
 * @since 2026-01-08
 */
@Data
public class WxCpDocSheetBatchUpdateResponse implements Serializable {
    private static final long serialVersionUID = 781694717017131015L;

    /**
     * 新增工作表响应
     */
    @SerializedName("add_sheet_response")
    private AddSheetResponse addSheetResponse;

    /**
     * 删除工作表响应
     */
    @SerializedName("delete_sheet_response")
    private DeleteSheetResponse deleteSheetResponse;

    /**
     * 更新范围内单元格内容响应
     */
    @SerializedName("update_range_response")
    private UpdateRangeResponse updateRangeResponse;

    /**
     * 删除表格连续的行或列响应
     */
    @SerializedName("delete_dimension_response")
    private DeleteDimensionResponse deleteDimensionResponse;

    /**
     * 从 JSON 字符串反序列化为 WxCpDocSheetBatchUpdateResponse 对象。
     *
     * @param json the json
     * @return 反序列化得到的 WxCpDocSheetBatchUpdateResponse 对象
     */
    public static WxCpDocSheetBatchUpdateResponse fromJson(String json) {
        return WxCpGsonBuilder.create().fromJson(json, WxCpDocSheetBatchUpdateResponse.class);
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
     * 新增工作表响应
     */
    @Getter
    @Setter
    public static class AddSheetResponse implements Serializable {
        private static final long serialVersionUID = 618814209485621336L;

        /**
         * 新增子表的属性
         */
        @SerializedName("properties")
        private Properties properties;
    }

    /**
     * 删除工作表响应
     */
    @Getter
    @Setter
    public static class DeleteSheetResponse implements Serializable {
        private static final long serialVersionUID = 625927337093938411L;

        /**
         * 被删除的工作表的唯一标识
         */
        @SerializedName("sheet_id")
        private String sheetId;
    }

    /**
     * 更新范围内单元格内容响应
     */
    @Getter
    @Setter
    public static class UpdateRangeResponse implements Serializable {
        private static final long serialVersionUID = 180842641209787414L;

        /**
         * 数据更新的成功的单元格数量
         */
        @SerializedName("updated_cells")
        private int updatedCells;
    }

    /**
     * 删除表格连续的行（或列），请求响应体结构
     */
    @Getter
    @Setter
    public static class DeleteDimensionResponse implements Serializable {
        private static final long serialVersionUID = 107245521502978033L;

        /**
         * 被删除的行数（或列数）
         */
        @SerializedName("deleted")
        private int deleted;
    }

}
