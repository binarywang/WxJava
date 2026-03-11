package me.chanjar.weixin.mp.bean.template;

import lombok.*;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板消息.
 * 参考 <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277&token=&lang=zh_CN">发送模板消息接口部分</a>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WxMpTemplateMessage implements Serializable {
  private static final long serialVersionUID = 5063374783759519418L;

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
   * 防重入id.
   */
  private String clientMsgId;

  /**
   * 模板数据.
   */
  @Builder.Default
  private List<WxMpTemplateData> data = new ArrayList<>();

  public WxMpTemplateMessage addData(WxMpTemplateData datum) {
    if (this.data == null) {
      this.data = new ArrayList<>();
    }
    this.data.add(resetValue(datum));
    return this;
  }

  /**
   * 处理微信模版消息字符串长度问题
   *
   * @link <a href=https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Template_Message_Interface.html#%E7%B1%BB%E7%9B%AE%E6%A8%A1%E6%9D%BF%E6%B6%88%E6%81%AF">模板消息</a>
   */
  private WxMpTemplateData resetValue(WxMpTemplateData datum) {
    String name = datum.getName();
    String value = datum.getValue();

    if (StringUtils.startsWith(name, "thing") && value.length() > 20) {
      value = StringUtils.substring(value, 0, 17) + "...";
    } else if (StringUtils.startsWith(name, "character_string") && value.length() > 32) {
      value = StringUtils.substring(value, 0, 29) + "...";
    } else if (StringUtils.startsWith(name, "phone_number") && value.length() > 17) {
      value = StringUtils.substring(value, 0, 14) + "...";
    } else if (StringUtils.startsWith(name, "car_number") && value.length() > 8) {
      value = StringUtils.substring(value, 0, 5) + "...";
    } else if (StringUtils.startsWith(name, "const") && value.length() > 20) {
      value = StringUtils.substring(value, 0, 17) + "...";
    }

    datum.setValue(value);
    return datum;
  }

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
