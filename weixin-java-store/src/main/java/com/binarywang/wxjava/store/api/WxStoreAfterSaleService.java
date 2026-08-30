package com.binarywang.wxjava.store.api;


import java.util.List;

import com.binarywang.wxjava.store.bean.after.*;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import com.binarywang.wxjava.store.bean.complaint.ComplaintOrderResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店 售后服务接口
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface WxStoreAfterSaleService {

  /**
   * 获取售后单列表
   *
   * @param beginCreateTime 订单创建启始时间 unix时间戳
   * @param endCreateTime   订单创建结束时间，end_create_time减去begin_create_time不得大于24小时
   * @param nextKey         翻页参数，从第二页开始传，来源于上一页的返回值
   * @return 售后单列表
   *
   * @throws WxErrorException 异常
   * @deprecated 使用 {@link WxStoreAfterSaleService#listIds(AfterSaleListParam)}
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
  WxStoreBaseResponse accept(String afterSaleOrderId, String addressId, Integer acceptType) throws WxErrorException;

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
  WxStoreBaseResponse reject(String afterSaleOrderId, String rejectReason, Integer rejectReasonType) throws WxErrorException;

  /**
   * 拒绝售后（支持拒绝凭证）
   * 文档地址 https://developers.weixin.qq.com/doc/channels/API/aftersale/rejectapply.html
   *
   * @param afterSaleOrderId   售后单号
   * @param rejectReason       拒绝原因
   * @param rejectReasonType   拒绝原因枚举值
   * @param rejectCertificates 拒绝凭证图片列表，可使用图片上传接口获取media_id
   * @see #getRejectReason()
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxStoreBaseResponse reject(String afterSaleOrderId, String rejectReason, Integer rejectReasonType,
    List<String> rejectCertificates) throws WxErrorException;

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
  WxStoreBaseResponse uploadRefundEvidence(String afterSaleOrderId, String desc, List<String> certificates)
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
  WxStoreBaseResponse addComplaintMaterial(String complaintId, String content, List<String> mediaIds)
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
  WxStoreBaseResponse addComplaintEvidence(String complaintId, String content, List<String> mediaIds)
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
  WxStoreBaseResponse acceptExchangeReship(String afterSaleOrderId, String waybillId, String deliveryId) throws WxErrorException;

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
  WxStoreBaseResponse rejectExchangeReship(String afterSaleOrderId, String rejectReason, Integer rejectReasonType, List<String> rejectCertificates) throws WxErrorException;

  /**
   * 商家协商
   * 文档地址：https://developers.weixin.qq.com/doc/store/shop/API/channels-shop-aftersale/api_merchantupdateaftersale.html
   * @param param 参数
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxStoreBaseResponse merchantUpdateAfterSale(AfterSaleMerchantUpdateParam param) throws WxErrorException;

  /**
   * 获取保障单列表。
   * 文档地址：https://developers.weixin.qq.com/doc/channels/API/channels-shop-aftersale/guarantee/api_searchguaranteeorder
   *
   * @param param 查询参数
   * @return 保障单列表
   * @throws WxErrorException 异常
   */
  default GuaranteeOrderListResponse listGuaranteeOrder(GuaranteeOrderListParam param) throws WxErrorException {
    throw new UnsupportedOperationException();
  }

  /**
   * 获取保障单详情。
   * 文档地址：https://developers.weixin.qq.com/doc/channels/API/channels-shop-aftersale/guarantee/api_getguaranteeorder
   *
   * @param guaranteeOrderId 保障单号
   * @return 保障单详情
   * @throws WxErrorException 异常
   */
  default GuaranteeOrderInfoResponse getGuaranteeOrder(String guaranteeOrderId) throws WxErrorException {
    throw new UnsupportedOperationException();
  }

  /**
   * 同意保障单申请。
   * 文档地址：https://developers.weixin.qq.com/doc/channels/API/channels-shop-aftersale/guarantee/api_merchantacceptguarantee
   *
   * @param guaranteeOrderId 保障单号
   * @return 响应结果
   * @throws WxErrorException 异常
   */
  default WxStoreBaseResponse acceptGuarantee(String guaranteeOrderId) throws WxErrorException {
    throw new UnsupportedOperationException();
  }

  /**
   * 商家协商保障单。
   * 文档地址：https://developers.weixin.qq.com/doc/channels/API/channels-shop-aftersale/guarantee/api_merchantmodifyguarantee
   *
   * @param request 协商参数
   * @return 响应结果
   * @throws WxErrorException 异常
   */
  default WxStoreBaseResponse modifyGuarantee(GuaranteeModifyRequest request) throws WxErrorException {
    throw new UnsupportedOperationException();
  }

  /**
   * 商家举证保障单。
   * 文档地址：https://developers.weixin.qq.com/doc/channels/API/channels-shop-aftersale/guarantee/api_merchantproofguarantee
   *
   * @param request 举证参数
   * @return 响应结果
   * @throws WxErrorException 异常
   */
  default WxStoreBaseResponse proofGuarantee(GuaranteeProofRequest request) throws WxErrorException {
    throw new UnsupportedOperationException();
  }

  /**
   * 拒绝保障单申请。
   * 文档地址：https://developers.weixin.qq.com/doc/channels/API/channels-shop-aftersale/guarantee/api_merchantrefuseguarantee
   *
   * @param request 拒绝参数
   * @return 响应结果
   * @throws WxErrorException 异常
   */
  default WxStoreBaseResponse refuseGuarantee(GuaranteeRefuseRequest request) throws WxErrorException {
    throw new UnsupportedOperationException();
  }
}
