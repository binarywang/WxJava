package cn.binarywang.wx.miniapp.bean.employee;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 小程序推送用工消息请求实体
 * <p>
 * 文档地址：<a href="https://developers.weixin.qq.com/miniprogram/dev/server/API/laboruse/api_sendemployeerelationmsg.html">推送用工消息</a>
 * </p>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on 2025-12-19
 * update on 2026-01-22 15:13:28
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class WxMaSendEmployeeMsgRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：模板id
   * 是否必填：是
   * 描述：需要在微信后台申请用工关系权限,通过后创建的模板审核通过后可以复制模板ID
   * </pre>
   */
  @SerializedName("template_id")
  private String templateId;

  /**
   * <pre>
   * 字段名：页面
   * 是否必填：是
   * 描述：用工消息通知跳转的page小程序链接(注意 小程序页面链接要是申请模板的小程序)
   * </pre>
   */
  @SerializedName("page")
  private String page;

  /**
   * <pre>
   * 字段名：被推送用户的openId
   * 是否必填：是
   * 描述：被推送用户的openId
   * </pre>
   */
  @SerializedName("touser")
  private String touser;

  /**
   * <pre>
   * 字段名：消息内容
   * 是否必填：是
   * 描述：需要根据小程序后台审核通过的模板id的字段类型序列化json传递
   *
   * </pre>
   *
   * 参考组装代码
   * <pre>
   * <code>
   * // 使用 HashMap 构建数据结构
   * Map<String, Object> data1 = new HashMap<>();
   * // 内层字段
   * Map<String, String> thing1 = new HashMap<>();
   * Map<String, String> thing2 = new HashMap<>();
   * Map<String, String> time1 = new HashMap<>();
   * Map<String, String> character_string1 = new HashMap<>();
   * Map<String, String> time2 = new HashMap<>();
   * thing1.put("value", "高和蓝枫箱体测试");
   * thing2.put("value", "门口全英测试");
   * time1.put("value", "2026年11月23日 19:19");
   * character_string1.put("value", "50kg");
   * time2.put("value", "2026年11月23日 19:19");
   *
   * // 模板消息变量，有顺序要求
   * Map<String, Object> dataContent = new LinkedHashMap<>();
   * dataContent.put("thing1", thing1);
   * dataContent.put("thing2", thing2);
   * dataContent.put("time1", time1);
   * dataContent.put("character_string1", character_string1);
   * dataContent.put("time2", time2);
   *
   * data1.put("data", dataContent);
   * </code>
   * </pre>
   */

  @SerializedName("data")
  private String data;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
