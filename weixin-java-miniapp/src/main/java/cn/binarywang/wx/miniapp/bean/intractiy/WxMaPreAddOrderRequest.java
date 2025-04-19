package cn.binarywang.wx.miniapp.bean.intractiy;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class WxMaPreAddOrderRequest extends BasicWxMaOrder {
  private Cargo cargo;

  public Cargo getCargo() {
    return cargo;
  }

  public void setCargo(Cargo cargo) {
    this.cargo = cargo;
  }

  public static class Cargo extends BasicWxMaOrder.Cargo {}

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
