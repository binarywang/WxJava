package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.bean.subscribemsg.CategoryData;
import me.chanjar.weixin.common.bean.subscribemsg.PubTemplateKeyword;
import me.chanjar.weixin.common.bean.subscribemsg.PubTemplateTitleListResult;
import me.chanjar.weixin.common.bean.subscribemsg.TemplateInfo;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.subscribe.WxMpSubscribeMessage;

import java.util.List;

/**
 * <pre>
 * 订阅消息服务接口
 * </pre>
 *
 * @author Mklaus  created on  2018 -01-22 上午11:07
 */
public interface WxMpSubscribeMsgService {
    /**
     * <pre>
     * 构造用户订阅一条模板消息授权的url连接
     * 详情请见: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1500374289_66bvB
     * </pre>
     *
     * @param redirectURI 用户授权完成后的重定向链接，无需urlencode, 方法内会进行encode
     * @param scene       重定向后会带上scene参数，开发者可以填0-10000的整形值，用来标识订阅场景值
     * @param reserved    用于保持请求和回调的状态，授权请后原样带回给第三方 (最多128字节，要求做urlencode)
     * @return url string
     */
    String subscribeMsgAuthorizationUrl(String redirectURI, int scene, String reserved);

    /**
     * <pre>
     * 发送一次性订阅消息
     * 详情请见: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1500374289_66bvB
     * </pre>
     *
     * @param message the message
     * @return 消息Id boolean
     * @throws WxErrorException the wx error exception
     */
    boolean sendOnce(WxMpSubscribeMessage message) throws WxErrorException;

    /**
     * <pre>
     * 获取账号所属类目下的公共模板标题
     *
     * 详情请见: <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/subscribe-message/subscribeMessage.getPubTemplateTitleList.html">获取账号所属类目下的公共模板标题</a>
     * 接口url格式: https://api.weixin.qq.com/wxaapi/newtmpl/getpubtemplatetitles?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param ids   类目 id，多个用逗号隔开
     * @param start 用于分页，表示从 start 开始。从 0 开始计数。
     * @param limit 用于分页，表示拉取 limit 条记录。最大为 30。
     * @return . pub template title list
     * @throws WxErrorException .
     */
    PubTemplateTitleListResult getPubTemplateTitleList(String[] ids, int start, int limit) throws WxErrorException;

    /**
     * <pre>
     * 获取模板库某个模板标题下关键词库
     *
     * 详情请见: https://developers.weixin.qq.com/doc/offiaccount/Subscription_Messages/api.html
     * 接口url格式: GET https://api.weixin.qq.com/wxaapi/newtmpl/getpubtemplatekeywords?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param id 模板标题 id，可通过接口获取
     * @return . pub template key words by id
     * @throws WxErrorException .
     */
    List<PubTemplateKeyword> getPubTemplateKeyWordsById(String id) throws WxErrorException;

    /**
     * <pre>
     * 组合模板并添加至账号下的个人模板库
     *
     * 详情请见: https://developers.weixin.qq.com/doc/offiaccount/Subscription_Messages/api.html
     * 接口url格式: POST https://api.weixin.qq.com/wxaapi/newtmpl/addtemplate?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param id            模板标题 id，可通过接口获取，也可登录小程序后台查看获取
     * @param keywordIdList 模板关键词列表
     * @param sceneDesc     服务场景描述，15个字以内
     * @return 添加至账号下的模板id ，发送小程序订阅消息时所需
     * @throws WxErrorException .
     */
    String addTemplate(String id, List<Integer> keywordIdList, String sceneDesc) throws WxErrorException;

    /**
     * <pre>
     * 获取当前账号下的个人模板列表
     *
     * 详情请见: https://developers.weixin.qq.com/doc/offiaccount/Subscription_Messages/api.html
     * 接口url格式: GET https://api.weixin.qq.com/wxaapi/newtmpl/gettemplate?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @return . template list
     * @throws WxErrorException .
     */
    List<TemplateInfo> getTemplateList() throws WxErrorException;

    /**
     * <pre>
     * 删除账号下的某个模板
     *
     * 详情请见: https://developers.weixin.qq.com/doc/offiaccount/Subscription_Messages/api.html
     * 接口url格式: POST https://api.weixin.qq.com/wxaapi/newtmpl/deltemplate?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param templateId 要删除的模板id
     * @return 删除是否成功 boolean
     * @throws WxErrorException .
     */
    boolean delTemplate(String templateId) throws WxErrorException;

    /**
     * <pre>
     * 获取公众号类目
     * https://developers.weixin.qq.com/doc/offiaccount/Subscription_Messages/api.html
     * GET https://api.weixin.qq.com/wxaapi/newtmpl/getcategory?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @return . category
     * @throws WxErrorException .
     */
    List<CategoryData> getCategory() throws WxErrorException;

    /**
     * <pre>
     * 发送订阅消息
     * https://developers.weixin.qq.com/doc/offiaccount/Subscription_Messages/api.html
     * </pre>
     *
     * @param subscribeMessage 订阅消息
     * @return 下发消息id，与下发结果回调的msgId对应
     * @throws WxErrorException .
     */
    String send(WxMpSubscribeMessage subscribeMessage) throws WxErrorException;

}
