package com.binarywang.wxjava.store.bean.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 资质文件id响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class QualificationFileResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 5172377567441096813L;

  /** 文件数据 */
  @JsonProperty("data")
  private QualificationFileId data;
}
