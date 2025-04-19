package me.chanjar.weixin.cp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.XmlUtils;
import me.chanjar.weixin.common.util.xml.IntegerArrayConverter;
import me.chanjar.weixin.common.util.xml.LongArrayConverter;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.util.crypto.WxCpCryptUtil;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import me.chanjar.weixin.cp.util.xml.XStreamTransformer;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 微信推送过来的消息，也是同步回复给用户的消息，xml格式
 * 相关字段的解释看微信开发者文档：
 * https://work.weixin.qq.com/api/doc#12973
 * https://work.weixin.qq.com/api/doc#12974
 * </pre>
 *
 * @author Daniel Qian
 */
@Data
@Slf4j
@XStreamAlias("xml")
public class WxCpXmlMessage implements Serializable {
  private static final long serialVersionUID = -1042994982179476410L;

  /**
   * 使用dom4j解析的存放所有xml属性和值的map.
   */
  private Map<String, Object> allFieldsMap;

  ///////////////////////
  // 以下都是微信推送过来的消息的xml的element所对应的属性
  ///////////////////////

  @XStreamAlias("AgentID")
  private String agentId;

  @XStreamAlias("ToUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String toUserName;

  @XStreamAlias("FromUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String fromUserName;

  @XStreamAlias("CreateTime")
  private Long createTime;

  /**
   * <pre>
   * 当接受用户消息时，可能会获得以下值：
   * {@link WxConsts.XmlMsgType#TEXT}
   * {@link WxConsts.XmlMsgType#IMAGE}
   * {@link WxConsts.XmlMsgType#VOICE}
   * {@link WxConsts.XmlMsgType#VIDEO}
   * {@link WxConsts.XmlMsgType#LOCATION}
   * {@link WxConsts.XmlMsgType#LINK}
   * {@link WxConsts.XmlMsgType#EVENT}
   * 当发送消息的时候使用：
   * {@link WxConsts.XmlMsgType#TEXT}
   * {@link WxConsts.XmlMsgType#IMAGE}
   * {@link WxConsts.XmlMsgType#VOICE}
   * {@link WxConsts.XmlMsgType#VIDEO}
   * {@link WxConsts.XmlMsgType#NEWS}
   * </pre>
   */
  @XStreamAlias("MsgType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String msgType;

  @XStreamAlias("Content")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String content;

  @XStreamAlias("MsgId")
  private Long msgId;

  @XStreamAlias("PicUrl")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String picUrl;

  @XStreamAlias("MediaId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String mediaId;

  @XStreamAlias("Format")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String format;

  @XStreamAlias("ThumbMediaId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String thumbMediaId;

  @XStreamAlias("Location_X")
  private Double locationX;

  @XStreamAlias("Location_Y")
  private Double locationY;

  @XStreamAlias("Scale")
  private Double scale;

  @XStreamAlias("Label")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String label;

  @XStreamAlias("Title")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String title;

  @XStreamAlias("Description")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String description;

  @XStreamAlias("Url")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String url;

  @XStreamAlias("Event")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String event;

  @XStreamAlias("UpdateDetail")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String updateDetail;

  @XStreamAlias("JoinScene")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String joinScene;

  @XStreamAlias("QuitScene")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String quitScene;

  @XStreamAlias("MemChangeCnt")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String memChangeCnt;

  @XStreamAlias("Source")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String source;

  @XStreamAlias("StrategyId")
  private String strategyId;

  @XStreamAlias("EventKey")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String eventKey;

  @XStreamAlias("Ticket")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String ticket;

  @XStreamAlias("Latitude")
  private Double latitude;

  @XStreamAlias("Longitude")
  private Double longitude;

  @XStreamAlias("Precision")
  private Double precision;

  @XStreamAlias("Recognition")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String recognition;

  @XStreamAlias("TaskId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String taskId;

  @XStreamAlias("CardType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String cardType;

  @XStreamAlias("ResponseCode")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String responseCode;

  @XStreamAlias("SelectedItems")
  private List<SelectedItem> selectedItems;

  /**
   * 微信客服
   * 调用拉取消息接口时，需要传此token，用于校验请求的合法性
   */
  @XStreamAlias("Token")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String token;

  /**
   * 有新消息的客服账号。可通过sync_msg接口指定open_kfid获取此客服账号的消息
   */
  @XStreamAlias("OpenKfId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String openKfId;

  /**
   * 新增授权的客服账号列表，多个AuthAddOpenKfId节点表示多个新增账号
   */
  @XStreamAlias("AuthAddOpenKfId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String authAddOpenKfId;

  /**
   * 取消授权的客服账号列表，多个AuthDelOpenKfId节点表示多个取消账号
   */
  @XStreamAlias("AuthDelOpenKfId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String authDelOpenKfId;

  /**
   * 失效的获客链接ID
   */
  @XStreamAlias("LinkId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String linkId;

  /**
   * 通讯录变更事件.
   * 请参考常量 me.chanjar.weixin.cp.constant.WxCpConsts.ContactChangeType
   */
  @XStreamAlias("ChangeType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String changeType;

  /**
   * 变更信息的成员UserID.
   */
  @XStreamAlias("UserID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String userId;

  /**
   * 变更信息的外部联系人的userid，注意不是企业成员的帐号.
   */
  @XStreamAlias("ExternalUserID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String externalUserId;

  /**
   * 添加此用户的「联系我」方式配置的state参数，可用于识别添加此用户的渠道.
   */
  @XStreamAlias("State")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String state;

  /**
   * 欢迎语code，可用于发送欢迎语.
   */
  @XStreamAlias("WelcomeCode")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String welcomeCode;

  /**
   * 新的UserID，变更时推送（userid由系统生成时可更改一次）.
   */
  @XStreamAlias("NewUserID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String newUserId;

  /**
   * 成员名称.
   * 或者部门名称
   */
  @XStreamAlias("Name")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String name;

  /**
   * 成员部门列表，变更时推送，仅返回该应用有查看权限的部门id.
   */
  @XStreamAlias("Department")
  @XStreamConverter(value = LongArrayConverter.class)
  private Long[] departments;

  /**
   * 主部门
   */
  @XStreamAlias("MainDepartment")
  private Long mainDepartment;

  /**
   * 手机号码.
   */
  @XStreamAlias("Mobile")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String mobile;

  /**
   * 职位信息。长度为0~64个字节.
   */
  @XStreamAlias("Position")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String position;

  /**
   * 群ID.
   */
  @XStreamAlias("ChatId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String chatId;

  /**
   * 性别，1表示男性，2表示女性.
   */
  @XStreamAlias("Gender")
  private Integer gender;

  /**
   * 邮箱.
   */
  @XStreamAlias("Email")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String email;

  /**
   * 企业邮箱;代开发自建应用不返回该字段。
   * ISSUE#2584
   *
   * @see <a href="https://developer.work.weixin.qq.com/document/path/90970">Link</a>
   */
  @XStreamAlias("BizMail")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String bizMail;

  /**
   * 头像url。注：如果要获取小图将url最后的”/0”改成”/100”即可.
   */
  @XStreamAlias("Avatar")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String avatar;

  /**
   * 英文名.
   */
  @XStreamAlias("EnglishName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String englishName;

  /**
   * 上级字段，标识是否为上级。0表示普通成员，1表示上级.
   */
  @XStreamAlias("IsLeader")
  private Integer isLeader;

  /**
   * 表示所在部门是否为上级，0-否，1-是，顺序与Department字段的部门逐一对应.
   */
  @XStreamAlias("IsLeaderInDept")
  @XStreamConverter(value = IntegerArrayConverter.class)
  private Integer[] isLeaderInDept;

  /**
   * 座机.
   */
  @XStreamAlias("Telephone")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String telephone;

  /**
   * 地址.
   */
  @XStreamAlias("Address")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String address;

  /**
   * 日程ID.
   */
  @XStreamAlias("ScheduleId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String scheduleId;

  /**
   * 日历ID.
   */
  @XStreamAlias("CalId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String calId;

  /**
   * 会议室ID.
   */
  @XStreamAlias("MeetingRoomId")
  private String meetingRoomId;

  /**
   * 会议室预定id，可根据该ID查询具体的会议预定情况
   */
  @XStreamAlias("BookingId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String bookingId;

  /**
   * 扩展属性.
   */
  @XStreamAlias("ExtAttr")
  private ExtAttr extAttrs = new ExtAttr();

  /**
   * 部门Id.
   * 或者客户联系回调标签/标签组id
   */
  @XStreamAlias("Id")
  @XStreamConverter(XStreamCDataConverter.class)
  private String id;

  /**
   * 父部门id.
   */
  @XStreamAlias("ParentId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String parentId;

  /**
   * 部门排序.
   */
  @XStreamAlias("Order")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String order;

  /**
   * 标签Id.
   */
  @XStreamAlias("TagId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String tagId;

  /**
   * 标签中新增的成员userid列表，用逗号分隔.
   */
  @XStreamAlias("AddUserItems")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String addUserItems;

  /**
   * 标签中删除的成员userid列表，用逗号分隔.
   */
  @XStreamAlias("DelUserItems")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String delUserItems;

  /**
   * 标签中新增的部门id列表，用逗号分隔.
   */
  @XStreamAlias("AddPartyItems")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String addPartyItems;

  /**
   * 标签中删除的部门id列表，用逗号分隔.
   */
  @XStreamAlias("DelPartyItems")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String delPartyItems;

  /**
   * 客户联系回调: 客户接替失败的原因
   */
  @XStreamAlias("FailReason")
  @XStreamConverter(XStreamCDataConverter.class)
  private String failReason;

  /**
   * 客户联系回调: 标签类型
   */
  @XStreamAlias("TagType")
  @XStreamConverter(XStreamCDataConverter.class)
  private String tagType;


  ///////////////////////////////////////
  // 群发消息返回的结果
  ///////////////////////////////////////
  /**
   * 多个时间共用字段.
   * 1. 群发的结果.
   * 2. 通讯录变更事件
   * 激活状态：1=已激活 2=已禁用 4=未激活 已激活代表已激活企业微信或已关注微工作台（原企业号）.
   * 3. 直播回调事件
   * 直播状态 ，0：预约中，1：直播中，2：已结束，4：已取消 （已过期状态目前没有回调）
   */
  @XStreamAlias("Status")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String status;

  /**
   * 直播ID
   */
  @XStreamAlias("LivingId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String livingId;

  /**
   * group_id下粉丝数；或者openid_list中的粉丝数.
   */
  @XStreamAlias("TotalCount")
  private Integer totalCount;

  /**
   * 过滤.
   * （过滤是指特定地区、性别的过滤、用户设置拒收的过滤，用户接收已超4条的过滤）后，准备发送的粉丝数，原则上，filterCount = sentCount + errorCount
   */
  @XStreamAlias("FilterCount")
  private Integer filterCount;

  /**
   * 发送成功的粉丝数.
   */
  @XStreamAlias("SentCount")
  private Integer sentCount;

  /**
   * 发送失败的粉丝数.
   */
  @XStreamAlias("ErrorCount")
  private Integer errorCount;

  @XStreamAlias("ScanCodeInfo")
  private ScanCodeInfo scanCodeInfo = new ScanCodeInfo();

  @XStreamAlias("SendPicsInfo")
  private SendPicsInfo sendPicsInfo = new SendPicsInfo();

  @XStreamAlias("SendLocationInfo")
  private SendLocationInfo sendLocationInfo = new SendLocationInfo();


  /**
   * 审批消息
   * <p>
   * 审批申请状态变化回调通知
   * https://developer.work.weixin.qq.com/document/path/91815
   * <p>
   * 自建应用审批状态变化通知回调
   * https://developer.work.weixin.qq.com/document/path/90269
   */
  @XStreamAlias("ApprovalInfo")
  private WxCpXmlApprovalInfo approvalInfo = new WxCpXmlApprovalInfo();


  /**
   * From xml wx cp xml message.
   *
   * @param xml the xml
   * @return the wx cp xml message
   */
  protected static WxCpXmlMessage fromXml(String xml) {
    //修改微信变态的消息内容格式，方便解析
    xml = xml.replace("</PicList><PicList>", "");
    final WxCpXmlMessage xmlMessage = XStreamTransformer.fromXml(WxCpXmlMessage.class, xml);
    xmlMessage.setAllFieldsMap(XmlUtils.xml2Map(xml));
    return xmlMessage;
  }

  /**
   * From xml wx cp xml message.
   *
   * @param xml     the xml
   * @param agentId the agent id
   * @return the wx cp xml message
   */
  public static WxCpXmlMessage fromXml(String xml, String agentId) {
    //修改微信变态的消息内容格式，方便解析
    xml = xml.replace("</PicList><PicList>", "");
    final WxCpXmlMessage xmlMessage = fromXml(xml);
    xmlMessage.setAgentId(agentId);
    return xmlMessage;
  }

  /**
   * From xml wx cp xml message.
   *
   * @param is the is
   * @return the wx cp xml message
   */
  protected static WxCpXmlMessage fromXml(InputStream is) {
    return XStreamTransformer.fromXml(WxCpXmlMessage.class, is);
  }

  /**
   * 从加密字符串转换.
   *
   * @param encryptedXml      the encrypted xml
   * @param wxCpConfigStorage the wx cp config storage
   * @param timestamp         the timestamp
   * @param nonce             the nonce
   * @param msgSignature      the msg signature
   * @return the wx cp xml message
   */
  public static WxCpXmlMessage fromEncryptedXml(String encryptedXml, WxCpConfigStorage wxCpConfigStorage,
                                                String timestamp, String nonce, String msgSignature) {
    WxCpCryptUtil cryptUtil = new WxCpCryptUtil(wxCpConfigStorage);
    WxCpXmlMessage wxCpXmlMessage = fromXml(encryptedXml);
    String plainText = cryptUtil.decrypt(msgSignature, timestamp, nonce, encryptedXml);
    log.debug("解密后的原始xml消息内容：{}", plainText);
    if (StringUtils.isNotEmpty(wxCpXmlMessage.getAgentId())) {
      return fromXml(plainText, wxCpXmlMessage.getAgentId());
    } else {
      return fromXml(plainText);
    }

  }

  /**
   * From encrypted xml wx cp xml message.
   *
   * @param is                the is
   * @param wxCpConfigStorage the wx cp config storage
   * @param timestamp         the timestamp
   * @param nonce             the nonce
   * @param msgSignature      the msg signature
   * @return the wx cp xml message
   */
  public static WxCpXmlMessage fromEncryptedXml(InputStream is, WxCpConfigStorage wxCpConfigStorage,
                                                String timestamp, String nonce, String msgSignature) {
    try {
      return fromEncryptedXml(IOUtils.toString(is, StandardCharsets.UTF_8), wxCpConfigStorage, timestamp, nonce,
        msgSignature);
    } catch (IOException e) {
      throw new WxRuntimeException(e);
    }
  }

  @Override
  public String toString() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * The type Scan code info.
   */
  @Data
  @XStreamAlias("ScanCodeInfo")
  public static class ScanCodeInfo implements Serializable {
    private static final long serialVersionUID = 7420078330239763395L;

    /**
     * 扫描类型，一般是qrcode.
     */
    @XStreamAlias("ScanType")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String scanType;

    /**
     * 扫描结果，即二维码对应的字符串信息.
     */
    @XStreamAlias("ScanResult")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String scanResult;
  }

  /**
   * The type Ext attr.
   */
  @Data
  public static class ExtAttr implements Serializable {
    private static final long serialVersionUID = -3418685294606228837L;

    /**
     * The Items.
     */
    @XStreamImplicit(itemFieldName = "Item")
    protected final List<Item> items = new ArrayList<>();

    /**
     * The type Item.
     */
    @XStreamAlias("Item")
    @Data
    public static class Item implements Serializable {
      private static final long serialVersionUID = -3418685294606228837L;

      @XStreamAlias("Name")
      @XStreamConverter(value = XStreamCDataConverter.class)
      private String name;

      @XStreamAlias("Value")
      @XStreamConverter(value = XStreamCDataConverter.class)
      private String value;
    }
  }

  /**
   * The type Send pics info.
   */
  @Data
  @XStreamAlias("SendPicsInfo")
  public static class SendPicsInfo implements Serializable {
    private static final long serialVersionUID = -6549728838848064881L;

    /**
     * The Pic list.
     */
    @XStreamAlias("PicList")
    protected final List<Item> picList = new ArrayList<>();

    @XStreamAlias("Count")
    private Long count;

    /**
     * The type Item.
     */
    @XStreamAlias("item")
    @Data
    public static class Item implements Serializable {
      private static final long serialVersionUID = -6549728838848064881L;

      @XStreamAlias("PicMd5Sum")
      @XStreamConverter(value = XStreamCDataConverter.class)
      private String picMd5Sum;
    }
  }

  /**
   * The type Send location info.
   */
  @Data
  @XStreamAlias("SendLocationInfo")
  public static class SendLocationInfo implements Serializable {
    private static final long serialVersionUID = 6319921071637597406L;

    @XStreamAlias("Location_X")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String locationX;

    @XStreamAlias("Location_Y")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String locationY;

    @XStreamAlias("Scale")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String scale;

    @XStreamAlias("Label")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String label;

    @XStreamAlias("Poiname")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String poiName;

  }


  /**
   * The type selected Items.
   */
  @Data
  @XStreamAlias("SelectedItem")
  public static class SelectedItem implements Serializable {
    private static final long serialVersionUID = 6319921121637597406L;

    @XStreamAlias("QuestionKey")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String questionKey;

    @XStreamAlias(value = "OptionIds")
    private List<String> optionIds;
  }

}
