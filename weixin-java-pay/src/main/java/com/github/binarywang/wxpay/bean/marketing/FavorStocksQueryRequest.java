package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 条件查询代金券批次列表
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_4.shtml
 * </pre>
 *
 * @author thinsstar
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavorStocksQueryRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：分页页码
   * 变量名：offset
   * 是否必填：是
   * 类型：uint32
   * 描述：
   *  页码从0开始，默认第0页。
   *  示例值：1
   * </pre>
   */
  @SerializedName(value = "offset")
  private Integer offset;

  /**
   * <pre>
   * 字段名：分页大小
   * 变量名：limit
   * 是否必填：是
   * 类型：uint32
   * 描述：
   *  分页大小，最大10。
   *  示例值：8
   * </pre>
   */
  @SerializedName(value = "limit")
  private Integer limit;

  /**
   * <pre>
   * 字段名：创建批次的商户号
   * 变量名：stock_creator_mchid
   * 是否必填：是
   * 类型：string[1,20]
   * 描述：
   *  批次创建方商户号。
   *  示例值：9856888
   * </pre>
   */
  @SerializedName(value = "stock_creator_mchid")
  private String stockCreatorMchid;

  /**
   * <pre>
   * 字段名：起始时间
   * 变量名：create_start_time
   * 是否必填：否
   * 类型：string[1,64]
   * 描述：
   *  起始创建时间，起始创建时间，遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
   *  校验规则：get请求，参数在 url中，需要进行 url 编码传递
   *  示例值：2015-05-20T13:29:35+08:00
   * </pre>
   */
  @SerializedName(value = "create_start_time")
  private String createStartTime;

  /**
   * <pre>
   * 字段名：终止时间
   * 变量名：create_end_time
   * 是否必填：否
   * 类型：string[1,64]
   * 描述：
   *  终止创建时间，遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
   *  校验规则：get请求，参数在 url中，需要进行 url 编码传递
   *  示例值：2015-05-20T13:29:35+08:00
   * </pre>
   */
  @SerializedName(value = "create_end_time")
  private String createEndTime;

  /**
   * <pre>
   * 字段名：批次状态
   * 变量名：status
   * 是否必填：否
   * 类型：string[1,20]
   * 描述：
   *  批次状态，枚举值：
   *  unactivated：未激活
   *  audit：审核中
   *  running：运行中
   *  stoped：已停止
   *  paused：暂停发放
   * </pre>
   */
  @SerializedName(value = "status")
  private String status;
}
