package com.binarywang.wxjava.store.bean.product;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/** 获取商品移动应用跳转 scheme 码响应. */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductSchemeResponse extends WxStoreBaseResponse {
  private static final long serialVersionUID = 7310433919100539990L;

  private String openlink;
}
