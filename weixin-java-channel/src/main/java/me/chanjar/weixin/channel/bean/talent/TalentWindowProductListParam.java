package me.chanjar.weixin.channel.bean.talent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 带货助手-获取达人橱窗商品列表 请求参数
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class TalentWindowProductListParam implements Serializable {

  private static final long serialVersionUID = 7419836250174638291L;

  /** 单页商品数（不超过500） */
  @JsonProperty("page_size")
  private Integer pageSize;

  /** 页面下标，下标从1开始（不可以与 lastBuffer 一起填写） */
  @JsonProperty("page_index")
  private Integer pageIndex;

  /** 由上次请求返回，顺序翻页时需要传入（不可以与 pageIndex 一起填写） */
  @JsonProperty("last_buffer")
  private String lastBuffer;
}
