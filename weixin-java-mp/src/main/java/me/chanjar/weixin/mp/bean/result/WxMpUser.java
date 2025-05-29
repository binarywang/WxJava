package me.chanjar.weixin.mp.bean.result;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * 微信用户信息.
 *
 * @author chanjarster
 */
@Data
public class WxMpUser implements Serializable {
  private static final long serialVersionUID = 5788154322646488738L;

  private Boolean subscribe;
  private String openId;
  /**
   * @deprecated 2021年12月27日之后不再输出
   */
  @Deprecated
  private String nickname;
  private String language;
  /**
   * @deprecated 2021年12月27日之后不再输出
   */
  @Deprecated
  private String headImgUrl;
  private Long subscribeTime;
  /**
   * https://mp.weixin.qq.com/cgi-bin/announce?action=getannouncement&announce_id=11513156443eZYea&version=&lang=zh_CN
   * <pre>
   * 只有在将公众号绑定到微信开放平台账号后，才会出现该字段。
   * 另外，在用户未关注公众号时，将不返回用户unionID信息。
   * 已关注的用户，开发者可使用“获取用户基本信息接口”获取unionID；
   * 未关注用户，开发者可使用“微信授权登录接口”并将scope参数设置为snsapi_userinfo，获取用户unionID
   * </pre>
   */
  private String unionId;
  private String remark;
  private Integer groupId;
  private Long[] tagIds;

  /**
   * 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）.
   */
  private String[] privileges;

  /**
   * subscribe_scene 返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENE_PROFILE_LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_WECHAT_ADVERTISEMENT 微信广告，ADD_SCENE_OTHERS 其他
   */
  private String subscribeScene;

  /**
   * qr_scene 二维码扫码场景（开发者自定义）.
   */
  private String qrScene;

  /**
   * qr_scene_str 二维码扫码场景描述（开发者自定义）.
   */
  private String qrSceneStr;

  public static WxMpUser fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpUser.class);
  }

  public static List<WxMpUser> fromJsonList(String json) {
    Type collectionType = new TypeToken<List<WxMpUser>>() {
    }.getType();
    Gson gson = WxMpGsonBuilder.create();
    JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
    return gson.fromJson(jsonObject.get("user_info_list"), collectionType);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}
