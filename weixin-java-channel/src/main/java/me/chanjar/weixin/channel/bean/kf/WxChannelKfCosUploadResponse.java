package me.chanjar.weixin.channel.bean.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

import java.io.Serializable;

/**
 * 上传多媒体资源返回结果
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxChannelKfCosUploadResponse extends WxChannelBaseResponse implements Serializable {

  private static final long serialVersionUID = -8073026558742450133L;

  /** 多媒体 cos_url */
  @JsonProperty("cos_url")
  private String cosUrl;
}
