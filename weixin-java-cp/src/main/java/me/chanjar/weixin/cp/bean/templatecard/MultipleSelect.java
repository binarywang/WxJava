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
 * 下拉式的选择器列表，multiple_interaction类型的卡片该字段不可为空，一个消息最多支持 3 个选择器
 *
 * @author yzts  created on  2021/9/22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultipleSelect implements Serializable {
  private static final long serialVersionUID = 3446092543065698516L;

  /**
   * 下拉式的选择器题目的key，用户提交选项后，会产生回调事件，回调事件会带上该key值表示该题，最长支持1024字节，不可重复
   */
  private String question_key;
  /**
   * 下拉式的选择器上面的title
   */
  private String title;
  /**
   * 默认选定的id，不填或错填默认第一个
   */
  private String selected_id;

  /**
   * 选项列表，下拉选项不超过 10 个，最少1个
   */
  private List<CheckboxOption> options;

  /**
   * To json json object.
   *
   * @return the json object
   */
  public JsonObject toJson() {
    JsonObject selectJson = new JsonObject();

    selectJson.addProperty("question_key", this.getQuestion_key());

    if (StringUtils.isNotBlank(this.getTitle())) {
      selectJson.addProperty("title", this.getTitle());
    }
    if (StringUtils.isNotBlank(this.getSelected_id())) {
      selectJson.addProperty("selected_id", this.getSelected_id());
    }
// select_list
    List<CheckboxOption> options = this.getOptions();
    if (null != options && !options.isEmpty()) {
      JsonArray optionJsonArray = new JsonArray();
      for (CheckboxOption option : this.getOptions()) {
        JsonObject tempObject = option.toJson();
        optionJsonArray.add(tempObject);
      }
      selectJson.add("option_list", optionJsonArray);
    }

    return selectJson;
  }
}
