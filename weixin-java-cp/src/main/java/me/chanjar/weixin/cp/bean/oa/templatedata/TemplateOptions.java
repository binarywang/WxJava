package me.chanjar.weixin.cp.bean.oa.templatedata;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * The type Template options.
 *
 * @author gyv123 @163.com
 */
@Data
public class TemplateOptions implements Serializable {

  private static final long serialVersionUID = -7883792668568772078L;

  private String key;

  /**
   * <a href="https://developer.work.weixin.qq.com/document/path/97437#%E9%99%845-%E5%8D%95%E9%80%89%E5%A4%9A%E9%80%89%E6%8E%A7%E4%BB%B6%EF%BC%88control%E5%8F%82%E6%95%B0%E4%B8%BAselector%EF%BC%89">创建审批模板，value为对象类型</a>
   * <a href="https://developer.work.weixin.qq.com/document/path/91982">获取审批模板详情，value为list类型</a>
   **/
  private List<TemplateTitle> value;
}
