package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.media.ImageUploadResult;
import com.github.binarywang.wxpay.bean.media.VideoUploadResult;
import com.github.binarywang.wxpay.exception.WxPayException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * <pre>
 * 微信支付通用媒体接口.
 * </pre>
 *
 * @author zhouyongshen
 */
public interface MerchantMediaService {
  /**
   * <pre>
   * 通用接口-图片上传API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/tool/chapter3_1.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/merchant/media/upload
   * </pre>
   *
   * @param imageFile 需要上传的图片文件
   * @return ImageUploadResult 微信返回的媒体文件标识Id。示例值：6uqyGjGrCf2GtyXP8bxrbuH9-aAoTjH-rKeSl3Lf4_So6kdkQu4w8BYVP3bzLtvR38lxt4PjtCDXsQpzqge_hQEovHzOhsLleGFQVRF-U_0
   * @throws WxPayException the wx pay exception
   */
  ImageUploadResult imageUploadV3(File imageFile) throws WxPayException, IOException;

  /**
   * <pre>
   * 通用接口-图片上传API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/tool/chapter3_1.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/merchant/media/upload
   * </pre>
   *
   * @param inputStream 需要上传的图片文件流
   * @param fileName 需要上传的图片文件名
   * @return ImageUploadResult 微信返回的媒体文件标识Id。示例值：6uqyGjGrCf2GtyXP8bxrbuH9-aAoTjH-rKeSl3Lf4_So6kdkQu4w8BYVP3bzLtvR38lxt4PjtCDXsQpzqge_hQEovHzOhsLleGFQVRF-U_0
   * @throws WxPayException the wx pay exception
   */
  ImageUploadResult imageUploadV3(InputStream inputStream, String fileName) throws WxPayException, IOException;

  /**
   * <pre>
   * 通用接口-视频上传API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/tool/chapter3_2.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/merchant/media/video_upload
   * </pre>
   *
   * @param videoFile 需要上传的视频文件
   * @return VideoUploadResult 微信返回的媒体文件标识Id。示例值：6uqyGjGrCf2GtyXP8bxrbuH9-aAoTjH-rKeSl3Lf4_So6kdkQu4w8BYVP3bzLtvR38lxt4PjtCDXsQpzqge_hQEovHzOhsLleGFQVRF-U_0
   * @throws WxPayException the wx pay exception
   * @throws IOException the io exception
   */
  VideoUploadResult videoUploadV3(File videoFile) throws WxPayException, IOException;

  /**
   * <pre>
   * 通用接口-视频上传API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/tool/chapter3_2.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/merchant/media/video_upload
   * 注意：此方法会将整个视频流读入内存计算SHA256后再上传，大文件可能导致OOM，建议大文件使用File方式上传
   * </pre>
   *
   * @param inputStream 需要上传的视频文件流
   * @param fileName 需要上传的视频文件名
   * @return VideoUploadResult 微信返回的媒体文件标识Id。示例值：6uqyGjGrCf2GtyXP8bxrbuH9-aAoTjH-rKeSl3Lf4_So6kdkQu4w8BYVP3bzLtvR38lxt4PjtCDXsQpzqge_hQEovHzOhsLleGFQVRF-U_0
   * @throws WxPayException the wx pay exception
   * @throws IOException the io exception
   */
  VideoUploadResult videoUploadV3(InputStream inputStream, String fileName) throws WxPayException, IOException;

}
