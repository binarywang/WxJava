package me.chanjar.weixin.cp.tp.service;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpTpDepart;

import java.util.List;

/**
 * <pre>
 *  部门管理接口
 *  Created by jamie on 2020/7/22.
 * </pre>
 */
public interface WxCpTpDepartmentService {

  /**
   * <pre>
   * 部门管理接口 - 创建部门.
   * 最多支持创建500个部门
   * 详情请见: <a href="https://work.weixin.qq.com/api/doc#90000/90135/90205">...</a>
   * </pre>
   *
   * @param depart 部门
   * @return 部门id long
   * @throws WxErrorException 异常
   */
  Long create(WxCpTpDepart depart) throws WxErrorException;

  /**
   * <pre>
   * 部门管理接口 - 获取部门列表.
   * 详情请见: <a href="https://work.weixin.qq.com/api/doc#90000/90135/90208">...</a>
   * </pre>
   *
   * @param id     部门id。获取指定部门及其下的子部门。非必需，可为null
   * @param corpId the corp id
   * @return 获取的部门列表 list
   * @throws WxErrorException 异常
   */
  List<WxCpTpDepart> list(Long id, String corpId) throws WxErrorException;

  /**
   * <pre>
   * 部门管理接口 - 更新部门.
   * 详情请见: <a href="https://work.weixin.qq.com/api/doc#90000/90135/90206">...</a>
   * 如果id为0(未部门),1(黑名单),2(星标组)，或者不存在的id，微信会返回系统繁忙的错误
   * </pre>
   *
   * @param group 要更新的group，group的id,name必须设置
   * @throws WxErrorException 异常
   */
  void update(WxCpTpDepart group) throws WxErrorException;

  /**
   * <pre>
   * 部门管理接口 - 删除部门.
   * 详情请见: <a href="https://work.weixin.qq.com/api/doc#90000/90135/90207">...</a>
   * 应用须拥有指定部门的管理权限
   * </pre>
   *
   * @param departId 部门id
   * @throws WxErrorException 异常
   */
  void delete(Long departId) throws WxErrorException;

  /**
   * <pre>
   * 部门管理接口 - 获取部门列表.
   * 详情请见: <a href="https://work.weixin.qq.com/api/doc#90000/90135/90208">...</a>
   * </pre>
   *
   * @param corpId the corp id
   * @return 获取所有的部门列表 list
   * @throws WxErrorException 异常
   */
  List<WxCpTpDepart> list(String corpId) throws WxErrorException;
}
