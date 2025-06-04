package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.WxCpOaService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.*;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.*;

/**
 * 企业微信 OA 接口实现
 *
 * @author Element  created on  2019-04-06 11:20
 */
@RequiredArgsConstructor
public class WxCpOaServiceImpl implements WxCpOaService {
  private final WxCpService mainService;

  private static final int MONTH_SECONDS = 31 * 24 * 60 * 60;
  private static final int USER_IDS_LIMIT = 100;

  @Override
  public String apply(WxCpOaApplyEventRequest request) throws WxErrorException {
    String responseContent = this.mainService.post(this.mainService.getWxCpConfigStorage().getApiUrl(APPLY_EVENT),
      request.toJson());
    return GsonParser.parse(responseContent).get("sp_no").getAsString();
  }

  @Override
  public List<WxCpCheckinData> getCheckinData(Integer openCheckinDataType, @NonNull Date startTime,
                                              @NonNull Date endTime,
                                              List<String> userIdList) throws WxErrorException {
    if (userIdList == null || userIdList.size() > USER_IDS_LIMIT) {
      throw new WxRuntimeException("用户列表不能为空，不超过 " + USER_IDS_LIMIT + " 个，若用户超过 " + USER_IDS_LIMIT + " 个，请分批获取");
    }

    long endTimestamp = endTime.getTime() / 1000L;
    long startTimestamp = startTime.getTime() / 1000L;

    if (endTimestamp - startTimestamp < 0 || endTimestamp - startTimestamp > MONTH_SECONDS) {
      throw new WxRuntimeException("获取记录时间跨度不超过一个月");
    }

    JsonObject jsonObject = new JsonObject();
    JsonArray jsonArray = new JsonArray();

    jsonObject.addProperty("opencheckindatatype", openCheckinDataType);
    jsonObject.addProperty("starttime", startTimestamp);
    jsonObject.addProperty("endtime", endTimestamp);

    for (String userid : userIdList) {
      jsonArray.add(userid);
    }

    jsonObject.add("useridlist", jsonArray);

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_CHECKIN_DATA);
    String responseContent = this.mainService.post(url, jsonObject.toString());
    JsonObject tmpJson = GsonParser.parse(responseContent);
    return WxCpGsonBuilder.create().fromJson(tmpJson.get("checkindata"),
      new TypeToken<List<WxCpCheckinData>>() {
      }.getType()
    );
  }

  @Override
  public List<WxCpCheckinOption> getCheckinOption(@NonNull Date datetime, List<String> userIdList) throws WxErrorException {
    if (userIdList == null || userIdList.size() > USER_IDS_LIMIT) {
      throw new WxRuntimeException("用户列表不能为空，不超过 " + USER_IDS_LIMIT + " 个，若用户超过 " + USER_IDS_LIMIT + " 个，请分批获取");
    }

    JsonArray jsonArray = new JsonArray();
    for (String userid : userIdList) {
      jsonArray.add(userid);
    }

    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("datetime", datetime.getTime() / 1000L);
    jsonObject.add("useridlist", jsonArray);

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_CHECKIN_OPTION);
    String responseContent = this.mainService.post(url, jsonObject.toString());
    JsonObject tmpJson = GsonParser.parse(responseContent);

    return WxCpGsonBuilder.create().fromJson(tmpJson.get("info"),
      new TypeToken<List<WxCpCheckinOption>>() {
      }.getType()
    );
  }

  @Override
  public List<WxCpCropCheckinOption> getCropCheckinOption() throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_CORP_CHECKIN_OPTION);
    String responseContent = this.mainService.post(url, jsonObject.toString());
    JsonObject tmpJson = GsonParser.parse(responseContent);

    return WxCpGsonBuilder.create().fromJson(tmpJson.get("group"),
      new TypeToken<List<WxCpCropCheckinOption>>() {
      }.getType()
    );
  }

  @Override
  public WxCpApprovalInfo getApprovalInfo(@NonNull Date startTime, @NonNull Date endTime,
                                          Integer cursor, Integer size, List<WxCpApprovalInfoQueryFilter> filters)
    throws WxErrorException {
    if (cursor == null) {
      cursor = 0;
    }

    if (size == null) {
      size = 100;
    }

    if (size < 0 || size > 100) {
      throw new IllegalArgumentException("size参数错误,请使用[1-100]填充，默认100");
    }

    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("starttime", startTime.getTime() / 1000L);
    jsonObject.addProperty("endtime", endTime.getTime() / 1000L);
    jsonObject.addProperty("size", size);
    jsonObject.addProperty("cursor", cursor);

    if (filters != null && !filters.isEmpty()) {
      JsonArray filterJsonArray = new JsonArray();
      for (WxCpApprovalInfoQueryFilter filter : filters) {
        filterJsonArray.add(JsonParser.parseString(filter.toJson()));
      }
      jsonObject.add("filters", filterJsonArray);
    }

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_APPROVAL_INFO);
    String responseContent = this.mainService.post(url, jsonObject.toString());

    return WxCpGsonBuilder.create().fromJson(responseContent, WxCpApprovalInfo.class);
  }

  @Override
  public WxCpApprovalInfo getApprovalInfo(@NonNull Date startTime, @NonNull Date endTime) throws WxErrorException {
    return this.getApprovalInfo(startTime, endTime, 0, null, null);
  }

  @Override
  public WxCpApprovalInfo getApprovalInfo(@NonNull Date startTime, @NonNull Date endTime, String newCursor,
                                          Integer size, List<WxCpApprovalInfoQueryFilter> filters)
    throws WxErrorException {
    if (newCursor == null) {
      newCursor = "";
    }

    if (size == null) {
      size = 100;
    }

    if (size < 0 || size > 100) {
      throw new IllegalArgumentException("size参数错误,请使用[1-100]填充，默认100");
    }

    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("starttime", startTime.getTime() / 1000L);
    jsonObject.addProperty("endtime", endTime.getTime() / 1000L);
    jsonObject.addProperty("size", size);
    jsonObject.addProperty("new_cursor", newCursor);

    if (filters != null && !filters.isEmpty()) {
      JsonArray filterJsonArray = new JsonArray();
      for (WxCpApprovalInfoQueryFilter filter : filters) {
        filterJsonArray.add(JsonParser.parseString(filter.toJson()));
      }
      jsonObject.add("filters", filterJsonArray);
    }

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_APPROVAL_INFO);
    String responseContent = this.mainService.post(url, jsonObject.toString());

    return WxCpGsonBuilder.create().fromJson(responseContent, WxCpApprovalInfo.class);
  }

  @Override
  public WxCpApprovalDetailResult getApprovalDetail(@NonNull String spNo) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("sp_no", spNo);

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_APPROVAL_DETAIL);
    String responseContent = this.mainService.post(url, jsonObject.toString());

    return WxCpGsonBuilder.create().fromJson(responseContent, WxCpApprovalDetailResult.class);
  }

  @Override
  public WxCpCorpConfInfo getCorpConf() throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_CORP_CONF);
    String responseContent = this.mainService.get(url, null);
    return WxCpCorpConfInfo.fromJson(responseContent);
  }

  @Override
  public WxCpUserVacationQuota getUserVacationQuota(@NonNull String userId) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_USER_VACATION_QUOTA);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("userid", userId);
    String responseContent = this.mainService.post(url, jsonObject.toString());
    return WxCpUserVacationQuota.fromJson(responseContent);
  }

  @Override
  public WxCpGetApprovalData getApprovalData(@NonNull Long startTime, @NonNull Long endTime, Long nextSpNum) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_APPROVAL_DATA);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("starttime", startTime);
    jsonObject.addProperty("endtime", endTime);
    if (nextSpNum != null) {
      jsonObject.addProperty("next_spnum", nextSpNum);
    }
    String responseContent = this.mainService.post(url, jsonObject.toString());
    return WxCpGetApprovalData.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp setOneUserQuota(@NonNull String userId, @NonNull Integer vacationId,
                                      @NonNull Integer leftDuration, @NonNull Integer timeAttr, String remarks) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(SET_ONE_USER_QUOTA);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("userid", userId);
    jsonObject.addProperty("vacation_id", vacationId);
    jsonObject.addProperty("leftduration", leftDuration);
    jsonObject.addProperty("time_attr", timeAttr);
    if (StringUtils.isNotEmpty(remarks)) {
      jsonObject.addProperty("remarks", remarks);
    }
    String responseContent = this.mainService.post(url, jsonObject.toString());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public List<WxCpDialRecord> getDialRecord(Date startTime, Date endTime, Integer offset, Integer limit)
    throws WxErrorException {
    JsonObject jsonObject = new JsonObject();

    if (offset == null) {
      offset = 0;
    }

    if (limit == null || limit <= 0) {
      limit = 100;
    }

    jsonObject.addProperty("offset", offset);
    jsonObject.addProperty("limit", limit);

    if (startTime != null && endTime != null) {
      long endtimestamp = endTime.getTime() / 1000L;
      long starttimestamp = startTime.getTime() / 1000L;

      if (endtimestamp - starttimestamp < 0 || endtimestamp - starttimestamp >= MONTH_SECONDS) {
        throw new WxRuntimeException("受限于网络传输，起止时间的最大跨度为30天，如超过30天，则以结束时间为基准向前取30天进行查询");
      }

      jsonObject.addProperty("start_time", starttimestamp);
      jsonObject.addProperty("end_time", endtimestamp);
    }

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_DIAL_RECORD);
    String responseContent = this.mainService.post(url, jsonObject.toString());
    JsonObject tmpJson = GsonParser.parse(responseContent);

    return WxCpGsonBuilder.create().fromJson(tmpJson.get("record"),
      new TypeToken<List<WxCpDialRecord>>() {
      }.getType()
    );
  }

  @Override
  public WxCpOaApprovalTemplateResult getTemplateDetail(@NonNull String templateId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("template_id", templateId);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_TEMPLATE_DETAIL);
    String responseContent = this.mainService.post(url, jsonObject.toString());
    return WxCpGsonBuilder.create().fromJson(responseContent, WxCpOaApprovalTemplateResult.class);
  }

  @Override
  public String createOaApprovalTemplate(WxCpOaApprovalTemplate cpTemplate) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(CREATE_TEMPLATE);
    String responseContent = this.mainService.post(url, WxCpGsonBuilder.create().toJson(cpTemplate));
    JsonObject tmpJson = GsonParser.parse(responseContent);
    return tmpJson.get("template_id").getAsString();
  }

  @Override
  public void updateOaApprovalTemplate(WxCpOaApprovalTemplate wxCpTemplate) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(UPDATE_TEMPLATE);
    this.mainService.post(url, WxCpGsonBuilder.create().toJson(wxCpTemplate));
  }

  @Override
  public List<WxCpCheckinDayData> getCheckinDayData(@NonNull Date startTime, @NonNull Date endTime,
                                                    List<String> userIdList)
    throws WxErrorException {
    if (userIdList == null || userIdList.size() > USER_IDS_LIMIT) {
      throw new WxRuntimeException("用户列表不能为空，不超过 " + USER_IDS_LIMIT + " 个，若用户超过 " + USER_IDS_LIMIT + " 个，请分批获取");
    }

    long endTimestamp = endTime.getTime() / 1000L;
    long startTimestamp = startTime.getTime() / 1000L;

    JsonObject jsonObject = new JsonObject();
    JsonArray jsonArray = new JsonArray();

    jsonObject.addProperty("starttime", startTimestamp);
    jsonObject.addProperty("endtime", endTimestamp);

    for (String userid : userIdList) {
      jsonArray.add(userid);
    }
    jsonObject.add("useridlist", jsonArray);

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_CHECKIN_DAY_DATA);
    String responseContent = this.mainService.post(url, jsonObject.toString());
    JsonObject tmpJson = GsonParser.parse(responseContent);
    return WxCpGsonBuilder.create().fromJson(tmpJson.get("datas"),
      new TypeToken<List<WxCpCheckinDayData>>() {
      }.getType()
    );
  }

  @Override
  public List<WxCpCheckinMonthData> getCheckinMonthData(@NonNull Date startTime, @NonNull Date endTime,
                                                        List<String> userIdList)
    throws WxErrorException {
    if (userIdList == null || userIdList.size() > USER_IDS_LIMIT) {
      throw new WxRuntimeException("用户列表不能为空，不超过 " + USER_IDS_LIMIT + " 个，若用户超过 " + USER_IDS_LIMIT + " 个，请分批获取");
    }

    long endTimestamp = endTime.getTime() / 1000L;
    long startTimestamp = startTime.getTime() / 1000L;

    JsonObject jsonObject = new JsonObject();
    JsonArray jsonArray = new JsonArray();

    jsonObject.addProperty("starttime", startTimestamp);
    jsonObject.addProperty("endtime", endTimestamp);

    for (String userid : userIdList) {
      jsonArray.add(userid);
    }
    jsonObject.add("useridlist", jsonArray);

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_CHECKIN_MONTH_DATA);
    String responseContent = this.mainService.post(url, jsonObject.toString());
    JsonObject tmpJson = GsonParser.parse(responseContent);
    return WxCpGsonBuilder.create().fromJson(tmpJson.get("datas"),
      new TypeToken<List<WxCpCheckinMonthData>>() {
      }.getType()
    );
  }

  @Override
  public List<WxCpCheckinSchedule> getCheckinScheduleList(@NonNull Date startTime, @NonNull Date endTime,
                                                          List<String> userIdList)
    throws WxErrorException {
    if (userIdList == null || userIdList.size() > USER_IDS_LIMIT) {
      throw new WxRuntimeException("用户列表不能为空，不超过 " + USER_IDS_LIMIT + " 个，若用户超过 " + USER_IDS_LIMIT + " 个，请分批获取");
    }

    long endTimestamp = endTime.getTime() / 1000L;
    long startTimestamp = startTime.getTime() / 1000L;


    JsonObject jsonObject = new JsonObject();
    JsonArray jsonArray = new JsonArray();

    jsonObject.addProperty("starttime", startTimestamp);
    jsonObject.addProperty("endtime", endTimestamp);

    for (String userid : userIdList) {
      jsonArray.add(userid);
    }
    jsonObject.add("useridlist", jsonArray);

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_CHECKIN_SCHEDULE_DATA);
    String responseContent = this.mainService.post(url, jsonObject.toString());
    JsonObject tmpJson = GsonParser.parse(responseContent);
    return WxCpGsonBuilder.create().fromJson(tmpJson.get("schedule_list"),
      new TypeToken<List<WxCpCheckinSchedule>>() {
      }.getType()
    );
  }

  @Override
  public void setCheckinScheduleList(WxCpSetCheckinSchedule wxCpSetCheckinSchedule) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(SET_CHECKIN_SCHEDULE_DATA);
    this.mainService.post(url, WxCpGsonBuilder.create().toJson(wxCpSetCheckinSchedule));
  }

  @Override
  public void addCheckInUserFace(String userId, String userFace) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("userid", userId);
    jsonObject.addProperty("userface", userFace);
    String url = this.mainService.getWxCpConfigStorage().getApiUrl(ADD_CHECK_IN_USER_FACE);
    this.mainService.post(url, jsonObject.toString());
  }
}
