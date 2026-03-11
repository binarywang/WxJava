package me.chanjar.weixin.cp.bean.hr;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 人事助手-员工档案字段类型枚举.
 *
 * @author <a href="https://github.com/leejoker">leejoker</a> created on 2024-01-01
 */
@Getter
@AllArgsConstructor
public enum WxCpHrFieldType {
  /**
   * 文本.
   */
  TEXT("文本", 1),
  /**
   * 日期.
   */
  DATE("日期", 2),
  /**
   * 数字.
   */
  NUMBER("数字", 3),
  /**
   * 单选.
   */
  SINGLE_SELECT("单选", 4),
  /**
   * 多选.
   */
  MULTI_SELECT("多选", 5),
  /**
   * 附件.
   */
  ATTACHMENT("附件", 6),
  /**
   * 手机.
   */
  PHONE("手机", 7),
  /**
   * 邮箱.
   */
  EMAIL("邮箱", 8);

  private final String typeName;
  private final int code;

  /**
   * 根据数值获取字段类型枚举.
   *
   * @param code 字段类型数值
   * @return 字段类型枚举，未匹配时返回 null
   */
  public static WxCpHrFieldType fromCode(int code) {
    for (WxCpHrFieldType type : WxCpHrFieldType.values()) {
      if (type.code == code) {
        return type;
      }
    }
    return null;
  }
}
