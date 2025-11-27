package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获取类目名称信息的返回结果.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
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
