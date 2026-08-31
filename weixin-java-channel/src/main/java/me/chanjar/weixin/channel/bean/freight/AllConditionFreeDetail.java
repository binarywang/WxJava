package me.chanjar.weixin.channel.bean.freight;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 计费规则列表
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.freight.AllConditionFreeDetail}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class AllConditionFreeDetail implements Serializable {

  private static final long serialVersionUID = -1649520737632417036L;
  /** 计费规则列表 */
  @JsonProperty("condition_free_detail_list")
  private List<ConditionFreeDetail> list;

  @JsonIgnore
  public void addDetail(ConditionFreeDetail detail) {
    if (list == null) {
      list = new ArrayList<>(16);
    }
    list.add(detail);
  }
}
