package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;


/**
 * 卡券图文消息HTML结果
 *
 * @author S (sshzh90@gmail.com)
 */
@Data
public class WxMpCardMpnewsGethtmlResult implements Serializable {
  private static final long serialVersionUID = 6435268886823478711L;

  /**
   * 返回一段html代码，可以直接嵌入到图文消息的正文里。即可以把这段代码嵌入到 上传图文消息素材接口 中的content字段里
   */
  @SerializedName("content")
  private String content;

  public static WxMpCardMpnewsGethtmlResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpCardMpnewsGethtmlResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}

