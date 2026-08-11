package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.todo.WxCpTodo;

import java.util.List;

/**
 * 企业微信待办接口.
 * <p>
 * 官方文档：
 * <a href="https://developer.work.weixin.qq.com/document/path/101524">获取待办详情</a>，
 * <a href="https://developer.work.weixin.qq.com/document/path/101534">更新待办状态</a>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2026-08-11
 */
public interface WxCpTodoService {
  /**
   * 获取待办详情.
   * <p>
   * 该接口用于获取指定的待办详情，请求参数仅包含必填的 todo_id，响应直接返回单个待办对象。
   * <p>
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/todo/get?access_token=ACCESS_TOKEN
   *
   * @param todoId 待办ID
   * @return 待办详情 wx cp todo
   * @throws WxErrorException the wx error exception
   */
  WxCpTodo get(String todoId) throws WxErrorException;

  /**
   * 更新待办状态.
   * <p>
   * 该接口用于修改指定的待办信息，支持修改待办整体状态（status 字段）、待办参与人及其状态（attendees[].userid / status 字段）。
   * 仅允许修改当前应用创建的待办，不允许修改已删除的待办。
   * <p>
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/todo/update?access_token=ACCESS_TOKEN
   *
   * @param todoId    待办ID
   * @param status    待办整体状态，可不传：0 - 完成；1 - 进行中。为 null 时不修改整体状态
   * @param attendees 待办参与人列表，最多支持20个参与人。为 null 或空时不修改参与人列表
   * @throws WxErrorException the wx error exception
   */
  void update(String todoId, Integer status, List<WxCpTodo.Attendee> attendees) throws WxErrorException;
}
