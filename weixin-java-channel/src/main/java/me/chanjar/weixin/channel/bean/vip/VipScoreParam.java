package me.chanjar.weixin.channel.bean.vip;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : zhenyun.su
 * @since : 2023/10/8
 * {
 *   "openid": "OPENID",
 *   "score": "100",
 *   "remark": "备注",
 *   "request_id": "REQUEST_ID"
 * }
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.vip.VipScoreParam}。
 */

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Deprecated
public class VipScoreParam implements Serializable {
  private static final long serialVersionUID = -4122983978977407168L;
  @JsonProperty("openid")
  private String openId;

  @JsonProperty("score")
  private String score;

  @JsonProperty("remark")
  private String remark;

  @JsonProperty("request_id")
  private String requestId;
}
