package cn.binarywang.wx.miniapp.demo;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import cn.binarywang.wx.miniapp.message.WxMaMessageRouter;
import cn.binarywang.wx.miniapp.message.WxMaOutMessage;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@AllArgsConstructor
public class WxMaPortalServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private WxMaConfig config;
  private WxMaService service;
  private WxMaMessageRouter messageRouter;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
    response.setContentType("text/html;charset=utf-8");
    response.setStatus(HttpServletResponse.SC_OK);

    String signature = request.getParameter("signature");
    String nonce = request.getParameter("nonce");
    String timestamp = request.getParameter("timestamp");

    if (!this.service.checkSignature(timestamp, nonce, signature)) {
      // 消息签名不正确，说明不是公众平台发过来的消息
      response.getWriter().println("非法请求");
      return;
    }

    String echoStr = request.getParameter("echostr");
    if (StringUtils.isNotBlank(echoStr)) {
      // 说明是一个仅仅用来验证的请求，回显echostr
      response.getWriter().println(echoStr);
      return;
    }

    String encryptType = request.getParameter("encrypt_type");
    final boolean isJson = Objects.equals(this.config.getMsgDataFormat(), WxMaConstants.MsgDataFormat.JSON);
    if (StringUtils.isBlank(encryptType)) {
      // 明文传输的消息
      WxMaMessage inMessage;
      if (isJson) {
        inMessage = WxMaMessage.fromJson(IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8));
      } else {//xml
        inMessage = WxMaMessage.fromXml(request.getInputStream());
      }

      final WxMaOutMessage outMessage = this.messageRouter.route(inMessage);
      if (outMessage != null) {
        if (isJson) {
          response.getWriter().write(outMessage.toJson());
        } else {
          response.getWriter().write(outMessage.toXml());
        }
        return;
      }

      response.getWriter().write("success");
      return;
    }

    if ("aes".equals(encryptType)) {
      // 是aes加密的消息
      String msgSignature = request.getParameter("msg_signature");
      WxMaMessage inMessage;
      if (isJson) {
        inMessage = WxMaMessage.fromEncryptedJson(request.getInputStream(), this.config);
      } else {//xml
        inMessage = WxMaMessage.fromEncryptedXml(request.getInputStream(), this.config, timestamp, nonce, msgSignature);
      }

      final WxMaOutMessage outMessage = this.messageRouter.route(inMessage);
      if (outMessage != null) {
        if (isJson) {
          response.getWriter().write(outMessage.toEncryptedJson(this.config));
        } else {
          response.getWriter().write(outMessage.toEncryptedXml(this.config));
        }
        return;
      }
      response.getWriter().write("success");
      return;
    }

    response.getWriter().println("不可识别的加密类型");
  }

}
