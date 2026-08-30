package com.binarywang.wxjava.store.api;


import com.binarywang.wxjava.store.bean.freight.FreightTemplate;
import com.binarywang.wxjava.store.bean.freight.TemplateIdResponse;
import com.binarywang.wxjava.store.bean.freight.TemplateInfoResponse;
import com.binarywang.wxjava.store.bean.freight.TemplateListResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店 运费模板服务接口
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface WxStoreFreightTemplateService {

  /**
   * 获取运费模板列表
   *
   * @param offset 起始位置
   * @param limit  拉取个数
   * @return 列表
   *
   * @throws WxErrorException 异常
   */
  TemplateListResponse listTemplate(Integer offset, Integer limit) throws WxErrorException;

  /**
   * 获取运费模板
   *
   * @param templateId 模板id
   * @return 运费模板
   *
   * @throws WxErrorException 异常
   */
  TemplateInfoResponse getTemplate(String templateId) throws WxErrorException;

  /**
   * 添加运费模板
   *
   * @param template 运费模板
   * @return TemplateIdResponse
   *
   * @throws WxErrorException 异常
   */
  TemplateIdResponse addTemplate(FreightTemplate template) throws WxErrorException;

  /**
   * 更新运费模板
   *
   * @param template 运费模板
   * @return TemplateIdResponse
   *
   * @throws WxErrorException 异常
   */
  TemplateIdResponse updateTemplate(FreightTemplate template) throws WxErrorException;
}
