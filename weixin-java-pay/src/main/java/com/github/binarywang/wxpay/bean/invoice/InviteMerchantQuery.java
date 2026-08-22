package com.github.binarywang.wxpay.bean.invoice;
import lombok.Data;
import java.io.Serializable;
/** 查询服务商邀请开通电子发票能力的商户条件。 */
@Data public class InviteMerchantQuery implements Serializable {
  private static final long serialVersionUID = 1L;
  private String queryTimeStart;
  private String queryTimeEnd;
  private Integer offset;
  private Integer limit;
  private String inviteCode;
  private String mchInviteStatus;
}
