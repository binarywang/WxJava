package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 收集表统计请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpFormStatisticRequest implements Serializable {
  private static final long serialVersionUID = -1264428118237579449L;

  @SerializedName("repeated_id")
  private String repeatedId;

  @SerializedName("req_type")
  private Integer reqType;

  @SerializedName("start_time")
  private Long startTime;

  @SerializedName("end_time")
  private Long endTime;

  @SerializedName("limit")
  private Long limit;

  @SerializedName("cursor")
  private Long cursor;

  public static WxCpFormStatisticRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFormStatisticRequest.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  public static String toJson(List<WxCpFormStatisticRequest> requests) {
    return WxCpGsonBuilder.create().toJson(requests);
  }
}
