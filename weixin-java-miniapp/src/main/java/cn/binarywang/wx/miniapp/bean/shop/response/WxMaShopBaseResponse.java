package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author leiin
 * created on  2021/3/23
 */
@Data
public class WxMaShopBaseResponse implements Serializable {
  private static final long serialVersionUID = -4647161641538864187L;

  /**
   * 错误码
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("errcode")
  private Integer errCode;

  /**
   * 错误信息
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("errmsg")
  private String errMsg;
}
