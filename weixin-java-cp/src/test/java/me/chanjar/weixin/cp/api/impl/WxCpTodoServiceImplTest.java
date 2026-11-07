package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.todo.WxCpTodo;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * 单元测试类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2026-08-11
 */
@Guice(modules = ApiTestModule.class)
public class WxCpTodoServiceImplTest {
  /**
   * The Wx service.
   */
  @Inject
  protected WxCpService wxService;

  private static final String TODO_ID = "17c7d2bd9f20d652840f72f59e796AAA";

  /**
   * Test get.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGet() throws WxErrorException {
    final WxCpTodo todo = this.wxService.getTodoService().get(TODO_ID);
    assertNotNull(todo, "get() 返回的待办对象不应为 null");
    assertEquals(todo.getTodoId(), TODO_ID, "返回的 todo_id 应与请求一致");
  }

  /**
   * Test update status only.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testUpdateStatusOnly() throws WxErrorException {
    this.wxService.getTodoService().update(TODO_ID, 0, null);
    // 更新成功后通过 get() 回查，验证整体状态确实写入
    final WxCpTodo todo = this.wxService.getTodoService().get(TODO_ID);
    assertNotNull(todo, "回查待办不应为 null");
    assertEquals(todo.getStatus(), Integer.valueOf(0), "待办整体状态应为 0（完成）");
  }

  /**
   * Test update with attendees.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testUpdateWithAttendees() throws WxErrorException {
    this.wxService.getTodoService().update(TODO_ID, 1,
      Arrays.asList(
        new WxCpTodo.Attendee().setUserid("lisi").setStatus(0),
        new WxCpTodo.Attendee().setUserid("zhangsan").setStatus(1)
      ));
    // 更新成功后通过 get() 回查，验证参与人列表确实写入
    final WxCpTodo todo = this.wxService.getTodoService().get(TODO_ID);
    assertNotNull(todo, "回查待办不应为 null");
    assertNotNull(todo.getAttendees(), "attendees 列表不应为 null");
    assertEquals(todo.getAttendees().size(), 2, "参与人数量应为 2");
    assertEquals(todo.getStatus(), Integer.valueOf(1), "待办整体状态应为 1（进行中）");
  }
}
