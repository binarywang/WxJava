package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.bean.oa.templatedata.TemplateTips;
import me.chanjar.weixin.cp.bean.oa.templatedata.TemplateTitle;
import me.chanjar.weixin.cp.bean.oa.templatedata.control.*;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 审批模板详情
 *
 * @author gyv12345 @163.com / Wang_Wong
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpOaApprovalTemplateResult implements Serializable {
  private static final long serialVersionUID = 6690547131189343887L;

  @SerializedName("errcode")
  private Integer errCode;

  @SerializedName("errmsg")
  private String errMsg;

  @SerializedName("template_names")
  private List<TemplateTitle> templateNames;

  @SerializedName("template_content")
  private TemplateContent templateContent;

  @Data
  public static class TemplateContent implements Serializable {
    private static final long serialVersionUID = -5640250983775840865L;

    private List<TemplateControls> controls;
  }

  @Data
  public static class TemplateControls implements Serializable {

    private static final long serialVersionUID = -7496794407355510374L;

    private TemplateProperty property;

    private TemplateConfig config;
  }

  @Data
  public static class TemplateProperty implements Serializable {

    private static final long serialVersionUID = -3429251158540167453L;

    private String control;

    private String id;

    private List<TemplateTitle> title;

    private List<TemplateTitle> placeholder;

    private Integer require;

    @SerializedName("un_print")
    private Integer unPrint;

    private TemplateConfig config;
  }

  @Data
  public static class TemplateConfig implements Serializable {

    private static final long serialVersionUID = 6993937809371277669L;

    private TemplateDate date;

    private TemplateSelector selector;

    private TemplateContact contact;

    private TemplateTable table;

    private TemplateAttendance attendance;

    @SerializedName("vacation_list")
    private TemplateVacation vacationList;

    @SerializedName("tips")
    private TemplateTips tips;

  }

  @Data
  public static class TemplateSelector implements Serializable {
    private static final long serialVersionUID = 4995408101489736881L;

    /**
     * single-单选；multi-多选
     */
    private String type;

    private List<TemplateOption> options;
  }

  @Data
  public static class TemplateOption implements Serializable {

    private static final long serialVersionUID = -7883792668568772078L;

    private String key;

    /**
     * 获取审批模板详情，value为list类型
     * https://developer.work.weixin.qq.com/document/path/91982
     */
    @SerializedName("value")
    private List<TemplateTitle> value;

  }

  public static WxCpOaApprovalTemplateResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpOaApprovalTemplateResult.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
