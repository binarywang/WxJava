package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaLiveGoodsService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.live.WxMaLiveGoodInfo;
import cn.binarywang.wx.miniapp.bean.live.WxMaLiveResult;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.GsonParser;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Broadcast.Goods.*;

/**
 * <pre>
 *  Created by lipengjun on 2020/6/29.
 * </pre>
 *
 * @author <a href="https://github.com/lipengjun92">lipengjun (939961241@qq.com)</a>
 */
@RequiredArgsConstructor
public class WxMaLiveGoodsServiceImpl implements WxMaLiveGoodsService {
  private final WxMaService wxMaService;

  @Override
  public WxMaLiveResult addGoods(WxMaLiveGoodInfo goods) throws WxErrorException {
    return WxMaLiveResult.fromJson(this.wxMaService.post(ADD_GOODS,
      GsonHelper.buildJsonObject("goodsInfo", goods)));
  }

  @Override
  public boolean resetAudit(Integer auditId, Integer goodsId) throws WxErrorException {
    this.wxMaService.post(RESET_AUDIT_GOODS,
      GsonHelper.buildJsonObject("auditId", auditId, "goodsId", goodsId));
    return true;
  }

  @Override
  public String auditGoods(Integer goodsId) throws WxErrorException {
    String responseContent = this.wxMaService.post(AUDIT_GOODS,
      GsonHelper.buildJsonObject("goodsId", goodsId));
    return GsonParser.parse(responseContent).get("auditId").getAsString();
  }

  @Override
  public boolean deleteGoods(Integer goodsId) throws WxErrorException {
    this.wxMaService.post(DELETE_GOODS, GsonHelper.buildJsonObject("goodsId", goodsId));
    return true;
  }

  @Override
  public boolean updateGoods(WxMaLiveGoodInfo goods) throws WxErrorException {
    Map<String, Object> map = new HashMap<>(2);
    map.put("goodsInfo", goods);
    this.wxMaService.post(UPDATE_GOODS, WxMaGsonBuilder.create().toJson(map));
    return true;
  }

  @Override
  public WxMaLiveResult getGoodsWareHouse(List<Integer> goodsIds) throws WxErrorException {
    Map<String, Object> map = new HashMap<>(2);
    map.put("goods_ids", goodsIds);
    String responseContent = this.wxMaService.post(GET_GOODS_WARE_HOUSE, WxMaGsonBuilder.create().toJson(map));
    return WxMaLiveResult.fromJson(responseContent);
  }

  @Override
  public WxMaLiveResult getApprovedGoods(Integer offset, Integer limit, Integer status) throws WxErrorException {
    ImmutableMap<String, ? extends Serializable> params = ImmutableMap.of("status", status, "offset", offset, "limit", limit);
    String responseContent = wxMaService.get(GET_APPROVED_GOODS, Joiner.on("&").withKeyValueSeparator("=").join(params));
    JsonObject jsonObject = GsonParser.parse(responseContent);
    JsonArray goodsArr = jsonObject.getAsJsonArray("goods");
    if (!goodsArr.isEmpty()) {
      for (int i = 0; i < goodsArr.size(); i++) {
        // 接口返回key是驼峰
        JsonObject goods = (JsonObject) goodsArr.get(i);
        goods.addProperty("goods_id", goods.get("goodsId").getAsInt());
        goods.addProperty("cover_img_url", goods.get("coverImgUrl").getAsString());
        goods.addProperty("price_type", goods.get("priceType").getAsInt());
        goods.addProperty("third_party_tag", goods.get("thirdPartyTag").getAsInt());
        goods.addProperty("audit_status", status);
      }
    }
    return WxMaLiveResult.fromJson(jsonObject.toString());
  }

  @Override
  public boolean setKey(List<String> goodsKey) throws WxErrorException {
    Map<String, Object> map = new HashMap<>(1);
    map.put("goodsKey", goodsKey);
    this.wxMaService.post(SET_KEY, WxMaGsonBuilder.create().toJson(map));
    return true;
  }

  @Override
  public List<String> getKey() throws WxErrorException {
    String responseContent = this.wxMaService.get(GET_KEY, null);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    boolean vendorGoodsKey = jsonObject.has("vendorGoodsKey");
    if (vendorGoodsKey) {
      return WxMaGsonBuilder.create().fromJson(jsonObject.getAsJsonArray("vendorGoodsKey"),
              new TypeToken<List<String>>() {
              }.getType());
    } else {
      return null;
    }
  }

}
