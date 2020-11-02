package com.binarywang.spring.starter.wxjava.cp.handler;

import com.binarywang.spring.starter.wxjava.cp.builder.TextBuilder;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * 通讯录变更事件处理器.
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class ContactChangeHandler extends AbstractHandler {


  @Override
  public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService cpService,
                                  WxSessionManager sessionManager) {
    String content = "收到通讯录变更事件，内容：" + wxMessage.toString();
    //this.logger.info(content);
    return new TextBuilder().build(content, wxMessage, cpService);
  }

}
