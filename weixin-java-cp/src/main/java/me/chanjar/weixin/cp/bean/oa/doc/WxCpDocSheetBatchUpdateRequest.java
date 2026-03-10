package me.chanjar.weixin.cp.bean.oa.doc;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.bean.oa.doc.WxCpDocSheetData.GridData;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 编辑表格内容请求
 * 
 * @author zhongying
 * @since 2026-01-07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpDocSheetBatchUpdateRequest implements Serializable {
    private static final long serialVersionUID = 584565591133421347L;

    /**
     * 文档的docid.必填
     */
    @SerializedName("docid")
    private String docId;

    /**
     * 更新操作列表.必填
     */
    @SerializedName("requests")
    private List<Request> requests;

    /**
     * From json wx cp doc sheet batch update request.
     *
     * @param json the json
     * @return the wx cp doc sheet batch update request
     */
    public static WxCpDocSheetBatchUpdateRequest fromJson(String json) {
        return WxCpGsonBuilder.create().fromJson(json, WxCpDocSheetBatchUpdateRequest.class);
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
     * 更新操作
     */
    @Getter
    @Setter
    public static class Request implements Serializable {
        private static final long serialVersionUID = 253933038745894628L;

        /**
         * 新增工作表
         */
        @SerializedName("add_sheet_request")
        private AddSheetRequest addSheetRequest;

        /**
         * 删除工作表请求
         */
        @SerializedName("delete_sheet_request")
        private DeleteSheetRequest deleteSheetRequest;

        /**
         * 更新范围内单元格内容
         */
        @SerializedName("update_range_request")
        private UpdateRangeRequest updateRangeRequest;

        /**
         * 删除表格连续的行或列
         */
        @SerializedName("delete_dimension_request")
        private DeleteDimensionRequest deleteDimensionRequest;

        /**
         * 新增工作表，新增需满足以下限制
         * 范围列数 <=200
         * 范围内的总单元格数量 <=10000
         */
        @Getter
        @Setter
        public static class AddSheetRequest implements Serializable {
            private static final long serialVersionUID = 523704967699486288L;

            /**
             * 工作表名称
             */
            @SerializedName("title")
            private String title;

            /**
             * 新增工作表的初始行数
             */
            @SerializedName("row_count")
            private int rowCount;

            /**
             * 新增工作表的初始列数
             */
            @SerializedName("column_count")
            private int columnCount;
        }

        /**
         * 删除工作表请求
         */
        @Getter
        @Setter
        public static class DeleteSheetRequest implements Serializable {
            private static final long serialVersionUID = 767974765396168274L;

            /**
             * 工作表唯一标识
             */
            @SerializedName("sheet_id")
            private String sheetId;
        }

        /**
         * 更新范围内单元格内容
         */
        @Getter
        @Setter
        public static class UpdateRangeRequest implements Serializable {
            private static final long serialVersionUID = 433859595039061888L;

            /**
             * 工作表唯一标识
             */
            @SerializedName("sheet_id")
            private String sheetId;

            /**
             * 表格数据
             */
            @SerializedName("grid_data")
            private GridData gridData;
        }

        /**
         * 删除表格连续的行或列
         * 注意：
         * 1.该操作会导致表格缩表
         * 2.删除的范围遵循 左闭右开 ———— [start_index,end_index) ，如果 end_index <= start_index
         * 则该请求报错。
         */
        @Getter
        @Setter
        public static class DeleteDimensionRequest implements Serializable {
            private static final long serialVersionUID = 107245521502978033L;

            /**
             * 工作表唯一标识
             */
            @SerializedName("sheet_id")
            private String sheetId;

            /**
             * 删除的维度类型.
             * ROW:行
             * COLUMN：列
             */
            @SerializedName("dimension")
            private String dimension;

            /**
             * 删除行列的起始序号（从1开始）
             */
            @SerializedName("start_index")
            private int startIndex;

            /**
             * 删除行列的终止序号（从1开始）
             */
            @SerializedName("end_index")
            private int endIndex;
        }
    }
}
