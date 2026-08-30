package me.chanjar.weixin.channel.api;


import me.chanjar.weixin.channel.bean.address.AddressDetail;
import me.chanjar.weixin.channel.bean.address.AddressIdResponse;
import me.chanjar.weixin.channel.bean.address.AddressInfoResponse;
import me.chanjar.weixin.channel.bean.address.AddressListResponse;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 地址管理服务
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Deprecated
public interface WxChannelAddressService {

  /**
   * 获取地址列表
   *
   * @param offset 起始位置
   * @param limit  拉取个数
   * @return 列表
   *
   * @throws WxErrorException 异常
   */
  AddressListResponse listAddress(Integer offset, Integer limit) throws WxErrorException;

  /**
   * 获取地址详情
   *
   * @param addressId 地址id
   * @return 地址详情
   *
   * @throws WxErrorException 异常
   */
  AddressInfoResponse getAddress(String addressId) throws WxErrorException;

  /**
   * 添加地址
   *
   * @param addressDetail 地址
   * @return AddressIdResponse
   *
   * @throws WxErrorException 异常
   */
  AddressIdResponse addAddress(AddressDetail addressDetail) throws WxErrorException;

  /**
   * 更新地址
   *
   * @param addressDetail 地址
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse updateAddress(AddressDetail addressDetail) throws WxErrorException;

  /**
   * 删除地址
   *
   * @param addressId 地址id
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse deleteAddress(String addressId) throws WxErrorException;
}
