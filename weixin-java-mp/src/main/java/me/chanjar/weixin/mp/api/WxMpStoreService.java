package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.store.WxMpStoreBaseInfo;
import me.chanjar.weixin.mp.bean.store.WxMpStoreInfo;
import me.chanjar.weixin.mp.bean.store.WxMpStoreListResult;

import java.util.List;

/**
 * 门店管理的相关接口代码.
 * Created by Binary Wang on 2016-09-23.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMpStoreService {
    /**
     * <pre>
     * 创建门店
     * 接口说明
     * 创建门店接口是为商户提供创建自己门店数据的接口，门店数据字段越完整，商户页面展示越丰富，越能够吸引更多用户，并提高曝光度。
     * 创建门店接口调用成功后会返回errcode 0、errmsg ok，但不会实时返回poi_id。
     * 成功创建后，会生成poi_id，但该id不一定为最终id。门店信息会经过审核，审核通过后方可获取最终poi_id，该id为门店的唯一id，强烈建议自行存储审核通过后的最终poi_id，并为后续调用使用。
     * 详情请见: <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444378120&token=&lang=zh_CN">微信门店接口</a>
     * 接口格式： https://api.weixin.qq.com/cgi-bin/poi/addpoi?access_token=TOKEN
     * </pre>
     *
     * @param request the request
     * @throws WxErrorException the wx error exception
     */
    void add(WxMpStoreBaseInfo request) throws WxErrorException;

    /**
     * <pre>
     * 查询门店信息
     * 创建门店后获取poi_id 后，商户可以利用poi_id，查询具体某条门店的信息。
     * 若在查询时，update_status 字段为1，表明在5 个工作日内曾用update 接口修改过门店扩展字段，该扩展字段为最新的修改字段，尚未经过审核采纳，因此不是最终结果。
     * 最终结果会在5 个工作日内，最终确认是否采纳，并前端生效（但该扩展字段的采纳过程不影响门店的可用性，即available_state仍为审核通过状态）
     * 注：扩展字段为公共编辑信息（大家都可修改），修改将会审核，并决定是否对修改建议进行采纳，但不会影响该门店的生效可用状态。
     * 详情请见: <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444378120&token=&lang=zh_CN">微信门店接口</a>
     * 接口格式：https://api.weixin.qq.com/cgi-bin/poi/getpoi?access_token=TOKEN
     * </pre>
     *
     * @param poiId 门店Id
     * @return the wx mp store base info
     * @throws WxErrorException the wx error exception
     */
    WxMpStoreBaseInfo get(String poiId) throws WxErrorException;

    /**
     * <pre>
     * 删除门店
     * 商户可以通过该接口，删除已经成功创建的门店。请商户慎重调用该接口。
     * 详情请见: <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444378120&token=&lang=zh_CN">微信门店接口</a>
     * 接口格式：https://api.weixin.qq.com/cgi-bin/poi/delpoi?access_token=TOKEN
     * </pre>
     *
     * @param poiId 门店Id
     * @throws WxErrorException the wx error exception
     */
    void delete(String poiId) throws WxErrorException;

    /**
     * <pre>
     * 查询门店列表(指定查询起始位置和个数)
     * 商户可以通过该接口，批量查询自己名下的门店list，并获取已审核通过的poi_id（所有状态均会返回poi_id，但该poi_id不一定为最终id）、商户自身sid 用于对应、商户名、分店名、地址字段。
     * 详情请见: <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444378120&token=&lang=zh_CN">微信门店接口</a>
     * 接口格式：https://api.weixin.qq.com/cgi-bin/poi/getpoilist?access_token=TOKEN
     * </pre>
     *
     * @param begin 开始位置，0 即为从第一条开始查询
     * @param limit 返回数据条数，最大允许50，默认为20
     * @return the wx mp store list result
     * @throws WxErrorException the wx error exception
     */
    WxMpStoreListResult list(int begin, int limit) throws WxErrorException;

    /**
     * <pre>
     * 查询门店列表（所有）
     * 商户可以通过该接口，批量查询自己名下的门店list，并获取已审核通过的poi_id（所有状态均会返回poi_id，但该poi_id不一定为最终id）、商户自身sid 用于对应、商户名、分店名、地址字段。
     * 详情请见: <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444378120&token=&lang=zh_CN">微信门店接口</a>
     * 接口格式：https://api.weixin.qq.com/cgi-bin/poi/getpoilist?access_token=TOKEN
     * </pre>
     *
     * @return the list
     * @throws WxErrorException the wx error exception
     */
    List<WxMpStoreInfo> listAll() throws WxErrorException;

    /**
     * <pre>
     * 修改门店服务信息
     * 商户可以通过该接口，修改门店的服务信息，包括：sid、图片列表、营业时间、推荐、特色服务、简介、人均价格、电话8个字段（名称、坐标、地址等不可修改）修改后需要人工审核。
     * 详情请见: <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444378120&token=&lang=zh_CN">微信门店接口</a>
     * 接口格式：https://api.weixin.qq.com/cgi-bin/poi/updatepoi?access_token=TOKEN
     * </pre>
     *
     * @param info the info
     * @throws WxErrorException the wx error exception
     */
    void update(WxMpStoreBaseInfo info) throws WxErrorException;

    /**
     * <pre>
     * 门店类目表
     * 类目名称接口是为商户提供自己门店类型信息的接口。门店类目定位的越规范，能够精准的吸引更多用户，提高曝光率。
     * 详情请见: <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444378120&token=&lang=zh_CN">微信门店接口</a>
     * 接口格式：https://api.weixin.qq.com/cgi-bin/poi/getwxcategory?access_token=TOKEN
     * </pre>
     *
     * @return the list
     * @throws WxErrorException the wx error exception
     */
    List<String> listCategories() throws WxErrorException;

}
