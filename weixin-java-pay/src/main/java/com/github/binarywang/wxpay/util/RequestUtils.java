package com.github.binarywang.wxpay.util;

import me.chanjar.weixin.common.error.WxRuntimeException;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * <pre>
 * 请求工具类.
 * Created by Wang_Wong on 2023-04-14.
 * </pre>
 *
 * @author <a href="https://github.com/0katekate0/">Wang_Wong</a>
 */
public class RequestUtils {

  /**
   * 获取请求头数据，微信V3版本回调使用
   *
   * @param request HTTP请求对象
   * @return 字符串
   * @throws WxRuntimeException 读取请求体失败时抛出
   */
  public static String readData(HttpServletRequest request) {
    StringBuilder result = new StringBuilder();
    try (BufferedReader br = request.getReader()) {
      for (String line; (line = br.readLine()) != null; ) {
        if (result.length() > 0) {
          result.append("\n");
        }
        result.append(line);
      }
    } catch (IOException e) {
      throw new WxRuntimeException("读取请求体失败", e);
    }

    return result.toString();
  }

}
