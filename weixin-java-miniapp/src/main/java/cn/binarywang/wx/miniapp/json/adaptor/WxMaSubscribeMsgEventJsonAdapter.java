package cn.binarywang.wx.miniapp.json.adaptor;

import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMsgEvent;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;

/**
 * WxMaSubscribeMsgEventJsonAdapter class
 *
 * @author dany
 * created on  2021/12/31
 */
@Slf4j
public class WxMaSubscribeMsgEventJsonAdapter implements JsonDeserializer<WxMaSubscribeMsgEvent.WxMaSubscribeMsgEventJson> {
  @Override
  public WxMaSubscribeMsgEvent.WxMaSubscribeMsgEventJson deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxMaSubscribeMsgEvent.WxMaSubscribeMsgEventJson result = new WxMaSubscribeMsgEvent.WxMaSubscribeMsgEventJson();
    if (json.isJsonArray()) {
      JsonArray array = json.getAsJsonArray();
      if (!array.isEmpty()) {
        JsonObject obj = array.get(0).getAsJsonObject();
        MsgEventTypeEnum eventType = detectMsgEventType(obj);
        for (int i = 0; i < array.size(); ++i) {
          obj = array.get(i).getAsJsonObject();
          setField(result, eventType, obj);
        }
      }
    } else {
      JsonObject obj = json.getAsJsonObject();
      MsgEventTypeEnum eventType = detectMsgEventType(obj);
      setField(result, eventType, obj);
    }
    return result;
  }

  public enum MsgEventTypeEnum {
    EVENT_POPUP,EVENT_CHANGE,EVENT_SENT;
  }
  private MsgEventTypeEnum detectMsgEventType(JsonObject  obj) {
    JsonElement popupScene = obj.get("PopupScene");
    if (popupScene != null) {
      return MsgEventTypeEnum.EVENT_POPUP;
    }

    JsonElement msgId = obj.get("MsgID");
    if (msgId != null) {
      return MsgEventTypeEnum.EVENT_SENT;
    }
    JsonElement errorCode = obj.get("ErrorCode");
    if (errorCode != null) {
      return MsgEventTypeEnum.EVENT_SENT;
    }
    JsonElement errorStatus = obj.get("ErrorStatus");
    if (errorStatus != null) {
      return MsgEventTypeEnum.EVENT_SENT;
    }

    return MsgEventTypeEnum.EVENT_CHANGE;
  }

  private WxMaSubscribeMsgEvent.WxMaSubscribeMsgEventJson setField(WxMaSubscribeMsgEvent.WxMaSubscribeMsgEventJson target,
                                                                   MsgEventTypeEnum eventType, JsonObject json) {
    switch (eventType) {
      case EVENT_POPUP:
        if (target.getPopupEvents() == null) {
          target.setPopupEvents(new WxMaSubscribeMsgEvent.SubscribeMsgPopupEvent());
        }
        WxMaSubscribeMsgEvent.PopupEvent popupEvent = new WxMaSubscribeMsgEvent.PopupEvent();
        popupEvent.setTemplateId(json.get("TemplateId").getAsString());
        popupEvent.setSubscribeStatusString(json.get("SubscribeStatusString").getAsString());
        popupEvent.setPopupScene(json.get("PopupScene").getAsString());
        target.getPopupEvents().getList().add(popupEvent);
        break;
      case EVENT_CHANGE:
        if (target.getChangeEvents() == null) {
          target.setChangeEvents(new WxMaSubscribeMsgEvent.SubscribeMsgChangeEvent());
        }
        WxMaSubscribeMsgEvent.ChangeEvent changeEvent = new WxMaSubscribeMsgEvent.ChangeEvent();
        changeEvent.setTemplateId(json.get("TemplateId").getAsString());
        changeEvent.setSubscribeStatusString(json.get("SubscribeStatusString").getAsString());
        target.getChangeEvents().getList().add(changeEvent);
        break;
      case EVENT_SENT:
        if (target.getSentEvent() == null) {
          target.setSentEvent(new WxMaSubscribeMsgEvent.SubscribeMsgSentEvent());
        }
        WxMaSubscribeMsgEvent.SentEvent sentEvent = new WxMaSubscribeMsgEvent.SentEvent();
        sentEvent.setTemplateId(json.get("TemplateId").getAsString());
        sentEvent.setMsgId(json.get("MsgID").getAsString());
        sentEvent.setErrorCode(json.get("ErrorCode").getAsString());
        sentEvent.setErrorStatus(json.get("ErrorStatus").getAsString());
        target.getSentEvent().setList(sentEvent);
        break;
    }
    return target;
  }
}
