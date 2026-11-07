package com.binarywang.wxjava.store.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.io.Serializable;
import com.binarywang.wxjava.store.util.JsonUtils;

/**
 * 视频号 消息 兼容Json和xml
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@JacksonXmlRootElement(localName = "xml")
public class WxStoreMessage implements Serializable {

  private static final long serialVersionUID = -6929595548318897649L;

  @JsonProperty("ToUserName")
  @JacksonXmlProperty(localName = "ToUserName")
  @JacksonXmlCData
  private String toUser;

  @JsonProperty("FromUserName")
  @JacksonXmlProperty(localName = "FromUserName")
  @JacksonXmlCData
  private String fromUser;

  @JsonProperty("CreateTime")
  @JacksonXmlProperty(localName = "CreateTime")
  private Long createTime;

  @JsonProperty("MsgType")
  @JacksonXmlProperty(localName = "MsgType")
  @JacksonXmlCData
  private String msgType;

  @JsonProperty("Event")
  @JacksonXmlProperty(localName = "Event")
  @JacksonXmlCData
  private String event;

  @JsonProperty("Encrypt")
  @JacksonXmlProperty(localName = "Encrypt")
  @JacksonXmlCData
  private String encrypt;

  @JsonProperty("MsgId")
  @JacksonXmlProperty(localName = "MsgId")
  private Long msgId;

  @JsonProperty("MsgID")
  @JacksonXmlProperty(localName = "MsgID")
  private void msgIdFill(Long msgId) {
    if (msgId != null) {
      this.msgId = msgId;
    }
  }

  @Override
  public String toString() {
    return this.toJson();
  }

  public String toJson() {
    return JsonUtils.encode(this);
  }

  public String getToUser() {
    return toUser;
  }

  public String getFromUser() {
    return fromUser;
  }

  public Long getCreateTime() {
    return createTime;
  }

  public String getMsgType() {
    return msgType;
  }

  public String getEvent() {
    return event;
  }

  public String getEncrypt() {
    return encrypt;
  }

  public Long getMsgId() {
    return msgId;
  }

  public void setToUser(String toUser) {
    this.toUser = toUser;
  }

  public void setFromUser(String fromUser) {
    this.fromUser = fromUser;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public void setEncrypt(String encrypt) {
    this.encrypt = encrypt;
  }

  public void setMsgId(Long msgId) {
    this.msgId = msgId;
  }
}
