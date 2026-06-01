package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.service.WxOAuth2Service;

/**
 * 微信公众号网页授权（OAuth2）服务接口.
 *
 * <p>完整文档见：
 * <a href="https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/Wechat_webpage_authorization.html">
 *   微信网页授权
 * </a>
 * </p>
 *
 * <p>包含以下能力：
 * <ul>
 *   <li>构建网页授权 URL</li>
 *   <li>通过 code 换取 access_token（{@code sns/oauth2/access_token}）</li>
 *   <li>刷新 access_token（{@code sns/oauth2/refresh_token}）</li>
 *   <li>获取用户基本信息（{@code sns/userinfo}）</li>
 *   <li>校验 access_token 是否有效（{@code sns/auth}）</li>
 * </ul>
 * </p>
 *
 * @author GitHub Copilot
 */
public interface WxMpOAuth2Service extends WxOAuth2Service {
}
