package me.chanjar.weixin.channel.bean.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.OffsetParam;

/**
 * 用户地址 列表 请求参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.address.AddressListParam}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
@Deprecated
public class AddressListParam extends OffsetParam {

  private static final long serialVersionUID = -4434287264623932176L;

  public AddressListParam(Integer offset, Integer limit) {
    super(offset, limit);
  }
}
