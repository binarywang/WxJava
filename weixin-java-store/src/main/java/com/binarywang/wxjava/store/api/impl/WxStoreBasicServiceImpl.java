package com.binarywang.wxjava.store.api.impl;

import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Basics.GET_ADDRESS_CODE;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Basics.GET_IMG_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Basics.GET_SHOP_H5URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Basics.GET_SHOP_INFO;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Basics.GET_SHOP_QRCODE;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Basics.GET_SHOP_TAGLINK;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Basics.IMG_UPLOAD_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Basics.UPLOAD_QUALIFICATION_FILE;

import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.api.WxStoreBasicService;
import com.binarywang.wxjava.store.bean.address.AddressCodeResponse;
import com.binarywang.wxjava.store.bean.image.StoreImageInfo;
import com.binarywang.wxjava.store.bean.image.StoreImageResponse;
import com.binarywang.wxjava.store.bean.image.QualificationFileResponse;
import com.binarywang.wxjava.store.bean.image.UploadImageResponse;
import com.binarywang.wxjava.store.bean.shop.ShopH5UrlResponse;
import com.binarywang.wxjava.store.bean.shop.ShopInfoResponse;
import com.binarywang.wxjava.store.bean.shop.ShopQrCodeResponse;
import com.binarywang.wxjava.store.bean.shop.ShopTagLinkResponse;
import com.binarywang.wxjava.store.executor.StoreFileUploadRequestExecutor;
import com.binarywang.wxjava.store.executor.StoreMediaDownloadRequestExecutor;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxStoreBasicServiceImpl implements WxStoreBasicService {

  /** 微信商店服务 */
  private final BaseWxStoreServiceImpl<?, ?> shopService;

  public WxStoreBasicServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public ShopInfoResponse getShopInfo() throws WxErrorException {
    String resJson = shopService.get(GET_SHOP_INFO, null);
    return ResponseUtils.decode(resJson, ShopInfoResponse.class);
  }

  @Override
  public StoreImageInfo uploadImg(int respType, String imgUrl) throws WxErrorException {
    String url = IMG_UPLOAD_URL + "?upload_type=1&resp_type=" + respType;
    String reqJson = "{\"img_url\":\"" + imgUrl + "\"}";
    String resJson = shopService.post(url, reqJson);
    UploadImageResponse response = ResponseUtils.decode(resJson, UploadImageResponse.class);
    return response.getImgInfo();
  }

  @Override
  public StoreImageInfo uploadImg(int respType, File file, int height, int width) throws WxErrorException {
    String url = IMG_UPLOAD_URL + "?upload_type=0&resp_type=" + respType + "&height=" + height + "&width=" + width;
    RequestExecutor<String, File> executor = StoreFileUploadRequestExecutor.create(shopService);
    String resJson = shopService.execute(executor, url, file);
    UploadImageResponse response = ResponseUtils.decode(resJson, UploadImageResponse.class);
    return response.getImgInfo();
  }

  @Override
  public QualificationFileResponse uploadQualificationFile(File file) throws WxErrorException {
    RequestExecutor<String, File> executor = StoreFileUploadRequestExecutor.create(shopService);
    String resJson = shopService.execute(executor, UPLOAD_QUALIFICATION_FILE, file);
    return ResponseUtils.decode(resJson, QualificationFileResponse.class);
  }

  @Override
  public StoreImageResponse getImg(String mediaId) throws WxErrorException {
    String appId = shopService.getConfig().getAppid();
    StoreImageResponse rs;
    try {
      String url = GET_IMG_URL + "?media_id=" + mediaId;
      File tempDirectory = new File(System.getProperty("java.io.tmpdir"), "wxjava-store-" + appId);
      if (!tempDirectory.exists() && !tempDirectory.mkdirs()) {
        throw new IOException("无法创建临时目录: " + tempDirectory);
      }
      RequestExecutor<StoreImageResponse, String> executor = StoreMediaDownloadRequestExecutor.create(shopService,
        tempDirectory);
      rs = shopService.execute(executor, url, null);
    } catch (IOException e) {
      throw new WxErrorException(WxError.builder().errorMsg(e.getMessage()).build(), e);
    }
    if (rs == null) {
      rs = ResponseUtils.internalError(StoreImageResponse.class);
    }
    return rs;
  }

  @Override
  public AddressCodeResponse getAddressCode(Integer code) throws WxErrorException {
    String reqJson = "{\"addr_code\": " + code + "}";
    String resJson = shopService.post(GET_ADDRESS_CODE, reqJson);
    return ResponseUtils.decode(resJson, AddressCodeResponse.class);
  }

  @Override
  public ShopH5UrlResponse getShopH5Url() throws WxErrorException {
    String resJson = shopService.post(GET_SHOP_H5URL, "{}");
    return ResponseUtils.decode(resJson, ShopH5UrlResponse.class);
  }

  @Override
  public ShopQrCodeResponse getShopQrCode(int qrcodeType) throws WxErrorException {
    String reqJson = "{\"qrcode_type\":" + qrcodeType + "}";
    String resJson = shopService.post(GET_SHOP_QRCODE, reqJson);
    return ResponseUtils.decode(resJson, ShopQrCodeResponse.class);
  }

  @Override
  public ShopTagLinkResponse getShopTagLink() throws WxErrorException {
    String resJson = shopService.post(GET_SHOP_TAGLINK, "{}");
    return ResponseUtils.decode(resJson, ShopTagLinkResponse.class);
  }

}
