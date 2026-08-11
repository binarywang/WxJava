package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 企业微信待办详情。
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
public class WxCpOaTodo implements Serializable {
  private static final long serialVersionUID = -1114385872940730722L;

  @SerializedName("todoid")
  private String todoId;

  @SerializedName("creator_userid")
  private String creatorUserId;

  private String title;
  private String content;
  private String url;
  private String formid;

  @SerializedName("create_time")
  private Long createTime;

  @SerializedName("finish_time")
  private Long finishTime;

  private Integer state;
}
