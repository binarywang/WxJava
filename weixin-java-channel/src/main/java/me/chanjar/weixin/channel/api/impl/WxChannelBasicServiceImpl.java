package me.chanjar.weixin.channel.api.impl;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Basics.GET_ADDRESS_CODE;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Basics.GET_IMG_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Basics.GET_SHOP_INFO;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Basics.IMG_UPLOAD_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Basics.UPLOAD_QUALIFICATION_FILE;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelBasicService;
import me.chanjar.weixin.channel.bean.address.AddressCodeResponse;
import me.chanjar.weixin.channel.bean.image.ChannelImageInfo;
import me.chanjar.weixin.channel.bean.image.ChannelImageResponse;
import me.chanjar.weixin.channel.bean.image.QualificationFileResponse;
import me.chanjar.weixin.channel.bean.image.UploadImageResponse;
import me.chanjar.weixin.channel.bean.shop.ShopInfoResponse;
import me.chanjar.weixin.channel.executor.ChannelFileUploadRequestExecutor;
import me.chanjar.weixin.channel.executor.ChannelMediaDownloadRequestExecutor;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxChannelBasicServiceImpl implements WxChannelBasicService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxChannelBasicServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public ShopInfoResponse getShopInfo() throws WxErrorException {
    String resJson = shopService.get(GET_SHOP_INFO, null);
    return ResponseUtils.decode(resJson, ShopInfoResponse.class);
  }

  @Override
  public ChannelImageInfo uploadImg(int respType, String imgUrl) throws WxErrorException {
    String url = IMG_UPLOAD_URL + "?upload_type=1&resp_type=" + respType;
    String reqJson = "{\"img_url\":\"" + imgUrl + "\"}";
    String resJson = shopService.post(url, reqJson);
    UploadImageResponse response = ResponseUtils.decode(resJson, UploadImageResponse.class);
    return response.getImgInfo();
  }

  @Override
  public ChannelImageInfo uploadImg(int respType, File file, int height, int width) throws WxErrorException {
    String url = IMG_UPLOAD_URL + "?upload_type=0&resp_type=" + respType + "&height=" + height + "&width=" + width;
    RequestExecutor<String, File> executor = ChannelFileUploadRequestExecutor.create(shopService);
    String resJson = shopService.execute(executor, url, file);
    UploadImageResponse response = ResponseUtils.decode(resJson, UploadImageResponse.class);
    return response.getImgInfo();
  }

  @Override
  public QualificationFileResponse uploadQualificationFile(File file) throws WxErrorException {
    RequestExecutor<String, File> executor = ChannelFileUploadRequestExecutor.create(shopService);
    String resJson = shopService.execute(executor, UPLOAD_QUALIFICATION_FILE, file);
    return ResponseUtils.decode(resJson, QualificationFileResponse.class);
  }

  @Override
  public ChannelImageResponse getImg(String mediaId) throws WxErrorException {
    String appId = shopService.getConfig().getAppid();
    ChannelImageResponse rs;
    try {
      String url = GET_IMG_URL + "?media_id=" + mediaId;
      RequestExecutor<ChannelImageResponse, String> executor = ChannelMediaDownloadRequestExecutor.create(shopService,
        Files.createTempDirectory("wxjava-channel-" + appId).toFile());
      rs = shopService.execute(executor, url, null);
    } catch (IOException e) {
      throw new WxErrorException(WxError.builder().errorMsg(e.getMessage()).build(), e);
    }
    if (rs == null) {
      rs = ResponseUtils.internalError(ChannelImageResponse.class);
    }
    return rs;
  }

  @Override
  public AddressCodeResponse getAddressCode(Integer code) throws WxErrorException {
    String reqJson = "{\"addr_code\": " + code + "}";
    String resJson = shopService.post(GET_ADDRESS_CODE, reqJson);
    return ResponseUtils.decode(resJson, AddressCodeResponse.class);
  }

}
