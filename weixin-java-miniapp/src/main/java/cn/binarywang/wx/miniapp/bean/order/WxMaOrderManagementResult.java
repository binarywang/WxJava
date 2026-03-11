package cn.binarywang.wx.miniapp.bean.order;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xzh
 * @Description
 * @createTime 2025/01/16 15:27
 */
@Data
public class WxMaOrderManagementResult implements Serializable {
  private static final long serialVersionUID = 1468925151935770503L;
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
}
