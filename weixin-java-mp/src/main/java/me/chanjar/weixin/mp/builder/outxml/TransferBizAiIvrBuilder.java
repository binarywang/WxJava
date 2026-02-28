package me.chanjar.weixin.mp.builder.outxml;

import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTransferBizAiIvrMessage;

/**
 * 转接AI回复消息builder.
 * <pre>
 * 用法: WxMpXmlOutTransferBizAiIvrMessage m = WxMpXmlOutMessage.TRANSFER_BIZ_AI_IVR().toUser("").fromUser("").build();
 * </pre>
 *
 * @author copilot
 */
public final class TransferBizAiIvrBuilder
  extends BaseBuilder<TransferBizAiIvrBuilder, WxMpXmlOutTransferBizAiIvrMessage> {

  @Override
  public WxMpXmlOutTransferBizAiIvrMessage build() {
    WxMpXmlOutTransferBizAiIvrMessage m = new WxMpXmlOutTransferBizAiIvrMessage();
    setCommon(m);
    return m;
  }
}
