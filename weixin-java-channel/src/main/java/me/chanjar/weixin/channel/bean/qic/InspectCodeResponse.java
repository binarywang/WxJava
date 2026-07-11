package me.chanjar.weixin.channel.bean.qic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InspectCodeResponse extends WxChannelBaseResponse {
  private static final long serialVersionUID = -6242555695898612990L;

  private DataPayload data;

  @Data
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class DataPayload implements Serializable {
    private static final long serialVersionUID = 684071509005627272L;

    @JsonProperty("backupDeliveryId")
    private String backupDeliveryId;

    @JsonProperty("backupDeliveryName")
    private String backupDeliveryName;

    @JsonProperty("boxDTOList")
    private List<BoxInfo> boxInfoList;

    @JsonProperty("channelAppId")
    private String channelAppId;

    @JsonProperty("deliveryId")
    private String deliveryId;

    @JsonProperty("deliveryName")
    private String deliveryName;

    @JsonProperty("embedGoodsMaterial")
    private String embedGoodsMaterial;

    @JsonProperty("goodsDesc")
    private String goodsDesc;

    @JsonProperty("expressMerge")
    private Boolean expressMerge;

    @JsonProperty("goodsMainMaterial")
    private String goodsMainMaterial;

    @JsonProperty("goodsName")
    private String goodsName;

    @JsonProperty("goodsNum")
    private Integer goodsNum;

    @JsonProperty("goodsPartsMaterial")
    private String goodsPartsMaterial;

    @JsonProperty("inspectBaseId")
    private String inspectBaseId;

    @JsonProperty("inspectBaseName")
    private String inspectBaseName;

    @JsonProperty("inspectCode")
    private String inspectCode;

    @JsonProperty("inspectOrgId")
    private String inspectOrgId;

    @JsonProperty("inspectOrgName")
    private String inspectOrgName;

    @JsonProperty("inspectOrgShortName")
    private String inspectOrgShortName;

    @JsonProperty("merchantName")
    private String merchantName;

    @JsonProperty("orderId")
    private String orderId;

    @JsonProperty("urgentOrder")
    private Boolean urgentOrder;

    @JsonProperty("printInfo")
    private String printInfo;

    @JsonProperty("needLabel")
    private Boolean needLabel;
  }

  @Data
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class BoxInfo implements Serializable {
    private static final long serialVersionUID = 4074623844069371776L;

    @JsonProperty("boxId")
    private Long boxId;

    @JsonProperty("boxName")
    private String boxName;

    @JsonProperty("boxNum")
    private Integer boxNum;
  }
}
