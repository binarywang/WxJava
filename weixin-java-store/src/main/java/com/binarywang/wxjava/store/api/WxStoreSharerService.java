package com.binarywang.wxjava.store.api;

import java.util.List;
import com.binarywang.wxjava.store.bean.sharer.SharerBindResponse;
import com.binarywang.wxjava.store.bean.sharer.SharerInfoResponse;
import com.binarywang.wxjava.store.bean.sharer.SharerOrderParam;
import com.binarywang.wxjava.store.bean.sharer.SharerOrderResponse;
import com.binarywang.wxjava.store.bean.sharer.SharerSearchResponse;
import com.binarywang.wxjava.store.bean.sharer.SharerUnbindResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店 分享员服务接口
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface WxStoreSharerService {

  /**
   * 邀请分享员
   *
   * @param username 邀请的用户微信号
   * @return SharerBindResponse
   *
   * @throws WxErrorException 异常
   */
  SharerBindResponse bindSharer(String username) throws WxErrorException;

  /**
   * 获取绑定的分享员
   *
   * @param openid   分享员openid
   * @param username 分享员微信号（二选一）
   * @return SharerSearchResponse
   *
   * @throws WxErrorException 异常
   */
  SharerSearchResponse searchSharer(String openid, String username) throws WxErrorException;

  /**
   * 获取绑定的分享员列表
   *
   * @param page       分页参数，页数
   * @param pageSize   分页参数，每页分享员数（不超过100
   * @param sharerType 分享员类型
   * @return 分享员列表
   *
   * @throws WxErrorException 异常
   */
  SharerInfoResponse listSharer(Integer page, Integer pageSize, Integer sharerType) throws WxErrorException;

  /**
   * 获取分享员订单列表
   *
   * @param param 参数
   * @return 列表
   *
   * @throws WxErrorException 异常
   */
  SharerOrderResponse listSharerOrder(SharerOrderParam param) throws WxErrorException;

  /**
   * 解绑分享员
   *
   * @param openIds openid列表
   * @return 状态
   *
   * @throws WxErrorException 异常
   */
  SharerUnbindResponse unbindSharer(List<String> openIds) throws WxErrorException;
}
