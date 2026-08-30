package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商家上传的信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class MerchantUploadInfo implements Serializable {

  private static final long serialVersionUID = 373513419356603563L;
  /** 拒绝原因 */
  @JsonProperty("reject_reason")
  private String rejectReason;

  /** 退款凭证 */
  @JsonProperty("refund_certificates")
  private List<String> refundCertificates;

}
