package me.chanjar.weixin.mp.api;

import java.io.File;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.enums.AiLangType;

/**
 * 微信AI开放接口（语音识别，微信翻译）
 * <p>
 * 提供微信AI相关的功能，包括语音识别、微信翻译等。
 * 支持上传语音文件进行语音识别，以及文本翻译功能。
 * </p>
 * <p>
 * 详情请见：<a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=21516712282KzWVE">微信AI开放接口</a>
 * </p>
 * Created by BinaryWang on 2018/6/9.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMpAiOpenService {

    /**
     * <pre>
     * 提交语音
     * </pre>
     *
     * @param voiceId   语音唯一标识
     * @param lang      语言，zh_CN 或 en_US，默认中文
     * @param voiceFile 语音文件
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
     * @see <a href="http://api.weixin.qq.com/cgi-bin/media/voice/addvoicetorecofortext">提交语音接口</a>
     */
    void uploadVoice(String voiceId, AiLangType lang, File voiceFile) throws WxErrorException;

    /**
     * <pre>
     * 获取语音识别结果
     * 请注意，添加完文件之后10s内调用这个接口
     * </pre>
     *
     * @param voiceId 语音唯一标识
     * @param lang    语言，zh_CN 或 en_US，默认中文
     * @return 语音识别结果文本
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
     * @see <a href="http://api.weixin.qq.com/cgi-bin/media/voice/queryrecoresultfortext">获取语音识别结果接口</a>
     */
    String queryRecognitionResult(String voiceId, AiLangType lang) throws WxErrorException;

    /**
     * <pre>
     * 识别指定语音文件内容
     * 此方法揉合了前两两个方法：uploadVoice 和 queryRecognitionResult
     * </pre>
     *
     * @param voiceId   语音唯一标识
     * @param lang      语言，zh_CN 或 en_US，默认中文
     * @param voiceFile 语音文件
     * @return 语音识别结果文本
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
     */
    String recogniseVoice(String voiceId, AiLangType lang, File voiceFile) throws WxErrorException;

    /**
     * <pre>
     * 微信翻译
     * </pre>
     *
     * @param langFrom 源语言，zh_CN 或 en_US
     * @param langTo   目标语言，zh_CN 或 en_US
     * @param content  要翻译的文本内容
     * @return 翻译结果文本
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
     * @see <a href="http://api.weixin.qq.com/cgi-bin/media/voice/translatecontent">微信翻译接口</a>
     */
    String translate(AiLangType langFrom, AiLangType langTo, String content) throws WxErrorException;
}
