package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.WxCpOaTodoService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.WxCpOaTodo;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.TODO_GET;
import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.TODO_UPDATE_STATUS;

/**
 * 企业微信待办接口实现。
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RequiredArgsConstructor
public class WxCpOaTodoServiceImpl implements WxCpOaTodoService {
  private final WxCpService wxCpService;

  @Override
  public WxCpOaTodo getTodo(String userId, String todoId) throws WxErrorException {
    String url = String.format("%s?userid=%s&todoid=%s",
      this.wxCpService.getWxCpConfigStorage().getApiUrl(TODO_GET), userId, todoId);
    String response = this.wxCpService.get(url, null);
    return WxCpGsonBuilder.create().fromJson(GsonParser.parse(response).get("todo"), WxCpOaTodo.class);
  }

  @Override
  public WxCpBaseResp updateTodoStatus(String userId, String todoId, int status) throws WxErrorException {
    JsonObject request = new JsonObject();
    request.addProperty("userid", userId);
    request.addProperty("todoid", todoId);
    request.addProperty("status", status);
    String response = this.wxCpService.post(
      this.wxCpService.getWxCpConfigStorage().getApiUrl(TODO_UPDATE_STATUS), request.toString());
    return WxCpBaseResp.fromJson(response);
  }
}
