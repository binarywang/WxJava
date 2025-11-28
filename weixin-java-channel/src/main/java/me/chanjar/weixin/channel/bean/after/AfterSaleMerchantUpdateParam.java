package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 售后单商家协商信息
 *
 * @author <a href="https://gitee.com/cchengg">Chu</a>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AfterSaleMerchantUpdateParam extends AfterSaleIdParam {
  private static final long serialVersionUID = -3672834150982780L;

  /** 协商修改把售后单修改成该售后类型。1:退款；2:退货退款*/
  @JsonProperty("type")
  private Integer type;

  /** 金额（单位：分）*/
  @JsonProperty("amount")
  private Integer amount;

  /** 协商描述*/
  @JsonProperty("merchant_update_desc")
  private String merchantUpdateDesc;

  /** 协商原因*/
  @JsonProperty("update_reason_type")
  private Integer updateReasonType;

  /** 1:已协商一致，邀请买家取消售后; 2:邀请买家核实与补充凭证; 3:修改买家售后申请*/
  @JsonProperty("merchant_update_type")
  private Integer merchantUpdateType;

  /** 协商凭证id列表，可使用图片上传接口获取media_id（数据类型填0），当update_reason_type对应的need_image为1时必填*/
  @JsonProperty("media_ids")
  private List<String> mediaIds;

  public AfterSaleMerchantUpdateParam() {
  }

  public AfterSaleMerchantUpdateParam(String afterSaleOrderId, Integer type, Integer updateReasonType, Integer merchantUpdateType
  , Integer amount, String merchantUpdateDesc, List<String> mediaIds) {
    super(afterSaleOrderId);
    this.type = type;
    this.updateReasonType = updateReasonType;
    this.merchantUpdateType = merchantUpdateType;
    this.amount = amount;
    this.merchantUpdateDesc = merchantUpdateDesc;
    this.mediaIds = mediaIds;
  }

}
