package com.binarywang.spring.starter.wxjava.cp.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 企业微信企业相关配置属性
 *
 * <p>企业微信中不同的 corpSecret 对应不同的权限范围，常见的有：</p>
 * <ul>
 *   <li>自建应用 Secret：在"应用管理 - 自建应用"中查看，只能调用该应用有权限的接口</li>
 *   <li>通讯录同步 Secret：在"管理工具 - 通讯录同步"中查看，用于管理部门和成员（增删改查）</li>
 *   <li>客户联系 Secret：在"客户联系"中查看，用于客户联系相关接口</li>
 * </ul>
 * <p>如需同时使用多种权限范围（例如：既要操作通讯录，又要调用自建应用接口），
 * 可在 {@code wx.cp.corps} 下配置多个条目，每个条目使用对应权限的 {@code corpSecret}，
 * 其中通讯录同步的条目无需填写 {@code agentId}。</p>
 *
 * @author yl
 * created on 2023/10/16
 */
@Data
@NoArgsConstructor
public class WxCpSingleProperties implements Serializable {
  private static final long serialVersionUID = -7502823825007859418L;
  /**
   * 微信企业号 corpId
   */
  private String corpId;
  /**
   * 微信企业号 corpSecret（权限密钥）
   *
   * <p>企业微信针对不同的功能模块提供了不同的 Secret，每种 Secret 只对对应模块的接口有调用权限：</p>
   * <ul>
   *   <li>自建应用 Secret：在"应用管理 - 自建应用"中找到对应应用，查看其 Secret，
   *       使用时需同时配置对应的 {@code agentId}</li>
   *   <li>通讯录同步 Secret：在"管理工具 - 通讯录同步"中查看，
   *       使用此 Secret 可管理部门、成员，无需配置 {@code agentId}</li>
   *   <li>其他 Secret（客户联系等）：根据需要在企业微信后台查看对应 Secret</li>
   * </ul>
   */
  private String corpSecret;
  /**
   * 微信企业号应用 token
   */
  private String token;
  /**
   * 微信企业号应用 ID（AgentId）
   *
   * <p>使用自建应用 Secret 时，需要填写对应应用的 AgentId。</p>
   * <p>使用通讯录同步 Secret 时，无需填写此字段。</p>
   */
  private Integer agentId;
  /**
   * 微信企业号应用 EncodingAESKey
   */
  private String aesKey;
  /**
   * 微信企业号应用 会话存档私钥
   */
  private String msgAuditPriKey;
  /**
   * 微信企业号应用 会话存档类库路径
   */
  private String msgAuditLibPath;

  /**
   * 自定义企业微信服务器baseUrl，用于替换默认的 https://qyapi.weixin.qq.com
   * 例如：http://proxy.company.com:8080
   */
  private String baseApiUrl;
}
