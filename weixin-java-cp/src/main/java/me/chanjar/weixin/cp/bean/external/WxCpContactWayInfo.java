package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.chanjar.weixin.cp.util.json.WxCpConclusionAdapter;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 「联系我」方式 对象
 *
 * @author element
 */
@Data
@NoArgsConstructor
public class WxCpContactWayInfo implements Serializable {
  private static final long serialVersionUID = -8697184659526210472L;

  @SerializedName("contact_way")
  private ContactWay contactWay;

  /**
   * The type Contact way.
   */
  @Getter
  @Setter
  public static class ContactWay implements Serializable {
    private static final long serialVersionUID = -8697184659526210472L;

    /**
     * 联系方式的配置id
     */
    @SerializedName("config_id")
    private String configId;

    /**
     * <pre>
     * 必填
     * 联系方式类型,1-单人, 2-多人
     * </pre>
     */
    private TYPE type;

    /**
     * <pre>
     * 必填
     * 场景，1-在小程序中联系，2-通过二维码联系
     * </pre>
     */
    private SCENE scene;

    /**
     * <pre>
     * 非必填
     * 在小程序中联系时使用的控件样式
     * <b>单人样式(type=1)时可选1,2,3</b>
     * <b>多人样式(type=2)时可选1,2</b>
     * </pre>
     */
    private Integer style;

    /**
     * <pre>
     * 非必填
     * 联系方式的备注信息，用于助记，不超过30个字符
     * </pre>
     */
    private String remark;

    /**
     * <pre>
     * 非必填
     * 外部客户添加时是否无需验证，默认为true
     * </pre>
     */
    @SerializedName("skip_verify")
    private Boolean skipVerify = Boolean.TRUE;

    /**
     * <pre>
     * 非必填
     * 企业自定义的state参数，用于区分不同的添加渠道，在调用“获取外部联系人详情(WxCpExternalContactService.getContactDetail)”  时会返回该参数值，不超过30个字符
     * </pre>
     */
    private String state;

    /**
     * <pre>
     * 联系二维码的URL，仅在scene为2时返回
     * </pre>
     */
    @SerializedName("qr_code")
    private String qrCode;

    /**
     * <pre>
     * 使用该联系方式的用户userID列表，在type为1时为必填，且只能有一个
     * </pre>
     */
    @SerializedName("user")
    private List<String> users;


    /**
     * <pre>
     * 非必填
     * 使用该联系方式的部门id列表，只在type为2时有效
     * </pre>
     */
    @SerializedName("party")
    private List<String> parties;

    /**
     * <pre>
     * 非必填
     * 是否临时会话模式，true表示使用临时会话模式，默认为false
     * </pre>
     */
    @SerializedName("is_temp")
    private Boolean isTemp = Boolean.FALSE;

    /**
     * <pre>
     * 非必填
     * 临时会话二维码有效期，以秒为单位。该参数仅在is_temp为true时有效，默认7天
     * </pre>
     */
    @SerializedName("expires_in")
    private Integer expiresIn;

    /**
     * <pre>
     * 非必填
     * 临时会话有效期，以秒为单位。该参数仅在is_temp为true时有效，默认为添加好友后24小时
     * </pre>
     */
    @SerializedName("chat_expires_in")
    private Integer chatExpiresIn;

    /**
     * <pre>
     * 非必填
     * 可进行临时会话的客户unionid，该参数仅在is_temp为true时有效，如不指定则不进行限制
     * </pre>
     */
    @SerializedName("unionid")
    private String unionId;


    /**
     *非必填,是否开启同一外部企业客户只能添加同一个员工，默认为否，开启后，同一个企业的客户会优先添加到同一个跟进人
     */
    @SerializedName("is_exclusive")
    private boolean isExclusive;

    /**
     * <pre>
     * 非必填
     * 结束语，会话结束时自动发送给客户，可参考“结束语定义”，仅在is_temp为true时有效
     * </pre>
     */
    private Conclusion conclusions;

    /**
     * From json wx cp contact way info . contact way.
     *
     * @param json the json
     * @return the wx cp contact way info . contact way
     */
    public static WxCpContactWayInfo.ContactWay fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, WxCpContactWayInfo.ContactWay.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

    /**
     * 结束语定义
     */
    @Data
    @JsonAdapter(WxCpConclusionAdapter.class)
    public static class Conclusion implements Serializable {
      private static final long serialVersionUID = -8697184659526210472L;

      private String textContent;
      private String imgMediaId;
      private String imgPicUrl;
      private String linkTitle;
      private String linkPicUrl;
      private String linkDesc;
      private String linkUrl;
      private String miniProgramTitle;
      private String miniProgramPicMediaId;
      private String miniProgramAppId;
      private String miniProgramPage;
    }

  }


  /**
   * From json wx cp contact way info.
   *
   * @param json the json
   * @return the wx cp contact way info
   */
  public static WxCpContactWayInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpContactWayInfo.class);
  }

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * The enum Type.
   */
  public enum TYPE {
    /**
     * 单人
     */
    @SerializedName("1")
    SINGLE,

    /**
     * 多人
     */
    @SerializedName("2")
    MULTI

  }

  /**
   * The enum Scene.
   */
  public enum SCENE {

    /**
     * 在小程序中联系
     */
    @SerializedName("1")
    MINIPROGRAM,

    /**
     * 通过二维码联系
     */
    @SerializedName("2")
    QRCODE

  }

}
