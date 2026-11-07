package com.binarywang.wxjava.store.api.impl;


import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Brand.ADD_BRAND_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Brand.ALL_BRAND_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Brand.CANCEL_BRAND_AUDIT_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Brand.DELETE_BRAND_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Brand.GET_BRAND_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Brand.LIST_BRAND_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Brand.LIST_BRAND_VALID_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Brand.UPDATE_BRAND_URL;

import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.api.WxStoreBrandService;
import com.binarywang.wxjava.store.bean.audit.AuditApplyResponse;
import com.binarywang.wxjava.store.bean.base.StreamPageParam;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import com.binarywang.wxjava.store.bean.brand.Brand;
import com.binarywang.wxjava.store.bean.brand.BrandApplyListResponse;
import com.binarywang.wxjava.store.bean.brand.BrandInfoResponse;
import com.binarywang.wxjava.store.bean.brand.BrandListResponse;
import com.binarywang.wxjava.store.bean.brand.BrandParam;
import com.binarywang.wxjava.store.bean.brand.BrandSearchParam;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店 品牌服务实现
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxStoreBrandServiceImpl implements WxStoreBrandService {

  /** 微信商店服务 */
  private final BaseWxStoreServiceImpl<?, ?> shopService;

  public WxStoreBrandServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService) {
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
  public WxStoreBaseResponse cancelBrandApply(String brandId, String auditId) throws WxErrorException {
    String reqJson = "{\"brand_id\":\"" + brandId + "\",\"audit_id\":\"" + auditId + "\"}";
    String resJson = shopService.post(CANCEL_BRAND_AUDIT_URL, reqJson);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse deleteBrandApply(String brandId) throws WxErrorException {
    String reqJson = "{\"brand_id\":\"" + brandId + "\"}";
    String resJson = shopService.post(DELETE_BRAND_URL, reqJson);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
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
