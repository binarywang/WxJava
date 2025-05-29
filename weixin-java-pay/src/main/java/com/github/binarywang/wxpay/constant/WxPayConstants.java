package com.github.binarywang.wxpay.constant;

import com.github.binarywang.wxpay.bean.order.WxPayAppOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayMwebOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayNativeOrderResult;
import com.github.binarywang.wxpay.bean.result.WxPayMicropayResult;
import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.Format;
import java.util.List;

/**
 * <pre>
 * 微信支付常量类
 * Created by Binary Wang on 2017-8-24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxPayConstants {

  /**
   * 拉取订单评价数据接口的参数中日期格式.
   */
  public static final Format QUERY_COMMENT_DATE_FORMAT = FastDateFormat.getInstance("yyyyMMddHHmmss");

  /**
   * 币种类型.
   */
  public static class CurrencyType {
    /**
     * 人民币.
     */
    public static final String CNY = "CNY";

  }

  /**
   * 校验用户姓名选项，企业付款时使用.
   */
  public static class CheckNameOption {
    /**
     * 不校验真实姓名.
     */
    public static final String NO_CHECK = "NO_CHECK";

    /**
     * 强校验真实姓名.
     */
    public static final String FORCE_CHECK = "FORCE_CHECK";
  }

  /**
   * 压缩账单的类型.
   */
  public static class TarType {
    /**
     * 固定值：GZIP，返回格式为.gzip的压缩包账单.
     */
    public static final String GZIP = "GZIP";
  }

  /**
   * 账单类型.
   */
  public static class BillType {
    /**
     * 查询红包时使用：通过商户订单号获取红包信息.
     */
    public static final String MCHT = "MCHT";

    //以下为下载对账单时的账单类型
    /**
     * 返回当日所有订单信息，默认值.
     */
    public static final String ALL = "ALL";
    /**
     * 返回当日成功支付的订单.
     */
    public static final String SUCCESS = "SUCCESS";
    /**
     * 返回当日退款订单.
     */
    public static final String REFUND = "REFUND";
    /**
     * 返回当日充值退款订单（相比其他对账单多一栏“返还手续费”）.
     */
    public static final String RECHARGE_REFUND = "RECHARGE_REFUND";
  }

  /**
   * 交易类型.
   */
  public static class TradeType {
    /**
     * 原生扫码支付.
     */
    public static final String NATIVE = "NATIVE";

    /**
     * App支付.
     */
    public static final String APP = "APP";

    /**
     * 公众号支付/小程序支付.
     */
    public static final String JSAPI = "JSAPI";

    /**
     * H5支付.
     */
    public static final String MWEB = "MWEB";

    /**
     * 刷卡支付.
     * 刷卡支付有单独的支付接口，不调用统一下单接口
     */
    public static final String MICROPAY = "MICROPAY";

    @SuppressWarnings("unused")
    public abstract static class Specific<R> {

      public abstract String getType();

      private Specific() {
      }

      public static Specific<WxPayNativeOrderResult> NATIVE =
        new Specific<WxPayNativeOrderResult>() {
          @Override
          public String getType() {
            return TradeType.NATIVE;
          }
        };

      public static Specific<WxPayAppOrderResult> APP =
        new Specific<WxPayAppOrderResult>() {
          @Override
          public String getType() {
            return TradeType.APP;
          }
        };

      public static Specific<WxPayMpOrderResult> JSAPI =
        new Specific<WxPayMpOrderResult>() {
          @Override
          public String getType() {
            return TradeType.JSAPI;
          }
        };

      public static Specific<WxPayMwebOrderResult> MWEB =
        new Specific<WxPayMwebOrderResult>() {
          @Override
          public String getType() {
            return TradeType.MWEB;
          }
        };

      public static Specific<WxPayMicropayResult> MICROPAY =
        new Specific<WxPayMicropayResult>() {
          @Override
          public String getType() {
            return TradeType.MICROPAY;
          }
        };
    }
  }

  /**
   * 签名类型.
   */
  public static class SignType {
    /**
     * The constant HMAC_SHA256.
     */
    public static final String HMAC_SHA256 = "HMAC-SHA256";
    /**
     * The constant MD5.
     */
    public static final String MD5 = "MD5";
    /**
     * The constant ALL_SIGN_TYPES.
     */
    public static final List<String> ALL_SIGN_TYPES = Lists.newArrayList(HMAC_SHA256, MD5);
  }

  /**
   * 限定支付方式.
   */
  public static class LimitPay {
    /**
     * no_credit--指定不能使用信用卡支付.
     */
    public static final String NO_CREDIT = "no_credit";
  }

  /**
   * 业务结果代码.
   */
  public static class ResultCode {
    /**
     * 成功.
     */
    public static final String SUCCESS = "SUCCESS";

    /**
     * 失败.
     */
    public static final String FAIL = "FAIL";
  }

  /**
   * 退款资金来源.
   */
  public static class RefundAccountSource {
    /**
     * 可用余额退款/基本账户.
     */
    public static final String RECHARGE_FUNDS = "REFUND_SOURCE_RECHARGE_FUNDS";

    /**
     * 未结算资金退款.
     */
    public static final String UNSETTLED_FUNDS = "REFUND_SOURCE_UNSETTLED_FUNDS";

  }

  /**
   * 退款渠道.
   */
  public static class RefundChannel {
    /**
     * 原路退款.
     */
    public static final String ORIGINAL = "ORIGINAL";

    /**
     * 退回到余额.
     */
    public static final String BALANCE = "BALANCE";

    /**
     * 原账户异常退到其他余额账户.
     */
    public static final String OTHER_BALANCE = "OTHER_BALANCE";

    /**
     * 原银行卡异常退到其他银行卡.
     */
    public static final String OTHER_BANKCARD = "OTHER_BANKCARD";
  }

  /**
   * 交易状态.
   */
  public static class WxpayTradeStatus {
    /**
     * 支付成功.
     */
    public static final String SUCCESS = "SUCCESS";

    /**
     * 支付失败(其他原因，如银行返回失败).
     */
    public static final String PAY_ERROR = "PAYERROR";

    /**
     * 用户支付中.
     */
    public static final String USER_PAYING = "USERPAYING";

    /**
     * 已关闭.
     */
    public static final String CLOSED = "CLOSED";

    /**
     * 未支付.
     */
    public static final String NOTPAY = "NOTPAY";

    /**
     * 转入退款.
     */
    public static final String REFUND = "REFUND";

    /**
     * 已撤销（刷卡支付）.
     */
    public static final String REVOKED = "REVOKED";
  }

  /**
   * 退款状态.
   */
  public static class RefundStatus {
    /**
     * 退款成功.
     */
    public static final String SUCCESS = "SUCCESS";

    /**
     * v2
     * 退款关闭.
     */
    public static final String REFUND_CLOSE = "REFUNDCLOSE";

    /**
     * 退款处理中.
     */
    public static final String PROCESSING = "PROCESSING";

    /**
     * v2
     * 退款异常.
     * 退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台（pay.weixin.qq.com）-交易中心，手动处理此笔退款。
     */
    public static final String CHANGE = "CHANGE";

    /**
     * v3
     * 退款关闭
     */
    public static final String CLOSED = "CLOSED";

    /**
     * v3
     * 退款异常
     */
    public static final String ABNORMAL = "ABNORMAL";
  }

  public static class ReceiverType {
    /**
     * 商户id
     */
    public static final String MERCHANT_ID = "MERCHANT_ID";
    /**
     * 个人微信号
     */
    public static final String PERSONAL_WECHATID = "PERSONAL_WECHATID";
    /**
     * 个人openid
     */
    public static final String PERSONAL_OPENID = "PERSONAL_OPENID";
    /**
     * 个人sub_openid
     */
    public static final String PERSONAL_SUB_OPENID = "PERSONAL_SUB_OPENID";
  }

  /**
   * 微信商户转账订单状态
   */
  @UtilityClass
  public static class TransformBillState {
    /**
     * 转账已受理
     */
    public static final String ACCEPTED = "ACCEPTED";

    /**
     * 转账处理中，转账结果尚未明确，如一直处于此状态，建议检查账户余额是否足够
     */
    public static final String PROCESSING = "PROCESSING";

    /**
     * 待收款用户确认，可拉起微信收款确认页面进行收款确认
     */
    public static final String WAIT_USER_CONFIRM = "WAIT_USER_CONFIRM";

    /**
     * 转账结果尚未明确，可拉起微信收款确认页面再次重试确认收款
     */
    public static final String TRANSFERING = "TRANSFERING";

    /**
     * 转账成功
     */
    public static final String SUCCESS = "SUCCESS";

    /**
     * 转账失败
     */
    public static final String FAIL = "FAIL";

    /**
     * 商户撤销请求受理成功，该笔转账正在撤销中
     */
    public static final String CANCELING = "CANCELING";

    /**
     * 转账撤销完成
     */
    public static final String CANCELLED = "CANCELLED";

  }

  /**
   * 【转账场景ID】 该笔转账使用的转账场景，可前往“商户平台-产品中心-商家转账”中申请。
   */
  @UtilityClass
  public static class TransformSceneId {
    /**
     * 现金营销
     */
    public static final String CASH_MARKETING = "1001";
  }

  /**
   * 用户收款感知
   *
   * @see <a href="https://pay.weixin.qq.com/doc/v3/merchant/4012711988#3.3-%E5%8F%91%E8%B5%B7%E8%BD%AC%E8%B4%A6">官方文档</a>
   */
  @UtilityClass
  public static class UserRecvPerception {
    /**
     * 转账场景 现金营销
     * 场景介绍 向参与营销活动的用户发放现金奖励
     */
    public static class CASH_MARKETING {
      /**
       * 默认展示
       */
      public static final String ACTIVITY = "活动奖励";

      /**
       * 需在发起转账时，“用户收款感知”字段主动传入“现金奖励”才可展示
       */
      public static final String CASH = "现金奖励";
    }

  }
}
