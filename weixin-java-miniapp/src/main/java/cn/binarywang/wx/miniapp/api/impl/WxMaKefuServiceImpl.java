package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaKefuService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.kefu.WxMaKfInfo;
import cn.binarywang.wx.miniapp.bean.kefu.WxMaKfList;
import cn.binarywang.wx.miniapp.bean.kefu.WxMaKfSession;
import cn.binarywang.wx.miniapp.bean.kefu.WxMaKfSessionList;
import cn.binarywang.wx.miniapp.bean.kefu.request.WxMaKfAccountRequest;
import cn.binarywang.wx.miniapp.bean.kefu.request.WxMaKfSessionRequest;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 小程序客服管理服务实现.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RequiredArgsConstructor
public class WxMaKefuServiceImpl implements WxMaKefuService {

  // 小程序客服管理接口URL
  private static final String KFLIST_GET_URL = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist";
  private static final String KFACCOUNT_ADD_URL = "https://api.weixin.qq.com/customservice/kfaccount/add";
  private static final String KFACCOUNT_UPDATE_URL = "https://api.weixin.qq.com/customservice/kfaccount/update";
  private static final String KFACCOUNT_DEL_URL = "https://api.weixin.qq.com/customservice/kfaccount/del?kf_account=%s";
  private static final String KFSESSION_CREATE_URL = "https://api.weixin.qq.com/customservice/kfsession/create";
  private static final String KFSESSION_CLOSE_URL = "https://api.weixin.qq.com/customservice/kfsession/close";
  private static final String KFSESSION_GET_URL = "https://api.weixin.qq.com/customservice/kfsession/getsession?openid=%s";
  private static final String KFSESSION_LIST_URL = "https://api.weixin.qq.com/customservice/kfsession/getsessionlist?kf_account=%s";

  private final WxMaService service;

  @Override
  public WxMaKfList kfList() throws WxErrorException {
    String responseContent = this.service.get(KFLIST_GET_URL, null);
    return WxMaKfList.fromJson(responseContent);
  }

  @Override
  public boolean kfAccountAdd(WxMaKfAccountRequest request) throws WxErrorException {
    String responseContent = this.service.post(KFACCOUNT_ADD_URL, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfAccountUpdate(WxMaKfAccountRequest request) throws WxErrorException {
    String responseContent = this.service.post(KFACCOUNT_UPDATE_URL, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfAccountDel(String kfAccount) throws WxErrorException {
    String url = String.format(KFACCOUNT_DEL_URL, kfAccount);
    String responseContent = this.service.get(url, null);
    return responseContent != null;
  }

  @Override
  public boolean kfSessionCreate(String openid, String kfAccount) throws WxErrorException {
    WxMaKfSessionRequest request = WxMaKfSessionRequest.builder()
        .kfAccount(kfAccount)
        .openid(openid)
        .build();
    String responseContent = this.service.post(KFSESSION_CREATE_URL, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfSessionClose(String openid, String kfAccount) throws WxErrorException {
    WxMaKfSessionRequest request = WxMaKfSessionRequest.builder()
        .kfAccount(kfAccount)
        .openid(openid)
        .build();
    String responseContent = this.service.post(KFSESSION_CLOSE_URL, request.toJson());
    return responseContent != null;
  }

  @Override
  public WxMaKfSession kfSessionGet(String openid) throws WxErrorException {
    String url = String.format(KFSESSION_GET_URL, openid);
    String responseContent = this.service.get(url, null);
    return WxMaKfSession.fromJson(responseContent);
  }

  @Override
  public WxMaKfSessionList kfSessionList(String kfAccount) throws WxErrorException {
    String url = String.format(KFSESSION_LIST_URL, kfAccount);
    String responseContent = this.service.get(url, null);
    return WxMaKfSessionList.fromJson(responseContent);
  }
}