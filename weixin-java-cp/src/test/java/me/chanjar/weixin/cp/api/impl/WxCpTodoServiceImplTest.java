package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.todo.WxCpTodo;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * 单元测试类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2026-08-11
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxCpTodoServiceImplTest {
  /**
   * The Wx service.
   */
  @Inject
  protected WxCpService wxService;

  /**
   * Test get.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGet() throws WxErrorException {
    this.wxService.getTodoService().get("17c7d2bd9f20d652840f72f59e796AAA");
  }

  /**
   * Test update status only.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testUpdateStatusOnly() throws WxErrorException {
    this.wxService.getTodoService().update("17c7d2bd9f20d652840f72f59e796AAA", 0, null);
  }

  /**
   * Test update with attendees.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testUpdateWithAttendees() throws WxErrorException {
    this.wxService.getTodoService().update("17c7d2bd9f20d652840f72f59e796AAA", 1,
      Arrays.asList(
        new WxCpTodo.Attendee().setUserid("lisi").setStatus(2),
        new WxCpTodo.Attendee().setUserid("zhangsan").setStatus(1)
      ));
  }
}
