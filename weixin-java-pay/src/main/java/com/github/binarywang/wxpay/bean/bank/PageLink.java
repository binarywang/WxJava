package com.github.binarywang.wxpay.bean.bank;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 支行列表
 *
 * @author hupeng
 **/
@Data
public class PageLink implements Serializable {

  private static final long serialVersionUID = -2624233403271204837L;

  /**
   * <pre>
   * 字段名：下一页链接
   * 变量名：next
   * 是否必填：否
   * 类型：string[1, 2048]
   * 描述：
   *  使用同样的limit进行下一页查询时的相对请求链接，使用方需要自行根据当前域名进行拼接。如果已经到最后时，为空
   *  示例值：/v3/capital/capitallhh/banks/1001/branches?offset=10&limit=5
   * </pre>
   */
  @SerializedName("next")
  private String next;

  /**
   * <pre>
   * 字段名：上一页链接
   * 变量名：prev
   * 是否必填：否
   * 类型：string[1, 2048]
   * 描述：
   *  使用同样的limit进行上一页查询时的相对请求链接，使用方需要自行根据当前域名进行拼接。如果是第一页，为空
   *  示例值：/v3/capital/capitallhh/banks/1001/branchesoffset=0&limit=5
   * </pre>
   */
  @SerializedName("prev")
  private String prev;

  /**
   * <pre>
   * 字段名：当前链接
   * 变量名：self
   * 是否必填：否
   * 类型：string[1, 2048]
   * 描述：
   *  当前的相对请求链接，使用方需要自行根据当前域名进行拼接
   *  示例值：/v3/capital/capitallhh/banks/1001/branches?offset=5&limit=5
   * </pre>
   */
  @SerializedName("self")
  private String self;
}
