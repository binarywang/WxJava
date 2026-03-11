package me.chanjar.weixin.channel.api;


import java.util.List;

import me.chanjar.weixin.channel.bean.after.*;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.complaint.ComplaintOrderResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 售后服务接口
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface WxChannelAfterSaleService {

  /**
   * 获取售后单列表
   *
   * @param beginCreateTime 订单创建启始时间 unix时间戳
   * @param endCreateTime   订单创建结束时间，end_create_time减去begin_create_time不得大于24小时
   * @param nextKey         翻页参数，从第二页开始传，来源于上一页的返回值
   * @return 售后单列表
   *
   * @throws WxErrorException 异常
   * @deprecated 使用 {@link WxChannelAfterSaleService#listIds(AfterSaleListParam)}
   */
  @Deprecated
  AfterSaleListResponse listIds(Long beginCreateTime, Long endCreateTime, String nextKey)
    throws WxErrorException;

  /**
   * 获取售后单列表
   *
   * @param param 参数
   * @return 售后单列表
   *
   * @throws WxErrorException 异常
   */
  AfterSaleListResponse listIds(AfterSaleListParam param) throws WxErrorException;

  /**
   * 获取售后单详情
   *
   * @param afterSaleOrderId 售后单号
   * @return 售后单信息
   *
   * @throws WxErrorException 异常
   */
  AfterSaleInfoResponse get(String afterSaleOrderId) throws WxErrorException;

  /**
   * 同意售后
   * 文档地址 https://developers.weixin.qq.com/doc/channels/API/aftersale/acceptapply.html
   *
   * @param afterSaleOrderId 售后单号
   * @param addressId        同意退货时传入地址id
   * @param acceptType       1. 同意退货退款，并通知用户退货; 2. 确认收到货并退款给用户。 如果不填则将根据当前的售后单状态自动选择相应操作。对于仅退款的情况，由于只存在一种同意的场景，无需填写此字段。
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse accept(String afterSaleOrderId, String addressId, Integer acceptType) throws WxErrorException;

  /**
   * 拒绝售后
   * 文档地址 https://developers.weixin.qq.com/doc/channels/API/aftersale/rejectapply.html
   *
   * @param afterSaleOrderId 售后单号
   * @param rejectReason     拒绝原因
   * @param rejectReasonType 拒绝原因枚举值
   * @see #getRejectReason()
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse reject(String afterSaleOrderId, String rejectReason, Integer rejectReasonType) throws WxErrorException;

  /**
   * 上传退款凭证
   *
   * @param afterSaleOrderId 售后单号
   * @param desc             退款凭证描述
   * @param certificates     退款凭证图片列表
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse uploadRefundEvidence(String afterSaleOrderId, String desc, List<String> certificates)
    throws WxErrorException;

  /**
   * 商家补充纠纷单留言
   *
   * @param complaintId 纠纷单号
   * @param content     留言内容，最多500字
   * @param mediaIds    图片media_id列表，所有留言总图片数量最多20张
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse addComplaintMaterial(String complaintId, String content, List<String> mediaIds)
    throws WxErrorException;

  /**
   * 商家举证
   *
   * @param complaintId 纠纷单号
   * @param content     举证内容，最多500字
   * @param mediaIds    图片media_id列表，所有留言总图片数量最多20张
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse addComplaintEvidence(String complaintId, String content, List<String> mediaIds)
    throws WxErrorException;

  /**
   * 获取纠纷单
   *
   * @param complaintId 纠纷单号
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  ComplaintOrderResponse getComplaint(String complaintId) throws WxErrorException;


  /**
   * 获取全量售后原因
   * 文档地址：https://developers.weixin.qq.com/doc/channels/API/aftersale/getaftersalereason.html
   *
   * @return 售后原因
   *
   * @throws WxErrorException 异常
   */
  AfterSaleReasonResponse getAllReason() throws WxErrorException;

  /**
   * 获取拒绝售后原因
   * 文档地址：https://developers.weixin.qq.com/doc/channels/API/aftersale/getrejectreason.html
   *
   * @return 拒绝售后原因
   *
   * @throws WxErrorException 异常
   */
  AfterSaleRejectReasonResponse getRejectReason() throws WxErrorException;

  /**
   * 换货发货
   * 文档地址：https://developers.weixin.qq.com/doc/store/shop/API/channels-shop-aftersale/api_acceptexchangereship.html
   *
   * @param afterSaleOrderId 售后单号
   * @param waybillId        快递单号
   * @param deliveryId       快递公司id
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse acceptExchangeReship(String afterSaleOrderId, String waybillId, String deliveryId) throws WxErrorException;

  /**
   * 换货拒绝发货
   * 文档地址：https://developers.weixin.qq.com/doc/store/shop/API/channels-shop-aftersale/api_rejectexchangereship.html
   *
   * @param afterSaleOrderId 售后单号
   * @param rejectReason 拒绝原因具体描述 ,可使用默认描述，也可以自定义描述
   * @param rejectReasonType 拒绝原因枚举值
   * @param rejectCertificates 退款凭证，可使用图片上传接口获取media_id（数据类型填0）
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse rejectExchangeReship(String afterSaleOrderId, String rejectReason, Integer rejectReasonType, List<String> rejectCertificates) throws WxErrorException;

  /**
   * 商家协商
   * 文档地址：https://developers.weixin.qq.com/doc/store/shop/API/channels-shop-aftersale/api_merchantupdateaftersale.html
   * @param param 参数
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse merchantUpdateAfterSale(AfterSaleMerchantUpdateParam param) throws WxErrorException;
}
