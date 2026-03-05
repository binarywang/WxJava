package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.face.WxMaFaceGetVerifyIdRequest;
import cn.binarywang.wx.miniapp.bean.face.WxMaFaceGetVerifyIdResponse;
import cn.binarywang.wx.miniapp.bean.face.WxMaFaceQueryVerifyInfoRequest;
import cn.binarywang.wx.miniapp.bean.face.WxMaFaceQueryVerifyInfoResponse;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * 微信小程序人脸核身服务测试类
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaFaceServiceImplTest {

  @Inject
  private WxMaService wxService;

  @Test
  public void testGetVerifyId() throws WxErrorException {
    WxMaFaceGetVerifyIdRequest request = WxMaFaceGetVerifyIdRequest.builder()
      .outSeqNo("TEST20240101001")
      .certInfo(WxMaFaceGetVerifyIdRequest.CertInfo.builder()
        .certType("IDENTITY_CARD")
        .certName("张三")
        .certNo("310101199801011234")
        .build())
      .openid("test_openid_001")
      .build();

    WxMaFaceGetVerifyIdResponse response = this.wxService.getFaceService().getVerifyId(request);
    assertNotNull(response);
  }

  @Test
  public void testQueryVerifyInfo() throws WxErrorException {
    String certType = "IDENTITY_CARD";
    String certName = "张三";
    String certNo = "310101199801011234";
    String certHash = WxMaFaceServiceImpl.calcCertHash(certType, certName, certNo);

    WxMaFaceQueryVerifyInfoRequest request = WxMaFaceQueryVerifyInfoRequest.builder()
      .verifyId("test_verify_id_001")
      .outSeqNo("TEST20240101001")
      .certHash(certHash)
      .openid("test_openid_001")
      .build();

    WxMaFaceQueryVerifyInfoResponse response = this.wxService.getFaceService().queryVerifyInfo(request);
    assertNotNull(response);
  }

  @Test
  public void testCalcCertHash() {
    // 验证官方文档给出的测试用例：
    // cert_info: {"cert_type":"IDENTITY_CARD","cert_name":"张三","cert_no":"310101199801011234"}
    // 期望结果：3c241f7ff324977aeb91f173bb2a7b06569e6fd784d5573db34a636d8671108b
    String certHash = WxMaFaceServiceImpl.calcCertHash("IDENTITY_CARD", "张三", "310101199801011234");
    assertEquals(certHash, "3c241f7ff324977aeb91f173bb2a7b06569e6fd784d5573db34a636d8671108b");
  }
}
