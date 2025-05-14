package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.league.promoter.PromoterInfoResponse;
import me.chanjar.weixin.channel.bean.league.promoter.PromoterListResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 优选联盟 达人服务
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface WxLeaguePromoterService {

    /**
     * 新增达人
     *
     * @param finderId 视频号finder_id，待废除
     * @return 结果
     * @deprecated 使用 {@link #addPromoterV2(String)}
     */
    @Deprecated
    WxChannelBaseResponse addPromoter(String finderId) throws WxErrorException;

    /**
     * 编辑达人
     *
     * @param finderId 视频号finder_id，待废除
     * @param type     操作 1取消邀请 2结束合作
     * @return 结果
     * @deprecated 使用 {@link #updatePromoterV2(String, int)}
     */
    @Deprecated
    WxChannelBaseResponse updatePromoter(String finderId, int type) throws WxErrorException;

    /**
     * 删除达人
     *
     * @param finderId 视频号finder_id，待废除
     * @return 结果
     * @deprecated 使用 {@link #deletePromoterV2(String)}
     */
    @Deprecated
    WxChannelBaseResponse deletePromoter(String finderId) throws WxErrorException;

    /**
     * 获取达人详情信息
     *
     * @param finderId 视频号finder_id，待废除
     * @return 结果
     * @deprecated 使用 {@link #getPromoterInfoV2(String)}
     */
    @Deprecated
    PromoterInfoResponse getPromoterInfo(String finderId) throws WxErrorException;

  /**
   * 新增达人
   *
   * @param promoterId 达人带货id
   * @return 结果
   */
  WxChannelBaseResponse addPromoterV2(String promoterId) throws WxErrorException;

  /**
   * 编辑达人
   *
   * @param promoterId 达人带货id
   * @param type     操作 1取消邀请 2结束合作
   * @return 结果
   */
  WxChannelBaseResponse updatePromoterV2(String promoterId, int type) throws WxErrorException;

  /**
   * 删除达人
   *
   * @param promoterId 达人带货id
   * @return 结果
   */
  WxChannelBaseResponse deletePromoterV2(String promoterId) throws WxErrorException;

  /**
   * 获取达人详情信息
   *
   * @param promoterId 达人带货id
   * @return 结果
   */
  PromoterInfoResponse getPromoterInfoV2(String promoterId) throws WxErrorException;

  /**
   * 获取达人列表
   *
   * @param pageIndex 页面下标，下标从1开始，默认为1
   * @param pageSize  单页达人数（不超过200）
   * @param status    拉取该状态下的达人列表
   * @return 结果
   */
  PromoterListResponse listPromoter(Integer pageIndex, Integer pageSize, Integer status) throws WxErrorException;
}
