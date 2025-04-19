package me.chanjar.weixin.channel.enums;

import com.google.common.collect.Maps;
import java.util.Map;
import lombok.Getter;

/**
 * 微信视频号全局返回码
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请使用 {@link me.chanjar.weixin.common.error.WxChannelErrorMsgEnum} 替代
 */
@Deprecated
@Getter
public enum WxChannelErrorMsgEnum {
  /**
   * 系统繁忙，此时请开发者稍候再试  system error
   */
  CODE_1(-1, "系统繁忙，此时请开发者稍候再试"),

  /**
   * 请求成功  ok
   */
  CODE_0(0, "请求成功"),

  /**
   * AppSecret 错误或者 AppSecret 不属于这个小店，请开发者确认 AppSecret 的正确性
   */
  CODE_40001(40001, "AppSecret 错误或者 AppSecret 不属于这个小店，请开发者确认 AppSecret 的正确性"),

  /**
   * 请确保 grant_type 字段值为 client_credential
   */
  CODE_40002(40002, "请确保 grant_type 字段值为 client_credential"),

  /**
   * 不合法的 AppID，请开发者检查 AppID 的正确性，避免异常字符，注意大小写
   */
  CODE_40013(40013, "不合法的 AppID，请开发者检查 AppID 的正确性，避免异常字符，注意大小写"),

  ;

  private final int code;
  private final String msg;

  WxChannelErrorMsgEnum(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  static final Map<Integer, String> valueMap = Maps.newHashMap();

  static {
    for (WxChannelErrorMsgEnum value : WxChannelErrorMsgEnum.values()) {
      valueMap.put(value.code, value.msg);
    }
  }

  /**
   * 通过错误代码查找其中文含义.
   */
  public static String findMsgByCode(int code) {
    return valueMap.getOrDefault(code, null);
  }
}
