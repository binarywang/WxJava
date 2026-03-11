package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 「联系我」方式 列表返回对象
 *
 * @author <a href="https://github.com/imyzt">imyzt</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class WxCpContactWayList extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -8697184659526210472L;

  @SerializedName("contact_way")
  private List<ContactWay> contactWay;

  /**
   * 分页参数，用于查询下一个分页的数据，为空时表示没有更多的分页
   */
  @SerializedName("next_cursor")
  private String nextCursor;

  /**
   * The type Contact way.
   */
  @Getter
  @Setter
  public static class ContactWay implements Serializable {
    private static final long serialVersionUID = -8697184659526210472L;

    /**
     * 联系方式的配置id
     */
    @SerializedName("config_id")
    private String configId;
  }

  /**
   * From json wx cp contact way list.
   *
   * @param json the json
   * @return the wx cp contact way list
   */
  public static WxCpContactWayList fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpContactWayList.class);
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
