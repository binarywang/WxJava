package me.chanjar.weixin.channel.bean.home.tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加/删除分类关联的商品 参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeProductEditParam implements Serializable {

  private static final long serialVersionUID = -4906016235749892703L;

  /** 参数 */
  @JsonProperty("req")
  private TreeProductEditInfo req;

}
