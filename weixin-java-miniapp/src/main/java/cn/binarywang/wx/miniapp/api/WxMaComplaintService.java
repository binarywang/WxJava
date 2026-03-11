package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.complaint.*;
import me.chanjar.weixin.common.error.WxErrorException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 小程序交易投诉接口
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on 2025-01-01
 */
public interface WxMaComplaintService {

  /**
   * <pre>
   * 查询投诉单列表API
   * 商户可通过调用此接口，查询指定时间段的所有用户投诉信息，以分页输出查询结果。
   * 文档详见: https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/guarantee/complaint.html
   * </pre>
   *
   * @param request {@link WxMaComplaintRequest} 查询投诉单列表请求数据
   * @return {@link WxMaComplaintResult} 微信返回的投诉单列表
   * @throws WxErrorException the wx error exception
   */
  WxMaComplaintResult queryComplaints(WxMaComplaintRequest request) throws WxErrorException;

  /**
   * <pre>
   * 查询投诉单详情API
   * 商户可通过调用此接口，查询指定投诉单的用户投诉详情，包含投诉内容、投诉关联订单、投诉人联系方式等信息，方便商户处理投诉。
   * 文档详见: https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/guarantee/complaint.html
   * </pre>
   *
   * @param request {@link WxMaComplaintDetailRequest} 投诉单详情请求数据
   * @return {@link WxMaComplaintDetailResult} 微信返回的投诉单详情
   * @throws WxErrorException the wx error exception
   */
  WxMaComplaintDetailResult getComplaint(WxMaComplaintDetailRequest request) throws WxErrorException;

  /**
   * <pre>
   * 查询投诉协商历史API
   * 商户可通过调用此接口，查询指定投诉的用户商户协商历史，以分页输出查询结果，方便商户根据处理历史来制定后续处理方案。
   * 文档详见: https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/guarantee/complaint.html
   * </pre>
   *
   * @param request {@link WxMaNegotiationHistoryRequest} 请求数据
   * @return {@link WxMaNegotiationHistoryResult} 微信返回结果
   * @throws WxErrorException the wx error exception
   */
  WxMaNegotiationHistoryResult queryNegotiationHistorys(WxMaNegotiationHistoryRequest request) throws WxErrorException;

  /**
   * <pre>
   * 创建投诉通知回调地址API
   * 商户通过调用此接口创建投诉通知回调URL，当用户产生新投诉且投诉状态已变更时，微信会通过回调URL通知商户。
   * 文档详见: https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/guarantee/complaint.html
   * </pre>
   *
   * @param request {@link WxMaComplaintNotifyUrlRequest} 请求数据
   * @return {@link WxMaComplaintNotifyUrlResult} 微信返回结果
   * @throws WxErrorException the wx error exception
   */
  WxMaComplaintNotifyUrlResult addComplaintNotifyUrl(WxMaComplaintNotifyUrlRequest request) throws WxErrorException;

  /**
   * <pre>
   * 查询投诉通知回调地址API
   * 商户通过调用此接口查询投诉通知的回调URL。
   * 文档详见: https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/guarantee/complaint.html
   * </pre>
   *
   * @return {@link WxMaComplaintNotifyUrlResult} 微信返回结果
   * @throws WxErrorException the wx error exception
   */
  WxMaComplaintNotifyUrlResult getComplaintNotifyUrl() throws WxErrorException;

  /**
   * <pre>
   * 更新投诉通知回调地址API
   * 商户通过调用此接口更新投诉通知的回调URL。
   * 文档详见: https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/guarantee/complaint.html
   * </pre>
   *
   * @param request {@link WxMaComplaintNotifyUrlRequest} 请求数据
   * @return {@link WxMaComplaintNotifyUrlResult} 微信返回结果
   * @throws WxErrorException the wx error exception
   */
  WxMaComplaintNotifyUrlResult updateComplaintNotifyUrl(WxMaComplaintNotifyUrlRequest request) throws WxErrorException;

  /**
   * <pre>
   * 删除投诉通知回调地址API
   * 当商户不再需要推送通知时，可通过调用此接口删除投诉通知的回调URL，取消通知回调。
   * 文档详见: https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/guarantee/complaint.html
   * </pre>
   *
   * @throws WxErrorException the wx error exception
   */
  void deleteComplaintNotifyUrl() throws WxErrorException;

  /**
   * <pre>
   * 提交回复API
   * 商户可通过调用此接口，提交回复内容。其中上传图片凭证需首先调用商户上传反馈图片接口，得到图片id，再将id填入请求。
   * 回复可配置文字链，传入跳转链接文案和跳转链接字段，用户点击即可跳转对应页面
   * 文档详见: https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/guarantee/complaint.html
   * </pre>
   *
   * @param request {@link WxMaResponseRequest} 请求数据
   * @throws WxErrorException the wx error exception
   */
  void submitResponse(WxMaResponseRequest request) throws WxErrorException;

  /**
   * <pre>
   * 反馈处理完成API
   * 商户可通过调用此接口，反馈投诉单已处理完成。
   * 文档详见: https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/guarantee/complaint.html
   * </pre>
   *
   * @param request {@link WxMaCompleteRequest} 请求数据
   * @throws WxErrorException the wx error exception
   */
  void complete(WxMaCompleteRequest request) throws WxErrorException;

  /**
   * <pre>
   * 商户上传反馈图片API
   * 商户可通过调用此接口上传反馈图片凭证，上传成功后可在提交回复时使用。
   * 文档详见: https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/guarantee/complaint.html
   * </pre>
   *
   * @param imageFile 需要上传的图片文件
   * @return String 微信返回的媒体文件标识Id
   * @throws WxErrorException the wx error exception
   * @throws IOException      IO异常
   */
  String uploadResponseImage(File imageFile) throws WxErrorException, IOException;

  /**
   * <pre>
   * 商户上传反馈图片API
   * 商户可通过调用此接口上传反馈图片凭证，上传成功后可在提交回复时使用。
   * 文档详见: https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/guarantee/complaint.html
   * </pre>
   *
   * @param inputStream 需要上传的图片文件流
   * @param fileName    需要上传的图片文件名
   * @return String 微信返回的媒体文件标识Id
   * @throws WxErrorException the wx error exception
   * @throws IOException      IO异常
   */
  String uploadResponseImage(InputStream inputStream, String fileName) throws WxErrorException, IOException;
}