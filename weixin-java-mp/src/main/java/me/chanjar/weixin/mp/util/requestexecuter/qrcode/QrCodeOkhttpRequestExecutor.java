package me.chanjar.weixin.mp.util.requestexecuter.qrcode;

import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.UUID;

/**
 *
 * @author ecoolper
 * created on  2017/5/5
 */
public class QrCodeOkhttpRequestExecutor extends QrCodeRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public QrCodeOkhttpRequestExecutor(RequestHttp<OkHttpClient, OkHttpProxyInfo> requestHttp) {
    super(requestHttp);
  }

  @Override
  public File execute(String uri, WxMpQrCodeTicket ticket, WxType wxType) throws WxErrorException, IOException {
    logger.debug("QrCodeOkhttpRequestExecutor is running");

    if (ticket != null) {
      if (uri.indexOf('?') == -1) {
        uri += '?';
      }
      uri += uri.endsWith("?")
        ? "ticket=" + URLEncoder.encode(ticket.getTicket(), "UTF-8")
        : "&ticket=" + URLEncoder.encode(ticket.getTicket(), "UTF-8");
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
