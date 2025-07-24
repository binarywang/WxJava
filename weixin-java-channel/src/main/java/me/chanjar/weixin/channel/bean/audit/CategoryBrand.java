package me.chanjar.weixin.channel.bean.audit;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类中的品牌
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryBrand implements Serializable {
  private static final long serialVersionUID = -5437441266080209907L;

  /** 品牌ID，是店铺申请且已审核通过的品牌ID */
  @JsonProperty("brand_id")
  private String brand_id;
}
