package me.chanjar.weixin.channel.api;


import me.chanjar.weixin.channel.bean.audit.AuditApplyResponse;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.brand.Brand;
import me.chanjar.weixin.channel.bean.brand.BrandApplyListResponse;
import me.chanjar.weixin.channel.bean.brand.BrandInfoResponse;
import me.chanjar.weixin.channel.bean.brand.BrandListResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 品牌服务接口
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Deprecated
public interface WxChannelBrandService {

  /**
   * 获取品牌库列表
   *
   * @param pageSize 每页数量(默认10, 不超过50)
   * @param nextKey  由上次请求返回, 记录翻页的上下文, 传入时会从上次返回的结果往后翻一页, 不传默认拉取第一页数据
   * @return 品牌库列表
   *
   * @throws WxErrorException 异常
   */
  BrandListResponse listAllBrand(Integer pageSize, String nextKey) throws WxErrorException;

  /**
   * 新增品牌资质
   *
   * @param brand 品牌参数
   * @return 审核id
   *
   * @throws WxErrorException 异常
   */
  AuditApplyResponse addBrandApply(Brand brand) throws WxErrorException;

  /**
   * 修改品牌资质
   *
   * @param brand 品牌参数
   * @return 审核id
   *
   * @throws WxErrorException 异常
   */
  AuditApplyResponse updateBrandApply(Brand brand) throws WxErrorException;

  /**
   * 撤回品牌资质审核
   *
   * @param brandId 品牌id
   * @param auditId 审核id
   * @return 审核id
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse cancelBrandApply(String brandId, String auditId) throws WxErrorException;

  /**
   * 删除品牌资质
   *
   * @param brandId 品牌id
   * @return 结果
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse deleteBrandApply(String brandId) throws WxErrorException;

  /**
   * 获取品牌资质申请详情
   *
   * @param brandId 品牌id
   * @return 品牌信息
   *
   * @throws WxErrorException 异常
   */
  BrandInfoResponse getBrandApply(String brandId) throws WxErrorException;

  /**
   * 获取品牌资质申请列表
   *
   * @param pageSize 每页数量(默认10, 不超过50)
   * @param nextKey  由上次请求返回, 记录翻页的上下文, 传入时会从上次返回的结果往后翻一页, 不传默认拉取第一页数据
   * @param status   审核单状态, 不填默认拉全部商品
   * @return 品牌列表
   *
   * @throws WxErrorException 异常
   */
  BrandApplyListResponse listBrandApply(Integer pageSize, String nextKey, Integer status) throws WxErrorException;

  /**
   * 获取生效中的品牌资质列表
   *
   * @param pageSize 每页数量(默认10, 不超过50)
   * @param nextKey  由上次请求返回, 记录翻页的上下文, 传入时会从上次返回的结果往后翻一页, 不传默认拉取第一页数据
   * @return 品牌列表
   *
   * @throws WxErrorException 异常
   */
  BrandApplyListResponse listValidBrandApply(Integer pageSize, String nextKey) throws WxErrorException;
}
