package me.chanjar.weixin.channel.bean.sharer;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 分享员信息响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class SharerInfoResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 1090517907546557929L;
  /** 分享员信息 */
  @JsonProperty("sharer_info_list")
  private List<SharerInfo> list;

}
