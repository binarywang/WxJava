package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.util.List;

/**
 * 微信开放平台小程序域名设置返回对象.
 *
 * @author yqx
 * created on  2018/9/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaDomainResult extends WxOpenResult {
  private static final long serialVersionUID = 3406315629639573330L;

  /**
   * request合法域名
   */
  @SerializedName("requestdomain")
  private List<String> requestDomain;
  /**
   * socket合法域名
   */
  @SerializedName("wsrequestdomain")
  private List<String> wsRequestDomain;
  /**
   * uploadFile合法域名
   */
  @SerializedName("uploaddomain")
  private List<String> uploadDomain;
  /**
   * downloadFile合法域名
   */
  @SerializedName("downloaddomain")
  private List<String> downloadDomain;
  /**
   * udp合法域名
   */
  @SerializedName("udpdomain")
  private List<String> udpDomain;
  /**
   * tcp合法域名
   */
  @SerializedName("tcpdomain")
  private List<String> tcpDomain;
  /**
   * request不合法域名
   */
  @SerializedName("invalid_requestdomain")
  private List<String> invalidRequestDomain;
  /**
   * socket不合法域名
   */
  @SerializedName("invalid_wsrequestdomain")
  private List<String> invalidWsRequestDomain;
  /**
   * uploadFile不合法域名
   */
  @SerializedName("invalid_uploaddomain")
  private List<String> invalidUploadDomain;
  /**
   * downloadFile不合法域名
   */
  @SerializedName("invalid_downloaddomain")
  private List<String> invalidDownloadDomain;
  /**
   * udp不合法域名
   */
  @SerializedName("invalid_udpdomain")
  private List<String> invalidUdpDomain;
  /**
   * tcp不合法域名
   */
  @SerializedName("invalid_tcpdomain")
  private List<String> invalidTcpDomain;

  public static WxOpenMaDomainResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxOpenMaDomainResult.class);
  }

}
