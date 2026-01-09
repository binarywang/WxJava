package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaExpressDeliveryReturnService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.express.request.WxMaExpressDeliveryReturnAddRequest;
import cn.binarywang.wx.miniapp.bean.express.result.WxMaExpressReturnInfoResult;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;

@RequiredArgsConstructor
public class WxMaExpressDeliveryReturnServiceImpl implements WxMaExpressDeliveryReturnService {
    private final WxMaService service;

    @Override
    public WxMaExpressReturnInfoResult addDeliveryReturn(WxMaExpressDeliveryReturnAddRequest wxMaExpressDeliveryReturnAddRequest) throws WxErrorException {
        String result = this.service.post(ADD_DELIVERY_RETURN_URL, wxMaExpressDeliveryReturnAddRequest.toJson());
        return WxMaExpressReturnInfoResult.fromJson(result);
    }

    @Override
    public WxMaExpressReturnInfoResult getDeliveryReturn(String returnId) throws WxErrorException {
        JsonObject param = new JsonObject();
        param.addProperty("return_id", returnId);
        String result = this.service.post(GET_DELIVERY_RETURN_URL, param);
        return WxMaExpressReturnInfoResult.fromJson(result);
    }

    @Override
    public WxMaExpressReturnInfoResult unbindDeliveryReturn(String returnId) throws WxErrorException {
        JsonObject param = new JsonObject();
        param.addProperty("return_id", returnId);
        String result = this.service.post(UNBIND_DELIVERY_RETURN_URL, param);
        return WxMaExpressReturnInfoResult.fromJson(result);
    }
}
