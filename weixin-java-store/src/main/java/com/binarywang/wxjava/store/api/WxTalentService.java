package com.binarywang.wxjava.store.api;

import com.binarywang.wxjava.store.bean.talent.TalentOrderDetailParam;
import com.binarywang.wxjava.store.bean.talent.TalentOrderDetailResponse;
import com.binarywang.wxjava.store.bean.talent.TalentOrderListParam;
import com.binarywang.wxjava.store.bean.talent.TalentOrderListResponse;
import com.binarywang.wxjava.store.bean.talent.TalentWindowProductDetailParam;
import com.binarywang.wxjava.store.bean.talent.TalentWindowProductDetailResponse;
import com.binarywang.wxjava.store.bean.talent.TalentWindowProductListParam;
import com.binarywang.wxjava.store.bean.talent.TalentWindowProductListResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店-带货助手服务接口
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
public interface WxTalentService {

  /**
   * <a href="https://developers.weixin.qq.com/doc/store/talent/openapi/order/api_get_order_list.html">获取佣金单列表</a>
   *
   * @param param 查询参数
   * @return 佣金单列表
   * @throws WxErrorException 接口调用异常
   */
  TalentOrderListResponse getOrderList(TalentOrderListParam param) throws WxErrorException;

  /**
   * <a href="https://developers.weixin.qq.com/doc/store/talent/openapi/order/api_get_order_detail.html">获取佣金单详情</a>
   *
   * @param param 查询参数
   * @return 佣金单详情
   * @throws WxErrorException 接口调用异常
   */
  TalentOrderDetailResponse getOrderDetail(TalentOrderDetailParam param) throws WxErrorException;

  /**
   * <a href="https://developers.weixin.qq.com/doc/store/talent/openapi/window/api_get_product_list.html">获取达人橱窗商品列表</a>
   *
   * @param param 查询参数
   * @return 橱窗商品列表
   * @throws WxErrorException 接口调用异常
   */
  TalentWindowProductListResponse getWindowProductList(TalentWindowProductListParam param) throws WxErrorException;

  /**
   * <a href="https://developers.weixin.qq.com/doc/store/talent/openapi/window/api_get_product_detail.html">获取达人橱窗商品详情</a>
   *
   * @param param 查询参数
   * @return 橱窗商品详情
   * @throws WxErrorException 接口调用异常
   */
  TalentWindowProductDetailResponse getWindowProductDetail(TalentWindowProductDetailParam param)
    throws WxErrorException;
}
