package me.chanjar.weixin.cp.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 调用wx.agentConfig时所需要的签名信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpAgentJsapiSignature implements Serializable {
  private static final long serialVersionUID = 2650119900835832545L;

  private String url;

  private String corpid;

  private Long agentid;

  private long timestamp;

  private String nonceStr;

  private String signature;
}
