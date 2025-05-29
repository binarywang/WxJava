package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpAgentWorkBench;

/**
 * The interface Wx cp agent work bench service.
 * <a href="https://work.weixin.qq.com/api/doc/90000/90135/92535">工作台自定义展示</a>
 *
 * @author songshiyu
 * created on 16:16 2020/9/27
 */
public interface WxCpAgentWorkBenchService {

  /**
   * Sets work bench template.
   *
   * @param wxCpAgentWorkBench the wx cp agent work bench
   * @throws WxErrorException the wx error exception
   */
  void setWorkBenchTemplate(WxCpAgentWorkBench wxCpAgentWorkBench) throws WxErrorException;

  /**
   * Gets work bench template.
   *
   * @param agentid the agentid
   * @return the work bench template
   * @throws WxErrorException the wx error exception
   */
  String getWorkBenchTemplate(Long agentid) throws WxErrorException;

  /**
   * Sets work bench data.
   *
   * @param wxCpAgentWorkBench the wx cp agent work bench
   * @throws WxErrorException the wx error exception
   */
  void setWorkBenchData(WxCpAgentWorkBench wxCpAgentWorkBench) throws WxErrorException;

  /**
   * Batch sets work bench data.
   *
   * @param wxCpAgentWorkBench the wx cp agent work bench
   * @throws WxErrorException the wx error exception
   */
  void batchSetWorkBenchData(WxCpAgentWorkBench wxCpAgentWorkBench) throws WxErrorException;
}
