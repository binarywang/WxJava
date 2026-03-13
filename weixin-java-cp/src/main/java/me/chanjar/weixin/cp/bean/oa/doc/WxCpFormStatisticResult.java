package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 收集表统计信息查询结果.
 *
 * <p>
 * 接口响应格式:
 * <pre>
 * {
 *   "errcode": 0,
 *   "errmsg": "ok",
 *   "statistic_list": [...]
 * }
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpFormStatisticResult extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -3648892040578890220L;

  @SerializedName("statistic_list")
  private List<WxCpFormStatistic> statisticList;

  public static WxCpFormStatisticResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFormStatisticResult.class);
  }
}
