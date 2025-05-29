package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.template.WxMpTemplate;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateIndustry;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.util.List;

/**
 * <pre>
 * 模板消息接口
 * http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277&token=&lang=zh_CN
 * Created by Binary Wang on 2016-10-14.
 * @author miller.lin
 * @author  <a href="https://github.com/binarywang">Binary Wang</a> </pre>
 */
public interface WxMpTemplateMsgService {
  /**
   * <pre>
   * 设置所属行业
   * 官方文档中暂未告知响应内容
   * 详情请见：http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277&token=&lang=zh_CN
   * </pre>
   *
   * @param wxMpIndustry 行业信息
   * @return 是否成功 industry
   * @throws WxErrorException .
   */
  boolean setIndustry(WxMpTemplateIndustry wxMpIndustry) throws WxErrorException;

  /***
   * <pre>
   * 获取设置的行业信息
   * 详情请见：http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277&token=&lang=zh_CN
   * </pre>
   *
   * @return wxMpIndustry industry
   * @throws WxErrorException .
   */
  WxMpTemplateIndustry getIndustry() throws WxErrorException;

  /**
   * <pre>
   * 发送模板消息
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277&token=&lang=zh_CN
   * </pre>
   *
   * @param templateMessage 模板消息
   * @return 消息Id string
   * @throws WxErrorException .
   */
  String sendTemplateMsg(WxMpTemplateMessage templateMessage) throws WxErrorException;

  /**
   * <pre>
   * 获得模板ID
   * 从行业模板库选择模板到账号后台，获得模板ID的过程可在MP中完成
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277&token=&lang=zh_CN
   * 接口地址格式：https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param shortTemplateId 模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
   * @return templateId 模板Id
   * @throws WxErrorException .
   * @deprecated 请使用 addTemplate(java.lang.String, java.util.List)
   */
  @Deprecated
  String addTemplate(String shortTemplateId) throws WxErrorException;

  /**
   * <pre>
   * 获得模板ID
   * 从类目模板库选择模板到账号后台，获得模板ID的过程可在MP中完成
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277&token=&lang=zh_CN
   * 接口地址格式：https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param shortTemplateId 模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式,对于类目模板，为纯数字ID
   * @param keywordNameList 选用的类目模板的关键词,按顺序传入,如果为空，或者关键词不在模板库中，会返回40246错误码
   * @return templateId 模板Id
   * @throws WxErrorException .
   */
  String addTemplate(String shortTemplateId, List<String> keywordNameList) throws WxErrorException;

  /**
   * <pre>
   * 获取模板列表
   * 获取已添加至账号下所有模板列表，可在MP中查看模板列表信息，为方便第三方开发者，提供通过接口调用的方式来获取账号下所有模板信息
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277&token=&lang=zh_CN
   * 接口地址格式：https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @return templateId 模板Id
   * @throws WxErrorException .
   */
  List<WxMpTemplate> getAllPrivateTemplate() throws WxErrorException;

  /**
   * <pre>
   * 删除模板
   * 删除模板可在MP中完成，为方便第三方开发者，提供通过接口调用的方式来删除某账号下的模板
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277&token=&lang=zh_CN
   * 接口地址格式：https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param templateId 模板Id
   * @return . boolean
   * @throws WxErrorException .
   */
  boolean delPrivateTemplate(String templateId) throws WxErrorException;
}
