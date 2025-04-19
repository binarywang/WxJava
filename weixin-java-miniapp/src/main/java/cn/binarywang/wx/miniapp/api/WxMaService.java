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
 * The interface Wx ma service.
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
   * 获取登录后的session信息.
   *
   * @param jsCode 登录时获取的 code
   * @return the wx ma jscode 2 session result
   * @throws WxErrorException the wx error exception
   */
  WxMaJscode2SessionResult jsCode2SessionInfo(String jsCode) throws WxErrorException;

  /**
   * 导入抽样数据
   *
   * <pre>
   * 第三方通过调用微信API，将数据写入到setdynamicdata这个API。每个Post数据包不超过5K，若数据过多可开多进（线）程并发导入数据（例如：数据量为十万量级可以开50个线程并行导数据）。
   * 文档地址：https://wsad.weixin.qq.com/wsad/zh_CN/htmledition/widget-docs-v3/html/custom/quickstart/implement/import/index.html
   * http请求方式：POST http(s)://api.weixin.qq.com/wxa/setdynamicdata?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param lifespan 数据有效时间，秒为单位，一般为86400，一天一次导入的频率
   * @param type 用于标识数据所属的服务类目
   * @param scene 1代表用于搜索的数据
   * @param data 推送到微信后台的数据列表，该数据被微信用于流量分配，注意该字段为string类型而不是object
   * @throws WxErrorException .
   */
  void setDynamicData(int lifespan, String type, int scene, String data) throws WxErrorException;

  /**
   *
   *
   * <pre>
   * 验证消息的确来自微信服务器.
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421135319&token=&lang=zh_CN
   * </pre>
   *
   * @param timestamp the timestamp
   * @param nonce the nonce
   * @param signature the signature
   * @return the boolean
   */
  boolean checkSignature(String timestamp, String nonce, String signature);

  /**
   * 获取access_token, 不强制刷新access_token.
   *
   * @return the access token
   * @throws WxErrorException the wx error exception
   * @see #getAccessToken(boolean) #getAccessToken(boolean)
   */
  String getAccessToken() throws WxErrorException;

  /**
   *
   *
   * <pre>
   * 获取access_token，本方法线程安全.
   * 且在多线程同时刷新时只刷新一次，避免超出2000次/日的调用次数上限
   *
   * 另：本service的所有方法都会在access_token过期是调用此方法
   *
   * 程序员在非必要情况下尽量不要主动调用此方法
   *
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183&token=&lang=zh_CN
   * </pre>
   *
   * @param forceRefresh 强制刷新
   * @return the access token
   * @throws WxErrorException the wx error exception
   */
  String getAccessToken(boolean forceRefresh) throws WxErrorException;

  /**
   *
   *
   * <pre>
   * 用户支付完成后，获取该用户的 UnionId，无需用户授权。本接口支持第三方平台代理查询。
   *
   * 注意：调用前需要用户完成支付，且在支付后的五分钟内有效。
   * 请求地址： GET https://api.weixin.qq.com/wxa/getpaidunionid?access_token=ACCESS_TOKEN&openid=OPENID
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api/getPaidUnionId.html
   * </pre>
   *
   * @param openid 必填 支付用户唯一标识
   * @param transactionId 非必填 微信支付订单号
   * @param mchId 非必填 微信支付分配的商户号，和商户订单号配合使用
   * @param outTradeNo 非必填 微信支付商户订单号，和商户号配合使用
   * @return UnionId. paid union id
   * @throws WxErrorException .
   */
  String getPaidUnionId(String openid, String transactionId, String mchId, String outTradeNo)
      throws WxErrorException;

  /**
   *
   *
   * <pre>
   * Service没有实现某个API的时候，可以用这个，
   * 比{@link #get}和{@link #post}方法更灵活，可以自己构造RequestExecutor用来处理不同的参数和不同的返回类型。
   * 可以参考，{@link MediaUploadRequestExecutor}的实现方法
   * </pre>
   *
   * @param <T> .
   * @param <E> .
   * @param executor 执行器
   * @param uri 接口请求地址
   * @param data 参数或请求数据
   * @return . t
   * @throws WxErrorException the wx error exception
   */
  <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException;

  WxMaApiResponse execute(
      ApiSignaturePostRequestExecutor executor,
      String uri,
      Map<String, String> headers,
      String data)
      throws WxErrorException;

  /**
   *
   *
   * <pre>
   * 设置当微信系统响应系统繁忙时，要等待多少 retrySleepMillis(ms) * 2^(重试次数 - 1) 再发起重试.
   * 默认：1000ms
   * </pre>
   *
   * @param retrySleepMillis 重试等待毫秒数
   */
  void setRetrySleepMillis(int retrySleepMillis);

  /**
   *
   *
   * <pre>
   * 设置当微信系统响应系统繁忙时，最大重试次数.
   * 默认：5次
   * </pre>
   *
   * @param maxRetryTimes 最大重试次数
   */
  void setMaxRetryTimes(int maxRetryTimes);

  /**
   * 获取WxMaConfig 对象.
   *
   * @return WxMaConfig wx ma config
   */
  WxMaConfig getWxMaConfig();

  /**
   * 注入 {@link WxMaConfig} 的实现.
   *
   * @param maConfig config
   */
  void setWxMaConfig(WxMaConfig maConfig);

  /**
   * Map里 加入新的 {@link WxMaConfig}，适用于动态添加新的微信公众号配置.
   *
   * @param miniappId 小程序标识
   * @param configStorage 新的微信配置
   */
  void addConfig(String miniappId, WxMaConfig configStorage);

  /**
   * 从 Map中 移除 {@link String miniappId} 所对应的 {@link WxMaConfig}，适用于动态移除小程序配置.
   *
   * @param miniappId 对应小程序的标识
   */
  void removeConfig(String miniappId);

  /**
   * 注入多个 {@link WxMaConfig} 的实现. 并为每个 {@link WxMaConfig} 赋予不同的 {@link String mpId} 值 随机采用一个{@link
   * String mpId}进行Http初始化操作
   *
   * @param configs WxMaConfig map
   */
  void setMultiConfigs(Map<String, WxMaConfig> configs);

  /**
   * 注入多个 {@link WxMaConfig} 的实现. 并为每个 {@link WxMaConfig} 赋予不同的 {@link String label} 值
   *
   * @param configs WxMaConfig map
   * @param defaultMiniappId 设置一个{@link WxMaConfig} 所对应的{@link String defaultMiniappId}进行Http初始化
   */
  void setMultiConfigs(Map<String, WxMaConfig> configs, String defaultMiniappId);

  /**
   * 进行相应的公众号切换.
   *
   * @param mpId 公众号标识
   * @return 切换是否成功 boolean
   */
  boolean switchover(String mpId);

  /**
   * 进行相应的小程序切换.
   *
   * @param miniAppId 小程序标识
   * @return 切换成功 ，则返回当前对象，方便链式调用，否则抛出异常
   */
  WxMaService switchoverTo(String miniAppId);

  /**
   * 进行相应的小程序切换.
   *
   * @param miniAppId 小程序标识
   * @param func 当对应的小程序配置不存在时，允许通过函数的方式进行调用获取
   * @return 切换成功 ，则返回当前对象，方便链式调用，否则抛出异常
   */
  WxMaService switchoverTo(String miniAppId, Function<String, WxMaConfig> func);

  /**
   * 返回消息（客服消息和模版消息）发送接口方法实现类，以方便调用其各个接口.
   *
   * @return WxMaMsgService msg service
   */
  WxMaMsgService getMsgService();

  /**
   * 返回素材相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMaMediaService media service
   */
  WxMaMediaService getMediaService();

  /**
   * 返回用户相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMaUserService user service
   */
  WxMaUserService getUserService();

  /**
   * 返回二维码相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMaQrcodeService qrcode service
   */
  WxMaQrcodeService getQrcodeService();

  /**
   * 返回获取小程序scheme码实现对象，以方便调用其各个接口.
   *
   * @return WxMaSchemeService wx ma scheme service
   */
  WxMaSchemeService getWxMaSchemeService();

  /**
   * 返回订阅消息配置相关接口方法的实现类对象, 以方便调用其各个接口.
   *
   * @return WxMaSubscribeService subscribe service
   */
  WxMaSubscribeService getSubscribeService();

  /**
   * 数据分析相关查询服务.
   *
   * @return WxMaAnalysisService analysis service
   */
  WxMaAnalysisService getAnalysisService();

  /**
   * 返回代码操作相关的 API.
   *
   * @return WxMaCodeService code service
   */
  WxMaCodeService getCodeService();

  /**
   * 返回jsapi操作相关的 API服务类对象.
   *
   * @return WxMaJsapiService jsapi service
   */
  WxMaJsapiService getJsapiService();

  /**
   * 小程序修改服务器地址、成员管理 API.
   *
   * @return WxMaSettingService setting service
   */
  WxMaSettingService getSettingService();

  /**
   * 返回分享相关查询服务.
   *
   * @return WxMaShareService share service
   */
  WxMaShareService getShareService();

  /**
   * 返回微信运动相关接口服务对象.
   *
   * @return WxMaShareService run service
   */
  WxMaRunService getRunService();

  /**
   * 返回小程序安全相关接口服务对象.
   *
   * @return WxMaShareService sec check service
   */
  WxMaSecurityService getSecurityService();

  /**
   * 返回插件相关接口服务对象.
   *
   * @return WxMaPluginService plugin service
   */
  WxMaPluginService getPluginService();

  /** 初始化http请求对象. */
  void initHttp();

  /**
   * 请求http请求相关信息.
   *
   * @return . request http
   */
  RequestHttp getRequestHttp();

  /**
   * 获取物流助手接口服务对象
   *
   * @return . express service
   */
  WxMaExpressService getExpressService();

  /**
   * 获取云开发接口服务对象
   *
   * @return . cloud service
   */
  WxMaCloudService getCloudService();

  /**
   * 获取服务端网络接口服务对象
   *
   * @return 。internet service
   */
  WxMaInternetService getInternetService();

  /**
   * 获取直播接口服务对象
   *
   * @return . live service
   */
  WxMaLiveService getLiveService();

  /**
   * 获取直播间商品服务对象
   *
   * @return . live goods service
   */
  WxMaLiveGoodsService getLiveGoodsService();

  /**
   * 获取直播成员管理接口服务对象
   *
   * @return . live service
   */
  WxMaLiveMemberService getLiveMemberService();

  /**
   * 获取ocr实现接口服务对象
   *
   * @return 。
   */
  WxOcrService getOcrService();

  /**
   * 返回图像处理接口的实现类对象，以方便调用其各个接口.
   *
   * @return WxImgProcService img proc service
   */
  WxImgProcService getImgProcService();

  /**
   * 返回小程序交易组件-售后服务接口
   *
   * @return
   */
  WxMaShopAfterSaleService getShopAfterSaleService();

  /**
   * 返回小程序交易组件-物流服务接口
   *
   * @return
   */
  WxMaShopDeliveryService getShopDeliveryService();

  /**
   * 返回小程序交易组件-订单服务接口
   *
   * @return
   */
  WxMaShopOrderService getShopOrderService();

  /**
   * 返回小程序交易组件-spu商品服务接口
   *
   * @return
   */
  WxMaShopSpuService getShopSpuService();

  /**
   * 返回小程序交易组件-接入申请接口
   *
   * @return
   */
  WxMaShopRegisterService getShopRegisterService();

  /**
   * 返回小程序交易组件-商户入驻接口
   *
   * @return
   */
  WxMaShopAccountService getShopAccountService();

  /**
   * 小程序交易组件-接入商品前必需接口-类目相关
   *
   * @return
   */
  WxMaShopCatService getShopCatService();

  /**
   * 小程序交易组件-接入商品前必需接口-上传图片
   *
   * @return
   */
  WxMaShopImgService getShopImgService();

  /**
   * 小程序交易组件-接入商品前必需接口-审核相关接口
   *
   * @return
   */
  WxMaShopAuditService getShopAuditService();

  /**
   * 获取小程序Link服务接口
   *
   * @return
   */
  WxMaLinkService getLinkService();

  /**
   * 获取电子发票报销方服务接口
   *
   * @return
   */
  WxMaReimburseInvoiceService getReimburseInvoiceService();

  /**
   * 返回设备订阅消息相关接口服务对象
   *
   * @return WxMaDeviceSubscribeService plugin service
   */
  WxMaDeviceSubscribeService getDeviceSubscribeService();

  /**
   * 返回小程序广告接入相关接口服务对象
   *
   * @return WxMaDeviceSubscribeService plugin service
   */
  WxMaMarketingService getMarketingService();

  /**
   * 返回微信小程序即时配送服务接口.
   *
   * @return WxMaImmediateDeliveryService
   */
  WxMaImmediateDeliveryService getWxMaImmediateDeliveryService();

  /**
   * 分享人接口
   *
   * @return WxMaShopSharerService
   */
  WxMaShopSharerService getShopSharerService();

  /**
   * 标准交易组件接口
   *
   * @return WxMaProductService
   */
  WxMaProductService getProductService();

  /**
   * 小商店-标准交易组件-订单服务
   *
   * @return getProductOrderService
   */
  WxMaProductOrderService getProductOrderService();

  /**
   * 小商店-标准交易组件-优惠券
   *
   * @return getWxMaShopCouponService
   */
  WxMaShopCouponService getWxMaShopCouponService();

  /**
   * 小程序支付管理-订单支付
   *
   * @return getWxMaShopPayService
   */
  WxMaShopPayService getWxMaShopPayService();

  /**
   * 小程序发货信息管理服务
   *
   * @return getWxMaOrderShippingService
   */
  WxMaOrderShippingService getWxMaOrderShippingService();
  /**
   * 小程序订单管理服务
   *
   * @return WxMaOrderManagementService
   */
  WxMaOrderManagementService getWxMaOrderManagementService();

  /**
   * 小程序openApi管理
   *
   * @return getWxMaOpenApiService
   */
  WxMaOpenApiService getWxMaOpenApiService();

  /**
   * 小程序短剧管理
   *
   * @return getWxMaVodService
   */
  WxMaVodService getWxMaVodService();

  /**
   * 小程序虚拟支付
   *
   * @return getWxMaXPayService
   */
  WxMaXPayService getWxMaXPayService();

  WxMaExpressDeliveryReturnService getWxMaExpressDeliveryReturnService();

  /**
   * 小程序推广员
   *
   * @return WxMaPromotionService
   */
  WxMaPromotionService getWxMaPromotionService();

  String postWithSignature(String url, Object obj) throws WxErrorException;

  String postWithSignature(String url, JsonObject jsonObject) throws WxErrorException;

  /**
   * 微信物流服务 -- 同城配送
   * https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/industry/express/business/intracity_service.html
   */
  WxMaIntracityService getIntracityService();
}
