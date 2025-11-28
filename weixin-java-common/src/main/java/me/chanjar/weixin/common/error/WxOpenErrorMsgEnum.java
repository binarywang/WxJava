package me.chanjar.weixin.common.error;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Map;

/**
 * <pre>
 *     微信开放平台全局返回码.
 *     参考文档：<a href="https://developers.weixin.qq.com/doc/oplatform/Return_codes/Return_code_descriptions_new.html">开放平台全局返回码</a>
 * </pre>
 *
 * @author Lam Jerry
 */

@Getter
public enum WxOpenErrorMsgEnum {
  /**
   * 系统繁忙，此时请开发者稍候再试  system error
   */
  CODE_1(-1, "系统繁忙，此时请开发者稍候再试"),

  /**
   * 请求成功  ok
   */
  CODE_0(0, "请求成功"),

  /**
   * POST参数非法
   */
  CODE_1003(1003, "POST参数非法"),

  /**
   * 商品id不存在
   */
  CODE_20002(20002, "商品id不存在"),

  /**
   * 获取 access_token 时 AppSecret 错误，或者 access_token 无效。请开发者认真比对 AppSecret  的正确性，或查看是否正在为恰当的公众号调用接口  invalid credential, access_token is invalid or not latest
   */
  CODE_40001(40001, "获取 access_token 时 AppSecret 错误，或者 access_token 无效。请开发者认真比对 AppSecret  的正确性，或查看是否正在为恰当的公众号调用接口"),

  /**
   * 不合法的凭证类型  invalid grant_type
   */
  CODE_40002(40002, "不合法的凭证类型"),

  /**
   * 不合法的 OpenID ，请开发者确认 OpenID （该用户）是否已关注公众号，或是否是其他公众号的 OpenID  invalid openid
   */
  CODE_40003(40003, "不合法的 OpenID ，请开发者确认 OpenID （该用户）是否已关注公众号，或是否是其他公众号的 OpenID"),

  /**
   * 不合法的媒体文件类型  invalid media type
   */
  CODE_40004(40004, "不合法的媒体文件类型"),

  /**
   * 上传素材文件格式不对  invalid file type
   */
  CODE_40005(40005, "上传素材文件格式不对"),

  /**
   * 上传素材文件大小超出限制  invalid meida size
   */
  CODE_40006(40006, "上传素材文件大小超出限制"),

  /**
   * 不合法的媒体文件 id  invalid media_id
   */
  CODE_40007(40007, "不合法的媒体文件 id"),

  /**
   * 不合法的消息类型  invalid message type
   */
  CODE_40008(40008, "不合法的消息类型"),

  /**
   * 图片尺寸太大  invalid image size
   */
  CODE_40009(40009, "图片尺寸太大"),

  /**
   * 不合法的语音文件大小  invalid voice size
   */
  CODE_40010(40010, "不合法的语音文件大小"),

  /**
   * 不合法的视频文件大小  invalid video size
   */
  CODE_40011(40011, "不合法的视频文件大小"),

  /**
   * 不合法的缩略图文件大小  invalid thumb size
   */
  CODE_40012(40012, "不合法的缩略图文件大小"),

  /**
   * 不合法的appid  invalid appid
   */
  CODE_40013(40013, "不合法的appid"),

  /**
   * 不合法的 access_token ，请开发者认真比对 access_token 的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口  invalid access_token
   */
  CODE_40014(40014, "不合法的 access_token ，请开发者认真比对 access_token 的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口"),

  /**
   * 不合法的菜单类型  invalid menu type
   */
  CODE_40015(40015, "不合法的菜单类型"),

  /**
   * 不合法的按钮个数  invalid button size
   */
  CODE_40016(40016, "不合法的按钮个数"),

  /**
   * 不合法的按钮类型  invalid button type
   */
  CODE_40017(40017, "不合法的按钮类型"),

  /**
   * 不合法的按钮名字长度  invalid button name size
   */
  CODE_40018(40018, "不合法的按钮名字长度"),

  /**
   * 不合法的按钮 KEY 长度  invalid button key size
   */
  CODE_40019(40019, "不合法的按钮 KEY 长度"),

  /**
   * 不合法的按钮 URL 长度  invalid button url size
   */
  CODE_40020(40020, "不合法的按钮 URL 长度"),

  /**
   * 不合法的菜单版本号  invalid menu version
   */
  CODE_40021(40021, "不合法的菜单版本号"),

  /**
   * 不合法的子菜单级数  invalid sub_menu level
   */
  CODE_40022(40022, "不合法的子菜单级数"),

  /**
   * 不合法的子菜单按钮个数  invalid sub button size
   */
  CODE_40023(40023, "不合法的子菜单按钮个数"),

  /**
   * 不合法的子菜单按钮类型  invalid sub button type
   */
  CODE_40024(40024, "不合法的子菜单按钮类型"),

  /**
   * 不合法的子菜单按钮名字长度  invalid sub button name size
   */
  CODE_40025(40025, "不合法的子菜单按钮名字长度"),

  /**
   * 不合法的子菜单按钮 KEY 长度  invalid sub button key size
   */
  CODE_40026(40026, "不合法的子菜单按钮 KEY 长度"),

  /**
   * 不合法的子菜单按钮 URL 长度  invalid sub button url size
   */
  CODE_40027(40027, "不合法的子菜单按钮 URL 长度"),

  /**
   * 不合法的自定义菜单使用用户  invalid menu api user
   */
  CODE_40028(40028, "不合法的自定义菜单使用用户"),

  /**
   * 无效的 oauth_code  invalid code
   */
  CODE_40029(40029, "无效的 oauth_code"),

  /**
   * 不合法的 refresh_token  invalid refresh_token
   */
  CODE_40030(40030, "不合法的 refresh_token"),

  /**
   * 不合法的 openid 列表  invalid openid list
   */
  CODE_40031(40031, "不合法的 openid 列表"),

  /**
   * 不合法的 openid 列表长度  invalid openid list size
   */
  CODE_40032(40032, "不合法的 openid 列表长度"),

  /**
   * 不合法的请求字符，不能包含 \\uxxxx 格式的字符  invalid charset. please check your request, if include \\uxxxx will create  fail!
   */
  CODE_40033(40033, "不合法的请求字符，不能包含 \\uxxxx 格式的字符"),

  /**
   * invalid template size
   */
  CODE_40034(40034, "invalid template size"),

  /**
   * 不合法的参数  invalid args size
   */
  CODE_40035(40035, "不合法的参数"),

  /**
   * 不合法的 template_id 长度  invalid template_id size
   */
  CODE_40036(40036, "不合法的 template_id 长度"),

  /**
   * 不合法的 template_id  invalid template_id
   */
  CODE_40037(40037, "不合法的 template_id"),

  /**
   * 不合法的请求格式  invalid packaging type
   */
  CODE_40038(40038, "不合法的请求格式"),

  /**
   * 不合法的 URL 长度  invalid url size
   */
  CODE_40039(40039, "不合法的 URL 长度"),

  /**
   * invalid plugin token
   */
  CODE_40040(40040, "invalid plugin token"),

  /**
   * invalid plugin id
   */
  CODE_40041(40041, "invalid plugin id"),

  /**
   * invalid plugin session
   */
  CODE_40042(40042, "invalid plugin session"),

  /**
   * invalid fav type
   */
  CODE_40043(40043, "invalid fav type"),

  /**
   * invalid size in link.title
   */
  CODE_40044(40044, "invalid size in link.title"),

  /**
   * invalid size in link.description
   */
  CODE_40045(40045, "invalid size in link.description"),

  /**
   * invalid size in link.iconurl
   */
  CODE_40046(40046, "invalid size in link.iconurl"),

  /**
   * invalid size in link.url
   */
  CODE_40047(40047, "invalid size in link.url"),

  /**
   * 无效的url  invalid url domain
   */
  CODE_40048(40048, "无效的url"),

  /**
   * invalid score report type
   */
  CODE_40049(40049, "invalid score report type"),

  /**
   * 不合法的分组 id  invalid timeline type
   */
  CODE_40050(40050, "不合法的分组 id"),

  /**
   * 分组名字不合法  invalid group name
   */
  CODE_40051(40051, "分组名字不合法"),

  /**
   * invalid action name
   */
  CODE_40052(40052, "invalid action name"),

  /**
   * invalid action info, please check document
   */
  CODE_40053(40053, "invalid action info, please check document"),

  /**
   * 不合法的子菜单按钮 url 域名  invalid sub button url domain
   */
  CODE_40054(40054, "不合法的子菜单按钮 url 域名"),

  /**
   * 不合法的菜单按钮 url 域名  invalid button url domain
   */
  CODE_40055(40055, "不合法的菜单按钮 url 域名"),

  /**
   * invalid serial code
   */
  CODE_40056(40056, "invalid serial code"),

  /**
   * invalid tabbar size
   */
  CODE_40057(40057, "invalid tabbar size"),

  /**
   * invalid tabbar name size
   */
  CODE_40058(40058, "invalid tabbar name size"),

  /**
   * invalid msg id
   */
  CODE_40059(40059, "invalid msg id"),

  /**
   * 删除单篇图文时，指定的 article_idx 不合法  invalid article idx
   */
  CODE_40060(40060, "删除单篇图文时，指定的 article_idx 不合法"),

  /**
   * invalid title size
   */
  CODE_40062(40062, "invalid title size"),

  /**
   * invalid message_ext size
   */
  CODE_40063(40063, "invalid message_ext size"),

  /**
   * invalid app type
   */
  CODE_40064(40064, "invalid app type"),

  /**
   * invalid msg status
   */
  CODE_40065(40065, "invalid msg status"),

  /**
   * 不合法的 url ，递交的页面被sitemap标记为拦截  invalid url
   */
  CODE_40066(40066, "不合法的 url ，递交的页面被sitemap标记为拦截"),

  /**
   * invalid tvid
   */
  CODE_40067(40067, "invalid tvid"),

  /**
   * contain mailcious url
   */
  CODE_40068(40068, "contain mailcious url"),

  /**
   * invalid hardware type
   */
  CODE_40069(40069, "invalid hardware type"),

  /**
   * invalid sku info
   */
  CODE_40070(40070, "invalid sku info"),

  /**
   * invalid card type
   */
  CODE_40071(40071, "invalid card type"),

  /**
   * invalid location id
   */
  CODE_40072(40072, "invalid location id"),

  /**
   * invalid card id
   */
  CODE_40073(40073, "invalid card id"),

  /**
   * invalid pay template id
   */
  CODE_40074(40074, "invalid pay template id"),

  /**
   * invalid encrypt code
   */
  CODE_40075(40075, "invalid encrypt code"),

  /**
   * invalid color id
   */
  CODE_40076(40076, "invalid color id"),

  /**
   * invalid score type
   */
  CODE_40077(40077, "invalid score type"),

  /**
   * invalid card status
   */
  CODE_40078(40078, "invalid card status"),

  /**
   * invalid time
   */
  CODE_40079(40079, "invalid time"),

  /**
   * invalid card ext
   */
  CODE_40080(40080, "invalid card ext"),

  /**
   * invalid template_id
   */
  CODE_40081(40081, "invalid template_id"),

  /**
   * invalid banner picture size
   */
  CODE_40082(40082, "invalid banner picture size"),

  /**
   * invalid banner url size
   */
  CODE_40083(40083, "invalid banner url size"),

  /**
   * invalid button desc size
   */
  CODE_40084(40084, "invalid button desc size"),

  /**
   * invalid button url size
   */
  CODE_40085(40085, "invalid button url size"),

  /**
   * invalid sharelink logo size
   */
  CODE_40086(40086, "invalid sharelink logo size"),

  /**
   * invalid sharelink desc size
   */
  CODE_40087(40087, "invalid sharelink desc size"),

  /**
   * invalid sharelink title size
   */
  CODE_40088(40088, "invalid sharelink title size"),

  /**
   * invalid platform id
   */
  CODE_40089(40089, "invalid platform id"),

  /**
   * invalid request source (bad client ip)
   */
  CODE_40090(40090, "invalid request source (bad client ip)"),

  /**
   * invalid component ticket
   */
  CODE_40091(40091, "invalid component ticket"),

  /**
   * invalid remark name
   */
  CODE_40092(40092, "invalid remark name"),

  /**
   * not completely ok, err_item will  return location_id=-1,check your required_fields in json.
   */
  CODE_40093(40093, "not completely ok, err_item will  return location_id=-1,check your required_fields in json."),

  /**
   * invalid component credential
   */
  CODE_40094(40094, "invalid component credential"),

  /**
   * bad source of caller
   */
  CODE_40095(40095, "bad source of caller"),

  /**
   * invalid biztype
   */
  CODE_40096(40096, "invalid biztype"),

  /**
   * 参数错误  invalid args
   */
  CODE_40097(40097, "参数错误"),

  /**
   * invalid poiid
   */
  CODE_40098(40098, "invalid poiid"),

  /**
   * invalid code, this code has consumed.
   */
  CODE_40099(40099, "invalid code, this code has consumed."),

  /**
   * invalid DateInfo, Make Sure  OldDateInfoType==NewDateInfoType && NewBeginTime<=OldBeginTime  && OldEndTime<= NewEndTime
   */
  CODE_40100(40100, "invalid DateInfo, Make Sure  OldDateInfoType==NewDateInfoType && NewBeginTime<=OldBeginTime  && OldEndTime<= NewEndTime"),

  /**
   * missing parameter
   */
  CODE_40101(40101, "missing parameter"),

  /**
   * invalid industry id
   */
  CODE_40102(40102, "invalid industry id"),

  /**
   * invalid industry index
   */
  CODE_40103(40103, "invalid industry index"),

  /**
   * invalid category id
   */
  CODE_40104(40104, "invalid category id"),

  /**
   * invalid view type
   */
  CODE_40105(40105, "invalid view type"),

  /**
   * invalid user name
   */
  CODE_40106(40106, "invalid user name"),

  /**
   * invalid card id! 1,card status must  verify ok; 2,this card must have location_id
   */
  CODE_40107(40107, "invalid card id! 1,card status must  verify ok; 2,this card must have location_id"),

  /**
   * invalid client version
   */
  CODE_40108(40108, "invalid client version"),

  /**
   * too many code size, must <= 100
   */
  CODE_40109(40109, "too many code size, must <= 100"),

  /**
   * have empty code
   */
  CODE_40110(40110, "have empty code"),

  /**
   * have same code
   */
  CODE_40111(40111, "have same code"),

  /**
   * can not set bind openid
   */
  CODE_40112(40112, "can not set bind openid"),

  /**
   * unsupported file type
   */
  CODE_40113(40113, "unsupported file type"),

  /**
   * invalid index value
   */
  CODE_40114(40114, "invalid index value"),

  /**
   * invalid session from
   */
  CODE_40115(40115, "invalid session from"),

  /**
   * invalid code
   */
  CODE_40116(40116, "invalid code"),

  /**
   * 分组名字不合法  invalid button media_id size
   */
  CODE_40117(40117, "分组名字不合法"),

  /**
   * media_id 大小不合法  invalid sub button media_id size
   */
  CODE_40118(40118, "media_id 大小不合法"),

  /**
   * button 类型错误  invalid use button type
   */
  CODE_40119(40119, "button 类型错误"),

  /**
   * 子 button 类型错误  invalid use sub button type
   */
  CODE_40120(40120, "子 button 类型错误"),

  /**
   * 不合法的 media_id 类型  invalid media type in view_limited
   */
  CODE_40121(40121, "不合法的 media_id 类型"),

  /**
   * invalid card quantity
   */
  CODE_40122(40122, "invalid card quantity"),

  /**
   * invalid task_id
   */
  CODE_40123(40123, "invalid task_id"),

  /**
   * too many custom field!
   */
  CODE_40124(40124, "too many custom field!"),

  /**
   * 不合法的  AppID ，请开发者检查 AppID 的正确性，避免异常字符，注意大小写  invalid  appsecret
   */
  CODE_40125(40125, "不合法的  AppID ，请开发者检查 AppID 的正确性，避免异常字符，注意大小写"),

  /**
   * invalid text size
   */
  CODE_40126(40126, "invalid text size"),

  /**
   * invalid user-card status! Hint: the  card was given to user, but may be deleted or expired or set unavailable !
   */
  CODE_40127(40127, "invalid user-card status! Hint: the  card was given to user, but may be deleted or expired or set unavailable !"),

  /**
   * invalid media id! must be uploaded  by api(cgi-bin/material/add_material)
   */
  CODE_40128(40128, "invalid media id! must be uploaded  by api(cgi-bin/material/add_material)"),

  /**
   * invalid scene
   */
  CODE_40129(40129, "invalid scene"),

  /**
   * invalid openid list size, at least two openid
   */
  CODE_40130(40130, "invalid openid list size, at least two openid"),

  /**
   * out of limit of ticket
   */
  CODE_40131(40131, "out of limit of ticket"),

  /**
   * 微信号不合法  invalid username
   */
  CODE_40132(40132, "微信号不合法"),

  /**
   * invalid encryt data
   */
  CODE_40133(40133, "invalid encryt data"),

  /**
   * invalid not supply bonus, can not  change card_id which supply bonus to be not supply
   */
  CODE_40135(40135, "invalid not supply bonus, can not  change card_id which supply bonus to be not supply"),

  /**
   * invalid use DepositCodeMode, make  sure sku.quantity>DepositCode.quantity
   */
  CODE_40136(40136, "invalid use DepositCodeMode, make  sure sku.quantity>DepositCode.quantity"),

  /**
   * 不支持的图片格式  invalid image format
   */
  CODE_40137(40137, "不支持的图片格式"),

  /**
   * emphasis word can not be first neither remark
   */
  CODE_40138(40138, "emphasis word can not be first neither remark"),

  /**
   * invalid sub merchant id
   */
  CODE_40139(40139, "invalid sub merchant id"),

  /**
   * invalid sub merchant status
   */
  CODE_40140(40140, "invalid sub merchant status"),

  /**
   * invalid image url
   */
  CODE_40141(40141, "invalid image url"),

  /**
   * invalid sharecard parameters
   */
  CODE_40142(40142, "invalid sharecard parameters"),

  /**
   * invalid least cost info, should be 0
   */
  CODE_40143(40143, "invalid least cost info, should be 0"),

  /**
   * 1)maybe share_card_list.num or  consume_share_self_num too big; 2)maybe card_id_list also has  self-card_id;3)maybe card_id_list has many different card_id;4)maybe both  consume_share_self_num and share_card_list.num bigger than 0
   */
  CODE_40144(40144, "1)maybe share_card_list.num or  consume_share_self_num too big; 2)maybe card_id_list also has  self-card_id;3)maybe card_id_list has many different card_id;4)maybe both  consume_share_self_num and share_card_list.num bigger than 0"),

  /**
   * invalid update! Can not both set  PayCell and CenterCellInfo(include: center_title, center_sub_title,  center_url).
   */
  CODE_40145(40145, "invalid update! Can not both set  PayCell and CenterCellInfo(include: center_title, center_sub_title,  center_url)."),

  /**
   * invalid openid! card may be marked by other user!
   */
  CODE_40146(40146, "invalid openid! card may be marked by other user!"),

  /**
   * invalid consume! Consume time overranging restricts.
   */
  CODE_40147(40147, "invalid consume! Consume time overranging restricts."),

  /**
   * invalid friends card type
   */
  CODE_40148(40148, "invalid friends card type"),

  /**
   * invalid use time limit
   */
  CODE_40149(40149, "invalid use time limit"),

  /**
   * invalid card parameters
   */
  CODE_40150(40150, "invalid card parameters"),

  /**
   * invalid card info, text/pic hit antispam
   */
  CODE_40151(40151, "invalid card info, text/pic hit antispam"),

  /**
   * invalid group id
   */
  CODE_40152(40152, "invalid group id"),

  /**
   * self consume cell for friends card must need verify code
   */
  CODE_40153(40153, "self consume cell for friends card must need verify code"),

  /**
   * invalid voip parameters
   */
  CODE_40154(40154, "invalid voip parameters"),

  /**
   * 请勿添加其他公众号的主页链接  please don't contain other home page url
   */
  CODE_40155(40155, "请勿添加其他公众号的主页链接"),

  /**
   * invalid face recognize parameters
   */
  CODE_40156(40156, "invalid face recognize parameters"),

  /**
   * invalid picture, has no face
   */
  CODE_40157(40157, "invalid picture, has no face"),

  /**
   * invalid use_custom_code, need be false
   */
  CODE_40158(40158, "invalid use_custom_code, need be false"),

  /**
   * invalid length for path, or the data is not json string
   */
  CODE_40159(40159, "invalid length for path, or the data is not json string"),

  /**
   * invalid image file
   */
  CODE_40160(40160, "invalid image file"),

  /**
   * image file not match
   */
  CODE_40161(40161, "image file not match"),

  /**
   * invalid lifespan
   */
  CODE_40162(40162, "invalid lifespan"),

  /**
   * oauth_code已使用  code been used
   */
  CODE_40163(40163, "oauth_code已使用"),

  /**
   * invalid ip, not in whitelist
   */
  CODE_40164(40164, "invalid ip, not in whitelist"),

  /**
   * invalid weapp pagepath
   */
  CODE_40165(40165, "invalid weapp pagepath"),

  /**
   * invalid weapp appid
   */
  CODE_40166(40166, "invalid weapp appid"),

  /**
   * there is no relation with plugin appid
   */
  CODE_40167(40167, "there is no relation with plugin appid"),

  /**
   * unlinked weapp card
   */
  CODE_40168(40168, "unlinked weapp card"),

  /**
   * invalid length for scene, or the data is not json string
   */
  CODE_40169(40169, "invalid length for scene, or the data is not json string"),

  /**
   * args count exceed count limit
   */
  CODE_40170(40170, "args count exceed count limit"),

  /**
   * product id can not empty and the length cannot exceed 32
   */
  CODE_40171(40171, "product id can not empty and the length cannot exceed 32"),

  /**
   * can not have same product id
   */
  CODE_40172(40172, "can not have same product id"),

  /**
   * there is no bind relation
   */
  CODE_40173(40173, "there is no bind relation"),

  /**
   * not card user
   */
  CODE_40174(40174, "not card user"),

  /**
   * invalid material id
   */
  CODE_40175(40175, "invalid material id"),

  /**
   * invalid template id
   */
  CODE_40176(40176, "invalid template id"),

  /**
   * invalid product id
   */
  CODE_40177(40177, "invalid product id"),

  /**
   * invalid sign
   */
  CODE_40178(40178, "invalid sign"),

  /**
   * Function is adjusted, rules are not  allowed to add or update
   */
  CODE_40179(40179, "Function is adjusted, rules are not  allowed to add or update"),

  /**
   * invalid client tmp token
   */
  CODE_40180(40180, "invalid client tmp token"),

  /**
   * invalid opengid
   */
  CODE_40181(40181, "invalid opengid"),

  /**
   * invalid pack_id
   */
  CODE_40182(40182, "invalid pack_id"),

  /**
   * invalid product_appid, product_appid  should bind with wxa_appid
   */
  CODE_40183(40183, "invalid product_appid, product_appid  should bind with wxa_appid"),

  /**
   * invalid url path
   */
  CODE_40184(40184, "invalid url path"),

  /**
   * invalid auth_token, or auth_token is expired
   */
  CODE_40185(40185, "invalid auth_token, or auth_token is expired"),

  /**
   * invalid delegate
   */
  CODE_40186(40186, "invalid delegate"),

  /**
   * invalid ip
   */
  CODE_40187(40187, "invalid ip"),

  /**
   * invalid scope
   */
  CODE_40188(40188, "invalid scope"),

  /**
   * invalid width
   */
  CODE_40189(40189, "invalid width"),

  /**
   * invalid delegate time
   */
  CODE_40190(40190, "invalid delegate time"),

  /**
   * invalid pic_url
   */
  CODE_40191(40191, "invalid pic_url"),

  /**
   * invalid author in news
   */
  CODE_40192(40192, "invalid author in news"),

  /**
   * invalid recommend length
   */
  CODE_40193(40193, "invalid recommend length"),

  /**
   * illegal recommend
   */
  CODE_40194(40194, "illegal recommend"),

  /**
   * invalid show_num
   */
  CODE_40195(40195, "invalid show_num"),

  /**
   * invalid smartmsg media_id
   */
  CODE_40196(40196, "invalid smartmsg media_id"),

  /**
   * invalid smartmsg media num
   */
  CODE_40197(40197, "invalid smartmsg media num"),

  /**
   * invalid default msg article size,  must be same as show_num
   */
  CODE_40198(40198, "invalid default msg article size,  must be same as show_num"),

  /**
   * 运单 ID 不存在，未查到运单  waybill_id not found
   */
  CODE_40199(40199, "运单 ID 不存在，未查到运单"),

  /**
   * invalid account type
   */
  CODE_40200(40200, "invalid account type"),

  /**
   * invalid check url
   */
  CODE_40201(40201, "invalid check url"),

  /**
   * invalid check action
   */
  CODE_40202(40202, "invalid check action"),

  /**
   * invalid check operator
   */
  CODE_40203(40203, "invalid check operator"),

  /**
   * can not delete wash or rumor article
   */
  CODE_40204(40204, "can not delete wash or rumor article"),

  /**
   * invalid check keywords string
   */
  CODE_40205(40205, "invalid check keywords string"),

  /**
   * invalid check begin stamp
   */
  CODE_40206(40206, "invalid check begin stamp"),

  /**
   * invalid check alive seconds
   */
  CODE_40207(40207, "invalid check alive seconds"),

  /**
   * invalid check notify id
   */
  CODE_40208(40208, "invalid check notify id"),

  /**
   * invalid check notify msg
   */
  CODE_40209(40209, "invalid check notify msg"),

  /**
   * pages 中的path参数不存在或为空  invalid check wxa path
   */
  CODE_40210(40210, "pages 中的path参数不存在或为空"),

  /**
   * invalid scope_data
   */
  CODE_40211(40211, "invalid scope_data"),

  /**
   * paegs 当中存在不合法的query，query格式遵循URL标准，即k1=v1&k2=v2  invalid query
   */
  CODE_40212(40212, "paegs 当中存在不合法的query，query格式遵循URL标准，即k1=v1&k2=v2"),

  /**
   * invalid href tag
   */
  CODE_40213(40213, "invalid href tag"),

  /**
   * invalid href text
   */
  CODE_40214(40214, "invalid href text"),

  /**
   * invalid image count
   */
  CODE_40215(40215, "invalid image count"),

  /**
   * invalid desc
   */
  CODE_40216(40216, "invalid desc"),

  /**
   * invalid video count
   */
  CODE_40217(40217, "invalid video count"),

  /**
   * invalid video id
   */
  CODE_40218(40218, "invalid video id"),

  /**
   * pages不存在或者参数为空  pages is empty
   */
  CODE_40219(40219, "pages不存在或者参数为空"),

  /**
   * data_list is empty
   */
  CODE_40220(40220, "data_list is empty"),

  /**
   * invalid Content-Encoding
   */
  CODE_40221(40221, "invalid Content-Encoding"),

  /**
   * invalid request idc domain
   */
  CODE_40222(40222, "invalid request idc domain"),

  /**
   * empty media cover, please check the media
   */
  CODE_40229(40229, "媒体封面为空，请添加媒体封面"),

  /**
   * 缺少 access_token 参数  access_token missing
   */
  CODE_41001(41001, "缺少 access_token 参数"),

  /**
   * 缺少 appid 参数  appid missing
   */
  CODE_41002(41002, "缺少 appid 参数"),

  /**
   * 缺少 refresh_token 参数  refresh_token missing
   */
  CODE_41003(41003, "缺少 refresh_token 参数"),

  /**
   * 缺少 secret 参数  appsecret missing
   */
  CODE_41004(41004, "缺少 secret 参数"),

  /**
   * 缺少多媒体文件数据，传输素材无视频或图片内容  media data missing
   */
  CODE_41005(41005, "缺少多媒体文件数据，传输素材无视频或图片内容"),

  /**
   * 缺少 media_id 参数  media_id missing
   */
  CODE_41006(41006, "缺少 media_id 参数"),

  /**
   * 缺少子菜单数据  sub_menu data missing
   */
  CODE_41007(41007, "缺少子菜单数据"),

  /**
   * 缺少 oauth code  missing code
   */
  CODE_41008(41008, "缺少 oauth code"),

  /**
   * 缺少 openid  missing openid
   */
  CODE_41009(41009, "缺少 openid"),

  /**
   * 缺失 url 参数  missing url
   */
  CODE_41010(41010, "缺失 url 参数"),

  /**
   * missing required fields! please  check document and request json!
   */
  CODE_41011(41011, "missing required fields! please  check document and request json!"),

  /**
   * missing card id
   */
  CODE_41012(41012, "missing card id"),

  /**
   * missing code
   */
  CODE_41013(41013, "missing code"),

  /**
   * missing ticket_class
   */
  CODE_41014(41014, "missing ticket_class"),

  /**
   * missing show_time
   */
  CODE_41015(41015, "missing show_time"),

  /**
   * missing screening_room
   */
  CODE_41016(41016, "missing screening_room"),

  /**
   * missing seat_number
   */
  CODE_41017(41017, "missing seat_number"),

  /**
   * missing component_appid
   */
  CODE_41018(41018, "missing component_appid"),

  /**
   * missing platform_secret
   */
  CODE_41019(41019, "missing platform_secret"),

  /**
   * missing platform_ticket
   */
  CODE_41020(41020, "missing platform_ticket"),

  /**
   * missing component_access_token
   */
  CODE_41021(41021, "missing component_access_token"),

  /**
   * missing "display" field
   */
  CODE_41024(41024, "missing \"display\" field"),

  /**
   * poi_list empty
   */
  CODE_41025(41025, "poi_list empty"),

  /**
   * missing image list info, text maybe empty
   */
  CODE_41026(41026, "missing image list info, text maybe empty"),

  /**
   * missing voip call key
   */
  CODE_41027(41027, "missing voip call key"),

  /**
   * invalid form id
   */
  CODE_41028(41028, "invalid form id"),

  /**
   * form id used count reach limit
   */
  CODE_41029(41029, "form id used count reach limit"),

  /**
   * page路径不正确，需要保证在现网版本小程序中存在，与app.json保持一致  invalid page
   */
  CODE_41030(41030, "page路径不正确，需要保证在现网版本小程序中存在，与app.json保持一致"),

  /**
   * the form id have been blocked!
   */
  CODE_41031(41031, "the form id have been blocked!"),

  /**
   * not allow to send message with  submitted form id, for punishment
   */
  CODE_41032(41032, "not allow to send message with  submitted form id, for punishment"),

  /**
   * 只允许通过api创建的小程序使用  invaid register type
   */
  CODE_41033(41033, "只允许通过api创建的小程序使用"),

  /**
   * not allow to send message with  submitted form id, for punishment
   */
  CODE_41034(41034, "not allow to send message with  submitted form id, for punishment"),

  /**
   * not allow to send message with prepay id, for punishment
   */
  CODE_41035(41035, "not allow to send message with prepay id, for punishment"),

  /**
   * appid ad cid
   */
  CODE_41036(41036, "appid ad cid"),

  /**
   * appid ad_mch_appid
   */
  CODE_41037(41037, "appid ad_mch_appid"),

  /**
   * appid pos_type
   */
  CODE_41038(41038, "appid pos_type"),

  /**
   * access_token 超时，请检查 access_token 的有效期，请参考基础支持 - 获取 access_token 中，对  access_token 的详细机制说明  access_token expired
   */
  CODE_42001(42001, "access_token 超时，请检查 access_token 的有效期，请参考基础支持 - 获取 access_token 中，对  access_token 的详细机制说明"),

  /**
   * refresh_token 超时  refresh_token expired
   */
  CODE_42002(42002, "refresh_token 超时"),

  /**
   * oauth_code 超时  code expired
   */
  CODE_42003(42003, "oauth_code 超时"),

  /**
   * plugin token expired
   */
  CODE_42004(42004, "plugin token expired"),

  /**
   * api usage expired
   */
  CODE_42005(42005, "api usage expired"),

  /**
   * component_access_token expired
   */
  CODE_42006(42006, "component_access_token expired"),

  /**
   * 用户修改微信密码， accesstoken 和 refreshtoken 失效，需要重新授权  access_token and refresh_token exception
   */
  CODE_42007(42007, "用户修改微信密码， accesstoken 和 refreshtoken 失效，需要重新授权"),

  /**
   * voip call key expired
   */
  CODE_42008(42008, "voip call key expired"),

  /**
   * client tmp token expired
   */
  CODE_42009(42009, "client tmp token expired"),

  /**
   * 需要 GET 请求  require GET method
   */
  CODE_43001(43001, "需要 GET 请求"),

  /**
   * 需要 POST 请求  require POST method
   */
  CODE_43002(43002, "需要 POST 请求"),

  /**
   * 需要 HTTPS 请求  require https
   */
  CODE_43003(43003, "需要 HTTPS 请求"),

  /**
   * 需要接收者关注  require subscribe
   */
  CODE_43004(43004, "需要接收者关注"),

  /**
   * 需要好友关系  require friend relations
   */
  CODE_43005(43005, "需要好友关系"),

  /**
   * require not block
   */
  CODE_43006(43006, "require not block"),

  /**
   * require bizuser authorize
   */
  CODE_43007(43007, "require bizuser authorize"),

  /**
   * require biz pay auth
   */
  CODE_43008(43008, "require biz pay auth"),

  /**
   * can not use custom code, need authorize
   */
  CODE_43009(43009, "can not use custom code, need authorize"),

  /**
   * can not use balance, need authorize
   */
  CODE_43010(43010, "can not use balance, need authorize"),

  /**
   * can not use bonus, need authorize
   */
  CODE_43011(43011, "can not use bonus, need authorize"),

  /**
   * can not use custom url, need authorize
   */
  CODE_43012(43012, "can not use custom url, need authorize"),

  /**
   * can not use shake card, need authorize
   */
  CODE_43013(43013, "can not use shake card, need authorize"),

  /**
   * require check agent
   */
  CODE_43014(43014, "require check agent"),

  /**
   * require authorize by wechat team to use this function!
   */
  CODE_43015(43015, "require authorize by wechat team to use this function!"),

  /**
   * 小程序未认证  require verify
   */
  CODE_43016(43016, "小程序未认证"),

  /**
   * require location id!
   */
  CODE_43017(43017, "require location id!"),

  /**
   * code has no been mark!
   */
  CODE_43018(43018, "code has no been mark!"),

  /**
   * 需要将接收者从黑名单中移除  require remove blacklist
   */
  CODE_43019(43019, "需要将接收者从黑名单中移除"),

  /**
   * change template too frequently
   */
  CODE_43100(43100, "change template too frequently"),

  /**
   * 用户拒绝接受消息，如果用户之前曾经订阅过，则表示用户取消了订阅关系  user refuse to accept the msg
   */
  CODE_43101(43101, "用户拒绝接受消息，如果用户之前曾经订阅过，则表示用户取消了订阅关系"),

  /**
   * the tempalte is not subscriptiontype
   */
  CODE_43102(43102, "the tempalte is not subscriptiontype"),

  /**
   * the api only can cancel the subscription
   */
  CODE_43103(43103, "the api only can cancel the subscription"),

  /**
   * this appid does not have permission
   */
  CODE_43104(43104, "this appid does not have permission"),

  /**
   * news has no binding relation with template_id
   */
  CODE_43105(43105, "news has no binding relation with template_id"),

  /**
   * not allow to add template, for punishment
   */
  CODE_43106(43106, "not allow to add template, for punishment"),

  /**
   * 多媒体文件为空  empty media data
   */
  CODE_44001(44001, "多媒体文件为空"),

  /**
   * POST 的数据包为空  empty post data
   */
  CODE_44002(44002, "POST 的数据包为空"),

  /**
   * 图文消息内容为空  empty news data
   */
  CODE_44003(44003, "图文消息内容为空"),

  /**
   * 文本消息内容为空  empty content
   */
  CODE_44004(44004, "文本消息内容为空"),

  /**
   * 空白的列表  empty list size
   */
  CODE_44005(44005, "空白的列表"),

  /**
   * empty file data
   */
  CODE_44006(44006, "empty file data"),

  /**
   * repeated msg id
   */
  CODE_44007(44007, "repeated msg id"),

  /**
   * image url size out of limit
   */
  CODE_44997(44997, "image url size out of limit"),

  /**
   * keyword string media size out of limit
   */
  CODE_44998(44998, "keyword string media size out of limit"),

  /**
   * keywords list size out of limit
   */
  CODE_44999(44999, "keywords list size out of limit"),

  /**
   * msg_id size out of limit
   */
  CODE_45000(45000, "msg_id size out of limit"),

  /**
   * 多媒体文件大小超过限制  media size out of limit
   */
  CODE_45001(45001, "多媒体文件大小超过限制"),

  /**
   * 消息内容超过限制  content size out of limit
   */
  CODE_45002(45002, "消息内容超过限制"),

  /**
   * 标题字段超过限制  title size out of limit
   */
  CODE_45003(45003, "标题字段超过限制"),

  /**
   * 描述字段超过限制  description size out of limit
   */
  CODE_45004(45004, "描述字段超过限制"),

  /**
   * 链接字段超过限制  url size out of limit
   */
  CODE_45005(45005, "链接字段超过限制"),

  /**
   * 图片链接字段超过限制  picurl size out of limit
   */
  CODE_45006(45006, "图片链接字段超过限制"),

  /**
   * 语音播放时间超过限制  playtime out of limit
   */
  CODE_45007(45007, "语音播放时间超过限制"),

  /**
   * 图文消息超过限制  article size out of limit
   */
  CODE_45008(45008, "图文消息超过限制"),

  /**
   * 接口调用超过限制  reach max api daily quota limit
   */
  CODE_45009(45009, "接口调用超过限制"),

  /**
   * 创建菜单个数超过限制  create menu limit
   */
  CODE_45010(45010, "创建菜单个数超过限制"),

  /**
   * API 调用太频繁，请稍候再试  api minute-quota reach limit, must slower, retry next minute
   */
  CODE_45011(45011, "API 调用太频繁，请稍候再试"),

  /**
   * 模板大小超过限制  template size out of limit
   */
  CODE_45012(45012, "模板大小超过限制"),

  /**
   * too many template args
   */
  CODE_45013(45013, "too many template args"),

  /**
   * template message size out of limit
   */
  CODE_45014(45014, "template message size out of limit"),

  /**
   * 回复时间超过限制  response out of time limit or subscription is canceled
   */
  CODE_45015(45015, "回复时间超过限制"),

  /**
   * 系统分组，不允许修改  can't modify sys group
   */
  CODE_45016(45016, "系统分组，不允许修改"),

  /**
   * 分组名字过长  can't set group name too long sys group
   */
  CODE_45017(45017, "分组名字过长"),

  /**
   * 分组数量超过上限  too many group now, no need to add new
   */
  CODE_45018(45018, "分组数量超过上限"),

  /**
   * too many openid, please input less
   */
  CODE_45019(45019, "too many openid, please input less"),

  /**
   * too many image, please input less
   */
  CODE_45020(45020, "too many image, please input less"),

  /**
   * some argument may be out of length  limit! please check document and request json!
   */
  CODE_45021(45021, "some argument may be out of length  limit! please check document and request json!"),

  /**
   * bonus is out of limit
   */
  CODE_45022(45022, "bonus is out of limit"),

  /**
   * balance is out of limit
   */
  CODE_45023(45023, "balance is out of limit"),

  /**
   * rank template number is out of limit
   */
  CODE_45024(45024, "rank template number is out of limit"),

  /**
   * poiid count is out of limit
   */
  CODE_45025(45025, "poiid count is out of limit"),

  /**
   * template num exceeds limit
   */
  CODE_45026(45026, "template num exceeds limit"),

  /**
   * template conflict with industry
   */
  CODE_45027(45027, "template conflict with industry"),

  /**
   * has no masssend quota
   */
  CODE_45028(45028, "has no masssend quota"),

  /**
   * qrcode count out of limit
   */
  CODE_45029(45029, "qrcode count out of limit"),

  /**
   * limit cardid, not support this function
   */
  CODE_45030(45030, "limit cardid, not support this function"),

  /**
   * stock is out of limit
   */
  CODE_45031(45031, "stock is out of limit"),

  /**
   * not inner ip for special acct in white-list
   */
  CODE_45032(45032, "not inner ip for special acct in white-list"),

  /**
   * user get card num is out of get_limit
   */
  CODE_45033(45033, "user get card num is out of get_limit"),

  /**
   * media file count is out of limit
   */
  CODE_45034(45034, "media file count is out of limit"),

  /**
   * access clientip is not registered, not in ip-white-list
   */
  CODE_45035(45035, "access clientip is not registered, not in ip-white-list"),

  /**
   * User receive announcement limit
   */
  CODE_45036(45036, "User receive announcement limit"),

  /**
   * user out of time limit or never talked in tempsession
   */
  CODE_45037(45037, "user out of time limit or never talked in tempsession"),

  /**
   * user subscribed, cannot use tempsession api
   */
  CODE_45038(45038, "user subscribed, cannot use tempsession api"),

  /**
   * card_list_size out of limit
   */
  CODE_45039(45039, "card_list_size out of limit"),

  /**
   * reach max monthly quota limit
   */
  CODE_45040(45040, "reach max monthly quota limit"),

  /**
   * this card reach total sku quantity limit!
   */
  CODE_45041(45041, "this card reach total sku quantity limit!"),

  /**
   * limit card type, this card type can  NOT create by sub merchant
   */
  CODE_45042(45042, "limit card type, this card type can  NOT create by sub merchant"),

  /**
   * can not set share_friends=true  because has no Abstract Or Text_Img_List has no img Or image url not valid
   */
  CODE_45043(45043, "can not set share_friends=true  because has no Abstract Or Text_Img_List has no img Or image url not valid"),

  /**
   * icon url size in abstract is out of limit
   */
  CODE_45044(45044, "icon url size in abstract is out of limit"),

  /**
   * unauthorized friends card, please contact administrator
   */
  CODE_45045(45045, "unauthorized friends card, please contact administrator"),

  /**
   * operate field conflict, CenterCell,  PayCell, SelfConsumeCell conflict
   */
  CODE_45046(45046, "operate field conflict, CenterCell,  PayCell, SelfConsumeCell conflict"),

  /**
   * 客服接口下行条数超过上限  out of response count limit
   */
  CODE_45047(45047, "客服接口下行条数超过上限"),

  /**
   * menu use invalid type
   */
  CODE_45048(45048, "menu use invalid type"),

  /**
   * ivr use invalid type
   */
  CODE_45049(45049, "ivr use invalid type"),

  /**
   * custom msg use invalid type
   */
  CODE_45050(45050, "custom msg use invalid type"),

  /**
   * template msg use invalid link
   */
  CODE_45051(45051, "template msg use invalid link"),

  /**
   * masssend msg use invalid type
   */
  CODE_45052(45052, "masssend msg use invalid type"),

  /**
   * exceed consume verify code size
   */
  CODE_45053(45053, "exceed consume verify code size"),

  /**
   * below consume verify code size
   */
  CODE_45054(45054, "below consume verify code size"),

  /**
   * the code is not in consume verify code charset
   */
  CODE_45055(45055, "the code is not in consume verify code charset"),

  /**
   * too many tag now, no need to add new
   */
  CODE_45056(45056, "too many tag now, no need to add new"),

  /**
   * can't delete the tag that has too many fans
   */
  CODE_45057(45057, "can't delete the tag that has too many fans"),

  /**
   * can't modify sys tag
   */
  CODE_45058(45058, "can't modify sys tag"),

  /**
   * can not tagging one user too much
   */
  CODE_45059(45059, "can not tagging one user too much"),

  /**
   * media is applied in ivr or menu, can not be deleted
   */
  CODE_45060(45060, "media is applied in ivr or menu, can not be deleted"),

  /**
   * maybe the update frequency is too often, please try again
   */
  CODE_45061(45061, "maybe the update frequency is too often, please try again"),

  /**
   * has agreement ad. please use mp.weixin.qq.com
   */
  CODE_45062(45062, "has agreement ad. please use mp.weixin.qq.com"),

  /**
   * accesstoken is not xiaochengxu
   */
  CODE_45063(45063, "accesstoken is not xiaochengxu"),

  /**
   * 创建菜单包含未关联的小程序  no permission to use weapp in menu
   */
  CODE_45064(45064, "创建菜单包含未关联的小程序"),

  /**
   * 相同 clientmsgid 已存在群发记录，返回数据中带有已存在的群发任务的 msgid  clientmsgid exist
   */
  CODE_45065(45065, "相同 clientmsgid 已存在群发记录，返回数据中带有已存在的群发任务的 msgid"),

  /**
   * 相同 clientmsgid 重试速度过快，请间隔1分钟重试  same clientmsgid retry too fast
   */
  CODE_45066(45066, "相同 clientmsgid 重试速度过快，请间隔1分钟重试"),

  /**
   * clientmsgid 长度超过限制  clientmsgid size out of limit
   */
  CODE_45067(45067, "clientmsgid 长度超过限制"),

  /**
   * file size out of limit
   */
  CODE_45068(45068, "file size out of limit"),

  /**
   * product list size out of limit
   */
  CODE_45069(45069, "product list size out of limit"),

  /**
   * the business account have been created
   */
  CODE_45070(45070, "the business account have been created"),

  /**
   * business account not found
   */
  CODE_45071(45071, "business account not found"),

  /**
   * command字段取值不对  invalid command
   */
  CODE_45072(45072, "command字段取值不对"),

  /**
   * not inner vip for sns in white list
   */
  CODE_45073(45073, "not inner vip for sns in white list"),

  /**
   * material list size out of limit, you  should delete the useless material
   */
  CODE_45074(45074, "material list size out of limit, you  should delete the useless material"),

  /**
   * invalid keyword id
   */
  CODE_45075(45075, "invalid keyword id"),

  /**
   * invalid count
   */
  CODE_45076(45076, "invalid count"),

  /**
   * number of business account reach limit
   */
  CODE_45077(45077, "number of business account reach limit"),

  /**
   * nickname is illegal!
   */
  CODE_45078(45078, "nickname is illegal!"),

  /**
   * nickname is forbidden!(matched forbidden keyword)
   */
  CODE_45079(45079, "nickname is forbidden!(matched forbidden keyword)"),

  /**
   * 下发输入状态，需要之前30秒内跟用户有过消息交互  need sending message to user, or  recving message from user in the last 30 seconds before typing
   */
  CODE_45080(45080, "下发输入状态，需要之前30秒内跟用户有过消息交互"),

  /**
   * 已经在输入状态，不可重复下发  you are already typing
   */
  CODE_45081(45081, "已经在输入状态，不可重复下发"),

  /**
   * need icp license for the url domain
   */
  CODE_45082(45082, "need icp license for the url domain"),

  /**
   * the speed out of range
   */
  CODE_45083(45083, "the speed out of range"),

  /**
   * No speed message
   */
  CODE_45084(45084, "No speed message"),

  /**
   * speed server err
   */
  CODE_45085(45085, "speed server err"),

  /**
   * invalid attrbute 'data-miniprogram-appid'
   */
  CODE_45086(45086, "invalid attrbute 'data-miniprogram-appid'"),

  /**
   * customer service message from this  account have been blocked!
   */
  CODE_45087(45087, "customer service message from this  account have been blocked!"),

  /**
   * action size out of limit
   */
  CODE_45088(45088, "action size out of limit"),

  /**
   * expired
   */
  CODE_45089(45089, "expired"),

  /**
   * invalid group msg ticket
   */
  CODE_45090(45090, "invalid group msg ticket"),

  /**
   * account_name is illegal!
   */
  CODE_45091(45091, "account_name is illegal!"),

  /**
   * no voice data
   */
  CODE_45092(45092, "no voice data"),

  /**
   * no quota to send msg
   */
  CODE_45093(45093, "no quota to send msg"),

  /**
   * not allow to send custom message  when user enter session, for punishment
   */
  CODE_45094(45094, "not allow to send custom message  when user enter session, for punishment"),

  /**
   * not allow to modify stock for the advertisement batch
   */
  CODE_45095(45095, "not allow to modify stock for the advertisement batch"),

  /**
   * invalid qrcode
   */
  CODE_45096(45096, "invalid qrcode"),

  /**
   * invalid qrcode prefix
   */
  CODE_45097(45097, "invalid qrcode prefix"),

  /**
   * msgmenu list size is out of limit
   */
  CODE_45098(45098, "msgmenu list size is out of limit"),

  /**
   * msgmenu item content size is out of limit
   */
  CODE_45099(45099, "msgmenu item content size is out of limit"),

  /**
   * invalid size of keyword_id_list
   */
  CODE_45100(45100, "invalid size of keyword_id_list"),

  /**
   * hit upload limit
   */
  CODE_45101(45101, "hit upload limit"),

  /**
   * this api have been blocked temporarily.
   */
  CODE_45102(45102, "this api have been blocked temporarily."),

  /**
   * This API has been unsupported
   */
  CODE_45103(45103, "This API has been unsupported"),

  /**
   * reach max domain quota limit
   */
  CODE_45104(45104, "reach max domain quota limit"),

  /**
   * the consume verify code not found
   */
  CODE_45154(45154, "the consume verify code not found"),

  /**
   * the consume verify code is existed
   */
  CODE_45155(45155, "the consume verify code is existed"),

  /**
   * the consume verify code's length not invalid
   */
  CODE_45156(45156, "the consume verify code's length not invalid"),

  /**
   * invalid tag name
   */
  CODE_45157(45157, "invalid tag name"),

  /**
   * tag name too long
   */
  CODE_45158(45158, "tag name too long"),

  /**
   * invalid tag id
   */
  CODE_45159(45159, "invalid tag id"),

  /**
   * invalid category to create card
   */
  CODE_45160(45160, "invalid category to create card"),

  /**
   * this video id must be generated by calling upload api
   */
  CODE_45161(45161, "this video id must be generated by calling upload api"),

  /**
   * invalid type
   */
  CODE_45162(45162, "invalid type"),

  /**
   * invalid sort_method
   */
  CODE_45163(45163, "invalid sort_method"),

  /**
   * invalid offset
   */
  CODE_45164(45164, "invalid offset"),

  /**
   * invalid limit
   */
  CODE_45165(45165, "invalid limit"),

  /**
   * invalid content
   */
  CODE_45166(45166, "invalid content"),

  /**
   * invalid voip call key
   */
  CODE_45167(45167, "invalid voip call key"),

  /**
   * keyword in blacklist
   */
  CODE_45168(45168, "keyword in blacklist"),

  /**
   * part or whole of the requests from  the very app is temporary blocked by supervisor
   */
  CODE_45501(45501, "part or whole of the requests from  the very app is temporary blocked by supervisor"),

  /**
   * 不存在媒体数据，media_id 不存在  media data no exist
   */
  CODE_46001(46001, "不存在媒体数据，media_id 不存在"),

  /**
   * 不存在的菜单版本  menu version no exist
   */
  CODE_46002(46002, "不存在的菜单版本"),

  /**
   * 不存在的菜单数据  menu no exist
   */
  CODE_46003(46003, "不存在的菜单数据"),

  /**
   * 不存在的用户  user no exist
   */
  CODE_46004(46004, "不存在的用户"),

  /**
   * poi no exist
   */
  CODE_46005(46005, "poi no exist"),

  /**
   * voip file not exist
   */
  CODE_46006(46006, "voip file not exist"),

  /**
   * file being transcoded, please try later
   */
  CODE_46007(46007, "file being transcoded, please try later"),

  /**
   * result id not exist
   */
  CODE_46008(46008, "result id not exist"),

  /**
   * there is no user data
   */
  CODE_46009(46009, "there is no user data"),

  /**
   * this api have been not supported  since 2020-01-11 00:00:00, please use new api(subscribeMessage)!
   */
  CODE_46101(46101, "this api have been not supported  since 2020-01-11 00:00:00, please use new api(subscribeMessage)!"),

  /**
   * 解析 JSON/XML 内容错误  data format error
   */
  CODE_47001(47001, "解析 JSON/XML 内容错误"),

  /**
   * data format error, do NOT use json  unicode encode (\\uxxxx\\uxxxx), please use utf8 encoded text!
   */
  CODE_47002(47002, "data format error, do NOT use json  unicode encode (\\uxxxx\\uxxxx), please use utf8 encoded text!"),

  /**
   * 模板参数不准确，可能为空或者不满足规则，errmsg会提示具体是哪个字段出错  argument invalid!
   */
  CODE_47003(47003, "模板参数不准确，可能为空或者不满足规则，errmsg会提示具体是哪个字段出错"),

  /**
   * 每次提交的页面数超过1000（备注：每次提交页面数应小于或等于1000）  submit pages count more than each quota
   */
  CODE_47004(47004, "每次提交的页面数超过1000（备注：每次提交页面数应小于或等于1000）"),

  /**
   * tabbar no exist
   */
  CODE_47005(47005, "tabbar no exist"),

  /**
   * 当天提交页面数达到了配额上限，请明天再试  submit pages count reach daily limit, please try tomorrow
   */
  CODE_47006(47006, "当天提交页面数达到了配额上限，请明天再试"),

  /**
   * 搜索结果总数超过了1000条  search results count more than limit
   */
  CODE_47101(47101, "搜索结果总数超过了1000条"),

  /**
   * next_page_info参数错误  next_page_info error
   */
  CODE_47102(47102, "next_page_info参数错误"),

  /**
   * 参数 activity_id 错误  activity_id error
   */
  CODE_47501(47501, "参数 activity_id 错误"),

  /**
   * 参数 target_state 错误  target_state error
   */
  CODE_47502(47502, "参数 target_state 错误"),

  /**
   * 参数 version_type 错误  version_type error
   */
  CODE_47503(47503, "参数 version_type 错误"),

  /**
   * activity_id  activity_id expired time
   */
  CODE_47504(47504, "activity_id"),

  /**
   * api 功能未授权，请确认公众号已获得该接口，可以在公众平台官网 - 开发者中心页中查看接口权限  api unauthorized
   */
  CODE_48001(48001, "api 功能未授权，请确认公众号已获得该接口，可以在公众平台官网 - 开发者中心页中查看接口权限"),

  /**
   * 粉丝拒收消息（粉丝在公众号选项中，关闭了 “ 接收消息 ” ）  user block receive message
   */
  CODE_48002(48002, "粉丝拒收消息（粉丝在公众号选项中，关闭了 “ 接收消息 ” ）"),

  /**
   * user not agree mass-send protocol
   */
  CODE_48003(48003, "user not agree mass-send protocol"),

  /**
   * api 接口被封禁，请登录 mp.weixin.qq.com 查看详情  api forbidden for irregularities, view detail on mp.weixin.qq.com
   */
  CODE_48004(48004, "api 接口被封禁，请登录 mp.weixin.qq.com 查看详情"),

  /**
   * api 禁止删除被自动回复和自定义菜单引用的素材  forbid to delete material used by auto-reply or menu
   */
  CODE_48005(48005, "api 禁止删除被自动回复和自定义菜单引用的素材"),

  /**
   * api 禁止清零调用次数，因为清零次数达到上限  forbid to clear quota because of reaching the limit
   */
  CODE_48006(48006, "api 禁止清零调用次数，因为清零次数达到上限"),

  /**
   * forbid to use other's voip call key
   */
  CODE_48007(48007, "forbid to use other's voip call key"),

  /**
   * 没有该类型消息的发送权限  no permission for this msgtype
   */
  CODE_48008(48008, "没有该类型消息的发送权限"),

  /**
   * this api is expired
   */
  CODE_48009(48009, "this api is expired"),

  /**
   * forbid to modify the material，  please see more information on mp.weixin.qq.com
   */
  CODE_48010(48010, "forbid to modify the material，  please see more information on mp.weixin.qq.com"),

  /**
   * disabled template id
   */
  CODE_48011(48011, "disabled template id"),

  /**
   * invalid token
   */
  CODE_48012(48012, "invalid token"),

  /**
   * 该视频非新接口上传，不能用于视频消息群发
   */
  CODE_48013(48013, "该视频非新接口上传，不能用于视频消息群发"),

  /**
   * 该视频审核状态异常，请检查后重试
   */
  CODE_48014(48014, "该视频审核状态异常，请检查后重试"),

  /**
   * 该账号无留言功能权限
   */
  CODE_48015(48015, "该账号无留言功能权限"),

  /**
   * 该账号不满足智能配置"观看更多"视频条件
   */
  CODE_48016(48016, "该账号不满足智能配置\"观看更多\"视频条件"),

  /**
   * not same appid with appid of access_token
   */
  CODE_49001(49001, "not same appid with appid of access_token"),

  /**
   * empty openid or transid
   */
  CODE_49002(49002, "empty openid or transid"),

  /**
   * not match openid with appid
   */
  CODE_49003(49003, "not match openid with appid"),

  /**
   * not match signature
   */
  CODE_49004(49004, "not match signature"),

  /**
   * not existed transid
   */
  CODE_49005(49005, "not existed transid"),

  /**
   * missing arg two_dim_code
   */
  CODE_49006(49006, "missing arg two_dim_code"),

  /**
   * invalid two_dim_code
   */
  CODE_49007(49007, "invalid two_dim_code"),

  /**
   * invalid qrcode
   */
  CODE_49008(49008, "invalid qrcode"),

  /**
   * missing arg qrcode
   */
  CODE_49009(49009, "missing arg qrcode"),

  /**
   * invalid partner id
   */
  CODE_49010(49010, "invalid partner id"),

  /**
   * not existed feedbackid
   */
  CODE_49300(49300, "not existed feedbackid"),

  /**
   * feedback exist
   */
  CODE_49301(49301, "feedback exist"),

  /**
   * feedback status already changed
   */
  CODE_49302(49302, "feedback status already changed"),

  /**
   * 用户未授权该 api  api unauthorized or user unauthorized
   */
  CODE_50001(50001, "用户未授权该 api"),

  /**
   * 用户受限，可能是用户帐号被冻结或注销  user limited
   */
  CODE_50002(50002, "用户受限，可能是用户帐号被冻结或注销"),

  /**
   * user unexpected, maybe not in white list
   */
  CODE_50003(50003, "user unexpected, maybe not in white list"),

  /**
   * user not allow to use accesstoken, maybe for punishment
   */
  CODE_50004(50004, "user not allow to use accesstoken, maybe for punishment"),

  /**
   * 用户未关注公众号  user is unsubscribed
   */
  CODE_50005(50005, "用户未关注公众号"),

  /**
   * user has switched off friends authorization
   */
  CODE_50006(50006, "user has switched off friends authorization"),

  /**
   * enterprise father account not exist
   */
  CODE_51000(51000, "enterprise father account not exist"),

  /**
   * enterprise child account not belong to the father
   */
  CODE_51001(51001, "enterprise child account not belong to the father"),

  /**
   * enterprise verify message not correct
   */
  CODE_51002(51002, "enterprise verify message not correct"),

  /**
   * invalid enterprise child list size
   */
  CODE_51003(51003, "invalid enterprise child list size"),

  /**
   * not a enterprise father account
   */
  CODE_51004(51004, "not a enterprise father account"),

  /**
   * not a enterprise child account
   */
  CODE_51005(51005, "not a enterprise child account"),

  /**
   * invalid nick name
   */
  CODE_51006(51006, "invalid nick name"),

  /**
   * not a enterprise account
   */
  CODE_51007(51007, "not a enterprise account"),

  /**
   * invalid email
   */
  CODE_51008(51008, "invalid email"),

  /**
   * invalid pwd
   */
  CODE_51009(51009, "invalid pwd"),

  /**
   * repeated email
   */
  CODE_51010(51010, "repeated email"),

  /**
   * access deny
   */
  CODE_51011(51011, "access deny"),

  /**
   * need verify code
   */
  CODE_51012(51012, "need verify code"),

  /**
   * wrong verify code
   */
  CODE_51013(51013, "wrong verify code"),

  /**
   * need modify pwd
   */
  CODE_51014(51014, "need modify pwd"),

  /**
   * user not exist
   */
  CODE_51015(51015, "user not exist"),

  /**
   * tv info not exist
   */
  CODE_51020(51020, "tv info not exist"),

  /**
   * stamp crossed
   */
  CODE_51021(51021, "stamp crossed"),

  /**
   * invalid stamp range
   */
  CODE_51022(51022, "invalid stamp range"),

  /**
   * stamp not match date
   */
  CODE_51023(51023, "stamp not match date"),

  /**
   * empty program name
   */
  CODE_51024(51024, "empty program name"),

  /**
   * empty action url
   */
  CODE_51025(51025, "empty action url"),

  /**
   * program name size out of limit
   */
  CODE_51026(51026, "program name size out of limit"),

  /**
   * action url size out of limit
   */
  CODE_51027(51027, "action url size out of limit"),

  /**
   * invalid program name
   */
  CODE_51028(51028, "invalid program name"),

  /**
   * invalid action url
   */
  CODE_51029(51029, "invalid action url"),

  /**
   * invalid action id
   */
  CODE_51030(51030, "invalid action id"),

  /**
   * invalid action offset
   */
  CODE_51031(51031, "invalid action offset"),

  /**
   * empty action title
   */
  CODE_51032(51032, "empty action title"),

  /**
   * action title size out of limit
   */
  CODE_51033(51033, "action title size out of limit"),

  /**
   * empty action icon url
   */
  CODE_51034(51034, "empty action icon url"),

  /**
   * action icon url out of limit
   */
  CODE_51035(51035, "action icon url out of limit"),

  /**
   * pic is not from cdn
   */
  CODE_52000(52000, "pic is not from cdn"),

  /**
   * wechat price is not less than origin price
   */
  CODE_52001(52001, "wechat price is not less than origin price"),

  /**
   * category/sku is wrong
   */
  CODE_52002(52002, "category/sku is wrong"),

  /**
   * product id not existed
   */
  CODE_52003(52003, "product id not existed"),

  /**
   * category id is not exist, or doesn't has sub category
   */
  CODE_52004(52004, "category id is not exist, or doesn't has sub category"),

  /**
   * quantity is zero
   */
  CODE_52005(52005, "quantity is zero"),

  /**
   * area code is invalid
   */
  CODE_52006(52006, "area code is invalid"),

  /**
   * express template param is error
   */
  CODE_52007(52007, "express template param is error"),

  /**
   * express template id is not existed
   */
  CODE_52008(52008, "express template id is not existed"),

  /**
   * group name is empty
   */
  CODE_52009(52009, "group name is empty"),

  /**
   * group id is not existed
   */
  CODE_52010(52010, "group id is not existed"),

  /**
   * mod_action is invalid
   */
  CODE_52011(52011, "mod_action is invalid"),

  /**
   * shelf components count is greater than 20
   */
  CODE_52012(52012, "shelf components count is greater than 20"),

  /**
   * shelf component is empty
   */
  CODE_52013(52013, "shelf component is empty"),

  /**
   * shelf id is not existed
   */
  CODE_52014(52014, "shelf id is not existed"),

  /**
   * order id is not existed
   */
  CODE_52015(52015, "order id is not existed"),

  /**
   * order filter param is invalid
   */
  CODE_52016(52016, "order filter param is invalid"),

  /**
   * order express param is invalid
   */
  CODE_52017(52017, "order express param is invalid"),

  /**
   * order delivery param is invalid
   */
  CODE_52018(52018, "order delivery param is invalid"),

  /**
   * brand name empty
   */
  CODE_52019(52019, "brand name empty"),

  /**
   * principal limit exceed
   */
  CODE_53000(53000, "principal limit exceed"),

  /**
   * principal in black list
   */
  CODE_53001(53001, "principal in black list"),

  /**
   * mobile limit exceed
   */
  CODE_53002(53002, "mobile limit exceed"),

  /**
   * idcard limit exceed
   */
  CODE_53003(53003, "idcard limit exceed"),

  /**
   * 名称格式不合法  nickname invalid
   */
  CODE_53010(53010, "名称格式不合法"),

  /**
   * 名称检测命中频率限制  check nickname too frequently
   */
  CODE_53011(53011, "名称检测命中频率限制"),

  /**
   * 禁止使用该名称  nickname ban
   */
  CODE_53012(53012, "禁止使用该名称"),

  /**
   * 公众号：名称与已有公众号名称重复;小程序：该名称与已有小程序名称重复  nickname has been occupied
   */
  CODE_53013(53013, "公众号：名称与已有公众号名称重复;小程序：该名称与已有小程序名称重复"),

  /**
   * 公众号：公众号已有{名称  A+}时，需与该帐号相同主体才可申请{名称 A};小程序：小程序已有{名称 A+}时，需与该帐号相同主体才可申请{名称 A}
   */
  CODE_53014(53014, "公众号：公众号已有{名称  A+}时，需与该帐号相同主体才可申请{名称 A};小程序：小程序已有{名称 A+}时，需与该帐号相同主体才可申请{名称 A}"),

  /**
   * 公众号：该名称与已有小程序名称重复，需与该小程序帐号相同主体才可申请;小程序：该名称与已有公众号名称重复，需与该公众号帐号相同主体才可申请
   */
  CODE_53015(53015, "公众号：该名称与已有小程序名称重复，需与该小程序帐号相同主体才可申请;小程序：该名称与已有公众号名称重复，需与该公众号帐号相同主体才可申请"),

  /**
   * 公众号：该名称与已有多个小程序名称重复，暂不支持申请;小程序：该名称与已有多个公众号名称重复，暂不支持申请
   */
  CODE_53016(53016, "公众号：该名称与已有多个小程序名称重复，暂不支持申请;小程序：该名称与已有多个公众号名称重复，暂不支持申请"),

  /**
   * 公众号：小程序已有{名称  A+}时，需与该帐号相同主体才可申请{名称 A};小程序：公众号已有{名称 A+}时，需与该帐号相同主体才可申请{名称 A}
   */
  CODE_53017(53017, "公众号：小程序已有{名称  A+}时，需与该帐号相同主体才可申请{名称 A};小程序：公众号已有{名称 A+}时，需与该帐号相同主体才可申请{名称 A}"),

  /**
   * 名称命中微信号  nickname hit alias
   */
  CODE_53018(53018, "名称命中微信号"),

  /**
   * 名称在保护期内  nickname protected by infringement
   */
  CODE_53019(53019, "名称在保护期内"),

  /**
   * order not found
   */
  CODE_53100(53100, "订单不存在"),

  /**
   * order already paid
   */
  CODE_53101(53101, "已经支付的订单"),

  /**
   * already has checking order, can not apply
   */
  CODE_53102(53102, "已有检查单，不能申请"),

  /**
   * order can not do refill
   */
  CODE_53103(53103, "order can not do refill"),

  /**
   * 本月功能介绍修改次数已用完  modify signature quota limit exceed
   */
  CODE_53200(53200, "本月功能介绍修改次数已用完"),

  /**
   * 功能介绍内容命中黑名单关键字  signature in black list, can not use
   */
  CODE_53201(53201, "功能介绍内容命中黑名单关键字"),

  /**
   * 本月头像修改次数已用完  modify avatar quota limit exceed
   */
  CODE_53202(53202, "本月头像修改次数已用完"),

  /**
   * can't be modified for the time being
   */
  CODE_53203(53203, "暂时还不能修改"),

  /**
   * signature invalid
   */
  CODE_53204(53204, "无效签名"),

  /**
   * 超出每月次数限制
   */
  CODE_53300(53300, "超出每月次数限制"),

  /**
   * 超出可配置类目总数限制
   */
  CODE_53301(53301, "超出可配置类目总数限制"),

  /**
   * 当前账号主体类型不允许设置此种类目
   */
  CODE_53302(53302, "当前账号主体类型不允许设置此种类目"),

  /**
   * 提交的参数不合法
   */
  CODE_53303(53303, "提交的参数不合法"),

  /**
   * 与已有类目重复
   */
  CODE_53304(53304, "与已有类目重复"),

  /**
   * 包含未通过IPC校验的类目
   */
  CODE_53305(53305, "包含未通过IPC校验的类目"),

  /**
   * 修改类目只允许修改类目资质，不允许修改类目ID
   */
  CODE_53306(53306, "修改类目只允许修改类目资质，不允许修改类目ID"),

  /**
   * 只有审核失败的类目允许修改
   */
  CODE_53307(53307, "只有审核失败的类目允许修改"),

  /**
   * 审核中的类目不允许删除
   */
  CODE_53308(53308, "审核中的类目不允许删除"),

  /**
   * 社交红包不允许删除
   */
  CODE_53309(53309, "社交红包不允许删除"),

  /**
   * 类目超过上限，但是可以添加apply_reason参数申请更多类目
   */
  CODE_53310(53310, "类目超过上限，但是可以添加apply_reason参数申请更多类目"),

  /**
   * 需要提交资料信息
   */
  CODE_53311(53311, "需要提交资料信息"),

  /**
   * empty jsapi name
   */
  CODE_60005(60005, "空的jsapi名称"),

  /**
   * user cancel the auth
   */
  CODE_60006(60006, "用户取消该授权"),

  /**
   * invalid component type
   */
  CODE_61000(61000, "无效的第三方类型"),

  /**
   * component type and component appid is not match
   */
  CODE_61001(61001, "第三方类型与第三方APPID不匹配"),

  /**
   * the third appid is not open KF
   */
  CODE_61002(61002, "第三方APPID没有开放客服"),

  /**
   * component is not authorized by this account
   */
  CODE_61003(61003, "帐号未授权"),

  /**
   * api 功能未授权，请确认公众号/小程序已获得该接口，可以在公众平台官网 - 开发者中心页中查看接口权限  access clientip is not registered
   */
  CODE_61004(61004, "api 功能未授权，请确认公众号/小程序已获得该接口，可以在公众平台官网 - 开发者中心页中查看接口权限"),

  /**
   * component ticket is expired
   */
  CODE_61005(61005, "ticket 已过期"),

  /**
   * component ticket is invalid
   */
  CODE_61006(61006, "无效 ticket"),

  /**
   * api is unauthorized to component
   */
  CODE_61007(61007, "接口未授权给第三方平台"),

  /**
   * component req key is duplicated
   */
  CODE_61008(61008, "第三方请求的key存在重复"),

  /**
   * code is invalid
   */
  CODE_61009(61009, "无效code"),

  /**
   * code is expired
   */
  CODE_61010(61010, "code已过期"),

  /**
   * invalid component
   */
  CODE_61011(61011, "无效的第三方平台"),

  /**
   * invalid option name
   */
  CODE_61012(61012, "无效的选项名称"),

  /**
   * invalid option value
   */
  CODE_61013(61013, "无效的选项值"),

  /**
   * must use component token for component api
   */
  CODE_61014(61014, "必须使用component接口的token"),

  /**
   * must use biz account token for not component api
   */
  CODE_61015(61015, "必须使用商业帐号token，而不是component api"),

  /**
   * function category of API need be  confirmed by component
   */
  CODE_61016(61016, "function category of API need be  confirmed by component"),

  /**
   * function category is not authorized
   */
  CODE_61017(61017, "function category is not authorized"),

  /**
   * already confirm
   */
  CODE_61018(61018, "已确认"),

  /**
   * not need confirm
   */
  CODE_61019(61019, "不需要确认"),

  /**
   * err parameter
   */
  CODE_61020(61020, "err parameter"),

  /**
   * can't confirm
   */
  CODE_61021(61021, "can't confirm"),

  /**
   * can't resubmit
   */
  CODE_61022(61022, "can't resubmit"),

  /**
   * refresh_token is invalid
   */
  CODE_61023(61023, "refresh_token is invalid"),

  /**
   * must use api(api_component_token) to  get token for component acct
   */
  CODE_61024(61024, "must use api(api_component_token) to  get token for component acct"),

  /**
   * read-only option
   */
  CODE_61025(61025, "read-only option"),

  /**
   * register access deny
   */
  CODE_61026(61026, "register access deny"),

  /**
   * register limit exceed
   */
  CODE_61027(61027, "register limit exceed"),

  /**
   * component is unpublished
   */
  CODE_61028(61028, "component is unpublished"),

  /**
   * component need republish with base category
   */
  CODE_61029(61029, "component need republish with base category"),

  /**
   * component cancel authorization not allowed
   */
  CODE_61030(61030, "component cancel authorization not allowed"),

  /**
   * invalid realname type
   */
  CODE_61051(61051, "invalid realname type"),

  /**
   * need to be certified
   */
  CODE_61052(61052, "need to be certified"),

  /**
   * realname exceed limits
   */
  CODE_61053(61053, "realname exceed limits"),

  /**
   * realname in black list
   */
  CODE_61054(61054, "realname in black list"),

  /**
   * exceed quota per month
   */
  CODE_61055(61055, "exceed quota per month"),

  /**
   * copy_wx_verify is required option
   */
  CODE_61056(61056, "copy_wx_verify is required option"),

  /**
   * invalid ticket
   */
  CODE_61058(61058, "invalid ticket"),

  /**
   * overseas access deny
   */
  CODE_61061(61061, "overseas access deny"),

  /**
   * admin exceed limits
   */
  CODE_61063(61063, "admin exceed limits"),

  /**
   * admin in black list
   */
  CODE_61064(61064, "admin in black list"),

  /**
   * idcard exceed limits
   */
  CODE_61065(61065, "idcard exceed limits"),

  /**
   * idcard in black list
   */
  CODE_61066(61066, "idcard in black list"),

  /**
   * mobile exceed limits
   */
  CODE_61067(61067, "mobile exceed limits"),

  /**
   * mobile in black list
   */
  CODE_61068(61068, "mobile in black list"),

  /**
   * invalid admin
   */
  CODE_61069(61069, "invalid admin"),

  /**
   * name, idcard, wechat name not in accordance
   */
  CODE_61070(61070, "name, idcard, wechat name not in accordance"),

  /**
   * invalid url
   */
  CODE_61100(61100, "invalid url"),

  /**
   * invalid openid
   */
  CODE_61101(61101, "invalid openid"),

  /**
   * share relation not existed
   */
  CODE_61102(61102, "share relation not existed"),

  /**
   * product wording not set
   */
  CODE_61200(61200, "product wording not set"),

  /**
   * invalid base info
   */
  CODE_61300(61300, "invalid base info"),

  /**
   * invalid detail info
   */
  CODE_61301(61301, "invalid detail info"),

  /**
   * invalid action info
   */
  CODE_61302(61302, "invalid action info"),

  /**
   * brand info not exist
   */
  CODE_61303(61303, "brand info not exist"),

  /**
   * invalid product id
   */
  CODE_61304(61304, "invalid product id"),

  /**
   * invalid key info
   */
  CODE_61305(61305, "invalid key info"),

  /**
   * invalid appid
   */
  CODE_61306(61306, "invalid appid"),

  /**
   * invalid card id
   */
  CODE_61307(61307, "invalid card id"),

  /**
   * base info not exist
   */
  CODE_61308(61308, "base info not exist"),

  /**
   * detail info not exist
   */
  CODE_61309(61309, "detail info not exist"),

  /**
   * action info not exist
   */
  CODE_61310(61310, "action info not exist"),

  /**
   * invalid media info
   */
  CODE_61311(61311, "invalid media info"),

  /**
   * invalid buffer size
   */
  CODE_61312(61312, "invalid buffer size"),

  /**
   * invalid buffer
   */
  CODE_61313(61313, "invalid buffer"),

  /**
   * invalid qrcode extinfo
   */
  CODE_61314(61314, "invalid qrcode extinfo"),

  /**
   * invalid local ext info
   */
  CODE_61315(61315, "invalid local ext info"),

  /**
   * key conflict
   */
  CODE_61316(61316, "key conflict"),

  /**
   * ticket invalid
   */
  CODE_61317(61317, "ticket invalid"),

  /**
   * verify not pass
   */
  CODE_61318(61318, "verify not pass"),

  /**
   * category invalid
   */
  CODE_61319(61319, "category invalid"),

  /**
   * merchant info not exist
   */
  CODE_61320(61320, "merchant info not exist"),

  /**
   * cate id is a leaf node
   */
  CODE_61321(61321, "cate id is a leaf node"),

  /**
   * category id no permision
   */
  CODE_61322(61322, "category id no permision"),

  /**
   * barcode no permision
   */
  CODE_61323(61323, "barcode no permision"),

  /**
   * exceed max action num
   */
  CODE_61324(61324, "exceed max action num"),

  /**
   * brandinfo invalid store mgr type
   */
  CODE_61325(61325, "brandinfo invalid store mgr type"),

  /**
   * anti-spam blocked
   */
  CODE_61326(61326, "anti-spam blocked"),

  /**
   * comment reach limit
   */
  CODE_61327(61327, "comment reach limit"),

  /**
   * comment data is not the newest
   */
  CODE_61328(61328, "comment data is not the newest"),

  /**
   * comment hit ban word
   */
  CODE_61329(61329, "comment hit ban word"),

  /**
   * image already add
   */
  CODE_61330(61330, "image already add"),

  /**
   * image never add
   */
  CODE_61331(61331, "image never add"),

  /**
   * warning, image quanlity too low
   */
  CODE_61332(61332, "warning, image quanlity too low"),

  /**
   * warning, image simility too high
   */
  CODE_61333(61333, "warning, image simility too high"),

  /**
   * product not exists
   */
  CODE_61334(61334, "product not exists"),

  /**
   * key apply fail
   */
  CODE_61335(61335, "key apply fail"),

  /**
   * check status fail
   */
  CODE_61336(61336, "check status fail"),

  /**
   * product already exists
   */
  CODE_61337(61337, "product already exists"),

  /**
   * forbid delete
   */
  CODE_61338(61338, "forbid delete"),

  /**
   * firmcode claimed
   */
  CODE_61339(61339, "firmcode claimed"),

  /**
   * check firm info fail
   */
  CODE_61340(61340, "check firm info fail"),

  /**
   * too many white list uin
   */
  CODE_61341(61341, "too many white list uin"),

  /**
   * keystandard not match
   */
  CODE_61342(61342, "keystandard not match"),

  /**
   * keystandard error
   */
  CODE_61343(61343, "keystandard error"),

  /**
   * id map not exists
   */
  CODE_61344(61344, "id map not exists"),

  /**
   * invalid action code
   */
  CODE_61345(61345, "invalid action code"),

  /**
   * invalid actioninfo store
   */
  CODE_61346(61346, "invalid actioninfo store"),

  /**
   * invalid actioninfo media
   */
  CODE_61347(61347, "invalid actioninfo media"),

  /**
   * invalid actioninfo text
   */
  CODE_61348(61348, "invalid actioninfo text"),

  /**
   * invalid input data
   */
  CODE_61350(61350, "invalid input data"),

  /**
   * input data exceed max size
   */
  CODE_61351(61351, "input data exceed max size"),

  /**
   * kf_account error
   */
  CODE_61400(61400, "kf_account error"),

  /**
   * kf system alredy transfer
   */
  CODE_61401(61401, "kf system alredy transfer"),

  /**
   * 系统错误 (system error)
   */
  CODE_61450(61450, "系统错误 (system error)"),

  /**
   * 参数错误 (invalid parameter)
   */
  CODE_61451(61451, "参数错误 (invalid parameter)"),

  /**
   * 无效客服账号 (invalid kf_account)
   */
  CODE_61452(61452, "无效客服账号 (invalid kf_account)"),

  /**
   * 客服帐号已存在 (kf_account exsited)
   */
  CODE_61453(61453, "客服帐号已存在 (kf_account exsited)"),

  /**
   * 客服帐号名长度超过限制 ( 仅允许 10 个英文字符，不包括 @ 及 @ 后的公众号的微信号 )(invalid  kf_acount  length)
   */
  CODE_61454(61454, "客服帐号名长度超过限制 ( 仅允许 10 个英文字符，不包括 @ 及 @ 后的公众号的微信号 )(invalid  kf_acount  length)"),

  /**
   * 客服帐号名包含非法字符 ( 仅允许英文 + 数字 )(illegal character in   kf_account)
   */
  CODE_61455(61455, "客服帐号名包含非法字符 ( 仅允许英文 + 数字 )(illegal character in   kf_account)"),

  /**
   * 客服帐号个数超过限制 (10 个客服账号 )(kf_account count exceeded)
   */
  CODE_61456(61456, "客服帐号个数超过限制 (10 个客服账号 )(kf_account count exceeded)"),

  /**
   * 无效头像文件类型 (invalid  file type)
   */
  CODE_61457(61457, "无效头像文件类型 (invalid  file type)"),

  /**
   * 日期格式错误  date format error
   */
  CODE_61500(61500, "日期格式错误"),

  /**
   * date range error
   */
  CODE_61501(61501, "date range error"),

  /**
   * this is game miniprogram, data api is not supported
   */
  CODE_61502(61502, "this is game miniprogram, data api is not supported"),

  /**
   * data not ready, please try later
   */
  CODE_61503(61503, "data not ready, please try later"),

  /**
   * trying to access other's app
   */
  CODE_62001(62001, "trying to access other's app"),

  /**
   * app name already exists
   */
  CODE_62002(62002, "app name already exists"),

  /**
   * please provide at least one platform
   */
  CODE_62003(62003, "please provide at least one platform"),

  /**
   * invalid app name
   */
  CODE_62004(62004, "invalid app name"),

  /**
   * invalid app id
   */
  CODE_62005(62005, "invalid app id"),

  /**
   * 部分参数为空  some arguments is empty
   */
  CODE_63001(63001, "部分参数为空"),

  /**
   * 无效的签名  invalid signature
   */
  CODE_63002(63002, "无效的签名"),

  /**
   * invalid signature method
   */
  CODE_63003(63003, "invalid signature method"),

  /**
   * no authroize
   */
  CODE_63004(63004, "no authroize"),

  /**
   * gen ticket fail
   */
  CODE_63149(63149, "gen ticket fail"),

  /**
   * set ticket fail
   */
  CODE_63152(63152, "set ticket fail"),

  /**
   * shortid decode fail
   */
  CODE_63153(63153, "shortid decode fail"),

  /**
   * invalid status
   */
  CODE_63154(63154, "invalid status"),

  /**
   * invalid color
   */
  CODE_63155(63155, "invalid color"),

  /**
   * invalid tag
   */
  CODE_63156(63156, "invalid tag"),

  /**
   * invalid recommend
   */
  CODE_63157(63157, "invalid recommend"),

  /**
   * branditem out of limits
   */
  CODE_63158(63158, "branditem out of limits"),

  /**
   * retail_price empty
   */
  CODE_63159(63159, "retail_price empty"),

  /**
   * priceinfo invalid
   */
  CODE_63160(63160, "priceinfo invalid"),

  /**
   * antifake module num limit
   */
  CODE_63161(63161, "antifake module num limit"),

  /**
   * antifake native_type err
   */
  CODE_63162(63162, "antifake native_type err"),

  /**
   * antifake link not exists
   */
  CODE_63163(63163, "antifake link not exists"),

  /**
   * module type not exist
   */
  CODE_63164(63164, "module type not exist"),

  /**
   * module info not exist
   */
  CODE_63165(63165, "module info not exist"),

  /**
   * item is beding verified
   */
  CODE_63166(63166, "item is beding verified"),

  /**
   * item not published
   */
  CODE_63167(63167, "item not published"),

  /**
   * verify not pass
   */
  CODE_63168(63168, "verify not pass"),

  /**
   * already published
   */
  CODE_63169(63169, "already published"),

  /**
   * only banner or media
   */
  CODE_63170(63170, "only banner or media"),

  /**
   * card num limit
   */
  CODE_63171(63171, "card num limit"),

  /**
   * user num limit
   */
  CODE_63172(63172, "user num limit"),

  /**
   * text num limit
   */
  CODE_63173(63173, "text num limit"),

  /**
   * link card user sum limit
   */
  CODE_63174(63174, "link card user sum limit"),

  /**
   * detail info error
   */
  CODE_63175(63175, "detail info error"),

  /**
   * not this type
   */
  CODE_63176(63176, "not this type"),

  /**
   * src or secretkey or version or expired_time is wrong
   */
  CODE_63177(63177, "src or secretkey or version or expired_time is wrong"),

  /**
   * appid wrong
   */
  CODE_63178(63178, "appid wrong"),

  /**
   * openid num limit
   */
  CODE_63179(63179, "openid num limit"),

  /**
   * this app msg not found
   */
  CODE_63180(63180, "this app msg not found"),

  /**
   * get history app msg end
   */
  CODE_63181(63181, "get history app msg end"),

  /**
   * openid_list empty
   */
  CODE_63182(63182, "openid_list empty"),

  /**
   * unknown deeplink type
   */
  CODE_65001(65001, "unknown deeplink type"),

  /**
   * deeplink unauthorized
   */
  CODE_65002(65002, "deeplink unauthorized"),

  /**
   * bad deeplink
   */
  CODE_65003(65003, "bad deeplink"),

  /**
   * deeplinks of the very type are supposed to have short-life
   */
  CODE_65004(65004, "deeplinks of the very type are supposed to have short-life"),

  /**
   * invalid categories
   */
  CODE_65104(65104, "invalid categories"),

  /**
   * invalid photo url
   */
  CODE_65105(65105, "invalid photo url"),

  /**
   * poi audit state must be approved
   */
  CODE_65106(65106, "poi audit state must be approved"),

  /**
   * poi not allowed modify now
   */
  CODE_65107(65107, "poi not allowed modify now"),

  /**
   * invalid business name
   */
  CODE_65109(65109, "invalid business name"),

  /**
   * invalid address
   */
  CODE_65110(65110, "invalid address"),

  /**
   * invalid telephone
   */
  CODE_65111(65111, "invalid telephone"),

  /**
   * invalid city
   */
  CODE_65112(65112, "invalid city"),

  /**
   * invalid province
   */
  CODE_65113(65113, "invalid province"),

  /**
   * photo list empty
   */
  CODE_65114(65114, "photo list empty"),

  /**
   * poi_id is not exist
   */
  CODE_65115(65115, "poi_id is not exist"),

  /**
   * poi has been deleted
   */
  CODE_65116(65116, "poi has been deleted"),

  /**
   * cannot delete poi
   */
  CODE_65117(65117, "cannot delete poi"),

  /**
   * store status is invalid
   */
  CODE_65118(65118, "store status is invalid"),

  /**
   * lack of qualification for relevant principals
   */
  CODE_65119(65119, "lack of qualification for relevant principals"),

  /**
   * category info is not found
   */
  CODE_65120(65120, "category info is not found"),

  /**
   * room_name is empty, please check your input
   */
  CODE_65201(65201, "room_name is empty, please check your input"),

  /**
   * user_id is empty, please check your input
   */
  CODE_65202(65202, "user_id is empty, please check your input"),

  /**
   * invalid check ticket
   */
  CODE_65203(65203, "invalid check ticket"),

  /**
   * invalid check ticket opt code
   */
  CODE_65204(65204, "invalid check ticket opt code"),

  /**
   * check ticket out of time
   */
  CODE_65205(65205, "check ticket out of time"),

  /**
   * 不存在此 menuid 对应的个性化菜单  this menu is not conditionalmenu
   */
  CODE_65301(65301, "不存在此 menuid 对应的个性化菜单"),

  /**
   * 没有相应的用户  no such user
   */
  CODE_65302(65302, "没有相应的用户"),

  /**
   * 没有默认菜单，不能创建个性化菜单  there is no selfmenu, please create selfmenu first
   */
  CODE_65303(65303, "没有默认菜单，不能创建个性化菜单"),

  /**
   * MatchRule 信息为空  match rule empty
   */
  CODE_65304(65304, "MatchRule 信息为空"),

  /**
   * 个性化菜单数量受限  menu count limit
   */
  CODE_65305(65305, "个性化菜单数量受限"),

  /**
   * 不支持个性化菜单的帐号  conditional menu not support
   */
  CODE_65306(65306, "不支持个性化菜单的帐号"),

  /**
   * 个性化菜单信息为空  conditional menu is empty
   */
  CODE_65307(65307, "个性化菜单信息为空"),

  /**
   * 包含没有响应类型的 button  exist empty button act
   */
  CODE_65308(65308, "包含没有响应类型的 button"),

  /**
   * 个性化菜单开关处于关闭状态  conditional menu switch is closed
   */
  CODE_65309(65309, "个性化菜单开关处于关闭状态"),

  /**
   * 填写了省份或城市信息，国家信息不能为空  region info: country is empty
   */
  CODE_65310(65310, "填写了省份或城市信息，国家信息不能为空"),

  /**
   * 填写了城市信息，省份信息不能为空  region info: province is empty
   */
  CODE_65311(65311, "填写了城市信息，省份信息不能为空"),

  /**
   * 不合法的国家信息  invalid country info
   */
  CODE_65312(65312, "不合法的国家信息"),

  /**
   * 不合法的省份信息  invalid province info
   */
  CODE_65313(65313, "不合法的省份信息"),

  /**
   * 不合法的城市信息  invalid city info
   */
  CODE_65314(65314, "不合法的城市信息"),

  /**
   * not fans
   */
  CODE_65315(65315, "not fans"),

  /**
   * 该公众号的菜单设置了过多的域名外跳（最多跳转到 3 个域名的链接）  domain count reach limit
   */
  CODE_65316(65316, "该公众号的菜单设置了过多的域名外跳（最多跳转到 3 个域名的链接）"),

  /**
   * 不合法的 URL  contain invalid url
   */
  CODE_65317(65317, "不合法的 URL"),

  /**
   * must use utf-8 charset
   */
  CODE_65318(65318, "must use utf-8 charset"),

  /**
   * not allow to create menu
   */
  CODE_65319(65319, "not allow to create menu"),

  /**
   * please enable new custom service, or  wait for a while if you have enabled
   */
  CODE_65400(65400, "please enable new custom service, or  wait for a while if you have enabled"),

  /**
   * invalid custom service account
   */
  CODE_65401(65401, "invalid custom service account"),

  /**
   * the custom service account need to bind a wechat user
   */
  CODE_65402(65402, "the custom service account need to bind a wechat user"),

  /**
   * illegal nickname
   */
  CODE_65403(65403, "illegal nickname"),

  /**
   * illegal custom service account
   */
  CODE_65404(65404, "illegal custom service account"),

  /**
   * custom service account number reach limit
   */
  CODE_65405(65405, "custom service account number reach limit"),

  /**
   * custom service account exists
   */
  CODE_65406(65406, "custom service account exists"),

  /**
   * the wechat user have been one of your workers
   */
  CODE_65407(65407, "the wechat user have been one of your workers"),

  /**
   * you have already invited the wechat user
   */
  CODE_65408(65408, "you have already invited the wechat user"),

  /**
   * wechat account invalid
   */
  CODE_65409(65409, "wechat account invalid"),

  /**
   * too many custom service accounts bound by the worker
   */
  CODE_65410(65410, "too many custom service accounts bound by the worker"),

  /**
   * a effective invitation to bind the  custom service account exists
   */
  CODE_65411(65411, "a effective invitation to bind the  custom service account exists"),

  /**
   * the custom service account have been  bound by a wechat user
   */
  CODE_65412(65412, "the custom service account have been  bound by a wechat user"),

  /**
   * no effective session for the customer
   */
  CODE_65413(65413, "no effective session for the customer"),

  /**
   * another worker is serving the customer
   */
  CODE_65414(65414, "another worker is serving the customer"),

  /**
   * the worker is not online
   */
  CODE_65415(65415, "the worker is not online"),

  /**
   * param invalid, please check
   */
  CODE_65416(65416, "param invalid, please check"),

  /**
   * it is too long from the starttime to endtime
   */
  CODE_65417(65417, "it is too long from the starttime to endtime"),

  /**
   * homepage not exists
   */
  CODE_65450(65450, "homepage not exists"),

  /**
   * invalid store type
   */
  CODE_68002(68002, "invalid store type"),

  /**
   * invalid store name
   */
  CODE_68003(68003, "invalid store name"),

  /**
   * invalid store wxa path
   */
  CODE_68004(68004, "invalid store wxa path"),

  /**
   * miss store wxa path
   */
  CODE_68005(68005, "miss store wxa path"),

  /**
   * invalid kefu type
   */
  CODE_68006(68006, "invalid kefu type"),

  /**
   * invalid kefu wxa path
   */
  CODE_68007(68007, "invalid kefu wxa path"),

  /**
   * invalid kefu phone number
   */
  CODE_68008(68008, "invalid kefu phone number"),

  /**
   * invalid sub mch id
   */
  CODE_68009(68009, "invalid sub mch id"),

  /**
   * store id has exist
   */
  CODE_68010(68010, "store id has exist"),

  /**
   * miss store name
   */
  CODE_68011(68011, "miss store name"),

  /**
   * miss create time
   */
  CODE_68012(68012, "miss create time"),

  /**
   * invalid status
   */
  CODE_68013(68013, "invalid status"),

  /**
   * invalid receiver info
   */
  CODE_68014(68014, "invalid receiver info"),

  /**
   * invalid product
   */
  CODE_68015(68015, "invalid product"),

  /**
   * invalid pay type
   */
  CODE_68016(68016, "invalid pay type"),

  /**
   * invalid fast mail no
   */
  CODE_68017(68017, "invalid fast mail no"),

  /**
   * invalid busi id
   */
  CODE_68018(68018, "invalid busi id"),

  /**
   * miss product sku
   */
  CODE_68019(68019, "miss product sku"),

  /**
   * invalid service type
   */
  CODE_68020(68020, "invalid service type"),

  /**
   * invalid service status
   */
  CODE_68021(68021, "invalid service status"),

  /**
   * invalid service_id
   */
  CODE_68022(68022, "invalid service_id"),

  /**
   * service_id has exist
   */
  CODE_68023(68023, "service_id has exist"),

  /**
   * miss service wxa path
   */
  CODE_68024(68024, "miss service wxa path"),

  /**
   * invalid product sku
   */
  CODE_68025(68025, "invalid product sku"),

  /**
   * invalid product spu
   */
  CODE_68026(68026, "invalid product spu"),

  /**
   * miss product spu
   */
  CODE_68027(68027, "miss product spu"),

  /**
   * can not find product spu and spu in order list
   */
  CODE_68028(68028, "can not find product spu and spu in order list"),

  /**
   * sku and spu duplicated
   */
  CODE_68029(68029, "sku and spu duplicated"),

  /**
   * busi_id has exist
   */
  CODE_68030(68030, "busi_id has exist"),

  /**
   * update fail
   */
  CODE_68031(68031, "update fail"),

  /**
   * busi_id not exist
   */
  CODE_68032(68032, "busi_id not exist"),

  /**
   * store no exist
   */
  CODE_68033(68033, "store no exist"),

  /**
   * miss product number
   */
  CODE_68034(68034, "miss product number"),

  /**
   * miss wxa order detail path
   */
  CODE_68035(68035, "miss wxa order detail path"),

  /**
   * there is no enough products to refund
   */
  CODE_68036(68036, "there is no enough products to refund"),

  /**
   * invalid refund info
   */
  CODE_68037(68037, "invalid refund info"),

  /**
   * shipped but no fast mail info
   */
  CODE_68038(68038, "shipped but no fast mail info"),

  /**
   * invalid wechat pay no
   */
  CODE_68039(68039, "invalid wechat pay no"),

  /**
   * all product has been refunded, the  order can not be finished
   */
  CODE_68040(68040, "all product has been refunded, the  order can not be finished"),

  /**
   * invalid service create time, it must  bigger than the time of order
   */
  CODE_68041(68041, "invalid service create time, it must  bigger than the time of order"),

  /**
   * invalid total cost, it must be  smaller than the sum of product and shipping cost
   */
  CODE_68042(68042, "invalid total cost, it must be  smaller than the sum of product and shipping cost"),

  /**
   * invalid role
   */
  CODE_68043(68043, "invalid role"),

  /**
   * invalid service_available args
   */
  CODE_68044(68044, "invalid service_available args"),

  /**
   * invalid order type
   */
  CODE_68045(68045, "invalid order type"),

  /**
   * invalid order deliver type
   */
  CODE_68046(68046, "invalid order deliver type"),

  /**
   * require store_id
   */
  CODE_68500(68500, "require store_id"),

  /**
   * invalid store_id
   */
  CODE_68501(68501, "invalid store_id"),

  /**
   * invalid parameter, parameter is zero or missing
   */
  CODE_71001(71001, "invalid parameter, parameter is zero or missing"),

  /**
   * invalid orderid, may be the other  parameter not fit with orderid
   */
  CODE_71002(71002, "invalid orderid, may be the other  parameter not fit with orderid"),

  /**
   * coin not enough
   */
  CODE_71003(71003, "coin not enough"),

  /**
   * card is expired
   */
  CODE_71004(71004, "card is expired"),

  /**
   * limit exe count
   */
  CODE_71005(71005, "limit exe count"),

  /**
   * limit coin count, 1 <= coin_count <= 100000
   */
  CODE_71006(71006, "limit coin count, 1 <= coin_count <= 100000"),

  /**
   * order finish
   */
  CODE_71007(71007, "order finish"),

  /**
   * order time out
   */
  CODE_71008(71008, "order time out"),

  /**
   * no match card
   */
  CODE_72001(72001, "no match card"),

  /**
   * mchid is not bind appid
   */
  CODE_72002(72002, "mchid is not bind appid"),

  /**
   * invalid card type, need member card
   */
  CODE_72003(72003, "invalid card type, need member card"),

  /**
   * mchid is occupied by the other appid
   */
  CODE_72004(72004, "mchid is occupied by the other appid"),

  /**
   * out of mchid size limit
   */
  CODE_72005(72005, "out of mchid size limit"),

  /**
   * invald title
   */
  CODE_72006(72006, "invald title"),

  /**
   * invalid reduce cost, can not less than 100
   */
  CODE_72007(72007, "invalid reduce cost, can not less than 100"),

  /**
   * invalid least cost, most larger than reduce cost
   */
  CODE_72008(72008, "invalid least cost, most larger than reduce cost"),

  /**
   * invalid get limit, can not over 50
   */
  CODE_72009(72009, "invalid get limit, can not over 50"),

  /**
   * invalid mchid
   */
  CODE_72010(72010, "invalid mchid"),

  /**
   * invalid activate_ticket.Maybe this  ticket is not belong this AppId
   */
  CODE_72011(72011, "invalid activate_ticket.Maybe this  ticket is not belong this AppId"),

  /**
   * activate_ticket has been expired
   */
  CODE_72012(72012, "activate_ticket has been expired"),

  /**
   * unauthorized order_id or authorization is expired
   */
  CODE_72013(72013, "unauthorized order_id or authorization is expired"),

  /**
   * task card share stock can not modify stock
   */
  CODE_72014(72014, "task card share stock can not modify stock"),

  /**
   * unauthorized create invoice
   */
  CODE_72015(72015, "unauthorized create invoice"),

  /**
   * unauthorized create member card
   */
  CODE_72016(72016, "unauthorized create member card"),

  /**
   * invalid invoice title
   */
  CODE_72017(72017, "invalid invoice title"),

  /**
   * duplicate order id, invoice had inserted to user
   */
  CODE_72018(72018, "duplicate order id, invoice had inserted to user"),

  /**
   * limit msg operation card list size, must <= 5
   */
  CODE_72019(72019, "limit msg operation card list size, must <= 5"),

  /**
   * limit consume in use limit
   */
  CODE_72020(72020, "limit consume in use limit"),

  /**
   * unauthorized create general card
   */
  CODE_72021(72021, "unauthorized create general card"),

  /**
   * user unexpected, please add user to white list
   */
  CODE_72022(72022, "user unexpected, please add user to white list"),

  /**
   * invoice has been lock by others
   */
  CODE_72023(72023, "invoice has been lock by others"),

  /**
   * invoice status error
   */
  CODE_72024(72024, "invoice status error"),

  /**
   * invoice token error
   */
  CODE_72025(72025, "invoice token error"),

  /**
   * need set wx_activate true
   */
  CODE_72026(72026, "need set wx_activate true"),

  /**
   * invoice action error
   */
  CODE_72027(72027, "invoice action error"),

  /**
   * invoice never set pay mch info
   */
  CODE_72028(72028, "invoice never set pay mch info"),

  /**
   * invoice never set auth field
   */
  CODE_72029(72029, "invoice never set auth field"),

  /**
   * invalid mchid
   */
  CODE_72030(72030, "invalid mchid"),

  /**
   * invalid params
   */
  CODE_72031(72031, "invalid params"),

  /**
   * pay gift card rule expired
   */
  CODE_72032(72032, "pay gift card rule expired"),

  /**
   * pay gift card rule status err
   */
  CODE_72033(72033, "pay gift card rule status err"),

  /**
   * invlid rule id
   */
  CODE_72034(72034, "invlid rule id"),

  /**
   * biz reject insert
   */
  CODE_72035(72035, "biz reject insert"),

  /**
   * invoice is busy, try again please
   */
  CODE_72036(72036, "invoice is busy, try again please"),

  /**
   * invoice owner error
   */
  CODE_72037(72037, "invoice owner error"),

  /**
   * invoice order never auth
   */
  CODE_72038(72038, "invoice order never auth"),

  /**
   * invoice must be lock first
   */
  CODE_72039(72039, "invoice must be lock first"),

  /**
   * invoice pdf error
   */
  CODE_72040(72040, "invoice pdf error"),

  /**
   * billing_code and billing_no invalid
   */
  CODE_72041(72041, "billing_code and billing_no invalid"),

  /**
   * billing_code and billing_no repeated
   */
  CODE_72042(72042, "billing_code and billing_no repeated"),

  /**
   * billing_code or billing_no size error
   */
  CODE_72043(72043, "billing_code or billing_no size error"),

  /**
   * scan text out of time
   */
  CODE_72044(72044, "scan text out of time"),

  /**
   * check_code is empty
   */
  CODE_72045(72045, "check_code is empty"),

  /**
   * pdf_url is invalid
   */
  CODE_72046(72046, "pdf_url is invalid"),

  /**
   * pdf billing_code or pdf billing_no is error
   */
  CODE_72047(72047, "pdf billing_code or pdf billing_no is error"),

  /**
   * insert too many invoice, need auth again
   */
  CODE_72048(72048, "insert too many invoice, need auth again"),

  /**
   * never auth
   */
  CODE_72049(72049, "never auth"),

  /**
   * auth expired, need auth again
   */
  CODE_72050(72050, "auth expired, need auth again"),

  /**
   * app type error
   */
  CODE_72051(72051, "app type error"),

  /**
   * get too many invoice
   */
  CODE_72052(72052, "get too many invoice"),

  /**
   * user never auth
   */
  CODE_72053(72053, "user never auth"),

  /**
   * invoices is inserting, wait a moment please
   */
  CODE_72054(72054, "invoices is inserting, wait a moment please"),

  /**
   * too many invoices
   */
  CODE_72055(72055, "too many invoices"),

  /**
   * order_id repeated, please check order_id
   */
  CODE_72056(72056, "order_id repeated, please check order_id"),

  /**
   * today insert limit
   */
  CODE_72057(72057, "today insert limit"),

  /**
   * callback biz error
   */
  CODE_72058(72058, "callback biz error"),

  /**
   * this invoice is giving to others, wait a moment please
   */
  CODE_72059(72059, "this invoice is giving to others, wait a moment please"),

  /**
   * this invoice has been cancelled,  check the reimburse_status please
   */
  CODE_72060(72060, "this invoice has been cancelled,  check the reimburse_status please"),

  /**
   * this invoice has been closed, check  the reimburse_status please
   */
  CODE_72061(72061, "this invoice has been closed, check  the reimburse_status please"),

  /**
   * this code_auth_key is limited, try  other code_auth_key please
   */
  CODE_72062(72062, "this code_auth_key is limited, try  other code_auth_key please"),

  /**
   * biz contact is empty, set contact first please
   */
  CODE_72063(72063, "biz contact is empty, set contact first please"),

  /**
   * tbc error
   */
  CODE_72064(72064, "tbc error"),

  /**
   * tbc logic error
   */
  CODE_72065(72065, "tbc logic error"),

  /**
   * the card is send for advertisement,  not allow modify time and budget
   */
  CODE_72066(72066, "the card is send for advertisement,  not allow modify time and budget"),

  /**
   * BatchInsertAuthKey_Expired
   */
  CODE_72067(72067, "BatchInsertAuthKey_Expired"),

  /**
   * BatchInsertAuthKey_Owner
   */
  CODE_72068(72068, "BatchInsertAuthKey_Owner"),

  /**
   * BATCHTASKRUN_ERROR
   */
  CODE_72069(72069, "BATCHTASKRUN_ERROR"),

  /**
   * BIZ_TITLE_KEY_OUT_TIME
   */
  CODE_72070(72070, "BIZ_TITLE_KEY_OUT_TIME"),

  /**
   * Discern_GaoPeng_Error
   */
  CODE_72071(72071, "Discern_GaoPeng_Error"),

  /**
   * Discern_Type_Error
   */
  CODE_72072(72072, "Discern_Type_Error"),

  /**
   * Fee_Error
   */
  CODE_72073(72073, "Fee_Error"),

  /**
   * HAS_Auth
   */
  CODE_72074(72074, "HAS_Auth"),

  /**
   * HAS_SEND
   */
  CODE_72075(72075, "HAS_SEND"),

  /**
   * INVOICESIGN
   */
  CODE_72076(72076, "INVOICESIGN"),

  /**
   * KEY_DELETED
   */
  CODE_72077(72077, "KEY_DELETED"),

  /**
   * KEY_EXPIRED
   */
  CODE_72078(72078, "KEY_EXPIRED"),

  /**
   * MOUNT_ERROR
   */
  CODE_72079(72079, "MOUNT_ERROR"),

  /**
   * NO_FOUND
   */
  CODE_72080(72080, "NO_FOUND"),

  /**
   * No_Pull_Pdf
   */
  CODE_72081(72081, "No_Pull_Pdf"),

  /**
   * PDF_CHECK_ERROR
   */
  CODE_72082(72082, "PDF_CHECK_ERROR"),

  /**
   * PULL_PDF_FAIL
   */
  CODE_72083(72083, "PULL_PDF_FAIL"),

  /**
   * PUSH_BIZ_EMPTY
   */
  CODE_72084(72084, "PUSH_BIZ_EMPTY"),

  /**
   * SDK_APPID_ERROR
   */
  CODE_72085(72085, "SDK_APPID_ERROR"),

  /**
   * SDK_BIZ_ERROR
   */
  CODE_72086(72086, "SDK_BIZ_ERROR"),

  /**
   * SDK_URL_ERROR
   */
  CODE_72087(72087, "SDK_URL_ERROR"),

  /**
   * Search_Title_Fail
   */
  CODE_72088(72088, "Search_Title_Fail"),

  /**
   * TITLE_BUSY
   */
  CODE_72089(72089, "TITLE_BUSY"),

  /**
   * TITLE_NO_FOUND
   */
  CODE_72090(72090, "TITLE_NO_FOUND"),

  /**
   * TOKEN_ERR
   */
  CODE_72091(72091, "TOKEN_ERR"),

  /**
   * USER_TITLE_NOT_FOUND
   */
  CODE_72092(72092, "USER_TITLE_NOT_FOUND"),

  /**
   * Verify_3rd_Fail
   */
  CODE_72093(72093, "Verify_3rd_Fail"),

  /**
   * sys error make out invoice failed
   */
  CODE_73000(73000, "sys error make out invoice failed"),

  /**
   * wxopenid error
   */
  CODE_73001(73001, "wxopenid error"),

  /**
   * ddh orderid empty
   */
  CODE_73002(73002, "ddh orderid empty"),

  /**
   * wxopenid empty
   */
  CODE_73003(73003, "wxopenid empty"),

  /**
   * fpqqlsh empty
   */
  CODE_73004(73004, "fpqqlsh empty"),

  /**
   * not a commercial
   */
  CODE_73005(73005, "not a commercial"),

  /**
   * kplx empty
   */
  CODE_73006(73006, "kplx empty"),

  /**
   * nsrmc empty
   */
  CODE_73007(73007, "nsrmc empty"),

  /**
   * nsrdz empty
   */
  CODE_73008(73008, "nsrdz empty"),

  /**
   * nsrdh empty
   */
  CODE_73009(73009, "nsrdh empty"),

  /**
   * ghfmc empty
   */
  CODE_73010(73010, "ghfmc empty"),

  /**
   * kpr empty
   */
  CODE_73011(73011, "kpr empty"),

  /**
   * jshj empty
   */
  CODE_73012(73012, "jshj empty"),

  /**
   * hjje empty
   */
  CODE_73013(73013, "hjje empty"),

  /**
   * hjse empty
   */
  CODE_73014(73014, "hjse empty"),

  /**
   * hylx empty
   */
  CODE_73015(73015, "hylx empty"),

  /**
   * nsrsbh empty
   */
  CODE_73016(73016, "nsrsbh empty"),

  /**
   * kaipiao plat error
   */
  CODE_73100(73100, "kaipiao plat error"),

  /**
   * nsrsbh not cmp
   */
  CODE_73101(73101, "nsrsbh not cmp"),

  /**
   * invalid wxa appid in url_cell, wxa  appid is need to bind biz appid
   */
  CODE_73103(73103, "invalid wxa appid in url_cell, wxa  appid is need to bind biz appid"),

  /**
   * reach frequency limit
   */
  CODE_73104(73104, "reach frequency limit"),

  /**
   * Kp plat make invoice timeout, please  try again with the same fpqqlsh
   */
  CODE_73105(73105, "Kp plat make invoice timeout, please  try again with the same fpqqlsh"),

  /**
   * Fpqqlsh exist with different ddh
   */
  CODE_73106(73106, "Fpqqlsh exist with different ddh"),

  /**
   * Fpqqlsh is processing, please wait and query later
   */
  CODE_73107(73107, "Fpqqlsh is processing, please wait and query later"),

  /**
   * This ddh with other fpqqlsh already exist
   */
  CODE_73108(73108, "This ddh with other fpqqlsh already exist"),

  /**
   * This Fpqqlsh not exist in kpplat
   */
  CODE_73109(73109, "This Fpqqlsh not exist in kpplat"),

  /**
   * get card detail by card id and code fail
   */
  CODE_73200(73200, "get card detail by card id and code fail"),

  /**
   * get cloud invoice record fail
   */
  CODE_73201(73201, "get cloud invoice record fail"),

  /**
   * get appinfo fail
   */
  CODE_73202(73202, "get appinfo fail"),

  /**
   * get invoice category or rule kv error
   */
  CODE_73203(73203, "get invoice category or rule kv error"),

  /**
   * request card not exist
   */
  CODE_73204(73204, "request card not exist"),

  /**
   * 朋友的券玩法升级中，当前暂停创建，请创建其他类型卡券
   */
  CODE_73205(73205, "朋友的券玩法升级中，当前暂停创建，请创建其他类型卡券"),

  /**
   * 朋友的券玩法升级中，当前暂停券点充值，请创建其他类型卡券
   */
  CODE_73206(73206, "朋友的券玩法升级中，当前暂停券点充值，请创建其他类型卡券"),

  /**
   * 朋友的券玩法升级中，当前暂停开通券点账户
   */
  CODE_73207(73207, "朋友的券玩法升级中，当前暂停开通券点账户"),

  /**
   * 朋友的券玩法升级中，当前不支持修改库存
   */
  CODE_73208(73208, "朋友的券玩法升级中，当前不支持修改库存"),

  /**
   * 朋友的券玩法升级中，当前不支持修改有效期
   */
  CODE_73209(73209, "朋友的券玩法升级中，当前不支持修改有效期"),

  /**
   * 当前批次不支持修改卡券批次库存
   */
  CODE_73210(73210, "当前批次不支持修改卡券批次库存"),

  /**
   * 不再支持配置网页链接跳转，请选择小程序替代
   */
  CODE_73211(73211, "不再支持配置网页链接跳转，请选择小程序替代"),

  /**
   * unauthorized backup member
   */
  CODE_73212(73212, "unauthorized backup member"),

  /**
   * invalid code type
   */
  CODE_73213(73213, "invalid code type"),

  /**
   * the user is already a member
   */
  CODE_73214(73214, "the user is already a member"),

  /**
   * 支付打通券能力已下线，请直接使用微信支付代金券API：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter1_1.shtml
   */
  CODE_73215(73215, "支付打通券能力已下线，请直接使用微信支付代金券API：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter1_1.shtml"),

  /**
   * 不合法的按钮名字，请从中选择一个:使用礼品卡/立即使用/去点外卖
   */
  CODE_73216(73216, "不合法的按钮名字，请从中选择一个:使用礼品卡/立即使用/去点外卖"),

  /**
   * 礼品卡本身没有设置appname和path，不允许在修改接口设置
   */
  CODE_73217(73217, "礼品卡本身没有设置appname和path，不允许在修改接口设置"),

  /**
   * 未授权使用礼品卡落地页跳转小程序功能
   */
  CODE_73218(73218, "未授权使用礼品卡落地页跳转小程序功能"),

  /**
   * not find this wx_hotel_id info
   */
  CODE_74000(74000, "not find this wx_hotel_id info"),

  /**
   * request some param empty
   */
  CODE_74001(74001, "request some param empty"),

  /**
   * request some param error
   */
  CODE_74002(74002, "request some param error"),

  /**
   * request some param error
   */
  CODE_74003(74003, "request some param error"),

  /**
   * datetime error
   */
  CODE_74004(74004, "datetime error"),

  /**
   * checkin mode error
   */
  CODE_74005(74005, "checkin mode error"),

  /**
   * carid from error
   */
  CODE_74007(74007, "carid from error"),

  /**
   * this hotel routecode not exist
   */
  CODE_74008(74008, "this hotel routecode not exist"),

  /**
   * this hotel routecode info error contract developer
   */
  CODE_74009(74009, "this hotel routecode info error contract developer"),

  /**
   * maybe not support report mode
   */
  CODE_74010(74010, "maybe not support report mode"),

  /**
   * pic deocde not ok maybe its not good picdata
   */
  CODE_74011(74011, "pic deocde not ok maybe its not good picdata"),

  /**
   * verify sys erro
   */
  CODE_74021(74021, "verify sys erro"),

  /**
   * inner police erro
   */
  CODE_74022(74022, "inner police erro"),

  /**
   * unable to detect the face
   */
  CODE_74023(74023, "unable to detect the face"),

  /**
   * report checkin 2 lvye sys erro
   */
  CODE_74040(74040, "report checkin 2 lvye sys erro"),

  /**
   * report checkou 2 lvye sys erro
   */
  CODE_74041(74041, "report checkou 2 lvye sys erro"),

  /**
   * some param emtpy please check
   */
  CODE_75001(75001, "some param emtpy please check"),

  /**
   * param illegal please check
   */
  CODE_75002(75002, "param illegal please check"),

  /**
   * sys error kv store error
   */
  CODE_75003(75003, "sys error kv store error"),

  /**
   * sys error kvstring store error
   */
  CODE_75004(75004, "sys error kvstring store error"),

  /**
   * product not exist please check your product_id
   */
  CODE_75005(75005, "product not exist please check your product_id"),

  /**
   * order not exist please check order_id and buyer_appid
   */
  CODE_75006(75006, "order not exist please check order_id and buyer_appid"),

  /**
   * do not allow this status to change  please check this order_id status now
   */
  CODE_75007(75007, "do not allow this status to change  please check this order_id status now"),

  /**
   * product has exist please use new id
   */
  CODE_75008(75008, "product has exist please use new id"),

  /**
   * notify order status failed
   */
  CODE_75009(75009, "notify order status failed"),

  /**
   * buyer bussiness info not exist
   */
  CODE_75010(75010, "buyer bussiness info not exist"),

  /**
   * you had registered
   */
  CODE_75011(75011, "you had registered"),

  /**
   * store image key to kv error, please try again
   */
  CODE_75012(75012, "store image key to kv error, please try again"),

  /**
   * get image fail, please check you image key
   */
  CODE_75013(75013, "get image fail, please check you image key"),

  /**
   * this key is not belong to you
   */
  CODE_75014(75014, "this key is not belong to you"),

  /**
   * this key is expired
   */
  CODE_75015(75015, "this key is expired"),

  /**
   * encrypt decode key fail
   */
  CODE_75016(75016, "encrypt decode key fail"),

  /**
   * encrypt encode key fail
   */
  CODE_75017(75017, "encrypt encode key fail"),

  /**
   * bind buyer business info fail please contact us
   */
  CODE_75018(75018, "bind buyer business info fail please contact us"),

  /**
   * this key is empty, user may not upload file
   */
  CODE_75019(75019, "this key is empty, user may not upload file"),

  /**
   * 系统错误，请稍后再试
   */
  CODE_80000(80000, "系统错误，请稍后再试"),

  /**
   * 参数格式校验错误
   */
  CODE_80001(80001, "参数格式校验错误"),

  /**
   * 签名失败
   */
  CODE_80002(80002, "签名失败"),

  /**
   * 该日期订单未生成
   */
  CODE_80003(80003, "该日期订单未生成"),

  /**
   * 用户未绑卡
   */
  CODE_80004(80004, "用户未绑卡"),

  /**
   * 姓名不符
   */
  CODE_80005(80005, "姓名不符"),

  /**
   * 身份证不符
   */
  CODE_80006(80006, "身份证不符"),

  /**
   * 获取城市信息失败
   */
  CODE_80007(80007, "获取城市信息失败"),

  /**
   * 未找到指定少儿信息
   */
  CODE_80008(80008, "未找到指定少儿信息"),

  /**
   * 少儿身份证不符
   */
  CODE_80009(80009, "少儿身份证不符"),

  /**
   * 少儿未绑定
   */
  CODE_80010(80010, "少儿未绑定"),

  /**
   * 签约号不符
   */
  CODE_80011(80011, "签约号不符"),

  /**
   * 该地区局方配置不存在
   */
  CODE_80012(80012, "该地区局方配置不存在"),

  /**
   * 调用方appid与局方配置不匹配
   */
  CODE_80013(80013, "调用方appid与局方配置不匹配"),

  /**
   * 获取消息账号失败
   */
  CODE_80014(80014, "获取消息账号失败"),

  /**
   * 非法的插件版本
   */
  CODE_80066(80066, "非法的插件版本"),

  /**
   * 找不到使用的插件
   */
  CODE_80067(80067, "找不到使用的插件"),

  /**
   * 没有权限使用该插件
   */
  CODE_80082(80082, "没有权限使用该插件"),

  /**
   * 商家未接入
   */
  CODE_80101(80101, "商家未接入"),

  /**
   * 实名校验code不存在
   */
  CODE_80111(80111, "实名校验code不存在"),

  /**
   * code并发冲突
   */
  CODE_80112(80112, "code并发冲突"),

  /**
   * 无效code
   */
  CODE_80113(80113, "无效code"),

  /**
   * report_type无效
   */
  CODE_80201(80201, "report_type无效"),

  /**
   * service_type无效
   */
  CODE_80202(80202, "service_type无效"),

  /**
   * 申请单不存在
   */
  CODE_80300(80300, "申请单不存在"),

  /**
   * 申请单不属于该账号
   */
  CODE_80301(80301, "申请单不属于该账号"),

  /**
   * 激活号段有重叠
   */
  CODE_80302(80302, "激活号段有重叠"),

  /**
   * 码格式错误
   */
  CODE_80303(80303, "码格式错误"),

  /**
   * 该码未激活
   */
  CODE_80304(80304, "该码未激活"),

  /**
   * 激活失败
   */
  CODE_80305(80305, "激活失败"),

  /**
   * 码索引超出申请范围
   */
  CODE_80306(80306, "码索引超出申请范围"),

  /**
   * 申请已存在
   */
  CODE_80307(80307, "申请已存在"),

  /**
   * 子任务未完成
   */
  CODE_80308(80308, "子任务未完成"),

  /**
   * 子任务文件过期
   */
  CODE_80309(80309, "子任务文件过期"),

  /**
   * 子任务不存在
   */
  CODE_80310(80310, "子任务不存在"),

  /**
   * 获取文件失败
   */
  CODE_80311(80311, "获取文件失败"),

  /**
   * 加密数据失败
   */
  CODE_80312(80312, "加密数据失败"),

  /**
   * 加密数据密钥不存在，请联系接口人申请
   */
  CODE_80313(80313, "加密数据密钥不存在，请联系接口人申请"),

  /**
   * can not set page_id in AddGiftCardPage
   */
  CODE_81000(81000, "can not set page_id in AddGiftCardPage"),

  /**
   * card_list is empty
   */
  CODE_81001(81001, "card_list is empty"),

  /**
   * card_id is not giftcard
   */
  CODE_81002(81002, "card_id is not giftcard"),

  /**
   * banner_pic_url is empty
   */
  CODE_81004(81004, "banner_pic_url is empty"),

  /**
   * banner_pic_url is not from cdn
   */
  CODE_81005(81005, "banner_pic_url is not from cdn"),

  /**
   * giftcard_wrap_pic_url_list is empty
   */
  CODE_81006(81006, "giftcard_wrap_pic_url_list is empty"),

  /**
   * giftcard_wrap_pic_url_list is not from cdn
   */
  CODE_81007(81007, "giftcard_wrap_pic_url_list is not from cdn"),

  /**
   * address is empty
   */
  CODE_81008(81008, "address is empty"),

  /**
   * service_phone is invalid
   */
  CODE_81009(81009, "service_phone is invalid"),

  /**
   * biz_description is empty
   */
  CODE_81010(81010, "biz_description is empty"),

  /**
   * invalid page_id
   */
  CODE_81011(81011, "invalid page_id"),

  /**
   * invalid order_id
   */
  CODE_81012(81012, "invalid order_id"),

  /**
   * invalid TIME_RANGE, begin_time +  31day must less than end_time
   */
  CODE_81013(81013, "invalid TIME_RANGE, begin_time +  31day must less than end_time"),

  /**
   * invalid count! count must equal or less than 100
   */
  CODE_81014(81014, "invalid count! count must equal or less than 100"),

  /**
   * invalid category_index OR   category.title is empty OR is_banner but has_category_index
   */
  CODE_81015(81015, "invalid category_index OR   category.title is empty OR is_banner but has_category_index"),

  /**
   * is_banner is more than 1
   */
  CODE_81016(81016, "is_banner is more than 1"),

  /**
   * order status error, please check pay status or gifting_status
   */
  CODE_81017(81017, "order status error, please check pay status or gifting_status"),

  /**
   * refund reduplicate, the order is already refunded
   */
  CODE_81018(81018, "refund reduplicate, the order is already refunded"),

  /**
   * lock order fail! the order is refunding by another request
   */
  CODE_81019(81019, "lock order fail! the order is refunding by another request"),

  /**
   * Invalid Args! page_id.size!=0 but  all==true, or page_id.size==0 but all==false.
   */
  CODE_81020(81020, "Invalid Args! page_id.size!=0 but  all==true, or page_id.size==0 but all==false."),

  /**
   * Empty theme_pic_url.
   */
  CODE_81021(81021, "Empty theme_pic_url."),

  /**
   * Empty theme.title.
   */
  CODE_81022(81022, "Empty theme.title."),

  /**
   * Empty theme.title_title.
   */
  CODE_81023(81023, "Empty theme.title_title."),

  /**
   * Empty theme.item_list.
   */
  CODE_81024(81024, "Empty theme.item_list."),

  /**
   * Empty theme.pic_item_list.
   */
  CODE_81025(81025, "Empty theme.pic_item_list."),

  /**
   * Invalid theme.title.length .
   */
  CODE_81026(81026, "Invalid theme.title.length ."),

  /**
   * Empty background_pic_url.
   */
  CODE_81027(81027, "Empty background_pic_url."),

  /**
   * Empty default_gifting_msg.
   */
  CODE_81028(81028, "Empty default_gifting_msg."),

  /**
   * Duplicate order_id
   */
  CODE_81029(81029, "Duplicate order_id"),

  /**
   * PreAlloc code fail
   */
  CODE_81030(81030, "PreAlloc code fail"),

  /**
   * Too many theme participate_activity
   */
  CODE_81031(81031, "Too many theme participate_activity"),

  /**
   * biz_template_id not exist
   */
  CODE_82000(82000, "biz_template_id not exist"),

  /**
   * result_page_style_id not exist
   */
  CODE_82001(82001, "result_page_style_id not exist"),

  /**
   * deal_msg_style_id not exist
   */
  CODE_82002(82002, "deal_msg_style_id not exist"),

  /**
   * card_style_id not exist
   */
  CODE_82003(82003, "card_style_id not exist"),

  /**
   * biz template not audit OK
   */
  CODE_82010(82010, "biz template not audit OK"),

  /**
   * biz template banned
   */
  CODE_82011(82011, "biz template banned"),

  /**
   * user not use service first
   */
  CODE_82020(82020, "user not use service first"),

  /**
   * exceed long period
   */
  CODE_82021(82021, "exceed long period"),

  /**
   * exceed long period max send cnt
   */
  CODE_82022(82022, "exceed long period max send cnt"),

  /**
   * exceed short period max send cnt
   */
  CODE_82023(82023, "exceed short period max send cnt"),

  /**
   * exceed data size limit
   */
  CODE_82024(82024, "exceed data size limit"),

  /**
   * invalid url
   */
  CODE_82025(82025, "invalid url"),

  /**
   * service disabled
   */
  CODE_82026(82026, "service disabled"),

  /**
   * invalid miniprogram appid
   */
  CODE_82027(82027, "invalid miniprogram appid"),

  /**
   * wx_cs_code should not be empty.
   */
  CODE_82100(82100, "wx_cs_code should not be empty."),

  /**
   * wx_cs_code is invalid.
   */
  CODE_82101(82101, "wx_cs_code is invalid."),

  /**
   * wx_cs_code is expire.
   */
  CODE_82102(82102, "wx_cs_code is expire."),

  /**
   * user_ip should not be empty.
   */
  CODE_82103(82103, "user_ip should not be empty."),

  /**
   * 公众平台账号与服务id不匹配
   */
  CODE_82200(82200, "公众平台账号与服务id不匹配"),

  /**
   * 该停车场已存在，请勿重复添加
   */
  CODE_82201(82201, "该停车场已存在，请勿重复添加"),

  /**
   * 该停车场信息不存在，请先导入
   */
  CODE_82202(82202, "该停车场信息不存在，请先导入"),

  /**
   * 停车场价格格式不正确
   */
  CODE_82203(82203, "停车场价格格式不正确"),

  /**
   * appid与code不匹配
   */
  CODE_82204(82204, "appid与code不匹配"),

  /**
   * wx_park_code字段为空
   */
  CODE_82205(82205, "wx_park_code字段为空"),

  /**
   * wx_park_code无效或已过期
   */
  CODE_82206(82206, "wx_park_code无效或已过期"),

  /**
   * 电话字段为空
   */
  CODE_82207(82207, "电话字段为空"),

  /**
   * 关闭时间格式不正确
   */
  CODE_82208(82208, "关闭时间格式不正确"),

  /**
   * 该appid不支持开通城市服务插件
   */
  CODE_82300(82300, "该appid不支持开通城市服务插件"),

  /**
   * 添加插件失败
   */
  CODE_82301(82301, "添加插件失败"),

  /**
   * 未添加城市服务插件
   */
  CODE_82302(82302, "未添加城市服务插件"),

  /**
   * fileid无效
   */
  CODE_82303(82303, "fileid无效"),

  /**
   * 临时文件过期
   */
  CODE_82304(82304, "临时文件过期"),

  /**
   * there is some param not exist
   */
  CODE_83000(83000, "there is some param not exist"),

  /**
   * system error
   */
  CODE_83001(83001, "system error"),

  /**
   * create_url_sence_failed
   */
  CODE_83002(83002, "create_url_sence_failed"),

  /**
   * appid maybe error or retry
   */
  CODE_83003(83003, "appid maybe error or retry"),

  /**
   * create appid auth failed or retry
   */
  CODE_83004(83004, "create appid auth failed or retry"),

  /**
   * wxwebencrytoken errro
   */
  CODE_83005(83005, "wxwebencrytoken errro"),

  /**
   * wxwebencrytoken expired or no exist
   */
  CODE_83006(83006, "wxwebencrytoken expired or no exist"),

  /**
   * wxwebencrytoken expired
   */
  CODE_83007(83007, "wxwebencrytoken expired"),

  /**
   * wxwebencrytoken no auth
   */
  CODE_83008(83008, "wxwebencrytoken no auth"),

  /**
   * wxwebencrytoken not the mate with openid
   */
  CODE_83009(83009, "wxwebencrytoken not the mate with openid"),

  /**
   * no exist service
   */
  CODE_83200(83200, "no exist service"),

  /**
   * uin has not the service
   */
  CODE_83201(83201, "uin has not the service"),

  /**
   * params is not json or not json array
   */
  CODE_83202(83202, "params is not json or not json array"),

  /**
   * params num exceed 10
   */
  CODE_83203(83203, "params num exceed 10"),

  /**
   * object has not key
   */
  CODE_83204(83204, "object has not key"),

  /**
   * key is not string
   */
  CODE_83205(83205, "key is not string"),

  /**
   * object has not value
   */
  CODE_83206(83206, "object has not value"),

  /**
   * value is not string
   */
  CODE_83207(83207, "value is not string"),

  /**
   * key or value is empty
   */
  CODE_83208(83208, "key or value is empty"),

  /**
   * key exist repeated
   */
  CODE_83209(83209, "key exist repeated"),

  /**
   * invalid identify id
   */
  CODE_84001(84001, "invalid identify id"),

  /**
   * user data expired
   */
  CODE_84002(84002, "user data expired"),

  /**
   * user data not exist
   */
  CODE_84003(84003, "user data not exist"),

  /**
   * video upload fail!
   */
  CODE_84004(84004, "video upload fail!"),

  /**
   * video download fail! please try again
   */
  CODE_84005(84005, "video download fail! please try again"),

  /**
   * name or id_card_number empty
   */
  CODE_84006(84006, "name or id_card_number empty"),

  /**
   * 微信号不存在或微信号设置为不可搜索  user not exist or user cannot be searched
   */
  CODE_85001(85001, "微信号不存在或微信号设置为不可搜索"),

  /**
   * 小程序绑定的体验者数量达到上限  number of tester reach bind limit
   */
  CODE_85002(85002, "小程序绑定的体验者数量达到上限"),

  /**
   * 微信号绑定的小程序体验者达到上限  user already bind too many weapps
   */
  CODE_85003(85003, "微信号绑定的小程序体验者达到上限"),

  /**
   * 微信号已经绑定  user already bind
   */
  CODE_85004(85004, "微信号已经绑定"),

  /**
   * appid not bind weapp
   */
  CODE_85005(85005, "appid not bind weapp"),

  /**
   * 标签格式错误  tag is in invalid format
   */
  CODE_85006(85006, "标签格式错误"),

  /**
   * 页面路径错误  page is in invalid format
   */
  CODE_85007(85007, "页面路径错误"),

  /**
   * 当前小程序没有已经审核通过的类目，请添加类目成功后重试  category is in invalid format
   */
  CODE_85008(85008, "当前小程序没有已经审核通过的类目，请添加类目成功后重试"),

  /**
   * 已经有正在审核的版本  already submit a version under auditing
   */
  CODE_85009(85009, "已经有正在审核的版本"),

  /**
   * item_list 有项目为空  missing required data
   */
  CODE_85010(85010, "item_list 有项目为空"),

  /**
   * 标题填写错误  title is in invalid format
   */
  CODE_85011(85011, "标题填写错误"),

  /**
   * 无效的审核 id  invalid audit id
   */
  CODE_85012(85012, "无效的审核 id"),

  /**
   * 无效的自定义配置  invalid ext_json, parse fail or containing invalid path
   */
  CODE_85013(85013, "无效的自定义配置"),

  /**
   * 无效的模板编号  template not exist
   */
  CODE_85014(85014, "无效的模板编号"),

  /**
   * 该账号不是小程序账号/版本输入错误
   */
  CODE_85015(85015, "该账号不是小程序账号/版本输入错误"),

  /**
   * 版本输入错误
   */
//    CODE_85015(85015, "版本输入错误"),

  /**
   * 域名数量超过限制 ，总数不能超过1000  exceed valid domain count
   */
  CODE_85016(85016, "域名数量超过限制 ，总数不能超过1000"),

  /**
   * 没有新增域名，请确认小程序已经添加了域名或该域名是否没有在第三方平台添加  no domain to modify after filtered, please confirm the domain has been  set in miniprogram or open
   */
  CODE_85017(85017, "没有新增域名，请确认小程序已经添加了域名或该域名是否没有在第三方平台添加"),

  /**
   * 域名没有在第三方平台设置
   */
  CODE_85018(85018, "域名没有在第三方平台设置"),

  /**
   * 没有审核版本  no version is under auditing
   */
  CODE_85019(85019, "没有审核版本"),

  /**
   * 审核状态未满足发布  status not allowed
   */
  CODE_85020(85020, "审核状态未满足发布"),

  /**
   * status not allowed
   */
  CODE_85021(85021, "status not allowed"),

  /**
   * invalid action
   */
  CODE_85022(85022, "invalid action"),

  /**
   * 审核列表填写的项目数不在 1-5 以内  item size is not in valid range
   */
  CODE_85023(85023, "审核列表填写的项目数不在 1-5 以内"),

  /**
   * need complete material
   */
  CODE_85024(85024, "need complete material"),

  /**
   * this phone reach bind limit
   */
  CODE_85025(85025, "this phone reach bind limit"),

  /**
   * this wechat account reach bind limit
   */
  CODE_85026(85026, "this wechat account reach bind limit"),

  /**
   * this idcard reach bind limit
   */
  CODE_85027(85027, "this idcard reach bind limit"),

  /**
   * this contractor reach bind limit
   */
  CODE_85028(85028, "this contractor reach bind limit"),

  /**
   * nickname has used
   */
  CODE_85029(85029, "nickname has used"),

  /**
   * invalid nickname size(4-30)
   */
  CODE_85030(85030, "invalid nickname size(4-30)"),

  /**
   * nickname is forbidden
   */
  CODE_85031(85031, "nickname is forbidden"),

  /**
   * nickname is complained
   */
  CODE_85032(85032, "nickname is complained"),

  /**
   * nickname is illegal
   */
  CODE_85033(85033, "nickname is illegal"),

  /**
   * nickname is protected
   */
  CODE_85034(85034, "nickname is protected"),

  /**
   * nickname is forbidden for different contractor
   */
  CODE_85035(85035, "nickname is forbidden for different contractor"),

  /**
   * introduction is illegal
   */
  CODE_85036(85036, "introduction is illegal"),

  /**
   * store has added
   */
  CODE_85038(85038, "store has added"),

  /**
   * store has added by others
   */
  CODE_85039(85039, "store has added by others"),

  /**
   * store has added by yourseld
   */
  CODE_85040(85040, "store has added by yourseld"),

  /**
   * credential has used
   */
  CODE_85041(85041, "credential has used"),

  /**
   * nearby reach limit
   */
  CODE_85042(85042, "nearby reach limit"),

  /**
   * 模板错误  invalid template, something wrong?
   */
  CODE_85043(85043, "模板错误"),

  /**
   * 代码包超过大小限制  package exceed max limit
   */
  CODE_85044(85044, "代码包超过大小限制"),

  /**
   * ext_json 有不存在的路径  some path in ext_json not exist
   */
  CODE_85045(85045, "ext_json 有不存在的路径"),

  /**
   * tabBar 中缺少 path  pagepath missing in tabbar list
   */
  CODE_85046(85046, "tabBar 中缺少 path"),

  /**
   * pages 字段为空  pages are empty
   */
  CODE_85047(85047, "pages 字段为空"),

  /**
   * ext_json 解析失败  parse ext_json fail
   */
  CODE_85048(85048, "ext_json 解析失败"),

  /**
   * reach headimg or introduction quota limit
   */
  CODE_85049(85049, "reach headimg or introduction quota limit"),

  /**
   * verifying, don't apply again
   */
  CODE_85050(85050, "verifying, don't apply again"),

  /**
   * version_desc或者preview_info超限  data too large
   */
  CODE_85051(85051, "version_desc或者preview_info超限"),

  /**
   * app is already released
   */
  CODE_85052(85052, "app is already released"),

  /**
   * please apply merchant first
   */
  CODE_85053(85053, "please apply merchant first"),

  /**
   * poi_id is null, please upgrade first
   */
  CODE_85054(85054, "poi_id is null, please upgrade first"),

  /**
   * map_poi_id is invalid
   */
  CODE_85055(85055, "map_poi_id is invalid"),

  /**
   * mediaid is invalid
   */
  CODE_85056(85056, "mediaid is invalid"),

  /**
   * invalid widget data format
   */
  CODE_85057(85057, "invalid widget data format"),

  /**
   * no valid audit_id exist
   */
  CODE_85058(85058, "no valid audit_id exist"),

  /**
   * overseas access deny
   */
  CODE_85059(85059, "overseas access deny"),

  /**
   * invalid taskid
   */
  CODE_85060(85060, "invalid taskid"),

  /**
   * this phone reach bind limit
   */
  CODE_85061(85061, "this phone reach bind limit"),

  /**
   * this phone in black list
   */
  CODE_85062(85062, "this phone in black list"),

  /**
   * idcard in black list
   */
  CODE_85063(85063, "idcard in black list"),

  /**
   * 找不到模板  template not found
   */
  CODE_85064(85064, "找不到模板"),

  /**
   * 模板库已满  template list is full
   */
  CODE_85065(85065, "模板库已满"),

  /**
   * 链接错误  illegal prefix
   */
  CODE_85066(85066, "链接错误"),

  /**
   * input data error
   */
  CODE_85067(85067, "input data error"),

  /**
   * 测试链接不是子链接  test url is not the sub prefix
   */
  CODE_85068(85068, "测试链接不是子链接"),

  /**
   * 校验文件失败  check confirm file fail
   */
  CODE_85069(85069, "校验文件失败"),

  /**
   * 个人类型小程序无法设置二维码规则  prefix in black list
   */
  CODE_85070(85070, "个人类型小程序无法设置二维码规则"),

  /**
   * 已添加该链接，请勿重复添加  prefix added repeated
   */
  CODE_85071(85071, "已添加该链接，请勿重复添加"),

  /**
   * 该链接已被占用  prefix owned by other
   */
  CODE_85072(85072, "该链接已被占用"),

  /**
   * 二维码规则已满  prefix beyond limit
   */
  CODE_85073(85073, "二维码规则已满"),

  /**
   * 小程序未发布, 小程序必须先发布代码才可以发布二维码跳转规则  not published
   */
  CODE_85074(85074, "小程序未发布, 小程序必须先发布代码才可以发布二维码跳转规则"),

  /**
   * 个人类型小程序无法设置二维码规则  can not access
   */
  CODE_85075(85075, "个人类型小程序无法设置二维码规则"),

  /**
   * 小程序类目信息失效（类目中含有官方下架的类目，请重新选择类目）  some category you choose is no longger supported, please choose other  category
   */
  CODE_85077(85077, "小程序类目信息失效（类目中含有官方下架的类目，请重新选择类目）"),

  /**
   * operator info error
   */
  CODE_85078(85078, "operator info error"),

  /**
   * 小程序没有线上版本，不能进行灰度  miniprogram has no online release
   */
  CODE_85079(85079, "小程序没有线上版本，不能进行灰度"),

  /**
   * 小程序提交的审核未审核通过  miniprogram commit not approved
   */
  CODE_85080(85080, "小程序提交的审核未审核通过"),

  /**
   * 无效的发布比例  invalid gray percentage
   */
  CODE_85081(85081, "无效的发布比例"),

  /**
   * 当前的发布比例需要比之前设置的高  gray percentage too low
   */
  CODE_85082(85082, "当前的发布比例需要比之前设置的高"),

  /**
   * 搜索标记位被封禁，无法修改  search status is banned
   */
  CODE_85083(85083, "搜索标记位被封禁，无法修改"),

  /**
   * 非法的 status 值，只能填 0 或者 1  search status invalid
   */
  CODE_85084(85084, "非法的 status 值，只能填 0 或者 1"),

  /**
   * 小程序提审数量已达本月上限，请点击查看  submit audit reach limit  pleasetry later
   */
  CODE_85085(85085, "小程序提审数量已达本月上限，请点击查看"),

  /**
   * 提交代码审核之前需提前上传代码  must commit before submit audit
   */
  CODE_85086(85086, "提交代码审核之前需提前上传代码"),

  /**
   * 小程序已使用 api navigateToMiniProgram，请声明跳转 appid 列表后再次提交  navigatetominiprogram appid list empty
   */
  CODE_85087(85087, "小程序已使用 api navigateToMiniProgram，请声明跳转 appid 列表后再次提交"),

  /**
   * no qbase privilege
   */
  CODE_85088(85088, "no qbase privilege"),

  /**
   * config not found
   */
  CODE_85089(85089, "config not found"),

  /**
   * wait and commit for this exappid later
   */
  CODE_85090(85090, "wait and commit for this exappid later"),

  /**
   * 小程序的搜索开关被关闭。请访问设置页面打开开关再重试  search status was turned off
   */
  CODE_85091(85091, "小程序的搜索开关被关闭。请访问设置页面打开开关再重试"),

  /**
   * preview_info格式错误  invalid preview_info format
   */
  CODE_85092(85092, "preview_info格式错误"),

  /**
   * preview_info 视频或者图片个数超限  invalid preview_info size
   */
  CODE_85093(85093, "preview_info 视频或者图片个数超限"),

  /**
   * 需提供审核机制说明信息  need add ugc declare
   */
  CODE_85094(85094, "需提供审核机制说明信息"),

  /**
   * 小程序不能发送该运动类型或运动类型不存在
   */
  CODE_85101(85101, "小程序不能发送该运动类型或运动类型不存在"),

  /**
   * 数值异常
   */
  CODE_85102(85102, "数值异常"),

  /**
   * 不是由第三方代小程序进行调用  should be called only from third party
   */
  CODE_86000(86000, "不是由第三方代小程序进行调用"),

  /**
   * 不存在第三方的已经提交的代码  component experience version not exists
   */
  CODE_86001(86001, "不存在第三方的已经提交的代码"),

  /**
   * 小程序还未设置昵称、头像、简介。请先设置完后再重新提交  miniprogram have not completed init procedure
   */
  CODE_86002(86002, "小程序还未设置昵称、头像、简介。请先设置完后再重新提交"),

  /**
   * component do not has category mall
   */
  CODE_86003(86003, "component do not has category mall"),

  /**
   * invalid wechat
   */
  CODE_86004(86004, "invalid wechat"),

  /**
   * wechat limit frequency
   */
  CODE_86005(86005, "wechat limit frequency"),

  /**
   * has no quota to send group msg
   */
  CODE_86006(86006, "has no quota to send group msg"),

  /**
   * 小程序禁止提交
   */
  CODE_86007(86007, "小程序禁止提交"),

  /**
   * 服务商被处罚，限制全部代码提审能力
   */
  CODE_86008(86008, "服务商被处罚，限制全部代码提审能力"),

  /**
   * 服务商新增小程序代码提审能力被限制
   */
  CODE_86009(86009, "服务商新增小程序代码提审能力被限制"),

  /**
   * 服务商迭代小程序代码提审能力被限制
   */
  CODE_86010(86010, "服务商迭代小程序代码提审能力被限制"),

  /**
   * 小游戏不能提交  this is game miniprogram, submit audit is forbidden
   */
  CODE_87006(87006, "小游戏不能提交"),

  /**
   * session_key is not existd or expired
   */
  CODE_87007(87007, "session_key is not existd or expired"),

  /**
   * invalid sig_method
   */
  CODE_87008(87008, "invalid sig_method"),

  /**
   * 无效的签名  invalid signature
   */
  CODE_87009(87009, "无效的签名"),

  /**
   * invalid buffer size
   */
  CODE_87010(87010, "invalid buffer size"),

  /**
   * 现网已经在灰度发布，不能进行版本回退  wxa has a gray release plan, forbid revert release
   */
  CODE_87011(87011, "现网已经在灰度发布，不能进行版本回退"),

  /**
   * 该版本不能回退，可能的原因：1:无上一个线上版用于回退 2:此版本为已回退版本，不能回退 3:此版本为回退功能上线之前的版本，不能回退  forbid revert this version release
   */
  CODE_87012(87012, "该版本不能回退，可能的原因：1:无上一个线上版用于回退 2:此版本为已回退版本，不能回退 3:此版本为回退功能上线之前的版本，不能回退"),

  /**
   * 撤回次数达到上限（每天5次，每个月 10 次）  no quota to undo code
   */
  CODE_87013(87013, "撤回次数达到上限（每天5次，每个月 10 次）"),

  /**
   * risky content
   */
  CODE_87014(87014, "risky content"),

  /**
   * query timeout, try a content with less size
   */
  CODE_87015(87015, "query timeout, try a content with less size"),

  /**
   * some key-value in list meet length exceed
   */
  CODE_87016(87016, "some key-value in list meet length exceed"),

  /**
   * user storage size exceed, delete some keys and try again
   */
  CODE_87017(87017, "user storage size exceed, delete some keys and try again"),

  /**
   * user has stored too much keys.  delete some keys and try again
   */
  CODE_87018(87018, "user has stored too much keys.  delete some keys and try again"),

  /**
   * some keys in list meet length exceed
   */
  CODE_87019(87019, "some keys in list meet length exceed"),

  /**
   * need friend
   */
  CODE_87080(87080, "need friend"),

  /**
   * invalid openid
   */
  CODE_87081(87081, "invalid openid"),

  /**
   * invalid key
   */
  CODE_87082(87082, "invalid key"),

  /**
   * invalid operation
   */
  CODE_87083(87083, "invalid operation"),

  /**
   * invalid opnum
   */
  CODE_87084(87084, "invalid opnum"),

  /**
   * check fail
   */
  CODE_87085(87085, "check fail"),

  /**
   * without comment privilege
   */
  CODE_88000(88000, "without comment privilege"),

  /**
   * msg_data is not exists
   */
  CODE_88001(88001, "msg_data is not exists"),

  /**
   * the article is limit for safety
   */
  CODE_88002(88002, "the article is limit for safety"),

  /**
   * elected comment upper limit
   */
  CODE_88003(88003, "elected comment upper limit"),

  /**
   * comment was deleted by user
   */
  CODE_88004(88004, "comment was deleted by user"),

  /**
   * already reply
   */
  CODE_88005(88005, "already reply"),

  /**
   * reply content beyond max len or content len is zero
   */
  CODE_88007(88007, "reply content beyond max len or content len is zero"),

  /**
   * comment is not exists
   */
  CODE_88008(88008, "comment is not exists"),

  /**
   * reply is not exists
   */
  CODE_88009(88009, "reply is not exists"),

  /**
   * count range error. cout <= 0 or count > 50
   */
  CODE_88010(88010, "count range error. cout <= 0 or count > 50"),

  /**
   * the article is limit for safety
   */
  CODE_88011(88011, "the article is limit for safety"),

  /**
   * account has bound open，该公众号/小程序已经绑定了开放平台帐号  account has bound open
   */
  CODE_89000(89000, "account has bound open，该公众号/小程序已经绑定了开放平台帐号"),

  /**
   * not same contractor，Authorizer 与开放平台帐号主体不相同  not same contractor
   */
  CODE_89001(89001, "not same contractor，Authorizer 与开放平台帐号主体不相同"),

  /**
   * open not exists，该公众号/小程序未绑定微信开放平台帐号。  open not exists
   */
  CODE_89002(89002, "open not exists，该公众号/小程序未绑定微信开放平台帐号。"),

  /**
   * 该开放平台帐号并非通过 api 创建，不允许操作  open is not created by api
   */
  CODE_89003(89003, "该开放平台帐号并非通过 api 创建，不允许操作"),

  /**
   * 该开放平台帐号所绑定的公众号/小程序已达上限（100  个）
   */
  CODE_89004(89004, "该开放平台帐号所绑定的公众号/小程序已达上限（100  个）"),

  /**
   * without add video ability, the ability was banned
   */
  CODE_89005(89005, "without add video ability, the ability was banned"),

  /**
   * without upload video ability, the ability was banned
   */
  CODE_89006(89006, "without upload video ability, the ability was banned"),

  /**
   * wxa quota limit
   */
  CODE_89007(89007, "wxa quota limit"),

  /**
   * overseas account can not link
   */
  CODE_89008(89008, "overseas account can not link"),

  /**
   * wxa reach link limit
   */
  CODE_89009(89009, "wxa reach link limit"),

  /**
   * link message has sent
   */
  CODE_89010(89010, "link message has sent"),

  /**
   * can not unlink nearby wxa
   */
  CODE_89011(89011, "can not unlink nearby wxa"),

  /**
   * can not unlink store or mall
   */
  CODE_89012(89012, "can not unlink store or mall"),

  /**
   * wxa is banned
   */
  CODE_89013(89013, "wxa is banned"),

  /**
   * support version error
   */
  CODE_89014(89014, "support version error"),

  /**
   * has linked wxa
   */
  CODE_89015(89015, "has linked wxa"),

  /**
   * reach same realname quota
   */
  CODE_89016(89016, "reach same realname quota"),

  /**
   * reach different realname quota
   */
  CODE_89017(89017, "reach different realname quota"),

  /**
   * unlink message has sent
   */
  CODE_89018(89018, "unlink message has sent"),

  /**
   * 业务域名无更改，无需重复设置  webview domain not change
   */
  CODE_89019(89019, "业务域名无更改，无需重复设置"),

  /**
   * 尚未设置小程序业务域名，请先在第三方平台中设置小程序业务域名后在调用本接口  open's webview domain is null! Need to set open's webview domain first!
   */
  CODE_89020(89020, "尚未设置小程序业务域名，请先在第三方平台中设置小程序业务域名后在调用本接口"),

  /**
   * 请求保存的域名不是第三方平台中已设置的小程序业务域名或子域名  request domain is not open's webview domain!
   */
  CODE_89021(89021, "请求保存的域名不是第三方平台中已设置的小程序业务域名或子域名"),

  /**
   * delete domain is not exist!
   */
  CODE_89022(89022, "delete domain is not exist!"),

  /**
   * 业务域名数量超过限制，最多可以添加100个业务域名  webview domain exceed limit
   */
  CODE_89029(89029, "业务域名数量超过限制，最多可以添加100个业务域名"),

  /**
   * operation reach month limit
   */
  CODE_89030(89030, "operation reach month limit"),

  /**
   * user bind reach limit
   */
  CODE_89031(89031, "user bind reach limit"),

  /**
   * weapp bind members reach limit
   */
  CODE_89032(89032, "weapp bind members reach limit"),

  /**
   * empty wx or openid
   */
  CODE_89033(89033, "empty wx or openid"),

  /**
   * userstr is invalid
   */
  CODE_89034(89034, "userstr is invalid"),

  /**
   * linking from mp
   */
  CODE_89035(89035, "linking from mp"),

  /**
   * 个人小程序不支持调用 setwebviewdomain 接口  not support single
   */
  CODE_89231(89231, "个人小程序不支持调用 setwebviewdomain 接口"),

  /**
   * hit black contractor
   */
  CODE_89235(89235, "hit black contractor"),

  /**
   * 该插件不能申请  this plugin can not apply
   */
  CODE_89236(89236, "该插件不能申请"),

  /**
   * 已经添加该插件  plugin has send apply message or already bind
   */
  CODE_89237(89237, "已经添加该插件"),

  /**
   * 申请或使用的插件已经达到上限  plugin count reach limit
   */
  CODE_89238(89238, "申请或使用的插件已经达到上限"),

  /**
   * 该插件不存在  plugin no exist
   */
  CODE_89239(89239, "该插件不存在"),

  /**
   * only applying status can be agreed or refused
   */
  CODE_89240(89240, "only applying status can be agreed or refused"),

  /**
   * only refused status can be deleted, please refused first
   */
  CODE_89241(89241, "only refused status can be deleted, please refused first"),

  /**
   * appid is no in the apply list, make sure appid is right
   */
  CODE_89242(89242, "appid is no in the apply list, make sure appid is right"),

  /**
   * 该申请为“待确认”状态，不可删除  can not delete apply request in 24 hours
   */
  CODE_89243(89243, "该申请为“待确认”状态，不可删除"),

  /**
   * 不存在该插件 appid  plugin appid is no in the plugin list, make sure plugin appid is right
   */
  CODE_89244(89244, "不存在该插件 appid"),

  /**
   * mini program forbidden to link
   */
  CODE_89245(89245, "mini program forbidden to link"),

  /**
   * plugins with special category are  used only by specific apps
   */
  CODE_89246(89246, "plugins with special category are  used only by specific apps"),

  /**
   * 系统内部错误  inner error, retry after some while
   */
  CODE_89247(89247, "系统内部错误"),

  /**
   * invalid code type
   */
  CODE_89248(89248, "invalid code type"),

  /**
   * task running
   */
  CODE_89249(89249, "task running"),

  /**
   * 内部错误  inner error, retry after some while
   */
  CODE_89250(89250, "内部错误"),

  /**
   * 模板消息已下发，待法人人脸核身校验  legal person checking
   */
  CODE_89251(89251, "模板消息已下发，待法人人脸核身校验"),

  /**
   * 法人&企业信息一致性校验中  front checking
   */
  CODE_89253(89253, "法人&企业信息一致性校验中"),

  /**
   * lack of some component rights
   */
  CODE_89254(89254, "lack of some component rights"),

  /**
   * code参数无效，请检查code长度以及内容是否正确；注意code_type的值不同需要传的code长度不一样  enterprise code invalid
   */
  CODE_89255(89255, "code参数无效，请检查code长度以及内容是否正确；注意code_type的值不同需要传的code长度不一样"),

  /**
   * token 信息有误  no component info
   */
  CODE_89256(89256, "token 信息有误"),

  /**
   * 该插件版本不支持快速更新  no such version
   */
  CODE_89257(89257, "该插件版本不支持快速更新"),

  /**
   * 当前小程序帐号存在灰度发布中的版本，不可操作快速更新  code is gray online
   */
  CODE_89258(89258, "当前小程序帐号存在灰度发布中的版本，不可操作快速更新"),

  /**
   * zhibo plugin is not allow to delete
   */
  CODE_89259(89259, "zhibo plugin is not allow to delete"),

  /**
   * 订单无效  invalid trade
   */
  CODE_89300(89300, "订单无效"),

  /**
   * 系统不稳定，请稍后再试，如多次失败请通过社区反馈
   */
  CODE_89401(89401, "系统不稳定，请稍后再试，如多次失败请通过社区反馈"),

  /**
   * 该小程序不在待审核队列，请检查是否已提交审核或已审完
   */
  CODE_89402(89402, "该小程序不在待审核队列，请检查是否已提交审核或已审完"),

  /**
   * 本单属于平台不支持加急种类，请等待正常审核流程
   */
  CODE_89403(89403, "本单属于平台不支持加急种类，请等待正常审核流程"),

  /**
   * 本单已加速成功，请勿重复提交
   */
  CODE_89404(89404, "本单已加速成功，请勿重复提交"),

  /**
   * 本月加急额度已用完，请提高提审质量以获取更多额度
   */
  CODE_89405(89405, "本月加急额度已用完，请提高提审质量以获取更多额度"),

  /**
   * 公众号有未处理的确认请求，请稍候重试
   */
  CODE_89501(89501, "公众号有未处理的确认请求，请稍候重试"),

  /**
   * 请耐心等待管理员确认
   */
  CODE_89502(89502, "请耐心等待管理员确认"),

  /**
   * 此次调用需要管理员确认，请耐心等候
   */
  CODE_89503(89503, "此次调用需要管理员确认，请耐心等候"),

  /**
   * 正在等管理员确认，请耐心等待
   */
  CODE_89504(89504, "正在等管理员确认，请耐心等待"),

  /**
   * 正在等管理员确认，请稍候重试
   */
  CODE_89505(89505, "正在等管理员确认，请稍候重试"),

  /**
   * 该IP调用求请求已被公众号管理员拒绝，请24小时后再试，建议调用前与管理员沟通确认
   */
  CODE_89506(89506, "该IP调用求请求已被公众号管理员拒绝，请24小时后再试，建议调用前与管理员沟通确认"),

  /**
   * 该IP调用求请求已被公众号管理员拒绝，请1小时后再试，建议调用前与管理员沟通确认
   */
  CODE_89507(89507, "该IP调用求请求已被公众号管理员拒绝，请1小时后再试，建议调用前与管理员沟通确认"),

  /**
   * invalid order id
   */
  CODE_90001(90001, "invalid order id"),

  /**
   * invalid busi id
   */
  CODE_90002(90002, "invalid busi id"),

  /**
   * invalid bill date
   */
  CODE_90003(90003, "invalid bill date"),

  /**
   * invalid bill type
   */
  CODE_90004(90004, "invalid bill type"),

  /**
   * invalid platform
   */
  CODE_90005(90005, "invalid platform"),

  /**
   * bill not exists
   */
  CODE_90006(90006, "bill not exists"),

  /**
   * invalid openid
   */
  CODE_90007(90007, "invalid openid"),

  /**
   * mp_sig error
   */
  CODE_90009(90009, "mp_sig error"),

  /**
   * no session
   */
  CODE_90010(90010, "no session"),

  /**
   * sig error
   */
  CODE_90011(90011, "sig error"),

  /**
   * order exist
   */
  CODE_90012(90012, "order exist"),

  /**
   * balance not enough
   */
  CODE_90013(90013, "balance not enough"),

  /**
   * order has been confirmed
   */
  CODE_90014(90014, "order has been confirmed"),

  /**
   * order has been canceled
   */
  CODE_90015(90015, "order has been canceled"),

  /**
   * order is being processed
   */
  CODE_90016(90016, "order is being processed"),

  /**
   * no privilege
   */
  CODE_90017(90017, "no privilege"),

  /**
   * invalid parameter
   */
  CODE_90018(90018, "invalid parameter"),

  /**
   * 不是公众号快速创建的小程序  not fast register
   */
  CODE_91001(91001, "不是公众号快速创建的小程序"),

  /**
   * 小程序发布后不可改名  has published
   */
  CODE_91002(91002, "小程序发布后不可改名"),

  /**
   * 改名状态不合法  invalid change stat
   */
  CODE_91003(91003, "改名状态不合法"),

  /**
   * 昵称不合法  invalid nickname
   */
  CODE_91004(91004, "昵称不合法"),

  /**
   * 昵称 15 天主体保护  nickname protected
   */
  CODE_91005(91005, "昵称 15 天主体保护"),

  /**
   * 昵称命中微信号  nickname used by username
   */
  CODE_91006(91006, "昵称命中微信号"),

  /**
   * 昵称已被占用  nickname used
   */
  CODE_91007(91007, "昵称已被占用"),

  /**
   * 昵称命中 7 天侵权保护期  nickname protected
   */
  CODE_91008(91008, "昵称命中 7 天侵权保护期"),

  /**
   * 需要提交材料  nickname need proof
   */
  CODE_91009(91009, "需要提交材料"),

  /**
   * 其他错误
   */
  CODE_91010(91010, "其他错误"),

  /**
   * 查不到昵称修改审核单信息
   */
  CODE_91011(91011, "查不到昵称修改审核单信息"),

  /**
   * 其它错误
   */
  CODE_91012(91012, "其它错误"),

  /**
   * 占用名字过多  lock name too more
   */
  CODE_91013(91013, "占用名字过多"),

  /**
   * +号规则 同一类型关联名主体不一致  diff master plus
   */
  CODE_91014(91014, "+号规则 同一类型关联名主体不一致"),

  /**
   * 原始名不同类型主体不一致  diff master
   */
  CODE_91015(91015, "原始名不同类型主体不一致"),

  /**
   * 名称占用者 ≥2  name more owner
   */
  CODE_91016(91016, "名称占用者 ≥2"),

  /**
   * +号规则 不同类型关联名主体不一致  other diff master plus
   */
  CODE_91017(91017, "+号规则 不同类型关联名主体不一致"),

  /**
   * 组织类型小程序发布后，侵权被清空昵称，需走认证改名
   */
  CODE_91018(91018, "组织类型小程序发布后，侵权被清空昵称，需走认证改名"),

  /**
   * 小程序正在审核中
   */
  CODE_91019(91019, "小程序正在审核中"),

  /**
   * 该经营资质已添加，请勿重复添加
   */
  CODE_92000(92000, "该经营资质已添加，请勿重复添加"),

  /**
   * 附近地点添加数量达到上线，无法继续添加
   */
  CODE_92002(92002, "附近地点添加数量达到上线，无法继续添加"),

  /**
   * 地点已被其它小程序占用
   */
  CODE_92003(92003, "地点已被其它小程序占用"),

  /**
   * 附近功能被封禁
   */
  CODE_92004(92004, "附近功能被封禁"),

  /**
   * 地点正在审核中
   */
  CODE_92005(92005, "地点正在审核中"),

  /**
   * 地点正在展示小程序
   */
  CODE_92006(92006, "地点正在展示小程序"),

  /**
   * 地点审核失败
   */
  CODE_92007(92007, "地点审核失败"),

  /**
   * 小程序未展示在该地点
   */
  CODE_92008(92008, "小程序未展示在该地点"),

  /**
   * 小程序未上架或不可见
   */
  CODE_93009(93009, "小程序未上架或不可见"),

  /**
   * 地点不存在
   */
  CODE_93010(93010, "地点不存在"),

  /**
   * 个人类型小程序不可用
   */
  CODE_93011(93011, "个人类型小程序不可用"),

  /**
   * 非普通类型小程序（门店小程序、小店小程序等）不可用
   */
  CODE_93012(93012, "非普通类型小程序（门店小程序、小店小程序等）不可用"),

  /**
   * 从腾讯地图获取地址详细信息失败
   */
  CODE_93013(93013, "从腾讯地图获取地址详细信息失败"),

  /**
   * 同一资质证件号重复添加
   */
  CODE_93014(93014, "同一资质证件号重复添加"),

  /**
   * 附近类目审核中
   */
  CODE_93015(93015, "附近类目审核中"),

  /**
   * 服务标签个数超限制（官方最多5个，自定义最多4个）
   */
  CODE_93016(93016, "服务标签个数超限制（官方最多5个，自定义最多4个）"),

  /**
   * 服务标签或者客服的名字不符合要求
   */
  CODE_93017(93017, "服务标签或者客服的名字不符合要求"),

  /**
   * 服务能力中填写的小程序appid不是同主体小程序
   */
  CODE_93018(93018, "服务能力中填写的小程序appid不是同主体小程序"),

  /**
   * 申请类目之后才能添加附近地点
   */
  CODE_93019(93019, "申请类目之后才能添加附近地点"),

  /**
   * qualification_list无效
   */
  CODE_93020(93020, "qualification_list无效"),

  /**
   * company_name字段为空
   */
  CODE_93021(93021, "company_name字段为空"),

  /**
   * credential字段为空
   */
  CODE_93022(93022, "credential字段为空"),

  /**
   * address字段为空
   */
  CODE_93023(93023, "address字段为空"),

  /**
   * qualification_list字段为空
   */
  CODE_93024(93024, "qualification_list字段为空"),

  /**
   * 服务appid对应的path不存在
   */
  CODE_93025(93025, "服务appid对应的path不存在"),

  /**
   * missing cert_serialno
   */
  CODE_94001(94001, "missing cert_serialno"),

  /**
   * use not register wechat pay
   */
  CODE_94002(94002, "use not register wechat pay"),

  /**
   * invalid sign
   */
  CODE_94003(94003, "invalid sign"),

  /**
   * user do not has real name info
   */
  CODE_94004(94004, "user do not has real name info"),

  /**
   * invalid user token
   */
  CODE_94005(94005, "invalid user token"),

  /**
   * appid unauthorized
   */
  CODE_94006(94006, "appid unauthorized"),

  /**
   * appid unbind mchid
   */
  CODE_94007(94007, "appid unbind mchid"),

  /**
   * invalid timestamp
   */
  CODE_94008(94008, "invalid timestamp"),

  /**
   * invalid cert_serialno, cert_serialno's size should be 40
   */
  CODE_94009(94009, "invalid cert_serialno, cert_serialno's size should be 40"),

  /**
   * invalid mch_id
   */
  CODE_94010(94010, "invalid mch_id"),

  /**
   * timestamp expired
   */
  CODE_94011(94011, "timestamp expired"),

  /**
   * appid和商户号的绑定关系不存在
   */
  CODE_94012(94012, "appid和商户号的绑定关系不存在"),

  /**
   * wxcode decode fail
   */
  CODE_95001(95001, "wxcode decode fail"),

  /**
   * wxcode recognize unautuorized
   */
  CODE_95002(95002, "wxcode recognize unautuorized"),

  /**
   * get product by page args invalid
   */
  CODE_95101(95101, "get product by page args invalid"),

  /**
   * get product materials by cond args invalid
   */
  CODE_95102(95102, "get product materials by cond args invalid"),

  /**
   * material id list size out of limit
   */
  CODE_95103(95103, "material id list size out of limit"),

  /**
   * import product frequence out of limit
   */
  CODE_95104(95104, "import product frequence out of limit"),

  /**
   * mp is importing products, api is rejected to import
   */
  CODE_95105(95105, "mp is importing products, api is rejected to import"),

  /**
   * api is rejected to import, need to  set commission ratio on mp first
   */
  CODE_95106(95106, "api is rejected to import, need to  set commission ratio on mp first"),

  /**
   * invalid image url
   */
  CODE_101000(101000, "无效图片链接"),

  /**
   * certificate not found
   */
  CODE_101001(101001, "未找到证书"),

  /**
   * not enough market quota
   */
  CODE_101002(101002, "not enough market quota"),

  /**
   * 入参错误
   */
  CODE_200002(200002, "入参错误"),

  /**
   * 此账号已被封禁，无法操作
   */
  CODE_200011(200011, "此账号已被封禁，无法操作"),

  /**
   * 个人模板数已达上限，上限25个
   */
  CODE_200012(200012, "个人模板数已达上限，上限25个"),

  /**
   * 此模板已被封禁，无法选用
   */
  CODE_200013(200013, "此模板已被封禁，无法选用"),

  /**
   * 模板  tid 参数错误
   */
  CODE_200014(200014, "模板  tid 参数错误"),

  /**
   * start  参数错误
   */
  CODE_200016(200016, "start  参数错误"),

  /**
   * limit  参数错误
   */
  CODE_200017(200017, "limit  参数错误"),

  /**
   * 类目  ids 缺失
   */
  CODE_200018(200018, "类目  ids 缺失"),

  /**
   * 类目  ids 不合法
   */
  CODE_200019(200019, "类目  ids 不合法"),

  /**
   * 关键词列表  kidList 参数错误
   */
  CODE_200020(200020, "关键词列表  kidList 参数错误"),

  /**
   * 场景描述  sceneDesc 参数错误
   */
  CODE_200021(200021, "场景描述  sceneDesc 参数错误"),

  /**
   * 禁止创建/更新商品（如商品创建功能被封禁） 或 禁止编辑&更新房间
   */
  CODE_300001(300001, "禁止创建/更新商品（如商品创建功能被封禁） 或 禁止编辑&更新房间"),

  /**
   * 名称长度不符合规则
   */
  CODE_300002(300002, "名称长度不符合规则"),

  /**
   * 价格输入不合规（如现价比原价大、传入价格非数字等）
   */
  CODE_300003(300003, "价格输入不合规（如现价比原价大、传入价格非数字等）"),

  /**
   * 商品名称存在违规违法内容
   */
  CODE_300004(300004, "商品名称存在违规违法内容"),

  /**
   * 商品图片存在违规违法内容
   */
  CODE_300005(300005, "商品图片存在违规违法内容"),

  /**
   * 图片上传失败（如mediaID过期）
   */
  CODE_300006(300006, "图片上传失败（如mediaID过期）"),

  /**
   * 线上小程序版本不存在该链接
   */
  CODE_300007(300007, "线上小程序版本不存在该链接"),

  /**
   * 添加商品失败
   */
  CODE_300008(300008, "添加商品失败"),

  /**
   * 商品审核撤回失败
   */
  CODE_300009(300009, "商品审核撤回失败"),

  /**
   * 商品审核状态不对（如商品审核中）
   */
  CODE_300010(300010, "商品审核状态不对（如商品审核中）"),

  /**
   * 操作非法（API不允许操作非API创建的商品）
   */
  CODE_300011(300011, "操作非法（API不允许操作非API创建的商品）"),

  /**
   * 没有提审额度（每天500次提审额度）
   */
  CODE_300012(300012, "没有提审额度（每天500次提审额度）"),

  /**
   * 提审失败
   */
  CODE_300013(300013, "提审失败"),

  /**
   * 审核中，无法删除（非零代表失败）
   */
  CODE_300014(300014, "审核中，无法删除（非零代表失败）"),

  /**
   * 商品未提审
   */
  CODE_300017(300017, "商品未提审"),

  /**
   * 商品添加成功，审核失败
   */
  CODE_300021(300021, "商品添加成功，审核失败"),

  /**
   * 此房间号不存在
   */
  CODE_300022(300022, "此房间号不存在"),

  /**
   * 房间状态 拦截（当前房间状态不允许此操作）
   */
  CODE_300023(300023, "房间状态 拦截（当前房间状态不允许此操作）"),

  /**
   * 商品不存在
   */
  CODE_300024(300024, "商品不存在"),

  /**
   * 商品审核未通过
   */
  CODE_300025(300025, "商品审核未通过"),

  /**
   * 房间商品数量已经满额
   */
  CODE_300026(300026, "房间商品数量已经满额"),

  /**
   * 导入商品失败
   */
  CODE_300027(300027, "导入商品失败"),

  /**
   * 房间名称违规
   */
  CODE_300028(300028, "房间名称违规"),

  /**
   * 主播昵称违规
   */
  CODE_300029(300029, "主播昵称违规"),

  /**
   * 主播微信号不合法
   */
  CODE_300030(300030, "主播微信号不合法"),

  /**
   * 直播间封面图不合规
   */
  CODE_300031(300031, "直播间封面图不合规"),

  /**
   * 直播间分享图违规
   */
  CODE_300032(300032, "直播间分享图违规"),

  /**
   * 添加商品超过直播间上限
   */
  CODE_300033(300033, "添加商品超过直播间上限"),

  /**
   * 主播微信昵称长度不符合要求
   */
  CODE_300034(300034, "主播微信昵称长度不符合要求"),

  /**
   * 主播微信号不存在
   */
  CODE_300035(300035, "主播微信号不存在"),

  /**
   * 主播微信号未实名认证
   */
  CODE_300036(300036, "主播微信号未实名认证"),

  /**
   * invalid file name
   */
  CODE_600001(600001, "invalid file name"),

  /**
   * no permission to upload file
   */
  CODE_600002(600002, "no permission to upload file"),

  /**
   * invalid size of source
   */
  CODE_600003(600003, "invalid size of source"),

  /**
   * 票据已存在
   */
  CODE_928000(928000, "票据已存在"),

  /**
   * 票据不存在
   */
  CODE_928001(928001, "票据不存在"),

  /**
   * sysem error
   */
  CODE_930555(930555, "sysem error"),

  /**
   * delivery timeout
   */
  CODE_930556(930556, "delivery timeout"),

  /**
   * delivery system error
   */
  CODE_930557(930557, "delivery system error"),

  /**
   * delivery logic error
   */
  CODE_930558(930558, "delivery logic error"),

  /**
   * 沙盒环境openid无效  invaild openid
   */
  CODE_930559(930559, "沙盒环境openid无效"),

  /**
   * shopid need bind first
   */
  CODE_930560(930560, "shopid need bind first"),

  /**
   * 参数错误  args error
   */
  CODE_930561(930561, "参数错误"),

  /**
   * order already exists
   */
  CODE_930562(930562, "order already exists"),

  /**
   * 订单不存在  order not exists
   */
  CODE_930563(930563, "订单不存在"),

  /**
   * 沙盒环境调用无配额  quota run out, try next day
   */
  CODE_930564(930564, "沙盒环境调用无配额"),

  /**
   * order finished
   */
  CODE_930565(930565, "order finished"),

  /**
   * not support, plz auth at mp.weixin.qq.com
   */
  CODE_930566(930566, "not support, plz auth at mp.weixin.qq.com"),

  /**
   * shop arg error
   */
  CODE_930567(930567, "shop arg error"),

  /**
   * 不支持个人类型小程序  not personal account
   */
  CODE_930568(930568, "不支持个人类型小程序"),

  /**
   * 已经开通不需要再开通  already open
   */
  CODE_930569(930569, "已经开通不需要再开通"),

  /**
   * cargo_first_class or cargo_second_class invalid
   */
  CODE_930570(930570, "cargo_first_class or cargo_second_class invalid"),

  /**
   * 该商户没有内测权限，请先申请权限: https://wj.qq.com/s2/7243532/fcfb/
   */
  CODE_930571(930571, "该商户没有内测权限，请先申请权限: https://wj.qq.com/s2/7243532/fcfb/"),

  /**
   * fee already set
   */
  CODE_931010(931010, "fee already set"),

  /**
   * unbind download url
   */
  CODE_6000100(6000100, "unbind download url"),

  /**
   * no response data
   */
  CODE_6000101(6000101, "no response data"),

  /**
   * response data too big
   */
  CODE_6000102(6000102, "response data too big"),

  /**
   * POST 数据参数不合法
   */
  CODE_9001001(9001001, "POST 数据参数不合法"),

  /**
   * 远端服务不可用
   */
  CODE_9001002(9001002, "远端服务不可用"),

  /**
   * Ticket 不合法
   */
  CODE_9001003(9001003, "Ticket 不合法"),

  /**
   * 获取摇周边用户信息失败
   */
  CODE_9001004(9001004, "获取摇周边用户信息失败"),

  /**
   * 获取商户信息失败
   */
  CODE_9001005(9001005, "获取商户信息失败"),

  /**
   * 获取 OpenID 失败
   */
  CODE_9001006(9001006, "获取 OpenID 失败"),

  /**
   * 上传文件缺失
   */
  CODE_9001007(9001007, "上传文件缺失"),

  /**
   * 上传素材的文件类型不合法
   */
  CODE_9001008(9001008, "上传素材的文件类型不合法"),

  /**
   * 上传素材的文件尺寸不合法
   */
  CODE_9001009(9001009, "上传素材的文件尺寸不合法"),

  /**
   * 上传失败
   */
  CODE_9001010(9001010, "上传失败"),

  /**
   * 帐号不合法
   */
  CODE_9001020(9001020, "帐号不合法"),

  /**
   * 已有设备激活率低于 50% ，不能新增设备
   */
  CODE_9001021(9001021, "已有设备激活率低于 50% ，不能新增设备"),

  /**
   * 设备申请数不合法，必须为大于 0 的数字
   */
  CODE_9001022(9001022, "设备申请数不合法，必须为大于 0 的数字"),

  /**
   * 已存在审核中的设备 ID 申请
   */
  CODE_9001023(9001023, "已存在审核中的设备 ID 申请"),

  /**
   * 一次查询设备 ID 数量不能超过 50
   */
  CODE_9001024(9001024, "一次查询设备 ID 数量不能超过 50"),

  /**
   * 设备 ID 不合法
   */
  CODE_9001025(9001025, "设备 ID 不合法"),

  /**
   * 页面 ID 不合法
   */
  CODE_9001026(9001026, "页面 ID 不合法"),

  /**
   * 页面参数不合法
   */
  CODE_9001027(9001027, "页面参数不合法"),

  /**
   * 一次删除页面 ID 数量不能超过 10
   */
  CODE_9001028(9001028, "一次删除页面 ID 数量不能超过 10"),

  /**
   * 页面已应用在设备中，请先解除应用关系再删除
   */
  CODE_9001029(9001029, "页面已应用在设备中，请先解除应用关系再删除"),

  /**
   * 一次查询页面 ID 数量不能超过 50
   */
  CODE_9001030(9001030, "一次查询页面 ID 数量不能超过 50"),

  /**
   * 时间区间不合法
   */
  CODE_9001031(9001031, "时间区间不合法"),

  /**
   * 保存设备与页面的绑定关系参数错误
   */
  CODE_9001032(9001032, "保存设备与页面的绑定关系参数错误"),

  /**
   * 门店 ID 不合法
   */
  CODE_9001033(9001033, "门店 ID 不合法"),

  /**
   * 设备备注信息过长
   */
  CODE_9001034(9001034, "设备备注信息过长"),

  /**
   * 设备申请参数不合法
   */
  CODE_9001035(9001035, "设备申请参数不合法"),

  /**
   * 查询起始值 begin 不合法
   */
  CODE_9001036(9001036, "查询起始值 begin 不合法"),

  /**
   * params invalid
   */
  CODE_9002008(9002008, "params invalid"),

  /**
   * shop id not exist
   */
  CODE_9002009(9002009, "shop id not exist"),

  /**
   * ssid or password should start with "WX"
   */
  CODE_9002010(9002010, "ssid or password should start with \"WX\""),

  /**
   * ssid can not include chinese
   */
  CODE_9002011(9002011, "ssid can not include chinese"),

  /**
   * passsword can not include chinese
   */
  CODE_9002012(9002012, "passsword can not include chinese"),

  /**
   * password must be between 8 and 24 characters
   */
  CODE_9002013(9002013, "password must be between 8 and 24 characters"),

  /**
   * device exist
   */
  CODE_9002016(9002016, "device exist"),

  /**
   * device not exist
   */
  CODE_9002017(9002017, "device not exist"),

  /**
   * the size of query list reach limit
   */
  CODE_9002026(9002026, "the size of query list reach limit"),

  /**
   * not allowed to modify, ensure you  are an certified or component account
   */
  CODE_9002041(9002041, "not allowed to modify, ensure you  are an certified or component account"),

  /**
   * invalid ssid, can not include none  utf8 characters, and should be between 1 and 32 bytes
   */
  CODE_9002044(9002044, "invalid ssid, can not include none  utf8 characters, and should be between 1 and 32 bytes"),

  /**
   * shop id has not be audited, this bar type is not enable
   */
  CODE_9002052(9002052, "shop id has not be audited, this bar type is not enable"),

  /**
   * protocol type is not same with the exist device
   */
  CODE_9007003(9007003, "protocol type is not same with the exist device"),

  /**
   * ssid not exist
   */
  CODE_9007004(9007004, "ssid not exist"),

  /**
   * device count limit
   */
  CODE_9007005(9007005, "device count limit"),

  /**
   * card info not exist
   */
  CODE_9008001(9008001, "card info not exist"),

  /**
   * card expiration time is invalid
   */
  CODE_9008002(9008002, "card expiration time is invalid"),

  /**
   * url size invalid, keep less than 255
   */
  CODE_9008003(9008003, "url size invalid, keep less than 255"),

  /**
   * url can not include chinese
   */
  CODE_9008004(9008004, "url can not include chinese"),

  /**
   * order_id not exist
   */
  CODE_9200001(9200001, "order_id not exist"),

  /**
   * order of other biz
   */
  CODE_9200002(9200002, "order of other biz"),

  /**
   * blocked
   */
  CODE_9200003(9200003, "blocked"),

  /**
   * payment notice disabled
   */
  CODE_9200211(9200211, "payment notice disabled"),

  /**
   * payment notice not exist
   */
  CODE_9200231(9200231, "payment notice not exist"),

  /**
   * payment notice paid
   */
  CODE_9200232(9200232, "payment notice paid"),

  /**
   * payment notice canceled
   */
  CODE_9200233(9200233, "payment notice canceled"),

  /**
   * payment notice expired
   */
  CODE_9200235(9200235, "payment notice expired"),

  /**
   * bank not allow
   */
  CODE_9200236(9200236, "bank not allow"),

  /**
   * freq limit
   */
  CODE_9200295(9200295, "freq limit"),

  /**
   * suspend payment at current time
   */
  CODE_9200297(9200297, "suspend payment at current time"),

  /**
   * 3rd resp decrypt error
   */
  CODE_9200298(9200298, "3rd resp decrypt error"),

  /**
   * 3rd resp system error
   */
  CODE_9200299(9200299, "3rd resp system error"),

  /**
   * 3rd resp sign error
   */
  CODE_9200300(9200300, "3rd resp sign error"),

  /**
   * desc empty
   */
  CODE_9201000(9201000, "desc empty"),

  /**
   * fee not equal items'
   */
  CODE_9201001(9201001, "fee not equal items'"),

  /**
   * payment info incorrect
   */
  CODE_9201002(9201002, "payment info incorrect"),

  /**
   * fee is 0
   */
  CODE_9201003(9201003, "fee is 0"),

  /**
   * payment expire date format error
   */
  CODE_9201004(9201004, "payment expire date format error"),

  /**
   * appid error
   */
  CODE_9201005(9201005, "appid error"),

  /**
   * payment order id error
   */
  CODE_9201006(9201006, "payment order id error"),

  /**
   * openid error
   */
  CODE_9201007(9201007, "openid error"),

  /**
   * return_url error
   */
  CODE_9201008(9201008, "return_url error"),

  /**
   * ip error
   */
  CODE_9201009(9201009, "ip error"),

  /**
   * order_id error
   */
  CODE_9201010(9201010, "order_id error"),

  /**
   * reason error
   */
  CODE_9201011(9201011, "reason error"),

  /**
   * mch_id error
   */
  CODE_9201012(9201012, "mch_id error"),

  /**
   * bill_date error
   */
  CODE_9201013(9201013, "bill_date error"),

  /**
   * bill_type error
   */
  CODE_9201014(9201014, "bill_type error"),

  /**
   * trade_type error
   */
  CODE_9201015(9201015, "trade_type error"),

  /**
   * bank_id error
   */
  CODE_9201016(9201016, "bank_id error"),

  /**
   * bank_account error
   */
  CODE_9201017(9201017, "bank_account error"),

  /**
   * payment_notice_no error
   */
  CODE_9201018(9201018, "payment_notice_no error"),

  /**
   * department_code error
   */
  CODE_9201019(9201019, "department_code error"),

  /**
   * payment_notice_type error
   */
  CODE_9201020(9201020, "payment_notice_type error"),

  /**
   * region_code error
   */
  CODE_9201021(9201021, "region_code error"),

  /**
   * department_name error
   */
  CODE_9201022(9201022, "department_name error"),

  /**
   * fee not equal finance's
   */
  CODE_9201023(9201023, "fee not equal finance's"),

  /**
   * refund_out_id error
   */
  CODE_9201024(9201024, "refund_out_id error"),

  /**
   * not combined order_id
   */
  CODE_9201026(9201026, "not combined order_id"),

  /**
   * partial sub order is test
   */
  CODE_9201027(9201027, "partial sub order is test"),

  /**
   * desc too long
   */
  CODE_9201029(9201029, "desc too long"),

  /**
   * sub order list size error
   */
  CODE_9201031(9201031, "sub order list size error"),

  /**
   * sub order repeated
   */
  CODE_9201032(9201032, "sub order repeated"),

  /**
   * auth_code empty
   */
  CODE_9201033(9201033, "auth_code empty"),

  /**
   * bank_id empty but mch_id not empty
   */
  CODE_9201034(9201034, "bank_id empty but mch_id not empty"),

  /**
   * sum of other fees exceed total fee
   */
  CODE_9201035(9201035, "sum of other fees exceed total fee"),

  /**
   * other user paying
   */
  CODE_9202000(9202000, "other user paying"),

  /**
   * pay process not finish
   */
  CODE_9202001(9202001, "pay process not finish"),

  /**
   * no refund permission
   */
  CODE_9202002(9202002, "no refund permission"),

  /**
   * ip limit
   */
  CODE_9202003(9202003, "ip limit"),

  /**
   * freq limit
   */
  CODE_9202004(9202004, "freq limit"),

  /**
   * user weixin account abnormal
   */
  CODE_9202005(9202005, "user weixin account abnormal"),

  /**
   * account balance not enough
   */
  CODE_9202006(9202006, "account balance not enough"),

  /**
   * refund request repeated
   */
  CODE_9202010(9202010, "refund request repeated"),

  /**
   * has refunded
   */
  CODE_9202011(9202011, "has refunded"),

  /**
   * refund exceed total fee
   */
  CODE_9202012(9202012, "refund exceed total fee"),

  /**
   * busi_id dup
   */
  CODE_9202013(9202013, "busi_id dup"),

  /**
   * not check sign
   */
  CODE_9202016(9202016, "not check sign"),

  /**
   * check sign failed
   */
  CODE_9202017(9202017, "check sign failed"),

  /**
   * sub order error
   */
  CODE_9202018(9202018, "sub order error"),

  /**
   * order status error
   */
  CODE_9202020(9202020, "order status error"),

  /**
   * unified order repeatedly
   */
  CODE_9202021(9202021, "unified order repeatedly"),

  /**
   * request to notification url fail
   */
  CODE_9203000(9203000, "request to notification url fail"),

  /**
   * http request fail
   */
  CODE_9203001(9203001, "http request fail"),

  /**
   * http response data error
   */
  CODE_9203002(9203002, "http response data error"),

  /**
   * http response data RSA decrypt fail
   */
  CODE_9203003(9203003, "http response data RSA decrypt fail"),

  /**
   * http response data AES decrypt fail
   */
  CODE_9203004(9203004, "http response data AES decrypt fail"),

  /**
   * system busy, please try again later
   */
  CODE_9203999(9203999, "system busy, please try again later"),

  /**
   * getrealname token error
   */
  CODE_9204000(9204000, "getrealname token error"),

  /**
   * getrealname user or token error
   */
  CODE_9204001(9204001, "getrealname user or token error"),

  /**
   * getrealname appid or token error
   */
  CODE_9204002(9204002, "getrealname appid or token error"),

  /**
   * finance conf not exist
   */
  CODE_9205000(9205000, "finance conf not exist"),

  /**
   * bank conf not exist
   */
  CODE_9205001(9205001, "bank conf not exist"),

  /**
   * wei ban ju conf not exist
   */
  CODE_9205002(9205002, "wei ban ju conf not exist"),

  /**
   * symmetric key conf not exist
   */
  CODE_9205010(9205010, "symmetric key conf not exist"),

  /**
   * out order id not exist
   */
  CODE_9205101(9205101, "out order id not exist"),

  /**
   * bill not exist
   */
  CODE_9205201(9205201, "bill not exist"),

  /**
   * 3rd resp pay_channel empty
   */
  CODE_9206000(9206000, "3rd resp pay_channel empty"),

  /**
   * 3rd resp order_id empty
   */
  CODE_9206001(9206001, "3rd resp order_id empty"),

  /**
   * 3rd resp bill_type_code empty
   */
  CODE_9206002(9206002, "3rd resp bill_type_code empty"),

  /**
   * 3rd resp bill_no empty
   */
  CODE_9206003(9206003, "3rd resp bill_no empty"),

  /**
   * 3rd resp empty
   */
  CODE_9206200(9206200, "3rd resp empty"),

  /**
   * 3rd resp not json
   */
  CODE_9206201(9206201, "3rd resp not json"),

  /**
   * connect 3rd error
   */
  CODE_9206900(9206900, "connect 3rd error"),

  /**
   * connect 3rd timeout
   */
  CODE_9206901(9206901, "connect 3rd timeout"),

  /**
   * read 3rd resp error
   */
  CODE_9206910(9206910, "read 3rd resp error"),

  /**
   * read 3rd resp timeout
   */
  CODE_9206911(9206911, "read 3rd resp timeout"),

  /**
   * boss error
   */
  CODE_9207000(9207000, "boss error"),

  /**
   * wechat pay error
   */
  CODE_9207001(9207001, "wechat pay error"),

  /**
   * boss param error
   */
  CODE_9207002(9207002, "boss param error"),

  /**
   * pay error
   */
  CODE_9207003(9207003, "pay error"),

  /**
   * auth_code expired
   */
  CODE_9207004(9207004, "auth_code expired"),

  /**
   * user balance not enough
   */
  CODE_9207005(9207005, "user balance not enough"),

  /**
   * card not support
   */
  CODE_9207006(9207006, "card not support"),

  /**
   * order reversed
   */
  CODE_9207007(9207007, "order reversed"),

  /**
   * user paying, need input password
   */
  CODE_9207008(9207008, "user paying, need input password"),

  /**
   * auth_code error
   */
  CODE_9207009(9207009, "auth_code error"),

  /**
   * auth_code invalid
   */
  CODE_9207010(9207010, "auth_code invalid"),

  /**
   * not allow to reverse when user paying
   */
  CODE_9207011(9207011, "not allow to reverse when user paying"),

  /**
   * order paid
   */
  CODE_9207012(9207012, "order paid"),

  /**
   * order closed
   */
  CODE_9207013(9207013, "order closed"),

  /**
   * vehicle not exists
   */
  CODE_9207028(9207028, "vehicle not exists"),

  /**
   * vehicle request blocked
   */
  CODE_9207029(9207029, "vehicle request blocked"),

  /**
   * vehicle auth error
   */
  CODE_9207030(9207030, "vehicle auth error"),

  /**
   * contract over limit
   */
  CODE_9207031(9207031, "contract over limit"),

  /**
   * trade error
   */
  CODE_9207032(9207032, "trade error"),

  /**
   * trade time invalid
   */
  CODE_9207033(9207033, "trade time invalid"),

  /**
   * channel type invalid
   */
  CODE_9207034(9207034, "channel type invalid"),

  /**
   * expire_time range error
   */
  CODE_9207050(9207050, "expire_time range error"),

  /**
   * query finance error
   */
  CODE_9210000(9210000, "query finance error"),

  /**
   * openid error
   */
  CODE_9291000(9291000, "openid error"),

  /**
   * openid appid not match
   */
  CODE_9291001(9291001, "openid appid not match"),

  /**
   * app_appid not exist
   */
  CODE_9291002(9291002, "app_appid not exist"),

  /**
   * app_appid not app
   */
  CODE_9291003(9291003, "app_appid not app"),

  /**
   * appid empty
   */
  CODE_9291004(9291004, "appid empty"),

  /**
   * appid not match access_token
   */
  CODE_9291005(9291005, "appid not match access_token"),

  /**
   * invalid sign
   */
  CODE_9291006(9291006, "invalid sign"),

  /**
   * backend logic error
   */
  CODE_9299999(9299999, "backend logic error"),

  /**
   * begin_time can not before now
   */
  CODE_9300001(9300001, "begin_time can not before now"),

  /**
   * end_time can not before now
   */
  CODE_9300002(9300002, "end_time can not before now"),

  /**
   * begin_time must less than end_time
   */
  CODE_9300003(9300003, "begin_time must less than end_time"),

  /**
   * end_time - begin_time > 1year
   */
  CODE_9300004(9300004, "end_time - begin_time > 1year"),

  /**
   * invalid max_partic_times
   */
  CODE_9300005(9300005, "invalid max_partic_times"),

  /**
   * invalid activity status
   */
  CODE_9300006(9300006, "invalid activity status"),

  /**
   * gift_num must >0 and <=15
   */
  CODE_9300007(9300007, "gift_num must >0 and <=15"),

  /**
   * invalid tiny appid
   */
  CODE_9300008(9300008, "invalid tiny appid"),

  /**
   * activity can not finish
   */
  CODE_9300009(9300009, "activity can not finish"),

  /**
   * card_info_list must >= 2
   */
  CODE_9300010(9300010, "card_info_list must >= 2"),

  /**
   * invalid card_id
   */
  CODE_9300011(9300011, "invalid card_id"),

  /**
   * card_id must belong this appid
   */
  CODE_9300012(9300012, "card_id must belong this appid"),

  /**
   * card_id is not swipe_card or pay.cash
   */
  CODE_9300013(9300013, "card_id is not swipe_card or pay.cash"),

  /**
   * some card_id is out of stock
   */
  CODE_9300014(9300014, "some card_id is out of stock"),

  /**
   * some card_id is invalid status
   */
  CODE_9300015(9300015, "some card_id is invalid status"),

  /**
   * membership or new/old tinyapp user only support one
   */
  CODE_9300016(9300016, "membership or new/old tinyapp user only support one"),

  /**
   * invalid logic for membership
   */
  CODE_9300017(9300017, "invalid logic for membership"),

  /**
   * invalid logic for tinyapp new/old user
   */
  CODE_9300018(9300018, "invalid logic for tinyapp new/old user"),

  /**
   * invalid activity type
   */
  CODE_9300019(9300019, "invalid activity type"),

  /**
   * invalid activity_id
   */
  CODE_9300020(9300020, "invalid activity_id"),

  /**
   * invalid help_max_times
   */
  CODE_9300021(9300021, "invalid help_max_times"),

  /**
   * invalid cover_url
   */
  CODE_9300022(9300022, "invalid cover_url"),

  /**
   * invalid gen_limit
   */
  CODE_9300023(9300023, "invalid gen_limit"),

  /**
   * card's end_time cannot early than act's end_time
   */
  CODE_9300024(9300024, "card's end_time cannot early than act's end_time"),

  /**
   * 快递侧逻辑错误，详细原因需要看 delivery_resultcode, 请先确认一下编码方式，python建议 json.dumps(b, ensure_ascii=False)，php建议 json_encode($arr, JSON_UNESCAPED_UNICODE)  Delivery side error
   */
  CODE_9300501(9300501, "快递侧逻辑错误，详细原因需要看 delivery_resultcode, 请先确认一下编码方式，python建议 json.dumps(b, ensure_ascii=False)，php建议 json_encode($arr, JSON_UNESCAPED_UNICODE)"),

  /**
   * 快递公司系统错误  Delivery side sys error
   */
  CODE_9300502(9300502, "快递公司系统错误"),

  /**
   * delivery_id 不存在  Specified delivery id is not registerred
   */
  CODE_9300503(9300503, "delivery_id 不存在"),

  /**
   * service_type 不存在  Specified delivery id has beed banned
   */
  CODE_9300504(9300504, "service_type 不存在"),

  /**
   * Shop banned
   */
  CODE_9300505(9300505, "Shop banned"),

  /**
   * 运单 ID 已经存在轨迹，不可取消  Order can't cancel
   */
  CODE_9300506(9300506, "运单 ID 已经存在轨迹，不可取消"),

  /**
   * Token 不正确  invalid token, can't decryption or  decryption result is different from the plaintext
   */
  CODE_9300507(9300507, "Token 不正确"),

  /**
   * order id has been used
   */
  CODE_9300508(9300508, "order id has been used"),

  /**
   * speed limit, retry too fast
   */
  CODE_9300509(9300509, "speed limit, retry too fast"),

  /**
   * invalid service type
   */
  CODE_9300510(9300510, "invalid service type"),

  /**
   * invalid branch id
   */
  CODE_9300511(9300511, "invalid branch id"),

  /**
   * 模板格式错误，渲染失败  invalid waybill template format
   */
  CODE_9300512(9300512, "模板格式错误，渲染失败"),

  /**
   * out of quota
   */
  CODE_9300513(9300513, "out of quota"),

  /**
   * add net branch fail, try update branch api
   */
  CODE_9300514(9300514, "add net branch fail, try update branch api"),

  /**
   * wxa appid not exist
   */
  CODE_9300515(9300515, "wxa appid not exist"),

  /**
   * wxa appid and current bizuin is not  linked or not the same owner
   */
  CODE_9300516(9300516, "wxa appid and current bizuin is not  linked or not the same owner"),

  /**
   * update_type 不正确,请使用"bind" 或者“unbind”  invalid update_type, please use [bind] or [unbind]
   */
  CODE_9300517(9300517, "update_type 不正确,请使用\"bind\" 或者“unbind”"),

  /**
   * invalid delivery id
   */
  CODE_9300520(9300520, "invalid delivery id"),

  /**
   * the orderid is in our system, and waybill is generating
   */
  CODE_9300521(9300521, "the orderid is in our system, and waybill is generating"),

  /**
   * this orderid is repeated
   */
  CODE_9300522(9300522, "this orderid is repeated"),

  /**
   * quota is not enough; go to charge please
   */
  CODE_9300523(9300523, "quota is not enough; go to charge please"),

  /**
   * 订单已取消（一般为重复取消订单）  order already canceled
   */
  CODE_9300524(9300524, "订单已取消（一般为重复取消订单）"),

  /**
   * bizid未绑定  biz id not bind
   */
  CODE_9300525(9300525, "bizid未绑定"),

  /**
   * 参数字段长度不正确  arg size exceed limit
   */
  CODE_9300526(9300526, "参数字段长度不正确"),

  /**
   * delivery does not support quota
   */
  CODE_9300527(9300527, "delivery does not support quota"),

  /**
   * invalid waybill_id
   */
  CODE_9300528(9300528, "invalid waybill_id"),

  /**
   * 账号已绑定过  biz_id already binded
   */
  CODE_9300529(9300529, "账号已绑定过"),

  /**
   * 解绑的biz_id不存在  biz_id is not exist
   */
  CODE_9300530(9300530, "解绑的biz_id不存在"),

  /**
   * bizid无效 或者密码错误  invalid biz_id or password
   */
  CODE_9300531(9300531, "bizid无效 或者密码错误"),

  /**
   * 绑定已提交，审核中  bind submit, and is checking
   */
  CODE_9300532(9300532, "绑定已提交，审核中"),

  /**
   * invalid tagid_list
   */
  CODE_9300533(9300533, "invalid tagid_list"),

  /**
   * add_source=2时，wx_appid和当前小程序不同主体  invalid appid, not same body
   */
  CODE_9300534(9300534, "add_source=2时，wx_appid和当前小程序不同主体"),

  /**
   * shop字段商品缩略图 url、商品名称为空或者非法，或者商品数量为0  invalid shop arg
   */
  CODE_9300535(9300535, "shop字段商品缩略图 url、商品名称为空或者非法，或者商品数量为0"),

  /**
   * add_source=2时，wx_appid无效  invalid wxa_appid
   */
  CODE_9300536(9300536, "add_source=2时，wx_appid无效"),

  /**
   * freq limit
   */
  CODE_9300537(9300537, "freq limit"),

  /**
   * input task empty
   */
  CODE_9300538(9300538, "input task empty"),

  /**
   * too many task
   */
  CODE_9300539(9300539, "too many task"),

  /**
   * task not exist
   */
  CODE_9300540(9300540, "task not exist"),

  /**
   * delivery callback error
   */
  CODE_9300541(9300541, "delivery callback error"),

  /**
   * id_card_no is invalid
   */
  CODE_9300601(9300601, "id_card_no is invalid"),

  /**
   * name is invalid
   */
  CODE_9300602(9300602, "name is invalid"),

  /**
   * plate_no is invalid
   */
  CODE_9300603(9300603, "plate_no is invalid"),

  /**
   * auth_key decode error
   */
  CODE_9300604(9300604, "auth_key decode error"),

  /**
   * auth_key is expired
   */
  CODE_9300605(9300605, "auth_key is expired"),

  /**
   * auth_key and appinfo not match
   */
  CODE_9300606(9300606, "auth_key and appinfo not match"),

  /**
   * user not confirm
   */
  CODE_9300607(9300607, "user not confirm"),

  /**
   * user confirm is expired
   */
  CODE_9300608(9300608, "user confirm is expired"),

  /**
   * api exceed limit
   */
  CODE_9300609(9300609, "api exceed limit"),

  /**
   * car license info is invalid
   */
  CODE_9300610(9300610, "car license info is invalid"),

  /**
   * varification type not support
   */
  CODE_9300611(9300611, "varification type not support"),

  /**
   * input param error
   */
  CODE_9300701(9300701, "input param error"),

  /**
   * this code has been used
   */
  CODE_9300702(9300702, "this code has been used"),

  /**
   * invalid date
   */
  CODE_9300703(9300703, "invalid date"),

  /**
   * not currently available
   */
  CODE_9300704(9300704, "not currently available"),

  /**
   * code not exist or expired
   */
  CODE_9300705(9300705, "code not exist or expired"),

  /**
   * code not exist or expired
   */
  CODE_9300706(9300706, "code not exist or expired"),

  /**
   * wxpay error
   */
  CODE_9300707(9300707, "wxpay error"),

  /**
   * wxpay overlimit
   */
  CODE_9300708(9300708, "wxpay overlimit"),

  /**
   * 无效的微信号
   */
  CODE_9300801(9300801, "无效的微信号"),

  /**
   * 服务号未开通导购功能
   */
  CODE_9300802(9300802, "服务号未开通导购功能"),

  /**
   * 微信号已经绑定为导购
   */
  CODE_9300803(9300803, "微信号已经绑定为导购"),

  /**
   * 该微信号不是导购
   */
  CODE_9300804(9300804, "该微信号不是导购"),

  /**
   * 微信号已经被其他账号绑定为导购
   */
  CODE_9300805(9300805, "微信号已经被其他账号绑定为导购"),

  /**
   * 粉丝和导购不存在绑定关系
   */
  CODE_9300806(9300806, "粉丝和导购不存在绑定关系"),

  /**
   * 标签值无效，不是可选标签值
   */
  CODE_9300807(9300807, "标签值无效，不是可选标签值"),

  /**
   * 标签值不存在
   */
  CODE_9300808(9300808, "标签值不存在"),

  /**
   * 展示标签值不存在
   */
  CODE_9300809(9300809, "展示标签值不存在"),

  /**
   * 导购昵称太长，最多16个字符
   */
  CODE_9300810(9300810, "导购昵称太长，最多16个字符"),

  /**
   * 只支持mmbiz.qpic.cn域名的图片
   */
  CODE_9300811(9300811, "只支持mmbiz.qpic.cn域名的图片"),

  /**
   * 达到导购绑定个数限制
   */
  CODE_9300812(9300812, "达到导购绑定个数限制"),

  /**
   * 达到导购粉丝绑定个数限制
   */
  CODE_9300813(9300813, "达到导购粉丝绑定个数限制"),

  /**
   * 敏感词个数超过上限
   */
  CODE_9300814(9300814, "敏感词个数超过上限"),

  /**
   * 快捷回复个数超过上限
   */
  CODE_9300815(9300815, "快捷回复个数超过上限"),

  /**
   * 文字素材个数超过上限
   */
  CODE_9300816(9300816, "文字素材个数超过上限"),

  /**
   * 小程序卡片素材个数超过上限
   */
  CODE_9300817(9300817, "小程序卡片素材个数超过上限"),

  /**
   * 图片素材个数超过上限
   */
  CODE_9300818(9300818, "图片素材个数超过上限"),

  /**
   * mediaid 有误
   */
  CODE_9300819(9300819, "mediaid 有误"),

  /**
   * 可查询标签类别超过上限
   */
  CODE_9300820(9300820, "可查询标签类别超过上限"),

  /**
   * 小程序卡片内appid不符合要求
   */
  CODE_9300821(9300821, "小程序卡片内appid不符合要求"),

  /**
   * 标签类别的名字无效
   */
  CODE_9300822(9300822, "标签类别的名字无效"),

  /**
   * 查询聊天记录时间参数有误
   */
  CODE_9300823(9300823, "查询聊天记录时间参数有误"),

  /**
   * 自动回复字数太长
   */
  CODE_9300824(9300824, "自动回复字数太长"),

  /**
   * 导购群组id错误
   */
  CODE_9300825(9300825, "导购群组id错误"),

  /**
   * 维护中
   */
  CODE_9300826(9300826, "维护中"),

  /**
   * invalid parameter
   */
  CODE_9301001(9301001, "invalid parameter"),

  /**
   * call api service failed
   */
  CODE_9301002(9301002, "call api service failed"),

  /**
   * internal exception
   */
  CODE_9301003(9301003, "internal exception"),

  /**
   * save data error
   */
  CODE_9301004(9301004, "save data error"),

  /**
   * invalid appid
   */
  CODE_9301006(9301006, "invalid appid"),

  /**
   * invalid api config
   */
  CODE_9301007(9301007, "invalid api config"),

  /**
   * invalid api info
   */
  CODE_9301008(9301008, "invalid api info"),

  /**
   * add result check failed
   */
  CODE_9301009(9301009, "add result check failed"),

  /**
   * consumption failure
   */
  CODE_9301010(9301010, "consumption failure"),

  /**
   * frequency limit reached
   */
  CODE_9301011(9301011, "frequency limit reached"),

  /**
   * service timeout
   */
  CODE_9301012(9301012, "service timeout"),

  /**
   * 该开发小程序已开通小程序直播权限，不支持发布版本。如需发版，请解绑开发小程序后再操作。
   */
  CODE_9400001(9400001, "该开发小程序已开通小程序直播权限，不支持发布版本。如需发版，请解绑开发小程序后再操作。"),

  /**
   * 商品已存在
   */
  CODE_9401001(9401001, "商品已存在"),

  /**
   * 商品不存在
   */
  CODE_9401002(9401002, "商品不存在"),

  /**
   * 类目已存在
   */
  CODE_9401003(9401003, "类目已存在"),

  /**
   * 类目不存在
   */
  CODE_9401004(9401004, "类目不存在"),

  /**
   * SKU已存在
   */
  CODE_9401005(9401005, "SKU已存在"),

  /**
   * SKU不存在
   */
  CODE_9401006(9401006, "SKU不存在"),

  /**
   * 属性已存在
   */
  CODE_9401007(9401007, "属性已存在"),

  /**
   * 属性不存在
   */
  CODE_9401008(9401008, "属性不存在"),

  /**
   * 非法参数
   */
  CODE_9401020(9401020, "非法参数"),

  /**
   * 没有商品权限
   */
  CODE_9401021(9401021, "没有商品权限"),

  /**
   * SPU NOT ALLOW
   */
  CODE_9401022(9401022, "SPU NOT ALLOW"),

  /**
   * SPU_NOT_ALLOW_EDIT
   */
  CODE_9401023(9401023, "SPU_NOT_ALLOW_EDIT"),

  /**
   * SKU NOT ALLOW
   */
  CODE_9401024(9401024, "SKU NOT ALLOW"),

  /**
   * SKU_NOT_ALLOW_EDIT
   */
  CODE_9401025(9401025, "SKU_NOT_ALLOW_EDIT"),

  /**
   * limit too large
   */
  CODE_9402001(9402001, "limit too large"),

  /**
   * single send been blocked
   */
  CODE_9402002(9402002, "single send been blocked"),

  /**
   * all send been blocked
   */
  CODE_9402003(9402003, "all send been blocked"),

  /**
   * invalid msg id
   */
  CODE_9402004(9402004, "invalid msg id"),

  /**
   * send msg too quick
   */
  CODE_9402005(9402005, "send msg too quick"),

  /**
   * send to single user too quick
   */
  CODE_9402006(9402006, "send to single user too quick"),

  /**
   * send to all user too quick
   */
  CODE_9402007(9402007, "send to all user too quick"),

  /**
   * send type error
   */
  CODE_9402008(9402008, "send type error"),

  /**
   * can not send this msg
   */
  CODE_9402009(9402009, "can not send this msg"),

  /**
   * content too long or no content
   */
  CODE_9402010(9402010, "content too long or no content"),

  /**
   * path not exist
   */
  CODE_9402011(9402011, "path not exist"),

  /**
   * contain evil word
   */
  CODE_9402012(9402012, "contain evil word"),

  /**
   * path need html suffix
   */
  CODE_9402013(9402013, "path need html suffix"),

  /**
   * not open to personal body type
   */
  CODE_9402014(9402014, "not open to personal body type"),

  /**
   * not open to violation body type
   */
  CODE_9402015(9402015, "not open to violation body type"),

  /**
   * not open to low quality provider
   */
  CODE_9402016(9402016, "not open to low quality provider"),

  /**
   * invalid product_id
   */
  CODE_9402101(9402101, "invalid product_id"),

  /**
   * device_id count more than limit
   */
  CODE_9402102(9402102, "device_id count more than limit"),

  /**
   * 请勿频繁提交，待上一次操作完成后再提交  concurrent limit
   */
  CODE_9402202(9402202, "请勿频繁提交，待上一次操作完成后再提交"),

  /**
   * user not book this ad id
   */
  CODE_9402301(9402301, "user not book this ad id"),

  /**
   * 消息类型错误!
   */
  CODE_9403000(9403000, "消息类型错误!"),

  /**
   * 消息字段的内容过长!
   */
  CODE_9403001(9403001, "消息字段的内容过长!"),

  /**
   * 消息字段的内容违规!
   */
  CODE_9403002(9403002, "消息字段的内容违规!"),

  /**
   * 发送的微信号太多!
   */
  CODE_9403003(9403003, "发送的微信号太多!"),

  /**
   * 存在错误的微信号!
   */
  CODE_9403004(9403004, "存在错误的微信号!"),

  /**
   * 直播间列表为空  live room not exsits
   */
  CODE_9410000(9410000, "直播间列表为空"),

  /**
   * 获取房间失败  inner error: get room fail
   */
  CODE_9410001(9410001, "获取房间失败"),

  /**
   * 获取商品失败  inner error: get goods fail
   */
  CODE_9410002(9410002, "获取商品失败"),

  /**
   * 获取回放失败  inner error: get replay url fail
   */
  CODE_9410003(9410003, "获取回放失败");


  private final int code;
  private final String msg;

  WxOpenErrorMsgEnum(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  static final Map<Integer, String> valueMap = Maps.newHashMap();

  static {
    for (WxOpenErrorMsgEnum value : WxOpenErrorMsgEnum.values()) {
      valueMap.put(value.code, value.msg);
    }
  }

  /**
   * 通过错误代码查找其中文含义.
   */
  public static String findMsgByCode(int code) {
    return valueMap.getOrDefault(code, null);
  }
}
