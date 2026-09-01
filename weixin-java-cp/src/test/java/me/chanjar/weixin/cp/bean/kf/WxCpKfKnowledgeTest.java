package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.JsonParser;
import me.chanjar.weixin.cp.api.WxCpKfService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpKfServiceImpl;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WxCpKfKnowledgeTest {

  @Test
  public void testKnowledgeGroupListFromJson() {
    String json = "{\"errcode\":0,\"errmsg\":\"ok\",\"next_cursor\":\"next\",\"has_more\":1,\"group_list\":[{\"group_id\":\"group-1\",\"name\":\"默认分组\",\"is_default\":1}]}";

    WxCpKfKnowledgeGroupListResp response = WxCpKfKnowledgeGroupListResp.fromJson(json);

    assertThat(response.getNextCursor()).isEqualTo("next");
    assertThat(response.getHasMore()).isEqualTo(1);
    assertThat(response.getGroupList()).singleElement().satisfies(group -> {
      assertThat(group.getGroupId()).isEqualTo("group-1");
      assertThat(group.getName()).isEqualTo("默认分组");
      assertThat(group.getIsDefault()).isEqualTo(1);
    });
  }

  @Test
  public void testKnowledgeIntentToJsonAndFromJson() {
    String json = "{\"group_id\":\"group-1\",\"intent_id\":\"intent-1\",\"question\":{\"text\":{\"content\":\"主问题\"}},\"similar_questions\":{\"items\":[{\"text\":{\"content\":\"相似问题\"}}]},\"answers\":[{\"text\":{\"content\":\"回答\"},\"attachments\":[{\"msgtype\":\"image\",\"image\":{\"media_id\":\"media-1\"}}]}]}";

    WxCpKfKnowledgeIntent intent = WxCpKfKnowledgeIntent.fromJson(json);
    String serialized = WxCpGsonBuilder.create().toJson(intent);

    assertThat(intent.getQuestion().getText().getContent()).isEqualTo("主问题");
    assertThat(intent.getSimilarQuestions().getItems()).hasSize(1);
    assertThat(intent.getAnswers().get(0).getAttachments().get(0).getImage().getMediaId()).isEqualTo("media-1");
    assertThat(JsonParser.parseString(serialized)).isEqualTo(JsonParser.parseString(json));
  }

  @Test
  public void testKnowledgeApiRequests() throws Exception {
    WxCpService cpService = mock(WxCpService.class, RETURNS_DEEP_STUBS);
    when(cpService.getWxCpConfigStorage().getApiUrl(anyString())).thenAnswer(invocation -> invocation.getArgument(0));
    when(cpService.post(anyString(), anyString())).thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"group_id\":\"group-1\",\"intent_id\":\"intent-1\"}");
    WxCpKfService service = new WxCpKfServiceImpl(cpService);

    WxCpKfKnowledgeGroup group = new WxCpKfKnowledgeGroup();
    group.setName("常见问题");
    WxCpKfKnowledgeIntent intent = WxCpKfKnowledgeIntent.fromJson("{\"group_id\":\"group-1\",\"intent_id\":\"intent-1\",\"question\":{\"text\":{\"content\":\"主问题\"}},\"answers\":[{\"text\":{\"content\":\"回答\"}}]}");

    assertThat(service.addKnowledgeGroup(group).getGroupId()).isEqualTo("group-1");
    group.setGroupId("group-1");
    service.delKnowledgeGroup("group-1");
    service.modKnowledgeGroup(group);
    service.listKnowledgeGroup("cursor", 100, "group-1");
    assertThat(service.addKnowledgeIntent(intent).getIntentId()).isEqualTo("intent-1");
    service.delKnowledgeIntent("intent-1");
    service.modKnowledgeIntent(intent);
    service.listKnowledgeIntent("cursor", 100, "group-1", "intent-1");

    verify(cpService).post("/cgi-bin/kf/knowledge/add_group", "{\"name\":\"常见问题\"}");
    verify(cpService).post("/cgi-bin/kf/knowledge/del_group", "{\"group_id\":\"group-1\"}");
    verify(cpService).post("/cgi-bin/kf/knowledge/mod_group", "{\"group_id\":\"group-1\",\"name\":\"常见问题\"}");
    verify(cpService).post("/cgi-bin/kf/knowledge/list_group", "{\"cursor\":\"cursor\",\"limit\":100,\"group_id\":\"group-1\"}");
    verify(cpService).post("/cgi-bin/kf/knowledge/add_intent", "{\"group_id\":\"group-1\",\"intent_id\":\"intent-1\",\"question\":{\"text\":{\"content\":\"主问题\"}},\"answers\":[{\"text\":{\"content\":\"回答\"}}]}");
    verify(cpService).post("/cgi-bin/kf/knowledge/del_intent", "{\"intent_id\":\"intent-1\"}");
    verify(cpService).post("/cgi-bin/kf/knowledge/mod_intent", "{\"group_id\":\"group-1\",\"intent_id\":\"intent-1\",\"question\":{\"text\":{\"content\":\"主问题\"}},\"answers\":[{\"text\":{\"content\":\"回答\"}}]}");
    verify(cpService).post("/cgi-bin/kf/knowledge/list_intent", "{\"cursor\":\"cursor\",\"limit\":100,\"group_id\":\"group-1\",\"intent_id\":\"intent-1\"}");
  }
}
