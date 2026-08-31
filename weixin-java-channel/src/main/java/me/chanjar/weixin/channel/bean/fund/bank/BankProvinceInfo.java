package me.chanjar.weixin.channel.bean.fund.bank;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 银行省份信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.fund.bank.BankProvinceInfo}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class BankProvinceInfo implements Serializable {

  private static final long serialVersionUID = -3409931656361300144L;
  /** 省份名称 */
  @JsonProperty("province_name")
  private String provinceName;

  /** 省份编码 */
  @JsonProperty("province_code")
  private Integer provinceCode;
}
