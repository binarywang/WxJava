package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaGroupEnterInfo;
import cn.binarywang.wx.miniapp.bean.WxMaShareInfo;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * 测试分享相关的接口
 *
 * @author zhfish
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaShareServiceImplTest {

  @Inject
  private WxMaService wxService;

  @Test
  public void testGetSessionKey() throws Exception {
    assertNotNull(this.wxService.getUserService().getSessionInfo("aaa"));
  }

  /**
   * TODO 测试数据有问题，需要替换为正确的数据
   */
  @Test
  public void testGetShareInfo() {
    WxMaShareInfo shareInfo = this.wxService.getShareService().getShareInfo("tiihtNczf5v6AKRyjwEUhQ==",
      "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZMQmRzooG2xrDcvSnxIMXFufNstNGTyaGS9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+3hVbJSRgv+4lGOETKUQz6OYStslQ142dNCuabNPGBzlooOmB231qMM85d2/fV6ChevvXvQP8Hkue1poOFtnEtpyxVLW1zAo6/1Xx1COxFvrc2d7UL/lmHInNlxuacJXwu0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn/Hz7saL8xz+W//FRAUid1OksQaQx4CMs8LOddcQhULW4ucetDf96JcR3g0gfRK4PC7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns/8wR2SiRS7MNACwTyrGvt9ts8p12PKFdlqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYVoKlaRv85IfVunYzO0IKXsyl7JCUjCpoG20f0a04COwfneQAGGwd5oa+T8yO5hzuyDb/XcxxmK01EpqOyuxINew==",
      "r7BXXKkLb8qrSNn05n0qiA==");
    assertNotNull(shareInfo);
    System.out.println(shareInfo.toString());
  }

  /**
   * TODO 测试数据有问题，需要替换为正确的数据
   */
  @Test
  public void testGetGroupEnterInfo() {
    WxMaGroupEnterInfo groupEnterInfo = this.wxService.getShareService().getGroupEnterInfo("tiihtNczf5v6AKRyjwEUhQ==",
      "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZMQmRzooG2xrDcvSnxIMXFufNstNGTyaGS9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+3hVbJSRgv+4lGOETKUQz6OYStslQ142dNCuabNPGBzlooOmB231qMM85d2/fV6ChevvXvQP8Hkue1poOFtnEtpyxVLW1zAo6/1Xx1COxFvrc2d7UL/lmHInNlxuacJXwu0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn/Hz7saL8xz+W//FRAUid1OksQaQx4CMs8LOddcQhULW4ucetDf96JcR3g0gfRK4PC7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns/8wR2SiRS7MNACwTyrGvt9ts8p12PKFdlqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYVoKlaRv85IfVunYzO0IKXsyl7JCUjCpoG20f0a04COwfneQAGGwd5oa+T8yO5hzuyDb/XcxxmK01EpqOyuxINew==",
      "r7BXXKkLb8qrSNn05n0qiA==");
    assertNotNull(groupEnterInfo);
    System.out.println(groupEnterInfo.toString());
  }
}
