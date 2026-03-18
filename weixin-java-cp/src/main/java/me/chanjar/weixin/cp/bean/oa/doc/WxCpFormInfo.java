package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 收集表信息.
 */
@Data
public class WxCpFormInfo implements Serializable {
  private static final long serialVersionUID = -3124382745213903596L;

  @SerializedName("formid")
  private String formId;

  @SerializedName("form_title")
  private String formTitle;

  @SerializedName("form_desc")
  private String formDesc;

  @SerializedName("form_header")
  private String formHeader;

  @SerializedName("form_question")
  private FormQuestion formQuestion;

  @SerializedName("form_setting")
  private FormSetting formSetting;

  @SerializedName("repeated_id")
  private List<String> repeatedId;

  @Getter
  @Setter
  public static class FormQuestion implements Serializable {
    private static final long serialVersionUID = 8800331150933320954L;

    @SerializedName("items")
    private List<QuestionItem> items;
  }

  @Getter
  @Setter
  public static class QuestionItem implements Serializable {
    private static final long serialVersionUID = -1193146858246875337L;

    @SerializedName("question_id")
    private Long questionId;

    @SerializedName("title")
    private String title;

    @SerializedName("pos")
    private Integer pos;

    @SerializedName("status")
    private Integer status;

    @SerializedName("reply_type")
    private Integer replyType;

    @SerializedName("must_reply")
    private Boolean mustReply;

    @SerializedName("note")
    private String note;

    @SerializedName("option_item")
    private List<OptionItem> optionItem;

    @SerializedName("placeholder")
    private String placeholder;

    @SerializedName("question_extend_setting")
    private JsonObject questionExtendSetting;
  }

  @Getter
  @Setter
  public static class OptionItem implements Serializable {
    private static final long serialVersionUID = 3572779939686615113L;

    @SerializedName("key")
    private Integer key;

    @SerializedName("value")
    private String value;

    @SerializedName("status")
    private Integer status;
  }

  @Getter
  @Setter
  public static class FormSetting implements Serializable {
    private static final long serialVersionUID = 6423877223362121311L;

    @SerializedName("fill_out_auth")
    private Integer fillOutAuth;

    @SerializedName("fill_in_range")
    private FillInRange fillInRange;

    @SerializedName("setting_manager_range")
    private SettingManagerRange settingManagerRange;

    @SerializedName("timed_repeat_info")
    private TimedRepeatInfo timedRepeatInfo;

    @SerializedName("allow_multi_fill")
    private Boolean allowMultiFill;

    @SerializedName("max_fill_cnt")
    private Integer maxFillCnt;

    @SerializedName("timed_finish")
    private Long timedFinish;

    @SerializedName("can_anonymous")
    private Boolean canAnonymous;

    @SerializedName("can_notify_submit")
    private Boolean canNotifySubmit;
  }

  @Getter
  @Setter
  public static class FillInRange implements Serializable {
    private static final long serialVersionUID = -4565903538375064972L;

    @SerializedName("userids")
    private List<String> userIds;

    @SerializedName("departmentids")
    private List<Long> departmentIds;
  }

  @Getter
  @Setter
  public static class SettingManagerRange implements Serializable {
    private static final long serialVersionUID = 8144560331127349770L;

    @SerializedName("userids")
    private List<String> userIds;
  }

  @Getter
  @Setter
  public static class TimedRepeatInfo implements Serializable {
    private static final long serialVersionUID = -2415196650200762709L;

    @SerializedName("enable")
    private Boolean enable;

    @SerializedName("week_flag")
    private Integer weekFlag;

    @SerializedName("remind_time")
    private Long remindTime;

    @SerializedName("repeat_type")
    private Integer repeatType;

    @SerializedName("skip_holiday")
    private Boolean skipHoliday;

    @SerializedName("day_of_month")
    private Integer dayOfMonth;

    @SerializedName("fork_finish_type")
    private Integer forkFinishType;

    @SerializedName("rule_ctime")
    private Long ruleCtime;

    @SerializedName("rule_mtime")
    private Long ruleMtime;
  }
}
