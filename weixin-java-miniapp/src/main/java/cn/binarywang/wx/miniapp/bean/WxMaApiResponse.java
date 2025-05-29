package cn.binarywang.wx.miniapp.bean;

import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WxMaApiResponse {
  private static final Logger logger = LoggerFactory.getLogger(WxMaApiResponse.class);

  private String content;
  private Map<String, String> headers;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public void setHeaders(Map<String, String> headers) {
    this.headers = headers;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
