package me.chanjar.weixin.cp.bean.message;

import com.google.common.base.Splitter;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 消息发送结果对象类.
 * Created by Binary Wang on 2017-6-22.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
public class WxCpMessageSendResult implements Serializable {
  private static final long serialVersionUID = 916455987193190004L;

  @Override
  public String toString() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * From json wx cp message send result.
   *
   * @param json the json
   * @return the wx cp message send result
   */
  public static WxCpMessageSendResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpMessageSendResult.class);
  }

  @SerializedName("errcode")
  private Integer errCode;

  @SerializedName("errmsg")
  private String errMsg;

  @SerializedName("invaliduser")
  private String invalidUser;

  @SerializedName("invalidparty")
  private String invalidParty;

  @SerializedName("invalidtag")
  private String invalidTag;

  @SerializedName("unlicenseduser")
  private String unlicensedUser;

  @SerializedName("msgid")
  private String msgId;

  /**
   * 仅消息类型为“按钮交互型”，“投票选择型”和“多项选择型”的模板卡片消息返回，应用可使用response_code调用更新模版卡片消息接口，24小时内有效，且只能使用一次
   */
  @SerializedName("response_code")
  private String responseCode;

  /**
   * Gets invalid user list.
   *
   * @return the invalid user list
   */
  public List<String> getInvalidUserList() {
    return this.content2List(this.invalidUser);
  }

  private List<String> content2List(String content) {
    if (StringUtils.isBlank(content)) {
      return Collections.emptyList();
    }

    return Splitter.on("|").splitToList(content);
  }

  /**
   * Gets invalid party list.
   *
   * @return the invalid party list
   */
  public List<String> getInvalidPartyList() {
    return this.content2List(this.invalidParty);
  }

  /**
   * Gets invalid tag list.
   *
   * @return the invalid tag list
   */
  public List<String> getInvalidTagList() {
    return this.content2List(this.invalidTag);
  }

  /**
   * Gets unlicensed user list.
   *
   * @return the unlicensed user list
   */
  public List<String> getUnlicensedUserList() {
    return this.content2List(this.unlicensedUser);
  }
}
