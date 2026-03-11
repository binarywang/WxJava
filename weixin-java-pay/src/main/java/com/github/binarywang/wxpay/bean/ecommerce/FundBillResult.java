package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;

/**
 * 资金账单结果
 *
 * @author f00lish
 * created on  2020/09/28
 */
@Data
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FundBillResult implements Serializable {
  private static final long serialVersionUID = 4008480977464421822L;

  /**
   * <pre>
   * 字段名：下载信息总数
   * 变量名：download_bill_count
   * 是否必填：是
   * 类型：int
   * 描述：
   *  下载信息总数
   *  示例值：1
   * </pre>
   */
  @SerializedName(value = "download_bill_count")
  private int downloadBillCount;


  /**
   * <pre>
   * 字段名：下载信息明细
   * 变量名：download_bill_list
   * 是否必填：否
   * 类型：array
   * 描述：
   *  下载信息明细
   * </pre>
   */
  @SerializedName(value = "download_bill_list")
  private FundBill[] downloadBillList;

  @Data
  public static class FundBill implements Serializable {

    private static final long serialVersionUID = 4008480977464421822L;

    /**
     * <pre>
     * 字段名：账单文件序号
     * 变量名：bill_sequence
     * 是否必填：是
     * 类型：int
     * 描述：
     *  商户将多个文件按账单文件序号的顺序合并为完整的资金账单文件，起始值为1
     *  示例值：1
     * </pre>
     */
    @SerializedName(value = "bill_sequence")
    private String billSequence;

    /**
     * <pre>
     * 字段名：哈希类型
     * 变量名：hash_type
     * 是否必填：是
     * 类型：string（32）
     * 描述：
     *  枚举值：
     *  SHA1：SHA1值
     *  示例值：SHA1
     * </pre>
     */
    @SerializedName(value = "hash_type")
    private String hashType;

    /**
     * <pre>
     * 字段名：哈希值
     * 变量名：hash_value
     * 是否必填：是
     * 类型：string（1024）
     * 描述：
     *  原始账单（gzip需要解压缩）的摘要值，用于校验文件的完整性。
     *  示例值：79bb0f45fc4c42234a918000b2668d689e2bde04
     * </pre>
     */
    @SerializedName(value = "hash_value")
    private String hashValue;

    /**
     * <pre>
     * 字段名：账单下载地址
     * 变量名：download_url
     * 是否必填：是
     * 类型：string（2048）
     * 描述：
     *  供下一步请求账单文件的下载地址，该地址30s内有效。
     *  示例值：https://api.mch.weixin.qq.com/v3/billdownload/file?token=xxx
     * </pre>
     */
    @SerializedName(value = "download_url")
    private String downloadUrl;


    /**
     * <pre>
     * 字段名：加密密钥
     * 变量名：encrypt_key
     * 是否必填：是
     * 类型：string（512）
     * 描述：
     *  加密账单文件使用的加密密钥。密钥用商户证书的公钥进行加密，然后进行Base64编码
     *  示例值：YpkbxSne+mDwyXq//xYPmtr9eQ5LsH7zLMZSs+GSEcY4wjhlsfioS4n9X6q1ZBL0wM1v5qd7KhWuj0rFJ4N1FidP7Q8KDy25QDTt46wiKnsPKSCAXWRFNw1D2JmJBqZsc9y5g0DupONWKYB2GfRigRDEBVszj67uOIILPdxOKX1w3N4jvu0U9IFanJa7ldm70KVvYrMWVgQFDPbgjh1gVDbuTAjmPN88AobLdkiegnBUS2woDZW+PfhPo13kweOiR3h1gXIKRlnKnN3Jkkwpna/AFFijXrFphO3voSuiV0CfptfzTtcae4X3DYG3RSroKqmpa+5tuy2aU2VJUSIuFQ==
     * </pre>
     */
    @SerializedName(value = "encrypt_key")
    private String encryptKey;

    /**
     * <pre>
     * 字段名：随机字符串
     * 变量名：nonce
     * 是否必填：是
     * 类型：string（16）
     * 描述：
     *  加密账单文件使用的随机字符串
     *  示例值：a8607ef79034c49c
     * </pre>
     */
    @SerializedName(value = "nonce")
    private String nonce;


  }

}
