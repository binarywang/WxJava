package me.chanjar.weixin.channel.api.impl;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.HomePage.*;


import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxStoreHomePageService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.home.background.BackgroundApplyResponse;
import me.chanjar.weixin.channel.bean.home.background.BackgroundGetResponse;
import me.chanjar.weixin.channel.bean.home.banner.BannerApplyParam;
import me.chanjar.weixin.channel.bean.home.banner.BannerApplyResponse;
import me.chanjar.weixin.channel.bean.home.banner.BannerGetResponse;
import me.chanjar.weixin.channel.bean.home.banner.BannerInfo;
import me.chanjar.weixin.channel.bean.home.tree.TreeProductEditInfo;
import me.chanjar.weixin.channel.bean.home.tree.TreeProductEditParam;
import me.chanjar.weixin.channel.bean.home.tree.TreeProductListInfo;
import me.chanjar.weixin.channel.bean.home.tree.TreeProductListParam;
import me.chanjar.weixin.channel.bean.home.tree.TreeProductListResponse;
import me.chanjar.weixin.channel.bean.home.tree.TreeShowGetResponse;
import me.chanjar.weixin.channel.bean.home.tree.TreeShowInfo;
import me.chanjar.weixin.channel.bean.home.tree.TreeShowParam;
import me.chanjar.weixin.channel.bean.home.tree.TreeShowSetResponse;
import me.chanjar.weixin.channel.bean.home.window.WindowProductIndexParam;
import me.chanjar.weixin.channel.bean.home.window.WindowProductListParam;
import me.chanjar.weixin.channel.bean.home.window.WindowProductSetting;
import me.chanjar.weixin.channel.bean.home.window.WindowProductSettingResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店 主页管理相关接口
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxStoreHomePageServiceImpl implements WxStoreHomePageService {

  /** 微信小店服务 */
  private final BaseWxChannelServiceImpl storeService;

  public WxStoreHomePageServiceImpl(BaseWxChannelServiceImpl storeService) {
    this.storeService = storeService;
  }


  @Override
  public WxChannelBaseResponse addTreeProduct(TreeProductEditInfo info) throws WxErrorException {
    TreeProductEditParam param = new TreeProductEditParam(info);
    String resJson = storeService.post(ADD_TREE_PRODUCT_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse delTreeProduct(TreeProductEditInfo info) throws WxErrorException {
    TreeProductEditParam param = new TreeProductEditParam(info);
    String resJson = storeService.post(DEL_TREE_PRODUCT_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public TreeProductListResponse getTreeProductList(TreeProductListInfo info) throws WxErrorException {
    TreeProductListParam param = new TreeProductListParam(info);
    String resJson = storeService.post(LIST_TREE_PRODUCT_URL, param);
    return ResponseUtils.decode(resJson, TreeProductListResponse.class);
  }

  @Override
  public TreeShowSetResponse setShowTree(TreeShowInfo info) throws WxErrorException {
    TreeShowParam param = new TreeShowParam(info);
    String resJson = storeService.post(SET_SHOW_TREE_URL, param);
    return ResponseUtils.decode(resJson, TreeShowSetResponse.class);
  }

  @Override
  public TreeShowGetResponse getShowTree() throws WxErrorException {
    String resJson = storeService.post(GET_SHOW_TREE_URL, "");
    return ResponseUtils.decode(resJson, TreeShowGetResponse.class);
  }

  @Override
  public WindowProductSettingResponse listWindowProduct(Integer pageSize, String nextKey) throws WxErrorException {
    WindowProductListParam param = new WindowProductListParam(pageSize, nextKey);
    String resJson = storeService.post(LIST_WINDOW_PRODUCT_URL, param);
    return ResponseUtils.decode(resJson, WindowProductSettingResponse.class);
  }

  @Override
  public WxChannelBaseResponse reorderWindowProduct(String productId, Integer indexNum) throws WxErrorException {
    WindowProductIndexParam param = new WindowProductIndexParam(productId, indexNum);
    String resJson = storeService.post(REORDER_WINDOW_PRODUCT_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse hideWindowProduct(String productId, Integer setHide) throws WxErrorException {
    WindowProductSetting param = new WindowProductSetting();
    param.setProductId(productId);
    param.setSetHide(setHide);
    String resJson = storeService.post(HIDE_WINDOW_PRODUCT_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse topWindowProduct(String productId, Integer setTop) throws WxErrorException {
    WindowProductSetting param = new WindowProductSetting();
    param.setProductId(productId);
    param.setSetTop(setTop);
    String resJson = storeService.post(TOP_WINDOW_PRODUCT_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public BackgroundApplyResponse applyBackground(String imgUrl) throws WxErrorException {
    String paramJson = "{\"img_url\":\"" + imgUrl + "\"}";
    String resJson = storeService.post(APPLY_BACKGROUND_URL, paramJson);
    return ResponseUtils.decode(resJson, BackgroundApplyResponse.class);
  }

  @Override
  public BackgroundGetResponse getBackground() throws WxErrorException {
    String resJson = storeService.post(GET_BACKGROUND_URL, "");
    return ResponseUtils.decode(resJson, BackgroundGetResponse.class);
  }

  @Override
  public WxChannelBaseResponse cancelBackground(Integer applyId) throws WxErrorException {
    String paramJson = "{\"apply_id\":" + applyId + "}";
    String resJson = storeService.post(CANCEL_BACKGROUND_URL, paramJson);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse removeBackground() throws WxErrorException {
    String resJson = storeService.post(REMOVE_BACKGROUND_URL, "");
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public BannerApplyResponse applyBanner(BannerInfo info) throws WxErrorException {
    BannerApplyParam param = new BannerApplyParam(info);
    String resJson = storeService.post(APPLY_BANNER_URL, param);
    return ResponseUtils.decode(resJson, BannerApplyResponse.class);
  }

  @Override
  public BannerGetResponse getBanner() throws WxErrorException {
    String resJson = storeService.post(GET_BANNER_URL, "");
    return ResponseUtils.decode(resJson, BannerGetResponse.class);
  }

  @Override
  public WxChannelBaseResponse cancelBanner(Integer applyId) throws WxErrorException {
    String paramJson = "{\"apply_id\":" + applyId + "}";
    String resJson = storeService.post(CANCEL_BANNER_URL, paramJson);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse removeBanner() throws WxErrorException {
    String resJson = storeService.post(REMOVE_BANNER_URL, "");
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }
}
