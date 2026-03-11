package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpAgentWorkBenchService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpAgentWorkBench;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.WorkBench.*;

/**
 * 工作台自定义展示实现
 *
 * @author songshiyu
 * created at 11:24 2020/9/28
 */
@RequiredArgsConstructor
public class WxCpAgentWorkBenchServiceImpl implements WxCpAgentWorkBenchService {
  private final WxCpService mainService;

  @Override
  public void setWorkBenchTemplate(WxCpAgentWorkBench wxCpAgentWorkBench) throws WxErrorException {
    final String url = String.format(this.mainService.getWxCpConfigStorage().getApiUrl(WORKBENCH_TEMPLATE_SET));
    this.mainService.post(url, wxCpAgentWorkBench.toTemplateString());
  }

  @Override
  public String getWorkBenchTemplate(Long agentId) throws WxErrorException {
    final String url = String.format(this.mainService.getWxCpConfigStorage().getApiUrl(WORKBENCH_TEMPLATE_GET));
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("agentid", agentId);
    return this.mainService.post(url, jsonObject.toString());
  }

  @Override
  public void setWorkBenchData(WxCpAgentWorkBench wxCpAgentWorkBench) throws WxErrorException {
    final String url = String.format(this.mainService.getWxCpConfigStorage().getApiUrl(WORKBENCH_DATA_SET));
    this.mainService.post(url, wxCpAgentWorkBench.toUserDataString());
  }

  @Override
  public void batchSetWorkBenchData(WxCpAgentWorkBench wxCpAgentWorkBench) throws WxErrorException {
    final String url = String.format(this.mainService.getWxCpConfigStorage().getApiUrl(WORKBENCH_BATCH_DATA_SET));
    this.mainService.post(url, wxCpAgentWorkBench.toBatchUserDataString());
  }
}
