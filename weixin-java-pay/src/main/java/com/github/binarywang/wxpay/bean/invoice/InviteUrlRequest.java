package com.github.binarywang.wxpay.bean.invoice;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/** 获取服务商电子发票能力邀请链接请求。 */
@Data
public class InviteUrlRequest implements Serializable {
  private static final long serialVersionUID = 1L;
  private String subMchid;
  private String operationType;
  private String fapiaoMode;
  private List<String> fapiaoAbilityTypeList;
  private String inviteChannel;
  private String operateUser;
  private String inviteCode;
}
