package me.chanjar.weixin.cp.bean.oa.applydata;

import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

public class ContentValueTipsTest {

  @Test
  public void testTipsWithLinksManualCreation() {
    System.out.println("Testing ContentValue.NewTips structure with Link (manual creation):");

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
    validateTipsStructure(tips, json);
  }

  @Test
  public void testTipsWithConvenienceMethods() {
    System.out.println("Testing ContentValue.NewTips with convenience methods:");

    // Test 1: Simple plain text
    ContentValue.NewTips textOnly = ContentValue.NewTips.ofText("zh_CN", "This is a simple text tip.");
    String textJson = WxCpGsonBuilder.create().toJson(textOnly);
    System.out.println("Text-only JSON: " + textJson);
    validateTipsStructure(textOnly, textJson);

    // Test 2: Single link
    ContentValue.NewTips linkOnly = ContentValue.NewTips.ofLink("zh_CN", "Visit WeChat Work", "https://work.weixin.qq.com");
    String linkJson = WxCpGsonBuilder.create().toJson(linkOnly);
    System.out.println("Link-only JSON: " + linkJson);
    validateTipsStructure(linkOnly, linkJson);

    // Test 3: Mixed content using convenience method
    ContentValue.NewTips.TipsContent.SubText.Content.Link link = 
      new ContentValue.NewTips.TipsContent.SubText.Content.Link();
    link.setTitle("click here");
    link.setUrl("https://work.weixin.qq.com");

    ContentValue.NewTips mixed = ContentValue.NewTips.of("zh_CN", 
      "For more information, ", link, " or contact support.");
    String mixedJson = WxCpGsonBuilder.create().toJson(mixed);
    System.out.println("Mixed content JSON: " + mixedJson);
    validateTipsStructure(mixed, mixedJson);

    System.out.println("All convenience method tests passed!");
  }

  private void validateTipsStructure(ContentValue.NewTips tips, String json) {
    try {
      ContentValue.NewTips parsedTips = WxCpGsonBuilder.create().fromJson(json, ContentValue.NewTips.class);
      assertNotNull(parsedTips);
      assertNotNull(parsedTips.getTipsContent());
      assertFalse(parsedTips.getTipsContent().isEmpty());

      ContentValue.NewTips.TipsContent.Text parsedText = parsedTips.getTipsContent().get(0).getText();
      assertNotNull(parsedText);
      assertNotNull(parsedText.getSubText());
      assertTrue(parsedText.getSubText().size() > 0);

      // Verify structure based on content
      for (ContentValue.NewTips.TipsContent.SubText subText : parsedText.getSubText()) {
        assertNotNull(subText.getType());
        assertNotNull(subText.getContent());
        
        if (subText.getType() == 1) {
          // Plain text
          assertNotNull(subText.getContent().getPlainText());
          assertNotNull(subText.getContent().getPlainText().getContent());
        } else if (subText.getType() == 2) {
          // Link
          assertNotNull(subText.getContent().getLink());
          assertNotNull(subText.getContent().getLink().getTitle());
          assertNotNull(subText.getContent().getLink().getUrl());
        }
      }

      System.out.println("✓ JSON parsing and validation successful");
    } catch (Exception e) {
      System.out.println("✗ Error parsing: " + e.getMessage());
      e.printStackTrace();
      fail("Failed to parse JSON: " + e.getMessage());
    }
  }
}