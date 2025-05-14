package me.chanjar.weixin.mp.api.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.mp.bean.card.membercard.*;
import me.chanjar.weixin.mp.enums.WxMpApiUrl;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.api.WxMpMemberCardService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.card.AdvancedInfo;
import me.chanjar.weixin.mp.bean.card.BaseInfo;
import me.chanjar.weixin.mp.bean.card.CardUpdateResult;
import me.chanjar.weixin.mp.bean.card.DateInfo;
import me.chanjar.weixin.mp.bean.card.WxMpCardCreateResult;
import me.chanjar.weixin.mp.bean.card.enums.BusinessServiceType;
import me.chanjar.weixin.mp.bean.card.enums.CardColor;
import me.chanjar.weixin.mp.bean.card.enums.DateInfoType;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * 会员卡相关接口的实现类
 *
 * @author YuJian(mgcnrx11 @ gmail.com)
 * @version 2017/7/8
 */
@Slf4j
@RequiredArgsConstructor
public class WxMpMemberCardServiceImpl implements WxMpMemberCardService {
  private final WxMpService wxMpService;

  private static final Gson GSON = WxMpGsonBuilder.create();

  @Override
  public WxMpService getWxMpService() {
    return this.wxMpService;
  }

  @Override
  public WxMpCardCreateResult createMemberCard(String createJson) throws WxErrorException {
    WxMpMemberCardCreateMessage createMessage = WxGsonBuilder.create()
      .fromJson(createJson, WxMpMemberCardCreateMessage.class);
    return createMemberCard(createMessage);
  }

  @Override
  public WxMpCardCreateResult createMemberCard(WxMpMemberCardCreateMessage createMessageMessage)
    throws WxErrorException {
    //校验请求对象合法性
    WxMpCardCreateResult validResult = validCheck(createMessageMessage);
    if (!validResult.isSuccess()) {
      return validResult;
    }

    String response = this.wxMpService.post(WxMpApiUrl.MemberCard.MEMBER_CARD_CREATE, GSON.toJson(createMessageMessage));
    return WxMpCardCreateResult.fromJson(response);
  }

  private WxMpCardCreateResult validCheck(WxMpMemberCardCreateMessage createMessageMessage) {
    if (createMessageMessage == null) {
      return WxMpCardCreateResult.failure("对象不能为空");
    }
    MemberCardCreateRequest cardCreateRequest = createMessageMessage.getCardCreateRequest();
    if (cardCreateRequest == null) {
      return WxMpCardCreateResult.failure("会员卡对象不能为空");
    }
    String cardType = cardCreateRequest.getCardType();
    if (!StringUtils.equals(cardType, "MEMBER_CARD")) {
      return WxMpCardCreateResult.failure("卡券类型必须等于MEMBER_CARD");
    }
    MemberCard memberCard = cardCreateRequest.getMemberCard();

    if (StringUtils.isEmpty(memberCard.getPrerogative())) {
      return WxMpCardCreateResult.failure("会员卡特权说明不能为空:prerogative");
    }
    //卡片激活规则
    if (!memberCard.isAutoActivate() && !memberCard.isWxActivate()
      && StringUtils.isEmpty(memberCard.getActivateUrl())) {
      return WxMpCardCreateResult.failure("会员卡激活方式为接口激活，activate_url不能为空");
    }

    BaseInfo baseInfo = memberCard.getBaseInfo();
    if (baseInfo == null) {
      return WxMpCardCreateResult.failure("会员卡基本信息对象base_info不能为空");
    }

    if (StringUtils.isBlank(baseInfo.getLogoUrl())) {
      return WxMpCardCreateResult.failure("会员卡基本信息的商户logo:logo_url不能为空");
    }

    if (StringUtils.isBlank(baseInfo.getCodeType())) {
      return WxMpCardCreateResult.failure("会员卡基本信息的条码类型:code_type不能为空");
    }

    if (StringUtils.isBlank(baseInfo.getBrandName())) {
      return WxMpCardCreateResult.failure("会员卡基本信息的商户名字:brand_name不能为空");
    }

    if (StringUtils.length(baseInfo.getBrandName()) > 12) {
      return WxMpCardCreateResult.failure("会员卡基本信息的商户名字:brand_name长度不能大于12个汉字");
    }

    if (StringUtils.isBlank(baseInfo.getTitle())) {
      return WxMpCardCreateResult.failure("会员卡基本信息的卡券名称:title不能为空");
    }

    if (StringUtils.length(baseInfo.getTitle()) > 9) {
      return WxMpCardCreateResult.failure("会员卡基本信息的卡券名称:title长度不能大于9个汉字");
    }

    if (StringUtils.isBlank(baseInfo.getColor())) {
      return WxMpCardCreateResult.failure("会员卡基本信息的卡颜色:color不能为空");
    }

    CardColor cardColor = null;
    try {
      cardColor = CardColor.valueOf(baseInfo.getColor());
    } catch (IllegalArgumentException ex) {

    }
    if (cardColor == null) {
      return WxMpCardCreateResult.failure("会员卡基本信息的卡颜色:" + baseInfo.getColor() + "不支持");
    }

    if (StringUtils.isBlank(baseInfo.getNotice())) {
      return WxMpCardCreateResult.failure("会员卡基本信息的使用提醒:notice不能为空");
    }

    if (StringUtils.isBlank(baseInfo.getDescription())) {
      return WxMpCardCreateResult.failure("会员卡基本信息的使用说明:description不能为空");
    }

    if (baseInfo.getSku() == null) {
      return WxMpCardCreateResult.failure("会员卡基本信息的商品信息:sku不能为空");
    }

    DateInfo dateInfo = baseInfo.getDateInfo();
    if (dateInfo == null) {
      return WxMpCardCreateResult.failure("会员卡基本信息的使用日期:date_info不能为空");
    }

    DateInfoType dateInfoType = null;
    try {
      dateInfoType = DateInfoType.valueOf(dateInfo.getType());
    } catch (IllegalArgumentException ex) {

    }

    if (dateInfoType == null) {
      return WxMpCardCreateResult.failure("会员卡基本信息的使用日期类型:" + dateInfo.getType() + "不合法");
    }

    //固定时长
    if (dateInfoType == DateInfoType.DATE_TYPE_FIX_TERM
      && (dateInfo.getFixedTerm() == null || dateInfo.getFixedBeginTerm() == null)) {
      return WxMpCardCreateResult.failure(String.format("会员卡基本信息的使用日期为:%s，fixedTerm和fixedBeginTerm不能为空",
        dateInfoType.getDescription()));
    }

    //固定期限
    if (dateInfoType == DateInfoType.DATE_TYPE_FIX_TIME_RANGE
      && (dateInfo.getBeginTimestamp() == null || dateInfo.getEndTimestamp() == null)) {
      return WxMpCardCreateResult.failure(String.format("会员卡基本信息的使用日期为:%s，beginTimestamp 和 endTimestamp 不能为空",
        dateInfoType.getDescription()));
    }
    if (dateInfoType == DateInfoType.DATE_TYPE_FIX_TIME_RANGE
      && (dateInfo.getBeginTimestamp() * 1000 < System.currentTimeMillis()
      || dateInfo.getEndTimestamp() * 1000 < System.currentTimeMillis()
      || dateInfo.getBeginTimestamp() > dateInfo.getEndTimestamp())) {
      return WxMpCardCreateResult.failure(String.format("会员卡基本信息的使用日期为:%s，beginTimestamp和endTimestamp的值不合法，请检查",
        dateInfoType.getDescription()));
    }

    if (!baseInfo.isUseAllLocations() && baseInfo.getLocationIdList().isEmpty()) {
      return WxMpCardCreateResult.failure("会员卡基本信息的门店使用范围选择指定门店,门店列表:locationIdList不能为空");
    }

    //校验高级信息
    AdvancedInfo advancedInfo = memberCard.getAdvancedInfo();
    if (advancedInfo != null) {
      if (advancedInfo.getBusinessServiceList() != null) {
        for (String bs : advancedInfo.getBusinessServiceList()) {
          try {
            BusinessServiceType.valueOf(bs);
          } catch (IllegalArgumentException ex) {
            return WxMpCardCreateResult.failure("会员卡高级信息的商户服务:" + bs + " 不合法");
          }
        }
      }
    }

    return WxMpCardCreateResult.success();
  }

  @Override
  public String activateMemberCard(WxMpMemberCardActivatedMessage activatedMessage) throws WxErrorException {
    return this.wxMpService.post(WxMpApiUrl.MemberCard.MEMBER_CARD_ACTIVATE, GSON.toJson(activatedMessage));
  }

  @Override
  public WxMpMemberCardUserInfoResult getUserInfo(String cardId, String code) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("card_id", cardId);
    jsonObject.addProperty("code", code);

    String responseContent = this.getWxMpService().post(WxMpApiUrl.MemberCard.MEMBER_CARD_USER_INFO_GET, jsonObject.toString());
    log.debug("{}", responseContent);
    JsonElement tmpJsonElement = new JsonParser().parse(responseContent);
    return WxMpGsonBuilder.create().fromJson(tmpJsonElement,
      new TypeToken<WxMpMemberCardUserInfoResult>() {
      }.getType());
  }

  @Override
  public WxMpMemberCardUpdateResult updateUserMemberCard(WxMpMemberCardUpdateMessage updateUserMessage)
    throws WxErrorException {

    String responseContent = this.getWxMpService().post(WxMpApiUrl.MemberCard.MEMBER_CARD_UPDATE_USER, GSON.toJson(updateUserMessage));

    JsonElement tmpJsonElement = new JsonParser().parse(responseContent);
    return WxMpGsonBuilder.create().fromJson(tmpJsonElement,
      new TypeToken<WxMpMemberCardUpdateResult>() {
      }.getType());
  }

  @Override
  public MemberCardActivateUserFormResult setActivateUserForm(MemberCardActivateUserFormRequest userFormRequest) throws WxErrorException {
    String responseContent = this.getWxMpService().post(WxMpApiUrl.MemberCard.MEMBER_CARD_ACTIVATE_USER_FORM, GSON.toJson(userFormRequest));
    return MemberCardActivateUserFormResult.fromJson(responseContent);
  }

  @Override
  public ActivatePluginParam getActivatePluginParam(String cardId, String outStr) throws WxErrorException {
    String url = this.getActivatePluginUrl(cardId, outStr);
    try {
      String decodedUrl = URLDecoder.decode(url, "UTF-8");
      Map<String, String> resultMap = parseRequestUrl(decodedUrl);
      ActivatePluginParam activatePluginParam = new ActivatePluginParam();
      activatePluginParam.setEncryptCardId(resultMap.get("encrypt_card_id"));
      activatePluginParam.setOuterStr(resultMap.get("outer_str"));
      activatePluginParam.setBiz(resultMap.get("biz") + "==");
      return activatePluginParam;
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return null;
  }


  @Override
  public String getActivatePluginUrl(String cardId, String outStr) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("card_id", cardId);
    params.addProperty("outer_str", outStr);
    String response = this.wxMpService.post(WxMpApiUrl.MemberCard.MEMBER_CARD_ACTIVATE_URL, GSON.toJson(params));
    ActivatePluginParamResult result = GSON.fromJson(response, ActivatePluginParamResult.class);
    return result.getUrl();
  }

  @Override
  public CardUpdateResult updateCardInfo(MemberCardUpdateRequest memberCardUpdateRequest) throws WxErrorException {
    String response = this.wxMpService.post(WxMpApiUrl.MemberCard.MEMBER_CARD_UPDATE, GSON.toJson(memberCardUpdateRequest));
    return GSON.fromJson(response, CardUpdateResult.class);
  }

  @Override
  public WxMpMemberCardActivateTempInfoResult getActivateTempInfo(String activateTicket) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("activate_ticket", activateTicket);
    String response = this.wxMpService.post(WxMpApiUrl.MemberCard.MEMBER_CARD_ACTIVATE_TEMP_INFO, GSON.toJson(params));
    return GSON.fromJson(response, WxMpMemberCardActivateTempInfoResult.class);
  }

  private static String truncateUrlPage(String strURL) {
    String strAllParam = null;
    String[] arrSplit;
    arrSplit = strURL.split("[?]");
    if (strURL.length() > 1) {
      if (arrSplit.length > 1) {
        if (arrSplit[1] != null) {
          strAllParam = arrSplit[1];
        }
      }
    }

    return strAllParam;
  }

  private static Map<String, String> parseRequestUrl(String url) {
    Map<String, String> mapRequest = new HashMap<>(16);

    String[] arrSplit;

    String strUrlParam = truncateUrlPage(url);
    if (strUrlParam == null) {
      return mapRequest;
    }
    arrSplit = strUrlParam.split("[&]");
    for (String strSplit : arrSplit) {
      String[] arrSplitEqual;
      arrSplitEqual = strSplit.split("[=]");

      //解析出键值
      if (arrSplitEqual.length > 1) {
        //正确解析
        mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

      } else {
        if (!"".equals(arrSplitEqual[0])) {
          //只有参数没有值，不加入
          mapRequest.put(arrSplitEqual[0], "");
        }
      }
    }
    return mapRequest;
  }

}
