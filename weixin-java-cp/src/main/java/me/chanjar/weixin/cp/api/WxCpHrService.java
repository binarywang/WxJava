package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.hr.WxCpHrEmployeeFieldData;
import me.chanjar.weixin.cp.bean.hr.WxCpHrEmployeeFieldDataResp;
import me.chanjar.weixin.cp.bean.hr.WxCpHrEmployeeFieldInfoResp;

import java.util.List;

/**
 * 人事助手相关接口.
 * 官方文档：<a href="https://developer.work.weixin.qq.com/document/path/99132">...</a>
 *
 * @author copilot
 */
public interface WxCpHrService {

  /**
   * 获取员工档案字段信息.
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：<a href="https://qyapi.weixin.qq.com/cgi-bin/hr/get_fields?access_token=ACCESS_TOKEN">...</a>
   * 权限说明：
   * 需要配置人事助手的secret，调用接口前需给对应成员赋予人事小助手应用的权限。
   *
   * @param fields 指定字段key列表，不填则返回全部字段
   * @return 字段信息响应 wx cp hr employee field info resp
   * @throws WxErrorException the wx error exception
   */
  WxCpHrEmployeeFieldInfoResp getFieldInfo(List<String> fields) throws WxErrorException;

  /**
   * 获取员工档案数据.
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：<a href="https://qyapi.weixin.qq.com/cgi-bin/hr/get_staff_info?access_token=ACCESS_TOKEN">...</a>
   * 权限说明：
   * 需要配置人事助手的secret，调用接口前需给对应成员赋予人事小助手应用的权限。
   *
   * @param userid 员工userid
   * @param fields 指定字段key列表
   * @return 员工档案数据响应 wx cp hr employee field data resp
   * @throws WxErrorException the wx error exception
   */
  WxCpHrEmployeeFieldDataResp getEmployeeFieldInfo(String userid, List<String> fields) throws WxErrorException;

  /**
   * 获取员工档案数据.
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：<a href="https://qyapi.weixin.qq.com/cgi-bin/hr/get_staff_info?access_token=ACCESS_TOKEN">...</a>
   */
  WxCpHrEmployeeFieldDataResp getEmployeeFieldInfo(String userid, boolean getAll, List<String> fields) throws WxErrorException;

  /**
   * 更新员工档案数据.
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：<a href="https://qyapi.weixin.qq.com/cgi-bin/hr/update_staff_info?access_token=ACCESS_TOKEN">...</a>
   * 权限说明：
   * 需要配置人事助手的secret，调用接口前需给对应成员赋予人事小助手应用的权限。
   *
   * @param userid    员工userid
   * @param fieldList 字段数据列表
   * @throws WxErrorException the wx error exception
   */
  void updateEmployeeFieldInfo(String userid, List<WxCpHrEmployeeFieldData.FieldItem> fieldList) throws WxErrorException;
}
