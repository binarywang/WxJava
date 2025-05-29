package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpAgent;

import java.util.List;

/**
 * <pre>
 *  管理企业号应用
 *  文档地址：<a href="https://work.weixin.qq.com/api/doc#10087">...</a>
 *  Created by huansinho on 2018/4/13.
 * </pre>
 *
 * @author <a href="https://github.com/huansinho">huansinho</a>
 */
public interface WxCpAgentService {
  /**
   * <pre>
   * 获取企业号应用信息
   * 该API用于获取企业号某个应用的基本信息，包括头像、昵称、账号类型、认证类型、可见范围等信息
   * 详情请见: <a href="https://work.weixin.qq.com/api/doc#10087">...</a>
   * </pre>
   *
   * @param agentId 企业应用的id
   * @return wx cp agent
   * @throws WxErrorException the wx error exception
   */
  WxCpAgent get(Integer agentId) throws WxErrorException;

  /**
   * <pre>
   * 设置应用.
   * 仅企业可调用，可设置当前凭证对应的应用；第三方不可调用。
   * 详情请见: <a href="https://work.weixin.qq.com/api/doc#10088">...</a>
   * </pre>
   *
   * @param agentInfo 应用信息
   * @throws WxErrorException the wx error exception
   */
  void set(WxCpAgent agentInfo) throws WxErrorException;

  /**
   * <pre>
   * 获取应用列表.
   * 企业仅可获取当前凭证对应的应用；第三方仅可获取被授权的应用。
   * 详情请见: <a href="https://work.weixin.qq.com/api/doc#11214">...</a>
   * </pre>
   *
   * @return the list
   * @throws WxErrorException the wx error exception
   */
  List<WxCpAgent> list() throws WxErrorException;

}
