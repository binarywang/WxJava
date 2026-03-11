package me.chanjar.weixin.channel.api.impl;


import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Brand.ADD_BRAND_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Brand.ALL_BRAND_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Brand.CANCEL_BRAND_AUDIT_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Brand.DELETE_BRAND_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Brand.GET_BRAND_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Brand.LIST_BRAND_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Brand.LIST_BRAND_VALID_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Brand.UPDATE_BRAND_URL;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelBrandService;
import me.chanjar.weixin.channel.bean.audit.AuditApplyResponse;
import me.chanjar.weixin.channel.bean.base.StreamPageParam;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.brand.Brand;
import me.chanjar.weixin.channel.bean.brand.BrandApplyListResponse;
import me.chanjar.weixin.channel.bean.brand.BrandInfoResponse;
import me.chanjar.weixin.channel.bean.brand.BrandListResponse;
import me.chanjar.weixin.channel.bean.brand.BrandParam;
import me.chanjar.weixin.channel.bean.brand.BrandSearchParam;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 品牌服务实现
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxChannelBrandServiceImpl implements WxChannelBrandService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxChannelBrandServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public BrandListResponse listAllBrand(Integer pageSize, String nextKey) throws WxErrorException {
    StreamPageParam param = new StreamPageParam(pageSize, nextKey);
    String resJson = shopService.post(ALL_BRAND_URL, param);
    return ResponseUtils.decode(resJson, BrandListResponse.class);
  }

  @Override
  public AuditApplyResponse addBrandApply(Brand brand) throws WxErrorException {
    BrandParam param = new BrandParam(brand);
    String resJson = shopService.post(ADD_BRAND_URL, param);
    return ResponseUtils.decode(resJson, AuditApplyResponse.class);
  }

  @Override
  public AuditApplyResponse updateBrandApply(Brand brand) throws WxErrorException {
    BrandParam param = new BrandParam(brand);
    String resJson = shopService.post(UPDATE_BRAND_URL, param);
    return ResponseUtils.decode(resJson, AuditApplyResponse.class);
  }

  @Override
  public WxChannelBaseResponse cancelBrandApply(String brandId, String auditId) throws WxErrorException {
    String reqJson = "{\"brand_id\":\"" + brandId + "\",\"audit_id\":\"" + auditId + "\"}";
    String resJson = shopService.post(CANCEL_BRAND_AUDIT_URL, reqJson);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse deleteBrandApply(String brandId) throws WxErrorException {
    String reqJson = "{\"brand_id\":\"" + brandId + "\"}";
    String resJson = shopService.post(DELETE_BRAND_URL, reqJson);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public BrandInfoResponse getBrandApply(String brandId) throws WxErrorException {
    String reqJson = "{\"brand_id\":\"" + brandId + "\"}";
    String resJson = shopService.post(GET_BRAND_URL, reqJson);
    return ResponseUtils.decode(resJson, BrandInfoResponse.class);
  }

  @Override
  public BrandApplyListResponse listBrandApply(Integer pageSize, String nextKey, Integer status)
    throws WxErrorException {
    BrandSearchParam param = new BrandSearchParam(pageSize, nextKey, status);
    String resJson = shopService.post(LIST_BRAND_URL, param);
    return ResponseUtils.decode(resJson, BrandApplyListResponse.class);
  }

  @Override
  public BrandApplyListResponse listValidBrandApply(Integer pageSize, String nextKey) throws WxErrorException {
    StreamPageParam param = new StreamPageParam(pageSize, nextKey);
    String resJson = shopService.post(LIST_BRAND_VALID_URL, param);
    return ResponseUtils.decode(resJson, BrandApplyListResponse.class);
  }
}
