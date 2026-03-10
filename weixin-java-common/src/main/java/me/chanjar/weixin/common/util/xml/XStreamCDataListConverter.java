package me.chanjar.weixin.common.util.xml;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * 兼容两种格式的字符串列表转换器：
 * <ul>
 *   <li>旧格式（4.8.0之前）：&lt;MemChangeList&gt;&lt;![CDATA[id1,id2]]&gt;&lt;/MemChangeList&gt;</li>
 *   <li>新格式（4.8.0起）：&lt;MemChangeList&gt;&lt;Item&gt;&lt;![CDATA[id1]]&gt;&lt;/Item&gt;&lt;/MemChangeList&gt;</li>
 * </ul>
 * 解析结果统一为逗号分隔的字符串。
 */
public class XStreamCDataListConverter implements Converter {

  @Override
  public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
    if (source != null) {
      writer.setValue("<![CDATA[" + source + "]]>");
    }
  }

  @Override
  public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
    if (reader.hasMoreChildren()) {
      // 新格式：含有 <Item> 子元素
      StringBuilder sb = new StringBuilder();
      while (reader.hasMoreChildren()) {
        reader.moveDown();
        String value = reader.getValue();
        if (value != null && !value.isEmpty()) {
          if (sb.length() > 0) {
            sb.append(",");
          }
          sb.append(value);
        }
        reader.moveUp();
      }
      return sb.length() > 0 ? sb.toString() : null;
    } else {
      // 旧格式：直接 CDATA 文本
      String value = reader.getValue();
      return (value != null && !value.isEmpty()) ? value : null;
    }
  }

  @Override
  public boolean canConvert(Class type) {
    return type == String.class;
  }
}
