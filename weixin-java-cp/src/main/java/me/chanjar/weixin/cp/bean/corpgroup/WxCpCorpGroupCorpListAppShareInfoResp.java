package me.chanjar.weixin.cp.bean.corpgroup;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 获取应用共享信息返回类
 * @author libo
 */
@Data
public class WxCpCorpGroupCorpListAppShareInfoResp implements Serializable {
  private static final long serialVersionUID = 7165788382879237583L;
  @SerializedName("ending")
  private int ending;
  @SerializedName("corp_list")
  private List<WxCpCorpGroupCorp> corpList;
  @SerializedName("next_cursor")
  private String nextCursor;
}
