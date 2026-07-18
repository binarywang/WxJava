package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.bean.ocr.*;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.requestexecuter.ocr.OcrDiscernRequestExecutor;
import me.chanjar.weixin.common.service.WxOcrService;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Ocr.*;

/**
 * ocr 接口实现.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2019-06-22
 */
@RequiredArgsConstructor
public class WxMaOcrServiceImpl implements WxOcrService {

  private final WxMaService service;

  @Override
  public WxOcrIdCardResult idCard(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.service.post(String.format(IDCARD, imgUrl), (String) null);
    return WxOcrIdCardResult.fromJson(result);
  }

  @Override
  public WxOcrIdCardResult idCard(File imgFile) throws WxErrorException {
    String result = this.service.execute(OcrDiscernRequestExecutor.create(this.service.getRequestHttp()),
      FILEIDCARD, imgFile);
    return WxOcrIdCardResult.fromJson(result);
  }

  @Override
  public WxOcrBankCardResult bankCard(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.service.post(String.format(BANK_CARD, imgUrl), (String) null);
    return WxOcrBankCardResult.fromJson(result);
  }

  @Override
  public WxOcrBankCardResult bankCard(File imgFile) throws WxErrorException {
    String result = this.service.execute(OcrDiscernRequestExecutor.create(this.service.getRequestHttp()),
      FILE_BANK_CARD, imgFile);
    return WxOcrBankCardResult.fromJson(result);
  }

  @Override
  public WxOcrDrivingResult driving(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.service.post(String.format(DRIVING, imgUrl), (String) null);
    return WxOcrDrivingResult.fromJson(result);
  }

  @Override
  public WxOcrDrivingResult driving(File imgFile) throws WxErrorException {
    String result = this.service.execute(OcrDiscernRequestExecutor.create(this.service.getRequestHttp()),
      FILE_DRIVING, imgFile);
    return WxOcrDrivingResult.fromJson(result);
  }

  @Override
  public WxOcrDrivingLicenseResult drivingLicense(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.service.post(String.format(DRIVING_LICENSE, imgUrl), (String) null);
    return WxOcrDrivingLicenseResult.fromJson(result);
  }

  @Override
  public WxOcrDrivingLicenseResult drivingLicense(File imgFile) throws WxErrorException {
    String result = this.service.execute(OcrDiscernRequestExecutor.create(this.service.getRequestHttp()),
      FILE_DRIVING_LICENSE, imgFile);
    return WxOcrDrivingLicenseResult.fromJson(result);
  }

  @Override
  public WxOcrBizLicenseResult bizLicense(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.service.post(String.format(BIZ_LICENSE, imgUrl), (String) null);
    return WxOcrBizLicenseResult.fromJson(result);
  }

  @Override
  public WxOcrBizLicenseResult bizLicense(File imgFile) throws WxErrorException {
    String result = this.service.execute(OcrDiscernRequestExecutor.create(this.service.getRequestHttp()),
      FILE_BIZ_LICENSE, imgFile);
    return WxOcrBizLicenseResult.fromJson(result);
  }

  @Override
  public WxOcrCommResult comm(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.service.post(String.format(COMM, imgUrl), (String) null);
    return WxOcrCommResult.fromJson(result);
  }

  @Override
  public WxOcrCommResult comm(File imgFile) throws WxErrorException {
    String result = this.service.execute(OcrDiscernRequestExecutor.create(this.service.getRequestHttp()),
      FILE_COMM, imgFile);
    return WxOcrCommResult.fromJson(result);
  }

  @Override
  public WxOcrMenuResult menu(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.service.post(String.format(MENU, imgUrl), (String) null);
    return WxOcrMenuResult.fromJson(result);
  }

  @Override
  public WxOcrMenuResult menu(File imgFile) throws WxErrorException {
    String result = this.service.execute(OcrDiscernRequestExecutor.create(this.service.getRequestHttp()),
      FILE_MENU, imgFile);
    return WxOcrMenuResult.fromJson(result);
  }
}
