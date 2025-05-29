package me.chanjar.weixin.open.api;

import me.chanjar.weixin.common.bean.result.WxMinishopImageUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;

import java.io.File;

/**
 * The interface Wx open service.
 *
 * @author <a href="https://github.com/007gzs">007</a>
 */
public interface WxOpenService {
  /**
   * Gets wx open component service.
   *
   * @return the wx open component service
   */
  WxOpenComponentService getWxOpenComponentService();

  /**
   * Gets wx open config storage.
   *
   * @return the wx open config storage
   */
  WxOpenConfigStorage getWxOpenConfigStorage();

  /**
   * Sets wx open config storage.
   *
   * @param wxOpenConfigStorage the wx open config storage
   */
  void setWxOpenConfigStorage(WxOpenConfigStorage wxOpenConfigStorage);

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的GET请求
   *
   * @param url        the url
   * @param queryParam the query param
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  String get(String url, String queryParam) throws WxErrorException;

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的POST请求
   *
   * @param url      the url
   * @param postData the post data
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  String post(String url, String postData) throws WxErrorException;


  WxMinishopImageUploadResult uploadMinishopMediaFile(String url, File file) throws WxErrorException;

}
