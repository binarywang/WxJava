package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

/**
 * 小程序版本信息
 *
 * @author cocoa
 * created on  20220727
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenVersioninfoResult extends WxOpenResult  {

  private static final long serialVersionUID = -1042219138582803275L;

  @SerializedName("exp_info")
  ExpInfo expInfo;

  @SerializedName("release_info")
  ReleaseInfo releaseInfo;

  @Override
  public String toString() {
    return WxOpenGsonBuilder.create().toJson(this);
  }

  @Data
  public static class ReleaseInfo {
    /**
     * 发布线上版的时间
     */
    @SerializedName("release_time")
    private Long releaseTime;
    /**
     * 线上版版本信息
     */
    @SerializedName("release_version")
    private String releaseVersion;
    /**
     * 线上版本描述
     */
    @SerializedName("release_desc")
    private String releaseDesc;
  }


  @Data
  public static class ExpInfo {
    /**
     * 提交体验版的时间
     */
    @SerializedName("exp_time")
    private Long expTime;
    /**
     * 头像已使用修改次数（本月）
     */
    @SerializedName("exp_version")
    private String expVersion;
    /**
     * 头像修改次数总额度（本月）
     */
    @SerializedName("exp_desc")
    private String expDesc;
  }


}
