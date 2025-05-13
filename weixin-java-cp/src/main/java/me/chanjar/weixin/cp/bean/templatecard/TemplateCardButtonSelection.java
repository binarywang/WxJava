package me.chanjar.weixin.cp.bean.templatecard;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenjie03
 * @version 1.0
 * @since 2022/11/4 11:54
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateCardButtonSelection implements Serializable {
  private String questionKey;
  private String title;
  private String selectedId;
  private List<TemplateCardButtonSelectionOption> optionList;

  /**
   * To json json object.
   *
   * @return the json object
   */
  public JsonObject toJson() {
    JsonObject btnObject = new JsonObject();

    if (StringUtils.isNotBlank(this.questionKey)) {
      btnObject.addProperty("question_key", this.questionKey);
    }
    if (StringUtils.isNotBlank(this.title)) {
      btnObject.addProperty("title", this.title);
    }
    if (StringUtils.isNotBlank(this.selectedId)) {
      btnObject.addProperty("selected_id", this.selectedId);
    }

    if (this.optionList != null && !this.optionList.isEmpty()) {
      JsonArray optionJsonArray = new JsonArray();
      for (TemplateCardButtonSelectionOption jump : this.getOptionList()) {
        JsonObject tempObject = jump.toJson();
        optionJsonArray.add(tempObject);
      }
      btnObject.add("option_list", optionJsonArray);
    }
    return btnObject;
  }
}

