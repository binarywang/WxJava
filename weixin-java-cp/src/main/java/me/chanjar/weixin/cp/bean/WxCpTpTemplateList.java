package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 应用模板列表.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2026-01-14
 */
@Data
public class WxCpTpTemplateList extends WxCpBaseResp {

  /**
   * 应用模板列表
   */
  @SerializedName("template_list")
  private List<Template> templateList;

  /**
   * From json wx cp tp template list.
   *
   * @param json the json
   * @return the wx cp tp template list
   */
  public static WxCpTpTemplateList fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpTemplateList.class);
  }

  @Override
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * 应用模板信息
   */
  @Data
  public static class Template implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 模板id
     */
    @SerializedName("template_id")
    private String templateId;

    /**
     * 模板类型
     */
    @SerializedName("template_type")
    private Integer templateType;

    /**
     * 应用名称
     */
    @SerializedName("app_name")
    private String appName;

    /**
     * 应用logo url
     */
    @SerializedName("logo_url")
    private String logoUrl;

    /**
     * 应用简介
     */
    @SerializedName("app_desc")
    private String appDesc;

    /**
     * 应用状态
     */
    @SerializedName("status")
    private Integer status;
  }
}
