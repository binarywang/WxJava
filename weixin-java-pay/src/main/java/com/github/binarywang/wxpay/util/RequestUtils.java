package com.github.binarywang.wxpay.util;

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
   */
  public static String readData(HttpServletRequest request) {
    BufferedReader br = null;
    StringBuilder result = new StringBuilder();
    try {
      br = request.getReader();
      for (String line; (line = br.readLine()) != null; ) {
        if (result.length() > 0) {
          result.append("\n");
        }
        result.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return result.toString();
  }

}
