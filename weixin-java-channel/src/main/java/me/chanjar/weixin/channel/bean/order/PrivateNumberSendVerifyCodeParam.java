package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取短信验证码 请求参数
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PrivateNumberSendVerifyCodeParam implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 手机号 */
  @JsonProperty("phone")
  private String phone;

}
