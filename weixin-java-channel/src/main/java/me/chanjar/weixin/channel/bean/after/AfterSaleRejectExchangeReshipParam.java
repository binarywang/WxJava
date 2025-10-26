package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 售后单换货拒绝发货信息
 *
 * @author <a href="https://gitee.com/cchengg">Chu</a>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AfterSaleRejectExchangeReshipParam extends AfterSaleIdParam {
  private static final long serialVersionUID = -7946679037747710613L;

  /** 拒绝原因具体描述 ,可使用默认描述，也可以自定义描述*/
  @JsonProperty("reject_reason")
  private String rejectReason;

  /** 拒绝原因枚举 */
  @JsonProperty("reject_reason_type")
  private Integer rejectReasonType;

  /** 退款凭证，可使用图片上传接口获取media_id（数据类型填0）*/
  @JsonProperty("reject_certificates")
  private List<String> rejectCertificates;

  public AfterSaleRejectExchangeReshipParam() {
  }

  public AfterSaleRejectExchangeReshipParam(String afterSaleOrderId, String rejectReason, Integer rejectReasonType, List<String> rejectCertificates) {
    super(afterSaleOrderId);
    this.rejectReason = rejectReason;
    this.rejectReasonType = rejectReasonType;
    this.rejectCertificates = rejectCertificates;
  }

}
