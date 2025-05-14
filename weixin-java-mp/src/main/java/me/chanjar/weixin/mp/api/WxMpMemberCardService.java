package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.card.CardUpdateResult;
import me.chanjar.weixin.mp.bean.card.WxMpCardCreateResult;
import me.chanjar.weixin.mp.bean.card.membercard.*;

/**
 * 会员卡相关接口.
 *
 * @author YuJian(mgcnrx11 @ gmail.com)
 * @author yuanqixun
 * @version 2017 /7/8 created on  2018-08-30
 */
public interface WxMpMemberCardService {
    /**
     * 得到WxMpService.
     *
     * @return WxMpService wx mp service
     */
    WxMpService getWxMpService();

    /**
     * 会员卡创建接口.
     *
     * @param createJson 会员卡json字符串
     * @return 返回json字符串 wx mp card create result
     * @throws WxErrorException 接口调用失败抛出的异常
     */
    WxMpCardCreateResult createMemberCard(String createJson) throws WxErrorException;

    /**
     * 会员卡创建接口
     *
     * @param createMessageMessage 会员卡创建对象
     * @return 会员卡信息的结果对象 wx mp card create result
     * @throws WxErrorException 接口调用失败抛出的异常
     */
    WxMpCardCreateResult createMemberCard(WxMpMemberCardCreateMessage createMessageMessage) throws WxErrorException;

    /**
     * 会员卡激活接口.
     *
     * @param activatedMessage 激活所需参数
     * @return 会员卡激活后的json字符串 string
     * @throws WxErrorException 接口调用失败抛出的异常
     */
    String activateMemberCard(WxMpMemberCardActivatedMessage activatedMessage) throws WxErrorException;

    /**
     * 拉取会员信息接口.
     *
     * @param cardId 会员卡的CardId，微信分配
     * @param code   领取会员的会员卡Code
     * @return 会员信息的结果对象 user info
     * @throws WxErrorException 接口调用失败抛出的异常
     */
    WxMpMemberCardUserInfoResult getUserInfo(String cardId, String code) throws WxErrorException;

    /**
     * 当会员持卡消费后，支持开发者调用该接口更新会员信息.
     * 会员卡交易后的每次信息变更需通过该接口通知微信，便于后续消息通知及其他扩展功能。
     * 1.开发者可以同时传入add_bonus和bonus解决由于同步失败带来的幂等性问题。
     * 同时传入add_bonus和bonus时 add_bonus作为积分变动消息中的变量值，而bonus作为卡面上的总积分额度显示。余额变动同理。
     * 2.开发者可以传入is_notify_bonus控制特殊的积分对账变动不发送消息，余额变动同理。
     *
     * @param updateUserMessage 更新会员信息所需字段消息
     * @return 调用返回的JSON字符串 。
     * @throws WxErrorException 接口调用失败抛出的异常
     */
    WxMpMemberCardUpdateResult updateUserMemberCard(WxMpMemberCardUpdateMessage updateUserMessage) throws WxErrorException;

    /**
     * 设置会员卡激活的字段（会员卡设置：wx_activate=true 时需要）.
     *
     * @param userFormRequest 会员卡激活字段对象
     * @return 会员卡激活后结果对象 activate user form
     * @throws WxErrorException 接口调用失败抛出的异常
     */
    MemberCardActivateUserFormResult setActivateUserForm(MemberCardActivateUserFormRequest userFormRequest) throws WxErrorException;

    /**
     * 获取会员卡开卡插件参数(跳转型开卡组件需要参数).
     *
     * @param cardId 会员卡的CardId，微信分配
     * @param outStr 会员卡设置商户的渠道
     * @return 会员卡开卡插件参数结果对象 activate plugin param
     * @throws WxErrorException 接口调用失败抛出的异常
     */
    ActivatePluginParam getActivatePluginParam(String cardId, String outStr) throws WxErrorException;

    /**
     * 获取开卡组件链接接口
     *
     * @param cardId 会员卡的CardId，微信分配
     * @param outStr 会员卡设置商户的渠道
     * @return 会员卡开卡插件参数结果对象 activate plugin url
     * @throws WxErrorException 接口调用失败抛出的异常
     */
    String getActivatePluginUrl(String cardId, String outStr) throws WxErrorException;

    /**
     * 更新会员卡信息.
     *
     * @param memberCardUpdateRequest 会员卡更新对象
     * @return 会员卡更新后结果对象 card update result
     * @throws WxErrorException 接口调用失败抛出的异常
     */
    CardUpdateResult updateCardInfo(MemberCardUpdateRequest memberCardUpdateRequest) throws WxErrorException;

    /**
     * 解析跳转型开卡字段用户提交的资料.
     * 开发者在URL上截取ticket后须先进行urldecode
     *
     * @param activateTicket 用户提交的资料
     * @return 开卡字段的会员信息对象 activate temp info
     * @throws WxErrorException 接口调用失败抛出的异常
     */
    WxMpMemberCardActivateTempInfoResult getActivateTempInfo(String activateTicket) throws WxErrorException;

}
