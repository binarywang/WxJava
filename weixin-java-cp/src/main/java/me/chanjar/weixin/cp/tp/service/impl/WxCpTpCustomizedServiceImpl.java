package me.chanjar.weixin.cp.tp.service.impl;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpTpCustomizedAppDetail;
import me.chanjar.weixin.cp.bean.WxCpTpTemplateList;
import me.chanjar.weixin.cp.tp.service.WxCpTpCustomizedService;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import org.apache.commons.lang3.StringUtils;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Tp.GET_CUSTOMIZED_APP_DETAIL;
import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Tp.GET_TEMPLATE_LIST;

/**
 * 企业微信第三方应用 - 代开发相关接口实现.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2026-01-14
 */
@RequiredArgsConstructor
public class WxCpTpCustomizedServiceImpl implements WxCpTpCustomizedService {

  private final WxCpTpService mainService;

  @Override
  public WxCpTpTemplateList getTemplateList() throws WxErrorException {
    String url = this.mainService.getWxCpTpConfigStorage().getApiUrl(GET_TEMPLATE_LIST);
    String responseText = this.mainService.get(url + "?provider_access_token="
      + this.mainService.getWxCpProviderToken(), null, true);
    return WxCpTpTemplateList.fromJson(responseText);
  }

  @Override
  public WxCpTpCustomizedAppDetail getCustomizedAppDetail(String authCorpId, Integer agentId) throws WxErrorException {
    String url = this.mainService.getWxCpTpConfigStorage().getApiUrl(GET_CUSTOMIZED_APP_DETAIL);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("auth_corpid", authCorpId);
    if (agentId != null) {
      jsonObject.addProperty("agentid", agentId);
    }
    String responseText = this.mainService.post(url + "?provider_access_token="
      + this.mainService.getWxCpProviderToken(), jsonObject.toString(), true);
    return WxCpTpCustomizedAppDetail.fromJson(responseText);
  }

}
