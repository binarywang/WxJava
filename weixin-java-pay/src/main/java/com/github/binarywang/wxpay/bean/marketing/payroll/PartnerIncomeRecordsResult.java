package com.github.binarywang.wxpay.bean.marketing.payroll;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 特约商户银行来账查询
 * 文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter4_1_27.shtml
 *
 * 适用对象：服务商
 * 请求URL：https://api.mch.weixin.qq.com/v3/merchantfund/partner/income-records
 * 请求方式：GET
 * </pre>
 *
 * @author xiaoqiang
 * created on  2021/12/7
 */
@Data
@NoArgsConstructor
public class PartnerIncomeRecordsResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：查询数据总条数
   * 变量名：total_count
   * 是否必填：是
   * 类型：int
   * 描述：
   *  经过条件筛选，查询到的银行来账记录总数 。
   * 示例值：20
   * </pre>
   */
  @SerializedName(value = "total_count")
  private int totalCount;

  /**
   * <pre>
   * 字段名：本次查询偏移量
   * 变量名：offset
   * 是否必填：否
   * 类型：int
   * 描述：
   * 该次请求资源的起始位置，请求中包含偏移量时应答消息返回相同偏移量，否则返回默认值0。
   * 示例值：0
   * </pre>
   */
  @SerializedName(value = "offset")
  private int offset;
  /**
   * <pre>
   * 字段名：本次请求最大查询条数
   * 变量名：limit
   * 是否必填：是
   * 类型：int
   * 描述：
   * 经过条件筛选，本次查询到的银行来账记录条数。
   * 示例值：100
   * </pre>
   */
  @SerializedName(value = "limit")
  private int limit;
  /**
   * <pre>
   * 字段名：银行来账记录列表
   * 变量名：data
   * 是否必填：否
   * 类型：array
   * 描述：
   *  单次查询返回的银行来账记录列表结果，如果查询结果为空时，则为空数组。
   * </pre>
   */
  @SerializedName(value = "data")
  private List<IncomeRecordData> incomeRecordDataList;


  @Data
  @NoArgsConstructor
  public static class IncomeRecordData implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <pre>
     * 字段名：特约商户号
     * 变量名：sub_mchid
     * 是否必填：是
     * 类型：string[1, 32]
     * 描述：
     *  需查询银行来账记录列表的特约商户的商户号，该商户号须为服务商的特约商户号。
     * 示例值：2480253391
     * </pre>
     */
    @SerializedName(value = "sub_mchid")
    private String subMchid;

    /**
     * <pre>
     * 字段名：账户类型
     * 变量名：account_type
     * 是否必填：是
     * 类型：string[1, 32]
     * 描述：
     *  需查询银行来账记录商户的账户类型。
     *     枚举值：
     *     BASIC：基本账户
     *     OPERATION：运营账户
     *     FEES：手续费账户
     * 示例值：BASIC
     * </pre>
     */
    @SerializedName(value = "account_type")
    private String accountType;
    /**
     * <pre>
     * 字段名：银行来账类型
     * 变量名：income_record_type
     * 是否必填：是
     * 类型：string[1, 64]
     * 描述：
     *  银行来账类型，后续会有所扩展。
     *     枚举值：
     *     OFFLINERECHARGE：转账充值
     *     ENTERPRISEDIRECTREVENUE：企业直收
     * 示例值：OFFLINERECHARGE
     * </pre>
     */
    @SerializedName(value = "income_record_type")
    private String incomeRecordType;
    /**
     * <pre>
     * 字段名：银行来账微信单号
     * 变量名：income_record_id
     * 是否必填：是
     * 类型：string[1, 64]
     * 描述：
     *  银行来账的微信单号
     * 示例值：4200000811202011056138519459
     * </pre>
     */
    @SerializedName(value = "income_record_id")
    private String incomeRecordId;
    /**
     * <pre>
     * 字段名：银行来账金额
     * 变量名：amount
     * 是否必填：是
     * 类型：int
     * 描述：
     *  银行来账金额，单位为分，只能为整数。
     * 示例值：2734921
     * </pre>
     */
    @SerializedName(value = "amount")
    private String amount;
    /**
     * <pre>
     * 字段名：银行来账完成时间
     * 变量名：success_time
     * 是否必填：是
     * 类型：string[1, 32]
     * 描述：
     *  银行来账完成时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
     * 示例值：2017-12-08T00:08:00.00+08:00
     * </pre>
     */
    @SerializedName(value = "success_time")
    private String successTime;
    /**
     * <pre>
     * 字段名：付款方银行名称
     * 变量名：bank_name
     * 是否必填：是
     * 类型：string[1, 256]
     * 描述：
     *  银行来账的付款方银行名称，由于部分银行的数据获取限制，该字段有可能为空。
     * 示例值：招商银行
     * </pre>
     */
    @SerializedName(value = "bank_name")
    private String bankName;
    /**
     * <pre>
     * 字段名：付款方银行户名
     * 变量名：bank_account_name
     * 是否必填：是
     * 类型：string[1, 256]
     * 描述：
     *  银行来账的付款方银行账户信息，户名为全称、明文，由于部分银行的数据获取限制，该字段有可能为空。
     * 示例值：北京三快科技有限公司
     * </pre>
     */
    @SerializedName(value = "bank_account_name")
    private String bankAccountName;
    /**
     * <pre>
     * 字段名：付款方银行卡号
     * 变量名：bank_account_number
     * 是否必填：是
     * 类型：string[1, 64]
     * 描述：
     *  四位掩码+付款方银行卡尾号后四位。
     * 示例值：****6473
     * </pre>
     */
    @SerializedName(value = "bank_account_number")
    private String bankAccountNumber;
    /**
     * <pre>
     * 字段名：银行备注
     * 变量名：recharge_remark
     * 是否必填：是
     * 类型：string[1, 256]
     * 描述：
     *  随银行转账时，商户填入的附言、摘要等信息，目前支持的银行及填写指引请查看各银行对账详情
     * 示例值：单号：202106010001
     * </pre>
     */
    @SerializedName(value = "recharge_remark")
    private String rechargeRemark;
  }

  /**
   * <pre>
   * 字段名：分页链接
   * 变量名：links
   * 是否必填：是
   * 类型：object
   * 描述：
   *  返回前后页和当前页面的访问链接
   * </pre>
   */
  @SerializedName(value = "links")
  private List<LinksData> linksDataList;


  @Data
  @NoArgsConstructor
  public static class LinksData implements Serializable {
    private static final long serialVersionUID = 109053454401492L;

    /**
     * <pre>
     * 字段名：下一页链接
     * 变量名：next
     * 是否必填：是
     * 类型：string[1, 2048]
     * 描述：
     *  使用同样的limit进行下一页查询时的相对请求链接，使用方需要自行根据当前域名进行拼接。如果已经到最后时，为空 。
     * 示例值：/v3/merchantfund/partner/income-records?offset=10&limit=5
     * </pre>
     */
    @SerializedName(value = "next")
    private String next;
    /**
     * <pre>
     * 字段名：上一页链接
     * 变量名：prev
     * 是否必填：是
     * 类型：string[1, 2048]
     * 描述：
     *  使用同样的limit进行上一页查询时的相对请求链接，使用方需要自行根据当前域名进行拼接。如果是第一页，为空。
     * 示例值：/v3/merchantfund/partner/income-records?offset=0&limit=5
     * </pre>
     */
    @SerializedName(value = "prev")
    private String prev;
    /**
     * <pre>
     * 字段名：当前链接
     * 变量名：self
     * 是否必填：是
     * 类型：string[1, 2048]
     * 描述：
     *  当前的相对请求链接，使用方需要自行根据当前域名进行拼接。
     * 示例值：/v3/merchantfund/partner/income-records?offset=5&limit=5
     * </pre>
     */
    @SerializedName(value = "self")
    private String self;
  }
}
