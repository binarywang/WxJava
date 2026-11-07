package me.chanjar.weixin.mp.bean.message;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class WxMpXmlItemTypesTest {
  @Test
  public void testItemsUseTheirContainingListType() {
    WxMpXmlMessage message = WxMpXmlMessage.fromXml("<xml>"
      + "<SendPicsInfo><Count>1</Count><PicList><item><PicMd5Sum>picture-md5</PicMd5Sum>"
      + "</item></PicList></SendPicsInfo>"
      + "<ArticleUrlResult><Count>1</Count><ResultList><item><ArticleIdx>1</ArticleIdx>"
      + "<ArticleUrl>https://example.com/article</ArticleUrl></item></ResultList></ArticleUrlResult>"
      + "</xml>");
    assertEquals(message.getSendPicsInfo().getPicList().get(0).getPicMd5Sum(), "picture-md5");
    assertEquals(message.getArticleUrlResult().getResultList().get(0).getArticleIdx(), "1");
    assertEquals(message.getArticleUrlResult().getResultList().get(0).getArticleUrl(),
      "https://example.com/article");
  }
}
