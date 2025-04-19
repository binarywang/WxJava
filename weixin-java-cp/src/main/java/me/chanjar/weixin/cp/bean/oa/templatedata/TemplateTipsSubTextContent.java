package me.chanjar.weixin.cp.bean.oa.templatedata;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author mrsiu@msn.com
 * @version 1.0
 * @date 2025/1/16 09:46
 */
@Data
public class TemplateTipsSubTextContent {
  @SerializedName("plain_text")
  private TemplateTipsSubTextContentPlainText plainText;
  private TemplateTipsSubTextContentLink link;
}
