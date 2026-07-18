package com.github.binarywang.wxpay.bean.invoice;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.io.Serializable;
import java.util.List;
/** 服务商邀请商户查询结果。 */
@Data public class InviteMerchantResult implements Serializable {
  private static final long serialVersionUID = 1L;
  @SerializedName("total_count") private Integer totalCount;
  private Integer offset;
  private Integer limit;
  @SerializedName("mch_invite_result_list") private List<Merchant> mchInviteResultList;
  @Data public static class Merchant implements Serializable {
    private static final long serialVersionUID = 1L;
    @SerializedName("sub_mchid") private String subMchid;
    @SerializedName("mch_invite_status") private String mchInviteStatus;
    @SerializedName("ep_name") private String epName;
    @SerializedName("tax_id") private String taxId;
    @SerializedName("invite_code") private String inviteCode;
    @SerializedName("operate_time") private String operateTime;
    @SerializedName("invite_failed_code") private String inviteFailedCode;
    @SerializedName("invite_failed_reason") private String inviteFailedReason;
  }
}
