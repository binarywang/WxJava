package me.chanjar.weixin.channel.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 带货达人列表数据
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class FinderGmvItem implements Serializable {

  private static final long serialVersionUID = -3740996985044711599L;

  /** 视频号id */
  @JsonProperty("finder_id")
  private String finderId;

  /** 视频号昵称 */
  @JsonProperty("finder_nickname")
  private String finderNickname;

  /** 带货达人数据 */
  @JsonProperty("data")
  private FinderGmvData data;
}
