package me.chanjar.weixin.cp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.basic.IntConverter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.util.XmlUtils;
import me.chanjar.weixin.common.util.xml.IntegerArrayConverter;
import me.chanjar.weixin.common.util.xml.StringArrayConverter;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.util.crypto.WxCpTpCryptUtil;
import me.chanjar.weixin.cp.util.xml.XStreamTransformer;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * sxh 修改版本，有些参数类型错误，修正版
 * 回调推送的message
 * https://work.weixin.qq.com/api/doc#90001/90143/90612
 *
 * @author zhenjun cai
 */
@XStreamAlias("xml")
@Slf4j
@Data
public class WxCpTpXmlMessage implements Serializable {
  private static final long serialVersionUID = 6031833682211475786L;

  /**
   * 使用dom4j解析的存放所有xml属性和值的map.
   */
  private Map<String, Object> allFieldsMap;

  /**
   * The Suite id.
   */
  @XStreamAlias("SuiteId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String suiteId;

  /**
   * The Info type.
   */
  @XStreamAlias("InfoType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String infoType;

  /**
   * The Time stamp.
   */
  @XStreamAlias("TimeStamp")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String timeStamp;

  /**
   * The Suite ticket.
   */
  @XStreamAlias("SuiteTicket")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String suiteTicket;

  /**
   * The Auth code.
   */
  @XStreamAlias("AuthCode")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String authCode;

  /**
   * The Auth corp id.
   */
  @XStreamAlias("AuthCorpId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String authCorpId;

  /**
   * The Change type.
   */
  @XStreamAlias("ChangeType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String changeType;

  /**
   * The User id.
   */
  @XStreamAlias("UserID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String userID;

  /**
   * The Department.
   */
  @XStreamAlias("Department")
  @XStreamConverter(value = IntegerArrayConverter.class)
  protected Integer[] department;

  /**
   * The Main department.
   */
  @XStreamAlias("MainDepartment")
  @XStreamConverter(value = IntConverter.class)
  protected Integer mainDepartment;

  /**
   * The Is leader in dept.
   */
  @XStreamAlias("IsLeaderInDept")
  @XStreamConverter(value = IntegerArrayConverter.class)
  protected Integer[] isLeaderInDept;

  /**
   * The Mobile.
   */
  @XStreamAlias("Mobile")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String mobile;

  /**
   * The Position.
   */
  @XStreamAlias("Position")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String position;

  /**
   * The Gender.
   */
  @XStreamAlias("Gender")
  @XStreamConverter(value = IntConverter.class)
  protected Integer gender;

  /**
   * The Email.
   */
  @XStreamAlias("Email")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String email;

  /**
   * The Status.
   */
  @XStreamAlias("Status")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String status;

  /**
   * The Avatar.
   */
  @XStreamAlias("Avatar")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String avatar;

  /**
   * The Alias.
   */
  @XStreamAlias("Alias")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String alias;

  /**
   * The Telephone.
   */
  @XStreamAlias("Telephone")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String telephone;

  /**
   * The Id.
   */
  @XStreamAlias("Id")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String id;

  /**
   * The Name.
   */
  @XStreamAlias("Name")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String name;

  /**
   * The Parent id.
   */
  @XStreamAlias("ParentId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String parentId;

  /**
   * The Order.
   */
  @XStreamAlias("Order")
  @XStreamConverter(value = IntConverter.class)
  protected Integer order;

  /**
   * The Tag id.
   */
  @XStreamAlias("TagId")
  @XStreamConverter(value = IntConverter.class)
  protected Integer tagId;

  /**
   * The Add user items.
   */
  @XStreamAlias("AddUserItems")
  @XStreamConverter(value = StringArrayConverter.class)
  protected String[] addUserItems;

  /**
   * The Del user items.
   */
  @XStreamAlias("DelUserItems")
  @XStreamConverter(value = StringArrayConverter.class)
  protected String[] delUserItems;

  /**
   * The Add party items.
   */
  @XStreamAlias("AddPartyItems")
  @XStreamConverter(value = IntegerArrayConverter.class)
  protected Integer[] addPartyItems;

  /**
   * The Del party items.
   */
  @XStreamAlias("DelPartyItems")
  @XStreamConverter(value = IntegerArrayConverter.class)
  protected Integer[] delPartyItems;

  /**
   * The Service corp id.
   */
//ref: https://work.weixin.qq.com/api/doc/90001/90143/90585
  @XStreamAlias("ServiceCorpId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String serviceCorpId;

  /**
   * The Register code.
   */
  @XStreamAlias("RegisterCode")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String registerCode;

  /**
   * The Contact sync.
   */
  @XStreamAlias("ContactSync")
  protected ContactSync contactSync;

  /**
   * The Auth user info.
   */
  @XStreamAlias("AuthUserInfo")
  protected AuthUserInfo authUserInfo;

  /**
   * The Template id.
   */
  @XStreamAlias("TemplateId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String templateId;

  /**
   * The Create time.
   */
  @XStreamAlias("CreateTime")
  protected Long createTime;

  /**
   * The To user name.
   */
  @XStreamAlias("ToUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String toUserName;

  /**
   * The From user name.
   */
  @XStreamAlias("FromUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String fromUserName;

  /**
   * The Msg type.
   */
  @XStreamAlias("MsgType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String msgType;

  /**
   * The Event.
   */
  @XStreamAlias("Event")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String event;

  /**
   * The Batch job.
   */
  @XStreamAlias("BatchJob")
  protected BatchJob batchJob;

  /**
   * The External user id.
   */
  @XStreamAlias("ExternalUserID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String externalUserID;

  /**
   * The State.
   */
  @XStreamAlias("State")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String state;

  /**
   * The Source.
   */
  @XStreamAlias("Source")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String source;

  /**
   * The Fail reason.
   */
  @XStreamAlias("FailReason")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String failReason;

  /**
   * The Chat id.
   */
  @XStreamAlias("ChatId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String chatId;

  /**
   * The Update detail.
   */
  @XStreamAlias("UpdateDetail")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String updateDetail;

  /**
   * The Join scene.
   */
  @XStreamAlias("JoinScene")
  protected Integer joinScene;

  /**
   * The Quit scene.
   */
  @XStreamAlias("QuitScene")
  protected Integer quitScene;

  /**
   * The Mem change cnt.
   */
  @XStreamAlias("MemChangeCnt")
  protected Integer memChangeCnt;

  /**
   * The Tag type.
   */
  @XStreamAlias("TagType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String tagType;

  /**
   * The Welcome code.
   */
  @XStreamAlias("WelcomeCode")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String welcomeCode;

  /**
   * The From user.
   */
  @XStreamAlias("FromUser")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String fromUser;

  /**
   * The Content.
   */
  @XStreamAlias("Content")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String content;

  /**
   * The Msg id.
   */
  @XStreamAlias("MsgId")
  protected String msgId;

  /**
   * The Agent id.
   */
  @XStreamAlias("AgentID")
  protected Integer agentID;

  /**
   * The Pic url.
   */
  @XStreamAlias("PicUrl")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String picUrl;

  /**
   * The Media id.
   */
  @XStreamAlias("MediaId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String mediaId;

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

  @XStreamAlias("EventKey")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String eventKey;

  @XStreamAlias("Latitude")
  private Double latitude;

  @XStreamAlias("Longitude")
  private Double longitude;

  @XStreamAlias("Precision")
  private Double precision;

  @XStreamAlias("AppType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String appType;

  @XStreamAlias("ScanCodeInfo")
  private WxCpXmlMessage.ScanCodeInfo scanCodeInfo = new WxCpXmlMessage.ScanCodeInfo();

  @XStreamAlias("SendPicsInfo")
  private WxCpXmlMessage.SendPicsInfo sendPicsInfo = new WxCpXmlMessage.SendPicsInfo();

  @XStreamAlias("SendLocationInfo")
  private WxCpXmlMessage.SendLocationInfo sendLocationInfo = new WxCpXmlMessage.SendLocationInfo();

  @XStreamAlias("ApprovalInfo")
  private WxCpXmlApprovalInfo approvalInfo = new WxCpXmlApprovalInfo();

  @XStreamAlias("TaskId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String taskId;

  @XStreamAlias("PaidCorpId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String paidCorpId;

  @XStreamAlias("OrderId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String orderId;

  @XStreamAlias("OperatorId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String operatorId;

  @XStreamAlias("OldOrderId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String oldOrderId;

  @XStreamAlias("NewOrderId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String newOrderId;

  /**
   * The type Contact sync.
   */
  @Data
  @XStreamAlias("ContactSync")
  public static class ContactSync implements Serializable {
    private static final long serialVersionUID = 6031833682211475786L;

    /**
     * The Access token.
     */
    @XStreamAlias("AccessToken")
    @XStreamConverter(value = XStreamCDataConverter.class)
    protected String accessToken;

    /**
     * The Expires in.
     */
    @XStreamAlias("ExpiresIn")
    protected Integer expiresIn;
  }

  /**
   * The type Auth user info.
   */
  @Data
  @XStreamAlias("AuthUserInfo")
  public static class AuthUserInfo implements Serializable {
    /**
     * The User id.
     */
    @XStreamAlias("UserId")
    @XStreamConverter(value = XStreamCDataConverter.class)
    protected String userId;
  }

  /**
   * The type Batch job.
   */
  @Data
  @XStreamAlias("BatchJob")
  public static class BatchJob implements Serializable {
    private static final long serialVersionUID = 6031833682211475786L;

    /**
     * The Job id.
     */
    @XStreamAlias("JobId")
    @XStreamConverter(value = XStreamCDataConverter.class)
    protected String JobId;

    /**
     * The Job type.
     */
    @XStreamAlias("JobType")
    @XStreamConverter(value = XStreamCDataConverter.class)
    protected String jobType;

    /**
     * The Err code.
     */
    @XStreamAlias("ErrCode")
    @XStreamConverter(value = IntConverter.class)
    protected Integer errCode;

    /**
     * The Err msg.
     */
    @XStreamAlias("ErrMsg")
    @XStreamConverter(value = XStreamCDataConverter.class)
    protected String errMsg;
  }

  /**
   * The type Approval info.
   * @deprecated 无法同时适配不同回调下的实体字段，使用WxCpXmlApprovalInfo可完美适配
   */
  @Data
  @XStreamAlias("ApprovalInfo")
  public static class ApprovalInfo implements Serializable {
    private static final long serialVersionUID = 6031833682211475786L;

    /**
     * The Third no.
     */
    @XStreamAlias("ThirdNo")
    protected String thirdNo;

    /**
     * The Open sp name.
     */
    @XStreamAlias("OpenSpName")
    protected String openSpName;

    /**
     * The Open template id.
     */
    @XStreamAlias("OpenTemplateId")
    protected String openTemplateId;

    /**
     * The Open sp status.
     */
    @XStreamAlias("OpenSpStatus")
    protected Integer openSpStatus;

    /**
     * The Apply time.
     */
    @XStreamAlias("ApplyTime")
    protected Long applyTime;

    /**
     * The Apply user name.
     */
    @XStreamAlias("ApplyUserName")
    protected String applyUserName;

    /**
     * The Apply user id.
     */
    @XStreamAlias("ApplyUserId")
    protected String applyUserId;

    /**
     * The Apply user party.
     */
    @XStreamAlias("ApplyUserParty")
    protected String applyUserParty;

    /**
     * The Apply user image.
     */
    @XStreamAlias("ApplyUserImage")
    protected String applyUserImage;

    /**
     * The Approval nodes.
     */
    @XStreamAlias("ApprovalNodes")
    protected List<ApprovalNode> approvalNodes;

    /**
     * The Notify nodes.
     */
    @XStreamAlias("NotifyNodes")
    protected List<NotifyNode> notifyNodes;

    /**
     * The Approverstep.
     */
    @XStreamAlias("approverstep")
    protected Integer approverstep;

    /**
     * The type Approval node.
     */
//自建/第三方应用调用审批流程引擎，状态通知
    //ref: https://work.weixin.qq.com/api/doc/90001/90143/90376#审批状态通知事件
    //1.自建/第三方应用调用审批流程引擎发起申请之后，审批状态发生变化时
    //2.自建/第三方应用调用审批流程引擎发起申请之后，在“审批中”状态，有任意审批人进行审批操作时
    @Data
    @XStreamAlias("ApprovalNode")
    public static class ApprovalNode implements Serializable {
      private static final long serialVersionUID = 6031833682211475786L;

      /**
       * The Node status.
       */
      @XStreamAlias("NodeStatus")
      protected Integer nodeStatus;

      /**
       * The Node attr.
       */
      @XStreamAlias("NodeAttr")
      protected Integer nodeAttr;

      /**
       * The Node type.
       */
      @XStreamAlias("NodeType")
      protected Integer nodeType;

      /**
       * The Items.
       */
      @XStreamAlias("Items")
      protected List<Item> items;

      /**
       * The type Item.
       */
      @Data
      @XStreamAlias("Item")
      public static class Item implements Serializable {
        private static final long serialVersionUID = 6031833682211475786L;

        /**
         * The Item name.
         */
        @XStreamAlias("ItemName")
        protected String itemName;
        /**
         * The Item user id.
         */
        @XStreamAlias("ItemUserId")
        protected String itemUserId;
        /**
         * The Item image.
         */
        @XStreamAlias("ItemImage")
        protected String itemImage;
        /**
         * The Item status.
         */
        @XStreamAlias("ItemStatus")
        protected Integer itemStatus;
        /**
         * The Item speech.
         */
        @XStreamAlias("ItemSpeech")
        protected String itemSpeech;
        /**
         * The Item op time.
         */
        @XStreamAlias("ItemOpTime")
        protected Long itemOpTime;
      }
    }

    /**
     * The type Notify node.
     */
    @Data
    @XStreamAlias("NotifyNode")
    public static class NotifyNode implements Serializable {
      private static final long serialVersionUID = 6031833682211475786L;

      /**
       * The Item name.
       */
      @XStreamAlias("ItemName")
      protected String itemName;
      /**
       * The Item user id.
       */
      @XStreamAlias("ItemUserId")
      protected String itemUserId;
      /**
       * The Item image.
       */
      @XStreamAlias("ItemImage")
      protected String itemImage;
    }
  }


  /**
   * From xml wx cp tp xml message.
   *
   * @param xml the xml
   * @return the wx cp tp xml message
   */
  public static WxCpTpXmlMessage fromXml(String xml) {
    //修改微信变态的消息内容格式，方便解析
    //xml = xml.replace("</PicList><PicList>", "");
    final WxCpTpXmlMessage xmlPackage = XStreamTransformer.fromXml(WxCpTpXmlMessage.class, xml);
    xmlPackage.setAllFieldsMap(XmlUtils.xml2Map(xml));
    return xmlPackage;
  }

  /**
   *
   * @param encryptedXml         the encrypted xml
   * @param wxCpTpConfigStorage  the wx cp config storage
   * @param timestamp            the timestamp
   * @param nonce                the nonce
   * @param msgSignature         the msg signature
   * @return                     the wx cp tp xml message
   */
  public static WxCpTpXmlMessage fromEncryptedXml(String encryptedXml, WxCpTpConfigStorage wxCpTpConfigStorage,
                                                  String timestamp, String nonce, String msgSignature) {
    WxCpTpCryptUtil cryptUtil = new WxCpTpCryptUtil(wxCpTpConfigStorage);
    String plainText = cryptUtil.decrypt(msgSignature, timestamp, nonce, encryptedXml);
    log.debug("解密后的原始xml消息内容：{}", plainText);
    return fromXml(plainText);
  }
}
