package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 上传预存code请求对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_6.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class BusiFavorCouponCodeRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>* 字段名：批次号
   * 变量名：stock_id
   * 是否必填：是
   * 类型：string[1,20]
   * 描述：
   * path 微信为每个商家券批次分配的唯一ID 示例值：98065001
   * </pre>
   */
  @SerializedName(value = "stock_id")
  private String stockId;

/**
 * <pre>* 字段名：券code列表
 * 变量名：coupon_code_list
 * 是否必填：否
 * 类型：array
 * 描述：
 * body 商户上传的券code列表，code允许包含的字符有0-9、a-z、A-Z、-、_、\、/、=、|。 特殊规则：单个券code长度为【1，32】，条目个数限制为【1，200】。 示例值：ABC9588200，ABC9588201
 * </pre>
 */
  @SerializedName(value = "coupon_code_list")
  private List<String> couponCodeList;

  /**
   * <pre>* 字段名：请求业务单据号
   * 变量名：upload_request_no
   * 是否必填：是
   * 类型：string[1,128]
   * 描述：
   * body 商户上传code的凭据号，商户侧需保持唯一性。 示例值：100002322019090134234sfdf
   * </pre>
   */
  @SerializedName(value = "upload_request_no")
  private String uploadRequestNo;
}
