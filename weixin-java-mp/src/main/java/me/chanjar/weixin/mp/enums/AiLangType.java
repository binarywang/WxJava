package me.chanjar.weixin.mp.enums;

import lombok.Getter;

/**
 * <pre>
 *  AI开放接口里的语言类型，目前只支持两种：中文和英文
 *  Created by BinaryWang on 2018/6/10.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Getter
public enum AiLangType {
  /**
   * 中文 汉语.
   */
  zh_CN("zh_CN"),
  /**
   * 英文 英语.
   */
  en_US("en_US");

  private final String code;

  AiLangType(String code) {
    this.code = code;
  }
}
