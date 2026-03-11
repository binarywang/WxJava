package com.github.binarywang.wxpay.bean.bank;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 支行列表
 *
 * @author hupeng
 **/
@Data
public class BankBranchesResult implements Serializable {

  private static final long serialVersionUID = -3500020131951579476L;

  /**
   * <pre>
   * 字段名：查询数据总条数
   * 变量名：total_count
   * 是否必填：是
   * 类型：int
   * 描述：
   *  经过条件筛选，查询到的支行总数
   *  示例值：10
   * </pre>
   */
  @SerializedName("total_count")
  private Integer totalCount;

  /**
   * <pre>
   * 字段名：本次查询条数
   * 变量名：count
   * 是否必填：是
   * 类型：int
   * 描述：
   *  本次查询到的支行数据条数
   *  示例值：10
   * </pre>
   */
  @SerializedName("count")
  private Integer count;

  /**
   * <pre>
   * 字段名：支行列表
   * 变量名：data
   * 是否必填：否
   * 类型：array
   * 描述：
   *  单次查询返回的支行列表结果数组
   * </pre>
   */
  @SerializedName("data")
  private List<BankBranch> data;

  /**
   * <pre>
   * 字段名：本次查询偏移量
   * 变量名：offset
   * 是否必填：是
   * 类型：int
   * 描述：
   *  该次请求资源的起始位置，请求中包含偏移量时应答消息返回相同偏移量，否则返回默认值0
   *  示例值：0
   * </pre>
   */
  @SerializedName("offset")
  private Integer offset;

  /**
   * <pre>
   * 字段名：分页链接
   * 变量名：offset
   * 是否必填：是
   * 类型：object
   * 描述：
   *  返回前后页和当前页面的访问链接
   * </pre>
   */
  @SerializedName("links")
  private PageLink links;

  /**
   * <pre>
   * 字段名：开户银行
   * 变量名：account_bank
   * 是否必填：是
   * 类型：string[1, 128]
   * 描述：
   *  查询到的支行所属开户银行的名称，非直连银行统一为其他银行
   *  示例值：招商银行其他银行
   * </pre>
   */
  @SerializedName("account_bank")
  private String accountBank;

  /**
   * <pre>
   * 字段名：开户银行编码
   * 变量名：account_bank_code
   * 是否必填：是
   * 类型：int
   * 描述：
   *  查询到的支行所属开户银行的开户银行编码，可用于付款到银行卡等场景中指定银行卡的开户银行
   *  示例值：1001
   * </pre>
   */
  @SerializedName("account_bank_code")
  private Integer accountBankCode;

  /**
   * <pre>
   * 字段名：银行别名
   * 变量名：bank_alias
   * 是否必填：是
   * 类型：string[1, 128]
   * 描述：
   *  查询到的支行所属银行的银行别名
   *  示例值：工商银行深圳前海微众银行
   * </pre>
   */
  @SerializedName("bank_alias")
  private String bankAlias;

  /**
   * <pre>
   * 字段名：银行别名编码
   * 变量名：bank_alias_code
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  查询到的支行所属银行的银行别名编码，用于校验回包
   *  示例值：1000006247
   * </pre>
   */
  @SerializedName("bank_alias_code")
  private String bankAliasCode;

  @Getter
  @Setter
  public static class BankBranch implements Serializable {

    private static final long serialVersionUID = -3500020131951579476L;

    /**
     * <pre>
     * 字段名：开户银行支行名称
     * 变量名：bank_branch_name
     * 是否必填：是
     * 类型：string[1, 128]
     * 描述：
     *  开户银行支行名称，用于开户银行为其他银行的情况下，在入驻、修改结算银行卡、企业付款等场景下填写结算银行卡信息。
     *  示例值：中国工商银行上海市周浦支行
     * </pre>
     */
    @SerializedName("bank_branch_name")
    private String bankBranchName;

    /**
     * <pre>
     * 字段名：开户银行支行联行号
     * 变量名：bank_branch_id
     * 是否必填：是
     * 类型：string[1, 64]
     * 描述：
     *  开户银行支行的联行号，用于开户银行为其他银行的情况下，在入驻、修改结算银行卡、企业付款等场景下填写结算银行卡信息。
     *  示例值：102290072311
     * </pre>
     */
    @SerializedName("bank_branch_id")
    private String bankBranchId;
  }
}
