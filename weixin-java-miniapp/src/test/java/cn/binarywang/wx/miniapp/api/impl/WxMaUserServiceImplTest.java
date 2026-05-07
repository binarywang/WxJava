package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import cn.binarywang.wx.miniapp.test.TestConfig;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * 测试用户相关的接口
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaUserServiceImplTest {

  @Inject
  private WxMaService wxService;

  @Test
  public void testGetSessionKey() throws Exception {
    assertNotNull(this.wxService.getUserService().getSessionInfo("aaa"));
  }

  @Test
  public void testGetUserInfo() {
    WxMaUserInfo userInfo = this.wxService.getUserService().getUserInfo("tiihtNczf5v6AKRyjwEUhQ==",
      "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZMQmRzooG2xrDcvSnxIMXFufNstNGTyaGS9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+3hVbJSRgv+4lGOETKUQz6OYStslQ142dNCuabNPGBzlooOmB231qMM85d2/fV6ChevvXvQP8Hkue1poOFtnEtpyxVLW1zAo6/1Xx1COxFvrc2d7UL/lmHInNlxuacJXwu0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn/Hz7saL8xz+W//FRAUid1OksQaQx4CMs8LOddcQhULW4ucetDf96JcR3g0gfRK4PC7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns/8wR2SiRS7MNACwTyrGvt9ts8p12PKFdlqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYVoKlaRv85IfVunYzO0IKXsyl7JCUjCpoG20f0a04COwfneQAGGwd5oa+T8yO5hzuyDb/XcxxmK01EpqOyuxINew==",
      "r7BXXKkLb8qrSNn05n0qiA==");
    assertNotNull(userInfo);
    System.out.println(userInfo.toString());
  }

  @Test
  public void testCheckUserInfo() {
    assertTrue(this.wxService.getUserService().checkUserInfo("HyVFkGl5F5OQWJZZaNzBBg==",
      "{\"nickName\":\"Band\",\"gender\":1,\"language\":\"zh_CN\",\"city\":\"Guangzhou\",\"province\":\"Guangdong\",\"country\":\"CN\",\"avatarUrl\":\"http://wx.qlogo.cn/mmopen/vi_32/1vZvI39NWFQ9XM4LtQpFrQJ1xlgZxx3w7bQxKARol6503Iuswjjn6nIGBiaycAjAtpujxyzYsrztuuICqIM5ibXQ/0\"}",
      "75e81ceda165f4ffa64f4068af58c64b8f54b88c"));
  }


  @Test
  public void testGetPhoneNoInfo() {
    WxMaPhoneNumberInfo phoneNoInfo = this.wxService.getUserService().getPhoneNoInfo("tiihtNczf5v6AKRyjwEUhQ==",
      "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZMQmRzooG2xrDcvSnxIMXFufNstNGTyaGS9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+3hVbJSRgv+4lGOETKUQz6OYStslQ142dNCuabNPGBzlooOmB231qMM85d2/fV6ChevvXvQP8Hkue1poOFtnEtpyxVLW1zAo6/1Xx1COxFvrc2d7UL/lmHInNlxuacJXwu0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn/Hz7saL8xz+W//FRAUid1OksQaQx4CMs8LOddcQhULW4ucetDf96JcR3g0gfRK4PC7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns/8wR2SiRS7MNACwTyrGvt9ts8p12PKFdlqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYVoKlaRv85IfVunYzO0IKXsyl7JCUjCpoG20f0a04COwfneQAGGwd5oa+T8yO5hzuyDb/XcxxmK01EpqOyuxINew==",
      "r7BXXKkLb8qrSNn05n0qiA==");
    assertNotNull(phoneNoInfo);
    System.out.println(phoneNoInfo.toString());
  }

  @Test
  public void testGetPhoneInfo() throws WxErrorException {
    WxMaPhoneNumberInfo phoneNoInfo = this.wxService.getUserService().getPhoneNumber("tiihtNczf5v6AKRyjwEUhQ==");
    assertNotNull(phoneNoInfo);
    System.out.println(phoneNoInfo.toString());
  }

  @Test
  public void testGetSessionInfo() {
  }

  /**
   *  TODO 测试数据有问题，需要替换为正确的数据
   */
  @Test
  public void testSetUserStorage() throws WxErrorException {
    this.wxService.getUserService().setUserStorage(ImmutableMap.of("1","2"),
      "r7BXXKkLb8qrSNn05n0qiA",((TestConfig)this.wxService.getWxMaConfig()).getOpenid());
  }

  @Test
  public void testGetAccessToken() throws Exception{
    assertNotNull(wxService.getAccessToken(true));
  }

  @Test(expectedExceptions = WxErrorException.class)
  public void testCheckSessionKey() throws WxErrorException {
    // 使用无效的 openid 和 sessionKey，预期微信服务端返回错误并抛出 WxErrorException
    this.wxService.getUserService().checkSessionKey("invalid_openid", "invalid_session_key");
  }
}
