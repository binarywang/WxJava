package cn.binarywang.wx.miniapp.bean.vod;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxMaVodDramaInfo implements Serializable {
  private static final long serialVersionUID = -7663757440028175135L;

  private String name;
  private String producer;
  private String playwright;

  @SerializedName("drama_id")
  private Integer dramaId;
  @SerializedName("create_time")
  private Long createTime;
  @SerializedName("cover_url")
  private String coverUrl;
  @SerializedName("media_count")
  private Long mediaCount;
  @SerializedName("expedited")
  private Long expedited;
  @SerializedName("production_license")
  private String productionLicense;
  @SerializedName("description")
  private String description;
  @SerializedName("status")
  private String status;

  @SerializedName("audit_detail")
  private DramaAuditDetail auditDetail;
  @SerializedName("media_list")
  private List<DramaMediaInfo> mediaList;

  @Data
  public static class DramaAuditDetail {

    @SerializedName("status")
    private Integer status;
    @SerializedName("create_time")
    private Long createTime;
    @SerializedName("audit_time")
    private Long auditTime;
    @SerializedName("reason")
    private String reason;
  }

  @Data
  public static class DramaMediaInfo {

    @SerializedName("media_id")
    private Integer mediaId;


  }
}
