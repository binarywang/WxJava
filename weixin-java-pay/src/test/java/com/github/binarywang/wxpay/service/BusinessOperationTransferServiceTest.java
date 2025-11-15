package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.transfer.*;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 运营工具-商家转账API测试
 *
 * @author WxJava Team
 */
public class BusinessOperationTransferServiceTest {

  private WxPayService wxPayService;

  @BeforeClass
  public void setup() {
    WxPayConfig config = new WxPayConfig();
    config.setAppId("test_app_id");
    config.setMchId("test_mch_id");
    
    wxPayService = new WxPayServiceImpl();
    wxPayService.setConfig(config);
  }

  @Test
  public void testServiceInitialization() {
    BusinessOperationTransferService service = this.wxPayService.getBusinessOperationTransferService();
    assertThat(service).isNotNull();
  }

  @Test
  public void testRequestBuilder() {
    BusinessOperationTransferRequest request = BusinessOperationTransferRequest.newBuilder()
      .appid("test_app_id")
      .outBillNo("OT" + System.currentTimeMillis())
      .operationSceneId(WxPayConstants.OperationSceneId.OPERATION_CASH_MARKETING)
      .openid("test_openid")
      .transferAmount(100)
      .transferRemark("测试转账")
      .userRecvPerception(WxPayConstants.UserRecvPerception.CASH_MARKETING.CASH)
      .build();

    assertThat(request.getAppid()).isEqualTo("test_app_id");
    assertThat(request.getOperationSceneId()).isEqualTo(WxPayConstants.OperationSceneId.OPERATION_CASH_MARKETING);
    assertThat(request.getTransferAmount()).isEqualTo(100);
    assertThat(request.getTransferRemark()).isEqualTo("测试转账");
  }

  @Test
  public void testQueryRequestBuilder() {
    BusinessOperationTransferQueryRequest request = BusinessOperationTransferQueryRequest.newBuilder()
      .outBillNo("OT123456789")
      .appid("test_app_id")
      .build();

    assertThat(request.getOutBillNo()).isEqualTo("OT123456789");
    assertThat(request.getAppid()).isEqualTo("test_app_id");
  }

  @Test
  public void testConstants() {
    // 测试运营工具转账场景ID常量
    assertThat(WxPayConstants.OperationSceneId.OPERATION_CASH_MARKETING).isEqualTo("2001");
    assertThat(WxPayConstants.OperationSceneId.OPERATION_COMMISSION).isEqualTo("2002");
    assertThat(WxPayConstants.OperationSceneId.OPERATION_PROMOTION).isEqualTo("2003");
  }

  @Test
  public void testResultClasses() {
    // 测试结果类的基本功能
    BusinessOperationTransferResult result = new BusinessOperationTransferResult();
    result.setOutBillNo("test_out_bill_no");
    result.setTransferBillNo("test_transfer_bill_no");
    result.setTransferState("SUCCESS");

    assertThat(result.getOutBillNo()).isEqualTo("test_out_bill_no");
    assertThat(result.getTransferBillNo()).isEqualTo("test_transfer_bill_no");
    assertThat(result.getTransferState()).isEqualTo("SUCCESS");

    BusinessOperationTransferQueryResult queryResult = new BusinessOperationTransferQueryResult();
    queryResult.setOperationSceneId("2001");
    queryResult.setTransferAmount(100);

    assertThat(queryResult.getOperationSceneId()).isEqualTo("2001");
    assertThat(queryResult.getTransferAmount()).isEqualTo(100);
  }
}