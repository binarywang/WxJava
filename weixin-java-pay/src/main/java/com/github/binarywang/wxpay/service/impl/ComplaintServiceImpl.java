package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.complaint.*;
import com.github.binarywang.wxpay.bean.media.ImageUploadResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.ComplaintService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.WechatPayUploadHttpPost;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.BadPaddingException;
import java.io.*;
import java.net.URI;
import java.util.List;

/**
 * <pre>
 * 消费者投诉2.0 实现.
 * Created by jmdhappy on 2022/3/19.
 * </pre>
 *
 * @author <a href="https://gitee.com/jeequan/jeepay">jmdhappy</a>
 */
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {
  private static final Gson GSON = new GsonBuilder().create();
  private final WxPayService payService;

  @Override
  public ComplaintResult queryComplaints(ComplaintRequest request) throws WxPayException, BadPaddingException {
    String url = String.format("%s/v3/merchant-service/complaints-v2?limit=%d&offset=%d&begin_date=%s&end_date=%s&complainted_mchid=%s",
      this.payService.getPayBaseUrl(), request.getLimit(), request.getOffset(), request.getBeginDate(), request.getEndDate(), request.getComplaintedMchid());
    String response = this.payService.getV3(url);
    ComplaintResult complaintResult = GSON.fromJson(response, ComplaintResult.class);
    List<ComplaintDetailResult> data = complaintResult.getData();
    for (ComplaintDetailResult complaintDetailResult : data) {
      // 对手机号进行解密操作
      if (complaintDetailResult.getPayerPhone() != null) {
        String payerPhone = RsaCryptoUtil.decryptOAEP(complaintDetailResult.getPayerPhone(), this.payService.getConfig().getPrivateKey());
        complaintDetailResult.setPayerPhone(payerPhone);
      }
    }
    return complaintResult;
  }

  @Override
  public ComplaintDetailResult getComplaint(ComplaintDetailRequest request) throws WxPayException, BadPaddingException {
    String url = String.format("%s/v3/merchant-service/complaints-v2/%s",
      this.payService.getPayBaseUrl(), request.getComplaintId());
    String response = this.payService.getV3(url);
    ComplaintDetailResult result = GSON.fromJson(response, ComplaintDetailResult.class);
    // 对手机号进行解密操作
    if (result.getPayerPhone() != null) {
      String payerPhone = RsaCryptoUtil.decryptOAEP(result.getPayerPhone(), this.payService.getConfig().getPrivateKey());
      result.setPayerPhone(payerPhone);
    }
    return result;
  }

  @Override
  public NegotiationHistoryResult queryNegotiationHistorys(NegotiationHistoryRequest request) throws WxPayException {
    String url = String.format("%s/v3/merchant-service/complaints-v2/%s/negotiation-historys?limit=%d&offset=%d",
      this.payService.getPayBaseUrl(), request.getComplaintId(), request.getLimit(), request.getOffset());
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, NegotiationHistoryResult.class);
  }

  @Override
  public ComplaintNotifyUrlResult addComplaintNotifyUrl(ComplaintNotifyUrlRequest request) throws WxPayException {
    String url = String.format("%s/v3/merchant-service/complaint-notifications", this.payService.getPayBaseUrl());
    String response = this.payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(response, ComplaintNotifyUrlResult.class);
  }

  @Override
  public ComplaintNotifyUrlResult getComplaintNotifyUrl() throws WxPayException {
    String url = String.format("%s/v3/merchant-service/complaint-notifications", this.payService.getPayBaseUrl());
    String response = this.payService.getV3(url);
    return GSON.fromJson(response, ComplaintNotifyUrlResult.class);
  }

  @Override
  public ComplaintNotifyUrlResult updateComplaintNotifyUrl(ComplaintNotifyUrlRequest request) throws WxPayException {
    String url = String.format("%s/v3/merchant-service/complaint-notifications", this.payService.getPayBaseUrl());
    String response = this.payService.putV3(url, GSON.toJson(request));
    return GSON.fromJson(response, ComplaintNotifyUrlResult.class);
  }

  @Override
  public void deleteComplaintNotifyUrl() throws WxPayException {
    String url = String.format("%s/v3/merchant-service/complaint-notifications", this.payService.getPayBaseUrl());
    this.payService.deleteV3(url);
  }

  @Override
  public void submitResponse(ResponseRequest request) throws WxPayException {
    String url = String.format("%s/v3/merchant-service/complaints-v2/%s/response", this.payService.getPayBaseUrl(), request.getComplaintId());
    // 上面url已经含有complaintId，这里设置为空，避免在body中再次传递，否则微信会报错
    request.setComplaintId(null);
    this.payService.postV3(url, GSON.toJson(request));
  }

  @Override
  public void complete(CompleteRequest request) throws WxPayException {
    String url = String.format("%s/v3/merchant-service/complaints-v2/%s/complete", this.payService.getPayBaseUrl(), request.getComplaintId());
    // 上面url已经含有complaintId，这里设置为空，避免在body中再次传递，否则微信会报错
    request.setComplaintId(null);
    this.payService.postV3(url, GSON.toJson(request));
  }

  @Override
  public void updateRefundProgress(UpdateRefundProgressRequest request) throws WxPayException {
    String url = String.format("%s/v3/merchant-service/complaints-v2/%s/update-refund-progress", this.payService.getPayBaseUrl(), request.getComplaintId());
    // 上面url已经含有complaintId，这里设置为空，避免在body中再次传递，否则微信会报错
    request.setComplaintId(null);
    this.payService.postV3(url, GSON.toJson(request));
  }

  @Override
  public ImageUploadResult uploadResponseImage(File imageFile) throws WxPayException, IOException {
    String url = String.format("%s/v3/merchant-service/images/upload", this.payService.getPayBaseUrl());

    try (FileInputStream s1 = new FileInputStream(imageFile)) {
      String sha256 = DigestUtils.sha256Hex(s1);
      try (InputStream s2 = new FileInputStream(imageFile)) {
        WechatPayUploadHttpPost request = new WechatPayUploadHttpPost.Builder(URI.create(url))
          .withImage(imageFile.getName(), sha256, s2)
          .build();
        String result = this.payService.postV3(url, request);
        return ImageUploadResult.fromJson(result);
      }
    }
  }

  @Override
  public ImageUploadResult uploadResponseImage(InputStream inputStream, String fileName) throws WxPayException, IOException {
    String url = String.format("%s/v3/merchant-service/images/upload", this.payService.getPayBaseUrl());
    try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
      //文件大小不能超过2M
      byte[] buffer = new byte[2048];
      int len;
      while ((len = inputStream.read(buffer)) > -1) {
        bos.write(buffer, 0, len);
      }
      bos.flush();
      byte[] data = bos.toByteArray();
      String sha256 = DigestUtils.sha256Hex(data);
      WechatPayUploadHttpPost request = new WechatPayUploadHttpPost.Builder(URI.create(url))
        .withImage(fileName, sha256, new ByteArrayInputStream(data))
        .build();
      String result = this.payService.postV3(url, request);
      return ImageUploadResult.fromJson(result);
    }
  }

}
