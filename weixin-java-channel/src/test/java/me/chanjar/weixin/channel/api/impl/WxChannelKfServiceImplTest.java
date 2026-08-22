package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertSame;

import me.chanjar.weixin.channel.bean.kf.WxChannelKfSendMsgParam;
import me.chanjar.weixin.channel.bean.kf.WxChannelKfSendMsgResponse;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.common.bean.CommonUploadParam;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Test;

/** 商家客服服务离线测试。 */
public class WxChannelKfServiceImplTest {

  @Test
  public void shouldUploadMediaWithDocumentedUrlAndMultipartFields() throws WxErrorException {
    RecordingChannelService channelService = new RecordingChannelService();
    channelService.uploadResult = "{\"errcode\":0,\"errmsg\":\"ok\",\"cos_url\":\"https://cos.example.com/image.png\"}";

    String cosUrl = new WxChannelKfServiceImpl(channelService)
      .uploadMedia("open-id", "image", "image.png", new byte[]{1, 2, 3});

    assertEquals(channelService.uploadUrl, "https://api.weixin.qq.com/channels/ec/commkf/cosupload");
    assertNotNull(channelService.uploadParam);
    assertEquals(channelService.uploadParam.getName(), "file");
    assertEquals(channelService.uploadParam.getData().getFileName(), "image.png");
    assertEquals(channelService.uploadParam.getData().readAllBytes(), new byte[]{1, 2, 3});
    assertEquals(channelService.uploadParam.getFormFields().get("open_id"), "open-id");
    assertEquals(channelService.uploadParam.getFormFields().get("msg_type"), "image");
    assertEquals(cosUrl, "https://cos.example.com/image.png");
  }

  @Test
  public void shouldSendJsonMessageAndDecodeResponse() throws WxErrorException {
    RecordingChannelService channelService = new RecordingChannelService();
    channelService.postResult = "{\"errcode\":0,\"errmsg\":\"ok\",\"msg_id\":\"message-id\"}";
    WxChannelKfSendMsgParam param = new WxChannelKfSendMsgParam();
    param.setRequestId("request-id");
    param.setOpenId("open-id");
    param.setMsgType("text");
    WxChannelKfSendMsgParam.Text text = new WxChannelKfSendMsgParam.Text();
    text.setContent("hello");
    param.setText(text);

    WxChannelKfSendMsgResponse response = new WxChannelKfServiceImpl(channelService).sendMessage(param);

    assertEquals(channelService.postUrl, "https://api.weixin.qq.com/channels/ec/commkf/sendmsg");
    assertEquals(channelService.postJson,
      "{\"request_id\":\"request-id\",\"open_id\":\"open-id\",\"msg_type\":\"text\",\"text\":{\"content\":\"hello\"}}");
    assertEquals(response.getMsgId(), "message-id");
    assertEquals(response.getErrCode(), 0);
  }

  @Test
  public void shouldCacheKfServiceEntryPoint() {
    WxChannelServiceImpl channelService = new WxChannelServiceImpl();

    assertSame(channelService.getKfService(), channelService.getKfService());
  }

  private static class RecordingChannelService extends WxChannelServiceImpl {

    private String uploadResult;
    private String postResult;
    private String uploadUrl;
    private CommonUploadParam uploadParam;
    private String postUrl;
    private String postJson;

    @Override
    public String upload(String url, CommonUploadParam param) {
      this.uploadUrl = url;
      this.uploadParam = param;
      return uploadResult;
    }

    @Override
    public String post(String url, Object obj) {
      this.postUrl = url;
      this.postJson = JsonUtils.encode(obj);
      return postResult;
    }
  }
}
