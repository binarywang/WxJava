package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建买赠活动参数
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftActivityAddParam implements Serializable {

  private static final long serialVersionUID = -3332952823917162308L;

  @JsonProperty("gift_activity")
  private GiftActivityInfo giftActivity;
}
