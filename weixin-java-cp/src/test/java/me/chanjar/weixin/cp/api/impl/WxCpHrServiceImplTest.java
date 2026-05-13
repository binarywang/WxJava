package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpHrService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.hr.WxCpHrEmployeeFieldData;
import me.chanjar.weixin.cp.bean.hr.WxCpHrEmployeeFieldDataResp;
import me.chanjar.weixin.cp.bean.hr.WxCpHrEmployeeFieldInfoResp;
import me.chanjar.weixin.cp.bean.hr.WxCpHrEmployeeFieldValue;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 人事助手接口单元测试类.
 *
 * @author <a href="https://github.com/leejoker">leejoker</a> created on 2024-01-01
 */
@Slf4j
@Guice(modules = ApiTestModule.class)
public class WxCpHrServiceImplTest {
  /**
   * The Wx service.
   */
  @Inject
  private WxCpService wxCpService;
  /**
   * The Config storage.
   */
  @Inject
  protected ApiTestModule.WxXmlCpInMemoryConfigStorage configStorage;

  /**
   * 测试获取员工档案字段信息.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetFieldInfo() throws WxErrorException {
    WxCpHrService hrService = this.wxCpService.getHrService();
    // 获取所有字段信息
    WxCpHrEmployeeFieldInfoResp resp = hrService.getFieldInfo(null);
    assertThat(resp).isNotNull();
    assertThat(resp.getGroupList()).isNotNull();
    System.out.println("获取所有字段信息: " + resp);

    // 打印分组信息示例
    if (resp.getGroupList() != null) {
      for (WxCpHrEmployeeFieldInfoResp.FieldGroup group : resp.getGroupList()) {
        System.out.println("分组: " + group.getGroupName() + " (ID: " + group.getGroupId() + "), 字段数量: " +
          (group.getFieldList() != null ? group.getFieldList().size() : 0));
      }
    }
  }

  /**
   * 测试获取指定字段信息.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetFieldInfoWithFilter() throws WxErrorException {
    WxCpHrService hrService = this.wxCpService.getHrService();
    // 获取指定字段信息
    WxCpHrEmployeeFieldInfoResp resp = hrService.getFieldInfo(Collections.singletonList("sys_field_name"));
    assertThat(resp).isNotNull();
    System.out.println("获取指定字段信息: " + resp);
  }

  /**
   * 测试获取员工档案数据.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetEmployeeFieldInfo() throws WxErrorException {
    WxCpHrService hrService = this.wxCpService.getHrService();

    // 先获取字段定义信息，建立字段ID到字段信息的映射
    WxCpHrEmployeeFieldInfoResp fieldInfoResp = hrService.getFieldInfo(null);
    java.util.Map<Integer, me.chanjar.weixin.cp.bean.hr.WxCpHrEmployeeFieldInfo> fieldInfoMap = new java.util.HashMap<>();
    java.util.Map<Integer, String> groupNameMap = new java.util.HashMap<>();
    
    if (fieldInfoResp != null && fieldInfoResp.getGroupList() != null) {
      for (WxCpHrEmployeeFieldInfoResp.FieldGroup group : fieldInfoResp.getGroupList()) {
        if (group.getFieldList() != null) {
          for (me.chanjar.weixin.cp.bean.hr.WxCpHrEmployeeFieldInfo field : group.getFieldList()) {
            if (field.getFieldId() != null) {
              fieldInfoMap.put(field.getFieldId(), field);
              groupNameMap.put(field.getFieldId(), group.getGroupName() + " (ID: " + group.getGroupId() + ")");
            }
          }
        }
      }
    }

    // 获取员工档案数据
    WxCpHrEmployeeFieldDataResp resp = hrService.getEmployeeFieldInfo(
      this.configStorage.getUserId(), true, null);
    assertThat(resp).isNotNull();
    assertThat(resp.getFieldInfoList()).isNotNull();

    // 按组组织字段数据
    java.util.Map<String, java.util.List<WxCpHrEmployeeFieldData>> groupedFields = new java.util.LinkedHashMap<>();
    if (resp.getFieldInfoList() != null) {
      for (WxCpHrEmployeeFieldData field : resp.getFieldInfoList()) {
        String groupName = groupNameMap.get(field.getFieldId());
        if (groupName == null) {
          groupName = "未分组";
        }
        groupedFields.computeIfAbsent(groupName, k -> new java.util.ArrayList<>()).add(field);
      }
    }

    // 按组打印所有字段信息
    System.out.println("=== 员工花名册数据 ===");
    int totalFields = 0;
    for (java.util.Map.Entry<String, java.util.List<WxCpHrEmployeeFieldData>> entry : groupedFields.entrySet()) {
      String groupName = entry.getKey();
      java.util.List<WxCpHrEmployeeFieldData> fields = entry.getValue();
      
      System.out.println("\n分组: " + groupName);
      for (WxCpHrEmployeeFieldData field : fields) {
        Object value = field.getValueString() != null ? field.getValueString() :
          field.getValueUint32() != null ? field.getValueUint32() :
          field.getValueUint64() != null ? field.getValueUint64() :
          field.getValueInt64();

        // 根据字段ID获取字段名称和类型
        me.chanjar.weixin.cp.bean.hr.WxCpHrEmployeeFieldInfo fieldInfo = fieldInfoMap.get(field.getFieldId());
        String fieldName = fieldInfo != null ? fieldInfo.getFieldName() : "未知字段";
        Integer fieldType = fieldInfo != null ? fieldInfo.getFieldType() : field.getValueType();
        
        System.out.println("  - 字段: " + fieldName + " (ID: " + field.getFieldId() + ", 类型: " + fieldType + ")");
        System.out.println("    值: " + value);
        totalFields++;
      }
    }
    System.out.println("\n总计: " + totalFields + " 个字段");
  }

  /**
   * 测试更新员工档案数据.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testUpdateEmployeeFieldInfo() throws WxErrorException {
    WxCpHrService hrService = this.wxCpService.getHrService();

    // 先获取字段信息，找到要更新的字段ID
    WxCpHrEmployeeFieldInfoResp fieldInfoResp = hrService.getFieldInfo(null);
    assertThat(fieldInfoResp).isNotNull();

    // 打印所有可用字段，方便调试
    System.out.println("=== 可用字段列表 ===");
    Integer firstFieldId = null;
    if (fieldInfoResp.getGroupList() != null) {
      for (WxCpHrEmployeeFieldInfoResp.FieldGroup group : fieldInfoResp.getGroupList()) {
        System.out.println("分组: " + group.getGroupName() + " (ID: " + group.getGroupId() + ")");
        if (group.getFieldList() != null) {
          for (me.chanjar.weixin.cp.bean.hr.WxCpHrEmployeeFieldInfo field : group.getFieldList()) {
            System.out.println(
              "  - 字段: " + field.getFieldName()
                + " (ID: " + field.getFieldId()
                + ", 类型: " + field.getFieldType() + ")"
            );
            // 记录第一个字段ID用于测试
            if (firstFieldId == null && field.getFieldId() != null) {
              firstFieldId = field.getFieldId();
            }
          }
        }
      }
    }

    // 使用第一个可用的字段ID进行测试
    assertThat(firstFieldId).as("至少应该有一个可用字段").isNotNull();
    System.out.println("使用字段ID进行更新测试: " + firstFieldId);

    // 创建更新对象
    WxCpHrEmployeeFieldData.FieldItem fieldItem = new WxCpHrEmployeeFieldData.FieldItem();
    fieldItem.setFieldId(firstFieldId);

    // 方式1: 使用 fieldValue 对象（推荐，支持多种类型）
    WxCpHrEmployeeFieldValue fieldValue = new WxCpHrEmployeeFieldValue();
    fieldValue.setTextValue("测试更新值-" + System.currentTimeMillis());
    fieldItem.setFieldValue(fieldValue);

    // 方式2: 直接使用 valueString（简化用法）
    // fieldItem.setValueString("测试姓名");

    hrService.updateEmployeeFieldInfo(this.configStorage.getUserId(), Arrays.asList(fieldItem));
    System.out.println("更新员工档案数据成功");
  }
}
