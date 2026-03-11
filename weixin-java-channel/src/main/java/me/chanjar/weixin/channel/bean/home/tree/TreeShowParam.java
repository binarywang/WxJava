package me.chanjar.weixin.channel.bean.home.tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 设置展示在店铺主页的商品分类 参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeShowParam implements Serializable {

  private static final long serialVersionUID = -1577647561992899360L;

  /** 分类信息 */
  @JsonProperty("req")
  private TreeShowInfo req;
}
