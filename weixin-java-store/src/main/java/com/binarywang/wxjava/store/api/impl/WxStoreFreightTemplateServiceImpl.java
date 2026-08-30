package com.binarywang.wxjava.store.api.impl;

import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.FreightTemplate.ADD_TEMPLATE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.FreightTemplate.GET_TEMPLATE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.FreightTemplate.LIST_TEMPLATE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.FreightTemplate.UPDATE_TEMPLATE_URL;

import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.api.WxStoreFreightTemplateService;
import com.binarywang.wxjava.store.bean.freight.FreightTemplate;
import com.binarywang.wxjava.store.bean.freight.TemplateAddParam;
import com.binarywang.wxjava.store.bean.freight.TemplateIdResponse;
import com.binarywang.wxjava.store.bean.freight.TemplateInfoResponse;
import com.binarywang.wxjava.store.bean.freight.TemplateListParam;
import com.binarywang.wxjava.store.bean.freight.TemplateListResponse;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店 运费模板服务实现
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxStoreFreightTemplateServiceImpl implements WxStoreFreightTemplateService {
    /** 微信商店服务 */
    private final BaseWxStoreServiceImpl<?, ?> shopService;

    public WxStoreFreightTemplateServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService) {
        this.shopService = shopService;
    }

    @Override
    public TemplateListResponse listTemplate(Integer offset, Integer limit) throws WxErrorException {
      TemplateListParam param = new TemplateListParam(offset, limit);
        String resJson = shopService.post(LIST_TEMPLATE_URL, param);
        return ResponseUtils.decode(resJson, TemplateListResponse.class);

    }

    @Override
    public TemplateInfoResponse getTemplate(String templateId) throws WxErrorException {
      String reqJson = "{\"template_id\": \"" + templateId + "\"}";
        String resJson = shopService.post(GET_TEMPLATE_URL, reqJson);
        return ResponseUtils.decode(resJson, TemplateInfoResponse.class);
    }

    @Override
    public TemplateIdResponse addTemplate(FreightTemplate template) throws WxErrorException {
      TemplateAddParam param = new TemplateAddParam(template);
        String resJson = shopService.post(ADD_TEMPLATE_URL, param);
        return ResponseUtils.decode(resJson, TemplateIdResponse.class);
    }

    @Override
    public TemplateIdResponse updateTemplate(FreightTemplate template) throws WxErrorException {
      TemplateAddParam param = new TemplateAddParam(template);
        String resJson = shopService.post(UPDATE_TEMPLATE_URL, param);
        return ResponseUtils.decode(resJson, TemplateIdResponse.class);
    }
}
