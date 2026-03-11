package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.TestConstants;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.media.MediaUploadByUrlReq;
import me.chanjar.weixin.cp.bean.media.MediaUploadByUrlResult;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;

/**
 * Created by Binary Wang on 2017-6-25.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxCpMediaServiceImplTest {
  @Inject
  private WxCpService wxService;

  private final List<String> mediaIds = new ArrayList<>();

  /**
   * Media data object [ ] [ ].
   *
   * @return the object [ ] [ ]
   */
  @DataProvider
  public Object[][] mediaData() {
    return new Object[][]{
      new Object[]{WxConsts.MediaFileType.IMAGE, TestConstants.FILE_JPG, "mm.jpeg"},
      //new Object[]{WxConsts.MediaFileType.VOICE, TestConstants.FILE_MP3, "mm.mp3"},
      // {"errcode":301017,"errmsg":"voice file only support amr like myvoice.amr"}
      new Object[]{WxConsts.MediaFileType.VOICE, TestConstants.FILE_AMR, "mm.amr"},
      new Object[]{WxConsts.MediaFileType.VIDEO, TestConstants.FILE_MP4, "mm.mp4"},
      new Object[]{WxConsts.MediaFileType.FILE, TestConstants.FILE_JPG, "mm.jpeg"}
    };
  }

  /**
   * Test upload media.
   *
   * @param mediaType the media type
   * @param fileType  the file type
   * @param fileName  the file name
   * @throws WxErrorException the wx error exception
   * @throws IOException      the io exception
   */
  @Test(dataProvider = "mediaData")
  public void testUploadMedia(String mediaType, String fileType, String fileName) throws WxErrorException, IOException {
    try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName)) {
      WxMediaUploadResult res = this.wxService.getMediaService().upload(mediaType, fileType, inputStream);
      assertThat(res).isNotNull();
      assertThat(res.getType()).isNotEmpty();
      assertThat(res.getCreatedAt()).isGreaterThan(0);
      assertTrue(res.getMediaId() != null || res.getThumbMediaId() != null);

      if (res.getMediaId() != null) {
        this.mediaIds.add(res.getMediaId());
      }
      if (res.getThumbMediaId() != null) {
        this.mediaIds.add(res.getThumbMediaId());
      }
    }
  }

  /**
   * Download media object [ ] [ ].
   *
   * @return the object [ ] [ ]
   */
  @DataProvider
  public Object[][] downloadMedia() {
    Object[][] params = new Object[this.mediaIds.size()][];
    for (int i = 0; i < this.mediaIds.size(); i++) {
      params[i] = new Object[]{this.mediaIds.get(i)};
    }
    return params;
  }

  /**
   * Test download.
   *
   * @param mediaId the media id
   * @throws WxErrorException the wx error exception
   */
  @Test(dependsOnMethods = {"testUploadMedia"}, dataProvider = "downloadMedia")
  public void testDownload(String mediaId) throws WxErrorException {
    File file = this.wxService.getMediaService().download(mediaId);
    assertThat(file).isNotNull();
    System.out.println(file);
  }

  /**
   * Test upload img.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testUploadImg() throws WxErrorException {
    URL url = ClassLoader.getSystemResource("mm.jpeg");
    String res = this.wxService.getMediaService().uploadImg(new File(url.getFile()));
    assertThat(res).isNotEmpty();
  }

  /**
   * Test get jssdk file.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetJssdkFile() throws WxErrorException {
    File file = this.wxService.getMediaService().getJssdkFile("....");
    assertThat(file).isNotNull();
    System.out.println(file);
  }

  /**
   * Test upload media by url.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testUploadMediaByUrl() throws WxErrorException {
    MediaUploadByUrlReq req = new MediaUploadByUrlReq();
    req.setScene(1);
    req.setType("video");
    req.setFilename("mov_bbb");
    req.setUrl("https://www.w3school.com.cn/example/html5/mov_bbb.mp4");
    req.setMd5("198918f40ecc7cab0fc4231adaf67c96");
    String jobId = this.wxService.getMediaService().uploadByUrl(req);
    System.out.println(jobId);
  }

  /**
   * Test upload media by url.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testUploadMediaByUrlResult() throws WxErrorException, InterruptedException {
    String jobId = "job1745801375_5GIKWuFF3M7hcIkeSNMqs_W26xy5VeSWjLaLFTEdSfQ";
    MediaUploadByUrlResult result = this.wxService.getMediaService().uploadByUrl(jobId);
    System.out.println(result);
  }

  @Test
  public void testUploadMediaJobFinishEvent() throws WxErrorException {
    File file = this.wxService.getMediaService().getJssdkFile("....");
  }
}
