package com.github.binarywang.wxpay.constant;

/**
 * <pre>
 * 微信支付错误码
 * Created by Binary Wang on 2018/11/18.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxPayErrorCode {
  /**
   * 统一下单接口的错误码.
   * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1
   */
  public static class UnifiedOrder {
    /**
     * <pre>
     * 描述：商户无此接口权限.
     * 原因：商户未开通此接口权限
     * 解决方案：请商户前往申请此接口权限
     * </pre>
     */
    public static final String NOAUTH = "NOAUTH";
    /**
     * <pre>
     * 描述：余额不足.
     * 原因：用户帐号余额不足
     * 解决方案：用户帐号余额不足，请用户充值或更换支付卡后再支付
     * </pre>
     */
    public static final String NOTENOUGH = "NOTENOUGH";
    /**
     * <pre>
     * 描述：商户订单已支付.
     * 原因：商户订单已支付，无需重复操作
     * 解决方案：商户订单已支付，无需更多操作
     * </pre>
     */
    public static final String ORDERPAID = "ORDERPAID";
    /**
     * <pre>
     * 描述：订单已关闭.
     * 原因：当前订单已关闭，无法支付
     * 解决方案：当前订单已关闭，请重新下单
     * </pre>
     */
    public static final String ORDERCLOSED = "ORDERCLOSED";
    /**
     * <pre>
     * 描述：系统错误.
     * 原因：系统超时
     * 解决方案：系统异常，请用相同参数重新调用
     * </pre>
     */
    public static final String SYSTEMERROR = "SYSTEMERROR";
    /**
     * <pre>
     * 描述：APPID不存在.
     * 原因：参数中缺少APPID
     * 解决方案：请检查APPID是否正确
     * </pre>
     */
    public static final String APPID_NOT_EXIST = "APPID_NOT_EXIST";
    /**
     * <pre>
     * 描述：MCHID不存在.
     * 原因：参数中缺少MCHID
     * 解决方案：请检查MCHID是否正确
     * </pre>
     */
    public static final String MCHID_NOT_EXIST = "MCHID_NOT_EXIST";
    /**
     * <pre>
     * 描述：appid和mch_id不匹配.
     * 原因：appid和mch_id不匹配
     * 解决方案：请确认appid和mch_id是否匹配
     * </pre>
     */
    public static final String APPID_MCHID_NOT_MATCH = "APPID_MCHID_NOT_MATCH";
    /**
     * <pre>
     * 描述：缺少参数.
     * 原因：缺少必要的请求参数
     * 解决方案：请检查参数是否齐全
     * </pre>
     */
    public static final String LACK_PARAMS = "LACK_PARAMS";
    /**
     * <pre>
     * 描述：商户订单号重复.
     * 原因：同一笔交易不能多次提交
     * 解决方案：请核实商户订单号是否重复提交
     * </pre>
     */
    public static final String OUT_TRADE_NO_USED = "OUT_TRADE_NO_USED";
    /**
     * <pre>
     * 描述：签名错误.
     * 原因：参数签名结果不正确
     * 解决方案：请检查签名参数和方法是否都符合签名算法要求
     * </pre>
     */
    public static final String SIGNERROR = "SIGNERROR";
    /**
     * <pre>
     * 描述：XML格式错误.
     * 原因：XML格式错误
     * 解决方案：请检查XML参数格式是否正确
     * </pre>
     */
    public static final String XML_FORMAT_ERROR = "XML_FORMAT_ERROR";
    /**
     * <pre>
     * 描述：请使用post方法.
     * 原因：未使用post传递参数
     * 解决方案：请检查请求参数是否通过post方法提交
     * </pre>
     */
    public static final String REQUIRE_POST_METHOD = "REQUIRE_POST_METHOD";
    /**
     * <pre>
     * 描述：post数据为空.
     * 原因：post数据不能为空
     * 解决方案：请检查post数据是否为空
     * </pre>
     */
    public static final String POST_DATA_EMPTY = "POST_DATA_EMPTY";
    /**
     * <pre>
     * 描述：编码格式错误.
     * 原因：未使用指定编码格式
     * 解决方案：请使用UTF-8编码格式
     * </pre>
     */
    public static final String NOT_UTF8 = "NOT_UTF8";
  }

  /**
   * 关闭订单.
   * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_3&index=5
   */
  public static class OrderClose {
    /**
     * 订单已支付.
     */
    public static final String ORDER_PAID = "ORDERPAID";

    /**
     * 系统错误.
     */
    public static final String SYSTEM_ERROR = "SYSTEMERROR";

    /**
     * 订单不存在.
     */
    public static final String ORDER_NOT_EXIST = "ORDERNOTEXIST";

    /**
     * 订单已关闭.
     */
    public static final String ORDER_CLOSED = "ORDERCLOSED";

    /**
     * 签名错误.
     */
    public static final String SIGN_ERROR = "SIGNERROR";

    /**
     * 未使用POST传递参数.
     */
    public static final String REQUIRE_POST_METHOD = "REQUIRE_POST_METHOD";

    /**
     * XML格式错误.
     */
    public static final String XML_FORMAT_ERROR = "XML_FORMAT_ERROR";

    /**
     * 订单状态错误.
     */
    public static final String TRADE_STATE_ERROR = "TRADE_STATE_ERROR";
  }

  /**
   * 退款申请.
   * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_4&index=6
   */
  public static class Refund {
    /**
     * <pre>
     * 描述：接口返回错误.
     * 原因：系统超时等
     * 解决方案：请不要更换商户退款单号，请使用相同参数再次调用API。
     * </pre>
     */
    public static final String SYSTEMERROR = "SYSTEMERROR";
    /**
     * <pre>
     * 描述：退款业务流程错误，需要商户触发重试来解决.
     * 原因：并发情况下，业务被拒绝，商户重试即可解决
     * 解决方案：请不要更换商户退款单号，请使用相同参数再次调用API。
     * </pre>
     */
    public static final String BIZERR_NEED_RETRY = "BIZERR_NEED_RETRY";
    /**
     * <pre>
     * 描述：订单已经超过退款期限.
     * 原因：订单已经超过可退款的最大期限(支付后一年内可退款)
     * 解决方案：请选择其他方式自行退款
     * </pre>
     */
    public static final String TRADE_OVERDUE = "TRADE_OVERDUE";
    /**
     * <pre>
     * 描述：业务错误.
     * 原因：申请退款业务发生错误
     * 解决方案：该错误都会返回具体的错误原因，请根据实际返回做相应处理。
     * </pre>
     */
    public static final String ERROR = "ERROR";
    /**
     * <pre>
     * 描述：退款请求失败.
     * 原因：用户帐号注销
     * 解决方案：此状态代表退款申请失败，商户可自行处理退款。
     * </pre>
     */
    public static final String USER_ACCOUNT_ABNORMAL = "USER_ACCOUNT_ABNORMAL";
    /**
     * <pre>
     * 描述：无效请求过多.
     * 原因：连续错误请求数过多被系统短暂屏蔽
     * 解决方案：请检查业务是否正常，确认业务正常后请在1分钟后再来重试
     * </pre>
     */
    public static final String INVALID_REQ_TOO_MUCH = "INVALID_REQ_TOO_MUCH";
    /**
     * <pre>
     * 描述：余额不足.
     * 原因：商户可用退款余额不足
     * 解决方案：此状态代表退款申请失败，商户可根据具体的错误提示做相应的处理。
     * </pre>
     */
    public static final String NOTENOUGH = "NOTENOUGH";
    /**
     * <pre>
     * 描述：无效transaction_id.
     * 原因：请求参数未按指引进行填写
     * 解决方案：请求参数错误，检查原交易号是否存在或发起支付交易接口返回失败
     * </pre>
     */
    public static final String INVALID_TRANSACTIONID = "INVALID_TRANSACTIONID";
    /**
     * <pre>
     * 描述：参数错误.
     * 原因：请求参数未按指引进行填写
     * 解决方案：请求参数错误，请重新检查再调用退款申请
     * </pre>
     */
    public static final String PARAM_ERROR = "PARAM_ERROR";
    /**
     * <pre>
     * 描述：APPID不存在.
     * 原因：参数中缺少APPID
     * 解决方案：请检查APPID是否正确
     * </pre>
     */
    public static final String APPID_NOT_EXIST = "APPID_NOT_EXIST";
    /**
     * <pre>
     * 描述：MCHID不存在.
     * 原因：参数中缺少MCHID
     * 解决方案：请检查MCHID是否正确
     * </pre>
     */
    public static final String MCHID_NOT_EXIST = "MCHID_NOT_EXIST";
    /**
     * <pre>
     * 描述：订单号不存在.
     * 原因：缺少有效的订单号
     * 解决方案：请检查你的订单号是否正确且是否已支付，未支付的订单不能发起退款
     * </pre>
     */
    public static final String ORDERNOTEXIST = "ORDERNOTEXIST";
    /**
     * <pre>
     * 描述：请使用post方法.
     * 原因：未使用post传递参数
     * 解决方案：请检查请求参数是否通过post方法提交
     * </pre>
     */
    public static final String REQUIRE_POST_METHOD = "REQUIRE_POST_METHOD";
    /**
     * <pre>
     * 描述：签名错误.
     * 原因：参数签名结果不正确
     * 解决方案：请检查签名参数和方法是否都符合签名算法要求
     * </pre>
     */
    public static final String SIGNERROR = "SIGNERROR";
    /**
     * <pre>
     * 描述：XML格式错误.
     * 原因：XML格式错误
     * 解决方案：请检查XML参数格式是否正确
     * </pre>
     */
    public static final String XML_FORMAT_ERROR = "XML_FORMAT_ERROR";
    /**
     * <pre>
     * 描述：频率限制.
     * 原因：2个月之前的订单申请退款有频率限制
     * 解决方案：该笔退款未受理，请降低频率后重试
     * </pre>
     */
    public static final String FREQUENCY_LIMITED = "FREQUENCY_LIMITED";
  }

  /**
   * 退款查询.
   * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_4&index=7
   */
  public static class RefundQuery {
    /**
     * <pre>
     * 描述：接口返回错误.
     * 原因：系统超时
     * 解决方案：请尝试再次掉调用API。
     * </pre>
     */
    public static final String SYSTEMERROR = "SYSTEMERROR";

    /**
     * <pre>
     * 描述：退款订单查询失败.
     * 原因：订单号错误或订单状态不正确
     * 解决方案：请检查订单号是否有误以及订单状态是否正确，如：未支付、已支付未退款
     * </pre>
     */
    public static final String REFUNDNOTEXIST = "REFUNDNOTEXIST";

    /**
     * <pre>
     * 描述：无效transaction_id.
     * 原因：请求参数未按指引进行填写
     * 解决方案：请求参数错误，检查原交易号是否存在或发起支付交易接口返回失败
     * </pre>
     */
    public static final String INVALID_TRANSACTIONID = "INVALID_TRANSACTIONID";

    /**
     * <pre>
     * 描述：参数错误.
     * 原因：请求参数未按指引进行填写
     * 解决方案：请求参数错误，请检查参数再调用退款申请
     * </pre>
     */
    public static final String PARAM_ERROR = "PARAM_ERROR";

    /**
     * <pre>
     * 描述：APPID不存在.
     * 原因：参数中缺少APPID
     * 解决方案：请检查APPID是否正确
     * </pre>
     */
    public static final String APPID_NOT_EXIST = "APPID_NOT_EXIST";

    /**
     * <pre>
     * 描述：MCHID不存在.
     * 原因：参数中缺少MCHID
     * 解决方案：请检查MCHID是否正确
     * </pre>
     */
    public static final String MCHID_NOT_EXIST = "MCHID_NOT_EXIST";

    /**
     * <pre>
     * 描述：请使用post方法.
     * 原因：未使用post传递参数
     * 解决方案：请检查请求参数是否通过post方法提交
     * </pre>
     */
    public static final String REQUIRE_POST_METHOD = "REQUIRE_POST_METHOD";

    /**
     * <pre>
     * 描述：签名错误.
     * 原因：参数签名结果不正确
     * 解决方案：请检查签名参数和方法是否都符合签名算法要求
     * </pre>
     */
    public static final String SIGNERROR = "SIGNERROR";

    /**
     * <pre>
     * 描述：XML格式错误.
     * 原因：XML格式错误
     * 解决方案：请检查XML参数格式是否正确
     * </pre>
     */
    public static final String XML_FORMAT_ERROR = "XML_FORMAT_ERROR";
  }

  /**
   * 下载对账单.
   * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_4&index=8
   */
  public static class DownloadBill {
    /**
     * <pre>
     * 描述：下载失败.
     * 原因：系统超时
     * 解决方案：请尝试再次查询。
     * </pre>
     */
    public static final String SYSTEMERROR = "SYSTEMERROR";

    /**
     * <pre>
     * 描述：参数错误.
     * 原因：请求参数未按指引进行填写
     * 解决方案：参数错误，请重新检查
     * </pre>
     */
    public static final String INVALID_BILL_TYPE = "invalid bill_type";

    /**
     * <pre>
     * 描述：参数错误.
     * 原因：请求参数未按指引进行填写
     * 解决方案：参数错误，请重新检查
     * </pre>
     */
    public static final String DATA_FORMAT_ERROR = "data format error";

    /**
     * <pre>
     * 描述：参数错误.
     * 原因：请求参数未按指引进行填写
     * 解决方案：参数错误，请重新检查
     * </pre>
     */
    public static final String MISSING_PARAMETER = "missing parameter";

    /**
     * <pre>
     * 描述：参数错误.
     * 原因：请求参数未按指引进行填写
     * 解决方案：参数错误，请重新检查
     * </pre>
     */
    public static final String SIGN_ERROR = "SIGN ERROR";

    /**
     * <pre>
     * 描述：账单不存在.
     * 原因：当前商户号没有已成交的订单，不生成对账单
     * 解决方案：请检查当前商户号在指定日期内是否有成功的交易。
     * 错误：微信官方文档这个错误的字符串显示是'NO Bill Exist'('o'是大写)，实际返回是'No Bill Exist'('o'是小写）
     * </pre>
     */
    public static final String NO_Bill_Exist = "No Bill Exist";

    /**
     * <pre>
     * 描述：账单未生成.
     * 原因：当前商户号没有已成交的订单或对账单尚未生成
     * 解决方案：请先检查当前商户号在指定日期内是否有成功的交易，如指定日期有交易则表示账单正在生成中，请在上午10点以后再下载。
     * </pre>
     */
    public static final String BILL_CREATING = "Bill Creating";

    /**
     * <pre>
     * 描述：账单压缩失败.
     * 原因：账单压缩失败，请稍后重试
     * 解决方案：账单压缩失败，请稍后重试
     * </pre>
     */
    public static final String COMPRESSG_ZIP_ERROR = "CompressGZip Error";

    /**
     * <pre>
     * 描述：账单解压失败.
     * 原因：账单解压失败，请稍后重试
     * 解决方案：账单解压失败，请稍后重试
     * </pre>
     */
    public static final String UN_COMPRESSG_ZIP_ERROR = "UnCompressGZip Error";


  }
}
