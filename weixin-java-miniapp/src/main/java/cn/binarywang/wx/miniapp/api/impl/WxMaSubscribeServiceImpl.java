package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaSubscribeService;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.subscribemsg.CategoryData;
import me.chanjar.weixin.common.bean.subscribemsg.PubTemplateKeyword;
import me.chanjar.weixin.common.bean.subscribemsg.TemplateInfo;
import me.chanjar.weixin.common.bean.subscribemsg.PubTemplateTitleListResult;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Subscribe.*;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2019-12-15
 */
@RequiredArgsConstructor
public class WxMaSubscribeServiceImpl implements WxMaSubscribeService {
  private final WxMaService service;

  @Override
  public PubTemplateTitleListResult getPubTemplateTitleList(String[] ids, int start, int limit) throws WxErrorException {
    ImmutableMap<String, ? extends Serializable> params = ImmutableMap.of("ids", StringUtils.join(ids, ","),
      "start", start, "limit", limit);
    String responseText = this.service.get(GET_PUB_TEMPLATE_TITLE_LIST_URL,
      Joiner.on("&").withKeyValueSeparator("=").join(params));
    return PubTemplateTitleListResult.fromJson(responseText);
  }

  @Override
  public List<PubTemplateKeyword> getPubTemplateKeyWordsById(String id) throws WxErrorException {
    String responseText = this.service.get(GET_PUB_TEMPLATE_KEY_WORDS_BY_ID_URL,
      Joiner.on("&").withKeyValueSeparator("=").join(ImmutableMap.of("tid", id)));
    return WxMaGsonBuilder.create().fromJson(GsonParser.parse(responseText)
      .getAsJsonArray("data"), new TypeToken<List<PubTemplateKeyword>>() {
    }.getType());
  }

  @Override
  public String addTemplate(String id, List<Integer> keywordIdList, String sceneDesc) throws WxErrorException {
    String responseText = this.service.post(TEMPLATE_ADD_URL, ImmutableMap.of("tid", id,
      "kidList", keywordIdList.toArray(),
      "sceneDesc", sceneDesc));
    return GsonParser.parse(responseText).get("priTmplId").getAsString();
  }

  @Override
  public List<TemplateInfo> getTemplateList() throws WxErrorException {
    String responseText = this.service.get(TEMPLATE_LIST_URL, null);
    return WxMaGsonBuilder.create().fromJson(GsonParser.parse(responseText)
      .getAsJsonArray("data"), new TypeToken<List<TemplateInfo>>() {
    }.getType());
  }

  @Override
  public boolean delTemplate(String templateId) throws WxErrorException {
    this.service.post(TEMPLATE_DEL_URL, ImmutableMap.of("priTmplId", templateId));
    return true;
  }

  @Override
  public List<CategoryData> getCategory() throws WxErrorException {
    String responseText = this.service.get(GET_CATEGORY_URL, null);
    return WxMaGsonBuilder.create().fromJson(GsonParser.parse(responseText)
      .getAsJsonArray("data"), new TypeToken<List<CategoryData>>() {
    }.getType());
  }

  @Override
  public void sendSubscribeMsg(WxMaSubscribeMessage subscribeMessage) throws WxErrorException {
    String responseContent = this.service.post(SUBSCRIBE_MSG_SEND_URL, subscribeMessage.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
  }
}
