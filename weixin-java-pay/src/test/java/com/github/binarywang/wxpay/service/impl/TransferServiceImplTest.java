package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.transfer.QueryTransferBatchesRequest;
import com.github.binarywang.wxpay.bean.transfer.TransferBatchesRequest;
import com.github.binarywang.wxpay.bean.transfer.TransferBillsRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取商家转账到零钱服务类API测试
 *
 * @author zhongjun
 * created on  2022/6/17
 **/
@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class TransferServiceImplTest {

  @Inject
  private WxPayService payService;

  @Test
  public void testTransferBatches() throws WxPayException {
    List<TransferBatchesRequest.TransferDetail> transferDetailList = new ArrayList<>();
    transferDetailList.add(TransferBatchesRequest.TransferDetail.newBuilder()
      .outDetailNo("1655447989156")
      .transferAmount(100)
      .transferRemark("测试转账")
      .openid("oX_7Jzr9gSZz4X_Xc9-_7HGf8XzI")
      .userName("测试用户").build());
    TransferBatchesRequest batchesRequest = TransferBatchesRequest.newBuilder()
      .appid("wxf636efh5xxxxx")
      .outBatchNo("1655447999520")
      .batchName("测试批次")
      .batchRemark("测试批次备注")
      .totalAmount(100)
      .totalNum(1)
      .transferDetailList(transferDetailList).build();
    log.info("发起商家转账:{}", this.payService.getTransferService().transferBatches(batchesRequest));
  }

  @Test
  public void testTransferBatchesBatchId() throws WxPayException {
    log.info("微信批次单号查询批次单:{}", this.payService.getTransferService().transferBatchesBatchId(QueryTransferBatchesRequest.newBuilder()
      .batchId("1655448154148")
      .needQueryDetail(true)
      .build()));

  }

  @Test
  public void testTransferBatchesBatchIdDetail() throws WxPayException {
    log.info("微信明细单号查询明细单:{}", this.payService.getTransferService().transferBatchesBatchIdDetail("1030000071100999991182020050700019480001", "1040000071100999991182020050700019500100"));
  }

  @Test
  public void testTransferBatchesOutBatchNo() throws WxPayException {
    log.info("商家批次单号查询批次单:{}", this.payService.getTransferService().transferBatchesOutBatchNo(QueryTransferBatchesRequest.newBuilder()
      .outBatchNo("1655447999520")
      .needQueryDetail(true)
      .build()));
  }

  @Test
  public void testTransferBatchesOutBatchNoDetail() throws WxPayException {
    log.info("商家明细单号查询明细单:{}", this.payService.getTransferService().transferBatchesOutBatchNoDetail("1655447999520", "1655447989156"));
  }

  @Test
  public void testTransferBills() throws WxPayException {
    TransferBillsRequest transferBillsRequest = TransferBillsRequest.newBuilder()
      .appid("wxf636efh5xxxxx")
      .outBillNo("1655447989156")
      .transferSceneId("1005")
      .transferAmount(100)
      .transferRemark("测试转账")
      .openid("oX_7Jzr9gSZz4X_Xc9-_7HGf8XzI")
      .userName("测试用户").build();
    log.info("发起商家转账:{}", this.payService.getTransferService().transferBills(transferBillsRequest));
  }

  @Test
  public void testTransformBillsCancel() throws WxPayException {
    log.info("撤销商家转账:{}", this.payService.getTransferService().transformBillsCancel("123456"));
  }

  @Test
  public void testGetBillsByOutBillNo() throws WxPayException {
    log.info("商户单号查询转账单：{}", this.payService.getTransferService().getBillsByOutBillNo("123456"));
  }

  @Test
  public void testGetBillsByTransferBillNo() throws WxPayException {
    log.info("微信单号查询转账单：{}", this.payService.getTransferService().getBillsByTransferBillNo("123456"));
  }
}
