package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.bean.WxCardApiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.card.*;

import java.util.List;

/**
 * 卡券相关接口
 * <p>
 * 提供微信卡券的创建、查询、核销、管理等功能。
 * 支持卡券API签名生成、卡券Code解码、卡券核销、库存管理等功能。
 * </p>
 * <p>
 * 详情请见：<a href="https://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html">卡券开发文档</a>
 * </p>
 *
 * @author YuJian(mgcnrx11 @ hotmail.com) on 01/11/2016
 * @author yuanqixun 2018-08-29
 */
public interface WxMpCardService {
    /**
     * <pre>
     * 获取WxMpService实例
     * </pre>
     *
     * @return WxMpService实例
     */
    WxMpService getWxMpService();

    /**
     * <pre>
     * 获得卡券api_ticket，不强制刷新卡券api_ticket
     * </pre>
     *
     * @return 卡券api_ticket
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
     * @see #getCardApiTicket(boolean)
     */
    String getCardApiTicket() throws WxErrorException;

    /**
     * <pre>
     * 获得卡券api_ticket
     * 获得时会检查卡券apiToken是否过期，如果过期了，那么就刷新一下，否则就什么都不干
     * </pre>
     *
     * @param forceRefresh 强制刷新，如果为true则强制刷新api_ticket
     * @return 卡券api_ticket
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
     * @see <a href="https://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html#.E9.99.84.E5.BD.954-.E5.8D.A1.E5.88.B8.E6.89.A9.E5.B1.95.E5.AD.97.E6.AE.B5.E5.8F.8A.E7.AD.BE.E5.90.8D.E7.94.9F.E6.88.90.E7.AE.97.E6.B3.95">卡券签名生成算法</a>
     */
    String getCardApiTicket(boolean forceRefresh) throws WxErrorException;

    /**
     * <pre>
     * 创建调用卡券api时所需要的签名
     * </pre>
     *
     * @param optionalSignParam 参与签名的参数数组。可以为下列字段：app_id, card_id, card_type, code, openid, location_id
     *                          注意：当做wx.chooseCard调用时，必须传入app_id参与签名，否则会造成签名失败导致拉取卡券列表为空
     * @return 卡券Api签名对象
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
     * @see <a href="https://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html#.E9.99.84.E5.BD.954-.E5.8D.A1.E5.88.B8.E6.89.A9.E5.B1.95.E5.AD.97.E6.AE.B5.E5.8F.8A.E7.AD.BE.E5.90.8D.E7.94.9F.E6.88.90.E7.AE.97.E6.B3.95">卡券签名生成算法</a>
     */
    WxCardApiSignature createCardApiSignature(String... optionalSignParam) throws WxErrorException;

    /**
     * <pre>
     * 卡券Code解码
     * </pre>
     *
     * @param encryptCode 加密Code，通过JSSDK的chooseCard接口获得
     * @return 解密后的Code
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
    String decryptCardCode(String encryptCode) throws WxErrorException;

    /**
     * <pre>
     * 卡券Code查询
     * </pre>
     *
     * @param cardId       卡券ID代表一类卡券
     * @param code         单张卡券的唯一标准
     * @param checkConsume 是否校验code核销状态，填入true和false时的code异常状态返回数据不同
     * @return WxMpCardResult对象，包含卡券查询结果信息
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
     * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1451025272&anchor=1">卡券Code查询接口</a>
     */
    WxMpCardResult queryCardCode(String cardId, String code, boolean checkConsume) throws WxErrorException;

    /**
     * <pre>
     * 卡券Code核销。核销失败会抛出异常
     * </pre>
     *
     * @param code 单张卡券的唯一标准
     * @return 调用返回的JSON字符串，可用 com.google.gson.JsonParser#parse 等方法直接取JSON串中的errcode等信息
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
    String consumeCardCode(String code) throws WxErrorException;

    /**
     * <pre>
     * 卡券Code核销。核销失败会抛出异常
     * </pre>
     *
     * @param code   单张卡券的唯一标准
     * @param cardId 当自定义Code卡券时需要传入card_id
     * @return 调用返回的JSON字符串，可用 com.google.gson.JsonParser#parse 等方法直接取JSON串中的errcode等信息
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
    String consumeCardCode(String code, String cardId) throws WxErrorException;

    /**
     * <pre>
     * 卡券Mark接口
     * 开发者在帮助消费者核销卡券之前，必须帮助先将此code（卡券串码）与一个openid绑定（即mark住），
     * 才能进一步调用核销接口，否则报错。
     * </pre>
     *
     * @param code   卡券的code码
     * @param cardId 卡券的ID
     * @param openId 用券用户的openid
     * @param isMark 是否要mark（占用）这个code，填写true或者false，表示占用或解除占用
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
    void markCardCode(String code, String cardId, String openId, boolean isMark) throws WxErrorException;

    /**
     * <pre>
     * 查看卡券详情接口
     * </pre>
     *
     * @param cardId 卡券的ID
     * @return 返回的卡券详情JSON字符串
     *          [注] 由于返回的JSON格式过于复杂，难以定义其对应格式的Bean并且难以维护，因此只返回String格式的JSON串。
     *          可由 com.google.gson.JsonParser#parse 等方法直接取JSON串中的某个字段。
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
     * @see <a href="https://mp.weixin.qq.com/wiki/14/8dd77aeaee85f922db5f8aa6386d385e.html#.E6.9F.A5.E7.9C.8B.E5.8D.A1.E5.88.B8.E8.AF.A6.E6.83.85">查看卡券详情</a>
     */
    String getCardDetail(String cardId) throws WxErrorException;

    /**
     * <pre>
     * 添加测试白名单
     * </pre>
     *
     * @param openid 用户的openid
     * @return 操作结果字符串
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
    String addTestWhiteList(String openid) throws WxErrorException;

    /**
     * <pre>
     * 创建卡券
     * </pre>
     *
     * @param cardCreateRequest 卡券创建请求对象
     * @return 卡券创建结果对象
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
    WxMpCardCreateResult createCard(WxMpCardCreateRequest cardCreateMessage) throws WxErrorException;

    /**
     * <pre>
     * 创建卡券二维码
     * </pre>
     *
     * @param cardId   卡券编号
     * @param outerStr 二维码标识
     * @return 卡券二维码创建结果对象
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
    WxMpCardQrcodeCreateResult createQrcodeCard(String cardId, String outerStr) throws WxErrorException;

    /**
     * <pre>
     * 创建卡券二维码
     * </pre>
     *
     * @param cardId    卡券编号
     * @param outerStr  用户首次领卡时，会通过 领取事件推送 给商户； 对于会员卡的二维码，用户每次扫码打开会员卡后点击任何url，会将该值拼入url中，方便开发者定位扫码来源
     * @param expiresIn 指定二维码的有效时间，范围是60 ~ 1800秒。不填默认为365天有效
     * @return 卡券二维码创建结果对象
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
    WxMpCardQrcodeCreateResult createQrcodeCard(String cardId, String outerStr, int expiresIn) throws WxErrorException;

    /**
     * <pre>
     * 创建卡券二维码
     * </pre>
     *
     * @param cardId       卡券编号
     * @param outerStr     用户首次领卡时，会通过 领取事件推送 给商户； 对于会员卡的二维码，用户每次扫码打开会员卡后点击任何url，会将该值拼入url中，方便开发者定位扫码来源
     * @param expiresIn    指定二维码的有效时间，范围是60 ~ 1800秒。不填默认为365天有效
     * @param openid       指定领取者的openid，只有该用户能领取。bind_openid字段为true的卡券必须填写，非指定openid不必填写
     * @param code         卡券Code码,use_custom_code字段为true的卡券必须填写，非自定义code和导入code模式的卡券不必填写
     * @param isUniqueCode 指定下发二维码，生成的二维码随机分配一个code，领取后不可再次扫描。填写true或false。默认false，注意填写该字段时，卡券须通过审核且库存不为0
     * @return 卡券二维码创建结果对象
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
    WxMpCardQrcodeCreateResult createQrcodeCard(String cardId, String outerStr, int expiresIn, String openid,
                                              String code, boolean isUniqueCode) throws WxErrorException;

    /**
     * <pre>
     * 创建卡券货架
     * </pre>
     *
     * @param createRequest 货架创建参数
     * @return 货架创建结果对象
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
    WxMpCardLandingPageCreateResult createLandingPage(WxMpCardLandingPageCreateRequest createRequest)
    throws WxErrorException;

    /**
     * <pre>
     * 将用户的卡券设置为失效状态
     * </pre>
     *
     * @param cardId 卡券编号
     * @param code   用户会员卡号
     * @param reason 设置为失效的原因
     * @return 操作结果字符串
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
     * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1451025272&anchor=9">设置卡券失效</a>
     */
    String unavailableCardCode(String cardId, String code, String reason) throws WxErrorException;

    /**
     * <pre>
     * 删除卡券接口
     * </pre>
     *
     * @param cardId 卡券id
     * @return 删除结果对象
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
    WxMpCardDeleteResult deleteCard(String cardId) throws WxErrorException;

    /**
     * <pre>
     * 导入自定义code(仅对自定义code商户)
     * </pre>
     *
     * @param cardId   卡券id
     * @param codeList 需导入微信卡券后台的自定义code，上限为100个
     * @return 导入结果对象
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
    WxMpCardCodeDepositResult cardCodeDeposit(String cardId, List<String> codeList) throws WxErrorException;

    /**
     * <pre>
     * 查询导入code数目接口
     * </pre>
     *
     * @param cardId 卡券id
     * @return 查询结果对象
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
    WxMpCardCodeDepositCountResult cardCodeDepositCount(String cardId) throws WxErrorException;

    /**
     * <pre>
     * 核查code接口
     * </pre>
     *
     * @param cardId   卡券id
     * @param codeList 已经微信卡券后台的自定义code，上限为100个
     * @return 核查结果对象
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
    WxMpCardCodeCheckcodeResult cardCodeCheckcode(String cardId, List<String> codeList) throws WxErrorException;

    /**
     * <pre>
     * 图文消息群发卡券获取内嵌html
     * </pre>
     *
     * @param cardId 卡券id
     * @return HTML获取结果对象
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
    WxMpCardMpnewsGethtmlResult cardMpnewsGethtml(String cardId) throws WxErrorException;

    /**
     * <pre>
     * 修改库存接口
     * </pre>
     *
     * @param cardId      卡券ID
     * @param changeValue 库存变更值，负值为减少库存
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
     * @see <a href="https://developers.weixin.qq.com/doc/offiaccount/Cards_and_Offer/Managing_Coupons_Vouchers_and_Cards.html#5">修改库存接口</a>
     */
    void cardModifyStock(String cardId, Integer changeValue) throws WxErrorException;

    /**
     * <pre>
     * 更改Code接口
     * </pre>
     *
     * @param cardId  卡券ID
     * @param oldCode 需变更的Code码
     * @param newCode 变更后的有效Code码
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
     * @see <a href="https://developers.weixin.qq.com/doc/offiaccount/Cards_and_Offer/Managing_Coupons_Vouchers_and_Cards.html#6">更改Code接口</a>
     */
    void cardCodeUpdate(String cardId, String oldCode, String newCode) throws WxErrorException;

    /**
     * <pre>
     * 设置买单接口
     * </pre>
     *
     * @param cardId 卡券ID
     * @param isOpen 是否开启买单功能，填true/false
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
     * @see <a href="https://developers.weixin.qq.com/doc/offiaccount/Cards_and_Offer/Create_a_Coupon_Voucher_or_Card.html#12">设置买单接口</a>
     */
    void cardPaycellSet(String cardId, Boolean isOpen) throws WxErrorException;

    /**
     * <pre>
     * 设置自助核销
     * </pre>
     *
     * @param cardId           卡券ID
     * @param isOpen           是否开启自助核销功能
     * @param needVerifyCod    用户核销时是否需要输入验证码， 填true/false， 默认为false
     * @param needRemarkAmount 用户核销时是否需要备注核销金额， 填true/false， 默认为false
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
     * @see <a href="https://developers.weixin.qq.com/doc/offiaccount/Cards_and_Offer/Create_a_Coupon_Voucher_or_Card.html#14">设置自助核销</a>
     */
    void cardSelfConsumeCellSet(String cardId, Boolean isOpen,
                              Boolean needVerifyCod, Boolean needRemarkAmount) throws WxErrorException;

    /**
     * <pre>
     * 获取用户已领取卡券接口
     * </pre>
     *
     * @param openId 需要查询的用户openid
     * @param cardId 卡券ID。不填写时默认查询当前appid下的卡券
     * @return 用户卡券列表结果对象
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
     * @see <a href="https://developers.weixin.qq.com/doc/offiaccount/Cards_and_Offer/Managing_Coupons_Vouchers_and_Cards.html#1">获取用户已领取卡券接口</a>
     */
    WxUserCardListResult getUserCardList(String openId, String cardId) throws WxErrorException;
}
