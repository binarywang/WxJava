package me.chanjar.weixin.open.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.bean.ma.WxFastMaCategory;
import me.chanjar.weixin.open.bean.result.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 微信第三方平台 小程序基础信息接口 (小程序名称、头像、描述、类目等信息设置)
 * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/category/getallcategories.html
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 */
public interface WxOpenMaBasicService {
  /**
   * 1 获取帐号基本信息.
   */
  String OPEN_GET_ACCOUNT_BASIC_INFO = "https://api.weixin.qq.com/cgi-bin/account/getaccountbasicinfo";

  /**
   * 2 小程序名称设置及改名.
   */
  String OPEN_SET_NICKNAME = "https://api.weixin.qq.com/wxa/setnickname";

  /**
   * 3 小程序改名审核状态查询.
   */
  String OPEN_API_WXA_QUERYNICKNAME = "https://api.weixin.qq.com/wxa/api_wxa_querynickname";

  /**
   * 4 微信认证名称检测.
   */
  String OPEN_CHECK_WX_VERIFY_NICKNAME = "https://api.weixin.qq.com/cgi-bin/wxverify/checkwxverifynickname";

  /**
   * 5 修改头像.
   */
  String OPEN_MODIFY_HEADIMAGE = "https://api.weixin.qq.com/cgi-bin/account/modifyheadimage";

  /**
   * 6修改功能介绍.
   */
  String OPEN_MODIFY_SIGNATURE = "https://api.weixin.qq.com/cgi-bin/account/modifysignature";

  /**
   * 7 换绑小程序管理员接口.
   */
  String OPEN_COMPONENT_REBIND_ADMIN = "https://api.weixin.qq.com/cgi-bin/account/componentrebindadmin";

  /**
   * 8.1 获取账号可以设置的所有类目
   */
  String OPEN_GET_ALL_CATEGORIES = "https://api.weixin.qq.com/cgi-bin/wxopen/getallcategories";
  /**
   * 8.2 获取不同类型主体可设置的类目
   */
  String OPEN_GET_ALL_CATEGORIES_BY_TYPE = "https://api.weixin.qq.com/cgi-bin/wxopen/getcategoriesbytype";
  /**
   * 8.3 添加类目
   */
  String OPEN_ADD_CATEGORY = "https://api.weixin.qq.com/cgi-bin/wxopen/addcategory";
  /**
   * 8.4 删除类目
   */
  String OPEN_DELETE_CATEGORY = "https://api.weixin.qq.com/cgi-bin/wxopen/deletecategory";
  /**
   * 8.5 获取账号已经设置的所有类目
   */
  String OPEN_GET_CATEGORY = "https://api.weixin.qq.com/cgi-bin/wxopen/getcategory";
  /**
   * 8.6 修改类目
   */
  String OPEN_MODIFY_CATEGORY = "https://api.weixin.qq.com/cgi-bin/wxopen/modifycategory";
  /**
   * 8.7 获取类目名称信息
   */
  String OPEN_GET_ALL_CATEGORY_NAME = "https://api.weixin.qq.com/cgi-bin/wxopen/getallcategorynamelist";

  /**
   * 获取订单页path信息
   */
  String OPEN_GET_ORDER_PATH_INFO = "https://api.weixin.qq.com/wxa/security/getorderpathinfo";

  String URL_COMPONENT_REBIND_ADMIN = "https://mp.weixin.qq.com/wxopen/componentrebindadmin?appid=%s&component_appid=%s&redirect_uri=%s";


  /**
   * 1.获取小程序的信息
   *
   * @return .
   * @throws WxErrorException .
   */
  WxFastMaAccountBasicInfoResult getAccountBasicInfo() throws WxErrorException;

  /**
   * 2.小程序名称设置及改名
   * <pre>
   *      若接口未返回audit_id，说明名称已直接设置成功，无需审核；若返回audit_id则名称正在审核中。
   *  </pre>
   *
   * @param nickname          昵称
   * @param idCard            身份证照片–临时素材mediaid(个人号必填)
   * @param license           组织机构代码证或营业执照–临时素材mediaid(组织号必填)
   * @param namingOtherStuff1 其他证明材料---临时素材 mediaid
   * @param namingOtherStuff2 其他证明材料---临时素材 mediaid
   * @return .
   * @throws WxErrorException .
   */
  WxFastMaSetNickameResult setNickname(String nickname, String idCard, String license, String namingOtherStuff1,
                                       String namingOtherStuff2) throws WxErrorException;

  /**
   * 3 小程序改名审核状态查询
   *
   * @param auditId 审核单id
   * @return .
   * @throws WxErrorException .
   */
  WxFastMaQueryNicknameStatusResult querySetNicknameStatus(String auditId) throws WxErrorException;

  /**
   * 4. 微信认证名称检测
   *
   * @param nickname 名称
   * @return .
   * @throws WxErrorException .
   */
  WxFastMaCheckNickameResult checkWxVerifyNickname(String nickname) throws WxErrorException;

  /**
   * 5.修改头像
   * <pre>
   *     图片格式只支持：BMP、JPEG、JPG、GIF、PNG，大小不超过2M
   *      注：实际头像始终为正方形
   * </pre>
   *
   * @param headImgMediaId 头像素材media_id
   * @param x1             裁剪框左上角x坐标（取值范围：[0, 1]）
   * @param y1             裁剪框左上角y坐标（取值范围：[0, 1]）
   * @param x2             裁剪框右下角x坐标（取值范围：[0, 1]）
   * @param y2             裁剪框右下角y坐标（取值范围：[0, 1]）
   * @return .
   * @throws WxErrorException .
   */
  WxOpenResult modifyHeadImage(String headImgMediaId, float x1, float y1, float x2, float y2) throws WxErrorException;

  /**
   * 6.修改功能介绍
   *
   * @param signature 简介：4-120字
   * @return .
   * @throws WxErrorException .
   */
  WxOpenResult modifySignature(String signature) throws WxErrorException;

  /**
   * 7.1 获取换绑管理员URL
   * @param redirectUri 跳转URL
   * @param appId 公众号的 appid
   * @return 换绑管理员URL
   */
  String getComponentRebindAdminUrl(String redirectUri, String appId) throws UnsupportedEncodingException;

  /**
   * 7.3 管理员换绑
   *
   * @param taskId 换绑管理员任务序列号(公众平台最终点击提交回跳到第三方平台时携带)
   * @return .
   * @throws WxErrorException .
   */
  WxOpenResult componentRebindAdmin(String taskId) throws WxErrorException;

  /**
   * 8.1 获取账号可以设置的所有类目
   * <pre>
   *     因为不同类目含有特定字段
   *     目前没有完整的类目信息数据
   *     为保证兼容性，放弃将response转换为实体
   * </pre>
   *
   * @return .
   * @throws WxErrorException .
   */
  String getAllCategories() throws WxErrorException;

  /**
   * 8.2获取不同类型主体可设置的类目
   */
  WxOpenGetAllCategoriesByTypeResult getAllCategoriesByType(String verifyType) throws WxErrorException;

  /**
   * 8.3添加类目
   *
   * @param categoryList 类目列表
   * @return .
   * @throws WxErrorException .
   */
  WxOpenResult addCategory(List<WxFastMaCategory> categoryList) throws WxErrorException;

  /**
   * 8.4删除类目
   *
   * @param first  一级类目ID
   * @param second 二级类目ID
   * @return .
   * @throws WxErrorException .
   */
  WxOpenResult deleteCategory(int first, int second) throws WxErrorException;

  /**
   * 8.5获取账号已经设置的所有类目
   *
   * @return .
   * @throws WxErrorException .
   */
  WxFastMaBeenSetCategoryResult getCategory() throws WxErrorException;

  /**
   * 8.6修改类目
   *
   * @param category 实体
   * @return .
   * @throws WxErrorException .
   */
  WxOpenResult modifyCategory(WxFastMaCategory category) throws WxErrorException;

  /**
   * 8.7 获取类目名称信息
   * <pre>
   *     获取所有类目名称信息，用于给用户展示选择
   *     https://developers.weixin.qq.com/doc/oplatform/openApi/miniprogram-management/category-management/api_getallcategoryname.html
   * </pre>
   *
   * @return 类目名称列表
   * @throws WxErrorException .
   */
  WxOpenMaCategoryNameListResult getAllCategoryName() throws WxErrorException;

  /**
   * 获取订单页Path信息
   *
   * @param infoType 0:线上版，1:审核版
   * @return 订单页Path信息
   * @throws WxErrorException .
   */
  WxOpenMaGetOrderPathResult getOrderPathInfo(int infoType) throws WxErrorException;
}
