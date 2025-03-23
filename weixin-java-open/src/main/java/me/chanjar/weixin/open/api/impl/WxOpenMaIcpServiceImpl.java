package me.chanjar.weixin.open.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import me.chanjar.weixin.open.bean.CommonUploadMultiParam;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.executor.CommonUploadMultiRequestExecutor;
import me.chanjar.weixin.common.util.http.BaseMediaDownloadRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.open.api.WxOpenMaIcpService;
import me.chanjar.weixin.open.bean.icp.*;
import me.chanjar.weixin.open.bean.result.WxOpenResult;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author xzh
 * @Description
 * @createTime 2024/08/14 14:48
 */
public class WxOpenMaIcpServiceImpl implements WxOpenMaIcpService {

  private final WxMaService wxMaService;

  public WxOpenMaIcpServiceImpl(WxMaService wxMaService) {
    this.wxMaService = wxMaService;
  }

  /**
   * 查询人脸核身任务状态
   *
   * @param taskId 任务id
   * @return 人脸核身任务的状态和结果
   * @throws WxErrorException e
   */
  @Override
  public WxOpenIcpVerifyTaskResult queryIcpVerifyTask(String taskId) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("task_id", taskId);
    String response = wxMaService.post(QUERY_ICP_VERIFY_TASK, params);
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenIcpVerifyTaskResult.class);
  }

  /**
   * 发起小程序管理员人脸核身
   *
   * @return 人脸核验任务结果
   * @throws WxErrorException e
   */
  @Override
  public WxOpenIcpCreateIcpVerifyTaskResult createIcpVerifyTask() throws WxErrorException {
    return createIcpVerifyTask(false);
  }

  /**
   * 发起小程序管理员人脸核身
   *
   * @param alongWithAuth 小程序认证及备案二合一场景，填 true，否则为小程序备案场景。默认值为 false。
   * @return 人脸核验任务结果
   * @throws WxErrorException e
   */
  @Override
  public WxOpenIcpCreateIcpVerifyTaskResult createIcpVerifyTask(boolean alongWithAuth) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("along_with_auth", alongWithAuth);
    String response = wxMaService.post(CREATE_ICP_VERIFY_TASK, params);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenIcpCreateIcpVerifyTaskResult.class);
  }

  /**
   * 上传小程序备案媒体材料
   *
   * @param param 备案媒体材料
   * @return 备案媒体材料结果
   * @throws WxErrorException e
   */
  @Override
  public WxOpenUploadIcpMediaResult uploadIcpMedia(WxOpenUploadIcpMediaParam param) throws WxErrorException {
    RequestExecutor<String, CommonUploadMultiParam> executor = CommonUploadMultiRequestExecutor.create(wxMaService.getRequestHttp());
    String response = wxMaService.execute(executor, UPLOAD_ICP_MEDIA, param.toCommonUploadMultiParam());
    return WxMaGsonBuilder.create().fromJson(response, WxOpenUploadIcpMediaResult.class);
  }

  /**
   * 撤回小程序备案申请
   *
   * @return r
   * @throws WxErrorException e
   */
  @Override
  public WxOpenResult cancelApplyIcpFiling() throws WxErrorException {
    String response = wxMaService.post(CANCEL_APPLY_ICP_FILING, "");
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  /**
   * 申请小程序备案
   *
   * @param param 参数
   * @return r
   * @throws WxErrorException e
   */
  @Override
  public WxOpenApplyIcpFilingResult applyIcpFiling(WxOpenApplyIcpFilingParam param) throws WxErrorException {
    String response = wxMaService.post(APPLY_ICP_FILING, param);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenApplyIcpFilingResult.class);
  }

  /**
   * 注销小程序备案
   *
   * @param cancelType 注销类型：1 -- 注销主体, 2 -- 注销小程序, 3 -- 注销微信小程序
   * @return r
   * @throws WxErrorException e
   */
  @Override
  public WxOpenResult cancelIcpFiling(Integer cancelType) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("cancel_type", cancelType);
    String response = wxMaService.post(CANCEL_ICP_FILING, params);
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  /**
   * 获取小程序备案状态及驳回原因
   *
   * @return r
   * @throws WxErrorException e
   */
  @Override
  public WxOpenIcpEntranceInfoResult getIcpEntranceInfo() throws WxErrorException {
    String response = wxMaService.get(GET_ICP_ENTRANCE_INFO, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenIcpEntranceInfoResult.class);
  }

  /**
   * 获取小程序已备案详情
   *
   * @return 已备案详情
   * @throws WxErrorException e
   */
  @Override
  public WxOpenOnlineIcpOrderResult getOnlineIcpOrder() throws WxErrorException {
    String response = wxMaService.get(GET_ONLINE_ICP_ORDER, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenOnlineIcpOrderResult.class);
  }

  /**
   * 获取小程序服务内容类型
   *
   * @return 小程序服务内容类型定义
   * @throws WxErrorException e
   */
  @Override
  public WxOpenQueryIcpServiceContentTypesResult queryIcpServiceContentTypes() throws WxErrorException {
    String response = wxMaService.get(QUERY_ICP_SERVICE_CONTENT_TYPES, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenQueryIcpServiceContentTypesResult.class);
  }

  /**
   * 获取证件类型
   *
   * @return 证件类型定义
   * @throws WxErrorException e
   */
  @Override
  public WxOpenQueryIcpCertificateTypeResult queryIcpCertificateTypes() throws WxErrorException {
    String response = wxMaService.get(QUERY_ICP_CERTIFICATE_TYPES, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenQueryIcpCertificateTypeResult.class);
  }

  /**
   * 获取区域信息
   *
   * @return 省市区的区域信息
   * @throws WxErrorException e
   */
  @Override
  public WxOpenQueryIcpDistrictCodeResult queryIcpDistrictCode() throws WxErrorException {
    String response = wxMaService.get(QUERY_ICP_DISTRICT_CODE, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenQueryIcpDistrictCodeResult.class);
  }

  /**
   * 获取前置审批项类型
   *
   * @return 小程序备案前置审批项类型定义
   * @throws WxErrorException e
   */
  @Override
  public WxOpenQueryIcpNrlxTypesResult queryIcpNrlxTypes() throws WxErrorException {
    String response = wxMaService.get(QUERY_ICP_NRLX_TYPES, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenQueryIcpNrlxTypesResult.class);
  }

  /**
   * 获取单位性质
   *
   * @return 单位性质定义
   * @throws WxErrorException e
   */
  @Override
  public WxOpenQueryIcpSubjectTypeResult queryIcpSubjectTypes() throws WxErrorException {
    String response = wxMaService.get(QUERY_ICP_SUBJECT_TYPES, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenQueryIcpSubjectTypeResult.class);
  }

  /**
   * 获取小程序备案媒体材料
   * @param mediaId 上传小程序备案媒体材料接口返回的 media_id，示例值：4ahCGpd3CYkE6RpkNkUR5czt3LvG8xDnDdKAz6bBKttSfM8p4k5Rj6823HXugPwQBurgMezyib7
   * @return 所上传的图片或视频媒体材料
   * @throws WxErrorException e
   */
  @Override
  public File getIcpMedia(String mediaId) throws WxErrorException {
    try {
      RequestExecutor<File, String> executor = BaseMediaDownloadRequestExecutor
        .create(this.wxMaService.getRequestHttp(), Files.createTempDirectory("wxma").toFile());
      return this.wxMaService.execute(executor, GET_ICP_MEDIA, "media_id=" + mediaId);
    } catch (IOException e) {
      throw new WxErrorException(WxError.builder().errorMsg(e.getMessage()).build(), e);
    }
  }

  /**
   * 申请小程序认证及备案
   *
   * @param param 参数
   * @return r
   * @throws WxErrorException e
   */
  @Override
  public WxOpenSubmitAuthAndIcpResult submitAuthAndIcp(WxOpenSubmitAuthAndIcpParam param) throws WxErrorException {
    String response = wxMaService.post(SUBMIT_AUTH_AND_ICP, param);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenSubmitAuthAndIcpResult.class);
  }

  /**
   * 查询小程序认证及备案进度
   *
   * @param procedureId 小程序认证及备案任务流程id
   * @return r
   * @throws WxErrorException e
   */
  @Override
  public WxOpenQueryAuthAndIcpResult queryAuthAndIcp(String procedureId) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("procedure_id", procedureId);
    String response = wxMaService.post(QUERY_AUTH_AND_ICP, params);
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenQueryAuthAndIcpResult.class);
  }
}
