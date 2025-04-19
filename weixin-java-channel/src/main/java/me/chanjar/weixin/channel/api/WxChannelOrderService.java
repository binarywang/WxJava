package me.chanjar.weixin.channel.api;

import java.util.List;
import me.chanjar.weixin.channel.bean.base.AddressInfo;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.delivery.PackageAuditInfo;
import me.chanjar.weixin.channel.bean.delivery.DeliveryCompanyResponse;
import me.chanjar.weixin.channel.bean.delivery.DeliveryInfo;
import me.chanjar.weixin.channel.bean.order.ChangeOrderInfo;
import me.chanjar.weixin.channel.bean.order.DecodeSensitiveInfoResponse;
import me.chanjar.weixin.channel.bean.order.DeliveryUpdateParam;
import me.chanjar.weixin.channel.bean.order.OrderInfoResponse;
import me.chanjar.weixin.channel.bean.order.OrderListParam;
import me.chanjar.weixin.channel.bean.order.OrderListResponse;
import me.chanjar.weixin.channel.bean.order.OrderSearchParam;
import me.chanjar.weixin.channel.bean.order.VirtualTelNumberResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 订单服务接口
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @link <a href="https://developers.weixin.qq.com/doc/channels/API/order/order_status_graph.html">订单接口文档</a>
 */
public interface WxChannelOrderService {

  /**
   * 获取订单
   *
   * @param orderId 订单id
   * @return 订单详情
   *
   * @throws WxErrorException 异常
   */
  OrderInfoResponse getOrder(String orderId) throws WxErrorException;

  /**
   * 获取订单列表
   *
   * @param param 搜索条件
   * @return 订单列表
   *
   * @throws WxErrorException 异常
   */
  OrderListResponse getOrders(OrderListParam param) throws WxErrorException;

  /**
   * 订单搜索
   *
   * @param param 搜索条件
   * @return 订单列表
   *
   * @throws WxErrorException 异常
   */
  OrderListResponse searchOrder(OrderSearchParam param) throws WxErrorException;

  /**
   * 更改订单价格
   *
   * @param orderId          订单id
   * @param expressFee       运费价格（以分为单位）(不填不改)
   * @param changeOrderInfos 改价列表
   * @return 结果
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse updatePrice(String orderId, Integer expressFee, List<ChangeOrderInfo> changeOrderInfos)
    throws WxErrorException;

  /**
   * 更改订单备注
   *
   * @param orderId       订单id
   * @param merchantNotes 备注
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse updateRemark(String orderId, String merchantNotes) throws WxErrorException;

  /**
   * 更新订单地址
   *
   * @param orderId     订单id
   * @param userAddress 用户地址
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse updateAddress(String orderId, AddressInfo userAddress) throws WxErrorException;

  /**
   * 修改物流信息 <br /> 发货完成的订单可以修改，最多修改1次 拆包发货的订单暂不允许修改物流 虚拟商品订单暂不允许修改物流
   *
   * @param param 物流信息
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse updateDelivery(DeliveryUpdateParam param) throws WxErrorException;

  /**
   * 同意用户修改收货地址请求
   *
   * @param orderId 订单id
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse acceptAddressModify(String orderId) throws WxErrorException;

  /**
   * 拒接用户修改收货地址请求
   *
   * @param orderId 订单id
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse rejectAddressModify(String orderId) throws WxErrorException;

  /**
   * 关闭订单 （需要订单状态为未付款状态）
   *
   * @param orderId 订单id
   * @return BaseResponse
   */
  WxChannelBaseResponse closeOrder(String orderId);

  /**
   * 获取快递公司列表
   *
   * @return 快递公司列表
   *
   * @throws WxErrorException 异常
   */
  DeliveryCompanyResponse listDeliveryCompany() throws WxErrorException;

  /**
   * 订单发货
   *
   * @param orderId      订单id
   * @param deliveryList 物流信息
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse deliveryOrder(String orderId, List<DeliveryInfo> deliveryList) throws WxErrorException;

  /**
   * 上传生鲜质检信息<br />
   *
   * 注意事项：<br />
   *  1. 非生鲜质检的订单不能进行上传 <br />
   *  2. 图片url必须用图片上传接口获取 {@link WxChannelBasicService#uploadImg(int, String)}<br />
   *
   * @param orderId 订单id
   * @param items   商品打包信息
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse uploadFreshInspect(String orderId, List<PackageAuditInfo> items) throws WxErrorException;

  /**
   * 兑换虚拟号
   *
   * @param orderId 订单id
   * @return 虚拟号信息
   * @throws WxErrorException 异常
   */
  VirtualTelNumberResponse getVirtualTelNumber(String orderId) throws WxErrorException;

  /**
   * 解码订单包含的敏感数据
   *
   * @param orderId 订单id
   * @return 解码结果
   * @throws WxErrorException 异常
   */
  DecodeSensitiveInfoResponse decodeSensitiveInfo(String orderId) throws WxErrorException;
}
