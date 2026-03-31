package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.face.WxMaFaceGetVerifyIdRequest;
import cn.binarywang.wx.miniapp.bean.face.WxMaFaceGetVerifyIdResponse;
import cn.binarywang.wx.miniapp.bean.face.WxMaFaceQueryVerifyInfoRequest;
import cn.binarywang.wx.miniapp.bean.face.WxMaFaceQueryVerifyInfoResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小程序人脸核身相关接口
 * <p>
 * 文档地址：<a href="https://developers.weixin.qq.com/miniprogram/dev/server/API/face/">微信人脸核身接口列表</a>
 * </p>
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
public interface WxMaFaceService {

  /**
   * 获取用户人脸核身会话唯一标识
   * <p>
   * 业务方后台根据「用户实名信息（姓名+身份证）」调用 getVerifyId 接口获取人脸核身会话唯一标识 verifyId 字段，
   * 然后给到小程序前端调用 wx.requestFacialVerify 接口使用。
   * </p>
   * <p>
   * 文档地址：<a href="https://developers.weixin.qq.com/miniprogram/dev/server/API/face/api_getverifyid">获取用户人脸核身会话唯一标识</a>
   * </p>
   *
   * @param request 请求参数
   * @return 包含 verifyId 的响应实体
   * @throws WxErrorException 调用微信接口失败时抛出
   */
  WxMaFaceGetVerifyIdResponse getVerifyId(WxMaFaceGetVerifyIdRequest request) throws WxErrorException;

  /**
   * 查询用户人脸核身真实验证结果
   * <p>
   * 业务方后台根据人脸核身会话唯一标识 verifyId 字段调用 queryVerifyInfo 接口查询用户人脸核身真实验证结果。
   * 核身通过的判断条件：errcode=0 且 verify_ret=10000。
   * </p>
   * <p>
   * 文档地址：<a href="https://developers.weixin.qq.com/miniprogram/dev/server/API/face/api_queryverifyinfo">查询用户人脸核身真实验证结果</a>
   * </p>
   *
   * @param request 请求参数
   * @return 包含 verifyRet 的响应实体
   * @throws WxErrorException 调用微信接口失败时抛出
   */
  WxMaFaceQueryVerifyInfoResponse queryVerifyInfo(WxMaFaceQueryVerifyInfoRequest request) throws WxErrorException;

}
