package me.chanjar.weixin.common.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import me.chanjar.weixin.common.error.WxRuntimeException;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultText;
import org.xml.sax.SAXException;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <pre>
 * XML转换工具类.
 * Created by Binary Wang on 2018/11/4.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class XmlUtils {

  public static Map<String, Object> xml2Map(String xmlString) {
    Map<String, Object> map = new HashMap<>(16);
    try {
      SAXReader saxReader = new SAXReader();
      saxReader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
      saxReader.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
      saxReader.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
      saxReader.setFeature("http://xml.org/sax/features/external-general-entities", false);
      saxReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
      Document doc = saxReader.read(new StringReader(xmlString));
      Element root = doc.getRootElement();
      List<Element> elements = root.elements();
      for (Element element : elements) {
        String elementName = element.getName();
        if (map.containsKey(elementName)) {
          if (map.get(elementName) instanceof List) {
            ((List<Object>) map.get(elementName)).add(element2MapOrString(element));
          } else {
            List<Object> value = Lists.newArrayList(map.get(elementName));
            value.add(element2MapOrString(element));
            map.put(elementName, value);
          }
        } else {
          map.put(elementName, element2MapOrString(element));
        }
      }
    } catch (DocumentException | SAXException e) {
      throw new WxRuntimeException(e);
    }

    return map;
  }

  private static Object element2MapOrString(Element element) {

    final List<Node> nodes = element.content();
    final List<String> names = names(nodes);

    // 判断节点下有无非文本节点(非Text和CDATA)，如无，直接取Text文本内容
    if (names.isEmpty()) {
      return element.getText();
    }

    Map<String, Object> result = Maps.newHashMap();
    Set<String> distinctNames = Sets.newHashSet(names);
    if (distinctNames.size() == 1) {
      // 说明是个列表，各个子对象是相同的name
      List<Object> list = Lists.newArrayList();
      for (Node node : nodes) {
        if (node instanceof DefaultText) {
          continue;
        }

        if (node instanceof Element) {
          list.add(element2MapOrString((Element) node));
        }
      }

      result.put(names.iterator().next(), list);
    } else if (distinctNames.size() == names.size()) {
      for (Node node : nodes) {
        if (node instanceof DefaultText) {
          continue;
        }

        if (node instanceof Element) {
          result.put(node.getName(), element2MapOrString((Element) node));
        }
      }
    } else {
      // 说明有重复name，但不是全部都相同
      Map<String, Long> namesCountMap = names.stream().collect(Collectors.groupingBy(a -> a, Collectors.counting()));
      for (Node node : nodes) {
        if (node instanceof DefaultText) {
          continue;
        }

        if (node instanceof Element) {
          String nodeName = node.getName();
          if (namesCountMap.get(nodeName) == 1) {
            result.put(nodeName, element2MapOrString((Element) node));
          } else {
            List<Object> values;
            if (result.containsKey(nodeName)) {
              values = (List<Object>) result.get(nodeName);
            } else {
              values = Lists.newArrayList();
              result.put(nodeName, values);
            }

            values.add(element2MapOrString((Element) node));
          }
        }
      }
    }

    return result;
  }

  private static List<String> names(List<Node> nodes) {
    List<String> names = Lists.newArrayList();
    for (Node node : nodes) {
      // 如果节点类型是Text或CDATA跳过
      if (node instanceof DefaultText || node instanceof CDATA) {
        continue;
      }
      names.add(node.getName());
    }

    return names;
  }
}
