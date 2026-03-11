package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.complaint.*;
import com.github.binarywang.wxpay.bean.media.ImageUploadResult;
import com.github.binarywang.wxpay.exception.WxPayException;

import javax.crypto.BadPaddingException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * <pre>
 * 微信支付 消费者投诉2.0 API.
 * Created by jmdhappy on 2022/3/19.
 * </pre>
 *
 * @author <a href="https://gitee.com/jeequan/jeepay">jmdhappy</a>
 */
public interface ComplaintService {

  /**
   * <pre>
   * 查询投诉单列表API
   * 商户可通过调用此接口，查询指定时间段的所有用户投诉信息，以分页输出查询结果。
   * 对于服务商、渠道商，可通过调用此接口，查询指定子商户号对应子商户的投诉信息，若不指定则查询所有子商户投诉信息。
   * 文档详见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter10_2_11.shtml">...</a>
   * </pre>
   *
   * @param request {@link ComplaintRequest} 查询投诉单列表请求数据
   * @return {@link ComplaintResult} 微信返回的投诉单列表
   * @throws WxPayException the wx pay exception
   * @throws BadPaddingException .
   */
  ComplaintResult queryComplaints(ComplaintRequest request) throws WxPayException, BadPaddingException;

  /**
   * <pre>
   * 查询投诉单详情API
   * 商户可通过调用此接口，查询指定投诉单的用户投诉详情，包含投诉内容、投诉关联订单、投诉人联系方式等信息，方便商户处理投诉。
   * 文档详见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter10_2_13.shtml">...</a>
   * </pre>
   *
   * @param request {@link ComplaintDetailRequest} 投诉单详情请求数据
   * @return {@link ComplaintDetailResult} 微信返回的投诉单详情
   * @throws WxPayException      the wx pay exception
   * @throws BadPaddingException .
   */
  ComplaintDetailResult getComplaint(ComplaintDetailRequest request) throws WxPayException, BadPaddingException;

  /**
   * <pre>
   * 查询投诉协商历史API
   * 商户可通过调用此接口，查询指定投诉的用户商户协商历史，以分页输出查询结果，方便商户根据处理历史来制定后续处理方案。
   * 文档详见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter10_2_12.shtml">...</a>
   * </pre>
   *
   * @param request {@link NegotiationHistoryRequest} 请求数据
   * @return {@link NegotiationHistoryResult} 微信返回结果
   * @throws WxPayException the wx pay exception
   */
  NegotiationHistoryResult queryNegotiationHistorys(NegotiationHistoryRequest request) throws WxPayException;

  /**
   * <pre>
   * 创建投诉通知回调地址API
   * 商户通过调用此接口创建投诉通知回调URL，当用户产生新投诉且投诉状态已变更时，微信支付会通过回 调URL通知商户。对于服务商、渠道商，会收到所有子商户的投诉信息推送。
   * 文档详见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter10_2_2.shtml">...</a>
   * </pre>
   *
   * @param request {@link ComplaintDetailRequest} 请求数据
   * @return {@link ComplaintNotifyUrlResult} 微信返回结果
   * @throws WxPayException the wx pay exception
   */
  ComplaintNotifyUrlResult addComplaintNotifyUrl(ComplaintNotifyUrlRequest request) throws WxPayException;

  /**
   * <pre>
   * 查询投诉通知回调地址API
   * 商户通过调用此接口查询投诉通知的回调URL。
   * 文档详见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter10_2_3.shtml">...</a>
   * </pre>
   *
   * @return {@link ComplaintNotifyUrlResult} 微信返回结果
   * @throws WxPayException the wx pay exception
   */
  ComplaintNotifyUrlResult getComplaintNotifyUrl() throws WxPayException;

  /**
   * <pre>
   * 更新投诉通知回调地址API
   * 商户通过调用此接口更新投诉通知的回调URL。
   * 文档详见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter10_2_4.shtml">...</a>
   * </pre>
   *
   * @param request {@link ComplaintDetailRequest} 请求数据
   * @return {@link ComplaintNotifyUrlResult} 微信返回结果
   * @throws WxPayException the wx pay exception
   */
  ComplaintNotifyUrlResult updateComplaintNotifyUrl(ComplaintNotifyUrlRequest request) throws WxPayException;

  /**
   * <pre>
   * 删除投诉通知回调地址API
   * 当商户不再需要推送通知时，可通过调用此接口删除投诉通知的回调URL，取消通知回调。
   * 文档详见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter10_2_5.shtml">...</a>
   * </pre>
   *
   * @throws WxPayException the wx pay exception
   */
  void deleteComplaintNotifyUrl() throws WxPayException;

  /**
   * <pre>
   * 提交回复API
   * 商户可通过调用此接口，提交回复内容。其中上传图片凭证需首先调用商户上传反馈图片接口，得到图片id，再将id填入请求。
   * 回复可配置文字链，传入跳转链接文案和跳转链接字段，用户点击即可跳转对应页面
   * 文档详见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter10_2_14.shtml">...</a>
   * </pre>
   *
   * @param request {@link ResponseRequest} 请求数据
   * @throws WxPayException the wx pay exception
   */
  void submitResponse(ResponseRequest request) throws WxPayException;

  /**
   * <pre>
   * 反馈处理完成API
   * 商户可通过调用此接口，反馈投诉单已处理完成。
   * 文档详见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter10_2_15.shtml">...</a>
   * </pre>
   *
   * @param request {@link CompleteRequest} 请求数据
   * @throws WxPayException the wx pay exception
   */
  void complete(CompleteRequest request) throws WxPayException;

  /**
   * <pre>
   * 更新退款审批结果API
   * 针对“申请退款单”，需要商户明确返回是否可退款的审批结果。
   * 若根据用户描述，核实可以退款，审批动作传入“APPROVE”，同意退款，并给出一个预计退款时间。传入“同意退款”后，需要额外调退款接口发起原路退款。退款到账后，投诉单的状态将自动扭转为“处理完成”。
   * 若根据用户描述，核实不能退款，审批动作传入“REJECT”，拒绝退款，并说明拒绝退款原因。驳回退款后，投诉单的状态将自动扭转为“处理完成”。
   * 文档详见: <a href="https://pay.wechatpay.cn/docs/merchant/apis/consumer-complaint/complaints/update-refund-progress.html">...</a>
   * </pre>
   *
   * @param request {@link UpdateRefundProgressRequest} 请求数据
   * @throws WxPayException the wx pay exception
   */
  void updateRefundProgress(UpdateRefundProgressRequest request) throws WxPayException;

  /**
   * <pre>
   * 商户上传反馈图片API
   * 文档详见: <a href="https://pay.weixin.qq.com/docs/merchant/apis/consumer-complaint/images/create-images.html">...</a>
   * 接口链接：https://api.mch.weixin.qq.com/v3/merchant-service/images/upload
   * </pre>
   *
   * @param imageFile 需要上传的图片文件
   * @return ImageUploadResult 微信返回的媒体文件标识Id。示例值：BB04A5DEEFEA18D4F2554C1EDD3B610B.bmp
   * @throws WxPayException the wx pay exception
   */
  ImageUploadResult uploadResponseImage(File imageFile) throws WxPayException, IOException;

  /**
   * <pre>
   * 商户上传反馈图片API
   * 文档详见: <a href="https://pay.weixin.qq.com/docs/merchant/apis/consumer-complaint/images/create-images.html">...</a>
   * 接口链接：https://api.mch.weixin.qq.com/v3/merchant-service/images/upload
   * </pre>
   *
   * @param inputStream 需要上传的图片文件流
   * @param fileName    需要上传的图片文件名
   * @return ImageUploadResult 微信返回的媒体文件标识Id。示例值：BB04A5DEEFEA18D4F2554C1EDD3B610B.bmp
   * @throws WxPayException the wx pay exception
   */
  ImageUploadResult uploadResponseImage(InputStream inputStream, String fileName) throws WxPayException, IOException;

}
