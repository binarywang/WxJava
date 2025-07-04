package com.github.binarywang.wxpay.bean.payscore;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2020-07-11
 */
public class WxPayScoreRequestTest {
  @Test
  public void testToJson() {
    WxPayScoreRequest request = WxPayScoreRequest.builder()
      .outOrderNo("QLS202005201058000201")
      .appid("123")
      .serviceId("345")
      .serviceIntroduction("租借服务")
      .timeRange(new TimeRange("20230901011023", "20230930235959","开始时间","结束时间"))
      .device(new Device("deviceId","deviceId","212323232"))
      .build();
    String json = request.toJson();
    System.out.println(json);

    String expectedJson = "{\"out_order_no\":\"QLS202005201058000201\",\"appid\":\"123\",\"service_id\":\"345\",\"service_introduction\":\"租借服务\",\"time_range\":{\"start_time\":\"20230901011023\",\"end_time\":\"20230930235959\",\"start_time_remark\":\"开始时间\",\"end_time_remark\":\"结束时间\"},\"device\":{\"start_device_id\":\"deviceId\",\"end_device_id\":\"deviceId\",\"materiel_no\":\"212323232\"}}";
    assertThat(request.toJson()).isEqualTo(expectedJson);
//    {
//      "out_order_no": "QLS202005201058000201",
//      "appid": "123",
//      "service_id": "345",
//      "service_introduction": "租借服务",
//      "time_range": {
//      "start_time": "20230901011023",
//        "end_time": "20230930235959",
//        "start_time_remark": "开始时间",
//        "end_time_remark": "结束时间"
//    },
//      "device": {
//      "start_device_id": "deviceId",
//        "end_device_id": "deviceId",
//        "materiel_no": "212323232"
//    }
//    }
  }
}
