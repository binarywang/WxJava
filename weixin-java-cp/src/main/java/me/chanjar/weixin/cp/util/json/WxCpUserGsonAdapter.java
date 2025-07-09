/*
 * KINGSTAR MEDIA SOLUTIONS Co.,LTD. Copyright c 2005-2013. All rights reserved.
 *
 * This source code is the property of KINGSTAR MEDIA SOLUTIONS LTD. It is intended
 * only for the use of KINGSTAR MEDIA application development. Reengineering, reproduction
 * arose from modification of the original source, or other redistribution of this source
 * is not permitted without written permission of the KINGSTAR MEDIA SOLUTIONS LTD.
 */

package me.chanjar.weixin.cp.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.cp.bean.Gender;
import me.chanjar.weixin.cp.bean.WxCpUser;

import java.lang.reflect.Type;

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

  @Override
  public WxCpUser deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject o = json.getAsJsonObject();
    WxCpUser user = new WxCpUser();

    if (o.get(DEPARTMENT) != null) {
      JsonArray departJsonArray = o.get(DEPARTMENT).getAsJsonArray();
      Long[] departIds = new Long[departJsonArray.size()];
      int i = 0;
      for (JsonElement jsonElement : departJsonArray) {
        departIds[i++] = jsonElement.getAsLong();
      }
      user.setDepartIds(departIds);
    }

    if (o.get("order") != null) {
      JsonArray departJsonArray = o.get("order").getAsJsonArray();
      Integer[] orders = new Integer[departJsonArray.size()];
      int i = 0;
      for (JsonElement jsonElement : departJsonArray) {
        orders[i++] = jsonElement.getAsInt();
      }
      user.setOrders(orders);
    }

    if (o.get("positions") != null) {
      JsonArray positionJsonArray = o.get("positions").getAsJsonArray();
      String[] positions = new String[positionJsonArray.size()];
      int i = 0;
      for (JsonElement jsonElement : positionJsonArray) {
        positions[i++] = jsonElement.getAsString();
      }
      user.setPositions(positions);
    }

    user.setUserId(GsonHelper.getString(o, "userid"));
    user.setName(GsonHelper.getString(o, "name"));
    user.setPosition(GsonHelper.getString(o, "position"));
    user.setMobile(GsonHelper.getString(o, "mobile"));
    user.setGender(Gender.fromCode(GsonHelper.getString(o, "gender")));
    user.setEmail(GsonHelper.getString(o, "email"));
    user.setBizMail(GsonHelper.getString(o, "biz_mail"));
    user.setAvatar(GsonHelper.getString(o, "avatar"));
    user.setThumbAvatar(GsonHelper.getString(o, "thumb_avatar"));
    user.setAddress(GsonHelper.getString(o, "address"));
    user.setAvatarMediaId(GsonHelper.getString(o, "avatar_mediaid"));
    user.setStatus(GsonHelper.getInteger(o, "status"));
    user.setEnable(GsonHelper.getInteger(o, "enable"));
    user.setAlias(GsonHelper.getString(o, "alias"));
    user.setIsLeader(GsonHelper.getInteger(o, "isleader"));
    user.setIsLeaderInDept(GsonHelper.getIntArray(o, "is_leader_in_dept"));
    user.setHideMobile(GsonHelper.getInteger(o, "hide_mobile"));
    user.setEnglishName(GsonHelper.getString(o, "english_name"));
    user.setTelephone(GsonHelper.getString(o, "telephone"));
    user.setQrCode(GsonHelper.getString(o, "qr_code"));
    user.setToInvite(GsonHelper.getBoolean(o, "to_invite"));
    user.setOpenUserId(GsonHelper.getString(o, "open_userid"));
    user.setMainDepartment(GsonHelper.getString(o, "main_department"));
    user.setDirectLeader(GsonHelper.getStringArray(o, "direct_leader"));

    if (GsonHelper.isNotNull(o.get(EXTRA_ATTR))) {
      this.buildExtraAttrs(o, user);
    }

    if (GsonHelper.isNotNull(o.get(EXTERNAL_PROFILE))) {
      user.setExternalCorpName(GsonHelper.getString(o.getAsJsonObject().get(EXTERNAL_PROFILE).getAsJsonObject(),
        EXTERNAL_CORP_NAME));
      JsonElement jsonElement = o.get(EXTERNAL_PROFILE).getAsJsonObject().get(WECHAT_CHANNELS);
      if (jsonElement != null) {
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        user.setWechatChannels(WechatChannels.builder().nickname(GsonHelper.getString(asJsonObject, "nickname")).status(GsonHelper.getInteger(asJsonObject, "status")).build());
      }
      this.buildExternalAttrs(o, user);
    }

    user.setExternalPosition(GsonHelper.getString(o, EXTERNAL_POSITION));

    return user;
  }

  private void buildExtraAttrs(JsonObject o, WxCpUser user) {
    JsonArray attrJsonElements = o.get(EXTRA_ATTR).getAsJsonObject().get("attrs").getAsJsonArray();
    for (JsonElement attrJsonElement : attrJsonElements) {
      final Integer type = GsonHelper.getInteger(attrJsonElement.getAsJsonObject(), "type");
      final Attr attr = new Attr().setType(type)
        .setName(GsonHelper.getString(attrJsonElement.getAsJsonObject(), "name"));
      user.getExtAttrs().add(attr);

      if (type == null) {
        attr.setTextValue(GsonHelper.getString(attrJsonElement.getAsJsonObject(), "value"));
        continue;
      }

      switch (type) {
        case 0: {
          JsonElement textJsonElement = attrJsonElement.getAsJsonObject().get("text");
          if (textJsonElement != null && !textJsonElement.isJsonNull() && textJsonElement.isJsonObject()) {
            attr.setTextValue(GsonHelper.getString(textJsonElement.getAsJsonObject(), "value"));
          } else {
            attr.setTextValue(null); // Clear or set a default value to avoid stale data
          }
          break;
        }
        case 1: {
          final JsonObject web = attrJsonElement.getAsJsonObject().get("web").getAsJsonObject();
          attr.setWebTitle(GsonHelper.getString(web, "title"))
            .setWebUrl(GsonHelper.getString(web, "url"));
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
      final Integer type = GsonHelper.getInteger(element.getAsJsonObject(), "type");
      final String name = GsonHelper.getString(element.getAsJsonObject(), "name");

      if (type == null) {
        continue;
      }

      switch (type) {
        case 0: {
          user.getExternalAttrs()
            .add(ExternalAttribute.builder()
              .type(type)
              .name(name)
              .value(GsonHelper.getString(element.getAsJsonObject().get("text").getAsJsonObject(), "value"))
              .build()
            );
          break;
        }
        case 1: {
          final JsonObject web = element.getAsJsonObject().get("web").getAsJsonObject();
          user.getExternalAttrs()
            .add(ExternalAttribute.builder()
              .type(type)
              .name(name)
              .url(GsonHelper.getString(web, "url"))
              .title(GsonHelper.getString(web, "title"))
              .build()
            );
          break;
        }
        case 2: {
          final JsonObject miniprogram = element.getAsJsonObject().get("miniprogram").getAsJsonObject();
          user.getExternalAttrs()
            .add(ExternalAttribute.builder()
              .type(type)
              .name(name)
              .appid(GsonHelper.getString(miniprogram, "appid"))
              .pagePath(GsonHelper.getString(miniprogram, "pagepath"))
              .title(GsonHelper.getString(miniprogram, "title"))
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
    this.addProperty(o, "userid", user.getUserId());
    this.addProperty(o, "new_userid", user.getNewUserId());
    this.addProperty(o, "name", user.getName());
    if (user.getDepartIds() != null) {
      JsonArray jsonArray = new JsonArray();
      for (Long departId : user.getDepartIds()) {
        jsonArray.add(new JsonPrimitive(departId));
      }
      o.add("department", jsonArray);
    }

    if (user.getOrders() != null) {
      JsonArray jsonArray = new JsonArray();
      for (Integer order : user.getOrders()) {
        jsonArray.add(new JsonPrimitive(order));
      }
      o.add("order", jsonArray);
    }

    this.addProperty(o, "position", user.getPosition());

    if (user.getPositions() != null) {
      JsonArray jsonArray = new JsonArray();
      for (String position : user.getPositions()) {
        jsonArray.add(new JsonPrimitive(position));
      }
      o.add("positions", jsonArray);
    }

    this.addProperty(o, "mobile", user.getMobile());
    if (user.getGender() != null) {
      o.addProperty("gender", user.getGender().getCode());
    }
    this.addProperty(o, "email", user.getEmail());
    this.addProperty(o, "biz_mail", user.getBizMail());
    this.addProperty(o, "avatar", user.getAvatar());
    this.addProperty(o, "thumb_avatar", user.getThumbAvatar());
    this.addProperty(o, "address", user.getAddress());
    this.addProperty(o, "avatar_mediaid", user.getAvatarMediaId());
    this.addProperty(o, "status", user.getStatus());
    this.addProperty(o, "enable", user.getEnable());
    this.addProperty(o, "alias", user.getAlias());
    this.addProperty(o, "isleader", user.getIsLeader());
    if (user.getIsLeaderInDept() != null && user.getIsLeaderInDept().length > 0) {
      JsonArray ary = new JsonArray();
      for (int item : user.getIsLeaderInDept()) {
        ary.add(item);
      }
      o.add("is_leader_in_dept", ary);
    }
    this.addProperty(o, "hide_mobile", user.getHideMobile());
    this.addProperty(o, "english_name", user.getEnglishName());
    this.addProperty(o, "telephone", user.getTelephone());
    this.addProperty(o, "qr_code", user.getQrCode());
    if (user.getToInvite() != null) {
      o.addProperty("to_invite", user.getToInvite());
    }
    this.addProperty(o, "main_department", user.getMainDepartment());

    if (user.getDirectLeader() != null && user.getDirectLeader().length > 0) {
      JsonArray ary = new JsonArray();
      for (String item : user.getDirectLeader()) {
        ary.add(item);
      }
      o.add("direct_leader", ary);
    }
    if (!user.getExtAttrs().isEmpty()) {
      JsonArray attrsJsonArray = new JsonArray();
      for (Attr attr : user.getExtAttrs()) {
        JsonObject attrJson = GsonHelper.buildJsonObject("type", attr.getType(),
          "name", attr.getName());
        attrsJsonArray.add(attrJson);

        if (attr.getType() == null) {
          attrJson.addProperty("name", attr.getName());
          attrJson.addProperty("value", attr.getTextValue());
          continue;
        }

        switch (attr.getType()) {
          case 0:
            attrJson.add("text", GsonHelper.buildJsonObject("value", attr.getTextValue()));
            break;
          case 1:
            attrJson.add("web", GsonHelper.buildJsonObject("url", attr.getWebUrl(), "title", attr.getWebTitle()));
            break;
          default: //ignored
        }
      }
      JsonObject attrsJson = new JsonObject();
      attrsJson.add("attrs", attrsJsonArray);
      o.add(EXTRA_ATTR, attrsJson);
    }

    this.addProperty(o, EXTERNAL_POSITION, user.getExternalPosition());

    JsonObject attrsJson = new JsonObject();
    o.add(EXTERNAL_PROFILE, attrsJson);

    this.addProperty(attrsJson, EXTERNAL_CORP_NAME, user.getExternalCorpName());

    if (user.getWechatChannels() != null) {
      attrsJson.add(WECHAT_CHANNELS, GsonHelper.buildJsonObject("nickname", user.getWechatChannels().getNickname(),
        "status", user.getWechatChannels().getStatus()));
    }

    if (!user.getExternalAttrs().isEmpty()) {
      JsonArray attrsJsonArray = new JsonArray();
      for (ExternalAttribute attr : user.getExternalAttrs()) {
        JsonObject attrJson = GsonHelper.buildJsonObject("type", attr.getType(),
          "name", attr.getName());

        attrsJsonArray.add(attrJson);

        if (attr.getType() == null) {
          continue;
        }

        switch (attr.getType()) {
          case 0:
            attrJson.add("text", GsonHelper.buildJsonObject("value", attr.getValue()));
            break;
          case 1:
            attrJson.add("web", GsonHelper.buildJsonObject("url", attr.getUrl(), "title", attr.getTitle()));
            break;
          case 2:
            attrJson.add("miniprogram", GsonHelper.buildJsonObject("appid", attr.getAppid(),
              "pagepath", attr.getPagePath(), "title", attr.getTitle()));
            break;
          default://忽略
        }
      }

      attrsJson.add(EXTERNAL_ATTR, attrsJsonArray);
    }

    return o;
  }

  private void addProperty(JsonObject object, String property, Integer value) {
    if (value != null) {
      object.addProperty(property, value);
    }
  }

  private void addProperty(JsonObject object, String property, String value) {
    if (value != null) {
      object.addProperty(property, value);
    }
  }

}
