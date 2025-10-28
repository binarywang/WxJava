package me.chanjar.weixin.mp.api;

import java.io.File;
import java.util.Date;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.kefu.request.WxMpKfAccountRequest;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfList;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfMsgList;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfOnlineList;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfSessionGetResult;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfSessionList;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfSessionWaitCaseList;

/**
 * <pre>
 * 客服接口.
 * 注意：命名采用kefu拼音的原因是：其英文CustomerService如果再加上Service后缀显得有点啰嗦，如果不加又显得表意不完整。
 * </pre>
 *
 * @author Binary Wang
 */
public interface WxMpKefuService {
    /**
     * <pre>
     * 发送客服消息
     * 详情请见: <a href="https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Service_Center_messages.html">发送客服消息</a>
     * 接口url格式：https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param message 客服消息对象，包含消息类型、内容、接收者等信息
     * @return 发送是否成功，true表示成功，false表示失败
     * @throws WxErrorException 微信API调用异常
     */
    boolean sendKefuMessage(WxMpKefuMessage message) throws WxErrorException;

    /**
     * <pre>
     * 发送客服消息
     * 详情请见: <a href="https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Service_Center_messages.html">发送客服消息</a>
     * 接口url格式：https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param message 客服消息对象，包含消息类型、内容、接收者等信息
     * @return 微信API响应结果，JSON格式字符串
     * @throws WxErrorException 微信API调用异常
     */
    String sendKefuMessageWithResponse(WxMpKefuMessage message) throws WxErrorException;

  //*******************客服管理接口***********************//

    /**
     * <pre>
     * 获取客服基本信息
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044813&token=&lang=zh_CN">客服管理</a>
     * 接口url格式：https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @return 客服基本信息列表，包含客服账号、昵称、头像等信息
     * @throws WxErrorException 微信API调用异常
     */
    WxMpKfList kfList() throws WxErrorException;

    /**
     * <pre>
     * 获取在线客服接待信息
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044813&token=&lang=zh_CN">客服管理</a>
     * 接口url格式：https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @return 在线客服接待信息列表，包含在线客服账号、接待状态等信息
     * @throws WxErrorException 微信API调用异常
     */
    WxMpKfOnlineList kfOnlineList() throws WxErrorException;

    /**
     * <pre>
     * 添加客服账号
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044813&token=&lang=zh_CN">客服管理</a>
     * 接口url格式：https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param request 客服账号请求对象，包含客服账号、昵称等信息
     * @return 添加是否成功，true表示成功，false表示失败
     * @throws WxErrorException 微信API调用异常
     */
    boolean kfAccountAdd(WxMpKfAccountRequest request) throws WxErrorException;

    /**
     * <pre>
     * 设置客服信息（即更新客服信息）
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044813&token=&lang=zh_CN">客服管理</a>
     * 接口url格式：https://api.weixin.qq.com/customservice/kfaccount/update?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param request 客服账号请求对象，包含客服账号、昵称等信息
     * @return 更新是否成功，true表示成功，false表示失败
     * @throws WxErrorException 微信API调用异常
     */
    boolean kfAccountUpdate(WxMpKfAccountRequest request) throws WxErrorException;

    /**
     * <pre>
     * 邀请绑定客服账号
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044813&token=&lang=zh_CN">客服管理</a>
     * 接口url格式：https://api.weixin.qq.com/customservice/kfaccount/inviteworker?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param request 客服账号请求对象，包含客服账号、邀请者微信号等信息
     * @return 邀请是否成功，true表示成功，false表示失败
     * @throws WxErrorException 微信API调用异常
     */
    boolean kfAccountInviteWorker(WxMpKfAccountRequest request) throws WxErrorException;

    /**
     * <pre>
     * 上传客服头像
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044813&token=&lang=zh_CN">客服管理</a>
     * 接口url格式：https://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT
     * </pre>
     *
     * @param kfAccount 客服账号，格式为：账号前缀@微信号
     * @param imgFile   头像图片文件，支持JPG、PNG格式，大小不超过2MB
     * @return 上传是否成功，true表示成功，false表示失败
     * @throws WxErrorException 微信API调用异常
     */
    boolean kfAccountUploadHeadImg(String kfAccount, File imgFile) throws WxErrorException;

    /**
     * <pre>
     * 删除客服账号
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044813&token=&lang=zh_CN">客服管理</a>
     * 接口url格式：https://api.weixin.qq.com/customservice/kfaccount/del?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT
     * </pre>
     *
     * @param kfAccount 客服账号，格式为：账号前缀@微信号
     * @return 删除是否成功，true表示成功，false表示失败
     * @throws WxErrorException 微信API调用异常
     */
    boolean kfAccountDel(String kfAccount) throws WxErrorException;

  //*******************客服会话控制接口***********************//

    /**
     * <pre>
     * 创建会话
     * 此接口在客服和用户之间创建一个会话，如果该客服和用户会话已存在，则直接返回0。指定的客服账号必须已经绑定微信号且在线。
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044820&token=&lang=zh_CN">客服会话控制接口</a>
     * 接口url格式： https://api.weixin.qq.com/customservice/kfsession/create?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param openid    用户的openid，标识具体的用户
     * @param kfAccount 客服账号，格式为：账号前缀@微信号
     * @return 创建是否成功，true表示成功，false表示失败
     * @throws WxErrorException 微信API调用异常
     */
    boolean kfSessionCreate(String openid, String kfAccount) throws WxErrorException;

    /**
     * <pre>
     * 关闭会话
     * 开发者可以使用本接口，关闭一个会话。
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044820&token=&lang=zh_CN">客服会话控制接口</a>
     * 接口url格式： https://api.weixin.qq.com/customservice/kfsession/close?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param openid    用户的openid，标识具体的用户
     * @param kfAccount 客服账号，格式为：账号前缀@微信号
     * @return 关闭是否成功，true表示成功，false表示失败
     * @throws WxErrorException 微信API调用异常
     */
    boolean kfSessionClose(String openid, String kfAccount) throws WxErrorException;

    /**
     * <pre>
     * 获取客户的会话状态
     * 此接口获取一个客户的会话，如果不存在，则kf_account为空。
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044820&token=&lang=zh_CN">客服会话控制接口</a>
     * 接口url格式： https://api.weixin.qq.com/customservice/kfsession/getsession?access_token=ACCESS_TOKEN&openid=OPENID
     * </pre>
     *
     * @param openid 用户的openid，标识具体的用户
     * @return 客户会话状态信息，包含客服账号、会话状态等
     * @throws WxErrorException 微信API调用异常
     */
    WxMpKfSessionGetResult kfSessionGet(String openid) throws WxErrorException;

    /**
     * <pre>
     * 获取客服的会话列表
     * 开发者可以通过本接口获取某个客服正在接待的会话列表。
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044820&token=&lang=zh_CN">客服会话控制</a>
     * 接口url格式： https://api.weixin.qq.com/customservice/kfsession/getsessionlist?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT
     * </pre>
     *
     * @param kfAccount 客服账号，格式为：账号前缀@微信号
     * @return 客服会话列表，包含正在接待的会话信息
     * @throws WxErrorException 微信API调用异常
     */
    WxMpKfSessionList kfSessionList(String kfAccount) throws WxErrorException;

    /**
     * <pre>
     * 获取未接入会话列表
     * 开发者可以通过本接口获取当前正在等待队列中的会话列表，此接口最多返回最早进入队列的100个未接入会话。
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044820&token=&lang=zh_CN">客服会话控制</a>
     * 接口url格式： https://api.weixin.qq.com/customservice/kfsession/getwaitcase?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @return 未接入会话列表，包含等待接入的会话信息
     * @throws WxErrorException 微信API调用异常
     */
    WxMpKfSessionWaitCaseList kfSessionGetWaitCase() throws WxErrorException;

  //*******************获取聊天记录的接口***********************//

    /**
     * <pre>
     * 获取聊天记录（原始接口）
     * 此接口返回的聊天记录中，对于图片、语音、视频，分别展示成文本格式的[image]、[voice]、[video]
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1464937269_mUtmK&token=&lang=zh_CN">获取聊天记录</a>
     * 接口url格式： https://api.weixin.qq.com/customservice/msgrecord/getmsglist?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param startTime 起始时间，用于筛选聊天记录的时间范围
     * @param endTime   结束时间，用于筛选聊天记录的时间范围
     * @param msgId     消息id顺序从小到大，从1开始，用于分页获取
     * @param number    每次获取条数，最多10000条，用于分页控制
     * @return 聊天记录对象，包含客服和用户的聊天消息列表
     * @throws WxErrorException 微信API调用异常
     */
    WxMpKfMsgList kfMsgList(Date startTime, Date endTime, Long msgId, Integer number) throws WxErrorException;

    /**
     * <pre>
     * 获取聊天记录（优化接口，返回指定时间段内所有的聊天记录）
     * 此接口返回的聊天记录中，对于图片、语音、视频，分别展示成文本格式的[image]、[voice]、[video]
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1464937269_mUtmK&token=&lang=zh_CN">获取聊天记录</a>
     * 接口url格式： https://api.weixin.qq.com/customservice/msgrecord/getmsglist?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param startTime 起始时间，用于筛选聊天记录的时间范围
     * @param endTime   结束时间，用于筛选聊天记录的时间范围
     * @return 聊天记录对象，包含客服和用户的聊天消息列表
     * @throws WxErrorException 微信API调用异常
     */
    WxMpKfMsgList kfMsgList(Date startTime, Date endTime) throws WxErrorException;

    /**
     * <pre>
     * 客服输入状态
     * 开发者可通过调用"客服输入状态"接口，返回客服当前输入状态给用户。
     * 此接口需要客服消息接口权限。
     * 如果不满足发送客服消息的触发条件，则无法下发输入状态。
     * 下发输入状态，需要客服之前30秒内跟用户有过消息交互。
     * 在输入状态中（持续15s），不可重复下发输入态。
     * 在输入状态中，如果向用户下发消息，会同时取消输入状态。
     *
     * 详情请见：<a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140547">客服输入状态</a>
     * 接口url格式：https://api.weixin.qq.com/cgi-bin/message/custom/typing?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param openid  用户的openid，标识具体的用户
     * @param command 输入状态命令，可选值："Typing"：对用户下发"正在输入"状态；"CancelTyping"：取消对用户的"正在输入"状态
     * @return 发送是否成功，true表示成功，false表示失败
     * @throws WxErrorException 微信API调用异常
     */
    boolean sendKfTypingState(String openid, String command) throws WxErrorException;
}
