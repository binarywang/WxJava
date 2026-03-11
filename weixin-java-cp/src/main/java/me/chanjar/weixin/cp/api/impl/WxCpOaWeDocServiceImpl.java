package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonObject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpOaWeDocService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.doc.*;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.*;

/**
 * 企业微信微盘接口实现类.
 *
 * @author Wang_Wong  created on  2022-04-22
 */
@Slf4j
@RequiredArgsConstructor
public class WxCpOaWeDocServiceImpl implements WxCpOaWeDocService {
  private final WxCpService cpService;

  @Override
  public WxCpDocCreateData docCreate(@NonNull WxCpDocCreateRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_CREATE_DOC);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpDocCreateData.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp docRename(@NonNull WxCpDocRenameRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_RENAME_DOC);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp docDelete(String docId, String formId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_DEL_DOC);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("docid", docId);
    jsonObject.addProperty("formid", formId);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpDocInfo docInfo(@NonNull String docId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_GET_DOC_BASE_INFO);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("docid", docId);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpDocInfo.fromJson(responseContent);
  }

  @Override
  public WxCpDocShare docShare(@NonNull String docId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_DOC_SHARE);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("docid", docId);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpDocShare.fromJson(responseContent);
  }

  @Override
  public WxCpDocSheetBatchUpdateResponse docBatchUpdate(@NonNull WxCpDocSheetBatchUpdateRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_SPREADSHEET_BATCH_UPDATE);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpDocSheetBatchUpdateResponse.fromJson(responseContent);
  }

  @Override
  public WxCpDocSheetProperties getSheetProperties(@NonNull String docId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_SPREADSHEET_GET_SHEET_PROPERTIES);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("docid", docId);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpDocSheetProperties.fromJson(responseContent);
  }

  @Override
  public WxCpDocSheetData getSheetRangeData(@NonNull WxCpDocSheetGetDataRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_SPREADSHEET_GET_SHEET_RANGE_DATA);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpDocSheetData.fromJson(responseContent);
  }
}
