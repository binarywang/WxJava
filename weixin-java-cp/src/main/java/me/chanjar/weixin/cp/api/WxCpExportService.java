package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.export.WxCpExportRequest;
import me.chanjar.weixin.cp.bean.export.WxCpExportResult;

/**
 * 异步导出接口
 *
 * @author <a href="https://github.com/zhongjun96">zhongjun</a> created on  2022/4/21
 */
public interface WxCpExportService {

  /**
   * <pre>
   *
   * 导出成员
   *
   * 请求方式：POST（HTTPS）
   * 请求地址：<a href="https://qyapi.weixin.qq.com/cgi-bin/export/simple_user?access_token=ACCESS_TOKEN">https://qyapi.weixin.qq.com/cgi-bin/export/simple_user?access_token=ACCESS_TOKEN</a>
   *
   * 文档地址：<a href="https://developer.work.weixin.qq.com/document/path/94849">https://developer.work.weixin.qq.com/document/path/94849</a>
   * </pre>
   *
   * @param params 导出参数
   * @return jobId 异步任务id
   * @throws WxErrorException .
   */
  String simpleUser(WxCpExportRequest params) throws WxErrorException;

  /**
   * <pre>
   *
   * 导出成员详情
   *
   * 请求方式：POST（HTTPS）
   * 请求地址：<a href="https://qyapi.weixin.qq.com/cgi-bin/export/user?access_token=ACCESS_TOKEN">https://qyapi.weixin.qq.com/cgi-bin/export/user?access_token=ACCESS_TOKEN</a>
   *
   * 文档地址：<a href="https://developer.work.weixin.qq.com/document/path/94851">https://developer.work.weixin.qq.com/document/path/94851</a>
   * </pre>
   *
   * @param params 导出参数
   * @return jobId 异步任务id
   * @throws WxErrorException .
   */
  String user(WxCpExportRequest params) throws WxErrorException;

  /**
   * <pre>
   *
   * 导出部门
   *
   * 请求方式：POST（HTTPS）
   * 请求地址：<a href="https://qyapi.weixin.qq.com/cgi-bin/export/department?access_token=ACCESS_TOKEN">https://qyapi.weixin.qq.com/cgi-bin/export/department?access_token=ACCESS_TOKEN</a>
   *
   * 文档地址：<a href="https://developer.work.weixin.qq.com/document/path/94852">https://developer.work.weixin.qq.com/document/path/94852</a>
   * </pre>
   *
   * @param params 导出参数
   * @return jobId 异步任务id
   * @throws WxErrorException .
   */
  String department(WxCpExportRequest params) throws WxErrorException;

  /**
   * <pre>
   *
   * 导出标签成员
   *
   * 请求方式：POST（HTTPS）
   * 请求地址：<a href="https://qyapi.weixin.qq.com/cgi-bin/export/taguser?access_token=ACCESS_TOKEN">https://qyapi.weixin.qq.com/cgi-bin/export/taguser?access_token=ACCESS_TOKEN</a>
   *
   * 文档地址：<a href="https://developer.work.weixin.qq.com/document/path/94853">https://developer.work.weixin.qq.com/document/path/94853</a>
   * </pre>
   *
   * @param params 导出参数
   * @return jobId 异步任务id
   * @throws WxErrorException .
   */
  String tagUser(WxCpExportRequest params) throws WxErrorException;

  /**
   * <pre>
   *
   * 获取导出结果
   *
   * 请求方式：GET（HTTPS）
   * {@code 请求地址：<a href="https://qyapi.weixin.qq.com/cgi-bin/export/get_result?access_token=ACCESS_TOKEN&jobid=jobid_xxxxxxxxxxxxxxx">https://qyapi.weixin.qq.com/cgi-bin/export/get_result?access_token=ACCESS_TOKEN&jobid=jobid_xxxxxxxxxxxxxxx</a>}
   *
   * 文档地址：<a href="https://developer.work.weixin.qq.com/document/path/94854">https://developer.work.weixin.qq.com/document/path/94854</a>
   * 返回的url文件下载解密可参考 <a href="https://blog.csdn.net/a201692/article/details/123530529">CSDN</a>
   * </pre>
   *
   * @param jobId 异步任务id
   * @return 导出结果 result
   * @throws WxErrorException .
   */
  WxCpExportResult getResult(String jobId) throws WxErrorException;
}
