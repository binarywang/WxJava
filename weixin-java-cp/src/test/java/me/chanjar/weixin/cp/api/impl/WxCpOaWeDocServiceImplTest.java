package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.doc.*;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import org.mockito.ArgumentCaptor;
import org.testng.annotations.Test;

import java.io.File;

import static java.util.Collections.singletonList;
import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * WeDoc 接口实现测试.
 */
public class WxCpOaWeDocServiceImplTest {

  @Test
  public void testLegacyApisUseExpectedPaths() throws WxErrorException {
    WxCpService cpService = mock(WxCpService.class);
    WxCpConfigStorage configStorage = mock(WxCpConfigStorage.class);
    when(cpService.getWxCpConfigStorage()).thenReturn(configStorage);
    when(configStorage.getApiUrl(WEDOC_CREATE_DOC)).thenReturn("https://api.test/create_doc");
    when(configStorage.getApiUrl(WEDOC_RENAME_DOC)).thenReturn("https://api.test/rename_doc");
    when(configStorage.getApiUrl(WEDOC_DEL_DOC)).thenReturn("https://api.test/del_doc");
    when(configStorage.getApiUrl(WEDOC_GET_DOC_BASE_INFO)).thenReturn("https://api.test/get_doc_base_info");
    when(configStorage.getApiUrl(WEDOC_DOC_SHARE)).thenReturn("https://api.test/doc_share");
    when(configStorage.getApiUrl(WEDOC_DOC_GET_AUTH)).thenReturn("https://api.test/doc_get_auth");
    when(configStorage.getApiUrl(WEDOC_MOD_DOC_JOIN_RULE)).thenReturn("https://api.test/mod_doc_join_rule");
    when(configStorage.getApiUrl(WEDOC_MOD_DOC_MEMBER)).thenReturn("https://api.test/mod_doc_member");
    when(configStorage.getApiUrl(WEDOC_MOD_DOC_SAFETY_SETTING))
      .thenReturn("https://api.test/mod_doc_safty_setting");
    when(configStorage.getApiUrl(WEDOC_SPREADSHEET_BATCH_UPDATE)).thenReturn("https://api.test/spreadsheet/batch_update");
    when(configStorage.getApiUrl(WEDOC_SPREADSHEET_GET_SHEET_PROPERTIES)).thenReturn("https://api.test/spreadsheet/get_sheet_properties");
    when(configStorage.getApiUrl(WEDOC_SPREADSHEET_GET_SHEET_RANGE_DATA)).thenReturn("https://api.test/spreadsheet/get_sheet_range_data");
    when(configStorage.getApiUrl(WEDOC_CREATE_FORM)).thenReturn("https://api.test/create_collect");
    when(configStorage.getApiUrl(WEDOC_MODIFY_FORM)).thenReturn("https://api.test/modify_collect");
    when(configStorage.getApiUrl(WEDOC_GET_FORM_INFO)).thenReturn("https://api.test/get_form_info");
    when(configStorage.getApiUrl(WEDOC_GET_FORM_STATISTIC)).thenReturn("https://api.test/get_form_statistic");
    when(configStorage.getApiUrl(WEDOC_GET_FORM_ANSWER)).thenReturn("https://api.test/get_form_answer");

    when(cpService.post(eq("https://api.test/create_doc"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"url\":\"https://wedoc.test/doc/1\",\"docid\":\"doc1\"}");
    when(cpService.post(eq("https://api.test/rename_doc"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    when(cpService.post(eq("https://api.test/del_doc"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    when(cpService.post(eq("https://api.test/get_doc_base_info"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"doc_base_info\":{\"docid\":\"doc1\",\"doc_name\":\"日报\",\"doc_type\":3}}");
    when(cpService.post(eq("https://api.test/doc_share"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"share_url\":\"https://wedoc.test/share/1\"}");
    when(cpService.post(eq("https://api.test/doc_get_auth"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"access_rule\":{\"enable_corp_internal\":true,\"corp_internal_auth\":1}}");
    when(cpService.post(eq("https://api.test/mod_doc_join_rule"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    when(cpService.post(eq("https://api.test/mod_doc_member"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    when(cpService.post(eq("https://api.test/mod_doc_safty_setting"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    when(cpService.post(eq("https://api.test/spreadsheet/batch_update"), anyString()))
      .thenReturn("{\"add_sheet_response\":{\"properties\":{\"sheet_id\":\"sheet2\",\"title\":\"Sheet A\",\"row_count\":20,\"column_count\":5}},\"update_range_response\":{\"updated_cells\":2}}");
    when(cpService.post(eq("https://api.test/spreadsheet/get_sheet_properties"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"properties\":[{\"sheet_id\":\"sheet1\",\"title\":\"Sheet A\",\"row_count\":20,\"column_count\":5}]}");
    when(cpService.post(eq("https://api.test/spreadsheet/get_sheet_range_data"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"grid_data\":{\"start_row\":0,\"start_column\":0,\"rows\":[{\"values\":[{\"cell_value\":{\"text\":\"hello\"}}]}]}}");
    when(cpService.post(eq("https://api.test/create_collect"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"formid\":\"FORMID1\"}");
    when(cpService.post(eq("https://api.test/modify_collect"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    when(cpService.post(eq("https://api.test/get_form_info"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"form_info\":{\"formid\":\"FORMID1\",\"form_title\":\"日报\"}}");
    when(cpService.post(eq("https://api.test/get_form_statistic"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\","
        + "\"statistic_list\":[{\"repeated_id\":\"repeat-1\","
        + "\"fill_cnt\":3,\"submit_users\":"
        + "[{\"userid\":\"zhangsan\",\"answer_id\":1}]}]}");
    when(cpService.post(eq("https://api.test/get_form_answer"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"answer\":{\"answer_list\":[{\"answer_id\":1,\"userid\":\"zhangsan\",\"reply\":{\"items\":[{\"question_id\":1,\"text_reply\":\"ok\"}]}}]}}");

    WxCpOaWeDocServiceImpl service = new WxCpOaWeDocServiceImpl(cpService);

    WxCpDocCreateRequest createRequest = WxCpDocCreateRequest.builder()
      .spaceId("space1")
      .fatherId("father1")
      .docType(3)
      .docName("日报")
      .build();
    WxCpDocCreateData createData = service.docCreate(createRequest);
    assertThat(createData.getDocId()).isEqualTo("doc1");
    ArgumentCaptor<String> createBodyCaptor = ArgumentCaptor.forClass(String.class);
    verify(cpService).post(eq("https://api.test/create_doc"), createBodyCaptor.capture());
    assertThat(createBodyCaptor.getValue()).contains("\"spaceid\":\"space1\"");
    assertThat(createBodyCaptor.getValue()).contains("\"doc_type\":3");

    WxCpBaseResp renameResp = service.docRename(WxCpDocRenameRequest.builder()
      .docId("doc1")
      .newName("周报")
      .build());
    assertThat(renameResp.getErrcode()).isZero();
    ArgumentCaptor<String> renameBodyCaptor = ArgumentCaptor.forClass(String.class);
    verify(cpService).post(eq("https://api.test/rename_doc"), renameBodyCaptor.capture());
    assertThat(renameBodyCaptor.getValue()).contains("\"new_name\":\"周报\"");

    WxCpBaseResp deleteResp = service.docDelete("doc1", null);
    assertThat(deleteResp.getErrcode()).isZero();
    ArgumentCaptor<String> deleteBodyCaptor = ArgumentCaptor.forClass(String.class);
    verify(cpService).post(eq("https://api.test/del_doc"), deleteBodyCaptor.capture());
    assertThat(deleteBodyCaptor.getValue()).contains("\"docid\":\"doc1\"");

    WxCpDocInfo docInfo = service.docInfo("doc1");
    assertThat(docInfo.getDocBaseInfo().getDocName()).isEqualTo("日报");
    verify(cpService).post(eq("https://api.test/get_doc_base_info"), anyString());

    WxCpDocShare docShare = service.docShare(WxCpDocShareRequest.builder().formId("FORMID1").build());
    assertThat(docShare.getShareUrl()).isEqualTo("https://wedoc.test/share/1");
    ArgumentCaptor<String> docShareBodyCaptor = ArgumentCaptor.forClass(String.class);
    verify(cpService).post(eq("https://api.test/doc_share"), docShareBodyCaptor.capture());
    assertThat(docShareBodyCaptor.getValue()).contains("\"formid\":\"FORMID1\"");

    WxCpDocAuthInfo docAuthInfo = service.docGetAuth("doc1");
    assertThat(docAuthInfo.getAccessRule().getEnableCorpInternal()).isTrue();
    verify(cpService).post(eq("https://api.test/doc_get_auth"), anyString());

    WxCpBaseResp joinRuleResp = service.docModifyJoinRule(WxCpDocModifyJoinRuleRequest.builder()
      .docId("doc1")
      .enableCorpInternal(true)
      .corpInternalAuth(1)
      .build());
    assertThat(joinRuleResp.getErrcode()).isZero();
    verify(cpService).post(eq("https://api.test/mod_doc_join_rule"), anyString());

    WxCpDocAuthInfo.DocMember docMember = new WxCpDocAuthInfo.DocMember();
    docMember.setType(1);
    docMember.setUserId("zhangsan");
    docMember.setAuth(7);
    WxCpBaseResp modifyMemberResp = service.docModifyMember(WxCpDocModifyMemberRequest.builder()
      .docId("doc1")
      .updateFileMemberList(singletonList(docMember))
      .build());
    assertThat(modifyMemberResp.getErrcode()).isZero();
    verify(cpService).post(eq("https://api.test/mod_doc_member"), anyString());

    WxCpDocAuthInfo.Watermark watermark = new WxCpDocAuthInfo.Watermark();
    watermark.setMarginType(2);
    watermark.setShowText(true);
    watermark.setText("wm");
    WxCpBaseResp safetyResp = service.docModifySafetySetting(
      WxCpDocModifySafetySettingRequest.builder()
        .docId("doc1")
        .enableReadonlyCopy(true)
        .watermark(watermark)
        .build());
    assertThat(safetyResp.getErrcode()).isZero();
    verify(cpService).post(
      eq("https://api.test/mod_doc_safty_setting"), anyString());

    WxCpDocSheetBatchUpdateRequest.Request.AddSheetRequest addSheetRequest =
      new WxCpDocSheetBatchUpdateRequest.Request.AddSheetRequest();
    addSheetRequest.setTitle("Sheet A");
    addSheetRequest.setRowCount(20);
    addSheetRequest.setColumnCount(5);
    WxCpDocSheetBatchUpdateRequest.Request batchRequest = new WxCpDocSheetBatchUpdateRequest.Request();
    batchRequest.setAddSheetRequest(addSheetRequest);
    WxCpDocSheetBatchUpdateResponse batchUpdateResponse = service.docBatchUpdate(WxCpDocSheetBatchUpdateRequest.builder()
      .docId("doc1")
      .requests(singletonList(batchRequest))
      .build());
    assertThat(batchUpdateResponse.getAddSheetResponse().getProperties().getSheetId()).isEqualTo("sheet2");
    verify(cpService).post(eq("https://api.test/spreadsheet/batch_update"), anyString());

    WxCpDocSheetProperties sheetProperties = service.getSheetProperties("doc1");
    assertThat(sheetProperties.getProperties()).hasSize(1);
    assertThat(sheetProperties.getProperties().get(0).getSheetId()).isEqualTo("sheet1");
    ArgumentCaptor<String> sheetPropertiesBodyCaptor = ArgumentCaptor.forClass(String.class);
    verify(cpService).post(eq("https://api.test/spreadsheet/get_sheet_properties"), sheetPropertiesBodyCaptor.capture());
    assertThat(sheetPropertiesBodyCaptor.getValue()).isEqualTo("{\"docid\":\"doc1\"}");

    WxCpDocSheetData sheetData = service.getSheetRangeData(WxCpDocSheetGetDataRequest.builder()
      .docId("doc1")
      .sheetId("sheet1")
      .range("A1:B2")
      .build());
    assertThat(sheetData.getGridData().getRows()).hasSize(1);
    verify(cpService).post(eq("https://api.test/spreadsheet/get_sheet_range_data"), anyString());

    WxCpFormInfo formInfo = new WxCpFormInfo();
    formInfo.setFormTitle("日报");
    WxCpFormCreateResult formCreateResult = service.formCreate(WxCpFormCreateRequest.builder()
      .spaceId("space1")
      .fatherId("father1")
      .formInfo(formInfo)
      .build());
    assertThat(formCreateResult.getFormId()).isEqualTo("FORMID1");
    verify(cpService).post(eq("https://api.test/create_collect"), anyString());

    WxCpBaseResp formModifyResp = service.formModify(WxCpFormModifyRequest.builder()
      .oper(1)
      .formId("FORMID1")
      .formInfo(formInfo)
      .build());
    assertThat(formModifyResp.getErrcode()).isZero();
    verify(cpService).post(eq("https://api.test/modify_collect"), anyString());

    WxCpFormInfoResult formInfoResult = service.formInfo("FORMID1");
    assertThat(formInfoResult.getFormInfo().getFormId()).isEqualTo("FORMID1");
    ArgumentCaptor<String> formInfoBodyCaptor = ArgumentCaptor.forClass(String.class);
    verify(cpService).post(eq("https://api.test/get_form_info"), formInfoBodyCaptor.capture());
    assertThat(formInfoBodyCaptor.getValue()).isEqualTo("{\"formid\":\"FORMID1\"}");

    WxCpFormStatistic formStatistic = service.formStatistic(WxCpFormStatisticRequest.builder()
      .repeatedId("repeat-1")
      .build());
    assertThat(formStatistic.getFillCnt()).isEqualTo(3);
    ArgumentCaptor<String> formStatisticBodyCaptor = ArgumentCaptor.forClass(String.class);
    verify(cpService).post(eq("https://api.test/get_form_statistic"), formStatisticBodyCaptor.capture());
    assertThat(formStatisticBodyCaptor.getValue()).startsWith("[");
    assertThat(formStatisticBodyCaptor.getValue()).contains("\"repeated_id\":\"repeat-1\"");

    WxCpFormAnswer formAnswer = service.formAnswer(WxCpFormAnswerRequest.builder()
      .repeatedId("repeat-1")
      .answerIds(singletonList(1L))
      .build());
    assertThat(formAnswer.getAnswer().getAnswerList()).hasSize(1);
    verify(cpService).post(eq("https://api.test/get_form_answer"), anyString());
  }

  @Test
  public void testNewApisUseExpectedPaths() throws Exception {
    WxCpService cpService = mock(WxCpService.class);
    WxCpConfigStorage configStorage = mock(WxCpConfigStorage.class);
    when(cpService.getWxCpConfigStorage()).thenReturn(configStorage);
    when(configStorage.getApiUrl(WEDOC_GET_DOC_DATA)).thenReturn("https://api.test/get_doc_data");
    when(configStorage.getApiUrl(WEDOC_MOD_DOC)).thenReturn("https://api.test/mod_doc");
    when(configStorage.getApiUrl(WEDOC_UPLOAD_DOC_IMAGE)).thenReturn("https://api.test/upload_doc_image");
    when(configStorage.getApiUrl(WEDOC_ADD_ADMIN)).thenReturn("https://api.test/add_admin");
    when(configStorage.getApiUrl(WEDOC_DEL_ADMIN)).thenReturn("https://api.test/del_admin");
    when(configStorage.getApiUrl(WEDOC_GET_ADMIN_LIST)).thenReturn("https://api.test/get_admin_list");
    when(configStorage.getApiUrl(WEDOC_SMARTSHEET_GET_SHEET_AUTH)).thenReturn("https://api.test/smartsheet/get_sheet_auth");
    when(configStorage.getApiUrl(WEDOC_SMARTSHEET_MOD_SHEET_AUTH)).thenReturn("https://api.test/smartsheet/mod_sheet_auth");
    when(configStorage.getApiUrl(WEDOC_SMARTSHEET_GET_SHEET)).thenReturn("https://api.test/smartsheet/get_sheet");
    when(configStorage.getApiUrl(WEDOC_SMARTSHEET_ADD_SHEET)).thenReturn("https://api.test/smartsheet/add_sheet");
    when(configStorage.getApiUrl(WEDOC_SMARTSHEET_DELETE_SHEET)).thenReturn("https://api.test/smartsheet/delete_sheet");
    when(configStorage.getApiUrl(WEDOC_SMARTSHEET_UPDATE_SHEET)).thenReturn("https://api.test/smartsheet/update_sheet");
    when(configStorage.getApiUrl(WEDOC_SMARTSHEET_GET_VIEWS)).thenReturn("https://api.test/smartsheet/get_views");
    when(configStorage.getApiUrl(WEDOC_SMARTSHEET_ADD_VIEW)).thenReturn("https://api.test/smartsheet/add_view");
    when(configStorage.getApiUrl(WEDOC_SMARTSHEET_DELETE_VIEWS)).thenReturn("https://api.test/smartsheet/delete_views");
    when(configStorage.getApiUrl(WEDOC_SMARTSHEET_UPDATE_VIEW)).thenReturn("https://api.test/smartsheet/update_view");
    when(configStorage.getApiUrl(WEDOC_SMARTSHEET_GET_FIELDS)).thenReturn("https://api.test/smartsheet/get_fields");
    when(configStorage.getApiUrl(WEDOC_SMARTSHEET_ADD_FIELDS)).thenReturn("https://api.test/smartsheet/add_fields");
    when(configStorage.getApiUrl(WEDOC_SMARTSHEET_DELETE_FIELDS)).thenReturn("https://api.test/smartsheet/delete_fields");
    when(configStorage.getApiUrl(WEDOC_SMARTSHEET_UPDATE_FIELDS)).thenReturn("https://api.test/smartsheet/update_fields");
    when(configStorage.getApiUrl(WEDOC_SMARTSHEET_GET_RECORDS)).thenReturn("https://api.test/smartsheet/get_records");
    when(configStorage.getApiUrl(WEDOC_SMARTSHEET_ADD_RECORDS)).thenReturn("https://api.test/smartsheet/add_records");
    when(configStorage.getApiUrl(WEDOC_SMARTSHEET_DELETE_RECORDS)).thenReturn("https://api.test/smartsheet/delete_records");
    when(configStorage.getApiUrl(WEDOC_SMARTSHEET_UPDATE_RECORDS)).thenReturn("https://api.test/smartsheet/update_records");

    when(cpService.post(eq("https://api.test/get_doc_data"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"docid\":\"doc1\"}");
    when(cpService.post(eq("https://api.test/mod_doc"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    when(cpService.upload(eq("https://api.test/upload_doc_image"), any()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"image_url\":\"https://img.test/a.png\",\"media_id\":\"media-1\"}");
    when(cpService.post(eq("https://api.test/add_admin"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    when(cpService.post(eq("https://api.test/del_admin"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    when(cpService.post(eq("https://api.test/get_admin_list"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"docid\":\"doc1\",\"admin_list\":[{\"userid\":\"zhangsan\",\"type\":1}]}");
    when(cpService.post(eq("https://api.test/smartsheet/get_sheet_auth"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"docid\":\"doc1\",\"sheet_id\":\"sheet1\"}");
    when(cpService.post(eq("https://api.test/smartsheet/mod_sheet_auth"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    when(cpService.post(eq("https://api.test/smartsheet/get_sheet"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"docid\":\"doc1\",\"sheet_list\":[{\"sheet_id\":\"sheet1\"}]}");
    when(cpService.post(eq("https://api.test/smartsheet/add_sheet"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"docid\":\"doc1\",\"sheet\":{\"sheet_id\":\"sheet2\"}}");
    when(cpService.post(eq("https://api.test/smartsheet/delete_sheet"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    when(cpService.post(eq("https://api.test/smartsheet/update_sheet"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    when(cpService.post(eq("https://api.test/smartsheet/get_views"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"docid\":\"doc1\",\"sheet_id\":\"sheet1\",\"views\":[{\"view_id\":\"view1\"}]}");
    when(cpService.post(eq("https://api.test/smartsheet/add_view"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"docid\":\"doc1\",\"sheet_id\":\"sheet1\",\"view\":{\"view_id\":\"view2\"}}");
    when(cpService.post(eq("https://api.test/smartsheet/delete_views"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    when(cpService.post(eq("https://api.test/smartsheet/update_view"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    when(cpService.post(eq("https://api.test/smartsheet/get_fields"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"docid\":\"doc1\",\"sheet_id\":\"sheet1\",\"field_list\":[{\"field_id\":\"field1\"}]}");
    when(cpService.post(eq("https://api.test/smartsheet/add_fields"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"docid\":\"doc1\",\"sheet_id\":\"sheet1\",\"fields\":[{\"field_id\":\"field2\"}]}");
    when(cpService.post(eq("https://api.test/smartsheet/delete_fields"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    when(cpService.post(eq("https://api.test/smartsheet/update_fields"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    when(cpService.post(eq("https://api.test/smartsheet/get_records"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"docid\":\"doc1\",\"sheet_id\":\"sheet1\",\"record_list\":[{\"record_id\":\"record1\"}],\"has_more\":true,\"next_cursor\":101}");
    when(cpService.post(eq("https://api.test/smartsheet/add_records"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"docid\":\"doc1\",\"sheet_id\":\"sheet1\",\"records\":[{\"record_id\":\"record2\"}]}");
    when(cpService.post(eq("https://api.test/smartsheet/delete_records"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    when(cpService.post(eq("https://api.test/smartsheet/update_records"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");

    WxCpOaWeDocServiceImpl service = new WxCpOaWeDocServiceImpl(cpService);

    JsonObject extra = new JsonObject();
    extra.addProperty("start", 0);
    WxCpDocGetDataRequest getDataRequest = WxCpDocGetDataRequest.builder()
      .docId("doc1")
      .extra(extra)
      .build();
    WxCpDocData docData = service.docGetData(getDataRequest);
    assertThat(docData.getDocId()).isEqualTo("doc1");
    verify(cpService).post(eq("https://api.test/get_doc_data"), anyString());

    JsonArray requests = new JsonArray();
    JsonObject op = new JsonObject();
    op.addProperty("op", "insert_text");
    requests.add(op);
    WxCpDocModifyRequest modifyRequest = WxCpDocModifyRequest.builder()
      .docId("doc1")
      .requests(requests)
      .build();
    WxCpBaseResp modifyResp = service.docModify(modifyRequest);
    assertThat(modifyResp.getErrcode()).isZero();
    verify(cpService).post(eq("https://api.test/mod_doc"), anyString());

    File uploadFile = File.createTempFile("wedoc-upload-", ".png");
    uploadFile.deleteOnExit();
    WxCpDocImageUploadResult uploadResult = service.docUploadImage(uploadFile);
    assertThat(uploadResult.getEffectiveUrl()).isEqualTo("https://img.test/a.png");
    assertThat(uploadResult.getMediaId()).isEqualTo("media-1");
    verify(cpService).upload(eq("https://api.test/upload_doc_image"), any());

    WxCpDocAdminRequest adminRequest = WxCpDocAdminRequest.builder()
      .docId("doc1")
      .userId("zhangsan")
      .type(1)
      .build();
    WxCpBaseResp addAdminResp = service.docAddAdmin(adminRequest);
    assertThat(addAdminResp.getErrcode()).isZero();
    ArgumentCaptor<String> addAdminBodyCaptor = ArgumentCaptor.forClass(String.class);
    verify(cpService).post(eq("https://api.test/add_admin"), addAdminBodyCaptor.capture());
    assertThat(addAdminBodyCaptor.getValue()).contains("\"docid\":\"doc1\"");
    assertThat(addAdminBodyCaptor.getValue()).contains("\"userid\":\"zhangsan\"");
    assertThat(addAdminBodyCaptor.getValue()).contains("\"type\":1");

    WxCpDocAdminRequest deleteAdminRequest = WxCpDocAdminRequest.builder()
      .docId("doc1")
      .openUserId("ou_zhangsan")
      .build();
    WxCpBaseResp deleteAdminResp = service.docDeleteAdmin(deleteAdminRequest);
    assertThat(deleteAdminResp.getErrcode()).isZero();
    ArgumentCaptor<String> deleteAdminBodyCaptor = ArgumentCaptor.forClass(String.class);
    verify(cpService).post(eq("https://api.test/del_admin"), deleteAdminBodyCaptor.capture());
    assertThat(deleteAdminBodyCaptor.getValue()).contains("\"docid\":\"doc1\"");
    assertThat(deleteAdminBodyCaptor.getValue()).contains("\"open_userid\":\"ou_zhangsan\"");

    WxCpDocAdminListResult adminListResult = service.docGetAdminList("doc1");
    assertThat(adminListResult.getDocId()).isEqualTo("doc1");
    assertThat(adminListResult.getAdminList()).hasSize(1);
    assertThat(adminListResult.getAdminList().get(0).getUserId()).isEqualTo("zhangsan");
    ArgumentCaptor<String> getAdminListBodyCaptor = ArgumentCaptor.forClass(String.class);
    verify(cpService).post(eq("https://api.test/get_admin_list"), getAdminListBodyCaptor.capture());
    assertThat(getAdminListBodyCaptor.getValue()).isEqualTo("{\"docid\":\"doc1\"}");

    WxCpDocSmartSheetAuthRequest authRequest = WxCpDocSmartSheetAuthRequest.builder()
      .docId("doc1")
      .sheetId("sheet1")
      .build();
    WxCpDocSmartSheetAuth auth = service.smartSheetGetAuth(authRequest);
    assertThat(auth.getSheetId()).isEqualTo("sheet1");
    verify(cpService).post(eq("https://api.test/smartsheet/get_sheet_auth"), anyString());

    JsonObject authInfo = new JsonObject();
    authInfo.addProperty("mode", "custom");
    WxCpDocSmartSheetModifyAuthRequest modifyAuthRequest = WxCpDocSmartSheetModifyAuthRequest.builder()
      .docId("doc1")
      .sheetId("sheet1")
      .authInfo(authInfo)
      .build();
    WxCpBaseResp modifyAuthResp = service.smartSheetModifyAuth(modifyAuthRequest);
    assertThat(modifyAuthResp.getErrcode()).isZero();
    verify(cpService).post(eq("https://api.test/smartsheet/mod_sheet_auth"), anyString());

    WxCpDocSmartSheetRequest sheetRequest = WxCpDocSmartSheetRequest.builder()
      .docId("doc1")
      .build();
    WxCpDocSmartSheetResult sheetResult = service.smartSheetGetSheet(sheetRequest);
    assertThat(sheetResult.getEffectiveSheets().getAsJsonArray()).hasSize(1);
    verify(cpService).post(eq("https://api.test/smartsheet/get_sheet"), anyString());

    JsonObject sheetProperties = new JsonObject();
    sheetProperties.addProperty("title", "Sheet A");
    WxCpDocSmartSheetRequest addSheetRequest = WxCpDocSmartSheetRequest.builder()
      .docId("doc1")
      .build()
      .addExtra("properties", sheetProperties);
    WxCpDocSmartSheetResult addSheetResult = service.smartSheetAddSheet(addSheetRequest);
    assertThat(addSheetResult.getEffectiveSheets().getAsJsonObject().get("sheet_id").getAsString()).isEqualTo("sheet2");
    verify(cpService).post(eq("https://api.test/smartsheet/add_sheet"), anyString());

    WxCpBaseResp deleteSheetResp = service.smartSheetDeleteSheet(WxCpDocSmartSheetRequest.builder()
      .docId("doc1")
      .sheetId("sheet1")
      .build());
    assertThat(deleteSheetResp.getErrcode()).isZero();
    verify(cpService).post(eq("https://api.test/smartsheet/delete_sheet"), anyString());

    WxCpBaseResp updateSheetResp = service.smartSheetUpdateSheet(WxCpDocSmartSheetRequest.builder()
      .docId("doc1")
      .sheetId("sheet1")
      .build()
      .addExtra("title", "Sheet B"));
    assertThat(updateSheetResp.getErrcode()).isZero();
    verify(cpService).post(eq("https://api.test/smartsheet/update_sheet"), anyString());

    WxCpDocSmartSheetResult viewsResult = service.smartSheetGetViews(WxCpDocSmartSheetRequest.builder()
      .docId("doc1")
      .sheetId("sheet1")
      .build());
    assertThat(viewsResult.getEffectiveViews().getAsJsonArray()).hasSize(1);
    verify(cpService).post(eq("https://api.test/smartsheet/get_views"), anyString());

    JsonObject view = new JsonObject();
    view.addProperty("title", "All Records");
    WxCpDocSmartSheetResult addViewResult = service.smartSheetAddView(WxCpDocSmartSheetRequest.builder()
      .docId("doc1")
      .sheetId("sheet1")
      .build()
      .addExtraArrayItem("views", view));
    assertThat(addViewResult.getEffectiveViews().getAsJsonObject().get("view_id").getAsString()).isEqualTo("view2");
    verify(cpService).post(eq("https://api.test/smartsheet/add_view"), anyString());

    JsonArray viewIds = new JsonArray();
    viewIds.add("view1");
    WxCpBaseResp deleteViewsResp = service.smartSheetDeleteViews(WxCpDocSmartSheetRequest.builder()
      .docId("doc1")
      .sheetId("sheet1")
      .build()
      .addExtra("view_ids", viewIds));
    assertThat(deleteViewsResp.getErrcode()).isZero();
    verify(cpService).post(eq("https://api.test/smartsheet/delete_views"), anyString());

    WxCpBaseResp updateViewResp = service.smartSheetUpdateView(WxCpDocSmartSheetRequest.builder()
      .docId("doc1")
      .sheetId("sheet1")
      .viewId("view1")
      .build()
      .addExtra("title", "Updated View"));
    assertThat(updateViewResp.getErrcode()).isZero();
    verify(cpService).post(eq("https://api.test/smartsheet/update_view"), anyString());

    WxCpDocSmartSheetResult fieldsResult = service.smartSheetGetFields(WxCpDocSmartSheetRequest.builder()
      .docId("doc1")
      .sheetId("sheet1")
      .build());
    assertThat(fieldsResult.getEffectiveFields().getAsJsonArray()).hasSize(1);
    verify(cpService).post(eq("https://api.test/smartsheet/get_fields"), anyString());

    JsonObject field = new JsonObject();
    field.addProperty("title", "Priority");
    field.addProperty("type", "single_select");
    WxCpDocSmartSheetResult addFieldsResult = service.smartSheetAddFields(WxCpDocSmartSheetRequest.builder()
      .docId("doc1")
      .sheetId("sheet1")
      .build()
      .addExtraArrayItem("fields", field));
    assertThat(addFieldsResult.getEffectiveFields().getAsJsonArray()).hasSize(1);
    verify(cpService).post(eq("https://api.test/smartsheet/add_fields"), anyString());

    JsonArray fieldIds = new JsonArray();
    fieldIds.add("field1");
    WxCpBaseResp deleteFieldsResp = service.smartSheetDeleteFields(WxCpDocSmartSheetRequest.builder()
      .docId("doc1")
      .sheetId("sheet1")
      .build()
      .addExtra("field_ids", fieldIds));
    assertThat(deleteFieldsResp.getErrcode()).isZero();
    verify(cpService).post(eq("https://api.test/smartsheet/delete_fields"), anyString());

    WxCpBaseResp updateFieldsResp = service.smartSheetUpdateFields(WxCpDocSmartSheetRequest.builder()
      .docId("doc1")
      .sheetId("sheet1")
      .build()
      .addExtraArrayItem("fields", field));
    assertThat(updateFieldsResp.getErrcode()).isZero();
    verify(cpService).post(eq("https://api.test/smartsheet/update_fields"), anyString());

    WxCpDocSmartSheetResult recordsResult = service.smartSheetGetRecords(WxCpDocSmartSheetRequest.builder()
      .docId("doc1")
      .sheetId("sheet1")
      .build());
    assertThat(recordsResult.getEffectiveRecords().getAsJsonArray()).hasSize(1);
    assertThat(recordsResult.getHasMore()).isTrue();
    verify(cpService).post(eq("https://api.test/smartsheet/get_records"), anyString());

    JsonObject record = new JsonObject();
    record.addProperty("record_id", "record2");
    record.add("values", new JsonObject());
    WxCpDocSmartSheetResult addRecordsResult = service.smartSheetAddRecords(WxCpDocSmartSheetRequest.builder()
      .docId("doc1")
      .sheetId("sheet1")
      .build()
      .addExtraArrayItem("records", record));
    assertThat(addRecordsResult.getEffectiveRecords().getAsJsonArray()).hasSize(1);
    verify(cpService).post(eq("https://api.test/smartsheet/add_records"), anyString());

    JsonArray recordIds = new JsonArray();
    recordIds.add("record1");
    WxCpBaseResp deleteRecordsResp = service.smartSheetDeleteRecords(WxCpDocSmartSheetRequest.builder()
      .docId("doc1")
      .sheetId("sheet1")
      .build()
      .addExtra("record_ids", recordIds));
    assertThat(deleteRecordsResp.getErrcode()).isZero();
    verify(cpService).post(eq("https://api.test/smartsheet/delete_records"), anyString());

    WxCpBaseResp updateRecordsResp = service.smartSheetUpdateRecords(WxCpDocSmartSheetRequest.builder()
      .docId("doc1")
      .sheetId("sheet1")
      .build()
      .addExtraArrayItem("records", record));
    assertThat(updateRecordsResp.getErrcode()).isZero();
    verify(cpService).post(eq("https://api.test/smartsheet/update_records"), anyString());
  }
}
