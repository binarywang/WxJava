package me.chanjar.weixin.channel.api;

import java.util.Map;
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
import me.chanjar.weixin.channel.bean.message.store.CloseStoreMessage;
import me.chanjar.weixin.channel.bean.message.store.NicknameUpdateMessage;
import me.chanjar.weixin.channel.bean.message.supplier.SupplierItemMessage;
import me.chanjar.weixin.channel.bean.message.vip.ExchangeInfoMessage;
import me.chanjar.weixin.channel.bean.message.vip.UserInfoMessage;
import me.chanjar.weixin.channel.bean.message.voucher.VoucherMessage;
import me.chanjar.weixin.channel.message.WxChannelMessage;
import me.chanjar.weixin.channel.message.WxChannelMessageRouterRule;
import me.chanjar.weixin.common.session.WxSessionManager;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface BaseWxChannelMessageService {

  /**
   * 路由微信消息
   *
   * @param message 消息
   * @param content 消息原始内容
   * @param appId   appId
   * @param service 服务实例
   * @return Object
   */
  Object route(final WxChannelMessage message, final String content, final String appId,
    final WxChannelService service);

  /**
   * 添加一条规则进入路由器
   *
   * @param rule 规则
   */
  void addRule(WxChannelMessageRouterRule<? extends WxChannelMessage> rule);

  /**
   * 订单下单
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void orderNew(final OrderIdMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 订单取消
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void orderCancel(OrderCancelMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 订单支付成功
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void orderPay(OrderPayMessage message, final String content, final String appId, final Map<String, Object> context,
    final WxSessionManager sessionManager);

  /**
   * 订单待发货
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void orderWaitShipping(OrderIdMessage message, final String content, final String appId, final Map<String, Object> context,
    final WxSessionManager sessionManager);

  /**
   * 订单发货
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void orderDelivery(OrderDeliveryMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 订单确认收货
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void orderConfirm(OrderConfirmMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 订单结算成功
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void orderSettle(OrderSettleMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 订单其他信息更新
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void orderExtInfoUpdate(OrderExtMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 订单状态更新
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void orderStatusUpdate(OrderStatusMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 商品审核结果
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void spuAudit(SpuAuditMessage message, final String content, final String appId, final Map<String, Object> context,
    final WxSessionManager sessionManager);

  /**
   * 商品系统下架通知
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void spuStatusUpdate(SpuAuditMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 商品更新通知
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void spuUpdate(SpuAuditMessage message, final String content, final String appId, final Map<String, Object> context,
    final WxSessionManager sessionManager);

  /**
   * 商品库存不足通知
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void stockNoEnough(SpuStockMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 类目审核结果
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void categoryAudit(CategoryAuditMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 品牌更新
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void brandUpdate(BrandMessage message, final String content, final String appId, final Map<String, Object> context,
    final WxSessionManager sessionManager);

  /**
   * 售后单状态更新
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void afterSaleStatusUpdate(AfterSaleMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 纠纷回调
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void complaintNotify(ComplaintMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 用户领券通知
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void couponReceive(CouponReceiveMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 创建优惠券通知
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void couponCreate(CouponActionMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 优惠券删除通知
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void couponDelete(CouponActionMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 优惠券过期通知
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void couponExpire(CouponActionMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 更新优惠券信息通知
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void couponUpdate(CouponActionMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 优惠券作废通知
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void couponInvalid(CouponActionMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 用户优惠券过期通知
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void userCouponExpire(UserCouponExpireMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 用户优惠券使用通知
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void userCouponUse(UserCouponExpireMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 用户优惠券返还通知
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void userCouponUnuse(UserCouponExpireMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 发放团购优惠成功回调
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void voucherSendSucc(VoucherMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);
  /**
   * 结算账户变更回调
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void accountNotify(AccountNotifyMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 提现回调
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void withdrawNotify(WithdrawNotifyMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 提现二维码回调
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void qrNotify(QrNotifyMessage message, final String content, final String appId, final Map<String, Object> context,
    final WxSessionManager sessionManager);

  /**
   * 团长商品变更
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void supplierItemUpdate(SupplierItemMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);


  /**
   * 用户加入会员.
   *
   * @param message        the message
   * @param content        the content
   * @param appId          the app id
   * @param context        the context
   * @param sessionManager the session manager
   */
  public void vipJoin(UserInfoMessage message, final String content, final String appId,
                      final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 用户注销会员.
   *
   * @param message        the message
   * @param content        the content
   * @param appId          the app id
   * @param context        the context
   * @param sessionManager the session manager
   */
  void vipClose(UserInfoMessage message, final String content, final String appId,
                final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 用户等级更新.
   *
   * @param message        the message
   * @param content        the content
   * @param appId          the app id
   * @param context        the context
   * @param sessionManager the session manager
   */
  void vipGradeUpdate(UserInfoMessage message, final String content, final String appId,
                      final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 用户积分更新.
   *
   * @param message        the message
   * @param content        the content
   * @param appId          the app id
   * @param context        the context
   * @param sessionManager the session manager
   */
  void vipScoreUpdate(UserInfoMessage message, final String content, final String appId,
                      final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 用户积分兑换
   *
   * @param message        the message
   * @param content        the content
   * @param appId          the app id
   * @param context        the context
   * @param sessionManager the session manager
   */
  void vipScoreExchange(ExchangeInfoMessage message, final String content, final String appId,
                        final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 小店注销
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void closeStore(CloseStoreMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);


  /**
   * 小店修改名称
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   */
  void updateNickname(NicknameUpdateMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);

  /**
   * 默认消息处理
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   * @return Object
   */
  Object defaultMessageHandler(WxChannelMessage message, final String content, final String appId,
    final Map<String, Object> context, final WxSessionManager sessionManager);


  /**
   * 分享员变更
   *
   * @param message        the message
   * @param content        the content
   * @param appId          the app id
   * @param context        the context
   * @param sessionManager the session manager
   */
  void sharerChange(WxChannelMessage message, final String content, final String appId,
                    final Map<String, Object> context, final WxSessionManager sessionManager);
}
