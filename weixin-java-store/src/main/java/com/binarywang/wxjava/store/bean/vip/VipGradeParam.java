package com.binarywang.wxjava.store.bean.vip;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : zhenyun.su
 * @since : 2023/10/8

 */

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class VipGradeParam implements Serializable {


  private static final long serialVersionUID = 8672089025435220864L;
  @JsonProperty("openid")
  private String openId;

  @JsonProperty("grade")
  private Integer grade;

}
