package me.chanjar.weixin.cp.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.cp.bean.WxCpTag;

import java.lang.reflect.Type;

/**
 * The type Wx cp tag gson adapter.
 *
 * @author Daniel Qian
 */
public class WxCpTagGsonAdapter implements JsonSerializer<WxCpTag>, JsonDeserializer<WxCpTag> {

  private static final String TAG_ID = "tagid";
  private static final String TAG_NAME = "tagname";

  @Override
  public JsonElement serialize(WxCpTag tag, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject o = new JsonObject();
    addPropertyIfNotNull(o, TAG_ID, tag.getId());
    addPropertyIfNotNull(o, TAG_NAME, tag.getName());
    return o;
  }

  private void addPropertyIfNotNull(JsonObject obj, String key, String value) {
    if (value != null) {
      obj.addProperty(key, value);
    }
  }

  @Override
  public WxCpTag deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException {
    JsonObject jsonObject = json.getAsJsonObject();
    return new WxCpTag(GsonHelper.getString(jsonObject, TAG_ID), GsonHelper.getString(jsonObject, TAG_NAME));
  }

}
