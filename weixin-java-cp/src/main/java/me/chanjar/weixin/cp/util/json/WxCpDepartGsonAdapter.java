package me.chanjar.weixin.cp.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.cp.bean.WxCpDepart;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;

/**
 * WxCpDepart的gson适配器.
 *
 * @author Daniel Qian
 */
public class WxCpDepartGsonAdapter implements JsonSerializer<WxCpDepart>, JsonDeserializer<WxCpDepart> {
  private static final String ID = "id";
  private static final String NAME = "name";
  private static final String EN_NAME = "name_en";
  private static final String DEPARTMENT_LEADER = "department_leader";
  private static final String PARENT_ID = "parentid";
  private static final String ORDER = "order";

  @Override
  public JsonElement serialize(WxCpDepart group, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject json = new JsonObject();
    addPropertyIfNotNull(json, ID, group.getId());
    addPropertyIfNotNull(json, NAME, group.getName());
    addPropertyIfNotNull(json, EN_NAME, group.getEnName());
    if (group.getDepartmentLeader() != null && group.getDepartmentLeader().length > 0) {
      JsonArray jsonArray = new JsonArray();
      Arrays.stream(group.getDepartmentLeader()).filter(Objects::nonNull).forEach(jsonArray::add);
      json.add(DEPARTMENT_LEADER, jsonArray);
    }
    addPropertyIfNotNull(json, PARENT_ID, group.getParentId());
    addPropertyIfNotNull(json, ORDER, group.getOrder());
    return json;
  }

  private void addPropertyIfNotNull(JsonObject json, String key, Object value) {
    if (value != null) {
      if (value instanceof Number) {
        json.addProperty(key, (Number) value);
      } else {
        json.addProperty(key, value.toString());
      }
    }
  }

  @Override
  public WxCpDepart deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException {
    WxCpDepart depart = new WxCpDepart();
    JsonObject departJson = json.getAsJsonObject();
    depart.setId(GsonHelper.getAsLong(departJson.get(ID)));
    depart.setName(GsonHelper.getAsString(departJson.get(NAME)));
    depart.setEnName(GsonHelper.getAsString(departJson.get(EN_NAME)));
    JsonArray jsonArray = departJson.getAsJsonArray(DEPARTMENT_LEADER);
    if (jsonArray != null && !jsonArray.isJsonNull()) {
      String[] departments = new String[jsonArray.size()];
      for (int i = 0; i < jsonArray.size(); i++) {
        departments[i] = jsonArray.get(i).getAsString();
      }
      depart.setDepartmentLeader(departments);
    }
    depart.setOrder(GsonHelper.getAsLong(departJson.get(ORDER)));
    depart.setParentId(GsonHelper.getAsLong(departJson.get(PARENT_ID)));
    return depart;
  }

}
