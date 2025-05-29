package me.chanjar.weixin.cp.tp.service;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.license.WxCpTpLicenseActiveAccount;
import me.chanjar.weixin.cp.bean.license.WxCpTpLicenseTransfer;
import me.chanjar.weixin.cp.bean.license.account.*;
import me.chanjar.weixin.cp.bean.license.order.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 * 服务商接口调用许可相关接口
 * 文档地址：https://developer.work.weixin.qq.com/document/path/95652
 * </pre>
 *
 * @author Totoro  created on  2022/6/27 10:57
 */
public interface WxCpTpLicenseService {


  /**
   * 下单购买账号
   * 服务商下单为企业购买新的账号，可以同时购买基础账号与互通账号。
   * 下单之后，需要到服务商管理端发起支付，支付完成之后，订单才能生效。
   * 文档地址：https://developer.work.weixin.qq.com/document/path/95644
   *
   * @param licenseNewOrderRequest 订单信息
   * @return 订单ID wx cp tp license create order resp
   * @throws WxErrorException ;
   */
  WxCpTpLicenseCreateOrderResp createNewOrder(WxCpTpLicenseNewOrderRequest licenseNewOrderRequest) throws WxErrorException;


  /**
   * 创建下单续期账号任务
   * <pre>
   *  可以下单为一批已激活账号的成员续期，续期下单分为两个步骤：
   * 传入userid列表创建一个任务，创建之后，可以往同一个任务继续追加待续期的userid列表；
   * 根据步骤1得到的jobid提交订单。
   * </pre>
   *
   * @param licenseRenewOrderJobRequest 续费订单信息
   * @return 返回JobId wx cp tp license renew order job resp
   * @throws WxErrorException ;
   */
  WxCpTpLicenseRenewOrderJobResp createRenewOrderJob(WxCpTpLicenseRenewOrderJobRequest licenseRenewOrderJobRequest) throws WxErrorException;


  /**
   * 提交续期订单
   * 创建续期任务之后，需要调用该接口，以提交订单任务。
   * 注意，提交之后，需要到服务商管理端发起支付，支付完成之后，订单才能生效。
   * 文档地址：https://developer.work.weixin.qq.com/document/path/95646
   *
   * @param licenseRenewOrderRequest 订单信息
   * @return 订单ID wx cp tp license create order resp
   * @throws WxErrorException ;
   */
  WxCpTpLicenseCreateOrderResp submitRenewOrder(WxCpTpLicenseRenewOrderRequest licenseRenewOrderRequest) throws WxErrorException;


  /**
   * 获取订单列表
   * 服务商查询自己某段时间内的平台能力服务订单列表
   * 文档地址：https://developer.work.weixin.qq.com/document/path/95647
   *
   * @param corpId    企业ID
   * @param startTime 开始时间,下单时间。可不填。但是不能单独指定该字段，start_time跟end_time必须同时指定。
   * @param endTime   结束时间,下单时间。起始时间跟结束时间不能超过31天。可不填。但是不能单独指定该字段，start_time跟end_time必须同时指定。
   * @param cursor    用于分页查询的游标，字符串类型，由上一次调用返回，首次调用可不填
   * @param limit     返回的最大记录数，整型，最大值1000，默认值500
   * @return 订单列表 order list
   * @throws WxErrorException ;
   */
  WxCpTpLicenseOrderListResp getOrderList(String corpId, Date startTime, Date endTime, String cursor, int limit) throws WxErrorException;


  /**
   * 获取订单详情
   * 查询某个订单的详情，包括订单的状态、基础账号个数、互通账号个数、账号购买时长等。
   * 注意，该接口不返回订单中的账号激活码列表或者续期的账号成员列表，请调用获取订单中的账号列表接口以获取账号列表。
   *
   * @param orderId 订单ID
   * @return 单条订单信息 order info
   * @throws WxErrorException ;
   */
  WxCpTpLicenseOrderInfoResp getOrderInfo(String orderId) throws WxErrorException;


  /**
   * 查询指定订单下的平台能力服务账号列表。
   * 若为购买账号的订单或者存量企业的版本付费迁移订单，则返回账号激活码列表；
   * 若为续期账号的订单，则返回续期账号的成员列表。注意，若是购买账号的订单，
   * 则仅订单支付完成时，系统才会生成账号，故支付完成之前，该接口不会返回账号激活码。
   * 文档地址：https://developer.work.weixin.qq.com/document/path/95649
   *
   * @param orderId 订单ID
   * @param limit   大小
   * @param cursor  分页游标
   * @return 订单账号列表 order account list
   * @throws WxErrorException ;
   */
  WxCpTpLicenseOrderAccountListResp getOrderAccountList(String orderId, int limit, String cursor) throws WxErrorException;


  /**
   * 激活账号
   * 下单购买账号并支付完成之后，先调用获取订单中的账号列表接口获取到账号激活码，
   * 然后可以调用该接口将激活码绑定到某个企业员工，以对其激活相应的平台服务能力。
   * 文档地址：https://developer.work.weixin.qq.com/document/path/95553
   *
   * @param code   激活码
   * @param corpId 企业ID
   * @param userId 用户ID
   * @return 激活结果 wx cp base resp
   * @throws WxErrorException ;
   */
  WxCpBaseResp activeCode(String code, String corpId, String userId) throws WxErrorException;


  /**
   * 批量激活账号
   * 可在一次请求里为一个企业的多个成员激活许可账号，便于服务商批量化处理。
   * 一个userid允许激活一个基础账号以及一个互通账号。
   * 单次激活的员工数量不超过1000
   *
   * @param corpId            企业ID
   * @param activeAccountList 激活列表
   * @return 激活结果 wx cp tp license batch active result resp
   * @throws WxErrorException ;
   */
  WxCpTpLicenseBatchActiveResultResp batchActiveCode(String corpId,
                                                     List<WxCpTpLicenseActiveAccount> activeAccountList) throws WxErrorException;


  /**
   * 获取激活码详情
   * 查询某个账号激活码的状态以及激活绑定情况。
   * 文档地址：https://developer.work.weixin.qq.com/document/path/95552
   *
   * @param code   激活码
   * @param corpId 企业ID
   * @return 激活码信息 active info by code
   * @throws WxErrorException ;
   */
  WxCpTpLicenseCodeInfoResp getActiveInfoByCode(String code, String corpId) throws WxErrorException;


  /**
   * 获取激活码详情
   * 查询某个账号激活码的状态以及激活绑定情况。
   * 文档地址：https://developer.work.weixin.qq.com/document/path/95552
   *
   * @param codes  激活码
   * @param corpId 企业ID
   * @return 激活码信息 wx cp tp license batch code info resp
   * @throws WxErrorException ;
   */
  WxCpTpLicenseBatchCodeInfoResp batchGetActiveInfoByCode(Collection<String> codes, String corpId) throws WxErrorException;


  /**
   * 获取企业的账号列表
   * 查询指定企业下的平台能力服务账号列表。
   * 文档地址：https://developer.work.weixin.qq.com/document/path/95544
   *
   * @param corpId 企业ID
   * @param limit  大小
   * @param cursor 游标
   * @return 已激活列表 corp account list
   * @throws WxErrorException the wx error exception
   */
  WxCpTpLicenseCorpAccountListResp getCorpAccountList(String corpId, int limit, String cursor) throws WxErrorException;


  /**
   * 获取成员的激活详情
   * 查询某个企业成员的激活情况。
   * 文档地址：https://developer.work.weixin.qq.com/document/path/95555
   *
   * @param corpId 企业ID
   * @param userId 用户ID
   * @return 激活情况 active info by user
   * @throws WxErrorException ;
   */
  WxCpTpLicenseActiveInfoByUserResp getActiveInfoByUser(String corpId, String userId) throws WxErrorException;


  /**
   * 账号继承
   * 在企业员工离职或者工作范围的有变更时，允许将其许可账号继承给其他员工。
   *
   * @param corpId       企业ID
   * @param transferList 转移列表
   * @return 转移结果 wx cp tp license batch transfer resp
   * @throws WxErrorException ;
   */
  WxCpTpLicenseBatchTransferResp batchTransferLicense(String corpId, List<WxCpTpLicenseTransfer> transferList) throws WxErrorException;


}
