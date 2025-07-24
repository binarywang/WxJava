package cn.binarywang.wx.miniapp.bean.express.result;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.GsonParser;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 运单信息返回结果对象
 * </pre>
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2019-11-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMaExpressOrderInfoResult extends WxMaExpressInfoResult implements Serializable {

  private static final long serialVersionUID = -9166603059965942285L;

  /**
   * 订单ID
   */
  @SerializedName("order_id")
  private String orderId;

  /**
   * 运单ID
   */
  @SerializedName("waybill_id")
  private String waybillId;

  /**
   * 运单 html 的 BASE64 结果
   */
  @SerializedName("print_html")
  private String printHtml;

  /**
   * 运单信息
   */
  @SerializedName("waybill_data")
  private List<Map<String,String>> waybillData;

  /**
   * 运单状态, 0正常，1取消
   */
  @SerializedName("order_status")
  private Integer orderStatus;

  public static WxMaExpressOrderInfoResult fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaExpressOrderInfoResult.class);
  }

  public static List<WxMaExpressOrderInfoResult> toList(String json) {
    JsonObject jsonObject = GsonParser.parse(json);
    return WxMaGsonBuilder.create().fromJson(jsonObject.get("order_list").toString(),
      new TypeToken<List<WxMaExpressOrderInfoResult>>() {
      }.getType());
  }
}
