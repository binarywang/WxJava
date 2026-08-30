package com.binarywang.wxjava.store.bean.sharer;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 分享员信息响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SharerInfoResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 1090517907546557929L;
  /** 分享员信息 */
  @JsonProperty("sharer_info_list")
  private List<SharerInfo> list;

}
