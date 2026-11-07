package com.binarywang.wxjava.store.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 微信小店 提现状态
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum WithdrawStatus {
    /** 受理成功 */
    CREATE_SUCCESS("CREATE_SUCCESS", "受理成功"),
    /** 提现成功 */
    SUCCESS("SUCCESS", "提现成功"),
    /** 提现失败 */
    FAIL("FAIL", "提现失败"),
    /** 提现退票 */
    REFUND("REFUND", "提现退票"),
    /** 关单 */
    CLOSE("CLOSE", "关单"),
    /** 业务单已创建 */
    INIT("INIT", "业务单已创建"),
    ;

    private final String key;
    private final String value;

    WithdrawStatus(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static WithdrawStatus getByKey(String key) {
        for (WithdrawStatus reason : WithdrawStatus.values()) {
            if (reason.getKey().equals(key)) {
                return reason;
            }
        }
        // 找不到就返回其他了
        return FAIL;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
