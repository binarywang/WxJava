package com.binarywang.wxjava.store.bean.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 发送客服消息请求参数。 */
@Data
@NoArgsConstructor
public class WxStoreKfSendMsgParam implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 请求幂等标识。 */
  @JsonProperty("request_id")
  private String requestId;

  /** 接收消息的用户 openid。 */
  @JsonProperty("open_id")
  private String openId;

  /** 消息类型。 */
  @JsonProperty("msg_type")
  private String msgType;

  /** 文本消息内容。 */
  @JsonProperty("text")
  private Text text;

  /** 图片消息内容。 */
  @JsonProperty("image")
  private CosUrlMessage image;

  /** 视频消息内容。 */
  @JsonProperty("video")
  private CosUrlMessage video;

  /** 文件消息内容。 */
  @JsonProperty("file")
  private CosUrlMessage file;

  /** 商品卡片消息内容。 */
  @JsonProperty("product_share")
  private ProductShareMessage productShare;

  /** 订单卡片消息内容。 */
  @JsonProperty("order_share")
  private OrderShareMessage orderShare;

  @Data
  @NoArgsConstructor
  public static class Text implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("content")
    private String content;
  }

  @Data
  @NoArgsConstructor
  public static class CosUrlMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("cos_url")
    private String cosUrl;
  }

  @Data
  @NoArgsConstructor
  public static class ProductShareMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("product_id")
    private String productId;
  }

  @Data
  @NoArgsConstructor
  public static class OrderShareMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("order_id")
    private String orderId;
  }
}
