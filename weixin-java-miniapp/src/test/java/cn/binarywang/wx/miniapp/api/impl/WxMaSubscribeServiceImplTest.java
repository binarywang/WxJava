package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaGetUserNotifyRequest;
import cn.binarywang.wx.miniapp.bean.WxMaGetUserNotifyResult;
import cn.binarywang.wx.miniapp.bean.WxMaServiceNotifyExtRequest;
import cn.binarywang.wx.miniapp.bean.WxMaServiceNotifyRequest;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import me.chanjar.weixin.common.bean.subscribemsg.CategoryData;
import me.chanjar.weixin.common.bean.subscribemsg.PubTemplateKeyword;
import me.chanjar.weixin.common.bean.subscribemsg.TemplateInfo;
import me.chanjar.weixin.common.bean.subscribemsg.PubTemplateTitleListResult;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2019-12-15
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaSubscribeServiceImplTest {
  @Inject
  protected WxMaService wxService;

  @Test
  public void testGetPubTemplateTitleList() throws WxErrorException {
    PubTemplateTitleListResult result = this.wxService.getSubscribeService().getPubTemplateTitleList(new String[]{"2", "616"}, 0, 30);
    System.out.println(result);

  }

  @Test
  public void testGetPubTemplateKeyWordsById() throws WxErrorException {
    final List<PubTemplateKeyword> result = this.wxService.getSubscribeService().getPubTemplateKeyWordsById("99");
    System.out.println(result);
  }

  @Test
  public void testAddTemplate() throws WxErrorException {
    final String templateId = this.wxService.getSubscribeService().addTemplate("401", Lists.newArrayList(1, 2), "测试数据");
    System.out.println(templateId);
  }

  @Test
  public void testGetTemplateList() throws WxErrorException {
    final List<TemplateInfo> templateList = this.wxService.getSubscribeService().getTemplateList();
    System.out.println(templateList);
  }

  @Test
  public void testDelTemplate() throws WxErrorException {
    this.wxService.getSubscribeService().delTemplate("priTmplId");
  }

  @Test
  public void testGetCategory() throws WxErrorException {
    final List<CategoryData> categoryData = this.wxService.getSubscribeService().getCategory();
    assertThat(categoryData).isNotNull();
    System.out.println(categoryData);
  }

  @Test
  public void testSendSubscribeMsg() throws WxErrorException {
    // TODO 待完善补充
    this.wxService.getSubscribeService().sendSubscribeMsg(WxMaSubscribeMessage.builder().build());
  }

  @Test
  public void testSetUserNotify() throws WxErrorException {
    // TODO 待完善补充，需要真实的 openid、notify_type、notify_code、content_json 参数
    WxMaServiceNotifyRequest request = WxMaServiceNotifyRequest.builder()
      .openid("test_openid")
      .notifyType(1)
      .notifyCode("test_notify_code")
      .contentJson("{}")
      .build();
    this.wxService.getSubscribeService().setUserNotify(request);
  }

  @Test
  public void testSetUserNotifyExt() throws WxErrorException {
    // TODO 待完善补充，需要真实的 openid、notify_type、notify_code、ext_json 参数
    WxMaServiceNotifyExtRequest request = WxMaServiceNotifyExtRequest.builder()
      .openid("test_openid")
      .notifyType(1)
      .notifyCode("test_notify_code")
      .extJson("{}")
      .build();
    this.wxService.getSubscribeService().setUserNotifyExt(request);
  }

  @Test
  public void testGetUserNotify() throws WxErrorException {
    // TODO 待完善补充，需要真实的 openid、notify_type、notify_code 参数
    WxMaGetUserNotifyRequest request = WxMaGetUserNotifyRequest.builder()
      .openid("test_openid")
      .notifyCode("test_notify_code")
      .notifyType(1)
      .build();
    WxMaGetUserNotifyResult result = this.wxService.getSubscribeService().getUserNotify(request);
    assertThat(result).isNotNull();
    System.out.println(result);
  }
}
