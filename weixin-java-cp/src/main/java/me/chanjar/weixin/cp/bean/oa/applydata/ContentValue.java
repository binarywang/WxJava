package me.chanjar.weixin.cp.bean.oa.applydata;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Content value.
 *
 * @author element
 */
@Data
@Accessors(chain = true)
public class ContentValue implements Serializable {
  private static final long serialVersionUID = -5607678965965065261L;

  private String text;

  @SerializedName("new_number")
  private String newNumber;

  @SerializedName("new_money")
  private String newMoney;

  private ContentValue.Date date;

  private ContentValue.Selector selector;

  private List<ContentValue.Member> members;

  private List<ContentValue.Department> departments;

  @SerializedName("new_tips")
  private NewTips newTips;

  private List<ContentValue.File> files;

  private List<ContentValue.Child> children;

  @SerializedName("related_approval")
  private List<RelatedApproval> relatedApproval;

  private Attendance attendance;

  private Vacation vacation;

  @SerializedName("date_range")
  private Attendance.DataRange dateRange;

  @SerializedName("punch_correction")
  private PunchCorrection punchCorrection;

  private Location location;

  private Formula formula;

  /**
   * The type Date.
   */
  @Data
  public static class Date implements Serializable {
    private static final long serialVersionUID = -6181554080062231138L;
    private String type;

    @SerializedName("s_timestamp")
    private String timestamp;
  }

  /**
   * The type Selector.
   */
  @Data
  public static class Selector implements Serializable {
    private static final long serialVersionUID = 7305458759126951773L;
    private String type;
    private List<Option> options;

    /**
     * The type Option.
     */
    @Data
    public static class Option implements Serializable {
      private static final long serialVersionUID = -3471071106328280252L;
      private String key;

      @SerializedName("value")
      private List<ContentTitle> values;
    }

  }

  /**
   * The type Member.
   */
  @Data
  public static class Member implements Serializable {
    private static final long serialVersionUID = 1316551341955496067L;

    @SerializedName("userid")
    private String userId;
    private String name;
  }

  /**
   * The type Department.
   */
  @Data
  public static class Department implements Serializable {
    private static final long serialVersionUID = -2513762192924826234L;

    @SerializedName("openapi_id")
    private String openApiId;
    private String name;
  }

  /**
   * The type Tips.
   */
  @Data
  public static class NewTips implements Serializable {
    private static final long serialVersionUID = 1094978100200056100L;
    @SerializedName("tips_content")
    private List<TipsContent> tipsContent;

    /**
     * Creates a simple Tips control with mixed plain text and clickable links.
     *
     * @param lang the language code (e.g., "zh_CN")
     * @param textAndLinks array of objects where strings become plain text and Link objects become clickable links
     * @return NewTips instance with the specified content
     */
    public static NewTips of(String lang, Object... textAndLinks) {
      NewTips tips = new NewTips();
      TipsContent content = new TipsContent();
      TipsContent.Text text = new TipsContent.Text();
      
      List<TipsContent.SubText> subTexts = new ArrayList<>();
      
      for (Object item : textAndLinks) {
        TipsContent.SubText subText = new TipsContent.SubText();
        TipsContent.SubText.Content subContent = new TipsContent.SubText.Content();
        
        if (item instanceof String) {
          // Plain text
          TipsContent.SubText.Content.PlainText plainText = new TipsContent.SubText.Content.PlainText();
          plainText.setContent((String) item);
          subContent.setPlainText(plainText);
          subText.setType(1);
        } else if (item instanceof TipsContent.SubText.Content.Link) {
          // Link
          subContent.setLink((TipsContent.SubText.Content.Link) item);
          subText.setType(2);
        }
        
        subText.setContent(subContent);
        subTexts.add(subText);
      }
      
      text.setSubText(subTexts);
      content.setText(text);
      content.setLang(lang);
      tips.setTipsContent(Arrays.asList(content));
      
      return tips;
    }

    /**
     * Creates a simple Tips control with only plain text.
     *
     * @param lang the language code (e.g., "zh_CN")
     * @param textContent the plain text content
     * @return NewTips instance with plain text content
     */
    public static NewTips ofText(String lang, String textContent) {
      return of(lang, textContent);
    }

    /**
     * Creates a Tips control with a single clickable link.
     *
     * @param lang the language code (e.g., "zh_CN")
     * @param linkTitle the display text for the link
     * @param linkUrl the URL to link to
     * @return NewTips instance with a single clickable link
     */
    public static NewTips ofLink(String lang, String linkTitle, String linkUrl) {
      TipsContent.SubText.Content.Link link = new TipsContent.SubText.Content.Link();
      link.setTitle(linkTitle);
      link.setUrl(linkUrl);
      return of(lang, link);
    }

    /**
     * The type tips_content.
     */
    @Data
    public static class TipsContent implements Serializable {
      private static final long serialVersionUID = 559432801311084797L;
      @SerializedName("text")
      private Text text;
      private String lang;

      /**
       * The type sub_text.
       */
      @Data
      public static class Text implements Serializable {
        private static final long serialVersionUID = -70174360931158924L;
        @SerializedName("sub_text")
        private List<SubText> subText;
      }

      /**
       * The type sub_text.
       */
      @Data
      public static class SubText implements Serializable {
        private static final long serialVersionUID = -8226911175438019317L;
        private Integer type;
        private Content content;

        @Data
        public static class Content implements Serializable {
          private static final long serialVersionUID = -6813250009451940525L;
          @SerializedName("plain_text")
          private PlainText plainText;
          private Link link;

          @Data
          public static class PlainText implements Serializable {
            private static final long serialVersionUID = -599377674188314118L;
            private String content;
          }

          @Data
          public static class Link implements Serializable {
            private static final long serialVersionUID = 2784173996170990308L;
            private String title;
            private String url;
          }
        }
      }
    }
  }

  /**
   * The type File.
   */
  @Data
  public static class File implements Serializable {
    private static final long serialVersionUID = 3890971381800855142L;

    @SerializedName("file_id")
    private String fileId;
    @SerializedName("file_name")
    private String fileName;
    @SerializedName("file_url")
    private String fileUrl;
  }

  /**
   * The type Child.
   */
  @Data
  public static class Child implements Serializable {
    private static final long serialVersionUID = -3500102073821161558L;
    private List<ApplyDataContent> list;
  }

  /**
   * The type Attendance.
   */
  @Data
  public static class Attendance implements Serializable {
    private static final long serialVersionUID = -6627566040706594166L;
    @SerializedName("date_range")
    private DataRange dateRange;
    private Integer type;
    @SerializedName("slice_info")
    private SliceInfo sliceInfo;

    /**
     * The type Data range.
     */
    @Data
    public static class DataRange implements Serializable {
      private static final long serialVersionUID = -3411836592583718255L;
      private String type;
      @SerializedName("new_begin")
      private Long begin;
      @SerializedName("new_end")
      private Long end;
      @SerializedName("new_duration")
      private Long duration;
    }

    /**
     * The type slice_info
     */
    @Data
    public static class SliceInfo implements Serializable {
      private static final long serialVersionUID = 4369560551634923348L;
      @SerializedName("day_items")
      private List<DayItems> dayItems;
      private Long duration;
      private Integer state;

      /**
       * The type day_items
       */
      @Data
      public static class DayItems implements Serializable {
        private static final long serialVersionUID = -7076615961077782776L;
        private Long daytime;
        private Long duration;
      }
    }

  }

  /**
   * The type Vacation.
   */
  @Data
  public static class Vacation implements Serializable {
    private static final long serialVersionUID = 2120523160034749170L;
    private Selector selector;
    private Attendance attendance;
  }

  /**
   * 关联审批单控件
   */
  @Data
  public static class RelatedApproval implements Serializable {
    private static final long serialVersionUID = 8629601623267510738L;
    /**
     * 关联审批单的模板名
     */
    @SerializedName("template_names")
    private List<TemplateName> templateNames;
    /**
     * 关联审批单的状态
     */
    @SerializedName("sp_status")
    private Integer spStatus;
    /**
     * 关联审批单的提单者
     */
    private String name;
    /**
     * 关联审批单的提单时间
     */
    @SerializedName("create_time")
    private Long createTime;
    /**
     * 关联审批单的审批单号
     */
    @SerializedName("sp_no")
    private String spNo;
  }

  /**
   * The type Template name.
   */
  @Data
  public static class TemplateName implements Serializable {
    private static final long serialVersionUID = 3152481506054355937L;
    private String text;
    private String lang;
  }

  /**
   * The type Punch correction.
   */
  @Data
  public static class PunchCorrection implements Serializable {
    private static final long serialVersionUID = 2120523160034749170L;
    private String state;
    private Long time;
    private Integer version;
    @SerializedName("daymonthyear")
    private Long dayMonthYear;
  }

  /**
   * The type Location
   */
  @Data
  public static class Location implements Serializable {
    private static final long serialVersionUID = 2480012159725572839L;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String title;
    private String address;
    private Long time;
  }

  /**
   * The type Formula
   */
  @Data
  public static class Formula implements Serializable {
    private static final long serialVersionUID = 816968197271971247L;
    private String value;
  }

}
