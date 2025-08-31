package cn.binarywang.wx.miniapp.bean.complaint;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 小程序查询投诉协商历史结果
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on 2025-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaNegotiationHistoryResult extends WxMaBaseResponse {

  /**
   * <pre>
   * 字段名：协商历史
   * 是否必填：是
   * 描述：协商历史记录
   * </pre>
   */
  @SerializedName("data")
  private List<NegotiationHistory> data;

  /**
   * <pre>
   * 字段名：总数
   * 是否必填：是
   * 描述：总协商历史条数，用于分页展示
   * </pre>
   */
  @SerializedName("total_count")
  private Integer totalCount;

  /**
   * 协商历史
   */
  @Data
  public static class NegotiationHistory implements Serializable {
    private static final long serialVersionUID = 3244929701614280806L;

    /**
     * <pre>
     * 字段名：操作时间
     * 是否必填：是
     * 描述：操作时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE
     * </pre>
     */
    @SerializedName("operate_time")
    private String operateTime;

    /**
     * <pre>
     * 字段名：操作类型
     * 是否必填：是
     * 描述：操作类型：USER_CREATE_COMPLAINT-用户提交投诉 USER_CONTINUE_COMPLAINT-用户继续投诉 MERCHANT_RESPONSE-商户回复 MERCHANT_CONFIRM_COMPLETE-商户确认完成处理
     * </pre>
     */
    @SerializedName("operate_type")
    private String operateType;

    /**
     * <pre>
     * 字段名：操作内容
     * 是否必填：是
     * 描述：具体的操作内容
     * </pre>
     */
    @SerializedName("operate_details")
    private String operateDetails;

    /**
     * <pre>
     * 字段名：图片凭证
     * 是否必填：否
     * 描述：操作过程中上传的图片凭证
     * </pre>
     */
    @SerializedName("image_list")
    private List<String> imageList;
  }
}