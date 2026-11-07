package com.binarywang.wxjava.store.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 佣金订单状态
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CommissionOrderStatus {

    /** 20 未结算 */
    NOT_SETTLED(20, "未结算"),
    /** 100 已结算 */
    SETTLED(100, "已结算"),
    /** 200 取消结算 */
    CANCEL_SETTLED(200, "取消结算"),

    ;

    private final int key;
    private final String val;

    CommissionOrderStatus(int key, String val) {
        this.key = key;
        this.val = val;
    }

    public int getKey() {
        return key;
    }

    public String getVal() {
        return val;
    }
}
