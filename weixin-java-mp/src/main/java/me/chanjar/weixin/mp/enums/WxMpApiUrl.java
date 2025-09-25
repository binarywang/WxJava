package me.chanjar.weixin.mp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.WxMpHostConfig;

import static me.chanjar.weixin.mp.config.WxMpHostConfig.API_DEFAULT_HOST_URL;
import static me.chanjar.weixin.mp.config.WxMpHostConfig.MP_DEFAULT_HOST_URL;
import static me.chanjar.weixin.mp.config.WxMpHostConfig.OPEN_DEFAULT_HOST_URL;
import static me.chanjar.weixin.mp.config.WxMpHostConfig.buildUrl;

/**
 * <pre>
 *  公众号接口api地址
 *  Created by BinaryWang on 2019-06-03.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMpApiUrl {

  /**
   * 得到api完整地址.
   *
   * @param config 微信公众号配置
   * @return api地址
   */
  default String getUrl(WxMpConfigStorage config) {
    WxMpHostConfig hostConfig = null;
    if (config != null) {
      hostConfig = config.getHostConfig();
    }
    return buildUrl(hostConfig, this.getPrefix(), this.getPath());

  }

  /**
   * the path
   *
   * @return path
   */
  String getPath();

  /**
   * the prefix
   *
   * @return prefix
   */
  String getPrefix();

  @AllArgsConstructor
  @Getter
  enum Device implements WxMpApiUrl {
    /**
     * get_bind_device.
     */
    DEVICE_GET_BIND_DEVICE(API_DEFAULT_HOST_URL, "/device/get_bind_device"),
    /**
     * get_openid.
     */
    DEVICE_GET_OPENID(API_DEFAULT_HOST_URL, "/device/get_openid"),
    /**
     * compel_unbind.
     */
    DEVICE_COMPEL_UNBIND(API_DEFAULT_HOST_URL, "/device/compel_unbind?"),
    /**
     * unbind.
     */
    DEVICE_UNBIND(API_DEFAULT_HOST_URL, "/device/unbind?"),
    /**
     * compel_bind.
     */
    DEVICE_COMPEL_BIND(API_DEFAULT_HOST_URL, "/device/compel_bind"),
    /**
     * bind.
     */
    DEVICE_BIND(API_DEFAULT_HOST_URL, "/device/bind"),
    /**
     * authorize_device.
     */
    DEVICE_AUTHORIZE_DEVICE(API_DEFAULT_HOST_URL, "/device/authorize_device"),
    /**
     * getqrcode.
     */
    DEVICE_GETQRCODE(API_DEFAULT_HOST_URL, "/device/getqrcode"),
    /**
     * transmsg.
     */
    DEVICE_TRANSMSG(API_DEFAULT_HOST_URL, "/device/transmsg");

    private final String prefix;
    private final String path;
  }

  @AllArgsConstructor
  @Getter
  enum OAuth2 implements WxMpApiUrl {
    /**
     * 用code换取oauth2的access token.
     */
    OAUTH2_ACCESS_TOKEN_URL(API_DEFAULT_HOST_URL, "/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code"),
    /**
     * 刷新oauth2的access token.
     */
    OAUTH2_REFRESH_TOKEN_URL(API_DEFAULT_HOST_URL, "/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s"),
    /**
     * 用oauth2获取用户信息.
     */
    OAUTH2_USERINFO_URL(API_DEFAULT_HOST_URL, "/sns/userinfo?access_token=%s&openid=%s&lang=%s"),
    /**
     * 验证oauth2的access token是否有效.
     */
    OAUTH2_VALIDATE_TOKEN_URL(API_DEFAULT_HOST_URL, "/sns/auth?access_token=%s&openid=%s"),
    /**
     * oauth2授权的url连接.
     */
    CONNECT_OAUTH2_AUTHORIZE_URL(OPEN_DEFAULT_HOST_URL, "/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&connect_redirect=1#wechat_redirect");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum Other implements WxMpApiUrl {
    /**
     * 获取access_token.
     */
    GET_ACCESS_TOKEN_URL(API_DEFAULT_HOST_URL, "/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s"),
    /**
     * 获取稳定版 access_token.
     */
    GET_STABLE_ACCESS_TOKEN_URL(API_DEFAULT_HOST_URL, "/cgi-bin/stable_token"),
    /**
     * 获得各种类型的ticket.
     */
    GET_TICKET_URL(API_DEFAULT_HOST_URL, "/cgi-bin/ticket/getticket?type="),
    /**
     * 长链接转短链接接口.
     */
    SHORTURL_API_URL(API_DEFAULT_HOST_URL, "/cgi-bin/shorturl"),
    /**
     * 语义查询接口.
     */
    SEMANTIC_SEMPROXY_SEARCH_URL(API_DEFAULT_HOST_URL, "/semantic/semproxy/search"),
    /**
     * 获取微信服务器IP地址.
     */
    GET_CALLBACK_IP_URL(API_DEFAULT_HOST_URL, "/cgi-bin/getcallbackip"),
    /**
     * 网络检测.
     */
    NETCHECK_URL(API_DEFAULT_HOST_URL, "/cgi-bin/callback/check"),
    /**
     * 第三方使用网站应用授权登录的url.
     */
    QRCONNECT_URL(OPEN_DEFAULT_HOST_URL, "/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect"),
    /**
     * 获取公众号的自动回复规则.
     */
    GET_CURRENT_AUTOREPLY_INFO_URL(API_DEFAULT_HOST_URL, "/cgi-bin/get_current_autoreply_info"),
    /**
     * 公众号调用或第三方平台帮公众号调用对公众号的所有api调用（包括第三方帮其调用）次数进行清零.
     */
    CLEAR_QUOTA_URL(API_DEFAULT_HOST_URL, "/cgi-bin/clear_quota"),

    /**
     * 短key托管(生成短key的url)
     */
    GEN_SHORTEN_URL(API_DEFAULT_HOST_URL, "/cgi-bin/shorten/gen"),

    /**
     * 短key解析(解析短key的url)
     */
    FETCH_SHORTEN_URL(API_DEFAULT_HOST_URL, "/cgi-bin/shorten/fetch");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum Marketing implements WxMpApiUrl {
    /**
     * sets add.
     */
    USER_ACTION_SETS_ADD(API_DEFAULT_HOST_URL, "/marketing/user_action_sets/add?version=v1.0"),
    /**
     * get.
     */
    USER_ACTION_SETS_GET(API_DEFAULT_HOST_URL, "/marketing/user_action_sets/get"),
    /**
     * add.
     */
    USER_ACTIONS_ADD(API_DEFAULT_HOST_URL, "/marketing/user_actions/add?version=v1.0"),
    /**
     * get.
     */
    WECHAT_AD_LEADS_GET(API_DEFAULT_HOST_URL, "/marketing/wechat_ad_leads/get");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum Menu implements WxMpApiUrl {
    /**
     * get_current_selfmenu_info.
     */
    GET_CURRENT_SELFMENU_INFO(API_DEFAULT_HOST_URL, "/cgi-bin/get_current_selfmenu_info"),
    /**
     * trymatch.
     */
    MENU_TRYMATCH(API_DEFAULT_HOST_URL, "/cgi-bin/menu/trymatch"),
    /**
     * get.
     */
    MENU_GET(API_DEFAULT_HOST_URL, "/cgi-bin/menu/get"),
    /**
     * delconditional.
     */
    MENU_DELCONDITIONAL(API_DEFAULT_HOST_URL, "/cgi-bin/menu/delconditional"),
    /**
     * delete.
     */
    MENU_DELETE(API_DEFAULT_HOST_URL, "/cgi-bin/menu/delete"),
    /**
     * create.
     */
    MENU_CREATE(API_DEFAULT_HOST_URL, "/cgi-bin/menu/create"),
    /**
     * addconditional.
     */
    MENU_ADDCONDITIONAL(API_DEFAULT_HOST_URL, "/cgi-bin/menu/addconditional");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum Qrcode implements WxMpApiUrl {
    /**
     * create.
     */
    QRCODE_CREATE(API_DEFAULT_HOST_URL, "/cgi-bin/qrcode/create"),
    /**
     * showqrcode.
     */
    SHOW_QRCODE(MP_DEFAULT_HOST_URL, "/cgi-bin/showqrcode"),
    /**
     * showqrcode.
     */
    SHOW_QRCODE_WITH_TICKET(MP_DEFAULT_HOST_URL, "/cgi-bin/showqrcode?ticket=%s");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum ShakeAround implements WxMpApiUrl {
    /**
     * getshakeinfo.
     */
    SHAKEAROUND_USER_GETSHAKEINFO(API_DEFAULT_HOST_URL, "/shakearound/user/getshakeinfo"),
    /**
     * add.
     */
    SHAKEAROUND_PAGE_ADD(API_DEFAULT_HOST_URL, "/shakearound/page/add"),
    /**
     * bindpage.
     */
    SHAKEAROUND_DEVICE_BINDPAGE(API_DEFAULT_HOST_URL, "/shakearound/device/bindpage"),
    /**
     * search.
     */
    SHAKEAROUND_RELATION_SEARCH(API_DEFAULT_HOST_URL, "/shakearound/relation/search");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum SubscribeMsg implements WxMpApiUrl {
    /**
     * subscribemsg.
     */
    SUBSCRIBE_MESSAGE_AUTHORIZE_URL(MP_DEFAULT_HOST_URL, "/mp/subscribemsg?action=get_confirm&appid=%s&scene=%d&template_id=%s&redirect_url=%s&reserved=%s#wechat_redirect"),
    /**
     * subscribe once.
     */
    SEND_MESSAGE_ONCE_URL(API_DEFAULT_HOST_URL, "/cgi-bin/message/template/subscribe"),
    /**
     * 订阅通知消息发送.
     */
    SEND_SUBSCRIBE_MESSAGE_URL(API_DEFAULT_HOST_URL, "/cgi-bin/message/subscribe/bizsend"),
    /**
     * 获取模板标题下的关键词列表.
     */
    GET_PUB_TEMPLATE_TITLE_LIST_URL(API_DEFAULT_HOST_URL, "/wxaapi/newtmpl/getpubtemplatetitles"),
    /**
     * 获取模板标题下的关键词列表.
     */
    GET_PUB_TEMPLATE_KEY_WORDS_BY_ID_URL(API_DEFAULT_HOST_URL, "/wxaapi/newtmpl/getpubtemplatekeywords"),
    /**
     * 组合模板并添加至账号下的个人模板库.
     */
    TEMPLATE_ADD_URL(API_DEFAULT_HOST_URL, "/wxaapi/newtmpl/addtemplate"),
    /**
     * 获取当前账号下的个人模板列表.
     */
    TEMPLATE_LIST_URL(API_DEFAULT_HOST_URL, "/wxaapi/newtmpl/gettemplate"),
    /**
     * 删除账号下的某个模板.
     */
    TEMPLATE_DEL_URL(API_DEFAULT_HOST_URL, "/wxaapi/newtmpl/deltemplate"),
    /**
     * 获取小程序账号的类目
     */
    GET_CATEGORY_URL(API_DEFAULT_HOST_URL, "/wxaapi/newtmpl/getcategory"),
    UNIFORM_MSG_SEND_URL(API_DEFAULT_HOST_URL, "/cgi-bin/message/wxopen/template/uniform_send"),
    ACTIVITY_ID_CREATE_URL(API_DEFAULT_HOST_URL, "/cgi-bin/message/wxopen/activityid/create"),
    UPDATABLE_MSG_SEND_URL(API_DEFAULT_HOST_URL, "/cgi-bin/message/wxopen/updatablemsg/send");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum TemplateMsg implements WxMpApiUrl {
    /**
     * send.
     */
    MESSAGE_TEMPLATE_SEND(API_DEFAULT_HOST_URL, "/cgi-bin/message/template/send"),
    /**
     * api_set_industry.
     */
    TEMPLATE_API_SET_INDUSTRY(API_DEFAULT_HOST_URL, "/cgi-bin/template/api_set_industry"),
    /**
     * get_industry.
     */
    TEMPLATE_GET_INDUSTRY(API_DEFAULT_HOST_URL, "/cgi-bin/template/get_industry"),
    /**
     * api_add_template.
     */
    TEMPLATE_API_ADD_TEMPLATE(API_DEFAULT_HOST_URL, "/cgi-bin/template/api_add_template"),
    /**
     * get_all_private_template.
     */
    TEMPLATE_GET_ALL_PRIVATE_TEMPLATE(API_DEFAULT_HOST_URL, "/cgi-bin/template/get_all_private_template"),
    /**
     * del_private_template.
     */
    TEMPLATE_DEL_PRIVATE_TEMPLATE(API_DEFAULT_HOST_URL, "/cgi-bin/template/del_private_template");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum UserBlacklist implements WxMpApiUrl {
    /**
     * getblacklist.
     */
    GETBLACKLIST(API_DEFAULT_HOST_URL, "/cgi-bin/tags/members/getblacklist"),
    /**
     * batchblacklist.
     */
    BATCHBLACKLIST(API_DEFAULT_HOST_URL, "/cgi-bin/tags/members/batchblacklist"),
    /**
     * batchunblacklist.
     */
    BATCHUNBLACKLIST(API_DEFAULT_HOST_URL, "/cgi-bin/tags/members/batchunblacklist");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum UserTag implements WxMpApiUrl {
    /**
     * create.
     */
    TAGS_CREATE(API_DEFAULT_HOST_URL, "/cgi-bin/tags/create"),
    /**
     * get.
     */
    TAGS_GET(API_DEFAULT_HOST_URL, "/cgi-bin/tags/get"),
    /**
     * update.
     */
    TAGS_UPDATE(API_DEFAULT_HOST_URL, "/cgi-bin/tags/update"),
    /**
     * delete.
     */
    TAGS_DELETE(API_DEFAULT_HOST_URL, "/cgi-bin/tags/delete"),
    /**
     * get.
     */
    TAG_GET(API_DEFAULT_HOST_URL, "/cgi-bin/user/tag/get"),
    /**
     * batchtagging.
     */
    TAGS_MEMBERS_BATCHTAGGING(API_DEFAULT_HOST_URL, "/cgi-bin/tags/members/batchtagging"),
    /**
     * batchuntagging.
     */
    TAGS_MEMBERS_BATCHUNTAGGING(API_DEFAULT_HOST_URL, "/cgi-bin/tags/members/batchuntagging"),
    /**
     * getidlist.
     */
    TAGS_GETIDLIST(API_DEFAULT_HOST_URL, "/cgi-bin/tags/getidlist");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum Wifi implements WxMpApiUrl {
    /**
     * list.
     */
    BIZWIFI_SHOP_LIST(API_DEFAULT_HOST_URL, "/bizwifi/shop/list"),

    /**
     * get.
     */
    BIZWIFI_SHOP_GET(API_DEFAULT_HOST_URL, "/bizwifi/shop/get"),

    /**
     * upadte.
     */
    BIZWIFI_SHOP_UPDATE(API_DEFAULT_HOST_URL, "/bizwifi/shop/update");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum AiOpen implements WxMpApiUrl {
    /**
     * translatecontent.
     */
    TRANSLATE_URL(API_DEFAULT_HOST_URL, "/cgi-bin/media/voice/translatecontent?lfrom=%s&lto=%s"),
    /**
     * addvoicetorecofortext.
     */
    VOICE_UPLOAD_URL(API_DEFAULT_HOST_URL, "/cgi-bin/media/voice/addvoicetorecofortext?format=%s&voice_id=%s&lang=%s"),
    /**
     * queryrecoresultfortext.
     */
    VOICE_QUERY_RESULT_URL(API_DEFAULT_HOST_URL, "/cgi-bin/media/voice/queryrecoresultfortext");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum Ocr implements WxMpApiUrl {
    /**
     * 身份证识别.
     */
    IDCARD(API_DEFAULT_HOST_URL, "/cv/ocr/idcard?img_url=%s"),

    FILEIDCARD(API_DEFAULT_HOST_URL, "/cv/ocr/idcard"),

    /**
     * 银行卡OCR识别
     */
    BANK_CARD(API_DEFAULT_HOST_URL, "/cv/ocr/bankcard?img_url=%s"),

    /**
     * 银行卡OCR识别(文件)
     */
    FILE_BANK_CARD(API_DEFAULT_HOST_URL, "/cv/ocr/bankcard"),

    /**
     * 行驶证OCR识别
     */
    DRIVING(API_DEFAULT_HOST_URL, "/cv/ocr/driving?img_url=%s"),
    /**
     * 行驶证OCR识别(文件)
     */
    FILE_DRIVING(API_DEFAULT_HOST_URL, "/cv/ocr/driving"),

    /**
     * 驾驶证OCR识别
     */
    DRIVING_LICENSE(API_DEFAULT_HOST_URL, "/cv/ocr/drivinglicense?img_url=%s"),

    /**
     * 驾驶证OCR识别(文件)
     */
    FILE_DRIVING_LICENSE(API_DEFAULT_HOST_URL, "/cv/ocr/drivinglicense"),

    /**
     * 营业执照OCR识别
     */
    BIZ_LICENSE(API_DEFAULT_HOST_URL, "/cv/ocr/bizlicense?img_url=%s"),

    /**
     * 营业执照OCR识别(文件)
     */
    FILE_BIZ_LICENSE(API_DEFAULT_HOST_URL, "/cv/ocr/bizlicense"),

    /**
     * 通用印刷体OCR识别
     */
    COMM(API_DEFAULT_HOST_URL, "/cv/ocr/comm?img_url=%s"),

    /**
     * 通用印刷体OCR识别(文件)
     */
    FILE_COMM(API_DEFAULT_HOST_URL, "/cv/ocr/comm");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum Card implements WxMpApiUrl {
    /**
     * create.
     */
    CARD_CREATE(API_DEFAULT_HOST_URL, "/card/create"),
    /**
     * get.
     */
    CARD_GET(API_DEFAULT_HOST_URL, "/card/get"),
    /**
     * wx_card.
     */
    CARD_GET_TICKET(API_DEFAULT_HOST_URL, "/cgi-bin/ticket/getticket?type=wx_card"),
    /**
     * decrypt.
     */
    CARD_CODE_DECRYPT(API_DEFAULT_HOST_URL, "/card/code/decrypt"),
    /**
     * get.
     */
    CARD_CODE_GET(API_DEFAULT_HOST_URL, "/card/code/get"),
    /**
     * consume.
     */
    CARD_CODE_CONSUME(API_DEFAULT_HOST_URL, "/card/code/consume"),
    /**
     * mark.
     */
    CARD_CODE_MARK(API_DEFAULT_HOST_URL, "/card/code/mark"),
    /**
     * set.
     */
    CARD_TEST_WHITELIST(API_DEFAULT_HOST_URL, "/card/testwhitelist/set"),
    /**
     * create.
     */
    CARD_QRCODE_CREATE(API_DEFAULT_HOST_URL, "/card/qrcode/create"),
    /**
     * create.
     */
    CARD_LANDING_PAGE_CREATE(API_DEFAULT_HOST_URL, "/card/landingpage/create"),
    /**
     * 将用户的卡券设置为失效状态.
     */
    CARD_CODE_UNAVAILABLE(API_DEFAULT_HOST_URL, "/card/code/unavailable"),
    /**
     * 卡券删除.
     */
    CARD_DELETE(API_DEFAULT_HOST_URL, "/card/delete"),

    /**
     * 导入code接口.
     */
    CARD_CODE_DEPOSIT(API_DEFAULT_HOST_URL, "/card/code/deposit"),

    /**
     * 查询导入code数目接口
     */
    CARD_CODE_DEPOSIT_COUNT(API_DEFAULT_HOST_URL, "/card/code/getdepositcount"),

    /**
     * 核查code接口
     */
    CARD_CODE_CHECKCODE(API_DEFAULT_HOST_URL, "/card/code/checkcode"),

    /**
     * 图文消息群发卡券
     */
    CARD_MPNEWS_GETHTML(API_DEFAULT_HOST_URL, "/card/mpnews/gethtml"),

    /**
     * 修改库存接口
     */
    CARD_MODIFY_STOCK(API_DEFAULT_HOST_URL, "/card/modifystock"),

    /**
     * 更改Code接口
     */
    CARD_CODE_UPDATE(API_DEFAULT_HOST_URL, "/card/code/update"),

    /**
     * 设置买单接口
     */
    CARD_PAYCELL_SET(API_DEFAULT_HOST_URL, "/card/paycell/set"),

    /**
     * 设置自助核销接口
     */
    CARD_SELF_CONSUME_CELL_SET(API_DEFAULT_HOST_URL, "/card/selfconsumecell/set"),

    /**
     * 获取用户已领取卡券接口
     */
    CARD_USER_CARD_LIST(API_DEFAULT_HOST_URL, "/card/user/getcardlist"),
    ;

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum DataCube implements WxMpApiUrl {
    /**
     * getusersummary.
     */
    GET_USER_SUMMARY(API_DEFAULT_HOST_URL, "/datacube/getusersummary"),
    /**
     * getusercumulate.
     */
    GET_USER_CUMULATE(API_DEFAULT_HOST_URL, "/datacube/getusercumulate"),
    /**
     * getarticlesummary.
     */
    GET_ARTICLE_SUMMARY(API_DEFAULT_HOST_URL, "/datacube/getarticlesummary"),
    /**
     * getarticletotal.
     */
    GET_ARTICLE_TOTAL(API_DEFAULT_HOST_URL, "/datacube/getarticletotal"),
    /**
     * getuserread.
     */
    GET_USER_READ(API_DEFAULT_HOST_URL, "/datacube/getuserread"),
    /**
     * getuserreadhour.
     */
    GET_USER_READ_HOUR(API_DEFAULT_HOST_URL, "/datacube/getuserreadhour"),
    /**
     * getusershare.
     */
    GET_USER_SHARE(API_DEFAULT_HOST_URL, "/datacube/getusershare"),
    /**
     * getusersharehour.
     */
    GET_USER_SHARE_HOUR(API_DEFAULT_HOST_URL, "/datacube/getusersharehour"),
    /**
     * getupstreammsg.
     */
    GET_UPSTREAM_MSG(API_DEFAULT_HOST_URL, "/datacube/getupstreammsg"),
    /**
     * getupstreammsghour.
     */
    GET_UPSTREAM_MSG_HOUR(API_DEFAULT_HOST_URL, "/datacube/getupstreammsghour"),
    /**
     * getupstreammsgweek.
     */
    GET_UPSTREAM_MSG_WEEK(API_DEFAULT_HOST_URL, "/datacube/getupstreammsgweek"),
    /**
     * getupstreammsgmonth.
     */
    GET_UPSTREAM_MSG_MONTH(API_DEFAULT_HOST_URL, "/datacube/getupstreammsgmonth"),
    /**
     * getupstreammsgdist.
     */
    GET_UPSTREAM_MSG_DIST(API_DEFAULT_HOST_URL, "/datacube/getupstreammsgdist"),
    /**
     * getupstreammsgdistweek.
     */
    GET_UPSTREAM_MSG_DIST_WEEK(API_DEFAULT_HOST_URL, "/datacube/getupstreammsgdistweek"),
    /**
     * getupstreammsgdistmonth.
     */
    GET_UPSTREAM_MSG_DIST_MONTH(API_DEFAULT_HOST_URL, "/datacube/getupstreammsgdistmonth"),
    /**
     * getinterfacesummary.
     */
    GET_INTERFACE_SUMMARY(API_DEFAULT_HOST_URL, "/datacube/getinterfacesummary"),
    /**
     * getinterfacesummaryhour.
     */
    GET_INTERFACE_SUMMARY_HOUR(API_DEFAULT_HOST_URL, "/datacube/getinterfacesummaryhour");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum Kefu implements WxMpApiUrl {
    /**
     * send.
     */
    MESSAGE_CUSTOM_SEND(API_DEFAULT_HOST_URL, "/cgi-bin/message/custom/send"),
    /**
     * getkflist.
     */
    GET_KF_LIST(API_DEFAULT_HOST_URL, "/cgi-bin/customservice/getkflist"),
    /**
     * getonlinekflist.
     */
    GET_ONLINE_KF_LIST(API_DEFAULT_HOST_URL, "/cgi-bin/customservice/getonlinekflist"),
    /**
     * add.
     */
    KFACCOUNT_ADD(API_DEFAULT_HOST_URL, "/customservice/kfaccount/add"),
    /**
     * update.
     */
    KFACCOUNT_UPDATE(API_DEFAULT_HOST_URL, "/customservice/kfaccount/update"),
    /**
     * inviteworker.
     */
    KFACCOUNT_INVITE_WORKER(API_DEFAULT_HOST_URL, "/customservice/kfaccount/inviteworker"),
    /**
     * uploadheadimg.
     */
    KFACCOUNT_UPLOAD_HEAD_IMG(API_DEFAULT_HOST_URL, "/customservice/kfaccount/uploadheadimg?kf_account=%s"),
    /**
     * del kfaccount.
     */
    KFACCOUNT_DEL(API_DEFAULT_HOST_URL, "/customservice/kfaccount/del?kf_account=%s"),
    /**
     * create.
     */
    KFSESSION_CREATE(API_DEFAULT_HOST_URL, "/customservice/kfsession/create"),
    /**
     * close.
     */
    KFSESSION_CLOSE(API_DEFAULT_HOST_URL, "/customservice/kfsession/close"),
    /**
     * getsession.
     */
    KFSESSION_GET_SESSION(API_DEFAULT_HOST_URL, "/customservice/kfsession/getsession?openid=%s"),
    /**
     * getsessionlist.
     */
    KFSESSION_GET_SESSION_LIST(API_DEFAULT_HOST_URL, "/customservice/kfsession/getsessionlist?kf_account=%s"),
    /**
     * getwaitcase.
     */
    KFSESSION_GET_WAIT_CASE(API_DEFAULT_HOST_URL, "/customservice/kfsession/getwaitcase"),
    /**
     * getmsglist.
     */
    MSG_RECORD_LIST(API_DEFAULT_HOST_URL, "/customservice/msgrecord/getmsglist"),
    /**
     * typing.
     */
    CUSTOM_TYPING(API_DEFAULT_HOST_URL, "/cgi-bin/message/custom/typing");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum MassMessage implements WxMpApiUrl {
    /**
     * 上传群发用的图文消息.
     */
    MEDIA_UPLOAD_NEWS_URL(API_DEFAULT_HOST_URL, "/cgi-bin/media/uploadnews"),
    /**
     * 上传群发用的视频.
     */
    MEDIA_UPLOAD_VIDEO_URL(API_DEFAULT_HOST_URL, "/cgi-bin/media/uploadvideo"),
    /**
     * 分组群发消息.
     */
    MESSAGE_MASS_SENDALL_URL(API_DEFAULT_HOST_URL, "/cgi-bin/message/mass/sendall"),
    /**
     * 按openId列表群发消息.
     */
    MESSAGE_MASS_SEND_URL(API_DEFAULT_HOST_URL, "/cgi-bin/message/mass/send"),
    /**
     * 群发消息预览接口.
     */
    MESSAGE_MASS_PREVIEW_URL(API_DEFAULT_HOST_URL, "/cgi-bin/message/mass/preview"),
    /**
     * 删除群发接口.
     */
    MESSAGE_MASS_DELETE_URL(API_DEFAULT_HOST_URL, "/cgi-bin/message/mass/delete"),


    /**
     * 获取群发速度.
     */
    MESSAGE_MASS_SPEED_GET_URL(API_DEFAULT_HOST_URL, "/cgi-bin/message/mass/speed/get"),


    /**
     * 设置群发速度.
     */
    MESSAGE_MASS_SPEED_SET_URL(API_DEFAULT_HOST_URL, "/cgi-bin/message/mass/speed/set"),


    /**
     * 查询群发消息发送状态【订阅号与服务号认证后均可用】
     */
    MESSAGE_MASS_GET_URL(API_DEFAULT_HOST_URL, "/cgi-bin/message/mass/get");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum Material implements WxMpApiUrl {
    /**
     * get.
     */
    MEDIA_GET_URL(API_DEFAULT_HOST_URL, "/cgi-bin/media/get"),
    /**
     * jssdk media get.
     */
    JSSDK_MEDIA_GET_URL(API_DEFAULT_HOST_URL, "/cgi-bin/media/get/jssdk"),
    /**
     * upload.
     */
    MEDIA_UPLOAD_URL(API_DEFAULT_HOST_URL, "/cgi-bin/media/upload?type=%s"),
    /**
     * uploadimg.
     */
    IMG_UPLOAD_URL(API_DEFAULT_HOST_URL, "/cgi-bin/media/uploadimg"),
    /**
     * add_material.
     */
    MATERIAL_ADD_URL(API_DEFAULT_HOST_URL, "/cgi-bin/material/add_material?type=%s"),
    /**
     * add_news.
     */
    NEWS_ADD_URL(API_DEFAULT_HOST_URL, "/cgi-bin/material/add_news"),
    /**
     * get_material.
     */
    MATERIAL_GET_URL(API_DEFAULT_HOST_URL, "/cgi-bin/material/get_material"),
    /**
     * update_news.
     */
    NEWS_UPDATE_URL(API_DEFAULT_HOST_URL, "/cgi-bin/material/update_news"),
    /**
     * del_material.
     */
    MATERIAL_DEL_URL(API_DEFAULT_HOST_URL, "/cgi-bin/material/del_material"),
    /**
     * get_materialcount.
     */
    MATERIAL_GET_COUNT_URL(API_DEFAULT_HOST_URL, "/cgi-bin/material/get_materialcount"),
    /**
     * batchget_material.
     */
    MATERIAL_BATCHGET_URL(API_DEFAULT_HOST_URL, "/cgi-bin/material/batchget_material");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum MemberCard implements WxMpApiUrl {
    /**
     * create.
     */
    MEMBER_CARD_CREATE(API_DEFAULT_HOST_URL, "/card/create"),
    /**
     * activate.
     */
    MEMBER_CARD_ACTIVATE(API_DEFAULT_HOST_URL, "/card/membercard/activate"),
    /**
     * get userinfo.
     */
    MEMBER_CARD_USER_INFO_GET(API_DEFAULT_HOST_URL, "/card/membercard/userinfo/get"),
    /**
     * updateuser.
     */
    MEMBER_CARD_UPDATE_USER(API_DEFAULT_HOST_URL, "/card/membercard/updateuser"),
    /**
     * 会员卡激活之微信开卡接口(wx_activate=true情况调用).
     */
    MEMBER_CARD_ACTIVATE_USER_FORM(API_DEFAULT_HOST_URL, "/card/membercard/activateuserform/set"),
    /**
     * 获取会员卡开卡插件参数.
     */
    MEMBER_CARD_ACTIVATE_URL(API_DEFAULT_HOST_URL, "/card/membercard/activate/geturl"),
    /**
     * 会员卡信息更新.
     */
    MEMBER_CARD_UPDATE(API_DEFAULT_HOST_URL, "/card/update"),
    /**
     * 跳转型会员卡开卡字段.
     * 获取用户提交资料(wx_activate=true情况调用),开发者根据activate_ticket获取到用户填写的信息
     */
    MEMBER_CARD_ACTIVATE_TEMP_INFO(API_DEFAULT_HOST_URL, "/card/membercard/activatetempinfo/get");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum Store implements WxMpApiUrl {
    /**
     * getwxcategory.
     */
    POI_GET_WX_CATEGORY_URL(API_DEFAULT_HOST_URL, "/cgi-bin/poi/getwxcategory"),
    /**
     * updatepoi.
     */
    POI_UPDATE_URL(API_DEFAULT_HOST_URL, "/cgi-bin/poi/updatepoi"),
    /**
     * getpoilist.
     */
    POI_LIST_URL(API_DEFAULT_HOST_URL, "/cgi-bin/poi/getpoilist"),
    /**
     * delpoi.
     */
    POI_DEL_URL(API_DEFAULT_HOST_URL, "/cgi-bin/poi/delpoi"),
    /**
     * getpoi.
     */
    POI_GET_URL(API_DEFAULT_HOST_URL, "/cgi-bin/poi/getpoi"),
    /**
     * addpoi.
     */
    POI_ADD_URL(API_DEFAULT_HOST_URL, "/cgi-bin/poi/addpoi");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum User implements WxMpApiUrl {
    /**
     * batchget.
     */
    USER_INFO_BATCH_GET_URL(API_DEFAULT_HOST_URL, "/cgi-bin/user/info/batchget"),
    /**
     * get.
     */
    USER_GET_URL(API_DEFAULT_HOST_URL, "/cgi-bin/user/get"),
    /**
     * info.
     */
    USER_INFO_URL(API_DEFAULT_HOST_URL, "/cgi-bin/user/info"),
    /**
     * updateremark.
     */
    USER_INFO_UPDATE_REMARK_URL(API_DEFAULT_HOST_URL, "/cgi-bin/user/info/updateremark"),
    /**
     * changeopenid.
     */
    USER_CHANGE_OPENID_URL(API_DEFAULT_HOST_URL, "/cgi-bin/changeopenid");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum Comment implements WxMpApiUrl {
    /**
     * 打开已群发文章评论.
     */
    OPEN(API_DEFAULT_HOST_URL, "/cgi-bin/comment/open"),

    /**
     * 关闭已群发文章评论.
     */
    CLOSE(API_DEFAULT_HOST_URL, "/cgi-bin/comment/close"),

    /**
     * 查看指定文章的评论数据.
     */
    LIST(API_DEFAULT_HOST_URL, "/cgi-bin/comment/list"),

    /**
     * 将评论标记精选.
     */
    MARK_ELECT(API_DEFAULT_HOST_URL, "/cgi-bin/comment/markelect"),

    /**
     * 将评论取消精选.
     */
    UNMARK_ELECT(API_DEFAULT_HOST_URL, "/cgi-bin/comment/unmarkelect"),

    /**
     * 删除评论.
     */
    DELETE(API_DEFAULT_HOST_URL, "/cgi-bin/comment/delete"),

    /**
     * 回复评论.
     */
    REPLY_ADD(API_DEFAULT_HOST_URL, "/cgi-bin/comment/reply/add"),

    /**
     * 删除回复.
     */
    REPLY_DELETE(API_DEFAULT_HOST_URL, "/cgi-bin/comment/reply/delete");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum ImgProc implements WxMpApiUrl {
    /**
     * 二维码/条码识别
     */
    QRCODE(API_DEFAULT_HOST_URL, "/cv/img/qrcode?img_url=%s"),

    /**
     * 二维码/条码识别(文件)
     */
    FILE_QRCODE(API_DEFAULT_HOST_URL, "/cv/img/qrcode"),

    /**
     * 图片高清化
     */
    SUPER_RESOLUTION(API_DEFAULT_HOST_URL, "/cv/img/superresolution?img_url=%s"),

    /**
     * 图片高清化(文件)
     */
    FILE_SUPER_RESOLUTION(API_DEFAULT_HOST_URL, "/cv/img/superresolution"),

    /**
     * 图片智能裁剪
     */
    AI_CROP(API_DEFAULT_HOST_URL, "/cv/img/aicrop?img_url=%s&ratios=%s"),

    /**
     * 图片智能裁剪(文件)
     */
    FILE_AI_CROP(API_DEFAULT_HOST_URL, "/cv/img/aicrop?ratios=%s");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum Invoice implements WxMpApiUrl {

    /**
     * 获取用户开票授权地址
     */
    GET_AUTH_URL(API_DEFAULT_HOST_URL, "/card/invoice/getauthurl"),

    /**
     * 获取用户开票授权信息
     */
    GET_AUTH_DATA(API_DEFAULT_HOST_URL, "/card/invoice/getauthdata"),

    /**
     * 拒绝为用户开票
     */
    REJECT_INSERT(API_DEFAULT_HOST_URL, "/card/invoice/rejectinsert"),

    /**
     * 开票
     */
    MAKE_OUT_INVOICE(API_DEFAULT_HOST_URL, "/card/invoice/makeoutinvoice"),

    /**
     * 发票冲红
     */
    CLEAR_OUT_INVOICE(API_DEFAULT_HOST_URL, "/card/invoice/clearoutinvoice"),

    /**
     * 查询发票信息
     */
    QUERY_INVOICE_INFO(API_DEFAULT_HOST_URL, "/card/invoice/queryinvoceinfo"),

    /**
     * 设置商户信息联系
     */
    SET_CONTACT_SET_BIZ_ATTR(API_DEFAULT_HOST_URL, "/card/invoice/setbizattr?action=set_contact"),

    /**
     * 获取商户联系信息
     */
    GET_CONTACT_SET_BIZ_ATTR(API_DEFAULT_HOST_URL, "/card/invoice/setbizattr?action=get_contact"),

    /**
     * 设置授权页面字段
     */
    SET_AUTH_FIELD_SET_BIZ_ATTR(API_DEFAULT_HOST_URL, "/card/invoice/setbizattr?action=set_auth_field"),

    /**
     * 获取授权页面字段
     */
    GET_AUTH_FIELD_SET_BIZ_ATTR(API_DEFAULT_HOST_URL, "/card/invoice/setbizattr?action=get_auth_field"),

    /**
     * 设置关联商户
     */
    SET_PAY_MCH_SET_BIZ_ATTR(API_DEFAULT_HOST_URL, "/card/invoice/setbizattr?action=set_pay_mch"),

    /**
     * 获取关联商户
     */
    GET_PAY_MCH_SET_BIZ_ATTR(API_DEFAULT_HOST_URL, "/card/invoice/setbizattr?action=get_pay_mch"),

    /**
     * 报销方查询报销发票信息
     */
    GET_INVOICE_INFO(API_DEFAULT_HOST_URL, "/card/invoice/reimburse/getinvoiceinfo"),

    /**
     * 报销方批量查询报销发票信息
     */
    GET_INVOICE_BATCH(API_DEFAULT_HOST_URL, "/card/invoice/reimburse/getinvoicebatch"),

    /**
     * 报销方更新发票状态
     */
    UPDATE_INVOICE_STATUS(API_DEFAULT_HOST_URL, "/card/invoice/reimburse/updateinvoicestatus"),

    /**
     * 报销方批量更新发票状态
     */
    UPDATE_STATUS_BATCH(API_DEFAULT_HOST_URL, "/card/invoice/reimburse/updatestatusbatch"),
    ;
    private final String prefix;
    private final String path;

  }

  /**
   * 对话能力
   */
  @AllArgsConstructor
  @Getter
  enum Guide implements WxMpApiUrl {
    /**
     * 添加顾问
     */
    ADD_GUIDE(API_DEFAULT_HOST_URL, "/cgi-bin/guide/addguideacct"),
    /**
     * 修改顾问
     */
    UPDATE_GUIDE(API_DEFAULT_HOST_URL, "/cgi-bin/guide/updateguideacct"),
    /**
     * 获取顾问信息
     */
    GET_GUIDE(API_DEFAULT_HOST_URL, "/cgi-bin/guide/getguideacct"),
    /**
     * 删除顾问
     */
    DEL_GUIDE(API_DEFAULT_HOST_URL, "/cgi-bin/guide/delguideacct"),
    /**
     * 获取服务号顾问列表
     */
    LIST_GUIDE(API_DEFAULT_HOST_URL, "/cgi-bin/guide/getguideacctlist"),
    /**
     * 生成顾问二维码
     */
    CREATE_QR_CODE(API_DEFAULT_HOST_URL, "/cgi-bin/guide/guidecreateqrcode"),
    /**
     * 获取顾问聊天记录
     */
    GET_GUIDE_CHAT_RECORD(API_DEFAULT_HOST_URL, "/cgi-bin/guide/getguidebuyerchatrecord"),
    /**
     * 设置快捷回复与关注自动回复
     */
    SET_GUIDE_CONFIG(API_DEFAULT_HOST_URL, "/cgi-bin/guide/setguideconfig"),
    /**
     * 获取快捷回复与关注自动回复
     */
    GET_GUIDE_CONFIG(API_DEFAULT_HOST_URL, "/cgi-bin/guide/getguideconfig"),
    /**
     * 为服务号设置敏感词与离线自动回复
     */
    SET_GUIDE_ACCT_CONFIG(API_DEFAULT_HOST_URL, "/cgi-bin/guide/setguideacctconfig"),
    /**
     * 获取服务号敏感词与离线自动回复
     */
    GET_GUIDE_ACCT_CONFIG(API_DEFAULT_HOST_URL, "/cgi-bin/guide/getguideacctconfig"),
    /**
     * 允许微信用户复制小程序页面路径
     */
    PUSH_SHOW_WX_PATH_MENU(API_DEFAULT_HOST_URL, "/cgi-bin/guide/pushshowwxapathmenu"),
    /**
     * 新建顾问分组
     */
    NEW_GUIDE_GROUP(API_DEFAULT_HOST_URL, "/cgi-bin/guide/newguidegroup"),
    /**
     * 获取服务号下所有顾问分组的列表
     */
    GET_GUIDE_GROUP_LIST(API_DEFAULT_HOST_URL, "/cgi-bin/guide/getguidegrouplist"),
    /**
     * 获取指定顾问分组内顾问信息
     */
    GET_GROUP_GUIDE_INFO(API_DEFAULT_HOST_URL, "/cgi-bin/guide/getgroupinfo"),
    /**
     * 分组内添加顾问
     */
    ADD_GROUP_GUIDE(API_DEFAULT_HOST_URL, "/cgi-bin/guide/addguide2guidegroup"),
    /**
     * 分组内删除顾问
     */
    DEL_GROUP_GUIDE(API_DEFAULT_HOST_URL, "/cgi-bin/guide/delguide2guidegroup"),
    /**
     * 获取顾问所在分组
     */
    GET_GROUP_ON_GUIDE(API_DEFAULT_HOST_URL, "/cgi-bin/guide/getgroupbyguide"),
    /**
     * 删除指定顾问分组
     */
    DEL_GROUP(API_DEFAULT_HOST_URL, "/cgi-bin/guide/delguidegroup"),
    /**
     * 为顾问分配客户
     */
    ADD_GUIDE_BUYER_RELATION(API_DEFAULT_HOST_URL, "/cgi-bin/guide/addguidebuyerrelation"),
    /**
     * 为顾问移除客户
     */
    DEL_GUIDE_BUYER_RELATION(API_DEFAULT_HOST_URL, "/cgi-bin/guide/delguidebuyerrelation"),
    /**
     * 获取顾问的客户列表
     */
    GET_GUIDE_BUYER_RELATION_LIST(API_DEFAULT_HOST_URL, "/cgi-bin/guide/getguidebuyerrelationlist"),
    /**
     * 为客户更换顾问
     */
    REBIND_GUIDE_ACCT_FOR_BUYER(API_DEFAULT_HOST_URL, "/cgi-bin/guide/rebindguideacctforbuyer"),
    /**
     * 修改客户昵称
     */
    UPDATE_GUIDE_BUYER_RELATION(API_DEFAULT_HOST_URL, "/cgi-bin/guide/updateguidebuyerrelation"),
    /**
     * 查询客户所属顾问
     */
    GET_GUIDE_BUYER_RELATION_BY_BUYER(API_DEFAULT_HOST_URL, "/cgi-bin/guide/getguidebuyerrelationbybuyer"),
    /**
     * 查询指定顾问和客户的关系
     */
    GET_GUIDE_BUYER_RELATION(API_DEFAULT_HOST_URL, "/cgi-bin/guide/getguidebuyerrelation"),
    /**
     * 新建标签类型
     */
    NEW_GUIDE_TAG_OPTION(API_DEFAULT_HOST_URL, "/cgi-bin/guide/newguidetagoption"),
    /**
     * 删除标签类型
     */
    DEL_GUIDE_TAG_OPTION(API_DEFAULT_HOST_URL, "/cgi-bin/guide/delguidetagoption"),
    /**
     * 为标签添加可选值
     */
    ADD_GUIDE_TAG_OPTION(API_DEFAULT_HOST_URL, "/cgi-bin/guide/addguidetagoption"),
    /**
     * 获取标签和可选值
     */
    GET_GUIDE_TAG_OPTION(API_DEFAULT_HOST_URL, "/cgi-bin/guide/getguidetagoption"),
    /**
     * 为客户设置标签
     */
    ADD_GUIDE_BUYER_TAG(API_DEFAULT_HOST_URL, "/cgi-bin/guide/addguidebuyertag"),
    /**
     * 查询客户标签
     */
    GET_GUIDE_BUYER_TAG(API_DEFAULT_HOST_URL, "/cgi-bin/guide/getguidebuyertag"),
    /**
     * 根据标签值筛选客户
     */
    QUERY_GUIDE_BUYER_BY_TAG(API_DEFAULT_HOST_URL, "/cgi-bin/guide/queryguidebuyerbytag"),
    /**
     * 删除客户标签
     */
    DEL_GUIDE_BUYER_TAG(API_DEFAULT_HOST_URL, "/cgi-bin/guide/delguidebuyertag"),
    /**
     * 设置自定义客户信息
     */
    ADD_GUIDE_BUYER_DISPLAY_TAG(API_DEFAULT_HOST_URL, "/cgi-bin/guide/addguidebuyerdisplaytag"),
    /**
     * 获取自定义客户信息
     */
    GET_GUIDE_BUYER_DISPLAY_TAG(API_DEFAULT_HOST_URL, "/cgi-bin/guide/getguidebuyerdisplaytag"),
    /**
     * 添加小程序卡片素材
     */
    SET_GUIDE_CARD_MATERIAL(API_DEFAULT_HOST_URL, "/cgi-bin/guide/setguidecardmaterial"),
    /**
     * 查询小程序卡片素材
     */
    GET_GUIDE_CARD_MATERIAL(API_DEFAULT_HOST_URL, "/cgi-bin/guide/getguidecardmaterial"),
    /**
     * 删除小程序卡片素材
     */
    DEL_GUIDE_CARD_MATERIAL(API_DEFAULT_HOST_URL, "/cgi-bin/guide/delguidecardmaterial"),
    /**
     * 添加图片素材
     */
    SET_GUIDE_IMAGE_MATERIAL(API_DEFAULT_HOST_URL, "/cgi-bin/guide/setguideimagematerial"),
    /**
     * 查询图片素材
     */
    GET_GUIDE_IMAGE_MATERIAL(API_DEFAULT_HOST_URL, "/cgi-bin/guide/getguideimagematerial"),
    /**
     * 删除图片素材
     */
    DEL_GUIDE_IMAGE_MATERIAL(API_DEFAULT_HOST_URL, "/cgi-bin/guide/delguideimagematerial"),
    /**
     * 添加文字素材
     */
    SET_GUIDE_WORD_MATERIAL(API_DEFAULT_HOST_URL, "/cgi-bin/guide/setguidewordmaterial"),
    /**
     * 查询文字素材
     */
    GET_GUIDE_WORD_MATERIAL(API_DEFAULT_HOST_URL, "/cgi-bin/guide/getguidewordmaterial"),
    /**
     * 删除文字素材
     */
    DEL_GUIDE_WORD_MATERIAL(API_DEFAULT_HOST_URL, "/cgi-bin/guide/delguidewordmaterial"),
    /**
     * 添加群发任务
     */
    ADD_GUIDE_MASSED_JOB(API_DEFAULT_HOST_URL, "/cgi-bin/guide/addguidemassendjob"),
    /**
     * 获取群发任务列表
     */
    GET_GUIDE_MASSED_JOB_LIST(API_DEFAULT_HOST_URL, "/cgi-bin/guide/getguidemassendjoblist"),
    /**
     * 获取指定群发任务信息
     */
    GET_GUIDE_MASSED_JOB(API_DEFAULT_HOST_URL, "/cgi-bin/guide/getguidemassendjob"),
    /**
     * 修改群发任务
     */
    UPDATE_GUIDE_MASSED_JOB(API_DEFAULT_HOST_URL, "/cgi-bin/guide/updateguidemassendjob"),
    /**
     * 取消群发任务
     */
    CANCEL_GUIDE_MASSED_JOB(API_DEFAULT_HOST_URL, "/cgi-bin/guide/cancelguidemassendjob"),
    ;


    private final String prefix;
    private final String path;

  }

  /**
   * 草稿箱 能力:
   * 新建草稿
   * 获取草稿
   * 删除草稿
   * 修改草稿
   * 获取草稿总数
   * 获取草稿列表
   * MP端开关（仅内测期间使用）- 上线后废弃，没实现，可以自己去公众号后台开启草稿箱
   */
  @AllArgsConstructor
  @Getter
  enum Draft implements WxMpApiUrl {

    /**
     * 新建草稿
     */
    ADD_DRAFT(API_DEFAULT_HOST_URL, "/cgi-bin/draft/add"),
    /**
     * 修改草稿
     */
    UPDATE_DRAFT(API_DEFAULT_HOST_URL, "/cgi-bin/draft/update"),
    /**
     * 获取草稿
     */
    GET_DRAFT(API_DEFAULT_HOST_URL, "/cgi-bin/draft/get"),
    /**
     * 删除草稿
     */
    DEL_DRAFT(API_DEFAULT_HOST_URL, "/cgi-bin/draft/delete"),
    /**
     * 获取草稿列表
     */
    LIST_DRAFT(API_DEFAULT_HOST_URL, "/cgi-bin/draft/batchget"),
    /**
     * 获取草稿总数
     */
    COUNT_DRAFT(API_DEFAULT_HOST_URL, "/cgi-bin/draft/count");

    private final String prefix;
    private final String path;

  }

  /**
   * 发布能力:
   * 发布接口
   * 发布状态轮询接口
   * 事件推送发布结果 -- 是回调，没实现
   * 删除发布
   * 通过 article_id 获取已发布文章
   * 获取成功发布列表
   */
  @AllArgsConstructor
  @Getter
  enum FreePublish implements WxMpApiUrl {

    /**
     * 发布接口
     */
    SUBMIT(API_DEFAULT_HOST_URL, "/cgi-bin/freepublish/submit"),
    /**
     * 通过 article_id 获取已发布文章
     */
    GET_ARTICLE(API_DEFAULT_HOST_URL, "/cgi-bin/freepublish/getarticle"),
    /**
     * 发布状态轮询接口
     */
    GET_PUSH_STATUS(API_DEFAULT_HOST_URL, "/cgi-bin/freepublish/get"),
    /**
     * 删除发布
     */
    DEL_PUSH(API_DEFAULT_HOST_URL, "/cgi-bin/freepublish/delete"),
    /**
     * 获取成功发布列表
     */
    BATCH_GET(API_DEFAULT_HOST_URL, "/cgi-bin/freepublish/batchget")
    ;

    private final String prefix;
    private final String path;

  }
}
