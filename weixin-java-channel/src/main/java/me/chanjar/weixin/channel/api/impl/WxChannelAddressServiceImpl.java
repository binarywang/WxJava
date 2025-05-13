package me.chanjar.weixin.channel.api.impl;


import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Address.ADD_ADDRESS_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Address.DELETE_ADDRESS_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Address.GET_ADDRESS_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Address.LIST_ADDRESS_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Address.UPDATE_ADDRESS_URL;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelAddressService;
import me.chanjar.weixin.channel.bean.address.AddressAddParam;
import me.chanjar.weixin.channel.bean.address.AddressDetail;
import me.chanjar.weixin.channel.bean.address.AddressIdParam;
import me.chanjar.weixin.channel.bean.address.AddressIdResponse;
import me.chanjar.weixin.channel.bean.address.AddressInfoResponse;
import me.chanjar.weixin.channel.bean.address.AddressListParam;
import me.chanjar.weixin.channel.bean.address.AddressListResponse;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 地址管理服务实现
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxChannelAddressServiceImpl implements WxChannelAddressService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxChannelAddressServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
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
  public WxChannelBaseResponse updateAddress(AddressDetail addressDetail) throws WxErrorException {
    AddressAddParam param = new AddressAddParam(addressDetail);
    String resJson = shopService.post(UPDATE_ADDRESS_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse deleteAddress(String addressId) throws WxErrorException {
    AddressIdParam param = new AddressIdParam(addressId);
    String resJson = shopService.post(DELETE_ADDRESS_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }
}
