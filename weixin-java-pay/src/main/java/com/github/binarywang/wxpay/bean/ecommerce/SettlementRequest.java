package com.github.binarywang.wxpay.bean.ecommerce;

import com.github.binarywang.wxpay.v3.SpecEncrypt;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * 普通服务商（支付机构、银行不可用），可使用本接口修改其进件、已签约的特约商户-结算账户信息。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_4.shtml
 * </pre>
 */
@Data
@NoArgsConstructor
public class SettlementRequest implements Serializable {

  /**
   * <pre>
   * 字段名：账户类型
   * 变量名：account_type
   * 是否必填：是
   * 类型：string(32)
   * 描述：
   *  根据特约商户号的主体类型，可选择的账户类型如下：
   * 1、小微主体：经营者个人银行卡
   * 2、个体工商户主体：经营者个人银行卡/ 对公银行账户
   * 3、企业主体：对公银行账户
   * 4、党政、机关及事业单位主体：对公银行账户
   * 5、其他组织主体：对公银行账户
   * 枚举值：
   *    ACCOUNT_TYPE_BUSINESS：对公银行账户
   *    ACCOUNT_TYPE_PRIVATE：经营者个人银行卡
   * 示例值：ACCOUNT_TYPE_BUSINESS
   * </pre>
   */
  @SerializedName(value = "account_type")
  private String accountType;

  /**
   * <pre>
   * 字段名：开户银行
   * 变量名：account_bank
   * 是否必填：是
   * 类型：string(128)
   * 描述：
   *  请填写开户银行名称，详细参见《开户银行对照表》https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter4_1.shtml。
   * 示例值：工商银行
   * </pre>
   */
  @SerializedName(value = "account_bank")
  private String accountBank;

  /**
   * <pre>
   * 字段名：开户银行省市编码
   * 变量名：bank_address_code
   * 是否必填：是
   * 类型：string(128)
   * 描述：
   *  需至少精确到市，详细参见《省市区编号对照表》。
   * 示例值：110000
   * </pre>
   */
  @SerializedName(value = "bank_address_code")
  private String bankAddressCode;

  /**
   * <pre>
   * 字段名：开户银行全称（含支行）
   * 变量名：bank_name
   * 是否必填：否
   * 类型：string(128)
   * 描述：
   *  若开户银行为“其他银行”，则需二选一填写“开户银行全称（含支行）”或“开户银行联行号”。
   * 填写银行全称，如"深圳农村商业银行XXX支行" ，详细参见开户银行全称（含支行）对照表。
   * 示例值：施秉县农村信用合作联社城关信用社
   * </pre>
   */
  @SerializedName(value = "bank_name")
  private String bankName;

  /**
   * <pre>
   * 字段名：开户银行联行号
   * 变量名：bank_branch_id
   * 是否必填：否
   * 类型：string(128)
   * 描述：
   *  若开户银行为“其他银行”，则需二选一填写“开户银行全称（含支行）”或“开户银行联行号”。
   * 填写银行联行号，详细参见《开户银行全称（含支行）对照表》。
   * 示例值：402713354941
   * </pre>
   */
  @SerializedName(value = "bank_branch_id")
  private String bankBranchId;

  /**
   * <pre>
   * 字段名：银行账号
   * 变量名：account_number
   * 是否必填：是
   * 类型：string(128)
   * 描述：
   *  1、数字，长度遵循系统支持的对公/对私卡号长度要求
   * 2、该字段需进行加密处理，加密方法详见《敏感信息加密说明》。(提醒：必须在HTTP头中上送Wechatpay-Serial)
   * </pre>
   */
  @SpecEncrypt
  @SerializedName(value = "account_number")
  private String accountNumber;
}
