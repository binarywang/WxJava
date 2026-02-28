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
    assertThat(resp.getFieldInfoList()).isNotNull();
    log.info("获取所有字段信息: {}", resp);
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
    log.info("获取指定字段信息: {}", resp);
  }

  /**
   * 测试获取员工档案数据.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetEmployeeFieldInfo() throws WxErrorException {
    WxCpHrService hrService = this.wxCpService.getHrService();
    WxCpHrEmployeeFieldDataResp resp = hrService.getEmployeeFieldInfo(
      Collections.singletonList(this.configStorage.getUserId()), null);
    assertThat(resp).isNotNull();
    assertThat(resp.getEmployeeFieldList()).isNotNull();
    log.info("获取员工档案数据: {}", resp);
  }

  /**
   * 测试更新员工档案数据.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testUpdateEmployeeFieldInfo() throws WxErrorException {
    WxCpHrService hrService = this.wxCpService.getHrService();
    WxCpHrEmployeeFieldData.FieldItem fieldItem = new WxCpHrEmployeeFieldData.FieldItem();
    fieldItem.setFieldKey("sys_field_name");
    WxCpHrEmployeeFieldValue fieldValue = new WxCpHrEmployeeFieldValue();
    fieldValue.setTextValue("测试姓名");
    fieldItem.setFieldValue(fieldValue);
    hrService.updateEmployeeFieldInfo(this.configStorage.getUserId(), Arrays.asList(fieldItem));
    log.info("更新员工档案数据成功");
  }
}
