package com.github.binarywang.wxpay.bean.marketing;

import com.github.binarywang.wxpay.bean.result.BaseWxPayV3Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * 测试FavorStocksGetResult的原始JSON保存功能
 *
 * @author Binary Wang
 */
public class FavorStocksGetResultTest {

  private static final Gson GSON = new GsonBuilder().create();

  @Test
  public void testRawJsonPreservation() {
    // 模拟微信API返回的JSON（包含未在Result类中定义的字段）
    String mockJson = "{\n" +
      "  \"stock_id\": \"9836588\",\n" +
      "  \"stock_creator_mchid\": \"1230000109\",\n" +
      "  \"stock_name\": \"微信支付代金券\",\n" +
      "  \"status\": \"running\",\n" +
      "  \"create_time\": \"2021-01-01T00:00:00.000+08:00\",\n" +
      "  \"description\": \"微信支付营销\",\n" +
      "  \"card_id\": \"pFS7Fjg9kqcMOBtl3bFn\",\n" +
      "  \"extra_field\": \"这是一个新增字段\"\n" +
      "}";

    // 模拟服务调用：反序列化JSON
    FavorStocksGetResult result = GSON.fromJson(mockJson, FavorStocksGetResult.class);
    
    // 模拟服务调用：设置原始JSON
    result.setRawJsonString(mockJson);

    // 验证基本字段正常解析
    assertEquals(result.getStockId(), "9836588");
    assertEquals(result.getStockCreatorMchId(), "1230000109");
    assertEquals(result.getStockName(), "微信支付代金券");
    assertEquals(result.getStatus(), "running");

    // 验证原始JSON被保存
    assertNotNull(result.getRawJsonString());
    assertEquals(result.getRawJsonString(), mockJson);

    // 验证可以从原始JSON中获取未定义的字段
    assertTrue(result.getRawJsonString().contains("\"card_id\": \"pFS7Fjg9kqcMOBtl3bFn\""));
    assertTrue(result.getRawJsonString().contains("\"extra_field\": \"这是一个新增字段\""));
  }

  @Test
  public void testBaseV3ResultInheritance() {
    FavorStocksGetResult result = new FavorStocksGetResult();
    
    // 验证继承关系
    assertTrue(result instanceof BaseWxPayV3Result);
    
    // 验证基类方法可用
    result.setRawJsonString("test json");
    assertEquals(result.getRawJsonString(), "test json");
  }

  @Test
  public void testNullRawJson() {
    FavorStocksGetResult result = new FavorStocksGetResult();
    
    // 验证初始状态下rawJsonString为null
    assertNull(result.getRawJsonString());
    
    // 验证设置null不会出错
    result.setRawJsonString(null);
    assertNull(result.getRawJsonString());
  }
}