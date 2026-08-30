package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 售后详情
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class AfterSaleDetail implements Serializable {

  private static final long serialVersionUID = -8130659179770831047L;
  /** 售后描述 */
  @JsonProperty("desc")
  private String desc;

  /** 是否已经收到货 */
  @JsonProperty("receive_product")
  private Boolean receiveProduct;

  /** 是否已经收到货 */
  @JsonProperty("cancel_time")
  private Long cancelTime;

  /** 举证图片media_id列表，根据mediaid获取文件内容接口 */
  @JsonProperty("prove_imgs")
  private List<String> proveImgs;

  /** 联系电话 */
  @JsonProperty("tel_number")
  private String telNumber;

  /** 举证图片media_id列表，根据mediaid获取文件内容接口 */
  @JsonProperty("media_id_list")
  private List<String> mediaIdList;
}
