package me.chanjar.weixin.cp.tp.service;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpTpConvertTmpExternalUserIdResult;
import me.chanjar.weixin.cp.bean.WxCpTpOpenKfIdConvertResult;
import me.chanjar.weixin.cp.bean.WxCpTpTagIdListConvertResult;
import me.chanjar.weixin.cp.bean.WxCpTpUnionidToExternalUseridResult;

/**
 * <pre>
 *  企业微信三方应用ID转换接口
 *
 * </pre>
 *
 * @author cocoa
 */
public interface WxCpTpIdConvertService {

  /**
   * unionid与external_userid的关联
   * <a href="https://developer.work.weixin.qq.com/document/path/95900">查看文档</a>
   *
   * @param unionid     微信客户的unionid
   * @param openid      微信客户的openid
   * @param subjectType 程序或公众号的主体类型： 0表示主体名称是企业的，1表示主体名称是服务商的
   * @throws WxErrorException 。
   */
  WxCpTpUnionidToExternalUseridResult unionidToExternalUserid(String cropId, String unionid, String openid,
                                                              Integer subjectType) throws WxErrorException;


  /**
   * 将企业主体下的客户标签ID转换成服务商主体下的客户标签ID
   * @param corpId             企业微信 ID
   * @param externalTagIdList  企业主体下的客户标签ID列表，最多不超过1000个
   * @return                    客户标签转换结果
   * @throws WxErrorException .
   */
  WxCpTpTagIdListConvertResult externalTagId(String corpId, String... externalTagIdList) throws WxErrorException;

  /**
   * 将企业主体下的微信客服ID转换成服务商主体下的微信客服ID
   * @param corpId             企业微信 ID
   * @param openKfIdList       微信客服ID列表，最多不超过1000个
   * @return                   微信客服ID转换结果
   * @throws WxErrorException  .
   */
  WxCpTpOpenKfIdConvertResult ConvertOpenKfId (String corpId, String... openKfIdList ) throws WxErrorException;

  /**
   * 将应用获取的外部用户临时idtmp_external_userid，转换为external_userid
   * @param corpId                 企业微信Id
   * @param businessType           业务类型。1-会议 2-收集表
   * @param userType               转换的目标用户类型。1-客户 2-企业互联 3-上下游 4-互联企业（圈子）
   * @param tmpExternalUserIdList  外部用户临时id，最多不超过100个
   * @return                       转换成功的结果列表
   */
  WxCpTpConvertTmpExternalUserIdResult convertTmpExternalUserId(String corpId, int businessType, int userType, String... tmpExternalUserIdList) throws WxErrorException;
}
