package cn.binarywang.wx.miniapp.bean.shop.request;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author leiin
 * created on  2022/6/28 11:39 上午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaShopAfterSaleListRequest implements Serializable {
  @SerializedName("status")
  private Integer status; //售后单状态，见 AfterSalesState
  @SerializedName("out_order_id")
  private String outOrderId; //售后单关联的外部订单号
  @SerializedName("order_id")
  private Long orderId; //售后单关联的微信侧订单号
  @SerializedName("openid")
  private String openid; //买家openid
  @SerializedName("begin_create_time")
  private Long beginCreateTime; //申请时间（单位毫秒）起始
  @SerializedName("end_create_time")
  private Long endCreateTime; //申请时间（单位毫秒）结束
  @SerializedName("offset")
  private Long offset; //本次拉取的起始位置（从0开始）
  @SerializedName("limit")
  private Integer limit; //本次拉取的大小（最大50）
}
