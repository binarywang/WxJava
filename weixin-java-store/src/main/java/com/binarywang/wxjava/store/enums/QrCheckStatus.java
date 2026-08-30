package com.binarywang.wxjava.store.enums;

/**
 * 二维码核销状态
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public enum QrCheckStatus {
    /** 0 未扫码 */
    NOT_SCAN(0, "未扫码"),
    /** 1 已确认 */
    CONFIRMED(1, "已确认"),
    /** 2 已取消 */
    CANCEL(2, "已取消"),
    /** 3 已失效 */
    INVALID(3, "已失效"),
    /** 4 已扫码 */
    SCAN(4, "已扫码"),

    ;

    private final int key;
    private final String value;

    QrCheckStatus(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static QrCheckStatus getByKey(Integer key) {
        for (QrCheckStatus status : QrCheckStatus.values()) {
            if (status.getKey() == key) {
                return status;
            }
        }
        return NOT_SCAN;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
