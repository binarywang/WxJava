package me.chanjar.weixin.cp.tp.service;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpTpProlongTryResult;


/**
 * 应用版本付费版本相关接口
 *
 * @author leiguoqing  created on  2022年4月24日
 */
public interface WxCpTpEditionService {

  /**
   * 延长试用期
   * <p>
   * <a href='https://developer.work.weixin.qq.com/document/path/91913'>文档地址</a>
   * </p>
   * 注意:
   * <ul>
   *     <li>一个应用可以多次延长试用，但是试用总天数不能超过60天</li>
   *     <li>仅限时试用或试用过期状态下的应用可以延长试用期</li>
   * </ul>
   *
   * @param buyerCorpId 购买方corpId
   * @param prolongDays 延长天数
   * @param appId       仅旧套件需要填此参数
   * @return the order
   * @throws WxErrorException the wx error exception
   */
  WxCpTpProlongTryResult prolongTry(String buyerCorpId, Integer prolongDays, String appId) throws WxErrorException;
}
