package com.github.binarywang.wxpay.testbase;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.thoughtworks.xstream.XStream;
import java.io.*;
import java.nio.charset.StandardCharsets;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;
import org.apache.http.HttpRequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Api test module.
 */
public class CustomizedApiTestModule implements Module {
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private static final String TEST_CONFIG_XML = "test-config.xml";

  @Override
  public void configure(Binder binder) {
    try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(TEST_CONFIG_XML)) {
      if (inputStream == null) {
        throw new WxRuntimeException("测试配置文件【" + TEST_CONFIG_XML + "】未找到，请参照test-config-sample.xml文件生成");
      }

      XmlWxPayConfig config = this.fromXml(XmlWxPayConfig.class, inputStream);
      config.setIfSaveApiData(true);

      config.setApiV3HttpClientBuilderCustomizer((builder) -> {
        builder.addInterceptorLast((HttpRequestInterceptor) (r, c) -> System.out.println("--------> V3 HttpRequestInterceptor ..."));
      });

      config.setHttpClientBuilderCustomizer((builder) -> {
        builder.addInterceptorLast((HttpRequestInterceptor) (r, c) -> System.out.println("--------> HttpRequestInterceptor ..."));
      });
      try (FileInputStream fis = new FileInputStream(config.getPrivateKeyPath());
           InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
           BufferedReader reader = new BufferedReader(isr)) {

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
          stringBuilder.append(line);
          stringBuilder.append(System.lineSeparator());
        }

        String fileContent = stringBuilder.toString();
        config.setPrivateKeyString(fileContent);
        System.out.println(fileContent);

      }
      WxPayService wxService = new WxPayServiceImpl();
      wxService.setConfig(config);

      binder.bind(WxPayService.class).toInstance(wxService);
      binder.bind(WxPayConfig.class).toInstance(config);
    } catch (IOException e) {
      this.log.error(e.getMessage(), e);
    }
  }

  @SuppressWarnings("unchecked")
  private <T> T fromXml(Class<T> clazz, InputStream is) {
    XStream xstream = XStreamInitializer.getInstance();
    xstream.alias("xml", clazz);
    xstream.processAnnotations(clazz);
    return (T) xstream.fromXML(is);
  }

}
