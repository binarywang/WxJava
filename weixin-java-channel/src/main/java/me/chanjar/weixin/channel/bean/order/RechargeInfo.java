package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 虚拟商品充值账户信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class RechargeInfo implements Serializable {

  /** 虚拟商品充值账号，当account_type=qq或phone_number或mail的时候返回 */
  @JsonProperty("account_no")
  private String accountNo;

  /** 账号充值类型，可选项: weixin(微信号),qq(qq),phone_number(电话号码),mail(邮箱) */
  @JsonProperty("account_type")
  private String accountType;

  /** 当account_type="weixin"的时候返回 */
  @JsonProperty("wx_openid")
  private String wxOpenId;
}
