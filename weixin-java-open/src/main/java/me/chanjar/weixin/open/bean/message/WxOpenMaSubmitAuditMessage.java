package me.chanjar.weixin.open.bean.message;

import cn.binarywang.wx.miniapp.bean.code.WxMaCodeSubmitAuditItem;
import cn.binarywang.wx.miniapp.bean.code.WxMaCodeSubmitAuditPreviewInfo;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 微信小程序代码包提交审核(仅供第三方开发者代小程序调用)
 * <p>
 * <b>重要提示：审核额度限制</b>
 * </p>
 * <ul>
 *   <li>每个第三方平台账号每月有审核额度限制（默认20次）</li>
 *   <li>每次调用 submitAudit 提交审核会消耗1个额度，无论审核是否通过</li>
 *   <li>建议在提交前先调用 queryQuota 检查剩余额度</li>
 * </ul>
 * <p>
 * <b>使用示例：</b>
 * </p>
 * <pre>{@code
 * // 1. 构建审核项
 * WxMaCodeSubmitAuditItem item = new WxMaCodeSubmitAuditItem();
 * item.setAddress("index");
 * item.setTag("游戏");
 * item.setFirstClass("游戏");
 * item.setSecondClass("休闲游戏");
 * item.setTitle("首页");
 *
 * // 2. 构建提交审核消息
 * WxOpenMaSubmitAuditMessage message = new WxOpenMaSubmitAuditMessage();
 * message.setItemList(Collections.singletonList(item));
 * message.setVersionDesc("版本描述");
 *
 * // 3. 提交审核
 * WxOpenMaSubmitAuditResult result = wxOpenMaService.submitAudit(message);
 * System.out.println("审核ID: " + result.getAuditId());
 * }</pre>
 *
 * @author yqx
 * @see me.chanjar.weixin.open.api.WxOpenMaService#submitAudit(WxOpenMaSubmitAuditMessage)
 * @see me.chanjar.weixin.open.api.WxOpenMaService#queryQuota()
 * created on  2018/9/13
 */
@Data
public class WxOpenMaSubmitAuditMessage implements Serializable {
  private static final long serialVersionUID = 8881103449144288927L;

  /**
   * 提交审核项的一个列表（至少填写1项，至多填写5项）
   */
  @SerializedName("item_list")
  private List<WxMaCodeSubmitAuditItem> itemList;

  /**
   * 预览信息（小程序页面截图和操作录屏）
   */
  @SerializedName("preview_info")
  private WxMaCodeSubmitAuditPreviewInfo previewInfo;

  /**
   * 小程序版本说明和功能解释
   */
  @SerializedName("version_desc")
  private String versionDesc;

  /**
   * 反馈内容，不超过200字
   */
  @SerializedName("feedback_info")
  private String feedbackInfo;

  /**
   * 图片media_id列表，中间用“丨”分割，xx丨yy丨zz，不超过5张图片, 其中 media_id 可以通过新增临时素材接口上传而得到
   */
  @SerializedName("feedback_stuff")
  private String feedbackStuff;

  /**
   * 用于声明是否不使用“代码中检测出但是未配置的隐私相关接口”
   */
  @SerializedName("privacy_api_not_use")
  private Boolean privacyApiNotUse;

  /**
   * 订单中心path
   */
  @SerializedName("order_path")
  private String orderPath;
}
