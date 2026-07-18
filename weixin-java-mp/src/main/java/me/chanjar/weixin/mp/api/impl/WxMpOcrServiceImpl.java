package me.chanjar.weixin.mp.api.impl;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.service.WxOcrService;
import me.chanjar.weixin.common.bean.ocr.*;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.requestexecuter.ocr.OcrDiscernRequestExecutor;
import me.chanjar.weixin.mp.api.WxMpService;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Ocr.*;

/**
 * ocr 接口实现.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2019-06-22
 */
@RequiredArgsConstructor
public class WxMpOcrServiceImpl implements WxOcrService {
  private final WxMpService mainService;

  @Override
  public WxOcrIdCardResult idCard(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.mainService.post(String.format(IDCARD.getUrl(this.mainService.getWxMpConfigStorage()),
      imgUrl), (String) null);
    return WxOcrIdCardResult.fromJson(result);
  }

  @Override
  public WxOcrIdCardResult idCard(File imgFile) throws WxErrorException {
    String result = this.mainService.execute(OcrDiscernRequestExecutor.create(this.mainService.getRequestHttp()),
      FILEIDCARD.getUrl(this.mainService.getWxMpConfigStorage()), imgFile);
    return WxOcrIdCardResult.fromJson(result);
  }

  @Override
  public WxOcrBankCardResult bankCard(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.mainService.post(String.format(BANK_CARD.getUrl(this.mainService.getWxMpConfigStorage()),
      imgUrl), (String) null);
    return WxOcrBankCardResult.fromJson(result);
  }

  @Override
  public WxOcrBankCardResult bankCard(File imgFile) throws WxErrorException {
    String result = this.mainService.execute(OcrDiscernRequestExecutor.create(this.mainService.getRequestHttp()),
      FILE_BANK_CARD.getUrl(this.mainService.getWxMpConfigStorage()), imgFile);
    return WxOcrBankCardResult.fromJson(result);
  }

  @Override
  public WxOcrDrivingResult driving(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.mainService.post(String.format(DRIVING.getUrl(this.mainService.getWxMpConfigStorage()),
      imgUrl), (String) null);
    return WxOcrDrivingResult.fromJson(result);
  }

  @Override
  public WxOcrDrivingResult driving(File imgFile) throws WxErrorException {
    String result = this.mainService.execute(OcrDiscernRequestExecutor.create(this.mainService.getRequestHttp()),
      FILE_DRIVING.getUrl(this.mainService.getWxMpConfigStorage()), imgFile);
    return WxOcrDrivingResult.fromJson(result);
  }

  @Override
  public WxOcrDrivingLicenseResult drivingLicense(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.mainService.post(String.format(DRIVING_LICENSE.getUrl(this.mainService.getWxMpConfigStorage()),
      imgUrl), (String) null);
    return WxOcrDrivingLicenseResult.fromJson(result);
  }

  @Override
  public WxOcrDrivingLicenseResult drivingLicense(File imgFile) throws WxErrorException {
    String result = this.mainService.execute(OcrDiscernRequestExecutor.create(this.mainService.getRequestHttp()),
      FILE_DRIVING_LICENSE.getUrl(this.mainService.getWxMpConfigStorage()), imgFile);
    return WxOcrDrivingLicenseResult.fromJson(result);
  }

  @Override
  public WxOcrBizLicenseResult bizLicense(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.mainService.post(String.format(BIZ_LICENSE.getUrl(this.mainService.getWxMpConfigStorage()),
      imgUrl), (String) null);
    return WxOcrBizLicenseResult.fromJson(result);
  }

  @Override
  public WxOcrBizLicenseResult bizLicense(File imgFile) throws WxErrorException {
    String result = this.mainService.execute(OcrDiscernRequestExecutor.create(this.mainService.getRequestHttp()),
      FILE_BIZ_LICENSE.getUrl(this.mainService.getWxMpConfigStorage()), imgFile);
    return WxOcrBizLicenseResult.fromJson(result);
  }

  @Override
  public WxOcrCommResult comm(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.mainService.post(String.format(COMM.getUrl(this.mainService.getWxMpConfigStorage()),
      imgUrl), (String) null);
    return WxOcrCommResult.fromJson(result);
  }

  @Override
  public WxOcrCommResult comm(File imgFile) throws WxErrorException {
    String result = this.mainService.execute(OcrDiscernRequestExecutor.create(this.mainService.getRequestHttp()),
      FILE_COMM.getUrl(this.mainService.getWxMpConfigStorage()), imgFile);
    return WxOcrCommResult.fromJson(result);
  }

  @Override
  public WxOcrMenuResult menu(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.mainService.post(String.format(MENU.getUrl(this.mainService.getWxMpConfigStorage()),
      imgUrl), (String) null);
    return WxOcrMenuResult.fromJson(result);
  }

  @Override
  public WxOcrMenuResult menu(File imgFile) throws WxErrorException {
    String result = this.mainService.execute(OcrDiscernRequestExecutor.create(this.mainService.getRequestHttp()),
      FILE_MENU.getUrl(this.mainService.getWxMpConfigStorage()), imgFile);
    return WxOcrMenuResult.fromJson(result);
  }
}
