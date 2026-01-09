package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.external.msg.Attachment;
import me.chanjar.weixin.cp.bean.external.msg.Text;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 企业群发消息任务
 *
 * @author songfan, Mr.Pan
 * @since 2020/7/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpMsgTemplate implements Serializable {
  private static final long serialVersionUID = 3172331565173474358L;

  /**
   * 群发任务的类型，默认为single，表示发送给客户，group表示发送给客户群
   */
  @SerializedName("chat_type")
  private String chatType;

  /**
   * 客户的外部联系人id列表，仅在chat_type为single时有效，不可与sender同时为空，最多可传入1万个客户
   */
  @SerializedName("external_userid")
  private List<String> externalUserid;

  /**
   * 客户群id列表，仅在chat_type为group时有效，最多可一次指定2000个客户群。指定群id之后，收到任务的群主无须再选择客户群，仅对4.1.10及以上版本的企业微信终端生效
   */
  @SerializedName("chat_id_list")
  private List<String> chatIdList;

  /**
   * 发送企业群发消息的成员userid，当类型为发送给客户群时必填
   */
  private String sender;

  /**
   * 是否允许成员在待发送客户列表中重新进行选择，默认为false，仅支持客户群发场景
   */
  @SerializedName("allow_select")
  private Boolean allowSelect;

  /**
   * 消息文本内容，最多4000个字节
   */
  private Text text;

  /**
   * 附件，最多支持添加9个附件
   */
  private List<Attachment> attachments;

  /**
   * From json wx cp msg template.
   *
   * @param json the json
   * @return the wx cp msg template
   */
  public static WxCpMsgTemplate fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpMsgTemplate.class);
  }

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
