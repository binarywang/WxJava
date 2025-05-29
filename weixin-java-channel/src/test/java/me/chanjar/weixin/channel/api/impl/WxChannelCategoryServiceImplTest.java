package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.google.inject.Inject;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelCategoryService;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.audit.AuditApplyResponse;
import me.chanjar.weixin.channel.bean.audit.AuditResponse;
import me.chanjar.weixin.channel.bean.audit.CategoryAuditInfo;
import me.chanjar.weixin.channel.bean.audit.CategoryAuditRequest;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.category.CategoryAndQualificationList;
import me.chanjar.weixin.channel.bean.category.CategoryDetailResult;
import me.chanjar.weixin.channel.bean.category.CategoryQualification;
import me.chanjar.weixin.channel.bean.category.CategoryQualificationResponse;
import me.chanjar.weixin.channel.bean.category.PassCategoryResponse;
import me.chanjar.weixin.channel.bean.category.ShopCategory;
import me.chanjar.weixin.channel.bean.category.ShopCategoryResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import org.testng.collections.CollectionUtils;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
@Guice(modules = ApiTestModule.class)
public class WxChannelCategoryServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testListAllCategory() throws WxErrorException {
    WxChannelCategoryService categoryService = channelService.getCategoryService();
    CategoryQualificationResponse response = categoryService.listAllCategory();
    assertNotNull(response);
    assertTrue(response.isSuccess());
    //System.out.println(response);
    // 测试分类：7231 瑜伽服上衣
    for (CategoryAndQualificationList cat : response.getCatsV2()) {
      if (cat.getList() == null) {
        continue;
      }
      for (CategoryQualification qua : cat.getList()) {
        if (qua.getCategory() == null) {
          log.error("category is null");
        }
        if ("7231".equals(qua.getCategory().getId())) {
          log.info("qua: {}", JsonUtils.encode(qua));
        }
      }
    }
  }

  @Test
  public void testListAvailableCategories() throws WxErrorException {
    WxChannelCategoryService categoryService = channelService.getCategoryService();
    ShopCategoryResponse response = categoryService.listAvailableCategories("0");
    assertNotNull(response);
    assertTrue(response.isSuccess());
    List<ShopCategory> v1 = response.getCategories();
    List<ShopCategory> v2 = response.getCatListV2();
    assertTrue(CollectionUtils.hasElements(v1) || CollectionUtils.hasElements(v2));
    v1.forEach(System.out::println);
    v2.forEach(System.out::println);
  }

  @Test
  public void testGetCategoryDetail() throws WxErrorException {
    WxChannelCategoryService categoryService = channelService.getCategoryService();
    CategoryDetailResult categoryDetail = categoryService.getCategoryDetail("7231");
    assertNotNull(categoryDetail);
    assertTrue(categoryDetail.isSuccess());
    System.out.println(categoryDetail);
  }

  @Test
  public void testAddCategory() throws WxErrorException {
//    WxChannelCategoryService categoryService = channelService.getCategoryService();
//    List<String> certificates = new ArrayList<>();
//    certificates.add(
//      "hWNith8iZSrxfN7W2tXOoWSXYWi1qADRJxwImvQl2DC6wqqJrl4g8i/UEZfd59yiiEKAnqy0WETFrOcGZFcJDfpH2ccmNZddzesR1/OpAC7bbfmEiDFBK2QL7MBjhR2m");
//    AuditApplyResponse response = categoryService.addCategory("1001", "1002", "1005", certificates);
//    assertNotNull(response);
//    assertTrue(response.isSuccess());
//    System.out.println(response);
    String json = "{\n"
      + "    \"category_info\": {\n"
      + "        \"cats_v2\":[\n"
      + "          {\n"
      + "            \"cat_id\": 6033\n"
      + "          },\n"
      + "          {\n"
      + "            \"cat_id\": 6057\n"
      + "          },\n"
      + "          {\n"
      + "            \"cat_id\": 6091\n"
      + "          },\n"
      + "          {\n"
      + "            \"cat_id\": 6093\n"
      + "          }\n"
      + "        ],\n"
      + "        \"certificate\": [\n"
      + "            \"THE_FILE_ID_1\",\n"
      + "            \"THE_FILE_ID_2\"\n"
      + "        ],\n"
      + "        \"brand_list\" : [\n"
      + "            { \"brand_id\": 1001 },\n"
      + "            { \"brand_id\": 1002 }\n"
      + "        ]\n"
      + "    }\n"
      + "}";
    CategoryAuditRequest param = JsonUtils.decode(json, CategoryAuditRequest.class);
    CategoryAuditInfo info = null;
    if (info != null) {
      info = param.getCategoryInfo();
    }
    WxChannelCategoryService categoryService = channelService.getCategoryService();
    AuditApplyResponse response = categoryService.addCategory(info);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(response);
  }

  @Test
  public void testCancelCategoryAudit() throws WxErrorException {
    WxChannelCategoryService categoryService = channelService.getCategoryService();
    String auditId = "4403159413";
    WxChannelBaseResponse response = categoryService.cancelCategoryAudit(auditId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(response);
  }

  @Test
  public void testGetAudit() throws WxErrorException {
    WxChannelCategoryService categoryService = channelService.getCategoryService();
    String auditId = "4403159413";
    AuditResponse response = categoryService.getAudit(auditId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(response);
  }

  @Test
  public void testListPassCategory() throws WxErrorException {
    WxChannelCategoryService categoryService = channelService.getCategoryService();
    PassCategoryResponse response = categoryService.listPassCategory();
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(response);
  }
}
