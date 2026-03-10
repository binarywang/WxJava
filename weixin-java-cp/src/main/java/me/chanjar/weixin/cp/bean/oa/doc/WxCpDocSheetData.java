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
 * 获取表格数据
 * 
 * @author zhongying
 * @since 2026-01-07
 */
@Data
public class WxCpDocSheetData extends WxCpBaseResp implements Serializable {
    private static final long serialVersionUID = 498054945993671723L;

    /**
     * 返回data
     */
    @SerializedName("grid_data")
    private GridData gridData;

    /**
     * From json sheet data.
     *
     * @param json the json
     * @return the sheet data
     */
    public static WxCpDocSheetData fromJson(String json) {
        return WxCpGsonBuilder.create().fromJson(json, WxCpDocSheetData.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    @Override
    public String toJson() {
        return WxCpGsonBuilder.create().toJson(this);
    }

    /**
     * 表格数据
     */
    @Getter
    @Setter
    public static class GridData implements Serializable {
        private static final long serialVersionUID = 389887488098521821L;

        /**
         * 起始行编号 （从0开始计算）
         */
        @SerializedName("start_row")
        private int startRow;

        /**
         * 起始列编号 （从0开始计算）
         */
        @SerializedName("start_column")
        private int startColumn;

        /**
         * 各行的数据
         */
        @SerializedName("rows")
        private List<RowData> rows;

        /**
         * 行数据
         */
        @Getter
        @Setter
        public static class RowData implements Serializable {
            private static final long serialVersionUID = 225648868492722337L;

            /**
             * 各个单元格的数据内容
             */
            @SerializedName("values")
            private List<CellData> values;

            /**
             * 单元格的信息
             */
            @Getter
            @Setter
            public static class CellData implements Serializable {
                private static final long serialVersionUID = 656471192719707304L;

                /**
                 * 单元格的数据内容
                 */
                @SerializedName("cell_value")
                private CellValue cellValue;

                /**
                 * 单元格的样式信息
                 */
                @SerializedName("cell_format")
                private CellFormat cellFormat;

                /**
                 * 单元格的数据内容，暂时只支持文本、链接，一个CellValue中只能选填一个字段
                 */
                @Getter
                @Setter
                public static class CellValue implements Serializable {
                    private static final long serialVersionUID = 656471192719707304L;

                    /**
                     * 文本
                     */
                    @SerializedName("text")
                    private String text;

                    /**
                     * 链接
                     */
                    @SerializedName("link")
                    private Link link;

                    /**
                     * 链接
                     */
                    @Getter
                    @Setter
                    public static class Link implements Serializable {
                        private static final long serialVersionUID = 912896452879347178L;

                        /**
                         * 链接url
                         */
                        @SerializedName("url")
                        private String url;

                        /**
                         * 链接标题
                         */
                        @SerializedName("text")
                        private String text;
                    }
                }

                /**
                 * 单元格的样式信息
                 */
                @Getter
                @Setter
                public static class CellFormat implements Serializable {
                    private static final long serialVersionUID = 656471192719707304L;

                    /**
                     * 文字样式
                     */

                    @SerializedName("text_format")
                    private TextFormat textFormat;

                    /**
                     * 文字样式
                     */
                    @Getter
                    @Setter
                    public static class TextFormat implements Serializable {
                        private static final long serialVersionUID = 184358104921122206L;

                        /**
                         * 字体
                         */
                        @SerializedName("font")
                        private String font;

                        /**
                         * 字体大小，最大72
                         */
                        @SerializedName("font_size")
                        private int fontSize;

                        /**
                         * 字体加粗
                         */
                        @SerializedName("bold")
                        private boolean bold;

                        /**
                         * 斜体
                         */
                        @SerializedName("italic")
                        private boolean italic;

                        /**
                         * 字体删除线
                         */
                        @SerializedName("strikethrough")
                        private boolean strikethrough;

                        /**
                         * 字体下划线
                         */
                        @SerializedName("underline")
                        private boolean underline;

                        /**
                         * 字体颜色
                         */
                        @SerializedName("color")
                        private Color color;

                        /**
                         * 字体颜色
                         */
                        @Getter
                        @Setter
                        public static class Color implements Serializable {
                            private static final long serialVersionUID = 140418085882147315L;

                            /**
                             * 红色，取值范围：[0,255]
                             */
                            @SerializedName("red")
                            private int red;

                            /**
                             * 绿色，取值范围：[0,255]
                             */
                            @SerializedName("green")
                            private int green;

                            /**
                             * 蓝色，取值范围：[0,255]
                             */
                            @SerializedName("blue")
                            private int blue;

                            /**
                             * alpha通道，取值范围：[0,255]，默认值为255完全不透明
                             */
                            @SerializedName("alpha")
                            private int alpha;

                        }

                    }
                }

            }

        }

    }

}
