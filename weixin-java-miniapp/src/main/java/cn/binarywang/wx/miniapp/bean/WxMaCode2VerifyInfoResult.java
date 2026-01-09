package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <pre>
 * 多端登录验证接口的响应
 * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/miniapp/openapi/code2Verifyinfo.html
 *
 * 微信返回报文：{"errcode": 0, "errmsg": "ok", "session_key":"xxx", "openid":"xxx", "unionid":"xxx", "is_limit": false}
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxMaCode2VerifyInfoResult implements Serializable {
  private static final long serialVersionUID = -2468325025088437364L;

  @SerializedName("session_key")
  private String sessionKey;

  @SerializedName("openid")
  private String openid;

  @SerializedName("unionid")
  private String unionid;

  /**
   * 是否为受限用户
   */
  @SerializedName("is_limit")
  private Boolean isLimit;

  public static WxMaCode2VerifyInfoResult fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaCode2VerifyInfoResult.class);
  }

}
