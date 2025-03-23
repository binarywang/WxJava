package me.chanjar.weixin.channel.api.impl;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Category.ADD_CATEGORY_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Category.AVAILABLE_CATEGORY_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Category.CANCEL_CATEGORY_AUDIT_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Category.GET_CATEGORY_AUDIT_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Category.GET_CATEGORY_DETAIL_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Category.LIST_ALL_CATEGORY_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Category.LIST_PASS_CATEGORY_URL;

import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelCategoryService;
import me.chanjar.weixin.channel.bean.audit.AuditApplyResponse;
import me.chanjar.weixin.channel.bean.audit.AuditResponse;
import me.chanjar.weixin.channel.bean.audit.CategoryAuditInfo;
import me.chanjar.weixin.channel.bean.audit.CategoryAuditRequest;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.category.CategoryDetailResult;
import me.chanjar.weixin.channel.bean.category.CategoryQualificationResponse;
import me.chanjar.weixin.channel.bean.category.PassCategoryResponse;
import me.chanjar.weixin.channel.bean.category.ShopCategory;
import me.chanjar.weixin.channel.bean.category.ShopCategoryResponse;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.SimpleGetRequestExecutor;
import me.chanjar.weixin.common.util.http.SimplePostRequestExecutor;

/**
 * 视频号小店 商品类目相关接口
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxChannelCategoryServiceImpl implements WxChannelCategoryService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl shopService;

  public WxChannelCategoryServiceImpl(BaseWxChannelServiceImpl shopService) {
    this.shopService = shopService;
  }

  @Override
  public CategoryQualificationResponse listAllCategory() throws WxErrorException {
    // 数据量太大了，不记录日志
    String resJson = (String) shopService.executeWithoutLog(SimpleGetRequestExecutor.create(shopService),
      LIST_ALL_CATEGORY_URL, null);
    return ResponseUtils.decode(resJson, CategoryQualificationResponse.class);
  }

  public List<ShopCategory> listAvailableCategory(String parentId) throws WxErrorException {
    Long pid = null;
    try {
      pid = Long.parseLong(parentId);
    } catch (Throwable e) {
      log.error("parentId必须为数字, " + parentId, e);
      return Collections.emptyList();
    }
    String reqJson = "{\"f_cat_id\": " + pid + "}";
    String resJson = (String) shopService.executeWithoutLog(SimplePostRequestExecutor.create(shopService),
      AVAILABLE_CATEGORY_URL, reqJson);
    ShopCategoryResponse response = ResponseUtils.decode(resJson, ShopCategoryResponse.class);
    return response.getCategories();
  }

  @Override
  public ShopCategoryResponse listAvailableCategories(String fCatId) throws WxErrorException {
    String reqJson = "{\"f_cat_id\": " + fCatId + "}";
    String resJson = (String) shopService.executeWithoutLog(SimplePostRequestExecutor.create(shopService),
      AVAILABLE_CATEGORY_URL, reqJson);
    return ResponseUtils.decode(resJson, ShopCategoryResponse.class);
  }

  @Override
  public CategoryDetailResult getCategoryDetail(String id) throws WxErrorException {
    Long catId = null;
    try {
      catId = Long.parseLong(id);
    } catch (Throwable e) {
      log.error("id必须为数字, " + id, e);
      return ResponseUtils.internalError(CategoryDetailResult.class);
    }
    String reqJson = "{\"cat_id\": " + catId + "}";
    String resJson = (String) shopService.executeWithoutLog(SimplePostRequestExecutor.create(shopService),
      GET_CATEGORY_DETAIL_URL, reqJson);
    return ResponseUtils.decode(resJson, CategoryDetailResult.class);
  }

  @Override
  public AuditApplyResponse addCategory(String level1, String level2, String level3, List<String> certificate)
    throws WxErrorException {
    String reqJson = null;
    try {
      Long l1 = Long.parseLong(level1);
      Long l2 = Long.parseLong(level2);
      Long l3 = Long.parseLong(level3);
      CategoryAuditInfo categoryInfo = new CategoryAuditInfo();
      categoryInfo.setLevel1(l1);
      categoryInfo.setLevel2(l2);
      categoryInfo.setLevel3(l3);
      categoryInfo.setCertificates(certificate);
      reqJson = JsonUtils.encode(new CategoryAuditRequest(categoryInfo));
    } catch (Throwable e) {
      log.error("微信请求异常", e);
    }
    String resJson = shopService.post(ADD_CATEGORY_URL, reqJson);
    return ResponseUtils.decode(resJson, AuditApplyResponse.class);
  }

  @Override
  public AuditApplyResponse addCategory(CategoryAuditInfo info) throws WxErrorException {
    String reqJson = JsonUtils.encode(new CategoryAuditRequest(info));
    String resJson = shopService.post(ADD_CATEGORY_URL, reqJson);
    return ResponseUtils.decode(resJson, AuditApplyResponse.class);
  }

  @Override
  public WxChannelBaseResponse cancelCategoryAudit(String auditId) throws WxErrorException {
    String resJson = shopService.post(CANCEL_CATEGORY_AUDIT_URL, "{\"audit_id\": \"" + auditId + "\"}");
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public AuditResponse getAudit(String auditId) throws WxErrorException {
    String resJson = shopService.post(GET_CATEGORY_AUDIT_URL, "{\"audit_id\": \"" + auditId + "\"}");
    return ResponseUtils.decode(resJson, AuditResponse.class);
  }

  @Override
  public PassCategoryResponse listPassCategory() throws WxErrorException {
    String resJson = shopService.get(LIST_PASS_CATEGORY_URL, null);
    return ResponseUtils.decode(resJson, PassCategoryResponse.class);
  }

}
