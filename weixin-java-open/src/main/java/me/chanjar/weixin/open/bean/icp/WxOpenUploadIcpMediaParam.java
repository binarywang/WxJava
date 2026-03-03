package me.chanjar.weixin.open.bean.icp;

import java.io.Serializable;
import java.util.*;

import com.google.gson.annotations.SerializedName;

import lombok.*;
import me.chanjar.weixin.common.bean.CommonUploadData;
import me.chanjar.weixin.open.bean.CommonUploadMultiParam;
import me.chanjar.weixin.common.bean.CommonUploadParam;


/**
 * @author xzh
 * @Description 文件上传
 * @createTime 2024/08/14 10:52
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxOpenUploadIcpMediaParam implements Serializable {

  private static final long serialVersionUID = -9082174509776922969L;

  /**
   * 媒体材料类型。目前支持两种：图片("image")和视频("video")，示例值："image"
   */
  @SerializedName("type")
  private String type;

  /**
   * 证件类型(参考：获取证件类型)，如果上传的是证件媒体材料，则必填，示例值：2
   */
  @SerializedName("certificate_type")
  private Integer certificateType;

  /**
   * 媒体材料所属的备案字段名(参考：申请小程序备案接口)，如要用于多个备案字段，则填写其中一个字段名即可。
   * 例如：要上传身份证头像面照片作为备案主体负责人和小程序负责人的证件照正面， 就填写
   * "icp_subject.principal_info.certificate_photo_front"
   */
  @SerializedName("icp_order_field")
  private String icpOrderField;

  /**
   * 待上传的图片或视频
   */
  private CommonUploadData media;


  public CommonUploadMultiParam toCommonUploadMultiParam() {
    return CommonUploadMultiParam.builder()
      .normalParams(Arrays.asList(
        CommonUploadMultiParam.NormalParam.builder().name("type").value(type).build(),
        CommonUploadMultiParam.NormalParam.builder().name("certificate_type").value(String.valueOf(certificateType)).build(),
        CommonUploadMultiParam.NormalParam.builder().name("icp_order_field").value(icpOrderField).build()
      ))
      .uploadParam(new CommonUploadParam("media", media))
      .build();
  }

}
