package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.WxCpCorpGroupService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.corpgroup.WxCpCorpGroupCorp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.CorpGroup.LIST_SHARE_APP_INFO;

/**
 * 企业互联相关接口实现类
 *
 * @author libo <422423229@qq.com>
 * Created on 27/2/2023 9:57 PM
 */
@RequiredArgsConstructor
public class WxCpCorpGroupServiceImpl implements WxCpCorpGroupService {
  private final WxCpService cpService;

  @Override
  public List<WxCpCorpGroupCorp> listAppShareInfo(Integer agentId, Integer businessType, String corpId,
                                                  Integer limit, String cursor) throws WxErrorException {
    final String url = this.cpService.getWxCpConfigStorage().getApiUrl(LIST_SHARE_APP_INFO);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("agentid", agentId);
    jsonObject.addProperty("corpid", corpId);
    jsonObject.addProperty("business_type", businessType);
    jsonObject.addProperty("limit", limit);
    jsonObject.addProperty("cursor", cursor);
    String responseContent = this.cpService.post(url, jsonObject);
    JsonObject tmpJson = GsonParser.parse(responseContent);

    return WxCpGsonBuilder.create().fromJson(tmpJson.get("corp_list"),
      new TypeToken<List<WxCpCorpGroupCorp>>() {
      }.getType()
    );
  }
}
