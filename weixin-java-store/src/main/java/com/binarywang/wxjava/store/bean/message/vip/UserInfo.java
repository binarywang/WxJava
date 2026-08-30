package com.binarywang.wxjava.store.bean.message.vip;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author <a href="https://github.com/asushiye">asushiye</a>
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class UserInfo implements Serializable {

  private static final long serialVersionUID = 1239486732464880985L;
  /** 入会时间 **/
  @JsonProperty("join_time")
  @JacksonXmlProperty(localName = "join_time")
  private Long joinTime;

  /** 注销时间 **/
  @JsonProperty("close_time")
  @JacksonXmlProperty(localName = "close_time")
  private Long closeTime;

  /** 手机号 **/
  @JsonProperty("phone_number")
  @JacksonXmlProperty(localName = "phone_number")
  private String phoneNumber;

  /** 等级 **/
  @JsonProperty("grade")
  @JacksonXmlProperty(localName = "grade")
  private Integer grade;

  /** 当前等级经验值 **/
  @JsonProperty("experience_value")
  @JacksonXmlProperty(localName = "experience_value")
  private Long experienceValue;

  /** 当前积分 **/
  @JsonProperty("score")
  @JacksonXmlProperty(localName = "score")
  private Long score;

  /** 本次改动积分,负数减少，正数新增 **/
  @JsonProperty("delta_score")
  @JacksonXmlProperty(localName = "delta_score")
  private Long deltaScore;


}
