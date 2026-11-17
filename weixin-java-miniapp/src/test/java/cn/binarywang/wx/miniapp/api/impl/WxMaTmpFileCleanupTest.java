package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.sun.net.httpserver.HttpServer;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

/**
 * 验证 SDK 内部创建的临时文件用完即删除，且不会每次调用都新建一个临时目录.
 */
public class WxMaTmpFileCleanupTest {

  @Test
  public void getQrCodeReusesOneTmpDir() throws Exception {
    byte[] image = {1, 2, 3, 4};
    HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
    server.createContext("/wxa/get_qrcode", exchange -> {
      exchange.getResponseHeaders().add("Content-Type", "image/jpeg");
      exchange.getResponseHeaders().add("Content-Disposition", "attachment; filename=\"qrcode.jpg\"");
      exchange.sendResponseHeaders(200, image.length);
      try (OutputStream out = exchange.getResponseBody()) {
        out.write(image);
      }
    });
    server.start();
    WxMaServiceImpl service = spy(new WxMaServiceImpl());
    try {
      WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
      config.setAppid("FAKE_APP_ID");
      config.setSecret("FAKE_SECRET");
      config.setApiHostUrl("http://127.0.0.1:" + server.getAddress().getPort());
      config.updateAccessToken("FAKE_ACCESS_TOKEN", 7200);
      service.setWxMaConfig(config);

      List<File> downloaded = new ArrayList<>();
      doAnswer(invocation -> {
        Object result = invocation.callRealMethod();
        downloaded.add((File) result);
        return result;
      }).when(service).execute(any(RequestExecutor.class), anyString(), isNull());

      WxMaCodeServiceImpl codeService = new WxMaCodeServiceImpl(service);
      assertEquals(codeService.getQrCode(null), image);
      assertEquals(codeService.getQrCode("pages/index"), image);

      assertEquals(downloaded.size(), 2);
      assertFalse(downloaded.get(0).exists());
      assertFalse(downloaded.get(1).exists());
      assertEquals(downloaded.get(1).getParentFile(), downloaded.get(0).getParentFile());
    } finally {
      service.getRequestHttpClient().close();
      server.stop(0);
    }
  }

  @Test
  public void uploadMediaFromStreamDeletesTmpFile() throws Exception {
    WxMaMediaServiceImpl mediaService = spy(new WxMaMediaServiceImpl(mock(WxMaService.class)));
    WxMediaUploadResult result = new WxMediaUploadResult();
    List<File> uploaded = new ArrayList<>();
    doAnswer(invocation -> {
      File file = invocation.getArgument(1);
      assertTrue(file.isFile());
      uploaded.add(file);
      return result;
    }).when(mediaService).uploadMedia(eq("image"), any(File.class));

    assertSame(mediaService.uploadMedia("image", "jpg", new ByteArrayInputStream(new byte[]{1, 2, 3})), result);
    assertSame(mediaService.uploadMedia("image", "jpg", new ByteArrayInputStream(new byte[]{4, 5, 6})), result);

    assertEquals(uploaded.size(), 2);
    assertFalse(uploaded.get(0).exists());
    assertFalse(uploaded.get(1).exists());
    assertEquals(uploaded.get(1).getParentFile(), uploaded.get(0).getParentFile());
  }

  @Test
  public void uploadResponseImageFromStreamDeletesTmpFile() throws Exception {
    WxMaComplaintServiceImpl complaintService = spy(new WxMaComplaintServiceImpl(mock(WxMaService.class)));
    List<File> uploaded = new ArrayList<>();
    doAnswer(invocation -> {
      File file = invocation.getArgument(0);
      assertTrue(file.isFile());
      uploaded.add(file);
      return "MEDIA_ID";
    }).when(complaintService).uploadResponseImage(any(File.class));

    assertEquals(complaintService.uploadResponseImage(new ByteArrayInputStream(new byte[]{1, 2, 3}), "jpg"),
      "MEDIA_ID");

    assertEquals(uploaded.size(), 1);
    assertFalse(uploaded.get(0).exists());
  }
}
