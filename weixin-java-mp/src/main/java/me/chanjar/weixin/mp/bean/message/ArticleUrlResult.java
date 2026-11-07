package me.chanjar.weixin.mp.bean.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author plw on 2021/9/7 10:39 AM.
 * @version 1.0
 */
@Data
@XStreamAlias("ArticleUrlResult")
@JacksonXmlRootElement(localName = "ArticleUrlResult")
public class ArticleUrlResult implements Serializable {

  @XStreamAlias("ResultList")
  @XStreamConverter(value = com.thoughtworks.xstream.converters.extended.NamedCollectionConverter.class,
    strings = "item", types = Item.class, useImplicitType = false)
  @JacksonXmlProperty(localName = "ResultList")
  private List<Item> resultList;

  @XStreamAlias("Count")
  @JacksonXmlProperty(localName = "Count")
  private Long count;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  @Data
  @XStreamAlias("item")
  @JacksonXmlRootElement(localName = "item")
  public static class Item implements Serializable {

    @XStreamAlias("ArticleIdx")
    @JacksonXmlProperty(localName = "ArticleIdx")
    private String articleIdx;

    @XStreamAlias("ArticleUrl")
    @XStreamConverter(value = XStreamCDataConverter.class)
    @JacksonXmlProperty(localName = "ArticleUrl")
    @JacksonXmlCData
    private String articleUrl;

    @Override
    public String toString() {
      return WxMpGsonBuilder.create().toJson(this);
    }

  }
}
