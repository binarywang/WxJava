package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.WxMaGroupEnterInfo;
import cn.binarywang.wx.miniapp.bean.WxMaShareInfo;

/**
 * 分享信息相关操作接口.
 *
 * @author zhfish
 */
public interface WxMaShareService {

  /**
   * 解密分享敏感数据.
   *
   * @param sessionKey    会话密钥
   * @param encryptedData 消息密文
   * @param ivStr         加密算法的初始向量
   */
  WxMaShareInfo getShareInfo(String sessionKey, String encryptedData, String ivStr);

  /**
   * 解密群入口敏感数据.
   * 对应 wx.getGroupEnterInfo 接口返回的 encryptedData 解密
   *
   * @param sessionKey    会话密钥
   * @param encryptedData 消息密文
   * @param ivStr         加密算法的初始向量
   * @return 群入口信息
   * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/api/open-api/group/wx.getGroupEnterInfo.html">wx.getGroupEnterInfo 官方文档</a>
   */
  WxMaGroupEnterInfo getGroupEnterInfo(String sessionKey, String encryptedData, String ivStr);

}
