package me.chanjar.weixin.open.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.util.WxOpenCryptUtil;
import me.chanjar.weixin.open.util.xml.XStreamTransformer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Data
@Slf4j
@XStreamAlias("xml")
public class WxOpenXmlMessage implements Serializable {
  private static final long serialVersionUID = -5641769554709507771L;

  /**
   * 第三方平台的APPID
   */
  @XStreamAlias("AppId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String appId;

  @XStreamAlias("CreateTime")
  private Long createTime;

  @XStreamAlias("InfoType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String infoType;

  @XStreamAlias("ComponentVerifyTicket")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String componentVerifyTicket;

  @XStreamAlias("AuthorizerAppid")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String authorizerAppid;

  @XStreamAlias("AuthorizationCode")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String authorizationCode;

  @XStreamAlias("AuthorizationCodeExpiredTime")
  private Long authorizationCodeExpiredTime;

  @XStreamAlias("PreAuthCode")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String preAuthCode;

  /**
   * 子平台APPID(公众号/小程序的APPID) 快速创建小程序、小程序认证中
   */
  @XStreamAlias("appid")
  private String subAppId;

  // 以下为快速创建小程序接口推送的的信息

  @XStreamAlias("status")
  private int status;

  @XStreamAlias("auth_code")
  private String authCode;

  @XStreamAlias("msg")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String msg;

  @XStreamAlias("info")
  private Info info = new Info();

  // 以下为小程序认证（年审）申请审核流程 推送的消息 infoType=notify_3rd_wxa_auth
  /**
   * 任务ID
   */
  @XStreamAlias("taskid")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String taskId;

  /**
   * 认证任务状态 0初始 1超24小时 2用户拒绝 3用户同意 4发起人脸 5人脸失败 6人脸ok 7人脸认证后手机验证码 8手机验证失败 9手机验证成功 11创建审核单失败 12创建审核单成功 14验证失败 15等待支付
   */
  @XStreamAlias("task_status")
  private Integer taskStatus;

  /**
   * 审核单状态，创建审核单成功后有效 0审核单不存在 1待支付 2审核中 3打回重填 4认证通过 5认证最终失败（不能再修改）
   */
  @XStreamAlias("apply_status")
  private Integer applyStatus;

  /**
   * 审核消息或失败原因
   */
  @XStreamAlias("message")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String message;


  /**
   * 审核提供商分配信息
   */
  @XStreamAlias("dispatch_info")
  private DispatchInfo dispatchInfo;


  // 以下为小程序认证（年审）即将到期通知(过期当天&过期30天&过期60) infoType=notify_3rd_wxa_wxverify，并会附带message
  /**
   * 过期时间戳（秒数）
   */
  @XStreamAlias("expired")
  private Long expired;

  //region 以下为小程序管理员人脸核身完成事件 推送的消息 infoType=notify_icpfiling_verify_result

  /**
   * 人脸核验任务id
   */
  @XStreamAlias("task_id")
  private String IcpVerifyTaskId;
  /**
   * 小程序唯一id
   */
  @XStreamAlias("verify_appid")
  private String verifyAppId;
  /**
   * 人脸核验结果： 2-核验失败；3-核验成功
   */
  @XStreamAlias("result")
  private Integer result;
  /**
   * 发起时 along_with_auth 填 true 时有效：9. 认证短信核验通过。
   */
  @XStreamAlias("along_with_auth_result")
  private Integer alongWithAuthResult;
  //endregion

  //region 当备案审核被驳回或通过时会推送该事件 推送的消息 infoType=notify_apply_icpfiling_result
  /**
   * 小程序唯一id
   */
  @XStreamAlias("authorizer_appid")
  private String beianAuthorizerAppId;
  /**
   * 备案状态，参考“获取小程序备案状态及驳回原因”接口的备案状态枚举¬
   */
  @XStreamAlias("beian_status")
  private Integer beianStatus;
  //endregion

  //region 认证及备案流程的主要节点均有事件推送到第三方平台的授权事件接收接口，包括支付完成、派单给审核机构、审核打回、审核通过、审核失败等。消息类型，固定为 notify_3rd_wxa_auth_and_icp

  /**
   * 小程序认证及备案任务流程 id
   */
  @XStreamAlias("procedure_id")
  private String procedureId;
  /**
   * 当前任务流程状态，见“小程序认证及备案进度查询” API 文档中的任务流程状态枚举
   */
  @XStreamAlias("procedure_status")
  private Integer procedureStatus;
  //endregion

  /**
   * 快速创建的小程序appId，已弃用，未来将删除
   *
   * @see #getSubAppId() 应使用此方法
   */
  @Deprecated
  public String getRegistAppId() {
    return subAppId;
  }

  /**
   * 快速创建的小程序appId，已弃用，未来将删除
   *
   * @see #setSubAppId(String) 应使用此方法
   */
  @Deprecated
  public void setRegistAppId(String value) {
    subAppId = value;
  }


  @XStreamAlias("info")
  @Data
  public static class Info implements Serializable {
    private static final long serialVersionUID = 7706235740094081194L;

    @XStreamAlias("name")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String name;

    @XStreamAlias("code")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String code;

    @XStreamAlias("code_type")
    private int codeType;

    @XStreamAlias("legal_persona_wechat")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String legalPersonaWechat;

    @XStreamAlias("legal_persona_name")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String legalPersonaName;

    @XStreamAlias("component_phone")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String componentPhone;

    // 创建个人小程序审核通知数据
    @XStreamAlias("wxuser")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String wxuser;

    @XStreamAlias("idname")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String idname;

    // 创建试用小程序成功/失败的通知数据
    @XStreamAlias("unique_id")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String uniqueId;

  }

  /**
   * 审核提供商分配信息
   */
  @Data
  public static class DispatchInfo {

    /**
     * 提供商，如：上海倍通企业信用征信有限公司
     */
    @XStreamConverter(value = XStreamCDataConverter.class)
    @XStreamAlias("provider")
    private String provider;

    /**
     * 联系方式，如：咨询电话：0411-84947888，咨询时间：周一至周五（工作日）8：30-17：30
     */
    @XStreamConverter(value = XStreamCDataConverter.class)
    @XStreamAlias("contact")
    private String contact;

    /**
     * 派遣时间戳(秒)，如：1704952913
     */
    @XStreamAlias("dispatch_time")
    private Long dispatchTime;
  }

  public static String wxMpOutXmlMessageToEncryptedXml(WxMpXmlOutMessage message, WxOpenConfigStorage wxOpenConfigStorage) {
    String plainXml = message.toXml();
    WxOpenCryptUtil pc = new WxOpenCryptUtil(wxOpenConfigStorage);
    return pc.encrypt(plainXml);
  }

  public static WxOpenXmlMessage fromXml(String xml) {
    //修改微信变态的消息内容格式，方便解析
    xml = xml.replace("</PicList><PicList>", "");
    return XStreamTransformer.fromXml(WxOpenXmlMessage.class, xml);
  }

  public static WxOpenXmlMessage fromXml(InputStream is) {
    return XStreamTransformer.fromXml(WxOpenXmlMessage.class, is);
  }

  /**
   * 从加密字符串转换
   *
   * @param encryptedXml        密文
   * @param wxOpenConfigStorage 配置存储器对象
   * @param timestamp           时间戳
   * @param nonce               随机串
   * @param msgSignature        签名串
   */
  public static WxOpenXmlMessage fromEncryptedXml(String encryptedXml, WxOpenConfigStorage wxOpenConfigStorage,
                                                  String timestamp, String nonce, String msgSignature) {
    WxOpenCryptUtil cryptUtil = new WxOpenCryptUtil(wxOpenConfigStorage);
    String plainText = cryptUtil.decryptXml(msgSignature, timestamp, nonce, encryptedXml);
    log.debug("解密后的原始xml消息内容：{}", plainText);
    return fromXml(plainText);
  }

  public static WxMpXmlMessage fromEncryptedMpXml(String encryptedXml, WxOpenConfigStorage wxOpenConfigStorage,
                                                  String timestamp, String nonce, String msgSignature) {
    WxOpenCryptUtil cryptUtil = new WxOpenCryptUtil(wxOpenConfigStorage);
    String plainText = cryptUtil.decryptXml(msgSignature, timestamp, nonce, encryptedXml);
    return WxMpXmlMessage.fromXml(plainText);
  }

  public static WxOpenXmlMessage fromEncryptedXml(InputStream is, WxOpenConfigStorage wxOpenConfigStorage,
                                                  String timestamp, String nonce, String msgSignature) {
    try {
      return fromEncryptedXml(IOUtils.toString(is, StandardCharsets.UTF_8),
        wxOpenConfigStorage, timestamp, nonce, msgSignature);
    } catch (IOException e) {
      throw new WxRuntimeException(e);
    }
  }
}
