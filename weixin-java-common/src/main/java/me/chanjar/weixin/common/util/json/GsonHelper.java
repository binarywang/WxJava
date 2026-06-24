package me.chanjar.weixin.common.util.json;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.error.WxRuntimeException;

import java.util.List;

public class GsonHelper {

  public static boolean isNull(JsonElement element) {
    return element == null || element.isJsonNull();
  }

  public static boolean isNotNull(JsonElement element) {
    return !isNull(element);
  }

  public static Long getLong(JsonObject json, String property) {
    return getAsLong(json.get(property));
  }

  public static long getPrimitiveLong(JsonObject json, String property) {
    return getAsPrimitiveLong(json.get(property));
  }

  public static Integer getInteger(JsonObject json, String property) {
    return getAsInteger(json.get(property));
  }

  public static int getPrimitiveInteger(JsonObject json, String property) {
    return getAsPrimitiveInt(json.get(property));
  }

  public static Double getDouble(JsonObject json, String property) {
    return getAsDouble(json.get(property));
  }

  public static double getPrimitiveDouble(JsonObject json, String property) {
    return getAsPrimitiveDouble(json.get(property));
  }

  public static Float getFloat(JsonObject json, String property) {
    return getAsFloat(json.get(property));
  }

  public static float getPrimitiveFloat(JsonObject json, String property) {
    return getAsPrimitiveFloat(json.get(property));
  }

  public static Boolean getBoolean(JsonObject json, String property) {
    return getAsBoolean(json.get(property));
  }

  public static String getString(JsonObject json, String property) {
    return getAsString(json.get(property));
  }

  public static String getAsString(JsonElement element) {
    return isNull(element) ? null : element.getAsString();
  }

  public static Long getAsLong(JsonElement element) {
    return isNull(element) ? null : element.getAsLong();
  }

  public static long getAsPrimitiveLong(JsonElement element) {
    Long r = getAsLong(element);
    return r == null ? 0L : r;
  }

  public static Integer getAsInteger(JsonElement element) {
    return isNull(element) ? null : element.getAsInt();
  }

  public static int getAsPrimitiveInt(JsonElement element) {
    Integer r = getAsInteger(element);
    return r == null ? 0 : r;
  }

  public static Boolean getAsBoolean(JsonElement element) {
    return isNull(element) ? null : element.getAsBoolean();
  }

  public static boolean getAsPrimitiveBool(JsonElement element) {
    Boolean r = getAsBoolean(element);
    return r != null && r;
  }

  public static Double getAsDouble(JsonElement element) {
    return isNull(element) ? null : element.getAsDouble();
  }

  public static double getAsPrimitiveDouble(JsonElement element) {
    Double r = getAsDouble(element);
    return r == null ? 0d : r;
  }

  public static Float getAsFloat(JsonElement element) {
    return isNull(element) ? null : element.getAsFloat();
  }

  public static float getAsPrimitiveFloat(JsonElement element) {
    Float r = getAsFloat(element);
    return r == null ? 0f : r;
  }

  public static Integer[] getIntArray(JsonObject o, String string) {
    JsonArray jsonArray = getAsJsonArray(o.get(string));
    if (jsonArray == null) {
      return null;
    }

    List<Integer> result = Lists.newArrayList();
    for (int i = 0; i < jsonArray.size(); i++) {
      result.add(jsonArray.get(i).getAsInt());
    }

    return result.toArray(new Integer[0]);
  }

  public static String[] getStringArray(JsonObject o, String string) {
    JsonArray jsonArray = getAsJsonArray(o.get(string));
    if (jsonArray == null) {
      return null;
    }

    List<String> result = Lists.newArrayList();
    for (int i = 0; i < jsonArray.size(); i++) {
      result.add(jsonArray.get(i).getAsString());
    }

    return result.toArray(new String[0]);
  }

  public static Long[] getLongArray(JsonObject o, String string) {
    JsonArray jsonArray = getAsJsonArray(o.get(string));
    if (jsonArray == null) {
      return null;
    }

    List<Long> result = Lists.newArrayList();
    for (int i = 0; i < jsonArray.size(); i++) {
      result.add(jsonArray.get(i).getAsLong());
    }

    return result.toArray(new Long[0]);
  }

  public static JsonArray getAsJsonArray(JsonElement element) {
    return (element == null || !element.isJsonArray()) ? null : element.getAsJsonArray();
  }

  /**
   * 快速构建JsonObject对象，批量添加一堆属性
   *
   * @param keyOrValue 包含key或value的数组
   * @return JsonObject对象.
   */
  public static JsonObject buildJsonObject(Object... keyOrValue) {
    JsonObject result = new JsonObject();
    put(result, keyOrValue);
    return result;
  }

  /**
   * 批量向JsonObject对象中添加属性
   *
   * @param jsonObject 原始JsonObject对象
   * @param keyOrValue 包含key或value的数组
   */
  public static void put(JsonObject jsonObject, Object... keyOrValue) {
    if (keyOrValue.length % 2 == 1) {
      throw new WxRuntimeException("参数个数必须为偶数");
    }

    for (int i = 0; i < keyOrValue.length / 2; i++) {
      final Object key = keyOrValue[2 * i];
      final Object value = keyOrValue[2 * i + 1];
      if (value == null) {
        jsonObject.add(key.toString(), null);
        continue;
      }

      if (value instanceof Boolean) {
        jsonObject.addProperty(key.toString(), (Boolean) value);
      } else if (value instanceof Character) {
        jsonObject.addProperty(key.toString(), (Character) value);
      } else if (value instanceof Number) {
        jsonObject.addProperty(key.toString(), (Number) value);
      } else if (value instanceof JsonElement) {
        jsonObject.add(key.toString(), (JsonElement) value);
      } else if (value instanceof List) {
        JsonArray array = new JsonArray();
        ((List<?>) value).forEach(a -> array.add(a.toString()));
        jsonObject.add(key.toString(), array);
      } else {
        jsonObject.add(key.toString(), WxGsonBuilder.create().toJsonTree(value));
      }
    }

  }
}
