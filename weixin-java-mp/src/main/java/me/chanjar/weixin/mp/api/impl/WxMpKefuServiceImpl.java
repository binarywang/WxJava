package me.chanjar.weixin.mp.api.impl;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.mp.api.WxMpKefuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.kefu.request.WxMpKfAccountRequest;
import me.chanjar.weixin.mp.bean.kefu.request.WxMpKfSessionRequest;
import me.chanjar.weixin.mp.bean.kefu.result.*;

import java.io.File;
import java.util.Date;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Kefu.*;

/**
 * @author Binary Wang
 */
@Slf4j
@RequiredArgsConstructor
public class WxMpKefuServiceImpl implements WxMpKefuService {
  private final WxMpService wxMpService;

  @Override
  public boolean sendKefuMessage(WxMpKefuMessage message) throws WxErrorException {
    return this.sendKefuMessageWithResponse(message) != null;
  }

  @Override
  public String sendKefuMessageWithResponse(WxMpKefuMessage message) throws WxErrorException {
    return this.wxMpService.post(MESSAGE_CUSTOM_SEND, message.toJson());
  }

  @Override
  public WxMpKfList kfList() throws WxErrorException {
    String responseContent = this.wxMpService.get(GET_KF_LIST, null);
    return WxMpKfList.fromJson(responseContent);
  }

  @Override
  public WxMpKfOnlineList kfOnlineList() throws WxErrorException {
    String responseContent = this.wxMpService.get(GET_ONLINE_KF_LIST, null);
    return WxMpKfOnlineList.fromJson(responseContent);
  }

  @Override
  public boolean kfAccountAdd(WxMpKfAccountRequest request) throws WxErrorException {
    String responseContent = this.wxMpService.post(KFACCOUNT_ADD, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfAccountUpdate(WxMpKfAccountRequest request) throws WxErrorException {
    String responseContent = this.wxMpService.post(KFACCOUNT_UPDATE, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfAccountInviteWorker(WxMpKfAccountRequest request) throws WxErrorException {
    String responseContent = this.wxMpService.post(KFACCOUNT_INVITE_WORKER, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfAccountUploadHeadImg(String kfAccount, File imgFile) throws WxErrorException {
    WxMediaUploadResult responseContent = this.wxMpService
      .execute(MediaUploadRequestExecutor.create(this.wxMpService.getRequestHttp()),
        String.format(KFACCOUNT_UPLOAD_HEAD_IMG.getUrl(this.wxMpService.getWxMpConfigStorage()), kfAccount), imgFile);
    return responseContent != null;
  }

  @Override
  public boolean kfAccountDel(String kfAccount) throws WxErrorException {
    String responseContent = this.wxMpService.get(String.format(KFACCOUNT_DEL.getUrl(this.wxMpService.getWxMpConfigStorage()),
      kfAccount), null);
    return responseContent != null;
  }

  @Override
  public boolean kfSessionCreate(String openid, String kfAccount) throws WxErrorException {
    WxMpKfSessionRequest request = new WxMpKfSessionRequest(kfAccount, openid);
    String responseContent = this.wxMpService.post(KFSESSION_CREATE, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfSessionClose(String openid, String kfAccount) throws WxErrorException {
    WxMpKfSessionRequest request = new WxMpKfSessionRequest(kfAccount, openid);
    String responseContent = this.wxMpService.post(KFSESSION_CLOSE, request.toJson());
    return responseContent != null;
  }

  @Override
  public WxMpKfSessionGetResult kfSessionGet(String openid) throws WxErrorException {
    String responseContent = this.wxMpService.get(String.format(KFSESSION_GET_SESSION
      .getUrl(this.wxMpService.getWxMpConfigStorage()), openid), null);
    return WxMpKfSessionGetResult.fromJson(responseContent);
  }

  @Override
  public WxMpKfSessionList kfSessionList(String kfAccount) throws WxErrorException {
    String responseContent = this.wxMpService.get(String.format(KFSESSION_GET_SESSION_LIST
      .getUrl(this.wxMpService.getWxMpConfigStorage()), kfAccount), null);
    return WxMpKfSessionList.fromJson(responseContent);
  }

  @Override
  public WxMpKfSessionWaitCaseList kfSessionGetWaitCase() throws WxErrorException {
    String responseContent = this.wxMpService.get(KFSESSION_GET_WAIT_CASE, null);
    return WxMpKfSessionWaitCaseList.fromJson(responseContent);
  }

  @Override
  public WxMpKfMsgList kfMsgList(Date startTime, Date endTime, Long msgId, Integer number) throws WxErrorException {
    if (number > 10000) {
      throw new WxErrorException("非法参数请求，每次最多查询10000条记录！");
    }

    if (startTime.after(endTime)) {
      throw new WxErrorException("起始时间不能晚于结束时间！");
    }

    JsonObject param = new JsonObject();
    param.addProperty("starttime", startTime.getTime() / 1000);
    param.addProperty("endtime", endTime.getTime() / 1000);
    param.addProperty("msgid", msgId);
    param.addProperty("number", number);

    String responseContent = this.wxMpService.post(MSG_RECORD_LIST, param.toString());

    return WxMpKfMsgList.fromJson(responseContent);
  }

  @Override
  public WxMpKfMsgList kfMsgList(Date startTime, Date endTime) throws WxErrorException {
    int number = 10000;
    WxMpKfMsgList result = this.kfMsgList(startTime, endTime, 1L, number);

    if (result != null && result.getNumber() == number) {
      Long msgId = result.getMsgId();
      WxMpKfMsgList followingResult = this.kfMsgList(startTime, endTime, msgId, number);
      while (followingResult != null && !followingResult.getRecords().isEmpty()) {
        result.getRecords().addAll(followingResult.getRecords());
        result.setNumber(result.getNumber() + followingResult.getNumber());
        result.setMsgId(followingResult.getMsgId());
        followingResult = this.kfMsgList(startTime, endTime, followingResult.getMsgId(), number);
      }
    }

    return result;
  }

  @Override
  public boolean sendKfTypingState(String openid, String command) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("touser", openid);
    params.addProperty("command", command);
    String responseContent = this.wxMpService.post(CUSTOM_TYPING, params.toString());
    return responseContent != null;
  }

}
