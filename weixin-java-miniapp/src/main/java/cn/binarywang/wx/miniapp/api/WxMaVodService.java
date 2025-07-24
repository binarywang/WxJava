package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.vod.*;
import me.chanjar.weixin.common.error.WxErrorException;

import java.io.File;
import java.util.List;

/**
 * 小程序短剧管理服务接口。
 * 提供短剧视频上传、管理、审核、播放等相关功能。
 * 文档：https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/vod.html
 *
 */
public interface WxMaVodService {

  /**
   * 获取媒体列表。
   * 分页获取已上传的媒体文件列表。
   *
   * @param request          获取媒体列表请求对象
   * @return                 媒体信息列表
   * @throws WxErrorException 获取失败时抛出
   */
  List<WxMaVodMediaInfo> listMedia(WxMaVodListMediaRequest request) throws WxErrorException;

  /**
   * 获取剧集列表。
   * 分页获取已创建的剧集列表。
   *
   * @param request          获取剧集列表请求对象
   * @return                 剧集信息列表
   * @throws WxErrorException 获取失败时抛出
   */
  List<WxMaVodDramaInfo> listDrama(WxMaVodListDramaRequest request) throws WxErrorException;

  /**
   * 获取媒体播放链接。
   * 获取指定媒体文件的播放地址和相关信息。
   *
   * @param request          获取媒体播放链接请求对象
   * @return                 媒体播放信息对象
   * @throws WxErrorException 获取失败时抛出
   */
  WxMaVodMediaPlaybackInfo getMediaLink(WxMaVodGetMediaLinkRequest request) throws WxErrorException;

  /**
   * 获取媒体详情。
   * 获取指定媒体文件的详细信息。
   *
   * @param request          获取媒体详情请求对象
   * @return                 媒体信息对象
   * @throws WxErrorException 获取失败时抛出
   */
  WxMaVodMediaInfo getMedia(WxMaVodGetMediaRequest request) throws WxErrorException;

  /**
   * 删除媒体文件。
   * 删除指定的媒体文件，删除后无法恢复。
   *
   * @param request          删除媒体请求对象
   * @return                 删除是否成功
   * @throws WxErrorException 删除失败时抛出
   */
  boolean deleteMedia(WxMaVodDeleteMediaRequest request) throws WxErrorException;

  /**
   * 获取剧集详情。
   * 获取指定剧集的详细信息。
   *
   * @param request          获取剧集详情请求对象
   * @return                 剧集信息对象
   * @throws WxErrorException 获取失败时抛出
   */
  WxMaVodDramaInfo getDrama(WxMaVodGetDramaRequest request) throws WxErrorException;

  /**
   * 审核剧集。
   * 提交剧集进行内容审核。
   *
   * @param request          审核剧集请求对象
   * @return                 审核任务ID
   * @throws WxErrorException 审核提交失败时抛出
   */
  Integer auditDrama(WxMaVodAuditDramaRequest request) throws WxErrorException;

  /**
   * 获取CDN用量数据。
   * 查询指定时间段内的CDN流量使用情况。
   *
   * @param request          获取CDN用量请求对象
   * @return                 CDN用量数据响应对象
   * @throws WxErrorException 获取失败时抛出
   */
  WxMaVodGetCdnUsageResponse getCdnUsageData(WxMaVodGetCdnUsageRequest request) throws WxErrorException;

  /**
   * 获取CDN日志。
   * 获取指定时间段内的CDN访问日志。
   *
   * @param request          获取CDN日志请求对象
   * @return                 CDN日志响应对象
   * @throws WxErrorException 获取失败时抛出
   */
  WxMaVodGetCdnLogResponse getCdnLogs(WxMaVodGetCdnLogRequest request) throws WxErrorException;

  /**
   * 拉取上传。
   * 通过URL拉取视频文件到平台进行上传。
   *
   * @param request          拉取上传请求对象
   * @return                 拉取上传响应对象
   * @throws WxErrorException 拉取失败时抛出
   */
  WxMaVodPullUploadResponse pullUpload(WxMaVodPullUploadRequest request) throws WxErrorException;

  /**
   * 获取任务状态。
   * 查询异步任务的执行状态和结果。
   *
   * @param request          获取任务状态请求对象
   * @return                 任务状态响应对象
   * @throws WxErrorException 获取失败时抛出
   */
  WxMaVodGetTaskResponse getTask(WxMaVodGetTaskRequest request) throws WxErrorException;

  /**
   * 单文件上传（简化版）。
   * 直接上传单个视频文件到平台。
   *
   * @param file             要上传的文件
   * @param mediaName        媒体文件名称
   * @param mediaType        媒体文件类型
   * @return                 单文件上传结果
   * @throws WxErrorException 上传失败时抛出
   */
  WxMaVodSingleFileUploadResult uploadSingleFile(File file, String mediaName, String mediaType) throws WxErrorException;

  /**
   * 单文件上传（完整版）。
   * 上传视频文件和封面图片到平台。
   *
   * @param file             要上传的视频文件
   * @param mediaName        媒体文件名称
   * @param mediaType        媒体文件类型
   * @param coverType        封面图片类型
   * @param coverData        封面图片文件
   * @param sourceContext    来源上下文信息
   * @return                 单文件上传结果
   * @throws WxErrorException 上传失败时抛出
   */
  WxMaVodSingleFileUploadResult uploadSingleFile(File file, String mediaName, String mediaType, String coverType, File coverData, String sourceContext) throws WxErrorException;

  /**
   * 申请上传。
   * 申请分片上传的上传凭证和上传地址。
   *
   * @param request          申请上传请求对象
   * @return                 申请上传响应对象
   * @throws WxErrorException 申请失败时抛出
   */
  WxMaVodApplyUploadResponse applyUpload(WxMaVodApplyUploadRequest request) throws WxErrorException;

  /**
   * 确认上传。
   * 确认分片上传完成，合并所有分片文件。
   *
   * @param request          确认上传请求对象
   * @return                 确认上传响应对象
   * @throws WxErrorException 确认失败时抛出
   */
  WxMaVodCommitUploadResponse commitUpload(WxMaVodCommitUploadRequest request) throws WxErrorException;

  /**
   * 上传分片。
   * 上传文件的一个分片。
   *
   * @param file             分片文件
   * @param uploadId         上传ID
   * @param partNumber       分片编号
   * @param resourceType     资源类型
   * @return                 分片上传结果
   * @throws WxErrorException 上传失败时抛出
   */
  WxMaVodUploadPartResult uploadPart(File file, String uploadId, Integer partNumber, Integer resourceType) throws WxErrorException;
}
