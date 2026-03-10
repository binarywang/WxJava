package me.chanjar.weixin.cp.bean.external.acquisition;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 获客链接的使用详情
 *
 * @author Hugo
 * @since 2023/12/11 10:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpCustomerAcquisitionStatistic extends WxCpBaseResp {
  private static final long serialVersionUID = -3816540677590841079L;

  /**
   * 点击链接客户数
   */
  @SerializedName("click_link_customer_cnt")
  private Integer clickLinkCustomerCnt;

  /**
   * 新增客户数
   */
  @SerializedName("new_customer_cnt")
  private Integer newCustomerCnt;

  public static WxCpCustomerAcquisitionStatistic fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpCustomerAcquisitionStatistic.class);
  }

}
