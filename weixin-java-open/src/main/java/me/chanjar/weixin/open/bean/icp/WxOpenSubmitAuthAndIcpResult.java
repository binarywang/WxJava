package me.chanjar.weixin.open.bean.icp;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author xzh
 * @Description
 * @createTime 2024/12/19 16:47
 */
@Getter
@Setter
@NoArgsConstructor
public class WxOpenSubmitAuthAndIcpResult extends WxOpenApplyIcpFilingResult {
  private static final long serialVersionUID = 2338143380820535842L;

  /**
   * 小程序认证及备案任务流程 id
   */
  @SerializedName("procedure_id")
  private String procedureId;

  /**
   * 小程序认证认证审核费用付费链接，当 pay_type 为 2 时返回
   */
  @SerializedName("pay_url")
  private String payUrl;
}
