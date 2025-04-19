package cn.binarywang.wx.miniapp.bean.order;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author xzh
 * @Description
 * @createTime 2025/01/16 15:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxMaOrderManagementGetOrderDetailPath extends WxMaOrderManagementResult {
  private static final long serialVersionUID = -5288666524298706169L;

  /**
   * 订单详情路径
   */
  @SerializedName("path")
  private String path;
}
