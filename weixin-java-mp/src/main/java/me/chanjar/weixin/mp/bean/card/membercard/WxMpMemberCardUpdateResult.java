package me.chanjar.weixin.mp.bean.card.membercard;

import java.io.Serializable;

import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * 用于 `7 更新会员信息` 的接口调用后的返回结果
 * <p>
 * {@code 参考：<a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1451025283">会员卡接口</a>}
 * </p>
 *
 * @author YuJian(mgcnrx11@gmail.com)
 * @version 2017/7/15
 */
@Data
public class WxMpMemberCardUpdateResult implements Serializable {

  private static final long serialVersionUID = 9084886191442098311L;

  private String errorCode;

  private String errorMsg;

  private String openId;

  private Integer resultBonus;

  private Double resultBalance;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  public static WxMpMemberCardUpdateResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpMemberCardUpdateResult.class);
  }
}
