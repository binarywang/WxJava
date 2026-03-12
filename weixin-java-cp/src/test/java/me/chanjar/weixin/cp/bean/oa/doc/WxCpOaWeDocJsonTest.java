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
      + "\"secure_setting\":{\"enable_readonly_copy\":false,\"watermark\":{\"margin_type\":2,\"show_text\":true,\"text\":\"mark\"}},"
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
      + "\"form_question\":{\"items\":[{\"question_id\":1,\"title\":\"问题1\",\"pos\":1,\"status\":1,\"reply_type\":1,\"must_reply\":true}]},"
      + "\"form_setting\":{\"fill_out_auth\":1},"
      + "\"repeated_id\":[\"REPEAT_ID1\"]"
      + "}"
      + "}";

    WxCpFormInfoResult result = WxCpFormInfoResult.fromJson(json);
    assertThat(result.getFormInfo().getFormId()).isEqualTo("FORMID1");
    assertThat(result.getFormInfo().getFormQuestion().getItems().get(0).getTitle()).isEqualTo("问题1");
    assertThat(result.getFormInfo().getRepeatedId()).containsExactly("REPEAT_ID1");
  }

  @Test
  public void testFormStatisticAndAnswerFromJson() {
    String statisticJson = "{"
      + "\"errcode\":0,"
      + "\"errmsg\":\"ok\","
      + "\"fill_cnt\":1,"
      + "\"fill_user_cnt\":1,"
      + "\"unfill_user_cnt\":2,"
      + "\"submit_users\":[{\"userid\":\"zhangsan\",\"answer_id\":3,\"submit_time\":1668418200,\"user_name\":\"张三\"}],"
      + "\"has_more\":false,"
      + "\"cursor\":1"
      + "}";
    WxCpFormStatistic statistic = WxCpFormStatistic.fromJson(statisticJson);
    assertThat(statistic.getSubmitUsers()).hasSize(1);
    assertThat(statistic.getSubmitUsers().get(0).getAnswerId()).isEqualTo(3L);

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
}
