package me.chanjar.weixin.channel.api;


import me.chanjar.weixin.channel.bean.freight.FreightTemplate;
import me.chanjar.weixin.channel.bean.freight.TemplateIdResponse;
import me.chanjar.weixin.channel.bean.freight.TemplateInfoResponse;
import me.chanjar.weixin.channel.bean.freight.TemplateListResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 运费模板服务接口
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Deprecated
public interface WxChannelFreightTemplateService {

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
