package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 收集表答案.
 */
@Data
public class WxCpFormAnswer extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -7318002552753089240L;

  @SerializedName("answer")
  private Answer answer;

  public static WxCpFormAnswer fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFormAnswer.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  @Getter
  @Setter
  public static class Answer implements Serializable {
    private static final long serialVersionUID = -3098783062125658694L;

    @SerializedName("answer_list")
    private List<AnswerItem> answerList;
  }

  @Getter
  @Setter
  public static class AnswerItem implements Serializable {
    private static final long serialVersionUID = -832821755226678178L;

    @SerializedName("answer_id")
    private Long answerId;

    @SerializedName("user_name")
    private String userName;

    @SerializedName("ctime")
    private Long ctime;

    @SerializedName("mtime")
    private Long mtime;

    @SerializedName("reply")
    private Reply reply;

    @SerializedName("answer_status")
    private Integer answerStatus;

    @SerializedName("tmp_external_userid")
    private String tmpExternalUserId;

    @SerializedName("userid")
    private String userId;
  }

  @Getter
  @Setter
  public static class Reply implements Serializable {
    private static final long serialVersionUID = 6883156174731993535L;

    @SerializedName("items")
    private List<ReplyItem> items;
  }

  @Getter
  @Setter
  public static class ReplyItem implements Serializable {
    private static final long serialVersionUID = 445782840175634399L;

    @SerializedName("question_id")
    private Long questionId;

    @SerializedName("text_reply")
    private String textReply;

    @SerializedName("option_reply")
    private List<Integer> optionReply;

    @SerializedName("option_extend_reply")
    private List<OptionExtendReply> optionExtendReply;

    @SerializedName("file_extend_reply")
    private List<FileExtendReply> fileExtendReply;

    @SerializedName("department_reply")
    private DepartmentReply departmentReply;

    @SerializedName("member_reply")
    private MemberReply memberReply;

    @SerializedName("duration_reply")
    private DurationReply durationReply;
  }

  @Getter
  @Setter
  public static class OptionExtendReply implements Serializable {
    private static final long serialVersionUID = -4423477242528254162L;

    @SerializedName("option_reply")
    private Integer optionReply;

    @SerializedName("extend_text")
    private String extendText;
  }

  @Getter
  @Setter
  public static class FileExtendReply implements Serializable {
    private static final long serialVersionUID = 6464225701350465385L;

    @SerializedName("name")
    private String name;

    @SerializedName("fileid")
    private String fileId;
  }

  @Getter
  @Setter
  public static class DepartmentReply implements Serializable {
    private static final long serialVersionUID = 6599506855445852146L;

    @SerializedName("list")
    private List<DepartmentItem> list;
  }

  @Getter
  @Setter
  public static class DepartmentItem implements Serializable {
    private static final long serialVersionUID = 8568990069949513158L;

    @SerializedName("department_id")
    private Long departmentId;
  }

  @Getter
  @Setter
  public static class MemberReply implements Serializable {
    private static final long serialVersionUID = -2447604628593912052L;

    @SerializedName("list")
    private List<MemberItem> list;
  }

  @Getter
  @Setter
  public static class MemberItem implements Serializable {
    private static final long serialVersionUID = -4863584961865113255L;

    @SerializedName("userid")
    private String userId;
  }

  @Getter
  @Setter
  public static class DurationReply implements Serializable {
    private static final long serialVersionUID = -5917696184201031172L;

    @SerializedName("begin_time")
    private Long beginTime;

    @SerializedName("end_time")
    private Long endTime;

    @SerializedName("time_scale")
    private Integer timeScale;

    @SerializedName("day_range")
    private Integer dayRange;

    @SerializedName("days")
    private Float days;

    @SerializedName("hours")
    private Float hours;
  }
}
