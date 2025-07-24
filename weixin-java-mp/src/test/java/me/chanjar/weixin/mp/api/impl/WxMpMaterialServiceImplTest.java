package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.api.test.TestConstants;
import me.chanjar.weixin.mp.bean.material.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.testng.Assert.*;

/**
 * 素材管理相关接口的测试
 *
 * @author chanjarster
 * @author codepiano
 * @author Binary Wang
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpMaterialServiceImplTest {
  @Inject
  protected WxMpService wxService;

  private Map<String, Map<String, Object>> mediaIds = new LinkedHashMap<>();
  // 缩略图的id，测试上传图文使用
  private String thumbMediaId = "";
  // 单图文消息media_id
  private String singleNewsMediaId = "";
  // 多图文消息media_id
  private String multiNewsMediaId = "";
  // 先查询保存测试开始前永久素材数据
  private WxMpMaterialCountResult wxMaterialCountResultBeforeTest;
  // 以下为media接口的测试
  private List<String> mediaIdsToDownload = new ArrayList<>();
  // 以下为高清语音接口的测试
  private List<String> voiceMediaIdsToDownload = new ArrayList<>();

  @DataProvider
  public Object[][] mediaFiles() {
    return new Object[][]{
      new Object[]{WxConsts.MediaFileType.IMAGE, TestConstants.FILE_JPG, "mm.jpeg"},
      new Object[]{WxConsts.MediaFileType.VOICE, TestConstants.FILE_MP3, "mm.mp3"},
      new Object[]{WxConsts.MediaFileType.VIDEO, TestConstants.FILE_MP4, "mm.mp4"},
      new Object[]{WxConsts.MediaFileType.THUMB, TestConstants.FILE_JPG, "mm.jpeg"}
    };
  }

  @Test(dataProvider = "mediaFiles")
  public void testUploadMaterial(String mediaType, String fileType, String fileName) throws WxErrorException, IOException {
    if (this.wxMaterialCountResultBeforeTest == null) {
      this.wxMaterialCountResultBeforeTest = this.wxService.getMaterialService()
        .materialCount();
    }

    try (InputStream inputStream = ClassLoader
      .getSystemResourceAsStream(fileName)) {
      File tempFile = FileUtils.createTmpFile(inputStream,
        UUID.randomUUID().toString(), fileType);
      WxMpMaterial wxMaterial = new WxMpMaterial();
      wxMaterial.setFile(tempFile);
      wxMaterial.setName(fileName);
      if (WxConsts.MediaFileType.VIDEO.equals(mediaType)) {
        wxMaterial.setVideoTitle("title");
        wxMaterial.setVideoIntroduction("test video description");
      }

      WxMpMaterialUploadResult res = this.wxService.getMaterialService()
        .materialFileUpload(mediaType, wxMaterial);
      assertNotNull(res.getMediaId());

      if (WxConsts.MediaFileType.IMAGE.equals(mediaType)
        || WxConsts.MediaFileType.THUMB.equals(mediaType)) {
        assertNotNull(res.getUrl());
      }

      if (WxConsts.MediaFileType.THUMB.equals(mediaType)) {
        this.thumbMediaId = res.getMediaId();
      }

      Map<String, Object> materialInfo = new HashMap<>();
      materialInfo.put("media_id", res.getMediaId());
      materialInfo.put("length", tempFile.length());
      materialInfo.put("filename", tempFile.getName());
      this.mediaIds.put(res.getMediaId(), materialInfo);

      System.out.println(res);
    }
  }

  @Test(dependsOnMethods = {"testUploadMaterial"})
  public void testAddNews() throws WxErrorException {
    // 单图文消息
    WxMpMaterialNews wxMpMaterialNewsSingle = new WxMpMaterialNews();
    WxMpNewsArticle article = new WxMpNewsArticle();
    article.setAuthor("author");
    article.setThumbMediaId(this.thumbMediaId);
    article.setTitle("single title");
    article.setContent("single content");
    article.setContentSourceUrl("content url");
    article.setShowCoverPic(true);
    article.setDigest("single news");
    wxMpMaterialNewsSingle.addArticle(article);

    // 多图文消息
    WxMpMaterialNews wxMpMaterialNewsMultiple = new WxMpMaterialNews();
    WxMpNewsArticle article1 = new WxMpNewsArticle();
    article1.setAuthor("author1");
    article1.setThumbMediaId(this.thumbMediaId);
    article1.setTitle("multi title1");
    article1.setContent("content 1");
    article1.setContentSourceUrl("content url");
    article1.setShowCoverPic(true);
    article1.setDigest("");

    WxMpNewsArticle article2 = new WxMpNewsArticle();
    article2.setAuthor("author2");
    article2.setThumbMediaId(this.thumbMediaId);
    article2.setTitle("multi title2");
    article2.setContent("content 2");
    article2.setContentSourceUrl("content url");
    article2.setShowCoverPic(true);
    article2.setDigest("");

    wxMpMaterialNewsMultiple.addArticle(article1);
    wxMpMaterialNewsMultiple.addArticle(article2);
  }

  @Test(dependsOnMethods = {"testAddNews"})
  public void testMaterialCount() throws WxErrorException {
    WxMpMaterialCountResult wxMaterialCountResult = this.wxService.getMaterialService().materialCount();
    // 测试上传过程中添加了一个音频，一个视频，两个图片，两个图文消息
    assertEquals(
      this.wxMaterialCountResultBeforeTest.getVoiceCount() + 1,
      wxMaterialCountResult.getVoiceCount());
    assertEquals(
      this.wxMaterialCountResultBeforeTest.getVideoCount() + 1,
      wxMaterialCountResult.getVideoCount());
    assertEquals(
      this.wxMaterialCountResultBeforeTest.getImageCount() + 2,
      wxMaterialCountResult.getImageCount());
    assertEquals(this.wxMaterialCountResultBeforeTest.getNewsCount() + 2,
      wxMaterialCountResult.getNewsCount());
  }

  @Test(dependsOnMethods = {"testMaterialCount"}, dataProvider = "downloadMaterial")
  public void testDownloadMaterial(String mediaId) throws WxErrorException, IOException {
    Map<String, Object> materialInfo = this.mediaIds.get(mediaId);
    assertNotNull(materialInfo);
    String filename = materialInfo.get("filename").toString();
    if (filename.endsWith(".mp3") || filename.endsWith(".jpeg")) {
      try (InputStream inputStream = this.wxService.getMaterialService()
        .materialImageOrVoiceDownload(mediaId)) {
        assertNotNull(inputStream);
      }
    }
    if (filename.endsWith("mp4")) {
      WxMpMaterialVideoInfoResult wxMaterialVideoInfoResult = this.wxService.getMaterialService().materialVideoInfo(mediaId);
      assertNotNull(wxMaterialVideoInfoResult);
      assertNotNull(wxMaterialVideoInfoResult.getDownUrl());
    }
  }

  @Test(dependsOnMethods = {"testAddNews", "testUploadMaterial"})
  public void testGetNewsInfo() throws WxErrorException {
    WxMpMaterialNews wxMpMaterialNewsSingle = this.wxService
      .getMaterialService().materialNewsInfo(this.singleNewsMediaId);
    WxMpMaterialNews wxMpMaterialNewsMultiple = this.wxService
      .getMaterialService().materialNewsInfo(this.multiNewsMediaId);
    assertNotNull(wxMpMaterialNewsSingle);
    assertNotNull(wxMpMaterialNewsMultiple);

    System.out.println(wxMpMaterialNewsSingle);
    System.out.println(wxMpMaterialNewsMultiple);
  }

  @Test(dependsOnMethods = {"testGetNewsInfo"})
  public void testUpdateNewsInfo() throws WxErrorException {
    WxMpMaterialNews wxMpMaterialNewsSingle = this.wxService
      .getMaterialService().materialNewsInfo(this.singleNewsMediaId);
    assertNotNull(wxMpMaterialNewsSingle);
    WxMpMaterialArticleUpdate wxMpMaterialArticleUpdateSingle = new WxMpMaterialArticleUpdate();
    WxMpNewsArticle articleSingle = wxMpMaterialNewsSingle.getArticles().get(0);
    articleSingle.setContent("content single update");
    wxMpMaterialArticleUpdateSingle.setMediaId(this.singleNewsMediaId);
    wxMpMaterialArticleUpdateSingle.setArticles(articleSingle);
    wxMpMaterialArticleUpdateSingle.setIndex(0);
    wxMpMaterialNewsSingle = this.wxService.getMaterialService()
      .materialNewsInfo(this.singleNewsMediaId);
    assertNotNull(wxMpMaterialNewsSingle);
    assertEquals("content single update",
      wxMpMaterialNewsSingle.getArticles().get(0).getContent());

    WxMpMaterialNews wxMpMaterialNewsMultiple = this.wxService
      .getMaterialService().materialNewsInfo(this.multiNewsMediaId);
    assertNotNull(wxMpMaterialNewsMultiple);
    WxMpMaterialArticleUpdate wxMpMaterialArticleUpdateMulti = new WxMpMaterialArticleUpdate();
    WxMpNewsArticle articleMulti = wxMpMaterialNewsMultiple.getArticles().get(1);
    articleMulti.setContent("content 2 update");
    wxMpMaterialArticleUpdateMulti.setMediaId(this.multiNewsMediaId);
    wxMpMaterialArticleUpdateMulti.setArticles(articleMulti);
    wxMpMaterialArticleUpdateMulti.setIndex(1);
    wxMpMaterialNewsMultiple = this.wxService.getMaterialService()
      .materialNewsInfo(this.multiNewsMediaId);
    assertNotNull(wxMpMaterialNewsMultiple);
    assertEquals("content 2 update",
      wxMpMaterialNewsMultiple.getArticles().get(1).getContent());
  }

  @Test(dependsOnMethods = {"testUpdateNewsInfo"})
  public void testMaterialNewsList() throws WxErrorException {
    WxMpMaterialNewsBatchGetResult wxMpMaterialNewsBatchGetResult = this.wxService.getMaterialService().materialNewsBatchGet(0, 20);
    assertNotNull(wxMpMaterialNewsBatchGetResult);
  }

  @Test//(dependsOnMethods = {"testMaterialNewsList"})
  public void testMaterialFileList() throws WxErrorException {
    WxMpMaterialFileBatchGetResult wxMpMaterialVoiceBatchGetResult = this.wxService.getMaterialService().materialFileBatchGet(WxConsts.MaterialType.VOICE, 0, 20);
    WxMpMaterialFileBatchGetResult wxMpMaterialVideoBatchGetResult = this.wxService.getMaterialService().materialFileBatchGet(WxConsts.MaterialType.VIDEO, 0, 20);
    WxMpMaterialFileBatchGetResult wxMpMaterialImageBatchGetResult = this.wxService.getMaterialService().materialFileBatchGet(WxConsts.MaterialType.IMAGE, 0, 20);
    assertNotNull(wxMpMaterialVoiceBatchGetResult);
    assertNotNull(wxMpMaterialVideoBatchGetResult);
    assertNotNull(wxMpMaterialImageBatchGetResult);
  }

  @Test(dependsOnMethods = {"testMaterialFileList"}, dataProvider = "allTestMaterial")
  public void testDeleteMaterial(String mediaId) throws WxErrorException {
    this.delete(mediaId);
  }

  @Test
  public void testDeleteMaterialDirectly() throws WxErrorException {
    this.delete("abc");
  }

  public void delete(String mediaId) throws WxErrorException {
    boolean result = this.wxService.getMaterialService().materialDelete(mediaId);
    assertTrue(result);
  }

  @DataProvider
  public Object[][] downloadMaterial() {
    Object[][] params = new Object[this.mediaIds.size()][];
    int index = 0;
    for (String mediaId : this.mediaIds.keySet()) {
      params[index] = new Object[]{mediaId};
      index++;
    }
    return params;
  }

  @DataProvider
  public Iterator<Object[]> allTestMaterial() {
    List<Object[]> params = new ArrayList<>();
    for (String mediaId : this.mediaIds.keySet()) {
      params.add(new Object[]{mediaId});
    }
    params.add(new Object[]{this.singleNewsMediaId});
    params.add(new Object[]{this.multiNewsMediaId});
    return params.iterator();
  }

  @Test(dataProvider = "mediaFiles")
  public void testUploadMedia(String mediaType, String fileType, String fileName) throws WxErrorException, IOException {
    try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName)) {
      WxMediaUploadResult res = this.wxService.getMaterialService().mediaUpload(mediaType, fileType, inputStream);
      assertNotNull(res.getType());
      assertNotNull(res.getCreatedAt());
      assertTrue(res.getMediaId() != null || res.getThumbMediaId() != null);

      if (res.getMediaId() != null && !mediaType.equals(WxConsts.MediaFileType.VIDEO)) {
        //video 不支持下载，所以不加入
        this.mediaIdsToDownload.add(res.getMediaId());

        // 音频media, 用于测试下载高清语音接口
        if (mediaType.equals(WxConsts.MediaFileType.VOICE)) {
          this.voiceMediaIdsToDownload.add(res.getMediaId());
        }
      }

      if (res.getThumbMediaId() != null) {
        this.mediaIdsToDownload.add(res.getThumbMediaId());
      }

      System.out.println(res);
    }
  }

  @DataProvider
  public Object[][] downloadMedia() {
    Object[][] params = new Object[this.mediaIdsToDownload.size()][];
    for (int i = 0; i < this.mediaIdsToDownload.size(); i++) {
      params[i] = new Object[]{this.mediaIdsToDownload.get(i)};
    }
    return params;
  }

  @DataProvider
  public Object[][] downloadJssdkMedia() {
    Object[][] params = new Object[this.voiceMediaIdsToDownload.size()][];
    for (int i = 0; i < this.voiceMediaIdsToDownload.size(); i++) {
      params[i] = new Object[]{this.voiceMediaIdsToDownload.get(i)};
    }
    return params;
  }

  @Test(dependsOnMethods = {"testUploadMedia"}, dataProvider = "downloadMedia")
  public void testDownloadMedia(String mediaId) throws WxErrorException {
    File file = this.wxService.getMaterialService().mediaDownload(mediaId);
    assertNotNull(file);
    System.out.println(file.getAbsolutePath());
  }

  @Test(dependsOnMethods = {"testUploadMedia"}, dataProvider = "downloadJssdkMedia")
  public void testDownloadJssdkMedia(String mediaId) throws WxErrorException {
    File file = this.wxService.getMaterialService().jssdkMediaDownload(mediaId);
    assertNotNull(file);
    System.out.println(file.getAbsolutePath());
  }
}
