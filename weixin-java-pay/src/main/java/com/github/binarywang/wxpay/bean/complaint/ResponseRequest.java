package com.github.binarywang.wxpay.bean.complaint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 微信消费者投诉2.0
 * 提交回复请求实体
 *
 * @author <a href="https://gitee.com/jeequan/jeepay">jmdhappy</a>
 * created on  2022-3-19
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRequest implements Serializable {

  private static final long serialVersionUID = 3244929701614220801L;

  /**
   * <pre>
   * 字段名：投诉单号
   * 是否必填：是
   * 描述：投诉单对应的投诉单号
   * </pre>
   */
  @SerializedName("complaint_id")
  @Expose
  private String complaintId;

  /**
   * <pre>
   * 字段名：被诉商户号
   * 是否必填：是
   * 描述：投诉单对应的被诉商户号
   * </pre>
   */
  @SerializedName("complainted_mchid")
  private String complaintedMchid;

  /**
   * <pre>
   * 字段名：回复内容
   * 是否必填：是
   * 描述：具体的投诉处理方案，限制200个字符以内。
   * </pre>
   */
  @SerializedName("response_content")
  private String responseContent;

  /**
   * <pre>
   * 字段名：回复图片
   * 是否必填：否
   * 描述：
   * 传入调用商户上传反馈图片接口返回的media_id，最多上传4张图片凭证
   * 示例值：file23578_21798531.jpg
   * </pre>
   */
  @SerializedName("response_images")
  private List<String> responseImages;

  /**
   * <pre>
   * 字段名：跳转链接
   * 是否必填：是
   * 描述：
   * 商户可在回复中附加跳转链接，引导用户跳转至商户客诉处理页面，链接需满足https格式
   * 注：配置文字链属于灰度功能, 若有需要请使用超管邮箱，按照要求发送邮件申请。邮件要求详情见：
   * 商户申请开通留言链接白名单指南。
   * 示例值：https://www.xxx.com/notify
   * </pre>
   */
  @SerializedName("jump_url")
  private String jumpUrl;

  /**
   * <pre>
   * 字段名：跳转链接文案
   * 是否必填：否
   * 描述：
   * 实际展示给用户的文案，附在回复内容之后。用户点击文案，即可进行跳转。
   * 注:若传入跳转链接，则跳转链接文案为必传项，二者缺一不可。
   * </pre>
   */
  @SerializedName("jump_url_text")
  private String jumpUrlText;

  /**
   * <pre>
   * 字段名：跳转小程序信息
   * 是否必填：否
   * 描述：商户可在回复中附加小程序信息，引导用户跳转至商户客诉处理小程序。
   * 注：配置小程序属于灰度功能，若有需要请联系对接的行业运营进行咨询。
   * </pre>
   */
  @SerializedName("mini_program_jump_info")
  private MiniProgramJumpInfo miniProgramJumpInfo;


  /**
   * 跳转小程序信息
   */
  @Data
  public static class MiniProgramJumpInfo implements Serializable {
    private static final long serialVersionUID = 1169503275787468380L;

    /**
     * <pre>
     * 字段名：跳转小程序APPID
     * 是否必填：是
     * 描述：商户可在回复中附加小程序页面路径，引导用户跳转至商户服务工具页面。
     *      该字段为小程序APPID。
     * </pre>
     */
    @SerializedName("appid")
    private String appId;

    /**
     * <pre>
     * 字段名：跳转小程序页面PATH
     * 是否必填：是
     * 描述：商户可在回复中附加小程序页面路径，引导用户跳转至商户服务工具页面。
     *      该字段为小程序路径。
     * </pre>
     */
    @SerializedName("path")
    private String path;

    /**
     * <pre>
     * 字段名：跳转小程序页面名称
     * 是否必填：是
     * 描述：商户可在回复中附加小程序页面路径，引导用户跳转至商户服务工具页面。
     *      该字段为商户可自定义的页面名称。
     * </pre>
     */
    @SerializedName("text")
    private String text;
  }

}
