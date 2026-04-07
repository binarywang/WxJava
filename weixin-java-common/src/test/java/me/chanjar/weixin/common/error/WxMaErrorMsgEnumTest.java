package me.chanjar.weixin.common.error;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

/**
 * 微信小程序错误码枚举测试
 *
 * @author GitHub Copilot
 */
@Test
public class WxMaErrorMsgEnumTest {

  public void testFindMsgByCodeForExistingCode() {
    String msg = WxMaErrorMsgEnum.findMsgByCode(40001);
    assertNotNull(msg);
  }

  public void testFindMsgByCodeForNonExistingCode() {
    String msg = WxMaErrorMsgEnum.findMsgByCode(999999);
    assertNull(msg);
  }

  /**
   * 验证微信小程序虚拟支付错误码
   */
  public void testVirtualPaymentErrorCodes() {
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490001), "openid错误");
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490002), "请求参数字段错误，具体看errmsg");
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490003), "签名错误");
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490004), "重复操作（赠送和代币支付和充值广告金相关接口会返回，表示之前的操作已经成功）");
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490005), "订单已经通过cancel_currency_pay接口退款，不支持再退款");
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490006), "代币的退款/支付操作金额不足");
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490007), "图片或文字存在敏感内容，禁止使用");
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490008), "代币未发布，不允许进行代币操作");
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490009), "用户session_key不存在或已过期，请重新登录");
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490011), "数据生成中，请稍后调用本接口获取");
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490012), "批量任务运行中，请等待完成后才能再次运行");
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490013), "禁止对核销状态的单进行退款");
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490014), "退款操作进行中，稍后可以使用相同参数重试");
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490015), "频率限制");
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490016), "退款的left_fee字段与实际不符，请通过query_order接口查询确认");
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490018), "广告金充值账户行业id不匹配");
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490019), "广告金充值账户id已绑定其他appid");
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490020), "广告金充值账户主体名称错误");
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490021), "账户未完成进件");
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490022), "广告金充值账户无效");
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490023), "广告金余额不足");
    assertEquals(WxMaErrorMsgEnum.findMsgByCode(268490024), "广告金充值金额必须大于0");
  }

  /**
   * 验证虚拟支付错误码中不存在的编号（如268490010、268490017）返回null
   */
  public void testVirtualPaymentMissingCodes() {
    assertNull(WxMaErrorMsgEnum.findMsgByCode(268490010));
    assertNull(WxMaErrorMsgEnum.findMsgByCode(268490017));
  }
}
