package me.chanjar.weixin.open.bean.icp;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author xzh
 * @Description 已备案详情
 * @createTime 2024/08/14 10:52
 */
@Getter
@Setter
@NoArgsConstructor
public class WxOpenOnlineIcpOrderResult implements Serializable {

  private static final long serialVersionUID = -8670174116784375577L;

  /**
   * 备案主体信息
   */
  @SerializedName("icp_subject")
  private WxOpenApplyIcpFilingParam.Subject icpSubject;

  /**
   * 微信小程序信息
   */
  @SerializedName("icp_applets")
  private WxOpenApplyIcpFilingParam.Applets icpApplets;
}
