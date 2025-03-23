package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.order.WxMaOrderManagementGetOrderDetailPath;
import cn.binarywang.wx.miniapp.bean.order.WxMaOrderManagementResult;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaOrderShippingInfoBaseResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * @author xzh
 * @Description
 * @createTime 2025/01/16 15:20
 */
public interface WxMaOrderManagementService {

  /**
   * 查询订单详情路径
   * 注意事项
   * 如果没有配置过订单详情路径，会返回成功，其中path为''。
   *
   * @return WxMaOrderManagementGetOrderDetailPath
   * @throws WxErrorException e
   */
  WxMaOrderManagementGetOrderDetailPath getOrderDetailPath()
    throws WxErrorException;


  /**
   * 配置订单详情路径
   * 注意事项
   * 调用接口前需要先完成订单中心授权协议签署。
   * 请确保配置的path可正常跳转到小程序，并且path必须包含字符串“${商品订单号}”。
   *
   * @param path 订单详情路径
   * @return WxMaOrderManagementResult
   * @throws WxErrorException e
   */
  WxMaOrderManagementResult updateOrderDetailPath(String path)
    throws WxErrorException;

}
