package me.chanjar.weixin.cp.bean.message;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateCardMessage implements Serializable {
  private static final long serialVersionUID = 8833792280163704239L;

  @SerializedName("userids")
  private List<String> userids;
  @SerializedName("partyids")
  private List<Integer> partyids;
  @SerializedName("tagids")
  private List<Integer> tagids;
  @SerializedName("atall")
  private Integer atall;
  @SerializedName("agentid")
  private Integer agentid;
  @SerializedName("response_code")
  private String responseCode;
  @SerializedName("enable_id_trans")
  private Integer enableIdTrans;
  @SerializedName("template_card")
  private TemplateCardDTO templateCard;

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  @NoArgsConstructor
  @Data
  public static class TemplateCardDTO {
    @SerializedName("card_type")
    private String cardType;
    @SerializedName("source")
    private SourceDTO source;
    @SerializedName("main_title")
    private MainTitleDTO mainTitle;
    @SerializedName("select_list")
    private List<SelectListDTO> selectList;
    @SerializedName("submit_button")
    private SubmitButtonDTO submitButton;
    @SerializedName("replace_text")
    private String replaceText;

    @SerializedName("checkbox")
    private CheckboxDTO checkbox;


    @SerializedName("action_menu")
    private ActionMenuDTO actionMenu;
    @SerializedName("quote_area")
    private QuoteAreaDTO quoteArea;
    @SerializedName("sub_title_text")
    private String subTitleText;
    @SerializedName("horizontal_content_list")
    private List<HorizontalContentListDTO> horizontalContentList;
    @SerializedName("card_action")
    private CardActionDTO cardAction;
    @SerializedName("button_selection")
    private ButtonSelectionDTO buttonSelection;
    @SerializedName("button_list")
    private List<ButtonListDTO> buttonList;

    @SerializedName("image_text_area")
    private ImageTextAreaDTO imageTextArea;
    @SerializedName("card_image")
    private CardImageDTO cardImage;
    @SerializedName("vertical_content_list")
    private List<MainTitleDTO> verticalContentList;
    @SerializedName("jump_list")
    private List<JumpListDTO> jumpList;


    @NoArgsConstructor
    @Data
    public static class SourceDTO {
      @SerializedName("icon_url")
      private String iconUrl;
      @SerializedName("desc")
      private String desc;
      @SerializedName("desc_color")
      private Integer descColor;
    }

    @NoArgsConstructor
    @Data
    public static class ActionMenuDTO {
      @SerializedName("desc")
      private String desc;
      @SerializedName("action_list")
      private List<SubmitButtonDTO> actionList;
    }

    @NoArgsConstructor
    @Data
    public static class QuoteAreaDTO {
      @SerializedName("type")
      private Integer type;
      @SerializedName("url")
      private String url;
      @SerializedName("title")
      private String title;
      @SerializedName("quote_text")
      private String quoteText;
    }

    @NoArgsConstructor
    @Data
    public static class CardActionDTO {
      @SerializedName("type")
      private Integer type;
      @SerializedName("url")
      private String url;
      @SerializedName("appid")
      private String appid;
      @SerializedName("pagepath")
      private String pagepath;
    }

    @NoArgsConstructor
    @Data
    public static class ButtonSelectionDTO {
      @SerializedName("question_key")
      private String questionKey;
      @SerializedName("title")
      private String title;
      @SerializedName("option_list")
      private List<SelectListDTO.OptionListDTO> optionList;
      @SerializedName("selected_id")
      private String selectedId;
    }

    @NoArgsConstructor
    @Data
    public static class HorizontalContentListDTO {
      @SerializedName("keyname")
      private String keyname;
      @SerializedName("value")
      private String value;
      @SerializedName("type")
      private Integer type;
      @SerializedName("url")
      private String url;
      @SerializedName("media_id")
      private String mediaId;
      @SerializedName("userid")
      private String userid;
    }

    @NoArgsConstructor
    @Data
    public static class ButtonListDTO {
      @SerializedName("text")
      private String text;
      @SerializedName("style")
      private Integer style;
      @SerializedName("key")
      private String key;
    }


    @NoArgsConstructor
    @Data
    public static class CheckboxDTO {
      @SerializedName("question_key")
      private String questionKey;
      @SerializedName("option_list")
      private List<OptionListDTO> optionList;
      @SerializedName("disable")
      private Boolean disable;
      @SerializedName("mode")
      private Integer mode;

      @NoArgsConstructor
      @Data
      public static class OptionListDTO {
        @SerializedName("id")
        private String id;
        @SerializedName("text")
        private String text;
        @SerializedName("is_checked")
        private Boolean isChecked;
      }

    }

    @NoArgsConstructor
    @Data
    public static class MainTitleDTO {
      @SerializedName("title")
      private String title;
      @SerializedName("desc")
      private String desc;
    }

    @NoArgsConstructor
    @Data
    public static class SubmitButtonDTO {
      @SerializedName("text")
      private String text;
      @SerializedName("key")
      private String key;
    }

    @NoArgsConstructor
    @Data
    public static class SelectListDTO {
      @SerializedName("question_key")
      private String questionKey;
      @SerializedName("title")
      private String title;
      @SerializedName("selected_id")
      private String selectedId;
      @SerializedName("disable")
      private Boolean disable;
      @SerializedName("option_list")
      private List<OptionListDTO> optionList;

      @NoArgsConstructor
      @Data
      public static class OptionListDTO {
        @SerializedName("id")
        private String id;
        @SerializedName("text")
        private String text;
      }
    }

    @NoArgsConstructor
    @Data
    public static class ImageTextAreaDTO {
      @SerializedName("type")
      private Integer type;
      @SerializedName("url")
      private String url;
      @SerializedName("title")
      private String title;
      @SerializedName("desc")
      private String desc;
      @SerializedName("image_url")
      private String imageUrl;
    }

    @NoArgsConstructor
    @Data
    public static class CardImageDTO {
      @SerializedName("url")
      private String url;
      @SerializedName("aspect_ratio")
      private Double aspectRatio;
    }

    @NoArgsConstructor
    @Data
    public static class JumpListDTO {
      @SerializedName("type")
      private Integer type;
      @SerializedName("title")
      private String title;
      @SerializedName("url")
      private String url;
      @SerializedName("appid")
      private String appid;
      @SerializedName("pagepath")
      private String pagepath;
    }


  }

}
