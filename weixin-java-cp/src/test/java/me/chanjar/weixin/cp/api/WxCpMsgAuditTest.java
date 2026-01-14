package me.chanjar.weixin.cp.api;

import com.google.common.collect.Lists;
import com.tencent.wework.Finance;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.util.XmlUtils;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.msgaudit.*;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.constant.WxCpConsts;
import me.chanjar.weixin.cp.demo.WxCpDemoInMemoryConfigStorage;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import me.chanjar.weixin.cp.util.xml.XStreamTransformer;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.*;

/**
 * 企业微信会话内容存档测试类.
 * <a href="https://developer.work.weixin.qq.com/document/path/91360">官方文档</a>
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a>
 * created on  2022-01-17
 */
@Slf4j
public class WxCpMsgAuditTest {

  private static WxCpConfigStorage wxCpConfigStorage;
  private static WxCpService cpService;

  /**
   * Test.
   */
// com.binarywang.spring.starter.wxjava.cp.config.WxCpServiceAutoConfiguration
  // WxCpServiceImpl.getAccessToken()
  @BeforeTest
  private void initCpService() {
    if (cpService == null) {
      InputStream inputStream = ClassLoader.getSystemResourceAsStream("test-config.xml");
      wxCpConfigStorage = WxCpDemoInMemoryConfigStorage.fromXml(inputStream);
      cpService = new WxCpServiceImpl();
      cpService.setWxCpConfigStorage(this.wxCpConfigStorage);
    }
  }

  @Test
  public void test() throws Exception {
    /*
     * 客户同意进行聊天内容存档事件回调
     * 配置了客户联系功能的成员添加外部联系人同意进行聊天内容存档时，回调该事件。
     *
     * https://developer.work.weixin.qq.com/document/path/92005
     */
    String msgAuditApprovedXml = "<xml>\n" +
      "\t<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
      "\t<FromUserName><![CDATA[sys]]></FromUserName> \n" +
      "\t<CreateTime>1403610513</CreateTime>\n" +
      "\t<MsgType><![CDATA[event]]></MsgType>\n" +
      "\t<Event><![CDATA[change_external_contact]]></Event>\n" +
      "\t<ChangeType><![CDATA[msg_audit_approved]]></ChangeType>\n" +
      "\t<UserID><![CDATA[zhangsan]]></UserID>\n" +
      "\t<ExternalUserID><![CDATA[woAJ2GCAAABiuyujaWJHDDGi0mACHAAA]]></ExternalUserID>\n" +
      "\t<WelcomeCode><![CDATA[WELCOMECODE]]></WelcomeCode>\n" +
      "</xml>";

    final WxCpXmlMessage msgAuditApprovedXmlMsg = XStreamTransformer.fromXml(WxCpXmlMessage.class, msgAuditApprovedXml);
    msgAuditApprovedXmlMsg.setAllFieldsMap(XmlUtils.xml2Map(msgAuditApprovedXml));
    log.info("msgAuditApprovedXmlMsg:{}", WxCpGsonBuilder.create().toJson(msgAuditApprovedXmlMsg));

    /*
     * 产生会话回调事件
     * 为了提升企业会话存档的使用性能，降低无效的轮询次数。
     * 当企业收到或发送新消息时，企业微信可以以事件的形式推送到企业指定的url。回调间隔为15秒，在15秒内若有消息则触发回调，若无消息则不会触发回调。
     *
     * https://developer.work.weixin.qq.com/document/path/95039
     */
    String msgAuditNotifyXml = "<xml>\n" +
      "   <ToUserName><![CDATA[CorpID]]></ToUserName>\n" +
      "   <FromUserName><![CDATA[sys]]></FromUserName> \n" +
      "   <CreateTime>1629101687</CreateTime>\n" +
      "   <MsgType><![CDATA[event]]></MsgType>\n" +
      "   <AgentID>2000004</AgentID>\n" +
      "   <Event><![CDATA[msgaudit_notify]]></Event>\n" +
      "</xml>";

    final WxCpXmlMessage msgAuditNotifyXmlMsg = XStreamTransformer.fromXml(WxCpXmlMessage.class, msgAuditNotifyXml);
    msgAuditNotifyXmlMsg.setAllFieldsMap(XmlUtils.xml2Map(msgAuditNotifyXml));
    log.info("msgAuditNotifyXmlMsg:{}", WxCpGsonBuilder.create().toJson(msgAuditNotifyXmlMsg));

    /*
     * 增加变更事件类型：产生会话回调事件
     */
    String msgauditNotify = WxCpConsts.EventType.MSGAUDIT_NOTIFY;


    /*
     * 仔细配置：
     * <xml>
     * <corpId>ww45xxx88865xxx</corpId>
     * <corpSecret>xIpum7Yt4NMXcyxdzcQ2l_46BG4QIQDR57MhA45ebIw</corpSecret> // secret
     * <agentId>200000</agentId> // 会话存档的应用id
     * <token></token> // 回调配置的token
     * <aesKey></aesKey> // 回调配置的EncodingAESKey
     *
     * // 企业微信会话存档
     * // 1、会话存档私钥，最好去除前缀和换行，如下所示！
     * // 2、仔细配置windows以及linux环境sdk路径
     * <msgAuditPriKey>MIxxx893B2pggd1r95T8k2QxxxxbD6xxxxmXsskn
     * +5XunyR1WJlJGqgi0OMVGYvSfkNb9kD50fM21CGLcN1y4miL9fVNBIsvJmIUeJCNS8TioAVGFvh2EgzjqTR1gH</msgAuditPriKey>
     * <msgAuditLibPath>/www/osfile/libcrypto-1_1-x64.dll,libssl-1_1-x64.dll,libcurl-x64.dll,WeWorkFinanceSdk.dll,
     * libWeWorkFinanceSdk_Java.so</msgAuditLibPath>
     * </xml>
     *
     * 注意：最好先配置lib开头的系统库，再配置sdk类库，配置绝对路径，最好配置为linux路径
     * Windows:
     * <msgAuditLibPath>D:/WorkSpace/libcrypto-1_1-x64.dll,libssl-1_1-x64.dll,libcurl-x64.dll,WeWorkFinanceSdk.dll,
     * libWeWorkFinanceSdk_Java.so</msgAuditLibPath>
     * Linux:
     * <msgAuditLibPath>/www/osfile/work_msg_storage/libcrypto-1_1-x64.dll,libssl-1_1-x64.dll,libcurl-x64.dll,
     * WeWorkFinanceSdk.dll,libWeWorkFinanceSdk_Java.so</msgAuditLibPath>
     *
     *
     * yml配置（支持多个corpId）：
     * wx:
     *   cp:
     *     appConfigs:
     *     - agentId: 10001 #客户联系
     *       corpId: xxxxxxxxxxx
     *       secret: T5fTj1n-sBAT4rKNW5c9IYNfPdXZxxxxxxxxxxx
     *       token: 2bSNqTcLtxxxxxxxxxxx
     *       aesKey: AXazu2Xyw44SNY1x8go2phn9p9B2xxxxxxxxxxx
     *     - agentId: 10002 #会话内容存档
     *       corpId: xxxxxxxxxxx
     *       secret: xIpum7Yt4NMXcyxdzcQ2l_46BG4Qxxxxxxxxxxx
     *       token:
     *       aesKey:
     *       msgAuditPriKey: MIxxx893B2pggd1r95T8k2QxxxxbD6xxxxmXsskn
     *       +5XunyR1WJlJGqgi0OMVGYvSfkNb9kD50fM21CGLcN1y4miL9fVNBIsvJmIUeJCNS8TioAVGFvh2EgzjqTR1gHxxx
     *       msgAuditLibPath: /www/osfile/libcrypto-1_1-x64.dll,libssl-1_1-x64.dll,libcurl-x64.dll,WeWorkFinanceSdk
     *       .dll,libWeWorkFinanceSdk_Java.so
     *
     *
     * 在线生成非对称加密公钥私钥对：
     * http://web.chacuo.net/netrsakeypair
     *
     *
     * 或者可以在linux上使用如下命令生成公钥私钥对：
     * openssl genrsa -out private_key.pem 2048
     * openssl rsa -in private_key.pem -pubout -out public_key.pem
     * /

     /*
     * 建议放到redis，本次请求获取消息记录开始的seq值。首次访问填写0，非首次使用上次企业微信返回的最大seq。允许从任意seq重入拉取。
     */
    long seq = 0L;

    /*
     * 图片，语音，视频，表情，文件，音频存档消息,音频共享文档消息调用  获取媒体消息
     */
    List<String> mediaType = Arrays.asList(WxCpConsts.MsgAuditMediaType.IMAGE,
      WxCpConsts.MsgAuditMediaType.VOICE, WxCpConsts.MsgAuditMediaType.VIDEO,
      WxCpConsts.MsgAuditMediaType.EMOTION, WxCpConsts.MsgAuditMediaType.FILE,
      WxCpConsts.MsgAuditMediaType.MEETING_VOICE_CALL, WxCpConsts.MsgAuditMediaType.VOIP_DOC_SHARE);

    // 模拟多次拉取数据，根据seq拉取
    for (int i = 0; i < 3; i++) {
      // 本次请求获取消息记录开始的seq值。首次访问填写0，非首次使用上次企业微信返回的最大seq。允许从任意seq重入拉取。
      WxCpChatDatas chatDatas = cpService.getMsgAuditService().getChatDatas(seq, 10L, null, null, 1000L);
      if (chatDatas != null && chatDatas.getChatData().size() > 0) {

        List<WxCpChatDatas.WxCpChatData> chatdata = chatDatas.getChatData();
        Iterator<WxCpChatDatas.WxCpChatData> iterator = chatdata.iterator();
        while (iterator.hasNext()) {
          WxCpChatDatas.WxCpChatData chatData = iterator.next();
          seq = chatData.getSeq();

          // 数据
//          String msgId = chatData.getMsgId();
//          String encryptChatMsg = chatData.getEncryptChatMsg();
//          String encryptRandomKey = chatData.getEncryptRandomKey();
//          Integer publickeyVer = chatData.getPublickeyVer();

          // 获取明文数据
          final String chatPlainText = cpService.getMsgAuditService().getChatPlainText(chatDatas.getSdk(), chatData, 2);
          final WxCpChatModel wxCpChatModel = WxCpChatModel.fromJson(chatPlainText);
          log.info("明文数据为：{}", wxCpChatModel.toJson());

          // 获取消息数据
          // https://developer.work.weixin.qq.com/document/path/91774
          final WxCpChatModel decryptData = cpService.getMsgAuditService().getDecryptData(chatDatas.getSdk(),
            chatData, 2);
          log.info("获取消息数据为：{}", decryptData.toJson());

          /*
           * 注意：
           * 根据上面返回的文件类型来获取媒体文件，
           * 不同的文件类型，拼接好存放文件的绝对路径，写入文件流，获取媒体文件。（拼接绝对文件路径的原因，以便上传到腾讯云或阿里云对象存储）
           *
           * 目标文件绝对路径+实际文件名，比如：/usr/local/file/20220114/474f866b39d10718810d55262af82662.gif
           */
          String path = "/usr/local/file/";
          String msgType = decryptData.getMsgType();
          if (mediaType.contains(decryptData.getMsgType())) {
            // 文件后缀
            String suffix = "";
            // 文件名md5
            String md5Sum = "";
            // sdkFileId
            String sdkFileId = "";
            switch (msgType) {
              case WxCpConsts.MsgAuditMediaType.IMAGE:
                suffix = WxCpConsts.MsgAuditMediaType.MsgAuditSuffix.JPG;
                md5Sum = decryptData.getImage().getMd5Sum();
                sdkFileId = decryptData.getImage().getSdkFileId();
                break;
              case WxCpConsts.MsgAuditMediaType.VOICE:
                suffix = WxCpConsts.MsgAuditMediaType.MsgAuditSuffix.AMR;
                md5Sum = decryptData.getVoice().getMd5Sum();
                sdkFileId = decryptData.getVoice().getSdkFileId();
                break;
              case WxCpConsts.MsgAuditMediaType.VIDEO:
                suffix = WxCpConsts.MsgAuditMediaType.MsgAuditSuffix.MP4;
                md5Sum = decryptData.getVideo().getMd5Sum();
                sdkFileId = decryptData.getVideo().getSdkFileId();
                break;
              case WxCpConsts.MsgAuditMediaType.EMOTION:
                md5Sum = decryptData.getEmotion().getMd5Sum();
                sdkFileId = decryptData.getEmotion().getSdkFileId();
                int type = decryptData.getEmotion().getType();
                switch (type) {
                  case 1:
                    suffix = WxCpConsts.MsgAuditMediaType.MsgAuditSuffix.GIF;
                    break;
                  case 2:
                    suffix = WxCpConsts.MsgAuditMediaType.MsgAuditSuffix.PNG;
                    break;
                  default:
                    return;
                }
                break;
              case WxCpConsts.MsgAuditMediaType.FILE:
                md5Sum = decryptData.getFile().getMd5Sum();
                suffix = "." + decryptData.getFile().getFileExt();
                sdkFileId = decryptData.getFile().getSdkFileId();
                break;
              // 音频存档消息
              case WxCpConsts.MsgAuditMediaType.MEETING_VOICE_CALL:

                md5Sum = decryptData.getVoiceId();
                sdkFileId = decryptData.getMeetingVoiceCall().getSdkFileId();
                for (WxCpChatModel.MeetingVoiceCall.DemoFileData demofiledata :
                  decryptData.getMeetingVoiceCall().getDemoFileData()) {
                  String demoFileDataFileName = demofiledata.getFileName();
                  suffix = demoFileDataFileName.substring(demoFileDataFileName.lastIndexOf(".") + 1);
                }

                break;
              // 音频共享文档消息
              case WxCpConsts.MsgAuditMediaType.VOIP_DOC_SHARE:

                md5Sum = decryptData.getVoipId();
                WxCpFileItem docShare = decryptData.getVoipDocShare();
                String fileName = docShare.getFileName();
                suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

                break;
              default:
                return;
            }

            /*
             * 拉取媒体文件
             *
             * 注意：
             * 1、根据上面返回的文件类型，拼接好存放文件的绝对路径即可。此时绝对路径写入文件流，来达到获取媒体文件的目的。
             * 2、拉取完媒体文件之后，此时文件已经存在绝对路径，可以通过mq异步上传到对象存储
             * 3、比如可以上传到阿里云oss或者腾讯云cos
             */
            String targetPath = path + md5Sum + suffix;
            cpService.getMsgAuditService().getMediaFile(chatDatas.getSdk(), sdkFileId, null, null, 1000L, targetPath);

          }
        }
      }
      // 注意：
      // 当此批次数据拉取完毕后，应释放此次sdk
      log.info("释放sdk {}", chatDatas.getSdk());
      Finance.DestroySdk(chatDatas.getSdk());

    }


    /*
     * 文本
     */
//    String text = "{\"msgid\":\"CAQQluDa4QUY0On2rYSAgAMgzPrShAE=\",\"action\":\"send\",\"from\":\"XuJinSheng\",
//    \"tolist\":[\"icefog\"],\"roomid\":\"\",\"msgtime\":1547087894783,\"msgtype\":\"text\",
//    \"text\":{\"content\":\"test\"}}";
    String text = "{\"msgid\":\"CAQQluDa4QUY0On2rYSAgAMgzPrShAE=\",\"action\":\"send\",\"from\":\"XuJinSheng\"," +
      "\"tolist\":[\"icefog\"],\"roomid\":\"\",\"msgtime\":1547087894783,\"msgtype\":\"text\"," +
      "\"text\":{\"content\":\"这是一条引用/回复消息：\\\"\\n------\\n@nick777\"}}";
    WxCpChatModel modelText = WxCpChatModel.fromJson(text);
    log.info("数据为：" + modelText.toJson());


    /*
     * 图片
     */
    String image = "{\"msgid\":\"CAQQvPnc4QUY0On2rYSAgAMgooLa0Q8=\",\"action\":\"send\",\"from\":\"XuJinSheng\"," +
      "\"tolist\":[\"icefog\"],\"roomid\":\"\",\"msgtime\":0,\"msgtype\":\"image\"," +
      "\"image\":{\"md5sum\":\"50de8e5ae8ffe4f1df7a93841f71993a\",\"filesize\":70961," +
      "\"sdkfileid" +
      "\":\"CtYBMzA2OTAyMDEwMjA0NjIzMDYwMDIwMTAwMDIwNGI3ZmU0MDZlMDIwMzBmNTliMTAyMDQ1YzliNTQ3NzAyMDQ1YzM3M2NiYzA0MjQ2NjM0MzgzNTM0NjEzNTY1MmQzNDYxMzQzODJkMzQzMTYxNjEyZDM5NjEzOTM2MmQ2MTM2NjQ2NDY0NjUzMDY2NjE2NjM1MzcwMjAxMDAwMjAzMDExNTQwMDQxMDUwZGU4ZTVhZThmZmU0ZjFkZjdhOTM4NDFmNzE5OTNhMDIwMTAyMDIwMTAwMDQwMBI4TkRkZk1UWTRPRGcxTVRBek1ETXlORFF6TWw4eE9UUTVOamN6TkRZMlh6RTFORGN4TWpNNU1ERT0aIGEwNGQwYWUyM2JlYzQ3NzQ5MjZhNWZjMjk0ZTEyNTkz\"}}";
    WxCpChatModel modelImage = WxCpChatModel.fromJson(image);
    log.info("数据为：" + modelImage.toJson());


    /*
     * 撤回消息
     */
    String revoke = "{\"msgid\":\"15775510700152506326_1603875615\",\"action\":\"recall\",\"from\":\"kenshin\"," +
      "\"tolist\":[\"wmUu0zBgAALV7ZymkcMyxvbTe8YdWxxA\"],\"roomid\":\"\",\"msgtime\":1603875615723," +
      "\"msgtype\":\"revoke\",\"revoke\":{\"pre_msgid\":\"14822339130656386894_1603875600\"}}";
    WxCpChatModel modelRevoke = WxCpChatModel.fromJson(revoke);
    log.info("数据为：" + modelRevoke.toJson());


    /*
     * 同意会话聊天内容
     */
    String agree = "{\"msgid\":\"8891446340739254950_1603875826\",\"action\":\"send\"," +
      "\"from\":\"wmGAgeDQAAvQeaTqWwkMTxGMkvI7OOuQ\",\"tolist\":[\"kenshin\"],\"roomid\":\"\"," +
      "\"msgtime\":1603875826656,\"msgtype\":\"agree\",\"agree\":{\"userid\":\"wmGAgeDQAAvQeaTqWwkMTxGMkvI7OOuQ\"," +
      "\"agree_time\":1603875826656}}";
    String disagree = "{\"msgid\":\"17972321270926900092_1603875944\",\"action\":\"send\"," +
      "\"from\":\"wmErxtDgAA9AW32YyyuYRimKr7D1KWlw\",\"tolist\":[\"kenshin\"],\"roomid\":\"\"," +
      "\"msgtime\":1603875944122,\"msgtype\":\"disagree\"," +
      "\"disagree\":{\"userid\":\"wmErxtDgAA9AW32YyyuYRimKr7D1KWlw\",\"disagree_time\":1603875944122}}";
    WxCpChatModel modelAgree = WxCpChatModel.fromJson(agree);
    WxCpChatModel modelDisagree = WxCpChatModel.fromJson(disagree);
    log.info("数据为：" + modelAgree.toJson());
    log.info("数据为：" + modelDisagree.toJson());


    /*
     * 语音
     */
    String voice = "{\"msgid\":\"10958372969718811103_1603875609\",\"action\":\"send\"," +
      "\"from\":\"wmGAgeDQAAdBjb8CK4ieMPRm7Cqm-9VA\",\"tolist\":[\"kenshin\"],\"roomid\":\"\"," +
      "\"msgtime\":1603875609704,\"msgtype\":\"voice\",\"voice\":{\"md5sum\":\"9db09c7fa627c9e53f17736c786a74d5\"," +
      "\"voice_size\":6810,\"play_length\":10," +
      "\"sdkfileid" +
      "\":\"kcyZjZqOXhETGYxajB2Zkp5Rk8zYzh4RVF3ZzZGdXlXNWRjMUoxVGZxbzFTTDJnQ2YxL0NraVcxUUJNK3VUamhEVGxtNklCbjZmMEEwSGRwN0h2cU1GQTU1MDRSMWdTSmN3b25ZMkFOeG5hMS90Y3hTQ0VXRlVxYkR0Ymt5c3JmV2VVcGt6UlNXR1ZuTFRWVGtudXVldDRjQ3hscDBrMmNhMFFXVnAwT3Y5NGVqVGpOcWNQV2wrbUJwV01TRm9xWmNDRVVrcFY5Nk9OUS9GbXIvSmZvOVVZZjYxUXBkWnMvUENkVFQxTHc2N0drb2pJT0FLZnhVekRKZ1FSNDU3ZnZtdmYvTzZDOG9DRXl2SUNIOHc9PRI0TkRkZk56ZzRNVE13TVRjMk5qQTRNak0yTmw4ek5qRTVOalExTjE4eE5qQXpPRGMxTmpBNRogNzM3MDY2NmM2YTc5Njg3NDdhNzU3NDY0NzY3NTY4NjY=\"}}";
    WxCpChatModel modelVoice = WxCpChatModel.fromJson(voice);
    log.info("数据为：" + modelVoice.toJson());


    /*
     * 视频
     */
    String video = "{\"msgid\":\"17955920891003447432_1603875627\",\"action\":\"send\",\"from\":\"kenshin\"," +
      "\"tolist\":[\"wmGAgeDQAAHuRJbt4ZQI_1cqoQcf41WQ\"],\"roomid\":\"\",\"msgtime\":1603875626823," +
      "\"msgtype\":\"video\",\"video\":{\"md5sum\":\"d06fc80c01d6fbffcca3b229ba41eac6\",\"filesize\":15169724," +
      "\"play_length\":108," +
      "\"sdkfileid" +
      "\":\"MzAzMjYxMzAzNTYzMzgzMjMyMzQwMjAxMDAwMjA0MDBlNzc4YzAwNDEwZDA2ZmM4MGMwMWQ2ZmJmZmNjYTNiMjI5YmE0MWVhYzYwMjAxMDQwMjAxMDAwNDAwEjhORGRmTVRZNE9EZzFNREEyTlRjM056QXpORjgxTWpZeE9USTBOek5mTVRZd016ZzNOVFl5Tnc9PRogNTIzNGQ1NTQ5N2RhNDM1ZDhlZTU5ODk4NDQ4NzRhNDk=\"}}";
    WxCpChatModel modelVideo = WxCpChatModel.fromJson(video);
    log.info("数据为：" + modelVideo.toJson());


    /*
     * 名片
     */
    String card = "{\"msgid\":\"13714216591700685558_1603875680\",\"action\":\"send\",\"from\":\"kenshin\"," +
      "\"tolist\":[\"wmGAgeDQAAy2Dtr0F8aK4dTuatfm-5Rg\"],\"roomid\":\"\",\"msgtime\":1603875680377," +
      "\"msgtype\":\"card\",\"card\":{\"corpname\":\"微信联系人\",\"userid\":\"wmGAgeDQAAGjFmfnP7A3j2JxQDdLNhSw\"}}";
    WxCpChatModel modelCard = WxCpChatModel.fromJson(card);
    log.info("数据为：" + modelCard.toJson());


    /*
     * 位置
     */
    String location = "{\"msgid\":\"2641513858500683770_1603876152\",\"action\":\"send\",\"from\":\"icefog\"," +
      "\"tolist\":[\"wmN6etBgAA0sbJ3invMvRxPQDFoq9uWA\"],\"roomid\":\"\",\"msgtime\":1603876152141," +
      "\"msgtype\":\"location\",\"location\":{\"longitude\":116.586285899,\"latitude\":39.911125799," +
      "\"address\":\"北京市xxx区xxx路xxx大厦x座\",\"title\":\"xxx管理中心\",\"zoom\":15}}";
    WxCpChatModel modelLocation = WxCpChatModel.fromJson(location);
    log.info("数据为：" + modelLocation.toJson());


    /*
     * 表情
     */
    String emotion = "{\"msgid\":\"6623217619416669654_1603875612\",\"action\":\"send\",\"from\":\"icef\"," +
      "\"tolist\":[\"wmErxtDgAAhteCglUZH2kUt3rq431qmg\"],\"roomid\":\"\",\"msgtime\":1603875611148," +
      "\"msgtype\":\"emotion\",\"emotion\":{\"type\":1,\"width\":290,\"height\":290,\"imagesize\":962604," +
      "\"md5sum\":\"94c2b0bba52cc456cb8221b248096612\"," +
      "\"sdkfileid\":\"4eE1ESTVNalE1TnprMFh6RTJNRE00TnpVMk1UST0aIDc0NzI2NjY1NzE3NTc0Nzg2ZDZlNzg2YTY5NjY2MTYx\"}}";
    WxCpChatModel modelEmotion = WxCpChatModel.fromJson(emotion);
    log.info("数据为：" + modelEmotion.toJson());


    /*
     * 文件
     */
    String file = "{\"msgid\":\"18039699423706571225_1603875608\",\"action\":\"send\",\"from\":\"kens\"," +
      "\"tolist\":[\"wmErxtDgAArDlFIhf76O6w4GxU81al8w\"],\"roomid\":\"\",\"msgtime\":1603875608214," +
      "\"msgtype\":\"file\",\"file\":{\"md5sum\":\"18e93fc2ea884df23b3d2d3b8667b9f0\",\"filename\":\"资料.docx\"," +
      "\"fileext\":\"docx\",\"filesize\":18181," +
      "\"sdkfileid" +
      "\":\"E4ODRkZjIzYjNkMmQzYjg2NjdiOWYwMDIwMTA1MDIwMTAwMDQwMBI4TkRkZk1UWTRPRGcxTURrek9UZzBPVEF6TTE4eE1EUXpOVGcxTlRVNVh6RTJNRE00TnpVMk1EZz0aIDMwMzkzMzY0NjEzNjM3NjY2NDY1NjMzNjYxMzIzNzYx\"}}";
    WxCpChatModel modelFile = WxCpChatModel.fromJson(file);
    log.info("数据为：" + modelFile.toJson());


    /*
     * 链接
     */
    String link = "{\"msgid\":\"11788441727514772650_1603875624\",\"action\":\"send\",\"from\":\"kenshin\"," +
      "\"tolist\":[\"0000726\"],\"roomid\":\"\",\"msgtime\":1603875624476,\"msgtype\":\"link\"," +
      "\"link\":{\"title\":\"邀请你加入群聊\",\"description\":\"技术支持群，进入可查看详情\",\"link_url\":\"https://work.weixin.qq" +
      ".com/wework_admin/external_room/join/exceed?vcode=xxx\",\"image_url\":\"https://wework.qpic.cn/wwpic/xxx/0\"}}";
    WxCpChatModel modelLink = WxCpChatModel.fromJson(link);
    log.info("数据为：" + modelLink.toJson());


    /*
     * 小程序消息
     */
    String weapp = "{\"msgid\":\"11930598857592605935_1603875608\",\"action\":\"send\",\"from\":\"kens\"," +
      "\"tolist\":[\"wmGAgeDQAAsgQetTQGqRbMxrkodpM3fA\"],\"roomid\":\"\",\"msgtime\":1603875608691," +
      "\"msgtype\":\"weapp\",\"weapp\":{\"title\":\"开始聊天前请仔细阅读服务须知事项\",\"description\":\"客户需同意存档聊天记录\"," +
      "\"username\":\"xxx@app\",\"displayname\":\"服务须知\"}}";
    WxCpChatModel modelWeapp = WxCpChatModel.fromJson(weapp);
    log.info("数据为：" + modelWeapp.toJson());


    /*
     * 会话记录消息
     */
    String chatrecord = "{\"msgid\":\"11354299838102555191_1603875658\",\"action\":\"send\",\"from\":\"ken\"," +
      "\"tolist\":[\"icef\"],\"roomid\":\"\",\"msgtime\":1603875657905,\"msgtype\":\"chatrecord\"," +
      "\"chatrecord\":{\"title\":\"群聊\",\"item\":[{\"type\":\"ChatRecordText\",\"msgtime\":1603875610," +
      "\"content\":\"{\\\"content\\\":\\\"test\\\"}\",\"from_chatroom\":false},{\"type\":\"ChatRecordText\"," +
      "\"msgtime\":1603875620,\"content\":\"{\\\"content\\\":\\\"test2\\\"}\",\"from_chatroom\":false}]}}";
    WxCpChatModel modelChatRecord = WxCpChatModel.fromJson(chatrecord);
    log.info("数据为：" + modelChatRecord.toJson());


    /*
     * 填表消息
     */
    String collect = "{\"msgid\":\"2500536226619379797_1576034482\",\"action\":\"send\",\"from\":\"nick\"," +
      "\"tolist\":[\"XuJinSheng\",\"15108264797\"],\"roomid\":\"wrjc7bDwYAOAhf9quEwRRxyyoMm0QAAA\"," +
      "\"msgtime\":1576034482344,\"msgtype\":\"collect\",\"collect\":{\"room_name\":\"这是一个群\",\"creator\":\"nick\"," +
      "\"create_time\":\"2019-12-11 11:21:22\",\"title\":\"这是填表title\",\"details\":[{\"id\":1,\"ques\":\"表项1，文本\"," +
      "\"type\":\"Text\"},{\"id\":2,\"ques\":\"表项2，数字\",\"type\":\"Number\"},{\"id\":3,\"ques\":\"表项3，日期\"," +
      "\"type\":\"Date\"},{\"id\":4,\"ques\":\"表项4，时间\",\"type\":\"Time\"}]}}";
    WxCpChatModel modelCollect = WxCpChatModel.fromJson(collect);
    log.info("数据为：" + modelCollect.toJson());


    /*
     * 红包消息
     */
    String redpacket = "{\"msgid\":\"333590477316965370_1603877439\",\"action\":\"send\",\"from\":\"kens\"," +
      "\"tolist\":[\"1000000444696\"],\"roomid\":\"\",\"msgtime\":1603877439038,\"msgtype\":\"redpacket\"," +
      "\"redpacket\":{\"type\":1,\"wish\":\"恭喜发财，大吉大利\",\"totalcnt\":1,\"totalamount\":3000}}";
    WxCpChatModel modelRedpacket = WxCpChatModel.fromJson(redpacket);
    log.info("数据为：" + modelRedpacket.toJson());


    /*
     * 会议邀请信息
     */
    String meeting = "{\"msgid\":\"5935786683775673543_1603877328\",\"action\":\"send\",\"from\":\"ken\"," +
      "\"tolist\":[\"icef\",\"test\"],\"roomid\":\"wr2vOpDgAAN4zVWKbS\",\"msgtime\":1603877328914," +
      "\"msgtype\":\"meeting\",\"meeting\":{\"topic\":\"夕会\",\"starttime\":1603877400,\"endtime\":1603881000," +
      "\"address\":\"\",\"remarks\":\"\",\"meetingtype\":102,\"meetingid\":1210342560,\"status\":1}}";
    WxCpChatModel modelMeeting = WxCpChatModel.fromJson(meeting);
    log.info("数据为：" + modelMeeting.toJson());


    /*
     * 切换企业日志
     */
    String switchlog = "{\"msgid\":\"125289002219525886280\",\"action\":\"switch\",\"time\":1554119421840," +
      "\"user\":\"XuJinSheng\"}";
    WxCpChatModel modelSwitchLog = WxCpChatModel.fromJson(switchlog);
    log.info("数据为：" + modelSwitchLog.toJson());


    /*
     * 在线文档消息
     */
    String docMsg = "{\"msgid\":\"9732089160923053207_1603877765\",\"action\":\"send\",\"from\":\"ken\"," +
      "\"tolist\":[\"icef\",\"test\"],\"roomid\":\"wrJawBCQAAStr3jxVxEH\",\"msgtime\":1603877765291," +
      "\"msgtype\":\"docmsg\",\"doc\":{\"title\":\"测试&演示客户\",\"doc_creator\":\"test\",\"link_url\":\"https://doc" +
      ".weixin.qq.com/txdoc/excel?docid=xxx\"}}";
    WxCpChatModel modelDocMsg = WxCpChatModel.fromJson(docMsg);
    log.info("数据为：" + modelDocMsg.toJson());


    /*
     * MarkDown格式消息
     */
    String markDown = "{\"msgid\":\"7546287934688259248_1603875715\",\"action\":\"send\",\"from\":\"ken\"," +
      "\"tolist\":[\"icef\",\"test\"],\"roomid\":\"wr0SfLCgAAgCaCPeM33UNe\",\"msgtime\":1603875715782," +
      "\"msgtype\":\"markdown\",\"info\":{\"content\":\"请前往系统查看，谢谢。\"}}";
    WxCpChatModel modelMarkDown = WxCpChatModel.fromJson(markDown);
    log.info("数据为：" + modelMarkDown.toJson());


    /*
     * 图文消息
     */
    String news = "{\"msgid\":\"118732825779547782215\",\"action\":\"send\",\"from\":\"kens\",\"tolist\":[\"icef\"," +
      "\"test\"],\"roomid\":\"wrErxtDgAA0jgXE5\",\"msgtime\":1603876045165,\"msgtype\":\"news\"," +
      "\"info\":{\"item\":[{\"title\":\"service \",\"description\":\"test\",\"url\":\"http://xxx\"," +
      "\"picurl\":\"https://www.qq.com/xxx.jpg\"}]}}";
    WxCpChatModel modelNews = WxCpChatModel.fromJson(news);
    log.info("数据为：" + modelNews.toJson());


    /*
     * 日程消息
     */
    String calendar = "{\"msgid\":\"2345881211604379705_1603877680\",\"action\":\"send\",\"from\":\"ken\"," +
      "\"tolist\":[\"icef\",\"test\"],\"roomid\":\"wr2LO0CAAAFrTZCGWWAxBA\",\"msgtime\":1603877680795," +
      "\"msgtype\":\"calendar\",\"calendar\":{\"title\":\"xxx业绩复盘会\",\"creatorname\":\"test\"," +
      "\"attendeename\":[\"aaa\",\"bbb\"],\"starttime\":1603882800,\"endtime\":1603886400,\"place\":\"\"," +
      "\"remarks\":\"\"}}";
    WxCpChatModel modelCalendar = WxCpChatModel.fromJson(calendar);
    log.info("数据为：" + modelCalendar.toJson());


    /*
     * 混合消息
     */
    String mixed = "{\"msgid\":\"DAQQluDa4QUY0On4kYSABAMgzPrShAE=\",\"action\":\"send\",\"from\":\"HeMiao\"," +
      "\"tolist\":[\"HeChangTian\",\"LiuZeYu\"],\"roomid\":\"wr_tZ2BwAAUwHpYMwy9cIWqnlU3Hzqfg\"," +
      "\"msgtime\":1577414359072,\"msgtype\":\"mixed\",\"mixed\":{\"item\":[{\"type\":\"text\"," +
      "\"content\":\"{\\\"content\\\":\\\"你好[微笑]\\\\n\\\"}\"},{\"type\":\"image\"," +
      "\"content\":\"{\\\"md5sum\\\":\\\"368b6c18c82e6441bfd89b343e9d2429\\\",\\\"filesize\\\":13177," +
      "\\\"sdkfileid" +
      "\\\":\\\"CtYBMzA2OTAyMDEwMjA0NjIzMDYwMDIwMTAwMDWwNDVmYWY4Y2Q3MDIwMzBmNTliMTAyMDQwYzljNTQ3NzAyMDQ1ZTA1NmFlMjA0MjQ2NjM0NjIzNjY2MzYzNTMyMmQzNzYxMzQ2NDJkMzQ2MjYxNjQyZDM4MzMzMzM4MmQ3MTYyMzczMTM4NjM2NDYxMzczMjY2MzkwMjAxMDAwMjAzMDIwMDEwMDQxMDM2OGI2YzE4YzgyZTY0NDFiZmQ4OWIyNDNlOWQyNDI4MDIwMTAyMDIwMTAwMDQwMBI4TkRkZk2UWTRPRGcxTVRneE5URTFNRGc1TVY4eE1UTTFOak0yTURVeFh6RTFOemMwTVRNek5EYz0aIDQzMTY5NDFlM2MxZDRmZjhhMjEwY2M0NDQzZGUXOTEy\\\"}\"}]}}";
    WxCpChatModel modelMixed = WxCpChatModel.fromJson(mixed);
    log.info("获取混合消息，文件对象为：{}", modelMixed.getMixed().getItem().get(0).getContent());

    // 返回文件对象
    WxCpFileItem wxCpFileItem = WxCpFileItem.fromJson(modelMixed.getMixed().getItem().get(1).getContent());
    log.info("获取混合消息，文件对象为：{}", wxCpFileItem.toJson());
    log.info("数据为：" + modelMixed.toJson());


    /*
     * 音频存档消息
     */
    String meetingVoiceCall = "{\"msgid\":\"17952229780246929345_1594197637\",\"action\":\"send\"," +
      "\"from\":\"wo137MCgAAYW6pIiKKrDe5SlzEhSgwbA\",\"tolist\":[\"wo137MCgAAYW6pIiKKrDe5SlzEhSgwbA\"]," +
      "\"msgtime\":1594197581203,\"msgtype\":\"meeting_voice_call\"," +
      "\"voiceid\":\"grb8a4c48a3c094a70982c518d55e40557\",\"meeting_voice_call\":{\"endtime\":1594197635," +
      "\"sdkfileid" +
      "\":\"CpsBKjAqd0xhb2JWRUJldGtwcE5DVTB6UjRUalN6c09vTjVyRnF4YVJ5M24rZC9YcHF3cHRPVzRwUUlaMy9iTytFcnc0SlBkZDU1YjRNb0MzbTZtRnViOXV5WjUwZUIwKzhjbU9uRUlxZ3pyK2VXSVhUWVN2ejAyWFJaTldGSkRJVFl0aUhkcVdjbDJ1L2RPbjJsRlBOamJaVDNnPT0SOE5EZGZNVFk0T0RnMU16YzVNVGt5T1RJMk9GOHhNalk0TXpBeE9EZzJYekUxT1RReE9UYzJNemM9GiA3YTYyNzA3NTY4Nzc2MTY3NzQ2MTY0NzA2ZTc4NjQ2OQ==\",\"demofiledata\":[{\"filename\":\"65eb1cdd3e7a3c1740ecd74220b6c627.docx\",\"demooperator\":\"wo137MCgAAYW6pIiKKrDe5SlzEhSgwbA\",\"starttime\":1594197599,\"endtime\":1594197609}],\"sharescreendata\":[{\"share\":\"wo137MCgAAYW6pIiKKrDe5SlzEhSgwbA\",\"starttime\":1594197624,\"endtime\":1594197624}]}}";
    WxCpChatModel modelMeetingVoiceCall = WxCpChatModel.fromJson(meetingVoiceCall);
    log.info("数据为：" + modelMeetingVoiceCall.toJson());


    /*
     * 音频共享文档消息
     */
    String voipDocShare = "{\"msgid\":\"16527954622422422847_1594199256\",\"action\":\"send\"," +
      "\"from\":\"18002520162\",\"tolist\":[\"wo137MCgAAYW6pIiKKrDe5SlzEhSgwbA\"],\"msgtime\":1594199235014," +
      "\"msgtype\":\"voip_doc_share\",\"voipid\":\"gr2751c98b19300571f8afb3b74514bd32\"," +
      "\"voip_doc_share\":{\"filename\":\"欢迎使用微盘.pdf.pdf\",\"md5sum\":\"ff893900f24e55e216e617a40e5c4648\"," +
      "\"filesize\":4400654," +
      "\"sdkfileid" +
      "\":\"CpsBKjAqZUlLdWJMd2gvQ1JxMzd0ZjlpdW5mZzJOOE9JZm5kbndvRmRqdnBETjY0QlcvdGtHSFFTYm95dHM2VlllQXhkUUN5KzRmSy9KT3pudnA2aHhYZFlPemc2aVZ6YktzaVh3YkFPZHlqNnl2L2MvcGlqcVRjRTlhZEZsOGlGdHJpQ2RWSVNVUngrVFpuUmo3TGlPQ1BJemlRPT0SOE5EZGZNVFk0T0RnMU16YzVNVGt5T1RJMk9GODFNelUyTlRBd01qQmZNVFU1TkRFNU9USTFOZz09GiA3YTcwNmQ2Zjc5NjY3MDZjNjY2Zjc4NzI3NTZmN2E2YQ==\"}}";
    WxCpChatModel modelVoipDocShare = WxCpChatModel.fromJson(voipDocShare);
    log.info("数据为：" + modelVoipDocShare.toJson());


    /*
     * 互通红包消息
     */
    String externalRedpacket = "{\"msgid\":\"8632214264349267353_1603786184\",\"action\":\"send\"," +
      "\"from\":\"woJ7ijBwAAmqwojT8r_DaNMbr_NAvaag\",\"tolist\":[\"woJ7ijBwAA6SjS_sIyPLZtyEPJlT7Cfw\"," +
      "\"tiny-six768\"],\"roomid\":\"wrJ7ijBwAAG1vly_DzVI72Ghc-PtA5Dw\",\"msgtime\":1603786183955," +
      "\"msgtype\":\"external_redpacket\",\"redpacket\":{\"type\":1,\"wish\":\"恭喜发财，大吉大利\",\"totalcnt\":2," +
      "\"totalamount\":20}}";
    WxCpChatModel modelExternalRedpacket = WxCpChatModel.fromJson(externalRedpacket);
    log.info("数据为：" + modelExternalRedpacket.toJson());


    /*
     * 视频号消息
     */
    String sphfeed = "{\"msgid\":\"5702551662099334532_1619511584_external\",\"action\":\"send\"," +
      "\"from\":\"yangzhu1\",\"tolist\":[\"wmJSb5CgAA4aWXWndJspQGpJMDbsMwMA\"],\"roomid\":\"\"," +
      "\"msgtime\":1619511584444,\"msgtype\":\"sphfeed\",\"sphfeed\":{\"feed_type\":4,\"sph_name\":\"云游天地旅行家\"," +
      "\"feed_desc\":\"瑞士丨盖尔默缆车，名副其实的过山车~\\n\\n#旅行#风景#热门\"}}";
    WxCpChatModel modelSphFeed = WxCpChatModel.fromJson(sphfeed);
    log.info("数据为：" + modelSphFeed.toJson());


    /*
     * 获取会话内容存档开启成员列表
     */
    List<String> permitUserList = cpService.getMsgAuditService().getPermitUserList(null);
    log.info(permitUserList.toString());


    ArrayList<WxCpCheckAgreeRequest.Info> userList = Lists.newArrayList();
    WxCpCheckAgreeRequest checkAgreeRequest = new WxCpCheckAgreeRequest();
    /*
     * 获取会话同意情况
     */
    WxCpCheckAgreeRequest.Info info = new WxCpCheckAgreeRequest.Info();
    info.setUserid("wangkai");
    info.setExteranalOpenId("wmOQpTDwAAkOscTrtUlSli0YLU2jcpUg");
    if (info != null) {
      userList.add(info);
      checkAgreeRequest.setInfo(userList);
    }

    WxCpAgreeInfo wxCpAgreeInfo = cpService.getMsgAuditService().checkSingleAgree(checkAgreeRequest);
    log.info(wxCpAgreeInfo.toJson());


    /*
     * 获取会话内容存档内部群信息
     */
    WxCpGroupChat room = cpService.getMsgAuditService().getGroupChat("wrOQpTDwAAyPl84GBJ40W5eWxWtixSCA");
    log.info(room.toJson());


    /*
     * 获取access_token
     * https://developer.work.weixin.qq.com/document/path/91039
     * https://www.jianshu.com/p/dde171887d63
     */
    String getUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s";
    String data = cpService.get(String.format(getUrl, wxCpConfigStorage.getCorpId(),
      wxCpConfigStorage.getCorpSecret()), null);


  }

  @Test
  public void testGetMediaFile() throws Exception {
    WxCpMsgAuditService msgAuditService = cpService.getMsgAuditService();
    WxCpChatDatas chatDatas = msgAuditService.getChatDatas(0L, 100L, null, null, 10);
    for (WxCpChatDatas.WxCpChatData chatDatum : chatDatas.getChatData()) {
      WxCpChatModel decryptData = msgAuditService.getDecryptData(chatDatas.getSdk(), chatDatum, 2);
      // 文件后缀
      String suffix = "";
      // 文件名md5
      String md5Sum = "";
      // sdkFileId
      String sdkFileId = "";
      String msgType = decryptData.getMsgType();
      if (msgType == null) msgType = "";
      switch (msgType) {
        case "image":
          suffix = ".jpg";
          md5Sum = decryptData.getImage().getMd5Sum();
          sdkFileId = decryptData.getImage().getSdkFileId();
          break;
        case "voice":
          suffix = ".amr";
          md5Sum = decryptData.getVoice().getMd5Sum();
          sdkFileId = decryptData.getVoice().getSdkFileId();
          break;
        case "video":
          suffix = ".mp4";
          md5Sum = decryptData.getVideo().getMd5Sum();
          sdkFileId = decryptData.getVideo().getSdkFileId();
          break;
        case "emotion":
          md5Sum = decryptData.getEmotion().getMd5Sum();
          sdkFileId = decryptData.getEmotion().getSdkFileId();
          int type = decryptData.getEmotion().getType();
          switch (type) {
            case 1:
              suffix = ".gif";
              break;
            case 2:
              suffix = ".png";
              break;
            default:
              return;
          }
          break;
        case "file":
          md5Sum = decryptData.getFile().getMd5Sum();
          suffix = "." + decryptData.getFile().getFileExt();
          sdkFileId = decryptData.getFile().getSdkFileId();
          break;
        // 音频存档消息
        case "meeting_voice_call":

          md5Sum = decryptData.getVoiceId();
          sdkFileId = decryptData.getMeetingVoiceCall().getSdkFileId();
          for (WxCpChatModel.MeetingVoiceCall.DemoFileData demofiledata :
            decryptData.getMeetingVoiceCall().getDemoFileData()) {
            String demoFileDataFileName = demofiledata.getFileName();
            suffix = demoFileDataFileName.substring(demoFileDataFileName.lastIndexOf(".") + 1);
          }

          break;
        // 音频共享文档消息
        case "voip_doc_share":

          md5Sum = decryptData.getVoipId();
          WxCpFileItem docShare = decryptData.getVoipDocShare();
          String fileName = docShare.getFileName();
          suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
          break;
        default:
          continue;
      }

      /*
       * 拉取媒体文件
       *
       * 传入一个each函数，用于遍历每个分片的数据
       */
      String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
      String targetPath = path + "testfile/" + md5Sum + suffix;
      File file = new File(targetPath);
      if (!file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      } else {
        file.delete();
      }
      cpService.getMsgAuditService().getMediaFile(chatDatas.getSdk(), sdkFileId, null, null, 1000L, data -> {
        try {
          // 大于512k的文件会分片拉取，此处需要使用追加写，避免后面的分片覆盖之前的数据。
          FileOutputStream outputStream = new FileOutputStream(targetPath, true);
          outputStream.write(data);
          outputStream.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
    }
    Finance.DestroySdk(chatDatas.getSdk());
  }

  /**
   * 测试新的安全API方法（推荐使用）
   * 这些方法不需要手动管理SDK生命周期，更加安全
   */
  @Test
  public void testNewSafeApi() throws Exception {
    WxCpMsgAuditService msgAuditService = cpService.getMsgAuditService();
    
    // 测试新的getChatRecords方法 - 不暴露SDK
    List<WxCpChatDatas.WxCpChatData> chatRecords = msgAuditService.getChatRecords(0L, 10L, null, null, 1000L);
    log.info("获取到 {} 条聊天记录", chatRecords.size());
    
    for (WxCpChatDatas.WxCpChatData chatData : chatRecords) {
      // 测试新的getDecryptChatData方法 - 不需要传入SDK
      WxCpChatModel decryptData = msgAuditService.getDecryptChatData(chatData, 2);
      log.info("解密数据：{}", decryptData.toJson());
      
      // 测试新的getChatRecordPlainText方法 - 不需要传入SDK
      String plainText = msgAuditService.getChatRecordPlainText(chatData, 2);
      log.info("明文数据：{}", plainText);
      
      // 如果是媒体消息，测试新的downloadMediaFile方法
      String msgType = decryptData.getMsgType();
      if ("image".equals(msgType) || "voice".equals(msgType) || "video".equals(msgType) || "file".equals(msgType)) {
        String suffix = "";
        String md5Sum = "";
        String sdkFileId = "";
        
        switch (msgType) {
          case "image":
            suffix = ".jpg";
            md5Sum = decryptData.getImage().getMd5Sum();
            sdkFileId = decryptData.getImage().getSdkFileId();
            break;
          case "voice":
            suffix = ".amr";
            md5Sum = decryptData.getVoice().getMd5Sum();
            sdkFileId = decryptData.getVoice().getSdkFileId();
            break;
          case "video":
            suffix = ".mp4";
            md5Sum = decryptData.getVideo().getMd5Sum();
            sdkFileId = decryptData.getVideo().getSdkFileId();
            break;
          case "file":
            md5Sum = decryptData.getFile().getMd5Sum();
            suffix = "." + decryptData.getFile().getFileExt();
            sdkFileId = decryptData.getFile().getSdkFileId();
            break;
          default:
            // 未知消息类型，跳过处理
            continue;
        }
        
        // 测试新的downloadMediaFile方法 - 不需要传入SDK
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String targetPath = path + "testfile-new/" + md5Sum + suffix;
        File file = new File(targetPath);
        
        // 确保父目录存在
        if (!file.getParentFile().exists()) {
          file.getParentFile().mkdirs();
        }
        
        // 删除已存在的文件
        if (file.exists()) {
          file.delete();
        }
        
        // 使用新的API下载媒体文件
        msgAuditService.downloadMediaFile(sdkFileId, null, null, 1000L, targetPath);
        log.info("媒体文件下载成功：{}", targetPath);
      }
    }
    
    // 注意：使用新API无需手动调用 Finance.DestroySdk()，SDK由框架自动管理
  }

  // 测试Uint64类型
  public static void main(String[] args){
    /*
     * 会议邀请信息
     */
    String meeting = "{\"msgid\":\"5935786683775673543_1603877328\",\"action\":\"send\",\"from\":\"ken\"," +
      "\"tolist\":[\"icef\",\"test\"],\"roomid\":\"wr2vOpDgAAN4zVWKbS\",\"msgtime\":1603877328914," +
      "\"msgtype\":\"meeting\",\"meeting\":{\"topic\":\"夕会\",\"starttime\":1603877400,\"endtime\":1603881000," +
      "\"address\":\"\",\"remarks\":\"\",\"meetingtype\":102,\"meetingid\":11101571002822706744,\"status\":1}}";
    WxCpChatModel modelMeeting = WxCpChatModel.fromJson(meeting);
    modelMeeting.getMeeting().getMeetingId();
    System.out.println(modelMeeting.toJson());

    /*
     * 音频共享文档消息
     */
    String voipDocShare = "{\"msgid\":\"16527954622422422847_1594199256\",\"action\":\"send\"," +
      "\"from\":\"18002520162\",\"tolist\":[\"wo137MCgAAYW6pIiKKrDe5SlzEhSgwbA\"],\"msgtime\":1594199235014," +
      "\"msgtype\":\"voip_doc_share\",\"voipid\":\"gr2751c98b19300571f8afb3b74514bd32\"," +
      "\"voip_doc_share\":{\"filename\":\"欢迎使用微盘.pdf.pdf\",\"md5sum\":\"ff893900f24e55e216e617a40e5c4648\"," +
      "\"filesize\":11101571002822706744," +
      "\"sdkfileid" +
      "\":\"CpsBKjAqZUlLdWJMd2gvQ1JxMzd0ZjlpdW5mZzJOOE9JZm5kbndvRmRqdnBETjY0QlcvdGtHSFFTYm95dHM2VlllQXhkUUN5KzRmSy9KT3pudnA2aHhYZFlPemc2aVZ6YktzaVh3YkFPZHlqNnl2L2MvcGlqcVRjRTlhZEZsOGlGdHJpQ2RWSVNVUngrVFpuUmo3TGlPQ1BJemlRPT0SOE5EZGZNVFk0T0RnMU16YzVNVGt5T1RJMk9GODFNelUyTlRBd01qQmZNVFU1TkRFNU9USTFOZz09GiA3YTcwNmQ2Zjc5NjY3MDZjNjY2Zjc4NzI3NTZmN2E2YQ==\"}}";
    WxCpChatModel modelVoipDocShare = WxCpChatModel.fromJson(voipDocShare);
    System.out.println(modelVoipDocShare.toJson());

    /*
     * 填表消息
     */
    String collect = "{\"msgid\":\"2500536226619379797_1576034482\",\"action\":\"send\",\"from\":\"nick\"," +
      "\"tolist\":[\"XuJinSheng\",\"15108264797\"],\"roomid\":\"wrjc7bDwYAOAhf9quEwRRxyyoMm0QAAA\"," +
      "\"msgtime\":1576034482344,\"msgtype\":\"collect\",\"collect\":{\"room_name\":\"这是一个群\",\"creator\":\"nick\"," +
      "\"create_time\":\"2019-12-11 11:21:22\",\"title\":\"这是填表title\",\"details\":[{\"id\":11101571002822706744,\"ques\":\"表项1，文本\"," +
      "\"type\":\"Text\"},{\"id\":2,\"ques\":\"表项2，数字\",\"type\":\"Number\"},{\"id\":3,\"ques\":\"表项3，日期\"," +
      "\"type\":\"Date\"},{\"id\":4,\"ques\":\"表项4，时间\",\"type\":\"Time\"}]}}";
    WxCpChatModel modelCollect = WxCpChatModel.fromJson(collect);
    System.out.println(modelCollect.toJson());

    BigInteger id = modelCollect.getCollect().getDetails().get(0).getId();
    System.out.println(id);

  }

}
