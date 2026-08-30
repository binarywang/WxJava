package me.chanjar.weixin.channel.api;

import java.io.File;
import me.chanjar.weixin.channel.bean.address.AddressCodeResponse;
import me.chanjar.weixin.channel.bean.image.ChannelImageInfo;
import me.chanjar.weixin.channel.bean.image.ChannelImageResponse;
import me.chanjar.weixin.channel.bean.image.QualificationFileResponse;
import me.chanjar.weixin.channel.bean.shop.ShopH5UrlResponse;
import me.chanjar.weixin.channel.bean.shop.ShopInfoResponse;
import me.chanjar.weixin.channel.bean.shop.ShopQrCodeResponse;
import me.chanjar.weixin.channel.bean.shop.ShopTagLinkResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 基础接口
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Deprecated
public interface WxChannelBasicService {

  /**
   * 获取店铺基本信息
   *
   * @return 店铺基本信息
   */
  ShopInfoResponse getShopInfo() throws WxErrorException;

  /**
   * 获取店铺H5链接
   *
   * @return 店铺H5链接
   * @throws WxErrorException 异常
   */
  ShopH5UrlResponse getShopH5Url() throws WxErrorException;

  /**
   * 获取店铺二维码
   *
   * @param qrcodeType 二维码类型，1:二维码；2:标准物料；3:送礼物物料
   * @return 店铺二维码
   * @throws WxErrorException 异常
   */
  ShopQrCodeResponse getShopQrCode(int qrcodeType) throws WxErrorException;

  /**
   * 获取店铺口令
   *
   * @return 店铺口令
   * @throws WxErrorException 异常
   */
  ShopTagLinkResponse getShopTagLink() throws WxErrorException;

  /**
   * 上传图片
   *
   * @param respType 0:media_id和pay_media_id；1:图片链接（商品信息相关图片请务必使用此参数得到链接）
   * @param imgUrl   图片url
   * @return 图片信息
   *
   * @throws WxErrorException 异常
   */
  ChannelImageInfo uploadImg(int respType, String imgUrl) throws WxErrorException;

  /**
   * 上传图片
   *
   * @param respType 0:media_id和pay_media_id；1:图片链接（商品信息相关图片请务必使用此参数得到链接）
   * @param file     图片文件
   * @param height   图片的高，单位：像素
   * @param width    图片的宽，单位：像素
   * @return 图片信息
   *
   * @throws WxErrorException 异常
   */
  ChannelImageInfo uploadImg(int respType, File file, int height, int width) throws WxErrorException;

  /**
   * 上传资质图片
   *
   * @param file 资质图片
   * @return 结果
   *
   * @throws WxErrorException 异常
   */
  QualificationFileResponse uploadQualificationFile(File file) throws WxErrorException;

  /**
   * 根据media_id获取图片
   *
   * @param mediaId media_id
   */
  ChannelImageResponse getImg(String mediaId) throws WxErrorException;

  /**
   * 获取地址编码(最多获取4级)
   *
   * @param code 地址行政编码，不填或者填0时，拉取全国的省级行政编码
   * @return AddressCodeResponse
   */
  AddressCodeResponse getAddressCode(Integer code) throws WxErrorException;
}
