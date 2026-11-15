package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaMediaService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.bean.CommonUploadParam;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.util.http.BaseMediaDownloadRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestExecutor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Media.MEDIA_GET_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Media.MEDIA_UPLOAD_URL;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RequiredArgsConstructor
public class WxMaMediaServiceImpl implements WxMaMediaService {
  private final WxMaService wxMaService;

  @Override
  public WxMediaUploadResult uploadMedia(String mediaType, String fileType, InputStream inputStream) throws WxErrorException {
    File tmpFile = null;
    try {
      tmpFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), fileType);
      return this.uploadMedia(mediaType, tmpFile);
    } catch (IOException e) {
      throw new WxErrorException(WxError.builder().errorMsg(e.getMessage()).build(), e);
    } finally {
      if (tmpFile != null) {
        tmpFile.delete();
      }
    }
  }

  @Override
  public WxMediaUploadResult uploadMedia(String mediaType, File file) throws WxErrorException {
//    return this.wxMaService.execute(MediaUploadRequestExecutor.create(this.wxMaService.getRequestHttp()), url, file);
    String url = String.format(MEDIA_UPLOAD_URL, mediaType);
    String result = wxMaService.upload(url, CommonUploadParam.fromFile("media", file));
    return WxMediaUploadResult.fromJson(result);
  }

  @Override
  public File getMedia(String mediaId) throws WxErrorException {
    try {
      RequestExecutor<File, String> executor = BaseMediaDownloadRequestExecutor
        .create(this.wxMaService.getRequestHttp(), FileUtils.getTmpDir());
      return this.wxMaService.execute(executor, MEDIA_GET_URL, "media_id=" + mediaId);
    } catch (IOException e) {
      throw new WxErrorException(WxError.builder().errorMsg(e.getMessage()).build(), e);
    }
  }

}
