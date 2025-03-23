package cn.binarywang.wx.miniapp.constant;

import lombok.experimental.UtilityClass;

/**
 * 小程序接口地址常量.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on 2021-01-28
 */
@UtilityClass
public class WxMaApiUrlConstants {

  /** openApi管理 */
  public interface OpenApi {
    /** 重置API调用次数 */
    String CLEAR_QUOTA = "https://api.weixin.qq.com/cgi-bin/clear_quota";

    /** 查询API调用额度 */
    String GET_API_QUOTA = "https://api.weixin.qq.com/cgi-bin/openapi/quota/get";

    /** 查询rid信息 */
    String GET_RID_INFO = "https://api.weixin.qq.com/cgi-bin/openapi/rid/get";

    /** 使用AppSecret重置 API 调用次数 */
    String CLEAR_QUOTA_BY_APP_SECRET =
        "https://api.weixin.qq.com/cgi-bin/clear_quota/v2?appid=%s&appsecret=%s";
  }

  public interface Analysis {
    String GET_DAILY_SUMMARY_TREND_URL =
        "https://api.weixin.qq.com/datacube/getweanalysisappiddailysummarytrend";
    String GET_DAILY_VISIT_TREND_URL =
        "https://api.weixin.qq.com/datacube/getweanalysisappiddailyvisittrend";
    String GET_WEEKLY_VISIT_TREND_URL =
        "https://api.weixin.qq.com/datacube/getweanalysisappidweeklyvisittrend";
    String GET_MONTHLY_VISIT_TREND_URL =
        "https://api.weixin.qq.com/datacube/getweanalysisappidmonthlyvisittrend";
    String GET_VISIT_DISTRIBUTION_URL =
        "https://api.weixin.qq.com/datacube/getweanalysisappidvisitdistribution";
    String GET_DAILY_RETAIN_INFO_URL =
        "https://api.weixin.qq.com/datacube/getweanalysisappiddailyretaininfo";
    String GET_WEEKLY_RETAIN_INFO_URL =
        "https://api.weixin.qq.com/datacube/getweanalysisappidweeklyretaininfo";
    String GET_MONTHLY_RETAIN_INFO_URL =
        "https://api.weixin.qq.com/datacube/getweanalysisappidmonthlyretaininfo";
    String GET_VISIT_PAGE_URL = "https://api.weixin.qq.com/datacube/getweanalysisappidvisitpage";
    String GET_USER_PORTRAIT_URL =
        "https://api.weixin.qq.com/datacube/getweanalysisappiduserportrait";
  }

  public interface Cloud {
    String INVOKE_CLOUD_FUNCTION_URL =
        "https://api.weixin.qq.com/tcb/invokecloudfunction?env=%s&name=%s";
    String DATABASE_COLLECTION_GET_URL = "https://api.weixin.qq.com/tcb/databasecollectionget";
    String DATABASE_COLLECTION_DELETE_URL =
        "https://api.weixin.qq.com/tcb/databasecollectiondelete";
    String DATABASE_COLLECTION_ADD_URL = "https://api.weixin.qq.com/tcb/databasecollectionadd";
    String GET_QCLOUD_TOKEN_URL = "https://api.weixin.qq.com/tcb/getqcloudtoken";
    String BATCH_DELETE_FILE_URL = "https://api.weixin.qq.com/tcb/batchdeletefile";
    String BATCH_DOWNLOAD_FILE_URL = "https://api.weixin.qq.com/tcb/batchdownloadfile";
    String UPLOAD_FILE_URL = "https://api.weixin.qq.com/tcb/uploadfile";
    String DATABASE_MIGRATE_QUERY_INFO_URL =
        "https://api.weixin.qq.com/tcb/databasemigratequeryinfo";
    String DATABASE_MIGRATE_EXPORT_URL = "https://api.weixin.qq.com/tcb/databasemigrateexport";
    String DATABASE_MIGRATE_IMPORT_URL = "https://api.weixin.qq.com/tcb/databasemigrateimport";
    String UPDATE_INDEX_URL = "https://api.weixin.qq.com/tcb/updateindex";
    String DATABASE_COUNT_URL = "https://api.weixin.qq.com/tcb/databasecount";
    String DATABASE_AGGREGATE_URL = "https://api.weixin.qq.com/tcb/databaseaggregate";
    String DATABASE_QUERY_URL = "https://api.weixin.qq.com/tcb/databasequery";
    String DATABASE_UPDATE_URL = "https://api.weixin.qq.com/tcb/databaseupdate";
    String DATABASE_DELETE_URL = "https://api.weixin.qq.com/tcb/databasedelete";
    String DATABASE_ADD_URL = "https://api.weixin.qq.com/tcb/databaseadd";
    String SEND_SMS_V2_URL = "https://api.weixin.qq.com/tcb/sendsmsv2";
  }

  public interface Msg {
    String KEFU_MESSAGE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
    String TEMPLATE_MSG_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send";
    String SUBSCRIBE_MSG_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send";
    String UNIFORM_MSG_SEND_URL =
        "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send";
    String ACTIVITY_ID_CREATE_URL =
        "https://api.weixin.qq.com/cgi-bin/message/wxopen/activityid/create";
    String UPDATABLE_MSG_SEND_URL =
        "https://api.weixin.qq.com/cgi-bin/message/wxopen/updatablemsg/send";
  }

  public interface Code {
    /** 为授权的小程序帐号上传小程序代码. */
    String COMMIT_URL = "https://api.weixin.qq.com/wxa/commit";

    String GET_QRCODE_URL = "https://api.weixin.qq.com/wxa/get_qrcode";
    String GET_CATEGORY_URL = "https://api.weixin.qq.com/wxa/get_category";
    String GET_PAGE_URL = "https://api.weixin.qq.com/wxa/get_page";
    String SUBMIT_AUDIT_URL = "https://api.weixin.qq.com/wxa/submit_audit";
    String GET_AUDIT_STATUS_URL = "https://api.weixin.qq.com/wxa/get_auditstatus";
    String GET_LATEST_AUDIT_STATUS_URL = "https://api.weixin.qq.com/wxa/get_latest_auditstatus";
    String RELEASE_URL = "https://api.weixin.qq.com/wxa/release";
    String CHANGE_VISIT_STATUS_URL = "https://api.weixin.qq.com/wxa/change_visitstatus";
    String REVERT_CODE_RELEASE_URL = "https://api.weixin.qq.com/wxa/revertcoderelease";
    String GET_SUPPORT_VERSION_URL =
        "https://api.weixin.qq.com/cgi-bin/wxopen/getweappsupportversion";
    String SET_SUPPORT_VERSION_URL =
        "https://api.weixin.qq.com/cgi-bin/wxopen/setweappsupportversion";
    String UNDO_CODE_AUDIT_URL = "https://api.weixin.qq.com/wxa/undocodeaudit";
    String GET_VERSION_INFO_URL = "https://api.weixin.qq.com/wxa/getversioninfo";
  }

  public interface Express {
    /** 获取支持的快递公司列表 */
    String ALL_DELIVERY_URL = "https://api.weixin.qq.com/cgi-bin/express/business/delivery/getall";

    /** 获取所有绑定的物流账号 */
    String ALL_ACCOUNT_URL = "https://api.weixin.qq.com/cgi-bin/express/business/account/getall";

    /** 绑定、解绑物流账号 */
    String BIND_ACCOUNT_URL = "https://api.weixin.qq.com/cgi-bin/express/business/account/bind";

    /** 获取电子面单余额 */
    String GET_QUOTA_URL = "https://api.weixin.qq.com/cgi-bin/express/business/quota/get";

    /** 配置面单打印员 */
    String UPDATE_PRINTER_URL = "https://api.weixin.qq.com/cgi-bin/express/business/printer/update";

    /** 获取打印员 */
    String GET_PRINTER_URL = "https://api.weixin.qq.com/cgi-bin/express/business/printer/getall";

    /** 生成运单 */
    String ADD_ORDER_URL = "https://api.weixin.qq.com/cgi-bin/express/business/order/add";

    /** 批量获取运单数据 */
    String BATCH_GET_ORDER_URL =
        "https://api.weixin.qq.com/cgi-bin/express/business/order/batchget";

    /** 取消运单 */
    String CANCEL_ORDER_URL = "https://api.weixin.qq.com/cgi-bin/express/business/order/cancel";

    /** 获取运单数据 */
    String GET_ORDER_URL = "https://api.weixin.qq.com/cgi-bin/express/business/order/get";

    /** 查询运单轨迹 */
    String GET_PATH_URL = "https://api.weixin.qq.com/cgi-bin/express/business/path/get";

    /** 模拟快递公司更新订单状态 */
    String TEST_UPDATE_ORDER_URL =
        "https://api.weixin.qq.com/cgi-bin/express/business/test_update_order";
  }

  public interface ImgProc {
    /** 二维码/条码识别 */
    String QRCODE = "https://api.weixin.qq.com/cv/img/qrcode?img_url=%s";

    /** 二维码/条码识别(文件) */
    String FILE_QRCODE = "https://api.weixin.qq.com/cv/img/qrcode";

    /** 图片高清化 */
    String SUPER_RESOLUTION = "https://api.weixin.qq.com/cv/img/superresolution?img_url=%s";

    /** 图片高清化(文件) */
    String FILE_SUPER_RESOLUTION = "https://api.weixin.qq.com/cv/img/superresolution";

    /** 图片智能裁剪 */
    String AI_CROP = "https://api.weixin.qq.com/cv/img/aicrop?img_url=%s&ratios=%s";

    /** 图片智能裁剪(文件) */
    String FILE_AI_CROP = "https://api.weixin.qq.com/cv/img/aicrop?ratios=%s";
  }

  public interface Jsapi {
    /** 获得jsapi_ticket的url */
    String GET_JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
  }

  public interface Broadcast {
    /** 直播间管理相关接口 */
    interface Room {
      /** 创建直播间 */
      String CREATE_ROOM = "https://api.weixin.qq.com/wxaapi/broadcast/room/create";

      /** 获取直播间列表 获取直播间回放 */
      String GET_LIVE_INFO = "https://api.weixin.qq.com/wxa/business/getliveinfo";

      /** 直播间导入商品 */
      String ADD_GOODS = "https://api.weixin.qq.com/wxaapi/broadcast/room/addgoods";

      /** 删除直播间 */
      String DELETE_ROOM = "https://api.weixin.qq.com/wxaapi/broadcast/room/deleteroom";

      /** 编辑直播间 */
      String EDIT_ROOM = "https://api.weixin.qq.com/wxaapi/broadcast/room/editroom";

      /** 获取直播间推流地址 */
      String GET_PUSH_URL = "https://api.weixin.qq.com/wxaapi/broadcast/room/getpushurl";

      /** 获取直播间分享二维码 */
      String GET_SHARED_CODE = "https://api.weixin.qq.com/wxaapi/broadcast/room/getsharedcode";

      /** 添加管理直播间小助手 */
      String ADD_ASSISTANT = "https://api.weixin.qq.com/wxaapi/broadcast/room/addassistant";

      /** 修改管理直播间小助手 */
      String MODIFY_ASSISTANT = "https://api.weixin.qq.com/wxaapi/broadcast/room/modifyassistant";

      /** 删除管理直播间小助手 */
      String REMOVE_ASSISTANT = "https://api.weixin.qq.com/wxaapi/broadcast/room/removeassistant";

      /** 查询管理直播间小助手 */
      String GET_ASSISTANT_LIST =
          "https://api.weixin.qq.com/wxaapi/broadcast/room/getassistantlist";

      /** 添加主播副号 */
      String ADD_SUBANCHOR = "https://api.weixin.qq.com/wxaapi/broadcast/room/addsubanchor";

      /** 修改主播副号 */
      String MODIFY_SUBANCHOR = "https://api.weixin.qq.com/wxaapi/broadcast/room/modifysubanchor";

      /** 删除主播副号 */
      String DELETE_SUBANCHOR = "https://api.weixin.qq.com/wxaapi/broadcast/room/deletesubanchor";

      /** 获取主播副号 */
      String GET_SUBANCHOR = "https://api.weixin.qq.com/wxaapi/broadcast/room/getsubanchor";

      /** 开启/关闭直播间官方收录 */
      String UPDATE_FEED_PUBLIC =
          "https://api.weixin.qq.com/wxaapi/broadcast/room/updatefeedpublic";

      /** 开启/关闭回放功能 */
      String UPDATE_REPLAY = "https://api.weixin.qq.com/wxaapi/broadcast/room/updatereplay";

      /** 开启/关闭客服功能 */
      String UPDATE_KF = "https://api.weixin.qq.com/wxaapi/broadcast/room/updatekf";

      /** 开启/关闭直播间全局禁言 */
      String UPDATE_COMMENT = "https://api.weixin.qq.com/wxaapi/broadcast/room/updatecomment";

      /** 上下架商品 */
      String ONSALE = "https://api.weixin.qq.com/wxaapi/broadcast/goods/onsale";

      /** 删除商品 */
      String DELETE_IN_ROOM = "https://api.weixin.qq.com/wxaapi/broadcast/goods/deleteInRoom";

      /** 推送商品 */
      String PUSH = "https://api.weixin.qq.com/wxaapi/broadcast/goods/push";

      /** 商品排序 */
      String SORT = "https://api.weixin.qq.com/wxaapi/broadcast/goods/sort";

      /** 下载商品讲解视频 */
      String GET_VIDEO = "https://api.weixin.qq.com/wxaapi/broadcast/goods/getVideo";
    }

    /** 直播商品管理相关接口 */
    interface Goods {
      String ADD_GOODS = "https://api.weixin.qq.com/wxaapi/broadcast/goods/add";
      String RESET_AUDIT_GOODS = "https://api.weixin.qq.com/wxaapi/broadcast/goods/resetaudit";
      String AUDIT_GOODS = "https://api.weixin.qq.com/wxaapi/broadcast/goods/audit";
      String DELETE_GOODS = "https://api.weixin.qq.com/wxaapi/broadcast/goods/delete";
      String UPDATE_GOODS = "https://api.weixin.qq.com/wxaapi/broadcast/goods/update";
      String GET_GOODS_WARE_HOUSE = "https://api.weixin.qq.com/wxa/business/getgoodswarehouse";
      String GET_APPROVED_GOODS = "https://api.weixin.qq.com/wxaapi/broadcast/goods/getapproved";

      /** 直播挂件设置全局 Key */
      String SET_KEY = "https://api.weixin.qq.com/wxaapi/broadcast/goods/setkey";

      /** 直播挂件获取全局 Key */
      String GET_KEY = "https://api.weixin.qq.com/wxaapi/broadcast/goods/getkey";
    }

    /** 小程序直播成员管理接口 */
    interface Role {
      String ADD_ROLE = "https://api.weixin.qq.com/wxaapi/broadcast/role/addrole";
      String DELETE_ROLE = "https://api.weixin.qq.com/wxaapi/broadcast/role/deleterole";
      String LIST_BY_ROLE = "https://api.weixin.qq.com/wxaapi/broadcast/role/getrolelist";
    }
  }

  public interface Media {
    String MEDIA_UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?type=%s";
    String MEDIA_GET_URL = "https://api.weixin.qq.com/cgi-bin/media/get";
  }

  public interface Plugin {
    String PLUGIN_URL = "https://api.weixin.qq.com/wxa/plugin";
  }

  public interface Qrcode {
    String CREATE_QRCODE_URL = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode";
    String GET_WXACODE_URL = "https://api.weixin.qq.com/wxa/getwxacode";
    String GET_WXACODE_UNLIMIT_URL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit";
  }

  public interface Run {}

  public interface Scheme {
    String GENERATE_SCHEME_URL = "https://api.weixin.qq.com/wxa/generatescheme";
    String GENERATE_NFC_SCHEME_URL = "https://api.weixin.qq.com/wxa/generatenfcscheme";
  }

  public interface Link {
    String GENERATE_URLLINK_URL = "https://api.weixin.qq.com/wxa/generate_urllink";
    String QUERY_URLLINK_URL = "https://api.weixin.qq.com/wxa/query_urllink";
  }

  public interface ShortLink {
    String GENERATE_SHORT_LINK_URL = "https://api.weixin.qq.com/wxa/genwxashortlink";
  }

  /** 小程序安全 */
  public interface SecCheck {
    String IMG_SEC_CHECK_URL = "https://api.weixin.qq.com/wxa/img_sec_check";
    String MSG_SEC_CHECK_URL = "https://api.weixin.qq.com/wxa/msg_sec_check";
    String MEDIA_CHECK_ASYNC_URL = "https://api.weixin.qq.com/wxa/media_check_async";

    /** 获取用户安全等级 */
    String GET_USER_RISK_RANK = "https://api.weixin.qq.com/wxa/getuserriskrank";
  }

  public interface Setting {
    /**
     * 修改服务器地址：https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1489138143_WPbOO&token=&lang=zh_CN
     * access_token 为 authorizer_access_token
     */
    String MODIFY_DOMAIN_URL = "https://api.weixin.qq.com/wxa/modify_domain";

    String SET_WEB_VIEW_DOMAIN_URL = "https://api.weixin.qq.com/wxa/setwebviewdomain";

    /**
     * 小程序成员管理：https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1489140588_nVUgx&token=&lang=zh_CN
     * access_token 为 authorizer_access_token
     */
    String BIND_TESTER_URL = "https://api.weixin.qq.com/wxa/bind_tester";

    String UNBIND_TESTER_URL = "https://api.weixin.qq.com/wxa/unbind_tester";
  }

  public interface Share {}

  public interface Subscribe {
    /** 获取模板标题下的关键词列表. */
    String GET_PUB_TEMPLATE_TITLE_LIST_URL =
        "https://api.weixin.qq.com/wxaapi/newtmpl/getpubtemplatetitles";

    /** 获取模板标题下的关键词列表. */
    String GET_PUB_TEMPLATE_KEY_WORDS_BY_ID_URL =
        "https://api.weixin.qq.com/wxaapi/newtmpl/getpubtemplatekeywords";

    /** 组合模板并添加至帐号下的个人模板库. */
    String TEMPLATE_ADD_URL = "https://api.weixin.qq.com/wxaapi/newtmpl/addtemplate";

    /** 获取当前帐号下的个人模板列表. */
    String TEMPLATE_LIST_URL = "https://api.weixin.qq.com/wxaapi/newtmpl/gettemplate";

    /** 删除帐号下的某个模板. */
    String TEMPLATE_DEL_URL = "https://api.weixin.qq.com/wxaapi/newtmpl/deltemplate";

    /** 获取小程序账号的类目 */
    String GET_CATEGORY_URL = "https://api.weixin.qq.com/wxaapi/newtmpl/getcategory";

    /** 发送订阅消息 */
    String SUBSCRIBE_MSG_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send";
  }

  public interface User {
    String SET_USER_STORAGE =
        "https://api.weixin.qq.com/wxa/set_user_storage?appid=%s&signature=%s&openid=%s&sig_method=%s";
    String GET_PHONE_NUMBER_URL = "https://api.weixin.qq.com/wxa/business/getuserphonenumber";
  }

  public interface Ocr {
    String IDCARD = "https://api.weixin.qq.com/cv/ocr/idcard?img_url=%s";
    String FILEIDCARD = "https://api.weixin.qq.com/cv/ocr/idcard";
    String BANK_CARD = "https://api.weixin.qq.com/cv/ocr/bankcard?img_url=%s";
    String FILE_BANK_CARD = "https://api.weixin.qq.com/cv/ocr/bankcard";
    String DRIVING = "https://api.weixin.qq.com/cv/ocr/driving?img_url=%s";
    String FILE_DRIVING = "https://api.weixin.qq.com/cv/ocr/driving";
    String DRIVING_LICENSE = "https://api.weixin.qq.com/cv/ocr/drivinglicense?img_url=%s";
    String FILE_DRIVING_LICENSE = "https://api.weixin.qq.com/cv/ocr/drivinglicense";
    String BIZ_LICENSE = "https://api.weixin.qq.com/cv/ocr/bizlicense?img_url=%s";
    String FILE_BIZ_LICENSE = "https://api.weixin.qq.com/cv/ocr/bizlicense";
    String COMM = "https://api.weixin.qq.com/cv/ocr/comm?img_url=%s";
    String FILE_COMM = "https://api.weixin.qq.com/cv/ocr/comm";
  }

  public interface Product {
    interface Spu {
      String PRODUCT_SPU_ADD_URL = "https://api.weixin.qq.com/product/spu/add";
      String PRODUCT_SPU_DEL_URL = "https://api.weixin.qq.com/product/spu/del";
      String PRODUCT_SPU_GET_URL = "https://api.weixin.qq.com/product/spu/get";
      String PRODUCT_SPU_GET_LIST_URL = "https://api.weixin.qq.com/product/spu/get_list";
      String PRODUCT_SPU_UPDATE_URL = "https://api.weixin.qq.com/product/spu/update";
      String PRODUCT_SPU_LISTING_URL = "https://api.weixin.qq.com/product/spu/listing";
      String PRODUCT_SPU_DELISTING_URL = "https://api.weixin.qq.com/product/spu/delisting";
    }

    interface Sku {
      String PRODUCT_ADD_SKU_URL = "https://api.weixin.qq.com/product/sku/add";
      String PRODUCT_BATCH_ADD_SKU_URL = "https://api.weixin.qq.com/product/sku/batch_add";
      String PRODUCT_DEL_SKU_URL = "https://api.weixin.qq.com/product/sku/del";
      String PRODUCT_UPDATE_SKU_URL = "https://api.weixin.qq.com/product/sku/update";
      String PRODUCT_UPDATE_SKU_PRICE_URL = "https://api.weixin.qq.com/product/sku/update_price";
      String PRODUCT_UPDATE_SKU_STOCK_URL = "https://api.weixin.qq.com/product/stock/update";
      String PRODUCT_SKU_LIST = "https://api.weixin.qq.com/product/sku/get_list";
    }

    interface Order {
      String PRODUCT_ORDER_GET_LIST = "https://api.weixin.qq.com/product/order/get_list";
      String PRODUCT_ORDER_DETAIL_URL = "https://api.weixin.qq.com/product/order/get";
      String PRODUCT_ORDER_CHANGE_MERCHANT_NOTES_URL =
          "https://api.weixin.qq.com/product/order/change_merchant_notes";

      String PRODUCT_DELIVERY_SEND = "https://api.weixin.qq.com/product/delivery/send";

      String GET_AFTER_SALE_ORDER = "https://api.weixin.qq.com/product/order/getaftersaleorder";
      String BATCH_GET_AFTER_SALE_ORDER =
          "https://api.weixin.qq.com/product/order/batchgetaftersaleorder";
      String AFTER_SALE_ACCEPT_APPLY = "https://api.weixin.qq.com/product/order/acceptapply";
      String AFTER_SALE_REJECT_APPLY = "https://api.weixin.qq.com/product/order/rejectrefund";
    }

    interface OTHER {
      String GET_CATEGORY = "https://api.weixin.qq.com/product/category/get";
      String GET_BRAND = "https://api.weixin.qq.com/product/brand/get";
      String GET_FREIGHT_TEMPLATE =
          "https://api.weixin.qq.com/product/delivery/get_freight_template";
      String IMG_UPLOAD = "https://api.weixin.qq.com/product/img/upload";
    }
  }

  public interface Shop {
    interface Spu {
      String SPU_ADD_URL = "https://api.weixin.qq.com/shop/spu/add";
      String SPU_DEL_URL = "https://api.weixin.qq.com/shop/spu/del";
      String SPU_GET_URL = "https://api.weixin.qq.com/shop/spu/get";
      String SPU_GET_LIST_URL = "https://api.weixin.qq.com/shop/spu/get_list";
      String SPU_UPDATE_URL = "https://api.weixin.qq.com/shop/spu/update";
      String SPU_UPDATE_WITHOUT_URL = "https://api.weixin.qq.com/shop/spu/update_without_audit";
      String SPU_LISTING_URL = "https://api.weixin.qq.com/shop/spu/listing";
      String SPU_DELISTING_URL = "https://api.weixin.qq.com/shop/spu/delisting";
      String DEL_AUDIT_URL = "https://api.weixin.qq.com/shop/spu/del_audit";
    }

    interface Order {
      String ORDER_CHECK_SCENE = "https://api.weixin.qq.com/shop/scene/check";
      String ORDER_ADD = "https://api.weixin.qq.com/shop/order/add";
      String ORDER_PAY = "https://api.weixin.qq.com/shop/order/pay";
      String ORDER_GET = "https://api.weixin.qq.com/shop/order/get";
      String ORDER_GET_LIST = "https://api.weixin.qq.com/shop/order/get_list";
      String ORDER_GET_PAYMENT_PARAMS = "https://api.weixin.qq.com/shop/order/getpaymentparams";
    }

    interface Register {
      String REGISTER_APPLY = "https://api.weixin.qq.com/shop/register/apply";
      String REGISTER_CHECK = "https://api.weixin.qq.com/shop/register/check";
      String REGISTER_FINISH_ACCESS_INFO =
          "https://api.weixin.qq.com/shop/register/finish_access_info";
      String REGISTER_APPLY_SCENE = "https://api.weixin.qq.com/shop/register/apply_scene";
    }

    interface Account {
      String GET_CATEGORY_LIST = "https://api.weixin.qq.com/shop/account/get_category_list";
      String GET_BRAND_LIST = "https://api.weixin.qq.com/shop/account/get_brand_list";
      String UPDATE_INFO = "https://api.weixin.qq.com/shop/account/update_info";
      String GET_INFO = "https://api.weixin.qq.com/shop/account/get_info";
    }

    interface Cat {
      String GET_CAT = "https://api.weixin.qq.com/shop/cat/get";
    }

    interface Img {
      String IMG_UPLOAD = "https://api.weixin.qq.com/shop/img/upload";
    }

    interface Audit {
      String AUDIT_BRAND = "https://api.weixin.qq.com/shop/audit/audit_brand";
      String AUDIT_CATEGORY = "https://api.weixin.qq.com/shop/audit/audit_category";
      String AUDIT_RESULT = "https://api.weixin.qq.com/shop/audit/result";
      String GET_MINIAPP_CERTIFICATE =
          "https://api.weixin.qq.com/shop/audit/get_miniapp_certificate";
    }

    interface Delivery {
      String GET_COMPANY_LIST = "https://api.weixin.qq.com/shop/delivery/get_company_list";
      String DELIVERY_SEND = "https://api.weixin.qq.com/shop/delivery/send";
      String DELIVERY_RECEIVE = "https://api.weixin.qq.com/shop/delivery/recieve";
    }

    interface Aftersale {
      String AFTERSALE_ADD = "https://api.weixin.qq.com/shop/ecaftersale/add";
      String AFTERSALE_CANCEL = "https://api.weixin.qq.com/shop/ecaftersale/cancel";
      String AFTERSALE_UPDATE = "https://api.weixin.qq.com/shop/aftersale/update";
      String EC_AFTERSALE_UPDATE = "https://api.weixin.qq.com/shop/ecaftersale/update";
      String AFTERSALE_UPLOAD_RETURN_INFO =
          "https://api.weixin.qq.com/shop/ecaftersale/uploadreturninfo";
      String AFTERSALE_ACCEPT_REFUND = "https://api.weixin.qq.com/shop/ecaftersale/acceptrefund";
      String AFTERSALE_ACCEPT_RETURN = "https://api.weixin.qq.com/shop/ecaftersale/acceptreturn";
      String AFTERSALE_REJECT = "https://api.weixin.qq.com/shop/ecaftersale/reject";
      String AFTERSALE_UPLOAD_CERTIFICATES =
          "https://api.weixin.qq.com/shop/ecaftersale/upload_certificates";
      String AFTERSALE_UPLOAD_DEADLINE = "https://api.weixin.qq.com/shop/aftersale/update_deadline";
      String AFTERSALE_GET_LIST = "https://api.weixin.qq.com/shop/ecaftersale/get_list";
      String AFTERSALE_GET = "https://api.weixin.qq.com/shop/aftersale/get";
      String ECAFTERSALE_GET = "https://api.weixin.qq.com/shop/ecaftersale/get";
    }

    interface Sharer {
      String BIND = "https://api.weixin.qq.com/shop/sharer/bind";
      String GET_SHARER_DATA_SUMMARY =
          "https://api.weixin.qq.com/shop/sharer/get_sharer_data_summary";
      String GET_SHARER_LIST = "https://api.weixin.qq.com/shop/sharer/get_sharer_list";
      String GET_SHARER_LIVE_ORDER_LIST =
          "https://api.weixin.qq.com/shop/sharer/get_sharer_live_order_list";
      String GET_SHARER_LIVE_SUMMARY_LIST =
          "https://api.weixin.qq.com/shop/sharer/get_sharer_live_summary_list";
      String SEARCH_SHARER = "https://api.weixin.qq.com/shop/sharer/search_sharer";
      String UNBIND = "https://api.weixin.qq.com/shop/sharer/unbind";
    }

    interface Coupon {
      String ADD_COUPON = "https://api.weixin.qq.com/shop/coupon/add";
      String GET_COUPON = "https://api.weixin.qq.com/shop/coupon/get";
      String GET_COUPON_LIST = "https://api.weixin.qq.com/shop/coupon/get_list";
      String UPDATE_COUPON = "https://api.weixin.qq.com/shop/coupon/update";
      String UPDATE_COUPON_STATUS = "https://api.weixin.qq.com/shop/coupon/update_status";
      String UPDATE_COUPON_STOCK = "https://api.weixin.qq.com/shop/coupon/update_coupon_stock";
      String ADD_USER_COUPON = "https://api.weixin.qq.com/shop/coupon/add_user_coupon";
      String GET_USER_COUPON_LIST = "https://api.weixin.qq.com/shop/coupon/get_usercoupon_list";
      String UPDATE_USER_COUPON = "https://api.weixin.qq.com/shop/coupon/update_user_coupon";
      String UPDATE_USER_COUPON_STATUS =
          "https://api.weixin.qq.com/shop/coupon/update_usercoupon_status";
    }

    interface Pay {
      String CREATE_ORDER = "https://api.weixin.qq.com/shop/pay/createorder";
      String GET_ORDER = "https://api.weixin.qq.com/shop/pay/getorder";
      String REFUND_ORDER = "https://api.weixin.qq.com/shop/pay/refundorder";
    }
  }

  /** 电子发票报销方 */
  public interface Invoice {

    /** 报销方查询报销发票信息 */
    String GET_INVOICE_INFO = "https://api.weixin.qq.com/card/invoice/reimburse/getinvoiceinfo";

    /** 报销方批量查询报销发票信息 */
    String GET_INVOICE_BATCH = "https://api.weixin.qq.com/card/invoice/reimburse/getinvoicebatch";

    /** 报销方更新发票状态 */
    String UPDATE_INVOICE_STATUS =
        "https://api.weixin.qq.com/card/invoice/reimburse/updateinvoicestatus";

    /** 报销方批量更新发票状态 */
    String UPDATE_STATUS_BATCH =
        "https://api.weixin.qq.com/card/invoice/reimburse/updatestatusbatch";
  }

  public interface Internet {
    String GET_USER_ENCRYPT_KEY = "https://api.weixin.qq.com/wxa/business/getuserencryptkey";
  }

  /** 设备订阅消息 */
  public interface DeviceSubscribe {
    /** 获取设备票据 */
    String GET_SN_TICKET_URL = "https://api.weixin.qq.com/wxa/getsnticket";

    /** 发送设备订阅消息 */
    String SEND_DEVICE_SUBSCRIBE_MSG_URL =
        "https://api.weixin.qq.com/cgi-bin/message/device/subscribe/send";
  }

  /**
   * 即时配送相关接口.
   *
   * <pre>
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/industry/immediate-delivery/overview.html
   * </pre>
   */
  public interface InstantDelivery {

    /**
     * 拉取已绑定账号.
     *
     * <pre>
     * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.getBindAccount.html
     * </pre>
     */
    String GET_BIND_ACCOUNT = "https://api.weixin.qq.com/cgi-bin/express/local/business/shop/get";

    /**
     * 拉取配送单信息.
     *
     * <pre>
     * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.getOrder.html
     * </pre>
     */
    String GET_ORDER = "https://api.weixin.qq.com/cgi-bin/express/local/business/order/get";

    /**
     * 模拟配送公司更新配送单状态.
     *
     * <pre>
     * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.mockUpdateOrder.html
     * </pre>
     */
    String MOCK_UPDATE_ORDER =
        "https://api.weixin.qq.com/cgi-bin/express/local/business/test_update_order";

    /** 物流服务-查询组件-跟踪物流面单 商户使用此接口向微信提供某交易单号对应的运单号。微信后台会跟踪运单的状态变化 */
    String TRACE_WAYBILL_URL =
        "https://api.weixin.qq.com/cgi-bin/express/delivery/open_msg/trace_waybill";

    /** 物流服务-查询组件-查询运单接口 query_trace 商户在调用完trace_waybill接口后，可以使用本接口查询到对应运单的详情信息 */
    String QUERY_WAYBILL_TRACE_URL =
        "https://api.weixin.qq.com/cgi-bin/express/delivery/open_msg/query_trace";

    /** 物流服务-消息组件-传运单接口(订阅消息) follow_waybill 商户在调用完trace_waybill接口后，可以使用本接口查询到对应运单的详情信息 */
    String FOLLOW_WAYBILL_URL =
        "https://api.weixin.qq.com/cgi-bin/express/delivery/open_msg/follow_waybill";

    /** 物流服务-消息组件-查运单接口(订阅消息) query_follow_trace 商户在调用完trace_waybill接口后，可以使用本接口查询到对应运单的详情信息 */
    String QUERY_FOLLOW_TRACE_URL =
        "https://api.weixin.qq.com/cgi-bin/express/delivery/open_msg/query_follow_trace";

    /** 获取运力id列表get_delivery_list 商户使用此接口获取所有运力id的列表 */
    String GET_DELIVERY_LIST_URL =
        "https://api.weixin.qq.com/cgi-bin/express/delivery/open_msg/get_delivery_list";

    /** 获取运力id列表get_delivery_list 商户使用此接口获取所有运力id的列表 */
    String UPDATE_WAYBILL_GOODS_URL =
        "https://api.weixin.qq.com/cgi-bin/express/delivery/open_msg/update_waybill_goods";

    /** 下单接口. */
    interface PlaceAnOrder {

      /**
       * 获取已支持的配送公司列表接口.
       *
       * <pre>
       * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.getAllImmeDelivery.html
       * </pre>
       */
      String GET_ALL_IMME_DELIVERY =
          "https://api.weixin.qq.com/cgi-bin/express/local/business/delivery/getall";

      /**
       * 预下配送单接口.
       *
       * <pre>
       * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.preAddOrder.html
       * </pre>
       */
      String PRE_ADD_ORDER =
          "https://api.weixin.qq.com/cgi-bin/express/local/business/order/pre_add";

      /**
       * 下配送单接口.
       *
       * <pre>
       * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.addOrder.html
       * </pre>
       */
      String ADD_ORDER = "https://api.weixin.qq.com/cgi-bin/express/local/business/order/add";

      /**
       * 重新下单.
       *
       * <pre>
       * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.reOrder.html
       * </pre>
       */
      String RE_ORDER = "https://api.weixin.qq.com/cgi-bin/express/local/business/order/readd";

      /**
       * 增加小费.
       *
       * <pre>
       * 可以对待接单状态的订单增加小费。需要注意：订单的小费，以最新一次加小费动作的金额为准，故下一次增加小费额必须大于上一次小费额.
       * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.addTip.html
       * </pre>
       */
      String ADD_TIP = "https://api.weixin.qq.com/cgi-bin/express/local/business/order/addtips";
    }

    /** 取消接口. */
    interface Cancel {

      /**
       * 预取消配送单接口.
       *
       * <pre>
       * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.preCancelOrder.html
       * </pre>
       */
      String PRE_CANCEL_ORDER =
          "https://api.weixin.qq.com/cgi-bin/express/local/business/order/precancel";

      /**
       * 取消配送单接口.
       *
       * <pre>
       * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.cancelOrder.html
       * </pre>
       */
      String CANCEL_ORDER = "https://api.weixin.qq.com/cgi-bin/express/local/business/order/cancel";

      /**
       * 异常件退回商家商家确认收货接口.
       *
       * <pre>
       * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.abnormalConfirm.html
       * </pre>
       */
      String ABNORMAL_CONFIRM =
          "https://api.weixin.qq.com/cgi-bin/express/local/business/order/confirm_return";
    }
  }

  /**
   * 发货信息管理服务相关接口
   *
   * <pre>
   * 文档地址： https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/order-shipping/order-shipping.html#%E4%B8%80%E3%80%81%E5%8F%91%E8%B4%A7%E4%BF%A1%E6%81%AF%E5%BD%95%E5%85%A5%E6%8E%A5%E5%8F%A3
   * </pre>
   */
  public interface OrderShipping {

    /**
     * 查询小程序是否已开通发货信息管理服务.
     *
     * <pre>
     * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/order-shipping/order-shipping.html#%E4%B8%83%E3%80%81%E6%9F%A5%E8%AF%A2%E5%B0%8F%E7%A8%8B%E5%BA%8F%E6%98%AF%E5%90%A6%E5%B7%B2%E5%BC%80%E9%80%9A%E5%8F%91%E8%B4%A7%E4%BF%A1%E6%81%AF%E7%AE%A1%E7%90%86%E6%9C%8D%E5%8A%A1
     * </pre>
     */
    String IS_TRADE_MANAGED = "https://api.weixin.qq.com/wxa/sec/order/is_trade_managed";

    /**
     * 发货信息录入接口.
     *
     * <pre>
     * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/order-shipping/order-shipping.html#%E4%B8%80%E3%80%81%E5%8F%91%E8%B4%A7%E4%BF%A1%E6%81%AF%E5%BD%95%E5%85%A5%E6%8E%A5%E5%8F%A3
     * </pre>
     */
    String UPLOAD_SHIPPING_INFO = "https://api.weixin.qq.com/wxa/sec/order/upload_shipping_info";

    /**
     * 发货信息合单录入接口.
     *
     * <pre>
     * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/order-shipping/order-shipping.html#%E4%BA%8C%E3%80%81%E5%8F%91%E8%B4%A7%E4%BF%A1%E6%81%AF%E5%90%88%E5%8D%95%E5%BD%95%E5%85%A5%E6%8E%A5%E5%8F%A3
     * </pre>
     */
    String UPLOAD_COMBINED_SHIPPING_INFO =
      "https://api.weixin.qq.com/wxa/sec/order/upload_combined_shipping_info";

    /**
     * 查询订单发货状态.
     *
     * <pre>
     * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/order-shipping/order-shipping.html#%E4%BA%8C%E3%80%81%E5%8F%91%E8%B4%A7%E4%BF%A1%E6%81%AF%E5%90%88%E5%8D%95%E5%BD%95%E5%85%A5%E6%8E%A5%E5%8F%A3
     * </pre>
     */
    String GET_SHIPPING_INFO = "https://api.weixin.qq.com/wxa/sec/order/get_order";

    /**
     * 查询订单发货状态列表.
     *
     * <pre>
     * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/order-shipping/order-shipping.html#%E5%9B%9B%E3%80%81%E6%9F%A5%E8%AF%A2%E8%AE%A2%E5%8D%95%E5%88%97%E8%A1%A8
     * </pre>
     */
    String GET_SHIPPING_INFO_LIST = "https://api.weixin.qq.com/wxa/sec/order/get_order_list";

    /**
     * 确认收货提醒接口.
     *
     * <pre>
     * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/order-shipping/order-shipping.html#%E4%BA%94%E3%80%81%E7%A1%AE%E8%AE%A4%E6%94%B6%E8%B4%A7%E6%8F%90%E9%86%92%E6%8E%A5%E5%8F%A3
     * </pre>
     */
    String NOTIFY_CONFIRM_RECEIVE =
      "https://api.weixin.qq.com/wxa/sec/order/notify_confirm_receive";

    /**
     * 消息跳转路径设置接口.
     *
     * <pre>
     * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/order-shipping/order-shipping.html#%E5%85%AD%E3%80%81%E6%B6%88%E6%81%AF%E8%B7%B3%E8%BD%AC%E8%B7%AF%E5%BE%84%E8%AE%BE%E7%BD%AE%E6%8E%A5%E5%8F%A3
     * </pre>
     */
    String SET_MSG_JUMP_PATH = "https://api.weixin.qq.com/wxa/sec/order/set_msg_jump_path";

    /**
     * 查询小程序是否已完成交易结算管理确认.
     *
     * <pre>
     * 文档地址： https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/order-shipping/order-shipping.html#%E5%85%AB%E3%80%81%E6%9F%A5%E8%AF%A2%E5%B0%8F%E7%A8%8B%E5%BA%8F%E6%98%AF%E5%90%A6%E5%B7%B2%E5%AE%8C%E6%88%90%E4%BA%A4%E6%98%93%E7%BB%93%E7%AE%97%E7%AE%A1%E7%90%86%E7%A1%AE%E8%AE%A4
     * </pre>
     */
    String IS_TRADE_MANAGEMENT_CONFIRMATION_COMPLETED = "https://api.weixin.qq.com/wxa/sec/order/is_trade_management_confirmation_completed";
    /**
     * 特殊发货报备.
     *
     * <pre>
     * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/order-shipping/order-shipping.html#%E5%8D%81%E3%80%81%E7%89%B9%E6%AE%8A%E5%8F%91%E8%B4%A7%E6%8A%A5%E5%A4%87
     * </pre>
     */
    String OP_SPECIAL_ORDER = "https://api.weixin.qq.com/wxa/sec/order/opspecialorder";

  }

  /**
   * 小程序订单管理
   *
   * <pre>
   * 文档地址： https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/order_center/order_center.html
   * </pre>
   */
  public interface OrderManagement {

    /**
     * 配置订单详情路径.
     *
     * <pre>
     * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/order_center/order_center.html
     * </pre>
     */
    String UPDATE_ORDER_DETAIL_PATH = "https://api.weixin.qq.com/wxa/sec/order/update_order_detail_path";

    /**
     * 查询订单详情路径.
     *
     * <pre>
     * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/order_center/order_center.html
     * </pre>
     */
    String GET_ORDER_DETAIL_PATH = "https://api.weixin.qq.com/wxa/sec/order/get_order_detail_path";

  }

  public interface Vod {
    String LIST_MEDIA_URL = "https://api.weixin.qq.com/wxa/sec/vod/listmedia";
    String GET_MEDIA_URL = "https://api.weixin.qq.com/wxa/sec/vod/getmedia";
    String GET_MEDIA_LINK_URL = "https://api.weixin.qq.com/wxa/sec/vod/getmedialink";
    String DELETE_MEDIA_URL = "https://api.weixin.qq.com/wxa/sec/vod/deletemedia";
    String AUDIT_DRAMA_URL = "https://api.weixin.qq.com/wxa/sec/vod/auditdrama";
    String LIST_DRAMAS_URL = "https://api.weixin.qq.com/wxa/sec/vod/listdramas";
    String GET_DRAMA_URL = "https://api.weixin.qq.com/wxa/sec/vod/getdrama";
    String SINGLE_FILE_UPLOAD_URL = "https://api.weixin.qq.com/wxa/sec/vod/singlefileupload";
    String PULL_UPLOAD_URL = "https://api.weixin.qq.com/wxa/sec/vod/pullupload";
    String GET_TASK_URL = "https://api.weixin.qq.com/wxa/sec/vod/gettask";
    String APPLY_UPLOAD_URL = "https://api.weixin.qq.com/wxa/sec/vod/applyupload";
    String UPLOAD_PART_URL = "https://api.weixin.qq.com/wxa/sec/vod/uploadpart";
    String COMMIT_UPLOAD_URL = "https://api.weixin.qq.com/wxa/sec/vod/commitupload";
    String GET_CDN_USAGE_DATA_URL = "https://api.weixin.qq.com/wxa/sec/vod/getcdnusagedata";
    String GET_CDN_LOGS_URL = "https://api.weixin.qq.com/wxa/sec/vod/getcdnlogs";
  }

  /**
   * 小程序虚拟支付服务相关接口
   *
   * <pre>
   * 文档地址： https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/industry/virtual-payment.html#_2-3-%E6%9C%8D%E5%8A%A1%E5%99%A8API
   * </pre>
   */
  public interface XPay {
    String QUERY_USER_BALANCE_URL =
        "https://api.weixin.qq.com/xpay/query_user_balance?pay_sig=%s&signature=%s";
    String CURRENCY_PAY_URL = "https://api.weixin.qq.com/xpay/currency_pay?pay_sig=%s&signature=%s";
    String QUERY_ORDER_URL = "https://api.weixin.qq.com/xpay/query_order?pay_sig=%s";
    String CANCEL_CURRENCY_PAY_URL =
        "https://api.weixin.qq.com/xpay/cancel_currency_pay?pay_sig=%s&signature=%s";
    String NOTIFY_PROVIDE_GOODS_URL =
        "https://api.weixin.qq.com/xpay/notify_provide_goods?pay_sig=%s";
    String PRESENT_CURRENCY_URL = "https://api.weixin.qq.com/xpay/present_currency?pay_sig=%s";
    String DOWNLOAD_BILL_URL = "https://api.weixin.qq.com/xpay/download_bill?pay_sig=%s";
    String REFUND_ORDER_URL = "https://api.weixin.qq.com/xpay/refund_order?pay_sig=%s";
    String CREATE_WITHDRAW_ORDER_URL =
        "https://api.weixin.qq.com/xpay/create_withdraw_order?pay_sig=%s";
    String QUERY_WITHDRAW_ORDER_URL =
        "https://api.weixin.qq.com/xpay/query_withdraw_order?pay_sig=%s";
    String START_UPLOAD_GOODS_URL = "https://api.weixin.qq.com/xpay/start_upload_goods?pay_sig=%s";
    String QUERY_UPLOAD_GOODS_URL = "https://api.weixin.qq.com/xpay/query_upload_goods?pay_sig=%s";
    String START_PUBLISH_GOODS_URL =
        "https://api.weixin.qq.com/xpay/start_publish_goods?pay_sig=%s";
    String QUERY_PUBLISH_GOODS_URL =
        "https://api.weixin.qq.com/xpay/query_publish_goods?pay_sig=%s";
  }

  /**
   * 退货组件
   *
   * <pre>
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/industry/express/business/express_sale_return.html
   * </pre>
   */
  public interface ExpressDeliveryReturn {
    String ADD_DELIVERY_RETURN_URL =
        "https://api.weixin.qq.com/cgi-bin/express/delivery/return/add";
    String GET_DELIVERY_RETURN_URL =
        "https://api.weixin.qq.com/cgi-bin/express/delivery/return/get";
    String UNBIND_DELIVERY_RETURN_URL =
        "https://api.weixin.qq.com/cgi-bin/express/delivery/return/unbind";
  }

  /**
   *
   *
   * <pre> 小程序推广员
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/promoter/instruction/instruction.html
   * </pre>
   */
  public interface Promotion {
    String PROMOTION_ADD_ROLE = "https://api.weixin.qq.com/promoter/addrole";
    String PROMOTION_GET_ROLE = "https://api.weixin.qq.com/promoter/getrole";
    String PROMOTION_UPDATE_ROLE = "https://api.weixin.qq.com/promoter/updaterole";
    String PROMOTION_ADD_PROMOTER = "https://api.weixin.qq.com/promoter/addpromoter";
    String PROMOTION_GET_PROMOTER = "https://api.weixin.qq.com/promoter/getpromoter";
    String PROMOTION_UPDATE_PROMOTER = "https://api.weixin.qq.com/promoter/updatepromoter";
    String PROMOTION_GET_INVITATION_MATERIAL =
        "https://api.weixin.qq.com/promoter/getinvitationmaterial";
    String PROMOTION_SEND_MSG = "https://api.weixin.qq.com/promoter/sendmsg";
    String PROMOTION_SINGLE_SEND_MSG = "https://api.weixin.qq.com/promoter/singlesendmsg";
    String PROMOTION_GET_MSG = "https://api.weixin.qq.com/promoter/getmsg";
    String PROMOTION_GET_MSG_CLICK_DATA = "https://api.weixin.qq.com/promoter/getmsgclickdata";
    String PROMOTION_GET_SHARE_MATERIAL = "https://api.weixin.qq.com/promoter/getsharematerial";
    String PROMOTION_GET_RELATION = "https://api.weixin.qq.com/promoter/getrelation";
    String PROMOTION_GET_ORDER = "https://api.weixin.qq.com/promoter/getorder";
  }

  public interface Intracity {
    String APPLY_URL = "https://api.weixin.qq.com/cgi-bin/express/intracity/apply";
    String CREATE_STORE_URL = "https://api.weixin.qq.com/cgi-bin/express/intracity/createstore";
    String QUERY_STORE_URL = "https://api.weixin.qq.com/cgi-bin/express/intracity/querystore";
    String UPDATE_STORE_URL = "https://api.weixin.qq.com/cgi-bin/express/intracity/updatestore";

    String STORE_CHARGE = "https://api.weixin.qq.com/cgi-bin/express/intracity/storecharge";
    String STORE_REFUND = "https://api.weixin.qq.com/cgi-bin/express/intracity/storerefund";
    String QUERY_FLOW = "https://api.weixin.qq.com/cgi-bin/express/intracity/queryflow";
    String BALANCE_QUERY = "https://api.weixin.qq.com/cgi-bin/express/intracity/balancequery";
    String GET_PAY_MODE = "https://api.weixin.qq.com/cgi-bin/express/intracity/getpaymode";
    String SET_PAY_MODE = "https://api.weixin.qq.com/cgi-bin/express/intracity/setpaymode";

    String PRE_ADD_ORDER = "https://api.weixin.qq.com/cgi-bin/express/intracity/preaddorder";
    String ADD_ORDER = "https://api.weixin.qq.com/cgi-bin/express/intracity/addorder";
    String QUERY_ORDER = "https://api.weixin.qq.com/cgi-bin/express/intracity/queryorder";
    String CANCEL_ORDER = "https://api.weixin.qq.com/cgi-bin/express/intracity/cancelorder";

    String GET_CITY = "https://api.weixin.qq.com/cgi-bin/express/intracity/getcity";
  }
}
