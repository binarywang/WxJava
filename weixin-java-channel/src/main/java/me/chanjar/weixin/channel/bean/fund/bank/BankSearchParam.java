package me.chanjar.weixin.channel.bean.fund.bank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 银行查询参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.fund.bank.BankSearchParam}。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class BankSearchParam implements Serializable {

  private static final long serialVersionUID = 6070269209439188188L;
  /** 偏移量 */
  @JsonProperty("offset")
  private Integer offset;

  /** 每页数据大小 */
  @JsonProperty("limit")
  private Integer limit;

  /** 银行关键字 */
  @JsonProperty("key_words")
  private String keyWords;

  /** 银行类型(1:对私银行,2:对公银行; 默认对公) */
  @JsonProperty("bank_type")
  private Integer bankType;
}
