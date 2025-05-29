package me.chanjar.weixin.channel.enums;

/**
 * 资金类型
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public enum FundsType {

    /** 1 订单支付收入 */
    ORDER_PAY_INCOME(1, "订单支付收入"),
    /** 2 订单手续费 */
    ORDER_FEE(2, "订单手续费"),
    /** 3 退款 */
    REFUND(3, "退款"),
    /** 4 提现 */
    WITHDRAW(4, "提现"),
    /** 5 提现失败退票 */
    WITHDRAW_FAIL(5, "提现失败退票"),
    /** 6 导购分账 */
    GUIDE_SHARE(6, "导购分账"),
    /** 7 联盟分账 */
    LEAGUE_SHARE(7, "联盟分账"),
    /** 8 运费险分账 */
    FREIGHT_SHARE(8, "运费险分账"),
    /** 9 联盟平台抽佣 */
    LEAGUE_PLAT_COMMISSION(9, "联盟平台抽佣"),
    /** 10 联盟抽佣 */
    LEAGUE_COMMISSION(10, "联盟抽佣"),
    /** 11台抽佣 */
    PLATFORM_COMMISSION(11, "平台抽佣"),
    /** 12 团长抽佣 */
    LEADER_COMMISSION(12, "团长抽佣"),
    /** 13 返佣人气卡 */
    POPULARITY_CARD(13, "返佣人气卡"),
    /** 14 极速退款垫资金 */
    FAST_REFUND(14, "极速退款垫资金"),
    /** 15 极速退款垫资回补 */
    FAST_REFUND_REPLENISHMENT(15, "极速退款垫资回补"),
    /** 16 运费险 */
    FREIGHT_INSURANCE(16, "运费险"),
    /** 99 分账 */
    SHARE(99, "分账"),
    ;

    private final int key;
    private final String value;

    FundsType(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
