package me.chanjar.weixin.channel.constant;

import lombok.experimental.UtilityClass;

/**
 * 视频号小店接口地址常量
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@UtilityClass
public class WxChannelApiUrlConstants {

  /**
   * 获取access_token.
   */
  public static final String GET_ACCESS_TOKEN_URL =
    "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

  /**
   * 获取Stable access_token.
   */
  public static final String GET_STABLE_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/stable_token";

  /** 基础接口 */
  public interface Basics {

    /** 获取店铺基本信息 */
    String GET_SHOP_INFO = "https://api.weixin.qq.com/channels/ec/basics/info/get";
    /** 上传图片 */
    String IMG_UPLOAD_URL = "https://api.weixin.qq.com/channels/ec/basics/img/upload";
    /** 上传资质图片 */
    String UPLOAD_QUALIFICATION_FILE = "https://api.weixin.qq.com/channels/ec/basics/qualification/upload";
    /** 下载图片 */
    String GET_IMG_URL = "https://api.weixin.qq.com/channels/ec/basics/media/get";
    /** 获取地址编码 */
    String GET_ADDRESS_CODE = "https://api.weixin.qq.com/channels/ec/basics/addresscode/get";
  }

  /** 商品类目相关接口 */
  public interface Category {

    /** 获取所有的类目 */
    String LIST_ALL_CATEGORY_URL = "https://api.weixin.qq.com/channels/ec/category/all";
    /** 获取类目详情 */
    String GET_CATEGORY_DETAIL_URL = "https://api.weixin.qq.com/channels/ec/category/detail";
    /** 获取可用的子类目详情 */
    String AVAILABLE_CATEGORY_URL = "https://api.weixin.qq.com/channels/ec/category/availablesoncategories/get";
    /** 上传类目资质 */
    String ADD_CATEGORY_URL = "https://api.weixin.qq.com/channels/ec/category/add";
    /** 获取类目审核结果 */
    String GET_CATEGORY_AUDIT_URL = "https://api.weixin.qq.com/channels/ec/category/audit/get";
    /** 取消类目提审 */
    String CANCEL_CATEGORY_AUDIT_URL = "https://api.weixin.qq.com/channels/ec/category/audit/cancel";
    /** 获取账号申请通过的类目和资质信息 */
    String LIST_PASS_CATEGORY_URL = "https://api.weixin.qq.com/channels/ec/category/list/get";
  }

  /** 主页管理相关接口 */
  public interface HomePage {

    /** 添加分类关联的商品 */
    String ADD_TREE_PRODUCT_URL = "https://api.weixin.qq.com/channels/ec/store/classification/tree/product/add";
    /** 删除分类关联的商品 */
    String DEL_TREE_PRODUCT_URL = "https://api.weixin.qq.com/channels/ec/store/classification/tree/product/del";
    /** 获取分类关联的商品ID列表 */
    String LIST_TREE_PRODUCT_URL = "https://api.weixin.qq.com/channels/ec/store/classification/tree/product/get";
    /** 设置展示在店铺主页的商品分类 */
    String SET_SHOW_TREE_URL = "https://api.weixin.qq.com/channels/ec/store/classification/tree/set";
    /** 获取在店铺主页展示的商品分类 */
    String GET_SHOW_TREE_URL = "https://api.weixin.qq.com/channels/ec/store/classification/tree/get";

    /** 获取主页展示商品列表 */
    String LIST_WINDOW_PRODUCT_URL = "https://api.weixin.qq.com/channels/ec/store/window/product/list/get";
    /** 重新排序主页展示商品 */
    String REORDER_WINDOW_PRODUCT_URL = "https://api.weixin.qq.com/channels/ec/store/window/product/reorder";
    /** 隐藏小店主页商品 */
    String HIDE_WINDOW_PRODUCT_URL = "https://api.weixin.qq.com/channels/ec/store/window/product/hide";
    /** 置顶小店主页商品 */
    String TOP_WINDOW_PRODUCT_URL = "https://api.weixin.qq.com/channels/ec/store/window/product/settop";

    /** 提交主页背景图申请 */
    String APPLY_BACKGROUND_URL = "https://api.weixin.qq.com/channels/ec/basics/homepage/background/apply/submit";
    /** 查询主页背景图 */
    String GET_BACKGROUND_URL = "https://api.weixin.qq.com/channels/ec/basics/homepage/background/get";
    /** 撤销主页背景图申请 */
    String CANCEL_BACKGROUND_URL = "https://api.weixin.qq.com/channels/ec/basics/homepage/background/apply/cancel";
    /** 清空主页背景图并撤销流程中的申请 */
    String REMOVE_BACKGROUND_URL = "https://api.weixin.qq.com/channels/ec/basics/homepage/background/remove";

    /** 提交精选展示位申请 */
    String APPLY_BANNER_URL = "https://api.weixin.qq.com/channels/ec/basics/homepage/banner/apply/submit";
    /** 查询精选展示位 */
    String GET_BANNER_URL = "https://api.weixin.qq.com/channels/ec/basics/homepage/banner/get";
    /** 撤销精选展示位申请 */
    String CANCEL_BANNER_URL = "https://api.weixin.qq.com/channels/ec/basics/homepage/banner/apply/cancel";
    /** 清空精选展示位并撤销流程中的申请 */
    String REMOVE_BANNER_URL = "https://api.weixin.qq.com/channels/ec/basics/homepage/banner/remove";
  }

  /** 品牌资质相关接口 */
  public interface Brand {

    /** 获取品牌库列表 */
    String ALL_BRAND_URL = "https://api.weixin.qq.com/channels/ec/brand/all";
    /** 新增品牌资质 */
    String ADD_BRAND_URL = "https://api.weixin.qq.com/channels/ec/brand/add";
    /** 更新品牌资质 */
    String UPDATE_BRAND_URL = "https://api.weixin.qq.com/channels/ec/brand/update";
    /** 撤回品牌资质审核 */
    String CANCEL_BRAND_AUDIT_URL = "https://api.weixin.qq.com/channels/ec/brand/audit/cancel";
    /** 删除品牌资质 */
    String DELETE_BRAND_URL = "https://api.weixin.qq.com/channels/ec/brand/delete";
    /** 获取品牌资质申请详情 */
    String GET_BRAND_URL = "https://api.weixin.qq.com/channels/ec/brand/get";
    /** 获取品牌资质申请列表 */
    String LIST_BRAND_URL = "https://api.weixin.qq.com/channels/ec/brand/list/get";
    /** 获取生效中的品牌资质列表 */
    String LIST_BRAND_VALID_URL = "https://api.weixin.qq.com/channels/ec/brand/valid/list/get";
  }

  /** 商品操作相关接口 */
  public interface Spu {

    /** 添加商品 */
    String SPU_ADD_URL = "https://api.weixin.qq.com/channels/ec/product/add";
    /** 删除商品 */
    String SPU_DEL_URL = "https://api.weixin.qq.com/channels/ec/product/delete";
    /** 获取商品详情 */
    String SPU_GET_URL = "https://api.weixin.qq.com/channels/ec/product/get";
    /** 获取商品列表 */
    String SPU_LIST_URL = "https://api.weixin.qq.com/channels/ec/product/list/get";
    /** 更新商品 */
    String SPU_UPDATE_URL = "https://api.weixin.qq.com/channels/ec/product/update";
    /** 更新商品 */
    String SPU_AUDIT_FREE_UPDATE_URL = "https://api.weixin.qq.com/channels/ec/product/auditfree";
    /** 上架商品 */
    String SPU_LISTING_URL = "https://api.weixin.qq.com/channels/ec/product/listing";
    /** 下架商品 */
    String SPU_DELISTING_URL = "https://api.weixin.qq.com/channels/ec/product/delisting";
    /** 撤回商品审核 */
    String CANCEL_AUDIT_URL = "https://api.weixin.qq.com/channels/ec/product/audit/cancel";
    /** 获取商品H5短链 */
    String SPU_H5URL_URL = "https://api.weixin.qq.com/channels/ec/product/h5url/get";
    /** 获取商品二维码 */
    String SPU_QRCODE_URL = "https://api.weixin.qq.com/channels/ec/product/qrcode/get";
    /** 获取商品口令 */
    String SPU_TAGLINK_URL = "https://api.weixin.qq.com/channels/ec/product/taglink/get";
    /** 获取实时库存 */
    String SPU_GET_STOCK_URL = "https://api.weixin.qq.com/channels/ec/product/stock/get";
    /** 获取实时库存 */
    String SPU_GET_STOCK_BATCH_URL = "https://api.weixin.qq.com/channels/ec/product/stock/batchget";
    /** 更新商品库存 */
    String SPU_UPDATE_STOCK_URL = "https://api.weixin.qq.com/channels/ec/product/stock/update";
    /** 添加限时抢购任务 */
    String ADD_LIMIT_TASK_URL = "https://api.weixin.qq.com/channels/ec/product/limiteddiscounttask/add";
    /** 拉取限时抢购任务列表 */
    String LIST_LIMIT_TASK_URL = "https://api.weixin.qq.com/channels/ec/product/limiteddiscounttask/list/get";
    /** 停止限时抢购任务 */
    String STOP_LIMIT_TASK_URL = "https://api.weixin.qq.com/channels/ec/product/limiteddiscounttask/stop";
    /** 删除限时抢购任务 */
    String DELETE_LIMIT_TASK_URL = "https://api.weixin.qq.com/channels/ec/product/limiteddiscounttask/delete";
  }

  /** 区域仓库 */
  public interface Warehouse {

    /** 添加区域仓库 */
    String ADD_WAREHOUSE_URL = "https://api.weixin.qq.com/channels/ec/warehouse/create";
    /** 获取区域仓库列表 */
    String LIST_WAREHOUSE_URL = "https://api.weixin.qq.com/channels/ec/warehouse/list/get";
    /** 获取区域仓库详情 */
    String GET_WAREHOUSE_URL = "https://api.weixin.qq.com/channels/ec/warehouse/get";
    /** 更新区域仓库详情 */
    String UPDATE_WAREHOUSE_URL = "https://api.weixin.qq.com/channels/ec/warehouse/detail/update";
    /** 批量增加覆盖区域 */
    String ADD_COVER_AREA_URL = "https://api.weixin.qq.com/channels/ec/warehouse/coverlocations/add";
    /** 批量删除覆盖区域 */
    String DELETE_COVER_AREA_URL = "https://api.weixin.qq.com/channels/ec/warehouse/coverlocations/del";
    /** 设置指定地址下的仓的优先级 */
    String SET_WAREHOUSE_PRIORITY_URL = "https://api.weixin.qq.com/channels/ec/warehouse/address/prioritysort/set";
    /** 获取指定地址下的仓的优先级 */
    String GET_WAREHOUSE_PRIORITY_URL = "https://api.weixin.qq.com/channels/ec/warehouse/address/prioritysort/get";
    /** 更新区域仓库存 */
    String UPDATE_WAREHOUSE_STOCK_URL = "https://api.weixin.qq.com/channels/ec/warehouse/stock/update";
    /** 获取区域仓库存 */
    String GET_WAREHOUSE_STOCK_URL = "https://api.weixin.qq.com/channels/ec/warehouse/stock/get";
  }

  /** 订单相关接口 */
  public interface Order {

    /** 获取订单列表 */
    String ORDER_LIST_URL = "https://api.weixin.qq.com/channels/ec/order/list/get";
    /** 获取订单详情 */
    String ORDER_GET_URL = "https://api.weixin.qq.com/channels/ec/order/get";
    /** 更改订单价格 */
    String UPDATE_PRICE_URL = "https://api.weixin.qq.com/channels/ec/order/price/update";
    /** 修改订单备注 */
    String UPDATE_REMARK_URL = "https://api.weixin.qq.com/channels/ec/order/merchantnotes/update";
    /** 更修改订单地址 */
    String UPDATE_ADDRESS_URL = "https://api.weixin.qq.com/channels/ec/order/address/update";
    /** 修改物流信息 */
    String UPDATE_EXPRESS_URL = "https://api.weixin.qq.com/channels/ec/order/deliveryinfo/update";
    /** 同意用户修改收货地址申请 */
    String ACCEPT_ADDRESS_MODIFY_URL = "https://api.weixin.qq.com/channels/ec/order/addressmodify/accept";
    /** 拒绝用户修改收货地址申请 */
    String REJECT_ADDRESS_MODIFY_URL = "https://api.weixin.qq.com/channels/ec/order/addressmodify/reject";
    /** 订单搜索 */
    String ORDER_SEARCH_URL = "https://api.weixin.qq.com/channels/ec/order/search";
    /** 上传生鲜质检信息 */
    String UPLOAD_FRESH_INSPECT_URL = "https://api.weixin.qq.com/channels/ec/order/freshinspect/submit";
    /** 兑换虚拟号 */
    String VIRTUAL_TEL_NUMBER_URL = "https://api.weixin.qq.com/channels/ec/order/virtualtelnumber/get";
    /** 解码订单包含的敏感数据 */
    String DECODE_SENSITIVE_INFO_URL = "https://api.weixin.qq.com/channels/ec/order/sensitiveinfo/decode";
  }

  /** 售后相关接口 */
  public interface AfterSale {

    /** 获取售后列表 */
    String AFTER_SALE_LIST_URL = "https://api.weixin.qq.com/channels/ec/aftersale/getaftersalelist";
    /** 获取售后单 */
    String AFTER_SALE_GET_URL = "https://api.weixin.qq.com/channels/ec/aftersale/getaftersaleorder";
    /** 同意售后 */
    String AFTER_SALE_ACCEPT_URL = "https://api.weixin.qq.com/channels/ec/aftersale/acceptapply";
    /** 拒绝售后 */
    String AFTER_SALE_REJECT_URL = "https://api.weixin.qq.com/channels/ec/aftersale/rejectapply";
    /** 上传退款凭证 */
    String AFTER_SALE_UPLOAD_URL = "https://api.weixin.qq.com/channels/ec/aftersale/uploadrefundcertificate";
    /** 获取全量售后原因*/
    String AFTER_SALE_REASON_GET_URL = "https://api.weixin.qq.com/channels/ec/aftersale/reason/get";
    /** 获取拒绝售后原因*/
    String AFTER_SALE_REJECT_REASON_GET_URL = "https://api.weixin.qq.com/channels/ec/aftersale/rejectreason/get";
  }

  /** 纠纷相关接口 */
  public interface Complaint {

    /** 商家补充纠纷单留言 */
    String ADD_COMPLAINT_MATERIAL_URL = "https://api.weixin.qq.com/channels/ec/aftersale/addcomplaintmaterial";
    /** 商家举证 */
    String ADD_COMPLAINT_PROOF_URL = "https://api.weixin.qq.com/channels/ec/aftersale/addcomplaintproof";
    /** 获取纠纷单 */
    String GET_COMPLAINT_ORDER_URL = "https://api.weixin.qq.com/channels/ec/aftersale/getcomplaintorder";
  }

  /** 物流相关接口 */
  public interface Delivery {

    /** 获取快递公司列表 */
    String GET_DELIVERY_COMPANY_URL = "https://api.weixin.qq.com/channels/ec/order/deliverycompanylist/get";
    /** 订单发货 */
    String DELIVERY_SEND_URL = "https://api.weixin.qq.com/channels/ec/order/delivery/send";
  }

  /** 运费模板相关接口 */
  public interface FreightTemplate {

    /** 获取运费模板列表 */
    String LIST_TEMPLATE_URL = "https://api.weixin.qq.com/channels/ec/merchant/getfreighttemplatelist";
    /** 查询运费模版 */
    String GET_TEMPLATE_URL = "https://api.weixin.qq.com/channels/ec/merchant/getfreighttemplatedetail";
    /** 增加运费模版 */
    String ADD_TEMPLATE_URL = "https://api.weixin.qq.com/channels/ec/merchant/addfreighttemplate";
    /** 更新运费模版 */
    String UPDATE_TEMPLATE_URL = "https://api.weixin.qq.com/channels/ec/merchant/updatefreighttemplate";
  }

  /** 地址管理相关接口 */
  public interface Address {

    /** 增加地址 */
    String ADD_ADDRESS_URL = "https://api.weixin.qq.com/channels/ec/merchant/address/add";
    /** 获取地址列表 */
    String LIST_ADDRESS_URL = "https://api.weixin.qq.com/channels/ec/merchant/address/list";
    /** 获取地址详情 */
    String GET_ADDRESS_URL = "https://api.weixin.qq.com/channels/ec/merchant/address/get";
    /** 更新地址 */
    String UPDATE_ADDRESS_URL = "https://api.weixin.qq.com/channels/ec/merchant/address/update";
    /** 删除地址 */
    String DELETE_ADDRESS_URL = "https://api.weixin.qq.com/channels/ec/merchant/address/delete";
  }

  /** 优惠券相关接口 */
  public interface Coupon {

    /** 创建优惠券 */
    String CREATE_COUPON_URL = "https://api.weixin.qq.com/channels/ec/coupon/create";
    /** 更新优惠券 */
    String UPDATE_COUPON_URL = "https://api.weixin.qq.com/channels/ec/coupon/update";
    /** 更新优惠券状态 */
    String UPDATE_COUPON_STATUS_URL = "https://api.weixin.qq.com/channels/ec/coupon/update_status";
    /** 获取优惠券详情 */
    String GET_COUPON_URL = "https://api.weixin.qq.com/channels/ec/coupon/get";
    /** 获取优惠券ID列表 */
    String LIST_COUPON_URL = "https://api.weixin.qq.com/channels/ec/coupon/get_list";
    /** 获取用户优惠券ID列表 */
    String LIST_USER_COUPON_URL = "https://api.weixin.qq.com/channels/ec/coupon/get_user_coupon_list";
    /** 获取用户优惠券详情 */
    String GET_USER_COUPON_URL = "https://api.weixin.qq.com/channels/ec/coupon/get_user_coupon";
  }

  /** 分享员相关接口 */
  public interface Share {

    /** 邀请分享员 */
    String BIND_SHARER_URL = "https://api.weixin.qq.com/channels/ec/sharer/bind";
    /** 获取绑定的分享员 */
    String SEARCH_SHARER_URL = "https://api.weixin.qq.com/channels/ec/sharer/search_sharer";
    /** 获取绑定的分享员列表 */
    String LIST_SHARER_URL = "https://api.weixin.qq.com/channels/ec/sharer/get_sharer_list";
    /** 获取分享员订单列表 */
    String LIST_SHARER_ORDER_URL = "https://api.weixin.qq.com/channels/ec/sharer/get_sharer_order_list";
    /** 解绑分享员 */
    String UNBIND_SHARER_URL = "https://api.weixin.qq.com/channels/ec/sharer/unbind";
  }

  /** 合作账号相关接口 */
  public interface Cooperation {
    /** 获取合作账号列表 */
    String LIST_COOPERATION_URL = "https://api.weixin.qq.com/channels/ec/cooperation/list";
    /** 查看合作账号邀请状态 */
    String GET_COOPERATION_STATUS_URL = "https://api.weixin.qq.com/channels/ec/cooperation/invitation/get";
    /** 邀请合作账号 */
    String GENERATE_QRCODE_COOPERATION_URL = "https://api.weixin.qq.com/channels/ec/cooperation/invitation/qrcode/generate";
    /** 取消合作账号邀请 */
    String CANCEL_COOPERATION_URL = "https://api.weixin.qq.com/channels/ec/cooperation/invitation/cancel";
    /** 解绑合作账号 */
    String UNBIND_COOPERATION_URL = "https://api.weixin.qq.com/channels/ec/cooperation/unbind";
  }

  /** 资金相关接口 */
  public interface Fund {

    /** 获取账户余额 */
    String GET_BALANCE_URL = "https://api.weixin.qq.com/channels/ec/funds/getbalance";
    /** 获取结算账户 */
    String GET_BANK_ACCOUNT_URL = "https://api.weixin.qq.com/channels/ec/funds/getbankacct";
    /** 获取资金流水详情 */
    String GET_BALANCE_FLOW_DETAIL_URL = "https://api.weixin.qq.com/channels/ec/funds/getfundsflowdetail";
    /** 获取资金流水列表 */
    String GET_BALANCE_FLOW_LIST_URL = "https://api.weixin.qq.com/channels/ec/funds/getfundsflowlist";
    /** 获取提现记录 */
    String GET_WITHDRAW_DETAIL_URL = "https://api.weixin.qq.com/channels/ec/funds/getwithdrawdetail";
    /** 获取提现记录列表 */
    String GET_WITHDRAW_LIST_URL = "https://api.weixin.qq.com/channels/ec/funds/getwithdrawlist";
    /** 修改结算账户 */
    String SET_BANK_ACCOUNT_URL = "https://api.weixin.qq.com/channels/ec/funds/setbankacct";
    /** 商户提现 */
    String WITHDRAW_URL = "https://api.weixin.qq.com/channels/ec/funds/submitwithdraw";
    /** 根据卡号查银行信息 */
    String GET_BANK_BY_NUM_URL = "https://api.weixin.qq.com/shop/funds/getbankbynum";
    /** 搜索银行列表 */
    String GET_BANK_LIST_URL = "https://api.weixin.qq.com/shop/funds/getbanklist";
    /** 查询城市列表 */
    String GET_CITY_URL = "https://api.weixin.qq.com/shop/funds/getcity";
    /** 查询大陆银行省份列表 */
    String GET_PROVINCE_URL = "https://api.weixin.qq.com/shop/funds/getprovince";
    /** 查询支行列表 */
    String GET_SUB_BANK_URL = "https://api.weixin.qq.com/shop/funds/getsubbranch";
    /** 获取二维码 */
    String GET_QRCODE_URL = "https://api.weixin.qq.com/shop/funds/qrcode/get";
    /** 查询扫码状态 */
    String CHECK_QRCODE_URL = "https://api.weixin.qq.com/shop/funds/qrcode/check";
  }

  /** 优选联盟相关接口 */
  public interface League {

    /** 添加团长商品到橱窗 */
    String ADD_SUPPLIER_GOODS_URL = "https://api.weixin.qq.com/channels/ec/league/headsupplier/window/add";
    /** 查询橱窗上团长商品列表 */
    String LIST_SUPPLIER_GOODS_URL = "https://api.weixin.qq.com/channels/ec/league/headsupplier/window/getall";
    /** 从橱窗移除团长商品 */
    String REMOVE_SUPPLIER_GOODS_URL = "https://api.weixin.qq.com/channels/ec/league/headsupplier/window/remove";
    /** 查询橱窗上团长商品详情 */
    String GET_SUPPLIER_GOODS_URL = "https://api.weixin.qq.com/channels/ec/league/headsupplier/window/getdetail";
    /** 获取达人橱窗授权链接 */
    String GET_SUPPLIER_AUTH_URL = "https://api.weixin.qq.com/channels/ec/league/headsupplier/windowauth/get";
    /** 获取达人橱窗授权状态 */
    String GET_SUPPLIER_AUTH_STATUS_URL = "https://api.weixin.qq.com/channels/ec/league/headsupplier/windowauth/status/get";
    /** 获取团长账户余额 */
    String GET_SUPPLIER_BALANCE_URL = "https://api.weixin.qq.com/channels/ec/league/headsupplier/funds/balance/get";
    /** 获取资金流水详情 */
    String GET_SUPPLIER_BALANCE_FLOW_DETAIL_URL = "https://api.weixin.qq.com/channels/ec/league/headsupplier/funds/flowdetail/get";
    /** 获取资金流水列表 */
    String GET_SUPPLIER_BALANCE_FLOW_LIST_URL = "https://api.weixin.qq.com/channels/ec/league/headsupplier/funds/flowlist/get";
    /** 获取合作商品详情 */
    String GET_SUPPLIER_ITEM_URL = "https://api.weixin.qq.com/channels/ec/league/headsupplier/item/get";
    /** 获取合作商品列表 */
    String GET_SUPPLIER_ITEM_LIST_URL = "https://api.weixin.qq.com/channels/ec/league/headsupplier/item/list/get";
    /** 获取佣金单详情 */
    String GET_SUPPLIER_ORDER_URL = "https://api.weixin.qq.com/channels/ec/league/headsupplier/order/get";
    /** 获取佣金单列表 */
    String GET_SUPPLIER_ORDER_LIST_URL = "https://api.weixin.qq.com/channels/ec/league/headsupplier/order/list/get";
    /** 获取合作小店详情 */
    String GET_SUPPLIER_SHOP_URL = "https://api.weixin.qq.com/channels/ec/league/headsupplier/shop/get";
    /** 获取合作小店列表 */
    String GET_SUPPLIER_SHOP_LIST_URL = "https://api.weixin.qq.com/channels/ec/league/headsupplier/shop/list/get";
    /** 新增达人 */
    String ADD_PROMOTER_URL = "https://api.weixin.qq.com/channels/ec/league/promoter/add";
    /** 编辑达人 */
    String EDIT_PROMOTER_URL = "https://api.weixin.qq.com/channels/ec/league/promoter/upd";
    /** 删除达人 */
    String DELETE_PROMOTER_URL = "https://api.weixin.qq.com/channels/ec/league/promoter/delete";
    /** 获取达人详情信息 */
    String GET_PROMOTER_URL = "https://api.weixin.qq.com/channels/ec/league/promoter/get";
    /** 拉取商店达人列表 */
    String GET_PROMOTER_LIST_URL = "https://api.weixin.qq.com/channels/ec/league/promoter/list/get";
    /** 批量新增联盟商品 */
    String BATCH_ADD_LEAGUE_ITEM_URL = "https://api.weixin.qq.com/channels/ec/league/item/batchadd";
    /** 更新联盟商品信息 */
    String UPDATE_LEAGUE_ITEM_URL = "https://api.weixin.qq.com/channels/ec/league/item/upd";
    /** 删除联盟商品 */
    String DELETE_LEAGUE_ITEM_URL = "https://api.weixin.qq.com/channels/ec/league/item/delete";
    /** 拉取联盟商品详情 */
    String GET_LEAGUE_ITEM_URL = "https://api.weixin.qq.com/channels/ec/league/item/get";
    /** 拉取联盟商品推广列表 */
    String GET_LEAGUE_ITEM_LIST_URL = "https://api.weixin.qq.com/channels/ec/league/item/list/get";
  }

  /** 视频号助手开放接口 */
  public interface Assistant {

    /** 上架商品到橱窗 */
    String ADD_WINDOW_PRODUCT_URL = "https://api.weixin.qq.com/channels/ec/window/product/add";
    /** 获取橱窗商品详情 */
    String GET_WINDOW_PRODUCT_URL = "https://api.weixin.qq.com/channels/ec/window/product/get";
    /** 获取已添加到橱窗的商品列表 */
    String LIST_WINDOW_PRODUCT_URL = "https://api.weixin.qq.com/channels/ec/window/product/list/get";
    /** 下架橱窗商品 */
    String OFF_WINDOW_PRODUCT_URL = "https://api.weixin.qq.com/channels/ec/window/product/off";
  }

  /**
   * 留资组件管理
   */
  public interface LeadComponent {

    /**
     * <a href="https://developers.weixin.qq.com/doc/channels/API/leads/get_leads_info_by_component_id.html">按时间获取留资信息详情</a>
     */
    String GET_LEADS_INFO_BY_COMPONENT_ID = "https://api.weixin.qq.com/channels/leads/get_leads_info_by_component_id";

    /**
     * <a href="https://developers.weixin.qq.com/doc/channels/API/leads/get_leads_info_by_request_id.html">按直播场次获取留资信息详情</a>
     */
    String GET_LEADS_INFO_BY_REQUEST_ID = "https://api.weixin.qq.com/channels/leads/get_leads_info_by_request_id";

    /**
     * <a href="https://developers.weixin.qq.com/doc/channels/API/leads/get_leads_request_id.html">获取留资request_id列表详情</a>
     */
    String GET_LEADS_REQUEST_ID = "https://api.weixin.qq.com/channels/leads/get_leads_request_id";

    /**
     * <a href="https://developers.weixin.qq.com/doc/channels/API/leads/get_leads_component_promote_record.html">获取留资组件直播推广记录信息详情</a>
     */
    String GET_LEADS_COMPONENT_PROMOTE_RECORD = "https://api.weixin.qq.com/channels/leads/get_leads_component_promote_record";

    /**
     * <a href="https://developers.weixin.qq.com/doc/channels/API/leads/get_leads_component_id.html">获取留资组件Id列表详情</a>
     */
    String GET_LEADS_COMPONENT_ID = "https://api.weixin.qq.com/channels/leads/get_leads_component_id";
  }

  /**
   * 留资服务的直播数据
   */
  public interface FinderLive {
    /**
     * <a href="https://developers.weixin.qq.com/doc/channels/API/live/get_finder_attr_by_appid.html">获取视频号账号信息</a>
     */
    String GET_FINDER_ATTR_BY_APPID = "https://api.weixin.qq.com/channels/finderlive/get_finder_attr_by_appid";
    /**
     * <a href="https://developers.weixin.qq.com/doc/channels/API/live/get_finder_live_data_list.html">获取留资直播间数据详情</a>
     */
    String GET_FINDER_LIVE_DATA_LIST = "https://api.weixin.qq.com/channels/finderlive/get_finder_live_data_list";
    /**
     * <a href="https://developers.weixin.qq.com/doc/channels/API/live/get_finder_live_leads_data.html">获取账号收集的留资数量</a>
     */
    String GET_FINDER_LIVE_LEADS_DATA = "https://api.weixin.qq.com/channels/finderlive/get_finder_live_leads_data";
  }


  /** 会员功能接口 */
  public interface Vip {
    /** 拉取用户详情 */
    String VIP_USER_INFO_URL = "https://api.weixin.qq.com/channels/ec/vip/user/info/get";
    /** 拉取用户列表 */
    String VIP_USER_LIST_URL = "https://api.weixin.qq.com/channels/ec/vip/user/list/get";

    /** 获取用户积分 */
    String VIP_SCORE_URL = "https://api.weixin.qq.com/channels/ec/vip/user/score/get";
    /** 增加用户积分 */
    String SCORE_INCREASE_URL = "https://api.weixin.qq.com/channels/ec/vip/user/score/increase";
    /** 减少用户积分 */
    String SCORE_DECREASE_URL = "https://api.weixin.qq.com/channels/ec/vip/user/score/decrease";

    /** 更新用户等级 */
    String GRADE_UPDATE_URL = "https://api.weixin.qq.com/channels/ec/vip/user/grade/update";
  }

  /**
   * 直播大屏数据
   */
  public interface LiveDashboard {
    /**
     * <a href="https://developers.weixin.qq.com/doc/channels/API/livedashboard/getlivelist.html">获取直播大屏直播列表</a>
     */
    String GET_LIVE_LIST_URL = "https://api.weixin.qq.com/channels/livedashboard/getlivelist";

    /**
     * <a href="https://developers.weixin.qq.com/doc/channels/API/livedashboard/getlivedata.html">获取直播大屏数据</a>
     */
    String GET_LIVE_DATA_URL = "https://api.weixin.qq.com/channels/livedashboard/getlivedata";
  }

  /**
   * 罗盘达人版API
   */
  public interface CompassFinder {
    /**
     * <a href="https://developers.weixin.qq.com/doc/channels/API/compass/finder/getfinderoverall.html">获取电商概览数据</a>
     */
    String GET_OVERALL_URL = "https://api.weixin.qq.com/channels/ec/compass/finder/overall/get";

    /**
     * <a href="https://developers.weixin.qq.com/doc/channels/API/compass/finder/getfinderproductdata.html">获取带货商品数据</a>
     */
    String GET_PRODUCT_DATA_URL = "https://api.weixin.qq.com/channels/ec/compass/finder/product/data/get";

    /**
     * <a href="https://developers.weixin.qq.com/doc/channels/API/compass/finder/getfinderproductlist.html">获取带货商品列表</a>
     */
    String GET_PRODUCT_LIST_URL = "https://api.weixin.qq.com/channels/ec/compass/finder/product/list/get";

    /**
     * <a href="https://developers.weixin.qq.com/doc/channels/API/compass/finder/getfindersaleprofiledata.html">获取带货人群数据</a>
     */
    String GET_SALE_PROFILE_DATA_URL = "https://api.weixin.qq.com/channels/ec/compass/finder/sale/profile/data/get";
  }

  /**
   * 罗盘商家版API
   */
  public interface CompassShop {

    /** 获取电商数据概览 */
    String GET_SHOP_OVERALL_URL = "https://api.weixin.qq.com/channels/ec/compass/shop/overall/get";
    /** 获取授权视频号列表 */
    String FINDER_AUTH_LIST_URL = "https://api.weixin.qq.com/channels/ec/compass/shop/finder/authorization/list/get";
    /** 获取带货达人列表 */
    String FINDER_LIST_URL = "https://api.weixin.qq.com/channels/ec/compass/shop/finder/list/get";
    /** 获取带货数据概览 */
    String GET_FINDER_OVERALL_URL = "https://api.weixin.qq.com/channels/ec/compass/shop/finder/overall/get";
    /** 获取带货达人商品列表 */
    String GET_FINDER_PRODUCT_LIST_URL = "https://api.weixin.qq.com/channels/ec/compass/shop/finder/product/list/get";
    /** 获取带货达人商品数据 */
    String GET_FINDER_PRODUCT_OVERALL_URL = "https://api.weixin.qq.com/channels/ec/compass/shop/finder/product/overall/get";
    /** 获取店铺开播列表 */
    String GET_LIVE_LIST_URL = "https://api.weixin.qq.com/channels/ec/compass/shop/live/list/get";
    /** 获取商品详细信息 */
    String GET_SHOP_PRODUCT_DATA_URL = "https://api.weixin.qq.com/channels/ec/compass/shop/product/data/get";
    /** 获取商品列表 */
    String GET_SHOP_PRODUCT_LIST_URL = "https://api.weixin.qq.com/channels/ec/compass/shop/product/list/get";
    /** 获取店铺人群数据 */
    String GET_SHOP_SALE_PROFILE_DATA_URL = "https://api.weixin.qq.com/channels/ec/compass/shop/sale/profile/data/get";
  }
}
