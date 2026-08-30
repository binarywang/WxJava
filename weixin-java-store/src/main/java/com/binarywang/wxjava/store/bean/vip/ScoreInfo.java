package com.binarywang.wxjava.store.bean.vip;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微信小店-会员功能 - 订单详情
 *
 * @author <a href="https://github.com/asushiye">asushiye</a>
 *
 */
@Data
@NoArgsConstructor
public class ScoreInfo implements Serializable {

  private static final long serialVersionUID = -3290653233070826576L;
  /** 积分 */
  @JsonProperty("score")
  protected String score;
}
