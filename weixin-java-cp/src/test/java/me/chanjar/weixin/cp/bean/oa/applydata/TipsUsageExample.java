package me.chanjar.weixin.cp.bean.oa.applydata;

import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * Usage examples for ContentValue.NewTips with clickable link support.
 * 
 * This example demonstrates how to create Tips controls that can render URLs as clickable links,
 * addressing the issue where "Tips控件无法将url渲染为可点击的链接".
 *
 * @author WxJava Community
 */
public class TipsUsageExample {
  
  public static void main(String[] args) {
    demonstrateBasicUsage();
    demonstrateAdvancedUsage();
  }
  
  /**
   * Basic usage examples for creating Tips with clickable links.
   */
  public static void demonstrateBasicUsage() {
    System.out.println("=== Basic Tips Usage Examples ===\n");
    
    // Example 1: Simple plain text tip
    ContentValue.NewTips textTip = ContentValue.NewTips.ofText("zh_CN", 
      "这是一个简单的文本提示。");
    System.out.println("1. Plain text tip JSON:");
    System.out.println(WxCpGsonBuilder.create().toJson(textTip));
    System.out.println();
    
    // Example 2: Simple clickable link tip
    ContentValue.NewTips linkTip = ContentValue.NewTips.ofLink("zh_CN", 
      "访问企业微信官网", "https://work.weixin.qq.com");
    System.out.println("2. Single link tip JSON:");
    System.out.println(WxCpGsonBuilder.create().toJson(linkTip));
    System.out.println();
    
    // Example 3: Mixed content - text with clickable link
    ContentValue.NewTips.TipsContent.SubText.Content.Link helpLink = 
      new ContentValue.NewTips.TipsContent.SubText.Content.Link();
    helpLink.setTitle("点击查看详情");
    helpLink.setUrl("https://work.weixin.qq.com/help");
    
    ContentValue.NewTips mixedTip = ContentValue.NewTips.of("zh_CN",
      "如需了解更多信息，请", helpLink, "。");
    System.out.println("3. Mixed content tip JSON:");
    System.out.println(WxCpGsonBuilder.create().toJson(mixedTip));
    System.out.println();
  }
  
  /**
   * Advanced usage examples showing complex Tips with multiple links and text.
   */
  public static void demonstrateAdvancedUsage() {
    System.out.println("=== Advanced Tips Usage Examples ===\n");
    
    // Example 4: Complex tip with multiple links
    ContentValue.NewTips.TipsContent.SubText.Content.Link docsLink = 
      new ContentValue.NewTips.TipsContent.SubText.Content.Link();
    docsLink.setTitle("开发文档");
    docsLink.setUrl("https://developer.work.weixin.qq.com");
    
    ContentValue.NewTips.TipsContent.SubText.Content.Link supportLink = 
      new ContentValue.NewTips.TipsContent.SubText.Content.Link();
    supportLink.setTitle("技术支持");
    supportLink.setUrl("https://work.weixin.qq.com/contact");
    
    ContentValue.NewTips complexTip = ContentValue.NewTips.of("zh_CN",
      "审批流程说明：\n1. 提交申请后系统将自动处理\n2. 如有疑问请查看", 
      docsLink, 
      "或联系", 
      supportLink);
    System.out.println("4. Complex tip with multiple links JSON:");
    System.out.println(WxCpGsonBuilder.create().toJson(complexTip));
    System.out.println();
    
    // Demonstrate that the structure supports proper type differentiation
    System.out.println("=== Type Verification ===");
    ContentValue.NewTips parsed = WxCpGsonBuilder.create().fromJson(
      WxCpGsonBuilder.create().toJson(complexTip), ContentValue.NewTips.class);
    
    parsed.getTipsContent().get(0).getText().getSubText().forEach(subText -> {
      if (subText.getType() == 1) {
        System.out.println("Plain text: \"" + subText.getContent().getPlainText().getContent() + "\"");
      } else if (subText.getType() == 2) {
        System.out.println("Link: \"" + subText.getContent().getLink().getTitle() + 
          "\" -> " + subText.getContent().getLink().getUrl());
      }
    });
  }
}