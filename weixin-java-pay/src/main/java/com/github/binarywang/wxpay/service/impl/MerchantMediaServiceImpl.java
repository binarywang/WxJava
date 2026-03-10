package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.media.ImageUploadResult;
import com.github.binarywang.wxpay.bean.media.VideoUploadResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.MerchantMediaService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.WechatPayUploadHttpPost;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.net.URI;

/**
 * 微信支付-媒体文件上传service
 * @author zhouyongshen
 */
@Slf4j
@RequiredArgsConstructor
public class MerchantMediaServiceImpl implements MerchantMediaService {
  private final WxPayService payService;

  @Override
  public ImageUploadResult imageUploadV3(File imageFile) throws WxPayException,IOException {
    String url = String.format("%s/v3/merchant/media/upload", this.payService.getPayBaseUrl());

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
  public ImageUploadResult imageUploadV3(InputStream inputStream, String fileName) throws WxPayException, IOException {
    String url = String.format("%s/v3/merchant/media/upload", this.payService.getPayBaseUrl());
    try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
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

  @Override
  public VideoUploadResult videoUploadV3(File videoFile) throws WxPayException, IOException {
    String url = String.format("%s/v3/merchant/media/video_upload", this.payService.getPayBaseUrl());

    try (FileInputStream s1 = new FileInputStream(videoFile)) {
      String sha256 = DigestUtils.sha256Hex(s1);
      try (InputStream s2 = new FileInputStream(videoFile)) {
        WechatPayUploadHttpPost request = new WechatPayUploadHttpPost.Builder(URI.create(url))
          .withVideo(videoFile.getName(), sha256, s2)
          .build();
        String result = this.payService.postV3(url, request);
        return VideoUploadResult.fromJson(result);
      }
    }
  }

  @Override
  public VideoUploadResult videoUploadV3(InputStream inputStream, String fileName) throws WxPayException, IOException {
    String url = String.format("%s/v3/merchant/media/video_upload", this.payService.getPayBaseUrl());
    try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
      byte[] buffer = new byte[2048];
      int len;
      while ((len = inputStream.read(buffer)) > -1) {
        bos.write(buffer, 0, len);
      }
      bos.flush();
      byte[] data = bos.toByteArray();
      String sha256 = DigestUtils.sha256Hex(data);
      WechatPayUploadHttpPost request = new WechatPayUploadHttpPost.Builder(URI.create(url))
        .withVideo(fileName, sha256, new ByteArrayInputStream(data))
        .build();
      String result = this.payService.postV3(url, request);
      return VideoUploadResult.fromJson(result);
    }
  }

}
