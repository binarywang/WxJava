package me.chanjar.weixin.common.util;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * <pre>
 *  数据处理工具类
 *  Created by BinaryWang on 2018/5/8.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class DataUtils {
  /**
   * 将数据中包含的secret字符使用星号替换，防止日志打印时被输出
   */
  public static <E> E handleDataWithSecret(E data) {
    E dataForLog = data;
    if(data instanceof String && StringUtils.contains((String)data, "&secret=")){
      dataForLog = (E) RegExUtils.replaceAll((String)data,"&secret=\\w+&","&secret=******&");
    }
    return dataForLog;
  }
}
