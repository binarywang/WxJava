package com.binarywang.wxjava.store.bean.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 店铺信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class ShopInfo implements Serializable {

  /** 店铺名称 */
  @JsonProperty("nickname")
  private String nickname;

  /** 店铺头像URL */
  @JsonProperty("headimg_url")
  private String headImgUrl;

  /** 店铺类型，目前为"企业"或"个体工商户" */
  @JsonProperty("subject_type")
  private String subjectType;

  /** 店铺状态，目前为 opening 或 open_finished 或 closing 或 close_finished */
  @JsonProperty("status")
  private String status;

  /** 店铺原始ID */
  @JsonProperty("username")
  private String username;
}
