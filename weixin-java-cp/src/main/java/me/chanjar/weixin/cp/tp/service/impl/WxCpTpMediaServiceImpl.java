package me.chanjar.weixin.cp.tp.service.impl;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.cp.tp.service.WxCpTpMediaService;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Media.IMG_UPLOAD;
import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Media.MEDIA_UPLOAD;

/**
 * <pre>
 * 媒体管理接口.
 * Created by Binary Wang on 2017-6-25.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RequiredArgsConstructor
public class WxCpTpMediaServiceImpl implements WxCpTpMediaService {
  private final WxCpTpService mainService;

  @Override
  public WxMediaUploadResult upload(String mediaType, String fileType, InputStream inputStream, String corpId)
    throws WxErrorException, IOException {
    File tmpFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), fileType);
    try {
      return this.upload(mediaType, tmpFile, corpId);
    } finally {
      tmpFile.delete();
    }
  }

  @Override
  public WxMediaUploadResult upload(String mediaType, File file, String corpId) throws WxErrorException {
    return this.mainService.execute(MediaUploadRequestExecutor.create(this.mainService.getRequestHttp()),
      mainService.getWxCpTpConfigStorage().getApiUrl(MEDIA_UPLOAD + mediaType) + "&access_token=" + mainService.getWxCpTpConfigStorage().getAccessToken(corpId), file);
  }

  @Override
  public String uploadImg(File file, String corpId) throws WxErrorException {
    String url = mainService.getWxCpTpConfigStorage().getApiUrl(IMG_UPLOAD);
    url += "?access_token=" + mainService.getWxCpTpConfigStorage().getAccessToken(corpId);
    return this.mainService.execute(MediaUploadRequestExecutor.create(this.mainService.getRequestHttp()), url, file)
      .getUrl();
  }
}
