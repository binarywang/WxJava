package cn.binarywang.wx.miniapp.bean.qrcode;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * URL Link 二维码快速跳转规则列表返回值。
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaQrcodeJumpRuleListResponse implements Serializable {
  private static final long serialVersionUID = 6706970228943946110L;

  /**
   * 规则列表。
   */
  @SerializedName("rule_list")
  private List<WxMaQrcodeJumpRule> ruleList;
}

