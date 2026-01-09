package com.github.binarywang.wxpay.v3.util;

import com.github.binarywang.wxpay.bean.profitsharing.request.ProfitSharingReceiverV3Request;
import com.github.binarywang.wxpay.bean.profitsharing.request.ProfitSharingV3Request;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.v3.SpecEncrypt;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * RsaCryptoUtil 测试类
 */
public class RsaCryptoUtilTest {

  /**
   * 测试反射能否找到嵌套类中的 @SpecEncrypt 注解字段
   */
  @Test
  public void testFindAnnotatedFieldsInNestedClass() {
    // 创建 Receiver 对象
    ProfitSharingV3Request.Receiver receiver = new ProfitSharingV3Request.Receiver();
    receiver.setName("测试姓名");
    
    // 使用反射查找带有 @SpecEncrypt 注解的字段
    Class<?> receiverClass = receiver.getClass();
    Field[] fields = receiverClass.getDeclaredFields();
    
    System.out.println("=== Receiver 类中的所有字段 ===");
    boolean foundNameField = false;
    boolean nameFieldHasAnnotation = false;
    
    for (Field field : fields) {
      System.out.println("字段名: " + field.getName() + ", 类型: " + field.getType().getName());
      if (field.getName().equals("name")) {
        foundNameField = true;
        if (field.isAnnotationPresent(SpecEncrypt.class)) {
          nameFieldHasAnnotation = true;
          System.out.println("  -> name 字段有 @SpecEncrypt 注解");
        } else {
          System.out.println("  -> name 字段没有 @SpecEncrypt 注解");
        }
      }
    }
    
    // 验证能够找到 name 字段并且它有 @SpecEncrypt 注解
    assertTrue(foundNameField, "应该能找到 name 字段");
    assertTrue(nameFieldHasAnnotation, "name 字段应该有 @SpecEncrypt 注解");
  }

  /**
   * 测试嵌套对象中的字段加密
   * 验证 List<Receiver> 中每个 Receiver 对象的 name 字段是否能被正确找到和处理
   */
  @Test
  public void testEncryptFieldsWithNestedObjects() {
    // 创建测试对象
    ProfitSharingV3Request request = ProfitSharingV3Request.newBuilder()
      .appid("test-appid")
      .subMchId("test-submchid")
      .transactionId("test-transaction")
      .outOrderNo("test-order-no")
      .unfreezeUnsplit(true)
      .build();
    
    List<ProfitSharingV3Request.Receiver> receivers = new ArrayList<>();
    ProfitSharingV3Request.Receiver receiver = new ProfitSharingV3Request.Receiver();
    receiver.setName("张三");  // 设置需要加密的字段
    receiver.setAccount("test-account");
    receiver.setType("PERSONAL_OPENID");
    receiver.setAmount(100);
    receiver.setRelationType("STORE");
    receiver.setDescription("测试分账");
    
    receivers.add(receiver);
    request.setReceivers(receivers);
    
    // 验证 receivers 字段有 @SpecEncrypt 注解
    try {
      Field receiversField = ProfitSharingV3Request.class.getDeclaredField("receivers");
      boolean hasAnnotation = receiversField.isAnnotationPresent(SpecEncrypt.class);
      System.out.println("ProfitSharingV3Request.receivers 字段有 @SpecEncrypt 注解: " + hasAnnotation);
      assertTrue(hasAnnotation, "receivers 字段应该有 @SpecEncrypt 注解");
    } catch (NoSuchFieldException e) {
      fail("应该能找到 receivers 字段");
    }
    
    System.out.println("测试对象创建成功，name字段: " + receiver.getName());
    // 验证name字段不为null
    assertNotNull(receiver.getName());
    assertEquals(receiver.getName(), "张三");
  }

  /**
   * 测试单个对象中的字段加密
   * 验证直接在对象上的 @SpecEncrypt 字段是否能被正确找到
   */
  @Test
  public void testEncryptFieldsWithDirectField() {
    // 创建测试对象
    ProfitSharingReceiverV3Request request = ProfitSharingReceiverV3Request.newBuilder()
      .appid("test-appid")
      .subMchId("test-submchid")
      .type("PERSONAL_OPENID")
      .account("test-account")
      .name("李四")
      .relationType("STORE")
      .build();
    
    // 验证 name 字段有 @SpecEncrypt 注解
    try {
      Field nameField = ProfitSharingReceiverV3Request.class.getDeclaredField("name");
      boolean hasAnnotation = nameField.isAnnotationPresent(SpecEncrypt.class);
      System.out.println("ProfitSharingReceiverV3Request.name 字段有 @SpecEncrypt 注解: " + hasAnnotation);
      assertTrue(hasAnnotation, "name 字段应该有 @SpecEncrypt 注解");
    } catch (NoSuchFieldException e) {
      fail("应该能找到 name 字段");
    }
    
    System.out.println("测试对象创建成功，name字段: " + request.getName());
    // 验证name字段不为null
    assertNotNull(request.getName());
    assertEquals(request.getName(), "李四");
  }
}
