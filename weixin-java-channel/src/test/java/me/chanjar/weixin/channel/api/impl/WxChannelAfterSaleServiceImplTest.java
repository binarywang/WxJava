package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.google.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import me.chanjar.weixin.channel.api.WxChannelAfterSaleService;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.after.AfterSaleInfoResponse;
import me.chanjar.weixin.channel.bean.after.AfterSaleCreateResponse;
import me.chanjar.weixin.channel.bean.after.AfterSaleGenAfterSaleOrderParam;
import me.chanjar.weixin.channel.bean.after.AfterSaleHandleFastExchangeReceiptParam;
import me.chanjar.weixin.channel.bean.after.AfterSaleListResponse;
import me.chanjar.weixin.channel.bean.after.AfterSaleRefundPriceDiffParam;
import me.chanjar.weixin.channel.bean.after.AfterSaleReasonResponse;
import me.chanjar.weixin.channel.bean.after.AfterSaleRejectReasonResponse;
import me.chanjar.weixin.channel.bean.after.AfterSaleVirtualTelNumResponse;
import me.chanjar.weixin.channel.bean.after.GuaranteeMerchantModifyParam;
import me.chanjar.weixin.channel.bean.after.GuaranteeMerchantProofParam;
import me.chanjar.weixin.channel.bean.after.GuaranteeOrderResponse;
import me.chanjar.weixin.channel.bean.after.SyncWorkOrderParam;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.complaint.ComplaintOrderResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxChannelAfterSaleServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testListIds() throws WxErrorException {
    WxChannelAfterSaleService afterSaleService = channelService.getAfterSaleService();
    Long beginCreateTime = LocalDateTime.now().minusDays(7).atZone(ZoneId.systemDefault()).toEpochSecond();
    Long endCreateTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
    String nextKey = null;
    AfterSaleListResponse response = afterSaleService.listIds(beginCreateTime, endCreateTime, nextKey);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGet() throws WxErrorException {
    WxChannelAfterSaleService afterSaleService = channelService.getAfterSaleService();
    String afterSaleOrderId = "";
    AfterSaleInfoResponse response = afterSaleService.get(afterSaleOrderId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testAccept() throws WxErrorException {
    WxChannelAfterSaleService afterSaleService = channelService.getAfterSaleService();
    String afterSaleOrderId = "";
    String addressId = null;
    WxChannelBaseResponse response = afterSaleService.accept(afterSaleOrderId, addressId, 2);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testReject() throws WxErrorException {
    WxChannelAfterSaleService afterSaleService = channelService.getAfterSaleService();
    String afterSaleOrderId = "";
    String rejectReason = null;
    WxChannelBaseResponse response = afterSaleService.reject(afterSaleOrderId, rejectReason,1);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testRejectWithCertificates() throws WxErrorException {
    WxChannelAfterSaleService afterSaleService = channelService.getAfterSaleService();
    String afterSaleOrderId = "";
    String rejectReason = null;
    List<String> rejectCertificates = new ArrayList<>(4);
    rejectCertificates.add("THE_FILE_ID_1");
    WxChannelBaseResponse response = afterSaleService.reject(afterSaleOrderId, rejectReason, 1, rejectCertificates);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUploadRefundEvidence() throws WxErrorException {
    WxChannelAfterSaleService afterSaleService = channelService.getAfterSaleService();
    String afterSaleOrderId = "";
    String desc = "";
    List<String> certificates = new ArrayList<>(4);
    WxChannelBaseResponse response = afterSaleService.uploadRefundEvidence(afterSaleOrderId, desc, certificates);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testAddComplaintMaterial() throws WxErrorException {
    WxChannelAfterSaleService afterSaleService = channelService.getAfterSaleService();
    String complaintId = "";
    String content = "";
    List<String> mediaIds = new ArrayList<>(4);
    WxChannelBaseResponse response = afterSaleService.addComplaintMaterial(complaintId, content, mediaIds);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testAddComplaintEvidence() throws WxErrorException {
    WxChannelAfterSaleService afterSaleService = channelService.getAfterSaleService();
    String complaintId = "";
    String content = "";
    List<String> mediaIds = new ArrayList<>(4);
    WxChannelBaseResponse response = afterSaleService.addComplaintEvidence(complaintId, content, mediaIds);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetComplaint() throws WxErrorException {
    WxChannelAfterSaleService afterSaleService = channelService.getAfterSaleService();
    String complaintId = "";
    ComplaintOrderResponse response = afterSaleService.getComplaint(complaintId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }


  @Test
  public void testGetAllReason() throws WxErrorException {
    WxChannelAfterSaleService afterSaleService = channelService.getAfterSaleService();
    AfterSaleReasonResponse allReason = afterSaleService.getAllReason();
    assertNotNull(allReason);
    assertTrue(allReason.isSuccess());
  }

  @Test
  public void testGetRejectReason() throws WxErrorException {
    WxChannelAfterSaleService afterSaleService = channelService.getAfterSaleService();
    AfterSaleRejectReasonResponse rejectReason = afterSaleService.getRejectReason();
    assertNotNull(rejectReason);
    assertTrue(rejectReason.isSuccess());
  }

  @Test
  public void testGenAfterSaleOrder() throws WxErrorException {
    WxChannelAfterSaleService afterSaleService = channelService.getAfterSaleService();
    AfterSaleGenAfterSaleOrderParam param = new AfterSaleGenAfterSaleOrderParam();
    param.setRequestId("request-id");
    param.setOrderId("");
    param.setProductId("");
    param.setSkuId("");
    param.setCount(1);
    param.setAmount(1);
    param.setReason("10000014");
    param.setType("REFUND");
    AfterSaleCreateResponse response = afterSaleService.genAfterSaleOrder(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testRefundPriceDiff() throws WxErrorException {
    WxChannelAfterSaleService afterSaleService = channelService.getAfterSaleService();
    AfterSaleRefundPriceDiffParam param = new AfterSaleRefundPriceDiffParam();
    param.setRequestId("request-id");
    param.setOrderId("");
    param.setProductId("");
    param.setSkuId("");
    param.setAmount(1);
    param.setReason("10001336");
    AfterSaleCreateResponse response = afterSaleService.refundPriceDiff(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testApplyVirtualTelNum() throws WxErrorException {
    WxChannelAfterSaleService afterSaleService = channelService.getAfterSaleService();
    AfterSaleVirtualTelNumResponse response = afterSaleService.applyVirtualTelNum("");
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testHandleFastExchangeReceipt() throws WxErrorException {
    WxChannelAfterSaleService afterSaleService = channelService.getAfterSaleService();
    AfterSaleHandleFastExchangeReceiptParam param = new AfterSaleHandleFastExchangeReceiptParam();
    param.setAfterSaleOrderId("");
    param.setAct(1);
    WxChannelBaseResponse response = afterSaleService.handleFastExchangeReceipt(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetGuaranteeOrder() throws WxErrorException {
    WxChannelAfterSaleService afterSaleService = channelService.getAfterSaleService();
    GuaranteeOrderResponse response = afterSaleService.getGuaranteeOrder("");
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testMerchantAcceptGuarantee() throws WxErrorException {
    WxChannelAfterSaleService afterSaleService = channelService.getAfterSaleService();
    WxChannelBaseResponse response = afterSaleService.merchantAcceptGuarantee("");
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testMerchantModifyGuarantee() throws WxErrorException {
    WxChannelAfterSaleService afterSaleService = channelService.getAfterSaleService();
    GuaranteeMerchantModifyParam param = new GuaranteeMerchantModifyParam();
    param.setGuaranteeOrderId("");
    param.setBadLevel(10);
    param.setMerchantRemark("remark");
    WxChannelBaseResponse response = afterSaleService.merchantModifyGuarantee(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testMerchantProofGuarantee() throws WxErrorException {
    WxChannelAfterSaleService afterSaleService = channelService.getAfterSaleService();
    GuaranteeMerchantProofParam param = new GuaranteeMerchantProofParam();
    param.setGuaranteeOrderId("");
    param.setContent("proof");
    param.setPicList(new ArrayList<>(4));
    WxChannelBaseResponse response = afterSaleService.merchantProofGuarantee(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testSyncWorkOrder() throws WxErrorException {
    WxChannelAfterSaleService afterSaleService = channelService.getAfterSaleService();
    SyncWorkOrderParam param = new SyncWorkOrderParam();
    param.setComplaintId("");
    SyncWorkOrderParam.WorkOrderInfo workOrderInfo = new SyncWorkOrderParam.WorkOrderInfo();
    workOrderInfo.setVersion(1);
    workOrderInfo.setWorkOrderId("");
    workOrderInfo.setItems(new ArrayList<>(0));
    param.setWorkOrderInfo(workOrderInfo);
    WxChannelBaseResponse response = afterSaleService.syncWorkOrder(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
