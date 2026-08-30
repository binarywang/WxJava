package com.binarywang.wxjava.store.util;


import static com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse.INTERNAL_ERROR_CODE;

import java.lang.reflect.InvocationTargetException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * 响应工具类
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
@UtilityClass
public class ResponseUtils {

  /**
   * 将json反序列化成对象
   *
   * @param json      json
   * @param valueType Class<T>
   * @return T
   */
  public static <T extends WxStoreBaseResponse> T decode(String json, Class<T> valueType) {
    T t = null;
    try {
      if (StringUtils.isNotBlank(json)) {
        t = JsonUtils.decode(json, valueType);
      }
    } catch (Exception e) {
      log.error("decode", e);
    }
    if (t == null) {
      t = internalError(valueType);
    }
    return t;
  }

  /**
   * 设置系统内部错误
   *
   * @param clazz 类
   * @param <T>   T
   * @return 错误
   */
  public static <T extends WxStoreBaseResponse> T internalError(Class<T> clazz) {
    try {
      T t = clazz.getDeclaredConstructor().newInstance();
      t.setErrCode(INTERNAL_ERROR_CODE);
      t.setErrMsg("内部错误");
      return t;
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      log.error("internalError", e);
    }
    // 正常情况下不会执行到这里
    return null;
  }

}
