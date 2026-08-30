package com.binarywang.wxjava.store.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 微信小店 账户类型
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AccountType {
    /** 对公银行账户 */
    ACCOUNT_TYPE_BUSINESS("ACCOUNT_TYPE_BUSINESS", "对公银行账户"),
    /** 经营者个人银行卡 */
    ACCOUNT_TYPE_PRIVATE("ACCOUNT_TYPE_PRIVATE", "经营者个人银行卡"),

    ;

    private final String key;
    private final String value;

    AccountType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static AccountType getByKey(String key) {
        for (AccountType reason : AccountType.values()) {
            if (reason.getKey().equals(key)) {
                return reason;
            }
        }
        return ACCOUNT_TYPE_PRIVATE;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
