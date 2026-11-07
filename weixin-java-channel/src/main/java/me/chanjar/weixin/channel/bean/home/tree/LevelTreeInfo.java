package me.chanjar.weixin.channel.bean.home.tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.home.tree.LevelTreeInfo}。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class LevelTreeInfo implements Serializable {

  /** 一级分类 */
  @JsonProperty("level_1")
  private List<OneLevelTreeNode> level1;

}
