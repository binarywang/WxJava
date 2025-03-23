package com.github.binarywang.wxpay.bean.bank;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 个人业务的银行列表
 *
 * @author zhongjun
 **/
@Data
public class BankingResult implements Serializable {
  private static final long serialVersionUID = -8372812998971715894L;

  /**
   * 银行列表数据的总条数，调用方需要根据总条数分页查询
   */
  @SerializedName("total_count")
  private Integer totalCount;

  /**
   * 本次查询银行列表返回的数据条数
   */
  @SerializedName("count")
  private Integer count;

  /**
   * 该次请求资源的起始位置，请求中包含偏移量时应答消息返回相同偏移量，否则返回默认值0。
   */
  @SerializedName("offset")
  private Integer offset;

  @SerializedName("data")
  private List<BankInfo> data;

  @SerializedName("links")
  private Link links;

  @Getter
  @Setter
  public static class Link implements Serializable {

    private static final long serialVersionUID = -8372812998971715894L;

    /**
     * 下一页链接
     */
    @SerializedName("next")
    private String next;
    /**
     * 上一页链接
     */
    @SerializedName("prev")
    private String prev;
    /**
     * 当前链接
     */
    @SerializedName("self")
    private String self;
  }

}
