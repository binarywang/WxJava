package com.github.binarywang.wxpay.bean.applyment;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WxPayApplyment4SubCreateRequestTest {

  @Test
  public void testMicroBizInfoSerialization() {
    // 1. Test MicroStoreInfo
    WxPayApplyment4SubCreateRequest.SubjectInfo.MicroBizInfo.MicroStoreInfo storeInfo =
      WxPayApplyment4SubCreateRequest.SubjectInfo.MicroBizInfo.MicroStoreInfo.builder()
        .microName("门店名称")
        .microAddressCode("440305")
        .microAddress("详细地址")
        .storeEntrancePic("media_id_1")
        .microIndoorCopy("media_id_2")
        .storeLongitude("113.941046")
        .storeLatitude("22.546154")
        .build();

    // 2. Test MicroMobileInfo
    WxPayApplyment4SubCreateRequest.SubjectInfo.MicroBizInfo.MicroMobileInfo mobileInfo =
      WxPayApplyment4SubCreateRequest.SubjectInfo.MicroBizInfo.MicroMobileInfo.builder()
        .microMobileName("流动经营名称")
        .microMobileCity("440305")
        .microMobileAddress("无")
        .microMobilePics(Arrays.asList("media_id_3", "media_id_4"))
        .build();

    // 3. Test MicroOnlineInfo
    WxPayApplyment4SubCreateRequest.SubjectInfo.MicroBizInfo.MicroOnlineInfo onlineInfo =
      WxPayApplyment4SubCreateRequest.SubjectInfo.MicroBizInfo.MicroOnlineInfo.builder()
        .microOnlineStore("线上店铺名称")
        .microEcName("电商平台名称")
        .microQrcode("media_id_5")
        .microLink("https://www.example.com")
        .build();

    WxPayApplyment4SubCreateRequest.SubjectInfo.MicroBizInfo microBizInfo =
      WxPayApplyment4SubCreateRequest.SubjectInfo.MicroBizInfo.builder()
        .microStoreInfo(storeInfo)
        .microMobileInfo(mobileInfo)
        .microOnlineInfo(onlineInfo)
        .build();

    Gson gson = new GsonBuilder().create();
    String json = gson.toJson(microBizInfo);

    // Verify MicroStoreInfo serialization
    assertTrue(json.contains("\"micro_name\":\"门店名称\""));
    assertTrue(json.contains("\"micro_address_code\":\"440305\""));
    assertTrue(json.contains("\"micro_address\":\"详细地址\""));
    assertTrue(json.contains("\"store_entrance_pic\":\"media_id_1\""));
    assertTrue(json.contains("\"micro_indoor_copy\":\"media_id_2\""));
    assertTrue(json.contains("\"store_longitude\":\"113.941046\""));
    assertTrue(json.contains("\"store_latitude\":\"22.546154\""));

    // Verify MicroMobileInfo serialization
    assertTrue(json.contains("\"micro_mobile_name\":\"流动经营名称\""));
    assertTrue(json.contains("\"micro_mobile_city\":\"440305\""));
    assertTrue(json.contains("\"micro_mobile_address\":\"无\""));
    assertTrue(json.contains("\"micro_mobile_pics\":[\"media_id_3\",\"media_id_4\"]"));

    // Verify MicroOnlineInfo serialization
    assertTrue(json.contains("\"micro_online_store\":\"线上店铺名称\""));
    assertTrue(json.contains("\"micro_ec_name\":\"电商平台名称\""));
    assertTrue(json.contains("\"micro_qrcode\":\"media_id_5\""));
    assertTrue(json.contains("\"micro_link\":\"https://www.example.com\""));

    // Verify deserialization
    WxPayApplyment4SubCreateRequest.SubjectInfo.MicroBizInfo deserialized =
      gson.fromJson(json, WxPayApplyment4SubCreateRequest.SubjectInfo.MicroBizInfo.class);

    // Verify MicroStoreInfo deserialization
    assertEquals(deserialized.getMicroStoreInfo().getMicroName(), "门店名称");
    assertEquals(deserialized.getMicroStoreInfo().getMicroAddressCode(), "440305");
    assertEquals(deserialized.getMicroStoreInfo().getMicroAddress(), "详细地址");
    assertEquals(deserialized.getMicroStoreInfo().getStoreEntrancePic(), "media_id_1");
    assertEquals(deserialized.getMicroStoreInfo().getMicroIndoorCopy(), "media_id_2");
    assertEquals(deserialized.getMicroStoreInfo().getStoreLongitude(), "113.941046");
    assertEquals(deserialized.getMicroStoreInfo().getStoreLatitude(), "22.546154");

    // Verify MicroMobileInfo deserialization
    assertEquals(deserialized.getMicroMobileInfo().getMicroMobileName(), "流动经营名称");
    assertEquals(deserialized.getMicroMobileInfo().getMicroMobileCity(), "440305");
    assertEquals(deserialized.getMicroMobileInfo().getMicroMobileAddress(), "无");
    assertEquals(deserialized.getMicroMobileInfo().getMicroMobilePics().size(), 2);
    assertEquals(deserialized.getMicroMobileInfo().getMicroMobilePics(), Arrays.asList("media_id_3", "media_id_4"));

    // Verify MicroOnlineInfo deserialization
    assertEquals(deserialized.getMicroOnlineInfo().getMicroOnlineStore(), "线上店铺名称");
    assertEquals(deserialized.getMicroOnlineInfo().getMicroEcName(), "电商平台名称");
    assertEquals(deserialized.getMicroOnlineInfo().getMicroQrcode(), "media_id_5");
    assertEquals(deserialized.getMicroOnlineInfo().getMicroLink(), "https://www.example.com");
  }
}
