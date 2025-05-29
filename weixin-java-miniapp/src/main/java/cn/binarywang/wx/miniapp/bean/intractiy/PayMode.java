package cn.binarywang.wx.miniapp.bean.intractiy;

import com.google.gson.annotations.SerializedName;

/** 充值、扣费主体 */
public enum PayMode {
  /** 门店 */
  @SerializedName("PAY_MODE_STORE")
  STORE,
  /** 小程序 */
  @SerializedName("PAY_MODE_APP")
  APP,
  /** 服务商 */
  @SerializedName("PAY_MODE_COMPONENT")
  COMPONENT;
}
