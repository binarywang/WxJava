package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.doc.WxCpDocData;
import me.chanjar.weixin.cp.bean.oa.doc.WxCpDocGetDataRequest;
import me.chanjar.weixin.cp.bean.oa.doc.WxCpDocImageUploadResult;
import me.chanjar.weixin.cp.bean.oa.doc.WxCpDocModifyRequest;
import me.chanjar.weixin.cp.bean.oa.doc.WxCpDocSmartSheetAuth;
import me.chanjar.weixin.cp.bean.oa.doc.WxCpDocSmartSheetAuthRequest;
import me.chanjar.weixin.cp.bean.oa.doc.WxCpDocSmartSheetModifyAuthRequest;
import me.chanjar.weixin.cp.bean.oa.doc.WxCpDocSmartSheetRequest;
import me.chanjar.weixin.cp.bean.oa.doc.WxCpDocSmartSheetResult;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import org.testng.annotations.Test;

import java.io.File;

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
  public void testNewApisUseExpectedPaths() throws WxErrorException {
    WxCpService cpService = mock(WxCpService.class);
    WxCpConfigStorage configStorage = mock(WxCpConfigStorage.class);
    when(cpService.getWxCpConfigStorage()).thenReturn(configStorage);
    when(configStorage.getApiUrl(WEDOC_GET_DOC_DATA)).thenReturn("https://api.test/get_doc_data");
    when(configStorage.getApiUrl(WEDOC_MOD_DOC)).thenReturn("https://api.test/mod_doc");
    when(configStorage.getApiUrl(WEDOC_UPLOAD_DOC_IMAGE)).thenReturn("https://api.test/upload_doc_image");
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

    WxCpDocImageUploadResult uploadResult = service.docUploadImage(new File("demo.png"));
    assertThat(uploadResult.getEffectiveUrl()).isEqualTo("https://img.test/a.png");
    assertThat(uploadResult.getMediaId()).isEqualTo("media-1");
    verify(cpService).upload(eq("https://api.test/upload_doc_image"), any());

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
