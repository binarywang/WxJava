package me.chanjar.weixin.cp.bean.oa.applydata;

import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

public class ContentValueTipsTest {

  @Test
  public void testTipsWithLinks() {
    System.out.println("Testing ContentValue.NewTips structure with Link:");

    // Create a Tips structure with both plain text and link
    ContentValue.NewTips tips = new ContentValue.NewTips();
    ContentValue.NewTips.TipsContent tipsContent = new ContentValue.NewTips.TipsContent();
    ContentValue.NewTips.TipsContent.Text text = new ContentValue.NewTips.TipsContent.Text();

    // Create plain text subtext
    ContentValue.NewTips.TipsContent.SubText plainSubText = new ContentValue.NewTips.TipsContent.SubText();
    ContentValue.NewTips.TipsContent.SubText.Content plainContent = new ContentValue.NewTips.TipsContent.SubText.Content();
    ContentValue.NewTips.TipsContent.SubText.Content.PlainText plainTextContent =
      new ContentValue.NewTips.TipsContent.SubText.Content.PlainText();
    plainTextContent.setContent("This is plain text. For more info, ");
    plainContent.setPlainText(plainTextContent);
    plainSubText.setType(1); // Type 1 for plain text
    plainSubText.setContent(plainContent);

    // Create link subtext
    ContentValue.NewTips.TipsContent.SubText linkSubText = new ContentValue.NewTips.TipsContent.SubText();
    ContentValue.NewTips.TipsContent.SubText.Content linkContent = new ContentValue.NewTips.TipsContent.SubText.Content();
    ContentValue.NewTips.TipsContent.SubText.Content.Link link =
      new ContentValue.NewTips.TipsContent.SubText.Content.Link();
    link.setTitle("click here");
    link.setUrl("https://work.weixin.qq.com");
    linkContent.setLink(link);
    linkSubText.setType(2); // Type 2 for link
    linkSubText.setContent(linkContent);

    text.setSubText(Arrays.asList(plainSubText, linkSubText));
    tipsContent.setText(text);
    tipsContent.setLang("zh_CN");

    tips.setTipsContent(Arrays.asList(tipsContent));

    // Convert to JSON
    String json = WxCpGsonBuilder.create().toJson(tips);
    System.out.println("Generated JSON:");
    System.out.println(json);

    // Try to parse it back
    try {
      ContentValue.NewTips parsedTips = WxCpGsonBuilder.create().fromJson(json, ContentValue.NewTips.class);
      assertNotNull(parsedTips);
      assertNotNull(parsedTips.getTipsContent());
      assertFalse(parsedTips.getTipsContent().isEmpty());

      ContentValue.NewTips.TipsContent.Text parsedText = parsedTips.getTipsContent().get(0).getText();
      assertNotNull(parsedText);
      assertNotNull(parsedText.getSubText());
      assertEquals(parsedText.getSubText().size(), 2);

      // Verify plain text
      assertEquals(parsedText.getSubText().get(0).getType().intValue(), 1);
      assertEquals(parsedText.getSubText().get(0).getContent().getPlainText().getContent(), "This is plain text. For more info, ");

      // Verify link
      assertEquals(parsedText.getSubText().get(1).getType().intValue(), 2);
      assertEquals(parsedText.getSubText().get(1).getContent().getLink().getTitle(), "click here");
      assertEquals(parsedText.getSubText().get(1).getContent().getLink().getUrl(), "https://work.weixin.qq.com");

      System.out.println("Test passed successfully!");
    } catch (Exception e) {
      System.out.println("Error parsing: " + e.getMessage());
      e.printStackTrace();
      fail("Failed to parse JSON: " + e.getMessage());
    }
  }
}