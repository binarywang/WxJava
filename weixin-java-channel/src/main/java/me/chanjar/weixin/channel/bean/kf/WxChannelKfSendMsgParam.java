package me.chanjar.weixin.channel.bean.kf;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 发送客服消息请求参数
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class WxChannelKfSendMsgParam implements Serializable {

  private static final long serialVersionUID = -7384287911696365032L;

  /** 唯一任务ID，填写后按任务ID去重 */
  @JsonProperty("request_id")
  private String requestId;

  /** 用户 open_id */
  @JsonProperty("open_id")
  private String openId;

  /** 消息类型 */
  @JsonProperty("msg_type")
  private String msgType;

  /** 文本消息 */
  @JsonProperty("text")
  private TextMessage text;

  /** 图片消息 */
  @JsonProperty("image")
  private CosUrlMessage image;

  /** 视频消息 */
  @JsonProperty("video")
  private CosUrlMessage video;

  /** 文件消息 */
  @JsonProperty("file")
  private CosUrlMessage file;

  /** 商品卡片消息 */
  @JsonProperty("product_share")
  private ProductShareMessage productShare;

  /** 订单卡片消息 */
  @JsonProperty("order_share")
  private OrderShareMessage orderShare;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class TextMessage implements Serializable {

    private static final long serialVersionUID = -5001585611550636499L;

    @JsonProperty("content")
    private String content;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CosUrlMessage implements Serializable {

    private static final long serialVersionUID = 8403720861098936947L;

    @JsonProperty("cos_url")
    private String cosUrl;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ProductShareMessage implements Serializable {

    private static final long serialVersionUID = -3049552399099249795L;

    @JsonProperty("product_id")
    private String productId;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class OrderShareMessage implements Serializable {

    private static final long serialVersionUID = 7136546635145180607L;

    @JsonProperty("order_id")
    private String orderId;
  }
}
