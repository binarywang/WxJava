package com.github.binarywang.wxpay.bean.invoice;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 服务商电子发票开通邀请链接。
 *
 * @author binarywang
 */
@Data
public class InviteUrlResult implements Serializable {
  private static final long serialVersionUID = 1L;

  @SerializedName("invite_url")
  private String inviteUrl;
}
