package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.bean.face.WxMaFaceQueryVerifyInfoRequest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * 微信小程序人脸核身服务本地计算测试类
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Test
public class WxMaFaceServiceImplTest {

  @Test
  public void testCalcCertHash() {
    // 验证官方文档给出的测试用例：
    // cert_info: {"cert_type":"IDENTITY_CARD","cert_name":"张三","cert_no":"310101199801011234"}
    // 期望结果：3c241f7ff324977aeb91f173bb2a7b06569e6fd784d5573db34a636d8671108b
    String certHash = WxMaFaceQueryVerifyInfoRequest.calcCertHash(
      "IDENTITY_CARD", "张三", "310101199801011234");
    assertEquals(certHash, "3c241f7ff324977aeb91f173bb2a7b06569e6fd784d5573db34a636d8671108b");
  }
}
