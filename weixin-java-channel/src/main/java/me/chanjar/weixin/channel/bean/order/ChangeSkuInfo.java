package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 更换sku信息
 */
@Data
@NoArgsConstructor
public class ChangeSkuInfo implements Serializable {

  private static final long serialVersionUID = 8783442929429377519L;

  /**
   * 发货前更换sku状态。3：等待商家处理，4：商家审核通过，5：商家拒绝，6：用户主动取消，7：超时默认拒绝
   */
  @JsonProperty("preshipment_change_sku_state")
  private Integer preshipmentChangeSkuState;

  /**
   * 原sku_id
   */
  @JsonProperty("old_sku_id")
  private String oldSkuId;

  /**
   * 用户申请更换的sku_id
   */
  @JsonProperty("new_sku_id")
  private String newSkuId;

  /**
   * 商家处理请求的最后时间，只有当前换款请求处于"等待商家处理"才有值
   */
  @JsonProperty("ddl_time_stamp")
  private Integer deadlineTimeStamp;

}
