package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaImmediateDeliveryService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import cn.binarywang.wx.miniapp.bean.delivery.*;
import cn.binarywang.wx.miniapp.bean.delivery.base.WxMaDeliveryBaseResponse;
import cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.InstantDelivery;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import static me.chanjar.weixin.common.api.WxConsts.ERR_CODE;

/**
 * 微信小程序即时配送服务.
 * <pre>
 *     文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/industry/immediate-delivery/overview.html
 * </pre>
 *
 * @author Luo
 * @version 1.0
 * created on  2021-10-13 16:40
 */
@RequiredArgsConstructor
public class WxMaImmediateDeliveryServiceImpl implements WxMaImmediateDeliveryService {

  /**
   * 顺丰同城响应码.
   */
  public static final String SF_ERR_CODE = "resultcode";

  /**
   * 顺丰同城响应说明.
   */
  public static final String SF_ERR_MSG = "resultmsg";

  /**
   * 成功响应状态码.
   */
  public static final int SUCCESS_CODE = 0;

  private final WxMaService wxMaService;

  /**
   * 拉取已绑定账号.
   * <pre>
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.getBindAccount.html
   * </pre>
   *
   * @return 响应
   * @throws WxErrorException 异常
   */
  @Override
  public BindAccountResponse getBindAccount() throws WxErrorException {
    return this.parse(this.wxMaService.post(InstantDelivery.GET_BIND_ACCOUNT, "{}"),
      BindAccountResponse.class);
  }

  /**
   * 下配送单接口.
   * <pre>
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.addOrder.html
   * </pre>
   *
   * @param request request
   * @return 响应
   * @throws WxErrorException 异常
   */
  @Override
  public AddOrderResponse addOrder(final AddOrderRequest request) throws WxErrorException {
    request.getDeliverySign();
    return this.parse(this.wxMaService.post(InstantDelivery.PlaceAnOrder.ADD_ORDER, request),
      AddOrderResponse.class);
  }

  /**
   * 拉取配送单信息.
   * <pre>
   * 商家可使用本接口查询某一配送单的配送状态，便于商家掌握配送情况。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.getOrder.html
   * </pre>
   *
   * @param request request
   * @return 响应
   * @throws WxErrorException 异常
   */
  @Override
  public GetOrderResponse getOrder(final GetOrderRequest request) throws WxErrorException {
    request.getDeliverySign();
    return this.parse(this.wxMaService.post(InstantDelivery.GET_ORDER, request),
      GetOrderResponse.class);
  }

  /**
   * 取消配送单接口.
   * <pre>
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.cancelOrder.html
   * </pre>
   *
   * @param request request
   * @return 响应
   * @throws WxErrorException 异常
   */
  @Override
  public CancelOrderResponse cancelOrder(final CancelOrderRequest request) throws WxErrorException {
    request.getDeliverySign();
    return this.parse(this.wxMaService.post(InstantDelivery.Cancel.CANCEL_ORDER, request),
      CancelOrderResponse.class);
  }

  /**
   * 异常件退回商家商家确认收货接口.
   * <pre>
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.abnormalConfirm.html
   * </pre>
   *
   * @param request request
   * @return 响应
   * @throws WxErrorException 异常
   */
  @Override
  public AbnormalConfirmResponse abnormalConfirm(final AbnormalConfirmRequest request) throws WxErrorException {
    request.getDeliverySign();
    return this.parse(this.wxMaService.post(InstantDelivery.Cancel.ABNORMAL_CONFIRM, request),
      AbnormalConfirmResponse.class);
  }

  /**
   * 模拟配送公司更新配送单状态, 该接口只用于沙盒环境，即订单并没有真实流转到运力方.
   * <pre>
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.mockUpdateOrder.html
   * </pre>
   *
   * @param request request
   * @return 响应
   * @throws WxErrorException 异常
   */
  @Override
  public MockUpdateOrderResponse mockUpdateOrder(final MockUpdateOrderRequest request) throws WxErrorException {
    return this.parse(this.wxMaService.post(InstantDelivery.MOCK_UPDATE_ORDER, request),
      MockUpdateOrderResponse.class);
  }

  @Override
  public TraceWaybillResponse traceWaybill(
    TraceWaybillRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(InstantDelivery.TRACE_WAYBILL_URL, request);
    TraceWaybillResponse response = TraceWaybillResponse.fromJson(responseContent);
    if (response.getErrcode() == -1) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return response;

  }

  @Override
  public QueryWaybillTraceResponse queryWaybillTrace(
    QueryWaybillTraceRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(InstantDelivery.QUERY_WAYBILL_TRACE_URL, request);
    QueryWaybillTraceResponse response = QueryWaybillTraceResponse.fromJson(responseContent);
    if (response.getErrcode() == -1) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return response;
  }

  @Override
  public FollowWaybillResponse followWaybill(
    FollowWaybillRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(InstantDelivery.FOLLOW_WAYBILL_URL, request);
    FollowWaybillResponse response = FollowWaybillResponse.fromJson(responseContent);
    if (response.getErrcode() == -1) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return response;
  }

  @Override
  public QueryFollowTraceResponse queryFollowTrace(
    QueryFollowTraceRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(InstantDelivery.QUERY_FOLLOW_TRACE_URL, request);
    QueryFollowTraceResponse response = QueryFollowTraceResponse.fromJson(responseContent);
    if (response.getErrcode() == -1) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return response;
  }

  @Override
  public GetDeliveryListResponse getDeliveryList() throws WxErrorException {
    String responseContent = this.wxMaService.post(InstantDelivery.GET_DELIVERY_LIST_URL,"{}");
    GetDeliveryListResponse response = GetDeliveryListResponse.fromJson(responseContent);
    if (response.getErrcode() == -1) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return response;
  }

  @Override
  public WxMaBaseResponse updateWaybillGoods(UpdateWaybillGoodsRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(InstantDelivery.UPDATE_WAYBILL_GOODS_URL,request);
    WxMaBaseResponse response = WxMaGsonBuilder.create().fromJson(responseContent, WxMaBaseResponse.class);
    if (response.getErrcode() == -1) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return response;
  }

  /**
   * 解析响应.
   *
   * @param responseContent 响应内容
   * @param valueType       类型
   * @param <T>             类型
   * @return 结果
   * @throws WxErrorException 异常
   */
  private <T extends WxMaDeliveryBaseResponse> T parse(final String responseContent, final Class<T> valueType) throws WxErrorException {
    if (StringUtils.isBlank(responseContent)) {
      throw new RuntimeException("the responseContent cannot be empty");
    }
    // 解析成Json对象
    JsonObject jsonObject = GsonParser.parse(responseContent);
    // 是否为微信错误响应 当 errcode==0 或者 不存在 还需要看 运力方 resultcode 状态码
    JsonElement element = jsonObject.get(ERR_CODE);
    // 正常响应下不会有该字段返回
    if (!ObjectUtils.isEmpty(element) && SUCCESS_CODE != element.getAsInt()) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    // 是否为运力方错误响应
    JsonElement delivery = jsonObject.get(SF_ERR_CODE);
    if (!ObjectUtils.isEmpty(delivery) && SUCCESS_CODE != delivery.getAsInt()) {
      throw new WxErrorException(jsonObject.get(SF_ERR_MSG).getAsString());
    }
    // 解析成对应响应对象
    return WxMaDeliveryBaseResponse.fromJson(responseContent, valueType);
  }

}
