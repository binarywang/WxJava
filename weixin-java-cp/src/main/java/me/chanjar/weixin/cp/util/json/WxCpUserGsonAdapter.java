package me.chanjar.weixin.cp.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.cp.bean.Gender;
import me.chanjar.weixin.cp.bean.WxCpUser;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.stream.IntStream;

import static me.chanjar.weixin.cp.bean.WxCpUser.*;

/**
 * cp user gson adapter.
 *
 * @author Daniel Qian
 */
public class WxCpUserGsonAdapter implements JsonDeserializer<WxCpUser>, JsonSerializer<WxCpUser> {
  private static final String EXTERNAL_PROFILE = "external_profile";
  private static final String EXTERNAL_ATTR = "external_attr";
  private static final String EXTRA_ATTR = "extattr";
  private static final String EXTERNAL_POSITION = "external_position";
  private static final String DEPARTMENT = "department";
  private static final String EXTERNAL_CORP_NAME = "external_corp_name";
  private static final String WECHAT_CHANNELS = "wechat_channels";
  private static final String ORDER = "order";
  private static final String POSITIONS = "positions";
  private static final String USER_ID = "userid";
  private static final String NEW_USER_ID = "new_userid";
  private static final String NAME = "name";
  private static final String POSITION = "position";
  private static final String MOBILE = "mobile";
  private static final String GENDER = "gender";
  private static final String EMAIL = "email";
  private static final String BIZ_MAIL = "biz_mail";
  private static final String AVATAR = "avatar";
  private static final String THUMB_AVATAR = "thumb_avatar";
  private static final String ADDRESS = "address";
  private static final String AVATAR_MEDIAID = "avatar_mediaid";
  private static final String STATUS = "status";
  private static final String ENABLE = "enable";
  private static final String ALIAS = "alias";
  private static final String IS_LEADER = "isleader";
  private static final String IS_LEADER_IN_DEPT = "is_leader_in_dept";
  private static final String HIDE_MOBILE = "hide_mobile";
  private static final String ENGLISH_NAME = "english_name";
  private static final String TELEPHONE = "telephone";
  private static final String QR_CODE = "qr_code";
  private static final String TO_INVITE = "to_invite";
  private static final String OPEN_USER_ID = "open_userid";
  private static final String MAIN_DEPARTMENT = "main_department";
  private static final String DIRECT_LEADER = "direct_leader";
  private static final String TYPE = "type";
  private static final String VALUE = "value";
  private static final String TEXT = "text";
  private static final String WEB = "web";
  private static final String MINIPROGRAM = "miniprogram";
  private static final String URL = "url";
  private static final String TITLE = "title";
  private static final String APPID = "appid";
  private static final String PAGE_PATH = "pagepath";
  private static final String ATTRS = "attrs";
  private static final String NICKNAME = "nickname";

  @Override
  public WxCpUser deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject o = json.getAsJsonObject();
    WxCpUser user = new WxCpUser();

    user.setDepartIds(parseJsonArrayToLongArray(o, DEPARTMENT));
    user.setOrders(parseJsonArrayToIntegerArray(o, ORDER));
    user.setPositions(parseJsonArrayToStringArray(o, POSITIONS));

    user.setUserId(GsonHelper.getString(o, USER_ID));
    user.setName(GsonHelper.getString(o, NAME));
    user.setPosition(GsonHelper.getString(o, POSITION));
    user.setMobile(GsonHelper.getString(o, MOBILE));
    user.setGender(Gender.fromCode(GsonHelper.getString(o, GENDER)));
    user.setEmail(GsonHelper.getString(o, EMAIL));
    user.setBizMail(GsonHelper.getString(o, BIZ_MAIL));
    user.setAvatar(GsonHelper.getString(o, AVATAR));
    user.setThumbAvatar(GsonHelper.getString(o, THUMB_AVATAR));
    user.setAddress(GsonHelper.getString(o, ADDRESS));
    user.setAvatarMediaId(GsonHelper.getString(o, AVATAR_MEDIAID));
    user.setStatus(GsonHelper.getInteger(o, STATUS));
    user.setEnable(GsonHelper.getInteger(o, ENABLE));
    user.setAlias(GsonHelper.getString(o, ALIAS));
    user.setIsLeader(GsonHelper.getInteger(o, IS_LEADER));
    user.setIsLeaderInDept(GsonHelper.getIntArray(o, IS_LEADER_IN_DEPT));
    user.setHideMobile(GsonHelper.getInteger(o, HIDE_MOBILE));
    user.setEnglishName(GsonHelper.getString(o, ENGLISH_NAME));
    user.setTelephone(GsonHelper.getString(o, TELEPHONE));
    user.setQrCode(GsonHelper.getString(o, QR_CODE));
    user.setToInvite(GsonHelper.getBoolean(o, TO_INVITE));
    user.setOpenUserId(GsonHelper.getString(o, OPEN_USER_ID));
    user.setMainDepartment(GsonHelper.getString(o, MAIN_DEPARTMENT));
    user.setDirectLeader(GsonHelper.getStringArray(o, DIRECT_LEADER));

    if (GsonHelper.isNotNull(o.get(EXTRA_ATTR))) {
      this.buildExtraAttrs(o, user);
    }

    if (GsonHelper.isNotNull(o.get(EXTERNAL_PROFILE))) {
      user.setExternalCorpName(GsonHelper.getString(o.getAsJsonObject().get(EXTERNAL_PROFILE).getAsJsonObject(),
        EXTERNAL_CORP_NAME));
      JsonElement jsonElement = o.get(EXTERNAL_PROFILE).getAsJsonObject().get(WECHAT_CHANNELS);
      if (jsonElement != null) {
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        user.setWechatChannels(WechatChannels.builder().nickname(GsonHelper.getString(asJsonObject, NICKNAME)).status(GsonHelper.getInteger(asJsonObject, STATUS)).build());
      }

      this.buildExternalAttrs(o, user);
    }

    user.setExternalPosition(GsonHelper.getString(o, EXTERNAL_POSITION));

    return user;
  }

  private Long[] parseJsonArrayToLongArray(JsonObject o, String key) {
    JsonElement element = o.get(key);
    if (element == null || !element.isJsonArray()) {
      return null;
    }
    JsonArray jsonArray = element.getAsJsonArray();
    return IntStream.range(0, jsonArray.size())
        .mapToObj(i -> jsonArray.get(i).getAsLong())
        .toArray(Long[]::new);
  }

  private Integer[] parseJsonArrayToIntegerArray(JsonObject o, String key) {
    JsonElement element = o.get(key);
    if (element == null || !element.isJsonArray()) {
      return null;
    }
    JsonArray jsonArray = element.getAsJsonArray();
    return IntStream.range(0, jsonArray.size())
        .mapToObj(i -> jsonArray.get(i).getAsInt())
        .toArray(Integer[]::new);
  }

  private String[] parseJsonArrayToStringArray(JsonObject o, String key) {
    JsonElement element = o.get(key);
    if (element == null || !element.isJsonArray()) {
      return null;
    }
    JsonArray jsonArray = element.getAsJsonArray();
    return IntStream.range(0, jsonArray.size())
        .mapToObj(i -> jsonArray.get(i).getAsString())
        .toArray(String[]::new);
  }

  private void buildExtraAttrs(JsonObject o, WxCpUser user) {
    JsonArray attrJsonElements = o.get(EXTRA_ATTR).getAsJsonObject().get(ATTRS).getAsJsonArray();
    for (JsonElement attrJsonElement : attrJsonElements) {
      final Integer type = GsonHelper.getInteger(attrJsonElement.getAsJsonObject(), TYPE);
      final Attr attr = new Attr().setType(type)
        .setName(GsonHelper.getString(attrJsonElement.getAsJsonObject(), NAME));
      user.getExtAttrs().add(attr);

      if (type == null) {
        attr.setTextValue(GsonHelper.getString(attrJsonElement.getAsJsonObject(), VALUE));
        continue;
      }

      switch (type) {
        case 0: {
          JsonElement textJsonElement = attrJsonElement.getAsJsonObject().get(TEXT);
          if (textJsonElement != null && !textJsonElement.isJsonNull() && textJsonElement.isJsonObject()) {
            attr.setTextValue(GsonHelper.getString(textJsonElement.getAsJsonObject(), VALUE));
          } else {
            attr.setTextValue(null); // Clear or set a default value to avoid stale data
          }
          break;
        }
        case 1: {
          final JsonObject web = attrJsonElement.getAsJsonObject().get(WEB).getAsJsonObject();
          attr.setWebTitle(GsonHelper.getString(web, TITLE))
            .setWebUrl(GsonHelper.getString(web, URL));
          break;
        }
        default://ignored
      }
    }
  }

  private void buildExternalAttrs(JsonObject o, WxCpUser user) {
    JsonElement jsonElement = o.get(EXTERNAL_PROFILE).getAsJsonObject().get(EXTERNAL_ATTR);
    if (jsonElement == null) {
      return;
    }

    JsonArray attrJsonElements = jsonElement.getAsJsonArray();
    for (JsonElement element : attrJsonElements) {
      final Integer type = GsonHelper.getInteger(element.getAsJsonObject(), TYPE);
      final String name = GsonHelper.getString(element.getAsJsonObject(), NAME);

      if (type == null) {
        continue;
      }

      switch (type) {
        case 0: {
          user.getExternalAttrs()
            .add(ExternalAttribute.builder()
              .type(type)
              .name(name)
              .value(GsonHelper.getString(element.getAsJsonObject().get(TEXT).getAsJsonObject(), VALUE))
              .build()
            );
          break;
        }
        case 1: {
          final JsonObject web = element.getAsJsonObject().get(WEB).getAsJsonObject();
          user.getExternalAttrs()
            .add(ExternalAttribute.builder()
              .type(type)
              .name(name)
              .url(GsonHelper.getString(web, URL))
              .title(GsonHelper.getString(web, TITLE))
              .build()
            );
          break;
        }
        case 2: {
          final JsonObject miniprogram = element.getAsJsonObject().get(MINIPROGRAM).getAsJsonObject();
          user.getExternalAttrs()
            .add(ExternalAttribute.builder()
              .type(type)
              .name(name)
              .appid(GsonHelper.getString(miniprogram, APPID))
              .pagePath(GsonHelper.getString(miniprogram, PAGE_PATH))
              .title(GsonHelper.getString(miniprogram, TITLE))
              .build()
            );
          break;
        }
        default://ignored
      }
    }
  }

  @Override
  public JsonElement serialize(WxCpUser user, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject o = new JsonObject();
    addProperty(o, USER_ID, user.getUserId());
    addProperty(o, NEW_USER_ID, user.getNewUserId());
    addProperty(o, NAME, user.getName());

    addArrayProperty(o, DEPARTMENT, user.getDepartIds());
    addArrayProperty(o, ORDER, user.getOrders());
    addProperty(o, POSITION, user.getPosition());
    addArrayProperty(o, POSITIONS, user.getPositions());

    addProperty(o, MOBILE, user.getMobile());
    if (user.getGender() != null) {
      o.addProperty(GENDER, user.getGender().getCode());
    }
    addProperty(o, EMAIL, user.getEmail());
    addProperty(o, BIZ_MAIL, user.getBizMail());
    addProperty(o, AVATAR, user.getAvatar());
    addProperty(o, THUMB_AVATAR, user.getThumbAvatar());
    addProperty(o, ADDRESS, user.getAddress());
    addProperty(o, AVATAR_MEDIAID, user.getAvatarMediaId());
    addProperty(o, STATUS, user.getStatus());
    addProperty(o, ENABLE, user.getEnable());
    addProperty(o, ALIAS, user.getAlias());
    addProperty(o, IS_LEADER, user.getIsLeader());
    if (user.getIsLeaderInDept() != null && user.getIsLeaderInDept().length > 0) {
      JsonArray ary = new JsonArray();
      Arrays.stream(user.getIsLeaderInDept()).forEach(ary::add);
      o.add(IS_LEADER_IN_DEPT, ary);
    }
    addProperty(o, HIDE_MOBILE, user.getHideMobile());
    addProperty(o, ENGLISH_NAME, user.getEnglishName());
    addProperty(o, TELEPHONE, user.getTelephone());
    addProperty(o, QR_CODE, user.getQrCode());
    if (user.getToInvite() != null) {
      o.addProperty(TO_INVITE, user.getToInvite());
    }
    addProperty(o, MAIN_DEPARTMENT, user.getMainDepartment());

    // Special handling for directLeader: include empty arrays to support WeChat Work API reset functionality
    if (user.getDirectLeader() != null) {
      JsonArray directLeaderArray = new JsonArray();
      Arrays.stream(user.getDirectLeader()).forEach(directLeaderArray::add);
      o.add(DIRECT_LEADER, directLeaderArray);
    }

    if (!user.getExtAttrs().isEmpty()) {
      JsonArray attrsJsonArray = new JsonArray();
      for (Attr attr : user.getExtAttrs()) {
        JsonObject attrJson = GsonHelper.buildJsonObject(TYPE, attr.getType(),
          NAME, attr.getName());
        attrsJsonArray.add(attrJson);

        if (attr.getType() == null) {
          attrJson.addProperty(NAME, attr.getName());
          attrJson.addProperty(VALUE, attr.getTextValue());
          continue;
        }

        switch (attr.getType()) {
          case 0:
            attrJson.add(TEXT, GsonHelper.buildJsonObject(VALUE, attr.getTextValue()));
            break;
          case 1:
            attrJson.add(WEB, GsonHelper.buildJsonObject(URL, attr.getWebUrl(), TITLE, attr.getWebTitle()));
            break;
          default: //ignored
        }
      }
      JsonObject attrsJson = new JsonObject();
      attrsJson.add(ATTRS, attrsJsonArray);
      o.add(EXTRA_ATTR, attrsJson);
    }

    this.addProperty(o, EXTERNAL_POSITION, user.getExternalPosition());

    JsonObject attrsJson = new JsonObject();
    o.add(EXTERNAL_PROFILE, attrsJson);

    this.addProperty(attrsJson, EXTERNAL_CORP_NAME, user.getExternalCorpName());

    if (user.getWechatChannels() != null) {
      attrsJson.add(WECHAT_CHANNELS, GsonHelper.buildJsonObject(NICKNAME, user.getWechatChannels().getNickname(),
        STATUS, user.getWechatChannels().getStatus()));
    }

    if (!user.getExternalAttrs().isEmpty()) {
      JsonArray attrsJsonArray = new JsonArray();
      for (ExternalAttribute attr : user.getExternalAttrs()) {
        JsonObject attrJson = GsonHelper.buildJsonObject(TYPE, attr.getType(),
          NAME, attr.getName());

        attrsJsonArray.add(attrJson);

        if (attr.getType() == null) {
          continue;
        }

        switch (attr.getType()) {
          case 0:
            attrJson.add(TEXT, GsonHelper.buildJsonObject(VALUE, attr.getValue()));
            break;
          case 1:
            attrJson.add(WEB, GsonHelper.buildJsonObject(URL, attr.getUrl(), TITLE, attr.getTitle()));
            break;
          case 2:
            attrJson.add(MINIPROGRAM, GsonHelper.buildJsonObject(APPID, attr.getAppid(),
              PAGE_PATH, attr.getPagePath(), TITLE, attr.getTitle()));
            break;
          default://忽略
        }
      }

      attrsJson.add(EXTERNAL_ATTR, attrsJsonArray);
    }

    return o;
  }

  private void addArrayProperty(JsonObject o, String key, Object[] array) {
    if (array != null && array.length > 0) {
      JsonArray jsonArray = new JsonArray();
      Arrays.stream(array).forEach(item -> {
        if (item instanceof Number) {
          jsonArray.add((Number) item);
        } else {
          jsonArray.add(item.toString());
        }
      });
      o.add(key, jsonArray);
    }
  }

  private void addProperty(JsonObject object, String property, Object value) {
    if (value != null) {
      if (value instanceof Number) {
        object.addProperty(property, (Number) value);
      } else if (value instanceof Boolean) {
        object.addProperty(property, (Boolean) value);
      } else {
        object.addProperty(property, value.toString());
      }
    }
  }

}
