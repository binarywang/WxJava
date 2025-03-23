package me.chanjar.weixin.channel.bean.home.tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 一级分类
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OneLevelTreeNode extends CatTreeNode {

  /** 二级分类 */
  @JsonProperty("level_2")
  private List<CatTreeNode> level2;

}
