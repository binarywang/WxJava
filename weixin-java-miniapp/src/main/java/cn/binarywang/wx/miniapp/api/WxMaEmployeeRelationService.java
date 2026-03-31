package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.employee.WxMaSendEmployeeMsgRequest;
import cn.binarywang.wx.miniapp.bean.employee.WxMaUnbindEmployeeRequest;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 小程序用工关系相关操作接口
 * <p>
 * 文档地址：<a href="https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/laboruse/intro.html">用工关系简介</a>
 * </p>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on 2025-12-19
 */
public interface WxMaEmployeeRelationService {

  /**
   * 解绑用工关系
   * <p>
   * 企业可以调用该接口解除和用户的B2C用工关系
   * </p>
   * 文档地址：<a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/laboruse/api_unbinduserb2cauthinfo.html">解绑用工关系</a>
   *
   * @param request 解绑请求参数
   * @throws WxErrorException 调用微信接口失败时抛出
   */
  void unbindEmployee(WxMaUnbindEmployeeRequest request) throws WxErrorException;

  /**
   * 推送用工消息
   * <p>
   * 企业可以调用该接口向用户推送用工相关消息
   * </p>
   * 文档地址：<a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/laboruse/api_sendemployeerelationmsg.html">推送用工消息</a>
   *
   * @param request 推送消息请求参数
   * @throws WxErrorException 调用微信接口失败时抛出
   */
  void sendEmployeeMsg(WxMaSendEmployeeMsgRequest request) throws WxErrorException;
}
