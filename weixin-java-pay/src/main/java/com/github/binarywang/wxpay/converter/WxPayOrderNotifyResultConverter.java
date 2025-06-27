package com.github.binarywang.wxpay.converter;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyCoupon;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.reflection.AbstractReflectionConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * The type Wxpay order notify result converter.
 *
 * @author aimilin
 */
public class WxPayOrderNotifyResultConverter extends AbstractReflectionConverter {

  /**
   * Instantiates a new Wx pay order notify result converter.
   *
   * @param mapper             the mapper
   * @param reflectionProvider the reflection provider
   */
  public WxPayOrderNotifyResultConverter(Mapper mapper, ReflectionProvider reflectionProvider) {
    super(mapper, reflectionProvider);
  }

  @Override
  public boolean canConvert(Class type) {
    return type.equals(WxPayOrderNotifyResult.class);
  }

  @Override
  public void marshal(Object original, HierarchicalStreamWriter writer, MarshallingContext context) {
    super.marshal(original, writer, context);
    WxPayOrderNotifyResult obj = (WxPayOrderNotifyResult) original;
    List<WxPayOrderNotifyCoupon> list = obj.getCouponList();
    if (list == null || list.isEmpty()) {
      return;
    }
    for (int i = 0; i < list.size(); i++) {
      WxPayOrderNotifyCoupon coupon = list.get(i);
      writer.startNode("coupon_id_" + i);
      writer.setValue(coupon.getCouponId());
      writer.endNode();
      writer.startNode("coupon_type_" + i);
      writer.setValue(coupon.getCouponType());
      writer.endNode();
      writer.startNode("coupon_fee_" + i);
      writer.setValue(String.valueOf(coupon.getCouponFee()));
      writer.endNode();
    }
  }

  @Override
  protected void marshallField(MarshallingContext context, Object newObj, Field field) {
    if ("couponList".equals(field.getName())) {
      return;
    }

    super.marshallField(context, newObj, field);
  }

  @Override
  public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
    WxPayOrderNotifyResult obj = new WxPayOrderNotifyResult();

    List<Field> fields = new ArrayList<>(Arrays.asList(obj.getClass().getDeclaredFields()));
    fields.addAll(Arrays.asList(obj.getClass().getSuperclass().getDeclaredFields()));
    Map<String, Field> fieldMap = getFieldMap(fields);

    Map<Integer, WxPayOrderNotifyCoupon> coupons = Maps.newTreeMap();
    while (reader.hasMoreChildren()) {
      reader.moveDown();
      if (fieldMap.containsKey(reader.getNodeName())) {
        Field field = fieldMap.get(reader.getNodeName());
        this.setFieldValue(context, obj, field);
      } else if (StringUtils.startsWith(reader.getNodeName(), "coupon_id_")) {
        String id = (String) context.convertAnother(obj, String.class);
        this.getElement(coupons, reader.getNodeName()).setCouponId(id);
      } else if (StringUtils.startsWith(reader.getNodeName(), "coupon_type_")) {
        String type = (String) context.convertAnother(obj, String.class);
        this.getElement(coupons, reader.getNodeName()).setCouponType(type);
      } else if (StringUtils.startsWith(reader.getNodeName(), "coupon_fee_")) {
        Integer fee = (Integer) context.convertAnother(obj, Integer.class);
        this.getElement(coupons, reader.getNodeName()).setCouponFee(fee);
      }
      reader.moveUp();
    }

    obj.setCouponList(Lists.newArrayList(coupons.values()));
    return obj;
  }

  private void setFieldValue(UnmarshallingContext context, WxPayOrderNotifyResult obj, Field field) {
    Object val = context.convertAnother(obj, field.getType());
    try {
      if (val != null) {
        /*
        这里加一个看似多余的(String)强转可解决高jdk版本下的编译报错问题，
        详情见讨论https://github.com/vaadin/framework/issues/10737
        */
        PropertyDescriptor pd = new PropertyDescriptor((String) field.getName(), obj.getClass());
        pd.getWriteMethod().invoke(obj, val);
      }
    } catch (Exception ignored) {
    }
  }

  private Map<String, Field> getFieldMap(List<Field> fields) {
    return Maps.uniqueIndex(fields, field -> {
      if (field.isAnnotationPresent(XStreamAlias.class)) {
        return field.getAnnotation(XStreamAlias.class).value();
      }
      return field.getName();
    });
  }

  private WxPayOrderNotifyCoupon getElement(Map<Integer, WxPayOrderNotifyCoupon> coupons, String nodeName) {
    Integer index = Integer.valueOf(StringUtils.substringAfterLast(nodeName, "_"));
    coupons.computeIfAbsent(index, k -> new WxPayOrderNotifyCoupon());

    return coupons.get(index);
  }
}
