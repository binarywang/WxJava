package me.chanjar.weixin.channel.bean.complaint;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 纠纷单留言
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class ComplaintParam implements Serializable {

  private static final long serialVersionUID = 6146118590005718327L;
  /** 纠纷单号 */
  @JsonProperty("complaint_id")
  private String complaintId;

  /** 留言内容，最多500字 */
  @JsonProperty("content")
  private String content;

  /** 图片media_id列表，所有留言总图片数量最多20张 */
  @JsonProperty("media_id_list")
  private List<String> mediaIds;
}
