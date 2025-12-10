package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaComplaintService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.complaint.*;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.bean.CommonUploadParam;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Complaint.*;

/**
 * 小程序交易投诉接口实现
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on 2025-01-01
 */
@RequiredArgsConstructor
public class WxMaComplaintServiceImpl implements WxMaComplaintService {
  private static final String JSON_CONTENT_TYPE = "application/json";
  private final WxMaService wxMaService;

  @Override
  public WxMaComplaintResult queryComplaints(WxMaComplaintRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(QUERY_COMPLAINTS_URL, request.toJson());
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaComplaintResult.class);
  }

  @Override
  public WxMaComplaintDetailResult getComplaint(WxMaComplaintDetailRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(GET_COMPLAINT_URL, request.toJson());
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaComplaintDetailResult.class);
  }

  @Override
  public WxMaNegotiationHistoryResult queryNegotiationHistorys(WxMaNegotiationHistoryRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(QUERY_NEGOTIATION_HISTORY_URL, request.toJson());
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaNegotiationHistoryResult.class);
  }

  @Override
  public WxMaComplaintNotifyUrlResult addComplaintNotifyUrl(WxMaComplaintNotifyUrlRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(ADD_COMPLAINT_NOTIFY_URL, request.toJson());
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaComplaintNotifyUrlResult.class);
  }

  @Override
  public WxMaComplaintNotifyUrlResult getComplaintNotifyUrl() throws WxErrorException {
    String responseContent = this.wxMaService.get(GET_COMPLAINT_NOTIFY_URL, null);
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaComplaintNotifyUrlResult.class);
  }

  @Override
  public WxMaComplaintNotifyUrlResult updateComplaintNotifyUrl(WxMaComplaintNotifyUrlRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(UPDATE_COMPLAINT_NOTIFY_URL, request.toJson());
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaComplaintNotifyUrlResult.class);
  }

  @Override
  public void deleteComplaintNotifyUrl() throws WxErrorException {
    this.wxMaService.post(DELETE_COMPLAINT_NOTIFY_URL, "{}");
  }

  @Override
  public void submitResponse(WxMaResponseRequest request) throws WxErrorException {
    this.wxMaService.post(SUBMIT_RESPONSE_URL, request.toJson());
  }

  @Override
  public void complete(WxMaCompleteRequest request) throws WxErrorException {
    this.wxMaService.post(COMPLETE_COMPLAINT_URL, request.toJson());
  }

  @Override
  public String uploadResponseImage(File imageFile) throws WxErrorException, IOException {
    String result = this.wxMaService.upload(UPLOAD_RESPONSE_IMAGE_URL, 
      CommonUploadParam.fromFile("image", imageFile));
    JsonObject jsonResult = WxMaGsonBuilder.create().fromJson(result, JsonObject.class);
    return jsonResult.get("media_id").getAsString();
  }

  @Override
  public String uploadResponseImage(InputStream inputStream, String fileName) throws WxErrorException, IOException {
    try {
      return this.uploadResponseImage(FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), fileName));
    } catch (IOException e) {
      throw new WxErrorException(WxError.builder().errorMsg(e.getMessage()).build(), e);
    }
  }
}