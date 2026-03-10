package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.corpgroup.WxCpCorpGroupCorp;

import java.util.List;

/**
 * 企业互联相关接口
 *
 * @author libo <422423229@qq.com>
 * @since 2023-02-27 9:57 PM
 */
public interface WxCpCorpGroupService {
  /**
   * List app share info list.
   *
   * @param agentId      the agent id
   * @param businessType the business type
   * @param corpId       the corp id
   * @param limit        the limit
   * @param cursor       the cursor
   * @return the list
   * @throws WxErrorException the wx error exception
   */
  List<WxCpCorpGroupCorp> listAppShareInfo(Integer agentId, Integer businessType, String corpId, Integer limit, String cursor) throws WxErrorException;
}
