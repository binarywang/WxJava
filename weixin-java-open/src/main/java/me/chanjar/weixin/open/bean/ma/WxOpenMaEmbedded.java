package me.chanjar.weixin.open.bean.ma;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.Serializable;

/**
 * 微信开放平台半屏小程序信息
 *
 * @author Yuan
 * @version 1.0.0
 * @date 2024-12-04 17:57:40
 */
@Data
public class WxOpenMaEmbedded implements Serializable {
  private static final long serialVersionUID = -7319330493157204072L;

  /**
   * 半屏小程序appid
   */
  @SerializedName("appid")
  private String appId;
  /**
   * 添加时间
   */
  @SerializedName("create_time")
  private Long createTime;
  /**
   * 头像url
   */
  @SerializedName("headimg")
  private String headImg;
  /**
   * 半屏小程序昵称
   */
  @SerializedName("nickname")
  private String nickName;
  /**
   * 申请理由
   */
  @SerializedName("reason")
  private String reason;
  /**
   * 申请状态,1-待验证，2-已通过，3-已拒绝，4-已超时，5-已撤销，6-已取消授权
   */
  @SerializedName("status")
  private String status;

  @Override
  public String toString() {
    return WxOpenGsonBuilder.create().toJson(this);
  }

  public String toJson() {
    return WxOpenGsonBuilder.create().toJson(this);
  }
}
