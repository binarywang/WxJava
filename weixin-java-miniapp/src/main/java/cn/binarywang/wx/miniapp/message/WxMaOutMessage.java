package cn.binarywang.wx.miniapp.message;

import cn.binarywang.wx.miniapp.config.WxMaConfig;

import java.io.Serializable;

/**
 * 微信小程序输出消息的通用接口，支持XML和JSON两种格式.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMaOutMessage extends Serializable {

  /**
   * 转换成XML格式.
   *
   * @return XML格式的消息
   */
  String toXml();

  /**
   * 转换成JSON格式.
   *
   * @return JSON格式的消息
   */
  String toJson();

  /**
   * 转换成加密的XML格式.
   *
   * @param config 配置对象
   * @return 加密后的XML格式消息
   */
  String toEncryptedXml(WxMaConfig config);

  /**
   * 转换成加密的JSON格式.
   *
   * @param config 配置对象
   * @return 加密后的JSON格式消息
   */
  String toEncryptedJson(WxMaConfig config);
}
