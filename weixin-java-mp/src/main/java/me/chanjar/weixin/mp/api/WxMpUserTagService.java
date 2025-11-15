package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.tag.WxTagListUser;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;

import java.util.List;

/**
 * 用户标签管理相关接口
 * <p>
 * 提供微信公众号用户标签的创建、查询、更新、删除等功能。
 * 通过标签可以方便地对用户进行分组管理和精准营销。
 * 一个公众号最多可以创建100个标签。
 * </p>
 * <p>
 * 详情请见：<a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837">用户标签管理</a>
 * </p>
 * Created by Binary Wang on 2016/9/2.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMpUserTagService {
    /**
     * <pre>
     * 创建标签
     * 一个公众号，最多可以创建100个标签。
     * </pre>
     *
     * @param name 标签名字（30个字符以内）
     * @return 创建的标签对象，包含标签ID等信息
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
     * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837">用户标签管理</a>
     * @see <a href="https://api.weixin.qq.com/cgi-bin/tags/create">创建标签接口</a>
     */
    WxUserTag tagCreate(String name) throws WxErrorException;

    /**
     * <pre>
     * 获取公众号已创建的标签
     * </pre>
     *
     * @return 标签列表，包含所有已创建的标签信息
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
     * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837">用户标签管理</a>
     * @see <a href="https://api.weixin.qq.com/cgi-bin/tags/get">获取标签接口</a>
     */
    List<WxUserTag> tagGet() throws WxErrorException;

    /**
     * <pre>
     * 编辑标签
     * 可以修改标签的名称，但不能修改标签ID。
     * </pre>
     *
     * @param tagId 标签ID，不能为null
     * @param name  新的标签名字（30个字符以内）
     * @return 操作是否成功，true表示成功，false表示失败
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
     * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837">用户标签管理</a>
     * @see <a href="https://api.weixin.qq.com/cgi-bin/tags/update">编辑标签接口</a>
     */
    Boolean tagUpdate(Long tagId, String name) throws WxErrorException;

    /**
     * <pre>
     * 删除标签
     * 删除标签后，该标签下的所有用户将被取消标签。
     * </pre>
     *
     * @param tagId 标签ID，不能为null
     * @return 操作是否成功，true表示成功，false表示失败
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
     * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837">用户标签管理</a>
     * @see <a href="https://api.weixin.qq.com/cgi-bin/tags/delete">删除标签接口</a>
     */
    Boolean tagDelete(Long tagId) throws WxErrorException;

    /**
     * <pre>
     * 获取标签下粉丝列表
     * 可用于获取某个标签下的所有用户信息，支持分页查询。
     * </pre>
     *
     * @param tagId      标签ID，不能为null
     * @param nextOpenid 第一个拉取用户的openid，不填从头开始拉取
     * @return 标签下粉丝列表对象，包含用户信息和分页信息
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
     * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837">用户标签管理</a>
     * @see <a href="https://api.weixin.qq.com/cgi-bin/user/tag/get">获取标签下粉丝列表接口</a>
     */
    WxTagListUser tagListUser(Long tagId, String nextOpenid) throws WxErrorException;

    /**
     * <pre>
     * 批量为用户打标签
     * 可以为多个用户同时打上同一个标签。
     * </pre>
     *
     * @param tagId   标签ID，不能为null
     * @param openids 用户openid数组，不能为null或空数组
     * @return 操作是否成功，true表示成功，false表示失败
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
     * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837">用户标签管理</a>
     * @see <a href="https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging">批量为用户打标签接口</a>
     */
    boolean batchTagging(Long tagId, String[] openids) throws WxErrorException;

    /**
     * <pre>
     * 批量为用户取消标签
     * 可以为多个用户同时取消同一个标签。
     * </pre>
     *
     * @param tagId   标签ID，不能为null
     * @param openids 用户openid数组，不能为null或空数组
     * @return 操作是否成功，true表示成功，false表示失败
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
     * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837">用户标签管理</a>
     * @see <a href="https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging">批量为用户取消标签接口</a>
     */
    boolean batchUntagging(Long tagId, String[] openids) throws WxErrorException;

    /**
     * <pre>
     * 获取用户身上的标签列表
     * 可查询某个用户被打上的所有标签ID。
     * </pre>
     *
     * @param openid 用户的openid，不能为null或空字符串
     * @return 标签ID的列表，表示该用户被打上的所有标签
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
     * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837">用户标签管理</a>
     * @see <a href="https://api.weixin.qq.com/cgi-bin/tags/getidlist">获取用户身上的标签列表接口</a>
     */
    List<Long> userTagList(String openid) throws WxErrorException;
}
