package me.chanjar.weixin.channel.api.impl;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.BaseWxChannelMessageService;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.message.after.AfterSaleMessage;
import me.chanjar.weixin.channel.bean.message.after.ComplaintMessage;
import me.chanjar.weixin.channel.bean.message.coupon.CouponActionMessage;
import me.chanjar.weixin.channel.bean.message.coupon.CouponReceiveMessage;
import me.chanjar.weixin.channel.bean.message.coupon.UserCouponExpireMessage;
import me.chanjar.weixin.channel.bean.message.fund.AccountNotifyMessage;
import me.chanjar.weixin.channel.bean.message.fund.QrNotifyMessage;
import me.chanjar.weixin.channel.bean.message.fund.WithdrawNotifyMessage;
import me.chanjar.weixin.channel.bean.message.order.OrderCancelMessage;
import me.chanjar.weixin.channel.bean.message.order.OrderConfirmMessage;
import me.chanjar.weixin.channel.bean.message.order.OrderDeliveryMessage;
import me.chanjar.weixin.channel.bean.message.order.OrderExtMessage;
import me.chanjar.weixin.channel.bean.message.order.OrderIdMessage;
import me.chanjar.weixin.channel.bean.message.order.OrderPayMessage;
import me.chanjar.weixin.channel.bean.message.order.OrderSettleMessage;
import me.chanjar.weixin.channel.bean.message.order.OrderStatusMessage;
import me.chanjar.weixin.channel.bean.message.product.BrandMessage;
import me.chanjar.weixin.channel.bean.message.product.CategoryAuditMessage;
import me.chanjar.weixin.channel.bean.message.product.SpuAuditMessage;
import me.chanjar.weixin.channel.bean.message.product.SpuStockMessage;
import me.chanjar.weixin.channel.bean.message.sharer.SharerChangeMessage;
import me.chanjar.weixin.channel.bean.message.store.CloseStoreMessage;
import me.chanjar.weixin.channel.bean.message.store.NicknameUpdateMessage;
import me.chanjar.weixin.channel.bean.message.supplier.SupplierItemMessage;
import me.chanjar.weixin.channel.bean.message.vip.ExchangeInfoMessage;
import me.chanjar.weixin.channel.bean.message.vip.UserInfoMessage;
import me.chanjar.weixin.channel.bean.message.voucher.VoucherMessage;
import me.chanjar.weixin.channel.message.WxChannelMessage;
import me.chanjar.weixin.channel.message.WxChannelMessageRouter;
import me.chanjar.weixin.channel.message.WxChannelMessageRouterRule;
import me.chanjar.weixin.channel.message.rule.HandlerConsumer;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.common.session.WxSessionManager;

import static me.chanjar.weixin.channel.constant.MessageEventConstants.*;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public abstract class BaseWxChannelMessageServiceImpl implements BaseWxChannelMessageService {

  /** 消息路由器 */
  protected WxChannelMessageRouter router;

  public BaseWxChannelMessageServiceImpl(WxChannelMessageRouter router) {
    this.router = router;
    this.addDefaultRule();
  }

  /**
   * 添加默认的回调规则
   */
  protected void addDefaultRule() {
    /* 品牌资质事件回调 */
    this.addRule(BrandMessage.class, BRAND, this::brandUpdate);
    /* 商品审核结果 */
    this.addRule(SpuAuditMessage.class, PRODUCT_SPU_AUDIT, this::spuAudit);
    /* 商品上下架 */
    this.addRule(SpuAuditMessage.class, PRODUCT_SPU_STATUS_UPDATE, this::spuStatusUpdate);
    /* 商品更新 */
    this.addRule(SpuAuditMessage.class, PRODUCT_SPU_UPDATE, this::spuUpdate);
    /* 商品库存不足 */
    this.addRule(SpuStockMessage.class, PRODUCT_STOCK_NO_ENOUGH, this::stockNoEnough);
    /* 类目审核结果 */
    this.addRule(CategoryAuditMessage.class, PRODUCT_CATEGORY_AUDIT, this::categoryAudit);
    /* 订单下单 */
    this.addRule(OrderIdMessage.class, ORDER_NEW, this::orderNew);
    /* 订单取消 */
    this.addRule(OrderCancelMessage.class, ORDER_CANCEL, this::orderCancel);
    /* 订单支付成功 */
    this.addRule(OrderPayMessage.class, ORDER_PAY, this::orderPay);
    /* 订单待发货 */
    this.addRule(OrderIdMessage.class, ORDER_WAIT_SHIPPING, this::orderWaitShipping);
    /* 订单发货 */
    this.addRule(OrderDeliveryMessage.class, ORDER_DELIVER, this::orderDelivery);
    /* 订单确认收货 */
    this.addRule(OrderConfirmMessage.class, ORDER_CONFIRM, this::orderConfirm);
    /* 订单结算成功 */
    this.addRule(OrderSettleMessage.class, ORDER_SETTLE, this::orderSettle);
    /* 订单其他信息更新 */
    this.addRule(OrderExtMessage.class, ORDER_EXT_INFO_UPDATE, this::orderExtInfoUpdate);
    /* 订单状态更新 */
    this.addRule(OrderStatusMessage.class, ORDER_STATUS_UPDATE, this::orderStatusUpdate);
    /* 售后单更新通知 */
    this.addRule(AfterSaleMessage.class, AFTER_SALE_UPDATE, this::afterSaleStatusUpdate);
    /* 纠纷更新通知 */
    this.addRule(ComplaintMessage.class, COMPLAINT_NOTIFY, this::complaintNotify);
    /* 优惠券领取通知 */
    this.addRule(CouponReceiveMessage.class, RECEIVE_COUPON, this::couponReceive);
    /* 优惠券使用通知 */
    this.addRule(CouponActionMessage.class, CREATE_COUPON, this::couponCreate);
    /* 优惠券删除通知 */
    this.addRule(CouponActionMessage.class, DELETE_COUPON, this::couponDelete);
    /* 优惠券过期通知 */
    this.addRule(CouponActionMessage.class, EXPIRE_COUPON, this::couponExpire);
    /* 更新优惠券信息通知 */
    this.addRule(CouponActionMessage.class, UPDATE_COUPON_INFO, this::couponUpdate);
    /* 更新优惠券信息通知 */
    this.addRule(CouponActionMessage.class, INVALID_COUPON, this::couponInvalid);
    /* 用户优惠券过期通知 */
    this.addRule(UserCouponExpireMessage.class, USER_COUPON_EXPIRE, this::userCouponExpire);
    /* 用户优惠券过期通知 */
    this.addRule(UserCouponExpireMessage.class, USER_COUPON_UNUSE, this::userCouponUnuse);
    /* 优惠券返还通知 */
    this.addRule(UserCouponExpireMessage.class, USER_COUPON_USE, this::userCouponUse);
    /* 发放团购优惠成功通知 */
    this.addRule(VoucherMessage.class, VOUCHER_SEND_SUCC, this::voucherSendSucc);
    /* 结算账户变更回调 */
    this.addRule(AccountNotifyMessage.class, ACCOUNT_NOTIFY, this::accountNotify);
    /* 提现回调 */
    this.addRule(WithdrawNotifyMessage.class, WITHDRAW_NOTIFY, this::withdrawNotify);
    /* 提现二维码回调 */
    this.addRule(QrNotifyMessage.class, QRCODE_STATUS, this::qrNotify);
    /* 团长 */
    this.addRule(SupplierItemMessage.class, SUPPLIER_ITEM_UPDATE, this::supplierItemUpdate);

    /* 用户加入会员 */
    this.addRule(UserInfoMessage.class, USER_VIP_JOIN, false, this::vipJoin);
    /* 用户注销会员 */
    this.addRule(UserInfoMessage.class, USER_VIP_CLOSE,false, this::vipClose);
    /* 用户等级信息更新 */
    this.addRule(UserInfoMessage.class, USER_VIP_GRADE_INFO_UPDATE, false, this::vipGradeUpdate);
    /* 用户积分更新 */
    this.addRule(UserInfoMessage.class, USER_VIP_SCORE_UPDATE, false, this::vipScoreUpdate);
    /* 用户积分兑换 */
    this.addRule(ExchangeInfoMessage.class, USER_VIP_SCORE_EXCHANGE, false, this::vipScoreExchange);

    /* 分享员变更 */
    this.addRule(SharerChangeMessage.class,SHARER_CHANGE,false,this::sharerChange);

    /* 小店注销 */
    this.addRule(CloseStoreMessage.class, CLOSE_STORE, this::closeStore);
    /* 小店修改名称 */
    this.addRule(NicknameUpdateMessage.class, SET_SHOP_NICKNAME, this::updateNickname);
  }

  /**
   * 添加一条规则进入路由器
   *
   * @param clazz 消息类型
   * @param event 事件类型
   * @param consumer 处理器
   * @param <T> 消息类型
   */
  protected <T extends WxChannelMessage> void addRule(Class<T> clazz, String event, Boolean async,
                                                      HandlerConsumer<T, String, String, Map<String, Object>, WxSessionManager> consumer) {
    WxChannelMessageRouterRule<T> rule = new WxChannelMessageRouterRule<>();
    rule.setMessageClass(clazz).setEvent(event).setAsync(async);
    rule.getHandlers().add((message, content, appId, context, sessionManager) -> {
      consumer.accept(message, content, appId, context, sessionManager);
      return "success";
    });
    rule.setNext(true);
    this.addRule(rule);
  }

  protected <T extends WxChannelMessage> void addRule(Class<T> clazz, String event,
    HandlerConsumer<T, String, String, Map<String, Object>, WxSessionManager> consumer) {
    this.addRule(clazz, event, true, consumer);
  }

  @Override
  public void addRule(WxChannelMessageRouterRule<? extends WxChannelMessage> rule) {
    router.getRules().add(rule);
  }

  @Override
  public Object route(WxChannelMessage message, String content, String appId, final WxChannelService service) {
    return router.route(message, content, appId, service);
  }


  @Override
  public void orderNew(OrderIdMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("订单下单:{}", JsonUtils.encode(message));
  }

  @Override
  public void orderCancel(OrderCancelMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("订单取消:{}", JsonUtils.encode(message));
  }

  @Override
  public void orderPay(OrderPayMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("订单支付成功:{}", JsonUtils.encode(message));
  }

  @Override
  public void orderWaitShipping(OrderIdMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("订单待发货:{}", JsonUtils.encode(message));
  }

  @Override
  public void orderDelivery(OrderDeliveryMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("订单发货:{}", JsonUtils.encode(message));
  }

  @Override
  public void orderConfirm(OrderConfirmMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("订单确认收货:{}", JsonUtils.encode(message));
  }

  @Override
  public void orderSettle(OrderSettleMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("订单结算:{}", JsonUtils.encode(message));
  }

  @Override
  public void orderExtInfoUpdate(OrderExtMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("订单其他信息更新:{}", JsonUtils.encode(message));
  }

  @Override
  public void orderStatusUpdate(OrderStatusMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("订单状态更新:{}", JsonUtils.encode(message));
  }

  @Override
  public void spuAudit(SpuAuditMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("商品审核:{}", JsonUtils.encode(message));
  }

  @Override
  public void spuStatusUpdate(SpuAuditMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("商品状态更新:{}", JsonUtils.encode(message));
  }

  @Override
  public void spuUpdate(SpuAuditMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("商品更新:{}", JsonUtils.encode(message));
  }

  @Override
  public void stockNoEnough(SpuStockMessage message, String content, String appId,
          Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("商品库存不足:{}", JsonUtils.encode(message));
  }

  @Override
  public void categoryAudit(CategoryAuditMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("分类审核:{}", JsonUtils.encode(message));
  }

  @Override
  public void brandUpdate(BrandMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("品牌更新:{}", JsonUtils.encode(message));
  }

  @Override
  public void afterSaleStatusUpdate(AfterSaleMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("售后状态更新:{}", JsonUtils.encode(message));
  }

  @Override
  public void complaintNotify(ComplaintMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("投诉通知:{}", JsonUtils.encode(message));
  }

  @Override
  public void couponReceive(CouponReceiveMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("优惠券领取:{}", JsonUtils.encode(message));
  }

  @Override
  public void couponCreate(CouponActionMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("优惠券创建:{}", JsonUtils.encode(message));
  }

  @Override
  public void couponDelete(CouponActionMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("优惠券删除:{}", JsonUtils.encode(message));
  }

  @Override
  public void couponExpire(CouponActionMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("优惠券过期:{}", JsonUtils.encode(message));
  }

  @Override
  public void couponUpdate(CouponActionMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("优惠券更新:{}", JsonUtils.encode(message));
  }

  @Override
  public void couponInvalid(CouponActionMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("优惠券失效:{}", JsonUtils.encode(message));
  }

  @Override
  public void userCouponExpire(UserCouponExpireMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("用户优惠券过期:{}", JsonUtils.encode(message));
  }

  @Override
  public void userCouponUse(UserCouponExpireMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("用户优惠券使用:{}", JsonUtils.encode(message));
  }

  @Override
  public void userCouponUnuse(UserCouponExpireMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("用户优惠券取消使用:{}", JsonUtils.encode(message));
  }

  @Override
  public void voucherSendSucc(VoucherMessage message, String content, String appId,
          Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("发放团购优惠成功:{}", JsonUtils.encode(message));
  }

  @Override
  public void accountNotify(AccountNotifyMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("账户通知:{}", JsonUtils.encode(message));
  }

  @Override
  public void withdrawNotify(WithdrawNotifyMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("提现通知:{}", JsonUtils.encode(message));
  }

  @Override
  public void qrNotify(QrNotifyMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("二维码通知:{}", JsonUtils.encode(message));
  }

  @Override
  public void supplierItemUpdate(SupplierItemMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("供应商商品更新:{}", JsonUtils.encode(message));
  }

  @Override
  public Object defaultMessageHandler(WxChannelMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("默认消息处理:{}", JsonUtils.encode(message));
    return null;
  }

  @Override
  public void sharerChange(WxChannelMessage message, String content, String appId, Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("分享员变更:{}", JsonUtils.encode(message));
  }

  @Override
  public void vipJoin(UserInfoMessage message, String content, String appId,
                               Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("用户加入会员:{}", JsonUtils.encode(message));
  }

  @Override
  public void vipClose(UserInfoMessage message, String content, String appId,
                                      Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("用户注销会员:{}", JsonUtils.encode(message));
  }

  @Override
  public void vipGradeUpdate(UserInfoMessage message, String content, String appId,
                                            Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("用户等级信息更新:{}", JsonUtils.encode(message));
  }

  @Override
  public void vipScoreUpdate(UserInfoMessage message, String content, String appId,
                                            Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("用户积分更新:{}", JsonUtils.encode(message));
  }

  @Override
  public void vipScoreExchange(ExchangeInfoMessage message, String content, String appId,
                                              Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("用户积分兑换:{}", JsonUtils.encode(message));
  }

  @Override
  public void closeStore(CloseStoreMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("小店注销:{}", JsonUtils.encode(message));
  }

  @Override
  public void updateNickname(NicknameUpdateMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("小店修改名称:{}", JsonUtils.encode(message));
  }
}
