package cn.binarywang.wx.miniapp.util.xml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import cn.binarywang.wx.miniapp.message.WxMaXmlOutMessage;
import com.thoughtworks.xstream.XStream;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class XStreamTransformer {
  private static final Map<Class<?>, XStream> CLASS_2_XSTREAM_INSTANCE = new HashMap<>();

  static {
    registerClass(WxMaMessage.class);
    registerClass(WxMaXmlOutMessage.class);
  }

  /**
   * {@code xml -> pojo.}
   *
   * @param <T>   返回类型
   * @param clazz 类型
   * @param xml   xml字符串
   * @return 转换后的对象
   */
  @SuppressWarnings("unchecked")
  public static <T> T fromXml(Class<T> clazz, String xml) {
    T object = (T) CLASS_2_XSTREAM_INSTANCE.get(clazz).fromXML(xml);
    return object;
  }

  /**
   * {@code xml -> pojo.}
   *
   * @param <T>   返回类型
   * @param clazz 类型
   * @param is    输入流
   * @return 转换后的对象
   */
  @SuppressWarnings("unchecked")
  public static <T> T fromXml(Class<T> clazz, InputStream is) {
    T object = (T) CLASS_2_XSTREAM_INSTANCE.get(clazz).fromXML(is);
    return object;
  }

  /**
   * {@code pojo -> xml.}
   *
   * @param <T>    类型参数
   * @param clazz  类型
   * @param object 对象
   * @return xml字符串
   */
  public static <T> String toXml(Class<T> clazz, T object) {
    return CLASS_2_XSTREAM_INSTANCE.get(clazz).toXML(object);
  }

  /**
   * 注册扩展消息的解析器.
   *
   * @param clz     类型
   * @param xStream xml解析器
   */
  public static void register(Class<?> clz, XStream xStream) {
    CLASS_2_XSTREAM_INSTANCE.put(clz, xStream);
  }
  /**
   * 注册第三方的该类及其子类.
   * 便利第三方类使用 XStreamTransformer进行序列化, 以及支持XStream 1.4.18 以上增加安全许可
   * @param clz 要注册的类
   */
  public static void registerExtendClass(Class<?> clz){
    XStream xstream = XStreamInitializer.getInstance();

    Class<?>[] innerClz = getInnerClasses(clz);
    xstream.processAnnotations(clz);
    xstream.processAnnotations(innerClz);
    xstream.allowTypes(new Class[]{clz});
    xstream.allowTypes(innerClz);

    register(clz, xstream);
  }
  /**
   * 会自动注册该类及其子类.
   *
   * @param clz 要注册的类
   */
  private static void registerClass(Class<?> clz) {
    XStream xstream = XStreamInitializer.getInstance();

    xstream.processAnnotations(clz);
    xstream.processAnnotations(getInnerClasses(clz));
    if (clz.equals(WxMaMessage.class)) {
      // 操蛋的微信，模板消息推送成功的消息是MsgID，其他消息推送过来是MsgId
      xstream.aliasField("MsgID", WxMaMessage.class, "msgId");
    }

    register(clz, xstream);
  }

  private static Class<?>[] getInnerClasses(Class<?> clz) {
    Class<?>[] innerClasses = clz.getClasses();
    if (innerClasses == null) {
      return null;
    }

    List<Class<?>> result = new ArrayList<>();
    result.addAll(Arrays.asList(innerClasses));
    for (Class<?> inner : innerClasses) {
      Class<?>[] innerClz = getInnerClasses(inner);
      if (innerClz == null) {
        continue;
      }

      result.addAll(Arrays.asList(innerClz));
    }

    return result.toArray(new Class<?>[0]);
  }
}
