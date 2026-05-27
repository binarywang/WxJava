package me.chanjar.weixin.channel.constant;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import org.testng.annotations.Test;

public class WxChannelApiUrlConstantsTest {

  @Test
  public void testListRelationCategoryUrl() {
    String relationCategoryUrl = WxChannelApiUrlConstants.Category.LIST_RELATION_CATEGORY_URL;
    assertEquals(relationCategoryUrl,
      "https://api.weixin.qq.com/shop/ec/category/get_category_relation_list");
    assertFalse(relationCategoryUrl.contains("/channels/ec/shop/ec/"));
  }
}
