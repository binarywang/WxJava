package me.chanjar.weixin.channel.test;

import com.google.inject.Binder;
import com.google.inject.Module;
import java.io.IOException;
import java.io.InputStream;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.api.impl.WxChannelServiceImpl;
import me.chanjar.weixin.channel.util.XmlUtils;
import me.chanjar.weixin.common.error.WxRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试模块
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public class ApiTestModule implements Module {

  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private static final String TEST_CONFIG_XML = "test-config.xml";

  @Override
  public void configure(Binder binder) {
    try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(TEST_CONFIG_XML)) {
      if (inputStream == null) {
        throw new WxRuntimeException(
          "测试配置文件【" + TEST_CONFIG_XML + "】未找到，请参照test-config-sample.xml文件生成");
      }

      // 提示xml相关依赖不存在时，引入一下就好
      TestConfig config = this.fromXml(TestConfig.class, inputStream);
      WxChannelService service = new WxChannelServiceImpl();

      service.setConfig(config);

      binder.bind(TestConfig.class).toInstance(config);
      binder.bind(WxChannelService.class).toInstance(service);
    } catch (IOException e) {
      this.log.error(e.getMessage(), e);
    }
  }

  private <T> T fromXml(Class<T> clazz, InputStream is) {
    return XmlUtils.decode(is, clazz);
  }

}
