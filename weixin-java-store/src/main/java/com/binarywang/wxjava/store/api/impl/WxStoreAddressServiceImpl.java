package com.binarywang.wxjava.store.api.impl;


import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Address.ADD_ADDRESS_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Address.DELETE_ADDRESS_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Address.GET_ADDRESS_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Address.LIST_ADDRESS_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Address.UPDATE_ADDRESS_URL;

import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.api.WxStoreAddressService;
import com.binarywang.wxjava.store.bean.address.AddressAddParam;
import com.binarywang.wxjava.store.bean.address.AddressDetail;
import com.binarywang.wxjava.store.bean.address.AddressIdParam;
import com.binarywang.wxjava.store.bean.address.AddressIdResponse;
import com.binarywang.wxjava.store.bean.address.AddressInfoResponse;
import com.binarywang.wxjava.store.bean.address.AddressListParam;
import com.binarywang.wxjava.store.bean.address.AddressListResponse;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店 地址管理服务实现
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxStoreAddressServiceImpl implements WxStoreAddressService {

  /** 微信商店服务 */
  private final BaseWxStoreServiceImpl<?, ?> shopService;

  public WxStoreAddressServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public AddressListResponse listAddress(Integer offset, Integer limit) throws WxErrorException {
    AddressListParam param = new AddressListParam(offset, limit);
    String resJson = shopService.post(LIST_ADDRESS_URL, param);
    return ResponseUtils.decode(resJson, AddressListResponse.class);
  }

  @Override
  public AddressInfoResponse getAddress(String addressId) throws WxErrorException {
    AddressIdParam param = new AddressIdParam(addressId);
    String resJson = shopService.post(GET_ADDRESS_URL, param);
    return ResponseUtils.decode(resJson, AddressInfoResponse.class);
  }

  @Override
  public AddressIdResponse addAddress(AddressDetail addressDetail) throws WxErrorException {
    AddressAddParam param = new AddressAddParam(addressDetail);
    String resJson = shopService.post(ADD_ADDRESS_URL, param);
    return ResponseUtils.decode(resJson, AddressIdResponse.class);
  }

  @Override
  public WxStoreBaseResponse updateAddress(AddressDetail addressDetail) throws WxErrorException {
    AddressAddParam param = new AddressAddParam(addressDetail);
    String resJson = shopService.post(UPDATE_ADDRESS_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse deleteAddress(String addressId) throws WxErrorException {
    AddressIdParam param = new AddressIdParam(addressId);
    String resJson = shopService.post(DELETE_ADDRESS_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }
}
