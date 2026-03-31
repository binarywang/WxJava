package me.chanjar.weixin.cp.bean.external.msg;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 客户标签列表
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
public class TagList implements Serializable {
  private static final long serialVersionUID = 1133054307780310675L;

  @SerializedName("tag_list")
  private List<String> tagList;
}
