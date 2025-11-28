package com.github.binarywang.wxpay.bean.brandmerchanttransfer.request;

import com.github.binarywang.wxpay.v3.SpecEncrypt;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 发起品牌红包商家转账API参数
 *
 * @author moran
 **/
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class BrandTransferBatchesRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：品牌ID
   * 变量名：brand_id
   * 是否必填：是
   * 类型：int
   * 描述：
   *  body品牌在微信支付进行品牌认证后的唯一标识品牌ID
   * 示例值：1234
   * </pre>
   */
  @SerializedName("brand_id")
  private Integer brandId;

  /**
   * <pre>
   * 字段名：品牌AppID
   * 变量名：brand_appid
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  body品牌商户在微信申请公众号/小程序或移动应用成功后分配的账号ID，需与品牌有绑定关系，使用品牌的AppID时需要填写
   * 示例值：wxf636efh567hg4356
   * </pre>
   */
  @SerializedName("brand_appid")
  private String brandAppid;

  /**
   * <pre>
   * 字段名：品牌红包发放场景
   * 变量名：scene
   * 是否必填：是
   * 类型：string
   * 描述：
   *  body品牌红包发放场景，用户可以在指定的场景领取到红包
   * CUSTOM_SEND - 自定义发放场景，自定义场景发放红包，使用已配置的自定义发放模板进行发红包
   * 示例值：CUSTOM_SEND
   * </pre>
   */
  @SerializedName("scene")
  private String scene;

  /**
   * <pre>
   * 字段名：品牌红包模板ID
   * 变量名：template_id
   * 是否必填：是
   * 类型：string[1, 128]
   * 描述：
   *  body品牌主配置的品牌红包模板ID
   * 示例值：123400001
   * </pre>
   */
  @SerializedName("template_id")
  private String templateId;

  /**
   * <pre>
   * 字段名：商家品牌红包批次单号
   * 变量名：out_batch_no
   * 是否必填：是
   * 类型：string[5, 32]
   * 描述：
   *  body品牌商户系统内部的品牌红包批次单号，要求此参数只能由数字、大小写字母组成，在商户系统内部唯一
   * 示例值：plfk2020042013
   * </pre>
   */
  @SerializedName("out_batch_no")
  private String outBatchNo;

  /**
   * <pre>
   * 字段名：品牌红包批次名称
   * 变量名：batch_name
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  body该品牌红包批次的名称，展示在用户红包领取通知的红包活动名称
   * 示例值：双十一营销用品牌红包
   * </pre>
   */
  @SerializedName("batch_name")
  private String batchName;

  /**
   * <pre>
   * 字段名：品牌红包批次备注
   * 变量名：batch_remark
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  body该批次品牌红包的备注，仅用于品牌商户内部管理
   * 示例值：双十一营销用品牌红包
   * </pre>
   */
  @SerializedName("batch_remark")
  private String batchRemark;

  /**
   * <pre>
   * 字段名：总金额
   * 变量名：total_amount
   * 是否必填：是
   * 类型：int
   * 描述：
   *  body品牌红包总金额必须与品牌红包批次内所有品牌红包明细发送金额之和保持一致，否则无法发送品牌红包
   * 示例值：10000
   * </pre>
   */
  @SerializedName("total_amount")
  private Integer totalAmount;

  /**
   * <pre>
   * 字段名：总笔数
   * 变量名：total_num
   * 是否必填：是
   * 类型：int
   * 描述：
   *  body一个品牌红包批次单最多发送10笔品牌红包明细。品牌红包总笔数必须与批次内所有品牌红包明细之和保持一致，否则无法发送品牌红包
   * 示例值：10
   * </pre>
   */
  @SerializedName("total_num")
  private Integer totalNum;

  /**
   * <pre>
   * 字段名：品牌红包明细列表
   * 变量名：detail_list
   * 是否必填：否
   * 类型：array
   * 描述：
   *  body品牌红包明细列表，最多10笔
   * </pre>
   */
  @SpecEncrypt
  @SerializedName("detail_list")
  private List<BrandTransferDetail> detailList;

  @Data
  @Builder(builderMethodName = "newBuilder")
  @AllArgsConstructor
  @NoArgsConstructor
  public static class BrandTransferDetail {

    /**
     * <pre>
     * 字段名：商家品牌红包明细单号
     * 变量名：out_detail_no
     * 是否必填：是
     * 类型：string[1, 32]
     * 描述：
     *  品牌商户系统内部区分品牌红包批次单下不同品牌红包明细单的唯一标识，要求此参数只能由数字、大小写字母组成
     * 示例值：x23zy545Bd5436
     * </pre>
     */
    @SerializedName("out_detail_no")
    private String outDetailNo;

    /**
     * <pre>
     * 字段名：红包金额（单位：分）
     * 变量名：amount
     * 是否必填：是
     * 类型：int
     * 描述：
     *  红包金额单位为“分”，红包金额的最大限额取决于商户在商户平台的设置额度
     * 示例值：100
     * </pre>
     */
    @SerializedName("amount")
    private Integer amount;

    /**
     * <pre>
     * 字段名：接收红包用户OpenID
     * 变量名：openid
     * 是否必填：是
     * 类型：string[1, 64]
     * 描述：
     *  接收红包的用户OpenID，该OpenID为用户在上方指定的AppID下的唯一标识。
     *  注：openid是微信用户在公众号appid下的唯一用户标识（appid不同，则获取到的openid就不同），可用于永久标记一个用户。
     *  获取openid：https://pay.weixin.qq.com/wiki/doc/apiv3/terms_definition/chapter1_1_3.shtml
     * 示例值：o-MYE42l80oelYMDE34nYD456Xoy
     * </pre>
     */
    @SerializedName("openid")
    private String openid;

    /**
     * <pre>
     * 字段名：接收红包用户姓名
     * 变量名：user_name
     * 是否必填：否
     * 类型：string[1, 1024]
     * 描述：
     *  1、明细转账金额 >= 2,000，收款用户姓名必填；
     *  2、同一批次转账明细中，收款用户姓名字段需全部填写、或全部不填写；
     *  3、若传入收款用户姓名，微信支付会校验用户OpenID与姓名是否一致，并提供电子回单；
     *  4、收款方姓名。采用标准RSA算法，公钥由微信侧提供
     *  5、该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
     * 示例值：757b340b45ebef5467rter35gf464344v3542sdf4t6re4tb4f54ty45t4yyry45
     * </pre>
     */
    @SpecEncrypt
    @SerializedName("user_name")
    private String userName;

    /**
     * <pre>
     * 字段名：红包备注
     * 变量名：remark
     * 是否必填：是
     * 类型：string[1, 32]
     * 描述：
     *  单个红包备注，会展示在客户端收款凭证的“红包说明”字段，UTF8编码，最多允许32个字符
     * 示例值：来自XX的红包
     * </pre>
     */
    @SerializedName("remark")
    private String remark;
  }
}
