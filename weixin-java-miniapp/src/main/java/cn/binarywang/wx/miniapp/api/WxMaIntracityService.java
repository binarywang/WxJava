package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.intractiy.*;
import java.util.List;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小程序 物流服务 同城配送服务API <br>
 * *不是*即时配送接口，两个相近，容易混淆<br>
 * 微信相关接口 <br>
 * https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/industry/express/business/intracity_service.html
 */
public interface WxMaIntracityService {

  /** 申请开通门店权限 */
  void apply() throws WxErrorException;

  /** 创建门店 */
  String createStore(WxMaStore store) throws WxErrorException;

  /**
   * 更新门店；只更新store中不为null的部分 wxStoreId和outStoreId至少要有一个不为null，根据这2个来更新。 仅支持更新 storeName orderPattern
   * serviceTransPrefer addressInfo几个属性
   */
  void updateStore(WxMaStore store) throws WxErrorException;

  /** 查询门店（列出所有门店） */
  List<WxMaStore> listAllStores() throws WxErrorException;

  /** 根据wx_store_id查询门店 */
  WxMaStore queryStoreByWxStoreId(String wxStoreId) throws WxErrorException;

  /** 根据 out_store_id 查询门店 */
  List<WxMaStore> queryStoreByOutStoreId(String outStoreId) throws WxErrorException;

  /** 门店运费充值，返回充值URL */
  String storeCharge(WxMaStoreChargeRequest request) throws WxErrorException;

  /** 门店运费退款，返回退款金额 */
  int storeRefund(WxMaStoreRefundRequest request) throws WxErrorException;

  /** 门店运费流水查询 */
  WxMaStoreFlowResponse<? extends WxMaStoreFlowResponse.BasicFlowRecord> queryFlow(
      WxMaQueryFlowRequest request) throws WxErrorException;

  /** 查询门店余额 */
  WxMaStoreBalance balanceQuery(String wxStoreId, String serviceTransId, PayMode payMode)
      throws WxErrorException;

  /**
   * 设置扣费主体 <br>
   * 接口调用成功后，小程序的管理员会收到模板消息，点击模板消息确认更改门店扣费主体后，修改生效。
   */
  void setPayMode(PayMode payMode) throws WxErrorException;

  /** 查询扣费主体 */
  WxMaGetPayModeResponse getPayMode() throws WxErrorException;

  /** 查询运费 */
  WxMaPreAddOrderResponse preAddOrder(WxMaPreAddOrderRequest request) throws WxErrorException;

  /** 创建配送单 */
  WxMaAddOrderResponse addOrder(WxMaAddOrderRequest order) throws WxErrorException;

  /** 查询配送单 根据wxOrderId */
  WxMaOrder queryOrderByWxOrderId(String wxOrderId) throws WxErrorException;

  /** 依据商户订单号 查询配送单 */
  WxMaOrder queryOrderByStoreOrderId(String wxStoreId, String storeOrderId) throws WxErrorException;

  /** 依据微信订单号 查询配送单 */
  WxMaCancelOrderResponse cancelOrderByWxOrderId(
      String wxOrderId, int cancelReasonId, String cancelReason) throws WxErrorException;

  /** 依据商户订单号 查询配送单 */
  WxMaCancelOrderResponse cancelOrderByStoreOrderId(
      String wxStoreId, String storeOrderId, int cancelReasonId, String cancelReason)
      throws WxErrorException;

  /**
   * 查询支持同城配送的城市
   *
   * @param serviceTransId 运力ID，传NULL则返回所有
   */
  List<WxMaTransCity> getCity(String serviceTransId) throws WxErrorException;
}
