package me.chanjar.weixin.channel.bean.message.fund;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 提现二维码回调 消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.message.fund.QrNotifyInfo}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class QrNotifyInfo implements Serializable {

  private static final long serialVersionUID = 2470016408300157273L;
  /** 二维码ticket */
  @JsonProperty("ticket")
  @JacksonXmlProperty(localName = "ticket")
  private String ticket;

  /** 二维码状态,1.已确认 2.已取消 3.已失效 4.已扫码 */
  @JsonProperty("status")
  @JacksonXmlProperty(localName = "status")
  private Integer status;

  /** 扫码者身份, 0.非管理员 1.管理员 */
  @JsonProperty("scan_user_type")
  @JacksonXmlProperty(localName = "scan_user_type")
  private Integer scanUserType;

}
