package me.chanjar.weixin.channel.bean.home.tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 主页分类信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatTreeNode implements Serializable {

  private static final long serialVersionUID = 3154219180098003510L;

  /** 分类id */
  @JsonProperty("id")
  private Integer id;

  /** 分类名字 */
  @JsonProperty("name")
  private String name;

  /** 是否在用户端展示该分类。1为是，0为否 */
  @JsonProperty("is_displayed")
  private Boolean displayed;
}
