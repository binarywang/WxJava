package me.chanjar.weixin.channel.bean.delivery;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.enums.PackageAuditItemType;

/**
 * 商品打包信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageAuditInfo implements Serializable {
  private static final long serialVersionUID = 1118087167138310282L;

  /**
   * 审核项名称，枚举类型参考 {@link PackageAuditItemType}
   * 使用方法：DeliveryAuditItemType.EXPRESS_PIC.getKey()
   */
  @JsonProperty("item_name")
  private String itemName;

  /** 图片/视频url */
  @JsonProperty("item_value")
  private String itemValue;

}
