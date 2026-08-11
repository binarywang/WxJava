package me.chanjar.weixin.cp.api.impl;

import com.google.common.collect.ImmutableMap;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.WxCpTodoService;
import me.chanjar.weixin.cp.bean.todo.WxCpTodo;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Todo.*;

/**
 * 企业微信待办接口实现类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2026-08-11
 */
@Slf4j
@RequiredArgsConstructor
public class WxCpTodoServiceImpl implements WxCpTodoService {
  private final WxCpService cpService;

  @Override
  public List<WxCpTodo> getDetails(List<String> todoIds) throws WxErrorException {
    final String response = this.cpService.post(this.cpService.getWxCpConfigStorage().getApiUrl(TODO_GET),
      WxCpGsonBuilder.create().toJson(ImmutableMap.of("todo_id_list", todoIds)));
    return WxCpGsonBuilder.create().fromJson(GsonParser.parse(response).get("data_list"),
      new TypeToken<List<WxCpTodo>>() {
      }.getType());
  }

  @Override
  public void update(String todoId, Integer status, List<WxCpTodo.Attendee> attendees) throws WxErrorException {
    final Map<String, Object> param = new HashMap<>(3);
    param.put("todo_id", todoId);
    if (status != null) {
      param.put("todo_status", status);
    }
    if (attendees != null && !attendees.isEmpty()) {
      final List<Map<String, Object>> followers = new ArrayList<>(attendees.size());
      for (WxCpTodo.Attendee attendee : attendees) {
        final Map<String, Object> follower = new HashMap<>(2);
        follower.put("follower_id", attendee.getUserid());
        if (attendee.getStatus() != null) {
          follower.put("status", attendee.getStatus());
        }
        followers.add(follower);
      }
      param.put("follower_list", ImmutableMap.of("followers", followers));
    }

    this.cpService.post(this.cpService.getWxCpConfigStorage().getApiUrl(TODO_UPDATE),
      WxCpGsonBuilder.create().toJson(param));
  }
}
