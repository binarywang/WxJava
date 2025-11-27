package me.chanjar.weixin.channel.bean.complaint;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 纠纷历史
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class ComplaintHistory implements Serializable {

  private static final long serialVersionUID = -4706637116597650133L;
  /** 历史操作类型,见 {@link me.chanjar.weixin.channel.enums.ComplaintItemType } */
  @JsonProperty("item_type")
  private Integer itemType;

  /** 操作时间，Unix时间戳 */
  @JsonProperty("time")
  private Long time;

  /** 用户联系电话 */
  @JsonProperty("phone_number")
  private String phoneNumber;

  /** 相关文本内容 */
  @JsonProperty("content")
  private String content;

  /** 相关图片media_id列表 */
  @JsonProperty("media_id_list")
  private List<String> mediaIds;

  /** 售后类型, 1-仅退款 2-退货退款 */
  @JsonProperty("after_sale_type")
  private Integer afterSaleType;

  /** 售后原因，见 {@link me.chanjar.weixin.channel.enums.AfterSalesReason} */
  @JsonProperty("after_sale_reason")
  private Integer afterSaleReason;
}
