package me.chanjar.weixin.channel.bean.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 资质文件id响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class QualificationFileResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 5172377567441096813L;

  /** 文件数据 */
  @JsonProperty("data")
  private QualificationFileId data;
}
