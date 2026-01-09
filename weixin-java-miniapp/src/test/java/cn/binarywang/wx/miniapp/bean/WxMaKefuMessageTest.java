package cn.binarywang.wx.miniapp.bean;

import org.testng.annotations.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
public class WxMaKefuMessageTest {

  public void testTextBuilder() {
    WxMaKefuMessage reply = WxMaKefuMessage.newTextBuilder()
      .toUser("OPENID")
      .content("sfsfdsdf")
      .build();
    assertThat(reply.toJson())
      .isEqualTo("{\"touser\":\"OPENID\",\"msgtype\":\"text\",\"text\":{\"content\":\"sfsfdsdf\"}}");
  }

  public void testImageBuilder() {
    WxMaKefuMessage reply = WxMaKefuMessage.newImageBuilder()
      .toUser("OPENID")
      .        mediaId("MEDIA_ID")
      .build();
    assertThat(reply.toJson())
      .isEqualTo( "{\"touser\":\"OPENID\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"MEDIA_ID\"}}");
  }

  public void testLinkBuilder() {
    WxMaKefuMessage reply = WxMaKefuMessage.newLinkBuilder()
      .toUser("OPENID")
      .url("url")
      .description("description")
      .title("title")
      .thumbUrl("thumbUrl")
      .build();
    assertThat(reply.toJson())
      .isEqualTo( "{\"touser\":\"OPENID\",\"msgtype\":\"link\"," +
        "\"link\":{\"title\":\"title\",\"description\":\"description\",\"url\":\"url\",\"thumb_url\":\"thumbUrl\"}}");
  }

  public void testMaPageBuilder() {
    WxMaKefuMessage reply = WxMaKefuMessage.newMaPageBuilder()
      .toUser("OPENID")
      .title("title")
      .pagePath("pagePath")
      .thumbMediaId("thumbMediaId")
      .build();
    assertThat(reply.toJson())
      .isEqualTo( "{\"touser\":\"OPENID\",\"msgtype\":\"miniprogrampage\"," +
        "\"miniprogrampage\":{\"title\":\"title\",\"pagepath\":\"pagePath\",\"thumb_media_id\":\"thumbMediaId\"}}");
  }

  public void testURLEscaped() {
    WxMaKefuMessage reply = WxMaKefuMessage.newLinkBuilder()
                                           .toUser("OPENID")
                                           .url("https://mp.weixin.qq.com/s?__biz=MzI0MDA2OTY5NQ==")
                                           .description("description")
                                           .title("title")
                                           .thumbUrl("thumbUrl")
                                           .build();
    assertThat(reply.toJson())
      .isEqualTo( "{\"touser\":\"OPENID\",\"msgtype\":\"link\"," +
        "\"link\":{\"title\":\"title\",\"description\":\"description\",\"url\":\"https://mp.weixin.qq.com/s?__biz=MzI0MDA2OTY5NQ==\",\"thumb_url\":\"thumbUrl\"}}");
  }

  public void testTextBuilderWithAiMsgContext() {
    WxMaKefuMessage reply = WxMaKefuMessage.newTextBuilder()
      .toUser("OPENID")
      .content("回复内容")
      .aiMsgContextMsgId("MSG_ID_123")
      .build();
    assertThat(reply.toJson())
      .isEqualTo("{\"touser\":\"OPENID\",\"msgtype\":\"text\",\"text\":{\"content\":\"回复内容\"},\"aimsgcontext\":{\"msgid\":\"MSG_ID_123\"}}");
  }

}
