package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.JsonObject;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 企业微信文档 JSON 测试.
 */
public class WxCpOaWeDocJsonTest {

  @Test
  public void testWxCpServiceExposeWeDocService() {
    WxCpService service = new WxCpServiceImpl();
    assertThat(service.getOaWeDocService()).isNotNull();
  }

  @Test
  public void testDocModifyJoinRuleRequestToJson() {
    WxCpDocAuthInfo.CoAuthInfo coAuthInfo = new WxCpDocAuthInfo.CoAuthInfo();
    coAuthInfo.setType(2);
    coAuthInfo.setDepartmentId(3L);
    coAuthInfo.setAuth(1);

    WxCpDocModifyJoinRuleRequest request = WxCpDocModifyJoinRuleRequest.builder()
      .docId("doc123")
      .enableCorpInternal(true)
      .corpInternalAuth(1)
      .updateCoAuthList(true)
      .coAuthList(Collections.singletonList(coAuthInfo))
      .build();

    String json = request.toJson();
    assertThat(json).contains("\"docid\":\"doc123\"");
    assertThat(json).contains("\"update_co_auth_list\":true");
    assertThat(json).contains("\"departmentid\":3");
  }

  @Test
  public void testDocAuthInfoFromJson() {
    String json = "{"
      + "\"errcode\":0,"
      + "\"errmsg\":\"ok\","
      + "\"access_rule\":{\"enable_corp_internal\":true,\"corp_internal_auth\":1},"
      + "\"secure_setting\":{\"enable_readonly_copy\":false,"
      + "\"watermark\":{\"margin_type\":2,"
      + "\"show_text\":true,\"text\":\"mark\"}},"
      + "\"doc_member_list\":[{\"type\":1,\"userid\":\"zhangsan\",\"auth\":7}],"
      + "\"co_auth_list\":[{\"type\":2,\"departmentid\":42,\"auth\":1}]"
      + "}";

    WxCpDocAuthInfo result = WxCpDocAuthInfo.fromJson(json);
    assertThat(result.getErrcode()).isZero();
    assertThat(result.getAccessRule().getEnableCorpInternal()).isTrue();
    assertThat(result.getSecureSetting().getWatermark().getText()).isEqualTo("mark");
    assertThat(result.getDocMemberList()).hasSize(1);
    assertThat(result.getCoAuthList().get(0).getDepartmentId()).isEqualTo(42L);
  }

  @Test
  public void testDocCreateRenameInfoAndShareJson() {
    WxCpDocCreateRequest createRequest = WxCpDocCreateRequest.builder()
      .spaceId("space1")
      .fatherId("father1")
      .docType(3)
      .docName("日报")
      .adminUsers(Arrays.asList("zhangsan", "lisi"))
      .build();
    assertThat(createRequest.toJson()).contains("\"spaceid\":\"space1\"");
    assertThat(createRequest.toJson()).contains("\"doc_type\":3");
    assertThat(createRequest.toJson()).contains("\"admin_users\":[\"zhangsan\",\"lisi\"]");

    String createJson = "{"
      + "\"errcode\":0,"
      + "\"errmsg\":\"ok\","
      + "\"docid\":\"doc123\""
      + "}";
    WxCpDocCreateData createData = WxCpDocCreateData.fromJson(createJson);
    assertThat(createData.getDocId()).isEqualTo("doc123");

    WxCpDocRenameRequest renameRequest = WxCpDocRenameRequest.builder()
      .docId("doc123")
      .newName("周报")
      .build();
    assertThat(renameRequest.toJson()).contains("\"docid\":\"doc123\"");
    assertThat(renameRequest.toJson()).contains("\"new_name\":\"周报\"");

    String infoJson = "{"
      + "\"errcode\":0,"
      + "\"errmsg\":\"ok\","
      + "\"doc_base_info\":{"
      + "\"docid\":\"doc123\","
      + "\"doc_name\":\"日报\","
      + "\"create_time\":1710000000,"
      + "\"modify_time\":1710000300,"
      + "\"doc_type\":3"
      + "}"
      + "}";
    WxCpDocInfo docInfo = WxCpDocInfo.fromJson(infoJson);
    assertThat(docInfo.getDocBaseInfo().getDocId()).isEqualTo("doc123");
    assertThat(docInfo.getDocBaseInfo().getDocName()).isEqualTo("日报");

    WxCpDocShareRequest shareRequest = WxCpDocShareRequest.builder()
      .formId("FORMID1")
      .build();
    assertThat(shareRequest.toJson()).contains("\"formid\":\"FORMID1\"");

    String shareJson = "{"
      + "\"errcode\":0,"
      + "\"errmsg\":\"ok\","
      + "\"share_url\":\"https://wedoc.test/share/1\""
      + "}";
    WxCpDocShare docShare = WxCpDocShare.fromJson(shareJson);
    assertThat(docShare.getShareUrl()).isEqualTo("https://wedoc.test/share/1");
  }

  @Test
  public void testFormCreateRequestToJson() {
    JsonObject extendSetting = new JsonObject();
    extendSetting.addProperty("camera_only", true);

    WxCpFormInfo.QuestionItem questionItem = new WxCpFormInfo.QuestionItem();
    questionItem.setQuestionId(1L);
    questionItem.setTitle("图片题");
    questionItem.setPos(1);
    questionItem.setStatus(1);
    questionItem.setReplyType(9);
    questionItem.setMustReply(true);
    questionItem.setQuestionExtendSetting(extendSetting);

    WxCpFormInfo.FormQuestion formQuestion = new WxCpFormInfo.FormQuestion();
    formQuestion.setItems(Collections.singletonList(questionItem));

    WxCpFormInfo formInfo = new WxCpFormInfo();
    formInfo.setFormTitle("每日上报");
    formInfo.setFormQuestion(formQuestion);

    WxCpFormCreateRequest request = WxCpFormCreateRequest.builder()
      .spaceId("space1")
      .fatherId("father1")
      .formInfo(formInfo)
      .build();

    String json = request.toJson();
    assertThat(json).contains("\"spaceid\":\"space1\"");
    assertThat(json).contains("\"form_title\":\"每日上报\"");
    assertThat(json).contains("\"camera_only\":true");
  }

  @Test
  public void testFormInfoResultFromJson() {
    String json = "{"
      + "\"errcode\":0,"
      + "\"errmsg\":\"ok\","
      + "\"form_info\":{"
      + "\"formid\":\"FORMID1\","
      + "\"form_title\":\"api创建的收集表\","
      + "\"form_question\":{\"items\":[{\"question_id\":1,"
      + "\"title\":\"问题1\",\"pos\":1,\"status\":1,"
      + "\"reply_type\":1,\"must_reply\":true}]},"
      + "\"form_setting\":{\"fill_out_auth\":1,\"max_fill_cnt\":2},"
      + "\"repeated_id\":[\"REPEAT_ID1\"]"
      + "}"
      + "}";

    WxCpFormInfoResult result = WxCpFormInfoResult.fromJson(json);
    assertThat(result.getFormInfo().getFormId()).isEqualTo("FORMID1");
    assertThat(result.getFormInfo().getFormQuestion().getItems().get(0).getTitle()).isEqualTo("问题1");
    assertThat(result.getFormInfo().getFormSetting().getMaxFillCnt()).isEqualTo(2);
    assertThat(result.getFormInfo().getRepeatedId()).containsExactly("REPEAT_ID1");
  }

  @Test
  public void testFormStatisticAndAnswerFromJson() {
    String statisticRequestJson = WxCpFormStatisticRequest.toJson(Collections.singletonList(
      WxCpFormStatisticRequest.builder().repeatedId("REPEAT_ID1").reqType(1).limit(100L).cursor(0L).build()
    ));
    assertThat(statisticRequestJson).startsWith("[");
    assertThat(statisticRequestJson).contains("\"repeated_id\":\"REPEAT_ID1\"");

    String statisticJson = "{\"errcode\":0,\"errmsg\":\"ok\","
      + "\"statistic_list\":[{"
      + "\"repeated_id\":\"REPEAT_ID1\","
      + "\"repeated_name\":\"第1次收集\","
      + "\"fill_cnt\":1,"
      + "\"fill_user_cnt\":1,"
      + "\"unfill_user_cnt\":2,"
      + "\"submit_users\":[{\"userid\":\"zhangsan\","
      + "\"answer_id\":3,\"submit_time\":1668418200,"
      + "\"user_name\":\"张三\"}],"
      + "\"has_more\":false,"
      + "\"cursor\":1"
      + "}]}";
    WxCpFormStatisticResult statisticResult =
      WxCpFormStatisticResult.fromJson(statisticJson);
    assertThat(statisticResult.getErrcode()).isZero();
    WxCpFormStatistic statistic =
      statisticResult.getStatisticList().get(0);
    assertThat(statistic.getSubmitUsers()).hasSize(1);
    assertThat(statistic.getSubmitUsers().get(0).getAnswerId())
      .isEqualTo(3L);

    String answerJson = "{"
      + "\"errcode\":0,"
      + "\"errmsg\":\"ok\","
      + "\"answer\":{\"answer_list\":[{"
      + "\"answer_id\":15,"
      + "\"user_name\":\"张三\","
      + "\"ctime\":1668430580,"
      + "\"mtime\":1668430580,"
      + "\"reply\":{\"items\":["
      + "{\"question_id\":1,\"text_reply\":\"答案\"},"
      + "{\"question_id\":2,\"option_reply\":[1,2]},"
      + "{\"question_id\":3,\"file_extend_reply\":[{\"name\":\"附件\",\"fileid\":\"FILEID1\"}]}"
      + "]},"
      + "\"answer_status\":1,"
      + "\"userid\":\"zhangsan\""
      + "}]}"
      + "}";
    WxCpFormAnswer answer = WxCpFormAnswer.fromJson(answerJson);
    assertThat(answer.getAnswer().getAnswerList()).hasSize(1);
    assertThat(answer.getAnswer().getAnswerList().get(0).getReply().getItems()).hasSize(3);
    assertThat(answer.getAnswer().getAnswerList().get(0).getReply().getItems().get(1).getOptionReply())
      .isEqualTo(Arrays.asList(1, 2));
  }

  @Test
  public void testDocGetDataAndModifyJson() {
    WxCpDocGetDataRequest getDataRequest = WxCpDocGetDataRequest.builder()
      .docId("doc123")
      .build();
    getDataRequest.addExtra("start", 0).addExtra("limit", 20);
    assertThat(getDataRequest.toJson()).contains("\"docid\":\"doc123\"");
    assertThat(getDataRequest.toJson()).contains("\"limit\":20");

    JsonObject insertRequest = new JsonObject();
    insertRequest.addProperty("op", "insert_text");
    insertRequest.addProperty("text", "hello");
    WxCpDocModifyRequest modifyRequest = WxCpDocModifyRequest.builder()
      .docId("doc123")
      .build();
    modifyRequest.addRequest(insertRequest).addExtra("client_token", "token-1");
    assertThat(modifyRequest.toJson()).contains("\"requests\"");
    assertThat(modifyRequest.toJson()).contains("\"insert_text\"");
    assertThat(modifyRequest.toJson()).contains("\"client_token\":\"token-1\"");

    String json = "{"
      + "\"errcode\":0,"
      + "\"errmsg\":\"ok\","
      + "\"docid\":\"doc123\","
      + "\"content\":{\"blocks\":[{\"block_id\":\"blk1\"}]},"
      + "\"has_more\":true,"
      + "\"next_cursor\":\"cursor-1\""
      + "}";
    WxCpDocData result = WxCpDocData.fromJson(json);
    assertThat(result.getDocId()).isEqualTo("doc123");
    assertThat(result.getContent().getAsJsonObject().getAsJsonArray("blocks")).hasSize(1);
    assertThat(result.getEffectiveContent().getAsJsonObject().getAsJsonArray("blocks")).hasSize(1);
    assertThat(result.getHasMore()).isTrue();
    assertThat(result.getNextCursor()).isEqualTo("cursor-1");

    String docContentJson = "{"
      + "\"errcode\":0,"
      + "\"errmsg\":\"ok\","
      + "\"docid\":\"doc123\","
      + "\"doc_content\":{\"blocks\":[{\"block_id\":\"blk2\"}]}"
      + "}";
    WxCpDocData docContentResult = WxCpDocData.fromJson(docContentJson);
    assertThat(docContentResult.getEffectiveContent().getAsJsonObject().getAsJsonArray("blocks")).hasSize(1);
  }

  @Test
  public void testDocModifyMemberAndSafetyJson() {
    WxCpDocAuthInfo.DocMember updateMember = new WxCpDocAuthInfo.DocMember();
    updateMember.setType(1);
    updateMember.setUserId("zhangsan");
    updateMember.setAuth(7);

    WxCpDocAuthInfo.DocMember deleteMember = new WxCpDocAuthInfo.DocMember();
    deleteMember.setType(1);
    deleteMember.setUserId("lisi");

    WxCpDocModifyMemberRequest memberRequest = WxCpDocModifyMemberRequest.builder()
      .docId("doc123")
      .updateFileMemberList(Collections.singletonList(updateMember))
      .delFileMemberList(Collections.singletonList(deleteMember))
      .build();
    assertThat(memberRequest.toJson()).contains("\"docid\":\"doc123\"");
    assertThat(memberRequest.toJson()).contains("\"update_file_member_list\"");
    assertThat(memberRequest.toJson()).contains("\"userid\":\"zhangsan\"");
    assertThat(memberRequest.toJson()).contains("\"del_file_member_list\"");

    WxCpDocAuthInfo.Watermark watermark = new WxCpDocAuthInfo.Watermark();
    watermark.setMarginType(2);
    watermark.setShowText(true);
    watermark.setText("watermark");
    WxCpDocModifySafetySettingRequest safetyRequest =
      WxCpDocModifySafetySettingRequest.builder()
        .docId("doc123")
        .enableReadonlyCopy(true)
        .watermark(watermark)
        .build();
    assertThat(safetyRequest.toJson())
      .contains("\"enable_readonly_copy\":true");
    assertThat(safetyRequest.toJson())
      .contains("\"text\":\"watermark\"");
  }

  @Test
  public void testDocUploadImageAndSmartSheetAuthJson() {
    String uploadJson = "{"
      + "\"errcode\":0,"
      + "\"errmsg\":\"ok\","
      + "\"image_url\":\"https://wedoc.test/image.png\","
      + "\"imageid\":\"img123\","
      + "\"media_id\":\"media123\","
      + "\"md5\":\"abc\""
      + "}";
    WxCpDocImageUploadResult uploadResult = WxCpDocImageUploadResult.fromJson(uploadJson);
    assertThat(uploadResult.getEffectiveUrl()).isEqualTo("https://wedoc.test/image.png");
    assertThat(uploadResult.getImageId()).isEqualTo("img123");
    assertThat(uploadResult.getMediaId()).isEqualTo("media123");

    JsonObject smartExtra = new JsonObject();
    smartExtra.addProperty("view_type", "field");
    WxCpDocSmartSheetAuthRequest smartRequest = WxCpDocSmartSheetAuthRequest.builder()
      .docId("doc456")
      .sheetId("sheet789")
      .extra(smartExtra)
      .build();
    assertThat(smartRequest.toJson()).contains("\"sheet_id\":\"sheet789\"");
    assertThat(smartRequest.toJson()).contains("\"view_type\":\"field\"");

    JsonObject authInfo = new JsonObject();
    authInfo.addProperty("mode", "custom");
    WxCpDocSmartSheetModifyAuthRequest modifyAuthRequest = WxCpDocSmartSheetModifyAuthRequest.builder()
      .docId("doc456")
      .sheetId("sheet789")
      .authInfo(authInfo)
      .build();
    assertThat(modifyAuthRequest.toJson()).contains("\"auth_info\"");
    assertThat(modifyAuthRequest.toJson()).contains("\"mode\":\"custom\"");

    String authJson = "{"
      + "\"errcode\":0,"
      + "\"errmsg\":\"ok\","
      + "\"docid\":\"doc456\","
      + "\"sheet_id\":\"sheet789\","
      + "\"auth_info\":{\"mode\":\"custom\"},"
      + "\"field_auth\":{\"columns\":[{\"field_id\":\"f1\"}]}"
      + "}";
    WxCpDocSmartSheetAuth smartSheetAuth = WxCpDocSmartSheetAuth.fromJson(authJson);
    assertThat(smartSheetAuth.getDocId()).isEqualTo("doc456");
    assertThat(smartSheetAuth.getAuthInfo().getAsJsonObject().get("mode").getAsString()).isEqualTo("custom");
    assertThat(smartSheetAuth.getFieldAuth().getAsJsonObject().getAsJsonArray("columns")).hasSize(1);
    assertThat(smartSheetAuth.getEffectiveAuthInfo().getAsJsonObject().get("mode").getAsString()).isEqualTo("custom");
  }

  @Test
  public void testDocAdminJson() {
    WxCpDocAdminRequest request = WxCpDocAdminRequest.builder()
      .docId("doc456")
      .userId("zhangsan")
      .type(1)
      .build();
    assertThat(request.toJson()).contains("\"docid\":\"doc456\"");
    assertThat(request.toJson()).contains("\"userid\":\"zhangsan\"");
    assertThat(request.toJson()).contains("\"type\":1");

    WxCpDocAdminRequest openUserRequest = WxCpDocAdminRequest.builder()
      .docId("doc456")
      .openUserId("ou_xxx")
      .build();
    assertThat(openUserRequest.toJson()).contains("\"open_userid\":\"ou_xxx\"");

    String json = "{"
      + "\"errcode\":0,"
      + "\"errmsg\":\"ok\","
      + "\"docid\":\"doc456\","
      + "\"admin_list\":[{\"userid\":\"zhangsan\",\"type\":1},{\"open_userid\":\"ou_xxx\",\"type\":2}]"
      + "}";
    WxCpDocAdminListResult result = WxCpDocAdminListResult.fromJson(json);
    assertThat(result.getDocId()).isEqualTo("doc456");
    assertThat(result.getAdminList()).hasSize(2);
    assertThat(result.getAdminList().get(0).getUserId()).isEqualTo("zhangsan");
    assertThat(result.getAdminList().get(1).getOpenUserId()).isEqualTo("ou_xxx");
  }

  @Test
  public void testSmartSheetCrudJson() {
    JsonObject properties = new JsonObject();
    properties.addProperty("title", "Sheet A");
    JsonObject field = new JsonObject();
    field.addProperty("title", "Priority");
    JsonObject record = new JsonObject();
    record.addProperty("record_id", "rec-1");

    WxCpDocSmartSheetRequest request = WxCpDocSmartSheetRequest.builder()
      .docId("doc456")
      .sheetId("sheet789")
      .viewId("view1")
      .build();
    request.addExtra("properties", properties)
      .addExtraArrayItem("fields", field)
      .addExtraArrayItem("records", record)
      .addExtra("offset", 20);
    assertThat(request.toJson()).contains("\"docid\":\"doc456\"");
    assertThat(request.toJson()).contains("\"sheet_id\":\"sheet789\"");
    assertThat(request.toJson()).contains("\"view_id\":\"view1\"");
    assertThat(request.toJson()).contains("\"title\":\"Sheet A\"");
    assertThat(request.toJson()).contains("\"record_id\":\"rec-1\"");
    assertThat(request.toJson()).contains("\"offset\":20");

    String resultJson = "{"
      + "\"errcode\":0,"
      + "\"errmsg\":\"ok\","
      + "\"docid\":\"doc456\","
      + "\"sheet_id\":\"sheet789\","
      + "\"sheet_list\":[{\"sheet_id\":\"sheet1\"}],"
      + "\"view_list\":[{\"view_id\":\"view1\"}],"
      + "\"field_list\":[{\"field_id\":\"field1\"}],"
      + "\"record_list\":[{\"record_id\":\"record1\"}],"
      + "\"has_more\":true,"
      + "\"next_cursor\":101"
      + "}";
    WxCpDocSmartSheetResult result = WxCpDocSmartSheetResult.fromJson(resultJson);
    assertThat(result.getDocId()).isEqualTo("doc456");
    assertThat(result.getEffectiveSheets().getAsJsonArray()).hasSize(1);
    assertThat(result.getEffectiveViews().getAsJsonArray()).hasSize(1);
    assertThat(result.getEffectiveFields().getAsJsonArray()).hasSize(1);
    assertThat(result.getEffectiveRecords().getAsJsonArray()).hasSize(1);
    assertThat(result.getHasMore()).isTrue();
    assertThat(result.getNextCursor().getAsInt()).isEqualTo(101);
  }

  @Test
  public void testSpreadsheetAndFormModifyJson() {
    WxCpDocSheetBatchUpdateRequest.Request.AddSheetRequest addSheetRequest =
      new WxCpDocSheetBatchUpdateRequest.Request.AddSheetRequest();
    addSheetRequest.setTitle("Sheet A");
    addSheetRequest.setRowCount(20);
    addSheetRequest.setColumnCount(5);
    WxCpDocSheetBatchUpdateRequest.Request request = new WxCpDocSheetBatchUpdateRequest.Request();
    request.setAddSheetRequest(addSheetRequest);

    WxCpDocSheetBatchUpdateRequest batchUpdateRequest = WxCpDocSheetBatchUpdateRequest.builder()
      .docId("doc123")
      .requests(Collections.singletonList(request))
      .build();
    assertThat(batchUpdateRequest.toJson()).contains("\"docid\":\"doc123\"");
    assertThat(batchUpdateRequest.toJson()).contains("\"add_sheet_request\"");
    assertThat(batchUpdateRequest.toJson()).contains("\"row_count\":20");

    String batchUpdateJson = "{"
      + "\"add_sheet_response\":{\"properties\":"
      + "{\"sheet_id\":\"sheet1\",\"title\":\"Sheet A\","
      + "\"row_count\":20,\"column_count\":5}},"
      + "\"update_range_response\":{\"updated_cells\":2}"
      + "}";
    WxCpDocSheetBatchUpdateResponse batchUpdateResponse = WxCpDocSheetBatchUpdateResponse.fromJson(batchUpdateJson);
    assertThat(batchUpdateResponse.getAddSheetResponse().getProperties().getSheetId()).isEqualTo("sheet1");
    assertThat(batchUpdateResponse.getUpdateRangeResponse().getUpdatedCells()).isEqualTo(2);

    String propertiesJson = "{"
      + "\"errcode\":0,"
      + "\"errmsg\":\"ok\","
      + "\"properties\":[{\"sheet_id\":\"sheet1\",\"title\":\"Sheet A\",\"row_count\":20,\"column_count\":5}]"
      + "}";
    WxCpDocSheetProperties properties = WxCpDocSheetProperties.fromJson(propertiesJson);
    assertThat(properties.getProperties()).hasSize(1);
    assertThat(properties.getProperties().get(0).getTitle()).isEqualTo("Sheet A");

    WxCpDocSheetGetDataRequest getDataRequest = WxCpDocSheetGetDataRequest.builder()
      .docId("doc123")
      .sheetId("sheet1")
      .range("A1:B2")
      .build();
    assertThat(getDataRequest.toJson()).contains("\"sheet_id\":\"sheet1\"");
    assertThat(getDataRequest.toJson()).contains("\"range\":\"A1:B2\"");

    String sheetDataJson = "{"
      + "\"errcode\":0,"
      + "\"errmsg\":\"ok\","
      + "\"grid_data\":{\"start_row\":0,\"start_column\":0,"
      + "\"rows\":[{\"values\":[{\"cell_value\":"
      + "{\"text\":\"hello\"}}]}]}"
      + "}";
    WxCpDocSheetData sheetData = WxCpDocSheetData.fromJson(sheetDataJson);
    assertThat(sheetData.getGridData().getRows()).hasSize(1);
    assertThat(sheetData.getGridData().getRows().get(0).getValues().get(0).getCellValue().getText()).isEqualTo("hello");

    WxCpFormInfo formInfo = new WxCpFormInfo();
    formInfo.setFormTitle("日报");
    WxCpFormModifyRequest formModifyRequest = WxCpFormModifyRequest.builder()
      .oper(1)
      .formId("FORMID1")
      .formInfo(formInfo)
      .build();
    assertThat(formModifyRequest.toJson()).contains("\"oper\":1");
    assertThat(formModifyRequest.toJson()).contains("\"formid\":\"FORMID1\"");
    assertThat(formModifyRequest.toJson()).contains("\"form_title\":\"日报\"");
  }
}
