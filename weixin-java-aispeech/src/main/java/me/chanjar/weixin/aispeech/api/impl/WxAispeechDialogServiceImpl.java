package me.chanjar.weixin.aispeech.api.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.chanjar.weixin.aispeech.api.WxAispeechDialogService;
import me.chanjar.weixin.aispeech.bean.dialog.AispeechApiResponse;
import me.chanjar.weixin.aispeech.bean.dialog.AsyncTaskResult;
import me.chanjar.weixin.aispeech.bean.dialog.BotIntent;
import me.chanjar.weixin.aispeech.bean.dialog.DialogQueryRequest;
import me.chanjar.weixin.aispeech.bean.dialog.DialogResult;
import me.chanjar.weixin.aispeech.bean.dialog.PublishProgress;
import me.chanjar.weixin.aispeech.util.WxAispeechSignUtil;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import org.apache.commons.lang3.StringUtils;

public class WxAispeechDialogServiceImpl implements WxAispeechDialogService {
  private final WxAispeechServiceImpl service;

  public WxAispeechDialogServiceImpl(WxAispeechServiceImpl service) {
    this.service = service;
  }

  @Override
  public String getAccessToken(String appid, String account) throws WxErrorException {
    Map<String, String> request = new HashMap<>();
    if (StringUtils.isNotBlank(account)) {
      request.put("account", account);
    }

    String response = service.executeDialogPost("/v2/token", request, false, appid);
    Type type = new TypeToken<AispeechApiResponse<JsonObject>>() { } .getType();
    AispeechApiResponse<JsonObject> result = WxGsonBuilder.create().fromJson(response, type);
    ensureSuccess(result);
    String token = result.getData().get("access_token").getAsString();
    service.getConfigStorage().setOpenAiToken(token);
    return token;
  }

  @Override
  public String importBotJson(int mode, List<BotIntent> data) throws WxErrorException {
    Map<String, Object> request = new HashMap<>();
    request.put("mode", mode);
    request.put("data", data);

    String response = service.executeDialogPost("/v2/bot/import/json", request, true, null);
    Type type = new TypeToken<AispeechApiResponse<JsonObject>>() { } .getType();
    AispeechApiResponse<JsonObject> result = WxGsonBuilder.create().fromJson(response, type);
    ensureSuccess(result);
    return result.getData().get("task_id").getAsString();
  }

  @Override
  public String publishBot() throws WxErrorException {
    String response = service.executeDialogPost("/v2/bot/publish", "{}", true, null);
    Type type = new TypeToken<AispeechApiResponse<JsonObject>>() { } .getType();
    AispeechApiResponse<JsonObject> result = WxGsonBuilder.create().fromJson(response, type);
    ensureSuccess(result);
    return result.getRequestId();
  }

  @Override
  public PublishProgress getPublishProgress(String env) throws WxErrorException {
    Map<String, String> request = new HashMap<>();
    request.put("env", env);

    String response = service.executeDialogPost("/v2/bot/effective_progress", request, true, null);
    Type type = new TypeToken<AispeechApiResponse<PublishProgress>>() { } .getType();
    AispeechApiResponse<PublishProgress> result = WxGsonBuilder.create().fromJson(response, type);
    ensureSuccess(result);
    return result.getData();
  }

  @Override
  public AsyncTaskResult queryAsyncTask(String taskId) throws WxErrorException {
    Map<String, String> request = new HashMap<>();
    request.put("task_id", taskId);

    String response = service.executeDialogPost("/v2/async/fetch", request, true, null);
    Type type = new TypeToken<AispeechApiResponse<AsyncTaskResult>>() { } .getType();
    AispeechApiResponse<AsyncTaskResult> result = WxGsonBuilder.create().fromJson(response, type);
    ensureSuccess(result);
    return result.getData();
  }

  @Override
  public DialogResult query(DialogQueryRequest request) throws WxErrorException {
    String json = WxGsonBuilder.create().toJson(request);
    String encrypted = WxAispeechSignUtil.encryptAesCbcToBase64(json, service.getConfigStorage().getAesKey());
    String response = service.executeDialogPost("/v2/bot/query", encrypted, true, null);

    String responseJson = response;
    if (!looksLikeJson(response)) {
      responseJson = WxAispeechSignUtil.decryptAesCbcFromBase64(response, service.getConfigStorage().getAesKey());
    }

    Type type = new TypeToken<AispeechApiResponse<DialogResult>>() { } .getType();
    AispeechApiResponse<DialogResult> result = WxGsonBuilder.create().fromJson(responseJson, type);
    ensureSuccess(result);

    DialogResult dialogResult = result.getData();
    if (dialogResult != null && looksLikeJson(dialogResult.getAnswer())) {
      dialogResult.setRawAnswer(WxGsonBuilder.create().fromJson(dialogResult.getAnswer(), JsonElement.class));
    }
    return dialogResult;
  }

  private boolean looksLikeJson(String value) {
    return StringUtils.isNotBlank(value) && (value.startsWith("{") || value.startsWith("["));
  }

  private void ensureSuccess(AispeechApiResponse<?> response) throws WxErrorException {
    if (response == null) {
      throw new WxErrorException("响应为空");
    }
    if (response.getCode() == null || response.getCode() != 0) {
      throw new WxErrorException(WxError.builder()
        .errorCode(response.getCode() == null ? -1 : response.getCode())
        .errorMsg(response.getMsg())
        .build());
    }
  }
}
