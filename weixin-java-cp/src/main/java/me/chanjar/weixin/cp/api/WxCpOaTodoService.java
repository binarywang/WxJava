package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.WxCpOaTodo;

/**
 * 企业微信待办接口。
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxCpOaTodoService {

  /**
   * 获取待办详情。
   *
   * @param userId 用户ID
   * @param todoId 待办ID
   * @return 待办详情
   * @throws WxErrorException 异常
   * @see <a href="https://developer.work.weixin.qq.com/document/path/101524">获取待办详情</a>
   */
  WxCpOaTodo getTodo(String userId, String todoId) throws WxErrorException;

  /**
   * 更新待办状态。
   *
   * @param userId 用户ID
   * @param todoId 待办ID
   * @param status 待办状态，0：未完成，1：已完成
   * @return 接口响应
   * @throws WxErrorException 异常
   * @see <a href="https://developer.work.weixin.qq.com/document/path/101531">更新待办状态</a>
   */
  WxCpBaseResp updateTodoStatus(String userId, String todoId, int status) throws WxErrorException;
}
