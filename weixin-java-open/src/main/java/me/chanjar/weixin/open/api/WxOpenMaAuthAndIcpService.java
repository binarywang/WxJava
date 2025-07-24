package me.chanjar.weixin.open.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.bean.authandicp.WxOpenQueryAuthAndIcpResult;
import me.chanjar.weixin.open.bean.authandicp.WxOpenSubmitAuthAndIcpParam;
import me.chanjar.weixin.open.bean.authandicp.WxOpenSubmitAuthAndIcpResult;

/**
 * 微信第三方平台 小程序认证及备案
 * @author 痴货
 * @createTime 2025/06/18 23:00
 */
public interface WxOpenMaAuthAndIcpService {

  String QUERY_AUTH_AND_ICP = "https://api.weixin.qq.com/wxa/sec/query_auth_and_icp";

  String SUBMIT_AUTH_AND_ICP = "https://api.weixin.qq.com/wxa/sec/submit_auth_and_icp";

  /**
   * 查询小程序认证及备案进度。
   * @param procedureId 小程序认证及备案任务流程id
   * @return 小程序认证及备案进度
   */
  WxOpenQueryAuthAndIcpResult queryAuthAndIcp(String procedureId) throws WxErrorException;

  /**
   * 提交小程序认证及备案信息。
   * @param param 提交小程序认证及备案信息参数
   * @return 提交结果
   */
  WxOpenSubmitAuthAndIcpResult submitAuthAndIcp(WxOpenSubmitAuthAndIcpParam param) throws WxErrorException;
}
