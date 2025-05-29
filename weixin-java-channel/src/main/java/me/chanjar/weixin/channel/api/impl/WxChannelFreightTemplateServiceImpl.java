package me.chanjar.weixin.channel.api.impl;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.FreightTemplate.ADD_TEMPLATE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.FreightTemplate.GET_TEMPLATE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.FreightTemplate.LIST_TEMPLATE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.FreightTemplate.UPDATE_TEMPLATE_URL;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelFreightTemplateService;
import me.chanjar.weixin.channel.bean.freight.FreightTemplate;
import me.chanjar.weixin.channel.bean.freight.TemplateAddParam;
import me.chanjar.weixin.channel.bean.freight.TemplateIdResponse;
import me.chanjar.weixin.channel.bean.freight.TemplateInfoResponse;
import me.chanjar.weixin.channel.bean.freight.TemplateListParam;
import me.chanjar.weixin.channel.bean.freight.TemplateListResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 运费模板服务实现
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxChannelFreightTemplateServiceImpl implements WxChannelFreightTemplateService {
    /** 微信商店服务 */
    private final BaseWxChannelServiceImpl<?, ?> shopService;

    public WxChannelFreightTemplateServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
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
