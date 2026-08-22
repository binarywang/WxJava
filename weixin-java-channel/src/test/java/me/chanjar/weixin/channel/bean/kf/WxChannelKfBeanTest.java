package me.chanjar.weixin.channel.bean.kf;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import me.chanjar.weixin.channel.util.JsonUtils;
import org.testng.annotations.Test;

/** JSON serialization tests for channel customer service models. */
public class WxChannelKfBeanTest {

  @Test
  public void testSendMsgParamJson() {
    WxChannelKfSendMsgParam param = createSendMsgParam("text");
    WxChannelKfSendMsgParam.Text text = new WxChannelKfSendMsgParam.Text();
    text.setContent("hello");
    param.setText(text);

    String json = JsonUtils.encode(param);
    assertNotNull(json);
    assertFalse(json.contains("requestId"));
    assertFalse(json.contains("openId"));
    assertFalse(json.contains("msgType"));
    WxChannelKfSendMsgParam decoded = JsonUtils.decode(json, WxChannelKfSendMsgParam.class);
    assertEquals(decoded.getRequestId(), "request-1");
    assertEquals(decoded.getOpenId(), "open-1");
    assertEquals(decoded.getMsgType(), "text");
    assertNotNull(decoded.getText());
    assertEquals(decoded.getText().getContent(), "hello");
  }

  @Test
  public void testImageMessageJson() {
    WxChannelKfSendMsgParam param = createSendMsgParam("image");
    WxChannelKfSendMsgParam.CosUrlMessage image = new WxChannelKfSendMsgParam.CosUrlMessage();
    image.setCosUrl("https://example.test/image");
    param.setImage(image);

    assertCosUrlMessageJson(param, "image", "https://example.test/image");
  }

  @Test
  public void testVideoMessageJson() {
    WxChannelKfSendMsgParam param = createSendMsgParam("video");
    WxChannelKfSendMsgParam.CosUrlMessage video = new WxChannelKfSendMsgParam.CosUrlMessage();
    video.setCosUrl("https://example.test/video");
    param.setVideo(video);

    assertCosUrlMessageJson(param, "video", "https://example.test/video");
  }

  @Test
  public void testFileMessageJson() {
    WxChannelKfSendMsgParam param = createSendMsgParam("file");
    WxChannelKfSendMsgParam.CosUrlMessage file = new WxChannelKfSendMsgParam.CosUrlMessage();
    file.setCosUrl("https://example.test/file");
    param.setFile(file);

    assertCosUrlMessageJson(param, "file", "https://example.test/file");
  }

  @Test
  public void testProductShareMessageJson() {
    WxChannelKfSendMsgParam param = createSendMsgParam("product_share");
    WxChannelKfSendMsgParam.ProductShareMessage product =
      new WxChannelKfSendMsgParam.ProductShareMessage();
    product.setProductId("product-1");
    param.setProductShare(product);

    String json = JsonUtils.encode(param);
    assertTrue(json.contains("\"product_share\":{\"product_id\":\"product-1\"}"));
    assertFalse(json.contains("productShare"));
    assertFalse(json.contains("productId"));
    WxChannelKfSendMsgParam decoded = JsonUtils.decode(json, WxChannelKfSendMsgParam.class);
    assertEquals(decoded.getProductShare().getProductId(), "product-1");
  }

  @Test
  public void testOrderShareMessageJson() {
    WxChannelKfSendMsgParam param = createSendMsgParam("order_share");
    WxChannelKfSendMsgParam.OrderShareMessage order = new WxChannelKfSendMsgParam.OrderShareMessage();
    order.setOrderId("order-1");
    param.setOrderShare(order);

    String json = JsonUtils.encode(param);
    assertTrue(json.contains("\"order_share\":{\"order_id\":\"order-1\"}"));
    assertFalse(json.contains("orderShare"));
    assertFalse(json.contains("orderId"));
    WxChannelKfSendMsgParam decoded = JsonUtils.decode(json, WxChannelKfSendMsgParam.class);
    assertEquals(decoded.getOrderShare().getOrderId(), "order-1");
  }

  @Test
  public void testCosUploadResponseJson() {
    WxChannelKfCosUploadResponse response = JsonUtils.decode(
      "{\"errcode\":0,\"errmsg\":\"ok\",\"cos_url\":\"https://example.test/media\"}",
      WxChannelKfCosUploadResponse.class);

    assertEquals(response.getErrCode(), 0);
    assertEquals(response.getCosUrl(), "https://example.test/media");
    assertEquals(JsonUtils.decode(JsonUtils.encode(response), WxChannelKfCosUploadResponse.class)
      .getCosUrl(), "https://example.test/media");
  }

  @Test
  public void testSendMsgResponseJson() {
    WxChannelKfSendMsgResponse response = JsonUtils.decode(
      "{\"errcode\":0,\"errmsg\":\"ok\",\"msg_id\":\"msg-1\"}",
      WxChannelKfSendMsgResponse.class);

    assertEquals(response.getErrCode(), 0);
    assertEquals(response.getMsgId(), "msg-1");
    assertEquals(JsonUtils.decode(JsonUtils.encode(response), WxChannelKfSendMsgResponse.class)
      .getMsgId(), "msg-1");
  }

  private WxChannelKfSendMsgParam createSendMsgParam(String msgType) {
    WxChannelKfSendMsgParam param = new WxChannelKfSendMsgParam();
    param.setRequestId("request-1");
    param.setOpenId("open-1");
    param.setMsgType(msgType);
    return param;
  }

  private void assertCosUrlMessageJson(WxChannelKfSendMsgParam param, String fieldName,
                                       String cosUrl) {
    String json = JsonUtils.encode(param);
    assertTrue(json.contains("\"" + fieldName + "\":{\"cos_url\":\"" + cosUrl + "\"}"));
    assertFalse(json.contains("cosUrl"));
    WxChannelKfSendMsgParam decoded = JsonUtils.decode(json, WxChannelKfSendMsgParam.class);
    if ("image".equals(fieldName)) {
      assertEquals(decoded.getImage().getCosUrl(), cosUrl);
    } else if ("video".equals(fieldName)) {
      assertEquals(decoded.getVideo().getCosUrl(), cosUrl);
    } else {
      assertEquals(decoded.getFile().getCosUrl(), cosUrl);
    }
  }
}
