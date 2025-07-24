package me.chanjar.weixin.cp.bean.oa.templatedata;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @author mrsiu@msn.com
 * @version 1.0
 * @date 2025/1/16 09:40
 */
@Data
public class TemplateTips {

  @SerializedName("tips_content")
  private List<TemplateTipsContent> tipsContent;
}
