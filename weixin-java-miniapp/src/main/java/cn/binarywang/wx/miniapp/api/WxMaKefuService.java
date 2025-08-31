package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.kefu.WxMaKfInfo;
import cn.binarywang.wx.miniapp.bean.kefu.WxMaKfList;
import cn.binarywang.wx.miniapp.bean.kefu.WxMaKfSession;
import cn.binarywang.wx.miniapp.bean.kefu.WxMaKfSessionList;
import cn.binarywang.wx.miniapp.bean.kefu.request.WxMaKfAccountRequest;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * <pre>
 * 小程序客服管理接口.
 * 不同于 WxMaCustomserviceWorkService (企业微信客服绑定) 和 WxMaMsgService.sendKefuMsg (发送客服消息),
 * 此接口专门处理小程序客服账号管理、会话管理等功能。
 * 
 * 注意：小程序客服管理接口与公众号客服管理接口在API端点和功能上有所不同。
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMaKefuService {

  /**
   * <pre>
   * 获取客服基本信息
   * 详情请见：<a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/customer-service/customerServiceMessage.getContactList.html">获取客服基本信息</a>
   * 接口url格式：https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @return 客服列表
   * @throws WxErrorException 异常
   */
  WxMaKfList kfList() throws WxErrorException;

  /**
   * <pre>
   * 添加客服账号
   * 详情请见：<a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/customer-service/customerServiceMessage.addKfAccount.html">添加客服账号</a>
   * 接口url格式：https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param request 客服账号信息
   * @return 是否成功
   * @throws WxErrorException 异常
   */
  boolean kfAccountAdd(WxMaKfAccountRequest request) throws WxErrorException;

  /**
   * <pre>
   * 修改客服账号
   * 详情请见：<a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/customer-service/customerServiceMessage.updateKfAccount.html">修改客服账号</a>
   * 接口url格式：https://api.weixin.qq.com/customservice/kfaccount/update?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param request 客服账号信息
   * @return 是否成功
   * @throws WxErrorException 异常
   */
  boolean kfAccountUpdate(WxMaKfAccountRequest request) throws WxErrorException;

  /**
   * <pre>
   * 删除客服账号
   * 详情请见：<a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/customer-service/customerServiceMessage.deleteKfAccount.html">删除客服账号</a>
   * 接口url格式：https://api.weixin.qq.com/customservice/kfaccount/del?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT
   * </pre>
   *
   * @param kfAccount 客服账号
   * @return 是否成功
   * @throws WxErrorException 异常
   */
  boolean kfAccountDel(String kfAccount) throws WxErrorException;

  /**
   * <pre>
   * 创建会话
   * 详情请见：<a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/customer-service/customerServiceMessage.createSession.html">创建会话</a>
   * 接口url格式：https://api.weixin.qq.com/customservice/kfsession/create?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param openid 用户openid
   * @param kfAccount 客服账号
   * @return 是否成功
   * @throws WxErrorException 异常
   */
  boolean kfSessionCreate(String openid, String kfAccount) throws WxErrorException;

  /**
   * <pre>
   * 关闭会话
   * 详情请见：<a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/customer-service/customerServiceMessage.closeSession.html">关闭会话</a>
   * 接口url格式：https://api.weixin.qq.com/customservice/kfsession/close?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param openid 用户openid
   * @param kfAccount 客服账号
   * @return 是否成功
   * @throws WxErrorException 异常
   */
  boolean kfSessionClose(String openid, String kfAccount) throws WxErrorException;

  /**
   * <pre>
   * 获取客户的会话状态
   * 详情请见：<a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/customer-service/customerServiceMessage.getSession.html">获取客户的会话状态</a>
   * 接口url格式：https://api.weixin.qq.com/customservice/kfsession/getsession?access_token=ACCESS_TOKEN&openid=OPENID
   * </pre>
   *
   * @param openid 用户openid
   * @return 会话信息
   * @throws WxErrorException 异常
   */
  WxMaKfSession kfSessionGet(String openid) throws WxErrorException;

  /**
   * <pre>
   * 获取客服的会话列表
   * 详情请见：<a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/customer-service/customerServiceMessage.getSessionList.html">获取客服的会话列表</a>
   * 接口url格式：https://api.weixin.qq.com/customservice/kfsession/getsessionlist?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT
   * </pre>
   *
   * @param kfAccount 客服账号
   * @return 会话列表
   * @throws WxErrorException 异常
   */
  WxMaKfSessionList kfSessionList(String kfAccount) throws WxErrorException;

}