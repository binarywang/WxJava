package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.template.WxMpTemplate;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateIndustry;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.util.List;

/**
 * 模板消息接口
 * <p>
 * 提供微信模板消息的发送、行业设置、模板管理等功能。
 * 模板消息用于在用户触发特定事件后，向用户发送重要的服务通知。
 * </p>
 * <p>
 * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277">模板消息开发文档</a>
 * </p>
 * Created by Binary Wang on 2016-10-14.
 *
 * @author miller.lin
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMpTemplateMsgService {
    /**
     * <pre>
     * 设置所属行业
     * 官方文档中暂未告知响应内容
     * </pre>
     *
     * @param wxMpIndustry 行业信息
     * @return 是否成功设置行业
     * @throws WxErrorException 微信API调用异常，可能包括：
     *                          <ul>
     *                            <li>40001 - 获取access_token时AppSecret错误，或者access_token无效</li>
     *                            <li>40002 - 请确保grant_type字段值为client_credential</li>
     *                            <li>40003 - appid对应公众号请开发者使用绑定的公众号测试</li>
     *                            <li>40004 - appid不正确</li>
     *                            <li>40006 - access_token超时</li>
     *                            <li>48001 - api功能未授权</li>
     *                            <li>45009 - 调用接口的QPS超限</li>
     *                            <li>其他业务错误码</li>
     *                          </ul>
     * @see <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277">设置所属行业</a>
     */
    boolean setIndustry(WxMpTemplateIndustry wxMpIndustry) throws WxErrorException;

    /**
     * <pre>
     * 获取设置的行业信息
     * </pre>
     *
     * @return 行业信息对象
     * @throws WxErrorException 微信API调用异常，可能包括：
     *                          <ul>
     *                            <li>40001 - 获取access_token时AppSecret错误，或者access_token无效</li>
     *                            <li>40002 - 请确保grant_type字段值为client_credential</li>
     *                            <li>40003 - appid对应公众号请开发者使用绑定的公众号测试</li>
     *                            <li>40004 - appid不正确</li>
     *                            <li>40006 - access_token超时</li>
     *                            <li>48001 - api功能未授权</li>
     *                            <li>45009 - 调用接口的QPS超限</li>
     *                            <li>其他业务错误码</li>
     *                          </ul>
     * @see <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277">获取设置的行业信息</a>
     */
    WxMpTemplateIndustry getIndustry() throws WxErrorException;

    /**
     * <pre>
     * 发送模板消息
     * </pre>
     *
     * @param templateMessage 模板消息对象
     * @return 消息ID，可用于查询模板消息发送状态
     * @throws WxErrorException 微信API调用异常，可能包括：
     *                          <ul>
     *                            <li>40001 - 获取access_token时AppSecret错误，或者access_token无效</li>
     *                            <li>40002 - 请确保grant_type字段值为client_credential</li>
     *                            <li>40003 - appid对应公众号请开发者使用绑定的公众号测试</li>
     *                            <li>40004 - appid不正确</li>
     *                            <li>40006 - access_token超时</li>
     *                            <li>48001 - api功能未授权</li>
     *                            <li>45009 - 调用接口的QPS超限</li>
     *                            <li>其他业务错误码</li>
     *                          </ul>
     * @see <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277">发送模板消息</a>
     */
    String sendTemplateMsg(WxMpTemplateMessage templateMessage) throws WxErrorException;

    /**
     * <pre>
     * 获得模板ID
     * 从行业模板库选择模板到账号后台，获得模板ID的过程可在MP中完成
     * </pre>
     *
     * @param shortTemplateId 模板库中模板的编号，有"TM**"和"OPENTMTM**"等形式
     * @return 模板ID
     * @throws WxErrorException 微信API调用异常，可能包括：
     *                          <ul>
     *                            <li>40001 - 获取access_token时AppSecret错误，或者access_token无效</li>
     *                            <li>40002 - 请确保grant_type字段值为client_credential</li>
     *                            <li>40003 - appid对应公众号请开发者使用绑定的公众号测试</li>
     *                            <li>40004 - appid不正确</li>
     *                            <li>40006 - access_token超时</li>
     *                            <li>48001 - api功能未授权</li>
     *                            <li>45009 - 调用接口的QPS超限</li>
     *                            <li>其他业务错误码</li>
     *                          </ul>
     * @deprecated 请使用 {@link #addTemplate(String, List)}
     * @see <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277">获得模板ID</a>
     */
    @Deprecated
    String addTemplate(String shortTemplateId) throws WxErrorException;

    /**
     * <pre>
     * 获得模板ID
     * 从类目模板库选择模板到账号后台，获得模板ID的过程可在MP中完成
     * </pre>
     *
     * @param shortTemplateId  模板库中模板的编号，有"TM**"和"OPENTMTM**"等形式，对于类目模板，为纯数字ID
     * @param keywordNameList 选用的类目模板的关键词，按顺序传入，如果为空，或者关键词不在模板库中，会返回40246错误码
     * @return 模板ID
     * @throws WxErrorException 微信API调用异常，可能包括：
     *                          <ul>
     *                            <li>40001 - 获取access_token时AppSecret错误，或者access_token无效</li>
     *                            <li>40002 - 请确保grant_type字段值为client_credential</li>
     *                            <li>40003 - appid对应公众号请开发者使用绑定的公众号测试</li>
     *                            <li>40004 - appid不正确</li>
     *                            <li>40006 - access_token超时</li>
     *                            <li>48001 - api功能未授权</li>
     *                            <li>45009 - 调用接口的QPS超限</li>
     *                            <li>40246 - 关键词不在模板库中</li>
     *                            <li>其他业务错误码</li>
     *                          </ul>
     * @see <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277">获得模板ID</a>
     */
    String addTemplate(String shortTemplateId, List<String> keywordNameList) throws WxErrorException;

    /**
     * <pre>
     * 获取模板列表
     * 获取已添加至账号下所有模板列表，可在MP中查看模板列表信息，为方便第三方开发者，提供通过接口调用的方式来获取账号下所有模板信息
     * </pre>
     *
     * @return 模板列表，包含所有已添加的模板信息
     * @throws WxErrorException 微信API调用异常，可能包括：
     *                          <ul>
     *                            <li>40001 - 获取access_token时AppSecret错误，或者access_token无效</li>
     *                            <li>40002 - 请确保grant_type字段值为client_credential</li>
     *                            <li>40003 - appid对应公众号请开发者使用绑定的公众号测试</li>
     *                            <li>40004 - appid不正确</li>
     *                            <li>40006 - access_token超时</li>
     *                            <li>48001 - api功能未授权</li>
     *                            <li>45009 - 调用接口的QPS超限</li>
     *                            <li>其他业务错误码</li>
     *                          </ul>
     * @see <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277">获取模板列表</a>
     */
    List<WxMpTemplate> getAllPrivateTemplate() throws WxErrorException;

    /**
     * <pre>
     * 删除模板
     * 删除模板可在MP中完成，为方便第三方开发者，提供通过接口调用的方式来删除某账号下的模板
     * </pre>
     *
     * @param templateId 模板ID
     * @return 是否成功删除
     * @throws WxErrorException 微信API调用异常，可能包括：
     *                          <ul>
     *                            <li>40001 - 获取access_token时AppSecret错误，或者access_token无效</li>
     *                            <li>40002 - 请确保grant_type字段值为client_credential</li>
     *                            <li>40003 - appid对应公众号请开发者使用绑定的公众号测试</li>
     *                            <li>40004 - appid不正确</li>
     *                            <li>40006 - access_token超时</li>
     *                            <li>48001 - api功能未授权</li>
     *                            <li>45009 - 调用接口的QPS超限</li>
     *                            <li>其他业务错误码</li>
     *                          </ul>
     * @see <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277">删除模板</a>
     */
    boolean delPrivateTemplate(String templateId) throws WxErrorException;
}
