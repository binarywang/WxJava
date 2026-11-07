package com.binarywang.wxjava.store.bean.message.voucher;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.message.WxStoreMessage;

/**
 * 发放团购优惠成功消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
public class VoucherMessage extends WxStoreMessage {

  private static final long serialVersionUID = 975858675917036089L;

  /** 发放团购优惠成功消息 */
  @JsonProperty("voucher_list")
  @JacksonXmlProperty(localName = "voucher_list")
  private List<VoucherInfo> voucherInfo;
}
