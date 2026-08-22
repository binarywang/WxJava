package me.chanjar.weixin.channel.api;


import java.util.List;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.limit.LimitTaskAddResponse;
import me.chanjar.weixin.channel.bean.limit.LimitTaskListResponse;
import me.chanjar.weixin.channel.bean.limit.LimitTaskParam;
import me.chanjar.weixin.channel.bean.product.GiftActivityAddResponse;
import me.chanjar.weixin.channel.bean.product.GiftActivityInfo;
import me.chanjar.weixin.channel.bean.product.GiftProductAddResponse;
import me.chanjar.weixin.channel.bean.product.GiftProductGetResponse;
import me.chanjar.weixin.channel.bean.product.GiftProductInfo;
import me.chanjar.weixin.channel.bean.product.GiftProductListParam;
import me.chanjar.weixin.channel.bean.product.GiftProductListResponse;
import me.chanjar.weixin.channel.bean.product.AddProductThirdPartySourceParam;
import me.chanjar.weixin.channel.bean.product.AddProductThirdPartySourceResponse;
import me.chanjar.weixin.channel.bean.product.ExternalProductMappingNewParam;
import me.chanjar.weixin.channel.bean.product.ExternalProductMappingNewResponse;
import me.chanjar.weixin.channel.bean.product.ExternalProductMappingParam;
import me.chanjar.weixin.channel.bean.product.ExternalProductMappingResponse;
import me.chanjar.weixin.channel.bean.product.ProductAuditQuotaResponse;
import me.chanjar.weixin.channel.bean.product.ProductAuditStrategyResponse;
import me.chanjar.weixin.channel.bean.product.ProductAuditStrategySetParam;
import me.chanjar.weixin.channel.bean.product.ProductBrandRecommendParam;
import me.chanjar.weixin.channel.bean.product.ProductBrandRecommendResponse;
import me.chanjar.weixin.channel.bean.product.ProductCategoryClassifyParam;
import me.chanjar.weixin.channel.bean.product.ProductCategoryClassifyResponse;
import me.chanjar.weixin.channel.bean.product.ProductCategoryPreCheckParam;
import me.chanjar.weixin.channel.bean.product.ProductCategoryPreCheckResponse;
import me.chanjar.weixin.channel.bean.product.ProductSchemeParam;
import me.chanjar.weixin.channel.bean.product.ProductSchemeResponse;
import me.chanjar.weixin.channel.bean.product.ProductStockFlowParam;
import me.chanjar.weixin.channel.bean.product.ProductStockFlowResponse;
import me.chanjar.weixin.channel.bean.product.ProductTimingSaleParam;
import me.chanjar.weixin.channel.bean.product.SkuStockBatchResponse;
import me.chanjar.weixin.channel.bean.product.SkuStockResponse;
import me.chanjar.weixin.channel.bean.product.SpuFastInfo;
import me.chanjar.weixin.channel.bean.product.SpuGetResponse;
import me.chanjar.weixin.channel.bean.product.SpuInfo;
import me.chanjar.weixin.channel.bean.product.SpuListResponse;
import me.chanjar.weixin.channel.bean.product.SpuUpdateInfo;
import me.chanjar.weixin.channel.bean.product.SpuUpdateResponse;
import me.chanjar.weixin.channel.bean.product.link.ProductH5UrlResponse;
import me.chanjar.weixin.channel.bean.product.link.ProductQrCodeResponse;
import me.chanjar.weixin.channel.bean.product.link.ProductTagLinkResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 商品服务接口
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @see <a href="https://developers.weixin.qq.com/doc/store/API/product/product_status.html">商品状态流转图</a>
 */
public interface WxChannelProductService {

  /**
   * 添加商品
   *
   * @param info 商品信息
   * @return 返回商品的状态和id
   *
   * @throws WxErrorException 异常
   */
  SpuUpdateResponse addProduct(SpuUpdateInfo info) throws WxErrorException;

  /**
   * 更新商品
   *
   * @param info 商品信息
   * @return 返回商品的状态和id
   *
   * @throws WxErrorException 异常
   */
  SpuUpdateResponse updateProduct(SpuUpdateInfo info) throws WxErrorException;

  /**
   * 添加商品
   *
   * @param info 商品信息
   * @return 返回商品的状态和id
   *
   * @throws WxErrorException 异常
   * @deprecated 请使用 {@link #addProduct(SpuUpdateInfo)}
   */
  @Deprecated
  SpuUpdateResponse addProduct(SpuInfo info) throws WxErrorException;

  /**
   * 更新商品
   *
   * @param info 商品信息
   * @return 返回商品的状态和id
   *
   * @throws WxErrorException 异常
   * @deprecated 请使用 {@link #updateProduct(SpuUpdateInfo)}
   */
  @Deprecated
  SpuUpdateResponse updateProduct(SpuInfo info) throws WxErrorException;

  /**
   * 免审更新商品
   *
   * @param info 商品信息
   * @return 返回商品的状态和id
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse updateProductAuditFree(SpuFastInfo info) throws WxErrorException;

  /**
   * 更新商品库存 （仅对edit_status != 2 的商品适用，其他状态的商品无法通过该接口修改库存）
   *
   * @param productId 内部商品ID
   * @param skuId     内部sku_id
   * @param diffType  修改类型 1增加 2减少 3设置
   *                  建议使用1或2，不建议使用3，因为使用3在高并发场景可能会出现预期外表现
   * @param num       增加、减少或者设置的库存值
   * @return WxChannelBaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse updateStock(String productId, String skuId, Integer diffType, Integer num)
    throws WxErrorException;

  /**
   * 删除商品
   *
   * @param productId 商品ID
   * @return 是否成功
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse deleteProduct(String productId) throws WxErrorException;

  /**
   * 撤回商品审核
   *
   * @param productId 商品ID
   * @return 是否成功
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse cancelProductAudit(String productId) throws WxErrorException;

  /**
   * 获取商品
   *
   * @param productId 商品ID
   * @param dataType  默认取1 1:获取线上数据 2:获取草稿数据 3:同时获取线上和草稿数据（注意：需成功上架后才有线上数据）
   * @return 商品信息
   *
   * @throws WxErrorException 异常
   */
  SpuGetResponse getProduct(String productId, Integer dataType) throws WxErrorException;

  /**
   * 获取商品列表
   *
   * @param pageSize 每页数量（默认10，不超过30）
   * @param nextKey  由上次请求返回，记录翻页的上下文。传入时会从上次返回的结果往后翻一页，不传默认拉取第一页数据。
   * @param status   商品状态，不填默认拉全部商品（不包含回收站） {@link me.chanjar.weixin.channel.enums.SpuStatus}
   * @return List
   *
   * @throws WxErrorException 异常
   */
  SpuListResponse listProduct(Integer pageSize, String nextKey, Integer status) throws WxErrorException;

  /**
   * 上架商品
   *
   * @param productId 商品ID
   * @return 是否成功
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse upProduct(String productId) throws WxErrorException;

  /**
   * 下架商品
   *
   * @param productId 商品ID
   * @return 是否成功
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse downProduct(String productId) throws WxErrorException;

  /**
   * 获取商品实时库存
   *
   * @param productId 商品ID
   * @param skuId     skuId
   * @return SkuStockResponse
   *
   * @throws WxErrorException 异常
   */
  SkuStockResponse getSkuStock(String productId, String skuId) throws WxErrorException;

  /**
   * 批量获取库存信息 （单次请求不能超过50个商品ID）
   *
   * @param productIds 商品ID列表
   * @return 库存信息
   * @throws WxErrorException 异常
   */
  SkuStockBatchResponse getSkuStockBatch(List<String> productIds) throws WxErrorException;

  /**
   * 获取商品H5链接
   *
   * @param productId 商品ID
   * @return 商品H5链接
   * @throws WxErrorException 异常
   */
  ProductH5UrlResponse getProductH5Url(String productId) throws WxErrorException;

  /**
   * 获取商品二维码
   *
   * @param productId 商品ID
   * @return 商品二维码
   * @throws WxErrorException 异常
   */
  ProductQrCodeResponse getProductQrCode(String productId) throws WxErrorException;

  /**
   * 获取商品口令
   *
   * @param productId 商品ID
   * @return 商品口令
   * @throws WxErrorException 异常
   */
  ProductTagLinkResponse getProductTagLink(String productId) throws WxErrorException;

  /**
   * 获取商品的移动应用跳转 scheme 码.
   *
   * @param param 商品 ID、来源 appid、过期时间和附加信息
   * @return 商品跳转 scheme 码
   * @throws WxErrorException 调用微信接口失败
   */
  ProductSchemeResponse getProductScheme(ProductSchemeParam param) throws WxErrorException;

  /**
   * 商品类目推荐.
   *
   * @param param 请求类型、商品标题、主图和可选类目 ID；当请求类型为 2 时必须提供类目 ID
   * @return 推荐类目及店铺经营权限
   * @throws WxErrorException 调用微信接口失败
   */
  ProductCategoryClassifyResponse classifyProductCategory(ProductCategoryClassifyParam param) throws WxErrorException;

  /**
   * 将定时开售商品改为立即开售.
   *
   * @param param 商品 ID 和定时开售任务 ID
   * @return 操作结果
   * @throws WxErrorException 调用微信接口失败
   */
  WxChannelBaseResponse beginTimingSale(ProductTimingSaleParam param) throws WxErrorException;

  /**
   * 取消商品开售.
   *
   * @param productId 商品 ID
   * @return 操作结果
   * @throws WxErrorException 调用微信接口失败
   */
  WxChannelBaseResponse cancelTimingSale(String productId) throws WxErrorException;

  /**
   * 查询站内外商品属性映射.
   *
   * @param param 叶子类目 ID、外部类目和外部属性
   * @return 对应的站内属性及可选属性值
   * @throws WxErrorException 调用微信接口失败
   */
  ExternalProductMappingResponse externalProductMapping(ExternalProductMappingParam param) throws WxErrorException;

  /**
   * 发品前校验店铺类目资质.
   *
   * @param param 待发布商品的叶子类目 ID
   * @return 校验结果和未通过原因
   * @throws WxErrorException 调用微信接口失败
   */
  ProductCategoryPreCheckResponse categoryPreCheck(ProductCategoryPreCheckParam param) throws WxErrorException;

  /**
   * 获取店铺维度的商品上架策略.
   *
   * @return 当前上架策略
   * @throws WxErrorException 调用微信接口失败
   */
  ProductAuditStrategyResponse getProductAuditStrategy() throws WxErrorException;

  /**
   * 设置店铺维度的商品上架策略.
   *
   * @param param 要设置的上架策略
   * @return 操作结果
   * @throws WxErrorException 调用微信接口失败
   */
  WxChannelBaseResponse setProductAuditStrategy(ProductAuditStrategySetParam param) throws WxErrorException;

  /**
   * 获取当前店铺的商品提审限额.
   *
   * @return 提审总额度和新品剩余额度
   * @throws WxErrorException 调用微信接口失败
   */
  ProductAuditQuotaResponse getProductAuditQuota() throws WxErrorException;

  /**
   * 商品属性映射及推荐.
   *
   * @param param 叶子类目、商品标题、主图及可选的外部属性
   * @return 推荐的站内属性
   * @throws WxErrorException 调用微信接口失败
   */
  ExternalProductMappingNewResponse externalProductMappingNew(ExternalProductMappingNewParam param)
    throws WxErrorException;

  /**
   * 根据商品信息推荐店铺已有资质的品牌.
   *
   * @param param 商品叶子类目、标题和图片
   * @return 推荐品牌
   * @throws WxErrorException 调用微信接口失败
   */
  ProductBrandRecommendResponse productBrandRecommend(ProductBrandRecommendParam param) throws WxErrorException;

  /**
   * 新增第三方货源信息.
   *
   * @param param 场景、发布方式、货主及货源商品信息
   * @return 包含第三方货源 ID 的操作结果
   * @throws WxErrorException 调用微信接口失败
   */
  AddProductThirdPartySourceResponse addProductThirdPartySource(AddProductThirdPartySourceParam param)
    throws WxErrorException;

  /**
   * 获取商品库存流水.
   *
   * @param param 商品、SKU、库存类型、时间范围和分页参数；pageSize 必填，stockType 为 1 时 finderId 必填，库存类型非 0 和 1 时 stockTypeId 必填
   * @return 库存流水及下一页标识
   * @throws WxErrorException 调用微信接口失败
   */
  ProductStockFlowResponse getStockFlow(ProductStockFlowParam param) throws WxErrorException;

  /**
   * 添加非卖商品
   *
   * @param info 赠品信息
   * @return 添加赠品响应
   * @throws WxErrorException 异常
   */
  GiftProductAddResponse addGiftProduct(GiftProductInfo info) throws WxErrorException;

  /**
   * 更新非卖商品
   *
   * @param info 赠品信息
   * @return 操作响应
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse updateGiftProduct(GiftProductInfo info) throws WxErrorException;

  /**
   * 在售商品转赠品
   *
   * @param productId 商品ID
   * @return 操作响应
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse setProductAsGift(String productId) throws WxErrorException;

  /**
   * 获取赠品
   *
   * @param productId 赠品商品ID
   * @return 赠品详情响应
   * @throws WxErrorException 异常
   */
  GiftProductGetResponse getGiftProduct(String productId) throws WxErrorException;

  /**
   * 获取赠品列表
   *
   * @param param 查询参数
   * @return 赠品列表
   * @throws WxErrorException 异常
   */
  GiftProductListResponse listGiftProduct(GiftProductListParam param) throws WxErrorException;

  /**
   * 更新赠品库存
   *
   * @param productId 赠品商品ID
   * @param skuId     赠品sku_id
   * @param diffType  修改类型 1增加 2减少 3设置
   *                  建议使用1或2，不建议使用3，因为使用3在高并发场景可能会出现预期外表现
   * @param num       增加、减少或者设置的库存值
   * @return 操作响应
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse updateGiftStock(String productId, String skuId, Integer diffType, Integer num)
    throws WxErrorException;

  /**
   * 创建赠品活动
   *
   * @param info 活动信息
   * @return 创建赠品活动响应
   * @throws WxErrorException 异常
   */
  GiftActivityAddResponse addGiftActivity(GiftActivityInfo info) throws WxErrorException;

  /**
   * 删除赠品活动
   *
   * @param activityId 活动ID
   * @return 操作响应
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse deleteGiftActivity(String activityId) throws WxErrorException;

  /**
   * 停止赠品活动
   *
   * @param activityId 活动ID
   * @return 操作响应
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse stopGiftActivity(String activityId) throws WxErrorException;

  /**
   * 添加限时抢购任务
   *
   * @param param 限时抢购任务
   * @return LimitTaskAddResponse
   *
   * @throws WxErrorException 异常
   */
  LimitTaskAddResponse addLimitTask(LimitTaskParam param) throws WxErrorException;

  /**
   * 拉取限时抢购任务列表
   *
   * @param pageSize 每页数量(默认10，不超过50)
   * @param nextKey  由上次请求返回，记录翻页的上下文。传入时会从上次返回的结果往后翻一页，不传默认拉取第一页数据
   * @param status   抢购活动状态
   * @return LimitTaskListResponse
   *
   * @throws WxErrorException 异常
   */
  LimitTaskListResponse listLimitTask(Integer pageSize, String nextKey, Integer status) throws WxErrorException;

  /**
   * 停止限时抢购任务
   *
   * @param taskId 限时抢购任务ID
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse stopLimitTask(String taskId) throws WxErrorException;

  /**
   * 停止限时抢购任务
   *
   * @param taskId 限时抢购任务ID
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse deleteLimitTask(String taskId) throws WxErrorException;
}
