package me.chanjar.weixin.mp.constant;

/**
 * <pre>
 * 微信公众号事件的相关常量
 * Created by Binary Wang on 2017-5-10.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMpEventConstants {
  /**
   * 门店审核事件.
   */
  public static final String POI_CHECK_NOTIFY = "poi_check_notify";

  /**
   * 接收会员信息事件.
   */
  public static final String SUBMIT_MEMBERCARD_USER_INFO = "submit_membercard_user_info";

  /**
   * 微信摇一摇周边-摇一摇事件通知.
   */
  public static final String SHAKEAROUND_USER_SHAKE = "ShakearoundUserShake";


  /**
   * 卡券相关事件.
   */
  public static class Card {
    public static final String CARD_PASS_CHECK = "card_pass_check";
    public static final String CARD_NOT_PASS_CHECK = "card_not_pass_check";
    public static final String USER_GET_CARD = "user_get_card";
    public static final String USER_DEL_CARD = "user_del_card";
    public static final String USER_CONSUME_CARD = "user_consume_card";
    public static final String USER_PAY_FROM_PAY_CELL = "user_pay_from_pay_cell";
    public static final String USER_VIEW_CARD = "user_view_card";
    public static final String USER_ENTER_SESSION_FROM_CARD = "user_enter_session_from_card";

    /**
     * 卡券转赠事件.
     */
    public static final String USER_GIFTING_CARD = "user_gifting_card";

    /**
     * 库存报警.
     */
    public static final String CARD_SKU_REMIND = "card_sku_remind";

    /**
     * 会员卡内容更新事件.
     */
    public static final String UPDATE_MEMBER_CARD = "update_member_card";

    /**
     * 券点流水详情事件.
     */
    public static final String CARD_PAY_ORDER = "card_pay_order";

    /**
     * 用户购买礼品卡付款成功事件.
     */
    public static final String GIFTCARD_PAY_DONE = "giftcard_pay_done";

    /**
     * 用户购买后赠送事件.
     */
    public static final String GIFTCARD_SEND_TO_FRIEND = "giftcard_send_to_friend";

    /**
     * 用户领取礼品卡成功事件.
     */
    public static final String GIFTCARD_USER_ACCEPT = "giftcard_user_accept";
  }


  /**
   * 客服相关事件.
   */
  public static class CustomerService {
    /**
     * 客服接入会话.
     */
    public static final String KF_CREATE_SESSION = "kf_create_session";

    /**
     * 客服关闭会话.
     */
    public static final String KF_CLOSE_SESSION = "kf_close_session";

    /**
     * 客服转接会话.
     */
    public static final String KF_SWITCH_SESSION = "kf_switch_session";
  }

  /**
   * 微信认证事件.
   */
  public static class Qualification {

    /**
     * 资质认证成功.
     */
    public static final String QUALIFICATION_VERIFY_SUCCESS = "qualification_verify_success";
    /**
     * 资质认证失败.
     */
    public static final String QUALIFICATION_VERIFY_FAIL = "qualification_verify_fail";
    /**
     * 名称认证成功.
     */
    public static final String NAMING_VERIFY_SUCCESS = "naming_verify_success";
    /**
     * 名称认证失败.
     */
    public static final String NAMING_VERIFY_FAIL = "naming_verify_fail";
    /**
     * 年审通知.
     */
    public static final String ANNUAL_RENEW = "annual_renew";
    /**
     * 认证过期失效通知.
     */
    public static final String VERIFY_EXPIRED = "verify_expired";
  }

  /**
   * 电子发票.
   */
  public static class Invoice {
    /**
     * 用户授权事件.
     */
    public static final String USER_AUTHORIZE_INVOICE = "user_authorize_invoice";

    /**
     * 统一开票接口-异步通知开票结果.
     */
    public static final String CLOUD_INVOICE_INVOICERESULT_EVENT = "cloud_invoice_invoiceresult_event";
  }

  /**
   * 对话助手相关事件
   */
  public static class Guide {
    /**
     * 顾问邀请结果通知事件.
     */
    public static final String GUIDE_INVITE_RESULT_EVENT = "guide_invite_result_event";

  }
}
