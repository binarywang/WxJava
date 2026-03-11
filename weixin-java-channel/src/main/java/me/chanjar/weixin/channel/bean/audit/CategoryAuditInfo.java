package me.chanjar.weixin.channel.bean.audit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类目审核信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryAuditInfo implements Serializable {

  private static final long serialVersionUID = -8792967130645424788L;

  /** 一级类目，字符类型，最长不超过10 */
  @JsonProperty("level1")
  private Long level1;

  /** 二级类目，字符类型，最长不超过10 */
  @JsonProperty("level2")
  private Long level2;

  /** 三级类目，字符类型，最长不超过10 */
  @JsonProperty("level3")
  private Long level3;

  /** 新类目树类目ID */
  @JsonProperty("cats_v2")
  private List<CatsV2> catsV2;

  /** 资质材料，图片fileid，图片类型，最多不超过10张 */
  @JsonProperty("certificate")
  private List<String> certificates;

  /** 报备函，图片fileid，图片类型，最多不超过10张 */
  @JsonProperty("baobeihan")
  private List<String> baobeihan;

  /** 经营证明，图片fileid，图片类型，最多不超过10张 */
  @JsonProperty("jingyingzhengming")
  private List<String> jingyingzhengming;

  /** 带货口碑，图片fileid，图片类型，最多不超过10张 */
  @JsonProperty("daihuokoubei")
  private List<String> daihuokoubei;

  /** 入住资质，图片fileid，图片类型，最多不超过10张 */
  @JsonProperty("ruzhuzhizhi")
  private List<String> ruzhuzhizhi;

  /** 经营流水，图片fileid，图片类型，最多不超过10张 */
  @JsonProperty("jingyingliushui")
  private List<String> jingyingliushui;

  /** 补充材料，图片fileid，图片类型，最多不超过10张 */
  @JsonProperty("buchongcailiao")
  private List<String> buchongcailiao;

  /** 经营平台，仅支持taobao，jd，douyin，kuaishou，pdd，other这些取值 */
  @JsonProperty("jingyingpingtai")
  private String jingyingpingtai;

  /** 账号名称 */
  @JsonProperty("zhanghaomingcheng")
  private String zhanghaomingcheng;

  /** 品牌列表，获取类目信息中的attr.is_limit_brand为true时必传 */
  @JsonProperty("brand_list")
  private List<CategoryBrand> brandList;
}
