package me.chanjar.weixin.mp.bean.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Mklaus
 * created on  2018-01-22 下午12:18
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class WxMpSubscribeMessage implements Serializable {

  /**
   * 接收者openid.
   */
  private String toUser;

  /**
   * 模板ID.
   */
  private String templateId;

  /**
   * 模板跳转链接.
   * <pre>
   * url和miniprogram都是非必填字段，若都不传则模板无跳转；若都传，会优先跳转至小程序。
   * 开发者可根据实际需要选择其中一种跳转方式即可。当用户的微信客户端版本不支持跳小程序时，将会跳转至url。
   * </pre>
   */
  private String url;

  /**
   * 跳小程序所需数据，不需跳小程序可不用传该数据.
   *
   * @see #url
   */
  private MiniProgram miniProgram;

  /**
   * 订阅场景值
   */
  private String scene;

  /**
   * 消息标题 (15字以内)
   */
  private String title;

  /**
   * 消息内容文本 （200字以内）
   */
  private String contentValue;

  /**
   * 消息内容文本颜色
   */
  private String contentColor;

  /**
   * 跳转网页时填写.
   */
  private String page;

  /**
   * 订阅通知消息专用
   */
  private Map<String, String> dataMap;

  public String toJson() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MiniProgram implements Serializable {
    private static final long serialVersionUID = -7945254706501974849L;

    private String appid;
    private String pagePath;

    /**
     * 是否使用path，否则使用pagepath.
     * 加入此字段是基于微信官方接口变化多端的考虑
     */
    private boolean usePath = false;
  }
}
