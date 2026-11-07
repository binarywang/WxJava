package com.binarywang.wxjava.store.bean.freight;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 不发货区域
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotSendArea extends AddressInfoList {

  private static final long serialVersionUID = -1836467830293286560L;
}
