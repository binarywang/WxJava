package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.realname.RealNameRequest;
import com.github.binarywang.wxpay.bean.realname.RealNameResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * <pre>
 *  实名验证测试类.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
@Slf4j
public class RealNameServiceImplTest {

  @Inject
  private WxPayService payService;

  /**
   * 测试查询用户实名认证信息.
   *
   * @throws WxPayException the wx pay exception
   */
  @Test
  public void testQueryRealName() throws WxPayException {
    RealNameRequest request = RealNameRequest.newBuilder()
      .openid("oUpF8uMuAJO_M2pxb1Q9zNjWeS6o")
      .build();

    RealNameResult result = this.payService.getRealNameService().queryRealName(request);
    log.info("实名认证查询结果：{}", result);
  }

  /**
   * 测试查询用户实名认证信息（简化方法）.
   *
   * @throws WxPayException the wx pay exception
   */
  @Test
  public void testQueryRealNameSimple() throws WxPayException {
    RealNameResult result = this.payService.getRealNameService()
      .queryRealName("oUpF8uMuAJO_M2pxb1Q9zNjWeS6o");
    log.info("实名认证查询结果：{}", result);
  }
}
