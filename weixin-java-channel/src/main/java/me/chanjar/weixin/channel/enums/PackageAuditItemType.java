package me.chanjar.weixin.channel.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 商品打包审核项
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PackageAuditItemType {
  /** 商品快递单图片url */
  EXPRESS_PIC("product_express_pic_url", "商品快递单图片url"),
  /** 商品包装箱图片url */
  BOX_PIC("product_packaging_box_pic_url", "商品包装箱图片url"),
  /** 商品开箱图片url */
  UNBOXING_PIC("product_unboxing_pic_url", "商品开箱图片url"),
  /** 商品单个细节图片url */
  DETAIL_PIC("single_product_detail_pic_url", "商品单个细节图片url"),
  ;

  public final String key;
  public final String value;

  PackageAuditItemType(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }
}
