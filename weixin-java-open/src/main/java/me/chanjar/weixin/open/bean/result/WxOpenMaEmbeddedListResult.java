package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.open.bean.ma.WxOpenMaEmbedded;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.util.List;

/**
 * 获取半屏小程序调用列表返回值
 *
 * @author Yuan
 * @version 1.0.0
 * @date 2024-12-04 18:06:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxOpenMaEmbeddedListResult extends WxOpenResult {
  private static final long serialVersionUID = -6484320771244602727L;

  /**
   * 半屏小程序列表
   */
  @SerializedName("wxa_embedded_list")
  private List<WxOpenMaEmbedded> embeddedList;

  /**
   * 授权方式,0表示需要管理员确认，1表示自动通过，2表示自动拒绝
   */
  @SerializedName("embedded_flag")
  private Integer embeddedFlag;

  @Override
  public String toString() {
    return WxOpenGsonBuilder.create().toJson(this);
  }

  public String toJson() {
    return WxOpenGsonBuilder.create().toJson(this);
  }
}
