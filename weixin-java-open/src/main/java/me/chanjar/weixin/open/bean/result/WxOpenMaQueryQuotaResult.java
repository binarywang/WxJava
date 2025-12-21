package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

/**
 * 微信开放平台小程序提交审核额度查询结果
 * <p>
 * 用于查询第三方平台服务商的当月提交审核限额和加急次数
 * </p>
 * <p>
 * <b>字段说明：</b>
 * </p>
 * <ul>
 *   <li>rest: 当月剩余提交审核次数</li>
 *   <li>limit: 当月提交审核额度上限（默认20次，可联系微信开放平台增加）</li>
 *   <li>speedupRest: 剩余加急次数</li>
 *   <li>speedupLimit: 加急额度上限</li>
 * </ul>
 * <p>
 * <b>重要提示：</b>
 * </p>
 * <ul>
 *   <li>每次调用 submitAudit 提交小程序审核，会消耗1个审核额度</li>
 *   <li>额度每月初自动重置</li>
 *   <li>审核撤回不会返还已消耗的额度</li>
 *   <li>建议在批量提交审核前，先检查剩余额度是否充足</li>
 * </ul>
 *
 * @see me.chanjar.weixin.open.api.WxOpenMaService#queryQuota()
 * @see me.chanjar.weixin.open.api.WxOpenMaService#submitAudit(me.chanjar.weixin.open.bean.message.WxOpenMaSubmitAuditMessage)
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaQueryQuotaResult extends WxOpenResult {

  private static final long serialVersionUID = 5915265985261653007L;

  /**
   * 当月剩余提交审核次数
   */
  @SerializedName("rest")
  private Integer rest;

  /**
   * 当月提交审核额度上限
   */
  @SerializedName("limit")
  private Integer limit;

  /**
   * 剩余加急次数
   */
  @SerializedName("speedup_rest")
  private Integer speedupRest;

  /**
   * 加急额度上限
   */
  @SerializedName("speedup_limit")
  private Integer speedupLimit;


  @Override
  public String toString() {
    return WxOpenGsonBuilder.create().toJson(this);
  }


}
