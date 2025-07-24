package me.chanjar.weixin.open.bean.authandicp;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

import java.util.List;

/**
 * @author 痴货
 * @Description
 * @createTime 2025/06/18 23:00
 */
@Getter
@Setter
@NoArgsConstructor
public class WxOpenSubmitAuthAndIcpResult extends WxOpenResult {

  private static final long serialVersionUID = -1175687058498454852L;

  /**
   * 错误提示
   */
  @SerializedName("hints")
  private List<Hint> hints;

  /**
   * 小程序认证及备案任务流程 id
   */
  @SerializedName("procedure_id")
  private String procedureId;

  /**
   * 小程序认证认证审核费用付费链接，当 pay_type 为 2 时返回
   */
  @SerializedName("pay_url")
  private String payUrl;

  @Data
  @EqualsAndHashCode(callSuper = true)
  public static class Hint extends WxOpenResult {

    private static final long serialVersionUID = 6585787444231265854L;

    /**
     * 校验失败的字段
     */
    @SerializedName("err_field")
    private String errField;
  }

}
