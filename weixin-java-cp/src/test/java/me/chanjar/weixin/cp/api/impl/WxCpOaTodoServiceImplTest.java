package me.chanjar.weixin.cp.api.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpOaTodoService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.WxCpOaTodo;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import org.mockito.ArgumentCaptor;
import org.testng.annotations.Test;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.TODO_GET;
import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.TODO_UPDATE_STATUS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * 企业微信待办接口实现测试。
 */
public class WxCpOaTodoServiceImplTest {

  @Test
  public void getTodoShouldUseExpectedPathAndParseResponse() throws WxErrorException {
    WxCpService cpService = mock(WxCpService.class);
    WxCpConfigStorage configStorage = mock(WxCpConfigStorage.class);
    when(cpService.getWxCpConfigStorage()).thenReturn(configStorage);
    when(configStorage.getApiUrl(TODO_GET)).thenReturn("https://api.test/oa/todo/get");
    when(cpService.get(eq("https://api.test/oa/todo/get?userid=zhangsan&todoid=todo-1"), eq(null)))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"todo\":{\"todoid\":\"todo-1\",\"creator_userid\":\"lisi\",\"title\":\"日报\",\"content\":\"提交日报\",\"url\":\"https://example.test/todo/1\",\"formid\":\"form-1\",\"create_time\":1700000000,\"finish_time\":1700000100,\"state\":1}}");

    WxCpOaTodoService service = new WxCpOaTodoServiceImpl(cpService);
    WxCpOaTodo todo = service.getTodo("zhangsan", "todo-1");

    assertThat(todo.getTodoId()).isEqualTo("todo-1");
    assertThat(todo.getCreatorUserId()).isEqualTo("lisi");
    assertThat(todo.getState()).isEqualTo(1);
  }

  @Test
  public void updateTodoStatusShouldPostExpectedJson() throws WxErrorException {
    WxCpService cpService = mock(WxCpService.class);
    WxCpConfigStorage configStorage = mock(WxCpConfigStorage.class);
    when(cpService.getWxCpConfigStorage()).thenReturn(configStorage);
    when(configStorage.getApiUrl(TODO_UPDATE_STATUS)).thenReturn("https://api.test/oa/todo/update_status");
    when(cpService.post(eq("https://api.test/oa/todo/update_status"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");

    WxCpOaTodoService service = new WxCpOaTodoServiceImpl(cpService);
    WxCpBaseResp response = service.updateTodoStatus("zhangsan", "todo-1", 1);

    assertThat(response.success()).isTrue();
    ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);
    verify(cpService).post(eq("https://api.test/oa/todo/update_status"), bodyCaptor.capture());
    assertThat(bodyCaptor.getValue()).contains("\"userid\":\"zhangsan\"");
    assertThat(bodyCaptor.getValue()).contains("\"todoid\":\"todo-1\"");
    assertThat(bodyCaptor.getValue()).contains("\"status\":1");
  }
}
