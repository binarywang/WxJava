package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xzh
 * @Description 小程序是否已完成交易结算管理确认结果
 * @createTime 2024/12/21 15:01
 */
@Data
public class WxMaOrderShippingITMCCompletedResult implements Serializable {

  private static final long serialVersionUID = -5397007157487018762L;
  /**
   * 错误码
   */
  @SerializedName("errcode")
  private Integer errCode;

  /**
   * 错误原因
   */
  @SerializedName("errmsg")
  private String errMsg;

  /**
   * 是否已完成交易结算管理确认
   */
  @SerializedName("completed")
  private Boolean completed;
}
