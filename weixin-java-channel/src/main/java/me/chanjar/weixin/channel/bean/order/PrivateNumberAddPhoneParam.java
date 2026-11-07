package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加待认证手机号 请求参数
 *
 * @author GitHub Copilot
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.order.PrivateNumberAddPhoneParam}。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Deprecated
public class PrivateNumberAddPhoneParam implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 手机号 */
  @JsonProperty("phone")
  private String phone;

}
