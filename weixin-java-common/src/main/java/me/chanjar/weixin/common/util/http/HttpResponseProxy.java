package me.chanjar.weixin.common.util.http;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpResponseProxy;
import me.chanjar.weixin.common.util.http.hc5.ApacheHttpClient5ResponseProxy;
import me.chanjar.weixin.common.util.http.jodd.JoddHttpResponseProxy;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpResponseProxy;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * http 框架的 response 代理类，方便提取公共方法
 * Created by Binary Wang on 2017-8-3.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface HttpResponseProxy {

  static ApacheHttpResponseProxy from(org.apache.http.client.methods.CloseableHttpResponse response) {
    return new ApacheHttpResponseProxy(response);
  }

  static ApacheHttpClient5ResponseProxy from(org.apache.hc.client5.http.impl.classic.CloseableHttpResponse response) {
    return new ApacheHttpClient5ResponseProxy(response);
  }

  static JoddHttpResponseProxy from(jodd.http.HttpResponse response) {
    return new JoddHttpResponseProxy(response);
  }

  static OkHttpResponseProxy from(okhttp3.Response response) {
    return new OkHttpResponseProxy(response);
  }

  String getFileName() throws WxErrorException;

  default String extractFileNameFromContentString(String content) throws WxErrorException {
    if (content == null || content.isEmpty()) {
      throw new WxErrorException("无法获取到文件名，content为空");
    }

    // 查找filename*=utf-8''开头的部分
    Pattern pattern = Pattern.compile("filename\\*=utf-8''(.*?)($|;|\\s|,)");
    Matcher matcher = pattern.matcher(content);
    if (matcher.find()) {
      String encodedFileName = matcher.group(1);
      // 解码URL编码的文件名
      try {
        return URLDecoder.decode(encodedFileName, StandardCharsets.UTF_8.name());
      } catch (UnsupportedEncodingException e) {
        throw new RuntimeException(e);
      }
    }

    // 查找普通filename="..."部分
    pattern = Pattern.compile("filename=\"(.*?)\"");
    matcher = pattern.matcher(content);
    if (matcher.find()) {
      return new String(matcher.group(1).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }

    throw new WxErrorException("无法获取到文件名，header信息有问题");
  }

}
