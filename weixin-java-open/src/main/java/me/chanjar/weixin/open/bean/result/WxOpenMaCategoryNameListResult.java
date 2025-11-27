package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获取类目名称信息的返回结果.
 * <p>
 * 用于获取所有小程序类目的 ID 和名称信息，包括一级类目和二级类目。
 * </p>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/miniprogram-management/category-management/api_getallcategoryname.html">官方文档</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaCategoryNameListResult extends WxOpenResult {
  private static final long serialVersionUID = 8989721350285449879L;

  /**
   * 类目名称列表.
   */
  @SerializedName("category_name_list")
  private List<CategoryName> categoryNameList;

  @Override
  public String toString() {
    return WxOpenGsonBuilder.create().toJson(this);
  }

  /**
   * 类目名称信息.
   * <p>
   * 包含一级类目和二级类目的 ID 和名称。
   * </p>
   */
  @Data
  public static class CategoryName implements Serializable {
    private static final long serialVersionUID = 8989721350285449880L;

    /**
     * 一级类目ID.
     */
    @SerializedName("first_id")
    private Integer firstId;

    /**
     * 一级类目名称.
     */
    @SerializedName("first_name")
    private String firstName;

    /**
     * 二级类目ID.
     */
    @SerializedName("second_id")
    private Integer secondId;

    /**
     * 二级类目名称.
     */
    @SerializedName("second_name")
    private String secondName;

    @Override
    public String toString() {
      return WxOpenGsonBuilder.create().toJson(this);
    }
  }
}
