package me.chanjar.weixin.cp.api;

import lombok.NonNull;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.msgaudit.*;

import java.util.List;
import java.util.function.Consumer;

/**
 * 会话内容存档接口.
 * 官方文档：https://developer.work.weixin.qq.com/document/path/91360
 * <p>
 * 如需自行实现，亦可调用Finance类库函数，进行实现：
 * com.tencent.wework.Finance
 *
 * @author Wang_Wong  created on  2022-01-14
 */
public interface WxCpMsgAuditService {

  /**
   * 拉取聊天记录函数
   *
   * @param seq     从指定的seq开始拉取消息，注意的是返回的消息从seq+1开始返回，seq为之前接口返回的最大seq值。首次使用请使用seq:0
   * @param limit   一次拉取的消息条数，最大值1000条，超过1000条会返回错误
   * @param proxy   使用代理的请求，需要传入代理的链接。如：socks5://10.0.0.1:8081 或者 http://10.0.0.1:8081，如果没有传null
   * @param passwd  代理账号密码，需要传入代理的账号密码。如 user_name:passwd_123，如果没有传null
   * @param timeout 超时时间，根据实际需要填写
   * @return 返回是否调用成功 chat datas
   * @throws Exception the exception
   * @deprecated 请使用 {@link #getChatRecords(long, long, String, String, long)} 代替，
   *             该方法会将SDK暴露给调用方，容易导致SDK生命周期管理混乱，引发JVM崩溃
   */
  @Deprecated
  WxCpChatDatas getChatDatas(long seq, @NonNull long limit, String proxy, String passwd, @NonNull long timeout) throws Exception;

  /**
   * 拉取聊天记录函数（推荐使用）
   * 该方法不会将SDK暴露给调用方，SDK生命周期由框架自动管理，更加安全
   *
   * @param seq     从指定的seq开始拉取消息，注意的是返回的消息从seq+1开始返回，seq为之前接口返回的最大seq值。首次使用请使用seq:0
   * @param limit   一次拉取的消息条数，最大值1000条，超过1000条会返回错误
   * @param proxy   使用代理的请求，需要传入代理的链接。如：socks5://10.0.0.1:8081 或者 http://10.0.0.1:8081，如果没有传null
   * @param passwd  代理账号密码，需要传入代理的账号密码。如 user_name:passwd_123，如果没有传null
   * @param timeout 超时时间，根据实际需要填写
   * @return 返回聊天记录列表，不包含SDK信息
   * @throws Exception the exception
   */
  List<WxCpChatDatas.WxCpChatData> getChatRecords(long seq, @NonNull long limit, String proxy, String passwd, @NonNull long timeout) throws Exception;

  /**
   * 获取解密的聊天数据Model
   *
   * @param sdk      getChatDatas()获取到的sdk
   * @param chatData getChatDatas()获取到的聊天数据
   * @param pkcs1    使用什么方式进行解密，1代表使用PKCS1进行解密，2代表PKCS8进行解密 ...
   * @return 解密后的聊天数据 decrypt data
   * @throws Exception the exception
   * @deprecated 请使用 {@link #getDecryptChatData(WxCpChatDatas.WxCpChatData, Integer)} 代替，
   *             该方法需要传入SDK，容易导致SDK生命周期管理混乱，引发JVM崩溃
   */
  @Deprecated
  WxCpChatModel getDecryptData(@NonNull long sdk, @NonNull WxCpChatDatas.WxCpChatData chatData,
                               @NonNull Integer pkcs1) throws Exception;

  /**
   * 获取解密的聊天数据Model（推荐使用）
   * 该方法不需要传入SDK，SDK由框架自动管理，更加安全
   *
   * @param chatData 聊天数据
   * @param pkcs1    使用什么方式进行解密，1代表使用PKCS1进行解密，2代表PKCS8进行解密 ...
   * @return 解密后的聊天数据
   * @throws Exception the exception
   */
  WxCpChatModel getDecryptChatData(@NonNull WxCpChatDatas.WxCpChatData chatData, @NonNull Integer pkcs1) throws Exception;

  /**
   * 获取解密的聊天数据明文
   *
   * @param sdk      getChatDatas()获取到的sdk
   * @param chatData getChatDatas()获取到的聊天数据
   * @param pkcs1    使用什么方式进行解密，1代表使用PKCS1进行解密，2代表PKCS8进行解密 ...
   * @return 解密后的明文 chat plain text
   * @throws Exception the exception
   * @deprecated 请使用 {@link #getChatRecordPlainText(WxCpChatDatas.WxCpChatData, Integer)} 代替，
   *             该方法需要传入SDK，容易导致SDK生命周期管理混乱，引发JVM崩溃
   */
  @Deprecated
  String getChatPlainText(@NonNull long sdk, @NonNull WxCpChatDatas.WxCpChatData chatData, @NonNull Integer pkcs1) throws Exception;

  /**
   * 获取解密的聊天数据明文（推荐使用）
   * 该方法不需要传入SDK，SDK由框架自动管理，更加安全
   *
   * @param chatData 聊天数据
   * @param pkcs1    使用什么方式进行解密，1代表使用PKCS1进行解密，2代表PKCS8进行解密 ...
   * @return 解密后的明文
   * @throws Exception the exception
   */
  String getChatRecordPlainText(@NonNull WxCpChatDatas.WxCpChatData chatData, @NonNull Integer pkcs1) throws Exception;

  /**
   * 获取媒体文件
   * 针对图片、文件等媒体数据，提供sdk接口拉取数据内容。
   * <p>
   * 注意：
   * 根据上面返回的文件类型，拼接好存放文件的绝对路径即可。此时绝对路径写入文件流，来达到获取媒体文件的目的。
   * 详情可以看官方文档，亦可阅读此接口源码。
   *
   * @param sdk            getChatDatas()获取到的sdk，注意，每次获取的sdk会不一样
   * @param sdkfileid      消息体内容中的sdkfileid信息
   * @param proxy          使用代理的请求，需要传入代理的链接。如：socks5://10.0.0.1:8081 或者 http://10.0.0.1:8081，如果没有传null
   * @param passwd         代理账号密码，需要传入代理的账号密码。如 user_name:passwd_123，如果没有传null
   * @param timeout        超时时间，分片数据需累加到文件存储。单次最大返回512K字节，如果文件比较大，自行设置长一点，比如timeout=10000
   * @param targetFilePath 目标文件绝对路径+实际文件名，比如：/usr/local/file/20220114/474f866b39d10718810d55262af82662.gif
   * @throws WxErrorException the wx error exception
   * @deprecated 请使用 {@link #downloadMediaFile(String, String, String, long, String)} 代替，
   *             该方法需要传入SDK，容易导致SDK生命周期管理混乱，引发JVM崩溃
   */
  @Deprecated
  void getMediaFile(@NonNull long sdk, @NonNull String sdkfileid, String proxy, String passwd, @NonNull long timeout,
                    @NonNull String targetFilePath) throws WxErrorException;

  /**
   * 获取媒体文件（推荐使用）
   * 该方法不需要传入SDK，SDK由框架自动管理，更加安全
   * 针对图片、文件等媒体数据，提供sdk接口拉取数据内容。
   * <p>
   * 注意：
   * 根据上面返回的文件类型，拼接好存放文件的绝对路径即可。此时绝对路径写入文件流，来达到获取媒体文件的目的。
   * 详情可以看官方文档，亦可阅读此接口源码。
   *
   * @param sdkfileid      消息体内容中的sdkfileid信息
   * @param proxy          使用代理的请求，需要传入代理的链接。如：socks5://10.0.0.1:8081 或者 http://10.0.0.1:8081，如果没有传null
   * @param passwd         代理账号密码，需要传入代理的账号密码。如 user_name:passwd_123，如果没有传null
   * @param timeout        超时时间，分片数据需累加到文件存储。单次最大返回512K字节，如果文件比较大，自行设置长一点，比如timeout=10000
   * @param targetFilePath 目标文件绝对路径+实际文件名，比如：/usr/local/file/20220114/474f866b39d10718810d55262af82662.gif
   * @throws WxErrorException the wx error exception
   */
  void downloadMediaFile(@NonNull String sdkfileid, String proxy, String passwd, @NonNull long timeout,
                         @NonNull String targetFilePath) throws WxErrorException;

  /**
   * 获取媒体文件 传入一个lambda，each所有的数据分片byte[]，更加灵活
   * 针对图片、文件等媒体数据，提供sdk接口拉取数据内容。
   * 详情可以看官方文档，亦可阅读此接口源码。
   *
   * @param sdk            getChatDatas()获取到的sdk，注意，每次获取的sdk会不一样
   * @param sdkfileid      消息体内容中的sdkfileid信息
   * @param proxy          使用代理的请求，需要传入代理的链接。如：socks5://10.0.0.1:8081 或者 http://10.0.0.1:8081，如果没有传null
   * @param passwd         代理账号密码，需要传入代理的账号密码。如 user_name:passwd_123，如果没有传null
   * @param timeout        超时时间，分片数据需累加到文件存储。单次最大返回512K字节，如果文件比较大，自行设置长一点，比如timeout=10000
   * @param action         传入一个lambda，each所有的数据分片
   * @throws WxErrorException the wx error exception
   * @deprecated 请使用 {@link #downloadMediaFile(String, String, String, long, Consumer)} 代替，
   *             该方法需要传入SDK，容易导致SDK生命周期管理混乱，引发JVM崩溃
   */
  @Deprecated
  void getMediaFile(@NonNull long sdk, @NonNull String sdkfileid, String proxy, String passwd, @NonNull long timeout,
                    @NonNull Consumer<byte[]> action) throws WxErrorException;

  /**
   * 获取媒体文件 传入一个lambda，each所有的数据分片byte[]，更加灵活（推荐使用）
   * 该方法不需要传入SDK，SDK由框架自动管理，更加安全
   * 针对图片、文件等媒体数据，提供sdk接口拉取数据内容。
   * 详情可以看官方文档，亦可阅读此接口源码。
   *
   * @param sdkfileid      消息体内容中的sdkfileid信息
   * @param proxy          使用代理的请求，需要传入代理的链接。如：socks5://10.0.0.1:8081 或者 http://10.0.0.1:8081，如果没有传null
   * @param passwd         代理账号密码，需要传入代理的账号密码。如 user_name:passwd_123，如果没有传null
   * @param timeout        超时时间，分片数据需累加到文件存储。单次最大返回512K字节，如果文件比较大，自行设置长一点，比如timeout=10000
   * @param action         传入一个lambda，each所有的数据分片
   * @throws WxErrorException the wx error exception
   */
  void downloadMediaFile(@NonNull String sdkfileid, String proxy, String passwd, @NonNull long timeout,
                         @NonNull Consumer<byte[]> action) throws WxErrorException;

  /**
   * 获取会话内容存档开启成员列表
   * 企业可通过此接口，获取企业开启会话内容存档的成员列表
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/msgaudit/get_permit_user_list?access_token=ACCESS_TOKEN
   *
   * @param type 拉取对应版本的开启成员列表。1表示办公版；2表示服务版；3表示企业版。非必填，不填写的时候返回全量成员列表。
   * @return permit user list
   * @throws WxErrorException the wx error exception
   */
  List<String> getPermitUserList(Integer type) throws WxErrorException;

  /**
   * 获取会话内容存档内部群信息
   * 企业可通过此接口，获取会话内容存档本企业的内部群信息，包括群名称、群主id、公告、群创建时间以及所有群成员的id与加入时间。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/msgaudit/groupchat/get?access_token=ACCESS_TOKEN
   *
   * @param roomid 待查询的群id
   * @return group chat
   * @throws WxErrorException the wx error exception
   */
  WxCpGroupChat getGroupChat(@NonNull String roomid) throws WxErrorException;

  /**
   * 获取会话同意情况
   * 企业可通过下述接口，获取会话中外部成员的同意情况
   * <p>
   * 单聊请求地址：https://qyapi.weixin.qq.com/cgi-bin/msgaudit/check_single_agree?access_token=ACCESS_TOKEN
   * <p>
   * 请求方式：POST（HTTPS）
   *
   * @param checkAgreeRequest 待查询的会话信息
   * @return wx cp agree info
   * @throws WxErrorException the wx error exception
   */
  WxCpAgreeInfo checkSingleAgree(@NonNull WxCpCheckAgreeRequest checkAgreeRequest) throws WxErrorException;

}
