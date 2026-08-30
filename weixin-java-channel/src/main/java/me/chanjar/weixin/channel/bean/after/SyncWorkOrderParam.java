package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class SyncWorkOrderParam implements Serializable {
  private static final long serialVersionUID = -7336088606071452113L;

  @JsonProperty("complaint_id")
  private String complaintId;

  @JsonProperty("work_order_info")
  private WorkOrderInfo workOrderInfo;

  @Data
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class WorkOrderInfo implements Serializable {
    private static final long serialVersionUID = 8573016851280130766L;

    @JsonProperty("version")
    private Integer version;

    @JsonProperty("items")
    private List<WorkOrderItem> items;

    @JsonProperty("work_order_id")
    private String workOrderId;
  }

  @Data
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class WorkOrderItem implements Serializable {
    private static final long serialVersionUID = 6925580701152256736L;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("desc")
    private String desc;

    @JsonProperty("update_time")
    private Long updateTime;

    @JsonProperty("result_type")
    private Integer resultType;

    @JsonProperty("refund_amount")
    private Integer refundAmount;

    @JsonProperty("media_list")
    private List<WorkOrderMedia> mediaList;
  }

  @Data
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class WorkOrderMedia implements Serializable {
    private static final long serialVersionUID = 2258990333977395631L;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("picture")
    private WorkOrderPicture picture;
  }

  @Data
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class WorkOrderPicture implements Serializable {
    private static final long serialVersionUID = -3339842364541603289L;

    @JsonProperty("tmp_media_id")
    private String tmpMediaId;
  }
}
