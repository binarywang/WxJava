package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.media.MarketingImageUploadResult;
import com.github.binarywang.wxpay.exception.WxPayException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * <pre>
 * 微信支付营销专用媒体接口.
 * </pre>
 *
 * @author thinsstar
 */
public interface MarketingMediaService {
  /**
   * <pre>
   * 营销专用接口-图片上传API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_0_1.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/favor/media/image-upload
   * </pre>
   *
   * @param imageFile 需要上传的图片文件
   * @return ImageUploadResult 微信返回的媒体文件标识Id。示例值：6uqyGjGrCf2GtyXP8bxrbuH9-aAoTjH-rKeSl3Lf4_So6kdkQu4w8BYVP3bzLtvR38lxt4PjtCDXsQpzqge_hQEovHzOhsLleGFQVRF-U_0
   * @throws WxPayException the wx pay exception
   */
  MarketingImageUploadResult imageUploadV3(File imageFile) throws WxPayException, IOException;

  /**
   * <pre>
   * 营销专用接口-图片上传API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_0_1.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/favor/media/image-upload
   * </pre>
   *
   * @param inputStream 需要上传的图片文件流
   * @param fileName    需要上传的图片文件名
   * @return ImageUploadResult 微信返回的媒体文件标识Id。示例值：6uqyGjGrCf2GtyXP8bxrbuH9-aAoTjH-rKeSl3Lf4_So6kdkQu4w8BYVP3bzLtvR38lxt4PjtCDXsQpzqge_hQEovHzOhsLleGFQVRF-U_0
   * @throws WxPayException the wx pay exception
   */
  MarketingImageUploadResult imageUploadV3(InputStream inputStream, String fileName) throws WxPayException, IOException;


}
