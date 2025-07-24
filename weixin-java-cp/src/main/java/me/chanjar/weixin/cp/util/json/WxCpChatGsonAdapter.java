package me.chanjar.weixin.cp.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.cp.bean.WxCpChat;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 群聊适配器
 *
 * @author gaigeshen
 */
public class WxCpChatGsonAdapter implements JsonSerializer<WxCpChat>, JsonDeserializer<WxCpChat> {

  public static final String FIELD_CHAT_ID = "chatid";
  public static final String FIELD_NAME = "name";
  public static final String FIELD_OWNER = "owner";
  public static final String FIELD_USER_LIST = "userlist";

  @Override
  public JsonElement serialize(WxCpChat chat, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject json = new JsonObject();
    addPropertyIfNotNull(json, FIELD_CHAT_ID, chat.getId());
    addPropertyIfNotNull(json, FIELD_NAME, chat.getName());
    addPropertyIfNotNull(json, FIELD_OWNER, chat.getOwner());
    if (chat.getUsers() != null && !chat.getUsers().isEmpty()) {
      JsonArray users = new JsonArray();
      chat.getUsers().forEach(users::add);
      json.add(FIELD_USER_LIST, users);
    }
    return json;
  }

  private void addPropertyIfNotNull(JsonObject json, String key, String value) {
    if (value != null) {
      json.addProperty(key, value);
    }
  }

  @Override
  public WxCpChat deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject chatJson = json.getAsJsonObject();
    WxCpChat chat = new WxCpChat();
    chat.setId(GsonHelper.getAsString(chatJson.get("chatid")));
    chat.setName(GsonHelper.getAsString(chatJson.get("name")));
    chat.setOwner(GsonHelper.getAsString(chatJson.get("owner")));
    JsonArray usersJson = chatJson.getAsJsonArray("userlist");
    if (usersJson != null && !usersJson.isEmpty()) {
      List<String> users = new ArrayList<>(usersJson.size());
      usersJson.forEach(e -> users.add(e.getAsString()));
      chat.setUsers(users);
    }
    return chat;
  }

}
