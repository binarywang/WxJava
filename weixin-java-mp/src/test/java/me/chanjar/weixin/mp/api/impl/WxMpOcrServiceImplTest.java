package me.chanjar.weixin.mp.api.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.api.test.TestConstants;
import me.chanjar.weixin.common.bean.ocr.WxOcrBankCardResult;
import me.chanjar.weixin.common.bean.ocr.WxOcrBizLicenseResult;
import me.chanjar.weixin.common.bean.ocr.WxOcrCommResult;
import me.chanjar.weixin.common.bean.ocr.WxOcrDrivingLicenseResult;
import me.chanjar.weixin.common.bean.ocr.WxOcrDrivingResult;
import me.chanjar.weixin.common.bean.ocr.WxOcrIdCardResult;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 测试类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2019-06-22
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpOcrServiceImplTest {
  @Inject
  private WxMpService service;

  @Test
  public void testIdCard() throws WxErrorException {
    final WxOcrIdCardResult result = this.service.getOcrService().idCard(
      "https://res.wx.qq.com/op_res/E_oqdHqP4ETOJsT46sQnXz1HbeHOpqDQTuhkYeaLaJTf-JKld7de3091dwxCQwa6");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testIdCard2() throws Exception {
    InputStream inputStream = this.getImageStream("https://res.wx.qq.com/op_res/E_oqdHqP4ETOJsT46sQnXz1HbeHOpqDQTuhkYeaLaJTf-JKld7de3091dwxCQwa6");
    File tempFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), TestConstants.FILE_JPG);
    final WxOcrIdCardResult result = this.service.getOcrService().idCard(tempFile);
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testBankCard() throws WxErrorException {
    final WxOcrBankCardResult result = this.service.getOcrService().bankCard("https://res.wx.qq.com/op_res/eP7PObYbBJj-_19EbGBL4PWe_zQ1NwET5NXSugjEWc-4ayns4Q-HFJrp-AOog8ih");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testBankCard2() throws Exception {
    InputStream inputStream = this.getImageStream("https://res.wx.qq.com/op_res/eP7PObYbBJj-_19EbGBL4PWe_zQ1NwET5NXSugjEWc-4ayns4Q-HFJrp-AOog8ih");
    File tempFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), TestConstants.FILE_JPG);
    final WxOcrBankCardResult result = this.service.getOcrService().bankCard(tempFile);
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testDriving() throws WxErrorException {
    final WxOcrDrivingResult result = this.service.getOcrService().driving("https://res.wx.qq.com/op_res/T051P5uWvh9gSJ9j78tWib53WiNi2pHSSZhoO8wnY3Av-djpsA4kA9whbtt6_Tb6");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testDriving2() throws Exception {
    InputStream inputStream = ClassLoader.getSystemResourceAsStream("mm.jpeg");
    File tempFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), TestConstants.FILE_JPG);
    final WxOcrDrivingResult result = this.service.getOcrService().driving(tempFile);
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testDrivingLicense() throws WxErrorException {
    final WxOcrDrivingLicenseResult result = this.service.getOcrService().drivingLicense("https://res.wx.qq.com/op_res/kD4YXjYVAW1eaQqn9uTA0rrOFoZRvVINitNDSGo5gJ7SzTCezNq_ZDDmU1I08kGn");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testDrivingLicense2() throws Exception {
    InputStream inputStream = this.getImageStream("https://res.wx.qq.com/op_res/kD4YXjYVAW1eaQqn9uTA0rrOFoZRvVINitNDSGo5gJ7SzTCezNq_ZDDmU1I08kGn");
    File tempFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), TestConstants.FILE_JPG);
    final WxOcrDrivingLicenseResult result = this.service.getOcrService().drivingLicense(tempFile);
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testBizLicense() throws WxErrorException {
    final WxOcrBizLicenseResult result = this.service.getOcrService().bizLicense("https://res.wx.qq.com/op_res/apCy0YbnEdjYsa_cjW6x3FlpCc20uQ-2BYE7aXnFsrB-ALHZNgdKXhzIUcrRnDoL");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testBizLicense2() throws Exception {
    InputStream inputStream = ClassLoader.getSystemResourceAsStream("mm.jpeg");
    File tempFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), TestConstants.FILE_JPG);
    final WxOcrBizLicenseResult result = this.service.getOcrService().bizLicense(tempFile);
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testComm() throws WxErrorException {
    final WxOcrCommResult result = this.service.getOcrService().comm("https://res.wx.qq.com/op_res/apCy0YbnEdjYsa_cjW6x3FlpCc20uQ-2BYE7aXnFsrB-ALHZNgdKXhzIUcrRnDoL");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testComm2() throws Exception {
    InputStream inputStream = ClassLoader.getSystemResourceAsStream("mm.jpeg");
    File tempFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), TestConstants.FILE_JPG);
    final WxOcrCommResult result = this.service.getOcrService().comm(tempFile);
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  private InputStream getImageStream(String url) {
    try {
      HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
      connection.setReadTimeout(5000);
      connection.setConnectTimeout(5000);
      connection.setRequestMethod("GET");
      if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
        return connection.getInputStream();
      }
    } catch (IOException e) {
      System.out.println("获取网络图片出现异常，图片路径为：" + url);
    }
    return null;
  }

  public static class MockTest {
    private final WxMpService wxService = mock(WxMpService.class);

    @Test
    public void testIdCard() throws Exception {
      String returnJson = "{\"type\":\"Back\",\"name\":\"张三\",\"id\":\"110101199909090099\",\"valid_date\":\"20110101-20210201\"}";

      when(wxService.post(anyString(), anyString())).thenReturn(returnJson);
      final WxMpOcrServiceImpl wxMpOcrService = new WxMpOcrServiceImpl(wxService);

      final WxOcrIdCardResult result = wxMpOcrService.idCard("abc");
      assertThat(result).isNotNull();
      System.out.println(result);
    }

    @Test
    public void testBankCard() throws Exception {
      String returnJson = "{\"number\":\"24234234345234\"}";

      when(wxService.post(anyString(), anyString())).thenReturn(returnJson);
      final WxMpOcrServiceImpl wxMpOcrService = new WxMpOcrServiceImpl(wxService);

      final WxOcrBankCardResult result = wxMpOcrService.bankCard("abc");
      assertThat(result).isNotNull();
      System.out.println(result);
    }

    @Test
    public void testDriving() throws Exception {
      String returnJson = "{\n" +
        "    \"errcode\": 0,\n" +
        "    \"errmsg\": \"ok\",\n" +
        "    \"plate_num\": \"粤xxxxx\", //车牌号码\n" +
        "    \"vehicle_type\": \"小型普通客车\", //车辆类型\n" +
        "    \"owner\": \"东莞市xxxxx机械厂\", //所有人\n" +
        "    \"addr\": \"广东省东莞市xxxxx号\", //住址\n" +
        "    \"use_character\": \"非营运\", //使用性质\n" +
        "    \"model\": \"江淮牌HFCxxxxxxx\", //品牌型号\n" +
        "    \"vin\": \"LJ166xxxxxxxx51\", //车辆识别代号\n" +
        "    \"engine_num\": \"J3xxxxx3\", //发动机号码\n" +
        "    \"register_date\": \"2018-07-06\", //注册日期\n" +
        "    \"issue_date\": \"2018-07-01\", //发证日期\n" +
        "    \"plate_num_b\": \"粤xxxxx\", //车牌号码\n" +
        "    \"record\": \"441xxxxxx3\", //号牌\n" +
        "    \"passengers_num\": \"7人\", //核定载人数\n" +
        "    \"total_quality\": \"2700kg\", //总质量\n" +
        "    \"prepare_quality\": \"1995kg\", //整备质量\n" +
        "    \"overall_size\": \"4582x1795x1458mm\", //外廓尺寸\n" +
        "    \"card_position_front\": {//卡片正面位置（检测到卡片正面才会返回）\n" +
        "        \"pos\": {\n" +
        "            \"left_top\": {\n" +
        "                \"x\": 119, \n" +
        "                \"y\": 2925\n" +
        "            }, \n" +
        "            \"right_top\": {\n" +
        "                \"x\": 1435, \n" +
        "                \"y\": 2887\n" +
        "            }, \n" +
        "            \"right_bottom\": {\n" +
        "                \"x\": 1435, \n" +
        "                \"y\": 3793\n" +
        "            }, \n" +
        "            \"left_bottom\": {\n" +
        "                \"x\": 119, \n" +
        "                \"y\": 3831\n" +
        "            }\n" +
        "        }\n" +
        "    }, \n" +
        "    \"card_position_back\": {//卡片反面位置（检测到卡片反面才会返回）\n" +
        "        \"pos\": {\n" +
        "            \"left_top\": {\n" +
        "                \"x\": 1523, \n" +
        "                \"y\": 2849\n" +
        "            }, \n" +
        "            \"right_top\": {\n" +
        "                \"x\": 2898, \n" +
        "                \"y\": 2887\n" +
        "            }, \n" +
        "            \"right_bottom\": {\n" +
        "                \"x\": 2927, \n" +
        "                \"y\": 3831\n" +
        "            }, \n" +
        "            \"left_bottom\": {\n" +
        "                \"x\": 1523, \n" +
        "                \"y\": 3831\n" +
        "            }\n" +
        "        }\n" +
        "    }, \n" +
        "    \"img_size\": {//图片大小\n" +
        "        \"w\": 3120, \n" +
        "        \"h\": 4208\n" +
        "    }\n" +
        "}";

      when(wxService.post(anyString(), anyString())).thenReturn(returnJson);
      final WxMpOcrServiceImpl wxMpOcrService = new WxMpOcrServiceImpl(wxService);

      final WxOcrDrivingResult result = wxMpOcrService.driving("abc");
      assertThat(result).isNotNull();
      System.out.println(result);
    }

    @Test
    public void testDrivingLicense() throws Exception {
      String returnJson = "{\n" +
        "    \"errcode\": 0,\n" +
        "    \"errmsg\": \"ok\",\n" +
        "    \"id_num\": \"660601xxxxxxxx1234\", //证号\n" +
        "    \"name\": \"张三\", //姓名\n" +
        "    \"sex\": \"男\", //性别\n" +
        "    \"nationality\": \"中国\", //国籍\n" +
        "    \"address\": \"广东省东莞市xxxxx号\", //住址\n" +
        "    \"birth_date\": \"1990-12-21\", //出生日期\n" +
        "    \"issue_date\": \"2012-12-21\", //初次领证日期\n" +
        "    \"car_class\": \"C1\", //准驾车型\n" +
        "    \"valid_from\": \"2018-07-06\", //有效期限起始日\n" +
        "    \"valid_to\": \"2020-07-01\", //有效期限终止日\n" +
        "    \"official_seal\": \"xx市公安局公安交通管理局\" //印章文字\n" +
        "}";
      when(wxService.post(anyString(), anyString())).thenReturn(returnJson);
      final WxMpOcrServiceImpl wxMpOcrService = new WxMpOcrServiceImpl(wxService);

      final WxOcrDrivingLicenseResult result = wxMpOcrService.drivingLicense("abc");
      assertThat(result).isNotNull();
      System.out.println(result);
    }

    @Test
    public void testBizLicense() throws Exception {
      String returnJson = "{\n" +
        "    \"errcode\": 0, \n" +
        "    \"errmsg\": \"ok\", \n" +
        "    \"reg_num\": \"123123\",//注册号\n" +
        "    \"serial\": \"123123\",//编号\n" +
        "    \"legal_representative\": \"张三\", //法定代表人姓名\n" +
        "    \"enterprise_name\": \"XX饮食店\", //企业名称\n" +
        "    \"type_of_organization\": \"个人经营\", //组成形式\n" +
        "    \"address\": \"XX市XX区XX路XX号\", //经营场所/企业住所\n" +
        "    \"type_of_enterprise\": \"xxx\", //公司类型\n" +
        "    \"business_scope\": \"中型餐馆(不含凉菜、不含裱花蛋糕，不含生食海产品)。\", //经营范围\n" +
        "    \"registered_capital\": \"200万\", //注册资本\n" +
        "    \"paid_in_capital\": \"200万\", //实收资本\n" +
        "    \"valid_period\": \"2019年1月1日\", //营业期限\n" +
        "    \"registered_date\": \"2018年1月1日\", //注册日期/成立日期\n" +
        "    \"cert_position\": { //营业执照位置\n" +
        "        \"pos\": {\n" +
        "            \"left_top\": {\n" +
        "                \"x\": 155, \n" +
        "                \"y\": 191\n" +
        "            }, \n" +
        "            \"right_top\": {\n" +
        "                \"x\": 725, \n" +
        "                \"y\": 157\n" +
        "            }, \n" +
        "            \"right_bottom\": {\n" +
        "                \"x\": 743, \n" +
        "                \"y\": 512\n" +
        "            }, \n" +
        "            \"left_bottom\": {\n" +
        "                \"x\": 164, \n" +
        "                \"y\": 525\n" +
        "            }\n" +
        "        }\n" +
        "    }, \n" +
        "    \"img_size\": { //图片大小\n" +
        "        \"w\": 966, \n" +
        "        \"h\": 728\n" +
        "    }\n" +
        "}";
      when(wxService.post(anyString(), anyString())).thenReturn(returnJson);
      final WxMpOcrServiceImpl wxMpOcrService = new WxMpOcrServiceImpl(wxService);

      final WxOcrBizLicenseResult result = wxMpOcrService.bizLicense("abc");
      assertThat(result).isNotNull();
      System.out.println(result);
    }

    @Test
    public void testComm() throws Exception {
      String returnJson = "{\n" +
        "    \"errcode\": 0, \n" +
        "    \"errmsg\": \"ok\", \n" +
        "    \"items\": [ //识别结果\n" +
        "        {\n" +
        "            \"text\": \"腾讯\", \n" +
        "            \"pos\": {\n" +
        "                \"left_top\": {\n" +
        "                    \"x\": 575, \n" +
        "                    \"y\": 519\n" +
        "                }, \n" +
        "                \"right_top\": {\n" +
        "                    \"x\": 744, \n" +
        "                    \"y\": 519\n" +
        "                }, \n" +
        "                \"right_bottom\": {\n" +
        "                    \"x\": 744, \n" +
        "                    \"y\": 532\n" +
        "                }, \n" +
        "                \"left_bottom\": {\n" +
        "                    \"x\": 573, \n" +
        "                    \"y\": 532\n" +
        "                }\n" +
        "            }\n" +
        "        }, \n" +
        "        {\n" +
        "            \"text\": \"微信团队\", \n" +
        "            \"pos\": {\n" +
        "                \"left_top\": {\n" +
        "                    \"x\": 670, \n" +
        "                    \"y\": 516\n" +
        "                }, \n" +
        "                \"right_top\": {\n" +
        "                    \"x\": 762, \n" +
        "                    \"y\": 517\n" +
        "                }, \n" +
        "                \"right_bottom\": {\n" +
        "                    \"x\": 762, \n" +
        "                    \"y\": 532\n" +
        "                }, \n" +
        "                \"left_bottom\": {\n" +
        "                    \"x\": 670, \n" +
        "                    \"y\": 531\n" +
        "                }\n" +
        "            }\n" +
        "        }\n" +
        "    ], \n" +
        "    \"img_size\": { //图片大小\n" +
        "        \"w\": 1280, \n" +
        "        \"h\": 720\n" +
        "    }\n" +
        "}";
      when(wxService.post(anyString(), anyString())).thenReturn(returnJson);
      final WxMpOcrServiceImpl wxMpOcrService = new WxMpOcrServiceImpl(wxService);

      final WxOcrCommResult result = wxMpOcrService.comm("abc");
      assertThat(result).isNotNull();
      System.out.println(result);
    }
  }
}
