package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 获取所有类目
 * author 痴货
 */
@Getter
@Setter
@NoArgsConstructor
public class WxOpenGetAllCategoriesByTypeResult extends WxOpenResult {

  private static final long serialVersionUID = -2845321894615646115L;

  /**
   * 类目信息列表
   */
  @SerializedName("categories_list")
  private CategoriesList categorieslist;

  @Getter
  @Setter
  @NoArgsConstructor
  public static class CategoriesList implements Serializable {

    private static final long serialVersionUID = -2845321894615646115L;

    /**
     * 类目信息
     */
    @SerializedName("categories")
    private List<Categories> categories;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  public static class Categories implements Serializable {

    private static final long serialVersionUID = -284532256461546115L;

    /**
     * 类目 ID
     */
    @SerializedName("id")
    private Integer id;

    /**
     * 类目名称
     */
    @SerializedName("name")
    private String name;

    /**
     * 类目层级
     */
    @SerializedName("level")
    private Integer level;

    /**
     * 父类目 ID
     */
    @SerializedName("father")
    private Integer father;

    /**
     * 子类目 ID
     */
    @SerializedName("children")
    private List<Integer> children;

    /**
     * 是否敏感类目（1 为敏感类目，需要提供相应资质审核；0 为非敏感类目，无需审核）
     */
    @SerializedName("sensitive_type")
    private Integer sensitiveType;

    /**
     * sensitive_type 为 1 的类目需要提供的资质证明
     */
    @SerializedName("qualify")
    private Qualify qualify;

    /**
     * 类目权限范围
     */
    @SerializedName("scope")
    private String scope;

  }

  @Getter
  @Setter
  @NoArgsConstructor
  public static class Qualify implements Serializable {

    private static final long serialVersionUID = -2841894318945616115L;

    /**
     * 资质证明列表
     */
    @SerializedName("exter_list")
    private List<Exter> exterList;

    /**
     * 备注
     */
    @SerializedName("remark")
    private String remark;

  }

  @Getter
  @Setter
  @NoArgsConstructor
  public static class Exter implements Serializable {

    private static final long serialVersionUID = -2841894318945616115L;

    @SerializedName("inner_list")
    private List<Inner> innerList;

  }

  @Getter
  @Setter
  @NoArgsConstructor
  public static class Inner implements Serializable {

    private static final long serialVersionUID = -15646131313516531L;

    /**
     * 资质文件名称
     */
    @SerializedName("name")
    private String name;

    /**
     * 资质文件示例
     */
    @SerializedName("url")
    private String url;

  }

}
