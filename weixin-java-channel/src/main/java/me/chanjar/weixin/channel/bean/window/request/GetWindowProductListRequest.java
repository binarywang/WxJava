package me.chanjar.weixin.channel.bean.window.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取账号收集的留资数据详情
 * @author imyzt
 * @date 2024/01/27
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.window.request.GetWindowProductListRequest}。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class GetWindowProductListRequest {

  /**
   * 用于指定查询某个店铺来源的商品
   */
  @JsonProperty("appid")
  private String appid;

  /**
   * 用于指定查询属于某个分店ID下的商品
   */
  @JsonProperty("branch_id")
  private int branchId;

  /**
   * 单页商品数（不超过200）
   */
  @JsonProperty("page_size")
  private int pageSize;

  /**
   * 页面下标，下标从1开始，默认为1
   */
  @JsonProperty("page_index")
  private int pageIndex;

  /**
   * 由上次请求返回，顺序翻页时需要传入，会从上次返回的结果往后翻一页（填了该值后page_index不生效）
   */
  @JsonProperty("last_buffer")
  private String lastBuffer;

  /**
   * 是否需要返回满足筛选条件的商品总数
   */
  @JsonProperty("need_total_num")
  private int needTotalNum;

}
