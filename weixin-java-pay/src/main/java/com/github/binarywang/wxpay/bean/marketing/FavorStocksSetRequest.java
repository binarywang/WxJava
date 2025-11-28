package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 激活代金券批次
 * 暂停代金券批次
 * 重启代金券批次
 * <pre>
 *   文档地址：
 *   https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_3.shtml
 *   https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_13.shtml
 *   https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_14.shtml
 * </pre>
 *
 * @author thinsstar
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavorStocksSetRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：创建批次的商户号
   * 变量名：stock_creator_mchid
   * 是否必填：是
   * 类型：string[1,20]
   * 描述：
   *  批次创建方商户号。
   *  示例值：8956000
   * </pre>
   */
  @SerializedName(value = "stock_creator_mchid")
  private String stockCreatorMchid;
}
