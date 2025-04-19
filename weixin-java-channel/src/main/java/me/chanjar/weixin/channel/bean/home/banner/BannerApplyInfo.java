package me.chanjar.weixin.channel.bean.home.banner;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 精选展示位申请信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class BannerApplyInfo implements Serializable {

  private static final long serialVersionUID = 72190625450999960L;

  /** 申请编号 */
  @JsonProperty("apply_id")
  private Integer applyId;

  /** 申请状态 1-审核中；2-审核驳回 */
  @JsonProperty("state")
  private Integer state;

  /** 展示位的展示样式 1-小图模式；2-大图模式 */
  @JsonProperty("scale")
  private Integer scale;

  /** 精选展示位申请明细 */
  @JsonProperty("banner")
  private List<BannerApplyDetail> banner;
}
