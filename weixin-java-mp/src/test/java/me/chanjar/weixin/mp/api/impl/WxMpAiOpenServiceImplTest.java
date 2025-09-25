package me.chanjar.weixin.mp.api.impl;

import java.io.File;

import org.testng.annotations.*;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.enums.AiLangType;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <pre>
 *  Created by BinaryWang on 2018/6/10.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpAiOpenServiceImplTest {
  @Inject
  protected WxMpService wxService;

  @Test
  public void testUploadVoice() throws WxErrorException {
    String voiceId = System.currentTimeMillis() + "a";
    AiLangType lang = AiLangType.zh_CN;
    this.wxService.getAiOpenService().uploadVoice(voiceId, lang, new File("d:\\t.mp3"));
  }

  @Test
  public void testRecogniseVoice() throws WxErrorException {
    String voiceId = System.currentTimeMillis() + "a";
    AiLangType lang = AiLangType.zh_CN;
    final String result = this.wxService.getAiOpenService().recogniseVoice(voiceId, lang, new File("d:\\t.mp3"));
    assertThat(result).isNotEmpty();
  }

  @Test
  public void testTranslate() throws WxErrorException {
    final String result = this.wxService.getAiOpenService().translate(AiLangType.zh_CN, AiLangType.en_US, "微信文档很坑爹");
    assertThat(result).isNotEmpty();
  }
}
