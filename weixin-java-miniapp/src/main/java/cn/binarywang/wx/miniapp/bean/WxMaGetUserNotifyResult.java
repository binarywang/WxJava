package cn.binarywang.wx.miniapp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询服务卡片状态响应.
 *
 * <p>接口文档：
 * <a href="https://developers.weixin.qq.com/miniprogram/dev/server/API/mp-message-management/subscribe-message/api_getusernotify.html">
 * 查询服务卡片状态</a>
 *
 * @author GitHub Copilot
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaGetUserNotifyResult extends WxMaBaseResponse {
  private static final long serialVersionUID = 1L;

  /**
   * 卡片状态信息.
   */
  @SerializedName("notify_info")
  private NotifyInfo notifyInfo;

  /**
   * 卡片状态详情.
   */
  @Data
  public static class NotifyInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 卡片ID.
     */
    @SerializedName("notify_type")
    private Integer notifyType;

    /**
     * 上次有效推送的卡片状态与状态相关字段，没推送过为空字符串.
     */
    @SerializedName("content_json")
    private String contentJson;

    /**
     * code 状态：0 正常；1 有风险；2 异常；10 用户拒收本次code.
     */
    @SerializedName("code_state")
    private Integer codeState;

    /**
     * code 过期时间，秒级时间戳.
     */
    @SerializedName("code_expire_time")
    private Long codeExpireTime;
  }
}
