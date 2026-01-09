package me.chanjar.weixin.common.util.json;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.Reader;

/**
 * @author niefy
 */
public class GsonParser {

  public static JsonObject parse(String json) {
    return new JsonParser().parse(json).getAsJsonObject();
  }

  public static JsonObject parse(Reader json) {
    return new JsonParser().parse(json).getAsJsonObject();
  }

  public static JsonObject parse(JsonReader json) {
    return new JsonParser().parse(json).getAsJsonObject();
  }
}
