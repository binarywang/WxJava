package me.chanjar.weixin.open.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.open.bean.result.WxOpenAuthorizerListResult;

import java.lang.reflect.Type;
import java.util.*;

/**
 * @author robgao Email 315789501@qq.com
 */
public class WxOpenAuthorizerListResultGsonAdapter implements JsonDeserializer<WxOpenAuthorizerListResult> {

  private static final String AUTHORIZER_APPID = "authorizer_appid";
  private static final String REFRESH_TOKEN = "refresh_token";
  private static final String AUTH_TIME = "auth_time";

  @Override
  public WxOpenAuthorizerListResult deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    JsonObject jsonObject = jsonElement.getAsJsonObject();

    WxOpenAuthorizerListResult wxOpenAuthorizerListResult = new WxOpenAuthorizerListResult();
    wxOpenAuthorizerListResult.setTotalCount(GsonHelper.getInteger(jsonObject, "total_count").intValue());

    List<Map<String, String>> list = new ArrayList<>();

    for (JsonElement element : jsonObject.getAsJsonArray("list")) {
      JsonObject authorizer = element.getAsJsonObject();
      Map<String, String> authorizerMap = new HashMap<>(10);

      authorizerMap.put(AUTHORIZER_APPID, GsonHelper.getString(authorizer, AUTHORIZER_APPID));
      authorizerMap.put(REFRESH_TOKEN, GsonHelper.getString(authorizer, REFRESH_TOKEN));
      authorizerMap.put(AUTH_TIME, GsonHelper.getString(authorizer, AUTH_TIME));
      list.add(authorizerMap);
    }
    wxOpenAuthorizerListResult.setList(list);
    return wxOpenAuthorizerListResult;
  }
}
