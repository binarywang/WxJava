package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.datacube.*;

import java.util.Date;
import java.util.List;

/**
 * 统计分析相关接口
 * Created by Binary Wang on 2016/8/23.
 *
 * @author binarywang (https://github.com/binarywang)
 */
public interface WxMpDataCubeService {
  //*******************用户分析数据接口***********************//

    /**
     * <pre>
     * 获取用户增减数据
     * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141082&token=&lang=zh_CN">用户分析数据接口</a>
     * 接口url格式：https://api.weixin.qq.com/datacube/getusersummary?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param beginDate 开始时间
     * @param endDate   最大时间跨度7天，endDate不能早于begingDate
     * @return the user summary
     * @throws WxErrorException the wx error exception
     */
    List<WxDataCubeUserSummary> getUserSummary(Date beginDate, Date endDate) throws WxErrorException;

    /**
     * <pre>
     * 获取累计用户数据
     * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141082&token=&lang=zh_CN">用户分析数据接口</a>
     * 接口url格式：https://api.weixin.qq.com/datacube/getusercumulate?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param beginDate 开始时间
     * @param endDate   最大时间跨度7天，endDate不能早于begingDate
     * @return the user cumulate
     * @throws WxErrorException the wx error exception
     */
    List<WxDataCubeUserCumulate> getUserCumulate(Date beginDate, Date endDate) throws WxErrorException;

  //*******************图文分析数据接口***********************//

    /**
     * <pre>
     * 获取图文群发每日数据（getarticlesummary）
     * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>
     * 接口url格式：https://api.weixin.qq.com/datacube/getarticlesummary?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param beginDate 开始时间
     * @param endDate   最大时间跨度1天，endDate不能早于begingDate
     * @return the article summary
     * @throws WxErrorException the wx error exception
     */
    List<WxDataCubeArticleResult> getArticleSummary(Date beginDate, Date endDate) throws WxErrorException;

    /**
     * 获取图文群发总数据（getarticletotal）
     * <p>
     * {@code 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>}
     * </p>
     * <p>
     * 接口url格式：https://api.weixin.qq.com/datacube/getarticletotal?access_token=ACCESS_TOKEN
     * </p>
     *
     * @param beginDate 开始时间
     * @param endDate 最大时间跨度1天，endDate不能早于begingDate
     * @return the article total
     * @throws WxErrorException the wx error exception
     */
    List<WxDataCubeArticleTotal> getArticleTotal(Date beginDate, Date endDate) throws WxErrorException;

    /**
     * 获取图文统计数据（getuserread）
     * <p>
     * {@code 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>}
     * </p>
     * <p>
     * 接口url格式：https://api.weixin.qq.com/datacube/getuserread?access_token=ACCESS_TOKEN
     * </p>
     *
     * @param beginDate 开始时间
     * @param endDate 最大时间跨度3天，endDate不能早于begingDate
     * @return the user read
     * @throws WxErrorException the wx error exception
     */
    List<WxDataCubeArticleResult> getUserRead(Date beginDate, Date endDate) throws WxErrorException;

    /**
     * 获取图文统计分时数据（getuserreadhour）
     * <p>
     * {@code 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>}
     * </p>
     * <p>
     * 接口url格式：https://api.weixin.qq.com/datacube/getuserreadhour?access_token=ACCESS_TOKEN
     * </p>
     *
     * @param beginDate 开始时间
     * @param endDate 最大时间跨度1天，endDate不能早于begingDate
     * @return the user read hour
     * @throws WxErrorException the wx error exception
     */
    List<WxDataCubeArticleResult> getUserReadHour(Date beginDate, Date endDate) throws WxErrorException;

    /**
     * 获取图文分享转发数据（getusershare）
     * <p>
     * {@code 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>}
     * </p>
     * <p>
     * 接口url格式：https://api.weixin.qq.com/datacube/getusershare?access_token=ACCESS_TOKEN
     * </p>
     *
     * @param beginDate 开始时间
     * @param endDate 最大时间跨度7天，endDate不能早于begingDate
     * @return the user share
     * @throws WxErrorException the wx error exception
     */
    List<WxDataCubeArticleResult> getUserShare(Date beginDate, Date endDate) throws WxErrorException;

    /**
     * 获取图文分享转发分时数据（getusersharehour）
     * <p>
     * {@code 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>}
     * </p>
     * <p>
     * 接口url格式：https://api.weixin.qq.com/datacube/getusersharehour?access_token=ACCESS_TOKEN
     * </p>
     *
     * @param beginDate 开始时间
     * @param endDate 最大时间跨度1天，endDate不能早于begingDate
     * @return the user share hour
     * @throws WxErrorException the wx error exception
     */
    List<WxDataCubeArticleResult> getUserShareHour(Date beginDate, Date endDate) throws WxErrorException;

  //*******************消息分析数据接口***********************//

    /**
     * 获取消息发送概况数据（getupstreammsg）
     * <p>
     * {@code 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消息分析数据接口</a>}
     * </p>
     * <p>
     * 接口url格式：https://api.weixin.qq.com/datacube/getupstreammsg?access_token=ACCESS_TOKEN
     * </p>
     *
     * @param beginDate 开始时间
     * @param endDate 最大时间跨度7天，endDate不能早于begingDate
     * @return the upstream msg
     * @throws WxErrorException the wx error exception
     */
    List<WxDataCubeMsgResult> getUpstreamMsg(Date beginDate, Date endDate) throws WxErrorException;

    /**
     * 获取消息分送分时数据（getupstreammsghour）
     * <p>
     * {@code 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消息分析数据接口</a>}
     * </p>
     * <p>
     * 接口url格式：https://api.weixin.qq.com/datacube/getupstreammsghour?access_token=ACCESS_TOKEN
     * </p>
     *
     * @param beginDate 开始时间
     * @param endDate 最大时间跨度1天，endDate不能早于begingDate
     * @return the upstream msg hour
     * @throws WxErrorException the wx error exception
     */
    List<WxDataCubeMsgResult> getUpstreamMsgHour(Date beginDate, Date endDate) throws WxErrorException;

    /**
     * 获取消息发送周数据（getupstreammsgweek）
     * <p>
     * {@code 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消息分析数据接口</a>}
     * </p>
     * <p>
     * 接口url格式：https://api.weixin.qq.com/datacube/getupstreammsgweek?access_token=ACCESS_TOKEN
     * </p>
     *
     * @param beginDate 开始时间
     * @param endDate 最大时间跨度30天，endDate不能早于begingDate
     * @return the upstream msg week
     * @throws WxErrorException the wx error exception
     */
    List<WxDataCubeMsgResult> getUpstreamMsgWeek(Date beginDate, Date endDate) throws WxErrorException;

    /**
     * 获取消息发送月数据（getupstreammsgmonth）
     * <p>
     * {@code 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消息分析数据接口</a>}
     * </p>
     * <p>
     * 接口url格式：https://api.weixin.qq.com/datacube/getupstreammsgmonth?access_token=ACCESS_TOKEN
     * </p>
     *
     * @param beginDate 开始时间
     * @param endDate 最大时间跨度30天，endDate不能早于begingDate
     * @return the upstream msg month
     * @throws WxErrorException the wx error exception
     */
    List<WxDataCubeMsgResult> getUpstreamMsgMonth(Date beginDate, Date endDate) throws WxErrorException;

    /**
     * 获取消息发送分布数据（getupstreammsgdist）
     * <p>
     * {@code 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消息分析数据接口</a>}
     * </p>
     * <p>
     * 接口url格式：https://api.weixin.qq.com/datacube/getupstreammsgdist?access_token=ACCESS_TOKEN
     * </p>
     *
     * @param beginDate 开始时间
     * @param endDate 最大时间跨度15天，endDate不能早于begingDate
     * @return the upstream msg dist
     * @throws WxErrorException the wx error exception
     */
    List<WxDataCubeMsgResult> getUpstreamMsgDist(Date beginDate, Date endDate) throws WxErrorException;

    /**
     * 获取消息发送分布周数据（getupstreammsgdistweek）
     * <p>
     * {@code 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消息分析数据接口</a>}
     * </p>
     * <p>
     * 接口url格式：https://api.weixin.qq.com/datacube/getupstreammsgdistweek?access_token=ACCESS_TOKEN
     * </p>
     *
     * @param beginDate 开始时间
     * @param endDate 最大时间跨度30天，endDate不能早于begingDate
     * @return the upstream msg dist week
     * @throws WxErrorException the wx error exception
     */
    List<WxDataCubeMsgResult> getUpstreamMsgDistWeek(Date beginDate, Date endDate) throws WxErrorException;

    /**
     * 获取消息发送分布月数据（getupstreammsgdistmonth）
     * <p>
     * {@code 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消息分析数据接口</a>}
     * </p>
     * <p>
     * 接口url格式：https://api.weixin.qq.com/datacube/getupstreammsgdistmonth?access_token=ACCESS_TOKEN
     * </p>
     *
     * @param beginDate 开始时间
     * @param endDate 最大时间跨度30天，endDate不能早于begingDate
     * @return the upstream msg dist month
     * @throws WxErrorException the wx error exception
     */
    List<WxDataCubeMsgResult> getUpstreamMsgDistMonth(Date beginDate, Date endDate) throws WxErrorException;

  //*******************接口分析数据接口***********************//

    /**
     * 获取接口分析数据（getinterfacesummary）
     * <p>
     * {@code 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141086&token=&lang=zh_CN">接口分析数据接口</a>}
     * </p>
     * <p>
     * 接口url格式：https://api.weixin.qq.com/datacube/getinterfacesummary?access_token=ACCESS_TOKEN
     * </p>
     *
     * @param beginDate 开始时间
     * @param endDate 最大时间跨度30天，endDate不能早于begingDate
     * @return the interface summary
     * @throws WxErrorException the wx error exception
     */
    List<WxDataCubeInterfaceResult> getInterfaceSummary(Date beginDate, Date endDate) throws WxErrorException;

    /**
     * 获取接口分析分时数据（getinterfacesummaryhour）
     * <p>
     * {@code 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141086&token=&lang=zh_CN">接口分析数据接口</a>}
     * </p>
     * <p>
     * 接口url格式：https://api.weixin.qq.com/datacube/getinterfacesummaryhour?access_token=ACCESS_TOKEN
     * </p>
     *
     * @param beginDate 开始时间
     * @param endDate 最大时间跨度1天，endDate不能早于begingDate
     * @return the interface summary hour
     * @throws WxErrorException the wx error exception
     */
    List<WxDataCubeInterfaceResult> getInterfaceSummaryHour(Date beginDate, Date endDate) throws WxErrorException;

}
