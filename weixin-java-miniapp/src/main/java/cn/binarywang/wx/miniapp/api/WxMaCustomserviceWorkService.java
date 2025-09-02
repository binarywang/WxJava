package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.customservice.WxMaCustomserviceResult;
import me.chanjar.weixin.common.error.WxErrorException;


/**
 * <pre>
 *  小程序 - 微信客服 相关接口
 *  负责处理 https://api.weixin.qq.com/customservice/work/**
 *  文档：https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/kf-work/getKfWorkBound.html
 *  绑定的企业ID，需和小程序主体一致。
 *  目前仅支持绑定非个人小程序。
 *  Created by tryking123 on 2025/8/18.
 * </pre>
 *
 * @author <a href="https://github.com/tryking123">tryking123</a>
 */
public interface WxMaCustomserviceWorkService {

  /**
   * 查询小程序的微信客服绑定情况
   */
  String GET_CUSTOMSERVICE_URL = "https://api.weixin.qq.com/customservice/work/get";
  /**
   * 为小程序绑定微信客服 注:此接口绑定的企业ID需完成企业认证
   */
  String BIND_CUSTOMSERVICE_URL = "https://api.weixin.qq.com/customservice/work/bind";
  /**
   * 为小程序解除绑定微信客服
   */
  String UNBIND_CUSTOMSERVICE_URL = "https://api.weixin.qq.com/customservice/work/unbind";

  /**
   * 查询小程序的微信客服绑定情况
   *
   * @return 成功示例json { "errcode": 0,"entityName": "XXXXX有限公司","corpid": "wwee11111xxxxxxx","bindTime": 1694611289 }
   * @throws WxErrorException
   */
  WxMaCustomserviceResult getCustomservice() throws WxErrorException;

  /**
   * 绑定微信客服
   * @param corpid 企业ID，获取方式参考:https://developer.work.weixin.qq.com/document/path/90665#corpid
   * @return 成功示例json { "errcode": 0 }
   * @throws WxErrorException
   */
  WxMaCustomserviceResult bindCustomservice(String corpid) throws WxErrorException;

  /**
   * 解除绑定微信客服
   * @param corpid  企业ID，获取方式参考:https://developer.work.weixin.qq.com/document/path/90665#corpid
   * @return 成功示例json { "errcode": 0 }
   * @throws WxErrorException
   */
  WxMaCustomserviceResult unbindCustomservice(String corpid) throws WxErrorException;
}
