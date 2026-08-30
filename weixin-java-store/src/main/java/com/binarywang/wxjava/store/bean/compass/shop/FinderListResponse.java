package com.binarywang.wxjava.store.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 带货达人列表 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FinderListResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 6358992001065379269L;

  /** 授权视频号id列表 */
  @JsonProperty("finder_list")
  private List<FinderGmvItem> finderList;

}
