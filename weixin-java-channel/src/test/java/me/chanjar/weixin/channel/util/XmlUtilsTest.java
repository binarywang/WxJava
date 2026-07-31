package me.chanjar.weixin.channel.util;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import me.chanjar.weixin.channel.bean.base.AttrInfo;
import org.testng.annotations.Test;

/**
 * {@link XmlUtils} 单元测试
 */
public class XmlUtilsTest {

  private static final String XML = "<AttrInfo><attr_key>这是Key</attr_key><attr_value>这是Value</attr_value></AttrInfo>";

  @Test
  public void testEncode() {
    String xml = XmlUtils.encode(new AttrInfo("这是Key", "这是Value"));
    assertNotNull(xml);
    assertEquals(xml, XML);
  }

  @Test
  public void testEncodeWithObjectMapper() {
    String xml = XmlUtils.encode(new XmlMapper(), new AttrInfo("这是Key", "这是Value"));
    assertNotNull(xml);
    assertEquals(XmlUtils.decode(xml, AttrInfo.class).getKey(), "这是Key");
  }

  @Test
  public void testDecode() {
    AttrInfo info = XmlUtils.decode(XML, AttrInfo.class);
    assertNotNull(info);
    assertEquals(info.getKey(), "这是Key");
    assertEquals(info.getValue(), "这是Value");
  }

  @Test
  public void testDecodeUnknownProperty() {
    String xml = "<AttrInfo><attr_key>k</attr_key><unknown>v</unknown></AttrInfo>";
    AttrInfo info = XmlUtils.decode(xml, AttrInfo.class);
    assertNotNull(info);
    assertEquals(info.getKey(), "k");
    assertNull(info.getValue());
  }

  @Test
  public void testDecodeEmptyOrInvalidXml() {
    assertNull(XmlUtils.decode((String) null, AttrInfo.class));
    assertNull(XmlUtils.decode("", AttrInfo.class));
    assertNull(XmlUtils.decode("not a xml", AttrInfo.class));
  }

  @Test
  public void testDecodeWithTypeReference() {
    AttrInfo info = XmlUtils.decode(XML, new TypeReference<AttrInfo>() {
    });
    assertNotNull(info);
    assertEquals(info.getValue(), "这是Value");
  }

  @Test
  public void testDecodeInputStream() {
    InputStream is = new ByteArrayInputStream(XML.getBytes(StandardCharsets.UTF_8));
    AttrInfo info = XmlUtils.decode(is, AttrInfo.class);
    assertNotNull(info);
    assertEquals(info.getKey(), "这是Key");
  }
}
