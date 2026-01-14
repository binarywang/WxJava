package me.chanjar.weixin.cp.tp.service;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpTpCustomizedAppDetail;
import me.chanjar.weixin.cp.bean.WxCpTpTemplateList;

/**
 * 企业微信第三方应用 - 代开发相关接口.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2026-01-14
 */
public interface WxCpTpCustomizedService {

  /**
   * 获取应用模板列表
   * <pre>
   * 服务商可通过本接口获取服务商所拥有的应用模板列表
   * 文档地址：https://developer.work.weixin.qq.com/document/path/97111
   * </pre>
   *
   * @return 应用模板列表
   * @throws WxErrorException 微信错误异常
   */
  WxCpTpTemplateList getTemplateList() throws WxErrorException;

  /**
   * 获取代开发应用详情
   * <pre>
   * 服务商可通过本接口获取某个授权企业中已经安装的代开发自建应用的详情
   * 文档地址：https://developer.work.weixin.qq.com/document/path/97111
   * </pre>
   *
   * @param authCorpId 授权企业的corpid
   * @param agentId    应用的agentid，为空时则返回该企业所有的代开发自建应用详情
   * @return 代开发应用详情
   * @throws WxErrorException 微信错误异常
   */
  WxCpTpCustomizedAppDetail getCustomizedAppDetail(String authCorpId, Integer agentId) throws WxErrorException;

}
