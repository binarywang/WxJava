package me.chanjar.weixin.channel.bean.sharer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.sharer.SharerSearchParam}。
 */
@Data
@JsonInclude(Include.NON_NULL)
@Deprecated
public class SharerSearchParam implements Serializable {

  private static final long serialVersionUID = -6763899740755735718L;
  /** 分享员openid */
  @JsonProperty("openid")
  private String openid;

  /** 微信号 */
  @JsonProperty("username")
  private String username;

  public SharerSearchParam() {
  }

  public SharerSearchParam(String openid, String username) {
    this.openid = openid;
    this.username = username;
  }
}
