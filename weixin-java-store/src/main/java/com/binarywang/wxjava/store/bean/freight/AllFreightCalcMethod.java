package com.binarywang.wxjava.store.bean.freight;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * 具体计费方法，默认运费，指定地区运费等
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
public class AllFreightCalcMethod implements Serializable {

  private static final long serialVersionUID = 6330919525271991949L;
  /** 计算方法列表 */
  @JsonProperty("freight_calc_method_list")
  private List<FreightCalcMethod> list;

  public AllFreightCalcMethod() {
  }

  public void addDetail(FreightCalcMethod detail) {
    if (list == null) {
      list = new ArrayList<>(16);
    }
    list.add(detail);
  }
}
