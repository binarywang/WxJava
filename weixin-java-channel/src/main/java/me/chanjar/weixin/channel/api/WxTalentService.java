package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.talent.TalentOrderDetailParam;
import me.chanjar.weixin.channel.bean.talent.TalentOrderDetailResponse;
import me.chanjar.weixin.channel.bean.talent.TalentOrderListParam;
import me.chanjar.weixin.channel.bean.talent.TalentOrderListResponse;
import me.chanjar.weixin.channel.bean.talent.TalentWindowProductDetailParam;
import me.chanjar.weixin.channel.bean.talent.TalentWindowProductDetailResponse;
import me.chanjar.weixin.channel.bean.talent.TalentWindowProductListParam;
import me.chanjar.weixin.channel.bean.talent.TalentWindowProductListResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店-带货助手服务接口
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Deprecated
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
