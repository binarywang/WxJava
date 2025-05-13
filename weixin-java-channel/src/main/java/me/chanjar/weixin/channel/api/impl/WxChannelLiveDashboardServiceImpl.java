package me.chanjar.weixin.channel.api.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelLiveDashboardService;
import me.chanjar.weixin.channel.bean.live.dashboard.LiveDataParam;
import me.chanjar.weixin.channel.bean.live.dashboard.LiveDataResponse;
import me.chanjar.weixin.channel.bean.live.dashboard.LiveListParam;
import me.chanjar.weixin.channel.bean.live.dashboard.LiveListResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.ObjectUtils;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.LiveDashboard.*;

/**
 * 视频号助手 直播大屏数据服务实现
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Slf4j
public class WxChannelLiveDashboardServiceImpl implements WxChannelLiveDashboardService {

  /**
   * 微信商店服务
   */
  private final BaseWxChannelServiceImpl<?, ?> shopService;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public WxChannelLiveDashboardServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {this.shopService = shopService;}

  @Override
  public LiveListResponse getLiveList(Long ds) throws WxErrorException {
    LiveListParam param = new LiveListParam(ds);
    String resJson = shopService.post(GET_LIVE_LIST_URL, param);
    return ResponseUtils.decode(resJson, LiveListResponse.class);
  }

  @Override
  public LiveDataResponse getLiveData(String exportId) throws WxErrorException {
    LiveDataParam param = new LiveDataParam(exportId);
    String resJson = shopService.post(GET_LIVE_DATA_URL, param);
    return this.convertLiveDataResponse(resJson);
  }

  /**
   * 微信接口获取直播数据中存在非标准JSON，方便业务处理返回前做好解析
   *    处理参数：
   *    live_dashboard_data，live_comparison_index，live_ec_data_summary，live_ec_conversion_metric，
   *    live_ec_profile，live_distribution_channel，single_live_ec_spu_data_page_v2
   *
   * @param resJson 直播数据返回JSON
   * @return LiveDataResponse
   *
   * @throws WxErrorException 异常
   */
  private LiveDataResponse convertLiveDataResponse(String resJson) throws WxErrorException {
    try {
      ObjectNode rootNode = (ObjectNode) objectMapper.readTree(resJson);
      String[] dataKeyArray = new String[] {
        "live_dashboard_data", "live_comparison_index", "live_ec_data_summary", "live_ec_conversion_metric",
        "live_ec_profile", "live_distribution_channel", "single_live_ec_spu_data_page_v2"
      };
      for(String dataKey : dataKeyArray) {
        JsonNode jsonNode = rootNode.get(dataKey);
        if (ObjectUtils.isNotEmpty(jsonNode)) {
          JsonNode dataJsonNode = objectMapper.readTree(jsonNode.asText());
          rootNode.set(dataKey, dataJsonNode);
        }
      }
      String json = objectMapper.writeValueAsString(rootNode);
      return ResponseUtils.decode(json, LiveDataResponse.class);
    } catch (JsonProcessingException e) {
      throw new WxErrorException(e);
    }
  }

}
