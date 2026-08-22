package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

/**
 * 获取账号所有可以设置的类目.
 *
 * @author Hipple
 * @since 2019/1/26 18:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxFastMaCanSetCategoryResult extends WxOpenResult {
  private static final long serialVersionUID = -2469386233538980102L;
  @SerializedName("categories_list")
  private CategoriesListBean categoriesList;

  /**
   * 错误码，已弃用，未来将删除
   *
   * @see WxOpenResult#getErrcode() 应使用此方法
   */
  @Deprecated
  public int getErrCode() {
    return NumberUtils.toInt(this.errcode);
  }

  /**
   * 错误码，已弃用，未来将删除
   *
   * @see WxOpenResult#setErrcode(String) 应使用此方法
   */
  @Deprecated
  public void setErrCode(int errCode) {
    this.errcode = String.valueOf(errCode);
  }

  @Data
  public static class CategoriesListBean {
    private List<CategoriesBean> categories;

    @Data
    public static class CategoriesBean {
      private int id;
      private QualifyBean qualify;
      private String name;
      private int level;
      private int father;
      @SerializedName("sensitive_type")
      private int sensitiveType;
      @SerializedName("available_for_plugin")
      private boolean availableForPlugin;
      @SerializedName("is_hidden")
      private boolean isHidden;
      private String type;
      @SerializedName("need_report")
      private int needReport;
      @SerializedName("can_use_cityserivce")
      private int canUseCityService;
      private List<Integer> children;
      @SerializedName("type_list")
      private List<?> typeList;
      @SerializedName("available_api_list")
      private List<?> availableApiList;
      private List<?> apis;

      @Data
      public static class QualifyBean {
        private String remark;
        @SerializedName("exter_list")
        private List<?> externalList;
      }

    }
  }
}
