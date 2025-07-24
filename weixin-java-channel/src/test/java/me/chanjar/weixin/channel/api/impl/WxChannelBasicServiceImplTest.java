package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.google.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;
import me.chanjar.weixin.channel.api.WxChannelBasicService;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.address.AddressCodeResponse;
import me.chanjar.weixin.channel.bean.image.ChannelImageInfo;
import me.chanjar.weixin.channel.bean.image.ChannelImageResponse;
import me.chanjar.weixin.channel.bean.image.QualificationFileResponse;
import me.chanjar.weixin.channel.bean.shop.ShopInfo;
import me.chanjar.weixin.channel.bean.shop.ShopInfoResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxChannelBasicServiceImplTest {

  private static final Logger log = LoggerFactory.getLogger(WxChannelBasicServiceImplTest.class);

  @Inject
  private WxChannelService channelService;

  @Test
  public void testGetAccessToken() throws WxErrorException {
    String accessToken = channelService.getAccessToken();
    assertNotNull(accessToken);
    log.info("accessToken: \n{}\n\n", accessToken);
    System.out.println(accessToken);
  }

  @Test
  public void testGetShopInfo() throws WxErrorException {
    WxChannelBasicService basicService = channelService.getBasicService();
    ShopInfoResponse response = basicService.getShopInfo();
    assertNotNull(response);
    assertTrue(response.isSuccess());
    ShopInfo info = response.getInfo();
    assertNotNull(info);
    System.out.println(JsonUtils.encode(response));
  }

  @Test
  public void testUploadImg() throws WxErrorException, IOException {
    String imgUrl = "https://github.githubassets.com/images/icons/emoji/octocat.png";
    WxChannelBasicService basicService = channelService.getBasicService();
    // 获取mediaId
    ChannelImageInfo info = basicService.uploadImg(0, imgUrl);
    assertNotNull(info);
    assertNotNull(info.getMediaId());
    System.out.println(info.getMediaId());
    // 获取临时链接
    info = basicService.uploadImg(1, imgUrl);
    assertNotNull(info);
    assertNotNull(info.getUrl());
    System.out.println(info.getUrl());
    // 本地文件的格式上传
    String fileName = "tmp.png";
    URL url = ClassLoader.getSystemResource(fileName);
    File f = File.createTempFile(UUID.randomUUID().toString(), ".png");
    FileUtils.copyURLToFile(url, f);
    info = basicService.uploadImg(1, f, 253, 253);
    assertNotNull(info);
    assertNotNull(info.getUrl());
    System.out.println(info.getUrl());
    FileUtils.forceDeleteOnExit(f);
  }

  @Test
  public void testUploadQualificationFile() throws IOException, WxErrorException {
    WxChannelBasicService basicService = channelService.getBasicService();
    // 本地文件的格式上传
    String fileName = "tmp.png";
    URL url = ClassLoader.getSystemResource(fileName);
    File f = File.createTempFile(UUID.randomUUID().toString(), ".png");
    FileUtils.copyURLToFile(url, f);
    QualificationFileResponse response = basicService.uploadQualificationFile(f);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    assertNotNull(response.getData());
    assertNotNull(response.getData().getId());
    System.out.println(response.getData().getId());
    FileUtils.forceDeleteOnExit(f);
  }

  @Test
  public void testGetImg() throws WxErrorException {
    WxChannelBasicService basicService = channelService.getBasicService();
    String mediaId = "ttRiex0K2utmlhR-IWcfaWP6deE5Gzo48Hq8abLEoVtTY618jAGtEmDDRPimKpTP8vlgTMwZokXHgm4fBVw82Q";
    ChannelImageResponse response = basicService.getImg(mediaId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(response.getContentType());
  }

  @Test
  public void testGetAddressCode() throws WxErrorException {
    WxChannelBasicService basicService = channelService.getBasicService();
    Integer rootCode = 0;
    AddressCodeResponse response = basicService.getAddressCode(rootCode);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(response);
  }
}
