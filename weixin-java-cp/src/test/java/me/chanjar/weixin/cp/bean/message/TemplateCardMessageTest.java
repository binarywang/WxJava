package me.chanjar.weixin.cp.bean.message;

import com.google.common.collect.Lists;
import me.chanjar.weixin.common.util.json.GsonParser;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试用例中的json参考 <a href="https://developer.work.weixin.qq.com/document/path/94888">https://developer.work.weixin.qq.com/document/path/94888</a>
 * <br>
 * created on  2024-10-22
 */
public class TemplateCardMessageTest {

  /**
   * Test to json video.
   */
  @Test
  public void testToJson() {

    TemplateCardMessage templateCardMessage = new TemplateCardMessage();
    templateCardMessage.setAgentid(0);
    templateCardMessage.setUserids(Lists.newArrayList("userid1", "userid2"));
    templateCardMessage.setResponseCode("xihrjiohewirfhwripsiqwjerdio_dhweu");
    TemplateCardMessage.TemplateCardDTO templateCardDTO = new TemplateCardMessage.TemplateCardDTO();
    templateCardMessage.setTemplateCard(templateCardDTO);
    TemplateCardMessage.TemplateCardDTO.SelectListDTO selectListDTO = new TemplateCardMessage.TemplateCardDTO.SelectListDTO();
    selectListDTO.setSelectedId("id");
    selectListDTO.setQuestionKey("question");
    templateCardDTO.setSelectList(Lists.newArrayList(selectListDTO));
    final String json = templateCardMessage.toJson();
    System.out.println(json);
    String expectedJson = "{\"userids\":[\"userid1\",\"userid2\"],\"agentid\":0,\"response_code\":\"xihrjiohewirfhwripsiqwjerdio_dhweu\",\"template_card\":{\"select_list\":[{\"question_key\":\"question\",\"selected_id\":\"id\"}]}}";

    assertThat(json).isEqualTo(GsonParser.parse(expectedJson).toString());
  }

}
