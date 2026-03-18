package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonObject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.CommonUploadParam;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpOaWeDocService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.doc.*;

import java.io.File;
import java.util.List;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.*;

/**
 * 企业微信文档接口实现类.
 *
 * @author Wang_Wong created on 2022-04-22
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
    return docShare(WxCpDocShareRequest.builder().docId(docId).build());
  }

  @Override
  public WxCpDocShare docShare(@NonNull WxCpDocShareRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_DOC_SHARE);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpDocShare.fromJson(responseContent);
  }

  @Override
  public WxCpDocAuthInfo docGetAuth(@NonNull String docId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_DOC_GET_AUTH);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("docid", docId);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpDocAuthInfo.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp docModifyJoinRule(@NonNull WxCpDocModifyJoinRuleRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_MOD_DOC_JOIN_RULE);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp docModifyMember(@NonNull WxCpDocModifyMemberRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_MOD_DOC_MEMBER);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp docModifySafetySetting(
    @NonNull WxCpDocModifySafetySettingRequest request
  ) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage()
      .getApiUrl(WEDOC_MOD_DOC_SAFETY_SETTING);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
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

  @Override
  public WxCpDocData docGetData(@NonNull WxCpDocGetDataRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_GET_DOC_DATA);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpDocData.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp docModify(@NonNull WxCpDocModifyRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_MOD_DOC);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpDocImageUploadResult docUploadImage(@NonNull File file) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_UPLOAD_DOC_IMAGE);
    String responseContent = this.cpService.upload(apiUrl, CommonUploadParam.fromFile("media", file));
    return WxCpDocImageUploadResult.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp docAddAdmin(@NonNull WxCpDocAdminRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_ADD_ADMIN);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp docDeleteAdmin(@NonNull WxCpDocAdminRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_DEL_ADMIN);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpDocAdminListResult docGetAdminList(@NonNull String docId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_GET_ADMIN_LIST);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("docid", docId);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpDocAdminListResult.fromJson(responseContent);
  }

  @Override
  public WxCpDocSmartSheetAuth smartSheetGetAuth(@NonNull WxCpDocSmartSheetAuthRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_SMARTSHEET_GET_SHEET_AUTH);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpDocSmartSheetAuth.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp smartSheetModifyAuth(@NonNull WxCpDocSmartSheetModifyAuthRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_SMARTSHEET_MOD_SHEET_AUTH);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpDocSmartSheetResult smartSheetGetSheet(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_SMARTSHEET_GET_SHEET);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpDocSmartSheetResult.fromJson(responseContent);
  }

  @Override
  public WxCpDocSmartSheetResult smartSheetAddSheet(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_SMARTSHEET_ADD_SHEET);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpDocSmartSheetResult.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp smartSheetDeleteSheet(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_SMARTSHEET_DELETE_SHEET);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp smartSheetUpdateSheet(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_SMARTSHEET_UPDATE_SHEET);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpDocSmartSheetResult smartSheetGetViews(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_SMARTSHEET_GET_VIEWS);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpDocSmartSheetResult.fromJson(responseContent);
  }

  @Override
  public WxCpDocSmartSheetResult smartSheetAddView(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_SMARTSHEET_ADD_VIEW);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpDocSmartSheetResult.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp smartSheetDeleteViews(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_SMARTSHEET_DELETE_VIEWS);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp smartSheetUpdateView(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_SMARTSHEET_UPDATE_VIEW);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpDocSmartSheetResult smartSheetGetFields(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_SMARTSHEET_GET_FIELDS);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpDocSmartSheetResult.fromJson(responseContent);
  }

  @Override
  public WxCpDocSmartSheetResult smartSheetAddFields(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_SMARTSHEET_ADD_FIELDS);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpDocSmartSheetResult.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp smartSheetDeleteFields(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_SMARTSHEET_DELETE_FIELDS);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp smartSheetUpdateFields(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_SMARTSHEET_UPDATE_FIELDS);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpDocSmartSheetResult smartSheetGetRecords(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_SMARTSHEET_GET_RECORDS);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpDocSmartSheetResult.fromJson(responseContent);
  }

  @Override
  public WxCpDocSmartSheetResult smartSheetAddRecords(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_SMARTSHEET_ADD_RECORDS);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpDocSmartSheetResult.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp smartSheetDeleteRecords(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_SMARTSHEET_DELETE_RECORDS);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp smartSheetUpdateRecords(@NonNull WxCpDocSmartSheetRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_SMARTSHEET_UPDATE_RECORDS);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpFormCreateResult formCreate(@NonNull WxCpFormCreateRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_CREATE_FORM);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpFormCreateResult.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp formModify(@NonNull WxCpFormModifyRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_MODIFY_FORM);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpFormInfoResult formInfo(@NonNull String formId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_GET_FORM_INFO);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("formid", formId);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpFormInfoResult.fromJson(responseContent);
  }

  @Override
  public WxCpFormStatisticResult formStatistic(@NonNull List<WxCpFormStatisticRequest> requests) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_GET_FORM_STATISTIC);
    String responseContent = this.cpService.post(apiUrl, WxCpFormStatisticRequest.toJson(requests));
    return WxCpFormStatisticResult.fromJson(responseContent);
  }

  @Override
  public WxCpFormAnswer formAnswer(@NonNull WxCpFormAnswerRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WEDOC_GET_FORM_ANSWER);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpFormAnswer.fromJson(responseContent);
  }
}
