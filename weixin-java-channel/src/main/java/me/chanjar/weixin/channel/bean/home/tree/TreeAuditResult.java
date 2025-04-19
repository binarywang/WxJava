package me.chanjar.weixin.channel.bean.home.tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 展示在店铺主页的商品分类
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class TreeAuditResult implements Serializable {

  private static final long serialVersionUID = 8142657614529852121L;

  /** 版本号。设置分类树的接口会用到 */
  @JsonProperty("version")
  private Integer version;

  /** 展示在店铺主页的商品分类 */
  @JsonProperty("audit_results")
  private List<TreeAuditResultDetail> auditResults;
}
