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
 */

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Deprecated
public class VipListParam implements Serializable {

  private static final long serialVersionUID = 7503422865410116202L;
  @JsonProperty("need_phone_number")
  private Boolean needPhoneNumber;

  @JsonProperty("page_num")
  private Integer pageNum;

  @JsonProperty("page_size")
  private Integer pageSize;

}
