package me.chanjar.weixin.open.executor;

import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import me.chanjar.weixin.open.bean.ma.WxMaQrcodeParam;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @author yqx
 * created on  2018-09-13
 */
public class MaQrCodeOkhttpRequestExecutor extends MaQrCodeRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
  public MaQrCodeOkhttpRequestExecutor(RequestHttp<OkHttpClient, OkHttpProxyInfo> requestHttp) {
    super(requestHttp);
  }

  @Override
  public File execute(String uri, WxMaQrcodeParam qrcodeParam, WxType wxType) throws WxErrorException, IOException {
    if (qrcodeParam != null && StringUtils.isNotBlank(qrcodeParam.getPagePath())) {
      if (uri.indexOf('?') == -1) {
        uri += '?';
      }
      uri += uri.endsWith("?")
        ? "path=" + URLEncoder.encode(qrcodeParam.getRequestPath(), "UTF-8")
        : "&path=" + URLEncoder.encode(qrcodeParam.getRequestPath(), "UTF-8");
    }

    OkHttpClient client = requestHttp.getRequestHttpClient();
    Request request = new Request.Builder().url(uri).get().build();
    Response response = client.newCall(request).execute();
    String contentTypeHeader = response.header("Content-Type");
    if ("text/plain".equals(contentTypeHeader)) {
      String responseContent = response.body().string();
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MP));
    }

    try (InputStream inputStream = response.body().byteStream()) {
      return FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), "jpg");
    }

  }
}
