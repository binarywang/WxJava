/*
 * KINGSTAR MEDIA SOLUTIONS Co.,LTD. Copyright c 2005-2013. All rights reserved.
 *
 * This source code is the property of KINGSTAR MEDIA SOLUTIONS LTD. It is intended
 * only for the use of KINGSTAR MEDIA application development. Reengineering, reproduction
 * arose from modification of the original source, or other redistribution of this source
 * is not permitted without written permission of the KINGSTAR MEDIA SOLUTIONS LTD.
 */
package me.chanjar.weixin.common.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.bean.menu.WxMenuRule;

import java.lang.reflect.Type;


/**
 * @author Daniel Qian
 */
public class WxMenuGsonAdapter implements JsonSerializer<WxMenu>, JsonDeserializer<WxMenu> {

  @Override
  public JsonElement serialize(WxMenu menu, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject json = new JsonObject();

    JsonArray buttonArray = new JsonArray();
    for (WxMenuButton button : menu.getButtons()) {
      JsonObject buttonJson = convertToJson(button);
      buttonArray.add(buttonJson);
    }
    json.add("button", buttonArray);

    if (menu.getMatchRule() != null) {
      json.add("matchrule", convertToJson(menu.getMatchRule()));
    }

    return json;
  }

  protected JsonObject convertToJson(WxMenuButton button) {
    JsonObject buttonJson = new JsonObject();
    buttonJson.addProperty("type", button.getType());
    buttonJson.addProperty("name", button.getName());
    buttonJson.addProperty("key", button.getKey());
    buttonJson.addProperty("url", button.getUrl());
    buttonJson.addProperty("media_id", button.getMediaId());
    buttonJson.addProperty("article_id", button.getArticleId());
    buttonJson.addProperty("appid", button.getAppId());
    buttonJson.addProperty("pagepath", button.getPagePath());
    if (button.getSubButtons() != null && !button.getSubButtons().isEmpty()) {
      JsonArray buttonArray = new JsonArray();
      for (WxMenuButton sub_button : button.getSubButtons()) {
        buttonArray.add(convertToJson(sub_button));
      }
      buttonJson.add("sub_button", buttonArray);
    }
    return buttonJson;
  }

  protected JsonObject convertToJson(WxMenuRule menuRule) {
    JsonObject matchRule = new JsonObject();
    matchRule.addProperty("tag_id", menuRule.getTagId());
    matchRule.addProperty("sex", menuRule.getSex());
    matchRule.addProperty("country", menuRule.getCountry());
    matchRule.addProperty("province", menuRule.getProvince());
    matchRule.addProperty("city", menuRule.getCity());
    matchRule.addProperty("client_platform_type", menuRule.getClientPlatformType());
    matchRule.addProperty("language", menuRule.getLanguage());
    return matchRule;
  }

  @Deprecated
  private WxMenuRule convertToRule(JsonObject json) {
    WxMenuRule menuRule = new WxMenuRule();
    //变态的微信接口，这里居然反人类的使用和序列化时不一样的名字
    //menuRule.setTagId(GsonHelper.getString(json,"tag_id"));
    menuRule.setTagId(GsonHelper.getString(json, "group_id"));
    menuRule.setSex(GsonHelper.getString(json, "sex"));
    menuRule.setCountry(GsonHelper.getString(json, "country"));
    menuRule.setProvince(GsonHelper.getString(json, "province"));
    menuRule.setCity(GsonHelper.getString(json, "city"));
    menuRule.setClientPlatformType(GsonHelper.getString(json, "client_platform_type"));
    menuRule.setLanguage(GsonHelper.getString(json, "language"));
    return menuRule;
  }

  @Override
  public WxMenu deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    /*
     * 操蛋的微信
     * 创建菜单时是 { button : ... }
     * 查询菜单时是 { menu : { button : ... } }
     * 现在企业号升级为企业微信后，没有此问题，因此需要单独处理
     */
    JsonArray buttonsJson = json.getAsJsonObject().get("menu").getAsJsonObject().get("button").getAsJsonArray();
    return this.buildMenuFromJson(buttonsJson);
  }

  protected WxMenu buildMenuFromJson(JsonArray buttonsJson) {
    WxMenu menu = new WxMenu();
    for (int i = 0; i < buttonsJson.size(); i++) {
      JsonObject buttonJson = buttonsJson.get(i).getAsJsonObject();
      WxMenuButton button = convertFromJson(buttonJson);
      menu.getButtons().add(button);
      if (buttonJson.get("sub_button") == null || buttonJson.get("sub_button").isJsonNull()) {
        continue;
      }
      JsonArray sub_buttonsJson = buttonJson.get("sub_button").getAsJsonArray();
      for (int j = 0; j < sub_buttonsJson.size(); j++) {
        JsonObject sub_buttonJson = sub_buttonsJson.get(j).getAsJsonObject();
        button.getSubButtons().add(convertFromJson(sub_buttonJson));
      }
    }
    return menu;
  }

  protected WxMenuButton convertFromJson(JsonObject json) {
    WxMenuButton button = new WxMenuButton();
    button.setName(GsonHelper.getString(json, "name"));
    button.setKey(GsonHelper.getString(json, "key"));
    button.setUrl(GsonHelper.getString(json, "url"));
    button.setType(GsonHelper.getString(json, "type"));
    button.setMediaId(GsonHelper.getString(json, "media_id"));
    button.setArticleId(GsonHelper.getString(json, "article_id"));
    button.setAppId(GsonHelper.getString(json, "appid"));
    button.setPagePath(GsonHelper.getString(json, "pagepath"));
    return button;
  }

}
