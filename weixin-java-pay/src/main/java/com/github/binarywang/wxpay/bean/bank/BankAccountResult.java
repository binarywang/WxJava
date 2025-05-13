package com.github.binarywang.wxpay.bean.bank;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 对私银行卡号开户银行信息
 *
 * @author zhongjun
 **/
@Data
public class BankAccountResult implements Serializable {

  private static final long serialVersionUID = -8226859146533243501L;

  /**
   * 根据卡号查询到的银行列表数据的总条数，未查询到对应银行列表时默认返回0，最大不超过两百条。
   */
  @SerializedName("total_count")
  private Integer totalCount;

  @SerializedName("data")
  private List<BankInfo> data;

}
