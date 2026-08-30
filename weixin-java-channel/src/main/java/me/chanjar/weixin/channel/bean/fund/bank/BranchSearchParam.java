package me.chanjar.weixin.channel.bean.fund.bank;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 银行支行信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class BranchSearchParam implements Serializable {

  private static final long serialVersionUID = -8800316690160248833L;
  /** 银行编码,通过查询银行信息或者搜索银行信息获取 */
  @JsonProperty("bank_code")
  private String bankCode;

  /** 城市编号,通过查询城市列表获取 */
  @JsonProperty("city_code")
  private String cityCode;

  /** 偏移量 */
  @JsonProperty("offset")
  private Integer offset;

  /** 限制个数 */
  @JsonProperty("limit")
  private Integer limit;
}
