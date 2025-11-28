package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.WxMaApiResponse;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.executor.ApiSignaturePostRequestExecutor;
import com.google.gson.JsonObject;
import java.util.Map;
import java.util.function.Function;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.service.WxImgProcService;
import me.chanjar.weixin.common.service.WxOcrService;
import me.chanjar.weixin.common.service.WxService;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;

/**
 * 微信小程序主服务接口，定义了所有小程序相关的核心操作方法。
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMaService extends WxService {
  /** 获取access_token. */
  String GET_ACCESS_TOKEN_URL =
      "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

  String GET_STABLE_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/stable_token";

  /** The constant JSCODE_TO_SESSION_URL. */
  String JSCODE_TO_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session";

  /** getPaidUnionId */
  String GET_PAID_UNION_ID_URL = "https://api.weixin.qq.com/wxa/getpaidunionid";

  /** 导入抽样数据 */
  String SET_DYNAMIC_DATA_URL = "https://api.weixin.qq.com/wxa/setdynamicdata";

  /**
   * 获取登录后的 session 信息。
   *
   * @param jsCode           登录时获取的 code
   * @return                 登录 session 结果对象
   * @throws WxErrorException 调用微信接口失败时抛出
   */
  WxMaJscode2SessionResult jsCode2SessionInfo(String jsCode) throws WxErrorException;

  /**
   * 导入抽样数据到微信后台，用于流量分配。
   * 第三方通过调用微信API，将数据写入到setdynamicdata这个API。每个Post数据包不超过5K，若数据过多可开多进（线）程并发导入数据（例如：数据量为十万量级可以开50个线程并行导数据）。
   * 文档地址：https://wsad.weixin.qq.com/wsad/zh_CN/htmledition/widget-docs-v3/html/custom/quickstart/implement/import/index.html
   * http请求方式：POST http(s)://api.weixin.qq.com/wxa/setdynamicdata?access_token=ACCESS_TOKEN
   *
   * @param lifespan         数据有效时间（秒），如 86400 表示一天
   * @param type             数据所属服务类目标识
   * @param scene            场景值，1 代表用于搜索的数据
   * @param data             推送到微信后台的数据列表（字符串类型）
   * @throws WxErrorException 调用微信接口失败时抛出
   */
  void setDynamicData(int lifespan, String type, int scene, String data) throws WxErrorException;

  /**
   * 校验消息是否来自微信服务器。
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421135319&token=&lang=zh_CN
   *
   * @param timestamp        时间戳
   * @param nonce            随机数
   * @param signature        签名字符串
   * @return                 校验通过返回 true，否则返回 false
   */
  boolean checkSignature(String timestamp, String nonce, String signature);

  /**
   * 获取 access_token，不强制刷新。
   *
   * @return                 access_token 字符串
   * @throws WxErrorException 调用微信接口失败时抛出
   * @see #getAccessToken(boolean)
   */
  String getAccessToken() throws WxErrorException;

  /**
   * 获取 access_token，本方法线程安全。多线程同时刷新时只刷新一次，避免超出调用次数上限。
   * 一般无需主动调用，所有接口会自动处理 token 过期。
   * 详情见：http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183&token=&lang=zh_CN
   *
   * @param forceRefresh     是否强制刷新
   * @return                 access_token 字符串
   * @throws WxErrorException 调用微信接口失败时抛出
   */
  String getAccessToken(boolean forceRefresh) throws WxErrorException;

  /**
   * 用户支付完成后，获取该用户的 UnionId，无需用户授权。本接口支持第三方平台代理查询。
   * 注意：调用前需要用户完成支付，且在支付后的五分钟内有效。
   * 请求地址： GET https://api.weixin.qq.com/wxa/getpaidunionid?access_token=ACCESS_TOKEN&openid=OPENID
   * 文档：https://developers.weixin.qq.com/miniprogram/dev/api/getPaidUnionId.html
   *
   * @param openid           支付用户唯一标识（必填）
   * @param transactionId    微信支付订单号（可选）
   * @param mchId            微信支付分配的商户号，与商户订单号配合使用（可选）
   * @param outTradeNo       微信支付商户订单号，与商户号配合使用（可选）
   * @return                 用户的 UnionId
   * @throws WxErrorException 调用微信接口失败时抛出
   */
  String getPaidUnionId(String openid, String transactionId, String mchId, String outTradeNo)
      throws WxErrorException;

  /**
   * 执行自定义的微信API请求。
   * <br>
   * Service没有实现某个API的时候，可以用这个方法，比{@link #get}和{@link #post}方法更灵活，可以自己构造RequestExecutor用来处理不同的参数和不同的返回类型。
   * 可以参考 {@link MediaUploadRequestExecutor} 的实现方法。
   *
   * @param <T>              返回的数据类型
   * @param <E>              请求参数的数据类型
   * @param executor         执行器对象
   * @param uri              接口请求地址
   * @param data             请求参数或数据
   * @return                 微信接口返回的数据对象
   * @throws WxErrorException 微信接口调用异常
   */
  <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException;

  /**
   * 执行带有签名的微信API请求。
   *
   * @param executor         签名请求执行器
   * @param uri              接口请求地址
   * @param headers          请求头信息
   * @param data             请求数据
   * @return                 微信接口响应对象
   * @throws WxErrorException 微信接口调用异常
   */
  WxMaApiResponse execute(
      ApiSignaturePostRequestExecutor<?, ?> executor,
      String uri,
      Map<String, String> headers,
      String data)
      throws WxErrorException;

  /**
   * 设置微信系统繁忙时的重试等待时间（毫秒）。
   *
   * @param retrySleepMillis 重试等待的毫秒数，默认1000ms
   */
  void setRetrySleepMillis(int retrySleepMillis);

  /**
   * 设置微信系统繁忙时的最大重试次数。
   *
   * @param maxRetryTimes    最大重试次数，默认5次
   */
  void setMaxRetryTimes(int maxRetryTimes);

  /**
   * 获取当前小程序的配置信息对象。
   *
   * @return                 当前小程序的WxMaConfig配置对象
   */
  WxMaConfig getWxMaConfig();

  /**
   * 注入小程序配置信息。
   *
   * @param maConfig         小程序配置信息对象
   */
  void setWxMaConfig(WxMaConfig maConfig);

  /**
   * 动态添加新的小程序配置信息。
   *
   * @param miniappId        小程序唯一标识
   * @param configStorage    新的小程序配置信息
   */
  void addConfig(String miniappId, WxMaConfig configStorage);

  /**
   * 动态移除指定小程序的配置信息。
   *
   * @param miniappId        小程序唯一标识
   */
  void removeConfig(String miniappId);

  /**
   * 批量注入多个小程序配置信息。
   *
   * @param configs          小程序配置Map，key为小程序标识
   */
  void setMultiConfigs(Map<String, WxMaConfig> configs);

  /**
   * 批量注入多个小程序配置信息，并指定默认小程序。
   *
   * @param configs          小程序配置Map，key为小程序标识
   * @param defaultMiniappId 默认小程序标识
   */
  void setMultiConfigs(Map<String, WxMaConfig> configs, String defaultMiniappId);

  /**
   * 切换到指定公众号。
   *
   * @param mpId             公众号标识
   * @return                 切换是否成功，true为成功，false为失败
   */
  boolean switchover(String mpId);

  /**
   * 切换到指定小程序。
   *
   * @param miniAppId        小程序标识
   * @return                 切换成功则返回当前对象，方便链式调用，否则抛出异常
   */
  WxMaService switchoverTo(String miniAppId);

  /**
   * 切换到指定小程序，并在配置不存在时通过函数获取配置。
   *
   * @param miniAppId        小程序标识
   * @param func             获取配置的函数
   * @return                 切换成功则返回当前对象，方便链式调用，否则抛出异常
   */
  WxMaService switchoverTo(String miniAppId, Function<String, WxMaConfig> func);

  /**
   * 获取消息（客服消息和模板消息）发送服务对象。
   *
   * @return                 消息服务对象WxMaMsgService
   */
  WxMaMsgService getMsgService();

  /**
   * 获取素材相关服务对象。
   *
   * @return                 素材服务对象WxMaMediaService
   */
  WxMaMediaService getMediaService();

  /**
   * 获取用户相关服务对象。
   *
   * @return                 用户服务对象WxMaUserService
   */
  WxMaUserService getUserService();

  /**
   * 获取二维码相关服务对象。
   *
   * @return                 二维码服务对象WxMaQrcodeService
   */
  WxMaQrcodeService getQrcodeService();

  /**
   * 获取小程序scheme码服务对象。
   *
   * @return                 scheme码服务对象WxMaSchemeService
   */
  WxMaSchemeService getWxMaSchemeService();

  /**
   * 获取订阅消息配置相关服务对象。
   *
   * @return                 订阅消息服务对象WxMaSubscribeService
   */
  WxMaSubscribeService getSubscribeService();

  /**
   * 获取数据分析相关服务对象。
   *
   * @return                 数据分析服务对象WxMaAnalysisService
   */
  WxMaAnalysisService getAnalysisService();

  /**
   * 获取代码操作相关服务对象。
   *
   * @return                 代码服务对象WxMaCodeService
   */
  WxMaCodeService getCodeService();

  /**
   * 获取小程序 - 微信客服。
   *
   * @return                 微信客服服务对象WxMaCustomserviceWorkService
   */
  WxMaCustomserviceWorkService getCustomserviceWorkService();

  /**
   * 获取小程序客服管理服务。
   *
   * @return                 客服管理服务对象WxMaKefuService
   */
  WxMaKefuService getKefuService();

  /**
   * 获取jsapi操作相关服务对象。
   *
   * @return                 jsapi服务对象WxMaJsapiService
   */
  WxMaJsapiService getJsapiService();

  /**
   * 获取小程序服务器地址、成员管理相关服务对象。
   *
   * @return                 设置服务对象WxMaSettingService
   */
  WxMaSettingService getSettingService();

  /**
   * 获取分享相关服务对象。
   *
   * @return                 分享服务对象WxMaShareService
   */
  WxMaShareService getShareService();

  /**
   * 获取微信运动相关服务对象。
   *
   * @return                 微信运动服务对象WxMaRunService
   */
  WxMaRunService getRunService();

  /**
   * 获取小程序安全相关服务对象。
   *
   * @return                 安全服务对象WxMaSecurityService
   */
  WxMaSecurityService getSecurityService();

  /**
   * 获取插件相关服务对象。
   *
   * @return                 插件服务对象WxMaPluginService
   */
  WxMaPluginService getPluginService();

  /**
   * 初始化http请求对象。
   */
  void initHttp();

  /**
   * 获取http请求相关信息。
   *
   * @return                 http请求对象RequestHttp
   */
  RequestHttp<?, ?> getRequestHttp();

  /**
   * 获取物流助手接口服务对象。
   *
   * @return                 物流助手服务对象WxMaExpressService
   */
  WxMaExpressService getExpressService();

  /**
   * 获取云开发接口服务对象。
   *
   * @return                 云开发服务对象WxMaCloudService
   */
  WxMaCloudService getCloudService();

  /**
   * 获取服务端网络接口服务对象。
   *
   * @return                 网络服务对象WxMaInternetService
   */
  WxMaInternetService getInternetService();

  /**
   * 获取直播接口服务对象。
   *
   * @return                 直播服务对象WxMaLiveService
   */
  WxMaLiveService getLiveService();

  /**
   * 获取直播间商品服务对象。
   *
   * @return                 直播商品服务对象WxMaLiveGoodsService
   */
  WxMaLiveGoodsService getLiveGoodsService();

  /**
   * 获取直播成员管理接口服务对象。
   *
   * @return                 直播成员服务对象WxMaLiveMemberService
   */
  WxMaLiveMemberService getLiveMemberService();

  /**
   * 获取OCR实现接口服务对象。
   *
   * @return                 OCR服务对象WxOcrService
   */
  WxOcrService getOcrService();

  /**
   * 获取图像处理接口服务对象。
   *
   * @return                 图像处理服务对象WxImgProcService
   */
  WxImgProcService getImgProcService();

  /**
   * 获取小程序交易组件-售后服务接口服务对象。
   *
   * @return                 售后服务对象WxMaShopAfterSaleService
   */
  WxMaShopAfterSaleService getShopAfterSaleService();

  /**
   * 获取小程序交易组件-物流服务接口服务对象。
   *
   * @return                 物流服务对象WxMaShopDeliveryService
   */
  WxMaShopDeliveryService getShopDeliveryService();

  /**
   * 获取小程序交易组件-订单服务接口服务对象。
   *
   * @return                 订单服务对象WxMaShopOrderService
   */
  WxMaShopOrderService getShopOrderService();

  /**
   * 获取小程序交易组件-spu商品服务接口服务对象。
   *
   * @return                 spu商品服务对象WxMaShopSpuService
   */
  WxMaShopSpuService getShopSpuService();

  /**
   * 获取小程序交易组件-接入申请接口服务对象。
   *
   * @return                 接入申请服务对象WxMaShopRegisterService
   */
  WxMaShopRegisterService getShopRegisterService();

  /**
   * 获取小程序交易组件-商户入驻接口服务对象。
   *
   * @return                 商户入驻服务对象WxMaShopAccountService
   */
  WxMaShopAccountService getShopAccountService();

  /**
   * 获取小程序交易组件-类目相关接口服务对象。
   *
   * @return                 类目服务对象WxMaShopCatService
   */
  WxMaShopCatService getShopCatService();

  /**
   * 获取小程序交易组件-上传图片接口服务对象。
   *
   * @return                 图片服务对象WxMaShopImgService
   */
  WxMaShopImgService getShopImgService();

  /**
   * 获取小程序交易组件-审核相关接口服务对象。
   *
   * @return                 审核服务对象WxMaShopAuditService
   */
  WxMaShopAuditService getShopAuditService();

  /**
   * 获取小程序Link服务接口服务对象。
   *
   * @return                 Link服务对象WxMaLinkService
   */
  WxMaLinkService getLinkService();

  /**
   * 获取电子发票报销方服务接口服务对象。
   *
   * @return                 电子发票报销方服务对象WxMaReimburseInvoiceService
   */
  WxMaReimburseInvoiceService getReimburseInvoiceService();

  /**
   * 获取设备订阅消息相关接口服务对象。
   *
   * @return                 设备订阅消息服务对象WxMaDeviceSubscribeService
   */
  WxMaDeviceSubscribeService getDeviceSubscribeService();

  /**
   * 获取小程序广告接入相关接口服务对象。
   *
   * @return                 广告服务对象WxMaMarketingService
   */
  WxMaMarketingService getMarketingService();

  /**
   * 获取微信小程序即时配送服务接口服务对象。
   *
   * @return                 即时配送服务对象WxMaImmediateDeliveryService
   */
  WxMaImmediateDeliveryService getWxMaImmediateDeliveryService();

  /**
   * 获取小程序分享人接口服务对象。
   *
   * @return                 分享人服务对象WxMaShopSharerService
   */
  WxMaShopSharerService getShopSharerService();

  /**
   * 获取标准交易组件接口服务对象。
   *
   * @return                 标准交易组件服务对象WxMaProductService
   */
  WxMaProductService getProductService();

  /**
   * 获取小商店-标准交易组件-订单服务对象。
   *
   * @return                 订单服务对象WxMaProductOrderService
   */
  WxMaProductOrderService getProductOrderService();

  /**
   * 获取小商店-标准交易组件-优惠券服务对象。
   *
   * @return                 优惠券服务对象WxMaShopCouponService
   */
  WxMaShopCouponService getWxMaShopCouponService();

  /**
   * 获取小程序支付管理-订单支付服务对象。
   *
   * @return                 订单支付服务对象WxMaShopPayService
   */
  WxMaShopPayService getWxMaShopPayService();

  /**
   * 获取小程序发货信息管理服务对象。
   *
   * @return                 发货信息管理服务对象WxMaOrderShippingService
   */
  WxMaOrderShippingService getWxMaOrderShippingService();

  /**
   * 获取小程序订单管理服务对象。
   *
   * @return                 订单管理服务对象WxMaOrderManagementService
   */
  WxMaOrderManagementService getWxMaOrderManagementService();

  /**
   * 获取小程序openApi管理服务对象。
   *
   * @return                 openApi管理服务对象WxMaOpenApiService
   */
  WxMaOpenApiService getWxMaOpenApiService();

  /**
   * 获取小程序短剧管理服务对象。
   *
   * @return                 短剧管理服务对象WxMaVodService
   */
  WxMaVodService getWxMaVodService();

  /**
   * 获取小程序虚拟支付服务对象。
   *
   * @return                 虚拟支付服务对象WxMaXPayService
   */
  WxMaXPayService getWxMaXPayService();

  /**
   * 获取小程序物流退货服务对象。
   *
   * @return                 物流退货服务对象WxMaExpressDeliveryReturnService
   */
  WxMaExpressDeliveryReturnService getWxMaExpressDeliveryReturnService();

  /**
   * 获取小程序推广员服务对象。
   *
   * @return                 推广员服务对象WxMaPromotionService
   */
  WxMaPromotionService getWxMaPromotionService();

  /**
   * 以签名方式POST数据到指定URL。
   *
   * @param url              请求地址
   * @param obj              请求对象
   * @return                 微信接口响应字符串
   * @throws WxErrorException 微信接口调用异常
   */
  String postWithSignature(String url, Object obj) throws WxErrorException;

  /**
   * 以签名方式POST数据到指定URL。
   *
   * @param url              请求地址
   * @param jsonObject       请求的Json对象
   * @return                 微信接口响应字符串
   * @throws WxErrorException 微信接口调用异常
   */
  String postWithSignature(String url, JsonObject jsonObject) throws WxErrorException;

  /**
   * 获取微信物流服务--同城配送服务对象。
   * <br>
   * 文档：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/industry/express/business/intracity_service.html
   *
   * @return                 同城配送服务对象WxMaIntracityService
   */
  WxMaIntracityService getIntracityService();

  /**
   * 获取交易投诉服务对象。
   * <br>
   * 文档：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/guarantee/complaint.html
   *
   * @return                 交易投诉服务对象WxMaComplaintService
   */
  WxMaComplaintService getComplaintService();
}
