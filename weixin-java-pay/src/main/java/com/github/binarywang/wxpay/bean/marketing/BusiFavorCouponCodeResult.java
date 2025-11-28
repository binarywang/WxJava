package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 上传预存code返回对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_6.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class BusiFavorCouponCodeResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>* 字段名：批次号
   * 变量名：stock_id
   * 是否必填：是
   * 类型：string[1,20]
   * 描述：
   * 微信为每个商家券批次分配的唯一ID。 示例值：98065001
   * </pre>
   */
  @SerializedName(value = "stock_id")
  private String stockId;

  /**
   * <pre>* 字段名：去重后上传code总数
   * 变量名：total_count
   * 是否必填：是
   * 类型：uint64
   * 描述：
   * 本次上传操作，去重后实际上传的code数目。 示例值：500
   * </pre>
   */
  @SerializedName(value = "total_count")
  private Integer totalCount;

  /**
   * <pre>* 字段名：上传成功code个数
   * 变量名：success_count
   * 是否必填：是
   * 类型：uint64
   * 描述：
   * 本次上传操作上传成功个数。 示例值：20
   * </pre>
   */
  @SerializedName(value = "success_count")
  private Integer successCount;

  /**
   * <pre>* 字段名：上传成功的code列表
   * 变量名：success_codes
   * 是否必填：否
   * 类型：array
   * 描述：
   * 本次新增上传成功的code信息。 特殊规则：单个券code长度为【1，32】，条目个数限制为【1，200】。 示例值：MMAA12345
   * </pre>
   */
  @SerializedName(value = "success_codes")
  private List<String> successCodes;

  /**
   * <pre>* 字段名：上传成功时间
   * 变量名：success_time
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   * 上传操作完成时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。 示例值：2015-05-20T13:29:35+08:00
   * </pre>
   */
  @SerializedName(value = "success_time")
  private String successTime;

  /**
   * <pre>* 字段名：上传失败code个数
   * 变量名：fail_count
   * 是否必填：否
   * 类型：uint64
   * 描述：
   * 本次上传操作上传失败的code数。 示例值：10
   * </pre>
   */
  @SerializedName(value = "fail_count")
  private Integer failCount;

  /**
   * <pre>* 字段名：+上传失败的code及原因
   * 变量名：fail_codes
   * 是否必填：否
   * 类型：array
   * 描述：
   * 本次导入失败的code信息，请参照错误信息，修改后重试。
   * </pre>
   */
  @SerializedName(value = "fail_codes")
  private List<FailCode> failCodes;

  /**
   * <pre>* 字段名：已存在的code列表
   * 变量名：exist_codes
   * 是否必填：否
   * 类型：array
   * 描述：
   * 历史已存在的code列表，本次不会重复导入。 特殊规则：单个券code长度为【1，32】，条目个数限制为【1，200】。 示例值：ABCD2345
   * </pre>
   */
  @SerializedName(value = "exist_codes")
  private List<String> existCodes;

  /**
   * <pre>* 字段名：本次请求中重复的code列表
   * 变量名：duplicate_codes
   * 是否必填：否
   * 类型：array
   * 描述：
   * 本次重复导入的code会被自动过滤，仅保留一个做导入，如满足要求则成功；如不满足要求，则失败；请参照报错提示修改重试。 特殊规则：单个券code长度为【1，32】，条目个数限制为【1，200】。 示例值：AACC2345
   * </pre>
   */
  @SerializedName(value = "duplicate_codes")
  private List<String> duplicateCodes;

  @Data
  @NoArgsConstructor
  public static class FailCode implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <pre>* 字段名：上传失败的券code
     * 变量名：coupon_code
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     * 商户通过API上传的券code。 示例值：ABCD23456
     * </pre>
     */
    @SerializedName(value = "coupon_code")
    private String couponCode;

    /**
     * <pre>* 字段名：上传失败错误码
     * 变量名：code
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     * 对应券code上传失败的错误码。 示例值：LENGTH_LIMIT
     * </pre>
     */
    @SerializedName(value = "code")
    private String code;

    /**
     * <pre>* 字段名：上传失败错误信息
     * 变量名：message
     * 是否必填：是
     * 类型：string[1,128]
     * 描述：
     * 上传失败的错误信息描述。 示例值：长度超过最大值32位
     * </pre>
     */
    @SerializedName(value = "message")
    private String message;
  }
}
