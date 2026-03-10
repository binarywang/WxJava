package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 返回结果
 *
 * @author yqx, WangWong
 * @since 2020/3/16
 */
@Getter
@Setter
public class WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -4301684507150486556L;

  /**
   * The Errcode.
   */
  @SerializedName("errcode")
  protected Long errcode;

  /**
   * The Errmsg.
   */
  @SerializedName("errmsg")
  protected String errmsg;

  /**
   * Success boolean.
   *
   * @return the boolean
   */
  public boolean success() {
    return getErrcode() == 0;
  }

  /**
   * From json wx cp base resp.
   *
   * @param json the json
   * @return the wx cp base resp
   */
  public static WxCpBaseResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpBaseResp.class);
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
