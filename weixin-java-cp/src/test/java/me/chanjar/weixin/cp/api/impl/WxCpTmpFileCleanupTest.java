package me.chanjar.weixin.cp.api.impl;

import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import me.chanjar.weixin.cp.tp.service.impl.WxCpTpMediaServiceImpl;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

/**
 * 验证通过输入流上传时，SDK 内部创建的临时文件在上传后被删除.
 */
public class WxCpTmpFileCleanupTest {

  @Test
  public void mediaUploadFromStreamDeletesTmpFile() throws Exception {
    WxCpMediaServiceImpl mediaService = spy(new WxCpMediaServiceImpl(mock(WxCpService.class)));
    WxMediaUploadResult result = new WxMediaUploadResult();
    List<File> uploaded = new ArrayList<>();
    doAnswer(invocation -> {
      File file = invocation.getArgument(1);
      assertTrue(file.isFile());
      uploaded.add(file);
      return result;
    }).when(mediaService).upload(eq("image"), any(File.class));

    assertSame(mediaService.upload("image", "jpg", new ByteArrayInputStream(new byte[]{1, 2, 3})), result);

    assertEquals(uploaded.size(), 1);
    assertFalse(uploaded.get(0).exists());
  }

  @Test
  public void tpMediaUploadFromStreamDeletesTmpFile() throws Exception {
    WxCpTpMediaServiceImpl mediaService = spy(new WxCpTpMediaServiceImpl(mock(WxCpTpService.class)));
    WxMediaUploadResult result = new WxMediaUploadResult();
    List<File> uploaded = new ArrayList<>();
    doAnswer(invocation -> {
      File file = invocation.getArgument(1);
      assertTrue(file.isFile());
      uploaded.add(file);
      return result;
    }).when(mediaService).upload(eq("image"), any(File.class), eq("CORP_ID"));

    assertSame(mediaService.upload("image", "jpg", new ByteArrayInputStream(new byte[]{1, 2, 3}), "CORP_ID"),
      result);

    assertEquals(uploaded.size(), 1);
    assertFalse(uploaded.get(0).exists());
  }

  @Test
  public void externalContactAttachmentFromStreamDeletesTmpFile() throws Exception {
    WxCpExternalContactServiceImpl contactService = spy(new WxCpExternalContactServiceImpl(mock(WxCpService.class)));
    WxMediaUploadResult result = new WxMediaUploadResult();
    List<File> uploaded = new ArrayList<>();
    doAnswer(invocation -> {
      File file = invocation.getArgument(2);
      assertTrue(file.isFile());
      uploaded.add(file);
      return result;
    }).when(contactService).uploadAttachment(eq("image"), eq(1), any(File.class));

    assertSame(contactService.uploadAttachment("image", "jpg", 1, new ByteArrayInputStream(new byte[]{1, 2, 3})),
      result);

    assertEquals(uploaded.size(), 1);
    assertFalse(uploaded.get(0).exists());
  }
}
