package me.chanjar.weixin.common.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.bean.menu.WxMenuRule;

import java.lang.reflect.Type;
import java.util.Optional;


/**
 * @author Daniel Qian
 */
public class WxMenuGsonAdapter implements JsonSerializer<WxMenu>, JsonDeserializer<WxMenu> {

  // JSON字段常量定义
  private static final String FIELD_BUTTON = "button";
  private static final String FIELD_MATCH_RULE = "matchrule";
  private static final String FIELD_SUB_BUTTON = "sub_button";
  private static final String FIELD_MENU = "menu";

  // 菜单按钮字段常量
  private static final String FIELD_TYPE = "type";
  private static final String FIELD_NAME = "name";
  private static final String FIELD_KEY = "key";
  private static final String FIELD_URL = "url";
  private static final String FIELD_MEDIA_ID = "media_id";
  private static final String FIELD_ARTICLE_ID = "article_id";
  private static final String FIELD_APP_ID = "appid";
  private static final String FIELD_PAGE_PATH = "pagepath";

  // 菜单规则字段常量
  private static final String FIELD_TAG_ID = "tag_id";
  private static final String FIELD_SEX = "sex";
  private static final String FIELD_COUNTRY = "country";
  private static final String FIELD_PROVINCE = "province";
  private static final String FIELD_CITY = "city";
  private static final String FIELD_CLIENT_PLATFORM_TYPE = "client_platform_type";
  private static final String FIELD_LANGUAGE = "language";

  @Override
  public JsonElement serialize(WxMenu menu, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject json = new JsonObject();
    JsonArray buttonArray = new JsonArray();
    Optional.ofNullable(menu.getButtons())
        .ifPresent(buttons -> buttons.stream()
            .map(this::convertToJson)
            .forEach(buttonArray::add));
    json.add(FIELD_BUTTON, buttonArray);
    if (menu.getMatchRule() != null) {
      json.add(FIELD_MATCH_RULE, convertToJson(menu.getMatchRule()));
    }
    return json;
  }

  protected JsonObject convertToJson(WxMenuButton button) {
    JsonObject buttonJson = new JsonObject();
    addPropertyIfNotNull(buttonJson, FIELD_TYPE, button.getType());
    addPropertyIfNotNull(buttonJson, FIELD_NAME, button.getName());
    addPropertyIfNotNull(buttonJson, FIELD_KEY, button.getKey());
    addPropertyIfNotNull(buttonJson, FIELD_URL, button.getUrl());
    addPropertyIfNotNull(buttonJson, FIELD_MEDIA_ID, button.getMediaId());
    addPropertyIfNotNull(buttonJson, FIELD_ARTICLE_ID, button.getArticleId());
    addPropertyIfNotNull(buttonJson, FIELD_APP_ID, button.getAppId());
    addPropertyIfNotNull(buttonJson, FIELD_PAGE_PATH, button.getPagePath());
    if (button.getSubButtons() != null && !button.getSubButtons().isEmpty()) {
      JsonArray buttonArray = new JsonArray();
      button.getSubButtons().stream()
          .map(this::convertToJson)
          .forEach(buttonArray::add);
      buttonJson.add(FIELD_SUB_BUTTON, buttonArray);
    }
    return buttonJson;
  }

  protected JsonObject convertToJson(WxMenuRule menuRule) {
    JsonObject matchRule = new JsonObject();
    addPropertyIfNotNull(matchRule, FIELD_TAG_ID, menuRule.getTagId());
    addPropertyIfNotNull(matchRule, FIELD_SEX, menuRule.getSex());
    addPropertyIfNotNull(matchRule, FIELD_COUNTRY, menuRule.getCountry());
    addPropertyIfNotNull(matchRule, FIELD_PROVINCE, menuRule.getProvince());
    addPropertyIfNotNull(matchRule, FIELD_CITY, menuRule.getCity());
    addPropertyIfNotNull(matchRule, FIELD_CLIENT_PLATFORM_TYPE, menuRule.getClientPlatformType());
    addPropertyIfNotNull(matchRule, FIELD_LANGUAGE, menuRule.getLanguage());
    return matchRule;
  }

  private void addPropertyIfNotNull(JsonObject obj, String key, String value) {
    if (value != null) {
      obj.addProperty(key, value);
    }
  }

  @Override
  public WxMenu deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject root = json.getAsJsonObject();
    JsonArray buttonsJson = null;
    if (root.has(FIELD_MENU)) {
      JsonObject menuObj = root.getAsJsonObject(FIELD_MENU);
      buttonsJson = menuObj.getAsJsonArray(FIELD_BUTTON);
    } else if (root.has(FIELD_BUTTON)) {
      buttonsJson = root.getAsJsonArray(FIELD_BUTTON);
    }
    if (buttonsJson == null) {
      throw new JsonParseException("No button array found in menu JSON");
    }
    return buildMenuFromJson(buttonsJson);
  }

  protected WxMenu buildMenuFromJson(JsonArray buttonsJson) {
    WxMenu menu = new WxMenu();
    for (JsonElement btnElem : buttonsJson) {
      JsonObject buttonJson = btnElem.getAsJsonObject();
      WxMenuButton button = convertFromJson(buttonJson);
      menu.getButtons().add(button);
      if (buttonJson.has(FIELD_SUB_BUTTON) && buttonJson.get(FIELD_SUB_BUTTON).isJsonArray()) {
        JsonArray sub_buttonsJson = buttonJson.getAsJsonArray(FIELD_SUB_BUTTON);
        for (JsonElement subBtnElem : sub_buttonsJson) {
          button.getSubButtons().add(convertFromJson(subBtnElem.getAsJsonObject()));
        }
      }
    }
    return menu;
  }

  protected WxMenuButton convertFromJson(JsonObject json) {
    WxMenuButton button = new WxMenuButton();
    button.setName(GsonHelper.getString(json, FIELD_NAME));
    button.setKey(GsonHelper.getString(json, FIELD_KEY));
    button.setUrl(GsonHelper.getString(json, FIELD_URL));
    button.setType(GsonHelper.getString(json, FIELD_TYPE));
    button.setMediaId(GsonHelper.getString(json, FIELD_MEDIA_ID));
    button.setArticleId(GsonHelper.getString(json, FIELD_ARTICLE_ID));
    button.setAppId(GsonHelper.getString(json, FIELD_APP_ID));
    button.setPagePath(GsonHelper.getString(json, FIELD_PAGE_PATH));
    return button;
  }

}
