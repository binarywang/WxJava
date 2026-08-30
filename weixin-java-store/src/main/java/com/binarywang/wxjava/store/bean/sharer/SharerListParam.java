package com.binarywang.wxjava.store.bean.sharer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.binarywang.wxjava.store.bean.base.PageParam;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class SharerListParam extends PageParam {

  private static final long serialVersionUID = -2454284952706596246L;
  /** 分享员类型 {@link com.binarywang.wxjava.store.enums.SharerType} */
  @JsonProperty("sharer_type")
  private Integer sharerType;

  public SharerListParam() {
  }

  public SharerListParam(Integer page, Integer pageSize, Integer sharerType) {
    super(page, pageSize);
    this.sharerType = sharerType;
  }
}
