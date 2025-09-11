package me.chanjar.weixin.mp.api.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.service.WxImgProcService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.api.test.TestConstants;
import me.chanjar.weixin.common.bean.imgproc.WxImgProcAiCropResult;
import me.chanjar.weixin.common.bean.imgproc.WxImgProcQrCodeResult;
import me.chanjar.weixin.common.bean.imgproc.WxImgProcSuperResolutionResult;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Test
@Guice(modules = ApiTestModule.class)
public class WxMpImgProcServiceImplTest {
  @Inject
  private WxMpService mpService;

  @Test
  public void testQrCode() throws WxErrorException {
    final WxImgProcQrCodeResult result = this.mpService.getImgProcService().qrCode("https://gitee.com/binary/weixin-java-tools/raw/master/images/qrcodes/mp.png");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testQrCode2() throws Exception {
    InputStream inputStream = ClassLoader.getSystemResourceAsStream("mm.jpeg");
    File tempFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), TestConstants.FILE_JPG);
    final WxImgProcQrCodeResult result = this.mpService.getImgProcService().qrCode(tempFile);
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testSuperResolution() throws WxErrorException {
    final WxImgProcSuperResolutionResult result = this.mpService.getImgProcService().superResolution("https://gitee.com/binary/weixin-java-tools/raw/master/images/qrcodes/mp.png");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testSuperResolution2() throws Exception {
    InputStream inputStream = ClassLoader.getSystemResourceAsStream("mm.jpeg");
    File tempFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), TestConstants.FILE_JPG);
    final WxImgProcSuperResolutionResult result = this.mpService.getImgProcService().superResolution(tempFile);
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testAiCrop() throws WxErrorException {
    final WxImgProcAiCropResult result = this.mpService.getImgProcService().aiCrop("https://gitee.com/binary/weixin-java-tools/images/banners/wiki.jpg");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testAiCrop2() throws WxErrorException {
    final WxImgProcAiCropResult result = this.mpService.getImgProcService().aiCrop("https://gitee.com/binary/weixin-java-tools/images/banners/wiki.jpg", "1,2.35");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testAiCrop3() throws Exception {
    InputStream inputStream = ClassLoader.getSystemResourceAsStream("mm.jpeg");
    File tempFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), TestConstants.FILE_JPG);
    final WxImgProcAiCropResult result = this.mpService.getImgProcService().aiCrop(tempFile);
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testAiCrop4() throws Exception {
    InputStream inputStream = ClassLoader.getSystemResourceAsStream("mm.jpeg");
    File tempFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), TestConstants.FILE_JPG);
    final WxImgProcAiCropResult result = this.mpService.getImgProcService().aiCrop(tempFile, "1,2.35,3.5");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  public static class mockTest {
    private WxMpService wxService = mock(WxMpService.class);

    @Test
    public void testQrCode() throws Exception {
      String returnJson = "{\n" +
        "    \"errcode\": 0, \n" +
        "    \"errmsg\": \"ok\", \n" +
        "    \"code_results\": [\n" +
        "        {\n" +
        "            \"type_name\": \"QR_CODE\", \n" +
        "            \"data\": \"https://www.qq.com\", \n" +
        "            \"pos\": {\n" +
        "                \"left_top\": {\n" +
        "                    \"x\": 585, \n" +
        "                    \"y\": 378\n" +
        "                }, \n" +
        "                \"right_top\": {\n" +
        "                    \"x\": 828, \n" +
        "                    \"y\": 378\n" +
        "                }, \n" +
        "                \"right_bottom\": {\n" +
        "                    \"x\": 828, \n" +
        "                    \"y\": 618\n" +
        "                }, \n" +
        "                \"left_bottom\": {\n" +
        "                    \"x\": 585, \n" +
        "                    \"y\": 618\n" +
        "                }\n" +
        "            }\n" +
        "        }, \n" +
        "        {\n" +
        "            \"type_name\": \"QR_CODE\", \n" +
        "            \"data\": \"https://mp.weixin.qq.com\", \n" +
        "            \"pos\": {\n" +
        "                \"left_top\": {\n" +
        "                    \"x\": 185, \n" +
        "                    \"y\": 142\n" +
        "                }, \n" +
        "                \"right_top\": {\n" +
        "                    \"x\": 396, \n" +
        "                    \"y\": 142\n" +
        "                }, \n" +
        "                \"right_bottom\": {\n" +
        "                    \"x\": 396, \n" +
        "                    \"y\": 353\n" +
        "                }, \n" +
        "                \"left_bottom\": {\n" +
        "                    \"x\": 185, \n" +
        "                    \"y\": 353\n" +
        "                }\n" +
        "            }\n" +
        "        }, \n" +
        "        {\n" +
        "            \"type_name\": \"EAN_13\", \n" +
        "            \"data\": \"5906789678957\"\n" +
        "        }, \n" +
        "        {\n" +
        "            \"type_name\": \"CODE_128\", \n" +
        "            \"data\": \"50090500019191\"\n" +
        "        }\n" +
        "    ], \n" +
        "    \"img_size\": {\n" +
        "        \"w\": 1000, \n" +
        "        \"h\": 900\n" +
        "    }\n" +
        "}";
      when(wxService.get(anyString(), anyString())).thenReturn(returnJson);
      final WxImgProcService wxMpImgProcService = new WxMpImgProcServiceImpl(wxService);
      final WxImgProcQrCodeResult result = wxMpImgProcService.qrCode("abc");
      assertThat(result).isNotNull();
      System.out.println(result);
    }

    @Test
    public void testSuperResolution() throws Exception {
      String returnJson = "{\n" +
        "    \"errcode\": 0, \n" +
        "    \"errmsg\": \"ok\", \n" +
        "    \"media_id\": \"6WXsIXkG7lXuDLspD9xfm5dsvHzb0EFl0li6ySxi92ap8Vl3zZoD9DpOyNudeJGB\"\n" +
        "}";
      when(wxService.get(anyString(), anyString())).thenReturn(returnJson);
      final WxImgProcService wxMpImgProcService = new WxMpImgProcServiceImpl(wxService);
      final WxImgProcSuperResolutionResult result = wxMpImgProcService.superResolution("abc");
      assertThat(result).isNotNull();
      System.out.println(result);
    }

    @Test
    public void testAiCrop() throws Exception {
      String returnJson = "{\n" +
        "    \"errcode\": 0, \n" +
        "    \"errmsg\": \"ok\", \n" +
        "    \"results\": [ //智能裁剪结果\n" +
        "        {\n" +
        "            \"crop_left\": 112, \n" +
        "            \"crop_top\": 0, \n" +
        "            \"crop_right\": 839, \n" +
        "            \"crop_bottom\": 727\n" +
        "        }, \n" +
        "        {\n" +
        "            \"crop_left\": 0, \n" +
        "            \"crop_top\": 205, \n" +
        "            \"crop_right\": 965, \n" +
        "            \"crop_bottom\": 615\n" +
        "        }\n" +
        "    ], \n" +
        "    \"img_size\": { //图片大小\n" +
        "        \"w\": 966, \n" +
        "        \"h\": 728\n" +
        "    }\n" +
        "}";
      when(wxService.get(anyString(), anyString())).thenReturn(returnJson);
      final WxImgProcService wxMpImgProcService = new WxMpImgProcServiceImpl(wxService);
      final WxImgProcAiCropResult result = wxMpImgProcService.aiCrop("abc");
      assertThat(result).isNotNull();
      System.out.println(result);
    }
  }
}
