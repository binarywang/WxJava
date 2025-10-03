package me.chanjar.weixin.cp.util.json;

import me.chanjar.weixin.cp.bean.WxCpUser;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <pre>
 *
 * Created by Binary Wang on 2018/9/16.
 * </pre>
 *
 * @author BinaryWang
 */
public class WxCpUserGsonAdapterTest {

  /**
   * Test deserialize.
   */
  @Test
  public void testDeserialize() {
    final String userJson = "{\n" +
      "    \"errcode\": 0,\n" +
      "    \"errmsg\": \"ok\",\n" +
      "    \"userid\": \"zhangsan\",\n" +
      "    \"name\": \"李四\",\n" +
      "    \"department\": [1, 2],\n" +
      "    \"order\": [1, 2],\n" +
      "    \"position\": \"后台工程师\",\n" +
      "    \"mobile\": \"15913215421\",\n" +
      "    \"gender\": \"1\",\n" +
      "    \"email\": \"zhangsan@gzdev.com\",\n" +
      "    \"isleader\": 1,\n" +
      "    \"avatar\": \"http://wx.qlogo" +
      ".cn/mmopen/ajNVdqHZLLA3WJ6DSZUfiakYe37PKnQhBIeOQBO4czqrnZDS79FH5Wm5m4X69TBicnHFlhiafvDwklOpZeXYQQ2icg/0\",\n" +
      "    \"telephone\": \"020-123456\",\n" +
      "    \"address\": \"广州市海珠区新港中路\"," +
      "    \"enable\": 1,\n" +
      "    \"alias\": \"jackzhang\",\n" +
      "    \"extattr\": {\n" +
      "        \"attrs\": [\n" +
      "            {\n" +
      "                \"type\": 0,\n" +
      "                \"name\": \"文本名称\",\n" +
      "                \"text\": {\n" +
      "                    \"value\": \"文本\"\n" +
      "                }\n" +
      "            },\n" +
      "            {\n" +
      "                \"type\": 1,\n" +
      "                \"name\": \"网页名称\",\n" +
      "                \"web\": {\n" +
      "                    \"url\": \"http://www.test.com\",\n" +
      "                    \"title\": \"标题\"\n" +
      "                }\n" +
      "            }\n" +
      "        ]\n" +
      "    }," +
      "    \"status\": 1,\n" +
      "    \"qr_code\": \"https://open.work.weixin.qq.com/wwopen/userQRCode?vcode=xxx\",\n" +
      "    \"external_position\": \"高级产品经理\",\n" +
      "    \"external_profile\": {\n" +
      "        \"external_corp_name\": \"企业简称\",\n" +
      "        \"external_attr\": [\n" +
      "            {\n" +
      "                \"type\": 0,\n" +
      "                \"name\": \"文本名称\",\n" +
      "                \"text\": {\n" +
      "                    \"value\": \"文本\"\n" +
      "                }\n" +
      "            },\n" +
      "            {\n" +
      "                \"type\": 1,\n" +
      "                \"name\": \"网页名称\",\n" +
      "                \"web\": {\n" +
      "                    \"url\": \"http://www.test.com\",\n" +
      "                    \"title\": \"标题\"\n" +
      "                }\n" +
      "            },\n" +
      "            {\n" +
      "                \"type\": 2,\n" +
      "                \"name\": \"测试app\",\n" +
      "                \"miniprogram\": {\n" +
      "                    \"appid\": \"wx8bd8012614784fake\",\n" +
      "                    \"pagepath\": \"/index\",\n" +
      "                    \"title\": \"my miniprogram\"\n" +
      "                }\n" +
      "            }\n" +
      "        ]\n" +
      "    }" +
      "}";

    final WxCpUser user = WxCpUser.fromJson(userJson);
    assertThat(user).isNotNull();

    assertThat(user.getOrders()).isNotEmpty();
    assertThat(user.getOrders()).hasSize(2);
    assertThat(user.getOrders()[0]).isEqualTo(1);
    assertThat(user.getOrders()[1]).isEqualTo(2);

    assertThat(user.getAddress()).isEqualTo("广州市海珠区新港中路");
    assertThat(user.getAlias()).isEqualTo("jackzhang");

    assertThat(user.getExtAttrs()).isNotEmpty();

    final WxCpUser.Attr extraAttr1 = user.getExtAttrs().get(0);
    assertThat(extraAttr1.getType()).isEqualTo(0);
    assertThat(extraAttr1.getName()).isEqualTo("文本名称");
    assertThat(extraAttr1.getTextValue()).isEqualTo("文本");

    final WxCpUser.Attr extraAttr2 = user.getExtAttrs().get(1);
    assertThat(extraAttr2.getType()).isEqualTo(1);
    assertThat(extraAttr2.getName()).isEqualTo("网页名称");
    assertThat(extraAttr2.getWebTitle()).isEqualTo("标题");
    assertThat(extraAttr2.getWebUrl()).isEqualTo("http://www.test.com");

    assertThat(user.getExternalPosition()).isEqualTo("高级产品经理");
    assertThat(user.getExternalCorpName()).isEqualTo("企业简称");

    assertThat(user.getExternalAttrs()).isNotEmpty();

    final WxCpUser.ExternalAttribute externalAttr1 = user.getExternalAttrs().get(0);
    assertThat(externalAttr1.getType()).isEqualTo(0);
    assertThat(externalAttr1.getName()).isEqualTo("文本名称");
    assertThat(externalAttr1.getValue()).isEqualTo("文本");

    final WxCpUser.ExternalAttribute externalAttr2 = user.getExternalAttrs().get(1);
    assertThat(externalAttr2.getType()).isEqualTo(1);
    assertThat(externalAttr2.getName()).isEqualTo("网页名称");
    assertThat(externalAttr2.getUrl()).isEqualTo("http://www.test.com");
    assertThat(externalAttr2.getTitle()).isEqualTo("标题");

    final WxCpUser.ExternalAttribute externalAttr3 = user.getExternalAttrs().get(2);
    assertThat(externalAttr3.getType()).isEqualTo(2);
    assertThat(externalAttr3.getName()).isEqualTo("测试app");
    assertThat(externalAttr3.getAppid()).isEqualTo("wx8bd8012614784fake");
    assertThat(externalAttr3.getPagePath()).isEqualTo("/index");
    assertThat(externalAttr3.getTitle()).isEqualTo("my miniprogram");

  }

  /**
   * Test serialize.
   */
  @Test
  public void testSerialize() {
    WxCpUser user = new WxCpUser();
    user.setOrders(new Integer[]{1, 2});
    user.addExtAttr(WxCpUser.Attr.builder()
      .type(0)
      .name("文本名称")
      .textValue("文本")
      .build());

    user.addExternalAttr(WxCpUser.ExternalAttribute.builder()
      .type(0)
      .name("文本名称")
      .value("文本")
      .build());
    user.addExternalAttr(WxCpUser.ExternalAttribute.builder()
      .type(1)
      .name("网页名称")
      .url("http://www.test.com")
      .title("标题")
      .build());
    user.addExternalAttr(WxCpUser.ExternalAttribute.builder()
      .type(2)
      .name("测试app")
      .appid("wx8bd80126147df384")
      .pagePath("/index")
      .title("my miniprogram")
      .build());

    assertThat(user.toJson()).isEqualTo("{\"order\":[1,2]," +
      "\"extattr\":{\"attrs\":[{\"type\":0,\"name\":\"文本名称\",\"text\":{\"value\":\"文本\"}}]}," +
      "\"external_profile\":{\"external_attr\":" +
      "[{\"type\":0,\"name\":\"文本名称\",\"text\":{\"value\":\"文本\"}}," +
      "{\"type\":1,\"name\":\"网页名称\",\"web\":{\"url\":\"http://www.test.com\",\"title\":\"标题\"}}," +
      "{\"type\":2,\"name\":\"测试app\"," +
      "\"miniprogram\":{\"appid\":\"wx8bd80126147df384\",\"pagepath\":\"/index\",\"title\":\"my miniprogram\"}}]}}");
  }

  /**
   * Test directLeader empty array serialization.
   * This test verifies that empty directLeader arrays are included in JSON as "direct_leader":[]
   * instead of being omitted, which is required for WeChat Work API to reset user direct leaders.
   */
  @Test
  public void testDirectLeaderEmptyArraySerialization() {
    WxCpUser user = new WxCpUser();
    user.setUserId("testuser");
    user.setName("Test User");
    
    // Test with empty array - should be serialized as "direct_leader":[]
    user.setDirectLeader(new String[]{});
    String json = user.toJson();
    assertThat(json).contains("\"direct_leader\":[]");
    
    // Test with null - should not include direct_leader field
    user.setDirectLeader(null);
    json = user.toJson();
    assertThat(json).doesNotContain("direct_leader");
    
    // Test with non-empty array - should be serialized normally
    user.setDirectLeader(new String[]{"leader1", "leader2"});
    json = user.toJson();
    assertThat(json).contains("\"direct_leader\":[\"leader1\",\"leader2\"]");
  }
}
