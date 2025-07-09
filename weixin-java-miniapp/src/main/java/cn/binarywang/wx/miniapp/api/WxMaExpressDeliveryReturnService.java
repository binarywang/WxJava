package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.express.request.WxMaExpressDeliveryReturnAddRequest;
import cn.binarywang.wx.miniapp.bean.express.result.WxMaExpressReturnInfoResult;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小程序物流退货组件接口。
 * 用于处理退货单相关操作，包括新增、查询和取消退货单。
 * 文档：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/express.html
 *
 */
public interface WxMaExpressDeliveryReturnService {

    /** 新增退货单接口地址 */
    String ADD_DELIVERY_RETURN_URL = "https://api.weixin.qq.com/cgi-bin/express/delivery/return/add";
    /** 获取退货单接口地址 */
    String GET_DELIVERY_RETURN_URL = "https://api.weixin.qq.com/cgi-bin/express/delivery/return/get";
    /** 取消退货单接口地址 */
    String UNBIND_DELIVERY_RETURN_URL = "https://api.weixin.qq.com/cgi-bin/express/delivery/return/unbind";

    /**
     * 新增退货单。
     * 用于创建新的退货单，返回退货单信息。
     *
     * @param wxMaExpressDeliveryReturnAddRequest 退货单新增请求对象
     * @return                                    退货单信息结果
     * @throws WxErrorException                   新增失败时抛出
     */
    WxMaExpressReturnInfoResult addDeliveryReturn(WxMaExpressDeliveryReturnAddRequest wxMaExpressDeliveryReturnAddRequest) throws WxErrorException;

    /**
     * 获取退货单信息。
     * 根据退货单ID查询退货单的详细信息。
     *
     * @param returnId          退货单ID
     * @return                  退货单信息结果
     * @throws WxErrorException 获取失败时抛出
     */
    WxMaExpressReturnInfoResult getDeliveryReturn(String returnId) throws WxErrorException;

    /**
     * 取消退货单。
     * 取消指定的退货单，取消后的退货单将无法继续使用。
     *
     * @param returnId          退货单ID
     * @return                  操作结果
     * @throws WxErrorException 取消失败时抛出
     */
    WxMaExpressReturnInfoResult unbindDeliveryReturn(String returnId) throws WxErrorException;
}
