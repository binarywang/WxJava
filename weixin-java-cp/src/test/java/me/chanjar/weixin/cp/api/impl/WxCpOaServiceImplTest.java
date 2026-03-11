package me.chanjar.weixin.cp.api.impl;

import com.google.gson.Gson;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.*;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 企业微信 OA数据接口 测试用例
 *
 * @author Element & Wang_Wong
 */
@Slf4j
@Guice(modules = ApiTestModule.class)
public class WxCpOaServiceImplTest {

  /**
   * The Wx service.
   */
  @Inject
  protected WxCpService wxService;

  /**
   * The Gson.
   */
  @Inject
  protected Gson gson;

  /**
   * Test get checkin data.
   *
   * @throws ParseException   the parse exception
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetCheckinData() throws ParseException, WxErrorException {
    Date startTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2019-04-11");
    Date endTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2019-05-10");

    List<WxCpCheckinData> results = wxService.getOaService()
      .getCheckinData(1, startTime, endTime, Lists.newArrayList("binary"));

    assertThat(results).isNotNull();

    System.out.println("results ");
    System.out.println(gson.toJson(results));

  }

  /**
   * Test get checkin day data.
   *
   * @throws ParseException   the parse exception
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetCheckinDayData() throws ParseException, WxErrorException {
    Date startTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2021-06-30");
    Date endTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2021-07-31");

    List<WxCpCheckinDayData> results = wxService.getOaService()
      .getCheckinDayData(startTime, endTime, Lists.newArrayList("12003648"));

    assertThat(results).isNotNull();


    System.out.println("results ");
    System.out.println(gson.toJson(results));

  }

  /**
   * Test get checkin month data.
   *
   * @throws ParseException   the parse exception
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetCheckinMonthData() throws ParseException, WxErrorException {
    Date startTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2021-07-01");
    Date endTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2021-07-31");

    List<WxCpCheckinMonthData> results = wxService.getOaService()
      .getCheckinMonthData(startTime, endTime, Lists.newArrayList("12003648"));

    assertThat(results).isNotNull();
    System.out.println("results ");
    System.out.println(gson.toJson(results));
  }

  /**
   * Test get checkin schedule data.
   *
   * @throws ParseException   the parse exception
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetCheckinScheduleData() throws ParseException, WxErrorException {
    Date startTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2021-07-01");
    Date endTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2021-07-31");

    List<WxCpCheckinSchedule> results = wxService.getOaService()
      .getCheckinScheduleList(startTime, endTime, Lists.newArrayList("12003648"));

    assertThat(results).isNotNull();
    System.out.println("results ");
    System.out.println(gson.toJson(results));
  }

  /**
   * Test set checkin schedule list.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testSetCheckinScheduleList() throws WxErrorException {
    WxCpSetCheckinSchedule wxCpSetCheckinSchedule = new WxCpSetCheckinSchedule();
    wxCpSetCheckinSchedule.setGroupId(3);
    wxCpSetCheckinSchedule.setYearmonth(202108);
    WxCpSetCheckinSchedule.Item item = new WxCpSetCheckinSchedule.Item();
    item.setScheduleId(0);
    item.setDay(20);
    item.setUserid("12003648");
    wxCpSetCheckinSchedule.setItems(Collections.singletonList(item));
    wxService.getOaService().setCheckinScheduleList(wxCpSetCheckinSchedule);
  }

  /**
   * Test get checkin option.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetCheckinOption() throws WxErrorException {

    Date now = new Date();
    List<WxCpCheckinOption> results = wxService.getOaService().getCheckinOption(now, Lists.newArrayList("binary"));
    assertThat(results).isNotNull();
    System.out.println("results ");
    System.out.println(gson.toJson(results));
  }

  /**
   * Test get crop checkin option.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetCropCheckinOption() throws WxErrorException {
    List<WxCpCropCheckinOption> results = wxService.getOaService().getCropCheckinOption();
    assertThat(results).isNotNull();
    System.out.println("results ");
    System.out.println(gson.toJson(results));
  }

  /**
   * Test new ot_info_v2 structure deserialization.
   */
  @Test
  public void testOtInfoV2Deserialization() {
    // Test JSON with ot_info_v2 structure based on the new API response format
    String jsonWithOtInfoV2 = "{\n" +
      "  \"groupid\": 1,\n" +
      "  \"groupname\": \"test group\",\n" +
      "  \"grouptype\": 0,\n" +
      "  \"ot_info_v2\": {\n" +
      "    \"workdayconf\": {\n" +
      "      \"allow_ot\": true,\n" +
      "      \"type\": 1\n" +
      "    },\n" +
      "    \"restdayconf\": {\n" +
      "      \"allow_ot\": false,\n" +
      "      \"type\": 0\n" +
      "    },\n" +
      "    \"holidayconf\": {\n" +
      "      \"allow_ot\": true,\n" +
      "      \"type\": 2\n" +
      "    }\n" +
      "  }\n" +
      "}";

    WxCpCropCheckinOption option = WxCpGsonBuilder.create().fromJson(jsonWithOtInfoV2, WxCpCropCheckinOption.class);
    assertThat(option).isNotNull();
    assertThat(option.getOtInfoV2()).isNotNull();
    assertThat(option.getOtInfoV2().getWorkdayConf()).isNotNull();
    assertThat(option.getOtInfoV2().getWorkdayConf().getAllowOt()).isTrue();
    assertThat(option.getOtInfoV2().getWorkdayConf().getType()).isEqualTo(1);
    assertThat(option.getOtInfoV2().getRestdayConf()).isNotNull();
    assertThat(option.getOtInfoV2().getRestdayConf().getAllowOt()).isFalse();
    assertThat(option.getOtInfoV2().getHolidayConf().getAllowOt()).isTrue();
    
    System.out.println("Parsed ot_info_v2 structure:");
    System.out.println(gson.toJson(option.getOtInfoV2()));
  }

  /**
   * Test late_rule field deserialization in getCropCheckinOption response.
   */
  @Test
  public void testLateRuleDeserialization() {
    // Test JSON with late_rule structure based on the issue #3323
    String jsonWithLateRule = "{\n" +
      "  \"grouptype\": 1,\n" +
      "  \"groupid\": 1,\n" +
      "  \"checkindate\": [\n" +
      "    {\n" +
      "      \"workdays\": [1, 2, 3, 4, 5],\n" +
      "      \"checkintime\": [\n" +
      "        {\n" +
      "          \"time_id\": 1,\n" +
      "          \"work_sec\": 32400,\n" +
      "          \"off_work_sec\": 64800,\n" +
      "          \"remind_work_sec\": 31800,\n" +
      "          \"remind_off_work_sec\": 64800,\n" +
      "          \"rest_begin_time\": 43200,\n" +
      "          \"rest_end_time\": 48600,\n" +
      "          \"allow_rest\": true,\n" +
      "          \"earliest_work_sec\": 21600,\n" +
      "          \"latest_work_sec\": 64740,\n" +
      "          \"earliest_off_work_sec\": 32460,\n" +
      "          \"latest_off_work_sec\": 107940,\n" +
      "          \"no_need_checkon\": false,\n" +
      "          \"no_need_checkoff\": false\n" +
      "        }\n" +
      "      ],\n" +
      "      \"noneed_offwork\": false,\n" +
      "      \"limit_aheadtime\": 0,\n" +
      "      \"flex_on_duty_time\": 0,\n" +
      "      \"flex_off_duty_time\": 0,\n" +
      "      \"allow_flex\": false,\n" +
      "      \"late_rule\": {\n" +
      "        \"offwork_after_time\": 3600,\n" +
      "        \"onwork_flex_time\": 3600,\n" +
      "        \"allow_offwork_after_time\": true,\n" +
      "        \"timerules\": [\n" +
      "          {\n" +
      "            \"offwork_after_time\": 18000,\n" +
      "            \"onwork_flex_time\": 3600\n" +
      "          },\n" +
      "          {\n" +
      "            \"offwork_after_time\": 21600,\n" +
      "            \"onwork_flex_time\": 7200\n" +
      "          }\n" +
      "        ]\n" +
      "      },\n" +
      "      \"max_allow_arrive_early\": 0,\n" +
      "      \"max_allow_arrive_late\": 0\n" +
      "    }\n" +
      "  ],\n" +
      "  \"groupname\": \"打卡\",\n" +
      "  \"need_photo\": false\n" +
      "}";

    WxCpCropCheckinOption option = WxCpGsonBuilder.create().fromJson(jsonWithLateRule, WxCpCropCheckinOption.class);
    assertThat(option).isNotNull();
    assertThat(option.getCheckinDate()).isNotNull();
    assertThat(option.getCheckinDate().size()).isEqualTo(1);
    
    WxCpCheckinGroupBase.CheckinDate checkinDate = option.getCheckinDate().get(0);
    assertThat(checkinDate).isNotNull();
    assertThat(checkinDate.getAllowFlex()).isFalse();
    assertThat(checkinDate.getMaxAllowArriveEarly()).isEqualTo(0);
    assertThat(checkinDate.getMaxAllowArriveLate()).isEqualTo(0);
    
    // Test late_rule field
    assertThat(checkinDate.getLateRule()).isNotNull();
    assertThat(checkinDate.getLateRule().getOffWorkAfterTime()).isEqualTo(3600);
    assertThat(checkinDate.getLateRule().getOnWorkFlexTime()).isEqualTo(3600);
    assertThat(checkinDate.getLateRule().getAllowOffWorkAfterTime()).isTrue();
    assertThat(checkinDate.getLateRule().getTimerules()).isNotNull();
    assertThat(checkinDate.getLateRule().getTimerules().size()).isEqualTo(2);
    
    // Test timerules
    WxCpCheckinGroupBase.TimeRule firstRule = checkinDate.getLateRule().getTimerules().get(0);
    assertThat(firstRule.getOffWorkAfterTime()).isEqualTo(18000);
    assertThat(firstRule.getOnWorkFlexTime()).isEqualTo(3600);
    
    // Test CheckinTime fields
    assertThat(checkinDate.getCheckinTime()).isNotNull();
    assertThat(checkinDate.getCheckinTime().size()).isEqualTo(1);
    
    WxCpCheckinGroupBase.CheckinTime checkinTime = checkinDate.getCheckinTime().get(0);
    assertThat(checkinTime.getTimeId()).isEqualTo(1);
    assertThat(checkinTime.getRestBeginTime()).isEqualTo(43200);
    assertThat(checkinTime.getRestEndTime()).isEqualTo(48600);
    assertThat(checkinTime.getAllowRest()).isTrue();
    assertThat(checkinTime.getEarliestWorkSec()).isEqualTo(21600);
    assertThat(checkinTime.getLatestWorkSec()).isEqualTo(64740);
    assertThat(checkinTime.getEarliestOffWorkSec()).isEqualTo(32460);
    assertThat(checkinTime.getLatestOffWorkSec()).isEqualTo(107940);
    assertThat(checkinTime.getNoNeedCheckon()).isFalse();
    assertThat(checkinTime.getNoNeedCheckoff()).isFalse();
    
    System.out.println("Successfully parsed late_rule and new checkintime fields:");
    System.out.println(gson.toJson(option));
  }

  /**
   * Test issue #3323 - full JSON from the issue report.
   */
  @Test
  public void testIssue3323FullJson() {
    // Full JSON from issue #3323
    String issueJson = "{\n" +
      "      \"grouptype\": 1,\n" +
      "      \"groupid\": 1,\n" +
      "      \"checkindate\": [\n" +
      "        {\n" +
      "          \"workdays\": [\n" +
      "            1,\n" +
      "            2,\n" +
      "            3,\n" +
      "            4,\n" +
      "            5\n" +
      "          ],\n" +
      "          \"checkintime\": [\n" +
      "            {\n" +
      "              \"time_id\": 1,\n" +
      "              \"work_sec\": 32400,\n" +
      "              \"off_work_sec\": 64800,\n" +
      "              \"remind_work_sec\": 31800,\n" +
      "              \"remind_off_work_sec\": 64800,\n" +
      "              \"rest_begin_time\": 43200,\n" +
      "              \"rest_end_time\": 48600,\n" +
      "              \"allow_rest\": true,\n" +
      "              \"earliest_work_sec\": 21600,\n" +
      "              \"latest_work_sec\": 64740,\n" +
      "              \"earliest_off_work_sec\": 32460,\n" +
      "              \"latest_off_work_sec\": 107940,\n" +
      "              \"no_need_checkon\": false,\n" +
      "              \"no_need_checkoff\": false\n" +
      "            }\n" +
      "          ],\n" +
      "          \"noneed_offwork\": false,\n" +
      "          \"limit_aheadtime\": 0,\n" +
      "          \"flex_on_duty_time\": 0,\n" +
      "          \"flex_off_duty_time\": 0,\n" +
      "          \"allow_flex\": false,\n" +
      "          \"late_rule\": {\n" +
      "            \"offwork_after_time\": 3600,\n" +
      "            \"onwork_flex_time\": 3600,\n" +
      "            \"allow_offwork_after_time\": true,\n" +
      "            \"timerules\": [\n" +
      "              {\n" +
      "                \"offwork_after_time\": 18000,\n" +
      "                \"onwork_flex_time\": 3600\n" +
      "              },\n" +
      "              {\n" +
      "                \"offwork_after_time\": 21600,\n" +
      "                \"onwork_flex_time\": 7200\n" +
      "              },\n" +
      "              {\n" +
      "                \"offwork_after_time\": 28800,\n" +
      "                \"onwork_flex_time\": 10800\n" +
      "              }\n" +
      "            ]\n" +
      "          },\n" +
      "          \"max_allow_arrive_early\": 0,\n" +
      "          \"max_allow_arrive_late\": 0\n" +
      "        }\n" +
      "      ],\n" +
      "      \"spe_workdays\": [],\n" +
      "      \"spe_offdays\": [],\n" +
      "      \"sync_holidays\": true,\n" +
      "      \"groupname\": \"打卡\",\n" +
      "      \"need_photo\": false,\n" +
      "      \"wifimac_infos\": [],\n" +
      "      \"note_can_use_local_pic\": true,\n" +
      "      \"allow_checkin_offworkday\": false,\n" +
      "      \"allow_apply_offworkday\": false,\n" +
      "      \"loc_infos\": []\n" +
      "    }";

    WxCpCropCheckinOption option = WxCpGsonBuilder.create().fromJson(issueJson, WxCpCropCheckinOption.class);
    assertThat(option).isNotNull();
    assertThat(option.getGroupId()).isEqualTo(1);
    assertThat(option.getGroupName()).isEqualTo("打卡");
    assertThat(option.getCheckinDate()).isNotNull();
    assertThat(option.getCheckinDate().size()).isEqualTo(1);
    
    WxCpCheckinGroupBase.CheckinDate checkinDate = option.getCheckinDate().get(0);
    assertThat(checkinDate.getLateRule()).isNotNull();
    assertThat(checkinDate.getLateRule().getOffWorkAfterTime()).isEqualTo(3600);
    assertThat(checkinDate.getLateRule().getOnWorkFlexTime()).isEqualTo(3600);
    assertThat(checkinDate.getLateRule().getAllowOffWorkAfterTime()).isTrue();
    assertThat(checkinDate.getLateRule().getTimerules()).isNotNull();
    assertThat(checkinDate.getLateRule().getTimerules().size()).isEqualTo(3);
    
    System.out.println("✓ Successfully parsed full JSON from issue #3323");
    System.out.println("✓ Late Rule offwork_after_time: " + checkinDate.getLateRule().getOffWorkAfterTime());
    System.out.println("✓ Late Rule onwork_flex_time: " + checkinDate.getLateRule().getOnWorkFlexTime());
    System.out.println("✓ Late Rule allow_offwork_after_time: " + checkinDate.getLateRule().getAllowOffWorkAfterTime());
    System.out.println("✓ Late Rule timerules count: " + checkinDate.getLateRule().getTimerules().size());
  }

  /**
   * Test get approval info.
   *
   * @throws WxErrorException the wx error exception
   * @throws ParseException   the parse exception
   */
  @Test
  public void testGetApprovalInfo() throws WxErrorException, ParseException {
    Date startTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2019-12-01");
    Date endTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2019-12-31");
    WxCpApprovalInfo result = wxService.getOaService().getApprovalInfo(startTime, endTime);

    assertThat(result).isNotNull();

    System.out.println("result ");
    System.out.println(gson.toJson(result));
  }

  /**
   * Test get approval detail.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetApprovalDetail() throws WxErrorException {
    String spNo = "201912020001";
    WxCpApprovalDetailResult result = wxService.getOaService().getApprovalDetail(spNo);

    assertThat(result).isNotNull();

    System.out.println("result ");
    System.out.println(gson.toJson(result));
  }

  /**
   * Test sum_money field deserialization in approval detail.
   * 测试审批详情中总费用金额字段的反序列化
   */
  @Test
  public void testApprovalDetailSumMoney() {
    // 测试包含总费用金额的审批详情JSON
    String jsonWithSumMoney = "{\n" +
      "  \"errcode\": 0,\n" +
      "  \"errmsg\": \"ok\",\n" +
      "  \"info\": {\n" +
      "    \"sp_no\": \"202601140001\",\n" +
      "    \"sp_name\": \"报销申请\",\n" +
      "    \"sp_status\": 2,\n" +
      "    \"template_id\": \"test_template_id\",\n" +
      "    \"apply_time\": 1610000000,\n" +
      "    \"applyer\": {\n" +
      "      \"userid\": \"test_user\",\n" +
      "      \"partyid\": \"1\"\n" +
      "    },\n" +
      "    \"sp_record\": [],\n" +
      "    \"notifyer\": [],\n" +
      "    \"apply_data\": {\n" +
      "      \"contents\": []\n" +
      "    },\n" +
      "    \"comments\": [],\n" +
      "    \"sum_money\": 100000\n" +
      "  }\n" +
      "}";

    WxCpApprovalDetailResult result = WxCpGsonBuilder.create().fromJson(jsonWithSumMoney, WxCpApprovalDetailResult.class);
    assertThat(result).isNotNull();
    assertThat(result.getErrCode()).isEqualTo(0);
    assertThat(result.getInfo()).isNotNull();
    assertThat(result.getInfo().getSpNo()).isEqualTo("202601140001");
    assertThat(result.getInfo().getSpName()).isEqualTo("报销申请");
    assertThat(result.getInfo().getSumMoney()).isNotNull();
    assertThat(result.getInfo().getSumMoney()).isEqualTo(100000L);

    System.out.println("成功解析总费用金额字段 sum_money: " + result.getInfo().getSumMoney());

    // 测试不包含 sum_money 字段的情况（向后兼容）
    String jsonWithoutSumMoney = "{\n" +
      "  \"errcode\": 0,\n" +
      "  \"errmsg\": \"ok\",\n" +
      "  \"info\": {\n" +
      "    \"sp_no\": \"202601140002\",\n" +
      "    \"sp_name\": \"请假申请\",\n" +
      "    \"sp_status\": 1,\n" +
      "    \"template_id\": \"test_template_id\",\n" +
      "    \"apply_time\": 1610000000,\n" +
      "    \"applyer\": {\n" +
      "      \"userid\": \"test_user\",\n" +
      "      \"partyid\": \"1\"\n" +
      "    },\n" +
      "    \"sp_record\": [],\n" +
      "    \"notifyer\": [],\n" +
      "    \"apply_data\": {\n" +
      "      \"contents\": []\n" +
      "    },\n" +
      "    \"comments\": []\n" +
      "  }\n" +
      "}";

    WxCpApprovalDetailResult resultWithoutMoney = WxCpGsonBuilder.create().fromJson(jsonWithoutSumMoney, WxCpApprovalDetailResult.class);
    assertThat(resultWithoutMoney).isNotNull();
    assertThat(resultWithoutMoney.getInfo()).isNotNull();
    assertThat(resultWithoutMoney.getInfo().getSpNo()).isEqualTo("202601140002");
    assertThat(resultWithoutMoney.getInfo().getSumMoney()).isNull();

    System.out.println("成功处理不包含 sum_money 字段的情况（向后兼容）");
    System.out.println("完整测试通过！");
  }

  /**
   * Test get template detail.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetTemplateDetail() throws WxErrorException {

    String json = "{\"errcode\":0,\"errmsg\":\"ok\",\"template_names\":[{\"text\":\"销售用章申请-CIC测试\",\"lang\":\"zh_CN\"}],\"template_content\":{\"controls\":[{\"property\":{\"control\":\"Text\",\"id\":\"Text-1642064119106\",\"title\":[{\"text\":\"甲方全称\",\"lang\":\"zh_CN\"}],\"placeholder\":[{\"text\":\"请输入\",\"lang\":\"zh_CN\"}],\"require\":1,\"un_print\":0,\"inner_id\":\"\",\"un_replace\":0,\"display\":1}},{\"property\":{\"control\":\"Selector\",\"id\":\"Selector-1641521155746\",\"title\":[{\"text\":\"用章公司\",\"lang\":\"zh_CN\"}],\"placeholder\":[{\"text\":\"请选择\",\"lang\":\"zh_CN\"}],\"require\":1,\"un_print\":0,\"inner_id\":\"\",\"un_replace\":0,\"display\":1},\"config\":{\"selector\":{\"type\":\"single\",\"options\":[{\"key\":\"option-1641521155746\",\"value\":[{\"text\":\"有限公司\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1703845381898\",\"value\":[{\"text\":\"有限公司\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1643277806277\",\"value\":[{\"text\":\"有限公司\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1641521181119\",\"value\":[{\"text\":\"有限公司\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1641521191559\",\"value\":[{\"text\":\"有限公司\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1641521216515\",\"value\":[{\"text\":\"有限公司\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1650417735718\",\"value\":[{\"text\":\"有限公司\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1652756795298\",\"value\":[{\"text\":\"有限公司\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1664422448363\",\"value\":[{\"text\":\"有限公司\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1673487035814\",\"value\":[{\"text\":\"有限公司\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1675128722320\",\"value\":[{\"text\":\"事务所\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1678071926146\",\"value\":[{\"text\":\"有限公司\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1678071927225\",\"value\":[{\"text\":\"有限公司\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1703845339862\",\"value\":[{\"text\":\"有限公司\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1703845330660\",\"value\":[{\"text\":\"有限公司\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1684459670059\",\"value\":[{\"text\":\"有限公司\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1698111016115\",\"value\":[{\"text\":\"有限公司\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1705559650950\",\"value\":[{\"text\":\"有限公司\",\"lang\":\"zh_CN\"}]}],\"op_relations\":[]}}},{\"property\":{\"control\":\"Text\",\"id\":\"Text-1641521297125\",\"title\":[{\"text\":\"渠道来源\",\"lang\":\"zh_CN\"}],\"placeholder\":[{\"text\":\"请输入\",\"lang\":\"zh_CN\"}],\"require\":1,\"un_print\":0,\"inner_id\":\"\",\"un_replace\":0,\"display\":1}},{\"property\":{\"control\":\"Selector\",\"id\":\"Selector-1641521316173\",\"title\":[{\"text\":\"印章类型\",\"lang\":\"zh_CN\"}],\"placeholder\":[{\"text\":\"请选择\",\"lang\":\"zh_CN\"}],\"require\":1,\"un_print\":0,\"inner_id\":\"\",\"un_replace\":0,\"display\":1},\"config\":{\"selector\":{\"type\":\"single\",\"options\":[{\"key\":\"option-1641521316173\",\"value\":[{\"text\":\"公章\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1641521316174\",\"value\":[{\"text\":\"业务章\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1641521339762\",\"value\":[{\"text\":\"法人章\",\"lang\":\"zh_CN\"}]}],\"op_relations\":[]}}},{\"property\":{\"control\":\"Selector\",\"id\":\"Selector-1641521355432\",\"title\":[{\"text\":\"是否外带\",\"lang\":\"zh_CN\"}],\"placeholder\":[{\"text\":\"请选择\",\"lang\":\"zh_CN\"}],\"require\":1,\"un_print\":0,\"inner_id\":\"\",\"un_replace\":0,\"display\":1},\"config\":{\"selector\":{\"type\":\"single\",\"options\":[{\"key\":\"option-1641521355432\",\"value\":[{\"text\":\"否\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1641521355433\",\"value\":[{\"text\":\"是\",\"lang\":\"zh_CN\"}]}],\"op_relations\":[]}}},{\"property\":{\"control\":\"Selector\",\"id\":\"Selector-1648619603087\",\"title\":[{\"text\":\"盖章形式\",\"lang\":\"zh_CN\"}],\"placeholder\":[{\"text\":\"请选择\",\"lang\":\"zh_CN\"}],\"require\":1,\"un_print\":0,\"inner_id\":\"\",\"un_replace\":0,\"display\":1},\"config\":{\"selector\":{\"type\":\"single\",\"options\":[{\"key\":\"option-1648619603087\",\"value\":[{\"text\":\"电子合同章\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1648619603088\",\"value\":[{\"text\":\"纸质合同章\",\"lang\":\"zh_CN\"}]}],\"op_relations\":[]}}},{\"property\":{\"control\":\"Textarea\",\"id\":\"Textarea-1641521378351\",\"title\":[{\"text\":\"用印事由\",\"lang\":\"zh_CN\"}],\"placeholder\":[{\"text\":\"请输入\",\"lang\":\"zh_CN\"}],\"require\":1,\"un_print\":0,\"inner_id\":\"\",\"un_replace\":0,\"display\":1}},{\"property\":{\"control\":\"Date\",\"id\":\"Date-1641521411373\",\"title\":[{\"text\":\"借用时间\",\"lang\":\"zh_CN\"}],\"placeholder\":[{\"text\":\"请选择\",\"lang\":\"zh_CN\"}],\"require\":0,\"un_print\":0,\"inner_id\":\"\",\"un_replace\":0,\"display\":1},\"config\":{\"date\":{\"type\":\"hour\"}}},{\"property\":{\"control\":\"Date\",\"id\":\"Date-1641521421730\",\"title\":[{\"text\":\"归还时间\",\"lang\":\"zh_CN\"}],\"placeholder\":[{\"text\":\"请选择\",\"lang\":\"zh_CN\"}],\"require\":0,\"un_print\":0,\"inner_id\":\"\",\"un_replace\":0,\"display\":1},\"config\":{\"date\":{\"type\":\"hour\"}}},{\"property\":{\"control\":\"Selector\",\"id\":\"Selector-1641521441251\",\"title\":[{\"text\":\"文件类型\",\"lang\":\"zh_CN\"}],\"placeholder\":[{\"text\":\"请选择\",\"lang\":\"zh_CN\"}],\"require\":1,\"un_print\":0,\"inner_id\":\"\",\"un_replace\":0,\"display\":1},\"config\":{\"selector\":{\"type\":\"single\",\"options\":[{\"key\":\"option-1641521441251\",\"value\":[{\"text\":\"业务合同\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1643278221491\",\"value\":[{\"text\":\"人事行政类文件\",\"lang\":\"zh_CN\"}]},{\"key\":\"option-1641521534238\",\"value\":[{\"text\":\"经纪人、商事服务合作合同\",\"lang\":\"zh_CN\"}]}],\"op_relations\":[]}}},{\"property\":{\"control\":\"Text\",\"id\":\"Text-1641521571559\",\"title\":[{\"text\":\"文件名称\",\"lang\":\"zh_CN\"}],\"placeholder\":[{\"text\":\"请输入\",\"lang\":\"zh_CN\"}],\"require\":1,\"un_print\":0,\"inner_id\":\"\",\"un_replace\":0,\"display\":1}},{\"property\":{\"control\":\"Text\",\"id\":\"Text-1641521587698\",\"title\":[{\"text\":\"文件份数\",\"lang\":\"zh_CN\"}],\"placeholder\":[{\"text\":\"请输入整数\",\"lang\":\"zh_CN\"}],\"require\":1,\"un_print\":0,\"inner_id\":\"\",\"un_replace\":0,\"display\":1}},{\"property\":{\"control\":\"Date\",\"id\":\"Date-1641521607834\",\"title\":[{\"text\":\"用印日期\",\"lang\":\"zh_CN\"}],\"placeholder\":[{\"text\":\"请选择\",\"lang\":\"zh_CN\"}],\"require\":1,\"un_print\":0,\"inner_id\":\"\",\"un_replace\":0,\"display\":1},\"config\":{\"date\":{\"type\":\"day\"}}},{\"property\":{\"control\":\"File\",\"id\":\"File-1641521617014\",\"title\":[{\"text\":\"附件\",\"lang\":\"zh_CN\"}],\"placeholder\":[{\"text\":\"一定要上传所盖章原件，以附件内容为主\",\"lang\":\"zh_CN\"}],\"require\":1,\"un_print\":0,\"inner_id\":\"\",\"un_replace\":0,\"display\":1},\"config\":{\"file\":{\"is_only_photo\":0}}}]}}";
    WxCpOaApprovalTemplateResult oaApprovalTemplateResult = WxCpOaApprovalTemplateResult.fromJson(json);
    System.out.println("模板信息：" + oaApprovalTemplateResult.toJson());

    String templateId = "3TkZjxugodbqpEMk9j7X6h6zKqYkc7MxQrrFmT7H";
    WxCpOaApprovalTemplateResult result = wxService.getOaService().getTemplateDetail(templateId);
    assertThat(result).isNotNull();
    System.out.println("result ");
    System.out.println(gson.toJson(result));
  }

  /**
   * Test apply.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testApply() throws WxErrorException {
    this.wxService.getOaService().apply(new WxCpOaApplyEventRequest().setCreatorUserId("123"));
  }

  /**
   * 获取审批数据（旧）
   * https://developer.work.weixin.qq.com/document/path/91530
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetApprovalData() throws WxErrorException {

    // 提示：推荐使用新接口“批量获取审批单号”及“获取审批申请详情”，此接口后续将不再维护、逐步下线。
//    WxCpGetApprovalData approvalData = this.wxService.getOaService().getApprovalData(System.currentTimeMillis(),
//    System.currentTimeMillis() + 3600L, null);
//    log.info("返回数据：{}", approvalData.toJson());

    String text = "{\"errcode\":0,\"errmsg\":\"ok\",\"count\":3,\"total\":5,\"next_spnum\":201704240001," +
      "\"data\":[{\"spname\":\"报销\",\"apply_name\":\"报销测试\",\"apply_org\":\"报销测试企业\",\"approval_name\":[\"审批人测试\"]," +
      "\"notify_name\":[\"抄送人测试\"],\"sp_status\":1,\"sp_num\":201704200001," +
      "\"mediaids\":[\"WWCISP_G8PYgRaOVHjXWUWFqchpBqqqUpGj0OyR9z6WTwhnMZGCPHxyviVstiv_2fTG8YOJq8L8zJT2T2OvTebANV-2MQ" +
      "\"],\"apply_time\":1499153693,\"apply_user_id\":\"testuser\",\"expense\":{\"expense_type\":1,\"reason\":\"\"," +
      "\"item\":[{\"expenseitem_type\":6,\"time\":1492617600,\"sums\":9900,\"reason\":\"\"}]}," +
      "\"comm\":{\"apply_data\":\"{\\\"item-1492610773696\\\":{\\\"title\\\":\\\"abc\\\",\\\"type\\\":\\\"text\\\"," +
      "\\\"value\\\":\\\"\\\"}}\"}},{\"spname\":\"请假\",\"apply_name\":\"请假测试\",\"apply_org\":\"请假测试企业\"," +
      "\"approval_name\":[\"审批人测试\"],\"notify_name\":[\"抄送人测试\"],\"sp_status\":1,\"sp_num\":201704200004," +
      "\"apply_time\":1499153693,\"apply_user_id\":\"testuser\",\"leave\":{\"timeunit\":0,\"leave_type\":4," +
      "\"start_time\":1492099200,\"end_time\":1492790400,\"duration\":144,\"reason\":\"\"}," +
      "\"comm\":{\"apply_data\":\"{\\\"item-1492610773696\\\":{\\\"title\\\":\\\"abc\\\",\\\"type\\\":\\\"text\\\"," +
      "\\\"value\\\":\\\"\\\"}}\"}},{\"spname\":\"自定义审批\",\"apply_name\":\"自定义\",\"apply_org\":\"自定义测试企业\"," +
      "\"approval_name\":[\"自定义审批人\"],\"notify_name\":[\"自定义抄送人\"],\"sp_status\":1,\"sp_num\":201704240001," +
      "\"apply_time\":1499153693,\"apply_user_id\":\"testuser\"," +
      "\"comm\":{\"apply_data\":\"{\\\"item-1492610773696\\\":{\\\"title\\\":\\\"abc\\\",\\\"type\\\":\\\"text\\\"," +
      "\\\"value\\\":\\\"\\\"}}\"}}]}";
    WxCpGetApprovalData wxCpGetApprovalData = WxCpGetApprovalData.fromJson(text);
    log.info("返回数据2：{}", wxCpGetApprovalData.toJson());

  }

  /**
   * Test get dial record.
   */
  @Test
  public void testGetDialRecord() {
  }

  /**
   * 获取企业假期管理配置
   * https://developer.work.weixin.qq.com/document/path/93375
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetCorpConf() throws WxErrorException {
    WxCpCorpConfInfo corpConf = this.wxService.getOaService().getCorpConf();
    log.info(corpConf.toJson());
  }

  /**
   * 获取成员假期余额
   * https://developer.work.weixin.qq.com/document/path/93376
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetUserVacationQuota() throws WxErrorException {
    WxCpUserVacationQuota vacationQuota = this.wxService.getOaService().getUserVacationQuota("WangKai");
    log.info(vacationQuota.toJson());

    String text =
      "{\"errcode\":0,\"errmsg\":\"ok\",\"lists\":[{\"id\":1,\"assignduration\":0,\"usedduration\":0," +
        "\"leftduration\":604800,\"vacationname\":\"年假\"},{\"id\":2,\"assignduration\":0,\"usedduration\":0," +
        "\"leftduration\":1296000,\"vacationname\":\"事假\"},{\"id\":3,\"assignduration\":0,\"usedduration\":0," +
        "\"leftduration\":0,\"vacationname\":\"病假\"}]}";
    WxCpUserVacationQuota json = WxCpUserVacationQuota.fromJson(text);
    log.info("数据为：{}", json.toJson());

  }

  /**
   * 修改成员假期余额
   * https://developer.work.weixin.qq.com/document/path/93377
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testSetOneUserQuota() throws WxErrorException {

    String text = "{\"errcode\":0,\"errmsg\":\"ok\"}";
    WxCpBaseResp resp = WxCpBaseResp.fromJson(text);
    log.info("返回结果为：{}", resp.toJson());

//    WxCpBaseResp wxCpBaseResp = this.wxService.getOaService().setOneUserQuota(, , , , );

  }

  /**
   * 创建审批模板
   * https://developer.work.weixin.qq.com/document/path/97437
   */
  @Test
  public void testCreateOaApprovalTemplate() {
    //TODO
    String json = "{\n" +
      "    \"template_name\": [{\n" +
      "        \"text\": \"我的api测试模版\",\n" +
      "        \"lang\": \"zh_CN\"\n" +
      "    }],\n" +
      "    \"template_content\": {\n" +
      "        \"controls\": [{\n" +
      "            \"property\": {\n" +
      "                \"control\": \"Selector\",\n" +
      "                \"id\": \"Selector-01\",\n" +
      "                \"title\": [{\n" +
      "                    \"text\": \"控件名称\",\n" +
      "                    \"lang\": \"zh_CN\"\n" +
      "                }],\n" +
      "                \"placeholder\": [{\n" +
      "                    \"text\": \"控件说明\",\n" +
      "                    \"lang\": \"zh_CN\"\n" +
      "                }],\n" +
      "                \"require\": 0,\n" +
      "                \"un_print\": 1\n" +
      "            },\n" +
      "            \"config\":{\n" +
      "                \"selector\": {\n" +
      "                \"type\": \"multi\",\n" +
      "                \"options\": [\n" +
      "                    {\n" +
      "                        \"key\": \"option-1\", \n" +
      "                        \"value\":{\n" +
      "                            \"text\":\"选项1\",\n" +
      "                            \"lang\":\"zh_CN\"\n" +
      "                        }\n" +
      "                    },\n" +
      "                    {\n" +
      "                        \"key\": \"option-2\",\n" +
      "                        \"value\":{\n" +
      "                            \"text\":\"选项2\",\n" +
      "                            \"lang\":\"zh_CN\"\n" +
      "                        }\n" +
      "                    }\n" +
      "                ]\n" +
      "                }\n" +
      "            }\n" +
      "        }]\n" +
      "    }\n" +
      "}";
    WxCpOaApprovalTemplate createTemplate = WxCpOaApprovalTemplate.fromJson(json);
    System.out.println("create_template数据为：" + createTemplate.toJson());

  }

  @Test
  public void testUpdateOaApprovalTemplate() {
    //TODO
  }

  @Test
  public void testGetCheckinScheduleList() {
    //TODO
  }

  @Test
  public void testAddCheckInUserFace() {
    //TODO
  }
}
