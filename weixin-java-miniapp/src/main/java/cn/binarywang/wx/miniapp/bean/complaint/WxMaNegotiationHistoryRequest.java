package cn.binarywang.wx.miniapp.bean.complaint;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 小程序查询投诉协商历史请求实体
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on 2025-01-01
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class WxMaNegotiationHistoryRequest implements Serializable {
  private static final long serialVersionUID = 3244929701614280806L;

  /**
   * <pre>
   * 字段名：投诉单号
   * 是否必填：是
   * 描述：投诉单对应的投诉单号
   * </pre>
   */
  @SerializedName("complaint_id")
  private String complaintId;

  /**
   * <pre>
   * 字段名：分页大小
   * 是否必填：否
   * 描述：单次拉取条目，最大为50，不传默认为10
   * </pre>
   */
  @SerializedName("limit")
  @Builder.Default
  private Integer limit = 10;

  /**
   * <pre>
   * 字段名：分页开始位置
   * 是否必填：否
   * 描述：该次请求的分页开始位置，从0开始计数，例如offset=10，表示从第11条记录开始返回，不传默认为0
   * </pre>
   */
  @SerializedName("offset")
  @Builder.Default
  private Integer offset = 0;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}