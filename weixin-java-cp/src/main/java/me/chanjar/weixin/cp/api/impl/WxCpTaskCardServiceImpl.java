package me.chanjar.weixin.cp.api.impl;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.WxCpTaskCardService;
import me.chanjar.weixin.cp.bean.message.TemplateCardMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.TaskCard.UPDATE_TASK_CARD;
import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.TaskCard.UPDATE_TEMPLATE_CARD;

/**
 * <pre>
 *  任务卡片管理接口.
 *  Created by Jeff on 2019-05-16.
 * </pre>
 *
 * @author <a href="https://github.com/domainname">Jeff</a> created on  2019-05-16
 */
@RequiredArgsConstructor
public class WxCpTaskCardServiceImpl implements WxCpTaskCardService {
  private final WxCpService mainService;

  @Override
  public void update(List<String> userIds, String taskId, String replaceName) throws WxErrorException {
    Integer agentId = this.mainService.getWxCpConfigStorage().getAgentId();

    Map<String, Object> data = new HashMap<>(4);
    data.put("userids", userIds);
    data.put("agentid", agentId);
    data.put("task_id", taskId);
    // 文档地址：https://open.work.weixin.qq.com/wwopen/devtool/interface?doc_id=16386
    data.put("clicked_key", replaceName);

    String url = this.mainService.getWxCpConfigStorage().getApiUrl(UPDATE_TASK_CARD);
    this.mainService.post(url, WxGsonBuilder.create().toJson(data));
  }

  @Override
  public void updateTemplateCardButton(List<String> userIds, List<Integer> partyIds,
                                       List<Integer> tagIds, Integer atAll,
                                       String responseCode, String replaceName) throws WxErrorException {
    Integer agentId = this.mainService.getWxCpConfigStorage().getAgentId();
    Map<String, Object> data = new HashMap<>(7);
    data.put("userids", userIds);
    data.put("partyids", partyIds);
    data.put("tagids", tagIds);
    data.put("atall", atAll);
    data.put("agentid", agentId);
    data.put("response_code", responseCode);
    Map<String, String> btnMap = new HashMap<>();
    btnMap.put("replace_name", replaceName);
    data.put("button", btnMap);

    String url = this.mainService.getWxCpConfigStorage().getApiUrl(UPDATE_TEMPLATE_CARD);
    this.mainService.post(url, WxGsonBuilder.create().toJson(data));

  }

  @Override
  public void updateTemplateCardButton(TemplateCardMessage templateCardMessage) throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getApiUrl(UPDATE_TEMPLATE_CARD);
    this.mainService.post(url, templateCardMessage.toJson());
  }
}
