package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.product.WxMinishopAddGoodsSkuData;
import cn.binarywang.wx.miniapp.bean.product.WxMinishopAddGoodsSpuData;
import cn.binarywang.wx.miniapp.bean.product.WxMinishopGetBrandResponse;
import cn.binarywang.wx.miniapp.bean.product.WxMinishopGetCategoryResponse;
import cn.binarywang.wx.miniapp.bean.product.WxMinishopGetFrightTemplateResponse;
import cn.binarywang.wx.miniapp.bean.product.WxMinishopResult;
import cn.binarywang.wx.miniapp.bean.product.WxMinishopSku;
import cn.binarywang.wx.miniapp.bean.product.WxMinishopSkuListResponse;
import cn.binarywang.wx.miniapp.bean.product.WxMinishopSpu;
import cn.binarywang.wx.miniapp.bean.product.WxMinishopSpuGetResponse;
import cn.binarywang.wx.miniapp.bean.product.WxMinishopSpuListResponse;
import cn.binarywang.wx.miniapp.bean.product.WxMinishopUpdateGoodsSkuData;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopSpuPageRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;

import java.io.File;
import java.util.List;
import me.chanjar.weixin.common.bean.result.WxMinishopImageUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 小程序交易组件-商品服务
 *
 * @author boris
 */
public interface WxMaProductService {

  WxMinishopImageUploadResult uploadImg(File file, Integer respType, Integer width, Integer height) throws WxErrorException;

  WxMinishopImageUploadResult uploadImg(String imgUrl, Integer respType) throws WxErrorException;

  WxMinishopGetCategoryResponse getCategory(Integer fCatId) throws WxErrorException;

  WxMinishopGetBrandResponse getBrand() throws WxErrorException;

  WxMinishopGetFrightTemplateResponse getFreightTemplate() throws WxErrorException;

  WxMinishopResult<WxMinishopAddGoodsSpuData> addSpu(WxMinishopSpu spuInfo) throws WxErrorException;

  WxMaShopBaseResponse deleteSpu(Integer productId, String outProductId) throws WxErrorException;

  WxMinishopSpuGetResponse getSpu(Integer productId, String outProductId, Integer needEditSpu)
    throws WxErrorException;

  WxMinishopSpuListResponse getSpuList(WxMaShopSpuPageRequest request)
    throws WxErrorException;

  WxMinishopResult<WxMinishopAddGoodsSpuData> updateSpu(WxMinishopSpu spuInfo) throws WxErrorException;

  WxMaShopBaseResponse listingSpu(Integer productId, String outProductId)
    throws WxErrorException;

  WxMaShopBaseResponse delistingSpu(Integer productId, String outProductId)
    throws WxErrorException;

  WxMinishopSkuListResponse getSkuList(Long productId, Integer needRealStock, Integer needEditSku)
    throws WxErrorException;

  /**
   * 小商店新增sku信息
   *
   * @param sku
   * @return
   * @throws WxErrorException
   */
  WxMinishopResult<WxMinishopAddGoodsSkuData> minishiopGoodsAddSku(WxMinishopSku sku) throws WxErrorException;


  /**
   * 小商店批量新增sku信息
   *
   * @param skuList
   * @return
   * @throws WxErrorException
   */
  WxMinishopResult<List<WxMinishopAddGoodsSkuData>> minishopGoodsBatchAddSku(List<WxMinishopSku> skuList) throws WxErrorException;


  /**
   * 小商店删除sku消息
   *
   * @param productId
   * @param outProductId
   * @param outSkuId
   * @param skuId
   * @return
   * @throws WxErrorException
   */
  WxMaShopBaseResponse minishopGoodsDelSku(Long productId, Long outProductId, String outSkuId, Long skuId) throws WxErrorException;


  /**
   * 小商店更新sku
   *
   * @param sku
   * @return
   * @throws WxErrorException
   */
  WxMinishopResult<WxMinishopUpdateGoodsSkuData> minishopGoodsUpdateSku(WxMinishopSku sku) throws WxErrorException;


  /**
   * 小商店更新sku价格
   *
   * @param productId
   * @param outProductId
   * @param outSkuId
   * @param skuId
   * @param salePrice
   * @param marketPrice
   * @return
   * @throws WxErrorException
   */
  WxMinishopResult<WxMinishopUpdateGoodsSkuData> minishopGoodsUpdateSkuPrice(Long productId,
    String outProductId, String outSkuId, Long skuId, Long salePrice, Long marketPrice) throws WxErrorException;


  /**
   * 小商店更新sku库存
   *
   * @param productId
   * @param outProductId
   * @param outSkuId
   * @param skuId
   * @param type
   * @param stockNum
   * @return
   * @throws WxErrorException
   */
  WxMinishopResult<WxMinishopUpdateGoodsSkuData> minishopGoodsUpdateSkuStock(Long productId,
    String outProductId, String outSkuId, Long skuId, Integer type, Integer stockNum) throws WxErrorException;

}
