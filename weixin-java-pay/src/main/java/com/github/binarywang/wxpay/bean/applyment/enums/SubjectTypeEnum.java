package com.github.binarywang.wxpay.bean.applyment.enums;

/**
 * 主体类型枚举类
 * <pre>
 *     商户申请接入时如何选择主体类型？ https://kf.qq.com/faq/180910IBZVnQ180910naQ77b.html
 * </pre>
 *
 * @author zhouyongshen
 * @author 狂龙骄子
 * @since 2023.01.14 新增{@link #SUBJECT_TYPE_GOVERNMENT}
 * @since 2023.09.19 移除SUBJECT_TYPE_MICRO小微主体
 * @since 2024.11.02 回退SUBJECT_TYPE_MICRO小微主体
 * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter11_1_1.shtml">服务商平台>>商户进件>>特约商户进件>>提交申请单>>请求参数>>主体资料>>主体类型</a>
 */
public enum SubjectTypeEnum {
  /**
   * （个体户）：营业执照上的主体类型一般为个体户、个体工商户、个体经营；
   */
  SUBJECT_TYPE_INDIVIDUAL,
  /**
   * (企业）：营业执照上的主体类型一般为有限公司、有限责任公司；
   */
  SUBJECT_TYPE_ENTERPRISE,
  /**
   * （事业单位）：包括国内各类事业单位，如：医疗、教育、学校等单位；
   */
  SUBJECT_TYPE_INSTITUTIONS,
  /**
   * （政府机关）：包括各级、各类政府机关，如机关党委、税务、民政、人社、工商、商务、市监等；
   */
  SUBJECT_TYPE_GOVERNMENT,
  /**
   * （社会组织）：包括社会团体、民办非企业、基金会、基层群众性自治组织、农村集体经济组织等组织。
   */
  SUBJECT_TYPE_OTHERS,
  /**
   * Tips: 特约商户进件不支持小微，但开户意愿提交支持，公用的一个枚举
   * (小微)：无营业执照、免办理工商注册登记的实体商户
   */
  SUBJECT_TYPE_MICRO;
}
