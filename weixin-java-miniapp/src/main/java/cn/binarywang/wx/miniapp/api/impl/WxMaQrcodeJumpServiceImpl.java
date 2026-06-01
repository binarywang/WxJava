package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaQrcodeJumpService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.qrcode.WxMaQrcodeJumpRule;
import cn.binarywang.wx.miniapp.bean.qrcode.WxMaQrcodeJumpRuleListResponse;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.QrcodeJump.*;
import static me.chanjar.weixin.common.util.json.WxGsonBuilder.create;

/**
 * {@link WxMaQrcodeJumpService} 实现。
 */
@RequiredArgsConstructor
public class WxMaQrcodeJumpServiceImpl implements WxMaQrcodeJumpService {
  private final WxMaService wxMaService;

  @Override
  public String addRule(WxMaQrcodeJumpRule rule) throws WxErrorException {
    return this.wxMaService.post(QRCODE_JUMP_ADD, create().toJson(rule));
  }

  @Override
  public List<WxMaQrcodeJumpRule> getRules(Boolean isDefault, String prefix) throws WxErrorException {
    final JsonObject request = new JsonObject();
    if (isDefault != null) {
      request.addProperty("is_default", isDefault);
    }
    if (prefix != null) {
      request.addProperty("prefix", prefix);
    }

    String response = this.wxMaService.post(QRCODE_JUMP_GET, request.toString());
    WxMaQrcodeJumpRuleListResponse result = create().fromJson(response, WxMaQrcodeJumpRuleListResponse.class);
    if (result == null || result.getRuleList() == null || result.getRuleList().isEmpty()) {
      return Collections.emptyList();
    }
    return result.getRuleList();
  }

  @Override
  public List<WxMaQrcodeJumpRule> getRuleList(Integer getType, Integer pageNum, Integer pageSize) throws WxErrorException {
    final JsonObject request = new JsonObject();
    if (getType != null) {
      request.addProperty("get_type", getType);
    }
    if (pageNum != null) {
      request.addProperty("page_num", pageNum);
    }
    if (pageSize != null) {
      request.addProperty("page_size", pageSize);
    }

    String response = this.wxMaService.post(QRCODE_JUMP_GET_LIST, request.toString());
    WxMaQrcodeJumpRuleListResponse result = create().fromJson(response, WxMaQrcodeJumpRuleListResponse.class);
    if (result == null || result.getRuleList() == null || result.getRuleList().isEmpty()) {
      return Collections.emptyList();
    }
    return result.getRuleList();
  }

  @Override
  public String deleteRule(String prefix) throws WxErrorException {
    final Map<String, String> request = new HashMap<>(1);
    request.put("prefix", prefix);
    return this.wxMaService.post(QRCODE_JUMP_DELETE, create().toJson(request));
  }
}
