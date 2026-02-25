package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Builder;
import lombok.Data;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

import java.io.Serializable;
import java.util.List;

/**
 * @author borisbao
 */
@Data
public class WxMaMediaAsyncCheckResult implements Serializable {
  private static final long serialVersionUID = 3928132365399916183L;

  /**
   * 任务id，用于匹配异步推送结果
   */
  @SerializedName("trace_id")
  private String traceId;

  /**
   * 综合结果
   */
  @SerializedName("result")
  private ResultBean result;

  /**
   * 详细检测结果列表
   */
  @SerializedName("detail")
  private List<DetailBean> detail;

  /**
   * 综合结果内部类定义
   */
  @Data
  @Builder
  @XStreamAlias("result")
  public static class ResultBean implements Serializable {
    /**
     * suggest : risky
     * label : 20001
     */
    @SerializedName("suggest")
    @XStreamAlias("suggest")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String suggest;
    @SerializedName("label")
    @XStreamAlias("label")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String label;
  }

  /**
   * 详细检测结果
   */
  @Data
  @Builder
  @XStreamAlias("detail")
  public static class DetailBean implements Serializable {
    /**
     * strategy : content_model
     * errcode : 0
     * suggest : risky
     * label : 20006
     * prob : 90
     */
    @SerializedName("strategy")
    @XStreamAlias("strategy")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String strategy;
    @SerializedName("errcode")
    @XStreamAlias("errcode")
    private Integer errcode;
    @SerializedName("suggest")
    @XStreamAlias("suggest")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String suggest;
    @SerializedName("label")
    @XStreamAlias("label")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String label;
    @SerializedName("prob")
    @XStreamAlias("prob")
    private Integer prob;
  }

  public static WxMaMediaAsyncCheckResult fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaMediaAsyncCheckResult.class);
  }

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
