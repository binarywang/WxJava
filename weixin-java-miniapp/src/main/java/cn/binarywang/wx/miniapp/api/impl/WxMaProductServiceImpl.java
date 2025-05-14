package cn.binarywang.wx.miniapp.api.impl;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.OTHER.GET_BRAND;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.OTHER.GET_CATEGORY;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.OTHER.GET_FREIGHT_TEMPLATE;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.OTHER.IMG_UPLOAD;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Sku.PRODUCT_SKU_LIST;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Sku.PRODUCT_ADD_SKU_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Sku.PRODUCT_BATCH_ADD_SKU_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Sku.PRODUCT_DEL_SKU_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Sku.PRODUCT_UPDATE_SKU_PRICE_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Sku.PRODUCT_UPDATE_SKU_STOCK_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Sku.PRODUCT_UPDATE_SKU_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Spu.PRODUCT_SPU_ADD_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Spu.PRODUCT_SPU_DELISTING_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Spu.PRODUCT_SPU_DEL_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Spu.PRODUCT_SPU_GET_LIST_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Spu.PRODUCT_SPU_GET_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Spu.PRODUCT_SPU_LISTING_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Spu.PRODUCT_SPU_UPDATE_URL;
import static me.chanjar.weixin.common.api.WxConsts.ERR_CODE;

import cn.binarywang.wx.miniapp.api.WxMaProductService;
import cn.binarywang.wx.miniapp.api.WxMaService;
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
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.result.WxMinishopImageUploadResult;
import me.chanjar.weixin.common.util.http.MinishopUploadRequestExecutor;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.GsonParser;

/**
 * @author boris
 */
@RequiredArgsConstructor
@Slf4j
public class WxMaProductServiceImpl implements WxMaProductService {
  private final WxMaService wxMaService;

  @Override
  public WxMinishopImageUploadResult uploadImg(File file, Integer respType, Integer width,
    Integer height) throws WxErrorException {
    String url = IMG_UPLOAD + "?upload_type=0" + "&height=" + height + "&width=" + width + "&resp_type=" + respType;
    WxMinishopImageUploadResult result = this.wxMaService.execute(
      MinishopUploadRequestExecutor.create(this.wxMaService.getRequestHttp()), url, file);
    return result;
  }

  @Override
  public WxMinishopImageUploadResult uploadImg(String imgUrl, Integer respType) throws WxErrorException {
    JsonObject jsonObject = GsonHelper.buildJsonObject("img_url", imgUrl);
    String url = IMG_UPLOAD + "?upload_type=1" + "&resp_type=" + respType;
    String response = this.wxMaService.post(url, jsonObject);
    JsonObject respObj = GsonParser.parse(response);

    if (respObj.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(response, WxType.MiniApp));
    }

    return WxMinishopImageUploadResult.fromJson(response);
  }

  @Override
  public WxMinishopGetCategoryResponse getCategory(Integer fCatId) throws WxErrorException {
    JsonObject jsonObject = GsonHelper.buildJsonObject("f_cat_id", fCatId);
    String response = this.wxMaService.post(GET_CATEGORY, jsonObject);
    JsonObject respObj = GsonParser.parse(response);

    if (respObj.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(response, WxType.MiniApp));
    }

    return WxMaGsonBuilder.create().fromJson(response, WxMinishopGetCategoryResponse.class);
  }

  @Override
  public WxMinishopGetBrandResponse getBrand() throws WxErrorException {
    String response = this.wxMaService.post(GET_BRAND, new Object());
    JsonObject respObj = GsonParser.parse(response);

    if (respObj.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(response, WxType.MiniApp));
    }

    return WxMaGsonBuilder.create().fromJson(response, WxMinishopGetBrandResponse.class);
  }

  @Override
  public WxMinishopGetFrightTemplateResponse getFreightTemplate() throws WxErrorException {
    String response = this.wxMaService.post(GET_FREIGHT_TEMPLATE, new Object());
    JsonObject respObj = GsonParser.parse(response);

    if (respObj.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(response, WxType.MiniApp));
    }

    return WxMaGsonBuilder.create().fromJson(response, WxMinishopGetFrightTemplateResponse.class);
  }

  @Override
  public WxMinishopResult<WxMinishopAddGoodsSpuData> addSpu(WxMinishopSpu spu) throws WxErrorException {

    String response = this.wxMaService.post(PRODUCT_SPU_ADD_URL, spu);

    JsonObject respObj = GsonParser.parse(response);

    if (respObj.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(response, WxType.MiniApp));
    }
    WxMinishopResult<WxMinishopAddGoodsSpuData> result = new WxMinishopResult<>();
    result.setErrcode(respObj.get(ERR_CODE).getAsInt());
    JsonObject dataObj = respObj.get("data").getAsJsonObject();
    WxMinishopAddGoodsSpuData resultData = new WxMinishopAddGoodsSpuData();
    resultData.setProductId(dataObj.get("product_id").getAsLong());
    resultData.setOutProductId(dataObj.get("out_product_id").getAsString());
    resultData.setCreateTime(dataObj.get("create_time").getAsString());
    result.setData(resultData);
    return result;
  }

  @Override
  public WxMaShopBaseResponse deleteSpu(Integer productId, String outProductId)
    throws WxErrorException {
    String responseContent = this.wxMaService
      .post(PRODUCT_SPU_DEL_URL, GsonHelper.buildJsonObject("product_id", productId,
        "out_product_id", outProductId));
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }

  @Override
  public WxMinishopSpuGetResponse getSpu(Integer productId, String outProductId, Integer needEditSpu)
    throws WxErrorException {
    String response = this.wxMaService
      .post(PRODUCT_SPU_GET_URL, GsonHelper.buildJsonObject("product_id", productId,
        "out_product_id", outProductId, "need_edit_spu", needEditSpu));
    JsonObject jsonObject = GsonParser.parse(response);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(response, WxType.MiniApp));
    }

    return WxMaGsonBuilder.create().fromJson(response, WxMinishopSpuGetResponse.class);
  }

  @Override
  public WxMinishopSpuListResponse getSpuList(WxMaShopSpuPageRequest request)
    throws WxErrorException {
    String responseContent = this.wxMaService.post(PRODUCT_SPU_GET_LIST_URL, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMinishopSpuListResponse.class);
  }

  @Override
  public WxMinishopResult<WxMinishopAddGoodsSpuData> updateSpu(WxMinishopSpu spu) throws WxErrorException {
    String response = this.wxMaService.post(PRODUCT_SPU_UPDATE_URL, spu);

    JsonObject respObj = GsonParser.parse(response);

    if (respObj.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(response, WxType.MiniApp));
    }
    WxMinishopResult<WxMinishopAddGoodsSpuData> result = new WxMinishopResult<>();
    result.setErrcode(respObj.get(ERR_CODE).getAsInt());
    JsonObject dataObj = respObj.get("data").getAsJsonObject();
    WxMinishopAddGoodsSpuData resultData = new WxMinishopAddGoodsSpuData();
    resultData.setProductId(dataObj.get("product_id").getAsLong());
    resultData.setOutProductId(dataObj.get("out_product_id").getAsString());
    resultData.setUpdateTime(dataObj.get("update_time").getAsString());
    result.setData(resultData);
    return result;
  }

  @Override
  public WxMaShopBaseResponse listingSpu(Integer productId, String outProductId)
    throws WxErrorException {
    String responseContent = this.wxMaService
      .post(PRODUCT_SPU_LISTING_URL, GsonHelper.buildJsonObject("product_id", productId,
        "out_product_id", outProductId));
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }

  @Override
  public WxMaShopBaseResponse delistingSpu(Integer productId, String outProductId)
    throws WxErrorException {
    String responseContent = this.wxMaService
      .post(PRODUCT_SPU_DELISTING_URL, GsonHelper.buildJsonObject("product_id", productId,
        "out_product_id", outProductId));
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }

  @Override
  public WxMinishopSkuListResponse getSkuList(Long productId, Integer needRealStock, Integer needEditSku)
    throws WxErrorException {
    String responseContent = this.wxMaService
      .post(PRODUCT_SKU_LIST, GsonHelper.buildJsonObject("product_id", productId,
        "need_edit_sku", needEditSku, "need_real_stock", needRealStock));
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMinishopSkuListResponse.class);
  }

  @Override
  public WxMinishopResult<WxMinishopAddGoodsSkuData> minishiopGoodsAddSku(
    WxMinishopSku sku) throws WxErrorException {
    String response = this.wxMaService
      .post(PRODUCT_ADD_SKU_URL, sku);
    JsonObject jsonObject = GsonParser.parse(response);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(response, WxType.MiniApp));
    }
    WxMinishopResult<WxMinishopAddGoodsSkuData> result = new WxMinishopResult<>();
    result.setErrcode(jsonObject.get(ERR_CODE).getAsInt());
    JsonObject dataObj = jsonObject.get("data").getAsJsonObject();
    WxMinishopAddGoodsSkuData resultData = new WxMinishopAddGoodsSkuData();
    resultData.setSkuId(dataObj.get("sku_id").getAsLong());
    resultData.setCreateTime(dataObj.get("create_time").getAsString());
    result.setData(resultData);
    return result;
  }

  @Override
  public WxMinishopResult<List<WxMinishopAddGoodsSkuData>> minishopGoodsBatchAddSku(
    List<WxMinishopSku> skuList) throws WxErrorException {
    String response = this.wxMaService
      .post(PRODUCT_BATCH_ADD_SKU_URL, GsonHelper.buildJsonObject("skus", skuList));
    JsonObject jsonObject = GsonParser.parse(response);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(response, WxType.MiniApp));
    }

    WxMinishopResult<List<WxMinishopAddGoodsSkuData>> result = new WxMinishopResult<>();
    result.setErrcode(jsonObject.get(ERR_CODE).getAsInt());
    JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();
    List<WxMinishopAddGoodsSkuData> skuData = new ArrayList<>();
    for (JsonElement jsonElement : jsonArray) {
      JsonObject element = jsonElement.getAsJsonObject();
      WxMinishopAddGoodsSkuData resultData = new WxMinishopAddGoodsSkuData();
      resultData.setSkuId(element.get("sku_id").getAsLong());
      resultData.setOutSkuId(element.get("out_sku_id").getAsString());
      resultData.setCreateTime(element.get("create_time").getAsString());
      skuData.add(resultData);
    }
    result.setData(skuData);
    return result;
  }

  @Override
  public WxMaShopBaseResponse minishopGoodsDelSku(Long productId, Long outProductId,
    String outSkuId, Long skuId) throws WxErrorException {
    String response = this.wxMaService
      .post(PRODUCT_DEL_SKU_URL, GsonHelper.buildJsonObject("product_id", productId,
        "out_product_id", outProductId, "out_sku_id", outSkuId, "sku_id", skuId));
    JsonObject jsonObject = GsonParser.parse(response);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(response, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(response, WxMaShopBaseResponse.class);
  }

  @Override
  public WxMinishopResult<WxMinishopUpdateGoodsSkuData> minishopGoodsUpdateSku(
    WxMinishopSku sku) throws WxErrorException {
    String response = this.wxMaService
      .post(PRODUCT_UPDATE_SKU_URL, sku);
    JsonObject jsonObject = GsonParser.parse(response);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(response, WxType.MiniApp));
    }
    WxMinishopResult<WxMinishopUpdateGoodsSkuData> result = new WxMinishopResult<>();
    result.setErrcode(jsonObject.get(ERR_CODE).getAsInt());
    JsonObject dataObj = jsonObject.get("data").getAsJsonObject();
    WxMinishopUpdateGoodsSkuData resultData = new WxMinishopUpdateGoodsSkuData();
    resultData.setUpdateTime(dataObj.get("update_time").getAsString());
    result.setData(resultData);
    return result;
  }

  @Override
  public WxMinishopResult<WxMinishopUpdateGoodsSkuData> minishopGoodsUpdateSkuPrice(
    Long productId, String outProductId, String outSkuId, Long skuId, Long salePrice,
    Long marketPrice) throws WxErrorException {
    String response = this.wxMaService
      .post(PRODUCT_UPDATE_SKU_PRICE_URL, GsonHelper.buildJsonObject(
        "product_id", productId, "out_product_id", outProductId,
        "sku_id", skuId, "out_sku_id", outSkuId, "sale_price", salePrice, "market_price", marketPrice));
    JsonObject jsonObject = GsonParser.parse(response);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(response, WxType.MiniApp));
    }

    WxMinishopResult<WxMinishopUpdateGoodsSkuData> result = new WxMinishopResult<>();
    result.setErrcode(jsonObject.get(ERR_CODE).getAsInt());
    JsonObject dataObj = jsonObject.get("data").getAsJsonObject();
    WxMinishopUpdateGoodsSkuData resultData = new WxMinishopUpdateGoodsSkuData();
    resultData.setUpdateTime(dataObj.get("update_time").getAsString());
    result.setData(resultData);
    return result;
  }

  @Override
  public WxMinishopResult<WxMinishopUpdateGoodsSkuData> minishopGoodsUpdateSkuStock(
    Long productId, String outProductId, String outSkuId, Long skuId, Integer type,
    Integer stockNum) throws WxErrorException {
    String response = this.wxMaService
      .post(PRODUCT_UPDATE_SKU_STOCK_URL, GsonHelper.buildJsonObject(
        "product_id", productId, "out_product_id", outProductId,
        "sku_id", skuId, "out_sku_id", outSkuId, "type", type, "stock_num", stockNum));
    JsonObject jsonObject = GsonParser.parse(response);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(response, WxType.MiniApp));
    }

    WxMinishopResult<WxMinishopUpdateGoodsSkuData> result = new WxMinishopResult<>();
    result.setErrcode(jsonObject.get(ERR_CODE).getAsInt());
    JsonObject dataObj = jsonObject.get("data").getAsJsonObject();
    WxMinishopUpdateGoodsSkuData resultData = new WxMinishopUpdateGoodsSkuData();
    resultData.setUpdateTime(dataObj.get("update_time").getAsString());
    result.setData(resultData);
    return result;
  }



}
