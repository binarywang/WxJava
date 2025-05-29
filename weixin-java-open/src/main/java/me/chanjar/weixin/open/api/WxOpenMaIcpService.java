package me.chanjar.weixin.open.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.bean.icp.*;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

import java.io.File;

/**
 * @author xzh
 * @Description 小程序备案
 * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/record/queryIcpVerifyTask.html
 * @createTime 2024/08/14 10:52
 */
public interface WxOpenMaIcpService {
  /**
   * 查询人脸核身任务状态
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/record/queryIcpVerifyTask.html
   */
  String QUERY_ICP_VERIFY_TASK = "https://api.weixin.qq.com/wxa/icp/query_icp_verifytask";

  /**
   * 发起小程序管理员人脸核身
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/record/createIcpVerifyTask.html
   */
  String CREATE_ICP_VERIFY_TASK = "https://api.weixin.qq.com/wxa/icp/create_icp_verifytask";

  /**
   * 上传小程序备案媒体材料
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/record/uploadIcpMedia.html
   */
  String UPLOAD_ICP_MEDIA = "https://api.weixin.qq.com/wxa/icp/upload_icp_media";

  /**
   * 撤回小程序备案申请
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/record/cancelApplyIcpFiling.html
   */
  String CANCEL_APPLY_ICP_FILING = "https://api.weixin.qq.com/wxa/icp/cancel_apply_icp_filing";

  /**
   * 申请小程序备案
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/record/applyIcpFiling.html
   */
  String APPLY_ICP_FILING = "https://api.weixin.qq.com/wxa/icp/apply_icp_filing";

  /**
   * 注销小程序备案
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/record/cancelIcpfiling.html
   */
  String CANCEL_ICP_FILING = "https://api.weixin.qq.com/wxa/icp/cancel_icp_filing";

  /**
   * 获取小程序备案状态及驳回原因
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/record/getIcpEntranceInfo.html
   */
  String GET_ICP_ENTRANCE_INFO = "https://api.weixin.qq.com/wxa/icp/get_icp_entrance_info";

  /**
   * 获取小程序已备案详情
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/record/getOnlineIcpOrder.html
   */
  String GET_ONLINE_ICP_ORDER = "https://api.weixin.qq.com/wxa/icp/get_online_icp_order";

  /**
   * 获取小程序服务内容类型
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/record/queryIcpServiceContentTypes.html
   */
  String QUERY_ICP_SERVICE_CONTENT_TYPES = "https://api.weixin.qq.com/wxa/icp/query_icp_service_content_types";

  /**
   * 获取证件类型
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/record/queryIcpCertificateTypes.html
   */
  String QUERY_ICP_CERTIFICATE_TYPES = "https://api.weixin.qq.com/wxa/icp/query_icp_certificate_types";

  /**
   * 获取区域信息
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/record/queryIcpDistrictCode.html
   */
  String QUERY_ICP_DISTRICT_CODE = "https://api.weixin.qq.com/wxa/icp/query_icp_district_code";

  /**
   * 获取前置审批项类型
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/record/queryIcpNrlxTypes.html
   */
  String QUERY_ICP_NRLX_TYPES = "https://api.weixin.qq.com/wxa/icp/query_icp_nrlx_types";

  /**
   * 获取单位性质
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/record/queryIcpSubjectTypes.html
   */
  String QUERY_ICP_SUBJECT_TYPES = "https://api.weixin.qq.com/wxa/icp/query_icp_subject_types";

  /**
   * 获取小程序备案媒体材料
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/record/getIcpMedia.html
   */
  String GET_ICP_MEDIA = "https://api.weixin.qq.com/wxa/icp/get_icp_media";

  /**
   * 申请小程序认证及备案
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/wxverifyicp/submitAuthAndIcp.html
   */
  String SUBMIT_AUTH_AND_ICP = "https://api.weixin.qq.com/wxa/sec/submit_auth_and_icp";

  /**
   * 查询小程序认证及备案进度
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/wxverifyicp/queryAuthAndIcp.html
   */
  String QUERY_AUTH_AND_ICP = "https://api.weixin.qq.com/wxa/sec/query_auth_and_icp";


  /**
   * 查询人脸核身任务状态
   *
   * @param taskId 任务id
   * @return 人脸核身任务的状态和结果
   * @throws WxErrorException e
   */
  WxOpenIcpVerifyTaskResult queryIcpVerifyTask(String taskId) throws WxErrorException;

  /**
   * 发起小程序管理员人脸核身
   *
   * @return 人脸核验任务结果
   * @throws WxErrorException e
   */
  WxOpenIcpCreateIcpVerifyTaskResult createIcpVerifyTask() throws WxErrorException;

  /**
   * 发起小程序管理员人脸核身
   *
   * @param alongWithAuth 小程序认证及备案二合一场景，填 true，否则为小程序备案场景。默认值为 false。
   * @return 人脸核验任务结果
   * @throws WxErrorException e
   */
  WxOpenIcpCreateIcpVerifyTaskResult createIcpVerifyTask(boolean alongWithAuth) throws WxErrorException;

  /**
   * 上传小程序备案媒体材料
   *
   * @param param 备案媒体材料
   * @return 备案媒体材料结果
   * @throws WxErrorException e
   */
  WxOpenUploadIcpMediaResult uploadIcpMedia(WxOpenUploadIcpMediaParam param) throws WxErrorException;

  /**
   * 撤回小程序备案申请
   *
   * @return r
   * @throws WxErrorException e
   */
  WxOpenResult cancelApplyIcpFiling() throws WxErrorException;

  /**
   * 申请小程序备案
   *
   * @param param 参数
   * @return r
   * @throws WxErrorException e
   */
  WxOpenApplyIcpFilingResult applyIcpFiling(WxOpenApplyIcpFilingParam param) throws WxErrorException;

  /**
   * 注销小程序备案
   * @param cancelType 注销类型：1 -- 注销主体, 2 -- 注销小程序, 3 -- 注销微信小程序
   * @return r
   * @throws WxErrorException e
   */
  WxOpenResult cancelIcpFiling(Integer cancelType) throws WxErrorException;

  /**
   * 获取小程序备案状态及驳回原因
   * @return r
   * @throws WxErrorException e
   */
  WxOpenIcpEntranceInfoResult getIcpEntranceInfo() throws WxErrorException;

  /**
   * 获取小程序已备案详情
   * @return 已备案详情
   * @throws WxErrorException e
   */
  WxOpenOnlineIcpOrderResult getOnlineIcpOrder() throws WxErrorException;

  /**
   * 获取小程序服务内容类型
   * @return 小程序服务内容类型定义
   * @throws WxErrorException e
   */
  WxOpenQueryIcpServiceContentTypesResult queryIcpServiceContentTypes() throws WxErrorException;

  /**
   * 获取证件类型
   * @return 证件类型定义
   * @throws WxErrorException e
   */
  WxOpenQueryIcpCertificateTypeResult queryIcpCertificateTypes() throws WxErrorException;

  /**
   * 获取区域信息
   * @return 省市区的区域信息
   * @throws WxErrorException e
   */
  WxOpenQueryIcpDistrictCodeResult queryIcpDistrictCode() throws WxErrorException;

  /**
   * 获取前置审批项类型
   * @return 小程序备案前置审批项类型定义
   * @throws WxErrorException e
   */
  WxOpenQueryIcpNrlxTypesResult queryIcpNrlxTypes() throws WxErrorException;

  /**
   * 获取单位性质
   * @return 单位性质定义
   * @throws WxErrorException e
   */
  WxOpenQueryIcpSubjectTypeResult queryIcpSubjectTypes() throws WxErrorException;

  /**
   * 获取小程序备案媒体材料
   * @param mediaId 上传小程序备案媒体材料接口返回的 media_id，示例值：4ahCGpd3CYkE6RpkNkUR5czt3LvG8xDnDdKAz6bBKttSfM8p4k5Rj6823HXugPwQBurgMezyib7
   * @return 所上传的图片或视频媒体材料
   * @throws WxErrorException e
   */
  File getIcpMedia(String mediaId) throws WxErrorException;

  /**
   * 申请小程序认证及备案
   *
   * @param param 参数
   * @return r
   * @throws WxErrorException e
   */
  WxOpenSubmitAuthAndIcpResult submitAuthAndIcp(WxOpenSubmitAuthAndIcpParam param) throws WxErrorException;

  /**
   * 查询小程序认证及备案进度
   * @param procedureId 小程序认证及备案任务流程id
   * @return r
   * @throws WxErrorException e
   */
  WxOpenQueryAuthAndIcpResult queryAuthAndIcp(String procedureId) throws WxErrorException;

}
