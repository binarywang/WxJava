package me.chanjar.weixin.cp.bean.external.msg;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 群发的客户标签
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
public class TagFilter implements Serializable {
  private static final long serialVersionUID = -6756444546744020234L;

  @SerializedName("group_list")
  private List<TagList> groupList;
}
