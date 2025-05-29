package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import me.chanjar.weixin.common.bean.subscribemsg.CategoryData;
import me.chanjar.weixin.common.bean.subscribemsg.PubTemplateKeyword;
import me.chanjar.weixin.common.bean.subscribemsg.TemplateInfo;
import me.chanjar.weixin.common.bean.subscribemsg.PubTemplateTitleListResult;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;

/**
 * 订阅消息类
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2019-12-15
 */
public interface WxMaSubscribeService {

  /**
   * <pre>
   * 获取账号所属类目下的公共模板标题
   *
   * 详情请见: <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/subscribe-message/subscribeMessage.getPubTemplateTitleList.html">获取账号所属类目下的公共模板标题</a>
   * 接口url格式: https://api.weixin.qq.com/wxaapi/newtmpl/getpubtemplatetitles?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param ids   类目 id，多个用逗号隔开
   * @param limit 用于分页，表示拉取 limit 条记录。最大为 30。
   * @param start 用于分页，表示从 start 开始。从 0 开始计数。
   * @return .
   * @throws WxErrorException .
   */
  PubTemplateTitleListResult getPubTemplateTitleList(String[] ids, int start, int limit) throws WxErrorException;

  /**
   * <pre>
   * 获取模板库某个模板标题下关键词库
   *
   * 详情请见: <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/subscribe-message/subscribeMessage.getPubTemplateKeyWordsById.html">获取模板标题下的关键词列表</a>
   * 接口url格式: GET https://api.weixin.qq.com/wxaapi/newtmpl/getpubtemplatekeywords?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param id 模板标题 id，可通过接口获取
   * @return .
   * @throws WxErrorException .
   */
  List<PubTemplateKeyword> getPubTemplateKeyWordsById(String id) throws WxErrorException;

  /**
   * <pre>
   * 组合模板并添加至账号下的个人模板库
   *
   * 详情请见: <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/subscribe-message/subscribeMessage.addTemplate.html">获取小程序模板库标题列表</a>
   * 接口url格式: POST https://api.weixin.qq.com/wxaapi/newtmpl/addtemplate?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param id            模板标题 id，可通过接口获取，也可登录小程序后台查看获取
   * @param keywordIdList 模板关键词列表
   * @param sceneDesc     服务场景描述，15个字以内
   * @return 添加至账号下的模板id，发送小程序订阅消息时所需
   * @throws WxErrorException .
   */
  String addTemplate(String id, List<Integer> keywordIdList, String sceneDesc) throws WxErrorException;

  /**
   * <pre>
   * 获取当前账号下的个人模板列表
   *
   * 详情请见: <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/subscribe-message/subscribeMessage.getTemplateList.html">获取当前账号下的个人模板列表</a>
   * 接口url格式: GET https://api.weixin.qq.com/wxaapi/newtmpl/gettemplate?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @return .
   * @throws WxErrorException .
   */
  List<TemplateInfo> getTemplateList() throws WxErrorException;

  /**
   * <pre>
   * 删除账号下的某个模板
   *
   * 详情请见: <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/subscribe-message/subscribeMessage.deleteTemplate.html">删除账号下的个人模板</a>
   * 接口url格式: POST https://api.weixin.qq.com/wxaapi/newtmpl/deltemplate?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param templateId 要删除的模板id
   * @return 删除是否成功
   * @throws WxErrorException .
   */
  boolean delTemplate(String templateId) throws WxErrorException;

  /**
   * <pre>
   * 获取小程序账号的类目
   * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/subscribe-message/subscribeMessage.getCategory.html
   * GET https://api.weixin.qq.com/wxaapi/newtmpl/getcategory?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @return .
   * @throws WxErrorException .
   */
  List<CategoryData> getCategory() throws WxErrorException;

  /**
   * <pre>
   * 发送订阅消息
   * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/subscribe-message/subscribeMessage.send.html
   * </pre>
   *
   * @param subscribeMessage 订阅消息
   * @throws WxErrorException .
   */
  void sendSubscribeMsg(WxMaSubscribeMessage subscribeMessage) throws WxErrorException;

}
