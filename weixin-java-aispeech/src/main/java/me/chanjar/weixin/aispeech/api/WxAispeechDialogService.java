package me.chanjar.weixin.aispeech.api;

import java.util.List;
import me.chanjar.weixin.aispeech.bean.dialog.AsyncTaskResult;
import me.chanjar.weixin.aispeech.bean.dialog.BotIntent;
import me.chanjar.weixin.aispeech.bean.dialog.DialogQueryRequest;
import me.chanjar.weixin.aispeech.bean.dialog.DialogResult;
import me.chanjar.weixin.aispeech.bean.dialog.PublishProgress;
import me.chanjar.weixin.common.error.WxErrorException;

public interface WxAispeechDialogService {
  String getAccessToken(String appid, String account) throws WxErrorException;

  String importBotJson(int mode, List<BotIntent> data) throws WxErrorException;

  String publishBot() throws WxErrorException;

  PublishProgress getPublishProgress(String env) throws WxErrorException;

  AsyncTaskResult queryAsyncTask(String taskId) throws WxErrorException;

  DialogResult query(DialogQueryRequest request) throws WxErrorException;
}
