package me.chanjar.weixin.common.util;

import com.google.common.collect.Lists;
import me.chanjar.weixin.common.annotation.Required;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * bean操作的一些工具类
 * Created by Binary Wang on 2016-10-21.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 */
public class BeanUtils {
  private static Logger log = LoggerFactory.getLogger(BeanUtils.class);

  /**
   * 检查bean里标记为@Required的field是否为空，为空则抛异常
   *
   * @param bean 要检查的bean对象
   */
  public static void checkRequiredFields(Object bean) throws WxErrorException {
    List<String> requiredFields = Lists.newArrayList();

    List<Field> fields = new ArrayList<>(Arrays.asList(bean.getClass().getDeclaredFields()));
    fields.addAll(Arrays.asList(bean.getClass().getSuperclass().getDeclaredFields()));
    for (Field field : fields) {
      try {
        boolean isAccessible = field.isAccessible();
        field.setAccessible(true);
        if (field.isAnnotationPresent(Required.class)) {
          // 两种情况，一种是值为null，
          // 另外一种情况是类型为字符串，但是字符串内容为空的，都认为是没有提供值
          boolean isRequiredMissing = field.get(bean) == null
            || (field.get(bean) instanceof String
            && StringUtils.isBlank(field.get(bean).toString())
          );
          if (isRequiredMissing) {
            requiredFields.add(field.getName());
          }
        }
        field.setAccessible(isAccessible);
      } catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
        log.error(e.getMessage(), e);
      }
    }

    if (!requiredFields.isEmpty()) {
      String msg = String.format("必填字段【%s】必须提供值！", requiredFields);
      log.debug(msg);
      throw new WxErrorException(msg);
    }
  }

}
