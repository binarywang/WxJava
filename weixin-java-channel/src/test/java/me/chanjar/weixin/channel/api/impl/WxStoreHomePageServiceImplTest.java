package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxStoreHomePageService;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.home.background.BackgroundApplyResponse;
import me.chanjar.weixin.channel.bean.home.background.BackgroundGetResponse;
import me.chanjar.weixin.channel.bean.home.banner.BannerApplyParam;
import me.chanjar.weixin.channel.bean.home.banner.BannerApplyResponse;
import me.chanjar.weixin.channel.bean.home.banner.BannerGetResponse;
import me.chanjar.weixin.channel.bean.home.banner.BannerInfo;
import me.chanjar.weixin.channel.bean.home.tree.TreeProductEditInfo;
import me.chanjar.weixin.channel.bean.home.tree.TreeProductEditParam;
import me.chanjar.weixin.channel.bean.home.tree.TreeProductListInfo;
import me.chanjar.weixin.channel.bean.home.tree.TreeProductListResponse;
import me.chanjar.weixin.channel.bean.home.tree.TreeShowGetResponse;
import me.chanjar.weixin.channel.bean.home.tree.TreeShowInfo;
import me.chanjar.weixin.channel.bean.home.tree.TreeShowParam;
import me.chanjar.weixin.channel.bean.home.tree.TreeShowSetResponse;
import me.chanjar.weixin.channel.bean.home.window.WindowProductSettingResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxStoreHomePageServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testAddTreeProduct() throws WxErrorException {
    WxStoreHomePageService service = channelService.getHomePageService();
    // https://developers.weixin.qq.com/doc/store/API/homepage/classification/addclassificationproduct.html
    String json = "{\n"
      + "  \"req\": {\n"
      + "    \"level_1_id\": 10000046,\n"
      + "    \"level_2_id\": 10000048,\n"
      + "    \"product_ids\": [\n"
      + "      10000076089602\n"
      + "    ]\n"
      + "  }\n"
      + "}";
    TreeProductEditParam param = JsonUtils.decode(json, TreeProductEditParam.class);
    TreeProductEditInfo info = null;
    if (param != null) {
      info = param.getReq();
    }
    WxChannelBaseResponse response = service.addTreeProduct(info);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    assertNotNull(info);
    System.out.println(JsonUtils.encode(response));
  }

  @Test
  public void testDelTreeProduct() throws WxErrorException {
    WxStoreHomePageService service = channelService.getHomePageService();
    String json = "{\n"
      + "  \"req\": {\n"
      + "    \"level_1_id\": 1,\n"
      + "    \"level_2_id\": 0,\n"
      + "    \"product_ids\": [\n"
      + "      123\n"
      + "    ]\n"
      + "  }\n"
      + "}";
    TreeProductEditParam param = JsonUtils.decode(json, TreeProductEditParam.class);
    TreeProductEditInfo info = null;
    if (param != null) {
      info = param.getReq();
    }
    WxChannelBaseResponse response = service.delTreeProduct(info);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    assertNotNull(info);
    System.out.println(JsonUtils.encode(response));
  }

  @Test
  public void testGetTreeProductList() throws WxErrorException {
    WxStoreHomePageService service = channelService.getHomePageService();
    String pageContext = "";
    TreeProductListInfo info = new TreeProductListInfo();
    info.setLevel1Id(1);
    info.setLevel2Id(2);
    info.setPageSize(10);
    info.setPageContext(pageContext);
    TreeProductListResponse response = service.getTreeProductList(info);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    assertNotNull(info);
    System.out.println(JsonUtils.encode(response));
  }

  @Test
  public void testSetShowTree() throws WxErrorException {
    WxStoreHomePageService service = channelService.getHomePageService();
    // https://developers.weixin.qq.com/doc/store/API/homepage/classification/setclassificationtree.html
    String json = "{\n"
      + "  \"req\": {\n"
      + "    \"version\": 121,\n"
      + "    \"classification_id_deleted\": [\n"
      + "      \"1.2\"\n"
      + "    ],\n"
      + "    \"tree\": {\n"
      + "      \"level_1\": [\n"
      + "        {\n"
      + "          \"id\": 4,\n"
      + "          \"name\": \"测试7\",\n"
      + "          \"level_2\": [\n"
      + "            {\n"
      + "              \"id\": 5,\n"
      + "              \"name\": \"1\",\n"
      + "              \"is_displayed\": 1\n"
      + "            }\n"
      + "          ],\n"
      + "          \"is_displayed\": 1\n"
      + "        },\n"
      + "        {\n"
      + "          \"id\": 6,\n"
      + "          \"name\": \"测试8\",\n"
      + "          \"level_2\": [\n"
      + "            {\n"
      + "              \"id\": 7,\n"
      + "              \"name\": \"1\",\n"
      + "              \"is_displayed\": 1\n"
      + "            },\n"
      + "            {\n"
      + "              \"id\": 8,\n"
      + "              \"name\": \"2\",\n"
      + "              \"is_displayed\": 1\n"
      + "            }\n"
      + "          ],\n"
      + "          \"is_displayed\": 1\n"
      + "        }\n"
      + "      ]\n"
      + "    }\n"
      + "  }\n"
      + "}";
    TreeShowParam param = JsonUtils.decode(json, TreeShowParam.class);
    TreeShowInfo info = null;
    if (param != null) {
      info = param.getReq();
    }
    TreeShowSetResponse response = service.setShowTree(info);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    assertNotNull(info);
    System.out.println(JsonUtils.encode(response));
  }

  @Test
  public void testGetShowTree() throws WxErrorException {
    WxStoreHomePageService service = channelService.getHomePageService();
    TreeShowGetResponse response = service.getShowTree();
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(JsonUtils.encode(response));
  }

  @Test
  public void testListWindowProduct() throws WxErrorException {
    WxStoreHomePageService service = channelService.getHomePageService();
    Integer pageSize = 1;
    String nextKey = "";
    WindowProductSettingResponse response = service.listWindowProduct(pageSize, nextKey);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(JsonUtils.encode(response));
  }

  @Test
  public void testReorderWindowProduct() throws WxErrorException {
    WxStoreHomePageService service = channelService.getHomePageService();
    String productId = "";
    Integer indexNum = 100;
    WxChannelBaseResponse response = service.reorderWindowProduct(productId, indexNum);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(JsonUtils.encode(response));
  }

  @Test
  public void testHideWindowProduct() throws WxErrorException {
    WxStoreHomePageService service = channelService.getHomePageService();
    String productId = "";
    // 0:显示 1:隐藏
    Integer setHide = 0;
    WxChannelBaseResponse response = service.hideWindowProduct(productId, setHide);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(JsonUtils.encode(response));
  }

  @Test
  public void testTopWindowProduct() throws WxErrorException {
    WxStoreHomePageService service = channelService.getHomePageService();
    String productId = "";
    // 0:取消置顶 1:置顶
    Integer setTop = 0;
    WxChannelBaseResponse response = service.topWindowProduct(productId, setTop);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(JsonUtils.encode(response));
  }

  @Test
  public void testApplyBackground() throws WxErrorException {
    WxStoreHomePageService service = channelService.getHomePageService();
    String imgUrl = "https://github.githubassets.com/images/icons/emoji/octocat.png";
    BackgroundApplyResponse response = service.applyBackground(imgUrl);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(JsonUtils.encode(response));
  }

  @Test
  public void testGetBackground() throws WxErrorException {
    WxStoreHomePageService service = channelService.getHomePageService();
    BackgroundGetResponse response = service.getBackground();
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(JsonUtils.encode(response));
  }

  @Test
  public void testCancelBackground() throws WxErrorException {
    Integer applyId = 1;
    WxStoreHomePageService service = channelService.getHomePageService();
    WxChannelBaseResponse response = service.cancelBackground(applyId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(JsonUtils.encode(response));
  }

  @Test
  public void testRemoveBackground() throws WxErrorException {
    WxStoreHomePageService service = channelService.getHomePageService();
    WxChannelBaseResponse response = service.removeBackground();
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(JsonUtils.encode(response));
  }

  @Test
  public void testApplyBanner() throws WxErrorException {
    WxStoreHomePageService service = channelService.getHomePageService();
    // https://developers.weixin.qq.com/doc/store/API/homepage/banner/submit_banner_apply.html
    String json = "{\n"
      + "  \"banner\": {\n"
      + "    \"scale\": 2,\n"
      + "    \"banner\": [\n"
      + "      {\n"
      + "        \"type\": 1,\n"
      + "        \"product\": {\n"
      + "          \"product_id\": 123\n"
      + "        },\n"
      + "        \"banner\": {\n"
      + "          \"description\": \"测试商品精品展示位描述\",\n"
      + "          \"img_url\": \"https://store.mp.video.tencent-cloud.com/abc\",\n"
      + "          \"title\": \"测试商品精品展示位标题\"\n"
      + "        }\n"
      + "      },\n"
      + "      {\n"
      + "        \"type\": 3,\n"
      + "        \"finder\": {\n"
      + "          \"feed_id\": \"export/abc\",\n"
      + "          \"finder_user_name\": \"sphabc\"\n"
      + "        },\n"
      + "        \"banner\": {\n"
      + "          \"description\": \"测试视频号视频精品展示位描述\",\n"
      + "          \"img_url\": \"https://store.mp.video.tencent-cloud.com/abc\",\n"
      + "          \"title\": \"测试视频号视频精品展示位标题\"\n"
      + "        }\n"
      + "      },\n"
      + "      {\n"
      + "        \"type\": 4,\n"
      + "        \"official_account\": {\n"
      + "          \"url\": \"https://mp.weixin.qq.com/abc\"\n"
      + "        },\n"
      + "        \"banner\": {\n"
      + "          \"description\": \"测试公众号文章精品展示位描述\",\n"
      + "          \"img_url\": \"https://store.mp.video.tencent-cloud.com/abc\",\n"
      + "          \"title\": \"测试公众号文章精品展示位标题\"\n"
      + "        }\n"
      + "      }\n"
      + "    ]\n"
      + "  }\n"
      + "}";
    BannerApplyParam param = JsonUtils.decode(json, BannerApplyParam.class);
    BannerInfo info = null;
    if (param != null) {
      info = param.getBanner();
    }
    BannerApplyResponse response = service.applyBanner(info);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    assertNotNull(info);
    System.out.println(JsonUtils.encode(response));
  }

  @Test
  public void testGetBanner() throws WxErrorException {
    WxStoreHomePageService service = channelService.getHomePageService();
    BannerGetResponse response = service.getBanner();
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(JsonUtils.encode(response));
  }

  @Test
  public void testCancelBanner() throws WxErrorException {
    Integer applyId = 1;
    WxStoreHomePageService service = channelService.getHomePageService();
    WxChannelBaseResponse response = service.cancelBanner(applyId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(JsonUtils.encode(response));
  }

  @Test
  public void testRemoveBanner() throws WxErrorException {
    WxStoreHomePageService service = channelService.getHomePageService();
    WxChannelBaseResponse response = service.removeBanner();
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(JsonUtils.encode(response));
  }
}
