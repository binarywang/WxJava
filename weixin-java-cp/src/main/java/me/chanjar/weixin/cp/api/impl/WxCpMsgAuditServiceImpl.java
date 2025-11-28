package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.tencent.wework.Finance;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.WxCpMsgAuditService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.msgaudit.*;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.util.crypto.WxCpCryptUtil;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.MsgAudit.*;

/**
 * 会话内容存档接口实现类.
 *
 * @author Wang_Wong  created on  2022-01-17
 */
@Slf4j
@RequiredArgsConstructor
public class WxCpMsgAuditServiceImpl implements WxCpMsgAuditService {
  private final WxCpService cpService;

  /**
   * SDK初始化有效期，根据企微文档为7200秒
   */
  private static final int SDK_EXPIRES_TIME = 7200;

  @Override
  public WxCpChatDatas getChatDatas(long seq, @NonNull long limit, String proxy, String passwd,
                                    @NonNull long timeout) throws Exception {
    // 获取或初始化SDK
    long sdk = this.initSdk();

    long slice = Finance.NewSlice();
    long ret = Finance.GetChatData(sdk, seq, limit, proxy, passwd, timeout, slice);
    if (ret != 0) {
      Finance.FreeSlice(slice);
      throw new WxErrorException("getchatdata err ret " + ret);
    }

    // 拉取会话存档
    String content = Finance.GetContentFromSlice(slice);
    Finance.FreeSlice(slice);
    WxCpChatDatas chatDatas = WxCpChatDatas.fromJson(content);
    if (chatDatas.getErrCode().intValue() != 0) {
      throw new WxErrorException(chatDatas.toJson());
    }

    chatDatas.setSdk(sdk);
    return chatDatas;
  }

  /**
   * 获取或初始化SDK，如果SDK已过期则重新初始化
   *
   * @return sdk id
   * @throws WxErrorException 初始化失败时抛出异常
   */
  private synchronized long initSdk() throws WxErrorException {
    WxCpConfigStorage configStorage = cpService.getWxCpConfigStorage();

    // 检查SDK是否已缓存且未过期
    if (!configStorage.isMsgAuditSdkExpired()) {
      long cachedSdk = configStorage.getMsgAuditSdk();
      if (cachedSdk > 0) {
        return cachedSdk;
      }
    }

    // SDK未初始化或已过期，需要重新初始化
    String configPath = configStorage.getMsgAuditLibPath();
    if (StringUtils.isEmpty(configPath)) {
      throw new WxErrorException("请配置会话存档sdk文件的路径，不要配错了！！");
    }

    // 替换斜杠
    String replacePath = configPath.replace("\\", "/");
    // 获取最后一个斜杠的下标，用作分割路径
    int lastIndex = replacePath.lastIndexOf("/") + 1;
    // 获取完整路径的前缀路径
    String prefixPath = replacePath.substring(0, lastIndex);
    // 获取后缀的所有文件，目的遍历所有文件
    String suffixFiles = replacePath.substring(lastIndex);

    // 包含so文件
    String[] libFiles = suffixFiles.split(",");
    if (libFiles.length <= 0) {
      throw new WxErrorException("请仔细配置会话存档文件路径！！");
    }

    List<String> libList = Arrays.asList(libFiles);
    // 判断windows系统会话存档sdk中dll的加载，因为会互相依赖所以是有顺序的，否则会导致无法加载sdk #2598
    List<String> osLib = new LinkedList<>();
    List<String> fileLib = new ArrayList<>();
    libList.forEach(s -> {
      if (s.contains("lib")) {
        osLib.add(s);
      } else {
        fileLib.add(s);
      }
    });
    osLib.addAll(fileLib);

    Finance.loadingLibraries(osLib, prefixPath);
    long sdk = Finance.NewSdk();
    // 因为会话存档单独有个secret,优先使用会话存档的secret
    String msgAuditSecret = configStorage.getMsgAuditSecret();
    if (StringUtils.isEmpty(msgAuditSecret)) {
      msgAuditSecret = configStorage.getCorpSecret();
    }
    long ret = Finance.Init(sdk, configStorage.getCorpId(), msgAuditSecret);
    if (ret != 0) {
      Finance.DestroySdk(sdk);
      throw new WxErrorException("init sdk err ret " + ret);
    }

    // 缓存SDK
    configStorage.updateMsgAuditSdk(sdk, SDK_EXPIRES_TIME);
    log.debug("初始化会话存档SDK成功，sdk={}", sdk);

    return sdk;
  }

  @Override
  public WxCpChatModel getDecryptData(@NonNull long sdk, @NonNull WxCpChatDatas.WxCpChatData chatData,
                                      @NonNull Integer pkcs1) throws Exception {
    String plainText = this.decryptChatData(sdk, chatData, pkcs1);
    return WxCpChatModel.fromJson(plainText);
  }

  /**
   * Decrypt chat data string.
   *
   * @param sdk      the sdk
   * @param chatData the chat data
   * @param pkcs1    the pkcs 1
   * @return the string
   * @throws Exception the exception
   */
  public String decryptChatData(long sdk, WxCpChatDatas.WxCpChatData chatData, Integer pkcs1) throws Exception {
    // 企业获取的会话内容，使用企业自行配置的消息加密公钥进行加密，企业可用自行保存的私钥解开会话内容数据。
    // msgAuditPriKey 会话存档私钥不能为空
    String priKey = cpService.getWxCpConfigStorage().getMsgAuditPriKey();
    if (StringUtils.isEmpty(priKey)) {
      throw new WxErrorException("请配置会话存档私钥【msgAuditPriKey】");
    }

    String decryptByPriKey = WxCpCryptUtil.decryptPriKey(chatData.getEncryptRandomKey(), priKey, pkcs1);
    // 每次使用DecryptData解密会话存档前需要调用NewSlice获取一个slice，在使用完slice中数据后，还需要调用FreeSlice释放。
    long msg = Finance.NewSlice();

    // 解密会话存档内容
    // sdk不会要求用户传入rsa私钥，保证用户会话存档数据只有自己能够解密。
    // 此处需要用户先用rsa私钥解密encrypt_random_key后，作为encrypt_key参数传入sdk来解密encrypt_chat_msg获取会话存档明文。
    int ret = Finance.DecryptData(sdk, decryptByPriKey, chatData.getEncryptChatMsg(), msg);
    if (ret != 0) {
      Finance.FreeSlice(msg);
      throw new WxErrorException("msg err ret " + ret);
    }

    // 明文
    String plainText = Finance.GetContentFromSlice(msg);
    Finance.FreeSlice(msg);
    return plainText;
  }

  @Override
  public String getChatPlainText(@NonNull long sdk, WxCpChatDatas.@NonNull WxCpChatData chatData,
                                 @NonNull Integer pkcs1) throws Exception {
    return this.decryptChatData(sdk, chatData, pkcs1);
  }

  @Override
  public void getMediaFile(@NonNull long sdk, @NonNull String sdkfileid, String proxy, String passwd,
                           @NonNull long timeout, @NonNull String targetFilePath) throws WxErrorException {
    /**
     * 1、媒体文件每次拉取的最大size为512k，因此超过512k的文件需要分片拉取。
     * 2、若该文件未拉取完整，sdk的IsMediaDataFinish接口会返回0，同时通过GetOutIndexBuf接口返回下次拉取需要传入GetMediaData的indexbuf。
     * 3、indexbuf一般格式如右侧所示，”Range:bytes=524288-1048575“:表示这次拉取的是从524288到1048575的分片。单个文件首次拉取填写的indexbuf
     * 为空字符串，拉取后续分片时直接填入上次返回的indexbuf即可。
     */
    File targetFile = new File(targetFilePath);
    if (!targetFile.getParentFile().exists()) {
      targetFile.getParentFile().mkdirs();
    }
    this.getMediaFile(sdk, sdkfileid, proxy, passwd, timeout, i -> {
      try {
        // 大于512k的文件会分片拉取，此处需要使用追加写，避免后面的分片覆盖之前的数据。
        FileOutputStream outputStream = new FileOutputStream(targetFile, true);
        outputStream.write(i);
        outputStream.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  @Override
  public void getMediaFile(@NonNull long sdk, @NonNull String sdkfileid, String proxy, String passwd, @NonNull long timeout, @NonNull Consumer<byte[]> action) throws WxErrorException {
    /**
     * 1、媒体文件每次拉取的最大size为512k，因此超过512k的文件需要分片拉取。
     * 2、若该文件未拉取完整，sdk的IsMediaDataFinish接口会返回0，同时通过GetOutIndexBuf接口返回下次拉取需要传入GetMediaData的indexbuf。
     * 3、indexbuf一般格式如右侧所示，”Range:bytes=524288-1048575“:表示这次拉取的是从524288到1048575的分片。单个文件首次拉取填写的indexbuf为空字符串，拉取后续分片时直接填入上次返回的indexbuf即可。
     */
    String indexbuf = "";
    int ret, data_len = 0;
    log.debug("正在分片拉取媒体文件 sdkFileId为{}", sdkfileid);
    while (true) {
      long mediaData = Finance.NewMediaData();
      ret = Finance.GetMediaData(sdk, indexbuf, sdkfileid, proxy, passwd, timeout, mediaData);
      if (ret != 0) {
        Finance.FreeMediaData(mediaData);
        throw new WxErrorException("getmediadata err ret " + ret);
      }

      data_len += Finance.GetDataLen(mediaData);
      log.debug("正在分片拉取媒体文件 len:{}, data_len:{}, is_finish:{} \n", Finance.GetIndexLen(mediaData), data_len, Finance.IsMediaDataFinish(mediaData));

      try {
        // 大于512k的文件会分片拉取，此处需要使用追加写，避免后面的分片覆盖之前的数据。
        action.accept(Finance.GetData(mediaData));
      } catch (Exception e) {
        e.printStackTrace();
      }

      if (Finance.IsMediaDataFinish(mediaData) == 1) {
        // 已经拉取完成最后一个分片
        Finance.FreeMediaData(mediaData);
        break;
      } else {
        // 获取下次拉取需要使用的indexbuf
        indexbuf = Finance.GetOutIndexBuf(mediaData);
        Finance.FreeMediaData(mediaData);
      }
    }
  }

  @Override
  public List<String> getPermitUserList(Integer type) throws WxErrorException {
    final String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(GET_PERMIT_USER_LIST);
    JsonObject jsonObject = new JsonObject();
    if (type != null) {
      jsonObject.addProperty("type", type);
    }
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpGsonBuilder.create().fromJson(GsonParser.parse(responseContent).getAsJsonArray("ids"),
      new TypeToken<List<String>>() {
      }.getType());
  }

  @Override
  public WxCpGroupChat getGroupChat(@NonNull String roomid) throws WxErrorException {
    final String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(GET_GROUP_CHAT);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("roomid", roomid);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpGroupChat.fromJson(responseContent);
  }

  @Override
  public WxCpAgreeInfo checkSingleAgree(@NonNull WxCpCheckAgreeRequest checkAgreeRequest) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(CHECK_SINGLE_AGREE);
    String responseContent = this.cpService.post(apiUrl, checkAgreeRequest.toJson());
    return WxCpAgreeInfo.fromJson(responseContent);
  }

}
