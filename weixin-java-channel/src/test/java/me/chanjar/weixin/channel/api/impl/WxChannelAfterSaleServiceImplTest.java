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
import me.chanjar.weixin.channel.bean.after.AfterSaleListResponse;
import me.chanjar.weixin.channel.bean.after.AfterSaleReasonResponse;
import me.chanjar.weixin.channel.bean.after.AfterSaleRejectReasonResponse;
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
}
