package com.github.binarywang.wxpay.bean.marketing.transfer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * 测试 BatchDetailsResult 的字段序列化和反序列化功能
 *
 * @author Binary Wang
 */
public class BatchDetailsResultTest {

  private static final Gson GSON = new GsonBuilder().create();

  @Test
  public void testBankFieldsDeserialization() {
    // 模拟微信API返回的JSON（包含银行相关字段）
    String mockJson = "{\n" +
      "  \"sp_mchid\": \"1900001109\",\n" +
      "  \"out_batch_no\": \"plfk2020042013\",\n" +
      "  \"batch_id\": \"1030000071100999991182020050700019480001\",\n" +
      "  \"appid\": \"wxf636efh567hg4356\",\n" +
      "  \"out_detail_no\": \"x23zy545Bd5436\",\n" +
      "  \"detail_id\": \"1040000071100999991182020050700019500100\",\n" +
      "  \"detail_status\": \"SUCCESS\",\n" +
      "  \"transfer_amount\": 200000,\n" +
      "  \"transfer_remark\": \"2020年4月报销\",\n" +
      "  \"openid\": \"o-MYE42l80oelYMDE34nYD456Xoy\",\n" +
      "  \"username\": \"757b340b45ebef5467rter35gf464344v3542sdf4t6re4tb4f54ty45t4yyry45\",\n" +
      "  \"initiate_time\": \"2015-05-20T13:29:35.120+08:00\",\n" +
      "  \"update_time\": \"2015-05-20T13:29:35.120+08:00\",\n" +
      "  \"bank_name\": \"中国农业银行股份有限公司深圳分行\",\n" +
      "  \"bank_card_number_tail\": \"1234\"\n" +
      "}";

    // 反序列化JSON
    BatchDetailsResult result = GSON.fromJson(mockJson, BatchDetailsResult.class);

    // 验证基本字段正常解析
    assertEquals(result.getSpMchid(), "1900001109");
    assertEquals(result.getOutBatchNo(), "plfk2020042013");
    assertEquals(result.getBatchId(), "1030000071100999991182020050700019480001");
    assertEquals(result.getAppId(), "wxf636efh567hg4356");
    assertEquals(result.getOutDetailNo(), "x23zy545Bd5436");
    assertEquals(result.getDetailId(), "1040000071100999991182020050700019500100");
    assertEquals(result.getDetailStatus(), "SUCCESS");
    assertEquals(result.getTransferAmount(), Integer.valueOf(200000));
    assertEquals(result.getTransferRemark(), "2020年4月报销");
    assertEquals(result.getOpenid(), "o-MYE42l80oelYMDE34nYD456Xoy");
    assertEquals(result.getUserName(), "757b340b45ebef5467rter35gf464344v3542sdf4t6re4tb4f54ty45t4yyry45");
    assertEquals(result.getInitiateTime(), "2015-05-20T13:29:35.120+08:00");
    assertEquals(result.getUpdateTime(), "2015-05-20T13:29:35.120+08:00");

    // 验证新增的银行相关字段
    assertEquals(result.getBankName(), "中国农业银行股份有限公司深圳分行");
    assertEquals(result.getBankCardNumberTail(), "1234");
  }

  @Test
  public void testBankFieldsWithNull() {
    // 测试不包含银行字段的情况（转账到零钱）
    String mockJsonWithoutBank = "{\n" +
      "  \"sp_mchid\": \"1900001109\",\n" +
      "  \"out_batch_no\": \"plfk2020042013\",\n" +
      "  \"batch_id\": \"1030000071100999991182020050700019480001\",\n" +
      "  \"out_detail_no\": \"x23zy545Bd5436\",\n" +
      "  \"detail_id\": \"1040000071100999991182020050700019500100\",\n" +
      "  \"detail_status\": \"SUCCESS\",\n" +
      "  \"transfer_amount\": 200000,\n" +
      "  \"transfer_remark\": \"2020年4月报销\",\n" +
      "  \"openid\": \"o-MYE42l80oelYMDE34nYD456Xoy\",\n" +
      "  \"username\": \"757b340b45ebef5467rter35gf464344v3542sdf4t6re4tb4f54ty45t4yyry45\",\n" +
      "  \"initiate_time\": \"2015-05-20T13:29:35.120+08:00\",\n" +
      "  \"update_time\": \"2015-05-20T13:29:35.120+08:00\"\n" +
      "}";

    BatchDetailsResult result = GSON.fromJson(mockJsonWithoutBank, BatchDetailsResult.class);

    // 验证其他字段正常
    assertEquals(result.getSpMchid(), "1900001109");
    assertEquals(result.getDetailStatus(), "SUCCESS");

    // 验证银行字段为null（转账到零钱场景下不返回这些字段）
    assertNull(result.getBankName());
    assertNull(result.getBankCardNumberTail());
  }

  @Test
  public void testBankFieldsSerialization() {
    // 测试序列化
    BatchDetailsResult result = new BatchDetailsResult();
    result.setSpMchid("1900001109");
    result.setOutBatchNo("plfk2020042013");
    result.setBatchId("1030000071100999991182020050700019480001");
    result.setDetailStatus("SUCCESS");
    result.setBankName("中国工商银行股份有限公司北京分行");
    result.setBankCardNumberTail("5678");

    String json = GSON.toJson(result);

    // 验证JSON包含银行字段
    assertTrue(json.contains("\"bank_name\":\"中国工商银行股份有限公司北京分行\""));
    assertTrue(json.contains("\"bank_card_number_tail\":\"5678\""));
  }

  @Test
  public void testToString() {
    // 测试toString方法
    BatchDetailsResult result = new BatchDetailsResult();
    result.setSpMchid("1900001109");
    result.setBankName("中国建设银行股份有限公司上海分行");
    result.setBankCardNumberTail("9012");

    String resultString = result.toString();

    // 验证toString包含所有字段
    assertNotNull(resultString);
    assertTrue(resultString.contains("1900001109"));
    assertTrue(resultString.contains("中国建设银行股份有限公司上海分行"));
    assertTrue(resultString.contains("9012"));
  }

  @Test
  public void testBankNameWithSpecialCharacters() {
    // 测试银行名称包含特殊字符的情况
    String mockJson = "{\n" +
      "  \"sp_mchid\": \"1900001109\",\n" +
      "  \"out_batch_no\": \"plfk2020042013\",\n" +
      "  \"batch_id\": \"1030000071100999991182020050700019480001\",\n" +
      "  \"out_detail_no\": \"x23zy545Bd5436\",\n" +
      "  \"detail_id\": \"1040000071100999991182020050700019500100\",\n" +
      "  \"detail_status\": \"SUCCESS\",\n" +
      "  \"transfer_amount\": 200000,\n" +
      "  \"transfer_remark\": \"2020年4月报销\",\n" +
      "  \"openid\": \"o-MYE42l80oelYMDE34nYD456Xoy\",\n" +
      "  \"username\": \"757b340b45ebef5467rter35gf464344v3542sdf4t6re4tb4f54ty45t4yyry45\",\n" +
      "  \"initiate_time\": \"2015-05-20T13:29:35.120+08:00\",\n" +
      "  \"update_time\": \"2015-05-20T13:29:35.120+08:00\",\n" +
      "  \"bank_name\": \"中国农业银行股份有限公司北京市朝阳区(支行)\",\n" +
      "  \"bank_card_number_tail\": \"0000\"\n" +
      "}";

    BatchDetailsResult result = GSON.fromJson(mockJson, BatchDetailsResult.class);

    // 验证特殊字符正确解析
    assertEquals(result.getBankName(), "中国农业银行股份有限公司北京市朝阳区(支行)");
    assertEquals(result.getBankCardNumberTail(), "0000");
  }

  @Test
  public void testFailedTransferWithoutBankFields() {
    // 测试转账失败的情况
    String mockJson = "{\n" +
      "  \"sp_mchid\": \"1900001109\",\n" +
      "  \"out_batch_no\": \"plfk2020042013\",\n" +
      "  \"batch_id\": \"1030000071100999991182020050700019480001\",\n" +
      "  \"out_detail_no\": \"x23zy545Bd5436\",\n" +
      "  \"detail_id\": \"1040000071100999991182020050700019500100\",\n" +
      "  \"detail_status\": \"FAIL\",\n" +
      "  \"transfer_amount\": 200000,\n" +
      "  \"transfer_remark\": \"2020年4月报销\",\n" +
      "  \"fail_reason\": \"ACCOUNT_FROZEN\",\n" +
      "  \"openid\": \"o-MYE42l80oelYMDE34nYD456Xoy\",\n" +
      "  \"username\": \"757b340b45ebef5467rter35gf464344v3542sdf4t6re4tb4f54ty45t4yyry45\",\n" +
      "  \"initiate_time\": \"2015-05-20T13:29:35.120+08:00\",\n" +
      "  \"update_time\": \"2015-05-20T13:29:35.120+08:00\"\n" +
      "}";

    BatchDetailsResult result = GSON.fromJson(mockJson, BatchDetailsResult.class);

    // 验证失败状态
    assertEquals(result.getDetailStatus(), "FAIL");
    assertEquals(result.getFailReason(), "ACCOUNT_FROZEN");

    // 失败的情况下银行字段应为null
    assertNull(result.getBankName());
    assertNull(result.getBankCardNumberTail());
  }
}
