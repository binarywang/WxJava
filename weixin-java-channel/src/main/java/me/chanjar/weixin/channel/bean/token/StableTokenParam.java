package me.chanjar.weixin.channel.bean.token;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 稳定版access_token,请求参数
 *
 * @author <a href="https://github.com/asushiye">asushiye</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StableTokenParam implements Serializable {
  private static final long serialVersionUID = 6849364823232834171L;

  @JsonProperty("grant_type")
  private String grantType;

  @JsonProperty("appid")
  private String appId;

  @JsonProperty("secret")
  private String secret;

  @JsonProperty("force_refresh")
  private Boolean forceRefresh;
}
